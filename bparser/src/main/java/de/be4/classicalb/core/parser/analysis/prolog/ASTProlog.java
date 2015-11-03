package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.*;
import de.prob.prolog.output.IPrologTermOutput;

/**
 * This class defines the output of a B machine as a prolog term.
 * 
 * @author plagge
 */
public class ASTProlog extends DepthFirstAdapter {
	// The tables SUM_TYPE and SIMPLE_NAME are used to translate the Java class
	// name to
	// the Prolog functor name.
	// These tables MUST be in sync with BParser.scc.
	// SUM_TYPE must list all sum-types in BPaser.scc.
	// The name of the sum-type is not part of the Prolog functor.

	// SIMPLE_NAME must list all AST Classes that are not part of a sum-type
	// If a class is not a token , not in ATOMIC_TYPE and not in SUM_TYPE we
	// throw an exception.
	private static final List<String> SUM_TYPE = new LinkedList<String>(
			Arrays.asList("expression", "predicate", "machine_clause",
					"substitution", "parse_unit", "model_clause",
					"context_clause", "eventstatus", "argpattern", "set",
					"machine_variant", "definition", "freetype_constructor"));

	private static final List<String> ATOMIC_TYPE = new LinkedList<String>(
			Arrays.asList("event", "freetype", "machine_header",
					"machine_reference", "operation", "rec_entry",
					"values_entry", "witness", "unit"));

	// the simpleFormats are mappings from (simple) class names to prolog
	// functor representing them
	private final Map<String, String> simpleFormats = new HashMap<String, String>();

	// to look up the identifier of each node
	private final PositionPrinter positionPrinter;

	// helper object to print the prolog terms
	private final IPrologTermOutput pout;

	public ASTProlog(final IPrologTermOutput pout,
			final PositionPrinter positionPrinter) {
		this.positionPrinter = positionPrinter;
		this.pout = pout;
		if (positionPrinter != null) {
			positionPrinter.setPrologTermOutput(pout);
		}
	}

	@Override
	public void inStart(final Start node) {
		// intentionally left blank: don't write the start node.
	}

	@Override
	public void outStart(final Start node) {
		pout.flush();
	}

	/**
	 * If the node is not handled otherwise, we just open it (see
	 * {@link #open(Node)}), print the sub-nodes, and close it later in
	 * {@link #defaultOut(Node)}
	 */
	@Override
	public void defaultIn(final Node node) {
		open(node);
	}

	/**
	 * This is the counterpart to {@link #defaultIn(Node)}
	 */
	@Override
	public void defaultOut(final Node node) {
		close(node);
	}

	/**
	 * This prints the functor of a prolog term together with the opening
	 * parenthesis. The first argument of the term is the identifier of the
	 * syntax tree element.
	 * 
	 * @param node
	 *            the node of the syntax tree, never <code>null</code>. It is
	 *            assumed that <code>node</code> is an abstract syntax tree
	 *            element, which class name is A* .
	 */
	private void open(final Node node) {
		pout.openTerm(simpleFormat(node));
		printPosition(node);
	}

	private void printPosition(final Node node) {
		if (positionPrinter != null) {
			positionPrinter.printPosition(node);
		} else {
			pout.printAtom("none");
		}
	}

	/**
	 * The counterpart to {@link #open(Node)}, prints the closing parenthesis of
	 * the term.
	 * 
	 * @param node
	 */
	private void close(final Node node) {
		pout.closeTerm();
	}

	/**
	 * Print a list of syntax tree elements as a Prolog list (
	 * <code>[term1, ..., termN]</code>)
	 * 
	 * @param nodes
	 *            A list of nodes, never <code>null</code>. The list may be
	 *            empty. The list does not use generics, because subclasses of
	 *            <code>Node</code> are used, too.
	 */
	private void printAsList(@SuppressWarnings("rawtypes") final List nodes) {
		pout.openList();
		for (Object elem : nodes) {
			((Node) elem).apply(this);
		}
		pout.closeList();
	}

