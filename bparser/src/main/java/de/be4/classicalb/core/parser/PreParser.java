package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.checking.DefinitionPreCollector;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.AExpressionParseUnit;
import de.be4.classicalb.core.parser.node.AFunctionExpression;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.APredicateParseUnit;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PParseUnit;
import de.be4.classicalb.core.preparser.lexer.LexerException;
import de.be4.classicalb.core.preparser.node.Start;
import de.be4.classicalb.core.preparser.node.Token;
import de.be4.classicalb.core.preparser.parser.Parser;
import de.be4.classicalb.core.preparser.parser.ParserException;

public class PreParser {

	private final PushbackReader pushbackReader;
	private boolean debugOutput = false;
	private DefinitionTypes types;

	private final IDefinitions defFileDefinitions = new Definitions();
	private final IFileContentProvider contentProvider;
	private final Set<String> doneDefFiles;
	private final String modelFileName;

	public PreParser(final PushbackReader pushbackReader,
			final IFileContentProvider contentProvider,
			final Set<String> doneDefFiles, final String modelFileName) {
		this.pushbackReader = pushbackReader;
		this.contentProvider = contentProvider;
		this.doneDefFiles = doneDefFiles;
		this.modelFileName = modelFileName;
	}

	public void setDebugOutput(final boolean debugOutput) {
		this.debugOutput = debugOutput;
	}

	public DefinitionTypes parse() throws PreParseException, IOException,
			BException {
		types = new DefinitionTypes();

		final PreLexer preLexer = new PreLexer(pushbackReader);
		preLexer.setDebugOutput(debugOutput);

		final Parser preParser = new Parser(preLexer);
		Start rootNode = null;
		try {
			rootNode = preParser.parse();
		} catch (final ParserException e) {
			throw new PreParseException(e.getToken(), e.getLocalizedMessage());
		} catch (final LexerException e) {
			throw new PreParseException(e.getLocalizedMessage());
		}

		final DefinitionPreCollector collector = new DefinitionPreCollector();
		rootNode.apply(collector);

		evaluateDefintionFiles(collector.getFileDefinitions());
		evaluateTypes(collector.getDefinitions());

		return types;
	}

	private void evaluateDefintionFiles(final List<Token> list)
			throws PreParseException, BException {
		final Set<String> newDoneList = new HashSet<String>(doneDefFiles);
		IDefinitionFileProvider cache = null;
		if (contentProvider instanceof IDefinitionFileProvider) {
			cache = (IDefinitionFileProvider) contentProvider;
		}

		for (final Token fileNameToken : list) {
			try {
				final String fileName = fileNameToken.getText();
				if (doneDefFiles.contains(fileName))
					throw new PreParseException(fileNameToken, "'" + fileName
							+ "' is a circular reference");

				IDefinitions definitions;

				if (cache != null && cache.getDefinitions(fileName) != null) {
					definitions = cache.getDefinitions(fileName);
				} else {
					final String content = contentProvider
							.getFileContent(fileName);
					newDoneList.add(fileName);
					final BParser parser = new BParser(fileName);
					parser.setDoneDefFiles(newDoneList);
					parser.parse(content, debugOutput, contentProvider);
					newDoneList.remove(fileName);

					definitions = parser.getDefinitions();

					if (cache != null) {
						cache.storeDefinition(fileName, definitions);
					}
				}

				defFileDefinitions.addAll(definitions);
				defFileDefinitions.addDefinitionFile(contentProvider
						.getFile(fileName));
				types.addAll(definitions.getTypes());
			} catch (final IOException e) {
				throw new PreParseException(fileNameToken,
						"Definition file cannot be read: "
								+ e.getLocalizedMessage() + " used in "
								+ modelFileName);
			} finally {
			}
		}
	}

	private void evaluateTypes(final Map<Token, Token> definitions)
			throws PreParseException {
		// use linked list as we rely on pop() and push()
		final LinkedList<Token> remainingDefinitions = sortDefinitions(definitions);
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
				type = determineType(definition, defRhs, todoDefs);

				if (type != null) {
					todoDefs.remove(definition.getText());
					oneParsed = true;
					types.addTyping(definition.getText(), type);
				} else {
					currentlyUnparseableDefinitions.push(definition);
				}
			}

