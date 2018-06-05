package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.*;
import de.be4.classicalb.core.parser.util.Utils;

/**
 * This class checks several dependencies between machine clauses. Examples are:
 * Which clauses are valid for an abstract machine? Is a PROPERTIES clause
 * present, if a CONSTANTS clause is used?
 * 
 * All violations of checks are collected in <code>exceptions</code> and can be
 * retrieved by {@link #getCheckExceptions()}.
 * 
 * @author Fabian
 * 
 */
public class ClausesCheck implements SemanticCheck {

	private static final String NAME_LOCAL_OPERATIONS = ALocalOperationsMachineClause.class.getSimpleName();
	private static final String NAME_VALUES = AValuesMachineClause.class.getSimpleName();
	private static final String NAME_IMPORTS = AImportsMachineClause.class.getSimpleName();
	private static final String NAME_CONSTANTS = AConstantsMachineClause.class.getSimpleName();
	private static final String NAME_ABSTRACT_CONSTANTS = AAbstractConstantsMachineClause.class.getSimpleName();
	private static final String NAME_VARIABLES = AVariablesMachineClause.class.getSimpleName();
	private static final String NAME_CONCRETE_VARIABLES = AConcreteVariablesMachineClause.class.getSimpleName();
	private static final String NAME_PROPERTIES = APropertiesMachineClause.class.getSimpleName();
	private static final String NAME_INVARIANT = AInvariantMachineClause.class.getSimpleName();
	private static final String NAME_INITIALISATION = AInitialisationMachineClause.class.getSimpleName();
	private static final String NAME_USES = AUsesMachineClause.class.getSimpleName();
	private static final String NAME_CONSTRAINTS = AConstraintsMachineClause.class.getSimpleName();
	private static final String NAME_INCLUDES = AIncludesMachineClause.class.getSimpleName();

	private static final String[] MACHINE_FORBIDDEN_CLAUSES = new String[] { /* NAME_LOCAL_OPERATIONS, */ NAME_IMPORTS,
			NAME_VALUES };
	private static final String[] REFINEMENT_FORBIDDEN_CLAUSES = new String[] { NAME_USES, NAME_CONSTRAINTS,
			NAME_LOCAL_OPERATIONS, NAME_IMPORTS, NAME_VALUES };
	private static final String[] IMPLEMENTATION_FORBIDDEN_CLAUSES = new String[] { NAME_CONSTRAINTS, NAME_INCLUDES,
			NAME_USES, NAME_ABSTRACT_CONSTANTS, NAME_VARIABLES, NAME_VARIABLES };

	private Map<String, Set<Node>> clauses;

	private final List<CheckException> exceptions = new ArrayList<>();

	/**
	 * The following requirements for the machine clauses are checked:
	 * <ul>
	 * <li>In MACHINEs, REFINEMENTs and IMPLEMENATATIONs different clauses are
	 * not allowed.</li>
	 * <li>If one of the CONCRETE_CONSTANTS, CONSTANTS or ABSTRACT_CONSTANTS
	 * clauses is present, then the PROPERTIES clause must be present.</li>
	 * <li>If one of the CONCRETE_VARIABLES, VARIABLES or ABSTRACT_VARIABLES
	 * clauses is present, then the INVARIANT and INITIALISATION clauses must be
	 * present.</li>
	 * </ul>
	 * 
	 */
	public void runChecks(final Start rootNode) {
		// only need to check complete machines
		if (!Utils.isCompleteMachine(rootNode)) {
			return;
		}

		final ClausesCollector collector = new ClausesCollector();
		rootNode.apply(collector);
		clauses = collector.getAvailableClauses();

		checkDoubleClauses();
		checkMachineClauses(rootNode);
		checkRefinementClauses(rootNode);
		checkImplementationClauses(rootNode);
		checkConstantsClause();
		checkVariablesClauses();
		if (collector.hasScalarParameter() && !collector.isRefinement()) {
			checkConstraintExistance(rootNode);
		}
	}

	private void checkConstraintExistance(Start rootNode) {

		if (!clauses.containsKey(NAME_CONSTRAINTS)) {
			exceptions.add(new CheckException("Specification has formal scalar parameter and no CONSTRAINTS clause.",
					rootNode.getPParseUnit()));
		}
	}

	/**
	 * If the machine is a IMPLEMENTATION the following clauses are not allowed:
	 * CONSTRAINTS, INCLUDES, USES, ABSTRACT_CONSTANTS, ABSTRACT_VARIABLES,
	 * VARIABLES
	 */
	private void checkImplementationClauses(final Start rootNode) {
		if (!(rootNode.getPParseUnit() instanceof AImplementationMachineParseUnit)) {
			return;
		}

		findForbidden(IMPLEMENTATION_FORBIDDEN_CLAUSES);
	}