	/**
	 * This method combines {@link #open(Node)}, {@link #printAsList(List)} and
	 * {@link #close(Node)}.
	 * 
	 * @param node
	 *            Like in {@link #open(Node)}
	 * @param list
	 *            Like in {@link #printAsList(List)}
	 */
	private void printOCAsList(final Node node,
			@SuppressWarnings("rawtypes") final List list) {
		open(node);
		printAsList(list);
		close(node);
	}

	/**
	 * @param node
	 *            Never <code>null</code>, node is assumed to be a terminal
	 *            symbol that can be printed as a simple string
	 */
	@Override
	public void defaultCase(final Node node) {
		pout.printAtom(node.toString().trim());
	}

	@Override
	public void caseTStringLiteral(TStringLiteral node) {
		String text = node.getText();
		pout.printAtom(text);
	}

	@Override
	public void caseTUnitContent(TUnitContent node) {
		String text = node.getText();
		String content = text.substring(1, text.length() - 1);
		pout.printString(content);
	}

	@Override
	public void caseEOF(final EOF node) {
		// do nothing
	}

	/**
	 * 
	 * @param AST
	 *            node
	 * @return Corresponging Prolog functor Name.
	 */
	private String simpleFormat(final Node node) {
		String className = node.getClass().getSimpleName();
		String formatted = simpleFormats.get(className);
		if (formatted == null) {
			formatted = toFunctorName(className);
			simpleFormats.put(className, formatted);
		}
		return formatted;
	}

	/**
	 * The translation from the names in the SableCC grammar to prolog functors
	 * must be systematic Otherwise it will not be possible to reuse the grammar
	 * for non-Java front-ends. Two magic cases here: "prover_comprehension_set"
	 * -> "comprehension_set" "op" -> "operation_call" Todo: do remove magic
	 * special cases DO NOT add extra special cases here !!
	 * 
	 * @param Java
	 *            class name
	 * @return Prolog functor name
	 */
	private String toFunctorName(final String className) {
		String camelName = formatCamel(className.substring(1)).substring(1);
		if (className.startsWith("T")) {
			// A SableCC Token
			return camelName;
		}

		if (className.startsWith("A")) {
			if (ATOMIC_TYPE.contains(camelName))
				return camelName;
			for (String checkend : SUM_TYPE)
				if (camelName.endsWith(checkend)) {
					String shortName = camelName.substring(0,
							camelName.length() - checkend.length() - 1);
					// hard-coded renamings
					if (shortName.equals("prover_comprehension_set"))
						return "comprehension_set";
					if (shortName.equals("op"))
						return "operation_call";
					return shortName;
				}
		}
		// There is no rule to translate the class name to a prolog functor.
		// Probably the class name is missing in table SUM_TYPE or in table
		// ATOMIC_TYPE.
		throw new RuntimeException("cannot determine functor name");
	}

