package de.prob.typechecker;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.ltl.core.parser.analysis.DepthFirstAdapter;
import de.be4.ltl.core.parser.internal.LtlLexer;
import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.lexer.LexerException;
import de.be4.ltl.core.parser.node.AActionLtl;
import de.be4.ltl.core.parser.node.AAndLtl;
import de.be4.ltl.core.parser.node.AEnabledLtl;
import de.be4.ltl.core.parser.node.AExistsLtl;
import de.be4.ltl.core.parser.node.AForallLtl;
import de.be4.ltl.core.parser.node.AHistoricallyLtl;
import de.be4.ltl.core.parser.node.AOnceLtl;
import de.be4.ltl.core.parser.node.AReleaseLtl;
import de.be4.ltl.core.parser.node.ASinceLtl;
import de.be4.ltl.core.parser.node.AStrongFairLtl;
import de.be4.ltl.core.parser.node.ATriggerLtl;
import de.be4.ltl.core.parser.node.AUnparsedLtl;
import de.be4.ltl.core.parser.node.AUntilLtl;
import de.be4.ltl.core.parser.node.AWeakFairLtl;
import de.be4.ltl.core.parser.node.AWeakuntilLtl;
import de.be4.ltl.core.parser.node.AYesterdayLtl;
import de.be4.ltl.core.parser.node.PLtl;
import de.be4.ltl.core.parser.node.Start;
import de.be4.ltl.core.parser.parser.Parser;
import de.be4.ltl.core.parser.parser.ParserException;
import de.prob.typechecker.exceptions.LTLParseException;
import de.prob.typechecker.exceptions.ScopeException;

public class LTLFormulaVisitor extends DepthFirstAdapter {

	private final String name;
	private final MachineContext machineContext;
	private String ltlFormula;
	private Start ltlFormulaStart;

	private final LinkedHashMap<de.be4.ltl.core.parser.node.Node, de.be4.classicalb.core.parser.node.Node> ltlNodeToBNodeTable;
	private final ArrayList<LTLBPredicate> bPredicates;
	private final Hashtable<String, AIdentifierExpression> ltlIdentifierTable;

	private ArrayList<Hashtable<String, AIdentifierExpression>> contextTable;

	public LTLFormulaVisitor(String name, MachineContext machineContext) {
		this.name = name;
		this.machineContext = machineContext;
		this.bPredicates = new ArrayList<LTLBPredicate>();
		this.ltlNodeToBNodeTable = new LinkedHashMap<de.be4.ltl.core.parser.node.Node, de.be4.classicalb.core.parser.node.Node>();
		this.ltlIdentifierTable = new Hashtable<String, AIdentifierExpression>();
		this.contextTable = new ArrayList<Hashtable<String, AIdentifierExpression>>();
	}

	public void parseDefinition(AExpressionDefinitionDefinition def) {
		if (!(def.getRhs() instanceof AStringExpression)) {
			throw new LTLParseException("Error: LTL formula is not in a string representation.");
		}
		AStringExpression stringNode = (AStringExpression) def.getRhs();
		this.ltlFormula = stringNode.getContent().getText();
		try {
			this.ltlFormulaStart = parseLTLFormula(ltlFormula);
		} catch (Exception e) {
			String message = "Parsing definition " + name + " (line " + def.getStartPos().getLine() + "):\n";
			throw new LTLParseException(message + e.getMessage());
		}
	}

	public void parseLTLString(final String ltlString) {
		try {
			this.ltlFormulaStart = parseLTLFormula(ltlString);
		} catch (Exception e) {
			throw new LTLParseException(e.getMessage());
		}
	}

	public ArrayList<LTLBPredicate> getBPredicates() {
		return bPredicates;
	}

	public Collection<AIdentifierExpression> getParameter() {
		return ltlIdentifierTable.values();
	}

	public AIdentifierExpression getLTLIdentifier(String identifier) {
		return ltlIdentifierTable.get(identifier);
	}

	public LinkedHashMap<de.be4.ltl.core.parser.node.Node, de.be4.classicalb.core.parser.node.Node> getUnparsedHashTable() {
		return ltlNodeToBNodeTable;
	}

	public de.be4.classicalb.core.parser.node.Node getBAst(de.be4.ltl.core.parser.node.Node unparsedLtl) {
		return ltlNodeToBNodeTable.get(unparsedLtl);
	}

	public String getName() {
		return this.name;
	}

	public void start() {
		ltlFormulaStart.apply(this);
	}

	public Start getLTLFormulaStart() {
		return ltlFormulaStart;
	}

	public static Start parseLTLFormula(String ltlFormula) throws ParserException, LexerException, IOException {
		StringReader reader = new StringReader(ltlFormula);
		PushbackReader r = new PushbackReader(reader);
		Lexer l = new LtlLexer(r);
		Parser p = new Parser(l);
		Start ast = null;
		ast = p.parse();
		return ast;
	}

