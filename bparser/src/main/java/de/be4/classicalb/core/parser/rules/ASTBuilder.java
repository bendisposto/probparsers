package de.be4.classicalb.core.parser.rules;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.node.ABooleanFalseExpression;
import de.be4.classicalb.core.parser.node.ABooleanTrueExpression;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.AEmptySequenceExpression;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AMultOrCartExpression;
import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.ASeqExpression;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.ASkipSubstitution;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.AStringSetExpression;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ATotalFunctionExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.TDefLiteralSubstitution;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.be4.classicalb.core.parser.util.NodeCloner;
import de.hhu.stups.sablecc.patch.PositionedNode;

public final class ASTBuilder {

	public static final String FORCE = "FORCE";
	public static final String STRING_APPEND = "STRING_APPEND";
	public static final String CHOOSE = "CHOOSE";
	public static final String TO_STRING = "TO_STRING";
	public static final String FORMAT_TO_STRING = "FORMAT_TO_STRING";
	public static final String SORT = "SORT";
	public static final String PRINT = "PRINT";
	public static final String PREFERENCES_PREFIX = "SET_PREF_";

	private ASTBuilder() {
		// contains only static methods
	}

	public static PPredicate createConjunction(List<PPredicate> predList) {
		if (predList.isEmpty()) {
			throw new AssertionError();
		} else if (predList.size() == 1) {
			return predList.get(0);
		} else {
			PPredicate p = predList.get(0);
			for (int i = 1; i < predList.size(); i++) {
				p = new AConjunctPredicate(p, predList.get(i));
			}
			return p;
		}
	}

	public static PExpression createSetOfPExpression(PExpression pExpression, PositionedNode pos) {
		final ArrayList<PExpression> list = new ArrayList<>();
		list.add((PExpression) cloneNode(pExpression));
		return createPositinedNode(new ASetExtensionExpression(list), pos);
	}

	public static PExpression createSetOfPExpression(PExpression... pExpressions) {
		final ArrayList<PExpression> list = new ArrayList<>();
		for (PExpression pExpression : pExpressions) {
			list.add((PExpression) cloneNode(pExpression));
		}
		return new ASetExtensionExpression(list);
	}

	public static PSubstitution createSequenceSubstitution(PSubstitution sub1, PSubstitution sub2,
			PSubstitution... subs) {
		List<PSubstitution> subList = new ArrayList<>();
		subList.add(sub1);
		subList.add(sub2);
		for (PSubstitution pSubstitution : subs) {
			subList.add(pSubstitution);
		}
		return new ASequenceSubstitution(subList);
	}

	public static <T extends PositionedNode> T createPositinedNode(T node, PositionedNode pos) {
		node.setStartPos(pos.getStartPos());
		node.setEndPos(pos.getEndPos());
		return node;
	}

	public static AStringExpression createStringExpression(String string) {
		return new AStringExpression(new TStringLiteral(string));
	}

