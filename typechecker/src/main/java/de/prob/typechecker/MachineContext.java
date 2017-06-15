package de.prob.typechecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;


import de.prob.typechecker.exceptions.ScopeException;
import de.be4.classicalb.core.parser.util.Utils;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AAssertionsMachineClause;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.AComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.AConcreteVariablesMachineClause;
import de.be4.classicalb.core.parser.node.AConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AConstraintsMachineClause;
import de.be4.classicalb.core.parser.node.ADeferredSetSet;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.ADefinitionPredicate;
import de.be4.classicalb.core.parser.node.ADefinitionSubstitution;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AEnumeratedSetSet;
import de.be4.classicalb.core.parser.node.AEventBComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.AFunctionExpression;
import de.be4.classicalb.core.parser.node.AGeneralProductExpression;
import de.be4.classicalb.core.parser.node.AGeneralSumExpression;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AOpSubstitution;
import de.be4.classicalb.core.parser.node.AOperation;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateParseUnit;
import de.be4.classicalb.core.parser.node.APrimedIdentifierExpression;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.AQuantifiedIntersectionExpression;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.ARecEntry;
import de.be4.classicalb.core.parser.node.ARecordFieldExpression;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.ASetsContextClause;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PDefinition;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.PMachineHeader;
import de.be4.classicalb.core.parser.node.POperation;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.PSet;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class MachineContext extends DepthFirstAdapter {

	private String machineName;
	private final Start start;

	private final ArrayList<LTLFormulaVisitor> ltlVisitors;
	private PPredicate constantsSetupPredicate;
	private boolean hasConstants = false;

	private final LinkedHashMap<String, Node> machineSetParameter;
	private final LinkedHashMap<String, Node> machineScalarParameter;
	private final LinkedHashMap<String, Node> deferredSets;
	private final LinkedHashMap<String, Node> enumeratedSets;
	private final LinkedHashMap<String, Node> enumValues;
	private final LinkedHashMap<String, Node> variables;
	private final LinkedHashMap<String, Node> constants;
	private final LinkedHashMap<String, Node> definitions;
	private final LinkedHashMap<String, Node> operations;
	private final LinkedHashMap<String, AIdentifierExpression> seenMachines;

	private PMachineHeader header;
	private AAbstractMachineParseUnit abstractMachineParseUnit;
	private AConstraintsMachineClause constraintMachineClause;
	private ASeesMachineClause seesMachineClause;
	private ASetsContextClause setsMachineClause;
	private ADefinitionsMachineClause definitionMachineClause;
	private APropertiesMachineClause propertiesMachineClause;
	private AInvariantMachineClause invariantMachineClause;
	private AInitialisationMachineClause initialisationMachineClause;
	private AOperationsMachineClause operationMachineClause;
	private AAssertionsMachineClause assertiondMachineClause;

	private ArrayList<LinkedHashMap<String, Node>> contextTable;

	protected final Hashtable<Node, Node> referencesTable;

	public MachineContext(final String machineName, final Start start) {
		this.start = start;
		this.machineName = machineName;
		this.referencesTable = new Hashtable<Node, Node>();
		this.ltlVisitors = new ArrayList<LTLFormulaVisitor>();

		this.machineSetParameter = new LinkedHashMap<String, Node>();
		this.machineScalarParameter = new LinkedHashMap<String, Node>();

		this.deferredSets = new LinkedHashMap<String, Node>();
		this.enumeratedSets = new LinkedHashMap<String, Node>();
		this.enumValues = new LinkedHashMap<String, Node>();
		this.constants = new LinkedHashMap<String, Node>();
		this.variables = new LinkedHashMap<String, Node>();
		this.definitions = new LinkedHashMap<String, Node>();
		this.operations = new LinkedHashMap<String, Node>();
		this.seenMachines = new LinkedHashMap<String, AIdentifierExpression>();
	}

	public void analyseMachine() {
		this.start.apply(this);
		checkConstantsSetup();
		checkLTLFormulas();
	}

	public void addLTLFromula(final String ltlFormula) {
		LTLFormulaVisitor ltlVisitor = new LTLFormulaVisitor("ltl", this);
		ltlVisitor.parseLTLString(ltlFormula);
		this.ltlVisitors.add(ltlVisitor);
	}

	public void setConstantSetupPredicate(final PPredicate constantsSetup) {
		this.constantsSetupPredicate = constantsSetup;
	}

	private void checkConstantsSetup() {
		if (constantsSetupPredicate == null) {
			return;
		}
		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			contextTable.add(s.getDeferredSets());
			contextTable.add(s.getEnumeratedSets());
			contextTable.add(s.getEnumValues());
			contextTable.add(s.getConstants());
			contextTable.add(s.getDefinitions());
		}
		constantsSetupPredicate.apply(this);
	}

	private void checkLTLFormulas() {
		if (ltlVisitors.size() == 1) {
			ltlVisitors.get(0).start();
			return;
		}
		ArrayList<LTLFormulaVisitor> unsupportedLTLformulas = new ArrayList<LTLFormulaVisitor>();
		for (int i = 0; i < ltlVisitors.size(); i++) {
			LTLFormulaVisitor visitor = ltlVisitors.get(i);
			try {
				visitor.start();
			} catch (ScopeException e) {
				System.out.println("Warning: LTL formula '" + visitor.getName() + "' cannot be checked.");
				unsupportedLTLformulas.add(visitor);
			}
		}
		ltlVisitors.removeAll(unsupportedLTLformulas);
	}

	public void checkLTLBPredicate(LTLBPredicate ltlbPredicate) {
		contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		contextTable.add(getDeferredSets());
		contextTable.add(getEnumeratedSets());
		contextTable.add(getEnumValues());
		contextTable.add(getConstants());
		contextTable.add(getVariables());
		contextTable.add(getDefinitions());

		LinkedHashMap<String, Node> identifierHashTable = ltlbPredicate.getIdentifierList();
		if (identifierHashTable.size() > 0) {
			LinkedHashMap<String, Node> currentContext = new LinkedHashMap<String, Node>();
			currentContext.putAll(identifierHashTable);
			contextTable.add(currentContext);
		}
		ltlbPredicate.getBFormula().apply(this);
	}

	private void exist(LinkedList<TIdentifierLiteral> list) {
		String name = Utils.getTIdentifierListAsString(list);
		identifierAlreadyExists(name);
	}

	private void identifierAlreadyExists(String name) {
		if (constants.containsKey(name) || variables.containsKey(name) || operations.containsKey(name)
				|| deferredSets.containsKey(name) || enumeratedSets.containsKey(name) || enumValues.containsKey(name)
				|| machineSetParameter.containsKey(name) || machineScalarParameter.containsKey(name)
				|| seenMachines.containsKey(name) || definitions.containsKey(name)) {
			throw new ScopeException("Duplicate identifier: '" + name + "'");
		}
	}

	@Override
	public void caseAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
		this.abstractMachineParseUnit = node;
		if (node.getVariant() != null) {
			node.getVariant().apply(this);
		}
		if (node.getHeader() != null) {
			node.getHeader().apply(this);
		}

		List<PMachineClause> machineClauses = node.getMachineClauses();
		// Sort the machine clauses: declarations clauses first, then
		// properties clauses
		MachineClauseSorter.sortMachineClauses(start);

		for (PMachineClause e : machineClauses) {
			e.apply(this);
		}
	}

	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		this.header = node;
		if (machineName == null) {
			List<TIdentifierLiteral> nameList = new ArrayList<TIdentifierLiteral>(node.getName());
			this.machineName = Utils.getTIdentifierListAsString(nameList);
		}

		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (PExpression e : copy) {
			AIdentifierExpression p = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(p.getIdentifier());
			exist(p.getIdentifier());
			if (Character.isUpperCase(name.charAt(0))) {
				this.machineSetParameter.put(name, p);
			} else {
				this.machineScalarParameter.put(name, p);
			}
		}
	}

	@Override
	public void caseAPredicateParseUnit(APredicateParseUnit node) {
		node.getPredicate().apply(this);
	}

	/**
	 * Definitions
	 */

	@Override
	public void caseADefinitionsMachineClause(ADefinitionsMachineClause node) {
		definitionMachineClause = node;
		DefinitionsSorter.sortDefinitions(node);

		List<PDefinition> copy = node.getDefinitions();

		/*
		 * The definitions are not in a predefined order. In particular
		 * definitions can depend on each other. First all definitions are added
		 * to the definitions context table. Then all definitions are visited.
		 */
		Collection<PDefinition> definitionsToRemove = new HashSet<PDefinition>();

		for (PDefinition e : copy) {
			if (e instanceof AExpressionDefinitionDefinition) {
				AExpressionDefinitionDefinition def = (AExpressionDefinitionDefinition) e;
				String name = def.getName().getText();
				if (name.startsWith("ASSERT_LTL")) {
					LTLFormulaVisitor visitor = new LTLFormulaVisitor(name, this);
					visitor.parseDefinition(def);
					this.ltlVisitors.add(visitor);
					definitionsToRemove.add(def);
				} else if (name.startsWith("ANIMATION_")) {
					definitionsToRemove.add(def);
				}
				evalDefinitionName(((AExpressionDefinitionDefinition) e).getName().getText().toString(), e);
			} else if (e instanceof APredicateDefinitionDefinition) {
				evalDefinitionName(((APredicateDefinitionDefinition) e).getName().getText().toString(), e);
			} else if (e instanceof ASubstitutionDefinitionDefinition) {
				evalDefinitionName(((ASubstitutionDefinitionDefinition) e).getName().getText().toString(), e);
			}
		}
		/*
		 * At this point all LTL definitions (ASSERT_LTL) are removed. LTL
		 * formulas are stored in the Arraylist {@value #ltlVisitors}.
		 */
		copy.removeAll(definitionsToRemove);
		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			contextTable.add(s.getDeferredSets());
			contextTable.add(s.getEnumeratedSets());
			contextTable.add(s.getEnumValues());
			contextTable.add(s.getConstants());
			contextTable.add(s.getVariables());
			contextTable.add(s.getDefinitions());
		}

		for (PDefinition e : copy) {
			e.apply(this);
		}
	}

	private void evalDefinitionName(String name, Node node) {
		identifierAlreadyExists(name);
		definitions.put(name, node);
	}

	@Override
	public void caseAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
		visitBDefinition(node, node.getName().getText().toString(), node.getParameters(), node.getRhs());
	}

	@Override
	public void caseAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
		visitBDefinition(node, node.getName().getText().toString(), node.getParameters(), node.getRhs());
	}

	/* d == x := 1 */
	@Override
	public void caseASubstitutionDefinitionDefinition(ASubstitutionDefinitionDefinition node) {
		visitBDefinition(node, node.getName().getText().toString(), node.getParameters(), node.getRhs());

	}

	public void visitBDefinition(Node node, String name, List<PExpression> copy, Node rightSide) {
		if (!this.definitions.containsValue(node)) {
			return;
		}
		contextTable.add(new LinkedHashMap<String, Node>());
		for (PExpression e : copy) {
			putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
		}
		rightSide.apply(this);
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseADefinitionExpression(ADefinitionExpression node) {
		visitBDefinitionCall(node, node.getDefLiteral().getText().toString(), node.getParameters());
	}

	@Override
	public void caseADefinitionPredicate(ADefinitionPredicate node) {
		visitBDefinitionCall(node, node.getDefLiteral().getText().toString(), node.getParameters());
	}

	@Override
	public void caseADefinitionSubstitution(ADefinitionSubstitution node) {
		visitBDefinitionCall(node, node.getDefLiteral().getText().toString(), node.getParameters());
	}

	private void visitBDefinitionCall(Node node, String name, List<PExpression> copy) {
		for (PExpression pExpression : copy) {
			pExpression.apply(this);
		}
		for (int i = contextTable.size() - 1; i >= 0; i--) {
			LinkedHashMap<String, Node> currentScope = contextTable.get(i);
			if (currentScope.containsKey(name)) {
				this.referencesTable.put(node, currentScope.get(name));
				return;
			}
		}
		throw new ScopeException("Unkown definition: '" + name + "' at position: " + node.getStartPos());
	}

	@Override
	public void caseASeesMachineClause(ASeesMachineClause node) {
		this.seesMachineClause = node;
		List<PExpression> copy = new ArrayList<PExpression>(node.getMachineNames());
		for (PExpression e : copy) {
			AIdentifierExpression p = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(p.getIdentifier());

			try {
				exist(p.getIdentifier());
			} catch (ScopeException e2) {
				throw new ScopeException("Machine '" + name + "' is seen twice.");
			}
			seenMachines.put(name, p);
		}
	}

	@Override
	public void caseASetsContextClause(ASetsContextClause node) {
		this.setsMachineClause = node;
		List<PSet> copy = new ArrayList<PSet>(node.getSet());
		for (PSet e : copy) {
			e.apply(this);
		}
	}

	@Override
	public void caseADeferredSetSet(ADeferredSetSet node) {
		List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());
		String name = Utils.getTIdentifierListAsString(copy);
		exist(node.getIdentifier());
		deferredSets.put(name, node);
	}

	@Override
	public void caseAEnumeratedSetSet(AEnumeratedSetSet node) {
		{
			List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());
			String name = Utils.getTIdentifierListAsString(copy);
			exist(node.getIdentifier());
			enumeratedSets.put(name, node);
		}
		List<PExpression> copy = new ArrayList<PExpression>(node.getElements());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(v.getIdentifier());
			exist(v.getIdentifier());
			enumValues.put(name, v);
		}
	}

	@Override
	public void caseAConstantsMachineClause(AConstantsMachineClause node) {
		hasConstants = true;
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression c = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(c.getIdentifier());
			exist(c.getIdentifier());
			constants.put(name, c);
		}
	}

	@Override
	public void caseAAbstractConstantsMachineClause(AAbstractConstantsMachineClause node) {
		hasConstants = true;
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression c = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(c.getIdentifier());
			exist(c.getIdentifier());
			constants.put(name, c);
		}
	}

	@Override
	public void caseAVariablesMachineClause(AVariablesMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(v.getIdentifier());
			exist(v.getIdentifier());
			variables.put(name, v);
		}
	}

	@Override
	public void caseAConcreteVariablesMachineClause(AConcreteVariablesMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(v.getIdentifier());
			exist(v.getIdentifier());
			variables.put(name, v);
		}
	}

	private void putLocalVariableIntoCurrentScope(AIdentifierExpression node) throws ScopeException {
		String name = Utils.getTIdentifierListAsString(node.getIdentifier());
		LinkedHashMap<String, Node> currentScope = contextTable.get(contextTable.size() - 1);
		if (currentScope.containsKey(name)) {
			throw new ScopeException("Duplicate Identifier: " + name);
		} else {
			currentScope.put(name, node);
		}
	}

	@Override
	public void caseAIdentifierExpression(AIdentifierExpression node) {
		String name = Utils.getTIdentifierListAsString(node.getIdentifier());
		for (int i = contextTable.size() - 1; i >= 0; i--) {
			LinkedHashMap<String, Node> currentScope = contextTable.get(i);
			if (currentScope.containsKey(name)) {
				this.referencesTable.put(node, currentScope.get(name));
				return;
			}
		}
		throw new ScopeException("Unkown Identifier: '" + name + "' at position: " + node.getStartPos());
	}

	@Override
	public void caseAPrimedIdentifierExpression(APrimedIdentifierExpression node) {
		String name = Utils.getTIdentifierListAsString(node.getIdentifier());
		for (int i = contextTable.size() - 1; i >= 0; i--) {
			LinkedHashMap<String, Node> currentScope = contextTable.get(i);
			if (currentScope.containsKey(name)) {
				this.referencesTable.put(node, currentScope.get(name));
				return;
			}
		}
		throw new ScopeException("Unkown Identifier: '" + name + "' at position: " + node.getStartPos());
	}

	private ArrayList<MachineContext> lookupReferencedMachines() {
		ArrayList<MachineContext> list = new ArrayList<MachineContext>();
		list.add(this);
		return list;
	}

	@Override
	public void caseAConstraintsMachineClause(AConstraintsMachineClause node) {
		this.constraintMachineClause = node;

		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		this.contextTable.add(this.machineScalarParameter);
		this.contextTable.add(this.machineSetParameter);
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
	}

	@Override
	public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
		this.propertiesMachineClause = node;
		hasConstants = true;
		/**
		 * check identifier scope in properties clauses
		 */

		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			contextTable.add(s.getDeferredSets());
			contextTable.add(s.getEnumeratedSets());
			contextTable.add(s.getEnumValues());
			contextTable.add(s.getConstants());
			contextTable.add(s.getDefinitions());
		}
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
	}

	@Override
	public void caseAInvariantMachineClause(AInvariantMachineClause node) {
		this.invariantMachineClause = node;

		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();

		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			this.contextTable.add(s.getSetParamter());
			this.contextTable.add(s.getScalarParameter());
			this.contextTable.add(s.getDeferredSets());
			this.contextTable.add(s.getEnumeratedSets());
			this.contextTable.add(s.getEnumValues());
			this.contextTable.add(s.getConstants());
			this.contextTable.add(s.getDefinitions());
			this.contextTable.add(s.getVariables());
		}
		node.getPredicates().apply(this);
	}

	@Override
	public void caseAAssertionsMachineClause(AAssertionsMachineClause node) {
		this.assertiondMachineClause = node;

		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			this.contextTable.add(s.getSetParamter());
			this.contextTable.add(s.getScalarParameter());
			this.contextTable.add(s.getDeferredSets());
			this.contextTable.add(s.getEnumeratedSets());
			this.contextTable.add(s.getEnumValues());
			this.contextTable.add(s.getConstants());
			this.contextTable.add(s.getDefinitions());
			this.contextTable.add(s.getVariables());
		}

		List<PPredicate> copy = new ArrayList<PPredicate>(node.getPredicates());
		for (PPredicate e : copy) {
			e.apply(this);
		}
	}

	@Override
	public void caseAInitialisationMachineClause(AInitialisationMachineClause node) {
		this.initialisationMachineClause = node;

		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();

		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);

			this.contextTable.add(s.getSetParamter());
			this.contextTable.add(s.getScalarParameter());
			this.contextTable.add(s.getDeferredSets());
			this.contextTable.add(s.getEnumeratedSets());
			this.contextTable.add(s.getEnumValues());
			this.contextTable.add(s.getConstants());
			this.contextTable.add(s.getDefinitions());
			this.contextTable.add(s.getVariables());
		}
		if (node.getSubstitutions() != null) {
			node.getSubstitutions().apply(this);
		}
	}

	@Override
	public void caseAOperationsMachineClause(AOperationsMachineClause node) {
		this.operationMachineClause = node;
		this.contextTable = new ArrayList<LinkedHashMap<String, Node>>();
		ArrayList<MachineContext> list = lookupReferencedMachines();
		for (int i = 0; i < list.size(); i++) {
			MachineContext s = list.get(i);
			this.contextTable.add(s.getSetParamter());
			this.contextTable.add(s.getScalarParameter());
			this.contextTable.add(s.getDeferredSets());
			this.contextTable.add(s.getEnumeratedSets());
			this.contextTable.add(s.getEnumValues());
			this.contextTable.add(s.getConstants());
			this.contextTable.add(s.getDefinitions());
			this.contextTable.add(s.getVariables());
		}
		List<POperation> copy = new ArrayList<POperation>(node.getOperations());
		// first collect all operations
		for (POperation e : copy) {
			AOperation op = (AOperation) e;
			String name = Utils.getTIdentifierListAsString(op.getOpName());
			// existString(name);
			if (operations.keySet().contains(name)) {
				throw new ScopeException(String.format("Duplicate operation: '%s'", name));
			}
			operations.put(name, op);
		}
		// visit all operations
		for (POperation e : copy) {
			e.apply(this);
		}
	}

	@Override
	public void caseAOperation(AOperation node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getReturnValues());
			for (PExpression e : copy) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				exist(id.getIdentifier());
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}

		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
			for (PExpression e : copy) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				exist(id.getIdentifier());
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getOperationBody() != null) {
			node.getOperationBody().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAAssignSubstitution(AAssignSubstitution node) {
		ArrayList<LinkedHashMap<String, Node>> temp = contextTable;
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getLhsExpression());
			ArrayList<LinkedHashMap<String, Node>> varTable = new ArrayList<LinkedHashMap<String, Node>>();
			varTable.add(variables);
			for (PExpression e : copy) {
				if (e instanceof AFunctionExpression) {
					contextTable = varTable;
					((AFunctionExpression) e).getIdentifier().apply(this);

					// full context table
					contextTable = temp;
					for (Node n : ((AFunctionExpression) e).getParameters()) {
						n.apply(this);
					}
				} else {
					contextTable = temp; // TODO outputparameter + variables
					e.apply(this);
				}
			}
		}
		{
			contextTable = temp;
			List<PExpression> copy = new ArrayList<PExpression>(node.getRhsExpressions());
			for (PExpression e : copy) {
				e.apply(this);
			}
		}
	}

	@Override
	public void caseALetSubstitution(ALetSubstitution node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
		}
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
	}

	@Override
	public void caseAAnySubstitution(AAnySubstitution node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
		}
		node.getWhere().apply(this);
		node.getThen().apply(this);
	}

	@Override
	public void caseAOpSubstitution(AOpSubstitution node) {
		if (node.getName() != null) {
			AIdentifierExpression op = (AIdentifierExpression) node.getName();
			String name = Utils.getTIdentifierListAsString(op.getIdentifier());
			Node o = operations.get(name);
			if (o != null) {
				this.referencesTable.put(op, o);
			} else {
				throw new ScopeException("Unknown operation '" + name + "'");
			}
		}
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
			for (PExpression e : copy) {
				e.apply(this);
			}
		}
	}

	@Override
	public void caseAForallPredicate(AForallPredicate node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getImplication() != null) {
			node.getImplication().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAExistsPredicate(AExistsPredicate node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getPredicate() != null) {
			node.getPredicate().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseALambdaExpression(ALambdaExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		node.getPredicate().apply(this);
		node.getExpression().apply(this);
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAComprehensionSetExpression(AComprehensionSetExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		node.getPredicates().apply(this);
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAEventBComprehensionSetExpression(AEventBComprehensionSetExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		node.getPredicates().apply(this);

		node.getExpression().apply(this);
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAQuantifiedUnionExpression(AQuantifiedUnionExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
		if (node.getExpression() != null) {
			node.getExpression().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAQuantifiedIntersectionExpression(AQuantifiedIntersectionExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
		if (node.getExpression() != null) {
			node.getExpression().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAGeneralProductExpression(AGeneralProductExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
		if (node.getExpression() != null) {
			node.getExpression().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseAGeneralSumExpression(AGeneralSumExpression node) {
		contextTable.add(new LinkedHashMap<String, Node>());
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
			for (PExpression e : copy) {
				putLocalVariableIntoCurrentScope((AIdentifierExpression) e);
			}
		}
		if (node.getPredicates() != null) {
			node.getPredicates().apply(this);
		}
		if (node.getExpression() != null) {
			node.getExpression().apply(this);
		}
		contextTable.remove(contextTable.size() - 1);
	}

	@Override
	public void caseARecEntry(ARecEntry node) {
		node.getValue().apply(this);
	}

	@Override
	public void caseARecordFieldExpression(ARecordFieldExpression node) {
		node.getRecord().apply(this);
	}

	public String getMachineName() {
		return machineName;
	}

	public PMachineHeader getHeader() {
		return header;
	}

	public Start getStartNode() {
		return start;
	}

	public LinkedHashMap<String, Node> getSetParamter() {
		return new LinkedHashMap<>(machineSetParameter);
	}

	public ArrayList<Node> getConstantArrayList() {
		ArrayList<Node> list = new ArrayList<Node>();
		for (Entry<String, Node> entry : constants.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	public LinkedHashMap<String, Node> getScalarParameter() {
		return new LinkedHashMap<>(machineScalarParameter);
	}

	public LinkedHashMap<String, Node> getConstants() {
		return constants;
	}

	public LinkedHashMap<String, Node> getDefinitions() {
		return new LinkedHashMap<>(definitions);
	}

	public LinkedHashMap<String, Node> getVariables() {
		return new LinkedHashMap<>(variables);
	}

	public LinkedHashMap<String, Node> getOperations() {
		return new LinkedHashMap<>(operations);
	}

	public LinkedHashMap<String, Node> getDeferredSets() {
		return new LinkedHashMap<>(deferredSets);
	}

	public LinkedHashMap<String, Node> getEnumeratedSets() {
		return new LinkedHashMap<>(enumeratedSets);
	}

	public LinkedHashMap<String, Node> getEnumValues() {
		return new LinkedHashMap<>(enumValues);
	}

	public LinkedHashMap<String, AIdentifierExpression> getSeenMachines() {
		return new LinkedHashMap<>(seenMachines);
	}

	protected Hashtable<Node, Node> getReferences() {
		return referencesTable;
	}

	public Node getReferenceNode(Node node) {
		return referencesTable.get(node);
	}

	public void addReference(Node node, Node ref) {
		referencesTable.put(node, ref);
	}

	public ArrayList<LTLFormulaVisitor> getLTLFormulas() {
		return ltlVisitors;
	}

	public AAbstractMachineParseUnit getAbstractMachineParseUnit() {
		return abstractMachineParseUnit;
	}

	public AConstraintsMachineClause getConstraintMachineClause() {
		return constraintMachineClause;
	}

	public ASeesMachineClause getSeesMachineClause() {
		return seesMachineClause;
	}

	public ASetsContextClause getSetsMachineClause() {
		return setsMachineClause;
	}

	public APropertiesMachineClause getPropertiesMachineClause() {
		return propertiesMachineClause;
	}

	public AAssertionsMachineClause getAssertionMachineClause() {
		return assertiondMachineClause;
	}

	public void setPropertiesMachineClaus(APropertiesMachineClause propertiesMachineClause) {
		this.propertiesMachineClause = propertiesMachineClause;
	}

	public AInvariantMachineClause getInvariantMachineClause() {
		return invariantMachineClause;
	}

	public boolean machineContainsOperations() {
		return operations.size() > 0;
	}

	public AInitialisationMachineClause getInitialisationMachineClause() {
		return initialisationMachineClause;
	}

	public AOperationsMachineClause getOperationMachineClause() {
		return operationMachineClause;
	}

	public ADefinitionsMachineClause getDefinitionMachineClause() {
		return definitionMachineClause;
	}

	public void setDefinitionsMachineClause(ADefinitionsMachineClause definitionMachineClause) {
		this.definitionMachineClause = definitionMachineClause;
	}

	public PPredicate getConstantsSetup() {
		return constantsSetupPredicate;
	}

	public boolean hasConstants() {
		return hasConstants;
	}

}
