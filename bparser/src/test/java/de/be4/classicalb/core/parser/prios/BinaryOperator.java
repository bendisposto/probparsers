package de.be4.classicalb.core.parser.prios;

import static de.be4.classicalb.core.parser.prios.EAssoc.LEFT;
import static de.be4.classicalb.core.parser.prios.EAssoc.RIGHT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class BinaryOperator {
	protected static final List<BinaryOperator> OPS = createBinaryOperators();

	private static void defineBinaryOperators(final Collection<BinaryOperator> ops) {
		addOp(ops, "*", 190, LEFT, "multiplication or Cartesian product");
		addOp(ops, "**", 200, RIGHT, "power");
		addOp(ops, "+", 180, LEFT, "addition");
		addOp(ops, "+->", 125, LEFT, "partial function");
		addOp(ops, "+->>", 125, LEFT, "partial surjection");
		addOp(ops, "-", 180, LEFT, "subtraction");
		addOp(ops, "-->", 125, LEFT, "total function");
		addOp(ops, "-->>", 125, LEFT, "total surjection");
		addOp(ops, "->", 160, LEFT, "insertion at front");
		addOp(ops, "..", 170, LEFT, "interval");
		addOp(ops, "/", 190, LEFT, "division");
		addOp(ops, "/\\", 160, LEFT, "intersection");
		addOp(ops, "/|\\", 160, LEFT, "restriction at front");
		addOp(ops, ";", 20, LEFT, "composition");
		addOp(ops, "<+", 160, LEFT, "function override");
		addOp(ops, "<->", 125, LEFT, "relations");
		addOp(ops, "<-", 160, LEFT, "insertion at end");
		addOp(ops, "<<|", 160, LEFT, "domain subtraction");
		addOp(ops, "<|", 160, LEFT, "domain restriction");
		addOp(ops, ">+>", 125, LEFT, "partial injection");
		addOp(ops, ">->", 125, LEFT, "total injection");
		addOp(ops, ">->>", 125, LEFT, "total bijection");
		addOp(ops, "><", 160, LEFT, "direct relational product");
		addOp(ops, "\\/", 160, LEFT, "union");
		addOp(ops, "\\|/", 160, LEFT, "restriction of sequence");
		addOp(ops, "^", 160, LEFT, "concatenation");
		addOp(ops, "mod", 190, LEFT, "modulo");
		addOp(ops, "|->", 160, LEFT, "maplet");
		addOp(ops, "|>", 160, LEFT, "range restriction");
		addOp(ops, "|>>", 160, LEFT, "range subtraction");
		addOp(ops, "||", 20, LEFT, "relational parallel product");
	}

	private static void addOp(final Collection<BinaryOperator> ops, final String symbol, final int priority,
			final EAssoc assoc, final String name) {
		final BinaryOperator op = new BinaryOperator(symbol, priority, assoc, name);
		ops.add(op);
	}

	private static List<BinaryOperator> createBinaryOperators() {
		List<BinaryOperator> binOps = new ArrayList<BinaryOperator>();
		defineBinaryOperators(binOps);
		return Collections.unmodifiableList(binOps);
	}

	private final String symbol;
	private final int priority;
	private final EAssoc associatifity;
	private final String name;

	public BinaryOperator(final String symbol, final int priority, final EAssoc associatifity, final String name) {
		this.symbol = symbol;
		this.priority = priority;
		this.associatifity = associatifity;
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getPriority() {
		return priority;
	}

	public EAssoc getAssociativity() {
		return associatifity;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return symbol + "(" + priority + ", " + associatifity + ", \"" + name + "\")";
	}

}