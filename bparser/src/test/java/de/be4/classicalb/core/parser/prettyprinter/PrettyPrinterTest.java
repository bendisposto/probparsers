package de.be4.classicalb.core.parser.prettyprinter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import util.Ast2String;
import util.PolySuite;
import util.PolySuite.Config;
import util.PolySuite.Configuration;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.util.PrettyPrinter;

@RunWith(PolySuite.class)
public class PrettyPrinterTest {

	static final String[] tests = {
			"5+6",
			"5",
			"5-6",
			"4+5+6",
			"4+5-6",
			"4+5*6",
			"4*5+6*7",
			"A\\B",
			"5*6",
			"5/6",
			"6 mod 5",
			"6**5",
			"A<->B",
			"A+->B",
			"A-->B",
			"A>+>B",
			"A>->B",
			"A+->>B",
			"A-->>B",
			"A>+>>B",
			"A>->>B",
			"A<<->B",
			"A<->>B",
			"A<<->>B",
			"A<+B",
			"A><B",
			"A^B",
			"A<|B",
			"A<<|B",
			"A|>B",
			"A|>>B",
			"A->B",
			"A<-B",
			"A\\/B",
			"A/\\B",
			"A/|\\B",
			"A\\|/B",
			"(2,3)",
			"5..20",
			"-x",
			"A~",
			"A[B]",
			"(A||B)",
			"(f;g)",
			"bool(x<0)",
			"max({1,2,3})",
			"min({1,2,3})",
			"card({1,2,3})",
			"SIGMA(y,z).(z<5|z*z)",
			"PI(a,b,c).(a<0&x<5|x*a*b*c)",
			"POW(A<->B)",
			"POW1(A<->B)",
			"FIN(A\\/B)",
			"FIN1(B/\\A)",
			"union(z)",
			"inter(A)",
			"id(A)",
			"closure(A)",
			"dom((1,2))",
			"ran((1,2))",
			"%x,y.(x<y|E)",
			"fnc(foo)",
			"rel(A<->POW(B))",
			"seq(S)",
			"seq1(S)",
			"iseq(S)",
			"iseq1(S)",
			"perm(S)",
			"perm([])",
			"size(S)",
			"first(S)",
			"last(S)",
			"front(S)",
			"tail(S)",
			"rev(S)",
			"prj1(S,T)",
			"prj2(S,T)",
			"iterate(A<->B,5)",
			"{x,y|x<y&x<5}",
			"UNION(z).(x<y|E)",
			"INTER(z).(x<y|E)",
			"perm([a,b,c])",
			"conc(S)",
			"TRUE",
			"MAXINT",
			"MININT",
			"{}",
			"INTEGER",
			"NATURAL",
			"NATURAL1",
			"NAT",
			"NAT1",
			"INT",
			"BOOL",
			"STRING",
			"\"a String\"",
			"foo(bar,3)",
			"succ <+ {1|->1, 3|->3}(3)",
			"ll(dd'right_sect)",
			"x",
			"rec(left_sect:River1,right_sect:Lock1)",
			"struct(left_sect:River1,right_sect:Lock1)",
			"a-b-c",
			"a+b+c",
			"(a**b)**c",
			"a+(b+c)",
			"A\\(B\\C)",
			"(A**B)**C",
			"a-(b-c)",
			"a+(b*c)",
			"dom({((1,2),3), ((2,4),5), ((23,45),23)})",
			"{6} <<| {6|->3, 9|->2}",
			"{1, 2} <| {1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9}",
			"{1, 2} <<| {1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9}",
			"{1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9} |> {4, 7, 9}",
			"{1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9} |>> {4, 7, 9}",
			"{1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9}[{1, 2}]",
			"{1 |-> 2, 2 |-> 3, 1 |-> 4, 3 |-> 7, 5 |-> 9}~",
			"{3 |-> 5, 3 |-> 9, 6 |-> 3, 9 |-> 2} <+ {2 |-> 7, 3 |-> 4, 5 |-> 1, 9 |-> 5}",
			"{8|->10, 7|->11, 2|->11, 6|->12}><{1|->20, 7|->20, 2|->21, 1|->22}",
			"(4+5)*6",
			"4*(5+6)",
			"(4+5)*(6+7)",
			"{1}*({2}*{3})",
			"({1}*{2})*{3}",
			"2**(3+4)",
			"2**3**4**5",
			"bool(B=TRUE => (3>2 & 4+4=8))",
			"bool((B=TRUE => 3>2) & 4+4=8)",
			"bool((C=TRUE => 3>2) & (C=FALSE => 2>3))",
			"bool(A=TRUE or B=TRUE & C=TRUE => D=TRUE <=> E=TRUE)",
			"bool((A=TRUE or B=TRUE) & (C=TRUE => (D=TRUE <=> E=TRUE)))"
			};

	private static final String PREFIX = "#EXPRESSION ";

	String theString;

	public PrettyPrinterTest(String theString) {
		this.theString = theString;
	}

	@Test
	public void testExpression() throws Exception {
		Start parse = BParser.parse(PREFIX + theString);
		PrettyPrinter prettyprinter = new PrettyPrinter();

		parse.apply(prettyprinter);
		String prettyPrint = prettyprinter.getPrettyPrint();
		System.out.println(prettyPrint);
		Start parse2 = BParser.parse(PREFIX + prettyPrint);
		PrettyPrinter prettyprinter2 = new PrettyPrinter();

		parse2.apply(prettyprinter2);
		System.out.println( prettyprinter2.getPrettyPrint());
		assertEquals(Ast2String.getTreeAsString(parse),
				Ast2String.getTreeAsString(parse2));
		assertEquals(prettyPrint, prettyprinter2.getPrettyPrint());
	}

	/*
	 * @Test public void testCoupleSecondRepresentation() throws Exception {
	 * String toParse = "#EXPRESSION 1|->2"; Start parse =
	 * BParser.parse(toParse); PrettyPrinter prettyprinter = new
	 * PrettyPrinter(); parse.apply(prettyprinter); assertEquals(toParse,
	 * prettyprinter.getPrettyPrint()); }
	 */

	@Config
	public static Configuration getConfig() {

		return new Configuration() {

			@Override
			public int size() {
				return tests.length;
			}

			@Override
			public String getTestValue(int index) {
				return tests[index];
			}

			@Override
			public String getTestName(int index) {
				return tests[index];
			}
		};
	}

}
