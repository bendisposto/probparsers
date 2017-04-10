package de.prob.typechecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import de.be4.classicalb.core.parser.util.Utils;
import de.prob.typechecker.btypes.AbstractHasFollowers;
import de.prob.typechecker.btypes.BType;
import de.prob.typechecker.btypes.BoolType;
import de.prob.typechecker.btypes.EnumeratedSetElement;
import de.prob.typechecker.btypes.FunctionType;
import de.prob.typechecker.btypes.ITypechecker;
import de.prob.typechecker.btypes.IntegerOrSetOfPairType;
import de.prob.typechecker.btypes.IntegerOrSetType;
import de.prob.typechecker.btypes.IntegerType;
import de.prob.typechecker.btypes.PairType;
import de.prob.typechecker.btypes.SetType;
import de.prob.typechecker.btypes.StringType;
import de.prob.typechecker.btypes.StructType;
import de.prob.typechecker.btypes.UntypedType;
import de.prob.typechecker.exceptions.TypeErrorException;
import de.prob.typechecker.exceptions.UnificationException;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.*;

/**
 * TODO we need a second run over the AST to check if all local variables have a
 * type. This run should be performed after the normal model checking task.
 */
public class Typechecker extends DepthFirstAdapter implements ITypechecker {

	private final Hashtable<Node, BType> types;
	private final Hashtable<Node, Node> referenceTable;
	private final MachineContext machineContext;

	public Typechecker(MachineContext context) {
		this.types = new Hashtable<Node, BType>();
		this.referenceTable = context.getReferences();
		this.machineContext = context;

		context.getStartNode().apply(this);
		checkConstantsSetup();
		checkLTLFormulas();
	}

	private void checkLTLFormulas() {
		ArrayList<LTLFormulaVisitor> visitors = machineContext.getLTLFormulas();
		for (int i = 0; i < visitors.size(); i++) {
			LTLFormulaVisitor visitor = visitors.get(i);
			Collection<AIdentifierExpression> parameter = visitor.getParameter();
			for (AIdentifierExpression param : parameter) {
				setType(param, new UntypedType());
			}
			for (int j = 0; j < visitor.getBPredicates().size(); j++) {
				LTLBPredicate ltlBPredicate = visitor.getBPredicates().get(j);
				ltlBPredicate.getBFormula().apply(this);
			}
		}

	}

	private void checkConstantsSetup() {
		PPredicate p = machineContext.getConstantsSetup();
		if (p != null) {
			setType(p, BoolType.getInstance());
			p.apply(this);
			for (Entry<String, Node> entry : machineContext.getConstants().entrySet()) {
				String c = entry.getKey();
				Node n = entry.getValue();
				if (getType(n).isUntyped()) {
					throw new TypeErrorException("Can not infer type of constant '" + c + "': " + getType(n));
				}
			}
		}
	}

	@Override
	public void caseAPredicateParseUnit(APredicateParseUnit node) {
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
	}