	@Override
	public void caseAUnparsedLtl(AUnparsedLtl node) {
		de.be4.classicalb.core.parser.node.Start start = parseBPredicate(node.getPredicate().getText());

		ltlNodeToBNodeTable.put(node, start);

		LTLBPredicate ltlBPredicate = new LTLBPredicate(getUnifiedContext(), start);
		this.bPredicates.add(ltlBPredicate);

		machineContext.checkLTLBPredicate(ltlBPredicate);
	}

	private de.be4.classicalb.core.parser.node.Start parseBPredicate(String text) {
		String bPredicate = "#PREDICATE " + text;
		BParser parser = new BParser("Testing");
		de.be4.classicalb.core.parser.node.Start start = null;
		try {
			start = parser.parse(bPredicate, false);
		} catch (BCompoundException e) {
			throw new LTLParseException(e.getMessage());
		}

		return start;
	}

	@Override
	public void caseAExistsLtl(AExistsLtl node) {
		handleQuantification(node, node.getExistsIdentifier().getText(), node.getPredicate().getText(), node.getLtl());

	}

	@Override
	public void caseAForallLtl(AForallLtl node) {
		handleQuantification(node, node.getForallIdentifier().getText(), node.getPredicate().getText(), node.getLtl());
	}

	private void handleQuantification(de.be4.ltl.core.parser.node.Node node, String parameterName,
			String bPredicateString, PLtl ltl) {
		// create an identifier (b ast node) for the parameter of the
		// quantification
		List<TIdentifierLiteral> list = new ArrayList<TIdentifierLiteral>();
		list.add(new TIdentifierLiteral(parameterName));
		AIdentifierExpression parameterNode = new AIdentifierExpression(list);

		// add the created identifier to the current context
		Hashtable<String, AIdentifierExpression> currentContext = new Hashtable<String, AIdentifierExpression>();
		currentContext.put(parameterName, parameterNode);
		this.contextTable.add(currentContext);

		// collection the all parameters in
		ltlIdentifierTable.put(parameterName, parameterNode);

		// parse the b predicate and create a reference to the b ast node
		de.be4.classicalb.core.parser.node.Start start = parseBPredicate(bPredicateString);
		ltlNodeToBNodeTable.put(node, start);

		// collect all identifiers which can be used in the bPredicate and
		// verify the bPredicate
		LTLBPredicate ltlBPredicate = new LTLBPredicate(getUnifiedContext(), start);
		this.bPredicates.add(ltlBPredicate);
		machineContext.checkLTLBPredicate(ltlBPredicate);

		// remaining LTL formula
		ltl.apply(this);

		// remove currentContext from contextTable
		contextTable.remove(contextTable.size() - 1);

	}

	private LinkedHashMap<String, Node> getUnifiedContext() {
		LinkedHashMap<String, Node> context = new LinkedHashMap<String, Node>();
		for (int i = 0; i < contextTable.size(); i++) {
			context.putAll(contextTable.get(i));
		}
		return context;
	}

	@Override
	public void caseAEnabledLtl(AEnabledLtl node) {
		String operationName = node.getOperation().getText();
		if (!machineContext.getOperations().containsKey(operationName)) {
			throw new ScopeException("Unkown operation " + operationName + ".");
		}
	}

	@Override
	public void caseAWeakFairLtl(AWeakFairLtl node) {
		String operationName = node.getOperation().getText().trim();
		if (!machineContext.getOperations().containsKey(operationName)) {
			throw new ScopeException("Unkown operation " + operationName + ".");
		}
	}

	@Override
	public void caseAStrongFairLtl(AStrongFairLtl node) {
		String operationName = node.getOperation().getText().trim();
		if (!machineContext.getOperations().containsKey(operationName)) {
			throw new ScopeException("Unkown operation " + operationName + ".");
		}
	}

	public void inAUntilLtl(AUntilLtl node) {
		throw new ScopeException("The 'until' operator is not supported.");
	}

	public void inAWeakuntilLtl(AWeakuntilLtl node) {
		throw new ScopeException("The 'weak until' operator is not supported.");
	}

	public void inAReleaseLtl(AReleaseLtl node) {
		throw new ScopeException("The 'release' operator is not supported.");
	}

	public void inASinceLtl(ASinceLtl node) {
		throw new ScopeException("The 'since' operator is not supported.");
	}

	public void inATriggerLtl(ATriggerLtl node) {
		throw new ScopeException("The 'trigger' operator is not supported.");
	}

	public void inAHistoricallyLtl(AHistoricallyLtl node) {
		throw new ScopeException("The 'history' operator is not supported.");
	}

	public void inAOnceLtl(AOnceLtl node) {
		throw new ScopeException("The 'once' operator is not supported.");
	}

	public void inAYesterdayLtl(AYesterdayLtl node) {
		throw new ScopeException("The 'yesterday' operator is not supported.");
	}

	@Override
	public void caseAActionLtl(AActionLtl node) {
		throw new ScopeException("The '[...]' operator is not supported.");
	}

	@Override
	public void caseAAndLtl(AAndLtl node) {
		inAAndLtl(node);
		if (node.getLeft() != null) {
			node.getLeft().apply(this);
		}
		if (node.getRight() != null) {
			node.getRight().apply(this);
		}
		outAAndLtl(node);
	}

	public MachineContext getMachineContext() {
		return this.machineContext;
	}

}
