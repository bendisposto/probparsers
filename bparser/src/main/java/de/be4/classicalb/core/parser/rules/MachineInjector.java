package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAssertionsMachineClause;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.AConstantsMachineClause;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetsMachineClause;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PDefinition;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.PSet;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TDefLiteralPredicate;

public class MachineInjector extends DepthFirstAdapter {
	AAbstractMachineParseUnit abstractMachineParseUnit;
	List<PMachineClause> clausesList;

	AAbstractConstantsMachineClause abstractConstantsClause;
	ASetsMachineClause setsClause;
	AConstantsMachineClause constantsClause;
	APropertiesMachineClause propertiesClause;
	AVariablesMachineClause variablesClause;
	AInvariantMachineClause invariantClause;
	AAssertionsMachineClause assertionsClause;
	AInitialisationMachineClause initialisationClause;
	AOperationsMachineClause operationClause;
	ADefinitionsMachineClause definitionsClause;
	HashSet<String> definitionNames = new HashSet<>();
	private APredicateDefinitionDefinition goalDefinition;
	private List<PDefinition> mainMachineDefinitions;

	public MachineInjector(Start start) {
		start.apply(this);
	}

	public void injectMachine(Start otherMachine) {
		ClauseFinder finder = new ClauseFinder();
		otherMachine.apply(finder);
	}

	public APredicateDefinitionDefinition getGoalDefinition() {
		return this.goalDefinition;
	}

	public List<PDefinition> getMainMachineDefinitions() {
		return this.mainMachineDefinitions;
	}

