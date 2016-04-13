package de.be4.classicalb.core.parser.extensions;

import java.util.HashMap;

import de.be4.classicalb.core.parser.analysis.transforming.RuleTransformation;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TKwDependsOnRules;
//import de.be4.classicalb.core.parser.node.TKwDependsOnRule;
import de.be4.classicalb.core.parser.node.TKwExpect;
import de.be4.classicalb.core.parser.node.TKwForAll;
import de.be4.classicalb.core.parser.node.TKwRule;
import de.be4.classicalb.core.parser.node.TKwRuleFail;
import de.be4.classicalb.core.parser.node.TKwRuleSuccess;
import de.be4.classicalb.core.parser.node.TKwRulesMachine;
import de.be4.classicalb.core.parser.node.TMachine;
import de.be4.classicalb.core.parser.node.Token;

public class RuleGrammar implements IGrammar {

	
	private static RuleGrammar ruleExtension;
	public static RuleGrammar  getInstance(){
		if(ruleExtension == null){
			ruleExtension = new RuleGrammar();
		}
		return ruleExtension;
	}
	
	private RuleGrammar(){}
	
	
	private static final HashMap<String, Class<? extends Token>> map = new HashMap<>();
	static {
		add(TKwRule.class);
		add(TKwRuleFail.class);
		add(TKwRuleSuccess.class);
		add(TKwDependsOnRules.class);
		add(TKwForAll.class);
		add(TKwExpect.class);
		
		map.put(new TKwRulesMachine().getText() ,TMachine.class);
	}
	
	private static void add(Class<? extends Token> clazz) {
		try {
			map.put(clazz.newInstance().getText(), clazz);
		} catch (InstantiationException e) {
			throw new RuntimeException("Cannot create an instance of class:"
					+ clazz.getName());
		} catch (IllegalAccessException e) {
			// should never happen
			throw new RuntimeException("Cannot create an instance of class:"
					+ clazz.getName());
		}
	}
	
	public static String getModelType(){
		return new TKwRulesMachine().getText();
	}

	@Override
	public boolean containsAlternativeDefinitionForToken(Token token) {
		if (token instanceof TIdentifierLiteral
				&& map.containsKey(token.getText())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Token createNewToken(Token token) {
		Class<? extends Token> clazz = map.get(token.getText());
		try {
			Token newToken = clazz.newInstance();
			newToken.setLine(token.getLine());
			newToken.setPos(token.getPos());
			return newToken;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Cannot create an instance of class:"
					+ clazz.getName());
		}
	}

	@Override
	public void applyAstTransformation(Start start) {
		start.apply(new RuleTransformation());
	}
}