	public static AIdentifierExpression createRuleIdentifier(TIdentifierLiteral ruleLiteral) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add((TIdentifierLiteral) cloneNode((ruleLiteral)));
		return new AIdentifierExpression(list);
	}

	public static List<PSubstitution> createSubstitutionList(PSubstitution... pSubstitutions) {
		List<PSubstitution> list = new ArrayList<>();
		for (PSubstitution pSubstitution : pSubstitutions) {
			list.add(pSubstitution);
		}
		return list;
	}

	public static List<PExpression> createExpressionList(PExpression... pExpressions) {
		final List<PExpression> list = new ArrayList<>();
		for (int i = 0; i < pExpressions.length; i++) {
			PExpression node = cloneNode(pExpressions[i]);
			list.add(node);
		}
		return list;
	}

	public static AIdentifierExpression createIdentifier(String name) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		return new AIdentifierExpression(list);
	}

	public static AIdentifierExpression createIdentifier(String name, PositionedNode positionNode) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral literal = new TIdentifierLiteral(name);
		// literal.setStartPos(positionNode.getStartPos());
		// literal.setEndPos(positionNode.getEndPos());
		list.add(literal);
		AIdentifierExpression result = new AIdentifierExpression(list);
		result.setStartPos(positionNode.getStartPos());
		result.setEndPos(positionNode.getEndPos());
		return result;
	}

	public static AIdentifierExpression createAIdentifierExpression(TIdentifierLiteral identifierLiteral) {
		final String name = identifierLiteral.getText();
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral literal = new TIdentifierLiteral(name);
		list.add(literal);
		AIdentifierExpression result = new AIdentifierExpression(list);
		result.setStartPos(identifierLiteral.getStartPos());
		result.setEndPos(identifierLiteral.getEndPos());
		return result;
	}

	public static List<PExpression> createIdentifierList(String... strings) {
		ArrayList<PExpression> list = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			list.add(createIdentifier(strings[i]));
		}
		return list;
	}

	public static AEqualPredicate createEqualPredicate(TIdentifierLiteral old, final String value) {
		TIdentifierLiteral e = NodeCloner.cloneNode(old);
		AIdentifierExpression aIdentifier = createAIdentifierExpression(e);
		final AEqualPredicate equal = new AEqualPredicate(aIdentifier,
				new AStringExpression(new TStringLiteral(value)));
		equal.setStartPos(e.getStartPos());
		equal.setEndPos(e.getEndPos());
		return equal;
	}

	private static List<PExpression> createExpressionList(String... names) {
		List<PExpression> list = new ArrayList<>();
		for (String name : names) {
			list.add(createIdentifier(name));
		}
		return list;
	}

	public static void addToStringDefinition(IDefinitions definitions) {
		if (definitions.containsDefinition(TO_STRING)) {
			return;
		}
		/*-
		 * TO_STRING(S) == "0"; 
		 * EXTERNAL_FUNCTION_TO_STRING(X) == X -->STRING;
		 */

		AExpressionDefinitionDefinition toStringDef = new AExpressionDefinitionDefinition();
		toStringDef.setName(new TIdentifierLiteral(TO_STRING));
		toStringDef.setParameters(createIdentifierList("S"));
		toStringDef.setRhs(new AStringExpression(new TStringLiteral("0")));
		definitions.addDefinition(toStringDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_TO_STRING"));
		toStringTypeDef.setParameters(createIdentifierList("X"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(createIdentifier("X"), new AStringSetExpression()));
		definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
	}

	public static void addPrintSubDefinitionToIdefinitions(IDefinitions definitions) {
		if (definitions.containsDefinition(PRINT)) {
			return;
		}

		/*-
		 * PRINT(x) == skip; 
		 * EXTERNAL_SUBSTITUTION_PRINT(T) == T; /* declare as external for any type T
		 */
		ASubstitutionDefinitionDefinition printDef = new ASubstitutionDefinitionDefinition();
		printDef.setName(new TDefLiteralSubstitution(PRINT));
		printDef.setParameters(createIdentifierList("value"));
		printDef.setRhs(new ASkipSubstitution());
		definitions.addDefinition(printDef, IDefinitions.Type.Substitution);

		AExpressionDefinitionDefinition forceDefType = new AExpressionDefinitionDefinition();
		forceDefType.setName(new TIdentifierLiteral("EXTERNAL_SUBSTITUTION_" + PRINT));
		forceDefType.setParameters(createIdentifierList("T"));
		forceDefType.setRhs(createIdentifier("T"));
		definitions.addDefinition(forceDefType, IDefinitions.Type.Expression);
	}

	public static void addForceDefinition(IDefinitions iDefinitions) {
		if (iDefinitions.containsDefinition(FORCE)) {
			return;
		}
		/*-
		 * EXTERNAL_FUNCTION_FORCE(T) == T --> T; 
		 * FORCE(value) == value;
		 * forces evaluation of symbolic set representations 
		 * usage: FORCE({ x | x:1..100 & x mod 2 = 0 } )
		 */

		AExpressionDefinitionDefinition forceDef = new AExpressionDefinitionDefinition();
		forceDef.setName(new TIdentifierLiteral(FORCE));
		String value = "value";
		forceDef.setParameters(createIdentifierList(value));
		forceDef.setRhs(createIdentifier(value));
		iDefinitions.addDefinition(forceDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition forceDefType = new AExpressionDefinitionDefinition();
		forceDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_" + FORCE));
		forceDefType.setParameters(createIdentifierList("T"));
		forceDefType.setRhs(new ATotalFunctionExpression(createIdentifier("T"), createIdentifier("T")));
		iDefinitions.addDefinition(forceDefType, IDefinitions.Type.Expression);
	}

	public static void addChooseDefinition(IDefinitions iDefinitions) {
		if (iDefinitions.containsDefinition(CHOOSE)) {
			return;
		}
		/*-
		 * TO_STRING(S) == "0";
		 * EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
		 */

		AExpressionDefinitionDefinition chooseDef = new AExpressionDefinitionDefinition();
		chooseDef.setName(new TIdentifierLiteral(CHOOSE));
		chooseDef.setParameters(createIdentifierList("X"));
		chooseDef.setRhs(new AStringExpression(new TStringLiteral("a member of X")));
		iDefinitions.addDefinition(chooseDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition chooseDefType = new AExpressionDefinitionDefinition();
		chooseDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_CHOOSE"));
		chooseDefType.setParameters(createIdentifierList("T"));
		chooseDefType.setRhs(
				new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")), createIdentifier("T")));
		iDefinitions.addDefinition(chooseDefType, IDefinitions.Type.Expression);
	}

	public static void addSortDefinition(IDefinitions iDefinitions) {
		if (iDefinitions.containsDefinition(SORT)) {
			return;
		}
		/*- SORT
		 *  SORT(X) == [];
		 *  EXTERNAL_FUNCTION_SORT(T) == POW(T)-->seq(T);
		 */
		AExpressionDefinitionDefinition sortDef = new AExpressionDefinitionDefinition();
		sortDef.setName(new TIdentifierLiteral(SORT));
		sortDef.setParameters(createExpressionList("X"));
		sortDef.setRhs(new AEmptySequenceExpression());
		iDefinitions.addDefinition(sortDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition sortType = new AExpressionDefinitionDefinition();
		sortType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_SORT"));
		sortType.setParameters(createExpressionList("T"));
		sortType.setRhs(new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")),
				new ASeqExpression(createIdentifier("T"))));
		iDefinitions.addDefinition(sortType, IDefinitions.Type.Expression);

	}

	public static void addFormatToStringDefinition(IDefinitions iDefinitions) {
		if (iDefinitions.containsDefinition(FORMAT_TO_STRING)) {
			return;
		}
		/*-
		 * FORMAT_TO_STRING(MyFormatString,ListOfValues) == "0";
		 * EXTERNAL_FUNCTION_FORMAT_TO_STRING(TO_STRING_TYPE) == STRING*seq(TO_STRING_TYPE) --> STRING;
		 */

		AExpressionDefinitionDefinition formatDef = new AExpressionDefinitionDefinition();
		formatDef.setName(new TIdentifierLiteral(FORMAT_TO_STRING));
		formatDef.setParameters(createExpressionList("S", "T"));
		formatDef.setRhs(new AStringExpression(new TStringLiteral("abc")));
		iDefinitions.addDefinition(formatDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition formatType = new AExpressionDefinitionDefinition();
		formatType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_FORMAT_TO_STRING"));
		formatType.setParameters(createExpressionList("T"));
		formatType.setRhs(new ATotalFunctionExpression(
				new AMultOrCartExpression(new AStringSetExpression(), new ASeqExpression(createIdentifier("T"))),
				new AStringSetExpression()));
		iDefinitions.addDefinition(formatType, IDefinitions.Type.Expression);
	}

	public static void addBooleanPreferenceDefinition(IDefinitions iDefinitions, String name, boolean bool) {
		AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition(new TIdentifierLiteral(name),
				new ArrayList<PExpression>(), bool ? new ABooleanTrueExpression() : new ABooleanFalseExpression());
		iDefinitions.addDefinition(def, IDefinitions.Type.Expression);
	}

	public static void addGeneralPreferenceDefinitions(IDefinitions iDefinitions, Map<String, String> map) {
		for (Entry<String, String> entry : map.entrySet()) {
			addGeneralPreferenceDefinition(iDefinitions, entry.getKey(), entry.getValue());
		}
	}

	public static void addGeneralPreferenceDefinition(IDefinitions iDefinitions, String name, String value) {
		if (iDefinitions.containsDefinition(name)) {
			return;
		}
		if ("TRUE".equals(value)) {
			addBooleanPreferenceDefinition(iDefinitions, PREFERENCES_PREFIX + name, true);
		} else if ("FALSE".equals(value)) {
			addBooleanPreferenceDefinition(iDefinitions, PREFERENCES_PREFIX + name, false);
		} else {
			if (isIntValue(value)) {
				AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition(
						new TIdentifierLiteral(PREFERENCES_PREFIX + name), new ArrayList<PExpression>(),
						new AIntegerExpression(new TIntegerLiteral(value)));
				iDefinitions.addDefinition(def, IDefinitions.Type.Expression);
			} else {
				AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition(
						new TIdentifierLiteral(PREFERENCES_PREFIX + name), new ArrayList<PExpression>(),
						new AStringExpression(new TStringLiteral(value)));
				iDefinitions.addDefinition(def, IDefinitions.Type.Expression);
			}
		}

	}

	private static boolean isIntValue(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
