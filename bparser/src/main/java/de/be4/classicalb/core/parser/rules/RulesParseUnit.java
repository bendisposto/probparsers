package de.be4.classicalb.core.parser.rules;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.CachingDefinitionFileProvider;
import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.util.Utils;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class RulesParseUnit implements IModel {
	private String machineName;
	private List<RulesMachineReference> machineReferences;

	private String content;
	private File machineFile;
	private BCompoundException bCompoundException;
	private boolean debugOuput;
	private ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
	private BParser bParser;
	private Start start;

	private final List<AbstractOperation> operationList = new ArrayList<>();
	private RulesMachineChecker rulesMachineChecker;
	private RulesReferencesFinder refFinder;

	public RulesParseUnit() {
	}

	public RulesParseUnit(String machineName) {
		this.machineName = machineName;
	}

	public List<AbstractOperation> getOperations() {
		return this.operationList;
	}

	@Override
	public Start getStart() {
		return this.start;
	}

	public String getPath() {
		if (this.machineFile != null) {
			return this.machineFile.getAbsolutePath();
		} else {
			return this.machineName;
		}
	}

	public void setMachineAsString(String content) {
		this.content = content;
	}

	public RulesMachineChecker getRulesMachineChecker() {
		return this.rulesMachineChecker;
	}

	public void setParsingBehaviour(final ParsingBehaviour parsingBehaviour) {
		this.parsingBehaviour = parsingBehaviour;
	}

	public void readMachineFromFile(File file) {
		this.machineFile = file;
		try {
			content = Utils.readFile(file);
			this.machineFile = machineFile.getCanonicalFile();
		} catch (IOException e) {
			bCompoundException = new BCompoundException(new BException(file.getAbsolutePath(), e));
		}
	}

	public void parse() {
		if (this.bCompoundException != null) {
			return;
		}
		try {
			bParser = null;
			if (machineFile != null) {
				bParser = new BParser(machineFile.getPath());
				bParser.setDirectory(machineFile.getParentFile());
			} else {
				bParser = new BParser();
			}
			ParseOptions parseOptions = new ParseOptions();
			parseOptions.setGrammar(RulesGrammar.getInstance());
			bParser.setParseOptions(parseOptions);
			start = bParser.parse(content, debugOuput, new CachingDefinitionFileProvider());
			refFinder = new RulesReferencesFinder(machineFile, start);
			refFinder.findReferencedMachines();

			this.machineReferences = refFinder.getReferences();
			this.machineName = refFinder.getName();
			this.rulesMachineChecker = new RulesMachineChecker(machineFile, bParser.getFileName(), machineReferences,
					start);
			rulesMachineChecker.runChecks();
			this.operationList.addAll(rulesMachineChecker.getOperations());

		} catch (BCompoundException e) {
			// store parser exceptions
			this.bCompoundException = e;
		}
	}

	public void translate() {
		final HashMap<String, AbstractOperation> allOperations = new HashMap<>();
		for (AbstractOperation op : operationList) {
			allOperations.put(op.getName(), op);
		}
		this.translate(allOperations);
	}

	public void translate(Map<String, AbstractOperation> allOperations) {
		if (bCompoundException != null) {
			return;
		}
		final RulesTransformation ruleTransformation = new RulesTransformation(start, bParser, rulesMachineChecker,
				allOperations);
		try {
			ruleTransformation.runTransformation();
		} catch (BCompoundException e) {
			bCompoundException = e;
		}
	}

	@Override
	public String getMachineName() {
		return machineName;
	}

	@Override
	public List<RulesMachineReference> getMachineReferences() {
		if (this.machineReferences == null) {
			return new ArrayList<>();
		} else {
			return this.machineReferences;
		}
	}

	public void printPrologOutput(final PrintStream out, final PrintStream err) {
		if (this.bCompoundException != null) {
			this.printExceptionAsProlog(err);
		} else {
			final IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(out), false);
			this.printAsProlog(pout, new NodeIdAssignment());
			pout.flush();
		}

	}

	public void printExceptionAsProlog(final PrintStream err) {
		PrologExceptionPrinter.printException(err, bCompoundException, parsingBehaviour.isUseIndention(), false);
	}

	@Override
	public void printAsProlog(final IPrologTermOutput pout, NodeIdAssignment nodeIdMapping) {
		assert start != null;
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(nodeIdMapping);
		pprinter.printSourcePositions(parsingBehaviour.isAddLineNumbers());
		final ASTProlog prolog = new ASTProlog(pout, pprinter);
		pout.openTerm("machine");
		start.apply(prolog);
		pout.closeTerm();
		pout.fullstop();
	}

	@Override
	public boolean hasError() {
		return this.bCompoundException != null;
	}

	@Override
	public BCompoundException getCompoundException() {
		return this.bCompoundException;
	}

}
