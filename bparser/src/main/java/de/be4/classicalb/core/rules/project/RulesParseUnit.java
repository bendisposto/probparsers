package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.CachingDefinitionFileProvider;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.util.Utils;
import de.be4.classicalb.core.rules.tranformation.AbstractOperation;
import de.be4.classicalb.core.rules.tranformation.RulesMachineVisitor;
import de.be4.classicalb.core.rules.tranformation.RulesTransformation;
import de.hhu.stups.sablecc.patch.IToken;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.hhu.stups.sablecc.patch.SourcecodeRange;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class RulesParseUnit implements IModel {
	private String machineName;
	private List<Reference> machineReferences;

	private String content;
	private File machineFile;
	private BCompoundException bCompoundException;
	private boolean debugOuput;
	// private IFileContentProvider contentProvider = new NoContentProvider();
	private ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
	private BParser bParser;
	private Start start;

	private final List<AbstractOperation> operationList = new ArrayList<>();
	private RulesMachineVisitor rulesMachineVisitor;

	public static RulesParseUnit createBParseUnit(String content) {
		RulesParseUnit bParseUnit = new RulesParseUnit();
		bParseUnit.setMachineAsString(content);
		bParseUnit.parse();
		return bParseUnit;
	}

	public RulesParseUnit() {
	}

	public RulesParseUnit(String machineName) {
		this.machineName = machineName;
	}

	public List<IToken> getTokenList() {
		return this.bParser.getSourcePositions().getTokenList();
	}

	public Map<PositionedNode, SourcecodeRange> getPositions() {
		return this.bParser.getPositions();
	}

	public List<AbstractOperation> getOperations() {
		return this.operationList;
	}

	public Start getStart() {
		return this.start;
	}

	public void setMachineAsString(String content) {
		this.content = content;
	}

	public File getFile() {
		return machineFile;
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
			start = bParser.parse(content, debugOuput, new CachingDefinitionFileProvider());

			ReferenceFinder refFinder = new ReferenceFinder(machineFile, start,
					parsingBehaviour.machineNameMustMatchFileName);
			refFinder.findReferencedMachines();
			this.machineReferences = refFinder.getReferences();
			this.machineName = refFinder.getName();
			this.rulesMachineVisitor = new RulesMachineVisitor(bParser.getFileName(), machineReferences, start);
			rulesMachineVisitor.runChecks();
			this.operationList.addAll(rulesMachineVisitor.getOperations());

		} catch (BCompoundException e) {
			// store parser exceptions
			this.bCompoundException = e;
		} catch (CheckException e) {
			if (machineFile != null) {
				bCompoundException = new BCompoundException(new BException(machineFile.getPath(), e));
			} else {
				bCompoundException = new BCompoundException(new BException("UnkownFile", e));
			}
		} catch (BException e) {
			bCompoundException = new BCompoundException(e);
		}
	}

	public void translate() {
		this.translate(null);
	}

	public void translate(List<AbstractOperation> sortedOperationsList) {
		if (bCompoundException != null) {
			return;
		}

		final RulesTransformation ruleTransformation = new RulesTransformation(start, bParser, machineReferences,
				rulesMachineVisitor);
		ruleTransformation.setSortedOperationList(sortedOperationsList);
		ruleTransformation.runTransformation();
	}

	public String getModelAsPrologTerm(NodeIdAssignment nodeIdMapping) {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		final IPrologTermOutput pout = new PrologTermOutput(output, parsingBehaviour.useIndention);
		printAsProlog(pout, nodeIdMapping);
		pout.flush();
		return output.toString();
	}

	public String getResultAsPrologTerm() {
		if (bCompoundException == null) {
			return this.getModelAsPrologTerm(new NodeIdAssignment());
		} else {
			return this.getErrorAsPrologTerm();
		}
	}

	@Override
	public String getMachineName() {
		return machineName;
	}

	@Override
	public List<Reference> getMachineReferences() {
		if (this.machineReferences == null) {
			return new ArrayList<>();
		} else {
			return this.machineReferences;
		}
	}

	public String getErrorAsPrologTerm() {
		assert bCompoundException != null;
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		printExceptionAsProlog(new PrintStream(output));
		try {
			output.flush();
			output.close();
			return output.toString();
		} catch (IOException e) {
			throw new RuntimeException("Unable to close output stream");
		}

	}

	public void printExceptionAsProlog(final PrintStream err) {
		PrologExceptionPrinter.printException(err, bCompoundException, parsingBehaviour.useIndention, false);
	}

	@Override
	public void printAsProlog(PrintWriter out, NodeIdAssignment nodeIdMapping) {
		final IPrologTermOutput pout = new PrologTermOutput(out, false);
		printAsProlog(pout, nodeIdMapping);
	}

	public void printAsProlog(final IPrologTermOutput pout, NodeIdAssignment nodeIdMapping) {
		assert start != null;
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(nodeIdMapping);
		final ASTProlog prolog = new ASTProlog(pout, pprinter);
		pout.openTerm("machine");
		if (parsingBehaviour.addLineNumbers) {
			final SourcePositions src = bParser.getSourcePositions();
			pprinter.setSourcePositions(src);
		}
		start.apply(prolog);
		pout.closeTerm();
		pout.fullstop();
	}

	@Override
	public boolean hasError() {
		if (this.bCompoundException == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public BCompoundException getBExeption() {
		return this.bCompoundException;
	}

}
