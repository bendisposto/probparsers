package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.be4.classicalb.core.parser.analysis.checking.DefinitionPreCollector;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.AExpressionParseUnit;
import de.be4.classicalb.core.parser.node.AFunctionExpression;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.APredicateParseUnit;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PParseUnit;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.util.Utils;
import de.be4.classicalb.core.preparser.lexer.LexerException;
import de.be4.classicalb.core.preparser.node.Start;
import de.be4.classicalb.core.preparser.node.Token;
import de.be4.classicalb.core.preparser.parser.Parser;
import de.be4.classicalb.core.preparser.parser.ParserException;

public class PreParser {

	private final PushbackReader pushbackReader;
	private boolean debugOutput = false;

	private final DefinitionTypes definitionTypes;
	private final IDefinitions defFileDefinitions;
	private final ParseOptions parseOptions;
	private final IFileContentProvider contentProvider;
	private final List<String> doneDefFiles;
	private final String modelFileName;
	private final File directory;

	public PreParser(final PushbackReader pushbackReader, final IFileContentProvider contentProvider,
			final List<String> doneDefFiles, final String modelFileName, final File directory,
			ParseOptions parseOptions, IDefinitions definitions) {
		this.pushbackReader = pushbackReader;
		this.contentProvider = contentProvider;
		this.doneDefFiles = doneDefFiles;
		this.modelFileName = modelFileName;
		this.directory = directory;
		this.parseOptions = parseOptions;
		this.defFileDefinitions = definitions;
		this.definitionTypes = new DefinitionTypes();
		definitionTypes.addAll(definitions.getTypes());
	}

	public void setDebugOutput(final boolean debugOutput) {
		this.debugOutput = debugOutput;
	}

	public void parse() throws PreParseException, IOException, BException, BCompoundException {
		final PreLexer preLexer = new PreLexer(pushbackReader);

		final Parser preParser = new Parser(preLexer);
		Start rootNode = null;
		try {
			rootNode = preParser.parse();
		} catch (final ParserException e) {
			if (e.getToken() instanceof de.be4.classicalb.core.preparser.node.TDefinitions) {
				final Token errorToken = e.getToken();
				final String message = "[" + errorToken.getLine() + "," + errorToken.getPos() + "] "
						+ "Clause 'DEFINITIONS' is used more than once";
				throw new PreParseException(e.getToken(), message);
			} else {
				throw new PreParseException(e.getToken(), e.getLocalizedMessage());
			}
		} catch (final LexerException e) {
			throw new PreParseException(e.getLocalizedMessage());
		}

		final DefinitionPreCollector collector = new DefinitionPreCollector();
		rootNode.apply(collector);

		evaluateDefinitionFiles(collector.getFileDefinitions());

		List<Token> sortedDefinitionList = sortDefinitionsByTopologicalOrderAndCheckForCycles(
				collector.getDefinitions());

		evaluateTypes(sortedDefinitionList, collector.getDefinitions());

	}

	private void evaluateDefinitionFiles(final List<Token> list)
			throws PreParseException, BException, BCompoundException {

		IDefinitionFileProvider cache = null;
		if (contentProvider instanceof IDefinitionFileProvider) {
			cache = (IDefinitionFileProvider) contentProvider;
		}

		for (final Token fileNameToken : list) {
			final List<String> newDoneList = new ArrayList<String>(doneDefFiles);
			try {
				final String fileName = fileNameToken.getText();
				if (doneDefFiles.contains(fileName)) {
					StringBuilder sb = new StringBuilder();
					for (String string : doneDefFiles) {
						sb.append(string).append(" -> ");
					}
					sb.append(fileName);
					throw new PreParseException(fileNameToken,
							"Cyclic references in definition files: " + sb.toString());
				}

				IDefinitions definitions;
				if (cache != null && cache.getDefinitions(fileName) != null) {
					definitions = cache.getDefinitions(fileName);
				} else {
					final String content = contentProvider.getFileContent(directory, fileName);
					newDoneList.add(fileName);
					final File file = contentProvider.getFile(directory, fileName);
					String filePath = fileName;
					if (file != null) {
						filePath = file.getCanonicalPath();
					}
					final BParser parser = new BParser(filePath, parseOptions);
					parser.setDirectory(directory);
					parser.setDoneDefFiles(newDoneList);
					parser.setDefinitions(new Definitions(file));
					parser.parse(content, debugOutput, contentProvider);

					definitions = parser.getDefinitions();

					if (cache != null) {
						cache.storeDefinition(fileName, definitions);
					}
				}
				defFileDefinitions.addDefinitions(definitions);
				definitionTypes.addAll(definitions.getTypes());
			} catch (final IOException e) {
				throw new PreParseException(fileNameToken, "Definition file cannot be read: " + e.getLocalizedMessage()
				// + " used in " + modelFileName
				);
			} finally {
			}
		}
	}