	@Override
	public void inAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
		this.abstractMachineParseUnit = node;
		this.clausesList = node.getMachineClauses();
	}

	@Override
	public void inASetsMachineClause(ASetsMachineClause node) {
		this.setsClause = node;
	}

	@Override
	public void inAAbstractConstantsMachineClause(AAbstractConstantsMachineClause node) {
		this.abstractConstantsClause = node;
	}

	@Override
	public void inAConstantsMachineClause(AConstantsMachineClause node) {
		this.constantsClause = node;
	}

	@Override
	public void inAPropertiesMachineClause(APropertiesMachineClause node) {
		this.propertiesClause = node;
	}

	@Override
	public void inADefinitionsMachineClause(ADefinitionsMachineClause node) {
		this.definitionsClause = node;
	}

	@Override
	public void inAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
		definitionNames.add(node.getName().getText());
	}

	@Override
	public void inASubstitutionDefinitionDefinition(ASubstitutionDefinitionDefinition node) {
		definitionNames.add(node.getName().getText());
	}

	@Override
	public void inAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
		definitionNames.add(node.getName().getText());
	}

	@Override
	public void caseAVariablesMachineClause(final AVariablesMachineClause node) {
		this.variablesClause = node;
	}

	@Override
	public void caseAInvariantMachineClause(final AInvariantMachineClause node) {
		this.invariantClause = node;
	}

	@Override
	public void caseAAssertionsMachineClause(final AAssertionsMachineClause node) {
		this.assertionsClause = node;
	}

	@Override
	public void caseAInitialisationMachineClause(final AInitialisationMachineClause node) {
		this.initialisationClause = node;
	}

	@Override
	public void caseAOperationsMachineClause(AOperationsMachineClause node) {
		this.operationClause = node;
	}

	class ClauseFinder extends DepthFirstAdapter {

		@Override
		public void outAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
			node.setMachineClauses(new LinkedList<PMachineClause>());
		}

		@Override
		public void inASetsMachineClause(ASetsMachineClause node) {
			if (setsClause == null) {
				setsClause = node;
				clausesList.add(node);
			} else {

				LinkedList<PSet> sets = setsClause.getSetDefinitions();
				for (PSet set : node.getSetDefinitions()) {
					sets.add(set);
				}
			}
		}

		@Override
		public void inAConstantsMachineClause(AConstantsMachineClause node) {
			if (constantsClause == null) {
				constantsClause = node;
				clausesList.add(node);
			} else {
				LinkedList<PExpression> identifiers = constantsClause.getIdentifiers();
				for (PExpression id : node.getIdentifiers()) {
					identifiers.add(id);
				}
			}
		}

		@Override
		public void inAAbstractConstantsMachineClause(AAbstractConstantsMachineClause node) {
			if (abstractConstantsClause == null) {
				abstractConstantsClause = node;
				clausesList.add(node);
			} else {
				LinkedList<PExpression> identifiers = abstractConstantsClause.getIdentifiers();
				for (PExpression id : node.getIdentifiers()) {
					identifiers.add(id);
				}
			}
		}

		@Override
		public void inAPropertiesMachineClause(APropertiesMachineClause node) {
			if (propertiesClause == null) {
				propertiesClause = node;
				clausesList.add(node);
			} else {
				AConjunctPredicate con = new AConjunctPredicate(propertiesClause.getPredicates(), node.getPredicates());
				propertiesClause.setPredicates(con);
			}
		}

		@Override
		public void inAVariablesMachineClause(final AVariablesMachineClause node) {
			if (variablesClause == null) {
				variablesClause = node;
				clausesList.add(node);
			} else {
				LinkedList<PExpression> identifiers = variablesClause.getIdentifiers();
				for (PExpression id : node.getIdentifiers()) {
					identifiers.add(id);
				}
			}

			Collections.sort(variablesClause.getIdentifiers(), new Comparator<PExpression>() {
				@Override
				public int compare(PExpression o1, PExpression o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
		}

		@Override
		public void inAInvariantMachineClause(final AInvariantMachineClause node) {
			if (invariantClause == null) {
				invariantClause = node;
				clausesList.add(node);
			} else {
				AConjunctPredicate con = new AConjunctPredicate(invariantClause.getPredicates(), node.getPredicates());
				invariantClause.setPredicates(con);
			}

		}

		@Override
		public void inAAssertionsMachineClause(final AAssertionsMachineClause node) {
			if (assertionsClause == null) {
				assertionsClause = node;
				clausesList.add(node);
			} else {
				assertionsClause.getPredicates().addAll(node.getPredicates());
			}

		}

		@Override
		public void inAInitialisationMachineClause(final AInitialisationMachineClause node) {
			if (initialisationClause == null) {
				initialisationClause = node;
				clausesList.add(node);
			} else {
				List<PSubstitution> list = new ArrayList<>();
				list.add(initialisationClause.getSubstitutions());
				list.add(node.getSubstitutions());
				ASequenceSubstitution seq = new ASequenceSubstitution(list);
				initialisationClause.setSubstitutions(seq);
			}
		}

		@Override
		public void inAOperationsMachineClause(AOperationsMachineClause node) {
			if (operationClause == null) {
				operationClause = node;
				clausesList.add(node);
			} else {
				operationClause.getOperations().addAll(0, node.getOperations());
			}

		}

		@Override
		public void caseADefinitionsMachineClause(ADefinitionsMachineClause node) {
			if (definitionsClause == null) {
				definitionsClause = new ADefinitionsMachineClause(new ArrayList<PDefinition>());
				clausesList.add(definitionsClause);
			}
			boolean first = false;
			if (mainMachineDefinitions == null) {
				first = true;
				mainMachineDefinitions = new ArrayList<>();
			}

			for (PDefinition def : node.getDefinitions()) {
				def.apply(this);
				// using the first GOAL definition
				if (def instanceof APredicateDefinitionDefinition && goalDefinition == null) {
					TDefLiteralPredicate name = ((APredicateDefinitionDefinition) def).getName();
					if ("GOAL".equals(name.getText())) {
						goalDefinition = (APredicateDefinitionDefinition) def;
					}
				}
				if (first) {
					mainMachineDefinitions.add(def);
				}
			}

		}

		@Override
		public void inAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
			final String name = node.getName().getText();
			if (!definitionNames.contains(name)) {
				definitionNames.add(name);
				definitionsClause.getDefinitions().add(node);
			}
		}

		@Override
		public void inASubstitutionDefinitionDefinition(ASubstitutionDefinitionDefinition node) {
			final String name = node.getName().getText();
			if (!definitionNames.contains(name)) {
				definitionNames.add(name);
				definitionsClause.getDefinitions().add(node);
			}
		}

		@Override
		public void inAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
			final String name = node.getName().getText();
			if (!definitionNames.contains(name)) {
				definitionNames.add(name);
				definitionsClause.getDefinitions().add(node);
			}
		}

	}

}
