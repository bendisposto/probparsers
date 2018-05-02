package de.prob.parser.antlr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.InstanceNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import files.BParser;
import files.BParserBaseVisitor;
import files.BParser.DeclarationClauseContext;
import files.BParser.Machine_instantiationContext;
import files.BParser.StartContext;

public class AstCreator {
	private final MachineNode machineNode;

	public MachineNode getMachineNode() {
		return this.machineNode;
	}

	public AstCreator(StartContext startContext) {
		this.machineNode = new MachineNode(Util.createSourceCodePosition(startContext));

		MachineConstructor machineConstructor = new MachineConstructor(startContext);

	}

	class MachineConstructor extends BParserBaseVisitor<Void> {
		ASTFormulaCreator formulaAstCreator = new ASTFormulaCreator();

		MachineConstructor(StartContext start) {
			start.accept(this);
		}

		@Override
		public Void visitInstanceClause(BParser.InstanceClauseContext ctx) {
			for (Machine_instantiationContext instance : ctx.machine_instantiation()) {
				String prefix = instance.prefix == null ? null : instance.prefix.getText();
				String name = ctx.name.getText();
				machineNode.addInstance(new InstanceNode(Util.createSourceCodePosition(ctx), name, prefix));
			}
			return null;
		}

		@Override
		public Void visitDeclarationClause(DeclarationClauseContext ctx) {
			LinkedHashMap<String, TerminalNode> declarations = new LinkedHashMap<>();
			for (TerminalNode terminalNode : ctx.identifier_list().IDENTIFIER()) {
				declarations.put(terminalNode.getSymbol().getText(), terminalNode);
			}
			switch (ctx.name.getText()) {
			case "CONSTANTS":
				machineNode.setConstants(createDeclarationList(ctx.identifier_list().IDENTIFIER()));
				break;
			case "VARIABLES":
				machineNode.setVariables(createDeclarationList(ctx.identifier_list().IDENTIFIER()));
				break;
			default:
				unreachable();
			}
			return null;
		}

		@Override
		public Void visitInitialisationClause(BParser.InitialisationClauseContext ctx) {
			SubstitutionNode subNode = (SubstitutionNode) ctx.substitution().accept(formulaAstCreator);
			machineNode.setInitialisation(subNode);
			return null;
		}

		@Override
		public Void visitPredicateClause(BParser.PredicateClauseContext ctx) {
			PredicateNode pred = (PredicateNode) ctx.pred.accept(formulaAstCreator);
			switch (ctx.name.getText()) {
			case "INVARIANT":
				machineNode.setInvariant(pred);
				break;
			case "PROPERTIES":
				machineNode.setProperties(pred);
				break;
			default:
				unreachable();
			}
			return null;
		}

		@Override
		public Void visitBOperation(BParser.BOperationContext ctx) {
			List<DeclarationNode> outputParamNodes = new ArrayList<>();
			if (ctx.output != null) {
				outputParamNodes = createDeclarationList(ctx.output.IDENTIFIER());
			}
			List<DeclarationNode> paramNodes = new ArrayList<>();
			if (ctx.parameters != null) {
				paramNodes = createDeclarationList(ctx.parameters.IDENTIFIER());
			}
			SubstitutionNode sub = (SubstitutionNode) ctx.substitution().accept(formulaAstCreator);

			OperationNode operationNode = new OperationNode(Util.createSourceCodePosition(ctx),
					ctx.IDENTIFIER().getText(), outputParamNodes, sub, paramNodes);
			machineNode.addOperation(operationNode);
			return null;
		}

		private void unreachable() {
			// TODO Auto-generated method stub

		}

		private List<DeclarationNode> createDeclarationList(List<TerminalNode> list) {
			List<DeclarationNode> declarationList = new ArrayList<>();
			for (TerminalNode terminalNode : list) {
				DeclarationNode declNode = new DeclarationNode(getSourcePositionFromTerminalNode(terminalNode),
						terminalNode.getSymbol().getText());
				declarationList.add(declNode);
			}
			return declarationList;
		}

		private SourceCodePosition getSourcePositionFromTerminalNode(TerminalNode terminalNode) {
			SourceCodePosition sourceCodePosition = new SourceCodePosition();
			sourceCodePosition.setStartLine(terminalNode.getSymbol().getLine());
			sourceCodePosition.setStartColumn(terminalNode.getSymbol().getCharPositionInLine());
			sourceCodePosition.setText(terminalNode.getSymbol().getText());
			return sourceCodePosition;
		}

	}
}