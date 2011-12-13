package de.be4.classicalb.core.parser.prios;

import static de.be4.classicalb.core.parser.analysis.ParseTestUtil.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import test.PolySuite;
import test.PolySuite.Config;
import test.PolySuite.Configuration;
import de.be4.classicalb.core.parser.exceptions.BException;

@RunWith(PolySuite.class)
public class OneBinopAssoziativityTests {
	private final String op1;
	private final String op2;
	private final EAssoc associativity;

	public OneBinopAssoziativityTests(final BinaryOperator op) {
		this(op.getSymbol(), op.getSymbol(), op.getAssociativity());
	}

	private OneBinopAssoziativityTests(final String op1, final String op2,
			final EAssoc associativity) {
		this.op1 = op1;
		this.op2 = op2;
		this.associativity = associativity;
	}

	@Config
	public static Configuration getConfig() {
		final List<BinaryOperator> binOps = BinaryOperator.OPS;
		return new Configuration() {
			public int size() {
				return binOps.size();
			}

			public BinaryOperator getTestValue(int index) {
				BinaryOperator operator = binOps.get(index);
				return operator;
			}

			public String getTestName(int index) {
				BinaryOperator operator = binOps.get(index);
				return "Assoziativity for " + operator.getName() + "("
						+ operator.getSymbol() + ")";
			}
		};
	}

	@Test
	public void testAssociativity() throws BException {
		final String expr = createTripleExpr(op1, op2);
		final String left = createTripleExprLeft(op1, op2);
		final String right = createTripleExprRight(op1, op2);

		final String pExpr = parseExpr(expr);
		final String pLeft = parseExpr(left);
		final String pRight = parseExpr(right);

		final boolean isLeft = pExpr.equals(pLeft);
		final boolean isRight = pExpr.equals(pRight);
		assertTrue(" must be either left and right assoziative",
				isLeft != isRight);
		switch (associativity) {
		case LEFT:
			assertTrue("expected to be left assoziative", isLeft);
			break;
		case RIGHT:
			assertTrue("expected to be right assoziative", isRight);
			break;
		}
	}
}
