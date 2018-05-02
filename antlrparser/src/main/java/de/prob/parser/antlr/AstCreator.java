package de.prob.parser.antlr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.PredicateNode;
import de.prob.parser.ast.nodes.SubstitutionNode;
import files.BParser;
import files.BParserBaseVisitor;
import files.BParser.DeclarationClauseContext;
import files.BParser.StartContext;

public class AstCreator {
	final StartContext startContext;
	private final MachineNode machineNode;

	public AstCreator(StartContext startContext) {
		this.startContext = startContext;
		this.machineNode = new MachineNode(createSourceCodePosition(startContext));

		MachineConstructor machineConstructor = new MachineConstructor(startContext);
	}

	public SourceCodePosition createSourceCodePosition(ParserRuleContext ctx) {
		return new SourceCodePosition();
	}

	class MachineConstructor extends BParserBaseVisitor<Void> {
		ASTFormulaCreator formulaVisitor = new ASTFormulaCreator();

		MachineConstructor(StartContext start) {
			start.accept(this);
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
		public Void visitPredicateClause(BParser.PredicateClauseContext ctx) {
			PredicateNode pred = (PredicateNode) ctx.pred.accept(formulaVisitor);
			switch (ctx.name.getText()) {
			case "INVARIANT":
				if (machineNode.getInvariant() == null) {
					machineNode.setInvariant(pred);
				} else {
					throw new VisitorException(new ScopeException("Duplicate INVARIANT clause."));
				}
				break;
			case "PROPERTIES":
				if (machineNode.getProperties() == null) {
					machineNode.setProperties(pred);
				} else {
					throw new VisitorException(new ScopeException("Duplicate PROPERTIES clause."));
				}
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
			SubstitutionNode sub = (SubstitutionNode) ctx.substitution().accept(formulaVisitor);

			OperationNode operationNode = new OperationNode(createSourceCodePosition(ctx), ctx.IDENTIFIER().getText(),
					outputParamNodes, sub, paramNodes);

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