	private void evaluateTypes(final List<Token> sortedDefinitionList, final Map<Token, Token> definitions)
			throws PreParseException {
		// use linked list as we rely on pop() and push()
		final LinkedList<Token> remainingDefinitions = new LinkedList<>(sortedDefinitionList);
		final LinkedList<Token> currentlyUnparseableDefinitions = new LinkedList<Token>();
		Set<String> todoDefs = new HashSet<String>();
		for (Token token : remainingDefinitions) {
			todoDefs.add(token.getText());
		}
		// use main parser for the rhs of each definition to determine type
		// if a definition can not be typed this way, it may be due to another
		// definition that is not yet parser (because it appears later in the
		// source code)
		// in this case, the definition is appended to the list again
		// the algorithm terminates if the queue is empty or if no definition
		// has been parsed
		boolean oneParsed = true;
		while (oneParsed) {
			oneParsed = false;

			while (!remainingDefinitions.isEmpty()) {
				final Token definition = remainingDefinitions.pop();

				final Token defRhs = definitions.get(definition);
				Definitions.Type type = null;
				DefinitionType definitionType = determineType(definition, defRhs, todoDefs);
				type = definitionType.type;
				if (type != null) {
					todoDefs.remove(definition.getText());
					oneParsed = true;
					definitionTypes.addTyping(definition.getText(), type);
					// types.addTyping(definition.getText(), type);
				} else {
					currentlyUnparseableDefinitions.push(definition);
				}
			}

			remainingDefinitions.addAll(currentlyUnparseableDefinitions);
			currentlyUnparseableDefinitions.clear();
		}

		if (!remainingDefinitions.isEmpty()) {
			final Token definition = remainingDefinitions.pop();
			final Token defRhs = definitions.get(definition);
			DefinitionType definitionType = determineType(definition, defRhs, todoDefs);
			if (definitionType.errorMessage != null) {
				throw new PreParseException(definitionType.errorMessage + " in file: " + modelFileName);
				// throw new BParseException(definitionType.errorToken,
				// definitionType.errorMessage + " in file: "
				// + modelFileName);
			} else {
				// fall back message
				throw new PreParseException(definition, "[" + definition.getLine() + "," + definition.getPos()
						+ "] expecting wellformed expression, predicate or substitution as DEFINITION body (DEFINITION arguments assumed to be expressions)");
			}
		}
	}

	private List<Token> sortDefinitionsByTopologicalOrderAndCheckForCycles(Map<Token, Token> definitions)
			throws PreParseException {
		final Set<String> definitionNames = new HashSet<String>();
		final Map<String, Token> definitionMap = new HashMap<String, Token>();
		for (Token token : definitions.keySet()) {
			final String definitionName = token.getText();
			definitionNames.add(definitionName);
			definitionMap.put(definitionName, token);
		}
		Map<String, Set<String>> dependencies = determineDependencies(definitionNames, definitions);
		List<String> sortedDefinitionNames = Utils.sortByTopologicalOrder(dependencies);
		if (sortedDefinitionNames.size() < definitionNames.size()) {
			Set<String> remaining = new HashSet<String>(definitionNames);
			remaining.removeAll(sortedDefinitionNames);
			List<String> cycle = Utils.determineCycle(remaining, dependencies);
			StringBuilder sb = new StringBuilder();
			for (Iterator< String>iterator = cycle.iterator(); iterator.hasNext();) {
				sb.append(iterator.next());
				if (iterator.hasNext()) {
					sb.append(" -> ");
				}
			}
			final Token firstDefinitionToken = definitionMap.get(cycle.get(0));
			throw new PreParseException(firstDefinitionToken, "Cyclic references in definitions: " + sb.toString());
		} else {
			List<Token> sortedDefinitionTokens = new ArrayList<>();
			for (String name : sortedDefinitionNames) {
				sortedDefinitionTokens.add(definitionMap.get(name));
			}
			return sortedDefinitionTokens;
		}

	}

