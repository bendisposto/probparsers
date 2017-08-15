package de.be4.classicalb.core.parser.rules;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.AAbstractConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.ABecomesElementOfSubstitution;
import de.be4.classicalb.core.parser.node.AChoiceSubstitution;
import de.be4.classicalb.core.parser.node.AComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.AComputationOperation;
import de.be4.classicalb.core.parser.node.AConstantsMachineClause;
import de.be4.classicalb.core.parser.node.ADeferredSetSet;
import de.be4.classicalb.core.parser.node.ADefineSubstitution;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.AEnumeratedSetSet;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AForLoopSubstitution;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AFunctionOperation;
import de.be4.classicalb.core.parser.node.AGeneralProductExpression;
import de.be4.classicalb.core.parser.node.AGeneralSumExpression;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.ALetExpressionExpression;
import de.be4.classicalb.core.parser.node.ALetPredicatePredicate;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AOperationAttribute;
import de.be4.classicalb.core.parser.node.AOperationCallSubstitution;
import de.be4.classicalb.core.parser.node.AOperatorExpression;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.APredicateAttributeOperationAttribute;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AQuantifiedIntersectionExpression;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.ARecEntry;
import de.be4.classicalb.core.parser.node.ARecordFieldExpression;
import de.be4.classicalb.core.parser.node.AReferencesMachineClause;
import de.be4.classicalb.core.parser.node.ARuleFailSubSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASymbolicComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.ASymbolicLambdaExpression;
import de.be4.classicalb.core.parser.node.AUsesMachineClause;
import de.be4.classicalb.core.parser.node.AVarSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.POperationAttribute;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.util.Utils;

/*
 * This class checks that all extensions for the rules language are used in a correct way
 */
public class RulesMachineChecker extends DepthFirstAdapter {
	private final String fileName;
	private String machineName;
	private final File file;
	private final Map<ARuleOperation, RuleOperation> rulesMap = new HashMap<>();
	private final Map<AComputationOperation, ComputationOperation> computationMap = new HashMap<>();
	private final Map<AFunctionOperation, FunctionOperation> functionMap = new HashMap<>();
	private final ArrayList<CheckException> errorList = new ArrayList<>();
	private final Set<AIdentifierExpression> referencedRuleOperations = new HashSet<>();

	private final KnownIdentifier knownIdentifier = new KnownIdentifier();
	private final LocalIdentifierScope identifierScope = new LocalIdentifierScope();
	private final HashSet<String> definitions = new HashSet<>();
	private final HashMap<String, HashSet<Node>> readIdentifier = new HashMap<>();
	private final List<RulesMachineReference> machineReferences;

	private AbstractOperation currentOperation;
	private final Start start;

	public RulesMachineChecker(final File file, final String fileName, List<RulesMachineReference> machineReferences,
			Start start) {
		this.file = file;
		this.fileName = fileName;
		this.machineReferences = machineReferences;
		this.start = start;
	}

	public void runChecks() throws BCompoundException {
		start.apply(this);
		if (!errorList.isEmpty()) {
			final List<BException> bExceptionList = new ArrayList<>();
			final String filePath = file == null ? "UnknownFile" : file.getAbsolutePath();
			for (CheckException checkException : errorList) {
				final BException bException = new BException(filePath, checkException);
				bExceptionList.add(bException);
			}
			throw new BCompoundException(bExceptionList);
		}
	}

	public Set<RuleOperation> getRuleOperations() {
		return new HashSet<>(this.rulesMap.values());
	}

	public String getFileName() {
		return this.fileName;
	}

	public Set<AIdentifierExpression> getReferencedRuleOperations() {
		return new HashSet<>(this.referencedRuleOperations);
	}

	public List<AbstractOperation> getOperations() {
		List<AbstractOperation> list = new ArrayList<>();
		list.addAll(rulesMap.values());
		list.addAll(computationMap.values());
		list.addAll(functionMap.values());
		return list;
	}

	public RuleOperation getRuleOperation(ARuleOperation aRuleOperation) {
		return this.rulesMap.get(aRuleOperation);
	}

	public ComputationOperation getComputationOperation(AComputationOperation node) {
		return this.computationMap.get(node);
	}

	private boolean isInRule() {
		return currentOperation != null && currentOperation instanceof RuleOperation;
	}

	public FunctionOperation getFunctionOperation(AFunctionOperation funcOp) {
		return this.functionMap.get(funcOp);
	}

