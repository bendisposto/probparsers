package de.prob.parser.antlr;


import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.be4.classicalb.core.parser.node.*;

import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class StaticSableCCAstBuilder {

	public static Node createExpression_keyword(String type, ParserRuleContext ctx) {
		PExpression node;
		switch (type) {
		case "NATURAL":
			node = new ANaturalSetExpression();
			break;
		case "NATURAL1":
			node = new ANatural1SetExpression();
			break;
		case "NAT":
			node = new ANatSetExpression();
			break;
		case "NAT1":
			node = new ANat1SetExpression();
			break;
		case "INTEGER":
			node = new AIntegerSetExpression();
			break;
		case "INT":
			node = new AIntSetExpression();
			break;
		case "BOOL":
			node = new ABoolSetExpression();
			break;
		case "PRED":
			node = new APredecessorExpression();
			break;
		case "SUCC":
			node = new ASuccessorExpression();
			break;
		case "MAXINT":
			node = new AMaxIntExpression();
			break;
		case "MININT":
			node = new AMinExpression();
			break;
		default:
			throw new RuntimeException("unexpected");
		}
		return createPositionedNode(node, ctx);
	}

	public static Node createExpressionPrefixOperator2Args(String type, PExpression left, PExpression right,
			ParserRuleContext ctx) {
		PExpression node;
		switch (type) {
		case "FATHER":
			node = new AFatherExpression(left, right);
			break;
		case "CONST":
			node = new AConcatExpression(left, right);
		case "PRJ1":
			node = new AFirstProjectionExpression(left, right);
			break;
		case "PRJ2":
			node = new ASecondProjectionExpression(left, right);
			break;
		case "RANK":
			node = new ARankExpression(left, right);
			break;
		case "SUBTREE":
			node = new ASubtreeExpression(left, right);
			break;
		case "ARITY":
			node = new AArityExpression(left, right);
			break;
		case "ITERATE":
			node = new AIterationExpression(left, right);
			break;
		default:
			throw new RuntimeException("unexpected: " + ctx.toString());
		}
		return createPositionedNode(node, ctx);
	}

	public static Node createExpressionPrefixOperator(String type, PExpression expression, ParserRuleContext ctx) {
		PExpression node;
		switch (type) {
		case "BTREE":
			node = new ABtreeExpression(expression);
			break;
		case "CARD":
			node = new ACardExpression(expression);
			break;
		case "CLOSURE":
			node = new AClosureExpression(expression);
			break;
		case "CLOSURE1":
			node = new AReflexiveClosureExpression(expression);
			break;
		case "CONC":
			node = new AGeneralConcatExpression(expression);
			break;
		case "DOM":
			node = new ADomainExpression(expression);
			break;
		case "FIRST":
			node = new AFirstExpression(expression);
			break;
		case "FNC":
			node = new ATransFunctionExpression(expression);
			break;
		case "FRONT":
			node = new AFrontExpression(expression);
			break;
		case "ID":
			node = new AIdentityExpression(expression);
			break;
		case "INFIX":
			node = new AInfixExpression(expression);
			break;
		case "ISEQ":
			node = new AIseqExpression(expression);
			break;
		case "ISEQ1":
			node = new AIseq1Expression(expression);
			break;
		case "LAST":
			node = new ALastExpression(expression);
			break;
		case "LEFT":
			node = new ALeftExpression(expression);
			break;
		case "MAX":
			node = new AMaxExpression(expression);
			break;
		case "MIN":
			node = new AMinExpression(expression);
			break;
		case "MIRROR":
			node = new AMirrorExpression(expression);
			break;
		case "PERM":
			node = new APermExpression(expression);
			break;
		case "POSTFIX":
			node = new APostfixExpression(expression);
			break;
		case "POW":
			node = new APowSubsetExpression(expression);
			break;
		case "PREFIX":
			node = new APrefixExpression(expression);
			break;
		case "RAN":
			node = new ARangeExpression(expression);
			break;
		case "REL":
			node = new ATransRelationExpression(expression);
			break;
		case "REV":
			node = new AReverseExpression(expression);
			break;
		case "RIGHT":
			node = new ARightExpression(expression);
			break;
		case "SEQ":
			node = new ASeqExpression(expression);
			break;
		case "SEQ1":
			node = new ASeq1Expression(expression);
			break;
		case "SIZE":
			node = new ASizeExpression(expression);
			break;
		case "SIZET":
			node = new ASizetExpression(expression);
			break;
		case "SONS":
			node = new ASonsExpression(expression);
			break;
		case "TAIL":
			node = new ATailExpression(expression);
			break;
		case "TOP":
			node = new ATopExpression(expression);
			break;
		case "TREE":
			node = new ATreeExpression(expression);
			break;
		case "GENERALIZED_UNION":
			node = new AGeneralUnionExpression(expression);
			break;
		case "GENERALIZED_INTER":
			node = new AGeneralIntersectionExpression(expression);
			break;
		default:
			throw new RuntimeException("unexpected: " + ctx.toString());
		}
		return createPositionedNode(node, ctx);
	}

	public static Node createExpressionOperator2Args(String type, PExpression left, PExpression right,
			ParserRuleContext ctx) {
		PExpression node;
		switch (type) {
		case "SET_RELATION":
			node = new ARelationsExpression(left, right);
			break;
		case "PARTIAL_FUNCTION":
			node = new APartialFunctionExpression(left, right);
			break;
		case "TOTAL_FUNCTION":
			node = new ATotalFunctionExpression(left, right);
			break;
		case "TOTAL_INJECTION":
			node = new ATotalInjectionExpression(left, right);
			break;
		case "PARTIAL_INJECTION":
			node = new APartialInjectionExpression(left, right);
			break;
		case "TOTAL_SURJECTION":
			node = new ATotalSurjectionExpression(left, right);
			break;
		case "PARTIAL_SURJECTION":
			node = new APartialSurjectionExpression(left, right);
			break;
		case "TOTAL_BIJECTION":
			node = new ATotalBijectionExpression(left, right);
			break;
		case "PARTIAL_BIJECTION":
			node = new APartialBijectionExpression(left, right);
			break;
		case "TOTAL_RELATION":
			node = new ATotalRelationExpression(left, right);
			break;
		case "SURJECTION_RELATION":
			node = new ASurjectionRelationExpression(left, right);
			break;
		case "TOTAL_SURJECTION_RELATION":
			node = new ATotalSurjectionRelationExpression(left, right);
			break;
		case "MULT":
			node = new AMultOrCartExpression(left, right);
			break;
		case "DIVIDE":
			node = new ADivExpression(left, right);
			break;
		case "PLUS":
			node = new AAddExpression(left, right);
			break;
		case "MINUS":
			node = new AMinusOrSetSubtractExpression(left, right);
			break;
		case "INTERVAL":
			node = new AIntervalExpression(left, right);
			break;
		case "POWER_OF":
			node = new APowerOfExpression(left, right);
			break;
		case "MOD":
			node = new AModuloExpression(left, right);
			break;
		case "SET_SUBTRACTION":
			node = new ASetSubtractionExpression(left, right);
			break;
		case "OVERWRITE_RELATION":
			node = new AOverwriteExpression(left, right);
			break;
		case "DIRECT_PRODUCT":
			node = new ADirectProductExpression(left, right);
			break;
		case "CONCAT":
			node = new AConcatExpression(left, right);
			break;
		case "DOMAIN_RESTRICTION":
			node = new ADomainRestrictionExpression(left, right);
			break;
		case "DOMAIN_SUBSTRACTION":
			node = new ADomainSubtractionExpression(left, right);
			break;
		case "RANGE_RESTRICTION":
			node = new ARangeRestrictionExpression(left, right);
			break;
		case "RANGE_SUBSTRATION":
			node = new ARangeSubtractionExpression(left, right);
			break;
		case "INSERT_FRONT":
			node = new AInsertFrontExpression(left, right);
			break;
		case "INSERT_TAIL":
			node = new AInsertTailExpression(left, right);
			break;
		case "UNION":
			node = new AUnionExpression(left, right);
			break;
		case "INTERSECTION":
			node = new AIntersectionExpression(left, right);
			break;
		case "RESTRICT_FRONT":
			node = new ARestrictFrontExpression(left, right);
			break;
		case "RESTRICT_TAIL":
			node = new ARestrictTailExpression(left, right);
			break;
		case "MAPLET":
			node = new ACoupleExpression(createPExpressionList(left, right));
			break;
		default:
			throw new RuntimeException("unexpected");
		}
		return createPositionedNode(node, ctx);
	}

	public static <T extends PositionedNode> T createPositionedNode(T node, TerminalNode terminalNode) {
		return createPositionedNode(node, terminalNode.getSymbol());
	}

	public static <T extends PositionedNode> T createPositionedNode(T node, ParserRuleContext ctx) {
		return createPositionedNode(node, ctx.start, ctx.stop);
	}

	public static <T extends PositionedNode> T createPositionedNode(T node, Token start, Token stop) {
		node.setStartPos(new SourcePosition(start.getLine(), start.getCharPositionInLine() + 1));
		node.setEndPos(new SourcePosition(stop.getLine(), stop.getCharPositionInLine() + stop.getText().length() + 1));
		return node;
	}

	public static List<PExpression> createPExpressionList(PExpression... pExpressions) {
		final List<PExpression> list = new ArrayList<>();
		for (PExpression pExpression : pExpressions) {
			list.add(pExpression);
		}
		return list;
	}

	public static <T extends PositionedNode> T createPositionedNode(T node, Token token) {
		final int tokenSize = token.getText().length();
		node.setStartPos(new SourcePosition(token.getLine(), token.getCharPositionInLine() + 1));
		node.setEndPos(new SourcePosition(token.getLine(), token.getCharPositionInLine() + 1 + tokenSize));
		return node;
	}

	public static List<PExpression> createAIdentifierExpressionList(List<Token> tokens) {
		List<PExpression> result = new ArrayList<>();
		for (Token token : tokens) {
			AIdentifierExpression aIdent = createAIdentifierExpression(token);
			result.add(aIdent);
		}
		return result;
	}

	public static AIdentifierExpression createAIdentifierExpression(Token token) {
		TIdentifierLiteral tIdentifierLiteral = createTIdentifierLiteral(token);
		List<TIdentifierLiteral> list = new ArrayList<>();
		list.add(tIdentifierLiteral);
		AIdentifierExpression aIdent = new AIdentifierExpression(list);
		final int tokenSize = token.getText().length();
		aIdent.setStartPos(new SourcePosition(token.getLine(), token.getCharPositionInLine() + 1));
		aIdent.setEndPos(new SourcePosition(token.getLine(), token.getCharPositionInLine() + 1 + tokenSize));
		return aIdent;
	}

	public static TIntegerLiteral createTIntegerLiteral(TerminalNode tnode) {
		TIntegerLiteral tIntLiteral = new TIntegerLiteral(tnode.getText(), tnode.getSymbol().getLine(),
				tnode.getSymbol().getCharPositionInLine() + 1);
		return tIntLiteral;
	}

	public static AIdentifierExpression createAIdentifierExpression(TerminalNode tNode) {
		TIdentifierLiteral tIdentifierLiteral = createTIdentifierLiteral(tNode);
		List<TIdentifierLiteral> list = new ArrayList<>();
		list.add(tIdentifierLiteral);
		return createPositionedNode(new AIdentifierExpression(list), tNode);
	}

	public static TIdentifierLiteral createTIdentifierLiteral(TerminalNode tNode) {
		return new TIdentifierLiteral(tNode.getText(), tNode.getSymbol().getLine(),
				tNode.getSymbol().getCharPositionInLine() + 1);
	}

	public static TIdentifierLiteral createTIdentifierLiteral(Token token) {
		return new TIdentifierLiteral(token.getText(), token.getLine(), token.getCharPositionInLine() + 1);
	}

	public static <T extends de.be4.classicalb.core.parser.node.Token> T createPositionedToken(T newToken, Token oldToken) {
		newToken.setLine(oldToken.getLine());
		newToken.setPos(oldToken.getCharPositionInLine() + 1);
		return newToken;
	}
}
