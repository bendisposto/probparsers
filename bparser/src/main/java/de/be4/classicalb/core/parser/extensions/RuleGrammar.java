package de.be4.classicalb.core.parser.extensions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.transforming.rules.RuleTransformation;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TKwConstantDependencies;
import de.be4.classicalb.core.parser.node.TKwCounterexample;
import de.be4.classicalb.core.parser.node.TKwExpect;
import de.be4.classicalb.core.parser.node.TKwFor;
import de.be4.classicalb.core.parser.node.TKwForAll;
import de.be4.classicalb.core.parser.node.TKwPredicateOperator;
import de.be4.classicalb.core.parser.node.TKwRule;
import de.be4.classicalb.core.parser.node.TKwRuleForAll;
import de.be4.classicalb.core.parser.node.TKwRulesMachine;
import de.be4.classicalb.core.parser.node.TKwSubstitutionOperator;
import de.be4.classicalb.core.parser.node.TMachine;
import de.be4.classicalb.core.parser.node.Token;

public class RuleGrammar implements IGrammar {

	public static final String SUCCEEDED_RULES = "SUCCEEDED_RULES";
	public static final String FAILED_RULES = "FAILED_RULES";
	public static final String NOT_CHECKED_RULES = "NOT_CHECKED_RULES";
	public static final String DEPENDS_ON_RULES = "DEPENDS_ON_RULES";

	public static final String RULE_FAIL = "RULE_FAIL";
	public static final String RULE_SUCCESS = "RULE_SUCCESS";

	private static RuleGrammar ruleExtension;

	public static RuleGrammar getInstance() {
		if (ruleExtension == null) {
			ruleExtension = new RuleGrammar();
		}
		return ruleExtension;
	}

	private RuleGrammar() {
		// singleton
	}

	private static final HashMap<String, Class<? extends Token>> map = new HashMap<>();
	static {
		add(TKwRule.class);
		add(TKwForAll.class);
		add(TKwExpect.class);
		add(TKwCounterexample.class);
		add(TKwRuleForAll.class);
		add(TKwFor.class);
		add(TKwConstantDependencies.class);
		map.put(new TKwRulesMachine().getText(), TMachine.class);
		map.put(SUCCEEDED_RULES, TKwPredicateOperator.class);
		map.put(FAILED_RULES, TKwPredicateOperator.class);
		map.put(NOT_CHECKED_RULES, TKwPredicateOperator.class);
		map.put(DEPENDS_ON_RULES, TKwPredicateOperator.class);
		map.put(RULE_FAIL, TKwSubstitutionOperator.class);
		map.put(RULE_SUCCESS, TKwSubstitutionOperator.class);
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

	public void applyAstTransformation(Start start, BParser bparser) throws CheckException {
		new RuleTransformation(start, bparser).runTransformation();
	}
}
