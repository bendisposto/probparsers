package de.prob.parser.antlr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.hhu.stups.sablecc.patch.SourcecodeRange;
import files.BParserBaseVisitor;
import files.BParser.*;

public class DefinitionsAnalyser implements IDefinitions {
	final ParseTree parseTree;
	HashMap<String, FormulaContext> definitions = new HashMap<>();
	HashMap<String, DefinitionType> definitionTypes = new HashMap<>();
	HashMap<String, Integer> definitionParameterNumber = new HashMap<>();
	HashMap<String, String> definitionDependencies = new HashMap<>();

	public DefinitionsAnalyser(ParseTree tree) {
		this.parseTree = tree;
	}

	public void analyse() {
		/*
		 * find all definitions and try to determine their by inspecting the top
		 * level node of the right side
		 */
		DefinitionFinder definitionFinder = new DefinitionFinder();
		this.parseTree.accept(definitionFinder);

		/*
		 * find all uses of definition in the complete CST compare the usage
		 * with the type of the definition
		 */
		DefinitionUsage definitionUsage = new DefinitionUsage();
		this.parseTree.accept(definitionUsage);

		HashSet<String> defsWithUnknownType = new HashSet<>();
		for (Iterator< String>iterator = definitionTypes.keySet().iterator(); iterator.hasNext();) {
			String defName = (String) iterator.next();
			DefinitionType definitionType = this.getDefinitionType(defName);
			if (definitionType == DefinitionType.UNKNOWN_TYPE) {
				defsWithUnknownType.add(defName);
			}
		}

		boolean progress = true;
		while (!defsWithUnknownType.isEmpty() && progress) {
			progress = false;
			for (Iterator< String>iterator = new HashSet<String>(defsWithUnknownType).iterator(); iterator.hasNext();) {
				String defName = iterator.next();
				String dependency = definitionDependencies.get(defName);
				if (this.isDefinition(dependency)) {
					DefinitionType otherType = this.getDefinitionType(dependency);
					if (otherType != DefinitionType.UNKNOWN_TYPE) {
						defsWithUnknownType.remove(defName);
						definitionTypes.put(defName, otherType);
						progress = true;
					}
				}
			}
		}

		for (Iterator< String>iterator = definitionTypes.keySet().iterator(); iterator.hasNext();) {
			String defName = (String) iterator.next();
			DefinitionType definitionType = this.getDefinitionType(defName);
			System.out.println(defName + " " + definitionType);
			if (definitionType == DefinitionType.UNKNOWN_TYPE) {
				// throw new RuntimeException("Unkown type of definition: " +
				// defName);
			}
		}

	}

	class DefinitionFinder extends BParserBaseVisitor<Void> {
		@Override
		public Void visitOrdinaryDefinition(OrdinaryDefinitionContext ctx) {
			FormulaContext formula = ctx.formula();
			final String name = ctx.name.getText();
			definitions.put(name, formula);
			List<Token> parameters = ctx.parameters;
			int numberOfParameter;
			if (parameters == null) {
				numberOfParameter = 0;
			} else {
				numberOfParameter = parameters.size();
			}
			definitionParameterNumber.put(name, numberOfParameter);

			if (formula instanceof FormulaPredicateContext) {
				definitionTypes.put(name, DefinitionType.PREDICATE_DEFINITION);
			} else if (formula instanceof FormulaSubstitutionContext) {
				definitionTypes.put(name, DefinitionType.SUBSTITUTION_DEFINITION);
			} else if (formula instanceof FormulaExpressionContext) {
				definitionTypes.put(name, DefinitionType.EXPRESSION_DEFINITION);
			} else {
				// ambiguous
				definitionTypes.put(name, DefinitionType.UNKNOWN_TYPE);

				FormulaAmbiguousCallContext call = (FormulaAmbiguousCallContext) formula;
				ComposedIdentifierContext composedIdentifier = (ComposedIdentifierContext) call.composed_identifier();

				List<TerminalNode> tokens = composedIdentifier.IDENTIFIER();
				String otherName = tokens.get(0).getText();

				if (tokens.size() == 1) {
					definitionDependencies.put(name, otherName);
				}

			}
			return null;
		}
	}