	/**
	 * If the machine is a REFINEMENT the following clauses are not allowed:
	 * USES, CONSTRAINTS, LOCAL_VARIABLES, VALUES, IMPORTS
	 */
	private void checkRefinementClauses(final Start rootNode) {
		if (!(rootNode.getPParseUnit() instanceof ARefinementMachineParseUnit)) {
			return;
		}

		findForbidden(REFINEMENT_FORBIDDEN_CLAUSES);
	}

	/**
	 * If the machine is a MACHINE the following clauses are not allowed:
	 * LOCAL_VARIABLES, VALUES, IMPORTS
	 */
	private void checkMachineClauses(final Start rootNode) {
		if (!(rootNode.getPParseUnit() instanceof AAbstractMachineParseUnit)) {
			return;
		}

		findForbidden(MACHINE_FORBIDDEN_CLAUSES);
	}

	private void checkVariablesClauses() {
		/*
		 * CONCRETE_VARIABLES || VARIABLES || ABSTRACT_VARIABLES => INVARIANT &&
		 * INITIALISATION
		 */
		if ((clauses.containsKey(NAME_VARIABLES) || clauses.containsKey(NAME_CONCRETE_VARIABLES))
				&& (!clauses.containsKey(NAME_INVARIANT) || !clauses.containsKey(NAME_INITIALISATION))) {

			final Set<Node> nodes = new HashSet<>();
			if (clauses.containsKey(NAME_VARIABLES)) {
				nodes.addAll(clauses.get(NAME_VARIABLES));
			}
			if (clauses.containsKey(NAME_CONCRETE_VARIABLES)) {
				nodes.addAll(clauses.get(NAME_CONCRETE_VARIABLES));
			}

			final StringBuilder message = new StringBuilder("Clause(s) missing: ");
			boolean first = true;
			if (!clauses.containsKey(NAME_INVARIANT)) {
				message.append("INVARIANT");
				first = false;
			}
			if (!clauses.containsKey(NAME_INITIALISATION)) {
				if (!first) {
					message.append(", ");
				}
				message.append("INITIALISATION");
			}
			exceptions.add(new CheckException(message.toString(), nodes.toArray(new Node[nodes.size()])));
		}
	}

	private void checkConstantsClause() {
		/*
		 * CONCRETE_CONSTANTS || CONSTANTS || ABSTRACT_CONSTANTS => PROPERTIES
		 */
		if ((clauses.containsKey(NAME_CONSTANTS) || clauses.containsKey(NAME_ABSTRACT_CONSTANTS))
				&& !clauses.containsKey(NAME_PROPERTIES)) {
			final Set<Node> nodes = new HashSet<>();

			if (clauses.containsKey(NAME_CONSTANTS)) {
				nodes.addAll(clauses.get(NAME_CONSTANTS));
			}
			if (clauses.containsKey(NAME_ABSTRACT_CONSTANTS)) {
				nodes.addAll(clauses.get(NAME_ABSTRACT_CONSTANTS));
			}
			exceptions.add(new CheckException("Clause(s) missing: PROPERTIES", nodes.toArray(new Node[nodes.size()])));
		}
	}

	private void findForbidden(final String[] forbiddenClassNames) {
		final Set<String> clauseClasses = clauses.keySet();

		final Set<Set<Node>> wrongClauses = new HashSet<>();

		for (int i = 0; i < forbiddenClassNames.length; i++) {
			if (clauseClasses.contains(forbiddenClassNames[i])) {
				wrongClauses.add(clauses.get(forbiddenClassNames[i]));
			}
		}

		if (!wrongClauses.isEmpty()) {
			final Set<Node> nodes = new HashSet<>();

			for (final Iterator<Set<Node>> iterator = wrongClauses.iterator(); iterator.hasNext();) {
				final Set<Node> nodeSet = iterator.next();
				nodes.addAll(nodeSet);
			}
			exceptions.add(new CheckException("Clause not allowed in abstract machine",
					nodes.toArray(new Node[nodes.size()])));
		}
	}

	/**
	 * Checks if one clause is used more than once in the machine.
	 */
	private void checkDoubleClauses() {
		for (final Iterator<Set<Node>> iterator = clauses.values().iterator(); iterator.hasNext();) {
			final Set<Node> nodesforClause = iterator.next();

			if (nodesforClause.size() > 1) {
				final Node clauseNode = nodesforClause.iterator().next();
				final String simpleClassName = clauseNode.getClass().getSimpleName();
				final int endIndex = simpleClassName.indexOf("MachineClause");
				final String clauseName = simpleClassName.substring(1, endIndex).toUpperCase();

				exceptions.add(new CheckException("Clause '" + clauseName + "' is used more than once",
						nodesforClause.toArray(new Node[nodesforClause.size()])));
			}
		}
	}

	public void setOptions(ParseOptions options) {
		// ignore options
	}

	@Override
	public List<CheckException> getCheckExceptions() {

		return exceptions;
	}
}