	private Map<String, Set<String>> determineDependencies(Set<String> definitionNames, Map<Token, Token> definitions)
			throws PreParseException {
		HashMap<String, Set<String>> dependencies = new HashMap<>();
		for (Entry<Token, Token> entry : definitions.entrySet()) {
			Token nameToken = entry.getKey();
			Token rhsToken = entry.getValue();
			// The FORMULA_PREFIX is needed to switch the lexer state from
			// section to normal. Note, that we do not parse the right hand side
			// of the definition here. Hence FORMULA_PREFIX has no further
			// meaning and substitutions can also be handled by the lexer.
			final Reader reader = new StringReader(BParser.FORMULA_PREFIX + "\n" + rhsToken.getText());

			final BLexer lexer = new BLexer(new PushbackReader(reader, BLexer.PUSHBACK_BUFFER_SIZE),
					new DefinitionTypes());
			lexer.setParseOptions(parseOptions);
			Set<String> set = new HashSet<String>();
			de.be4.classicalb.core.parser.node.Token next = null;
			try {
				next = lexer.next();
				while (!(next instanceof EOF)) {
					if (next instanceof TIdentifierLiteral) {
						TIdentifierLiteral id = (TIdentifierLiteral) next;
						String name = id.getText();
						if (definitionNames.contains(name)) {
							set.add(name);
						}
					}
					next = lexer.next();
				}
			} catch (IOException e) {
			} catch (BLexerException e) {
				de.be4.classicalb.core.parser.node.Token errorToken = e.getLastToken();
				final String newMessage = determineNewErrorMessageWithCorrectedPositionInformations(nameToken, rhsToken,
						errorToken, e.getMessage());
				throw new PreParseException(newMessage);
			} catch (de.be4.classicalb.core.parser.lexer.LexerException e) {
				final String newMessage = determineNewErrorMessageWithCorrectedPositionInformationsWithoutToken(
						nameToken, rhsToken, e.getMessage());
				throw new PreParseException(newMessage);
			}
			dependencies.put(nameToken.getText(), set);
		}
		return dependencies;
	}

	@SuppressWarnings("unused")
	private LinkedList<Token> sortDefinitionsByPosition(final Map<Token, Token> definitions) {
		// LinkedList will be used as a queue later on!
		// however, the list is needed for collections.sort
		// we can not use a priority queue to sort, as the sorting is done once
		// afterwards, it has to remain unsorted
		final LinkedList<Token> list = new LinkedList<Token>();
		for (final Iterator< Token>iterator = definitions.keySet().iterator(); iterator.hasNext();) {
			final Token definition = iterator.next();
			list.add(definition);

		}
		/*
		 * Sort the definitions in order of their appearance in the sourcecode.
		 * Dependencies in between definitions are handled later when computing
		 * there type
		 */
		Collections.sort(list, new Comparator<Token>() {
			public int compare(final Token o1, final Token o2) {
				if (o1.getLine() == o2.getLine()) {
					if (o1.getPos() == o2.getPos())
						return 0;
					else
						return o1.getPos() - o2.getPos();
				} else
					return o1.getLine() - o2.getLine();
			}
		});
		return list;
	}

	class DefinitionType {
		Definitions.Type type;
		String errorMessage;
		de.be4.classicalb.core.parser.node.Token errorToken;

		DefinitionType() {

		}

		DefinitionType(Definitions.Type t, de.be4.classicalb.core.parser.node.Token n) {
			this.type = t;
			this.errorToken = n;
		}

		DefinitionType(Definitions.Type t) {
			this.type = t;
		}

		DefinitionType(String errorMessage, de.be4.classicalb.core.parser.node.Token t) {
			this.errorMessage = errorMessage;
			this.errorToken = t;
		}
	}