	public void setType(Node node, BType t) {
		this.types.put(node, t);
		if (t instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) t).addFollower(node);
		}
	}

	public void updateType(Node node, AbstractHasFollowers oldType, BType newType) {
		oldType.deleteFollower(node);
		this.types.put(node, newType);
		if (newType instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) newType).addFollower(node);
		}
	}

	public BType getType(Node node) {
		BType res = types.get(node);
		if (res == null) {
			new TypeErrorException("Node '" + node + "' has no type.\n" + node.getStartPos());
		}
		return res;
	}

	@Override
	public void caseAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
		if (node.getVariant() != null) {
			node.getVariant().apply(this);
		}
		if (node.getHeader() != null) {
			node.getHeader().apply(this);
		}
		{
			List<PMachineClause> copy = new ArrayList<PMachineClause>(node.getMachineClauses());
			for (PMachineClause e : copy) {
				e.apply(this);
			}
		}
	}

	/**
	 * Declarations
	 */

	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (PExpression e : copy) {
			AIdentifierExpression p = (AIdentifierExpression) e;
			String name = Utils.getTIdentifierListAsString(p.getIdentifier());

			if (Character.isUpperCase(name.charAt(0))) {

				EnumeratedSetElement m = new EnumeratedSetElement(name);
				setType(p, new SetType(m));
			} else {
				UntypedType u = new UntypedType();
				setType(p, u);
			}
		}
	}

	@Override
	public void caseASetsMachineClause(ASetsMachineClause node) {
		List<PSet> copy = new ArrayList<PSet>(node.getSetDefinitions());
		for (PSet e : copy) {
			e.apply(this);
		}
	}

	@Override
	public void caseAEnumeratedSetSet(AEnumeratedSetSet node) {
		List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());

		String setName = Utils.getTIdentifierListAsString(copy);
		SetType set = new SetType(new EnumeratedSetElement(setName));
		setType(node, set);
		List<PExpression> copy2 = new ArrayList<PExpression>(node.getElements());
		for (PExpression e : copy2) {
			setType(e, set.getSubtype());
		}
	}

	@Override
	public void caseADeferredSetSet(ADeferredSetSet node) {
		List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());
		String name = Utils.getTIdentifierListAsString(copy);
		setType(node, new SetType(new EnumeratedSetElement(name)));
	}

	@Override
	public void caseAConstantsMachineClause(AConstantsMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression id = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(id, u);
		}
	}

	@Override
	public void caseAAbstractConstantsMachineClause(AAbstractConstantsMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression id = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(id, u);
		}
	}

	@Override
	public void caseAVariablesMachineClause(AVariablesMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(v, u);
		}
	}

	@Override
	public void caseAConcreteVariablesMachineClause(AConcreteVariablesMachineClause node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(v, u);
		}
	}

	/**
	 * Definitions
	 */

	@Override
	public void caseADefinitionsMachineClause(ADefinitionsMachineClause node) {
		List<PDefinition> copy = new ArrayList<PDefinition>(node.getDefinitions());
		for (PDefinition e : copy) {
			setType(e, new UntypedType());
		}

		for (PDefinition e : copy) {
			e.apply(this);
		}
	}

	@Override
	// d(a) == 1
	public void caseAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (PExpression e : copy) {
			UntypedType u = new UntypedType();
			setType(e, u);
		}
		setType(node.getRhs(), getType(node));
		node.getRhs().apply(this);
	}

	@Override
	// d(a) == 1 = 1
	public void caseAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
		setType(node, BoolType.getInstance());
		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (PExpression e : copy) {
			setType(e, new UntypedType());
		}
		setType(node.getRhs(), BoolType.getInstance());
		node.getRhs().apply(this);
	}

	@Override
	public void caseADefinitionExpression(ADefinitionExpression node) {
		BType expected = getType(node);
		// String name = node.getDefLiteral().getText().toString();
		Node originalDef = referenceTable.get(node);
		BType found = getType(originalDef);

		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + expected + "', found '" + found + "' at definition call\n");
		}
		LinkedList<PExpression> params = ((AExpressionDefinitionDefinition) originalDef).getParameters();
		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (int i = 0; i < params.size(); i++) {
			BType type = getType(params.get(i));
			setType(copy.get(i), type);
			copy.get(i).apply(this);
		}
	}

	@Override
	public void caseADefinitionPredicate(ADefinitionPredicate node) {
		BType expected = getType(node);
		Node originalDef = referenceTable.get(node);
		BType found = BoolType.getInstance();

		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + expected + "', found '" + found + "' at definition call\n");
		}
		LinkedList<PExpression> params = ((APredicateDefinitionDefinition) originalDef).getParameters();
		List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (int i = 0; i < params.size(); i++) {
			setType(copy.get(i), getType(params.get(i)));
			copy.get(i).apply(this);
		}
	}

	/**
	 * Properties
	 */

	@Override
	public void caseAConstraintsMachineClause(AConstraintsMachineClause node) {
		if (node.getPredicates() != null) {
			setType(node.getPredicates(), BoolType.getInstance());
			node.getPredicates().apply(this);
		}
		for (Entry<String, Node> entry : machineContext.getScalarParameter().entrySet()) {
			String name = entry.getKey();
			Node n = entry.getValue();
			if (getType(n).isUntyped()) {
				throw new TypeErrorException("Can not infer type of parameter '" + name + "'");
			}
		}
	}

	@Override
	public void caseAPropertiesMachineClause(final APropertiesMachineClause node) {
		if (node.getPredicates() != null) {
			setType(node.getPredicates(), BoolType.getInstance());
			node.getPredicates().apply(this);
		}
		for (Entry<String, Node> entry : machineContext.getConstants().entrySet()) {
			String c = entry.getKey();
			Node n = entry.getValue();
			if (getType(n).isUntyped()) {
				throw new TypeErrorException("Can not infer type of constant '" + c + "': " + getType(n));
			}
		}
	}

	@Override
	public void caseAInvariantMachineClause(AInvariantMachineClause node) {
		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);
		for (Entry<String, Node> entry : machineContext.getVariables().entrySet()) {
			String c = entry.getKey();
			Node n = entry.getValue();
			if (getType(n).isUntyped()) {
				throw new TypeErrorException("Can not infer type of variable '" + c + "'");
			}
		}
	}

	@Override
	public void caseAAssertionsMachineClause(AAssertionsMachineClause node) {
		List<PPredicate> copy = new ArrayList<PPredicate>(node.getPredicates());
		for (PPredicate e : copy) {
			setType(e, BoolType.getInstance());
			e.apply(this);
		}
	}

	@Override
	public void caseAInitialisationMachineClause(AInitialisationMachineClause node) {
		if (node.getSubstitutions() != null) {
			node.getSubstitutions().apply(this);
		}
	}

	@Override
	public void caseAOperation(AOperation node) {
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getReturnValues());
			for (PExpression e : copy) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				UntypedType u = new UntypedType();
				setType(id, u);
			}

		}
		{
			List<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
			for (PExpression e : copy) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				UntypedType u = new UntypedType();
				setType(id, u);
			}
		}
		if (node.getOperationBody() != null) {
			node.getOperationBody().apply(this);
		}
	}

	/**
	 * Expressions
	 */

	@Override
	public void caseAIdentifierExpression(AIdentifierExpression node) {
		BType expected = getType(node);
		if (expected == null) {
			throw new RuntimeException("Not implemented in Typechecker: " + node + " Pos: " + node.getStartPos());
		}
		Node identifierDeclarationNode = referenceTable.get(node);
		BType found = getType(identifierDeclarationNode);

		String name = Utils.getTIdentifierListAsString(node.getIdentifier());
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + found + "' at identifier " + name
					+ "\n" + node.getStartPos());
		}
	}

	@Override
	public void caseAEqualPredicate(AEqualPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + getType(node) + "', found BOOL at '=' \n" + node.getStartPos());
		}

		UntypedType x = new UntypedType();
		setType(node.getLeft(), x);
		setType(node.getRight(), x);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseANotEqualPredicate(ANotEqualPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + getType(node) + "', found BOOL at '=' \n" + node.getClass());
		}

		UntypedType x = new UntypedType();
		setType(node.getLeft(), x);
		setType(node.getRight(), x);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAForallPredicate(AForallPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + getType(node) + "', found BOOL at 'For All' \n");
		}

		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getImplication(), BoolType.getInstance());
		node.getImplication().apply(this);
	}

	@Override
	public void caseAExistsPredicate(AExistsPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Expected '" + getType(node) + "', found BOOL at 'Exists' \n");
		}

		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
	}

	/**
	 * Substitutions
	 * 
	 */

	@Override
	public void caseAPreconditionSubstitution(APreconditionSubstitution node) {
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
	}

	@Override
	public void caseAAssertionSubstitution(AAssertionSubstitution node) {
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
	}

	@Override
	public void caseASelectSubstitution(ASelectSubstitution node) {
		setType(node.getCondition(), BoolType.getInstance());
		node.getCondition().apply(this);
		node.getThen().apply(this);
		List<PSubstitution> copy = new ArrayList<PSubstitution>(node.getWhenSubstitutions());
		for (PSubstitution e : copy) {
			e.apply(this);
		}
		if (node.getElse() != null) {
			node.getElse().apply(this);
		}
	}

	@Override
	public void caseASelectWhenSubstitution(ASelectWhenSubstitution node) {
		setType(node.getCondition(), BoolType.getInstance());
		node.getCondition().apply(this);
		node.getSubstitution().apply(this);
	}

	@Override
	public void caseAIfSubstitution(AIfSubstitution node) {
		setType(node.getCondition(), BoolType.getInstance());
		node.getCondition().apply(this);
		node.getThen().apply(this);
		List<PSubstitution> copy = new ArrayList<PSubstitution>(node.getElsifSubstitutions());
		for (PSubstitution e : copy) {
			e.apply(this);
		}
		if (node.getElse() != null) {
			node.getElse().apply(this);
		}
	}

	@Override
	public void caseAIfElsifSubstitution(AIfElsifSubstitution node) {
		setType(node.getCondition(), BoolType.getInstance());
		node.getCondition().apply(this);
		node.getThenSubstitution().apply(this);
	}

	@Override
	public void caseAAssignSubstitution(AAssignSubstitution node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getLhsExpression());
		List<PExpression> copy2 = new ArrayList<PExpression>(node.getRhsExpressions());

		for (int i = 0; i < copy.size(); i++) {
			PExpression left = copy.get(i);
			PExpression right = copy2.get(i);

			UntypedType x = new UntypedType();
			setType(left, x);
			setType(right, x);

			left.apply(this);
			right.apply(this);
		}

	}

	@Override
	public void caseABecomesSuchSubstitution(ABecomesSuchSubstitution node) {
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
	}

	@Override
	public void caseABecomesElementOfSubstitution(ABecomesElementOfSubstitution node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		SetType set = new SetType(new UntypedType());

		setType(node.getSet(), set);
		for (PExpression e : copy) {
			setType(e, set.getSubtype());
		}
		for (PExpression e : copy) {
			e.apply(this);
		}
		node.getSet().apply(this);
	}

	@Override
	public void caseAAnySubstitution(AAnySubstitution node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}
		setType(node.getWhere(), BoolType.getInstance());
		node.getWhere().apply(this);
		node.getThen().apply(this);
	}

	@Override
	public void caseALetSubstitution(ALetSubstitution node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
	}

	/****************************************************************************
	 * Arithmetic operators *
	 ****************************************************************************/

	@Override
	public void caseAIntegerExpression(AIntegerExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException(
					"Excepted '" + getType(node) + "' , found 'INTEGER' in '" + node.getLiteral().getText() + "'");
		}
	}

	@Override
	public void caseAIntegerSetExpression(AIntegerSetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'INTEGER'");
		}
	}

	@Override
	public void caseANaturalSetExpression(ANaturalSetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'NATURAL'");
		}
	}

	@Override
	public void caseANatural1SetExpression(ANatural1SetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'NATURAL1'");
		}
	}

	@Override
	public void caseAIntSetExpression(AIntSetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'INT'");
		}
	}

	@Override
	public void caseANatSetExpression(ANatSetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'NAT'");
		}
	}

	@Override
	public void caseANat1SetExpression(ANat1SetExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(INTEGER)' in 'NAT1'");
		}
	}

	@Override
	public void caseAUnaryMinusExpression(AUnaryMinusExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in '-'");
		}
	}

	@Override
	public void caseAIntervalExpression(AIntervalExpression node) {
		try {
			SetType found = new SetType(IntegerType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException(
					"Excepted '" + getType(node) + "' , found 'POW(INTEGER)' at interval operator");
		}

		setType(node.getLeftBorder(), IntegerType.getInstance());
		setType(node.getRightBorder(), IntegerType.getInstance());
		node.getLeftBorder().apply(this);
		node.getRightBorder().apply(this);
	}

	@Override
	public void caseAMaxIntExpression(AMaxIntExpression node) {
		unify(getType(node), IntegerType.getInstance(), node);
	}

	@Override
	public void caseAMinIntExpression(AMinIntExpression node) {
		unify(getType(node), IntegerType.getInstance(), node);
	}

	@Override
	public void caseAGreaterPredicate(AGreaterPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' > '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseALessPredicate(ALessPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' < '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAGreaterEqualPredicate(AGreaterEqualPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' >= '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseALessEqualPredicate(ALessEqualPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' <= '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAMinExpression(AMinExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' min '");
		}
		setType(node.getExpression(), new SetType(IntegerType.getInstance()));
		node.getExpression().apply(this);
	}

	@Override
	public void caseAMaxExpression(AMaxExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' min '");
		}
		setType(node.getExpression(), new SetType(IntegerType.getInstance()));
		node.getExpression().apply(this);
	}

	@Override
	public void caseAAddExpression(AAddExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' + '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAMinusOrSetSubtractExpression(AMinusOrSetSubtractExpression node) {
		BType expected = getType(node);

		BType found = new IntegerOrSetType();
		unify(expected, found, node);

		setType(node.getLeft(), getType(node));
		setType(node.getRight(), getType(node));

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAMultOrCartExpression(AMultOrCartExpression node) {
		BType expected = getType(node);
		IntegerOrSetOfPairType found = new IntegerOrSetOfPairType(node.getStartPos(), node.getEndPos());
		// setType(node.getLeft(), found.getFirst());
		// setType(node.getRight(), found.getSecond());
		BType result = null;
		try {
			result = expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "' at "
					+ node.getClass().getSimpleName() + "\n " + node.getStartPos());
		}
		//
		// BType res2 = getType(node);
		// if(res2 != result){
		// AbstractHasFollowers a = (AbstractHasFollowers) res2;
		// throw new RuntimeException();
		// }
		//

		if (result instanceof IntegerOrSetOfPairType) {
			setType(node.getLeft(), ((IntegerOrSetOfPairType) result).getFirst());
			setType(node.getRight(), ((IntegerOrSetOfPairType) result).getSecond());
		} else if (result instanceof IntegerType) {
			setType(node.getLeft(), result);
			setType(node.getRight(), result);
		} else if (result instanceof SetType) {
			PairType pair = (PairType) ((SetType) result).getSubtype();
			setType(node.getLeft(), new SetType(pair.getFirst()));
			setType(node.getRight(), new SetType(pair.getSecond()));
		} else {
			throw new RuntimeException("Unexpected class type: " + result.getClass());
		}

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseADivExpression(ADivExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' / '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAPowerOfExpression(APowerOfExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' ** '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAModuloExpression(AModuloExpression node) {
		try {
			IntegerType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'INTEGER' in ' mod '");
		}
		setType(node.getLeft(), IntegerType.getInstance());
		setType(node.getRight(), IntegerType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseASuccessorExpression(ASuccessorExpression node) {
		FunctionType found = new FunctionType(IntegerType.getInstance(), IntegerType.getInstance());
		unify(getType(node), found, node);
	}

	@Override
	public void caseAPredecessorExpression(APredecessorExpression node) {
		FunctionType found = new FunctionType(IntegerType.getInstance(), IntegerType.getInstance());
		unify(getType(node), found, node);
	}

	@Override
	public void caseAGeneralSumExpression(AGeneralSumExpression node) {
		BType expected = getType(node);
		try {
			IntegerType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + "INTEGER" + "'");
		}

		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);

		setType(node.getExpression(), IntegerType.getInstance());
		node.getExpression().apply(this);
	}

	@Override
	public void caseAGeneralProductExpression(AGeneralProductExpression node) {
		BType expected = getType(node);
		try {
			IntegerType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + "INTEGER" + "'");
		}

		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);

		setType(node.getExpression(), IntegerType.getInstance());
		node.getExpression().apply(this);
	}

	/**
	 * Booleans
	 */

	@Override
	public void caseABooleanTrueExpression(ABooleanTrueExpression node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in 'TRUE'");
		}
	}

	@Override
	public void caseABooleanFalseExpression(ABooleanFalseExpression node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in 'FALSE'");
		}
	}

	@Override
	public void caseABoolSetExpression(ABoolSetExpression node) {
		try {
			SetType found = new SetType(BoolType.getInstance());
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'POW(BOOL)' in 'BOOL'");
		}
	}

	@Override
	public void caseAConvertBoolExpression(AConvertBoolExpression node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in 'bool(...)'");
		}
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
	}

	/**
	 * Logical Operator
	 */

	@Override
	public void caseAConjunctPredicate(AConjunctPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException(
					"Excepted '" + getType(node) + "' , found 'BOOL' in ' & '." + node.getStartPos());
		}
		setType(node.getLeft(), BoolType.getInstance());
		setType(node.getRight(), BoolType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseADisjunctPredicate(ADisjunctPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' or '");
		}
		setType(node.getLeft(), BoolType.getInstance());
		setType(node.getRight(), BoolType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAImplicationPredicate(AImplicationPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' => '");
		}
		setType(node.getLeft(), BoolType.getInstance());
		setType(node.getRight(), BoolType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAEquivalencePredicate(AEquivalencePredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' <=> '");
		}
		setType(node.getLeft(), BoolType.getInstance());
		setType(node.getRight(), BoolType.getInstance());
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseANegationPredicate(ANegationPredicate node) {
		try {
			BoolType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found 'BOOL' in ' not '");
		}
		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);
	}

	/**
	 * Sets
	 */

	@Override
	public void caseAEmptySetExpression(AEmptySetExpression node) {
		BType expected = getType(node);
		if (expected instanceof FunctionType) {
			return;
		} else {
			unify(expected, new SetType(new UntypedType()), node);
		}
	}

	@Override
	public void caseASetExtensionExpression(ASetExtensionExpression node) {
		if (functionTest(node))
			return;
		UntypedType u = new UntypedType();
		for (PExpression e : node.getExpressions()) {
			setType(e, u);
		}
		BType expected = getType(node);
		List<PExpression> copy = new ArrayList<PExpression>(node.getExpressions());
		for (PExpression e : copy) {
			e.apply(this);
		}
		BType found = new SetType(getType(copy.get(0)));
		unify(expected, found, node);
	}

	private boolean functionTest(ASetExtensionExpression node) {
		ArrayList<Node> list1 = new ArrayList<Node>();
		ArrayList<Node> list2 = new ArrayList<Node>();
		try {
			for (PExpression e : node.getExpressions()) {
				ACoupleExpression couple = (ACoupleExpression) e;
				Node left = couple.getList().get(0);
				Node right = couple.getList().get(1);
				list1.add(left);
				list2.add(right);
				if (couple.getList().size() > 2)
					return false;
			}
			if (compareElementsOfList(list1)) {
				UntypedType leftType = new UntypedType();
				UntypedType rightType = new UntypedType();
				for (int i = 0; i < list1.size(); i++) {
					setType(list1.get(i), leftType);
					setType(list2.get(i), rightType);
				}
				for (int i = 0; i < list1.size(); i++) {
					list1.get(i).apply(this);
					list2.get(i).apply(this);
				}
				BType res1 = getType(list1.get(0));
				BType res2 = getType(list2.get(0));
				FunctionType func = new FunctionType(res1, res2);
				BType expected = getType(node);
				unify(expected, func, node);
				return true;
			}

		} catch (ClassCastException e) {
			return false;
		}
		return false;
	}

	private boolean compareElementsOfList(ArrayList<Node> list) {
		if (list.size() == 1) {
			return true;
		}
		try {
			if (list.get(0) instanceof AIntegerExpression) {
				HashSet<Integer> set = new HashSet<Integer>();
				for (int i = 0; i < list.size(); i++) {
					AIntegerExpression aInt = (AIntegerExpression) list.get(i);
					int integer = Integer.parseInt(aInt.getLiteral().getText());
					set.add(integer);
				}
				if (list.size() == set.size()) {
					return true;
				}
			} else if (list.get(0) instanceof AIdentifierExpression) {
				HashSet<Node> set = new HashSet<Node>();
				for (int i = 0; i < list.size(); i++) {
					AIdentifierExpression id = (AIdentifierExpression) list.get(i);
					Node enumValue = machineContext.getReferences().get(id);
					if (!machineContext.getEnumValues().containsValue(enumValue)) {
						return false;
					}
					set.add(enumValue);
				}
				if (list.size() == set.size()) {
					return true;
				}

			}
		} catch (ClassCastException e) {
		}
		return false;
	}

	@Override
	public void caseAComprehensionSetExpression(AComprehensionSetExpression node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		ArrayList<BType> typesList = new ArrayList<BType>();
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			typesList.add(u);
			setType(v, u);
		}
		BType listType = makePair(typesList);
		SetType found = new SetType(listType);

		try {
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found " + found + "'");
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);

	}

	@Override
	public void caseAEventBComprehensionSetExpression(AEventBComprehensionSetExpression node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);

		setType(node.getExpression(), new UntypedType());
		node.getExpression().apply(this);

		BType found = new SetType(getType(node.getExpression()));

		BType expected = getType(node);
		unify(expected, found, node);
	}

	public static BType makePair(ArrayList<BType> list) {
		if (list.size() == 1)
			return list.get(0);
		PairType p = new PairType(list.get(0), null);
		for (int i = 1; i < list.size(); i++) {
			p.setSecond(list.get(i));
			if (i < list.size() - 1) {
				p = new PairType(p, null);
			}
		}
		return p;
	}

	// POW, POW1, FIN, FIN1
	private void subset(Node node, Node expr) {
		SetType found = new SetType(new SetType(new UntypedType()));
		BType expected = getType(node);
		try {
			found = found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found 'POW(POW(_A))' in 'POW'");
		}

		setType(expr, found.getSubtype());
		expr.apply(this);
	}

	@Override
	public void caseAPowSubsetExpression(APowSubsetExpression node) {
		subset(node, node.getExpression());
	}

	@Override
	public void caseAPow1SubsetExpression(APow1SubsetExpression node) {
		subset(node, node.getExpression());
	}

	@Override
	public void caseAFinSubsetExpression(AFinSubsetExpression node) {
		subset(node, node.getExpression());
	}

	@Override
	public void caseAFin1SubsetExpression(AFin1SubsetExpression node) {
		subset(node, node.getExpression());
	}

	// union, intersection, substraction,
	private void setSetSet(Node node, Node left, Node right) {
		SetType found = new SetType(new UntypedType());
		BType expected = getType(node);
		try {
			found = found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
		setType(left, found);
		setType(right, found);
		left.apply(this);
		right.apply(this);
	}

	@Override
	public void caseAUnionExpression(AUnionExpression node) {
		setSetSet(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseAIntersectionExpression(AIntersectionExpression node) {
		setSetSet(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseASetSubtractionExpression(ASetSubtractionExpression node) {
		setSetSet(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseACardExpression(ACardExpression node) {
		BType found = IntegerType.getInstance();
		BType expected = getType(node);

		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}

		setType(node.getExpression(), new UntypedType());
		node.getExpression().apply(this);
		BType type = getType(node.getExpression());
		if (!(type instanceof FunctionType)) {
			new SetType(new UntypedType()).unify(type, this);
		}
	}

	@Override
	public void caseAMemberPredicate(AMemberPredicate node) {
		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set.getSubtype());
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseANotMemberPredicate(ANotMemberPredicate node) {
		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set.getSubtype());
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseASubsetPredicate(ASubsetPredicate node) {
		BType expected = getType(node);
		try {
			BoolType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found 'BOOL'");
		}
		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set);
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseASubsetStrictPredicate(ASubsetStrictPredicate node) {
		BType expected = getType(node);
		try {
			BoolType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found 'BOOL'");
		}

		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set);
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseANotSubsetPredicate(ANotSubsetPredicate node) {
		BType expected = getType(node);
		try {
			BoolType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found 'BOOL'");
		}

		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set);
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseANotSubsetStrictPredicate(ANotSubsetStrictPredicate node) {
		BType expected = getType(node);
		try {
			BoolType.getInstance().unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found 'BOOL'");
		}

		SetType set = new SetType(new UntypedType());

		setType(node.getLeft(), set);
		setType(node.getRight(), set);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAGeneralUnionExpression(AGeneralUnionExpression node) {
		SetType set = new SetType(new SetType(new UntypedType()));
		setType(node.getExpression(), set);
		node.getExpression().apply(this);

		BType found = ((SetType) getType(node.getExpression())).getSubtype();
		BType expected = getType(node);
		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + found + "'");
		}
	}

	@Override
	public void caseAGeneralIntersectionExpression(AGeneralIntersectionExpression node) {
		SetType set = new SetType(new SetType(new UntypedType()));
		setType(node.getExpression(), set);
		node.getExpression().apply(this);

		BType found = ((SetType) getType(node.getExpression())).getSubtype();
		BType expected = getType(node);
		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + found + "'");
		}
	}

	@Override
	public void caseAQuantifiedUnionExpression(AQuantifiedUnionExpression node) {
		BType expected = getType(node);
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(v, u);
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);
		setType(node.getExpression(), new SetType(new UntypedType()));
		node.getExpression().apply(this);

		BType found = getType(node.getExpression());
		try {
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	@Override
	public void caseAQuantifiedIntersectionExpression(AQuantifiedIntersectionExpression node) {
		BType expected = getType(node);
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			UntypedType u = new UntypedType();
			setType(v, u);
		}

		setType(node.getPredicates(), BoolType.getInstance());
		node.getPredicates().apply(this);
		setType(node.getExpression(), new SetType(new UntypedType()));
		node.getExpression().apply(this);

		BType found = getType(node.getExpression());
		try {
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	/**
	 * Functions
	 */

	@Override
	public void caseALambdaExpression(ALambdaExpression node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		for (PExpression e : copy) {
			AIdentifierExpression v = (AIdentifierExpression) e;
			setType(v, new UntypedType());
		}

		setType(node.getPredicate(), BoolType.getInstance());
		node.getPredicate().apply(this);

		setType(node.getExpression(), new UntypedType());
		node.getExpression().apply(this);

		ArrayList<BType> typesList = new ArrayList<BType>();
		for (PExpression e : copy) {
			typesList.add(getType(e));
		}
		BType domain = makePair(typesList);
		BType range = getType(node.getExpression());

		BType found = new FunctionType(domain, range);
		// BType found = new SetType(new PairType(domain, range));

		BType expected = getType(node);
		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	@Override
	public void caseAFunctionExpression(AFunctionExpression node) {
		FunctionType func = new FunctionType(new UntypedType(), new UntypedType());
		setType(node.getIdentifier(), func);
		node.getIdentifier().apply(this);

		BType id = getType(node.getIdentifier());
		BType domainFound;
		BType rangeFound;
		if (id instanceof FunctionType) {
			domainFound = ((FunctionType) id).getDomain();
			rangeFound = ((FunctionType) id).getRange();
		} else {
			PairType p = (PairType) ((SetType) id).getSubtype();
			domainFound = p.getFirst();
			rangeFound = p.getSecond();
		}
		BType expected = getType(node);
		try {
			rangeFound.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + rangeFound + "'");
		}

		ArrayList<PExpression> copy = new ArrayList<PExpression>(node.getParameters());
		for (PExpression e : copy) {
			setType(e, new UntypedType());
			e.apply(this);
		}

		ArrayList<BType> foundList = new ArrayList<BType>();
		for (PExpression e : copy) {
			foundList.add(getType(e));
		}

		BType p = makePair(foundList);
		try {
			domainFound.unify(p, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + domainFound + "' , found '" + makePair(foundList) + "'");
		}
	}

	@Override
	public void caseADomainExpression(ADomainExpression node) {
		FunctionType f = new FunctionType(new UntypedType(), new UntypedType());
		setType(node.getExpression(), f);
		node.getExpression().apply(this);

		BType b = getType(node.getExpression());
		BType domainFound;
		if (b instanceof FunctionType) {
			domainFound = new SetType(((FunctionType) b).getDomain());
		} else {
			PairType p = (PairType) ((SetType) b).getSubtype();
			domainFound = new SetType(p.getFirst());
		}
		BType expected = getType(node);
		try {
			domainFound.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + domainFound + "'");
		}
	}

	@Override
	public void caseARangeExpression(ARangeExpression node) {
		FunctionType f = new FunctionType(new UntypedType(), new UntypedType());
		setType(node.getExpression(), f);
		node.getExpression().apply(this);

		BType b = getType(node.getExpression());
		BType rangeFound;
		if (b instanceof FunctionType) {
			rangeFound = new SetType(((FunctionType) b).getRange());
		} else {
			PairType p = (PairType) ((SetType) b).getSubtype();
			rangeFound = new SetType(p.getSecond());
		}
		BType expected = getType(node);
		try {
			rangeFound.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found '" + rangeFound + "'");
		}
	}

	@Override
	public void caseATotalFunctionExpression(ATotalFunctionExpression node) {
		evalFunction(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseAPartialFunctionExpression(APartialFunctionExpression node) {
		BType dom = new UntypedType();
		BType ran = new UntypedType();
		setType(node.getLeft(), new SetType(dom));
		setType(node.getRight(), new SetType(ran));
		BType expected = getType(node);
		BType found = evalFunctionTypeVsRelationType(node, dom, ran);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
		// evalFunction(node, node.getLeft(), node.getRight());
	}

	private BType evalFunctionTypeVsRelationType(Node node, BType dom, BType ran) {
		String clazz = node.parent().getClass().getName();
		if (clazz.contains("Total") || clazz.contains("Partial"))
			return new SetType(new SetType(new PairType(dom, ran)));
		else
			return new SetType(new FunctionType(dom, ran));
	}

	@Override
	public void caseATotalInjectionExpression(ATotalInjectionExpression node) {
		evalFunction(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseAPartialInjectionExpression(APartialInjectionExpression node) {
		BType dom = new UntypedType();
		BType ran = new UntypedType();
		setType(node.getLeft(), new SetType(dom));
		setType(node.getRight(), new SetType(ran));
		BType expected = getType(node);
		BType found = evalFunctionTypeVsRelationType(node, dom, ran);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
		// evalFunction(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseATotalSurjectionExpression(ATotalSurjectionExpression node) {
		evalFunction(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseAPartialSurjectionExpression(APartialSurjectionExpression node) {
		// evalFunction(node, node.getLeft(), node.getRight());
		BType dom = new UntypedType();
		BType ran = new UntypedType();
		setType(node.getLeft(), new SetType(dom));
		setType(node.getRight(), new SetType(ran));
		BType expected = getType(node);
		BType found = evalFunctionTypeVsRelationType(node, dom, ran);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseATotalBijectionExpression(ATotalBijectionExpression node) {
		evalFunction(node, node.getLeft(), node.getRight());
	}

	@Override
	public void caseAPartialBijectionExpression(APartialBijectionExpression node) {
		// evalFunction(node, node.getLeft(), node.getRight());
		BType dom = new UntypedType();
		BType ran = new UntypedType();
		setType(node.getLeft(), new SetType(dom));
		setType(node.getRight(), new SetType(ran));
		BType expected = getType(node);
		BType found = evalFunctionTypeVsRelationType(node, dom, ran);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	public void evalFunction(Node node, Node left, Node right) {
		setType(left, new SetType(new UntypedType()));
		left.apply(this);
		setType(right, new SetType(new UntypedType()));
		right.apply(this);

		BType leftType = ((SetType) getType(left)).getSubtype();
		BType rightType = ((SetType) getType(right)).getSubtype();

		BType found = new SetType(new FunctionType(leftType, rightType));
		BType expected = getType(node);
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	/**
	 * Relations
	 */

	@Override
	public void caseACoupleExpression(ACoupleExpression node) {
		BType expected = getType(node);

		List<PExpression> copy = new ArrayList<PExpression>(node.getList());

		ArrayList<BType> list = new ArrayList<BType>();

		for (PExpression e : copy) {
			setType(e, new UntypedType());
			e.apply(this);
		}

		for (PExpression e : copy) {
			list.add(getType(e));
		}

		BType found = makePair(list);
		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	@Override
	public void caseARelationsExpression(ARelationsExpression node) {
		setType(node.getLeft(), new SetType(new UntypedType()));
		node.getLeft().apply(this);
		setType(node.getRight(), new SetType(new UntypedType()));
		node.getRight().apply(this);

		BType left = ((SetType) getType(node.getLeft())).getSubtype();
		BType right = ((SetType) getType(node.getRight())).getSubtype();

		BType found = new SetType(new SetType(new PairType(left, right)));
		BType expected = getType(node);
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	@Override
	public void caseAIdentityExpression(AIdentityExpression node) {
		SetType s = new SetType(new UntypedType());
		setType(node.getExpression(), s);
		node.getExpression().apply(this);

		s = (SetType) getType(node.getExpression());

		// BType found = new SetType(new PairType(s.getSubtype(),
		// s.getSubtype()));
		BType found = new FunctionType(s.getSubtype(), s.getSubtype());
		BType expected = getType(node);

		unify(expected, found, node);
	}

	@Override
	public void caseADomainRestrictionExpression(ADomainRestrictionExpression node) {
		UntypedType u = new UntypedType();
		SetType setType = new SetType(u);
		FunctionType f = new FunctionType(u, new UntypedType());
		setType(node.getLeft(), setType);
		setType(node.getRight(), f);
		node.getLeft().apply(this);
		node.getRight().apply(this);

		BType found = getType(node.getRight());
		BType expected = getType(node);
		unify(expected, found, node);
	}

	@Override
	public void caseADomainSubtractionExpression(ADomainSubtractionExpression node) {
		UntypedType u = new UntypedType();
		SetType setType = new SetType(u);
		FunctionType f = new FunctionType(u, new UntypedType());
		setType(node.getLeft(), setType);
		setType(node.getRight(), f);
		node.getLeft().apply(this);
		node.getRight().apply(this);

		BType found = getType(node.getRight());
		BType expected = getType(node);
		unify(expected, found, node);
	}

	@Override
	public void caseARangeRestrictionExpression(ARangeRestrictionExpression node) {
		UntypedType u = new UntypedType();
		SetType setType = new SetType(u);
		FunctionType f = new FunctionType(new UntypedType(), u);
		setType(node.getLeft(), f);
		setType(node.getRight(), setType);
		node.getLeft().apply(this);
		node.getRight().apply(this);

		BType found = getType(node.getLeft());
		BType expected = getType(node);
		unify(expected, found, node);
	}

	@Override
	public void caseARangeSubtractionExpression(ARangeSubtractionExpression node) {
		UntypedType u = new UntypedType();
		SetType setType = new SetType(u);
		FunctionType f = new FunctionType(new UntypedType(), u);
		setType(node.getLeft(), f);
		setType(node.getRight(), setType);
		node.getLeft().apply(this);
		node.getRight().apply(this);

		BType found = getType(node.getLeft());
		BType expected = getType(node);
		unify(expected, found, node);
	}

	@Override
	public void caseAImageExpression(AImageExpression node) {
		BType expected = getType(node);
		BType dom = new UntypedType();
		BType ran = new UntypedType();
		BType found = new SetType(ran);
		BType left = new FunctionType(dom, ran);
		BType right = new SetType(dom);
		setType(node.getLeft(), left);
		setType(node.getRight(), right);
		unify(expected, found, node);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAReverseExpression(AReverseExpression node) {
		BType left = new UntypedType();
		BType right = new UntypedType();

		BType found = new SetType(new PairType(left, right));

		BType expr = new FunctionType(right, left);
		setType(node.getExpression(), expr);
		BType expected = getType(node);

		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAOverwriteExpression(AOverwriteExpression node) {
		BType expected = getType(node);
		BType found = new FunctionType(new UntypedType(), new UntypedType());
		setType(node.getLeft(), found);
		setType(node.getRight(), found);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseADirectProductExpression(ADirectProductExpression node) {
		UntypedType u = new UntypedType();
		UntypedType u1 = new UntypedType();
		UntypedType u2 = new UntypedType();
		BType left = new SetType(new PairType(u, u1));
		BType right = new SetType(new PairType(u, u2));
		setType(node.getLeft(), left);
		setType(node.getRight(), right);

		BType expected = getType(node);
		BType found = new SetType(new PairType(u, new PairType(u1, u2)));
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAParallelProductExpression(AParallelProductExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		BType left = new SetType(new PairType(t, u));
		UntypedType v = new UntypedType();
		UntypedType w = new UntypedType();
		BType right = new SetType(new PairType(v, w));
		setType(node.getLeft(), left);
		setType(node.getRight(), right);
		BType found = new SetType(new PairType(new PairType(t, v), new PairType(u, w)));
		BType expected = getType(node);

		unify(expected, found, node);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseACompositionExpression(ACompositionExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		UntypedType v = new UntypedType();
		BType left = new SetType(new PairType(t, u));
		BType right = new SetType(new PairType(u, v));
		setType(node.getLeft(), left);
		setType(node.getRight(), right);
		BType found = new SetType(new PairType(t, v));
		BType expected = getType(node);

		unify(expected, found, node);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAFirstProjectionExpression(AFirstProjectionExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		BType left = new SetType(t);
		BType right = new SetType(u);
		BType found = new SetType(new PairType(new PairType(t, u), t));
		setType(node.getExp1(), left);
		setType(node.getExp2(), right);
		BType expected = getType(node);

		unify(expected, found, node);

		node.getExp1().apply(this);
		node.getExp2().apply(this);
	}

	@Override
	public void caseASecondProjectionExpression(ASecondProjectionExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		BType left = new SetType(t);
		BType right = new SetType(u);
		BType found = new SetType(new PairType(new PairType(t, u), u));
		setType(node.getExp1(), left);
		setType(node.getExp2(), right);
		BType expected = getType(node);

		unify(expected, found, node);

		node.getExp1().apply(this);
		node.getExp2().apply(this);
	}

	@Override
	public void caseAIterationExpression(AIterationExpression node) {
		UntypedType t = new UntypedType();
		BType found = new SetType(new PairType(t, t));
		setType(node.getRight(), IntegerType.getInstance());
		setType(node.getLeft(), found);
		BType expected = getType(node);

		unify(expected, found, node);

		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAClosureExpression(AClosureExpression node) {
		UntypedType t = new UntypedType();
		BType found = new SetType(new PairType(t, t));
		setType(node.getExpression(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAReflexiveClosureExpression(AReflexiveClosureExpression node) {
		UntypedType t = new UntypedType();
		BType found = new SetType(new PairType(t, t));
		setType(node.getExpression(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseATransFunctionExpression(ATransFunctionExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		setType(node.getExpression(), new SetType(new PairType(t, u)));
		BType found = new SetType(new PairType(t, new SetType(u)));
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseATransRelationExpression(ATransRelationExpression node) {
		UntypedType t = new UntypedType();
		UntypedType u = new UntypedType();
		setType(node.getExpression(), new SetType(new PairType(t, new SetType(u))));
		BType found = new SetType(new PairType(t, u));
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	public void unify(BType expected, BType found, Node node) {
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "' at "
					+ node.getClass().getSimpleName() + "\n " + node.getStartPos() + ":" + node.getEndPos());
		}
	}

	@Override
	public void caseAEmptySequenceExpression(AEmptySequenceExpression node) {
		BType expected = getType(node);
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		try {
			expected.unify(found, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	/**
	 * Sequences
	 */

	@Override
	public void caseASeqExpression(ASeqExpression node) {
		UntypedType t = new UntypedType();
		setType(node.getExpression(), new SetType(t));
		BType found = new SetType(new FunctionType(IntegerType.getInstance(), t));
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseASizeExpression(ASizeExpression node) {
		setType(node.getExpression(), new FunctionType(IntegerType.getInstance(), new UntypedType()));
		BType found = IntegerType.getInstance();
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAConcatExpression(AConcatExpression node) {
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		setType(node.getLeft(), found);
		setType(node.getRight(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAInsertTailExpression(AInsertTailExpression node) {
		UntypedType t = new UntypedType();
		BType found = new FunctionType(IntegerType.getInstance(), t);
		setType(node.getLeft(), found);
		setType(node.getRight(), t);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseAFirstExpression(AFirstExpression node) {
		BType found = new UntypedType();
		setType(node.getExpression(), new FunctionType(IntegerType.getInstance(), found));
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseATailExpression(ATailExpression node) {
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		setType(node.getExpression(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	/**
	 * Sequences Extended
	 */

	private void evalSetOfSequences(Node node, Node expr) {
		UntypedType t = new UntypedType();
		setType(expr, new SetType(t));
		BType found = new SetType(new FunctionType(IntegerType.getInstance(), t));
		BType expected = getType(node);
		unify(expected, found, node);
		expr.apply(this);
	}

	@Override
	public void caseAIseqExpression(AIseqExpression node) {
		evalSetOfSequences(node, node.getExpression());
	}

	@Override
	public void caseAIseq1Expression(AIseq1Expression node) {
		evalSetOfSequences(node, node.getExpression());
	}

	@Override
	public void caseASeq1Expression(ASeq1Expression node) {
		evalSetOfSequences(node, node.getExpression());
	}

	@Override
	public void caseAInsertFrontExpression(AInsertFrontExpression node) {
		UntypedType t = new UntypedType();
		BType found = new FunctionType(IntegerType.getInstance(), t);
		setType(node.getLeft(), t);
		setType(node.getRight(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseALastExpression(ALastExpression node) {
		BType found = new UntypedType();
		setType(node.getExpression(), new FunctionType(IntegerType.getInstance(), found));
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAPermExpression(APermExpression node) {
		evalSetOfSequences(node, node.getExpression());
	}

	@Override
	public void caseARevExpression(ARevExpression node) {
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		setType(node.getExpression(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAFrontExpression(AFrontExpression node) {
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		setType(node.getExpression(), found);
		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseAGeneralConcatExpression(AGeneralConcatExpression node) {

		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		setType(node.getExpression(), new FunctionType(IntegerType.getInstance(), found));

		// BType found = new SetType(new PairType(IntegerType.getInstance(),
		// new UntypedType()));
		// setType(node.getExpression(),
		// new SetType(new PairType(IntegerType.getInstance(), found)));

		BType expected = getType(node);
		unify(expected, found, node);
		node.getExpression().apply(this);
	}

	@Override
	public void caseARestrictFrontExpression(ARestrictFrontExpression node) {
		UntypedType t = new UntypedType();
		BType found = new FunctionType(IntegerType.getInstance(), t);
		setType(node.getLeft(), found);
		setType(node.getRight(), IntegerType.getInstance());
		BType expected = getType(node);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseARestrictTailExpression(ARestrictTailExpression node) {
		UntypedType t = new UntypedType();
		BType found = new FunctionType(IntegerType.getInstance(), t);
		setType(node.getLeft(), found);
		setType(node.getRight(), IntegerType.getInstance());
		BType expected = getType(node);
		unify(expected, found, node);
		node.getLeft().apply(this);
		node.getRight().apply(this);
	}

	@Override
	public void caseASequenceExtensionExpression(ASequenceExtensionExpression node) {
		BType expected = getType(node);
		BType found = new FunctionType(IntegerType.getInstance(), new UntypedType());
		try {
			found = found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
		BType subtype;
		if (found instanceof FunctionType) {
			subtype = ((FunctionType) found).getRange();
		} else {
			PairType p = (PairType) ((SetType) found).getSubtype();
			subtype = p.getSecond();
		}
		for (PExpression e : node.getExpression()) {
			setType(e, subtype);
		}
		List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
		for (PExpression e : copy) {
			e.apply(this);
		}
	}

	/**
	 * Records
	 */

	@Override
	public void caseARecExpression(ARecExpression node) {
		StructType found = new StructType();
		found.setComplete();

		List<PRecEntry> copy = new ArrayList<PRecEntry>(node.getEntries());
		for (PRecEntry e2 : copy) {
			ARecEntry e = (ARecEntry) e2;
			setType(e.getValue(), new UntypedType());
			e.getValue().apply(this);

			AIdentifierExpression i = (AIdentifierExpression) e.getIdentifier();
			String name = Utils.getTIdentifierListAsString(i.getIdentifier());
			found.add(name, getType(e.getValue()));
		}
		BType expected = getType(node);
		try {
			unify(expected, found, node);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	@Override
	public void caseARecordFieldExpression(ARecordFieldExpression node) {
		StructType s = new StructType();
		AIdentifierExpression i = (AIdentifierExpression) node.getIdentifier();
		String fieldName = Utils.getTIdentifierListAsString(i.getIdentifier());
		s.add(fieldName, new UntypedType());
		setType(node.getRecord(), s);

		node.getRecord().apply(this);

		BType found = ((StructType) getType(node.getRecord())).getType(fieldName);
		BType expected = getType(node);
		try {
			unify(expected, found, node);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}

	}

	@Override
	public void caseAStructExpression(AStructExpression node) {
		StructType s = new StructType();
		s.setComplete();

		List<PRecEntry> copy = new ArrayList<PRecEntry>(node.getEntries());
		for (PRecEntry e2 : copy) {
			ARecEntry e = (ARecEntry) e2;
			setType(e.getValue(), new SetType(new UntypedType()));
			e.getValue().apply(this);

			AIdentifierExpression i = (AIdentifierExpression) e.getIdentifier();
			String name = Utils.getTIdentifierListAsString(i.getIdentifier());
			BType t = ((SetType) getType(e.getValue())).getSubtype();
			s.add(name, t);
		}
		BType found = new SetType(s);

		BType expected = getType(node);
		try {
			found.unify(expected, this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + expected + "' , found " + found + "'");
		}
	}

	/**
	 * Strings
	 */

	@Override
	public void caseAStringExpression(AStringExpression node) {
		try {
			StringType.getInstance().unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found " + StringType.getInstance() + "'");
		}
	}

	@Override
	public void caseAStringSetExpression(AStringSetExpression node) {
		SetType found = new SetType(StringType.getInstance());
		try {
			found.unify(getType(node), this);
		} catch (UnificationException e) {
			throw new TypeErrorException("Excepted '" + getType(node) + "' , found " + found + "'");
		}
	}

	public void inALabelPredicate(ALabelPredicate node) {
		BType type = getType(node);
		setType(node.getPredicate(), type);
	}

}