	public Map<String, HashSet<Node>> getUnknownIdentifier() {
		HashMap<String, HashSet<Node>> result = new HashMap<>();
		for (Entry<String, HashSet<Node>> entry : readIdentifier.entrySet()) {
			String name = entry.getKey();
			HashSet<Node> nodes = entry.getValue();
			if (!this.knownIdentifier.getKnownIdentifierNames().contains(name) && !this.definitions.contains(name)) {
				result.put(name, nodes);
			}
		}
		return result;
	}

	public Set<String> getGlobalIdentifierNames() {
		return this.knownIdentifier.getKnownIdentifierNames();
	}

	public Set<TIdentifierLiteral> getGlobalIdentifiers() {
		return this.knownIdentifier.getKnownIdentifiers();
	}

	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		if (!node.getParameters().isEmpty()) {
			errorList.add(new CheckException("A RULES_MACHINE must not have any machine parameters", node));
		}
		LinkedList<TIdentifierLiteral> nameList = node.getName();
		if (nameList.size() > 1) {
			errorList.add(new CheckException("Renaming of a RULES_MACHINE name is not allowed.", node));
		}
		final TIdentifierLiteral nameLiteral = nameList.get(0);
		this.machineName = nameLiteral.getText();
	}

	@Override
	public void caseAReferencesMachineClause(AReferencesMachineClause node) {
		// do nothing
	}

	@Override
	public void caseAAbstractConstantsMachineClause(AAbstractConstantsMachineClause node) {
		this.knownIdentifier.addKnownIdentifierList(node.getIdentifiers());
	}

	@Override
	public void caseAConstantsMachineClause(AConstantsMachineClause node) {
		this.knownIdentifier.addKnownIdentifierList(node.getIdentifiers());
	}

	@Override
	public void caseARecordFieldExpression(ARecordFieldExpression node) {
		node.getRecord().apply(this);
		// do not visit the field identifier
	}

	@Override
	public void caseARecEntry(ARecEntry node) {
		// do not visit the field identifier
		node.getValue().apply(this);
	}

	@Override
	public void caseAEnumeratedSetSet(AEnumeratedSetSet node) {
		List<TIdentifierLiteral> copy = new ArrayList<>(node.getIdentifier());
		this.knownIdentifier.addKnownIdentifier(copy.get(0));
		this.knownIdentifier.addKnownIdentifierList(new ArrayList<PExpression>(node.getElements()));
	}

	class OccurredAttributes {
		final HashMap<String, POperationAttribute> map = new HashMap<>();

		public void add(String attrName, POperationAttribute node) {
			if (map.containsKey(attrName)) {
				errorList.add(new CheckException(String.format("%s clause is used more than once in operation '%s'.",
						attrName, currentOperation.getName()), node));
			}
			map.put(attrName, node);
		}
	}

	private void visitOperationAttributes(final LinkedList<POperationAttribute> attributes) {
		OccurredAttributes occurredAttributes = new OccurredAttributes();
		// set operation attributes
		for (POperationAttribute pOperationAttribute : attributes) {
			if (pOperationAttribute instanceof APredicateAttributeOperationAttribute) {
				checkOperationPredicateAttribute(occurredAttributes, pOperationAttribute);
			} else {
				checkOperationExpressionAttribute(occurredAttributes, pOperationAttribute);
			}
		}
	}

	private void checkOperationExpressionAttribute(OccurredAttributes occurredAttributes,
			POperationAttribute pOperationAttribute) throws AssertionError {
		AOperationAttribute attribute = (AOperationAttribute) pOperationAttribute;
		LinkedList<PExpression> arguments = attribute.getArguments();
		String name = attribute.getName().getText();
		occurredAttributes.add(name, pOperationAttribute);
		switch (name) {
		case RulesGrammar.DEPENDS_ON_RULE:
			checkDependsOnRuleAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.DEPENDS_ON_COMPUTATION:
			checkDependsOnComputationAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.RULEID:
			checkRuleIdAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.ERROR_TYPES:
			checkErrorTypesAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.CLASSIFICATION:
			checkClassificationAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.TAGS:
			checkTagsAttribute(pOperationAttribute, arguments);
			return;
		case RulesGrammar.REPLACES:
			checkReplacesAttribute(pOperationAttribute, arguments);
			return;
		default:
			throw new AssertionError("Unexpected operation attribute: " + name);
		}
	}

	private void checkReplacesAttribute(POperationAttribute pOperationAttribute, LinkedList<PExpression> arguments) {
		if (arguments.size() != 1 || !(arguments.get(0) instanceof AIdentifierExpression)) {
			errorList.add(new CheckException("Expected exactly one identifier after REPLACES.", pOperationAttribute));
			return;
		}
		final AIdentifierExpression idExpr = (AIdentifierExpression) arguments.get(0);
		currentOperation.addReplacesIdentifier(idExpr);
		return;
	}

	private void checkTagsAttribute(POperationAttribute pOperationAttribute, LinkedList<PExpression> arguments) {
		final List<String> tags = new ArrayList<>();
		for (PExpression pExpression : arguments) {
			if (pExpression instanceof AIdentifierExpression) {
				final AIdentifierExpression ident = (AIdentifierExpression) pExpression;
				final String identifierAsString = Utils.getTIdentifierListAsString(ident.getIdentifier());
				tags.add(identifierAsString);
			} else if (pExpression instanceof AStringExpression) {
				final AStringExpression stringExpr = (AStringExpression) pExpression;
				tags.add(stringExpr.getContent().getText());
			} else {
				errorList.add(new CheckException("Expected identifier or string after the TAGS attribute.",
						pOperationAttribute));
			}
		}
		currentOperation.addTags(tags);
		return;
	}

	private void checkClassificationAttribute(POperationAttribute pOperationAttribute,
			LinkedList<PExpression> arguments) {
		if (currentOperation instanceof RuleOperation) {
			final RuleOperation rule = (RuleOperation) currentOperation;
			if (arguments.size() == 1 && arguments.get(0) instanceof AIdentifierExpression) {
				AIdentifierExpression identifier = (AIdentifierExpression) arguments.get(0);
				String identifierString = Utils.getTIdentifierListAsString(identifier.getIdentifier());
				rule.setClassification(identifierString);
			} else {
				errorList.add(new CheckException("Expected exactly one identifier after CLASSIFICATION.",
						pOperationAttribute));
			}
		} else {
			errorList.add(new CheckException(
					"CLASSIFICATION is not an attribute of a FUNCTION or COMPUTATION operation.", pOperationAttribute));
		}
		return;
	}

	private void checkErrorTypesAttribute(POperationAttribute pOperationAttribute, LinkedList<PExpression> arguments) {
		if (currentOperation instanceof RuleOperation) {
			final RuleOperation rule = (RuleOperation) currentOperation;
			if (arguments.size() == 1 && arguments.get(0) instanceof AIntegerExpression) {
				AIntegerExpression intExpr = (AIntegerExpression) arguments.get(0);
				rule.setErrrorTypes(intExpr);
			} else {
				errorList.add(
						new CheckException("Expected exactly one integer after ERROR_TYPES.", pOperationAttribute));
			}
		} else {
			errorList.add(new CheckException("ERROR_TYPES is not an attribute of a FUNCTION or COMPUTATION operation.",
					pOperationAttribute));
		}
		return;
	}

	private void checkRuleIdAttribute(POperationAttribute pOperationAttribute, LinkedList<PExpression> arguments) {
		if (currentOperation instanceof RuleOperation) {
			final RuleOperation rule = (RuleOperation) currentOperation;
			if (arguments.size() == 1 && arguments.get(0) instanceof AIdentifierExpression) {
				rule.setRuleId((AIdentifierExpression) arguments.get(0));
			} else {
				errorList.add(new CheckException("Expected exactly one identifier behind RULEID", pOperationAttribute));
			}
		} else {
			errorList.add(new CheckException("RULEID is not an attribute of a FUNCTION or Computation operation",
					pOperationAttribute));
		}
		return;
	}

	private void checkDependsOnComputationAttribute(POperationAttribute pOperationAttribute,
			LinkedList<PExpression> arguments) {
		List<AIdentifierExpression> list = new ArrayList<>();
		for (PExpression pExpression : arguments) {
			if (pExpression instanceof AIdentifierExpression) {
				list.add((AIdentifierExpression) pExpression);
			} else {
				errorList.add(new CheckException("Expected a list of identifiers after DEPENDS_ON_COMPUTATION.",
						pOperationAttribute));
			}
		}
		currentOperation.addAllComputationDependencies(list);
		return;
	}

	private void checkDependsOnRuleAttribute(POperationAttribute pOperationAttribute,
			LinkedList<PExpression> arguments) {
		final List<AIdentifierExpression> list = new ArrayList<>();
		for (final PExpression pExpression : arguments) {
			if (pExpression instanceof AIdentifierExpression) {
				list.add((AIdentifierExpression) pExpression);
			} else {
				errorList.add(new CheckException("Expected a list of identifiers after DEPENDS_ON_RULE.",
						pOperationAttribute));
			}
		}
		currentOperation.addAllRuleDependencies(list);
		return;
	}

	private void checkOperationPredicateAttribute(OccurredAttributes occurredAttributes,
			POperationAttribute pOperationAttribute) throws AssertionError {
		APredicateAttributeOperationAttribute attr = (APredicateAttributeOperationAttribute) pOperationAttribute;
		PPredicate predicate = attr.getPredicate();
		final String attrName = attr.getName().getText();
		occurredAttributes.add(attrName, pOperationAttribute);
		switch (attrName) {
		case RulesGrammar.ACTIVATION:
			if (currentOperation instanceof FunctionOperation) {
				errorList.add(new CheckException("ACTIVATION is not a valid attribute of a FUNCTION operation.",
						pOperationAttribute));
			} else {
				currentOperation.setActivationPredicate(predicate);
			}
			break;
		case RulesGrammar.PRECONDITION:
			if (currentOperation instanceof FunctionOperation) {
				FunctionOperation func = (FunctionOperation) currentOperation;
				func.setPreconditionPredicate(predicate);
			} else {
				errorList.add(new CheckException(
						"PRECONDITION clause is not allowed for a RULE or COMPUTATION operation", pOperationAttribute));
			}
			break;
		case RulesGrammar.POSTCONDITION:
			if (currentOperation instanceof RuleOperation) {
				errorList.add(new CheckException("POSTCONDITION attribute is not allowed for a RULE operation",
						pOperationAttribute));
			} else {
				currentOperation.setPostcondition(predicate);
			}
			break;
		default:
			throw new AssertionError("Unexpected operation attribute: " + attrName);
		}
		predicate.apply(this);
	}

	private boolean containsRule(String name) {
		for (RuleOperation rule : this.rulesMap.values()) {
			if (name.equals(rule.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		currentOperation = new RuleOperation(node.getRuleName(), this.fileName, this.machineName, machineReferences);
		if (containsRule(currentOperation.getName())) {
			errorList.add(new CheckException("Duplicate operation name '" + currentOperation.getName() + "'.",
					node.getRuleName()));
		}
		rulesMap.put(node, (RuleOperation) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getRuleBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void caseAComputationOperation(AComputationOperation node) {
		currentOperation = new ComputationOperation(node.getName(), this.fileName, this.machineName, machineReferences);
		computationMap.put(node, (ComputationOperation) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void caseAFunctionOperation(AFunctionOperation node) {
		currentOperation = new FunctionOperation(node.getName(), this.fileName, this.machineName, machineReferences);
		functionMap.put(node, (FunctionOperation) currentOperation);

		final LinkedList<PExpression> localVariables = new LinkedList<>();
		localVariables.addAll(node.getReturnValues());
		localVariables.addAll(node.getParameters());

		this.identifierScope.createNewScope(localVariables);
		visitOperationAttributes(node.getAttributes());
		node.getBody().apply(this);
		currentOperation = null;
		this.identifierScope.removeScope();
	}

	@Override
	public void outADefineSubstitution(ADefineSubstitution node) {
		// the defined variable should not be used in the TYPE or the VALUE
		// section
		if (currentOperation != null && currentOperation instanceof ComputationOperation) {
			ComputationOperation computationOperation = (ComputationOperation) currentOperation;
			try {
				computationOperation.addDefineVariable(node.getName());
				this.knownIdentifier.addKnownIdentifier(node.getName());
			} catch (CheckException e) {
				this.errorList.add(e);
			}
		}
	}

	@Override
	public void caseAVarSubstitution(AVarSubstitution node) {
		final HashSet<String> variables = new HashSet<>();
		LinkedList<PExpression> identifiers = node.getIdentifiers();
		for (PExpression e : identifiers) {
			if (e instanceof AIdentifierExpression) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				String name = id.getIdentifier().get(0).getText();
				variables.add(name);
			} else {
				errorList.add(new CheckException("There must be a list of identifiers in VAR substitution.", node));
			}
		}
		this.identifierScope.createNewScope(new LinkedList<PExpression>(node.getIdentifiers()));
		node.getSubstitution().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAOperatorExpression(AOperatorExpression node) {
		final String operatorName = node.getName().getText();
		final LinkedList<PExpression> parameters = node.getIdentifiers();
		switch (operatorName) {
		case RulesGrammar.STRING_FORMAT:
			checkStringFormatOperator(node, parameters);
			return;
		case RulesGrammar.GET_RULE_COUNTEREXAMPLES:
			checkGetTuleCounterExamplesOperator(node, parameters);
			return;
		default:
			throw new AssertionError("Unkown expression operator: " + operatorName);
		}
	}

	private void checkStringFormatOperator(AOperatorExpression node, final LinkedList<PExpression> parameters) {
		PExpression stringValue = parameters.get(0);
		if (stringValue instanceof AStringExpression) {
			AStringExpression string = (AStringExpression) stringValue;
			String content = string.getContent().getText();
			int count = (content.length() - content.replace("~w", "").length()) / 2;
			if (count != parameters.size() - 1) {
				this.errorList.add(new CheckException("The number of arguments (" + (parameters.size() - 1)
						+ ") does not match the number of placeholders (" + count + ") in the string.", node));
			}
		}
		LinkedList<PExpression> identifiers = node.getIdentifiers();
		for (PExpression pExpression : identifiers) {
			pExpression.apply(this);
		}
		return;
	}

	private void checkGetTuleCounterExamplesOperator(AOperatorExpression node,
			final LinkedList<PExpression> parameters) {
		// the grammar ensures at least one argument
		if (parameters.size() > 2) {
			this.errorList.add(new CheckException("Invalid number of arguments. Expected one or two arguments.", node));
		}
		PExpression pExpression = node.getIdentifiers().get(0);
		if (!(pExpression instanceof AIdentifierExpression)) {
			this.errorList.add(
					new CheckException("The first argument of GET_RULE_COUNTEREXAMPLES must be an identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) pExpression);
		return;
	}

	@Override
	public void inAAssignSubstitution(AAssignSubstitution node) {
		ArrayList<PExpression> righthand = new ArrayList<>(node.getRhsExpressions());
		for (PExpression pExpression : righthand) {
			pExpression.apply(this);
		}
		List<PExpression> copy = new ArrayList<>(node.getLhsExpression());
		checkThatIdentifiersAreLocalVariables(copy);
	}

	@Override
	public void inAOperationCallSubstitution(AOperationCallSubstitution node) {
		LinkedList<TIdentifierLiteral> opNameList = node.getOperation();
		if (opNameList.size() > 1) {
			errorList.add(new CheckException("Renaming of operation names is not allowed.", node));
		}
		List<PExpression> copy = new ArrayList<>(node.getResultIdentifiers());
		checkThatIdentifiersAreLocalVariables(copy);
		if (currentOperation != null) {
			currentOperation.addFunctionCall(opNameList.get(0));
		}
	}

	private void checkThatIdentifiersAreLocalVariables(List<PExpression> copy) {
		for (PExpression e : copy) {
			if (e instanceof AIdentifierExpression) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				String name = id.getIdentifier().get(0).getText();
				if (!this.identifierScope.contains(name)) {
					errorList.add(new CheckException("Identifier '" + name
							+ "' is not a local variable (VAR). Hence, it can not be assigned here.", id));
				}
			} else {
				errorList.add(new CheckException(
						"There must be an identifier on the left side of the assign substitution. A function assignment 'f(1) := 1' is also not permitted.",
						e));
			}
		}
	}

	@Override
	public void caseAIdentifierExpression(AIdentifierExpression node) {
		List<TIdentifierLiteral> copy = new ArrayList<>(node.getIdentifier());
		if (copy.size() > 1) {
			this.errorList.add(new CheckException("Identifier renaming is not allowed in a RULES_MACHINE.", node));
		}
		final String name = copy.get(0).getText();
		if (currentOperation != null) {
			currentOperation.addReadVariable(node);
		}
		if (!this.identifierScope.contains(name)) {
			addReadIdentifier(node);
		}
	}

	private void addReadIdentifier(AIdentifierExpression node) {
		LinkedList<TIdentifierLiteral> list = node.getIdentifier();
		String name = list.get(0).getText();
		if (this.readIdentifier.containsKey(name)) {
			HashSet<Node> hashSet = readIdentifier.get(name);
			hashSet.add(node);
		} else {
			HashSet<Node> hashSet = new HashSet<>();
			hashSet.add(node);
			readIdentifier.put(name, hashSet);
		}
	}

	@Override
	public void caseAOperatorPredicate(AOperatorPredicate node) {
		final List<PExpression> arguments = new ArrayList<>(node.getIdentifiers());
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RulesGrammar.SUCCEEDED_RULE:
			checkSucceededRuleOperator(node, arguments);
			return;
		case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE:
			checkSucceededRuleErrorTypeOperator(node, arguments);
			return;
		case RulesGrammar.FAILED_RULE:
			checkFailedRuleOperator(node, arguments);
			return;
		case RulesGrammar.FAILED_RULE_ALL_ERROR_TYPES:
			checkFailedRuleAllErrorTypesOperator(node, arguments);
			return;
		case RulesGrammar.FAILED_RULE_ERROR_TYPE:
			checkFailedRuleErrorTypeOperator(node, arguments);
			return;
		case RulesGrammar.NOT_CHECKED_RULE:
			checkNotCheckedRuleOperator(node, arguments);
			return;
		case RulesGrammar.DISABLED_RULE:
			checkDisabledRuleOperator(node, arguments);
			return;
		default:
			throw new AssertionError("Unsupported predicate operator: " + operatorName);
		}
	}

	private void checkDisabledRuleOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 1 && !(arguments.get(0) instanceof AIdentifierExpression)) {
			this.errorList.add(new CheckException(
					"The DISABLED_RULE predicate operator expects exactly one rule identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkNotCheckedRuleOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 1 && !(arguments.get(0) instanceof AIdentifierExpression)) {
			this.errorList.add(new CheckException(
					"The NOT_CHECKED_RULE predicate operator expects exactly one rule identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkFailedRuleErrorTypeOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 2) {
			this.errorList.add(new CheckException(
					"The FAILED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.", node));
			return;
		}
		PExpression pExpression = node.getIdentifiers().get(0);
		if (!(pExpression instanceof AIdentifierExpression)) {
			this.errorList.add(
					new CheckException("The first argument of FAILED_RULE_ERROR_TYPE must be an identifier.", node));
			return;
		}
		PExpression secondArg = node.getIdentifiers().get(1);
		if (!(secondArg instanceof AIntegerExpression)) {
			this.errorList.add(new CheckException(
					"The second argument of FAILED_RULE_ERROR_TYPE must be an integer literal.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkFailedRuleAllErrorTypesOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 1 && !(arguments.get(0) instanceof AIdentifierExpression)) {
			this.errorList.add(new CheckException(
					"The FAILED_RULE_ALL_ERROR_TYPES predicate operator expects exactly one rule identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkFailedRuleOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 1 && !(arguments.get(0) instanceof AIdentifierExpression)) {
			this.errorList.add(new CheckException(
					"The FAILED_RULE predicate operator expects exactly one rule identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkSucceededRuleOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 1 || !(arguments.get(0) instanceof AIdentifierExpression)) {
			this.errorList.add(new CheckException(
					"The SUCCEEDED_RULE predicate operator expects exactly one rule identifier.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	private void checkSucceededRuleErrorTypeOperator(AOperatorPredicate node, final List<PExpression> arguments) {
		if (arguments.size() != 2) {
			this.errorList.add(new CheckException(
					"The SUCCEEDED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.", node));
			return;
		}
		PExpression pExpression = node.getIdentifiers().get(0);
		if (!(pExpression instanceof AIdentifierExpression)) {
			this.errorList.add(
					new CheckException("The first argument of SUCCEEDED_RULE_ERROR_TYPE must be an identifier.", node));
			return;
		}
		PExpression secondArg = node.getIdentifiers().get(1);
		if (!(secondArg instanceof AIntegerExpression)) {
			this.errorList.add(new CheckException(
					"The second argument of SUCCEEDED_RULE_ERROR_TYPE must be an integer value.", node));
			return;
		}
		this.referencedRuleOperations.add((AIdentifierExpression) arguments.get(0));
		return;
	}

	@Override
	public void caseARuleFailSubSubstitution(ARuleFailSubSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_FAIL used outside of a RULE operation", node));
			return;
		}
		if (!node.getIdentifiers().isEmpty() && node.getWhen() == null) {
			this.errorList.add(new CheckException(
					"The WHEN predicate must be provided if RULE_FAIL has at least one parameter.", node));
			return;
		}
		this.identifierScope.createNewScope(new LinkedList<PExpression>(node.getIdentifiers()));
		if (node.getWhen() != null) {
			node.getWhen().apply(this);
		}
		node.getMessage().apply(this);
		this.identifierScope.removeScope();
		checkErrorType(node.getErrorType());
	}

	@Override
	public void caseAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_FORALL used outside of a RULE operation", node));
			return;
		}
		this.identifierScope.createNewScope(new LinkedList<PExpression>(node.getIdentifiers()));
		node.getWhere().apply(this);
		node.getExpect().apply(this);
		node.getMessage().apply(this);
		this.identifierScope.removeScope();
		checkErrorType(node.getErrorType());
	}

	private void checkErrorType(TIntegerLiteral node) {
		if (node != null && currentOperation instanceof RuleOperation) {
			final RuleOperation rule = (RuleOperation) currentOperation;
			int errorType = Integer.parseInt(node.getText());
			if (rule.getNumberOfErrorTypes() == null) {
				errorList.add(new CheckException("Define the number of error types of the rule operation.", node));
			} else if (errorType > rule.getNumberOfErrorTypes()) {
				errorList.add(new CheckException(
						"The error type exceeded the number of error types specified for this rule operation.", node));
			} else if (errorType < 1) {
				errorList.add(new CheckException("The ERROR_TYPE must be a natural number greater than zero.", node));
			}
		}
	}

	// local identifier

	@Override
	public void caseAForLoopSubstitution(AForLoopSubstitution node) {
		node.getSet().apply(this);
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getDoSubst().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseALetSubstitution(ALetSubstitution node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseALetPredicatePredicate(ALetPredicatePredicate node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getAssignment().apply(this);
		node.getPred().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseALetExpressionExpression(ALetExpressionExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getAssignment().apply(this);
		node.getExpr().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAGeneralProductExpression(AGeneralProductExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAGeneralSumExpression(AGeneralSumExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAQuantifiedIntersectionExpression(AQuantifiedIntersectionExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAQuantifiedUnionExpression(AQuantifiedUnionExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseASymbolicComprehensionSetExpression(ASymbolicComprehensionSetExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAComprehensionSetExpression(AComprehensionSetExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicates().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseASymbolicLambdaExpression(ASymbolicLambdaExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicate().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseALambdaExpression(ALambdaExpression node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicate().apply(this);
		node.getExpression().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAExistsPredicate(AExistsPredicate node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getPredicate().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAForallPredicate(AForallPredicate node) {
		this.identifierScope.createNewScope(new LinkedList<>(node.getIdentifiers()));
		node.getImplication().apply(this);
		this.identifierScope.removeScope();
	}

	// definitions
	@Override
	public void caseAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
		final String name = node.getName().getText();
		this.definitions.add(name);
		this.identifierScope.createNewScope(new LinkedList<>(node.getParameters()));
		node.getRhs().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
		final String name = node.getName().getText();
		this.definitions.add(name);
		if ("GOAL".equals(name)) {
			errorList.add(new CheckException("The GOAL definition must be a predicate.", node));
			return;
		}
		this.identifierScope.createNewScope(new LinkedList<>(node.getParameters()));
		node.getRhs().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseASubstitutionDefinitionDefinition(ASubstitutionDefinitionDefinition node) {
		final String name = node.getName().getText();
		this.definitions.add(name);
		if ("GOAL".equals(name)) {
			errorList.add(new CheckException("The GOAL definition must be a predicate.", node));
			return;
		}
		this.identifierScope.createNewScope(new LinkedList<>(node.getParameters()));
		node.getRhs().apply(this);
		this.identifierScope.removeScope();
	}

	@Override
	public void caseADefinitionExpression(ADefinitionExpression node) {
		node.getDefLiteral().apply(this);
		final String defName = node.getDefLiteral().getText();
		if ("READ_XML_FROM_STRING".equals(defName)) {
			if (node.getParameters().size() != 1) {
				errorList.add(new CheckException(
						"The external function 'READ_XML_FROM_STRING' requires exactly one argrument.", node));
				return;
			}
			PExpression pExpression = node.getParameters().get(0);
			if (pExpression instanceof AStringExpression) {
				AStringExpression aStringExpr = (AStringExpression) pExpression;
				TStringLiteral content = aStringExpr.getContent();
				String text = content.getText();
				int xmlStartIndex = text.indexOf("<?");
				if (xmlStartIndex == -1) {
					return;
				}
				String testString = text.substring(0, xmlStartIndex);
				int numberOfNewLines = testString.length() - testString.replace("\n", "").length();
				try {
					InputSource inputSource = new InputSource(new StringReader(text.trim()));
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser saxParser = factory.newSAXParser();
					Locale newLocale = new Locale("en", "GB");
					// Surprisingly, we need both of the following two lines in
					// order to obtain all error messages in English.
					java.util.Locale.setDefault(newLocale);
					saxParser.setProperty("http://apache.org/xml/properties/locale", newLocale);
					saxParser.parse(inputSource, new DefaultHandler());
				} catch (SAXParseException e) {
					final int line = content.getLine() + numberOfNewLines + e.getLineNumber() - 1;
					final int column = (numberOfNewLines == 0 && e.getLineNumber() == 1)
							? content.getPos() + e.getColumnNumber() : e.getColumnNumber();
					TStringLiteral dummy = new TStringLiteral("", line, column);
					String message = e.getMessage();
					errorList.add(new CheckException(message, dummy, e));
				} catch (SAXException e) {
					String message = e.getMessage();
					errorList.add(new CheckException(message, aStringExpr, e));
				} catch (ParserConfigurationException | IOException e) {
					/*
					 * We do nothing. The error is not handled by the parser but
					 * will be handled by the ProB prolog kernel.
					 */
				}

			}

		}
		super.caseADefinitionExpression(node);
	}

	/*
	 * nodes not allowed in a rules machine
	 */

	@Override
	public void caseAChoiceSubstitution(AChoiceSubstitution node) {
		errorList.add(new CheckException("A CHOICE substitution is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseASeesMachineClause(ASeesMachineClause node) {
		errorList.add(new CheckException("The SEES clause is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseAUsesMachineClause(AUsesMachineClause node) {
		errorList.add(new CheckException("The USES clause is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseAAnySubstitution(AAnySubstitution node) {
		errorList.add(new CheckException("The ANY substitution is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseABecomesElementOfSubstitution(ABecomesElementOfSubstitution node) {
		errorList.add(new CheckException(
				"The BecomesElementOf substitution (a,b:(P)) is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseADeferredSetSet(ADeferredSetSet node) {
		errorList.add(new CheckException("Deferred sets are not allowed in a RULES_MACHINE.", node));
	}

	class KnownIdentifier {
		Map<String, TIdentifierLiteral> knownIdentifiers = new HashMap<>();

		public void addKnownIdentifierList(List<PExpression> parameters) {
			for (PExpression pExpression : parameters) {
				this.addKnownIdentifier(pExpression);
			}
		}

		public void addKnownIdentifier(TIdentifierLiteral identifier) {
			knownIdentifiers.put(identifier.getText(), identifier);
		}

		public Set<String> getKnownIdentifierNames() {
			return new HashSet<>(knownIdentifiers.keySet());
		}

		public Set<TIdentifierLiteral> getKnownIdentifiers() {
			return new HashSet<>(this.knownIdentifiers.values());
		}

		public void addKnownIdentifier(PExpression expression) {
			if (expression instanceof AIdentifierExpression) {
				AIdentifierExpression identifier = (AIdentifierExpression) expression;
				LinkedList<TIdentifierLiteral> list = identifier.getIdentifier();
				if (list.size() > 1) {
					errorList.add(new CheckException("Renaming of constants is not allowed.", expression));
					return;
				}
				TIdentifierLiteral tIdentifierLiteral = list.get(0);
				String constantName = tIdentifierLiteral.getText();
				if (this.knownIdentifiers.containsKey(constantName)) {
					errorList.add(new CheckException("Constant already exists.", expression));
					return;
				}
				knownIdentifiers.put(constantName, tIdentifierLiteral);
			} else {
				errorList.add(new CheckException("Identifier expected.", expression));
			}
		}
	}

	class LocalIdentifierScope {
		private final LinkedList<Set<String>> localVariablesScope = new LinkedList<>();

		public void createNewScope(final List<PExpression> parameters) {
			final HashSet<String> set = new HashSet<>();
			for (PExpression expression : parameters) {
				if (expression instanceof AIdentifierExpression) {
					AIdentifierExpression identifier = (AIdentifierExpression) expression;
					if (identifier.getIdentifier().size() > 1) {
						errorList.add(new CheckException("Renaming of identifier is not allowed.", expression));
					}
					TIdentifierLiteral tIdentifierLiteral = identifier.getIdentifier().getFirst();
					String identifierName = tIdentifierLiteral.getText();
					set.add(identifierName);
				} else {
					errorList.add(new CheckException("Identifier expected.", expression));
				}
			}
			localVariablesScope.add(set);
		}

		public void removeScope() {
			this.localVariablesScope.removeLast();
		}

		public boolean contains(String identifier) {
			for (int i = localVariablesScope.size() - 1; i >= 0; i--) {
				Set<String> ids = localVariablesScope.get(i);
				if (ids.contains(identifier)) {
					return true;
				}
			}
			return false;
		}
	}
}
