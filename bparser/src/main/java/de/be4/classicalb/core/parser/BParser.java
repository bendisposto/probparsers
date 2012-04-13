package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.ASTDisplay;
import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.analysis.checking.ClausesCheck;
import de.be4.classicalb.core.parser.analysis.checking.DefinitionCollector;
import de.be4.classicalb.core.parser.analysis.checking.DefinitionUsageCheck;
import de.be4.classicalb.core.parser.analysis.checking.IdentListCheck;
import de.be4.classicalb.core.parser.analysis.checking.PrimedIdentifierCheck;
import de.be4.classicalb.core.parser.analysis.checking.ProverExpressionsCheck;
import de.be4.classicalb.core.parser.analysis.checking.SemanticCheck;
import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.pragma.PragmaLocator;
import de.be4.classicalb.core.parser.analysis.pragma.internal.RawPragma;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.RecursiveMachineLoader;
import de.be4.classicalb.core.parser.analysis.transforming.Couples;
import de.be4.classicalb.core.parser.analysis.transforming.OpSubstitutions;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;
import de.be4.classicalb.core.parser.parser.Parser;
import de.be4.classicalb.core.parser.parser.ParserException;
import de.hhu.stups.sablecc.patch.IToken;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.hhu.stups.sablecc.patch.SourcecodeRange;

public class BParser {

	private static final int APPROXIMATE_TOKEN_LENGTH = 2;
	public static final String EXPRESSION_PREFIX = "#EXPRESSION";
	public static final String PREDICATE_PREFIX = "#PREDICATE";
	public static final String SUBSTITUTION_PREFIX = "#SUBSTITUTION";
	public static final String OPERATION_PATTERN_PREFIX = "#OPPATTERN";
	public static final String CSP_PATTERN_PREFIX = "#CSPPATTERN";

	private Parser parser;
	private SourcePositions sourcePositions;
	private final Definitions definitions = new Definitions();
	private final ParseOptions parseOptions = new ParseOptions();
	private List<Pragma> pragmas = new ArrayList<Pragma>();

	private Set<String> doneDefFiles = new HashSet<String>();
	private final String fileName;
	private IDefinitionFileProvider contentProvider;

	public BParser() {
		this(null);
	}

	public BParser(final String fileName) {
		this.fileName = fileName;
	}
	
	public IDefinitionFileProvider getContentProvider() {
		return contentProvider;
	}


	
	public static void printASTasProlog(final PrintStream out,
			final BParser parser, final File bfile, final Start tree,
			final boolean idention, final boolean withLineInfo, IDefinitionFileProvider contentProvider)
					throws BException {
		final RecursiveMachineLoader rml = new RecursiveMachineLoader(
				bfile.getParent(), contentProvider);
		final SourcePositions positions = withLineInfo ? parser
				.getSourcePositions() : null;
				
				List<Pragma> pragmas = new ArrayList<Pragma>();
				pragmas.addAll(parser.getPragmas());
				
				rml.loadAllMachines(bfile, tree, positions, parser.getDefinitions(),
						pragmas);
				rml.printAsProlog(new PrintWriter(out), idention);
	}

	// private static String getASTasFastProlog(final BParser parser,
	// final File bfile, final Start tree) throws BException {
	// final RecursiveMachineLoader rml = new RecursiveMachineLoader(
	// bfile.getParent());
	// rml.loadAllMachines(bfile, tree, null, parser.getDefinitions());
	// StructuredPrologOutput structuredPrologOutput = new
	// StructuredPrologOutput();
	// rml.printAsProlog(structuredPrologOutput);
	// Collection<PrologTerm> sentences = structuredPrologOutput
	// .getSentences();
	// StructuredPrologOutput output = new StructuredPrologOutput();
	//
	// output.openList();
	// for (PrologTerm term : sentences) {
	// output.printTerm(term);
	// }
	// output.closeList();
	// output.fullstop();
	//
	// FastReadTransformer transformer = new FastReadTransformer(output);
	// return transformer.write();
	// }

