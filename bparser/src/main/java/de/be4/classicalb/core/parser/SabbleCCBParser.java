package de.be4.classicalb.core.parser;

import java.util.HashSet;

import de.be4.classicalb.core.parser.lexer.Lexer;
import de.be4.classicalb.core.parser.parser.Parser;

public class SabbleCCBParser extends Parser {

	private static HashSet<String> productionRulesToBeOptimised = new HashSet<>();
	static {
		productionRulesToBeOptimised.add("AMultiComposedIdentifierList");
		productionRulesToBeOptimised.add("AMultipleExpressionList");
		productionRulesToBeOptimised.add("AMultiIdentifierList");
	}

	public SabbleCCBParser(Lexer lexer) {
		super(lexer);
	}

	@Override
	protected boolean addElementsFromListToNewList(String productionRuleAsString) {
		if (productionRulesToBeOptimised.contains(productionRuleAsString)) {
			return false; // apply optimisation
		}
		return true; // no optimisation
	}
}
