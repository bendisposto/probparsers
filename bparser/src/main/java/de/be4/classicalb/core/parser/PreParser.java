package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.checking.DefintionPreCollector;
import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.preparser.lexer.LexerException;
import de.be4.classicalb.core.preparser.node.Start;
import de.be4.classicalb.core.preparser.node.Token;
import de.be4.classicalb.core.preparser.parser.Parser;
import de.be4.classicalb.core.preparser.parser.ParserException;

public class PreParser {

	private final PushbackReader pushbackReader;
	private boolean debugOutput = false;
	private DefinitionTypes types;

	private final Definitions defFileDefinitions = new Definitions();
	private final List<Pragma> pragmas = new ArrayList<Pragma>();
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

		final DefintionPreCollector collector = new DefintionPreCollector();
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

				Definitions definitions;

				if (cache != null && cache.getDefinitions(fileName) != null) {
					definitions = cache.getDefinitions(fileName);
				} else {
					final String content = contentProvider
							.getFileContent(fileName);
					newDoneList.add(fileName);
					final BParser parser = new BParser(fileName);
					parser.setDoneDefFiles(newDoneList);
					parser.parse(content, debugOutput, contentProvider);
					pragmas.addAll(parser.getPragmas());
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
								+ e.getLocalizedMessage() + " used in"
								+ modelFileName);
			} finally {
			}
		}
	}

	private void evaluateTypes(final Map<Token, Token> definitions)
			throws PreParseException {

		final List<Token> list = sortDefinitions(definitions);

		// use main parser for the rhs of each definition to determine type
		for (final Iterator<Token> iterator = list.iterator(); iterator
				.hasNext();) {
			final Token definition = iterator.next();

			final Token defRhs = definitions.get(definition);
			final Definitions.Type type = determineType(defRhs);

			if (type != null) {
				types.addTyping(definition.getText(), type);
			} else
				throw new PreParseException(
						defRhs,
						"["
								+ defRhs.getLine()
								+ ","
								+ defRhs.getPos()
								+ "] expecting wellformed expression, predicate or substitution as DEFINITION body (DEFINITION arguments assumed to be expressions)");
		}
	}

	private List<Token> sortDefinitions(final Map<Token, Token> definitions) {
		final List<Token> list = new ArrayList<Token>();

		for (final Iterator<Token> iterator = definitions.keySet().iterator(); iterator
				.hasNext();) {
			final Token definition = iterator.next();
			list.add(definition);
		}

		/*
		 * Sort the definitions in order of their appearance in the sourcecode.
		 * TODO check if dependency analysis is possible/better
		 */
		Collections.sort(list, new Comparator<Token>() {
			public int compare(final Token o1, final Token o2) {
				if (o1.getLine() == o2.getLine()) {
					if (o1.getPos() == o2.getPos()) return 0;
					else
						return o1.getPos() - o2.getPos();
				} else
					return o1.getLine() - o2.getLine();
			}
		});
		return list;
	}

	private Definitions.Type determineType(final Token rhsToken) {
		final String definitionRhs = rhsToken.getText().trim();

		if (tryParsing(BParser.PREDICATE_PREFIX, definitionRhs))
			return Definitions.Type.Predicate;

		if (tryParsing(BParser.EXPRESSION_PREFIX, definitionRhs))
			if (tryParsing(BParser.SUBSTITUTION_PREFIX, definitionRhs)) return Definitions.Type.ExprOrSubst;
			else
				return Definitions.Type.Expression;

		if (tryParsing(BParser.SUBSTITUTION_PREFIX, definitionRhs))
			return Definitions.Type.Substitution;

		return null;
	}

	private boolean tryParsing(final String prefix, final String definitionRhs) {

		final Reader reader = new StringReader(prefix + " " + definitionRhs);
		final BLexer lexer = new BLexer(new PushbackReader(reader, 99), types);
		lexer.setDebugOutput(debugOutput);
		final de.be4.classicalb.core.parser.parser.Parser parser = new de.be4.classicalb.core.parser.parser.Parser(
				lexer);

		try {
			parser.parse();
			return true;
		} catch (final de.be4.classicalb.core.parser.parser.ParserException e) {
			// IGNORE
		} catch (final de.be4.classicalb.core.parser.lexer.LexerException e) {
			// IGNORE
		} catch (final IOException e) {
			// IGNORE
		}

		return false;
	}

	public Definitions getDefFileDefinitions() {
		return defFileDefinitions;
	}

	public List<? extends Pragma> getPragmas() {
		return pragmas;
	}
}
