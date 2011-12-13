import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import de.prob.unicode.UnicodeTranslator;

public class ToUnicodeTest {

	@Test
	public void TIn() {
		assertTrue(UnicodeTranslator.toUnicode(":").equals("\u2208"));
	}

	@Test
	public void TNotsubseteq() {
		assertTrue(UnicodeTranslator.toUnicode("/<:").equals("\u2288"));
	}

	@Test
	public void TNotsubset() {
		assertTrue(UnicodeTranslator.toUnicode("/<<:").equals("\u2284"));
	}

	@Test
	public void TSubseteq() {
		assertTrue(UnicodeTranslator.toUnicode("<:").equals("\u2286"));
	}

	@Test
	public void TSetminus() {
		assertTrue(UnicodeTranslator.toUnicode("\\").equals("\u2216"));
	}

	@Test
	public void TDotdot() {
		assertTrue(UnicodeTranslator.toUnicode("..").equals("\u2025"));
	}

	@Test
	public void TNat() {
		assertTrue(UnicodeTranslator.toUnicode("NAT").equals("\u2115"));
	}

	@Test
	public void TEmptyset() {
		assertTrue(UnicodeTranslator.toUnicode("{}").equals("\u2205"));
	}

	@Test
	public void TBcmsuch() {
		assertTrue(UnicodeTranslator.toUnicode(":|").equals(":\u2223"));
	}

	@Test
	public void TBfalse() {
		assertTrue(UnicodeTranslator.toUnicode("false").equals("\u22a5"));
	}

	@Test
	public void TForall() {
		assertTrue(UnicodeTranslator.toUnicode("!").equals("\u2200"));
	}

	@Test
	public void TExists() {
		assertTrue(UnicodeTranslator.toUnicode("#").equals("\u2203"));
	}

	@Test
	public void TMapsto() {
		assertTrue(UnicodeTranslator.toUnicode("|->").equals("\u21a6"));
	}

	@Test
	public void TBtrue() {
		assertTrue(UnicodeTranslator.toUnicode("true").equals("\u22a4"));
	}

	@Test
	public void TSubset() {
		assertTrue(UnicodeTranslator.toUnicode("<<:").equals("\u2282"));
	}

	@Test
	public void TBunion() {
		assertTrue(UnicodeTranslator.toUnicode("\\/").equals("\u222a"));
	}

	@Test
	public void TBinter() {
		assertTrue(UnicodeTranslator.toUnicode("/\\").equals("\u2229"));
	}

	@Test
	public void TDomres() {
		assertTrue(UnicodeTranslator.toUnicode("<|").equals("\u25c1"));
	}

	@Test
	public void TRanres() {
		assertTrue(UnicodeTranslator.toUnicode("|>").equals("\u25b7"));
	}

	@Test
	public void TDomsub() {
		assertTrue(UnicodeTranslator.toUnicode("<<|").equals("\u2a64"));
	}

	@Test
	public void TRansub() {
		assertTrue(UnicodeTranslator.toUnicode("|>>").equals("\u2a65"));
	}

	@Test
	public void TLambda() {
		assertTrue(UnicodeTranslator.toUnicode("%").equals("\u03bb"));
	}

	@Test
	public void TOftype() {
		assertTrue(UnicodeTranslator.toUnicode("oftype").equals("\u2982"));
	}

	@Test
	public void TNotin() {
		assertTrue(UnicodeTranslator.toUnicode("/:").equals("\u2209"));
	}

	@Test
	public void TCprod() {
		assertTrue(UnicodeTranslator.toUnicode("**").equals("\u00d7"));
	}

	@Test
	public void TUnion() {
		assertTrue(UnicodeTranslator.toUnicode("UNION").equals("\u22c3"));
	}

	@Test
	public void TInter() {
		assertTrue(UnicodeTranslator.toUnicode("INTER").equals("\u22c2"));
	}

	/*
	 * FIXME java.lang.AssertionError - why? \u002c = , \u003b = ; see
	 * ToAsciiTest.ForwardComposition(), ToAsciiTest.SequentialSubstitution()
	 */
	@Test
	public void TFcomp() {
		String actual = UnicodeTranslator.toUnicode(";");
		String expected = "\u003b";
		assertEquals(expected, actual);
	}

	@Test
	public void TBcomp() {
		String actual = UnicodeTranslator.toUnicode("circ");;
		String expected = "\u2218";
		assertEquals(expected, actual);
	}

	@Test
	public void TStrel() {
		assertTrue(UnicodeTranslator.toUnicode("<<->>").equals("\ue102"));
	}

	@Test
	public void TDprod() {
		assertTrue(UnicodeTranslator.toUnicode("><").equals("\u2297"));
	}

	@Test
	public void TPprod() {
		assertTrue(UnicodeTranslator.toUnicode("||").equals("\u2225"));
	}

	@Test
	public void TBcmeq() {
		assertTrue(UnicodeTranslator.toUnicode(":=").equals("\u2254"));
	}

	@Test
	public void TBcmin() {
		assertTrue(UnicodeTranslator.toUnicode("::").equals(":\u2208"));
	}

	@Test
	public void TIntg() {
		assertTrue(UnicodeTranslator.toUnicode("INT").equals("\u2124"));
	}

	@Test
	public void TLand() {
		assertTrue(UnicodeTranslator.toUnicode("&").equals("\u2227"));
	}

	@Test
	public void TLimp() {
		assertTrue(UnicodeTranslator.toUnicode("=>").equals("\u21d2"));
	}

	@Test
	public void TLeqv() {
		assertTrue(UnicodeTranslator.toUnicode("<=>").equals("\u21d4"));
	}

	@Test
	public void TLnot() {
		assertTrue(UnicodeTranslator.toUnicode("not").equals("\u00ac"));
	}

	@Test
	public void TQdot() {
		assertTrue(UnicodeTranslator.toUnicode(".").equals("\u00b7"));
	}

	@Test
	public void TConv() {
		assertTrue(UnicodeTranslator.toUnicode("~").equals("\u223c"));
	}

	@Test
	public void TTrel() {
		assertTrue(UnicodeTranslator.toUnicode("<<->").equals("\ue100"));
	}

	@Test
	public void TSurjectiveRel() {
		assertTrue(UnicodeTranslator.toUnicode("<->>").equals("\ue101"));
	}

	@Test
	public void TPfun() {
		assertTrue(UnicodeTranslator.toUnicode("+->").equals("\u21f8"));
	}

	@Test
	public void TTfun() {
		assertTrue(UnicodeTranslator.toUnicode("-->").equals("\u2192"));
	}

	@Test
	public void TPinj() {
		assertTrue(UnicodeTranslator.toUnicode(">+>").equals("\u2914"));
	}

	@Test
	public void TTinj() {
		assertTrue(UnicodeTranslator.toUnicode(">->").equals("\u21a3"));
	}

	@Test
	public void TPsur() {
		assertTrue(UnicodeTranslator.toUnicode("+>>").equals("\u2900"));
	}

	@Test
	public void TTsur() {
		assertTrue(UnicodeTranslator.toUnicode("->>").equals("\u21a0"));
	}

	@Test
	public void TTbij() {
		assertTrue(UnicodeTranslator.toUnicode(">->>").equals("\u2916"));
	}

	@Test
	public void TExpn() {
		// XXX Test doesn't make sense, works in both directions!
		assertTrue(UnicodeTranslator.toUnicode("\u005e").equals("^"));
		assertTrue(UnicodeTranslator.toUnicode("^").equals("\u005e"));
	}

	@Test
	public void TLor() {
		assertTrue(UnicodeTranslator.toUnicode("or").equals("\u2228"));
	}