	/**
	 * Parses the input file.
	 * 
	 * @see #parse(String, boolean, IFileContentProvider)
	 * @param machine
	 * @param verbose
	 * @return
	 * @throws IOException
	 * @throws BException
	 */
	public Start parseFile(final File machine, final boolean verbose)
			throws IOException, BException {
		contentProvider = new CachingDefinitionFileProvider(
						machine);
		return parseFile(machine, verbose, contentProvider);
	}

	/**
	 * Parses the input file.
	 * 
	 * @see #parse(String, boolean)
	 * @param machine
	 * @param verbose
	 * @return
	 * @throws IOException
	 * @throws BException
	 */
	public Start parseFile(final File machine, final boolean verbose,
			final IFileContentProvider contentProvider) throws IOException,
			BException {
		String content = readFile(machine);
		return parse(content, verbose, contentProvider);
	}

	public final String readFile(final File machine)
			throws FileNotFoundException, IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(machine));

		final StringBuilder builder = new StringBuilder();
		final char[] buffer = new char[1024];
		int read;
		while ((read = inputStreamReader.read(buffer)) >= 0) {
			builder.append(String.valueOf(buffer, 0, read));
		}
		String content = builder.toString();
		return content;
	}

	/**
	 * Use this method, if you only need to parse small inputs. This only gives
	 * the AST or an Exception, but no information about source positions. If
	 * you need those, call the instance method of BParser instead
	 * 
	 * @param input
	 * @return AST of the input
	 * @throws BException
	 */
	public static Start parse(final String input) throws BException {
		BParser parser = new BParser("String Input");
		return parser.parse(input, false, new NoContentProvider());
	}

	/**
	 * Like {@link #parse(String, boolean, IFileContentProvider)}, but with
	 * {@link NoContentProvider} as last parameter, i.e., loading of referenced
	 * files is not enabled.
	 * 
	 * Use {@link #parse(String, boolean, IFileContentProvider)} instead to be
	 * able to control loading of referenced files.
	 * 
	 * @param input
	 * @param debugOutput
	 * @return
	 * @throws BException
	 */
	public Start parse(final String input, final boolean debugOutput)
			throws BException {
		return parse(input, debugOutput, new NoContentProvider());
	}

	/**
	 * Parses the input string.
	 * 
	 * @param input
	 *            The {@link String} to be parsed
	 * @param debugOutput
	 *            output debug messages on standard out?
	 * @param contentProvider
	 *            A {@link IFileContentProvider} that is able to load content of
	 *            referenced files during the parsing process. The content
	 *            provider is used for referenced definition files for example.
	 * @return the root node of the AST
	 * @throws BException
	 *             <p>
	 *             The {@link BException} class stores the actual exception as
	 *             delegate and forwards all method calls to it. So it is save
	 *             for tools to just use this exception if they want to extract
	 *             an error message. If the tools needs to extract additional
	 *             information, such as a sourcecode position or involved tokens
	 *             respectively nodes, it needs to retrieve the delegate
	 *             exception. The {@link BException} class offers a
	 *             {@link BException#getCause()} method for this, which returns
	 *             the delegate exception.
	 *             </p>
	 *             <p>
	 *             Internal exceptions:
	 *             <ul>
	 *             <li> {@link PreParseException}: This exception contains errors
	 *             that occur during the preparsing. If possible it supplies a
	 *             token, where the error occured.</li>
	 *             <li> {@link BLexerException}: If any error occurs in the
	 *             generated or customized lexer a {@link LexerException} is
	 *             thrown. Usually the lexer classes just throw a
	 *             {@link LexerException}. But this class unfortunately does not
	 *             contain any explicit information about the sourcecode
	 *             position where the error occured. Using aspect-oriented
	 *             programming we intercept the throwing of these exceptions to
	 *             replace them by our own exception. In our own exception we
	 *             provide the sourcecode position of the last characters that
	 *             were read from the input.</li>
	 *             <li> {@link BParseException}: This exception is thrown in two
	 *             situations. On the one hand if the parser throws a
	 *             {@link ParserException} we convert it into a
	 *             {@link BParseException}. On the other hand it can be thrown
	 *             if any error is found during the AST transformations after
	 *             the parser has finished. We try to provide a token if a
	 *             single token is involved in the error. Otherwise a
	 *             {@link SourcecodeRange} is provided, which can be used to
	 *             retrieve detailed position information from the
	 *             {@link SourcePositions} (s. {@link #getSourcePositions()}).</li>
	 *             <li> {@link CheckException}: If any problem occurs while
	 *             performing semantic checks, a {@link CheckException} is
	 *             thrown. We provide one or more nodes that are involved in the
	 *             problem. For example, if we find dublicate machine clauses,
	 *             we will list all occurances in the exception.</li>
	 *             </ul>
	 *             </p>
	 */
	public Start parse(final String input, final boolean debugOutput,
			final IFileContentProvider contentProvider) throws BException {
		final Reader reader = new StringReader(input);

		try {
			// PreParsing 
			final DefinitionTypes defTypes = preParsing(debugOutput, reader,
					contentProvider);

			if (debugOutput) {
				System.out.println();
			}

			/*
			 * Main parser
			 */
			final BLexer lexer = new BLexer(new PushbackReader(reader, 99),
					defTypes, input.length() / APPROXIMATE_TOKEN_LENGTH);
			lexer.setDebugOutput(debugOutput);

			parser = new Parser(lexer);
			final Start rootNode = parser.parse();
			final List<IToken> tokenList = lexer.getTokenList();

			/*
			 * Retrieving sourcecode positions which were found by ParserAspect
			 */
			Map<PositionedNode, SourcecodeRange> positions;
			// TODO adjust to new position aspects
			// final de.hhu.stups.sablecc.patch.ParserAspect parserAspect =
			// Aspects
			// .aspectOf(de.hhu.stups.sablecc.patch.ParserAspect.class,
			// parser);

			positions = parser.getMapping();
			// positions = parserAspect.getMapping();
			// parserAspect.printCounting();

			sourcePositions = new SourcePositions(tokenList, positions);

			/*
			 * Collect available definition declarations. Needs to be done now
			 * cause they are needed by the following transformations.
			 */
			final DefinitionCollector collector = new DefinitionCollector(
					defTypes);

			rootNode.apply(collector);
			definitions.addAll(collector.getDefintions());

			// perfom AST tranformations that can't be done by SableCC
			applyAstTransformations(rootNode);

			// perform some semantic checks which are not done in the parser
			performSemanticChecks(rootNode);

			// locate the pragmas

			List<RawPragma> locateTasks = lexer.getPragmas();

			pragmas.addAll( PragmaLocator.locate(rootNode, locateTasks, input));

			return rootNode;
		} catch (final LexerException e) {
			/*
			 * Actually it's supposed to be a BLexerException because the aspect
			 * 'LexerAspect' replaces any LexerException to provide sourcecode
			 * position information in the BLexerException.
			 */
			throw new BException(fileName, e);
		} catch (final ParserException e) {
			final Token token = e.getToken();
			final SourcecodeRange range = sourcePositions == null ? null
					: sourcePositions.getSourcecodeRange(token);
			final String msg = e.getLocalizedMessage();
			throw new BException(fileName, new BParseException(token, range,
					msg));
		} catch (final BParseException e) {
			throw new BException(fileName, e);
		} catch (final IOException e) {
			// shouldn't happen and if, we cannot handle it
			throw new Error("Using Reader failed", e);
		} catch (final PreParseException e) {
			throw new BException(fileName, e);
		} catch (final CheckException e) {
			throw new BException(fileName, e);
		}
	}

	private DefinitionTypes preParsing(final boolean debugOutput,
			final Reader reader, final IFileContentProvider contentProvider)
			throws IOException, PreParseException, BException {
		final PreParser preParser = new PreParser(
				new PushbackReader(reader, 99), contentProvider, doneDefFiles,
				this.fileName);
		preParser.setDebugOutput(debugOutput);
		final DefinitionTypes definitionTypes = preParser.parse();

		/*
		 * Collect the definitions of all referenced definition files and add
		 * them to the internal definitions
		 */
		definitions.addAll(preParser.getDefFileDefinitions());
        pragmas.addAll(preParser.getPragmas());
		reader.reset();
		return definitionTypes;
	}

	private void applyAstTransformations(final Start rootNode) {
		rootNode.apply(new OpSubstitutions(sourcePositions, definitions));
		rootNode.apply(new Couples());

		// TODO more AST transformations?
	}

	private void performSemanticChecks(final Start rootNode)
			throws CheckException {
		final SemanticCheck[] checks = { new ClausesCheck(),
				new IdentListCheck(),
				new DefinitionUsageCheck(getDefinitions()),
				new PrimedIdentifierCheck(), new ProverExpressionsCheck() };
		// TODO apply more checks?

		for (SemanticCheck check : checks) {
			check.setOptions(parseOptions);
			check.runChecks(rootNode);
		}
	}

	public SourcePositions getSourcePositions() {
		return sourcePositions;
	}

	public Definitions getDefinitions() {
		return definitions;
	}

	public static String getBuildRevision() {
		return Utils.getRevisionFromManifest();
	}

	public Set<String> getDoneDefFiles() {
		return doneDefFiles;
	}

	public void setDoneDefFiles(final Set<String> doneDefFiles) {
		this.doneDefFiles = doneDefFiles;
	}

	public ParseOptions getOptions() {
		return parseOptions;
	}

	public int fullParsing(final File bfile, final ParsingBehaviour options,
			final PrintStream out) {

		try {

			// Properties hashes = new Properties();

			if (options.outputFile != null) {
				if (hashesStillValid(options.outputFile)) return 0;
			}

			final long start = System.currentTimeMillis();
			final Start tree = parseFile(bfile, options.verbose);
			final long end = System.currentTimeMillis();

			if (options.printTime) {
				out.println("Time for parsing: " + (end - start) + "ms");
			}

			if (options.printAST) {
				ASTPrinter sw = new ASTPrinter(out);
				tree.apply(sw);
			}

			if (options.displayGraphically) {
				tree.apply(new ASTDisplay());
			}

			if (options.prologOutput) {
				printASTasProlog(out, this, bfile, tree, options.useIndention,
						options.addLineNumbers, contentProvider);
			}
			if (options.fastPrologOutput) {
				// try {
				// String fp = getASTasFastProlog(this, bfile, tree);
				// out.println(fp);
				// } catch (Throwable e) {
				// e.printStackTrace();
				// }
			}
		} catch (final IOException e) {
			if (options.prologOutput) {
				PrologExceptionPrinter.printException(System.err, e,
						bfile.getName());
			} else {
				System.err.println();
				System.err.println("Error reading input file: "
						+ e.getLocalizedMessage());
			}
			return -2;
		} catch (final BException e) {
			if (options.prologOutput) {
				PrologExceptionPrinter.printException(System.err, e);
			} else {
				System.err.println();
				System.err.println("Error parsing input file: "
						+ e.getLocalizedMessage());
			}
			return -3;
		}
		return 0;
	}

	private boolean hashesStillValid(final File outputFile) {
		// File dir = outputFile.getParentFile();
		// Properties hashValues = readHashValues(outputFile, dir);
		// Set<Entry<Object, Object>> entrySet = hashValues.entrySet();
		// for (Entry<Object, Object> entry : entrySet) {
		// String file = (String) entry.getKey();
		// String hash = (String) entry.getValue();
		// File f = new File(dir + File.separator + file);
		// try {
		// if (!(f.exists() || FileDigest.sha(f).equals(hash)))
		// return false;
		// } catch (Exception e) {
		// return false;
		// }
		// }
		return false;
	}

	public List<Pragma> getPragmas() {
		return pragmas;
	}

	// private Properties readHashValues(final File target, final File dir) {
	// String name = target.getName();
	// Properties p = new Properties();
	// String hashfile = name + ".hashes";
	// File hf = new File(dir.getAbsoluteFile() + File.separator + hashfile);
	// if (!hf.exists())
	// return p;
	// try {
	// p.load(new BufferedInputStream(new FileInputStream(hf)));
	// } catch (Exception e) {
	// // ignore
	// }
	// return p;
	// }

}
