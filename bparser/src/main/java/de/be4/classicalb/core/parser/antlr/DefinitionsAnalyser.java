package de.be4.classicalb.core.parser.antlr;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

import files.BParserBaseVisitor;
import static files.BParser.*;

public class DefinitionsAnalyser extends BParserBaseVisitor<Void> {
	final ParseTree parseTree;
	HashMap<String, FormulaContext> definitions = new HashMap<>();
	HashMap<String, DefinitionType> definitionsType = new HashMap<>();

	public enum DefinitionType {
		EXPRESSION_DEFINITION, PREDICATE_DEFINITION, SUBSTITUTION_DEFINITION
	}

	public DefinitionsAnalyser(ParseTree tree) {
		this.parseTree = tree;
	}

	@Override
	public Void visitDefinition(DefinitionContext ctx) {
		FormulaContext formula = ctx.formula();
		final String name = ctx.name.getText();
		definitions.put(name, formula);
		if (formula instanceof FormulaPredicateContext) {
			definitionsType.put(name, DefinitionType.PREDICATE_DEFINITION);
		} else if (formula instanceof FormulaSubstitutionContext) {
			definitionsType.put(name, DefinitionType.SUBSTITUTION_DEFINITION);
		} else {
			// handle exception caught during the parsing process

			// no exception -->
			definitionsType.put(name, DefinitionType.EXPRESSION_DEFINITION);
		}
		return null;
	}

	public DefinitionType getDefinitionType(final String definitionName) {
		return definitionsType.get(definitionName);
	}

	public boolean isDefinition(final String definitionName) {
		return definitions.containsKey(definitionName);
	}

	public void analyse() {
		this.parseTree.accept(this);
	}

}
