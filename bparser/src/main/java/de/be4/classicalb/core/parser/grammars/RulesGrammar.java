package de.be4.classicalb.core.parser.grammars;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TKwAttributeIdentifier;
import de.be4.classicalb.core.parser.node.TKwBody;
import de.be4.classicalb.core.parser.node.TKwComputation;
import de.be4.classicalb.core.parser.node.TKwCounterexample;
import de.be4.classicalb.core.parser.node.TKwDefine;
import de.be4.classicalb.core.parser.node.TKwDummyValue;
import de.be4.classicalb.core.parser.node.TKwExpect;
import de.be4.classicalb.core.parser.node.TKwExpressionOperator;
import de.be4.classicalb.core.parser.node.TKwFor;
import de.be4.classicalb.core.parser.node.TKwFunction;
import de.be4.classicalb.core.parser.node.TKwPredicateAttribute;
import de.be4.classicalb.core.parser.node.TKwPredicateOperator;
import de.be4.classicalb.core.parser.node.TKwReferences;
import de.be4.classicalb.core.parser.node.TKwRule;
import de.be4.classicalb.core.parser.node.TKwRuleAny;
import de.be4.classicalb.core.parser.node.TKwRuleErrorType;
import de.be4.classicalb.core.parser.node.TKwRuleForAll;
import de.be4.classicalb.core.parser.node.TKwRulesMachine;
import de.be4.classicalb.core.parser.node.TKwSubstitutionOperator;
import de.be4.classicalb.core.parser.node.TKwType;
import de.be4.classicalb.core.parser.node.TKwValue;
import de.be4.classicalb.core.parser.node.TMachine;
import de.be4.classicalb.core.parser.node.Token;

public class RulesGrammar implements IGrammar {

	public static final String SUCCEEDED_RULE = "SUCCEEDED_RULE";
	public static final String SUCCEEDED_RULE_ERROR_TYPE = "SUCCEEDED_RULE_ERROR_TYPE";
	public static final String FAILED_RULE = "FAILED_RULE";
	public static final String FAILED_RULE_ERROR_TYPE = "FAILED_RULE_ERROR_TYPE";
	public static final String GET_RULE_COUNTEREXAMPLES = "GET_RULE_COUNTEREXAMPLES";
	// public static final String FAILED_RULE_ALL_ERROR_TYPES =
	// "FAILED_RULE_ALL_ERROR_TYPES";
	public static final String NOT_CHECKED_RULE = "NOT_CHECKED_RULE";
	public static final String DISABLED_RULE = "DISABLED_RULE";
	public static final String DEPENDS_ON_RULE = "DEPENDS_ON_RULE";
	public static final String DEPENDS_ON_COMPUTATION = "DEPENDS_ON_COMPUTATION";
	public static final String ERROR_TYPES = "ERROR_TYPES";
	public static final String RULEID = "RULEID";
	public static final String STRING_FORMAT = "STRING_FORMAT";
	public static final String ACTIVATION = "ACTIVATION";
	public static final String PRECONDITION = "PRECONDITION";
	public static final String CLASSIFICATION = "CLASSIFICATION";

	public static final String RULE_FAIL = "RULE_FAIL";

	private static RulesGrammar ruleExtension;

	public static RulesGrammar getInstance() {
		if (ruleExtension == null) {
			ruleExtension = new RulesGrammar();
		}
		return ruleExtension;
	}

	private RulesGrammar() {
		// singleton
	}

	private static final HashMap<String, Class<? extends Token>> map = new HashMap<>();
	static {
		add(TKwRule.class);
		add(TKwExpect.class);
		add(TKwCounterexample.class);
		add(TKwRuleForAll.class);
		add(TKwFor.class);
		add(TKwComputation.class);
		add(TKwDefine.class);
		add(TKwType.class);
		add(TKwValue.class);
		add(TKwDummyValue.class);
		add(TKwFunction.class);
		add(TKwReferences.class);
		add(TKwRuleAny.class);
		add(TKwRuleErrorType.class);
		add(TKwBody.class);

		map.put(new TKwRulesMachine().getText(), TMachine.class);
		map.put(SUCCEEDED_RULE, TKwPredicateOperator.class);
		map.put(SUCCEEDED_RULE_ERROR_TYPE, TKwPredicateOperator.class);
		map.put(FAILED_RULE, TKwPredicateOperator.class);
		map.put(FAILED_RULE_ERROR_TYPE, TKwPredicateOperator.class);
		// map.put(FAILED_RULE_ALL_ERROR_TYPES, TKwPredicateOperator.class);
		map.put(NOT_CHECKED_RULE, TKwPredicateOperator.class);
		map.put(DISABLED_RULE, TKwPredicateOperator.class);

		map.put(DEPENDS_ON_RULE, TKwAttributeIdentifier.class);
		map.put(DEPENDS_ON_COMPUTATION, TKwAttributeIdentifier.class);
		map.put(RULEID, TKwAttributeIdentifier.class);
		map.put(ERROR_TYPES, TKwAttributeIdentifier.class);
		map.put(CLASSIFICATION, TKwAttributeIdentifier.class);

		map.put(ACTIVATION, TKwPredicateAttribute.class);
		map.put(PRECONDITION, TKwPredicateAttribute.class);
		

		map.put(RULE_FAIL, TKwSubstitutionOperator.class);
		map.put(GET_RULE_COUNTEREXAMPLES, TKwExpressionOperator.class);
		map.put(STRING_FORMAT, TKwExpressionOperator.class);
	}

	private static void add(Class<? extends Token> clazz) {
		try {
			map.put(clazz.newInstance().getText(), clazz);
		} catch (InstantiationException e) {
			throw new RuntimeException("Cannot create an instance of class:" + clazz.getName());
		} catch (IllegalAccessException e) {
			// should never happen
			throw new RuntimeException("Cannot create an instance of class:" + clazz.getName());
		}
	}

	public static String getModelType() {
		return new TKwRulesMachine().getText();
	}

	@Override
	public boolean containsAlternativeDefinitionForToken(Token token) {
		if (token instanceof TIdentifierLiteral && map.containsKey(token.getText())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Token createNewToken(Token token) {
		Class<? extends Token> clazz = map.get(token.getText());
		try {
			// default constructor
			Token newToken = clazz.newInstance();
			newToken.setLine(token.getLine());
			newToken.setPos(token.getPos());
			return newToken;
		} catch (InstantiationException e) {
			// if the class has not default constructor we call the
			// construct with a text string as the single argument
			// e.g. TKwPredicateOperator(token.getText)
			Class<?>[] cArg = new Class<?>[] { String.class };
			try {
				Token newInstance = clazz.getDeclaredConstructor(cArg).newInstance(token.getText());
				newInstance.setLine(token.getLine());
				newInstance.setPos(token.getPos());
				return newInstance;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				throw new RuntimeException("Cannot create an instance of class:" + clazz.getName());
			}

		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot create an instance of class:" + clazz.getName());
		}
	}

}