	/**
	 * 
	 * @param input
	 *            A string with an identifier in camel style (e.g.
	 *            ClassDoingSomeStuff), never <code>null</code>.
	 * @return The input string in lower case and seperated by _ (e.g.
	 *         class_doing_some_stuff).
	 */
	private String formatCamel(final String input) {
		StringWriter out = new StringWriter();
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char current = chars[i];
			if (Character.isUpperCase(current)) {
				out.append('_');
				out.append(Character.toLowerCase(current));
			} else {
				out.append(current);
			}
		}
		return out.toString();
	}

	private void printIdentifier(final LinkedList<TIdentifierLiteral> list) {
		pout.printAtom(Utils.getIdentifierAsString(list));
	}

	private void printNullSafeSubstitution(final Node subst) {
		if (subst == null) {
			pout.openTerm("skip");
			pout.printAtom("none");
			pout.closeTerm();
		} else {
			subst.apply(this);
		}
	}

	@Override
	public void caseAIdentifierExpression(final AIdentifierExpression node) {
		open(node);
		printIdentifier(node.getIdentifier());
		close(node);
	}

	@Override
	public void caseAPrimedIdentifierExpression(
			final APrimedIdentifierExpression node) {
		open(node);
		printIdentifier(node.getIdentifier());
		pout.printNumber(Long.parseLong((node.getGrade().getText())));
		close(node);
	}

	/***************************************************************************
	 * special cases with lists
	 */

	// Parse Units
	@Override
	public void caseAAbstractMachineParseUnit(
			final AAbstractMachineParseUnit node) {
		open(node);
		node.getVariant().apply(this);
		node.getHeader().apply(this);
		printAsList(node.getMachineClauses());
		close(node);
	}

	@Override
	public void caseARefinementMachineParseUnit(
			final ARefinementMachineParseUnit node) {
		open(node);
		node.getHeader().apply(this);
		node.getRefMachine().apply(this);
		printAsList(node.getMachineClauses());
		close(node);
	}

	@Override
	public void caseAImplementationMachineParseUnit(
			final AImplementationMachineParseUnit node) {
		open(node);
		node.getHeader().apply(this);
		node.getRefMachine().apply(this);
		printAsList(node.getMachineClauses());
		close(node);
	}

	// machine header

	@Override
	public void caseAMachineHeader(final AMachineHeader node) {
		open(node);
		printIdentifier(node.getName());
		printAsList(node.getParameters());
		close(node);
	}

	@Override
	public void caseAExtendedExprExpression(final AExtendedExprExpression node) {
		open(node);
		pout.printAtom(node.getIdentifier().getText());
		printAsList(node.getExpressions());
		printAsList(node.getPredicates());
		close(node);
	}

	@Override
	public void caseAExtendedPredPredicate(final AExtendedPredPredicate node) {
		open(node);
		pout.printAtom(node.getIdentifier().getText());
		printAsList(node.getExpressions());
		printAsList(node.getPredicates());
		close(node);
	}

	// machine clauses

	@Override
	public void caseADefinitionsMachineClause(
			final ADefinitionsMachineClause node) {
		printOCAsList(node, node.getDefinitions());
	}

	@Override
	public void caseASeesMachineClause(final ASeesMachineClause node) {
		printOCAsList(node, node.getMachineNames());
	}

	@Override
	public void caseAPromotesMachineClause(final APromotesMachineClause node) {
		printOCAsList(node, node.getOperationNames());
	}

	@Override
	public void caseAUsesMachineClause(final AUsesMachineClause node) {
		printOCAsList(node, node.getMachineNames());
	}

	@Override
	public void caseAIncludesMachineClause(final AIncludesMachineClause node) {
		printOCAsList(node, node.getMachineReferences());
	}

	@Override
	public void caseAExtendsMachineClause(final AExtendsMachineClause node) {
		printOCAsList(node, node.getMachineReferences());
	}

	@Override
	public void caseAImportsMachineClause(final AImportsMachineClause node) {
		printOCAsList(node, node.getMachineReferences());
	}

	@Override
	public void caseASetsMachineClause(final ASetsMachineClause node) {
		printOCAsList(node, node.getSetDefinitions());
	}

	@Override
	public void caseAVariablesMachineClause(final AVariablesMachineClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAConcreteVariablesMachineClause(
			final AConcreteVariablesMachineClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAAbstractConstantsMachineClause(
			final AAbstractConstantsMachineClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAConstantsMachineClause(final AConstantsMachineClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAAssertionsMachineClause(final AAssertionsMachineClause node) {
		printOCAsList(node, node.getPredicates());
	}

	@Override
	public void caseAValuesMachineClause(final AValuesMachineClause node) {
		printOCAsList(node, node.getEntries());
	}

	@Override
	public void caseALocalOperationsMachineClause(
			final ALocalOperationsMachineClause node) {
		printOCAsList(node, node.getOperations());
	}

	@Override
	public void caseAOperationsMachineClause(final AOperationsMachineClause node) {
		printOCAsList(node, node.getOperations());
	}

	// machine reference

	@Override
	public void caseAMachineReference(final AMachineReference node) {
		open(node);
		printIdentifier(node.getMachineName());
		printAsList(node.getParameters());
		close(node);
	}

	// definition

	@Override
	public void caseAPredicateDefinitionDefinition(
			final APredicateDefinitionDefinition node) {
		open(node);
		node.getName().apply(this);
		printAsList(node.getParameters());
		node.getRhs().apply(this);
		close(node);
	}

	@Override
	public void caseASubstitutionDefinitionDefinition(
			final ASubstitutionDefinitionDefinition node) {
		open(node);
		node.getName().apply(this);
		printAsList(node.getParameters());
		node.getRhs().apply(this);
		close(node);
	}

	@Override
	public void caseAExpressionDefinitionDefinition(
			final AExpressionDefinitionDefinition node) {
		open(node);
		node.getName().apply(this);
		printAsList(node.getParameters());
		node.getRhs().apply(this);
		close(node);
	}

	// set

	@Override
	public void caseAEnumeratedSetSet(final AEnumeratedSetSet node) {
		open(node);
		printIdentifier(node.getIdentifier());
		printAsList(node.getElements());
		close(node);
	}

	// operation

	@Override
	public void caseAOperation(final AOperation node) {
		open(node);
		pout.openTerm("identifier");
		printPosition(node);
		printIdentifier(node.getOpName());
		pout.closeTerm();
		printAsList(node.getReturnValues());
		printAsList(node.getParameters());
		node.getOperationBody().apply(this);
		close(node);
	}

	// predicate

	@Override
	public void caseAForallPredicate(final AForallPredicate node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getImplication().apply(this);
		close(node);
	}

	@Override
	public void caseAExistsPredicate(final AExistsPredicate node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicate().apply(this);
		close(node);
	}

	@Override
	public void caseADefinitionPredicate(final ADefinitionPredicate node) {
		open(node);
		node.getDefLiteral().apply(this);
		printAsList(node.getParameters());
		close(node);
	}

	// expression

	@Override
	public void caseAGeneralSumExpression(final AGeneralSumExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseAGeneralProductExpression(
			final AGeneralProductExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseACoupleExpression(final ACoupleExpression node) {
		printOCAsList(node, node.getList());
	}

	@Override
	public void caseAComprehensionSetExpression(
			final AComprehensionSetExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		close(node);
	}

	@Override
	public void caseASymbolicComprehensionSetExpression(
			final ASymbolicComprehensionSetExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		close(node);
	}

	@Override
	public void caseAProverComprehensionSetExpression(
			final AProverComprehensionSetExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		close(node);
	}

	@Override
	public void caseAEventBComprehensionSetExpression(
			final AEventBComprehensionSetExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getExpression().apply(this);
		node.getPredicates().apply(this);
		close(node);
	}

	@Override
	public void caseASetExtensionExpression(final ASetExtensionExpression node) {
		printOCAsList(node, node.getExpressions());
	}

	@Override
	public void caseAQuantifiedUnionExpression(
			final AQuantifiedUnionExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseAQuantifiedIntersectionExpression(
			final AQuantifiedIntersectionExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicates().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseALambdaExpression(final ALambdaExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicate().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseASymbolicLambdaExpression(ASymbolicLambdaExpression node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicate().apply(this);
		node.getExpression().apply(this);
		close(node);
	}

	@Override
	public void caseASequenceExtensionExpression(
			final ASequenceExtensionExpression node) {
		printOCAsList(node, node.getExpression());
	}

	@Override
	public void caseAFunctionExpression(final AFunctionExpression node) {
		open(node);
		node.getIdentifier().apply(this);
		printAsList(node.getParameters());
		close(node);
	}

	@Override
	public void caseARecExpression(final ARecExpression node) {
		printOCAsList(node, node.getEntries());
	}

	@Override
	public void caseAStructExpression(final AStructExpression node) {
		printOCAsList(node, node.getEntries());
	}

	@Override
	public void caseAIntegerExpression(final AIntegerExpression node) {
		open(node);
		final String text = node.getLiteral().getText();
		if (text.length() < 17) {
			pout.printNumber(Long.parseLong(text));
		} else {
			pout.printNumber(new BigInteger(text));
		}
		close(node);
	}

	@Override
	public void caseADefinitionExpression(final ADefinitionExpression node) {
		open(node);
		node.getDefLiteral().apply(this);
		printAsList(node.getParameters());
		close(node);
	}

	// substitutions

	@Override
	public void caseAAssignSubstitution(final AAssignSubstitution node) {
		open(node);
		printAsList(node.getLhsExpression());
		printAsList(node.getRhsExpressions());
		close(node);
	}

	@Override
	public void caseAChoiceSubstitution(final AChoiceSubstitution node) {
		printOCAsList(node, node.getSubstitutions());
	}

	@Override
	public void caseAIfSubstitution(final AIfSubstitution node) {
		open(node);
		node.getCondition().apply(this);
		node.getThen().apply(this);
		printAsList(node.getElsifSubstitutions());
		printNullSafeSubstitution(node.getElse());
		close(node);
	}

	@Override
	public void caseASelectSubstitution(final ASelectSubstitution node) {
		open(node);
		node.getCondition().apply(this);
		node.getThen().apply(this);
		printAsList(node.getWhenSubstitutions());
		final Node elsenode = node.getElse();
		if (elsenode != null) {
			elsenode.apply(this);
		}
		close(node);
	}

	@Override
	public void caseACaseSubstitution(final ACaseSubstitution node) {
		open(node);
		node.getExpression().apply(this);
		printAsList(node.getEitherExpr());
		node.getEitherSubst().apply(this);
		printAsList(node.getOrSubstitutions());
		printNullSafeSubstitution(node.getElse());
		close(node);
	}

	@Override
	public void caseACaseOrSubstitution(final ACaseOrSubstitution node) {
		open(node);
		printAsList(node.getExpressions());
		node.getSubstitution().apply(this);
		close(node);
	}

	@Override
	public void caseAAnySubstitution(final AAnySubstitution node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getWhere().apply(this);
		node.getThen().apply(this);
		close(node);
	}

	@Override
	public void caseALetSubstitution(final ALetSubstitution node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicate().apply(this);
		node.getSubstitution().apply(this);
		close(node);
	}

	@Override
	public void caseABecomesElementOfSubstitution(
			final ABecomesElementOfSubstitution node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getSet().apply(this);
		close(node);
	}

	@Override
	public void caseABecomesSuchSubstitution(final ABecomesSuchSubstitution node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getPredicate().apply(this);
		close(node);
	}

	@Override
	public void caseAVarSubstitution(final AVarSubstitution node) {
		open(node);
		printAsList(node.getIdentifiers());
		node.getSubstitution().apply(this);
		close(node);
	}

	@Override
	public void caseASequenceSubstitution(final ASequenceSubstitution node) {
		printOCAsList(node, node.getSubstitutions());
	}

	@Override
	public void caseAOpSubstitution(final AOpSubstitution node) {
		open(node);
		node.getName().apply(this);
		pout.emptyList();
		printAsList(node.getParameters());
		close(node);
	}

	@Override
	public void caseAOperationCallSubstitution(
			final AOperationCallSubstitution node) {
		open(node);
		pout.openTerm("identifier");
		printPosition(node);
		printIdentifier(node.getOperation());
		pout.closeTerm();
		printAsList(node.getResultIdentifiers());
		printAsList(node.getParameters());
		close(node);
	}

	@Override
	public void caseAParallelSubstitution(final AParallelSubstitution node) {
		printOCAsList(node, node.getSubstitutions());
	}

	@Override
	public void caseADefinitionSubstitution(final ADefinitionSubstitution node) {
		open(node);
		node.getDefLiteral().apply(this);
		printAsList(node.getParameters());
		close(node);
	}

	// true and false

	@Override
	public void caseABooleanTrueExpression(final ABooleanTrueExpression node) {
		pout.openTerm("boolean_true");
		printPosition(node);
		pout.closeTerm();
	}

	@Override
	public void caseAPartitionPredicate(final APartitionPredicate node) {
		open(node);
		node.getSet().apply(this);
		printAsList(node.getElements());
		close(node);
	}

	// ignore some nodes

	@Override
	public void caseAExpressionParseUnit(final AExpressionParseUnit node) {
		node.getExpression().apply(this);
	}

	@Override
	public void caseAMachineClauseParseUnit(final AMachineClauseParseUnit node) {
		node.getMachineClause().apply(this);
	}

	@Override
	public void caseAPredicateParseUnit(final APredicateParseUnit node) {
		node.getPredicate().apply(this);
	}

	@Override
	public void caseASubstitutionParseUnit(final ASubstitutionParseUnit node) {
		node.getSubstitution().apply(this);
	}

	@Override
	public void caseAEventBModelParseUnit(final AEventBModelParseUnit node) {
		open(node);
		node.getName().apply(this);
		printAsList(node.getModelClauses());
		close(node);
	}

	@Override
	public void caseAVariablesModelClause(final AVariablesModelClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseASeesModelClause(final ASeesModelClause node) {
		printOCAsList(node, node.getSees());
	}

	@Override
	public void caseAInvariantModelClause(final AInvariantModelClause node) {
		printOCAsList(node, node.getPredicates());
	}

	@Override
	public void caseATheoremsModelClause(final ATheoremsModelClause node) {
		printOCAsList(node, node.getPredicates());
	}

	@Override
	public void caseAEventsModelClause(final AEventsModelClause node) {
		printOCAsList(node, node.getEvent());
	}

	@Override
	public void caseAEvent(final AEvent node) {
		open(node);
		node.getEventName().apply(this);
		final PEventstatus status = node.getStatus();
		if (status != null) {
			status.apply(this);
		}
		printAsList(node.getRefines());
		printAsList(node.getVariables());
		printAsList(node.getGuards());
		printAsList(node.getTheorems());
		printAsList(node.getAssignments());
		printAsList(node.getWitness());
		close(node);
	}

	@Override
	public void caseAWitness(final AWitness node) {
		open(node);
		pout.openTerm("identifier");
		printPosition(node);
		pout.printAtom(node.getName().getText().trim());
		pout.closeTerm();
		node.getPredicate().apply(this);
		close(node);
	}

	@Override
	public void caseAEventBContextParseUnit(final AEventBContextParseUnit node) {
		open(node);
		node.getName().apply(this);
		printAsList(node.getContextClauses());
		close(node);
	}

	@Override
	public void caseAExtendsContextClause(final AExtendsContextClause node) {
		printOCAsList(node, node.getExtends());
	}

	@Override
	public void caseASetsContextClause(final ASetsContextClause node) {
		printOCAsList(node, node.getSet());
	}

	@Override
	public void caseAConstantsContextClause(final AConstantsContextClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAAbstractConstantsContextClause(
			final AAbstractConstantsContextClause node) {
		printOCAsList(node, node.getIdentifiers());
	}

	@Override
	public void caseAAxiomsContextClause(final AAxiomsContextClause node) {
		printOCAsList(node, node.getPredicates());
	}

	@Override
	public void caseATheoremsContextClause(final ATheoremsContextClause node) {
		printOCAsList(node, node.getPredicates());
	}

	@Override
	public void caseAOppatternParseUnit(final AOppatternParseUnit node) {
		open(node);
		printIdentifier(node.getName());
		printAsList(node.getParameters());
		close(node);
	}

	@Override
	public void caseAFreetypesMachineClause(AFreetypesMachineClause node) {
		printOCAsList(node, node.getFreetypes());
	}

	@Override
	public void caseAFreetype(AFreetype node) {
		open(node);
		pout.printAtom(node.getName().getText());
		printAsList(node.getConstructors());
		close(node);
	}

	@Override
	public void caseAConstructorFreetypeConstructor(
			AConstructorFreetypeConstructor node) {
		open(node);
		pout.printAtom(node.getName().getText());
		node.getArgument().apply(this);
		close(node);
	}

	@Override
	public void caseAElementFreetypeConstructor(AElementFreetypeConstructor node) {
		open(node);
		pout.printAtom(node.getName().getText());
		close(node);
	}
}