			remainingDefinitions.addAll(currentlyUnparseableDefinitions);
			currentlyUnparseableDefinitions.clear();
		}

		if (!remainingDefinitions.isEmpty()) {
			final Token definition = remainingDefinitions.pop();
			// final Token defRhs = definitions.get(definition); //unused
			throw new PreParseException(
					definition,
					"["
							+ definition.getLine()
							+ ","
							+ definition.getPos()
							+ "] expecting wellformed expression, predicate or substitution as DEFINITION body (DEFINITION arguments assumed to be expressions)");
		}
	}

	private LinkedList<Token> sortDefinitions(
			final Map<Token, Token> definitions) {
		// LinkedList will be used as a queue later on!
		// however, the list is needed for collections.sort
		// we can not use a priority queue to sort, as the sorting is done once
		// afterwards, it has to remain unsorted
		final LinkedList<Token> list = new LinkedList<Token>();

		for (final Iterator<Token> iterator = definitions.keySet().iterator(); iterator
				.hasNext();) {
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

	private Definitions.Type determineType(final Token definition,
			final Token rhsToken, Set<String> definitions)
			throws PreParseException {

		final String definitionRhs = rhsToken.getText().trim();

		de.be4.classicalb.core.parser.node.Start expr;
		de.be4.classicalb.core.parser.node.Token errorToken = null;
		try {
			expr = tryParsing(BParser.FORMULA_PREFIX, definitionRhs);
			// Predicate?
			PParseUnit parseunit = expr.getPParseUnit();
			if (parseunit instanceof APredicateParseUnit)
				return IDefinitions.Type.Predicate;
			// Expression or Expression/Substituion (e.g. f(x))?
			AExpressionParseUnit unit = (AExpressionParseUnit) parseunit;

			PreParserIdentifierTypeVisitor visitor = new PreParserIdentifierTypeVisitor(
					definitions);
			unit.apply(visitor);

			if (visitor.isKaboom())
				return null;

			PExpression expression = unit.getExpression();
			if ((expression instanceof AIdentifierExpression)
					|| (expression instanceof AFunctionExpression)
					|| (expression instanceof ADefinitionExpression)) {
				return IDefinitions.Type.ExprOrSubst;
			}

			return IDefinitions.Type.Expression;

		} catch (RhsException e) {
			errorToken = e.getToken();
			try {
				tryParsing(BParser.SUBSTITUTION_PREFIX, definitionRhs);
				return IDefinitions.Type.Substitution;
			} catch (RhsException ex) {
				int line = errorToken.getLine();
				definition.setLine(definition.getLine() + line - 1);
				int pos = errorToken.getPos();
				pos = line == 1 ? pos - 10 : pos;
				definition.setPos(pos);
				return null;
			} catch (de.be4.classicalb.core.parser.lexer.LexerException e1) {
				// should not happen
				return null;
			}

		} catch (BLexerException e) {
			errorToken = e.getLastToken();
			int line = errorToken.getLine();
			int pos = errorToken.getPos();
			pos = line == 1 ? rhsToken.getPos() + pos
					- BParser.FORMULA_PREFIX.length() - 1 : pos;
			line = definition.getLine() + line - 1;
			throw new PreParseException("["
					+ line + "," + pos + "] " + e.getMessage());
		}catch (de.be4.classicalb.core.parser.lexer.LexerException e){
			throw new PreParseException(e.getMessage());
		}

	}

	private de.be4.classicalb.core.parser.node.Start tryParsing(
			final String prefix, final String definitionRhs)
			throws RhsException,
			de.be4.classicalb.core.parser.lexer.LexerException {

		final Reader reader = new StringReader(prefix + " " + definitionRhs);
		final BLexer lexer = new BLexer(new PushbackReader(reader, 99), types); // FIXME
																				// Magic
																				// number!!!!
		lexer.setDebugOutput(debugOutput);
		final de.be4.classicalb.core.parser.parser.Parser parser = new de.be4.classicalb.core.parser.parser.Parser(
				lexer);

		try {
			return parser.parse();
		} catch (final IOException e) {
			// IGNORE
		} catch (de.be4.classicalb.core.parser.parser.ParserException ex) {
			throw new RhsException(ex.getToken());
		}

		return null;
	}

	public IDefinitions getDefFileDefinitions() {
		return defFileDefinitions;
	}

}