	@Test
	public void TPow() {
		assertTrue(UnicodeTranslator.toUnicode("POW").equals("\u2119"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,1] Unknown token: | Escape
	 * sequence needed?
	 */
	@Test
	public void TMid() {
		assertTrue(UnicodeTranslator.toUnicode("|").equals("\u2223"));
	}

	@Test
	public void TNeq() {
		assertTrue(UnicodeTranslator.toUnicode("/=").equals("\u2260"));
	}

	@Test
	public void TRel() {
		assertTrue(UnicodeTranslator.toUnicode("<->").equals("\u2194"));
	}

	@Test
	public void TOvl() {
		assertTrue(UnicodeTranslator.toUnicode("<+").equals("\ue103"));
	}

	@Test
	public void TLeq() {
		assertTrue(UnicodeTranslator.toUnicode("<=").equals("\u2264"));
	}

	@Test
	public void TGeq() {
		assertTrue(UnicodeTranslator.toUnicode(">=").equals("\u2265"));
	}

	@Test
	public void TDiv() {
		assertTrue(UnicodeTranslator.toUnicode("/").equals("\u00f7"));
	}

	@Test
	public void TMult() {
		assertTrue(UnicodeTranslator.toUnicode("*").equals("\u2217"));
	}

	@Test
	public void TMinus() {
		assertTrue(UnicodeTranslator.toUnicode("-").equals("\u2212"));
	}

	@Test
	public void TComma() {
		assertTrue(UnicodeTranslator.toUnicode(",").equals(","));
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Conjunction() {
		assertTrue(UnicodeTranslator.toUnicode("P & Q").equals("P \u2227 Q"));
	}

	@Test
	public void Disjunction() {
		assertTrue(UnicodeTranslator.toUnicode("P or Q").equals("P \u2228 Q"));
	}

	@Test
	public void Implication() {
		assertTrue(UnicodeTranslator.toUnicode("P => Q").equals("P \u21d2 Q"));
	}

	@Test
	public void Equivalence() {
		assertTrue(UnicodeTranslator.toUnicode("P <=> Q").equals("P \u21d4 Q"));
	}

	@Test
	public void Negation() {
		assertTrue(UnicodeTranslator.toUnicode("not P").equals("\u00ac P"));
	}

	@Test
	public void UniversalQuantification() {
		assertTrue(UnicodeTranslator.toUnicode("!(z).(P => Q)").equals(
				"\u2200(z)\u00b7(P \u21d2 Q)"));
	}

	@Test
	public void UniversalQuantification2() {
		assertTrue(UnicodeTranslator.toUnicode("(!z.P => Q)").equals(
				"(\u2200z\u00b7P \u21d2 Q)"));
	}

	@Test
	public void ExistentialQuantification() {
		assertTrue(UnicodeTranslator.toUnicode("#(z).(P & Q)").equals(
				"\u2203(z)\u00b7(P \u2227 Q)"));
	}

	@Test
	public void ExistentialQuantification2() {
		assertTrue(UnicodeTranslator.toUnicode("(#z.P & Q)").equals(
				"(\u2203z\u00b7P \u2227 Q)"));
	}

	/*
	 * de.prob.unicode.lexer.LexerException: [1,1] Unknown token: [ '[' not
	 * permitted
	 */
	@Ignore
	public void Substitution() {
		assertTrue(UnicodeTranslator.toUnicode("[G] P").equals("[G] P"));
	}

	@Test
	public void Equality() {
		assertTrue(UnicodeTranslator.toUnicode("E = F").equals("E = F"));
	}

	@Test
	public void Inequality() {
		assertTrue(UnicodeTranslator.toUnicode("E /= F").equals("E \u2260 F"));
	}

	@Test
	public void SingletonSet() {
		assertTrue(UnicodeTranslator.toUnicode("{E}").equals("{E}"));
	}

	@Test
	public void SetEnumeration() {
		assertTrue(UnicodeTranslator.toUnicode("{E, F}").equals("{E, F}"));
	}

	@Test
	public void EmptySet() {
		assertTrue(UnicodeTranslator.toUnicode("{}").equals("\u2205"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,4] Unknown token: |
	 */
	@Test
	public void SetComprehension() {
		assertTrue(UnicodeTranslator.toUnicode("{z | P}")
				.equals("{z \u2223 P}"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,8] Unknown token: |
	 */
	@Test
	public void SetComprehension2() {
		assertTrue(UnicodeTranslator.toUnicode("{z . P | F}").equals(
				"{z \u00b7 P \u2223 F}"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,4] Unknown token: |
	 */
	@Test
	public void SetComprehension3() {
		assertTrue(UnicodeTranslator.toUnicode("{F | P}")
				.equals("{F \u2223 P}"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,4] Unknown token: |
	 */
	@Test
	public void SetComprehension4() {
		assertTrue(UnicodeTranslator.toUnicode("{x | P}")
				.equals("{x \u2223 P}"));
	}

	@Test
	public void Union() {
		assertTrue(UnicodeTranslator.toUnicode("S \\/ T").equals("S \u222a T"));
	}

	@Test
	public void Intersection() {
		assertTrue(UnicodeTranslator.toUnicode("S /\\ T").equals("S \u2229 T"));
	}

	@Test
	public void Difference() {
		assertTrue(UnicodeTranslator.toUnicode("S-T").equals("S\u2212T"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,14] Unknown token: |
	 */
	@Test
	public void Difference2() {
		String expected = "S \u2216 T";
		String actual = UnicodeTranslator.toUnicode("S \\ T");
		assertEquals(expected, actual);
	}

	@Test
	public void OrderedPair() {
		assertTrue(UnicodeTranslator.toUnicode("E |-> F").equals("E \u21a6 F"));
	}

	@Test
	public void CartesianProduct() {
		// XXX really \u2217 '*' and not \u00d7 'x'?
		assertTrue(UnicodeTranslator.toUnicode("S * T").equals("S \u2217 T"));
	}

	@Test
	public void CartesianProduct2() {
		assertTrue(UnicodeTranslator.toUnicode("S ** T").equals("S \u00d7 T"));
	}

	@Test
	public void Powerset() {
		assertTrue(UnicodeTranslator.toUnicode("POW(S)").equals("\u2119(S)"));
	}

	// XXX NonEmptySubsets not provided? What's the unicode character? \u2119
	// and \u2081
	@Test
	public void NonEmptySubsets() {
		assertTrue(UnicodeTranslator.toUnicode("POW1(S)").equals("POW1(S)"));
	}

	// XXX FiniteSets not provided? What's the unicode character?
	@Test
	public void FiniteSets() {
		assertTrue(UnicodeTranslator.toUnicode("finite S").equals("finite S"));
	}

	// XXX FiniteSubsets not provided? What's the unicode character? \u1D53D ?
	@Test
	public void FiniteSubsets() {
		assertTrue(UnicodeTranslator.toUnicode("FIN(S)").equals("FIN(S)"));
	}

	// XXX FiniteNonEmptySubsets not provided? What's the unicode character?
	// \u1D53D and \u2081 ?
	@Test
	public void FiniteNonEmptySubsets() {
		assertTrue(UnicodeTranslator.toUnicode("FIN1(S)").equals("FIN1(S)"));
	}

	@Test
	public void Cardinality() {
		assertTrue(UnicodeTranslator.toUnicode("card(S)").equals("card(S)"));
	}

	@Test
	public void Partition() {
		assertTrue(UnicodeTranslator.toUnicode("partition(S,x,y)").equals(
				"partition(S,x,y)"));
	}

	@Test
	public void GeneralizedUnion() {
		assertTrue(UnicodeTranslator.toUnicode("UNION(U)").equals("\u22c3(U)"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,14] Unknown token: |
	 */
	@Test
	public void GeneralizedUnion2() {
		assertTrue(UnicodeTranslator.toUnicode("UNION (z).(P | E)").equals(
				"\u22c3 (z)\u00b7(P \u2223 E)"));
	}

	@Test
	public void GeneralizedUnion3() {
		assertTrue(UnicodeTranslator.toUnicode("union(U)").equals("union(U)"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,11] Unknown token: |
	 */
	@Test
	public void QuantifiedUnion() {
		assertTrue(UnicodeTranslator.toUnicode("UNION z.P | S").equals(
				"\u22c3 z\u00b7P \u2223 S"));
	}

	@Test
	public void GeneralizedIntersection() {
		assertTrue(UnicodeTranslator.toUnicode("INTER(U)").equals("\u22c2(U)"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,14] Unknown token: |
	 */
	@Test
	public void GeneralizedIntersection2() {
		assertTrue(UnicodeTranslator.toUnicode("INTER (z).(P | E)").equals(
				"\u22c2 (z)\u00b7(P \u2223 E)"));
	}

	@Test
	public void SetMembership() {
		assertTrue(UnicodeTranslator.toUnicode("E : S").equals("E \u2208 S"));
	}

	@Test
	public void SetNonMembership() {
		assertTrue(UnicodeTranslator.toUnicode("E /: S").equals("E \u2209 S"));
	}

	@Test
	public void Subset() {
		assertTrue(UnicodeTranslator.toUnicode("S <: T").equals("S \u2286 T"));
	}

	@Test
	public void NotASubset() {
		assertTrue(UnicodeTranslator.toUnicode("S /<: T").equals("S \u2288 T"));
	}

	@Test
	public void ProperSubset() {
		assertTrue(UnicodeTranslator.toUnicode("S <<: T").equals("S \u2282 T"));
	}

	@Test
	public void NotAProperSubset() {
		assertTrue(UnicodeTranslator.toUnicode("S /<<: T").equals("S \u2284 T"));
	}

	@Test
	public void NaturalNumbers() {
		assertTrue(UnicodeTranslator.toUnicode("NAT").equals("\u2115"));
	}

	// XXX PositiveNaturalNumbers not provided? What's the unicode character?
	// \u2115 and \u2081
	@Test
	public void PositiveNaturalNumbers() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1").equals("NAT1"));
	}

	@Test
	public void Minimum() {
		assertTrue(UnicodeTranslator.toUnicode("min(S)").equals("min(S)"));
	}

	@Test
	public void Maximum() {
		assertTrue(UnicodeTranslator.toUnicode("max(S)").equals("max(S)"));
	}

	@Test
	public void Sum() {
		assertTrue(UnicodeTranslator.toUnicode("m + n").equals("m + n"));
	}

	@Test
	public void DifferenceAlt() {
		assertTrue(UnicodeTranslator.toUnicode("m - n").equals("m \u2212 n"));
	}

	@Test
	public void Product() {
		// XXX why \u2217 '*' and not \u00d7 'x'?
		assertTrue(UnicodeTranslator.toUnicode("m * n").equals("m \u2217 n"));
	}

	@Test
	public void Quotient() {
		assertTrue(UnicodeTranslator.toUnicode("m / n").equals("m \u00f7 n"));
	}

	@Test
	public void Remainder() {
		assertTrue(UnicodeTranslator.toUnicode("m mod n").equals("m mod n"));
	}

	@Test
	public void Interval() {
		assertTrue(UnicodeTranslator.toUnicode("m .. n").equals("m \u2025 n"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,13] Unknown token: |
	 */
	@Test
	public void SetSummation() {
		// SIGMA not provided (\u2211)
		assertTrue(UnicodeTranslator.toUnicode("SIGMA(z).(P | E)").equals(
				"SIGMA(z)\u00b7(P \u2223 E)"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,10] Unknown token: |
	 */
	@Test
	public void SetProduct() {
		// PI not provided (\u220F)
		assertTrue(UnicodeTranslator.toUnicode("PI(z).(P | E)").equals(
				"PI(z)\u00b7(P \u2223 E)"));
	}

	@Test
	public void Greater() {
		assertTrue(UnicodeTranslator.toUnicode("m > n").equals("m > n"));
	}

	@Test
	public void Less() {
		assertTrue(UnicodeTranslator.toUnicode("m < n").equals("m < n"));
	}

	@Test
	public void GreaterOrEqual() {
		assertTrue(UnicodeTranslator.toUnicode("m >= n").equals("m \u2265 n"));
	}

	@Test
	public void LessOrEqual() {
		assertTrue(UnicodeTranslator.toUnicode("m <= n").equals("m \u2264 n"));
	}

	@Test
	public void Relations() {
		assertTrue(UnicodeTranslator.toUnicode("S <-> T").equals("S \u2194 T"));
	}

	@Test
	public void Domain() {
		assertTrue(UnicodeTranslator.toUnicode("dom(r)").equals("dom(r)"));
	}

	@Test
	public void Range() {
		assertTrue(UnicodeTranslator.toUnicode("ran(r)").equals("ran(r)"));
	}

	@Test
	public void ForwardComposition() {
		assertTrue(UnicodeTranslator.toUnicode("p ; q").equals("p ; q"));
	}

	@Test
	public void BackwardComposition() {
		assertTrue(UnicodeTranslator.toUnicode("p circ q").equals("p \u2218 q"));
	}

	@Test
	public void Identity() {
		assertTrue(UnicodeTranslator.toUnicode("id(S)").equals("id(S)"));
	}

	@Test
	public void DomainRestriction() {
		assertTrue(UnicodeTranslator.toUnicode("S <| r").equals("S \u25c1 r"));
	}

	@Test
	public void DomainSubtraction() {
		assertTrue(UnicodeTranslator.toUnicode("S <<| r").equals("S \u2a64 r"));
	}

	@Test
	public void RangeRestriction() {
		assertTrue(UnicodeTranslator.toUnicode("r |> T").equals("r \u25b7 T"));
	}

	@Test
	public void RangeSubtraction() {
		assertTrue(UnicodeTranslator.toUnicode("r |>> T").equals("r \u2a65 T"));
	}

	@Test
	public void Inverse() {
		assertTrue(UnicodeTranslator.toUnicode("r~").equals("r\u223c"));
	}

	/*
	 * de.prob.unicode.lexer.LexerException: [1,2] Unknown token: [ '[' not
	 * permitted
	 */
	@Ignore
	public void relationalImage() {
		assertTrue(UnicodeTranslator.toUnicode("r[S]").equals("r[S]"));
	}

	@Test
	public void RightOverriding() {
		assertTrue(UnicodeTranslator.toUnicode("r1 <+ r2").equals(
				"r1 \ue103 r2"));
	}

	@Test
	public void DirectProduct() {
		assertTrue(UnicodeTranslator.toUnicode("p >< q").equals("p \u2297 q"));
	}

	@Test
	public void ParallelProduct() {
		assertTrue(UnicodeTranslator.toUnicode("p || q").equals("p \u2225 q"));
	}

	// XXX Iteration not provided? something like r^n
	@Test
	public void Iteration() {
		assertTrue(UnicodeTranslator.toUnicode("iterate(r,n)").equals(
				"iterate(r,n)"));
	}

	@Test
	public void Closure() {
		assertTrue(UnicodeTranslator.toUnicode("closure(r)").equals(
				"closure(r)"));
	}

	// XXX reflexibleClosure not provided? something like r^*
	@Test
	public void rClosure() {
		assertTrue(UnicodeTranslator.toUnicode("rclosure(r)").equals(
				"rclosure(r)"));
	}

	// irreflexible Closure not provided? something like r^+
	@Test
	public void iClosure() {
		assertTrue(UnicodeTranslator.toUnicode("iclosure(r)").equals(
				"iclosure(r)"));
	}

	@Test
	public void Projection1() {
		assertTrue(UnicodeTranslator.toUnicode("prj1(S,T)").equals("prj1(S,T)"));
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection1_1() {
		assertTrue(UnicodeTranslator.toUnicode("prj1").equals("prj1"));
	}

	@Test
	public void Projection2() {
		assertTrue(UnicodeTranslator.toUnicode("prj2(S,T)").equals("prj2(S,T)"));
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection2_1() {
		assertTrue(UnicodeTranslator.toUnicode("prj2").equals("prj2"));
	}

	@Test
	public void PartialFunctions() {
		assertTrue(UnicodeTranslator.toUnicode("S +-> T").equals("S \u21f8 T"));
	}

	@Test
	public void TotalFunctions() {
		assertTrue(UnicodeTranslator.toUnicode("S --> T").equals("S \u2192 T"));
	}

	@Test
	public void PartialInjections() {
		assertTrue(UnicodeTranslator.toUnicode("S >+> T").equals("S \u2914 T"));
	}

	@Test
	public void TotalInjections() {
		assertTrue(UnicodeTranslator.toUnicode("S >-> T").equals("S \u21a3 T"));
	}

	/*
	 * XXX PartialSurjections not provided? why does this test fail?
	 */
	@Test
	public void PartialSurjections() {
		assertTrue(UnicodeTranslator.toUnicode("S +>> T").equals("S \u2900 T"));
	}

	/*
	 * XXX TotalSurjections not provided? why does this test fail?
	 */
	@Test
	public void TotalSurjections() {
		assertTrue(UnicodeTranslator.toUnicode("S ->> T").equals("S \u21a0 T"));
	}

	@Test
	public void Bijections() {
		assertTrue(UnicodeTranslator.toUnicode("S >->> T").equals("S \u2916 T"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,6] Unknown token: |E
	 */
	@Test
	public void LambdaAbstraction() {
		assertTrue(UnicodeTranslator.toUnicode("%z.(P|E)").equals(
				"\u03bbz\u00b7(P\u2223E)"));
	}

	@Test
	public void FunctionApplication() {
		assertTrue(UnicodeTranslator.toUnicode("f(E)").equals("f(E)"));
	}

	@Test
	public void FunctionApplication2() {
		assertTrue(UnicodeTranslator.toUnicode("f(E |-> F)").equals(
				"f(E \u21a6 F)"));
	}

	@Test
	public void FiniteSequences() {
		assertTrue(UnicodeTranslator.toUnicode("seq S").equals("seq S"));
	}

	// XXX FiniteNonEmptySequences not provided? What's the unicode character?
	// unknown and \u2081
	@Test
	public void FiniteNonEmptySequences() {
		assertTrue(UnicodeTranslator.toUnicode("seq1(S)").equals("seq1(S)"));
	}

	@Test
	public void InjectiveSequences() {
		assertTrue(UnicodeTranslator.toUnicode("iseq(S)").equals("iseq(S)"));
	}

	@Test
	public void Permutations() {
		assertTrue(UnicodeTranslator.toUnicode("perm(S)").equals("perm(S)"));
	}

	@Test
	public void SequenceConcatenations() {
		// XXX really meant \u005e for sequence concatenation? not \u0311 ?
		assertTrue(UnicodeTranslator.toUnicode("s^t").equals("s\u005et"));
	}


	@Test
	public void Size() {
		assertTrue(UnicodeTranslator.toUnicode("size(s)").equals("size(s)"));
	}

	@Test
	public void Reverse() {
		assertTrue(UnicodeTranslator.toUnicode("rev(s)").equals("rev(s)"));
	}

	/*
	 * de.prob.unicode.lexer.LexerException: [1,4] Unknown token: |\ Take not
	 * provided.
	 */
	@Ignore
	public void Take() {
		assertTrue(UnicodeTranslator.toUnicode("s /|\\ n").equals("s /|\\ n"));
	}

	/*
	 * de.prob.unicode.lexer.LexerException: [1,4] Unknown token: |\ Drop not
	 * provided.
	 */
	@Ignore
	public void Drop() {
		assertTrue(UnicodeTranslator.toUnicode("s \\|/ n").equals("s \\|/ n"));
	}

	@Test
	public void FirstElement() {
		assertTrue(UnicodeTranslator.toUnicode("first(s)").equals("first(s)"));
	}

	@Test
	public void LastElement() {
		assertTrue(UnicodeTranslator.toUnicode("last(s)").equals("last(s)"));
	}

	@Test
	public void Tail() {
		assertTrue(UnicodeTranslator.toUnicode("tail(s)").equals("tail(s)"));
	}

	@Test
	public void Front() {
		assertTrue(UnicodeTranslator.toUnicode("front(s)").equals("front(s)"));
	}

	@Test
	public void GeneralizedConcatenation() {
		assertTrue(UnicodeTranslator.toUnicode("conc(ss)").equals("conc(ss)"));
	}

	/*
	 * de.prob.unicode.lexer.LexerException: [1,1] Unknown token: [ '[' not
	 * permitted
	 */
	@Ignore
	public void Substitution2() {
		assertTrue(UnicodeTranslator.toUnicode("[G]P").equals("[G]P"));
	}

	@Test
	public void Skip() {
		assertTrue(UnicodeTranslator.toUnicode("skip").equals("skip"));
	}

	/*
	 * XXX SimpleSubstitution not provided? why does this test fail?
	 */
	@Test
	public void SimpleSubstitution() {
		assertTrue(UnicodeTranslator.toUnicode("x := E").equals("x \u2254 E"));
	}

	/*
	 * XXX BooleanSubstitution not provided? why does this test fail?
	 */
	@Test
	public void BooleanSubstitution() {
		assertTrue(UnicodeTranslator.toUnicode("x := bool(P)").equals(
				"x \u2254 bool(P)"));
	}

	@Test
	public void ChoiceFromSet() {
		assertTrue(UnicodeTranslator.toUnicode("x :: S").equals("x :\u2208 S"));
	}

	/*
	 * XXX ChoiceByPredicate not provided? why does this test fail? makes
	 * "x ? P"
	 */
	@Test
	public void ChoiceByPredicate() {
		String expected = "x \u2208 P";
		String actual = UnicodeTranslator.toUnicode("x : P");
		assertEquals(expected, actual);
	}

	@Test
	public void ChoiceByPredicate2() {
		String expected = "x :\u2223 P";
		String actual = UnicodeTranslator.toUnicode("x :| P");
		assertEquals(expected, actual);
	}

	/*
	 * XXX FunctionalOverride not provided? why does this test fail?
	 */
	@Test
	public void FunctionalOverride() {
		assertTrue(UnicodeTranslator.toUnicode("f(x) := E").equals("f(x) \u2254 E"));
	}


	@Test
	public void MultipleSubstitution() {
		assertTrue(UnicodeTranslator.toUnicode("x,y := E,F").equals(
				"x,y \u2254 E,F"));
	}

	@Test
	public void ParallelSubstitution() {
		assertTrue(UnicodeTranslator.toUnicode("G || H").equals("G \u2225 H"));
	}

	@Test
	public void SequentialSubstitution() {
		assertTrue(UnicodeTranslator.toUnicode("G ; H").equals("G ; H"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,3] Unknown token: |
	 */
	@Test
	public void Precondition() {
		assertTrue(UnicodeTranslator.toUnicode("P | G").equals("P \u2223 G"));
	}

	
	@Test
	public void Context() {
		assertTrue(UnicodeTranslator.toUnicode("CONTEXT").equals("CONTEXT"));
	}

	@Test
	public void Extends() {
		assertTrue(UnicodeTranslator.toUnicode("EXTENDS").equals("EXTENDS"));
	}

	@Test
	public void Sets() {
		assertTrue(UnicodeTranslator.toUnicode("SETS").equals("SETS"));
	}

	@Test
	public void Constants() {
		assertTrue(UnicodeTranslator.toUnicode("CONSTANTS").equals("CONSTANTS"));
	}

	@Test
	public void Axioms() {
		assertTrue(UnicodeTranslator.toUnicode("AXIOMS").equals("AXIOMS"));
	}

	@Test
	public void Theorems() {
		assertTrue(UnicodeTranslator.toUnicode("THEOREMS").equals("THEOREMS"));
	}

	@Test
	public void End() {
		assertTrue(UnicodeTranslator.toUnicode("END").equals("END"));
	}

	@Test
	public void Machine() {
		assertTrue(UnicodeTranslator.toUnicode("MACHINE").equals("MACHINE"));
	}

	@Test
	public void Refines() {
		assertTrue(UnicodeTranslator.toUnicode("REFINES").equals("REFINES"));
	}

	@Test
	public void Sees() {
		assertTrue(UnicodeTranslator.toUnicode("SEES").equals("SEES"));
	}

	@Test
	public void Variables() {
		assertTrue(UnicodeTranslator.toUnicode("VARIABLES").equals("VARIABLES"));
	}

	@Test
	public void Invariant() {
		assertTrue(UnicodeTranslator.toUnicode("INVARIANT").equals("INVARIANT"));
	}

	@Test
	public void Variant() {
		assertTrue(UnicodeTranslator.toUnicode("VARIANT").equals("VARIANT"));
	}

	@Test
	public void Events() {
		assertTrue(UnicodeTranslator.toUnicode("EVENTS").equals("EVENTS"));
	}

	@Test
	public void Any() {
		assertTrue(UnicodeTranslator.toUnicode("ANY").equals("ANY"));
	}

	@Test
	public void Where() {
		assertTrue(UnicodeTranslator.toUnicode("WHERE").equals("WHERE"));
	}

	@Test
	public void With() {
		assertTrue(UnicodeTranslator.toUnicode("WITH").equals("WITH"));
	}

	@Test
	public void Then() {
		assertTrue(UnicodeTranslator.toUnicode("THEN").equals("THEN"));
	}

	/*--------------------------------------------------------------*/

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,4] Unknown token: _
	 * Identifiers consists of letters, digits, underscores
	 */

	@Test
	public void Letter() {
		assertTrue(UnicodeTranslator.toUnicode("abc").equals("abc"));
	}

	@Test
	public void LetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc123").equals("abc123"));
	}

	@Test
	public void LetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc_").equals("abc_"));
	}

	@Test
	public void LetterANY() {
		assertTrue(UnicodeTranslator.toUnicode("abcANY").equals("abcANY"));
		assertTrue(UnicodeTranslator.toUnicode("abcany").equals("abcany"));
	}

	@Test
	public void LetterFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("abcFALSE").equals("abcFALSE"));
		assertTrue(UnicodeTranslator.toUnicode("abcfalse").equals("abcfalse"));
	}

	@Test
	public void LetterINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTEGER").equals(
				"abcINTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("abcinteger").equals(
				"abcinteger"));
	}

	@Test
	public void LetterINTER() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTER").equals("abcINTER"));
		assertTrue(UnicodeTranslator.toUnicode("abcinter").equals("abcinter"));
	}

	@Test
	public void LetterNAT() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT").equals("abcNAT"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat").equals("abcnat"));
	}

	@Test
	public void LetterNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT1").equals("abcNAT1"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat1").equals("abcnat1"));
	}

	@Test
	public void LetterNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("abcNATURAL").equals(
				"abcNATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("abcnatural").equals(
				"abcnatural"));
	}

	@Test
	public void LetterNOT() {
		assertTrue(UnicodeTranslator.toUnicode("abcNOT").equals("abcNOT"));
		assertTrue(UnicodeTranslator.toUnicode("abcnot").equals("abcnot"));
	}

	@Test
	public void LetterOR() {
		assertTrue(UnicodeTranslator.toUnicode("abcOR").equals("abcOR"));
		assertTrue(UnicodeTranslator.toUnicode("abcor").equals("abcor"));
	}

	@Test
	public void LetterPOW() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW").equals("abcPOW"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow").equals("abcpow"));
	}

	@Test
	public void LetterPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW1").equals("abcPOW1"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow1").equals("abcpow1"));
	}

	@Test
	public void LetterTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("abcTRUE").equals("abcTRUE"));
		assertTrue(UnicodeTranslator.toUnicode("abctrue").equals("abctrue"));
	}

	@Test
	public void LetterUNION() {
		assertTrue(UnicodeTranslator.toUnicode("abcUNION").equals("abcUNION"));
		assertTrue(UnicodeTranslator.toUnicode("abcunion").equals("abcunion"));
	}

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,1] Unknown token: _
	 */
	@Test
	public void LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_").equals("abc123_"));
	}

	@Test
	public void LetterDigitANY() {
		assertTrue(UnicodeTranslator.toUnicode("abc123ANY").equals("abc123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("abc123any").equals("abc123any"));
	}

	@Test
	public void LetterDigitFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("abc123FALSE").equals(
				"abc123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("abc123false").equals(
				"abc123false"));
	}

	@Test
	public void LetterDigitINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("abc123INTEGER").equals(
				"abc123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("abc123integer").equals(
				"abc123integer"));
	}

	@Test
	public void LetterDigitINTER() {
		assertTrue(UnicodeTranslator.toUnicode("abc123INTER").equals(
				"abc123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("abc123inter").equals(
				"abc123inter"));
	}

	@Test
	public void LetterDigitNAT() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NAT").equals("abc123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("abc123nat").equals("abc123nat"));
	}

	@Test
	public void LetterDigitNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NAT1").equals(
				"abc123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("abc123nat1").equals(
				"abc123nat1"));
	}

	@Test
	public void LetterDigitNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NATURAL").equals(
				"abc123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("abc123natural").equals(
				"abc123natural"));
	}

	@Test
	public void LetterDigitNOT() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NOT").equals("abc123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("abc123not").equals("abc123not"));
	}

	@Test
	public void LetterDigitOR() {
		assertTrue(UnicodeTranslator.toUnicode("abc123OR").equals("abc123OR"));
		assertTrue(UnicodeTranslator.toUnicode("abc123or").equals("abc123or"));
	}

	@Test
	public void LetterDigitPOW() {
		assertTrue(UnicodeTranslator.toUnicode("abc123POW").equals("abc123POW"));
		assertTrue(UnicodeTranslator.toUnicode("abc123pow").equals("abc123pow"));
	}

	@Test
	public void LetterDigitPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("abc123POW1").equals(
				"abc123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("abc123pow1").equals(
				"abc123pow1"));
	}

	@Test
	public void LetterDigitTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("abc123TRUE").equals(
				"abc123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("abc123true").equals(
				"abc123true"));
	}

	@Test
	public void LetterDigitUNION() {
		assertTrue(UnicodeTranslator.toUnicode("abc123UNION").equals(
				"abc123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("abc123union").equals(
				"abc123union"));
	}

	@Test
	public void LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123").equals("abc_123"));
	}

	@Test
	public void LetterUnderscoreANY() {
		assertTrue(UnicodeTranslator.toUnicode("abc_ANY").equals("abc_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("abc_any").equals("abc_any"));
	}

	@Test
	public void LetterUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("abc_FALSE").equals("abc_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("abc_false").equals("abc_false"));
	}

	@Test
	public void LetterUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTEGER").equals(
				"abc_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("abc_integer").equals(
				"abc_integer"));
	}

	@Test
	public void LetterUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTER").equals("abc_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("abc_inter").equals("abc_inter"));
	}

	@Test
	public void LetterUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT").equals("abc_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat").equals("abc_nat"));
	}

	@Test
	public void LetterUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT1").equals("abc_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat1").equals("abc_nat1"));
	}

	@Test
	public void LetterUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NATURAL").equals(
				"abc_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("abc_natural").equals(
				"abc_natural"));
	}

	@Test
	public void LetterUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NOT").equals("abc_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("abc_not").equals("abc_not"));
	}

	@Test
	public void LetterUnderscoreOR() {
		assertTrue(UnicodeTranslator.toUnicode("abc_OR").equals("abc_OR"));
		assertTrue(UnicodeTranslator.toUnicode("abc_or").equals("abc_or"));
	}

	@Test
	public void LetterUnderscorePOW() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW").equals("abc_POW"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow").equals("abc_pow"));
	}

	@Test
	public void LetterUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW1").equals("abc_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow1").equals("abc_pow1"));
	}

	@Test
	public void LetterUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("abc_TRUE").equals("abc_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("abc_true").equals("abc_true"));
	}

	@Test
	public void LetterUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toUnicode("abc_UNION").equals("abc_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("abc_union").equals("abc_union"));
	}

	@Test
	public void LetterANYDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcANY123").equals("abcANY123"));
		assertTrue(UnicodeTranslator.toUnicode("abcany123").equals("abcany123"));
	}

	@Test
	public void LetterFALSEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcFALSE123").equals(
				"abcFALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("abcfalse123").equals(
				"abcfalse123"));
	}

	@Test
	public void LetterINTEGERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTEGER123").equals(
				"abcINTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("abcinteger123").equals(
				"abcinteger123"));
	}

	@Test
	public void LetterINTERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTER123").equals(
				"abcINTER123"));
		assertTrue(UnicodeTranslator.toUnicode("abcinter123").equals(
				"abcinter123"));
	}

	@Test
	public void LetterNATDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT123").equals("abcNAT123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat123").equals("abcnat123"));
	}

	@Test
	public void LetterNAT1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT1123").equals(
				"abcNAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat1123").equals(
				"abcnat1123"));
	}

	@Test
	public void LetterNATURALDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNATURAL123").equals(
				"abcNATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnatural123").equals(
				"abcnatural123"));
	}

	@Test
	public void LetterNOTDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNOT123").equals("abcNOT123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnot123").equals("abcnot123"));
	}

	@Test
	public void LetterORDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcOR123").equals("abcOR123"));
		assertTrue(UnicodeTranslator.toUnicode("abcor123").equals("abcor123"));
	}

	@Test
	public void LetterPOWDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW123").equals("abcPOW123"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow123").equals("abcpow123"));
	}

	@Test
	public void LetterPOW1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW1123").equals(
				"abcPOW1123"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow1123").equals(
				"abcpow1123"));
	}

	@Test
	public void LetterTRUEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcTRUE123").equals(
				"abcTRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("abctrue123").equals(
				"abctrue123"));
	}

	@Test
	public void LetterUNIONDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcUNION123").equals(
				"abcUNION123"));
		assertTrue(UnicodeTranslator.toUnicode("abcunion123").equals(
				"abcunion123"));
	}

	@Test
	public void LetterANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcANY_").equals("abcANY_"));
		assertTrue(UnicodeTranslator.toUnicode("abcany_").equals("abcany_"));
	}

	@Test
	public void LetterFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcFALSE_").equals("abcFALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("abcfalse_").equals("abcfalse_"));
	}

	@Test
	public void LetterINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTEGER_").equals(
				"abcINTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("abcinteger_").equals(
				"abcinteger_"));
	}

	@Test
	public void LetterINTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTER_").equals("abcINTER_"));
		assertTrue(UnicodeTranslator.toUnicode("abcinter_").equals("abcinter_"));
	}

	@Test
	public void LetterNATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT_").equals("abcNAT_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat_").equals("abcnat_"));
	}

	@Test
	public void LetterNAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT1_").equals("abcNAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat1_").equals("abcnat1_"));
	}

	@Test
	public void LetterNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNATURAL_").equals(
				"abcNATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnatural_").equals(
				"abcnatural_"));
	}

	@Test
	public void LetterNOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNOT_").equals("abcNOT_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnot_").equals("abcnot_"));
	}

	@Test
	public void LetterORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcOR_").equals("abcOR_"));
		assertTrue(UnicodeTranslator.toUnicode("abcor_").equals("abcor_"));
	}

	@Test
	public void LetterPOWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW_").equals("abcPOW_"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow_").equals("abcpow_"));
	}

	@Test
	public void LetterPOW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW1_").equals("abcPOW1_"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow1_").equals("abcpow1_"));
	}

	@Test
	public void LetterTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcTRUE_").equals("abcTRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("abctrue_").equals("abctrue_"));
	}

	@Test
	public void LetterUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcUNION_").equals("abcUNION_"));
		assertTrue(UnicodeTranslator.toUnicode("abcunion_").equals("abcunion_"));
	}

	@Test
	public void LetterDigitUnderscoreANY() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_ANY").equals(
				"abc123_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_any").equals(
				"abc123_any"));
	}

	@Test
	public void LetterDigitUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_FALSE").equals(
				"abc123_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_false").equals(
				"abc123_false"));
	}

	@Test
	public void LetterDigitUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_INTEGER").equals(
				"abc123_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_integer").equals(
				"abc123_integer"));
	}

	@Test
	public void LetterDigitUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_INTER").equals(
				"abc123_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_inter").equals(
				"abc123_inter"));
	}

	@Test
	public void LetterDigitUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_NAT").equals(
				"abc123_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_nat").equals(
				"abc123_nat"));
	}

	@Test
	public void LetterDigitUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_NAT1").equals(
				"abc123_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_nat1").equals(
				"abc123_nat1"));
	}

	@Test
	public void LetterDigitUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_NATURAL").equals(
				"abc123_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_natural").equals(
				"abc123_natural"));
	}

	@Test
	public void LetterDigitUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_NOT").equals(
				"abc123_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_not").equals(
				"abc123_not"));
	}

	@Test
	public void LetterDigitUnderscoreOR() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_OR").equals("abc123_OR"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_or").equals("abc123_or"));
	}

	@Test
	public void LetterDigitUnderscorePOW() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_POW").equals(
				"abc123_POW"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_pow").equals(
				"abc123_pow"));
	}

	@Test
	public void LetterDigitUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_POW1").equals(
				"abc123_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_pow1").equals(
				"abc123_pow1"));
	}

	@Test
	public void LetterDigitUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_TRUE").equals(
				"abc123_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_true").equals(
				"abc123_true"));
	}

	@Test
	public void LetterDigitUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toUnicode("abc123_UNION").equals(
				"abc123_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("abc123_union").equals(
				"abc123_union"));
	}

	@Test
	public void LetterDigitANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123ANY_").equals(
				"abc123ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123any_").equals(
				"abc123any_"));
	}

	@Test
	public void LetterDigitFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123FALSE_").equals(
				"abc123FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123false_").equals(
				"abc123false_"));
	}

	@Test
	public void LetterDigitINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123INTEGER_").equals(
				"abc123INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123integer_").equals(
				"abc123integer_"));
	}

	@Test
	public void LetterDigitINTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123INTER_").equals(
				"abc123INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123inter_").equals(
				"abc123inter_"));
	}

	@Test
	public void LetterDigitNATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NAT_").equals(
				"abc123NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123nat_").equals(
				"abc123nat_"));
	}

	@Test
	public void LetterDigitNAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NAT1_").equals(
				"abc123NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123nat1_").equals(
				"abc123nat1_"));
	}

	@Test
	public void LetterDigitNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NATURAL_").equals(
				"abc123NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123natural_").equals(
				"abc123natural_"));
	}

	@Test
	public void LetterDigitNOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123NOT_").equals(
				"abc123NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123not_").equals(
				"abc123not_"));
	}

	@Test
	public void LetterDigitORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123OR_").equals("abc123OR_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123or_").equals("abc123or_"));
	}

	@Test
	public void LetterDigitPOWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123POW_").equals(
				"abc123POW_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123pow_").equals(
				"abc123pow_"));
	}

	@Test
	public void LetterDigitPOW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123POW1_").equals(
				"abc123POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123pow1_").equals(
				"abc123pow1_"));
	}

	@Test
	public void LetterDigitTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123TRUE_").equals(
				"abc123TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123true_").equals(
				"abc123true_"));
	}

	@Test
	public void LetterDigitUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abc123UNION_").equals(
				"abc123UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("abc123union_").equals(
				"abc123union_"));
	}

	@Test
	public void LetterUnderscoreDigitANY() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123ANY").equals(
				"abc_123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123any").equals(
				"abc_123any"));
	}

	@Test
	public void LetterUnderscoreDigitFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123FALSE").equals(
				"abc_123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123false").equals(
				"abc_123false"));
	}

	@Test
	public void LetterUnderscoreDigitINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123INTEGER").equals(
				"abc_123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123integer").equals(
				"abc_123integer"));
	}

	@Test
	public void LetterUnderscoreDigitINTER() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123INTER").equals(
				"abc_123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123inter").equals(
				"abc_123inter"));
	}

	@Test
	public void LetterUnderscoreDigitANT() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123NAT").equals(
				"abc_123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123nat").equals(
				"abc_123nat"));
	}

	@Test
	public void LetterUnderscoreDigitNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123NAT1").equals(
				"abc_123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123nat1").equals(
				"abc_123nat1"));
	}

	@Test
	public void LetterUnderscoreDigitNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123NATURAL").equals(
				"abc_123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123natural").equals(
				"abc_123natural"));
	}

	@Test
	public void LetterUnderscoreDigitNOT() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123NOT").equals(
				"abc_123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123not").equals(
				"abc_123not"));
	}

	@Test
	public void LetterUnderscoreDigitOR() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123OR").equals("abc_123OR"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123or").equals("abc_123or"));
	}

	@Test
	public void LetterUnderscoreDigitPOW() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123POW").equals(
				"abc_123POW"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123pow").equals(
				"abc_123pow"));
	}

	@Test
	public void LetterUnderscoreDigitPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123POW1").equals(
				"abc_123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123pow1").equals(
				"abc_123pow1"));
	}

	@Test
	public void LetterUnderscoreDigitTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123TRUE").equals(
				"abc_123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123true").equals(
				"abc_123true"));
	}

	@Test
	public void LetterUnderscoreDigitUNION() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123UNION").equals(
				"abc_123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("abc_123union").equals(
				"abc_123union"));
	}

	@Test
	public void LetterUnderscoreANYDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_ANY123").equals(
				"abc_ANY123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_any123").equals(
				"abc_any123"));
	}

	@Test
	public void LetterUnderscoreFALSEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_FALSE123").equals(
				"abc_FALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_false123").equals(
				"abc_false123"));
	}

	@Test
	public void LetterUnderscoreINTEGERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTEGER123").equals(
				"abc_INTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_integer123").equals(
				"abc_integer123"));
	}

	@Test
	public void LetterUnderscoreINTERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTER123").equals(
				"abc_INTER123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_inter123").equals(
				"abc_inter123"));
	}

	@Test
	public void LetterUnderscoreNATDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT123").equals(
				"abc_NAT123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat123").equals(
				"abc_nat123"));
	}

	@Test
	public void LetterUnderscoreNAT1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT1123").equals(
				"abc_NAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat1123").equals(
				"abc_nat1123"));
	}

	@Test
	public void LetterUnderscoreNATURALDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NATURAL123").equals(
				"abc_NATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_natural123").equals(
				"abc_natural123"));
	}

	@Test
	public void LetterUnderscoreNOTDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NOT123").equals(
				"abc_NOT123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_not123").equals(
				"abc_not123"));
	}

	@Test
	public void LetterUnderscoreORDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_OR123").equals("abc_OR123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_or123").equals("abc_or123"));
	}

	@Test
	public void LetterUnderscorePOWDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW123").equals(
				"abc_POW123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow123").equals(
				"abc_pow123"));
	}

	@Test
	public void LetterUnderscorePOW1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW1123").equals(
				"abc_POW1123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow1123").equals(
				"abc_pow1123"));
	}

	@Test
	public void LetterUnderscoreTRUEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_TRUE123").equals(
				"abc_TRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_true123").equals(
				"abc_true123"));
	}

	@Test
	public void LetterUnderscoreUNIONDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abc_UNION123").equals(
				"abc_UNION123"));
		assertTrue(UnicodeTranslator.toUnicode("abc_union123").equals(
				"abc_union123"));
	}

	@Test
	public void LetterANYDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcANY123_").equals(
				"abcANY123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcany123_").equals(
				"abcany123_"));
	}

	@Test
	public void LetterFALSEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcFALSE123_").equals(
				"abcFALSE123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcfalse123_").equals(
				"abcfalse123_"));
	}

	@Test
	public void LetterINTEGERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTEGER123_").equals(
				"abcINTEGER123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcinteger123_").equals(
				"abcinteger123_"));
	}

	@Test
	public void LetterINTERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTER123_").equals(
				"abcINTER123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcinter123_").equals(
				"abcinter123_"));
	}

	@Test
	public void LetterNATDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT123_").equals(
				"abcNAT123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat123_").equals(
				"abcnat123_"));
	}

	@Test
	public void LetterNAT1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT1123_").equals(
				"abcNAT1123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat1123_").equals(
				"abcnat1123_"));
	}

	@Test
	public void LetterNATURALDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNATURAL123_").equals(
				"abcNATURAL123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnatural123_").equals(
				"abcnatural123_"));
	}

	@Test
	public void LetterNOTDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcNOT123_").equals(
				"abcNOT123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcnot123_").equals(
				"abcnot123_"));
	}

	@Test
	public void LetterORDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcOR123_").equals("abcOR123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcor123_").equals("abcor123_"));
	}

	@Test
	public void LetterPOWDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW123_").equals(
				"abcPOW123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow123_").equals(
				"abcpow123_"));
	}

	@Test
	public void LetterPOW1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW1123_").equals(
				"abcPOW1123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow1123_").equals(
				"abcpow1123_"));
	}

	@Test
	public void LetterTRUEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcTRUE123_").equals(
				"abcTRUE123_"));
		assertTrue(UnicodeTranslator.toUnicode("abctrue123_").equals(
				"abctrue123_"));
	}

	@Test
	public void LetterUNIONDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("abcUNION123_").equals(
				"abcUNION123_"));
		assertTrue(UnicodeTranslator.toUnicode("abcunion123_").equals(
				"abcunion123_"));
	}

	@Test
	public void LetterANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcANY_123").equals(
				"abcANY_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcany_123").equals(
				"abcany_123"));
	}

	@Test
	public void LetterFALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcFALSE_123").equals(
				"abcFALSE_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcfalse_123").equals(
				"abcfalse_123"));
	}

	@Test
	public void LetterINTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTEGER_123").equals(
				"abcINTEGER_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcinteger_123").equals(
				"abcinteger_123"));
	}

	@Test
	public void LetterINTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcINTER_123").equals(
				"abcINTER_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcinter_123").equals(
				"abcinter_123"));
	}

	@Test
	public void LetterNATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT_123").equals(
				"abcNAT_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat_123").equals(
				"abcnat_123"));
	}

	@Test
	public void LetterNAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNAT1_123").equals(
				"abcNAT1_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnat1_123").equals(
				"abcnat1_123"));
	}

	@Test
	public void LetterNATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNATURAL_123").equals(
				"abcNATURAL_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnatural_123").equals(
				"abcnatural_123"));
	}

	@Test
	public void LetterNOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcNOT_123").equals(
				"abcNOT_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcnot_123").equals(
				"abcnot_123"));
	}

	@Test
	public void LetterORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcOR_123").equals("abcOR_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcor_123").equals("abcor_123"));
	}

	@Test
	public void LetterPOWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW_123").equals(
				"abcPOW_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow_123").equals(
				"abcpow_123"));
	}

	@Test
	public void LetterPOW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcPOW1_123").equals(
				"abcPOW1_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcpow1_123").equals(
				"abcpow1_123"));
	}

	@Test
	public void LetterTRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcTRUE_123").equals(
				"abcTRUE_123"));
		assertTrue(UnicodeTranslator.toUnicode("abctrue_123").equals(
				"abctrue_123"));
	}

	@Test
	public void LetterUNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("abcUNION_123").equals(
				"abcUNION_123"));
		assertTrue(UnicodeTranslator.toUnicode("abcunion_123").equals(
				"abcunion_123"));
	}

	@Test
	public void Digit() {
		assertTrue(UnicodeTranslator.toUnicode("123").equals("123"));
	}

	@Test
	public void DigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123abc").equals("123abc"));
	}

	@Test
	public void DigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123_").equals("123_"));
	}

	@Test
	public void DigitANY() {
		assertTrue(UnicodeTranslator.toUnicode("123ANY").equals("123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("123any").equals("123any"));
	}

	@Test
	public void DigitFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("123FALSE").equals("123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123false").equals("123false"));
	}

	@Test
	public void DigitINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("123INTEGER").equals(
				"123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123integer").equals(
				"123integer"));
	}

	@Test
	public void DigitINTER() {
		assertTrue(UnicodeTranslator.toUnicode("123INTER").equals("123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("123inter").equals("123inter"));
	}

	@Test
	public void DigitNAT() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT").equals("123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("123nat").equals("123nat"));
	}

	@Test
	public void DigitNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT1").equals("123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1").equals("123nat1"));
	}

	@Test
	public void DigitNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("123NATURAL").equals(
				"123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123natural").equals(
				"123natural"));
	}

	@Test
	public void DigitNOT() {
		assertTrue(UnicodeTranslator.toUnicode("123NOT").equals("123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("123not").equals("123not"));
	}

	@Test
	public void DigitOR() {
		assertTrue(UnicodeTranslator.toUnicode("123OR").equals("123OR"));
		assertTrue(UnicodeTranslator.toUnicode("123or").equals("123or"));
	}

	@Test
	public void DigitPOW() {
		assertTrue(UnicodeTranslator.toUnicode("123POW").equals("123POW"));
		assertTrue(UnicodeTranslator.toUnicode("123pow").equals("123pow"));
	}

	@Test
	public void DigitPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("123POW1").equals("123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1").equals("123pow1"));
	}

	@Test
	public void DigitTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("123TRUE").equals("123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123true").equals("123true"));
	}

	@Test
	public void DigitUNION() {
		assertTrue(UnicodeTranslator.toUnicode("123UNION").equals("123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("123union").equals("123union"));
	}

	@Test
	public void DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_").equals("123abc_"));
	}

	@Test
	public void DigitLetterANY() {
		assertTrue(UnicodeTranslator.toUnicode("123abcANY").equals("123abcANY"));
		assertTrue(UnicodeTranslator.toUnicode("123abcany").equals("123abcany"));
	}

	@Test
	public void DigitLetterFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("123abcFALSE").equals(
				"123abcFALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123abcfalse").equals(
				"123abcfalse"));
	}

	@Test
	public void DigitLetterINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("123abcINTEGER").equals(
				"123abcINTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123abcinteger").equals(
				"123abcinteger"));
	}

	@Test
	public void DigitLetterINTER() {
		assertTrue(UnicodeTranslator.toUnicode("123abcINTER").equals(
				"123abcINTER"));
		assertTrue(UnicodeTranslator.toUnicode("123abcinter").equals(
				"123abcinter"));
	}

	@Test
	public void DigitLetterNAT() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNAT").equals("123abcNAT"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnat").equals("123abcnat"));
	}

	@Test
	public void DigitLetterNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNAT1").equals(
				"123abcNAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnat1").equals(
				"123abcnat1"));
	}

	@Test
	public void DigitLetterNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNATURAL").equals(
				"123abcNATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnatural").equals(
				"123abcnatural"));
	}

	@Test
	public void DigitLetterNOT() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNOT").equals("123abcNOT"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnot").equals("123abcnot"));
	}

	@Test
	public void DigitLetterOR() {
		assertTrue(UnicodeTranslator.toUnicode("123abcOR").equals("123abcOR"));
		assertTrue(UnicodeTranslator.toUnicode("123abcor").equals("123abcor"));
	}

	@Test
	public void DigitLetterPOW() {
		assertTrue(UnicodeTranslator.toUnicode("123abcPOW").equals("123abcPOW"));
		assertTrue(UnicodeTranslator.toUnicode("123abcpow").equals("123abcpow"));
	}

	@Test
	public void DigitLetterPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("123abcPOW1").equals(
				"123abcPOW1"));
		assertTrue(UnicodeTranslator.toUnicode("123abcpow1").equals(
				"123abcpow1"));
	}

	@Test
	public void DigitLetterTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("123abcTRUE").equals(
				"123abcTRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123abctrue").equals(
				"123abctrue"));
	}

	@Test
	public void DigitLetterUNION() {
		assertTrue(UnicodeTranslator.toUnicode("123abcUNION").equals(
				"123abcUNION"));
		assertTrue(UnicodeTranslator.toUnicode("123abcunion").equals(
				"123abcunion"));
	}

	@Test
	public void DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_abc").equals("123_abc"));
	}

	@Test
	public void DigitUnderscoreANY() {
		assertTrue(UnicodeTranslator.toUnicode("123_ANY").equals("123_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("123_any").equals("123_any"));
	}

	@Test
	public void DigitUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("123_FALSE").equals("123_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123_false").equals("123_false"));
	}

	@Test
	public void DigitUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTEGER").equals(
				"123_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123_integer").equals(
				"123_integer"));
	}

	@Test
	public void DigitUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTER").equals("123_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("123_inter").equals("123_inter"));
	}

	@Test
	public void DigitUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toUnicode("123_NAT").equals("123_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("123_nat").equals("123_nat"));
	}

	@Test
	public void DigitUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("123_NAT1").equals("123_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123_nat1").equals("123_nat1"));
	}

	@Test
	public void DigitUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("123_NATURAL").equals(
				"123_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123_natural").equals(
				"123_natural"));
	}

	@Test
	public void DigitUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toUnicode("123_NOT").equals("123_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("123_not").equals("123_not"));
	}

	@Test
	public void DigitUnderscoreOR() {
		assertTrue(UnicodeTranslator.toUnicode("123_OR").equals("123_OR"));
		assertTrue(UnicodeTranslator.toUnicode("123_or").equals("123_or"));
	}

	@Test
	public void DigitUnderscorePOW() {
		assertTrue(UnicodeTranslator.toUnicode("123_POW").equals("123_POW"));
		assertTrue(UnicodeTranslator.toUnicode("123_pow").equals("123_pow"));
	}

	@Test
	public void DigitUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toUnicode("123_POW1").equals("123_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("123_pow1").equals("123_pow1"));
	}

	@Test
	public void DigitUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("123_TRUE").equals("123_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123_true").equals("123_true"));
	}

	@Test
	public void DigitUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toUnicode("123_UNION").equals("123_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("123_union").equals("123_union"));
	}

	@Test
	public void DigitANYLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123ANYabc").equals("123ANYabc"));
		assertTrue(UnicodeTranslator.toUnicode("123anyabc").equals("123anyabc"));
	}

	@Test
	public void DigitFALSELetter() {
		assertTrue(UnicodeTranslator.toUnicode("123FALSEabc").equals(
				"123FALSEabc"));
		assertTrue(UnicodeTranslator.toUnicode("123falseabc").equals(
				"123falseabc"));
	}

	@Test
	public void DigitINTEGERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123INTEGERabc").equals(
				"123INTEGERabc"));
		assertTrue(UnicodeTranslator.toUnicode("123integerabc").equals(
				"123integerabc"));
	}

	@Test
	public void DigitINTERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123INTERabc").equals(
				"123INTERabc"));
		assertTrue(UnicodeTranslator.toUnicode("123interabc").equals(
				"123interabc"));
	}

	@Test
	public void DigitNATLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NATabc").equals("123NATabc"));
		assertTrue(UnicodeTranslator.toUnicode("123natabc").equals("123natabc"));
	}

	@Test
	public void DigitNAT1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT1abc").equals(
				"123NAT1abc"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1abc").equals(
				"123nat1abc"));
	}

	@Test
	public void DigitNATURALLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NATURALabc").equals(
				"123NATURALabc"));
		assertTrue(UnicodeTranslator.toUnicode("123naturalabc").equals(
				"123naturalabc"));
	}

	@Test
	public void DigitNOTLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NOTabc").equals("123NOTabc"));
		assertTrue(UnicodeTranslator.toUnicode("123notabc").equals("123notabc"));
	}

	@Test
	public void DigitORLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123ORabc").equals("123ORabc"));
		assertTrue(UnicodeTranslator.toUnicode("123orabc").equals("123orabc"));
	}

	@Test
	public void DigitPOWLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123POWabc").equals("123POWabc"));
		assertTrue(UnicodeTranslator.toUnicode("123powabc").equals("123powabc"));
	}

	@Test
	public void DigitPOW1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("123POW1abc").equals(
				"123POW1abc"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1abc").equals(
				"123pow1abc"));
	}

	@Test
	public void DigitTRUELetter() {
		assertTrue(UnicodeTranslator.toUnicode("123TRUEabc").equals(
				"123TRUEabc"));
		assertTrue(UnicodeTranslator.toUnicode("123trueabc").equals(
				"123trueabc"));
	}

	@Test
	public void DigitUNIONLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123UNIONabc").equals(
				"123UNIONabc"));
		assertTrue(UnicodeTranslator.toUnicode("123unionabc").equals(
				"123unionabc"));
	}

	@Test
	public void DigitANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123ANY_").equals("123ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("123any_").equals("123any_"));
	}

	@Test
	public void DigitFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123FALSE_").equals("123FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("123false_").equals("123false_"));
	}

	@Test
	public void DigitINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123INTEGER_").equals(
				"123INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("123integer_").equals(
				"123integer_"));
	}

	@Test
	public void DigitINTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123INTER_").equals("123INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("123inter_").equals("123inter_"));
	}

	@Test
	public void DigitNATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT_").equals("123NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("123nat_").equals("123nat_"));
	}

	@Test
	public void DigitNAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT1_").equals("123NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1_").equals("123nat1_"));
	}

	@Test
	public void DigitNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NATURAL_").equals(
				"123NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("123natural_").equals(
				"123natural_"));
	}

	@Test
	public void DigitNOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NOT_").equals("123NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("123not_").equals("123not_"));
	}

	@Test
	public void DigitORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123OR_").equals("123OR_"));
		assertTrue(UnicodeTranslator.toUnicode("123or_").equals("123or_"));
	}

	@Test
	public void DigitPOWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123POW_").equals("123POW_"));
		assertTrue(UnicodeTranslator.toUnicode("123pow_").equals("123pow_"));
	}

	@Test
	public void DigitPOW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("123POW1_").equals("123POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1_").equals("123pow1_"));
	}

	@Test
	public void DigitTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123TRUE_").equals("123TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("123true_").equals("123true_"));
	}

	@Test
	public void DigitUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123UNION_").equals("123UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("123union_").equals("123union_"));
	}

	@Test
	public void DigitLetterUnderscoreANY() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_ANY").equals(
				"123abc_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_any").equals(
				"123abc_any"));
	}

	@Test
	public void DigitLetterUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_FALSE").equals(
				"123abc_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_false").equals(
				"123abc_false"));
	}

	@Test
	public void DigitLetterUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_INTEGER").equals(
				"123abc_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_integer").equals(
				"123abc_integer"));
	}

	@Test
	public void DigitLetterUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_INTER").equals(
				"123abc_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_inter").equals(
				"123abc_inter"));
	}

	@Test
	public void DigitLetterUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_NAT").equals(
				"123abc_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_nat").equals(
				"123abc_nat"));
	}

	@Test
	public void DigitLetterUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_NAT1").equals(
				"123abc_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_nat1").equals(
				"123abc_nat1"));
	}

	@Test
	public void DigitLetterUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_NATURAL").equals(
				"123abc_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_natural").equals(
				"123abc_natural"));
	}

	@Test
	public void DigitLetterUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_NOT").equals(
				"123abc_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_not").equals(
				"123abc_not"));
	}

	@Test
	public void DigitLetterUnderscoreOR() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_OR").equals("123abc_OR"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_or").equals("123abc_or"));
	}

	@Test
	public void DigitLetterUnderscorePOW() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_POW").equals(
				"123abc_POW"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_pow").equals(
				"123abc_pow"));
	}

	@Test
	public void DigitLetterUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_POW1").equals(
				"123abc_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_pow1").equals(
				"123abc_pow1"));
	}

	@Test
	public void DigitLetterUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_TRUE").equals(
				"123abc_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_true").equals(
				"123abc_true"));
	}

	@Test
	public void DigitLetterUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toUnicode("123abc_UNION").equals(
				"123abc_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("123abc_union").equals(
				"123abc_union"));
	}

	@Test
	public void DigitLetterANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcANY_").equals(
				"123abcANY_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcany_").equals(
				"123abcany_"));
	}

	@Test
	public void DigitLetterFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcFALSE_").equals(
				"123abcFALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcfalse_").equals(
				"123abcfalse_"));
	}

	@Test
	public void DigitLetterINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcINTEGER_").equals(
				"123abcINTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcinteger_").equals(
				"123abcinteger_"));
	}

	@Test
	public void DigitLetterINTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcINTER_").equals(
				"123abcINTER_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcinter_").equals(
				"123abcinter_"));
	}

	@Test
	public void DigitLetterNATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNAT_").equals(
				"123abcNAT_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnat_").equals(
				"123abcnat_"));
	}

	@Test
	public void DigitLetterNAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNAT1_").equals(
				"123abcNAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnat1_").equals(
				"123abcnat1_"));
	}

	@Test
	public void DigitLetterNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNATURAL_").equals(
				"123abcNATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnatural_").equals(
				"123abcnatural_"));
	}

	@Test
	public void DigitLetterNOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcNOT_").equals(
				"123abcNOT_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcnot_").equals(
				"123abcnot_"));
	}

	@Test
	public void DigitLetterORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcOR_").equals("123abcOR_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcor_").equals("123abcor_"));
	}

	@Test
	public void DigitLetterPOWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcPOW_").equals(
				"123abcPOW_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcpow_").equals(
				"123abcpow_"));
	}

	@Test
	public void DigitLetterPOW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcPOW1_").equals(
				"123abcPOW1_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcpow1_").equals(
				"123abcpow1_"));
	}

	@Test
	public void DigitLetterTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcTRUE_").equals(
				"123abcTRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("123abctrue_").equals(
				"123abctrue_"));
	}

	@Test
	public void DigitLetterUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123abcUNION_").equals(
				"123abcUNION_"));
		assertTrue(UnicodeTranslator.toUnicode("123abcunion_").equals(
				"123abcunion_"));
	}

	@Test
	public void DigitUnderscoreLetterANY() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcANY").equals(
				"123_abcANY"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcany").equals(
				"123_abcany"));
	}

	@Test
	public void DigitUnderscoreLetterFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcFALSE").equals(
				"123_abcFALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcfalse").equals(
				"123_abcfalse"));
	}

	@Test
	public void DigitUnderscoreLetterINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcINTEGER").equals(
				"123_abcINTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcinteger").equals(
				"123_abcinteger"));
	}

	@Test
	public void DigitUnderscoreLetterINTER() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcINTER").equals(
				"123_abcINTER"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcinter").equals(
				"123_abcinter"));
	}

	@Test
	public void DigitUnderscoreLetterNAT() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcNAT").equals(
				"123_abcNAT"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcnat").equals(
				"123_abcnat"));
	}

	@Test
	public void DigitUnderscoreLetterNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcNAT1").equals(
				"123_abcNAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcnat1").equals(
				"123_abcnat1"));
	}

	@Test
	public void DigitUnderscoreLetterNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcNATURAL").equals(
				"123_abcNATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcnatural").equals(
				"123_abcnatural"));
	}

	@Test
	public void DigitUnderscoreLetterNOT() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcNOT").equals(
				"123_abcNOT"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcnot").equals(
				"123_abcnot"));
	}

	@Test
	public void DigitUnderscoreLetterOR() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcOR").equals("123_abcOR"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcor").equals("123_abcor"));
	}

	@Test
	public void DigitUnderscoreLetterPOW() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcPOW").equals(
				"123_abcPOW"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcpow").equals(
				"123_abcpow"));
	}

	@Test
	public void DigitUnderscoreLetterPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcPOW1").equals(
				"123_abcPOW1"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcpow1").equals(
				"123_abcpow1"));
	}

	@Test
	public void DigitUnderscoreLetterTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcTRUE").equals(
				"123_abcTRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123_abctrue").equals(
				"123_abctrue"));
	}

	@Test
	public void DigitUnderscoreLetterUNION() {
		assertTrue(UnicodeTranslator.toUnicode("123_abcUNION").equals(
				"123_abcUNION"));
		assertTrue(UnicodeTranslator.toUnicode("123_abcunion").equals(
				"123_abcunion"));
	}

	@Test
	public void DigitUnderscoreANYLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_ANYabc").equals(
				"123_ANYabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_anyabc").equals(
				"123_anyabc"));
	}

	@Test
	public void DigitUnderscoreFALSELetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_FALSEabc").equals(
				"123_FALSEabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_falseabc").equals(
				"123_falseabc"));
	}

	@Test
	public void DigitUnderscoreINTEGERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTEGERabc").equals(
				"123_INTEGERabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_integerabc").equals(
				"123_integerabc"));
	}

	@Test
	public void DigitUnderscoreINTERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTERabc").equals(
				"123_INTERabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_interabc").equals(
				"123_interabc"));
	}

	@Test
	public void DigitUnderscoreNATLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_NATabc").equals(
				"123_NATabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_natabc").equals(
				"123_natabc"));
	}

	@Test
	public void DigitUnderscoreNAT1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("123_NAT1abc").equals(
				"123_NAT1abc"));
		assertTrue(UnicodeTranslator.toUnicode("123_nat1abc").equals(
				"123_nat1abc"));
	}

	@Test
	public void DigitUnderscoreNATURALLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_NATURALabc").equals(
				"123_NATURALabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_naturalabc").equals(
				"123_naturalabc"));
	}

	@Test
	public void DigitUnderscoreNOTLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_NOTabc").equals(
				"123_NOTabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_notabc").equals(
				"123_notabc"));
	}

	@Test
	public void DigitUnderscoreORLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_ORabc").equals("123_ORabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_orabc").equals("123_orabc"));
	}

	@Test
	public void DigitUnderscorePOWLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_POWabc").equals(
				"123_POWabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_powabc").equals(
				"123_powabc"));
	}

	@Test
	public void DigitUnderscorePOW1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("123_POW1abc").equals(
				"123_POW1abc"));
		assertTrue(UnicodeTranslator.toUnicode("123_pow1abc").equals(
				"123_pow1abc"));
	}

	@Test
	public void DigitUnderscoreTRUELetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_TRUEabc").equals(
				"123_TRUEabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_trueabc").equals(
				"123_trueabc"));
	}

	@Test
	public void DigitUnderscoreUNIONLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123_UNIONabc").equals(
				"123_UNIONabc"));
		assertTrue(UnicodeTranslator.toUnicode("123_unionabc").equals(
				"123_unionabc"));
	}

	@Test
	public void DigitANYLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123ANYabc_").equals(
				"123ANYabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123anyabc_").equals(
				"123anyabc_"));
	}

	@Test
	public void DigitFALSELetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123FALSEabc_").equals(
				"123FALSEabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123falseabc_").equals(
				"123falseabc_"));
	}

	@Test
	public void DigitINTEGERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123INTEGERabc_").equals(
				"123INTEGERabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123integerabc_").equals(
				"123integerabc_"));
	}

	@Test
	public void DigitINTERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123INTERabc_").equals(
				"123INTERabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123interabc_").equals(
				"123interabc_"));
	}

	@Test
	public void DigitNATLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NATabc_").equals(
				"123NATabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123natabc_").equals(
				"123natabc_"));
	}

	@Test
	public void DigitNAT1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT1abc_").equals(
				"123NAT1abc_"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1abc_").equals(
				"123nat1abc_"));
	}

	@Test
	public void DigitNATURALLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NATURALabc_").equals(
				"123NATURALabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123naturalabc_").equals(
				"123naturalabc_"));
	}

	@Test
	public void DigitNOTLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123NOTabc_").equals(
				"123NOTabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123notabc_").equals(
				"123notabc_"));
	}

	@Test
	public void DigitORLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123ORabc_").equals("123ORabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123orabc_").equals("123orabc_"));
	}

	@Test
	public void DigitPOWLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123POWabc_").equals(
				"123POWabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123powabc_").equals(
				"123powabc_"));
	}

	@Test
	public void DigitPOW1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123POW1abc_").equals(
				"123POW1abc_"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1abc_").equals(
				"123pow1abc_"));
	}

	@Test
	public void DigitTRUELetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123TRUEabc_").equals(
				"123TRUEabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123trueabc_").equals(
				"123trueabc_"));
	}

	@Test
	public void DigitUNIONLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("123UNIONabc_").equals(
				"123UNIONabc_"));
		assertTrue(UnicodeTranslator.toUnicode("123unionabc_").equals(
				"123unionabc_"));
	}

	@Test
	public void DigitANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123ANY_abc").equals(
				"123ANY_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123any_abc").equals(
				"123any_abc"));
	}

	@Test
	public void DigitFALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123FALSE_abc").equals(
				"123FALSE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123false_abc").equals(
				"123false_abc"));
	}

	@Test
	public void DigitINTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123INTEGER_abc").equals(
				"123INTEGER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123integer_abc").equals(
				"123integer_abc"));
	}

	@Test
	public void DigitINTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123INTER_abc").equals(
				"123INTER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123inter_abc").equals(
				"123inter_abc"));
	}

	@Test
	public void DigitNATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT_abc").equals(
				"123NAT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123nat_abc").equals(
				"123nat_abc"));
	}

	@Test
	public void DigitNAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NAT1_abc").equals(
				"123NAT1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1_abc").equals(
				"123nat1_abc"));
	}

	@Test
	public void DigitNATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NATURAL_abc").equals(
				"123NATURAL_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123natural_abc").equals(
				"123natural_abc"));
	}

	@Test
	public void DigitNOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123NOT_abc").equals(
				"123NOT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123not_abc").equals(
				"123not_abc"));
	}

	@Test
	public void DigitORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123OR_abc").equals("123OR_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123or_abc").equals("123or_abc"));
	}

	@Test
	public void DigitPOWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123POW_abc").equals(
				"123POW_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123pow_abc").equals(
				"123pow_abc"));
	}

	@Test
	public void DigitPOW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123POW1_abc").equals(
				"123POW1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1_abc").equals(
				"123pow1_abc"));
	}

	@Test
	public void DigitTRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123TRUE_abc").equals(
				"123TRUE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123true_abc").equals(
				"123true_abc"));
	}

	@Test
	public void DigitUNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("123UNION_abc").equals(
				"123UNION_abc"));
		assertTrue(UnicodeTranslator.toUnicode("123union_abc").equals(
				"123union_abc"));
	}

	@Test
	public void Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("_").equals("_"));
	}

	@Test
	public void UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_abc").equals("_abc"));
	}

	@Test
	public void UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_123").equals("_123"));
	}

	@Test
	public void UnderscoreANY() {
		assertTrue(UnicodeTranslator.toUnicode("_ANY").equals("_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("_any").equals("_any"));
	}

	@Test
	public void UnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSE").equals("_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_false").equals("_false"));
	}

	@Test
	public void UnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("_INTEGER").equals("_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_integer").equals("_integer"));
	}

	@Test
	public void UnderscoreINTER() {
		assertTrue(UnicodeTranslator.toUnicode("_INTER").equals("_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("_inter").equals("_inter"));
	}

	@Test
	public void UnderscoreNAT() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT").equals("_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("_nat").equals("_nat"));
	}

	@Test
	public void UnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1").equals("_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1").equals("_nat1"));
	}

	@Test
	public void UnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURAL").equals("_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_natural").equals("_natural"));
	}

	@Test
	public void UnderscoreNOT() {
		assertTrue(UnicodeTranslator.toUnicode("_NOT").equals("_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("_not").equals("_not"));
	}

	@Test
	public void UnderscoreOR() {
		assertTrue(UnicodeTranslator.toUnicode("_OR").equals("_OR"));
		assertTrue(UnicodeTranslator.toUnicode("_or").equals("_or"));
	}

	@Test
	public void UnderscorePOW() {
		assertTrue(UnicodeTranslator.toUnicode("_POW").equals("_POW"));
		assertTrue(UnicodeTranslator.toUnicode("_pow").equals("_pow"));
	}

	@Test
	public void UnderscorePOW1() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1").equals("_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1").equals("_pow1"));
	}

	@Test
	public void UnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUE").equals("_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_true").equals("_true"));
	}

	@Test
	public void UnderscoreUNION() {
		assertTrue(UnicodeTranslator.toUnicode("_UNION").equals("_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("_union").equals("_union"));
	}

	@Test
	public void UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123").equals("_abc123"));
	}

	@Test
	public void UnderscoreLetterANY() {
		assertTrue(UnicodeTranslator.toUnicode("_123ANY").equals("_123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("_123any").equals("_123any"));
	}

	@Test
	public void UnderscoreLetterFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("_123FALSE").equals("_123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_123false").equals("_123false"));
	}

	@Test
	public void UnderscoreLetterINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTEGER").equals(
				"_123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_123integer").equals(
				"_123integer"));
	}

	@Test
	public void UnderscoreLetterINTER() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTER").equals("_123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("_123inter").equals("_123inter"));
	}

	@Test
	public void UnderscoreLetterNAT() {
		assertTrue(UnicodeTranslator.toUnicode("_123NAT").equals("_123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("_123nat").equals("_123nat"));
	}

	@Test
	public void UnderscoreLetterNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("_123NAT1").equals("_123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_123nat1").equals("_123nat1"));
	}

	@Test
	public void UnderscoreLetterNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("_123NATURAL").equals(
				"_123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_123natural").equals(
				"_123natural"));
	}

	@Test
	public void UnderscoreLetterNOT() {
		assertTrue(UnicodeTranslator.toUnicode("_123NOT").equals("_123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("_123not").equals("_123not"));
	}

	@Test
	public void UnderscoreLetterOR() {
		assertTrue(UnicodeTranslator.toUnicode("_123OR").equals("_123OR"));
		assertTrue(UnicodeTranslator.toUnicode("_123or").equals("_123or"));
	}

	@Test
	public void UnderscoreLetterPOW() {
		assertTrue(UnicodeTranslator.toUnicode("_123POW").equals("_123POW"));
		assertTrue(UnicodeTranslator.toUnicode("_123pow").equals("_123pow"));
	}

	@Test
	public void UnderscoreLetterPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("_123POW1").equals("_123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("_123pow1").equals("_123pow1"));
	}

	@Test
	public void UnderscoreLetterTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("_123TRUE").equals("_123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_123true").equals("_123true"));
	}

	@Test
	public void UnderscoreLetterUNION() {
		assertTrue(UnicodeTranslator.toUnicode("_123UNION").equals("_123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("_123union").equals("_123union"));
	}

	@Test
	public void UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123abc").equals("_123abc"));
	}

	@Test
	public void UnderscoreDigitANY() {
		assertTrue(UnicodeTranslator.toUnicode("_123ANY").equals("_123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("_123any").equals("_123any"));
	}

	@Test
	public void UnderscoreDigitFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("_123FALSE").equals("_123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_123false").equals("_123false"));
	}

	@Test
	public void UnderscoreDigitINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTEGER").equals(
				"_123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_123integer").equals(
				"_123integer"));
	}

	@Test
	public void UnderscoreDigitINTER() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTER").equals("_123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("_123inter").equals("_123inter"));
	}

	@Test
	public void UnderscoreDigitNAT() {
		assertTrue(UnicodeTranslator.toUnicode("_123NAT").equals("_123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("_123nat").equals("_123nat"));
	}

	@Test
	public void UnderscoreDigitNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("_123NAT1").equals("_123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_123nat1").equals("_123nat1"));
	}

	@Test
	public void UnderscoreDigitNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("_123NATURAL").equals(
				"_123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_123natural").equals(
				"_123natural"));
	}

	@Test
	public void UnderscoreDigitNOT() {
		assertTrue(UnicodeTranslator.toUnicode("_123NOT").equals("_123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("_123not").equals("_123not"));
	}

	@Test
	public void UnderscoreDigitOR() {
		assertTrue(UnicodeTranslator.toUnicode("_123OR").equals("_123OR"));
		assertTrue(UnicodeTranslator.toUnicode("_123or").equals("_123or"));
	}

	@Test
	public void UnderscoreDigitPOW() {
		assertTrue(UnicodeTranslator.toUnicode("_123POW").equals("_123POW"));
		assertTrue(UnicodeTranslator.toUnicode("_123pow").equals("_123pow"));
	}

	@Test
	public void UnderscoreDigitPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("_123POW1").equals("_123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("_123pow1").equals("_123pow1"));
	}

	@Test
	public void UnderscoreDigitTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("_123TRUE").equals("_123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_123true").equals("_123true"));
	}

	@Test
	public void UnderscoreDigitUNION() {
		assertTrue(UnicodeTranslator.toUnicode("_123UNION").equals("_123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("_123union").equals("_123union"));
	}

	@Test
	public void UnderscoreANYLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_ANYabc").equals("_ANYabc"));
		assertTrue(UnicodeTranslator.toUnicode("_anyabc").equals("_anyabc"));
	}

	@Test
	public void UnderscoreFALSELetter() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSEabc").equals("_FALSEabc"));
		assertTrue(UnicodeTranslator.toUnicode("_falseabc").equals("_falseabc"));
	}

	@Test
	public void UnderscoreINTEGERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_INTEGERabc").equals(
				"_INTEGERabc"));
		assertTrue(UnicodeTranslator.toUnicode("_integerabc").equals(
				"_integerabc"));
	}

	@Test
	public void UnderscoreINTERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_INTERabc").equals("_INTERabc"));
		assertTrue(UnicodeTranslator.toUnicode("_interabc").equals("_interabc"));
	}

	@Test
	public void UnderscoreNATLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NATabc").equals("_NATabc"));
		assertTrue(UnicodeTranslator.toUnicode("_natabc").equals("_natabc"));
	}

	@Test
	public void UnderscoreNAT1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1abc").equals("_NAT1abc"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1abc").equals("_nat1abc"));
	}

	@Test
	public void UnderscoreNATURALLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURALabc").equals(
				"_NATURALabc"));
		assertTrue(UnicodeTranslator.toUnicode("_naturalabc").equals(
				"_naturalabc"));
	}

	@Test
	public void UnderscoreNOTLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NOTabc").equals("_NOTabc"));
		assertTrue(UnicodeTranslator.toUnicode("_notabc").equals("_notabc"));
	}

	@Test
	public void UnderscoreORLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_ORabc").equals("_ORabc"));
		assertTrue(UnicodeTranslator.toUnicode("_orabc").equals("_orabc"));
	}

	@Test
	public void UnderscorePOWLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_POWabc").equals("_POWabc"));
		assertTrue(UnicodeTranslator.toUnicode("_powabc").equals("_powabc"));
	}

	@Test
	public void UnderscorePOW1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1abc").equals("_POW1abc"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1abc").equals("_pow1abc"));
	}

	@Test
	public void UnderscoreTRUELetter() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUEabc").equals("_TRUEabc"));
		assertTrue(UnicodeTranslator.toUnicode("_trueabc").equals("_trueabc"));
	}

	@Test
	public void UnderscoreUNIONLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_UNIONabc").equals("_UNIONabc"));
		assertTrue(UnicodeTranslator.toUnicode("_unionabc").equals("_unionabc"));
	}

	@Test
	public void UnderscoreANYDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_ANY123").equals("_ANY123"));
		assertTrue(UnicodeTranslator.toUnicode("_any123").equals("_any123"));
	}

	@Test
	public void UnderscoreFALSEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSE123").equals("_FALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("_false123").equals("_false123"));
	}

	@Test
	public void UnderscoreINTEGERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_INTEGER123").equals(
				"_INTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("_integer123").equals(
				"_integer123"));
	}

	@Test
	public void UnderscoreINTERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_INTER123").equals("_INTER123"));
		assertTrue(UnicodeTranslator.toUnicode("_inter123").equals("_inter123"));
	}

	@Test
	public void UnderscoreNATDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT123").equals("_NAT123"));
		assertTrue(UnicodeTranslator.toUnicode("_nat123").equals("_nat123"));
	}

	@Test
	public void UnderscoreNAT1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1123").equals("_NAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1123").equals("_nat1123"));
	}

	@Test
	public void UnderscoreNATURALDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURAL123").equals(
				"_NATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("_natural123").equals(
				"_natural123"));
	}

	@Test
	public void UnderscoreNOTDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NOT123").equals("_NOT123"));
		assertTrue(UnicodeTranslator.toUnicode("_not123").equals("_not123"));
	}

	@Test
	public void UnderscoreORDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_OR123").equals("_OR123"));
		assertTrue(UnicodeTranslator.toUnicode("_or123").equals("_or123"));
	}

	@Test
	public void UnderscorePOWDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_POW123").equals("_POW123"));
		assertTrue(UnicodeTranslator.toUnicode("_pow123").equals("_pow123"));
	}

	@Test
	public void UnderscorePOW1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1123").equals("_POW1123"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1123").equals("_pow1123"));
	}

	@Test
	public void UnderscoreTRUEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUE123").equals("_TRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("_true123").equals("_true123"));
	}

	@Test
	public void UnderscoreUNIONDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_UNION123").equals("_UNION123"));
		assertTrue(UnicodeTranslator.toUnicode("_union123").equals("_union123"));
	}

	@Test
	public void UnderscoreLetterDigitANY() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123ANY").equals(
				"_abc123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123any").equals(
				"_abc123any"));
	}

	@Test
	public void UnderscoreLetterDigitFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123FALSE").equals(
				"_abc123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123false").equals(
				"_abc123false"));
	}

	@Test
	public void UnderscoreLetterDigitINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123INTEGER").equals(
				"_abc123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123integer").equals(
				"_abc123integer"));
	}

	@Test
	public void UnderscoreLetterDigitINTER() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123INTER").equals(
				"_abc123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123inter").equals(
				"_abc123inter"));
	}

	@Test
	public void UnderscoreLetterDigitNAT() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123NAT").equals(
				"_abc123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123nat").equals(
				"_abc123nat"));
	}

	@Test
	public void UnderscoreLetterDigitNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123NAT1").equals(
				"_abc123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123nat1").equals(
				"_abc123nat1"));
	}

	@Test
	public void UnderscoreLetterDigitNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123NATURAL").equals(
				"_abc123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123natural").equals(
				"_abc123natural"));
	}

	@Test
	public void UnderscoreLetterDigitNOT() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123NOT").equals(
				"_abc123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123not").equals(
				"_abc123not"));
	}

	@Test
	public void UnderscoreLetterDigitOR() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123OR").equals("_abc123OR"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123or").equals("_abc123or"));
	}

	@Test
	public void UnderscoreLetterDigitPOW() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123POW").equals(
				"_abc123POW"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123pow").equals(
				"_abc123pow"));
	}

	@Test
	public void UnderscoreLetterDigitPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123POW1").equals(
				"_abc123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123pow1").equals(
				"_abc123pow1"));
	}

	@Test
	public void UnderscoreLetterDigitTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123TRUE").equals(
				"_abc123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123true").equals(
				"_abc123true"));
	}

	@Test
	public void UnderscoreLetterDigitUNION() {
		assertTrue(UnicodeTranslator.toUnicode("_abc123UNION").equals(
				"_abc123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("_abc123union").equals(
				"_abc123union"));
	}

	@Test
	public void UnderscoreLetterANYDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcANY123").equals(
				"_abcANY123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcany123").equals(
				"_abcany123"));
	}

	@Test
	public void UnderscoreLetterFALSEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcFALSE123").equals(
				"_abcFALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcfalse123").equals(
				"_abcfalse123"));
	}

	@Test
	public void UnderscoreLetterINTEGERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcINTEGER123").equals(
				"_abcINTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcinteger123").equals(
				"_abcinteger123"));
	}

	@Test
	public void UnderscoreLetterINTERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcINTER123").equals(
				"_abcINTER123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcinter123").equals(
				"_abcinter123"));
	}

	@Test
	public void UnderscoreLetterNATDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcNAT123").equals(
				"_abcNAT123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcnat123").equals(
				"_abcnat123"));
	}

	@Test
	public void UnderscoreLetterNAT1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcNAT1123").equals(
				"_abcNAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcnat1123").equals(
				"_abcnat1123"));
	}

	@Test
	public void UnderscoreLetterNATURALDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcNATURAL123").equals(
				"_abcNATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcnatural123").equals(
				"_abcnatural123"));
	}

	@Test
	public void UnderscoreLetterNOTDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcNOT123").equals(
				"_abcNOT123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcnot123").equals(
				"_abcnot123"));
	}

	@Test
	public void UnderscoreLetterORDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcOR123").equals("_abcOR123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcor123").equals("_abcor123"));
	}

	@Test
	public void UnderscoreLetterPOWDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcPOW123").equals(
				"_abcPOW123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcpow123").equals(
				"_abcpow123"));
	}

	@Test
	public void UnderscoreLetterPOW1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcPOW1123").equals(
				"_abcPOW1123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcpow1123").equals(
				"_abcpow1123"));
	}

	@Test
	public void UnderscoreLetterTRUEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcTRUE123").equals(
				"_abcTRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("_abctrue123").equals(
				"_abctrue123"));
	}

	@Test
	public void UnderscoreLetterUNIONDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_abcUNION123").equals(
				"_abcUNION123"));
		assertTrue(UnicodeTranslator.toUnicode("_abcunion123").equals(
				"_abcunion123"));
	}

	@Test
	public void UnderscoreDigitLetterANY() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcANY").equals(
				"_123abcANY"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcany").equals(
				"_123abcany"));
	}

	@Test
	public void UnderscoreDigitLetterFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcFALSE").equals(
				"_123abcFALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcfalse").equals(
				"_123abcfalse"));
	}

	@Test
	public void UnderscoreDigitLetterINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcINTEGER").equals(
				"_123abcINTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcinteger").equals(
				"_123abcinteger"));
	}

	@Test
	public void UnderscoreDigitLetterINTER() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcINTER").equals(
				"_123abcINTER"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcinter").equals(
				"_123abcinter"));
	}

	@Test
	public void UnderscoreDigitLetterNAT() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcNAT").equals(
				"_123abcNAT"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcnat").equals(
				"_123abcnat"));
	}

	@Test
	public void UnderscoreDigitLetterNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcNAT1").equals(
				"_123abcNAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcnat1").equals(
				"_123abcnat1"));
	}

	@Test
	public void UnderscoreDigitLetterNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcNATURAL").equals(
				"_123abcNATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcnatural").equals(
				"_123abcnatural"));
	}

	@Test
	public void UnderscoreDigitLetterNOT() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcNOT").equals(
				"_123abcNOT"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcnot").equals(
				"_123abcnot"));
	}

	@Test
	public void UnderscoreDigitLetterOR() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcOR").equals("_123abcOR"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcor").equals("_123abcor"));
	}

	@Test
	public void UnderscoreDigitLetterPOW() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcPOW").equals(
				"_123abcPOW"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcpow").equals(
				"_123abcpow"));
	}

	@Test
	public void UnderscoreDigitLetterPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcPOW1").equals(
				"_123abcPOW1"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcpow1").equals(
				"_123abcpow1"));
	}

	@Test
	public void UnderscoreDigitLetterTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcTRUE").equals(
				"_123abcTRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_123abctrue").equals(
				"_123abctrue"));
	}

	@Test
	public void UnderscoreDigitLetterUNION() {
		assertTrue(UnicodeTranslator.toUnicode("_123abcUNION").equals(
				"_123abcUNION"));
		assertTrue(UnicodeTranslator.toUnicode("_123abcunion").equals(
				"_123abcunion"));
	}

	@Test
	public void UnderscoreDigitANYLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123ANYabc").equals(
				"_123ANYabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123anyabc").equals(
				"_123anyabc"));
	}

	@Test
	public void UnderscoreDigitFALSELetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123FALSEabc").equals(
				"_123FALSEabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123falseabc").equals(
				"_123falseabc"));
	}

	@Test
	public void UnderscoreDigitINTEGERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTEGERabc").equals(
				"_123INTEGERabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123integerabc").equals(
				"_123integerabc"));
	}

	@Test
	public void UnderscoreDigitINTERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123INTERabc").equals(
				"_123INTERabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123interabc").equals(
				"_123interabc"));
	}

	@Test
	public void UnderscoreDigitNATLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123NATabc").equals(
				"_123NATabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123natabc").equals(
				"_123natabc"));
	}

	@Test
	public void UnderscoreDigitNAT1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("_123NAT1abc").equals(
				"_123NAT1abc"));
		assertTrue(UnicodeTranslator.toUnicode("_123nat1abc").equals(
				"_123nat1abc"));
	}

	@Test
	public void UnderscoreDigitNATURALLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123NATURALabc").equals(
				"_123NATURALabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123naturalabc").equals(
				"_123naturalabc"));
	}

	@Test
	public void UnderscoreDigitNOTLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123NOTabc").equals(
				"_123NOTabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123notabc").equals(
				"_123notabc"));
	}

	@Test
	public void UnderscoreDigitORLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123ORabc").equals("_123ORabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123orabc").equals("_123orabc"));
	}

	@Test
	public void UnderscoreDigitPOWLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123POWabc").equals(
				"_123POWabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123powabc").equals(
				"_123powabc"));
	}

	@Test
	public void UnderscoreDigitPOW1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("_123POW1abc").equals(
				"_123POW1abc"));
		assertTrue(UnicodeTranslator.toUnicode("_123pow1abc").equals(
				"_123pow1abc"));
	}

	@Test
	public void UnderscoreDigitTRUELetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123TRUEabc").equals(
				"_123TRUEabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123trueabc").equals(
				"_123trueabc"));
	}

	@Test
	public void UnderscoreDigitUNIONLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_123UNIONabc").equals(
				"_123UNIONabc"));
		assertTrue(UnicodeTranslator.toUnicode("_123unionabc").equals(
				"_123unionabc"));
	}

	@Test
	public void UnderscoreANYLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_ANYabc123").equals(
				"_ANYabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_anyabc123").equals(
				"_anyabc123"));
	}

	@Test
	public void UnderscoreFALSELetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSEabc123").equals(
				"_FALSEabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_falseabc123").equals(
				"_falseabc123"));
	}

	@Test
	public void UnderscoreINTEGERLetterDigit() {
		String expected = "_INTEGERabc123";
		String actual1 = UnicodeTranslator.toUnicode("_INTEGERabc123");
		assertEquals(expected, actual1);

		String expected2 = "_integerabc123";
		String actual2 = UnicodeTranslator.toUnicode("_integerabc123");
		assertEquals(expected2, actual2);

	}

	@Test
	public void UnderscoreINTERLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_INTERabc123").equals(
				"_INTERabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_interabc123").equals(
				"_interabc123"));
	}

	@Test
	public void UnderscoreNATLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NATabc123").equals(
				"_NATabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_natabc123").equals(
				"_natabc123"));
	}

	@Test
	public void UnderscoreNAT1LetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1abc123").equals(
				"_NAT1abc123"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1abc123").equals(
				"_nat1abc123"));
	}

	@Test
	public void UnderscoreNATURALLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURALabc123").equals(
				"_NATURALabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_naturalabc123").equals(
				"_naturalabc123"));
	}

	@Test
	public void UnderscoreNOTLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_NOTabc123").equals(
				"_NOTabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_notabc123").equals(
				"_notabc123"));
	}

	@Test
	public void UnderscoreORLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_ORabc123").equals("_ORabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_orabc123").equals("_orabc123"));
	}

	@Test
	public void UnderscorePOWLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_POWabc123").equals(
				"_POWabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_powabc123").equals(
				"_powabc123"));
	}

	@Test
	public void UnderscorePOW1LetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1abc123").equals(
				"_POW1abc123"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1abc123").equals(
				"_pow1abc123"));
	}

	@Test
	public void UnderscoreTRUELetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUEabc123").equals(
				"_TRUEabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_trueabc123").equals(
				"_trueabc123"));
	}

	@Test
	public void UnderscoreUNIONLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("_UNIONabc123").equals(
				"_UNIONabc123"));
		assertTrue(UnicodeTranslator.toUnicode("_unionabc123").equals(
				"_unionabc123"));
	}

	@Test
	public void UnderscoreANYDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_ANY123abc").equals(
				"_ANY123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_any123abc").equals(
				"_any123abc"));
	}

	@Test
	public void UnderscoreFALSEDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSE123abc").equals(
				"_FALSE123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_false123abc").equals(
				"_false123abc"));
	}

	@Test
	public void UnderscoreINTEGERDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_INTEGER123abc").equals(
				"_INTEGER123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_integer123abc").equals(
				"_integer123abc"));
	}

	@Test
	public void UnderscoreINTERDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_INTER123abc").equals(
				"_INTER123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_inter123abc").equals(
				"_inter123abc"));
	}

	@Test
	public void UnderscoreNATDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT123abc").equals(
				"_NAT123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_nat123abc").equals(
				"_nat123abc"));
	}

	@Test
	public void UnderscoreNAT1DigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1123abc").equals(
				"_NAT1123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1123abc").equals(
				"_nat1123abc"));
	}

	@Test
	public void UnderscoreNATURALDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURAL123abc").equals(
				"_NATURAL123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_natural123abc").equals(
				"_natural123abc"));
	}

	@Test
	public void UnderscoreNOTDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_NOT123abc").equals(
				"_NOT123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_not123abc").equals(
				"_not123abc"));
	}

	@Test
	public void UnderscoreORDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_OR123abc").equals("_OR123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_or123abc").equals("_or123abc"));
	}

	@Test
	public void UnderscorePOWDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_POW123abc").equals(
				"_POW123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_pow123abc").equals(
				"_pow123abc"));
	}

	@Test
	public void UnderscorePOW1DigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1123abc").equals(
				"_POW1123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1123abc").equals(
				"_pow1123abc"));
	}

	@Test
	public void UnderscoreTRUEDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUE123abc").equals(
				"_TRUE123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_true123abc").equals(
				"_true123abc"));
	}

	@Test
	public void UnderscoreUNIONDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("_UNION123abc").equals(
				"_UNION123abc"));
		assertTrue(UnicodeTranslator.toUnicode("_union123abc").equals(
				"_union123abc"));
	}

	// @Test
	// public void Keyword()
	// {
	// // check, if the keywords have to be translated or not
	// assertTrue(UnicodeTranslator.toUnicode("ANY").equals("ANY"));
	// assertFalse(UnicodeTranslator.toUnicode("ANY").equals("ANY"));
	// }

	@Test
	public void ANYLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ANYabc").equals("ANYabc"));
		assertTrue(UnicodeTranslator.toUnicode("anyabc").equals("anyabc"));
	}

	@Test
	public void FALSELetter() {
		assertTrue(UnicodeTranslator.toUnicode("FALSEabc").equals("FALSEabc"));
		assertTrue(UnicodeTranslator.toUnicode("falseabc").equals("falseabc"));
	}

	@Test
	public void INTEGERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGERabc").equals(
				"INTEGERabc"));
		assertTrue(UnicodeTranslator.toUnicode("integerabc").equals(
				"integerabc"));
	}

	@Test
	public void INTERLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTERabc").equals("INTERabc"));
		assertTrue(UnicodeTranslator.toUnicode("interabc").equals("interabc"));
	}

	@Test
	public void NATLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATabc").equals("NATabc"));
		assertTrue(UnicodeTranslator.toUnicode("natabc").equals("natabc"));
	}

	@Test
	public void NAT1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1abc").equals("NAT1abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat1abc").equals("nat1abc"));
	}

	@Test
	public void NATURALLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATURALabc").equals(
				"NATURALabc"));
		assertTrue(UnicodeTranslator.toUnicode("naturalabc").equals(
				"naturalabc"));
	}

	@Test
	public void NOTLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NOTabc").equals("NOTabc"));
		assertTrue(UnicodeTranslator.toUnicode("notabc").equals("notabc"));
	}

	@Test
	public void ORLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ORabc").equals("ORabc"));
		assertTrue(UnicodeTranslator.toUnicode("orabc").equals("orabc"));
	}

	@Test
	public void POWLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POWabc").equals("POWabc"));
		assertTrue(UnicodeTranslator.toUnicode("powabc").equals("powabc"));
	}

	@Test
	public void POW1Letter() {
		assertTrue(UnicodeTranslator.toUnicode("POW1abc").equals("POW1abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow1abc").equals("pow1abc"));
	}

	@Test
	public void TRUELetter() {
		assertTrue(UnicodeTranslator.toUnicode("TRUEabc").equals("TRUEabc"));
		assertTrue(UnicodeTranslator.toUnicode("trueabc").equals("trueabc"));
	}

	@Test
	public void UNIONLetter() {
		assertTrue(UnicodeTranslator.toUnicode("UNIONabc").equals("UNIONabc"));
		assertTrue(UnicodeTranslator.toUnicode("unionabc").equals("unionabc"));
	}

	@Test
	public void ANYDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ANY123").equals("ANY123"));
		assertTrue(UnicodeTranslator.toUnicode("any123").equals("any123"));
	}

	@Test
	public void FALSEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE123").equals("FALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("false123").equals("false123"));
	}

	@Test
	public void INTEGERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123").equals(
				"INTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("integer123").equals(
				"integer123"));
	}

	@Test
	public void INTERDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTER123").equals("INTER123"));
		assertTrue(UnicodeTranslator.toUnicode("inter123").equals("inter123"));
	}

	@Test
	public void NATDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT123").equals("NAT123"));
		assertTrue(UnicodeTranslator.toUnicode("nat123").equals("nat123"));
	}

	@Test
	public void NAT1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1123").equals("NAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123").equals("nat1123"));
	}

	@Test
	public void NATURALDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123").equals(
				"NATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("natural123").equals(
				"natural123"));
	}

	@Test
	public void NOTDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NOT123").equals("NOT123"));
		assertTrue(UnicodeTranslator.toUnicode("not123").equals("not123"));
	}

	@Test
	public void ORDigit() {
		assertTrue(UnicodeTranslator.toUnicode("OR123").equals("OR123"));
		assertTrue(UnicodeTranslator.toUnicode("or123").equals("or123"));
	}

	@Test
	public void POWDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW123").equals("POW123"));
		assertTrue(UnicodeTranslator.toUnicode("pow123").equals("pow123"));
	}

	@Test
	public void POW1Digit() {
		assertTrue(UnicodeTranslator.toUnicode("POW1123").equals("POW1123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123").equals("pow1123"));
	}

	@Test
	public void TRUEDigit() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE123").equals("TRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("true123").equals("true123"));
	}

	@Test
	public void UNIONDigit() {
		assertTrue(UnicodeTranslator.toUnicode("UNION123").equals("UNION123"));
		assertTrue(UnicodeTranslator.toUnicode("union123").equals("union123"));
	}

	@Test
	public void ANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ANY_").equals("ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("any_").equals("any_"));
	}

	@Test
	public void FALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE_").equals("FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("false_").equals("false_"));
	}

	@Test
	public void INTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_").equals("INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("integer_").equals("integer_"));
	}

	@Test
	public void INTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTER_").equals("INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("inter_").equals("inter_"));
	}

	@Test
	public void NATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT_").equals("NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("nat_").equals("nat_"));
	}

	@Test
	public void NAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1_").equals("NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_").equals("nat1_"));
	}

	@Test
	public void NATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_").equals("NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("natural_").equals("natural_"));
	}

	@Test
	public void NOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NOT_").equals("NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("not_").equals("not_"));
	}

	@Test
	public void ORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("OR_").equals("OR_"));
		assertTrue(UnicodeTranslator.toUnicode("or_").equals("or_"));
	}

	@Test
	public void POWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW_").equals("POW_"));
		assertTrue(UnicodeTranslator.toUnicode("pow_").equals("pow_"));
	}

	@Test
	public void POW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW1_").equals("POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_").equals("pow1_"));
	}

	@Test
	public void TRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE_").equals("TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("true_").equals("true_"));
	}

	@Test
	public void UNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("UNION_").equals("UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("union_").equals("union_"));
	}

	@Test
	public void ANYLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ANYabc123").equals("ANYabc123"));
		assertTrue(UnicodeTranslator.toUnicode("anyabc123").equals("anyabc123"));
	}

	@Test
	public void FALSELetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("FALSEabc123").equals(
				"FALSEabc123"));
		assertTrue(UnicodeTranslator.toUnicode("falseabc123").equals(
				"falseabc123"));
	}

	@Test
	public void INTEGERLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGERabc123").equals(
				"INTEGERabc123"));
		assertTrue(UnicodeTranslator.toUnicode("integerabc123").equals(
				"integerabc123"));
	}

	@Test
	public void INTERLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTERabc123").equals(
				"INTERabc123"));
		assertTrue(UnicodeTranslator.toUnicode("interabc123").equals(
				"interabc123"));
	}

	@Test
	public void NATLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATabc123").equals("NATabc123"));
		assertTrue(UnicodeTranslator.toUnicode("natabc123").equals("natabc123"));
	}

	@Test
	public void NAT1LetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1abc123").equals(
				"NAT1abc123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1abc123").equals(
				"nat1abc123"));
	}

	@Test
	public void NATURALLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATURALabc123").equals(
				"NATURALabc123"));
		assertTrue(UnicodeTranslator.toUnicode("naturalabc123").equals(
				"naturalabc123"));
	}

	@Test
	public void NOTLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NOTabc123").equals("NOTabc123"));
		assertTrue(UnicodeTranslator.toUnicode("notabc123").equals("notabc123"));
	}

	@Test
	public void ORLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ORabc123").equals("ORabc123"));
		assertTrue(UnicodeTranslator.toUnicode("orabc123").equals("orabc123"));
	}

	@Test
	public void POWLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POWabc123").equals("POWabc123"));
		assertTrue(UnicodeTranslator.toUnicode("powabc123").equals("powabc123"));
	}

	@Test
	public void POW1LetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW1abc123").equals(
				"POW1abc123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1abc123").equals(
				"pow1abc123"));
	}

	@Test
	public void TRUELetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("TRUEabc123").equals(
				"TRUEabc123"));
		assertTrue(UnicodeTranslator.toUnicode("trueabc123").equals(
				"trueabc123"));
	}

	@Test
	public void UNIONLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("UNIONabc123").equals(
				"UNIONabc123"));
		assertTrue(UnicodeTranslator.toUnicode("unionabc123").equals(
				"unionabc123"));
	}

	@Test
	public void ANYLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ANYabc_").equals("ANYabc_"));
		assertTrue(UnicodeTranslator.toUnicode("anyabc_").equals("anyabc_"));
	}

	@Test
	public void FALSELetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("FALSEabc_").equals("FALSEabc_"));
		assertTrue(UnicodeTranslator.toUnicode("falseabc_").equals("falseabc_"));
	}

	@Test
	public void INTEGERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGERabc_").equals(
				"INTEGERabc_"));
		assertTrue(UnicodeTranslator.toUnicode("integerabc_").equals(
				"integerabc_"));
	}

	@Test
	public void INTERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTERabc_").equals("INTERabc_"));
		assertTrue(UnicodeTranslator.toUnicode("interabc_").equals("interabc_"));
	}

	@Test
	public void NATLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATabc_").equals("NATabc_"));
		assertTrue(UnicodeTranslator.toUnicode("natabc_").equals("natabc_"));
	}

	@Test
	public void NAT1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1abc_").equals("NAT1abc_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1abc_").equals("nat1abc_"));
	}

	@Test
	public void NATURALLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATURALabc_").equals(
				"NATURALabc_"));
		assertTrue(UnicodeTranslator.toUnicode("naturalabc_").equals(
				"naturalabc_"));
	}

	@Test
	public void NOTLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NOTabc_").equals("NOTabc_"));
		assertTrue(UnicodeTranslator.toUnicode("notabc_").equals("notabc_"));
	}

	@Test
	public void ORLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ORabc_").equals("ORabc_"));
		assertTrue(UnicodeTranslator.toUnicode("orabc_").equals("orabc_"));
	}

	@Test
	public void POWLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POWabc_").equals("POWabc_"));
		assertTrue(UnicodeTranslator.toUnicode("powabc_").equals("powabc_"));
	}

	@Test
	public void POW1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW1abc_").equals("POW1abc_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1abc_").equals("pow1abc_"));
	}

	@Test
	public void TRUELetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("TRUEabc_").equals("TRUEabc_"));
		assertTrue(UnicodeTranslator.toUnicode("trueabc_").equals("trueabc_"));
	}

	@Test
	public void UNIONLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("UNIONabc_").equals("UNIONabc_"));
		assertTrue(UnicodeTranslator.toUnicode("unionabc_").equals("unionabc_"));
	}

	@Test
	public void ANYDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ANY123abc").equals("ANY123abc"));
		assertTrue(UnicodeTranslator.toUnicode("any123abc").equals("any123abc"));
	}

	@Test
	public void FALSEDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE123abc").equals(
				"FALSE123abc"));
		assertTrue(UnicodeTranslator.toUnicode("false123abc").equals(
				"false123abc"));
	}

	@Test
	public void INTEGERDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123abc").equals(
				"INTEGER123abc"));
		assertTrue(UnicodeTranslator.toUnicode("integer123abc").equals(
				"integer123abc"));
	}

	@Test
	public void INTERDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTER123abc").equals(
				"INTER123abc"));
		assertTrue(UnicodeTranslator.toUnicode("inter123abc").equals(
				"inter123abc"));
	}

	@Test
	public void NATDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT123abc").equals("NAT123abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat123abc").equals("nat123abc"));
	}

	@Test
	public void NAT1DigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1123abc").equals(
				"NAT1123abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123abc").equals(
				"nat1123abc"));
	}

	@Test
	public void NATURALDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123abc").equals(
				"NATURAL123abc"));
		assertTrue(UnicodeTranslator.toUnicode("natural123abc").equals(
				"natural123abc"));
	}

	@Test
	public void NOTDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("not123abc").equals("not123abc"));
		assertTrue(UnicodeTranslator.toUnicode("NOT123abc").equals("NOT123abc"));
	}

	@Test
	public void ORDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("or123abc").equals("or123abc"));
		assertTrue(UnicodeTranslator.toUnicode("OR123abc").equals("OR123abc"));
	}

	@Test
	public void POWDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW123abc").equals("POW123abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow123abc").equals("pow123abc"));
	}

	@Test
	public void POW1DigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW1123abc").equals(
				"POW1123abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123abc").equals(
				"pow1123abc"));
	}

	@Test
	public void TRUEDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE123abc").equals(
				"TRUE123abc"));
		assertTrue(UnicodeTranslator.toUnicode("true123abc").equals(
				"true123abc"));
	}

	@Test
	public void UNIONDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("UNION123abc").equals(
				"UNION123abc"));
		assertTrue(UnicodeTranslator.toUnicode("union123abc").equals(
				"union123abc"));
	}

	@Test
	public void ANYDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ANY123_").equals("ANY123_"));
		assertTrue(UnicodeTranslator.toUnicode("any123_").equals("any123_"));
	}

	@Test
	public void FALSEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE123_").equals("FALSE123_"));
		assertTrue(UnicodeTranslator.toUnicode("false123_").equals("false123_"));
	}

	@Test
	public void INTEGERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123_").equals(
				"INTEGER123_"));
		assertTrue(UnicodeTranslator.toUnicode("integer123_").equals(
				"integer123_"));
	}

	@Test
	public void INTERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTER123_").equals("INTER123_"));
		assertTrue(UnicodeTranslator.toUnicode("inter123_").equals("inter123_"));
	}

	@Test
	public void NATDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT123_").equals("NAT123_"));
		assertTrue(UnicodeTranslator.toUnicode("nat123_").equals("nat123_"));
	}

	@Test
	public void NAT1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1123_").equals("NAT1123_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123_").equals("nat1123_"));
	}

	@Test
	public void NATURALDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123_").equals(
				"NATURAL123_"));
		assertTrue(UnicodeTranslator.toUnicode("natural123_").equals(
				"natural123_"));
	}

	@Test
	public void NOTDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NOT123_").equals("NOT123_"));
		assertTrue(UnicodeTranslator.toUnicode("not123_").equals("not123_"));
	}

	@Test
	public void ORDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("OR123_").equals("OR123_"));
		assertTrue(UnicodeTranslator.toUnicode("or123_").equals("or123_"));
	}

	@Test
	public void POWDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW123_").equals("POW123_"));
		assertTrue(UnicodeTranslator.toUnicode("pow123_").equals("pow123_"));
	}

	@Test
	public void POW1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW1123_").equals("POW1123_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123_").equals("pow1123_"));
	}

	@Test
	public void TRUEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE123_").equals("TRUE123_"));
		assertTrue(UnicodeTranslator.toUnicode("true123_").equals("true123_"));
	}

	@Test
	public void UNIONDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("UNION123_").equals("UNION123_"));
		assertTrue(UnicodeTranslator.toUnicode("union123_").equals("union123_"));
	}

	@Test
	public void ANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ANY_abc").equals("ANY_abc"));
		assertTrue(UnicodeTranslator.toUnicode("any_abc").equals("any_abc"));
	}

	@Test
	public void FALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE_abc").equals("FALSE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("false_abc").equals("false_abc"));
	}

	@Test
	public void INTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_abc").equals(
				"INTEGER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("integer_abc").equals(
				"integer_abc"));
	}

	@Test
	public void INTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTER_abc").equals("INTER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("inter_abc").equals("inter_abc"));
	}

	@Test
	public void NATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT_abc").equals("NAT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat_abc").equals("nat_abc"));
	}

	@Test
	public void NAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1_abc").equals("NAT1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_abc").equals("nat1_abc"));
	}

	@Test
	public void NATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_abc").equals(
				"NATURAL_abc"));
		assertTrue(UnicodeTranslator.toUnicode("natural_abc").equals(
				"natural_abc"));
	}

	@Test
	public void NOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NOT_abc").equals("NOT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("not_abc").equals("not_abc"));
	}

	@Test
	public void ORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("OR_abc").equals("OR_abc"));
		assertTrue(UnicodeTranslator.toUnicode("or_abc").equals("or_abc"));
	}

	@Test
	public void POWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW_abc").equals("POW_abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow_abc").equals("pow_abc"));
	}

	@Test
	public void POW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW1_abc").equals("POW1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_abc").equals("pow1_abc"));
	}

	@Test
	public void TRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE_abc").equals("TRUE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("true_abc").equals("true_abc"));
	}

	@Test
	public void UNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("UNION_abc").equals("UNION_abc"));
		assertTrue(UnicodeTranslator.toUnicode("union_abc").equals("union_abc"));
	}

	@Test
	public void ANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ANY_123").equals("ANY_123"));
		assertTrue(UnicodeTranslator.toUnicode("any_123").equals("any_123"));
	}

	@Test
	public void FALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE_123").equals("FALSE_123"));
		assertTrue(UnicodeTranslator.toUnicode("false_123").equals("false_123"));
	}

	@Test
	public void INTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_123").equals(
				"INTEGER_123"));
		assertTrue(UnicodeTranslator.toUnicode("integer_123").equals(
				"integer_123"));
	}

	@Test
	public void INTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTER_123").equals("INTER_123"));
		assertTrue(UnicodeTranslator.toUnicode("inter_123").equals("inter_123"));
	}

	@Test
	public void NATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT_123").equals("NAT_123"));
		assertTrue(UnicodeTranslator.toUnicode("nat_123").equals("nat_123"));
	}

	@Test
	public void NAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1_123").equals("NAT1_123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_123").equals("nat1_123"));
	}

	@Test
	public void NATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_123").equals(
				"NATURAL_123"));
		assertTrue(UnicodeTranslator.toUnicode("natural_123").equals(
				"natural_123"));
	}

	@Test
	public void NOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NOT_123").equals("NOT_123"));
		assertTrue(UnicodeTranslator.toUnicode("not_123").equals("not_123"));
	}

	@Test
	public void ORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("OR_123").equals("OR_123"));
		assertTrue(UnicodeTranslator.toUnicode("or_123").equals("or_123"));
	}

	@Test
	public void POWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW_123").equals("POW_123"));
		assertTrue(UnicodeTranslator.toUnicode("pow_123").equals("pow_123"));
	}

	@Test
	public void POW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW1_123").equals("POW1_123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_123").equals("pow1_123"));
	}

	@Test
	public void TRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE_123").equals("TRUE_123"));
		assertTrue(UnicodeTranslator.toUnicode("true_123").equals("true_123"));
	}

	@Test
	public void UNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("UNION_123").equals("UNION_123"));
		assertTrue(UnicodeTranslator.toUnicode("union_123").equals("union_123"));
	}

	@Test
	public void ANYLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ANYabc123_").equals(
				"ANYabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("anyabc123_").equals(
				"anyabc123_"));
	}

	@Test
	public void FALSELetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("FALSEabc123_").equals(
				"FALSEabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("falseabc123_").equals(
				"falseabc123_"));
	}

	@Test
	public void INTEGERLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGERabc123_").equals(
				"INTEGERabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("integerabc123_").equals(
				"integerabc123_"));
	}

	@Test
	public void INTERLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTERabc123_").equals(
				"INTERabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("interabc123_").equals(
				"interabc123_"));
	}

	@Test
	public void NATLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATabc123_").equals(
				"NATabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("natabc123_").equals(
				"natabc123_"));
	}

	@Test
	public void NAT1LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1abc123_").equals(
				"NAT1abc123_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1abc123_").equals(
				"nat1abc123_"));
	}

	@Test
	public void NATURALLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATURALabc123_").equals(
				"NATURALabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("naturalabc123_").equals(
				"naturalabc123_"));
	}

	@Test
	public void NOTLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NOTabc123_").equals(
				"NOTabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("notabc123_").equals(
				"notabc123_"));
	}

	@Test
	public void ORLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ORabc123_").equals("ORabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("orabc123_").equals("orabc123_"));
	}

	@Test
	public void POWLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POWabc123_").equals(
				"POWabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("powabc123_").equals(
				"powabc123_"));
	}

	@Test
	public void POW1LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW1abc123_").equals(
				"POW1abc123_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1abc123_").equals(
				"pow1abc123_"));
	}

	@Test
	public void TRUELetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("TRUEabc123_").equals(
				"TRUEabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("trueabc123_").equals(
				"trueabc123_"));
	}

	@Test
	public void UNIONLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("UNIONabc123_").equals(
				"UNIONabc123_"));
		assertTrue(UnicodeTranslator.toUnicode("unionabc123_").equals(
				"unionabc123_"));
	}

	@Test
	public void ANYLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ANYabc_123").equals(
				"ANYabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("anyabc_123").equals(
				"anyabc_123"));
	}

	@Test
	public void FALSELetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("FALSEabc_123").equals(
				"FALSEabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("falseabc_123").equals(
				"falseabc_123"));
	}

	@Test
	public void INTEGERLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGERabc_123").equals(
				"INTEGERabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("integerabc_123").equals(
				"integerabc_123"));
	}

	@Test
	public void INTERLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTERabc_123").equals(
				"INTERabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("interabc_123").equals(
				"interabc_123"));
	}

	@Test
	public void NATLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATabc_123").equals(
				"NATabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("natabc_123").equals(
				"natabc_123"));
	}

	@Test
	public void NAT1LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1abc_123").equals(
				"NAT1abc_123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1abc_123").equals(
				"nat1abc_123"));
	}

	@Test
	public void NATURALLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATURALabc_123").equals(
				"NATURALabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("naturalabc_123").equals(
				"naturalabc_123"));
	}

	@Test
	public void NOTLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NOTabc_123").equals(
				"NOTabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("notabc_123").equals(
				"notabc_123"));
	}

	@Test
	public void ORLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ORabc_123").equals("ORabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("orabc_123").equals("orabc_123"));
	}

	@Test
	public void POWLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POWabc_123").equals(
				"POWabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("powabc_123").equals(
				"powabc_123"));
	}

	@Test
	public void POW1LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW1abc_123").equals(
				"POW1abc_123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1abc_123").equals(
				"pow1abc_123"));
	}

	@Test
	public void TRUELetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("TRUEabc_123").equals(
				"TRUEabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("trueabc_123").equals(
				"trueabc_123"));
	}

	@Test
	public void UNIONLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("UNIONabc_123").equals(
				"UNIONabc_123"));
		assertTrue(UnicodeTranslator.toUnicode("unionabc_123").equals(
				"unionabc_123"));
	}

	@Test
	public void ANYDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("ANY123abc_").equals(
				"ANY123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("any123abc_").equals(
				"any123abc_"));
	}

	@Test
	public void FALSEDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE123abc_").equals(
				"FALSE123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("false123abc_").equals(
				"false123abc_"));
	}

	@Test
	public void INTEGERDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123abc_").equals(
				"INTEGER123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("integer123abc_").equals(
				"integer123abc_"));
	}

	@Test
	public void INTERDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("INTER123abc_").equals(
				"INTER123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("inter123abc_").equals(
				"inter123abc_"));
	}

	@Test
	public void NATDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT123abc_").equals(
				"NAT123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("nat123abc_").equals(
				"nat123abc_"));
	}

	@Test
	public void NAT1DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1123abc_").equals(
				"NAT1123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123abc_").equals(
				"nat1123abc_"));
	}

	@Test
	public void NATURALDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123abc_").equals(
				"NATURAL123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("natural123abc_").equals(
				"natural123abc_"));
	}

	@Test
	public void NOTDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("NOT123abc_").equals(
				"NOT123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("not123abc_").equals(
				"not123abc_"));
	}

	@Test
	public void ORDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("OR123abc_").equals("OR123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("or123abc_").equals("or123abc_"));
	}

	@Test
	public void POWDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW123abc_").equals(
				"POW123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("pow123abc_").equals(
				"pow123abc_"));
	}

	@Test
	public void POW1DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("POW1123abc_").equals(
				"POW1123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123abc_").equals(
				"pow1123abc_"));
	}

	@Test
	public void TRUEDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE123abc_").equals(
				"TRUE123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("true123abc_").equals(
				"true123abc_"));
	}

	@Test
	public void UNIONDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("UNION123abc_").equals(
				"UNION123abc_"));
		assertTrue(UnicodeTranslator.toUnicode("union123abc_").equals(
				"union123abc_"));
	}

	@Test
	public void ANYDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ANY123_abc").equals(
				"ANY123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("any123_abc").equals(
				"any123_abc"));
	}

	@Test
	public void FALSEDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE123_abc").equals(
				"FALSE123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("false123_abc").equals(
				"false123_abc"));
	}

	@Test
	public void INTEGERDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123_abc").equals(
				"INTEGER123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("integer123_abc").equals(
				"integer123_abc"));
	}

	@Test
	public void INTERDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTER123_abc").equals(
				"INTER123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("inter123_abc").equals(
				"inter123_abc"));
	}

	@Test
	public void NATDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT123_abc").equals(
				"NAT123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat123_abc").equals(
				"nat123_abc"));
	}

	@Test
	public void NAT1DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1123_abc").equals(
				"NAT1123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123_abc").equals(
				"nat1123_abc"));
	}

	@Test
	public void NATURALDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123_abc").equals(
				"NATURAL123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("natural123_abc").equals(
				"natural123_abc"));
	}

	@Test
	public void NOTDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NOT123_abc").equals(
				"NOT123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("not123_abc").equals(
				"not123_abc"));
	}

	@Test
	public void ORDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("OR123_abc").equals("OR123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("or123_abc").equals("or123_abc"));
	}

	@Test
	public void POWDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW123_abc").equals(
				"POW123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow123_abc").equals(
				"pow123_abc"));
	}

	@Test
	public void POW1DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW1123_abc").equals(
				"POW1123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123_abc").equals(
				"pow1123_abc"));
	}

	@Test
	public void TRUEDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE123_abc").equals(
				"TRUE123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("true123_abc").equals(
				"true123_abc"));
	}

	@Test
	public void UNIONDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("UNION123_abc").equals(
				"UNION123_abc"));
		assertTrue(UnicodeTranslator.toUnicode("union123_abc").equals(
				"union123_abc"));
	}

	@Test
	public void ANYUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("ANY_abc123").equals(
				"ANY_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("any_abc123").equals(
				"any_abc123"));
	}

	@Test
	public void FALSEUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE_abc123").equals(
				"FALSE_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("false_abc123").equals(
				"false_abc123"));
	}

	@Test
	public void INTEGERUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_abc123").equals(
				"INTEGER_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("integer_abc123").equals(
				"integer_abc123"));
	}

	@Test
	public void INTERUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("INTER_abc123").equals(
				"INTER_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("inter_abc123").equals(
				"inter_abc123"));
	}

	@Test
	public void NATUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT_abc123").equals(
				"NAT_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("nat_abc123").equals(
				"nat_abc123"));
	}

	@Test
	public void NAT1UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1_abc123").equals(
				"NAT1_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_abc123").equals(
				"nat1_abc123"));
	}

	@Test
	public void NATURALUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_abc123").equals(
				"NATURAL_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("natural_abc123").equals(
				"natural_abc123"));
	}

	@Test
	public void NOTUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("NOT_abc123").equals(
				"NOT_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("not_abc123").equals(
				"not_abc123"));
	}

	@Test
	public void ORUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("OR_abc123").equals("OR_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("or_abc123").equals("or_abc123"));
	}

	@Test
	public void POWUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW_abc123").equals(
				"POW_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("pow_abc123").equals(
				"pow_abc123"));
	}

	@Test
	public void POW1UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("POW1_abc123").equals(
				"POW1_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_abc123").equals(
				"pow1_abc123"));
	}

	@Test
	public void TRUEUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE_abc123").equals(
				"TRUE_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("true_abc123").equals(
				"true_abc123"));
	}

	@Test
	public void UNIONUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toUnicode("UNION_abc123").equals(
				"UNION_abc123"));
		assertTrue(UnicodeTranslator.toUnicode("union_abc123").equals(
				"union_abc123"));
	}

	@Test
	public void ANYUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("ANY_123abc").equals(
				"ANY_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("any_123abc").equals(
				"any_123abc"));
	}

	@Test
	public void FALSEUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("FALSE_123abc").equals(
				"FALSE_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("false_123abc").equals(
				"false_123abc"));
	}

	@Test
	public void INTEGERUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_123abc").equals(
				"INTEGER_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("integer_123abc").equals(
				"integer_123abc"));
	}

	@Test
	public void INTERUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("INTER_123abc").equals(
				"INTER_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("inter_123abc").equals(
				"inter_123abc"));
	}

	@Test
	public void NATUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT_123abc").equals(
				"NAT_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat_123abc").equals(
				"nat_123abc"));
	}

	@Test
	public void NAT1UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NAT1_123abc").equals(
				"NAT1_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_123abc").equals(
				"nat1_123abc"));
	}

	@Test
	public void NATURALUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_123abc").equals(
				"NATURAL_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("natural_123abc").equals(
				"natural_123abc"));
	}

	@Test
	public void NOTUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("NOT_123abc").equals(
				"NOT_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("not_123abc").equals(
				"not_123abc"));
	}

	@Test
	public void ORUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("OR_123abc").equals("OR_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("or_123abc").equals("or_123abc"));
	}

	@Test
	public void POWUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW_123abc").equals(
				"POW_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow_123abc").equals(
				"pow_123abc"));
	}

	@Test
	public void POW1UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("POW1_123abc").equals(
				"POW1_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_123abc").equals(
				"pow1_123abc"));
	}

	@Test
	public void TRUEUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("TRUE_123abc").equals(
				"TRUE_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("true_123abc").equals(
				"true_123abc"));
	}

	@Test
	public void UNIONUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toUnicode("UNION_123abc").equals(
				"UNION_123abc"));
		assertTrue(UnicodeTranslator.toUnicode("union_123abc").equals(
				"union_123abc"));
	}

	@Test
	public void UnderscoreDigitUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_123_").equals("_123_"));
	}

	@Test
	public void UnderscoreLetterUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_abc_").equals("_abc_"));
	}

	@Test
	public void UnderscoreANYUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_ANY_").equals("_ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("_any_").equals("_any_"));
	}

	@Test
	public void UnderscoreFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_FALSE_").equals("_FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("_false_").equals("_false_"));
	}

	@Test
	public void UnderscoreINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_INTEGER_").equals("_INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("_integer_").equals("_integer_"));
	}

	@Test
	public void UnderscoreINTERUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_INTER_").equals("_INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("_inter_").equals("_inter_"));
	}

	@Test
	public void UnderscoreNATUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT_").equals("_NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("_nat_").equals("_nat_"));
	}

	@Test
	public void UnderscoreNAT1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("_NAT1_").equals("_NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1_").equals("_nat1_"));
	}

	@Test
	public void UnderscoreNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_NATURAL_").equals("_NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("_natural_").equals("_natural_"));
	}

	@Test
	public void UnderscoreNOTUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_NOT_").equals("_NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("_not_").equals("_not_"));
	}

	@Test
	public void UnderscoreORUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_OR_").equals("_OR_"));
		assertTrue(UnicodeTranslator.toUnicode("_or_").equals("_or_"));
	}

	@Test
	public void UnderscorePOWUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_POW_").equals("_POW_"));
		assertTrue(UnicodeTranslator.toUnicode("_pow_").equals("_pow_"));
	}

	@Test
	public void UnderscorePOW1Underscore() {
		assertTrue(UnicodeTranslator.toUnicode("_POW1_").equals("_POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1_").equals("_pow1_"));
	}

	@Test
	public void UnderscoreTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_TRUE_").equals("_TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("_true_").equals("_true_"));
	}

	@Test
	public void UnderscoreUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toUnicode("_UNION_").equals("_UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("_union_").equals("_union_"));
	}

	@Test
	public void LetterUnderscoreDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_123_abc").equals(
				"abc_123_abc"));
	}

	@Test
	public void LetterUnderscoreLetterUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_abc_abc").equals(
				"abc_abc_abc"));
	}

	@Test
	public void LetterUnderscoreANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_ANY_abc").equals(
				"abc_ANY_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_any_abc").equals(
				"abc_any_abc"));
	}

	@Test
	public void LetterUnderscoreFALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_FALSE_abc").equals(
				"abc_FALSE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_false_abc").equals(
				"abc_false_abc"));
	}

	@Test
	public void LetterUnderscoreINTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTEGER_abc").equals(
				"abc_INTEGER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_integer_abc").equals(
				"abc_integer_abc"));
	}

	@Test
	public void LetterUnderscoreINTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_INTER_abc").equals(
				"abc_INTER_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_inter_abc").equals(
				"abc_inter_abc"));
	}

	@Test
	public void LetterUnderscoreNATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT_abc").equals(
				"abc_NAT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat_abc").equals(
				"abc_nat_abc"));
	}

	@Test
	public void LetterUnderscoreNAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NAT1_abc").equals(
				"abc_NAT1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_nat1_abc").equals(
				"abc_nat1_abc"));
	}

	@Test
	public void LetterUnderscoreNATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NATURAL_abc").equals(
				"abc_NATURAL_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_natural_abc").equals(
				"abc_natural_abc"));
	}

	@Test
	public void LetterUnderscoreNOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_NOT_abc").equals(
				"abc_NOT_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_not_abc").equals(
				"abc_not_abc"));
	}

	@Test
	public void LetterUnderscoreORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_OR_abc").equals(
				"abc_OR_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_or_abc").equals(
				"abc_or_abc"));
	}

	@Test
	public void LetterUnderscorePOWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW_abc").equals(
				"abc_POW_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow_abc").equals(
				"abc_pow_abc"));
	}

	@Test
	public void LetterUnderscorePOW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_POW1_abc").equals(
				"abc_POW1_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_pow1_abc").equals(
				"abc_pow1_abc"));
	}

	@Test
	public void LetterUnderscoreTRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_TRUE_abc").equals(
				"abc_TRUE_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_true_abc").equals(
				"abc_true_abc"));
	}

	@Test
	public void LetterUnderscoreUNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toUnicode("abc_UNION_abc").equals(
				"abc_UNION_abc"));
		assertTrue(UnicodeTranslator.toUnicode("abc_union_abc").equals(
				"abc_union_abc"));
	}

	@Test
	public void DigitUnderscoreDigitUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_123_123").equals(
				"123_123_123"));
	}

	@Test
	public void DigitUnderscoreLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_abc_123").equals(
				"123_abc_123"));
	}

	@Test
	public void DigitUnderscoreANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_ANY_123").equals(
				"123_ANY_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_any_123").equals(
				"123_any_123"));
	}

	@Test
	public void DigitUnderscoreFALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_FALSE_123").equals(
				"123_FALSE_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_false_123").equals(
				"123_false_123"));
	}

	@Test
	public void DigitUnderscoreINTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTEGER_123").equals(
				"123_INTEGER_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_integer_123").equals(
				"123_integer_123"));
	}

	@Test
	public void DigitUnderscoreINTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_INTER_123").equals(
				"123_INTER_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_inter_123").equals(
				"123_inter_123"));
	}

	@Test
	public void DigitUnderscoreNATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_NAT_123").equals(
				"123_NAT_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_nat_123").equals(
				"123_nat_123"));
	}

	@Test
	public void DigitUnderscoreNAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_NAT1_123").equals(
				"123_NAT1_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_nat1_123").equals(
				"123_nat1_123"));
	}

	@Test
	public void DigitUnderscoreNATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_NATURAL_123").equals(
				"123_NATURAL_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_natural_123").equals(
				"123_natural_123"));
	}

	@Test
	public void DigitUnderscoreNOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_NOT_123").equals(
				"123_NOT_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_not_123").equals(
				"123_not_123"));
	}

	@Test
	public void DigitUnderscoreORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_OR_123").equals(
				"123_OR_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_or_123").equals(
				"123_or_123"));
	}

	@Test
	public void DigitUnderscorePOWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_POW_123").equals(
				"123_POW_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_pow_123").equals(
				"123_pow_123"));
	}

	@Test
	public void DigitUnderscorePOW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_POW1_123").equals(
				"123_POW1_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_pow1_123").equals(
				"123_pow1_123"));
	}

	@Test
	public void DigitUnderscoreTRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_TRUE_123").equals(
				"123_TRUE_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_true_123").equals(
				"123_true_123"));
	}

	@Test
	public void DigitUnderscoreUNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toUnicode("123_UNION_123").equals(
				"123_UNION_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_union_123").equals(
				"123_union_123"));
	}

	/*--------------------------------------------------------------*/

	/*
	 * XXX de.prob.unicode.lexer.LexerException: [1,4] Unknown token: _
	 */
	@Test
	public void Var_123() {
		assertTrue(UnicodeTranslator.toUnicode("var_123").equals("var_123"));
		assertTrue(UnicodeTranslator.toUnicode("123_var").equals("123_var"));
		assertTrue(UnicodeTranslator.toUnicode("var_123_var").equals(
				"var_123_var"));

		assertTrue(UnicodeTranslator.toUnicode("var_").equals("var_"));
		assertTrue(UnicodeTranslator.toUnicode("_var").equals("_var"));
		assertTrue(UnicodeTranslator.toUnicode("_var_").equals("_var_"));

		assertTrue(UnicodeTranslator.toUnicode("123_").equals("123_"));
		assertTrue(UnicodeTranslator.toUnicode("_123").equals("_123"));
		assertTrue(UnicodeTranslator.toUnicode("_123_").equals("_123_"));
	}

	@Test
	public void Var123() {
		assertTrue(UnicodeTranslator.toUnicode("var123").equals("var123"));
		assertTrue(UnicodeTranslator.toUnicode("123var").equals("123var"));
		assertTrue(UnicodeTranslator.toUnicode("var123var").equals("var123var"));
		assertTrue(UnicodeTranslator.toUnicode("123var123").equals("123var123"));
	}

	@Test
	public void VarANY() {
		assertTrue(UnicodeTranslator.toUnicode("varANY").equals("varANY"));
		assertTrue(UnicodeTranslator.toUnicode("varany").equals("varany"));
		assertTrue(UnicodeTranslator.toUnicode("varANYvar").equals("varANYvar"));
		assertTrue(UnicodeTranslator.toUnicode("varanyvar").equals("varanyvar"));
		assertTrue(UnicodeTranslator.toUnicode("ANYvar").equals("ANYvar"));
		assertTrue(UnicodeTranslator.toUnicode("anyvar").equals("anyvar"));

		assertTrue(UnicodeTranslator.toUnicode("123ANY").equals("123ANY"));
		assertTrue(UnicodeTranslator.toUnicode("123any").equals("123any"));
		assertTrue(UnicodeTranslator.toUnicode("123ANY123").equals("123ANY123"));
		assertTrue(UnicodeTranslator.toUnicode("123any123").equals("123any123"));
		assertTrue(UnicodeTranslator.toUnicode("ANY123").equals("ANY123"));
		assertTrue(UnicodeTranslator.toUnicode("any123").equals("any123"));

		assertTrue(UnicodeTranslator.toUnicode("_ANY").equals("_ANY"));
		assertTrue(UnicodeTranslator.toUnicode("_any").equals("_any"));
		assertTrue(UnicodeTranslator.toUnicode("_ANY_").equals("_ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("_any_").equals("_any_"));
		assertTrue(UnicodeTranslator.toUnicode("ANY_").equals("ANY_"));
		assertTrue(UnicodeTranslator.toUnicode("any_").equals("any_"));
	}

	@Test
	public void VarFALSE() {
		assertTrue(UnicodeTranslator.toUnicode("varFALSE").equals("varFALSE"));
		assertTrue(UnicodeTranslator.toUnicode("varfalse").equals("varfalse"));
		assertTrue(UnicodeTranslator.toUnicode("varFALSEvar").equals(
				"varFALSEvar"));
		assertTrue(UnicodeTranslator.toUnicode("varfalsevar").equals(
				"varfalsevar"));
		assertTrue(UnicodeTranslator.toUnicode("FALSEvar").equals("FALSEvar"));
		assertTrue(UnicodeTranslator.toUnicode("falsevar").equals("falsevar"));

		assertTrue(UnicodeTranslator.toUnicode("123FALSE").equals("123FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("123false").equals("123false"));
		assertTrue(UnicodeTranslator.toUnicode("123FALSE123").equals(
				"123FALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("123false123").equals(
				"123false123"));
		assertTrue(UnicodeTranslator.toUnicode("FALSE123").equals("FALSE123"));
		assertTrue(UnicodeTranslator.toUnicode("false123").equals("false123"));

		assertTrue(UnicodeTranslator.toUnicode("_FALSE").equals("_FALSE"));
		assertTrue(UnicodeTranslator.toUnicode("_false").equals("_false"));
		assertTrue(UnicodeTranslator.toUnicode("_FALSE_").equals("_FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("_false_").equals("_false_"));
		assertTrue(UnicodeTranslator.toUnicode("FALSE_").equals("FALSE_"));
		assertTrue(UnicodeTranslator.toUnicode("false_").equals("false_"));
	}

	@Test
	public void VarINTEGER() {
		assertTrue(UnicodeTranslator.toUnicode("varINTEGER").equals(
				"varINTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("varinteger").equals(
				"varinteger"));
		assertTrue(UnicodeTranslator.toUnicode("varINTEGERvar").equals(
				"varINTEGERvar"));
		assertTrue(UnicodeTranslator.toUnicode("varintegervar").equals(
				"varintegervar"));
		assertTrue(UnicodeTranslator.toUnicode("INTEGERvar").equals(
				"INTEGERvar"));
		assertTrue(UnicodeTranslator.toUnicode("integervar").equals(
				"integervar"));

		assertTrue(UnicodeTranslator.toUnicode("123INTEGER").equals(
				"123INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("123integer").equals(
				"123integer"));
		assertTrue(UnicodeTranslator.toUnicode("123INTEGER123").equals(
				"123INTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("123integer123").equals(
				"123integer123"));
		assertTrue(UnicodeTranslator.toUnicode("INTEGER123").equals(
				"INTEGER123"));
		assertTrue(UnicodeTranslator.toUnicode("integer123").equals(
				"integer123"));

		assertTrue(UnicodeTranslator.toUnicode("_INTEGER").equals("_INTEGER"));
		assertTrue(UnicodeTranslator.toUnicode("_integer").equals("_integer"));
		assertTrue(UnicodeTranslator.toUnicode("_INTEGER_").equals("_INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("_integer_").equals("_integer_"));
		assertTrue(UnicodeTranslator.toUnicode("INTEGER_").equals("INTEGER_"));
		assertTrue(UnicodeTranslator.toUnicode("integer_").equals("integer_"));
	}

	@Test
	public void VarINTER() {
		assertTrue(UnicodeTranslator.toUnicode("varINTER").equals("varINTER"));
		assertTrue(UnicodeTranslator.toUnicode("varinter").equals("varinter"));
		assertTrue(UnicodeTranslator.toUnicode("varINTERvar").equals(
				"varINTERvar"));
		assertTrue(UnicodeTranslator.toUnicode("varintervar").equals(
				"varintervar"));
		assertTrue(UnicodeTranslator.toUnicode("INTERvar").equals("INTERvar"));
		assertTrue(UnicodeTranslator.toUnicode("intervar").equals("intervar"));

		assertTrue(UnicodeTranslator.toUnicode("123INTER").equals("123INTER"));
		assertTrue(UnicodeTranslator.toUnicode("123inter").equals("123inter"));
		assertTrue(UnicodeTranslator.toUnicode("123INTER123").equals(
				"123INTER123"));
		assertTrue(UnicodeTranslator.toUnicode("123inter123").equals(
				"123inter123"));
		assertTrue(UnicodeTranslator.toUnicode("INTER123").equals("INTER123"));
		assertTrue(UnicodeTranslator.toUnicode("inter123").equals("inter123"));

		assertTrue(UnicodeTranslator.toUnicode("_INTER").equals("_INTER"));
		assertTrue(UnicodeTranslator.toUnicode("_inter").equals("_inter"));
		assertTrue(UnicodeTranslator.toUnicode("_INTER_").equals("_INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("_inter_").equals("_inter_"));
		assertTrue(UnicodeTranslator.toUnicode("INTER_").equals("INTER_"));
		assertTrue(UnicodeTranslator.toUnicode("inter_").equals("inter_"));
	}

	@Test
	public void VarNAT() {
		assertTrue(UnicodeTranslator.toUnicode("varNAT").equals("varNAT"));
		assertTrue(UnicodeTranslator.toUnicode("varnat").equals("varnat"));
		assertTrue(UnicodeTranslator.toUnicode("varNATvar").equals("varNATvar"));
		assertTrue(UnicodeTranslator.toUnicode("varnatvar").equals("varnatvar"));
		assertTrue(UnicodeTranslator.toUnicode("NATvar").equals("NATvar"));
		assertTrue(UnicodeTranslator.toUnicode("natvar").equals("natvar"));

		assertTrue(UnicodeTranslator.toUnicode("123NAT").equals("123NAT"));
		assertTrue(UnicodeTranslator.toUnicode("123nat").equals("123nat"));
		assertTrue(UnicodeTranslator.toUnicode("123NAT123").equals("123NAT123"));
		assertTrue(UnicodeTranslator.toUnicode("123nat123").equals("123nat123"));
		assertTrue(UnicodeTranslator.toUnicode("NAT123").equals("NAT123"));
		assertTrue(UnicodeTranslator.toUnicode("nat123").equals("nat123"));

		assertTrue(UnicodeTranslator.toUnicode("_NAT").equals("_NAT"));
		assertTrue(UnicodeTranslator.toUnicode("_nat").equals("_nat"));
		assertTrue(UnicodeTranslator.toUnicode("_NAT_").equals("_NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("_nat_").equals("_nat_"));
		assertTrue(UnicodeTranslator.toUnicode("NAT_").equals("NAT_"));
		assertTrue(UnicodeTranslator.toUnicode("nat_").equals("nat_"));
	}

	@Test
	public void VarNAT1() {
		assertTrue(UnicodeTranslator.toUnicode("varNAT1").equals("varNAT1"));
		assertTrue(UnicodeTranslator.toUnicode("varnat1").equals("varnat1"));
		assertTrue(UnicodeTranslator.toUnicode("varNAT1var").equals(
				"varNAT1var"));
		assertTrue(UnicodeTranslator.toUnicode("varnat1var").equals(
				"varnat1var"));
		assertTrue(UnicodeTranslator.toUnicode("NAT1var").equals("NAT1var"));
		assertTrue(UnicodeTranslator.toUnicode("nat1var").equals("nat1var"));

		assertTrue(UnicodeTranslator.toUnicode("123NAT1").equals("123NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1").equals("123nat1"));
		assertTrue(UnicodeTranslator.toUnicode("123NAT1123").equals(
				"123NAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("123nat1123").equals(
				"123nat1123"));
		assertTrue(UnicodeTranslator.toUnicode("NAT1123").equals("NAT1123"));
		assertTrue(UnicodeTranslator.toUnicode("nat1123").equals("nat1123"));

		assertTrue(UnicodeTranslator.toUnicode("_NAT1").equals("_NAT1"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1").equals("_nat1"));
		assertTrue(UnicodeTranslator.toUnicode("_NAT1_").equals("_NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("_nat1_").equals("_nat1_"));
		assertTrue(UnicodeTranslator.toUnicode("NAT1_").equals("NAT1_"));
		assertTrue(UnicodeTranslator.toUnicode("nat1_").equals("nat1_"));
	}

	@Test
	public void VarNATURAL() {
		assertTrue(UnicodeTranslator.toUnicode("varNATURAL").equals(
				"varNATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("varnatural").equals(
				"varnatural"));
		assertTrue(UnicodeTranslator.toUnicode("varNATURALvar").equals(
				"varNATURALvar"));
		assertTrue(UnicodeTranslator.toUnicode("varnaturalvar").equals(
				"varnaturalvar"));
		assertTrue(UnicodeTranslator.toUnicode("NATURALvar").equals(
				"NATURALvar"));
		assertTrue(UnicodeTranslator.toUnicode("naturalvar").equals(
				"naturalvar"));

		assertTrue(UnicodeTranslator.toUnicode("123NATURAL").equals(
				"123NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("123natural").equals(
				"123natural"));
		assertTrue(UnicodeTranslator.toUnicode("123NATURAL123").equals(
				"123NATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("123natural123").equals(
				"123natural123"));
		assertTrue(UnicodeTranslator.toUnicode("NATURAL123").equals(
				"NATURAL123"));
		assertTrue(UnicodeTranslator.toUnicode("natural123").equals(
				"natural123"));

		assertTrue(UnicodeTranslator.toUnicode("_NATURAL").equals("_NATURAL"));
		assertTrue(UnicodeTranslator.toUnicode("_natural").equals("_natural"));
		assertTrue(UnicodeTranslator.toUnicode("_NATURAL_").equals("_NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("_natural_").equals("_natural_"));
		assertTrue(UnicodeTranslator.toUnicode("NATURAL_").equals("NATURAL_"));
		assertTrue(UnicodeTranslator.toUnicode("natural_").equals("natural_"));
	}

	@Test
	public void VarNOT() {
		assertTrue(UnicodeTranslator.toUnicode("varNOT").equals("varNOT"));
		assertTrue(UnicodeTranslator.toUnicode("varnot").equals("varnot"));
		assertTrue(UnicodeTranslator.toUnicode("varNOTvar").equals("varNOTvar"));
		assertTrue(UnicodeTranslator.toUnicode("varnotvar").equals("varnotvar"));
		assertTrue(UnicodeTranslator.toUnicode("NOTvar").equals("NOTvar"));
		assertTrue(UnicodeTranslator.toUnicode("notvar").equals("notvar"));

		assertTrue(UnicodeTranslator.toUnicode("123NOT").equals("123NOT"));
		assertTrue(UnicodeTranslator.toUnicode("123not").equals("123not"));
		assertTrue(UnicodeTranslator.toUnicode("123NOT123").equals("123NOT123"));
		assertTrue(UnicodeTranslator.toUnicode("123not123").equals("123not123"));
		assertTrue(UnicodeTranslator.toUnicode("NOT123").equals("NOT123"));
		assertTrue(UnicodeTranslator.toUnicode("not123").equals("not123"));

		assertTrue(UnicodeTranslator.toUnicode("_NOT").equals("_NOT"));
		assertTrue(UnicodeTranslator.toUnicode("_not").equals("_not"));
		assertTrue(UnicodeTranslator.toUnicode("_NOT_").equals("_NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("_not_").equals("_not_"));
		assertTrue(UnicodeTranslator.toUnicode("NOT_").equals("NOT_"));
		assertTrue(UnicodeTranslator.toUnicode("not_").equals("not_"));
	}

	@Test
	public void VarOr() {
		assertTrue(UnicodeTranslator.toUnicode("varOR").equals("varOR"));
		assertTrue(UnicodeTranslator.toUnicode("varor").equals("varor"));
		assertTrue(UnicodeTranslator.toUnicode("varORvar").equals("varORvar"));
		assertTrue(UnicodeTranslator.toUnicode("varorvar").equals("varorvar"));
		assertTrue(UnicodeTranslator.toUnicode("ORvar").equals("ORvar"));
		assertTrue(UnicodeTranslator.toUnicode("orvar").equals("orvar"));

		assertTrue(UnicodeTranslator.toUnicode("123OR").equals("123OR"));
		assertTrue(UnicodeTranslator.toUnicode("123or").equals("123or"));
		assertTrue(UnicodeTranslator.toUnicode("123OR123").equals("123OR123"));
		assertTrue(UnicodeTranslator.toUnicode("123or123").equals("123or123"));
		assertTrue(UnicodeTranslator.toUnicode("OR123").equals("OR123"));
		assertTrue(UnicodeTranslator.toUnicode("or123").equals("or123"));

		assertTrue(UnicodeTranslator.toUnicode("_OR").equals("_OR"));
		assertTrue(UnicodeTranslator.toUnicode("_or").equals("_or"));
		assertTrue(UnicodeTranslator.toUnicode("_OR_").equals("_OR_"));
		assertTrue(UnicodeTranslator.toUnicode("_or_").equals("_or_"));
		assertTrue(UnicodeTranslator.toUnicode("OR_").equals("OR_"));
		assertTrue(UnicodeTranslator.toUnicode("or_").equals("or_"));
	}

	@Test
	public void VarPOW() {
		assertTrue(UnicodeTranslator.toUnicode("varPOW").equals("varPOW"));
		assertTrue(UnicodeTranslator.toUnicode("varpow").equals("varpow"));
		assertTrue(UnicodeTranslator.toUnicode("varPOWvar").equals("varPOWvar"));
		assertTrue(UnicodeTranslator.toUnicode("varpowvar").equals("varpowvar"));
		assertTrue(UnicodeTranslator.toUnicode("POWvar").equals("POWvar"));
		assertTrue(UnicodeTranslator.toUnicode("powvar").equals("powvar"));

		assertTrue(UnicodeTranslator.toUnicode("123pow").equals("123pow"));
		assertTrue(UnicodeTranslator.toUnicode("123POW").equals("123POW"));
		assertTrue(UnicodeTranslator.toUnicode("123POW123").equals("123POW123"));
		assertTrue(UnicodeTranslator.toUnicode("123pow123").equals("123pow123"));
		assertTrue(UnicodeTranslator.toUnicode("POW123").equals("POW123"));
		assertTrue(UnicodeTranslator.toUnicode("pow123").equals("pow123"));

		assertTrue(UnicodeTranslator.toUnicode("_POW").equals("_POW"));
		assertTrue(UnicodeTranslator.toUnicode("_pow").equals("_pow"));
		assertTrue(UnicodeTranslator.toUnicode("_POW_").equals("_POW_"));
		assertTrue(UnicodeTranslator.toUnicode("_pow_").equals("_pow_"));
		assertTrue(UnicodeTranslator.toUnicode("POW_").equals("POW_"));
		assertTrue(UnicodeTranslator.toUnicode("pow_").equals("pow_"));
	}

	@Test
	public void VarPOW1() {
		assertTrue(UnicodeTranslator.toUnicode("varPOW1").equals("varPOW1"));
		assertTrue(UnicodeTranslator.toUnicode("varpow1").equals("varpow1"));
		assertTrue(UnicodeTranslator.toUnicode("varPOW1var").equals(
				"varPOW1var"));
		assertTrue(UnicodeTranslator.toUnicode("varpow1var").equals(
				"varpow1var"));
		assertTrue(UnicodeTranslator.toUnicode("POW1var").equals("POW1var"));
		assertTrue(UnicodeTranslator.toUnicode("pow1var").equals("pow1var"));

		assertTrue(UnicodeTranslator.toUnicode("123POW1").equals("123POW1"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1").equals("123pow1"));
		assertTrue(UnicodeTranslator.toUnicode("123POW1123").equals(
				"123POW1123"));
		assertTrue(UnicodeTranslator.toUnicode("123pow1123").equals(
				"123pow1123"));
		assertTrue(UnicodeTranslator.toUnicode("POW1123").equals("POW1123"));
		assertTrue(UnicodeTranslator.toUnicode("pow1123").equals("pow1123"));

		assertTrue(UnicodeTranslator.toUnicode("_POW1").equals("_POW1"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1").equals("_pow1"));
		assertTrue(UnicodeTranslator.toUnicode("_POW1_").equals("_POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("_pow1_").equals("_pow1_"));
		assertTrue(UnicodeTranslator.toUnicode("POW1_").equals("POW1_"));
		assertTrue(UnicodeTranslator.toUnicode("pow1_").equals("pow1_"));
	}

	@Test
	public void VarTRUE() {
		assertTrue(UnicodeTranslator.toUnicode("varTRUE").equals("varTRUE"));
		assertTrue(UnicodeTranslator.toUnicode("vartrue").equals("vartrue"));
		assertTrue(UnicodeTranslator.toUnicode("varTRUEvar").equals(
				"varTRUEvar"));
		assertTrue(UnicodeTranslator.toUnicode("vartruevar").equals(
				"vartruevar"));
		assertTrue(UnicodeTranslator.toUnicode("TRUEvar").equals("TRUEvar"));
		assertTrue(UnicodeTranslator.toUnicode("truevar").equals("truevar"));

		assertTrue(UnicodeTranslator.toUnicode("123TRUE").equals("123TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("123true").equals("123true"));
		assertTrue(UnicodeTranslator.toUnicode("123TRUE123").equals(
				"123TRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("123true123").equals(
				"123true123"));
		assertTrue(UnicodeTranslator.toUnicode("TRUE123").equals("TRUE123"));
		assertTrue(UnicodeTranslator.toUnicode("true123").equals("true123"));

		assertTrue(UnicodeTranslator.toUnicode("_TRUE").equals("_TRUE"));
		assertTrue(UnicodeTranslator.toUnicode("_true").equals("_true"));
		assertTrue(UnicodeTranslator.toUnicode("_TRUE_").equals("_TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("_true_").equals("_true_"));
		assertTrue(UnicodeTranslator.toUnicode("TRUE_").equals("TRUE_"));
		assertTrue(UnicodeTranslator.toUnicode("true_").equals("true_"));
	}

	@Test
	public void VarUNION() {
		assertTrue(UnicodeTranslator.toUnicode("varUNION").equals("varUNION"));
		assertTrue(UnicodeTranslator.toUnicode("varunion").equals("varunion"));
		assertTrue(UnicodeTranslator.toUnicode("varUNIONvar").equals(
				"varUNIONvar"));
		assertTrue(UnicodeTranslator.toUnicode("varunionvar").equals(
				"varunionvar"));
		assertTrue(UnicodeTranslator.toUnicode("UNIONvar").equals("UNIONvar"));
		assertTrue(UnicodeTranslator.toUnicode("unionvar").equals("unionvar"));

		assertTrue(UnicodeTranslator.toUnicode("123UNION").equals("123UNION"));
		assertTrue(UnicodeTranslator.toUnicode("123union").equals("123union"));
		assertTrue(UnicodeTranslator.toUnicode("123UNION123").equals(
				"123UNION123"));
		assertTrue(UnicodeTranslator.toUnicode("123union123").equals(
				"123union123"));
		assertTrue(UnicodeTranslator.toUnicode("UNION123").equals("UNION123"));
		assertTrue(UnicodeTranslator.toUnicode("union123").equals("union123"));

		assertTrue(UnicodeTranslator.toUnicode("_UNION").equals("_UNION"));
		assertTrue(UnicodeTranslator.toUnicode("_union").equals("_union"));
		assertTrue(UnicodeTranslator.toUnicode("_UNION_").equals("_UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("_union_").equals("_union_"));
		assertTrue(UnicodeTranslator.toUnicode("UNION_").equals("UNION_"));
		assertTrue(UnicodeTranslator.toUnicode("union_").equals("union_"));
	}
}