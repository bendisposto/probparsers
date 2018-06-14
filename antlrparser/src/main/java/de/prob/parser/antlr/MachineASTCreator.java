package de.prob.parser.antlr;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetDeclarationNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import files.BParser;
import files.BParser.DeclarationClauseContext;
import files.BParser.Machine_instantiationContext;
import files.BParser.StartContext;
import files.BParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MachineASTCreator {
	private final MachineNode machineNode;

	public static MachineNode createMachineAST(StartContext startContext) {
		MachineASTCreator machineASTCreator = new MachineASTCreator(startContext);
		return machineASTCreator.getMachineNode();
	}

	private MachineNode getMachineNode() {
		return this.machineNode;
	}

	private MachineASTCreator(StartContext startContext) {
		this.machineNode = new MachineNode(Util.createSourceCodePosition(startContext));
		new MachineConstructor(startContext);
	}

	class MachineConstructor extends BParserBaseVisitor<Void> {
		FormulaASTCreator formulaAstCreator = new FormulaASTCreator();

		MachineConstructor(StartContext start) {
			start.accept(this);
		}

		@Override
		public Void visitMachine_header(BParser.Machine_headerContext ctx) {
			machineNode.setName(ctx.IDENTIFIER().getText());
			return null;
		}

		@Override
		public Void visitInstanceClause(BParser.InstanceClauseContext ctx) {
			MachineReferenceNode.Kind kind = null;
			switch (ctx.name.getType()) {
			case BParser.INCLUDES:
				kind = MachineReferenceNode.Kind.INCLUDED;
				break;
			case BParser.EXTENDS:
				kind = MachineReferenceNode.Kind.EXTENDED;
				break;
			default:
				throw new RuntimeException("Unknown instance type: " + ctx.name.getText());
			}
			for (Machine_instantiationContext instance : ctx.machine_instantiation()) {
				String prefix = instance.prefix == null ? null : instance.prefix.getText();
				String machineName = instance.name.getText();
				machineNode.addMachineReferenceNode(
						new MachineReferenceNode(Util.createSourceCodePosition(ctx), machineName, kind, prefix, true));
			}
			return null;
		}

		@Override
		public Void visitEnumeratedSet(BParser.EnumeratedSetContext ctx) {
			SourceCodePosition position = getSourcePositionFromTerminalNode(ctx.IDENTIFIER());
			DeclarationNode declarationNode = new DeclarationNode(position, ctx.IDENTIFIER().getSymbol().getText(),
					DeclarationNode.Kind.ENUMERATED_SET, machineNode);
			machineNode.addSetEnumeration(
					new EnumeratedSetDeclarationNode(position, declarationNode, createDeclarationList(
							ctx.identifier_list().IDENTIFIER(), DeclarationNode.Kind.ENUMERATED_SET_ELEMENT)));
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
			case "ABSTRACT_CONSTANTS":
			case "CONCRETE_CONSTANTS":
				machineNode.setConstants(
						createDeclarationList(ctx.identifier_list().IDENTIFIER(), DeclarationNode.Kind.CONSTANT));
				break;
			case "VARIABLES":
			case "ABSTRACT_VARIABLES":
			case "CONCRETE_VARIABLES":
				machineNode.setVariables(
						createDeclarationList(ctx.identifier_list().IDENTIFIER(), DeclarationNode.Kind.VARIABLE));
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
				outputParamNodes = createDeclarationList(ctx.output.IDENTIFIER(),
						DeclarationNode.Kind.OP_OUTPUT_PARAMETER);
			}
			List<DeclarationNode> paramNodes = new ArrayList<>();
			if (ctx.parameters != null) {
				paramNodes = createDeclarationList(ctx.parameters.IDENTIFIER(),
						DeclarationNode.Kind.OP_INPUT_PARAMETER);
			}
			SubstitutionNode sub = (SubstitutionNode) ctx.substitution().accept(formulaAstCreator);

			OperationNode operationNode = new OperationNode(Util.createSourceCodePosition(ctx),
					ctx.IDENTIFIER().getText(), outputParamNodes, sub, paramNodes);
			machineNode.addOperation(operationNode);
			return null;
		}

		private void unreachable() {
			throw new RuntimeException();
		}

		private List<DeclarationNode> createDeclarationList(List<TerminalNode> list, DeclarationNode.Kind kind) {
			List<DeclarationNode> declarationList = new ArrayList<>();
			for (TerminalNode terminalNode : list) {
				DeclarationNode declNode = new DeclarationNode(getSourcePositionFromTerminalNode(terminalNode),
						terminalNode.getSymbol().getText(), kind, machineNode);
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