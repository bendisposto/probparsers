package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.ABooleanFalseExpression;
import de.be4.classicalb.core.parser.node.ABooleanTrueExpression;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIncludesMachineClause;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AMachineMachineVariant;
import de.be4.classicalb.core.parser.node.AMachineReference;
import de.be4.classicalb.core.parser.node.AMultOrCartExpression;
import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.APromotesMachineClause;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ASeqExpression;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.AStringSetExpression;
import de.be4.classicalb.core.parser.node.ATotalFunctionExpression;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.PDefinition;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.PMachineReference;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.be4.classicalb.core.parser.util.NodeCloner;
import static de.be4.classicalb.core.rules.tranformation.ASTBuilder.*;
import de.hhu.stups.sablecc.patch.IToken;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.hhu.stups.sablecc.patch.SourcecodeRange;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class BMachine implements IModel {
	private final String machineName;
	private ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
	private BCompoundException bCompoundException;
	private final Start start;
	private final File file;

	private AAbstractMachineParseUnit parseUnit;
	private ADefinitionsMachineClause definitionsClause;

	public BMachine(String name, File file) {
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add(new TIdentifierLiteral(name));
		AMachineHeader header = new AMachineHeader(nameList, new ArrayList<PExpression>());

		parseUnit = new AAbstractMachineParseUnit(new AMachineMachineVariant(), header,
				new ArrayList<PMachineClause>());
		this.start = new Start(parseUnit, new EOF());
		this.file = file;
		this.machineName = name;
	}

	public Start getStart() {
		return this.start;
	}

	public void setParsingBehaviour(ParsingBehaviour parsingBehaviour) {
		this.parsingBehaviour = parsingBehaviour;
	}

	public void addIncludesClause(String machineName) {
		AIncludesMachineClause includes = new AIncludesMachineClause();
		List<PMachineReference> referencesList = new ArrayList<>();
		List<TIdentifierLiteral> idList = new ArrayList<>();
		idList.add(new TIdentifierLiteral(machineName));
		referencesList.add(new AMachineReference(idList, new ArrayList<PExpression>()));
		includes.setMachineReferences(referencesList);
		this.parseUnit.getMachineClauses().add(includes);
	}

	public void addPromotesClause(List<String> operationList) {
		APromotesMachineClause promotes = new APromotesMachineClause();
		List<PExpression> opList = new ArrayList<>();
		for (String name : operationList) {
			List<TIdentifierLiteral> idList = new ArrayList<>();
			idList.add(new TIdentifierLiteral(name));
			AIdentifierExpression idExpr = new AIdentifierExpression(idList);
			opList.add(idExpr);
		}
		promotes.setMachineNames(opList);
		this.parseUnit.getMachineClauses().add(promotes);

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
		final IPrologTermOutput pout = new PrologTermOutput(output, false);
		printAsProlog(pout, nodeIdMapping);
		pout.flush();
		return output.toString();
	}

	@Override
	public String getMachineName() {
		return machineName;
	}

	@Override
	public List<Reference> getMachineReferences() {
		return new ArrayList<Reference>();
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
			pprinter.setSourcePositions(
					new SourcePositions(new ArrayList<IToken>(), new HashMap<PositionedNode, SourcecodeRange>()));
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

	@Override
	public File getFile() {
		return this.file;
	}

	public void addPreferenceDefinition(String name, boolean bool) {
		if (this.definitionsClause == null) {
			definitionsClause = new ADefinitionsMachineClause(new ArrayList<PDefinition>());
			this.parseUnit.getMachineClauses().add(definitionsClause);
		}
		AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition(new TIdentifierLiteral(name),
				new ArrayList<PExpression>(), bool ? new ABooleanTrueExpression() : new ABooleanFalseExpression());
		definitionsClause.getDefinitions().add(def);
	}

	public void addPreferenceDefinition(String name, int value) {
		if (this.definitionsClause == null) {
			definitionsClause = new ADefinitionsMachineClause(new ArrayList<PDefinition>());
			this.parseUnit.getMachineClauses().add(definitionsClause);
		}
		AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition(new TIdentifierLiteral(name),
				new ArrayList<PExpression>(), new AIntegerExpression(new TIntegerLiteral("" + value)));
		definitionsClause.getDefinitions().add(def);
	}

	private static List<PExpression> createExpressionList(String... names) {
		List<PExpression> list = new ArrayList<>();
		for (String name : names) {
			list.add(createIdentifier(name));
		}
		return list;
	}

	private static AIdentifierExpression createIdentifier(String name) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		return new AIdentifierExpression(list);
	}

	public void addExternalFunctions() {
		final List<PDefinition> externalFunctionsList = new ArrayList<>();
		{
			// TO_STRING(sss) == "0";
			AExpressionDefinitionDefinition toStringDef = new AExpressionDefinitionDefinition();
			toStringDef.setName(new TIdentifierLiteral("TO_STRING"));
			toStringDef.setParameters(createExpressionList("X"));
			toStringDef.setRhs(new AStringExpression(new TStringLiteral("abc")));
			externalFunctionsList.add(toStringDef);
			// EXTERNAL_FUNCTION_TO_STRING(T) == (T --> STRING);
			AExpressionDefinitionDefinition toStringType = new AExpressionDefinitionDefinition();
			toStringType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_TO_STRING"));
			toStringType.setParameters(createExpressionList("T"));
			toStringType.setRhs(new ATotalFunctionExpression(createIdentifier("T"), new AStringSetExpression()));
			externalFunctionsList.add(toStringType);
		}

		{
			// FORMAT_TO_STRING(MyFormatString,ListOfValues) == "0";
			AExpressionDefinitionDefinition formatDef = new AExpressionDefinitionDefinition();
			formatDef.setName(new TIdentifierLiteral("FORMAT_TO_STRING"));
			formatDef.setParameters(createExpressionList("S", "T"));
			formatDef.setRhs(new AStringExpression(new TStringLiteral("abc")));
			externalFunctionsList.add(formatDef);
			// EXTERNAL_FUNCTION_FORMAT_TO_STRING(TO_STRING_TYPE) ==
			// ((STRING*seq(TO_STRING_TYPE)) --> STRING);
			AExpressionDefinitionDefinition formatType = new AExpressionDefinitionDefinition();
			formatType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_FORMAT_TO_STRING"));
			formatType.setParameters(createExpressionList("T"));
			formatType.setRhs(new ATotalFunctionExpression(
					new AMultOrCartExpression(new AStringSetExpression(), new ASeqExpression(createIdentifier("T"))),
					new AStringSetExpression()));
			externalFunctionsList.add(formatType);
		}
		{
			// CHOOSE(XXX) == "a member of XXX";
			AExpressionDefinitionDefinition chooseDef = new AExpressionDefinitionDefinition();
			chooseDef.setName(new TIdentifierLiteral("CHOOSE"));
			chooseDef.setParameters(createExpressionList("X"));
			chooseDef.setRhs(new AStringExpression(new TStringLiteral("a member of X")));
			externalFunctionsList.add(chooseDef);
			// EXTERNAL_FUNCTION_CHOOSE(CHOOSE_TYPE) ==
			// (POW(CHOOSE_TYPE)-->CHOOSE_TYPE)
			AExpressionDefinitionDefinition chooseDefType = new AExpressionDefinitionDefinition();
			chooseDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_CHOOSE"));
			chooseDefType.setParameters(createExpressionList("T"));
			chooseDefType.setRhs(new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")),
					createIdentifier("T")));
			externalFunctionsList.add(chooseDefType);
		}
		if (this.definitionsClause == null) {
			definitionsClause = new ADefinitionsMachineClause(new ArrayList<PDefinition>());
			this.parseUnit.getMachineClauses().add(definitionsClause);
		}
		definitionsClause.getDefinitions().addAll(externalFunctionsList);
	}

	public void addDefinition(PDefinition goalDefinition) {
		if (this.definitionsClause == null) {
			definitionsClause = new ADefinitionsMachineClause(new ArrayList<PDefinition>());
			this.parseUnit.getMachineClauses().add(definitionsClause);
		}
		PDefinition def = (PDefinition) NodeCloner.cloneNode(goalDefinition);
		definitionsClause.getDefinitions().add(def);
	}

	public void addPropertiesPredicates(HashMap<String, String> constantStringValues) {
		if (constantStringValues.size() == 0) {
			return;
		}
		APropertiesMachineClause clause = new APropertiesMachineClause();
		this.parseUnit.getMachineClauses().add(clause);
		List<PPredicate> predList = new ArrayList<>();
		for (Entry<String, String> entry : constantStringValues.entrySet()) {
			AIdentifierExpression identifier = createIdentifier(entry.getKey());
			AStringExpression value = new AStringExpression(new TStringLiteral(entry.getValue()));
			AEqualPredicate equal = new AEqualPredicate(identifier, value);
			predList.add(equal);
		}
		clause.setPredicates(createConjunction(predList));
	}

}
