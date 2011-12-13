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
public class PriorityTests {
	final BinaryOperator lower, higher;

	public PriorityTests(Tuple t) {
		lower = t.getOp1();
		higher = t.getOp2();
	}

	// @Override
	// public String getName() {
	// return "Testing priority between " + lower.getPriority() + " and "
	// + higher.getPriority() + ": " + lower.getSymbol() + " vs "
	// + higher.getSymbol();
	// }

	@Config
	public static Configuration getConfig() {
		List<BinaryOperator> binOps = BinaryOperator.OPS;
		final List<Tuple> ops = new ArrayList<Tuple>(binOps.size()
				* binOps.size());

		for (BinaryOperator op1 : binOps) {
			for (BinaryOperator op2 : binOps) {
				if (op1.getPriority() < op2.getPriority())
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
				return "Priority for " + left.getName() + " / "
						+ right.getName() + "(" + left.getSymbol() + " "
						+ right.getSymbol() + ")";
			}
		};
	}

	@Test
	public void testPriority() throws BException {
		final String symL = lower.getSymbol();
		final String symH = higher.getSymbol();
		final String exprA = createTripleExpr(symH, symL);
		final String exprB = createTripleExpr(symL, symH);
		final String expectedA = createTripleExprLeft(symH, symL);
		final String expectedB = createTripleExprRight(symL, symH);

		final String pExprA = parseExpr(exprA);
		final String pExprB = parseExpr(exprB);
		final String pExpectedA = parseExpr(expectedA);
		final String pExpectedB = parseExpr(expectedB);

		assertEquals(pExpectedA, pExprA);
		assertEquals(pExpectedB, pExprB);
	}

}