	class DefinitionUsage extends BParserBaseVisitor<Void> {
		@Override
		public Void visitSubstitutionIdentifierCall(SubstitutionIdentifierCallContext ctx) {
			final ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
			final List<TerminalNode> identifiers = subNode.IDENTIFIER();
			final TerminalNode id = identifiers.get(0);
			Expression_listContext expression_list = ctx.expression_list();
			int numberOfArguments = (expression_list == null) ? 0 : expression_list.exprs.size();

			if (identifiers.size() == 1 && DefinitionsAnalyser.this.isDefinition(id.getText())) {
				addDefinitionTypeUsage(id.getText(), DefinitionType.SUBSTITUTION_DEFINITION, subNode,
						numberOfArguments);
			}
			visitChildren(ctx);
			return null;
		}

		@Override
		public Void visitExpressionIdentifier(ExpressionIdentifierContext ctx) {
			ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
			List<TerminalNode> identifiers = subNode.IDENTIFIER();
			final String firstName = identifiers.get(0).getText();
			if (identifiers.size() == 1 && DefinitionsAnalyser.this.isDefinition(firstName)) {
				addDefinitionTypeUsage(firstName, DefinitionType.EXPRESSION_DEFINITION, ctx, 0);
			}
			return null;
		}

		@Override
		public Void visitExpressionFunctionCall(ExpressionFunctionCallContext ctx) {
			final ExpressionContext function = ctx.function;
			final List<Expression_in_parContext> arguments = ctx.arguments;
			if (function instanceof ExpressionIdentifierContext) {
				ExpressionIdentifierContext identifierContext = (ExpressionIdentifierContext) function;
				ComposedIdentifierContext subNode = (ComposedIdentifierContext) identifierContext.composed_identifier();
				List<TerminalNode> tokens = subNode.IDENTIFIER();
				final TerminalNode id = tokens.get(0);
				if (tokens.size() == 1 && DefinitionsAnalyser.this.isDefinition(id.getText())) {
					addDefinitionTypeUsage(id.getText(), DefinitionType.EXPRESSION_DEFINITION, function,
							arguments.size());
				}
				//do not visit function again
				for (Expression_in_parContext expression_in_parContext : ctx.arguments) {
					expression_in_parContext.accept(this);
				}
				return null;
			}
			visitChildren(ctx);
			return null;
		}

		private void addDefinitionTypeUsage(final String defName, final DefinitionType type,
				final ParserRuleContext context, int numberOfArguments) {

			if (definitionTypes.get(defName) != DefinitionType.UNKNOWN_TYPE && definitionTypes.get(defName) != type) {
				SourcecodeRange sourcecodeRange = new SourcecodeRange(context.getStart().getCharPositionInLine() + 1,
						context.getStop().getCharPositionInLine() + context.getStop().getText().length() + 1);
				String msg = "Invalid definition type. Expected: " + definitionTypes.get(defName);
				BParseException exception = new BParseException(null, msg);
				throw new RuntimeException(exception);
			}
			final int numberOfParameter = definitionParameterNumber.get(defName);
			if (numberOfParameter != numberOfArguments) {
				throw new RuntimeException("Number of Parameter does not match for definition '" + defName
						+ "'. Expected: " + numberOfParameter + ", found: " + numberOfArguments + "\nLine: "
						+ context.getStart().getLine());
			}

			if (definitionTypes.get(defName) == DefinitionType.UNKNOWN_TYPE) {
				definitionTypes.put(defName, type);
				return;
			}

		}
	}

	public DefinitionType getDefinitionType(final String definitionName) {
		return definitionTypes.get(definitionName);
	}

	public boolean isDefinition(final String definitionName) {
		return definitions.containsKey(definitionName);
	}

}
