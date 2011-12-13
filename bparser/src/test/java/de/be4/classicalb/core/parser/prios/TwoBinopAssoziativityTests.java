package de.be4.classicalb.core.parser.prios;

import static de.be4.classicalb.core.parser.analysis.ParseTestUtil.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import test.PolySuite;
import test.PolySuite.Config;
import test.PolySuite.Configuration;
import de.be4.classicalb.core.parser.exceptions.BException;

@RunWith(PolySuite.class)
public class TwoBinopAssoziativityTests {
	private final BinaryOperator op1;
	private final BinaryOperator op2;
	private EAssoc assoc;

	public TwoBinopAssoziativityTests(Tuple t) {
		this.op1 = t.getOp1();
		this.op2 = t.getOp2();
		this.assoc = t.getAssoc();
	}

	@Config
	public static Configuration getConfig() {
		List<BinaryOperator> binOps = BinaryOperator.OPS;
		final List<Tuple> ops = new ArrayList<Tuple>(binOps.size()
				* binOps.size());

		for (BinaryOperator op1 : binOps) {
			for (BinaryOperator op2 : binOps) {
				if (op1.getPriority() == op2.getPriority()
						&& op1.getAssociativity() == op2.getAssociativity())
					ops.add(new Tuple(op1, op2, op1.getAssociativity()));
			}
		}

		return new Configuration() {
			public int size() {
				return ops.size();
			}

			public Tuple getTestValue(int index) {
				Tuple operators = ops.get(index);
				return operators;
			}

			public String getTestName(int index) {
				Tuple t = ops.get(index);
				BinaryOperator left = t.getOp1();
				BinaryOperator right = t.getOp2();
				return "Assoziativity for " + left.getName() + " / "
						+ right.getName() + "(" + left.getSymbol() + " "
						+ right.getSymbol() + ")";
			}
		};
	}

	@Test
	public void testAssociativity() throws BException {
		String s1 = op1.getSymbol();
		String s2 = op2.getSymbol();
		final String expr = createTripleExpr(s1, s2);
		final String left = createTripleExprLeft(s1, s2);
		final String right = createTripleExprRight(s1, s2);

		final String pExpr = parseExpr(expr);
		final String pLeft = parseExpr(left);
		final String pRight = parseExpr(right);

		final boolean isLeft = pExpr.equals(pLeft);
		final boolean isRight = pExpr.equals(pRight);
		assertTrue(" must be either left and right assoziative",
				isLeft != isRight);
		switch (assoc) {
		case LEFT:
			assertTrue("expected to be left assoziative", isLeft);
			break;
		case RIGHT:
			assertTrue("expected to be right assoziative", isRight);
			break;
		}
	}
}
