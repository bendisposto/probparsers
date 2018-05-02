package de.prob.parser.antlr;


import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.Node;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public  class ScopeChecker {
    final LinkedList<LinkedHashMap<String, DeclarationNode>> scopeTable = new LinkedList<>();
    final LinkedHashMap<Node, DeclarationNode> declarationReferences = new LinkedHashMap<>();

    public class ScopeCheckerVisitorException extends RuntimeException {
        private static final long serialVersionUID = 5003348008806300117L;
        final ScopeException scopeException;

        public ScopeCheckerVisitorException(ScopeException e) {
            this.scopeException = e;
        }

        public ScopeException getScopeException() {
            return scopeException;
        }
    }

    
    
//    @Override
//	public Void visitIdentifierExpression( IdentifierExpressionContext ctx) {
//        lookUpTerminalNode(ctx.IDENTIFIER());
//        return null;
//    }
//
//    @Override
//    public Void visitPredicateIdentifier( PredicateIdentifierContext ctx) {
//        lookUpTerminalNode(ctx.IDENTIFIER());
//        return null;
//    }
//
//    @Override
//    public Void visitSetComprehensionExpression( SetComprehensionExpressionContext ctx) {
//        visitQuantifiedFormula(ctx.identifier_list().IDENTIFIER(), ctx.predicate());
//        return null;
//    }
//
//    @Override
//    public Void visitQuantifiedExpression( QuantifiedExpressionContext ctx) {
//        visitQuantifiedFormula(ctx.quantified_variables_list().identifier_list().IDENTIFIER(), ctx.predicate(),
//                ctx.expression());
//        return null;
//    }
//
//    @Override
//    public Void visitQuantifiedPredicate( QuantifiedPredicateContext ctx) {
//        visitQuantifiedFormula(ctx.quantified_variables_list().identifier_list().IDENTIFIER(), ctx.predicate());
//        return null;
//    }
//
//    private void visitQuantifiedFormula(List<TerminalNode> identifiers, ParserRuleContext... contexts) {
//        LinkedHashMap<String, TerminalNode> localIdentifiers = new LinkedHashMap<>();
//        for (TerminalNode terminalNode : identifiers) {
//            localIdentifiers.put(terminalNode.getSymbol().getText(), terminalNode);
//        }
//        scopeTable.add(localIdentifiers);
//        for (ParserRuleContext node : contexts) {
//            node.accept(this);
//        }
//        scopeTable.removeLast();
//    }
//
//    @Override
//    public Void visitAssignSubstitution( AssignSubstitutionContext ctx) {
//        ctx.identifier_list().IDENTIFIER().stream().forEach(this::lookUpTerminalNode);
//        ctx.expression_list().accept(this);
//        return null;
//    }
//
//    @Override
//    public Void visitBecomesElementOfSubstitution( BecomesElementOfSubstitutionContext ctx) {
//        ctx.identifier_list().IDENTIFIER().stream().forEach(this::lookUpTerminalNode);
//        ctx.expression().accept(this);
//        return null;
//    }
//
//    @Override
//    public Void visitBecomesSuchThatSubstitution( BecomesSuchThatSubstitutionContext ctx) {
//        ctx.identifier_list().IDENTIFIER().stream().forEach(this::lookUpTerminalNode);
//        ctx.predicate().accept(this);
//        return null;
//    }
//
    public void lookUpTerminalNode(String name, Node terminalNode) {
        for (int i = scopeTable.size() - 1; i >= 0; i--) {
            LinkedHashMap<String, DeclarationNode> map = scopeTable.get(i);
            if (map.containsKey(name)) {
            	DeclarationNode declarationToken = map.get(name);
                addDeclarationReference(terminalNode, declarationToken);
                return;
            }
        }
        identifierNodeNotFound(terminalNode);
    }
//
//    @Override
//    public Void visitAnySubstitution( AnySubstitutionContext ctx) {
//        LinkedHashMap<String, TerminalNode> localIdentifiers = new LinkedHashMap<>();
//        for (TerminalNode terminalNode : ctx.identifier_list().IDENTIFIER()) {
//            localIdentifiers.put(terminalNode.getSymbol().getText(), terminalNode);
//        }
//        scopeTable.add(localIdentifiers);
//        ctx.predicate().accept(this);
//        ctx.substitution().accept(this);
//        scopeTable.removeLast();
//        return null;
//    }
//
    public void addDeclarationReference(Node identifierToken, DeclarationNode declarationToken) {
        this.declarationReferences.put(identifierToken, declarationToken);
    }

    public void identifierNodeNotFound(Node node) {
        throw new ScopeCheckerVisitorException(
                new ScopeException("Unknown identifier: " + node.getSourceCodePosition().getText()));
    }

}