	private DefinitionType determineType(final Token definition, final Token rhsToken,
			final Set<String> untypedDefinitions) throws PreParseException {

		final String definitionRhs = rhsToken.getText();

		de.be4.classicalb.core.parser.node.Start start;
		de.be4.classicalb.core.parser.node.Token errorToken = null;
		try {
			start = tryParsing(BParser.FORMULA_PREFIX, definitionRhs);
			// Predicate?
			PParseUnit parseunit = start.getPParseUnit();
			if (parseunit instanceof APredicateParseUnit) {
				return new DefinitionType(IDefinitions.Type.Predicate);
			}

			// Expression or Expression/Substituion (e.g. f(x))?
			AExpressionParseUnit expressionParseUnit = (AExpressionParseUnit) parseunit;

			PreParserIdentifierTypeVisitor visitor = new PreParserIdentifierTypeVisitor(untypedDefinitions);
			expressionParseUnit.apply(visitor);

			if (visitor.isKaboom()) {
				return new DefinitionType();
			}

			PExpression expression = expressionParseUnit.getExpression();
			if ((expression instanceof AIdentifierExpression) || (expression instanceof AFunctionExpression)
					|| (expression instanceof ADefinitionExpression)) {
				return new DefinitionType(IDefinitions.Type.ExprOrSubst);
			}

			return new DefinitionType(IDefinitions.Type.Expression);

		} catch (de.be4.classicalb.core.parser.parser.ParserException e) {
			errorToken = e.getToken();
			try {
				tryParsing(BParser.SUBSTITUTION_PREFIX, definitionRhs);
				return new DefinitionType(IDefinitions.Type.Substitution, errorToken);
			} catch (de.be4.classicalb.core.parser.parser.ParserException ex) {
				final de.be4.classicalb.core.parser.node.Token errorToken2 = ex.getToken();
				if (errorToken.getLine() > errorToken2.getLine() || (errorToken.getLine() == errorToken2.getLine()
						&& errorToken.getPos() >= errorToken2.getPos())) {
					final String newMessage = determineNewErrorMessageWithCorrectedPositionInformations(definition,
							rhsToken, errorToken, e.getMessage());
					return new DefinitionType(newMessage, errorToken);
				} else {
					final String newMessage = determineNewErrorMessageWithCorrectedPositionInformations(definition,
							rhsToken, errorToken2, ex.getMessage());
					return new DefinitionType(newMessage, errorToken2);
				}
			} catch (BLexerException e1) {
				errorToken = e1.getLastToken();
				final String newMessage = determineNewErrorMessageWithCorrectedPositionInformations(definition,
						rhsToken, errorToken, e.getMessage());
				throw new PreParseException(newMessage);
			} catch (de.be4.classicalb.core.parser.lexer.LexerException e3) {
				throw new PreParseException(determineNewErrorMessageWithCorrectedPositionInformationsWithoutToken(
						definition, rhsToken, e3.getMessage()));
			}
		} catch (BLexerException e) {
			errorToken = e.getLastToken();
			final String newMessage = determineNewErrorMessageWithCorrectedPositionInformations(definition, rhsToken,
					errorToken, e.getMessage());
			throw new PreParseException(newMessage);
		} catch (de.be4.classicalb.core.parser.lexer.LexerException e) {
			throw new PreParseException(determineNewErrorMessageWithCorrectedPositionInformationsWithoutToken(
					definition, rhsToken, e.getMessage()));
		}

	}

	private String determineNewErrorMessageWithCorrectedPositionInformations(Token definition, Token rhsToken,
			de.be4.classicalb.core.parser.node.Token errorToken, String oldMessage) {
		// the parsed string starts in the second line, e.g. #formula\n ...
		int line = errorToken.getLine();
		int pos = errorToken.getPos();
		pos = line == 2 ? rhsToken.getPos() + pos - 1 : pos;
		line = definition.getLine() + line - 2;
		final int index = oldMessage.indexOf("]");
		String message = oldMessage.substring(index + 1);
		if (oldMessage.contains("expecting: EOF")) {
			message = "expecting end of definition";
		}
		return "[" + line + "," + pos + "]" + message;
	}

	private String determineNewErrorMessageWithCorrectedPositionInformationsWithoutToken(Token definition,
			Token rhsToken, String oldMessage) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher((CharSequence) oldMessage);
		m.find();
		int line = Integer.parseInt(m.group());
		m.find();
		int pos = Integer.parseInt(m.group());
		pos = line == 2 ? rhsToken.getPos() + pos - 1 : pos;
		line = definition.getLine() + line - 2;
		final int index = oldMessage.indexOf("]");
		String message = oldMessage.substring(index + 1);
		return "[" + line + "," + pos + "]" + message;
	}

	private de.be4.classicalb.core.parser.node.Start tryParsing(final String prefix, final String definitionRhs)
			throws de.be4.classicalb.core.parser.lexer.LexerException,
			de.be4.classicalb.core.parser.parser.ParserException {

		final Reader reader = new StringReader(prefix + "\n" + definitionRhs);
		final BLexer lexer = new BLexer(new PushbackReader(reader, BLexer.PUSHBACK_BUFFER_SIZE), this.definitionTypes);
		lexer.setParseOptions(parseOptions);
		final de.be4.classicalb.core.parser.parser.Parser parser = new SabbleCCBParser(lexer);
		try {
			return parser.parse();
		} catch (final IOException e) {
			// IGNORE
			return null;
		}
	}

	public IDefinitions getDefFileDefinitions() {
		return defFileDefinitions;
	}

	public DefinitionTypes getDefinitionTypes() {
		return this.definitionTypes;
	}

}
