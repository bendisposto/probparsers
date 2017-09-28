package de.prob.unicode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.prob.unicode.UnicodeTranslator;

public class ToAsciiTest {

	@Test
	public void TIn() {
		assertTrue(UnicodeTranslator.toAscii("\u2208").equals(":"));
	}

	@Test
	public void TNotsubseteq() {
		assertTrue(UnicodeTranslator.toAscii("\u2288").equals("/<:"));
	}

	@Test
	public void TNotsubset() {
		assertTrue(UnicodeTranslator.toAscii("\u2284").equals("/<<:"));
	}

	@Test
	public void TSubseteq() {
		assertTrue(UnicodeTranslator.toAscii("\u2286").equals("<:"));
	}

	@Test
	public void TSetminus() {
		assertTrue(UnicodeTranslator.toAscii("\u2216").equals("\\"));
	}

	@Test
	public void TDotdot() {
		assertTrue(UnicodeTranslator.toAscii("\u2025").equals(".."));
	}

	@Test
	public void TNat() {
		assertTrue(UnicodeTranslator.toAscii("\u2115").equals("NAT"));
	}

	@Test
	public void TEmptyset() {
		assertTrue(UnicodeTranslator.toAscii("\u2205").equals("{}"));
	}

	@Test
	public void TBcmsuch() {
		assertTrue(UnicodeTranslator.toAscii(":\u2223").equals(":|"));
	}

	@Test
	public void TBfalse() {
		assertTrue(UnicodeTranslator.toAscii("\u22a5").equals("false"));
	}

	@Test
	public void TForall() {
		assertTrue(UnicodeTranslator.toAscii("\u2200").equals("!"));
	}

	@Test
	public void TExists() {
		assertTrue(UnicodeTranslator.toAscii("\u2203").equals("#"));
	}

	@Test
	public void TMapsto() {
		assertTrue(UnicodeTranslator.toAscii("\u21a6").equals("|->"));
	}

	@Test
	public void TBtrue() {
		assertTrue(UnicodeTranslator.toAscii("\u22a4").equals("true"));
	}

	@Test
	public void TSubset() {
		assertTrue(UnicodeTranslator.toAscii("\u2282").equals("<<:"));
	}

	@Test
	public void TBunion() {
		assertTrue(UnicodeTranslator.toAscii("\u222a").equals("\\/"));
	}

	@Test
	public void TBinter() {
		assertTrue(UnicodeTranslator.toAscii("\u2229").equals("/\\"));
	}

	@Test
	public void TDomres() {
		assertTrue(UnicodeTranslator.toAscii("\u25c1").equals("<|"));
	}

	@Test
	public void TRanres() {
		assertTrue(UnicodeTranslator.toAscii("\u25b7").equals("|>"));
	}

	@Test
	public void TDomsub() {
		assertTrue(UnicodeTranslator.toAscii("\u2a64").equals("<<|"));
	}

	@Test
	public void TRansub() {
		assertTrue(UnicodeTranslator.toAscii("\u2a65").equals("|>>"));
	}

	@Test
	public void TLambda() {
		assertTrue(UnicodeTranslator.toAscii("\u03bb").equals("%"));
	}

	@Test
	public void TOftype() {
		assertTrue(UnicodeTranslator.toAscii("\u2982").equals("oftype"));
	}

	@Test
	public void TNotin() {
		assertTrue(UnicodeTranslator.toAscii("\u2209").equals("/:"));
	}

	@Test
	public void TCprod() {
		assertTrue(UnicodeTranslator.toAscii("\u00d7").equals("**"));
	}

	@Test
	public void TUnion() {
		assertTrue(UnicodeTranslator.toAscii("\u22c3").equals("UNION"));
	}

	@Test
	public void TInter() {
		assertTrue(UnicodeTranslator.toAscii("\u22c2").equals("INTER"));
	}

	@Test
	public void TFcomp() {
		assertTrue(UnicodeTranslator.toAscii("\u003b").equals(";"));
	}

	@Test
	public void TBcomp() {
		assertTrue(UnicodeTranslator.toAscii("\u2218").equals("circ"));
	}

	@Test
	public void TTotalSurjectiveRel() {
		assertTrue(UnicodeTranslator.toAscii("\ue102").equals("<<->>"));
	}

	@Test
	public void TDprod() {
		assertTrue(UnicodeTranslator.toAscii("\u2297").equals("><"));
	}

	@Test
	public void TPprod() {
		assertTrue(UnicodeTranslator.toAscii("\u2225").equals("||"));
	}

	@Test
	public void TBcmeq() {
		assertTrue(UnicodeTranslator.toAscii("\u2254").equals(":="));
	}

	@Test
	public void TBcmin() {
		assertTrue(UnicodeTranslator.toAscii(":\u2208").equals("::"));
	}

	@Test
	public void TIntg() {
		assertTrue(UnicodeTranslator.toAscii("\u2124").equals("INT"));
	}

	@Test
	public void TLand() {
		assertTrue(UnicodeTranslator.toAscii("\u2227").equals("&"));
	}

	@Test
	public void TLimp() {
		assertTrue(UnicodeTranslator.toAscii("\u21d2").equals("=>"));
	}

	@Test
	public void TLeqv() {
		assertTrue(UnicodeTranslator.toAscii("\u21d4").equals("<=>"));
	}

	@Test
	public void TLnot() {
		assertTrue(UnicodeTranslator.toAscii("\u00ac").equals("not"));
	}

	@Test
	public void TQdot() {
		assertTrue(UnicodeTranslator.toAscii("\u00b7").equals("."));
	}

	@Test
	public void TConv() {
		assertTrue(UnicodeTranslator.toAscii("\u223c").equals("~"));
	}

	@Test
	public void TTotalRel() {
		assertTrue(UnicodeTranslator.toAscii("\ue100").equals("<<->"));
	}

	@Test
	public void TSurjectiveRel() {
		assertTrue(UnicodeTranslator.toAscii("\ue101").equals("<->>"));
	}

	@Test
	public void TPfun() {
		assertTrue(UnicodeTranslator.toAscii("\u21f8").equals("+->"));
	}

	@Test
	public void TTfun() {
		assertTrue(UnicodeTranslator.toAscii("\u2192").equals("-->"));
	}

	@Test
	public void TPinj() {
		assertTrue(UnicodeTranslator.toAscii("\u2914").equals(">+>"));
	}

	@Test
	public void TTinj() {
		assertTrue(UnicodeTranslator.toAscii("\u21a3").equals(">->"));
	}

	@Test
	public void TPsur() {
		assertTrue(UnicodeTranslator.toAscii("\u2900").equals("+>>"));
	}

	@Test
	public void TTsur() {
		assertTrue(UnicodeTranslator.toAscii("\u21a0").equals("->>"));
	}

	@Test
	public void TTbij() {
		assertTrue(UnicodeTranslator.toAscii("\u2916").equals(">->>"));
	}

	@Test
	public void TExpn() {
		assertTrue(UnicodeTranslator.toAscii("\u005e").equals("^"));
	}

	@Test
	public void TLor() {
		assertTrue(UnicodeTranslator.toAscii("\u2228").equals("or"));
	}

	@Test
	public void TPow() {
		assertTrue(UnicodeTranslator.toAscii("\u2119").equals("POW"));
	}

	@Test
	public void TMid() {
		assertTrue(UnicodeTranslator.toAscii("\u2223").equals("|"));
	}

	@Test
	public void TNeq() {
		assertTrue(UnicodeTranslator.toAscii("\u2260").equals("/="));
	}

	@Test
	public void TRel() {
		assertTrue(UnicodeTranslator.toAscii("\u2194").equals("<->"));
	}

	@Test
	public void TOvl() {
		assertTrue(UnicodeTranslator.toAscii("\ue103").equals("<+"));
	}

	@Test
	public void TLeq() {
		assertTrue(UnicodeTranslator.toAscii("\u2264").equals("<="));
	}

	@Test
	public void TGeq() {
		assertTrue(UnicodeTranslator.toAscii("\u2265").equals(">="));
	}

	@Test
	public void TDiv() {
		assertTrue(UnicodeTranslator.toAscii("\u00f7").equals("/"));
	}

	@Test
	public void TMult() {
		assertTrue(UnicodeTranslator.toAscii("\u2217").equals("*"));
	}

	@Test
	public void TMinus() {
		assertTrue(UnicodeTranslator.toAscii("\u2212").equals("-"));
	}

	@Test
	public void TComma() {
		assertTrue(UnicodeTranslator.toAscii(",").equals(","));
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Conjunction() {
		assertTrue(UnicodeTranslator.toAscii("P \u2227 Q").equals("P & Q"));
	}

	@Test
	public void Disjunction() {
		assertTrue(UnicodeTranslator.toAscii("P \u2228 Q").equals("P or Q"));
	}

	@Test
	public void Implication() {
		assertTrue(UnicodeTranslator.toAscii("P \u21d2 Q").equals("P => Q"));
	}

	@Test
	public void Equivalence() {
		assertTrue(UnicodeTranslator.toAscii("P \u21d4 Q").equals("P <=> Q"));
	}

	@Test
	public void Negation() {
		assertTrue(UnicodeTranslator.toAscii("\u00ac P").equals("not P"));
	}

	@Test
	public void UniversalQuantification() {
		// XXX really intended!?
		assertTrue(UnicodeTranslator.toAscii("!(z).(P => Q)").equals(
				"!(z).(P => Q)"));
		assertTrue(UnicodeTranslator.toAscii("\u2200(z)\u00b7(P \u21d2 Q)")
				.equals("!(z).(P => Q)"));
	}

	@Test
	public void UniversalQuantification2() {
		assertTrue(UnicodeTranslator.toAscii("(\u2200z\u00b7P \u21d2 Q)")
				.equals("(!z.P => Q)"));
	}

	@Test
	public void ExistentialQuantification() {
		assertTrue(UnicodeTranslator.toAscii("\u2203(z)\u00b7(P \u2227 Q)")
				.equals("#(z).(P & Q)"));
	}

	@Test
	public void ExistentialQuantification2() {
		assertTrue(UnicodeTranslator.toAscii("(\u2203z\u00b7P \u2227 Q)")
				.equals("(#z.P & Q)"));
	}

	@Test
	public void Substitution() {
		assertTrue(UnicodeTranslator.toAscii("[G] P").equals("[G] P"));
	}

	@Test
	public void Equality() {
		assertTrue(UnicodeTranslator.toAscii("E = F").equals("E = F"));
	}

	@Test
	public void Inequality() {
		assertTrue(UnicodeTranslator.toAscii("E \u2260 F").equals("E /= F"));
	}

	@Test
	public void SingletonSet() {
		assertTrue(UnicodeTranslator.toAscii("{E}").equals("{E}"));
	}

	@Test
	public void SetEnumeration() {
		assertTrue(UnicodeTranslator.toAscii("{E, F}").equals("{E, F}"));
	}

	@Test
	public void EmptySet() {
		assertTrue(UnicodeTranslator.toAscii("\u2205").equals("{}"));
	}

	@Test
	public void SetComprehension() {
		assertTrue(UnicodeTranslator.toAscii("{z \u2223 P}").equals("{z | P}"));
	}

	@Test
	public void SetComprehension2() {
		assertTrue(UnicodeTranslator.toAscii("{z \u00b7 P \u2223 F}").equals(
				"{z . P | F}"));
	}

	@Test
	public void SetComprehension3() {
		assertTrue(UnicodeTranslator.toAscii("{F \u2223 P}").equals("{F | P}"));
	}

	@Test
	public void SetComprehension4() {
		assertTrue(UnicodeTranslator.toAscii("{x \u2223 P}").equals("{x | P}"));
	}

	@Test
	public void Union() {
		assertTrue(UnicodeTranslator.toAscii("S \u222a T").equals("S \\/ T"));
	}

	@Test
	public void Intersection() {
		assertTrue(UnicodeTranslator.toAscii("S \u2229 T").equals("S /\\ T"));
	}

	@Test
	public void Difference() {
		assertTrue(UnicodeTranslator.toAscii("S\u2212T").equals("S-T"));
	}

	@Test
	public void Difference2() {
		assertTrue(UnicodeTranslator.toAscii("S\\T").equals("S\\T"));
	}

	@Test
	public void OrderedPair() {
		assertTrue(UnicodeTranslator.toAscii("E \u21a6 F").equals("E |-> F"));
	}

	@Test
	public void CartesianProduct() {
		// XXX why \u2217 '*' and not \u00d7 'x'?
		assertTrue(UnicodeTranslator.toAscii("S \u2217 T").equals("S * T"));
	}

	@Test
	public void CartesianProduct2() {
		assertTrue(UnicodeTranslator.toAscii("S \u00d7 T").equals("S ** T"));
	}

	@Test
	public void Powerset() {
		assertTrue(UnicodeTranslator.toAscii("\u2119(S)").equals("POW(S)"));
	}

	// XXX NonEmptySubsets not provided? What's the unicode character? \u2119
	// and \u2081
	@Test
	public void NonEmptySubsets() {
		assertTrue(UnicodeTranslator.toAscii("POW1(S)").equals("POW1(S)"));
	}

	// XXX FiniteSets not provided?
	// http://wiki.event-b.org/images/EventB-Summary.pdf
	// S is finite = Unicode, finite S = Ascii
	@Test
	public void FiniteSets() {
		assertTrue(UnicodeTranslator.toAscii("finite S").equals("finite S"));
	}

	// XXX FiniteSubsets not provided? What's the unicode character? \u1D53D ?
	@Test
	public void FiniteSubsets() {
		assertTrue(UnicodeTranslator.toAscii("FIN(S)").equals("FIN(S)"));
	}

	// XXX FiniteNonEmptySubsets not provided? What's the unicode character?
	// \u1D53D and \u2081 ?
	@Test
	public void FiniteNonEmptySubsets() {
		assertTrue(UnicodeTranslator.toAscii("FIN1(S)").equals("FIN1(S)"));
	}

	@Test
	public void Cardinality() {
		assertTrue(UnicodeTranslator.toAscii("card(S)").equals("card(S)"));
	}

	@Test
	public void Partition() {
		assertTrue(UnicodeTranslator.toAscii("partition(S,x,y)").equals(
				"partition(S,x,y)"));
	}

	@Test
	public void GeneralizedUnion() {
		assertTrue(UnicodeTranslator.toAscii("\u22c3(U)").equals("UNION(U)"));
	}

	@Test
	public void GeneralizedUnion2() {
		assertTrue(UnicodeTranslator.toAscii("\u22c3 (z)\u00b7(P \u2223 E)")
				.equals("UNION (z).(P | E)"));
	}

	@Test
	public void GeneralizedUnion3() {
		assertTrue(UnicodeTranslator.toAscii("union(U)").equals("union(U)"));
	}

	@Test
	public void QuantifiedUnion() {
		assertTrue(UnicodeTranslator.toAscii("\u22c3 z\u00b7P \u2223 S")
				.equals("UNION z.P | S"));
	}

	@Test
	public void GeneralizedIntersection() {
		assertTrue(UnicodeTranslator.toAscii("\u22c2(U)").equals("INTER(U)"));
	}

	@Test
	public void GeneralizedIntersection2() {
		assertTrue(UnicodeTranslator.toAscii("\u22c2 (z)\u00b7(P \u2223 E)")
				.equals("INTER (z).(P | E)"));
	}

	@Test
	public void GeneralizedIntersection3() {
		assertTrue(UnicodeTranslator.toAscii("inter(U)").equals("inter(U)"));
	}

	@Test
	public void QuantifiedIntersection() {
		assertTrue(UnicodeTranslator.toAscii("\u22c2 z\u00b7P \u2223 S")
				.equals("INTER z.P | S"));
	}

	@Test
	public void SetMembership() {
		assertTrue(UnicodeTranslator.toAscii("E \u2208 S").equals("E : S"));
	}

	@Test
	public void SetNonMembership() {
		assertTrue(UnicodeTranslator.toAscii("E \u2209 S").equals("E /: S"));
	}

	@Test
	public void Subset() {
		assertTrue(UnicodeTranslator.toAscii("S \u2286 T").equals("S <: T"));
	}

	@Test
	public void NotASubset() {
		assertTrue(UnicodeTranslator.toAscii("S \u2288 T").equals("S /<: T"));
	}

	@Test
	public void ProperSubset() {
		assertTrue(UnicodeTranslator.toAscii("S \u2282 T").equals("S <<: T"));
	}

	@Test
	public void NotAProperSubset() {
		assertTrue(UnicodeTranslator.toAscii("S \u2284 T").equals("S /<<: T"));
	}

	@Test
	public void NaturalNumbers() {
		assertTrue(UnicodeTranslator.toAscii("\u2115").equals("NAT"));
	}

	// XXX PositiveNaturalNumbers not provided? \u2115 and \u2081
	@Test
	public void PositiveNaturalNumbers() {
		assertTrue(UnicodeTranslator.toAscii("NAT1").equals("NAT1"));
	}

	@Test
	public void Minimum() {
		assertTrue(UnicodeTranslator.toAscii("min(S)").equals("min(S)"));
	}

	@Test
	public void Maximum() {
		assertTrue(UnicodeTranslator.toAscii("max(S)").equals("max(S)"));
	}

	@Test
	public void Sum() {
		assertTrue(UnicodeTranslator.toAscii("m + n").equals("m + n"));
	}

	@Test
	public void DifferenceAlt() {
		assertTrue(UnicodeTranslator.toAscii("m \u2212 n").equals("m - n"));
	}

	@Test
	public void Product() {
		// XXX why \u2217 '*' and not \u00d7 'x'?
		assertTrue(UnicodeTranslator.toAscii("m \u2217 n").equals("m * n"));
	}

	@Test
	public void Quotient() {
		assertTrue(UnicodeTranslator.toAscii("m \u00f7 n").equals("m / n"));
	}

	@Test
	public void Remainder() {
		assertTrue(UnicodeTranslator.toAscii("m mod n").equals("m mod n"));
	}

	@Test
	public void Interval() {
		assertTrue(UnicodeTranslator.toAscii("m \u2025 n").equals("m .. n"));
	}

	@Test
	public void SetSummation() {
		// XXX SIGMA not provided (\u2211)
		assertTrue(UnicodeTranslator.toAscii("SIGMA(z)\u00b7(P \u2223 E)")
				.equals("SIGMA(z).(P | E)"));
	}

	@Test
	public void SetProduct() {
		// XXX PI not provided (\u220F)
		assertTrue(UnicodeTranslator.toAscii("PI(z)\u00b7(P \u2223 E)").equals(
				"PI(z).(P | E)"));
	}

	@Test
	public void Greater() {
		assertTrue(UnicodeTranslator.toAscii("m > n").equals("m > n"));
	}

	@Test
	public void Less() {
		assertTrue(UnicodeTranslator.toAscii("m < n").equals("m < n"));
	}

	@Test
	public void GreaterOrEqual() {
		assertTrue(UnicodeTranslator.toAscii("m \u2265 n").equals("m >= n"));
	}

	@Test
	public void LessOrEqual() {
		assertTrue(UnicodeTranslator.toAscii("m \u2264 n").equals("m <= n"));
	}

	@Test
	public void Relations() {
		assertTrue(UnicodeTranslator.toAscii("S \u2194 T").equals("S <-> T"));
	}

	@Test
	public void Domain() {
		assertTrue(UnicodeTranslator.toAscii("dom(r)").equals("dom(r)"));
	}

	@Test
	public void Range() {
		assertTrue(UnicodeTranslator.toAscii("ran(r)").equals("ran(r)"));
	}

	@Test
	public void ForwardComposition() {
		String expected = "p ; q";
		String actual = UnicodeTranslator.toAscii("p ; q");
		assertEquals(expected, actual);

	}

	@Test
	public void BackwardComposition() {
		assertTrue(UnicodeTranslator.toAscii("p \u2218 q").equals("p circ q"));
	}

	@Test
	public void Identity() {
		assertTrue(UnicodeTranslator.toAscii("id(S)").equals("id(S)"));
	}

	@Test
	public void DomainRestriction() {
		assertTrue(UnicodeTranslator.toAscii("S \u25c1 r").equals("S <| r"));
	}

	@Test
	public void DomainSubtraction() {
		assertTrue(UnicodeTranslator.toAscii("S \u2a64 r").equals("S <<| r"));
	}

	@Test
	public void RangeRestriction() {
		assertTrue(UnicodeTranslator.toAscii("r \u25b7 T").equals("r |> T"));
	}

	@Test
	public void RangeSubtraction() {
		assertTrue(UnicodeTranslator.toAscii("r \u2a65 T").equals("r |>> T"));
	}

	@Test
	public void Inverse() {
		assertTrue(UnicodeTranslator.toAscii("r\u223c").equals("r~"));
	}

	@Test
	public void relationalImage() {
		assertTrue(UnicodeTranslator.toAscii("r[S]").equals("r[S]"));
	}

	@Test
	public void RightOverriding() {
		assertTrue(UnicodeTranslator.toAscii("r1 \ue103 r2").equals("r1 <+ r2"));
	}

	/*
	 * XXX java.io.IOException: Pushback buffer overflow LeftOverriding not
	 * provided? How to escape '+>' ?
	 */
	@Test
	public void LeftOverriding() {
		assertTrue(UnicodeTranslator.toAscii("r1 +> r2").equals("r1 +> r2"));
		// assertTrue(UnicodeTranslator.toAscii("r1 +\\> r2").equals("r1 +\\> r2"));
		// // makes "r1 +\> r2", that's not correct
	}

	@Test
	public void DirectProduct() {
		assertTrue(UnicodeTranslator.toAscii("p \u2297 q").equals("p >< q"));
	}

	@Test
	public void ParallelProduct() {
		assertTrue(UnicodeTranslator.toAscii("p \u2225 q").equals("p || q"));
	}

	// XXX Iteration not provided? something like r^n
	@Test
	public void Iteration() {
		assertTrue(UnicodeTranslator.toAscii("iterate(r,n)").equals(
				"iterate(r,n)"));
	}

	@Test
	public void Closure() {
		assertTrue(UnicodeTranslator.toAscii("closure(r)").equals("closure(r)"));
	}

	// XXX reflexibleClosure not provided? something like r^*
	@Test
	public void rClosure() {
		assertTrue(UnicodeTranslator.toAscii("rclosure(r)").equals(
				"rclosure(r)"));
	}

	// XXX irreflexible Closure not provided? something like r^+
	@Test
	public void iClosure() {
		assertTrue(UnicodeTranslator.toAscii("iclosure(r)").equals(
				"iclosure(r)"));
	}

	@Test
	public void Projection1() {
		assertTrue(UnicodeTranslator.toAscii("prj1(S,T)").equals("prj1(S,T)"));
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection1_1() {
		assertTrue(UnicodeTranslator.toAscii("prj1").equals("prj1"));
	}

	@Test
	public void Projection2() {
		assertTrue(UnicodeTranslator.toAscii("prj2(S,T)").equals("prj2(S,T)"));
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection2_1() {
		assertTrue(UnicodeTranslator.toAscii("prj2").equals("prj2"));
	}

	@Test
	public void PartialFunctions() {
		assertTrue(UnicodeTranslator.toAscii("S \u21f8 T").equals("S +-> T"));
	}

	@Test
	public void TotalFunctions() {
		assertTrue(UnicodeTranslator.toAscii("S \u2192 T").equals("S --> T"));
	}

	@Test
	public void PartialInjections() {
		assertTrue(UnicodeTranslator.toAscii("S \u2914 T").equals("S >+> T"));
	}

	@Test
	public void TotalInjections() {
		assertTrue(UnicodeTranslator.toAscii("S \u21a3 T").equals("S >-> T"));
	}

	// XXX PartialSurjections not provided? What's the unicode character? \u2900
	@Test
	public void PartialSurjections() {
		assertTrue(UnicodeTranslator.toAscii("S +->> T").equals("S +->> T"));
	}

	// XXX TotalSurjections not provided? What's the unicode character? \u21A0
	@Test
	public void TotalSurjections() {
		assertTrue(UnicodeTranslator.toAscii("S -->> T").equals("S -->> T"));
	}

	@Test
	public void Bijections() {
		assertTrue(UnicodeTranslator.toAscii("S \u2916 T").equals("S >->> T"));
	}

	@Test
	public void LambdaAbstraction() {
		assertTrue(UnicodeTranslator.toAscii("\u03bbz\u00b7(P\u2223E)").equals(
				"%z.(P|E)"));
	}

	@Test
	public void FunctionApplication() {
		assertTrue(UnicodeTranslator.toAscii("f(E)").equals("f(E)"));
	}

	@Test
	public void FunctionApplication2() {
		assertTrue(UnicodeTranslator.toAscii("f(E \u21a6 F)").equals(
				"f(E |-> F)"));
	}

	@Test
	public void FiniteSequences() {
		assertTrue(UnicodeTranslator.toAscii("seq S").equals("seq S"));
	}

	@Test
	public void FiniteNonEmptySequences() {
		assertTrue(UnicodeTranslator.toAscii("seq1(S)").equals("seq1(S)"));
	}

	@Test
	public void InjectiveSequences() {
		assertTrue(UnicodeTranslator.toAscii("iseq(S)").equals("iseq(S)"));
	}

	@Test
	public void Permutations() {
		assertTrue(UnicodeTranslator.toAscii("perm(S)").equals("perm(S)"));
	}

	@Test
	public void SequenceConcatenations() {
		// XXX really meant \u005e for sequence concatenation? not \u0311 ?
		assertTrue(UnicodeTranslator.toAscii("s\u005et").equals("s^t"));
	}

	/*
	 * XXX java.io.IOException: Pushback buffer overflow PrependElement not
	 * provided? How to escape '->' ? What's the unicode character? \u2192
	 */
	@Test
	public void PrependElement() {
		assertTrue(UnicodeTranslator.toAscii("E -> s").equals("E -> s"));
		// assertTrue(UnicodeTranslator.toAscii("E -\\> s").equals("E -\\> s"));
		// // makes "E -\> s", that's not correct
	}

	/*
	 * XXX java.io.IOException: Pushback buffer overflow AppendElement not
	 * provided? How to escape '<-' ? What's the unicode character? \u2190
	 */
	@Test
	public void AppendElement() {
		assertEquals(UnicodeTranslator.toAscii("s <- E"), "s <- E");
		// assertTrue(UnicodeTranslator.toAscii("s <\\- E").equals("s <\\- E"));
		// // makes "s <\- E", that's not correct
	}

	@Test
	public void SingletonSequence() {
		assertTrue(UnicodeTranslator.toAscii("[E]").equals("[E]"));
	}

	@Test
	public void SequenceConstruction() {
		assertTrue(UnicodeTranslator.toAscii("[E,F]").equals("[E,F]"));
	}

	@Test
	public void Size() {
		assertTrue(UnicodeTranslator.toAscii("size(s)").equals("size(s)"));
	}

	@Test
	public void Reverse() {
		assertTrue(UnicodeTranslator.toAscii("rev(s)").equals("rev(s)"));
	}

	@Test
	public void Take() {
		assertTrue(UnicodeTranslator.toAscii("s /|\\ n").equals("s /|\\ n"));
	}

	@Test
	public void Drop() {
		assertTrue(UnicodeTranslator.toAscii("s \\|/ n").equals("s \\|/ n"));
	}

	@Test
	public void FirstElement() {
		assertTrue(UnicodeTranslator.toAscii("first(s)").equals("first(s)"));
	}

	@Test
	public void LastElement() {
		assertTrue(UnicodeTranslator.toAscii("last(s)").equals("last(s)"));
	}

	@Test
	public void Tail() {
		assertTrue(UnicodeTranslator.toAscii("tail(s)").equals("tail(s)"));
	}

	@Test
	public void Front() {
		assertTrue(UnicodeTranslator.toAscii("front(s)").equals("front(s)"));
	}

	@Test
	public void GeneralizedConcatenation() {
		assertTrue(UnicodeTranslator.toAscii("conc(ss)").equals("conc(ss)"));
	}

	@Test
	public void Skip() {
		assertTrue(UnicodeTranslator.toAscii("skip").equals("skip"));
	}

	@Test
	public void SimpleSubstitution() {
		assertTrue(UnicodeTranslator.toAscii("x := E").equals("x := E"));
	}

	@Test
	public void BooleanSubstitution() {
		assertTrue(UnicodeTranslator.toAscii("x := bool(P)").equals(
				"x := bool(P)"));
	}

	@Test
	public void ChoiceFromSet() {
		assertTrue(UnicodeTranslator.toAscii("x :\u2208 S").equals("x :: S"));
	}

	@Test
	public void ChoiceByPredicate() {
		assertTrue(UnicodeTranslator.toAscii("x : P").equals("x : P"));
	}

	@Test
	public void ChoiceByPredicate2() {
		assertTrue(UnicodeTranslator.toAscii("x :| P").equals("x :| P"));
	}

	@Test
	public void FunctionalOverride() {
		assertTrue(UnicodeTranslator.toAscii("f(x) := E").equals("f(x) := E"));
	}

	@Test
	public void MultipleSubstitution() {
		assertTrue(UnicodeTranslator.toAscii("x,y := E,F").equals("x,y := E,F"));
	}

	@Test
	public void ParallelSubstitution() {
		assertTrue(UnicodeTranslator.toAscii("G \u2225 H").equals("G || H"));
	}

	@Test
	public void SequentialSubstitution() {
		assertTrue(UnicodeTranslator.toAscii("G ; H").equals("G ; H"));
	}

	@Test
	public void Precondition() {
		assertTrue(UnicodeTranslator.toAscii("P \u2223 G").equals("P | G"));
	}

	// XXX Guarding not provided? What's the unicode character? \u21D2
	@Test
	public void Guarding() {
		assertTrue(UnicodeTranslator.toAscii("P ==> G").equals("P ==> G"));
	}

	@Test
	public void Alternatives() {
		assertTrue(UnicodeTranslator.toAscii("P [] G").equals("P [] G"));
	}

	@Test
	public void UnboundedChoice() {
		assertTrue(UnicodeTranslator.toAscii("@z \u00b7 G").equals("@z . G"));
	}

	@Test
	public void Context() {
		assertTrue(UnicodeTranslator.toAscii("CONTEXT").equals("CONTEXT"));
	}

	@Test
	public void Extends() {
		assertTrue(UnicodeTranslator.toAscii("EXTENDS").equals("EXTENDS"));
	}

	@Test
	public void Sets() {
		assertTrue(UnicodeTranslator.toAscii("SETS").equals("SETS"));
	}

	@Test
	public void Constants() {
		assertTrue(UnicodeTranslator.toAscii("CONSTANTS").equals("CONSTANTS"));
	}

	@Test
	public void Axioms() {
		assertTrue(UnicodeTranslator.toAscii("AXIOMS").equals("AXIOMS"));
	}

	@Test
	public void Theorems() {
		assertTrue(UnicodeTranslator.toAscii("THEOREMS").equals("THEOREMS"));
	}

	@Test
	public void End() {
		assertTrue(UnicodeTranslator.toAscii("END").equals("END"));
	}

	@Test
	public void Machine() {
		assertTrue(UnicodeTranslator.toAscii("MACHINE").equals("MACHINE"));
	}

	@Test
	public void Refines() {
		assertTrue(UnicodeTranslator.toAscii("REFINES").equals("REFINES"));
	}

	@Test
	public void Sees() {
		assertTrue(UnicodeTranslator.toAscii("SEES").equals("SEES"));
	}

	@Test
	public void Variables() {
		assertTrue(UnicodeTranslator.toAscii("VARIABLES").equals("VARIABLES"));
	}

	@Test
	public void Invariant() {
		assertTrue(UnicodeTranslator.toAscii("INVARIANT").equals("INVARIANT"));
	}

	@Test
	public void Variant() {
		assertTrue(UnicodeTranslator.toAscii("VARIANT").equals("VARIANT"));
	}

	@Test
	public void Events() {
		assertTrue(UnicodeTranslator.toAscii("EVENTS").equals("EVENTS"));
	}

	@Test
	public void Any() {
		assertTrue(UnicodeTranslator.toAscii("ANY").equals("ANY"));
	}

	@Test
	public void Where() {
		assertTrue(UnicodeTranslator.toAscii("WHERE").equals("WHERE"));
	}

	@Test
	public void With() {
		assertTrue(UnicodeTranslator.toAscii("WITH").equals("WITH"));
	}

	@Test
	public void Then() {
		assertTrue(UnicodeTranslator.toAscii("THEN").equals("THEN"));
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Letter() {
		assertTrue(UnicodeTranslator.toAscii("abc").equals("abc"));
	}

	@Test
	public void LetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc123").equals("abc123"));
	}

	@Test
	public void LetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc_").equals("abc_"));
	}

	@Test
	public void LetterANY() {
		assertTrue(UnicodeTranslator.toAscii("abcANY").equals("abcANY"));
		assertTrue(UnicodeTranslator.toAscii("abcany").equals("abcany"));
	}

	@Test
	public void LetterFALSE() {
		assertTrue(UnicodeTranslator.toAscii("abcFALSE").equals("abcFALSE"));
		assertTrue(UnicodeTranslator.toAscii("abcfalse").equals("abcfalse"));
	}

	@Test
	public void LetterINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("abcINTEGER").equals("abcINTEGER"));
		assertTrue(UnicodeTranslator.toAscii("abcinteger").equals("abcinteger"));
	}

	@Test
	public void LetterINTER() {
		assertTrue(UnicodeTranslator.toAscii("abcINTER").equals("abcINTER"));
		assertTrue(UnicodeTranslator.toAscii("abcinter").equals("abcinter"));
	}

	@Test
	public void LetterNAT() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT").equals("abcNAT"));
		assertTrue(UnicodeTranslator.toAscii("abcnat").equals("abcnat"));
	}

	@Test
	public void LetterNAT1() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT1").equals("abcNAT1"));
		assertTrue(UnicodeTranslator.toAscii("abcnat1").equals("abcnat1"));
	}

	@Test
	public void LetterNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("abcNATURAL").equals("abcNATURAL"));
		assertTrue(UnicodeTranslator.toAscii("abcnatural").equals("abcnatural"));
	}

	@Test
	public void LetterNOT() {
		assertTrue(UnicodeTranslator.toAscii("abcNOT").equals("abcNOT"));
		assertTrue(UnicodeTranslator.toAscii("abcnot").equals("abcnot"));
	}

	@Test
	public void LetterOR() {
		assertTrue(UnicodeTranslator.toAscii("abcOR").equals("abcOR"));
		assertTrue(UnicodeTranslator.toAscii("abcor").equals("abcor"));
	}

	@Test
	public void LetterPOW() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW").equals("abcPOW"));
		assertTrue(UnicodeTranslator.toAscii("abcpow").equals("abcpow"));
	}

	@Test
	public void LetterPOW1() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW1").equals("abcPOW1"));
		assertTrue(UnicodeTranslator.toAscii("abcpow1").equals("abcpow1"));
	}

	@Test
	public void LetterTRUE() {
		assertTrue(UnicodeTranslator.toAscii("abcTRUE").equals("abcTRUE"));
		assertTrue(UnicodeTranslator.toAscii("abctrue").equals("abctrue"));
	}

	@Test
	public void LetterUNION() {
		assertTrue(UnicodeTranslator.toAscii("abcUNION").equals("abcUNION"));
		assertTrue(UnicodeTranslator.toAscii("abcunion").equals("abcunion"));
	}

	@Test
	public void LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123_").equals("abc123_"));
	}

	@Test
	public void LetterDigitANY() {
		assertTrue(UnicodeTranslator.toAscii("abc123ANY").equals("abc123ANY"));
		assertTrue(UnicodeTranslator.toAscii("abc123any").equals("abc123any"));
	}

	@Test
	public void LetterDigitFALSE() {
		assertTrue(UnicodeTranslator.toAscii("abc123FALSE").equals(
				"abc123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("abc123false").equals(
				"abc123false"));
	}

	@Test
	public void LetterDigitINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("abc123INTEGER").equals(
				"abc123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("abc123integer").equals(
				"abc123integer"));
	}

	@Test
	public void LetterDigitINTER() {
		assertTrue(UnicodeTranslator.toAscii("abc123INTER").equals(
				"abc123INTER"));
		assertTrue(UnicodeTranslator.toAscii("abc123inter").equals(
				"abc123inter"));
	}

	@Test
	public void LetterDigitNAT() {
		assertTrue(UnicodeTranslator.toAscii("abc123NAT").equals("abc123NAT"));
		assertTrue(UnicodeTranslator.toAscii("abc123nat").equals("abc123nat"));
	}

	@Test
	public void LetterDigitNAT1() {
		assertTrue(UnicodeTranslator.toAscii("abc123NAT1").equals("abc123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("abc123nat1").equals("abc123nat1"));
	}

	@Test
	public void LetterDigitNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("abc123NATURAL").equals(
				"abc123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("abc123natural").equals(
				"abc123natural"));
	}

	@Test
	public void LetterDigitNOT() {
		assertTrue(UnicodeTranslator.toAscii("abc123NOT").equals("abc123NOT"));
		assertTrue(UnicodeTranslator.toAscii("abc123not").equals("abc123not"));
	}

	@Test
	public void LetterDigitOR() {
		assertTrue(UnicodeTranslator.toAscii("abc123OR").equals("abc123OR"));
		assertTrue(UnicodeTranslator.toAscii("abc123or").equals("abc123or"));
	}

	@Test
	public void LetterDigitPOW() {
		assertTrue(UnicodeTranslator.toAscii("abc123POW").equals("abc123POW"));
		assertTrue(UnicodeTranslator.toAscii("abc123pow").equals("abc123pow"));
	}

	@Test
	public void LetterDigitPOW1() {
		assertTrue(UnicodeTranslator.toAscii("abc123POW1").equals("abc123POW1"));
		assertTrue(UnicodeTranslator.toAscii("abc123pow1").equals("abc123pow1"));
	}

	@Test
	public void LetterDigitTRUE() {
		assertTrue(UnicodeTranslator.toAscii("abc123TRUE").equals("abc123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("abc123true").equals("abc123true"));
	}

	@Test
	public void LetterDigitUNION() {
		assertTrue(UnicodeTranslator.toAscii("abc123UNION").equals(
				"abc123UNION"));
		assertTrue(UnicodeTranslator.toAscii("abc123union").equals(
				"abc123union"));
	}

	@Test
	public void LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_123").equals("abc_123"));
	}

	@Test
	public void LetterUnderscoreANY() {
		assertTrue(UnicodeTranslator.toAscii("abc_ANY").equals("abc_ANY"));
		assertTrue(UnicodeTranslator.toAscii("abc_any").equals("abc_any"));
	}

	@Test
	public void LetterUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toAscii("abc_FALSE").equals("abc_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("abc_false").equals("abc_false"));
	}

	@Test
	public void LetterUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTEGER").equals(
				"abc_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("abc_integer").equals(
				"abc_integer"));
	}

	@Test
	public void LetterUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTER").equals("abc_INTER"));
		assertTrue(UnicodeTranslator.toAscii("abc_inter").equals("abc_inter"));
	}

	@Test
	public void LetterUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT").equals("abc_NAT"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat").equals("abc_nat"));
	}

	@Test
	public void LetterUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT1").equals("abc_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat1").equals("abc_nat1"));
	}

	@Test
	public void LetterUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("abc_NATURAL").equals(
				"abc_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("abc_natural").equals(
				"abc_natural"));
	}

	@Test
	public void LetterUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toAscii("abc_NOT").equals("abc_NOT"));
		assertTrue(UnicodeTranslator.toAscii("abc_not").equals("abc_not"));
	}

	@Test
	public void LetterUnderscoreOR() {
		assertTrue(UnicodeTranslator.toAscii("abc_OR").equals("abc_OR"));
		assertTrue(UnicodeTranslator.toAscii("abc_or").equals("abc_or"));
	}

	@Test
	public void LetterUnderscorePOW() {
		assertTrue(UnicodeTranslator.toAscii("abc_pow").equals("abc_pow"));
		assertTrue(UnicodeTranslator.toAscii("abc_POW").equals("abc_POW"));
	}

	@Test
	public void LetterUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toAscii("abc_POW1").equals("abc_POW1"));
		assertTrue(UnicodeTranslator.toAscii("abc_pow1").equals("abc_pow1"));
	}

	@Test
	public void LetterUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toAscii("abc_TRUE").equals("abc_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("abc_true").equals("abc_true"));
	}

	@Test
	public void LetterUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toAscii("abc_UNION").equals("abc_UNION"));
		assertTrue(UnicodeTranslator.toAscii("abc_union").equals("abc_union"));
	}

	@Test
	public void LetterANYDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcANY123").equals("abcANY123"));
		assertTrue(UnicodeTranslator.toAscii("abcany123").equals("abcany123"));
	}

	@Test
	public void LetterFALSEDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcFALSE123").equals(
				"abcFALSE123"));
		assertTrue(UnicodeTranslator.toAscii("abcfalse123").equals(
				"abcfalse123"));
	}

	@Test
	public void LetterINTEGERDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcINTEGER123").equals(
				"abcINTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("abcinteger123").equals(
				"abcinteger123"));
	}

	@Test
	public void LetterINTERDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcINTER123").equals(
				"abcINTER123"));
		assertTrue(UnicodeTranslator.toAscii("abcinter123").equals(
				"abcinter123"));
	}

	@Test
	public void LetterNATDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT123").equals("abcNAT123"));
		assertTrue(UnicodeTranslator.toAscii("abcnat123").equals("abcnat123"));
	}

	@Test
	public void LetterNAT1Digit() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT1123").equals("abcNAT1123"));
		assertTrue(UnicodeTranslator.toAscii("abcnat1123").equals("abcnat1123"));
	}

	public void LetterNATURALDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNATURAL123").equals(
				"abcNATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("abcnatural123").equals(
				"abcnatural123"));
	}

	public void LetterNOTDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNOT123").equals("abcNOT123"));
		assertTrue(UnicodeTranslator.toAscii("abcnot123").equals("abcnot123"));
	}

	@Test
	public void LetterORDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcOR123").equals("abcOR123"));
		assertTrue(UnicodeTranslator.toAscii("abcor123").equals("abcor123"));
	}

	@Test
	public void LetterPOWDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW123").equals("abcPOW123"));
		assertTrue(UnicodeTranslator.toAscii("abcpow123").equals("abcpow123"));
	}

	@Test
	public void LetterPOW1Digit() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW1123").equals("abcPOW1123"));
		assertTrue(UnicodeTranslator.toAscii("abcpow1123").equals("abcpow1123"));
	}

	@Test
	public void LetterTRUEDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcTRUE123").equals("abcTRUE123"));
		assertTrue(UnicodeTranslator.toAscii("abctrue123").equals("abctrue123"));
	}

	@Test
	public void LetterUNIONDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcUNION123").equals(
				"abcUNION123"));
		assertTrue(UnicodeTranslator.toAscii("abcunion123").equals(
				"abcunion123"));
	}

	@Test
	public void LetterANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcANY_").equals("abcANY_"));
		assertTrue(UnicodeTranslator.toAscii("abcany_").equals("abcany_"));
	}

	@Test
	public void LetterFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcFALSE_").equals("abcFALSE_"));
		assertTrue(UnicodeTranslator.toAscii("abcfalse_").equals("abcfalse_"));
	}

	@Test
	public void LetterINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcINTEGER_").equals(
				"abcINTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("abcinteger_").equals(
				"abcinteger_"));
	}

	@Test
	public void LetterINTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcINTER_").equals("abcINTER_"));
		assertTrue(UnicodeTranslator.toAscii("abcinter_").equals("abcinter_"));
	}

	@Test
	public void LetterNATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT_").equals("abcNAT_"));
		assertTrue(UnicodeTranslator.toAscii("abcnat_").equals("abcnat_"));
	}

	@Test
	public void LetterNAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT1_").equals("abcNAT1_"));
		assertTrue(UnicodeTranslator.toAscii("abcnat1_").equals("abcnat1_"));
	}

	@Test
	public void LetterNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNATURAL_").equals(
				"abcNATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("abcnatural_").equals(
				"abcnatural_"));
	}

	@Test
	public void LetterNOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNOT_").equals("abcNOT_"));
		assertTrue(UnicodeTranslator.toAscii("abcnot_").equals("abcnot_"));
	}

	@Test
	public void LetterORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcOR_").equals("abcOR_"));
		assertTrue(UnicodeTranslator.toAscii("abcor_").equals("abcor_"));
	}

	@Test
	public void LetterPOWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW_").equals("abcPOW_"));
		assertTrue(UnicodeTranslator.toAscii("abcpow_").equals("abcpow_"));
	}

	@Test
	public void LetterPOW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW1_").equals("abcPOW1_"));
		assertTrue(UnicodeTranslator.toAscii("abcpow1_").equals("abcpow1_"));
	}

	@Test
	public void LetterTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcTRUE_").equals("abcTRUE_"));
		assertTrue(UnicodeTranslator.toAscii("abctrue_").equals("abctrue_"));
	}

	@Test
	public void LetterUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcUNION_").equals("abcUNION_"));
		assertTrue(UnicodeTranslator.toAscii("abcunion_").equals("abcunion_"));
	}

	@Test
	public void LetterDigitUnderscoreANY() {
		assertTrue(UnicodeTranslator.toAscii("abc123_ANY").equals("abc123_ANY"));
		assertTrue(UnicodeTranslator.toAscii("abc123_any").equals("abc123_any"));
	}

	@Test
	public void LetterDigitUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toAscii("abc123_FALSE").equals(
				"abc123_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("abc123_false").equals(
				"abc123_false"));
	}

	@Test
	public void LetterDigitUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("abc123_INTEGER").equals(
				"abc123_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("abc123_integer").equals(
				"abc123_integer"));
	}

	@Test
	public void LetterDigitUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toAscii("abc123_INTER").equals(
				"abc123_INTER"));
		assertTrue(UnicodeTranslator.toAscii("abc123_inter").equals(
				"abc123_inter"));
	}

	@Test
	public void LetterDigitUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toAscii("abc123_NAT").equals("abc123_NAT"));
		assertTrue(UnicodeTranslator.toAscii("abc123_nat").equals("abc123_nat"));
	}

	@Test
	public void LetterDigitUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toAscii("abc123_NAT1").equals(
				"abc123_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("abc123_nat1").equals(
				"abc123_nat1"));
	}

	@Test
	public void LetterDigitUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("abc123_NATURAL").equals(
				"abc123_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("abc123_natural").equals(
				"abc123_natural"));
	}

	@Test
	public void LetterDigitUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toAscii("abc123_NOT").equals("abc123_NOT"));
		assertTrue(UnicodeTranslator.toAscii("abc123_not").equals("abc123_not"));
	}

	@Test
	public void LetterDigitUnderscoreOR() {
		assertTrue(UnicodeTranslator.toAscii("abc123_OR").equals("abc123_OR"));
		assertTrue(UnicodeTranslator.toAscii("abc123_or").equals("abc123_or"));
	}

	@Test
	public void LetterDigitUnderscorePOW() {
		assertTrue(UnicodeTranslator.toAscii("abc123_POW").equals("abc123_POW"));
		assertTrue(UnicodeTranslator.toAscii("abc123_pow").equals("abc123_pow"));
	}

	@Test
	public void LetterDigitUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toAscii("abc123_POW1").equals(
				"abc123_POW1"));
		assertTrue(UnicodeTranslator.toAscii("abc123_pow1").equals(
				"abc123_pow1"));
	}

	@Test
	public void LetterDigitUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toAscii("abc123_TRUE").equals(
				"abc123_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("abc123_true").equals(
				"abc123_true"));
	}

	@Test
	public void LetterDigitUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toAscii("abc123_UNION").equals(
				"abc123_UNION"));
		assertTrue(UnicodeTranslator.toAscii("abc123_union").equals(
				"abc123_union"));
	}

	@Test
	public void LetterDigitANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123ANY_").equals("abc123ANY_"));
		assertTrue(UnicodeTranslator.toAscii("abc123any_").equals("abc123any_"));
	}

	@Test
	public void LetterDigitFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123FALSE_").equals(
				"abc123FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("abc123false_").equals(
				"abc123false_"));
	}

	@Test
	public void LetterDigitINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123INTEGER_").equals(
				"abc123INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("abc123integer_").equals(
				"abc123integer_"));
	}

	@Test
	public void LetterDigitINTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123INTER_").equals(
				"abc123INTER_"));
		assertTrue(UnicodeTranslator.toAscii("abc123inter_").equals(
				"abc123inter_"));
	}

	@Test
	public void LetterDigitNATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123NAT_").equals("abc123NAT_"));
		assertTrue(UnicodeTranslator.toAscii("abc123nat_").equals("abc123nat_"));
	}

	@Test
	public void LetterDigitNAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123NAT1_").equals(
				"abc123NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("abc123nat1_").equals(
				"abc123nat1_"));
	}

	@Test
	public void LetterDigitNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123NATURAL_").equals(
				"abc123NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("abc123natural_").equals(
				"abc123natural_"));
	}

	@Test
	public void LetterDigitNOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123NOT_").equals("abc123NOT_"));
		assertTrue(UnicodeTranslator.toAscii("abc123not_").equals("abc123not_"));
	}

	@Test
	public void LetterDigitORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123OR_").equals("abc123OR_"));
		assertTrue(UnicodeTranslator.toAscii("abc123or_").equals("abc123or_"));
	}

	@Test
	public void LetterDigitPOWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123POW_").equals("abc123POW_"));
		assertTrue(UnicodeTranslator.toAscii("abc123pow_").equals("abc123pow_"));
	}

	@Test
	public void LetterDigitPOW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123POW1_").equals(
				"abc123POW1_"));
		assertTrue(UnicodeTranslator.toAscii("abc123pow1_").equals(
				"abc123pow1_"));
	}

	@Test
	public void LetterDigitTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123TRUE_").equals(
				"abc123TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("abc123true_").equals(
				"abc123true_"));
	}

	@Test
	public void LetterDigitUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abc123UNION_").equals(
				"abc123UNION_"));
		assertTrue(UnicodeTranslator.toAscii("abc123union_").equals(
				"abc123union_"));
	}

	@Test
	public void LetterUnderscoreDigitANY() {
		assertTrue(UnicodeTranslator.toAscii("abc_123ANY").equals("abc_123ANY"));
		assertTrue(UnicodeTranslator.toAscii("abc_123any").equals("abc_123any"));
	}

	@Test
	public void LetterUnderscoreDigitFALSE() {
		assertTrue(UnicodeTranslator.toAscii("abc_123FALSE").equals(
				"abc_123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("abc_123false").equals(
				"abc_123false"));
	}

	@Test
	public void LetterUnderscoreDigitINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("abc_123INTEGER").equals(
				"abc_123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("abc_123integer").equals(
				"abc_123integer"));
	}

	@Test
	public void LetterUnderscoreDigitINTER() {
		assertTrue(UnicodeTranslator.toAscii("abc_123INTER").equals(
				"abc_123INTER"));
		assertTrue(UnicodeTranslator.toAscii("abc_123inter").equals(
				"abc_123inter"));
	}

	@Test
	public void LetterUnderscoreDigitANT() {
		assertTrue(UnicodeTranslator.toAscii("abc_123NAT").equals("abc_123NAT"));
		assertTrue(UnicodeTranslator.toAscii("abc_123nat").equals("abc_123nat"));
	}

	@Test
	public void LetterUnderscoreDigitNAT1() {
		assertTrue(UnicodeTranslator.toAscii("abc_123NAT1").equals(
				"abc_123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("abc_123nat1").equals(
				"abc_123nat1"));
	}

	@Test
	public void LetterUnderscoreDigitNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("abc_123NATURAL").equals(
				"abc_123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("abc_123natural").equals(
				"abc_123natural"));
	}

	@Test
	public void LetterUnderscoreDigitNOT() {
		assertTrue(UnicodeTranslator.toAscii("abc_123NOT").equals("abc_123NOT"));
		assertTrue(UnicodeTranslator.toAscii("abc_123not").equals("abc_123not"));
	}

	@Test
	public void LetterUnderscoreDigitOR() {
		assertTrue(UnicodeTranslator.toAscii("abc_123OR").equals("abc_123OR"));
		assertTrue(UnicodeTranslator.toAscii("abc_123or").equals("abc_123or"));
	}

	@Test
	public void LetterUnderscoreDigitPOW() {
		assertTrue(UnicodeTranslator.toAscii("abc_123POW").equals("abc_123POW"));
		assertTrue(UnicodeTranslator.toAscii("abc_123pow").equals("abc_123pow"));
	}

	@Test
	public void LetterUnderscoreDigitPOW1() {
		assertTrue(UnicodeTranslator.toAscii("abc_123POW1").equals(
				"abc_123POW1"));
		assertTrue(UnicodeTranslator.toAscii("abc_123pow1").equals(
				"abc_123pow1"));
	}

	@Test
	public void LetterUnderscoreDigitTRUE() {
		assertTrue(UnicodeTranslator.toAscii("abc_123TRUE").equals(
				"abc_123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("abc_123true").equals(
				"abc_123true"));
	}

	@Test
	public void LetterUnderscoreDigitUNION() {
		assertTrue(UnicodeTranslator.toAscii("abc_123UNION").equals(
				"abc_123UNION"));
		assertTrue(UnicodeTranslator.toAscii("abc_123union").equals(
				"abc_123union"));
	}

	@Test
	public void LetterUnderscoreANYDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_ANY123").equals("abc_ANY123"));
		assertTrue(UnicodeTranslator.toAscii("abc_any123").equals("abc_any123"));
	}

	@Test
	public void LetterUnderscoreFALSEDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_FALSE123").equals(
				"abc_FALSE123"));
		assertTrue(UnicodeTranslator.toAscii("abc_false123").equals(
				"abc_false123"));
	}

	@Test
	public void LetterUnderscoreINTEGERDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTEGER123").equals(
				"abc_INTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("abc_integer123").equals(
				"abc_integer123"));
	}

	@Test
	public void LetterUnderscoreINTERDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTER123").equals(
				"abc_INTER123"));
		assertTrue(UnicodeTranslator.toAscii("abc_inter123").equals(
				"abc_inter123"));
	}

	@Test
	public void LetterUnderscoreNATDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT123").equals("abc_NAT123"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat123").equals("abc_nat123"));
	}

	@Test
	public void LetterUnderscoreNAT1Digit() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT1123").equals(
				"abc_NAT1123"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat1123").equals(
				"abc_nat1123"));
	}

	@Test
	public void LetterUnderscoreNATURALDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_NATURAL123").equals(
				"abc_NATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("abc_natural123").equals(
				"abc_natural123"));
	}

	@Test
	public void LetterUnderscoreNOTDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_NOT123").equals("abc_NOT123"));
		assertTrue(UnicodeTranslator.toAscii("abc_not123").equals("abc_not123"));
	}

	@Test
	public void LetterUnderscoreORDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_OR123").equals("abc_OR123"));
		assertTrue(UnicodeTranslator.toAscii("abc_or123").equals("abc_or123"));
	}

	@Test
	public void LetterUnderscorePOWDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_POW123").equals("abc_POW123"));
		assertTrue(UnicodeTranslator.toAscii("abc_pow123").equals("abc_pow123"));
	}

	@Test
	public void LetterUnderscorePOW1Digit() {
		assertTrue(UnicodeTranslator.toAscii("abc_POW1123").equals(
				"abc_POW1123"));
		assertTrue(UnicodeTranslator.toAscii("abc_pow1123").equals(
				"abc_pow1123"));
	}

	@Test
	public void LetterUnderscoreTRUEDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_TRUE123").equals(
				"abc_TRUE123"));
		assertTrue(UnicodeTranslator.toAscii("abc_true123").equals(
				"abc_true123"));
	}

	@Test
	public void LetterUnderscoreUNIONDigit() {
		assertTrue(UnicodeTranslator.toAscii("abc_UNION123").equals(
				"abc_UNION123"));
		assertTrue(UnicodeTranslator.toAscii("abc_union123").equals(
				"abc_union123"));
	}

	@Test
	public void LetterANYDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcANY123_").equals("abcANY123_"));
		assertTrue(UnicodeTranslator.toAscii("abcany123_").equals("abcany123_"));
	}

	@Test
	public void LetterFALSEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcFALSE123_").equals(
				"abcFALSE123_"));
		assertTrue(UnicodeTranslator.toAscii("abcfalse123_").equals(
				"abcfalse123_"));
	}

	@Test
	public void LetterINTEGERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcINTEGER123_").equals(
				"abcINTEGER123_"));
		assertTrue(UnicodeTranslator.toAscii("abcinteger123_").equals(
				"abcinteger123_"));
	}

	@Test
	public void LetterINTERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcINTER123_").equals(
				"abcINTER123_"));
		assertTrue(UnicodeTranslator.toAscii("abcinter123_").equals(
				"abcinter123_"));
	}

	@Test
	public void LetterNATDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT123_").equals("abcNAT123_"));
		assertTrue(UnicodeTranslator.toAscii("abcnat123_").equals("abcnat123_"));
	}

	@Test
	public void LetterNAT1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT1123_").equals(
				"abcNAT1123_"));
		assertTrue(UnicodeTranslator.toAscii("abcnat1123_").equals(
				"abcnat1123_"));
	}

	@Test
	public void LetterNATURALDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNATURAL123_").equals(
				"abcNATURAL123_"));
		assertTrue(UnicodeTranslator.toAscii("abcnatural123_").equals(
				"abcnatural123_"));
	}

	@Test
	public void LetterNOTDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcNOT123_").equals("abcNOT123_"));
		assertTrue(UnicodeTranslator.toAscii("abcnot123_").equals("abcnot123_"));
	}

	@Test
	public void LetterORDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcOR123_").equals("abcOR123_"));
		assertTrue(UnicodeTranslator.toAscii("abcor123_").equals("abcor123_"));
	}

	@Test
	public void LetterPOWDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW123_").equals("abcPOW123_"));
		assertTrue(UnicodeTranslator.toAscii("abcpow123_").equals("abcpow123_"));
	}

	@Test
	public void LetterPOW1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW1123_").equals(
				"abcPOW1123_"));
		assertTrue(UnicodeTranslator.toAscii("abcpow1123_").equals(
				"abcpow1123_"));
	}

	@Test
	public void LetterTRUEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcTRUE123_").equals(
				"abcTRUE123_"));
		assertTrue(UnicodeTranslator.toAscii("abctrue123_").equals(
				"abctrue123_"));
	}

	@Test
	public void LetterUNIONDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("abcUNION123_").equals(
				"abcUNION123_"));
		assertTrue(UnicodeTranslator.toAscii("abcunion123_").equals(
				"abcunion123_"));
	}

	@Test
	public void LetterANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcANY_123").equals("abcANY_123"));
		assertTrue(UnicodeTranslator.toAscii("abcany_123").equals("abcany_123"));
	}

	@Test
	public void LetterFALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcFALSE_123").equals(
				"abcFALSE_123"));
		assertTrue(UnicodeTranslator.toAscii("abcfalse_123").equals(
				"abcfalse_123"));
	}

	@Test
	public void LetterINTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcINTEGER_123").equals(
				"abcINTEGER_123"));
		assertTrue(UnicodeTranslator.toAscii("abcinteger_123").equals(
				"abcinteger_123"));
	}

	@Test
	public void LetterINTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcINTER_123").equals(
				"abcINTER_123"));
		assertTrue(UnicodeTranslator.toAscii("abcinter_123").equals(
				"abcinter_123"));
	}

	@Test
	public void LetterNATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT_123").equals("abcNAT_123"));
		assertTrue(UnicodeTranslator.toAscii("abcnat_123").equals("abcnat_123"));
	}

	@Test
	public void LetterNAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNAT1_123").equals(
				"abcNAT1_123"));
		assertTrue(UnicodeTranslator.toAscii("abcnat1_123").equals(
				"abcnat1_123"));
	}

	@Test
	public void LetterNATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNATURAL_123").equals(
				"abcNATURAL_123"));
		assertTrue(UnicodeTranslator.toAscii("abcnatural_123").equals(
				"abcnatural_123"));
	}

	@Test
	public void LetterNOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcNOT_123").equals("abcNOT_123"));
		assertTrue(UnicodeTranslator.toAscii("abcnot_123").equals("abcnot_123"));
	}

	@Test
	public void LetterORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcOR_123").equals("abcOR_123"));
		assertTrue(UnicodeTranslator.toAscii("abcor_123").equals("abcor_123"));
	}

	@Test
	public void LetterPOWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW_123").equals("abcPOW_123"));
		assertTrue(UnicodeTranslator.toAscii("abcpow_123").equals("abcpow_123"));
	}

	@Test
	public void LetterPOW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcPOW1_123").equals(
				"abcPOW1_123"));
		assertTrue(UnicodeTranslator.toAscii("abcpow1_123").equals(
				"abcpow1_123"));
	}

	@Test
	public void LetterTRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcTRUE_123").equals(
				"abcTRUE_123"));
		assertTrue(UnicodeTranslator.toAscii("abctrue_123").equals(
				"abctrue_123"));
	}

	@Test
	public void LetterUNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("abcUNION_123").equals(
				"abcUNION_123"));
		assertTrue(UnicodeTranslator.toAscii("abcunion_123").equals(
				"abcunion_123"));
	}

	@Test
	public void Digit() {
		assertTrue(UnicodeTranslator.toAscii("123").equals("123"));
	}

	@Test
	public void DigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("123abc").equals("123abc"));
	}

	@Test
	public void DigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123_").equals("123_"));
	}

	@Test
	public void DigitANY() {
		assertTrue(UnicodeTranslator.toAscii("123ANY").equals("123ANY"));
		assertTrue(UnicodeTranslator.toAscii("123any").equals("123any"));
	}

	@Test
	public void DigitFALSE() {
		assertTrue(UnicodeTranslator.toAscii("123FALSE").equals("123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("123false").equals("123false"));
	}

	@Test
	public void DigitINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("123INTEGER").equals("123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123integer").equals("123integer"));
	}

	@Test
	public void DigitINTER() {
		assertTrue(UnicodeTranslator.toAscii("123INTER").equals("123INTER"));
		assertTrue(UnicodeTranslator.toAscii("123inter").equals("123inter"));
	}

	@Test
	public void DigitNAT() {
		assertTrue(UnicodeTranslator.toAscii("123NAT").equals("123NAT"));
		assertTrue(UnicodeTranslator.toAscii("123nat").equals("123nat"));
	}

	@Test
	public void DigitNAT1() {
		assertTrue(UnicodeTranslator.toAscii("123NAT1").equals("123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("123nat1").equals("123nat1"));
	}

	@Test
	public void DigitNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("123NATURAL").equals("123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123natural").equals("123natural"));
	}

	@Test
	public void DigitNOT() {
		assertTrue(UnicodeTranslator.toAscii("123NOT").equals("123NOT"));
		assertTrue(UnicodeTranslator.toAscii("123not").equals("123not"));
	}

	@Test
	public void DigitOR() {
		assertTrue(UnicodeTranslator.toAscii("123OR").equals("123OR"));
		assertTrue(UnicodeTranslator.toAscii("123or").equals("123or"));
	}

	@Test
	public void DigitPOW() {
		assertTrue(UnicodeTranslator.toAscii("123POW").equals("123POW"));
		assertTrue(UnicodeTranslator.toAscii("123pow").equals("123pow"));
	}

	@Test
	public void DigitPOW1() {
		assertTrue(UnicodeTranslator.toAscii("123POW1").equals("123POW1"));
		assertTrue(UnicodeTranslator.toAscii("123pow1").equals("123pow1"));
	}

	@Test
	public void DigitTRUE() {
		assertTrue(UnicodeTranslator.toAscii("123TRUE").equals("123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("123true").equals("123true"));
	}

	@Test
	public void DigitUNION() {
		assertTrue(UnicodeTranslator.toAscii("123UNION").equals("123UNION"));
		assertTrue(UnicodeTranslator.toAscii("123union").equals("123union"));
	}

	@Test
	public void DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abc_").equals("123abc_"));
	}

	@Test
	public void DigitLetterANY() {
		assertTrue(UnicodeTranslator.toAscii("123abcANY").equals("123abcANY"));
		assertTrue(UnicodeTranslator.toAscii("123abcany").equals("123abcany"));
	}

	@Test
	public void DigitLetterFALSE() {
		assertTrue(UnicodeTranslator.toAscii("123abcFALSE").equals(
				"123abcFALSE"));
		assertTrue(UnicodeTranslator.toAscii("123abcfalse").equals(
				"123abcfalse"));
	}

	@Test
	public void DigitLetterINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("123abcINTEGER").equals(
				"123abcINTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123abcinteger").equals(
				"123abcinteger"));
	}

	@Test
	public void DigitLetterINTER() {
		assertTrue(UnicodeTranslator.toAscii("123abcINTER").equals(
				"123abcINTER"));
		assertTrue(UnicodeTranslator.toAscii("123abcinter").equals(
				"123abcinter"));
	}

	@Test
	public void DigitLetterNAT() {
		assertTrue(UnicodeTranslator.toAscii("123abcNAT").equals("123abcNAT"));
		assertTrue(UnicodeTranslator.toAscii("123abcnat").equals("123abcnat"));
	}

	@Test
	public void DigitLetterNAT1() {
		assertTrue(UnicodeTranslator.toAscii("123abcNAT1").equals("123abcNAT1"));
		assertTrue(UnicodeTranslator.toAscii("123abcnat1").equals("123abcnat1"));
	}

	@Test
	public void DigitLetterNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("123abcNATURAL").equals(
				"123abcNATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123abcnatural").equals(
				"123abcnatural"));
	}

	@Test
	public void DigitLetterNOT() {
		assertTrue(UnicodeTranslator.toAscii("123abcNOT").equals("123abcNOT"));
		assertTrue(UnicodeTranslator.toAscii("123abcnot").equals("123abcnot"));
	}

	@Test
	public void DigitLetterOR() {
		assertTrue(UnicodeTranslator.toAscii("123abcOR").equals("123abcOR"));
		assertTrue(UnicodeTranslator.toAscii("123abcor").equals("123abcor"));
	}

	@Test
	public void DigitLetterPOW() {
		assertTrue(UnicodeTranslator.toAscii("123abcPOW").equals("123abcPOW"));
		assertTrue(UnicodeTranslator.toAscii("123abcpow").equals("123abcpow"));
	}

	@Test
	public void DigitLetterPOW1() {
		assertTrue(UnicodeTranslator.toAscii("123abcPOW1").equals("123abcPOW1"));
		assertTrue(UnicodeTranslator.toAscii("123abcpow1").equals("123abcpow1"));
	}

	@Test
	public void DigitLetterTRUE() {
		assertTrue(UnicodeTranslator.toAscii("123abcTRUE").equals("123abcTRUE"));
		assertTrue(UnicodeTranslator.toAscii("123abctrue").equals("123abctrue"));
	}

	@Test
	public void DigitLetterUNION() {
		assertTrue(UnicodeTranslator.toAscii("123abcUNION").equals(
				"123abcUNION"));
		assertTrue(UnicodeTranslator.toAscii("123abcunion").equals(
				"123abcunion"));
	}

	@Test
	public void DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_abc").equals("123_abc"));
	}

	@Test
	public void DigitUnderscoreANY() {
		assertTrue(UnicodeTranslator.toAscii("123_ANY").equals("123_ANY"));
		assertTrue(UnicodeTranslator.toAscii("123_any").equals("123_any"));
	}

	@Test
	public void DigitUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toAscii("123_FALSE").equals("123_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("123_false").equals("123_false"));
	}

	@Test
	public void DigitUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("123_INTEGER").equals(
				"123_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123_integer").equals(
				"123_integer"));
	}

	@Test
	public void DigitUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toAscii("123_INTER").equals("123_INTER"));
		assertTrue(UnicodeTranslator.toAscii("123_inter").equals("123_inter"));
	}

	@Test
	public void DigitUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toAscii("123_NAT").equals("123_NAT"));
		assertTrue(UnicodeTranslator.toAscii("123_nat").equals("123_nat"));
	}

	@Test
	public void DigitUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toAscii("123_NAT1").equals("123_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("123_nat1").equals("123_nat1"));
	}

	@Test
	public void DigitUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("123_NATURAL").equals(
				"123_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123_natural").equals(
				"123_natural"));
	}

	@Test
	public void DigitUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toAscii("123_NOT").equals("123_NOT"));
		assertTrue(UnicodeTranslator.toAscii("123_not").equals("123_not"));
	}

	@Test
	public void DigitUnderscoreOR() {
		assertTrue(UnicodeTranslator.toAscii("123_OR").equals("123_OR"));
		assertTrue(UnicodeTranslator.toAscii("123_or").equals("123_or"));
	}

	@Test
	public void DigitUnderscorePOW() {
		assertTrue(UnicodeTranslator.toAscii("123_POW").equals("123_POW"));
		assertTrue(UnicodeTranslator.toAscii("123_pow").equals("123_pow"));
	}

	@Test
	public void DigitUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toAscii("123_POW1").equals("123_POW1"));
		assertTrue(UnicodeTranslator.toAscii("123_pow1").equals("123_pow1"));
	}

	@Test
	public void DigitUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toAscii("123_TRUE").equals("123_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("123_true").equals("123_true"));
	}

	@Test
	public void DigitUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toAscii("123_UNION").equals("123_UNION"));
		assertTrue(UnicodeTranslator.toAscii("123_union").equals("123_union"));
	}

	@Test
	public void DigitANYLetter() {
		assertTrue(UnicodeTranslator.toAscii("123ANYabc").equals("123ANYabc"));
		assertTrue(UnicodeTranslator.toAscii("123anyabc").equals("123anyabc"));
	}

	@Test
	public void DigitFALSELetter() {
		assertTrue(UnicodeTranslator.toAscii("123FALSEabc").equals(
				"123FALSEabc"));
		assertTrue(UnicodeTranslator.toAscii("123falseabc").equals(
				"123falseabc"));
	}

	@Test
	public void DigitINTEGERLetter() {
		assertTrue(UnicodeTranslator.toAscii("123INTEGERabc").equals(
				"123INTEGERabc"));
		assertTrue(UnicodeTranslator.toAscii("123integerabc").equals(
				"123integerabc"));
	}

	@Test
	public void DigitINTERLetter() {
		assertTrue(UnicodeTranslator.toAscii("123INTERabc").equals(
				"123INTERabc"));
		assertTrue(UnicodeTranslator.toAscii("123interabc").equals(
				"123interabc"));
	}

	@Test
	public void DigitNATLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NATabc").equals("123NATabc"));
		assertTrue(UnicodeTranslator.toAscii("123natabc").equals("123natabc"));
	}

	@Test
	public void DigitNAT1Letter() {
		assertTrue(UnicodeTranslator.toAscii("123NAT1abc").equals("123NAT1abc"));
		assertTrue(UnicodeTranslator.toAscii("123nat1abc").equals("123nat1abc"));
	}

	@Test
	public void DigitNATURALLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NATURALabc").equals(
				"123NATURALabc"));
		assertTrue(UnicodeTranslator.toAscii("123naturalabc").equals(
				"123naturalabc"));
	}

	@Test
	public void DigitNOTLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NOTabc").equals("123NOTabc"));
		assertTrue(UnicodeTranslator.toAscii("123notabc").equals("123notabc"));
	}

	@Test
	public void DigitORLetter() {
		assertTrue(UnicodeTranslator.toAscii("123ORabc").equals("123ORabc"));
		assertTrue(UnicodeTranslator.toAscii("123orabc").equals("123orabc"));
	}

	@Test
	public void DigitPOWLetter() {
		assertTrue(UnicodeTranslator.toAscii("123POWabc").equals("123POWabc"));
		assertTrue(UnicodeTranslator.toAscii("123powabc").equals("123powabc"));
	}

	@Test
	public void DigitPOW1Letter() {
		assertTrue(UnicodeTranslator.toAscii("123POW1abc").equals("123POW1abc"));
		assertTrue(UnicodeTranslator.toAscii("123pow1abc").equals("123pow1abc"));
	}

	@Test
	public void DigitTRUELetter() {
		assertTrue(UnicodeTranslator.toAscii("123TRUEabc").equals("123TRUEabc"));
		assertTrue(UnicodeTranslator.toAscii("123trueabc").equals("123trueabc"));
	}

	@Test
	public void DigitUNIONLetter() {
		assertTrue(UnicodeTranslator.toAscii("123UNIONabc").equals(
				"123UNIONabc"));
		assertTrue(UnicodeTranslator.toAscii("123unionabc").equals(
				"123unionabc"));
	}

	@Test
	public void DigitANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123ANY_").equals("123ANY_"));
		assertTrue(UnicodeTranslator.toAscii("123any_").equals("123any_"));
	}

	@Test
	public void DigitFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123FALSE_").equals("123FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("123false_").equals("123false_"));
	}

	@Test
	public void DigitINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123INTEGER_").equals(
				"123INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("123integer_").equals(
				"123integer_"));
	}

	@Test
	public void DigitINTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123INTER_").equals("123INTER_"));
		assertTrue(UnicodeTranslator.toAscii("123inter_").equals("123inter_"));
	}

	@Test
	public void DigitNATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NAT_").equals("123NAT_"));
		assertTrue(UnicodeTranslator.toAscii("123nat_").equals("123nat_"));
	}

	@Test
	public void DigitNAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("123NAT1_").equals("123NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("123nat1_").equals("123nat1_"));
	}

	@Test
	public void DigitNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NATURAL_").equals(
				"123NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("123natural_").equals(
				"123natural_"));
	}

	@Test
	public void DigitNOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NOT_").equals("123NOT_"));
		assertTrue(UnicodeTranslator.toAscii("123not_").equals("123not_"));
	}

	@Test
	public void DigitORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123OR_").equals("123OR_"));
		assertTrue(UnicodeTranslator.toAscii("123or_").equals("123or_"));
	}

	@Test
	public void DigitPOWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123POW_").equals("123POW_"));
		assertTrue(UnicodeTranslator.toAscii("123pow_").equals("123pow_"));
	}

	@Test
	public void DigitPOW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("123POW1_").equals("123POW1_"));
		assertTrue(UnicodeTranslator.toAscii("123pow1_").equals("123pow1_"));
	}

	@Test
	public void DigitTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123TRUE_").equals("123TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("123true_").equals("123true_"));
	}

	@Test
	public void DigitUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123UNION_").equals("123UNION_"));
		assertTrue(UnicodeTranslator.toAscii("123union_").equals("123union_"));
	}

	@Test
	public void DigitLetterUnderscoreANY() {
		assertTrue(UnicodeTranslator.toAscii("123abc_ANY").equals("123abc_ANY"));
		assertTrue(UnicodeTranslator.toAscii("123abc_any").equals("123abc_any"));
	}

	@Test
	public void DigitLetterUnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toAscii("123abc_FALSE").equals(
				"123abc_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("123abc_false").equals(
				"123abc_false"));
	}

	@Test
	public void DigitLetterUnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("123abc_INTEGER").equals(
				"123abc_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123abc_integer").equals(
				"123abc_integer"));
	}

	@Test
	public void DigitLetterUnderscoreINTER() {
		assertTrue(UnicodeTranslator.toAscii("123abc_INTER").equals(
				"123abc_INTER"));
		assertTrue(UnicodeTranslator.toAscii("123abc_inter").equals(
				"123abc_inter"));
	}

	@Test
	public void DigitLetterUnderscoreNAT() {
		assertTrue(UnicodeTranslator.toAscii("123abc_NAT").equals("123abc_NAT"));
		assertTrue(UnicodeTranslator.toAscii("123abc_nat").equals("123abc_nat"));
	}

	@Test
	public void DigitLetterUnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toAscii("123abc_NAT1").equals(
				"123abc_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("123abc_nat1").equals(
				"123abc_nat1"));
	}

	@Test
	public void DigitLetterUnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("123abc_NATURAL").equals(
				"123abc_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123abc_natural").equals(
				"123abc_natural"));
	}

	@Test
	public void DigitLetterUnderscoreNOT() {
		assertTrue(UnicodeTranslator.toAscii("123abc_NOT").equals("123abc_NOT"));
		assertTrue(UnicodeTranslator.toAscii("123abc_not").equals("123abc_not"));
	}

	@Test
	public void DigitLetterUnderscoreOR() {
		assertTrue(UnicodeTranslator.toAscii("123abc_OR").equals("123abc_OR"));
		assertTrue(UnicodeTranslator.toAscii("123abc_or").equals("123abc_or"));
	}

	@Test
	public void DigitLetterUnderscorePOW() {
		assertTrue(UnicodeTranslator.toAscii("123abc_POW").equals("123abc_POW"));
		assertTrue(UnicodeTranslator.toAscii("123abc_pow").equals("123abc_pow"));
	}

	@Test
	public void DigitLetterUnderscorePOW1() {
		assertTrue(UnicodeTranslator.toAscii("123abc_POW1").equals(
				"123abc_POW1"));
		assertTrue(UnicodeTranslator.toAscii("123abc_pow1").equals(
				"123abc_pow1"));
	}

	@Test
	public void DigitLetterUnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toAscii("123abc_TRUE").equals(
				"123abc_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("123abc_true").equals(
				"123abc_true"));
	}

	@Test
	public void DigitLetterUnderscoreUNION() {
		assertTrue(UnicodeTranslator.toAscii("123abc_UNION").equals(
				"123abc_UNION"));
		assertTrue(UnicodeTranslator.toAscii("123abc_union").equals(
				"123abc_union"));
	}

	@Test
	public void DigitLetterANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcANY_").equals("123abcANY_"));
		assertTrue(UnicodeTranslator.toAscii("123abcany_").equals("123abcany_"));
	}

	@Test
	public void DigitLetterFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcFALSE_").equals(
				"123abcFALSE_"));
		assertTrue(UnicodeTranslator.toAscii("123abcfalse_").equals(
				"123abcfalse_"));
	}

	@Test
	public void DigitLetterINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcINTEGER_").equals(
				"123abcINTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("123abcinteger_").equals(
				"123abcinteger_"));
	}

	@Test
	public void DigitLetterINTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcINTER_").equals(
				"123abcINTER_"));
		assertTrue(UnicodeTranslator.toAscii("123abcinter_").equals(
				"123abcinter_"));
	}

	@Test
	public void DigitLetterNATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcNAT_").equals("123abcNAT_"));
		assertTrue(UnicodeTranslator.toAscii("123abcnat_").equals("123abcnat_"));
	}

	@Test
	public void DigitLetterNAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcNAT1_").equals(
				"123abcNAT1_"));
		assertTrue(UnicodeTranslator.toAscii("123abcnat1_").equals(
				"123abcnat1_"));
	}

	@Test
	public void DigitLetterNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcNATURAL_").equals(
				"123abcNATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("123abcnatural_").equals(
				"123abcnatural_"));
	}

	@Test
	public void DigitLetterNOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcNOT_").equals("123abcNOT_"));
		assertTrue(UnicodeTranslator.toAscii("123abcnot_").equals("123abcnot_"));
	}

	@Test
	public void DigitLetterORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcOR_").equals("123abcOR_"));
		assertTrue(UnicodeTranslator.toAscii("123abcor_").equals("123abcor_"));
	}

	@Test
	public void DigitLetterPOWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcPOW_").equals("123abcPOW_"));
		assertTrue(UnicodeTranslator.toAscii("123abcpow_").equals("123abcpow_"));
	}

	@Test
	public void DigitLetterPOW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcPOW1_").equals(
				"123abcPOW1_"));
		assertTrue(UnicodeTranslator.toAscii("123abcpow1_").equals(
				"123abcpow1_"));
	}

	@Test
	public void DigitLetterTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcTRUE_").equals(
				"123abcTRUE_"));
		assertTrue(UnicodeTranslator.toAscii("123abctrue_").equals(
				"123abctrue_"));
	}

	@Test
	public void DigitLetterUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123abcUNION_").equals(
				"123abcUNION_"));
		assertTrue(UnicodeTranslator.toAscii("123abcunion_").equals(
				"123abcunion_"));
	}

	@Test
	public void DigitUnderscoreLetterANY() {
		assertTrue(UnicodeTranslator.toAscii("123_abcANY").equals("123_abcANY"));
		assertTrue(UnicodeTranslator.toAscii("123_abcany").equals("123_abcany"));
	}

	@Test
	public void DigitUnderscoreLetterFALSE() {
		assertTrue(UnicodeTranslator.toAscii("123_abcFALSE").equals(
				"123_abcFALSE"));
		assertTrue(UnicodeTranslator.toAscii("123_abcfalse").equals(
				"123_abcfalse"));
	}

	@Test
	public void DigitUnderscoreLetterINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("123_abcINTEGER").equals(
				"123_abcINTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123_abcinteger").equals(
				"123_abcinteger"));
	}

	@Test
	public void DigitUnderscoreLetterINTER() {
		assertTrue(UnicodeTranslator.toAscii("123_abcINTER").equals(
				"123_abcINTER"));
		assertTrue(UnicodeTranslator.toAscii("123_abcinter").equals(
				"123_abcinter"));
	}

	@Test
	public void DigitUnderscoreLetterNAT() {
		assertTrue(UnicodeTranslator.toAscii("123_abcNAT").equals("123_abcNAT"));
		assertTrue(UnicodeTranslator.toAscii("123_abcnat").equals("123_abcnat"));
	}

	@Test
	public void DigitUnderscoreLetterNAT1() {
		assertTrue(UnicodeTranslator.toAscii("123_abcNAT1").equals(
				"123_abcNAT1"));
		assertTrue(UnicodeTranslator.toAscii("123_abcnat1").equals(
				"123_abcnat1"));
	}

	@Test
	public void DigitUnderscoreLetterNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("123_abcNATURAL").equals(
				"123_abcNATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123_abcnatural").equals(
				"123_abcnatural"));
	}

	@Test
	public void DigitUnderscoreLetterNOT() {
		assertTrue(UnicodeTranslator.toAscii("123_abcNOT").equals("123_abcNOT"));
		assertTrue(UnicodeTranslator.toAscii("123_abcnot").equals("123_abcnot"));
	}

	@Test
	public void DigitUnderscoreLetterOR() {
		assertTrue(UnicodeTranslator.toAscii("123_abcOR").equals("123_abcOR"));
		assertTrue(UnicodeTranslator.toAscii("123_abcor").equals("123_abcor"));
	}

	@Test
	public void DigitUnderscoreLetterPOW() {
		assertTrue(UnicodeTranslator.toAscii("123_abcPOW").equals("123_abcPOW"));
		assertTrue(UnicodeTranslator.toAscii("123_abcpow").equals("123_abcpow"));
	}

	@Test
	public void DigitUnderscoreLetterPOW1() {
		assertTrue(UnicodeTranslator.toAscii("123_abcPOW1").equals(
				"123_abcPOW1"));
		assertTrue(UnicodeTranslator.toAscii("123_abcpow1").equals(
				"123_abcpow1"));
	}

	@Test
	public void DigitUnderscoreLetterTRUE() {
		assertTrue(UnicodeTranslator.toAscii("123_abcTRUE").equals(
				"123_abcTRUE"));
		assertTrue(UnicodeTranslator.toAscii("123_abctrue").equals(
				"123_abctrue"));
	}

	@Test
	public void DigitUnderscoreLetterUNION() {
		assertTrue(UnicodeTranslator.toAscii("123_abcUNION").equals(
				"123_abcUNION"));
		assertTrue(UnicodeTranslator.toAscii("123_abcunion").equals(
				"123_abcunion"));
	}

	@Test
	public void DigitUnderscoreANYLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_ANYabc").equals("123_ANYabc"));
		assertTrue(UnicodeTranslator.toAscii("123_anyabc").equals("123_anyabc"));
	}

	@Test
	public void DigitUnderscoreFALSELetter() {
		assertTrue(UnicodeTranslator.toAscii("123_FALSEabc").equals(
				"123_FALSEabc"));
		assertTrue(UnicodeTranslator.toAscii("123_falseabc").equals(
				"123_falseabc"));
	}

	@Test
	public void DigitUnderscoreINTEGERLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_INTEGERabc").equals(
				"123_INTEGERabc"));
		assertTrue(UnicodeTranslator.toAscii("123_integerabc").equals(
				"123_integerabc"));
	}

	@Test
	public void DigitUnderscoreINTERLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_INTERabc").equals(
				"123_INTERabc"));
		assertTrue(UnicodeTranslator.toAscii("123_interabc").equals(
				"123_interabc"));
	}

	@Test
	public void DigitUnderscoreNATLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_NATabc").equals("123_NATabc"));
		assertTrue(UnicodeTranslator.toAscii("123_natabc").equals("123_natabc"));
	}

	@Test
	public void DigitUnderscoreNAT1Letter() {
		assertTrue(UnicodeTranslator.toAscii("123_NAT1abc").equals(
				"123_NAT1abc"));
		assertTrue(UnicodeTranslator.toAscii("123_nat1abc").equals(
				"123_nat1abc"));
	}

	@Test
	public void DigitUnderscoreNATURALLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_NATURALabc").equals(
				"123_NATURALabc"));
		assertTrue(UnicodeTranslator.toAscii("123_naturalabc").equals(
				"123_naturalabc"));
	}

	@Test
	public void DigitUnderscoreNOTLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_NOTabc").equals("123_NOTabc"));
		assertTrue(UnicodeTranslator.toAscii("123_notabc").equals("123_notabc"));
	}

	@Test
	public void DigitUnderscoreORLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_ORabc").equals("123_ORabc"));
		assertTrue(UnicodeTranslator.toAscii("123_orabc").equals("123_orabc"));
	}

	@Test
	public void DigitUnderscorePOWLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_POWabc").equals("123_POWabc"));
		assertTrue(UnicodeTranslator.toAscii("123_powabc").equals("123_powabc"));
	}

	@Test
	public void DigitUnderscorePOW1Letter() {
		assertTrue(UnicodeTranslator.toAscii("123_POW1abc").equals(
				"123_POW1abc"));
		assertTrue(UnicodeTranslator.toAscii("123_pow1abc").equals(
				"123_pow1abc"));
	}

	@Test
	public void DigitUnderscoreTRUELetter() {
		assertTrue(UnicodeTranslator.toAscii("123_TRUEabc").equals(
				"123_TRUEabc"));
		assertTrue(UnicodeTranslator.toAscii("123_trueabc").equals(
				"123_trueabc"));
	}

	@Test
	public void DigitUnderscoreUNIONLetter() {
		assertTrue(UnicodeTranslator.toAscii("123_UNIONabc").equals(
				"123_UNIONabc"));
		assertTrue(UnicodeTranslator.toAscii("123_unionabc").equals(
				"123_unionabc"));
	}

	@Test
	public void DigitANYLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123ANYabc_").equals("123ANYabc_"));
		assertTrue(UnicodeTranslator.toAscii("123anyabc_").equals("123anyabc_"));
	}

	@Test
	public void DigitFALSELetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123FALSEabc_").equals(
				"123FALSEabc_"));
		assertTrue(UnicodeTranslator.toAscii("123falseabc_").equals(
				"123falseabc_"));
	}

	@Test
	public void DigitINTEGERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123INTEGERabc_").equals(
				"123INTEGERabc_"));
		assertTrue(UnicodeTranslator.toAscii("123integerabc_").equals(
				"123integerabc_"));
	}

	@Test
	public void DigitINTERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123INTERabc_").equals(
				"123INTERabc_"));
		assertTrue(UnicodeTranslator.toAscii("123interabc_").equals(
				"123interabc_"));
	}

	@Test
	public void DigitNATLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NATabc_").equals("123NATabc_"));
		assertTrue(UnicodeTranslator.toAscii("123natabc_").equals("123natabc_"));
	}

	@Test
	public void DigitNAT1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NAT1abc_").equals(
				"123NAT1abc_"));
		assertTrue(UnicodeTranslator.toAscii("123nat1abc_").equals(
				"123nat1abc_"));
	}

	@Test
	public void DigitNATURALLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NATURALabc_").equals(
				"123NATURALabc_"));
		assertTrue(UnicodeTranslator.toAscii("123naturalabc_").equals(
				"123naturalabc_"));
	}

	@Test
	public void DigitNOTLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123NOTabc_").equals("123NOTabc_"));
		assertTrue(UnicodeTranslator.toAscii("123notabc_").equals("123notabc_"));
	}

	@Test
	public void DigitORLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123ORabc_").equals("123ORabc_"));
		assertTrue(UnicodeTranslator.toAscii("123orabc_").equals("123orabc_"));
	}

	@Test
	public void DigitPOWLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123POWabc_").equals("123POWabc_"));
		assertTrue(UnicodeTranslator.toAscii("123powabc_").equals("123powabc_"));
	}

	@Test
	public void DigitPOW1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123POW1abc_").equals(
				"123POW1abc_"));
		assertTrue(UnicodeTranslator.toAscii("123pow1abc_").equals(
				"123pow1abc_"));
	}

	@Test
	public void DigitTRUELetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123TRUEabc_").equals(
				"123TRUEabc_"));
		assertTrue(UnicodeTranslator.toAscii("123trueabc_").equals(
				"123trueabc_"));
	}

	@Test
	public void DigitUNIONLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("123UNIONabc_").equals(
				"123UNIONabc_"));
		assertTrue(UnicodeTranslator.toAscii("123unionabc_").equals(
				"123unionabc_"));
	}

	@Test
	public void DigitANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123ANY_abc").equals("123ANY_abc"));
		assertTrue(UnicodeTranslator.toAscii("123any_abc").equals("123any_abc"));
	}

	@Test
	public void DigitFALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123FALSE_abc").equals(
				"123FALSE_abc"));
		assertTrue(UnicodeTranslator.toAscii("123false_abc").equals(
				"123false_abc"));
	}

	@Test
	public void DigitINTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123INTEGER_abc").equals(
				"123INTEGER_abc"));
		assertTrue(UnicodeTranslator.toAscii("123integer_abc").equals(
				"123integer_abc"));
	}

	@Test
	public void DigitINTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123INTER_abc").equals(
				"123INTER_abc"));
		assertTrue(UnicodeTranslator.toAscii("123inter_abc").equals(
				"123inter_abc"));
	}

	@Test
	public void DigitNATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NAT_abc").equals("123NAT_abc"));
		assertTrue(UnicodeTranslator.toAscii("123nat_abc").equals("123nat_abc"));
	}

	@Test
	public void DigitNAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NAT1_abc").equals(
				"123NAT1_abc"));
		assertTrue(UnicodeTranslator.toAscii("123nat1_abc").equals(
				"123nat1_abc"));
	}

	@Test
	public void DigitNATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NATURAL_abc").equals(
				"123NATURAL_abc"));
		assertTrue(UnicodeTranslator.toAscii("123natural_abc").equals(
				"123natural_abc"));
	}

	@Test
	public void DigitNOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123NOT_abc").equals("123NOT_abc"));
		assertTrue(UnicodeTranslator.toAscii("123not_abc").equals("123not_abc"));
	}

	@Test
	public void DigitORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123OR_abc").equals("123OR_abc"));
		assertTrue(UnicodeTranslator.toAscii("123or_abc").equals("123or_abc"));
	}

	@Test
	public void DigitPOWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123POW_abc").equals("123POW_abc"));
		assertTrue(UnicodeTranslator.toAscii("123pow_abc").equals("123pow_abc"));
	}

	@Test
	public void DigitPOW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123POW1_abc").equals(
				"123POW1_abc"));
		assertTrue(UnicodeTranslator.toAscii("123pow1_abc").equals(
				"123pow1_abc"));
	}

	@Test
	public void DigitTRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123TRUE_abc").equals(
				"123TRUE_abc"));
		assertTrue(UnicodeTranslator.toAscii("123true_abc").equals(
				"123true_abc"));
	}

	@Test
	public void DigitUNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("123UNION_abc").equals(
				"123UNION_abc"));
		assertTrue(UnicodeTranslator.toAscii("123union_abc").equals(
				"123union_abc"));
	}

	@Test
	public void Underscore() {
		assertTrue(UnicodeTranslator.toAscii("_").equals("_"));
	}

	@Test
	public void UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("_abc").equals("_abc"));
	}

	@Test
	public void UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("_123").equals("_123"));
	}

	@Test
	public void UnderscoreANY() {
		assertTrue(UnicodeTranslator.toAscii("_ANY").equals("_ANY"));
		assertTrue(UnicodeTranslator.toAscii("_any").equals("_any"));
	}

	@Test
	public void UnderscoreFALSE() {
		assertTrue(UnicodeTranslator.toAscii("_FALSE").equals("_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("_false").equals("_false"));
	}

	@Test
	public void UnderscoreINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGER").equals("_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_integer").equals("_integer"));
	}

	@Test
	public void UnderscoreINTER() {
		assertTrue(UnicodeTranslator.toAscii("_INTER").equals("_INTER"));
		assertTrue(UnicodeTranslator.toAscii("_inter").equals("_inter"));
	}

	@Test
	public void UnderscoreNAT() {
		assertTrue(UnicodeTranslator.toAscii("_NAT").equals("_NAT"));
		assertTrue(UnicodeTranslator.toAscii("_nat").equals("_nat"));
	}

	@Test
	public void UnderscoreNAT1() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1").equals("_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("_nat1").equals("_nat1"));
	}

	@Test
	public void UnderscoreNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("_NATURAL").equals("_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_natural").equals("_natural"));
	}

	@Test
	public void UnderscoreNOT() {
		assertTrue(UnicodeTranslator.toAscii("_NOT").equals("_NOT"));
		assertTrue(UnicodeTranslator.toAscii("_not").equals("_not"));
	}

	@Test
	public void UnderscoreOR() {
		assertTrue(UnicodeTranslator.toAscii("_OR").equals("_OR"));
		assertTrue(UnicodeTranslator.toAscii("_or").equals("_or"));
	}

	@Test
	public void UnderscorePOW() {
		assertTrue(UnicodeTranslator.toAscii("_POW").equals("_POW"));
		assertTrue(UnicodeTranslator.toAscii("_pow").equals("_pow"));
	}

	@Test
	public void UnderscorePOW1() {
		assertTrue(UnicodeTranslator.toAscii("_POW1").equals("_POW1"));
		assertTrue(UnicodeTranslator.toAscii("_pow1").equals("_pow1"));
	}

	@Test
	public void UnderscoreTRUE() {
		assertTrue(UnicodeTranslator.toAscii("_TRUE").equals("_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("_true").equals("_true"));
	}

	@Test
	public void UnderscoreUNION() {
		assertTrue(UnicodeTranslator.toAscii("_UNION").equals("_UNION"));
		assertTrue(UnicodeTranslator.toAscii("_union").equals("_union"));
	}

	@Test
	public void UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abc123").equals("_abc123"));
	}

	@Test
	public void UnderscoreLetterANY() {
		assertTrue(UnicodeTranslator.toAscii("_123ANY").equals("_123ANY"));
		assertTrue(UnicodeTranslator.toAscii("_123any").equals("_123any"));
	}

	@Test
	public void UnderscoreLetterFALSE() {
		assertTrue(UnicodeTranslator.toAscii("_123FALSE").equals("_123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("_123false").equals("_123false"));
	}

	@Test
	public void UnderscoreLetterINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("_123INTEGER").equals(
				"_123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_123integer").equals(
				"_123integer"));
	}

	@Test
	public void UnderscoreLetterINTER() {
		assertTrue(UnicodeTranslator.toAscii("_123INTER").equals("_123INTER"));
		assertTrue(UnicodeTranslator.toAscii("_123inter").equals("_123inter"));
	}

	@Test
	public void UnderscoreLetterNAT() {
		assertTrue(UnicodeTranslator.toAscii("_123NAT").equals("_123NAT"));
		assertTrue(UnicodeTranslator.toAscii("_123nat").equals("_123nat"));
	}

	@Test
	public void UnderscoreLetterNAT1() {
		assertTrue(UnicodeTranslator.toAscii("_123NAT1").equals("_123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("_123nat1").equals("_123nat1"));
	}

	@Test
	public void UnderscoreLetterNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("_123NATURAL").equals(
				"_123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_123natural").equals(
				"_123natural"));
	}

	@Test
	public void UnderscoreLetterNOT() {
		assertTrue(UnicodeTranslator.toAscii("_123NOT").equals("_123NOT"));
		assertTrue(UnicodeTranslator.toAscii("_123not").equals("_123not"));
	}

	@Test
	public void UnderscoreLetterOR() {
		assertTrue(UnicodeTranslator.toAscii("_123OR").equals("_123OR"));
		assertTrue(UnicodeTranslator.toAscii("_123or").equals("_123or"));
	}

	@Test
	public void UnderscoreLetterPOW() {
		assertTrue(UnicodeTranslator.toAscii("_123POW").equals("_123POW"));
		assertTrue(UnicodeTranslator.toAscii("_123pow").equals("_123pow"));
	}

	@Test
	public void UnderscoreLetterPOW1() {
		assertTrue(UnicodeTranslator.toAscii("_123POW1").equals("_123POW1"));
		assertTrue(UnicodeTranslator.toAscii("_123pow1").equals("_123pow1"));
	}

	@Test
	public void UnderscoreLetterTRUE() {
		assertTrue(UnicodeTranslator.toAscii("_123TRUE").equals("_123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("_123true").equals("_123true"));
	}

	@Test
	public void UnderscoreLetterUNION() {
		assertTrue(UnicodeTranslator.toAscii("_123UNION").equals("_123UNION"));
		assertTrue(UnicodeTranslator.toAscii("_123union").equals("_123union"));
	}

	@Test
	public void UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123abc").equals("_123abc"));
	}

	@Test
	public void UnderscoreDigitANY() {
		assertTrue(UnicodeTranslator.toAscii("_123ANY").equals("_123ANY"));
		assertTrue(UnicodeTranslator.toAscii("_123any").equals("_123any"));
	}

	@Test
	public void UnderscoreDigitFALSE() {
		assertTrue(UnicodeTranslator.toAscii("_123FALSE").equals("_123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("_123false").equals("_123false"));
	}

	@Test
	public void UnderscoreDigitINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("_123INTEGER").equals(
				"_123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_123integer").equals(
				"_123integer"));
	}

	@Test
	public void UnderscoreDigitINTER() {
		assertTrue(UnicodeTranslator.toAscii("_123INTER").equals("_123INTER"));
		assertTrue(UnicodeTranslator.toAscii("_123inter").equals("_123inter"));
	}

	@Test
	public void UnderscoreDigitNAT() {
		assertTrue(UnicodeTranslator.toAscii("_123NAT").equals("_123NAT"));
		assertTrue(UnicodeTranslator.toAscii("_123nat").equals("_123nat"));
	}

	@Test
	public void UnderscoreDigitNAT1() {
		assertTrue(UnicodeTranslator.toAscii("_123NAT1").equals("_123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("_123nat1").equals("_123nat1"));
	}

	@Test
	public void UnderscoreDigitNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("_123NATURAL").equals(
				"_123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_123natural").equals(
				"_123natural"));
	}

	@Test
	public void UnderscoreDigitNOT() {
		assertTrue(UnicodeTranslator.toAscii("_123NOT").equals("_123NOT"));
		assertTrue(UnicodeTranslator.toAscii("_123not").equals("_123not"));
	}

	@Test
	public void UnderscoreDigitOR() {
		assertTrue(UnicodeTranslator.toAscii("_123OR").equals("_123OR"));
		assertTrue(UnicodeTranslator.toAscii("_123or").equals("_123or"));
	}

	@Test
	public void UnderscoreDigitPOW() {
		assertTrue(UnicodeTranslator.toAscii("_123POW").equals("_123POW"));
		assertTrue(UnicodeTranslator.toAscii("_123pow").equals("_123pow"));
	}

	@Test
	public void UnderscoreDigitPOW1() {
		assertTrue(UnicodeTranslator.toAscii("_123POW1").equals("_123POW1"));
		assertTrue(UnicodeTranslator.toAscii("_123pow1").equals("_123pow1"));
	}

	@Test
	public void UnderscoreDigitTRUE() {
		assertTrue(UnicodeTranslator.toAscii("_123TRUE").equals("_123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("_123true").equals("_123true"));
	}

	@Test
	public void UnderscoreDigitUNION() {
		assertTrue(UnicodeTranslator.toAscii("_123UNION").equals("_123UNION"));
		assertTrue(UnicodeTranslator.toAscii("_123union").equals("_123union"));
	}

	@Test
	public void UnderscoreANYLetter() {
		assertTrue(UnicodeTranslator.toAscii("_ANYabc").equals("_ANYabc"));
		assertTrue(UnicodeTranslator.toAscii("_anyabc").equals("_anyabc"));
	}

	@Test
	public void UnderscoreFALSELetter() {
		assertTrue(UnicodeTranslator.toAscii("_FALSEabc").equals("_FALSEabc"));
		assertTrue(UnicodeTranslator.toAscii("_falseabc").equals("_falseabc"));
	}

	@Test
	public void UnderscoreINTEGERLetter() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGERabc").equals(
				"_INTEGERabc"));
		assertTrue(UnicodeTranslator.toAscii("_integerabc").equals(
				"_integerabc"));
	}

	@Test
	public void UnderscoreINTERLetter() {
		assertTrue(UnicodeTranslator.toAscii("_INTERabc").equals("_INTERabc"));
		assertTrue(UnicodeTranslator.toAscii("_interabc").equals("_interabc"));
	}

	@Test
	public void UnderscoreNATLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NATabc").equals("_NATabc"));
		assertTrue(UnicodeTranslator.toAscii("_natabc").equals("_natabc"));
	}

	@Test
	public void UnderscoreNAT1Letter() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1abc").equals("_NAT1abc"));
		assertTrue(UnicodeTranslator.toAscii("_nat1abc").equals("_nat1abc"));
	}

	@Test
	public void UnderscoreNATURALLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NATURALabc").equals(
				"_NATURALabc"));
		assertTrue(UnicodeTranslator.toAscii("_naturalabc").equals(
				"_naturalabc"));
	}

	@Test
	public void UnderscoreNOTLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NOTabc").equals("_NOTabc"));
		assertTrue(UnicodeTranslator.toAscii("_notabc").equals("_notabc"));
	}

	@Test
	public void UnderscoreORLetter() {
		assertTrue(UnicodeTranslator.toAscii("_ORabc").equals("_ORabc"));
		assertTrue(UnicodeTranslator.toAscii("_orabc").equals("_orabc"));
	}

	@Test
	public void UnderscorePOWLetter() {
		assertTrue(UnicodeTranslator.toAscii("_POWabc").equals("_POWabc"));
		assertTrue(UnicodeTranslator.toAscii("_powabc").equals("_powabc"));
	}

	@Test
	public void UnderscorePOW1Letter() {
		assertTrue(UnicodeTranslator.toAscii("_POW1abc").equals("_POW1abc"));
		assertTrue(UnicodeTranslator.toAscii("_pow1abc").equals("_pow1abc"));
	}

	@Test
	public void UnderscoreTRUELetter() {
		assertTrue(UnicodeTranslator.toAscii("_TRUEabc").equals("_TRUEabc"));
		assertTrue(UnicodeTranslator.toAscii("_trueabc").equals("_trueabc"));
	}

	@Test
	public void UnderscoreUNIONLetter() {
		assertTrue(UnicodeTranslator.toAscii("_UNIONabc").equals("_UNIONabc"));
		assertTrue(UnicodeTranslator.toAscii("_unionabc").equals("_unionabc"));
	}

	@Test
	public void UnderscoreANYDigit() {
		assertTrue(UnicodeTranslator.toAscii("_ANY123").equals("_ANY123"));
		assertTrue(UnicodeTranslator.toAscii("_any123").equals("_any123"));
	}

	@Test
	public void UnderscoreFALSEDigit() {
		assertTrue(UnicodeTranslator.toAscii("_FALSE123").equals("_FALSE123"));
		assertTrue(UnicodeTranslator.toAscii("_false123").equals("_false123"));
	}

	@Test
	public void UnderscoreINTEGERDigit() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGER123").equals(
				"_INTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("_integer123").equals(
				"_integer123"));
	}

	@Test
	public void UnderscoreINTERDigit() {
		assertTrue(UnicodeTranslator.toAscii("_INTER123").equals("_INTER123"));
		assertTrue(UnicodeTranslator.toAscii("_inter123").equals("_inter123"));
	}

	@Test
	public void UnderscoreNATDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NAT123").equals("_NAT123"));
		assertTrue(UnicodeTranslator.toAscii("_nat123").equals("_nat123"));
	}

	@Test
	public void UnderscoreNAT1Digit() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1123").equals("_NAT1123"));
		assertTrue(UnicodeTranslator.toAscii("_nat1123").equals("_nat1123"));
	}

	@Test
	public void UnderscoreNATURALDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NATURAL123").equals(
				"_NATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("_natural123").equals(
				"_natural123"));
	}

	@Test
	public void UnderscoreNOTDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NOT123").equals("_NOT123"));
		assertTrue(UnicodeTranslator.toAscii("_not123").equals("_not123"));
	}

	@Test
	public void UnderscoreORDigit() {
		assertTrue(UnicodeTranslator.toAscii("_OR123").equals("_OR123"));
		assertTrue(UnicodeTranslator.toAscii("_or123").equals("_or123"));
	}

	@Test
	public void UnderscorePOWDigit() {
		assertTrue(UnicodeTranslator.toAscii("_POW123").equals("_POW123"));
		assertTrue(UnicodeTranslator.toAscii("_pow123").equals("_pow123"));
	}

	@Test
	public void UnderscorePOW1Digit() {
		assertTrue(UnicodeTranslator.toAscii("_POW1123").equals("_POW1123"));
		assertTrue(UnicodeTranslator.toAscii("_pow1123").equals("_pow1123"));
	}

	@Test
	public void UnderscoreTRUEDigit() {
		assertTrue(UnicodeTranslator.toAscii("_TRUE123").equals("_TRUE123"));
		assertTrue(UnicodeTranslator.toAscii("_true123").equals("_true123"));
	}

	@Test
	public void UnderscoreUNIONDigit() {
		assertTrue(UnicodeTranslator.toAscii("_UNION123").equals("_UNION123"));
		assertTrue(UnicodeTranslator.toAscii("_union123").equals("_union123"));
	}

	@Test
	public void UnderscoreLetterDigitANY() {
		assertTrue(UnicodeTranslator.toAscii("_abc123ANY").equals("_abc123ANY"));
		assertTrue(UnicodeTranslator.toAscii("_abc123any").equals("_abc123any"));
	}

	@Test
	public void UnderscoreLetterDigitFALSE() {
		assertTrue(UnicodeTranslator.toAscii("_abc123FALSE").equals(
				"_abc123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("_abc123false").equals(
				"_abc123false"));
	}

	@Test
	public void UnderscoreLetterDigitINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("_abc123INTEGER").equals(
				"_abc123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_abc123integer").equals(
				"_abc123integer"));
	}

	@Test
	public void UnderscoreLetterDigitINTER() {
		assertTrue(UnicodeTranslator.toAscii("_abc123INTER").equals(
				"_abc123INTER"));
		assertTrue(UnicodeTranslator.toAscii("_abc123inter").equals(
				"_abc123inter"));
	}

	@Test
	public void UnderscoreLetterDigitNAT() {
		assertTrue(UnicodeTranslator.toAscii("_abc123NAT").equals("_abc123NAT"));
		assertTrue(UnicodeTranslator.toAscii("_abc123nat").equals("_abc123nat"));
	}

	@Test
	public void UnderscoreLetterDigitNAT1() {
		assertTrue(UnicodeTranslator.toAscii("_abc123NAT1").equals(
				"_abc123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("_abc123nat1").equals(
				"_abc123nat1"));
	}

	@Test
	public void UnderscoreLetterDigitNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("_abc123NATURAL").equals(
				"_abc123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_abc123natural").equals(
				"_abc123natural"));
	}

	@Test
	public void UnderscoreLetterDigitNOT() {
		assertTrue(UnicodeTranslator.toAscii("_abc123NOT").equals("_abc123NOT"));
		assertTrue(UnicodeTranslator.toAscii("_abc123not").equals("_abc123not"));
	}

	@Test
	public void UnderscoreLetterDigitOR() {
		assertTrue(UnicodeTranslator.toAscii("_abc123OR").equals("_abc123OR"));
		assertTrue(UnicodeTranslator.toAscii("_abc123or").equals("_abc123or"));
	}

	@Test
	public void UnderscoreLetterDigitPOW() {
		assertTrue(UnicodeTranslator.toAscii("_abc123POW").equals("_abc123POW"));
		assertTrue(UnicodeTranslator.toAscii("_abc123pow").equals("_abc123pow"));
	}

	@Test
	public void UnderscoreLetterDigitPOW1() {
		assertTrue(UnicodeTranslator.toAscii("_abc123POW1").equals(
				"_abc123POW1"));
		assertTrue(UnicodeTranslator.toAscii("_abc123pow1").equals(
				"_abc123pow1"));
	}

	@Test
	public void UnderscoreLetterDigitTRUE() {
		assertTrue(UnicodeTranslator.toAscii("_abc123TRUE").equals(
				"_abc123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("_abc123true").equals(
				"_abc123true"));
	}

	@Test
	public void UnderscoreLetterDigitUNION() {
		assertTrue(UnicodeTranslator.toAscii("_abc123UNION").equals(
				"_abc123UNION"));
		assertTrue(UnicodeTranslator.toAscii("_abc123union").equals(
				"_abc123union"));
	}

	@Test
	public void UnderscoreLetterANYDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcANY123").equals("_abcANY123"));
		assertTrue(UnicodeTranslator.toAscii("_abcany123").equals("_abcany123"));
	}

	@Test
	public void UnderscoreLetterFALSEDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcFALSE123").equals(
				"_abcFALSE123"));
		assertTrue(UnicodeTranslator.toAscii("_abcfalse123").equals(
				"_abcfalse123"));
	}

	@Test
	public void UnderscoreLetterINTEGERDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcINTEGER123").equals(
				"_abcINTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("_abcinteger123").equals(
				"_abcinteger123"));
	}

	@Test
	public void UnderscoreLetterINTERDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcINTER123").equals(
				"_abcINTER123"));
		assertTrue(UnicodeTranslator.toAscii("_abcinter123").equals(
				"_abcinter123"));
	}

	@Test
	public void UnderscoreLetterNATDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcNAT123").equals("_abcNAT123"));
		assertTrue(UnicodeTranslator.toAscii("_abcnat123").equals("_abcnat123"));
	}

	@Test
	public void UnderscoreLetterNAT1Digit() {
		assertTrue(UnicodeTranslator.toAscii("_abcNAT1123").equals(
				"_abcNAT1123"));
		assertTrue(UnicodeTranslator.toAscii("_abcnat1123").equals(
				"_abcnat1123"));
	}

	@Test
	public void UnderscoreLetterNATURALDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcNATURAL123").equals(
				"_abcNATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("_abcnatural123").equals(
				"_abcnatural123"));
	}

	@Test
	public void UnderscoreLetterNOTDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcNOT123").equals("_abcNOT123"));
		assertTrue(UnicodeTranslator.toAscii("_abcnot123").equals("_abcnot123"));
	}

	@Test
	public void UnderscoreLetterORDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcOR123").equals("_abcOR123"));
		assertTrue(UnicodeTranslator.toAscii("_abcor123").equals("_abcor123"));
	}

	@Test
	public void UnderscoreLetterPOWDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcPOW123").equals("_abcPOW123"));
		assertTrue(UnicodeTranslator.toAscii("_abcpow123").equals("_abcpow123"));
	}

	@Test
	public void UnderscoreLetterPOW1Digit() {
		assertTrue(UnicodeTranslator.toAscii("_abcPOW1123").equals(
				"_abcPOW1123"));
		assertTrue(UnicodeTranslator.toAscii("_abcpow1123").equals(
				"_abcpow1123"));
	}

	@Test
	public void UnderscoreLetterTRUEDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcTRUE123").equals(
				"_abcTRUE123"));
		assertTrue(UnicodeTranslator.toAscii("_abctrue123").equals(
				"_abctrue123"));
	}

	@Test
	public void UnderscoreLetterUNIONDigit() {
		assertTrue(UnicodeTranslator.toAscii("_abcUNION123").equals(
				"_abcUNION123"));
		assertTrue(UnicodeTranslator.toAscii("_abcunion123").equals(
				"_abcunion123"));
	}

	@Test
	public void UnderscoreDigitLetterANY() {
		assertTrue(UnicodeTranslator.toAscii("_123abcANY").equals("_123abcANY"));
		assertTrue(UnicodeTranslator.toAscii("_123abcany").equals("_123abcany"));
	}

	@Test
	public void UnderscoreDigitLetterFALSE() {
		assertTrue(UnicodeTranslator.toAscii("_123abcFALSE").equals(
				"_123abcFALSE"));
		assertTrue(UnicodeTranslator.toAscii("_123abcfalse").equals(
				"_123abcfalse"));
	}

	@Test
	public void UnderscoreDigitLetterINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("_123abcINTEGER").equals(
				"_123abcINTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_123abcinteger").equals(
				"_123abcinteger"));
	}

	@Test
	public void UnderscoreDigitLetterINTER() {
		assertTrue(UnicodeTranslator.toAscii("_123abcINTER").equals(
				"_123abcINTER"));
		assertTrue(UnicodeTranslator.toAscii("_123abcinter").equals(
				"_123abcinter"));
	}

	@Test
	public void UnderscoreDigitLetterNAT() {
		assertTrue(UnicodeTranslator.toAscii("_123abcNAT").equals("_123abcNAT"));
		assertTrue(UnicodeTranslator.toAscii("_123abcnat").equals("_123abcnat"));
	}

	@Test
	public void UnderscoreDigitLetterNAT1() {
		assertTrue(UnicodeTranslator.toAscii("_123abcNAT1").equals(
				"_123abcNAT1"));
		assertTrue(UnicodeTranslator.toAscii("_123abcnat1").equals(
				"_123abcnat1"));
	}

	@Test
	public void UnderscoreDigitLetterNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("_123abcNATURAL").equals(
				"_123abcNATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_123abcnatural").equals(
				"_123abcnatural"));
	}

	@Test
	public void UnderscoreDigitLetterNOT() {
		assertTrue(UnicodeTranslator.toAscii("_123abcNOT").equals("_123abcNOT"));
		assertTrue(UnicodeTranslator.toAscii("_123abcnot").equals("_123abcnot"));
	}

	@Test
	public void UnderscoreDigitLetterOR() {
		assertTrue(UnicodeTranslator.toAscii("_123abcOR").equals("_123abcOR"));
		assertTrue(UnicodeTranslator.toAscii("_123abcor").equals("_123abcor"));
	}

	@Test
	public void UnderscoreDigitLetterPOW() {
		assertTrue(UnicodeTranslator.toAscii("_123abcPOW").equals("_123abcPOW"));
		assertTrue(UnicodeTranslator.toAscii("_123abcpow").equals("_123abcpow"));
	}

	@Test
	public void UnderscoreDigitLetterPOW1() {
		assertTrue(UnicodeTranslator.toAscii("_123abcPOW1").equals(
				"_123abcPOW1"));
		assertTrue(UnicodeTranslator.toAscii("_123abcpow1").equals(
				"_123abcpow1"));
	}

	@Test
	public void UnderscoreDigitLetterTRUE() {
		assertTrue(UnicodeTranslator.toAscii("_123abcTRUE").equals(
				"_123abcTRUE"));
		assertTrue(UnicodeTranslator.toAscii("_123abctrue").equals(
				"_123abctrue"));
	}

	@Test
	public void UnderscoreDigitLetterUNION() {
		assertTrue(UnicodeTranslator.toAscii("_123abcUNION").equals(
				"_123abcUNION"));
		assertTrue(UnicodeTranslator.toAscii("_123abcunion").equals(
				"_123abcunion"));
	}

	@Test
	public void UnderscoreDigitANYLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123ANYabc").equals("_123ANYabc"));
		assertTrue(UnicodeTranslator.toAscii("_123anyabc").equals("_123anyabc"));
	}

	@Test
	public void UnderscoreDigitFALSELetter() {
		assertTrue(UnicodeTranslator.toAscii("_123FALSEabc").equals(
				"_123FALSEabc"));
		assertTrue(UnicodeTranslator.toAscii("_123falseabc").equals(
				"_123falseabc"));
	}

	@Test
	public void UnderscoreDigitINTEGERLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123INTEGERabc").equals(
				"_123INTEGERabc"));
		assertTrue(UnicodeTranslator.toAscii("_123integerabc").equals(
				"_123integerabc"));
	}

	@Test
	public void UnderscoreDigitINTERLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123INTERabc").equals(
				"_123INTERabc"));
		assertTrue(UnicodeTranslator.toAscii("_123interabc").equals(
				"_123interabc"));
	}

	@Test
	public void UnderscoreDigitNATLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123NATabc").equals("_123NATabc"));
		assertTrue(UnicodeTranslator.toAscii("_123natabc").equals("_123natabc"));
	}

	@Test
	public void UnderscoreDigitNAT1Letter() {
		assertTrue(UnicodeTranslator.toAscii("_123NAT1abc").equals(
				"_123NAT1abc"));
		assertTrue(UnicodeTranslator.toAscii("_123nat1abc").equals(
				"_123nat1abc"));
	}

	@Test
	public void UnderscoreDigitNATURALLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123NATURALabc").equals(
				"_123NATURALabc"));
		assertTrue(UnicodeTranslator.toAscii("_123naturalabc").equals(
				"_123naturalabc"));
	}

	@Test
	public void UnderscoreDigitNOTLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123NOTabc").equals("_123NOTabc"));
		assertTrue(UnicodeTranslator.toAscii("_123notabc").equals("_123notabc"));
	}

	@Test
	public void UnderscoreDigitORLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123orabc").equals("_123orabc"));
		assertTrue(UnicodeTranslator.toAscii("_123ORabc").equals("_123ORabc"));
	}

	@Test
	public void UnderscoreDigitPOWLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123POWabc").equals("_123POWabc"));
		assertTrue(UnicodeTranslator.toAscii("_123powabc").equals("_123powabc"));
	}

	@Test
	public void UnderscoreDigitPOW1Letter() {
		assertTrue(UnicodeTranslator.toAscii("_123POW1abc").equals(
				"_123POW1abc"));
		assertTrue(UnicodeTranslator.toAscii("_123pow1abc").equals(
				"_123pow1abc"));
	}

	@Test
	public void UnderscoreDigitTRUELetter() {
		assertTrue(UnicodeTranslator.toAscii("_123TRUEabc").equals(
				"_123TRUEabc"));
		assertTrue(UnicodeTranslator.toAscii("_123trueabc").equals(
				"_123trueabc"));
	}

	@Test
	public void UnderscoreDigitUNIONLetter() {
		assertTrue(UnicodeTranslator.toAscii("_123UNIONabc").equals(
				"_123UNIONabc"));
		assertTrue(UnicodeTranslator.toAscii("_123unionabc").equals(
				"_123unionabc"));
	}

	@Test
	public void UnderscoreANYLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_ANYabc123").equals("_ANYabc123"));
		assertTrue(UnicodeTranslator.toAscii("_anyabc123").equals("_anyabc123"));
	}

	@Test
	public void UnderscoreFALSELetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_FALSEabc123").equals(
				"_FALSEabc123"));
		assertTrue(UnicodeTranslator.toAscii("_falseabc123").equals(
				"_falseabc123"));
	}

	@Test
	public void UnderscoreINTEGERLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGERabc123").equals(
				"_INTEGERabc123"));
		assertTrue(UnicodeTranslator.toAscii("_integerabc123").equals(
				"_integerabc123"));
	}

	@Test
	public void UnderscoreINTERLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_INTERabc123").equals(
				"_INTERabc123"));
		assertTrue(UnicodeTranslator.toAscii("_interabc123").equals(
				"_interabc123"));
	}

	@Test
	public void UnderscoreNATLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NATabc123").equals("_NATabc123"));
		assertTrue(UnicodeTranslator.toAscii("_natabc123").equals("_natabc123"));
	}

	@Test
	public void UnderscoreNAT1LetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1abc123").equals(
				"_NAT1abc123"));
		assertTrue(UnicodeTranslator.toAscii("_nat1abc123").equals(
				"_nat1abc123"));
	}

	@Test
	public void UnderscoreNATURALLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NATURALabc123").equals(
				"_NATURALabc123"));
		assertTrue(UnicodeTranslator.toAscii("_naturalabc123").equals(
				"_naturalabc123"));
	}

	@Test
	public void UnderscoreNOTLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_NOTabc123").equals("_NOTabc123"));
		assertTrue(UnicodeTranslator.toAscii("_notabc123").equals("_notabc123"));
	}

	@Test
	public void UnderscoreORLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_ORabc123").equals("_ORabc123"));
		assertTrue(UnicodeTranslator.toAscii("_orabc123").equals("_orabc123"));
	}

	@Test
	public void UnderscorePOWLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_POWabc123").equals("_POWabc123"));
		assertTrue(UnicodeTranslator.toAscii("_powabc123").equals("_powabc123"));
	}

	@Test
	public void UnderscorePOW1LetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_POW1abc123").equals(
				"_POW1abc123"));
		assertTrue(UnicodeTranslator.toAscii("_pow1abc123").equals(
				"_pow1abc123"));
	}

	@Test
	public void UnderscoreTRUELetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_TRUEabc123").equals(
				"_TRUEabc123"));
		assertTrue(UnicodeTranslator.toAscii("_trueabc123").equals(
				"_trueabc123"));
	}

	@Test
	public void UnderscoreUNIONLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("_UNIONabc123").equals(
				"_UNIONabc123"));
		assertTrue(UnicodeTranslator.toAscii("_unionabc123").equals(
				"_unionabc123"));
	}

	@Test
	public void UnderscoreANYDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_ANY123abc").equals("_ANY123abc"));
		assertTrue(UnicodeTranslator.toAscii("_any123abc").equals("_any123abc"));
	}

	@Test
	public void UnderscoreFALSEDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_FALSE123abc").equals(
				"_FALSE123abc"));
		assertTrue(UnicodeTranslator.toAscii("_false123abc").equals(
				"_false123abc"));
	}

	@Test
	public void UnderscoreINTEGERDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGER123abc").equals(
				"_INTEGER123abc"));
		assertTrue(UnicodeTranslator.toAscii("_integer123abc").equals(
				"_integer123abc"));
	}

	@Test
	public void UnderscoreINTERDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_INTER123abc").equals(
				"_INTER123abc"));
		assertTrue(UnicodeTranslator.toAscii("_inter123abc").equals(
				"_inter123abc"));
	}

	@Test
	public void UnderscoreNATDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NAT123abc").equals("_NAT123abc"));
		assertTrue(UnicodeTranslator.toAscii("_nat123abc").equals("_nat123abc"));
	}

	@Test
	public void UnderscoreNAT1DigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1123abc").equals(
				"_NAT1123abc"));
		assertTrue(UnicodeTranslator.toAscii("_nat1123abc").equals(
				"_nat1123abc"));
	}

	@Test
	public void UnderscoreNATURALDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NATURAL123abc").equals(
				"_NATURAL123abc"));
		assertTrue(UnicodeTranslator.toAscii("_natural123abc").equals(
				"_natural123abc"));
	}

	@Test
	public void UnderscoreNOTDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_NOT123abc").equals("_NOT123abc"));
		assertTrue(UnicodeTranslator.toAscii("_not123abc").equals("_not123abc"));
	}

	@Test
	public void UnderscoreORDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_OR123abc").equals("_OR123abc"));
		assertTrue(UnicodeTranslator.toAscii("_or123abc").equals("_or123abc"));
	}

	@Test
	public void UnderscorePOWDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_POW123abc").equals("_POW123abc"));
		assertTrue(UnicodeTranslator.toAscii("_pow123abc").equals("_pow123abc"));
	}

	@Test
	public void UnderscorePOW1DigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_POW1123abc").equals(
				"_POW1123abc"));
		assertTrue(UnicodeTranslator.toAscii("_pow1123abc").equals(
				"_pow1123abc"));
	}

	@Test
	public void UnderscoreTRUEDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_TRUE123abc").equals(
				"_TRUE123abc"));
		assertTrue(UnicodeTranslator.toAscii("_true123abc").equals(
				"_true123abc"));
	}

	@Test
	public void UnderscoreUNIONDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("_UNION123abc").equals(
				"_UNION123abc"));
		assertTrue(UnicodeTranslator.toAscii("_union123abc").equals(
				"_union123abc"));
	}

	// @Test
	// public void Keyword()
	// {
	// // check, if the keywords have to be translated or not
	// assertTrue(UnicodeTranslator.toAscii("ANY").equals("ANY"));
	// assertFalse(UnicodeTranslator.toAscii("ANY").equals("ANY"));
	// }

	@Test
	public void ANYLetter() {
		assertTrue(UnicodeTranslator.toAscii("ANYabc").equals("ANYabc"));
		assertTrue(UnicodeTranslator.toAscii("anyabc").equals("anyabc"));
	}

	@Test
	public void FALSELetter() {
		assertTrue(UnicodeTranslator.toAscii("FALSEabc").equals("FALSEabc"));
		assertTrue(UnicodeTranslator.toAscii("falseabc").equals("falseabc"));
	}

	@Test
	public void INTEGERLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTEGERabc").equals("INTEGERabc"));
		assertTrue(UnicodeTranslator.toAscii("integerabc").equals("integerabc"));
	}

	@Test
	public void INTERLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTERabc").equals("INTERabc"));
		assertTrue(UnicodeTranslator.toAscii("interabc").equals("interabc"));
	}

	@Test
	public void NATLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATabc").equals("NATabc"));
		assertTrue(UnicodeTranslator.toAscii("natabc").equals("natabc"));
	}

	@Test
	public void NAT1Letter() {
		assertTrue(UnicodeTranslator.toAscii("NAT1abc").equals("NAT1abc"));
		assertTrue(UnicodeTranslator.toAscii("nat1abc").equals("nat1abc"));
	}

	@Test
	public void NATURALLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATURALabc").equals("NATURALabc"));
		assertTrue(UnicodeTranslator.toAscii("naturalabc").equals("naturalabc"));
	}

	@Test
	public void NOTLetter() {
		assertTrue(UnicodeTranslator.toAscii("NOTabc").equals("NOTabc"));
		assertTrue(UnicodeTranslator.toAscii("notabc").equals("notabc"));
	}

	@Test
	public void ORLetter() {
		assertTrue(UnicodeTranslator.toAscii("ORabc").equals("ORabc"));
		assertTrue(UnicodeTranslator.toAscii("orabc").equals("orabc"));
	}

	@Test
	public void POWLetter() {
		assertTrue(UnicodeTranslator.toAscii("POWabc").equals("POWabc"));
		assertTrue(UnicodeTranslator.toAscii("powabc").equals("powabc"));
	}

	@Test
	public void POW1Letter() {
		assertTrue(UnicodeTranslator.toAscii("POW1abc").equals("POW1abc"));
		assertTrue(UnicodeTranslator.toAscii("pow1abc").equals("pow1abc"));
	}

	@Test
	public void TRUELetter() {
		assertTrue(UnicodeTranslator.toAscii("TRUEabc").equals("TRUEabc"));
		assertTrue(UnicodeTranslator.toAscii("trueabc").equals("trueabc"));
	}

	@Test
	public void UNIONLetter() {
		assertTrue(UnicodeTranslator.toAscii("UNIONabc").equals("UNIONabc"));
		assertTrue(UnicodeTranslator.toAscii("unionabc").equals("unionabc"));
	}

	@Test
	public void ANYDigit() {
		assertTrue(UnicodeTranslator.toAscii("ANY123").equals("ANY123"));
		assertTrue(UnicodeTranslator.toAscii("any123").equals("any123"));
	}

	@Test
	public void FALSEDigit() {
		assertTrue(UnicodeTranslator.toAscii("FALSE123").equals("FALSE123"));
		assertTrue(UnicodeTranslator.toAscii("false123").equals("false123"));
	}

	@Test
	public void INTEGERDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER123").equals("INTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("integer123").equals("integer123"));
	}

	@Test
	public void INTERDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTER123").equals("INTER123"));
		assertTrue(UnicodeTranslator.toAscii("inter123").equals("inter123"));
	}

	@Test
	public void NATDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT123").equals("NAT123"));
		assertTrue(UnicodeTranslator.toAscii("nat123").equals("nat123"));
	}

	@Test
	public void NAT1Digit() {
		assertTrue(UnicodeTranslator.toAscii("NAT1123").equals("NAT1123"));
		assertTrue(UnicodeTranslator.toAscii("nat1123").equals("nat1123"));
	}

	@Test
	public void NATURALDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL123").equals("NATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("natural123").equals("natural123"));
	}

	@Test
	public void NOTDigit() {
		assertTrue(UnicodeTranslator.toAscii("not123").equals("not123"));
		assertTrue(UnicodeTranslator.toAscii("NOT123").equals("NOT123"));
	}

	@Test
	public void ORDigit() {
		assertTrue(UnicodeTranslator.toAscii("or123").equals("or123"));
		assertTrue(UnicodeTranslator.toAscii("OR123").equals("OR123"));
	}

	@Test
	public void POWDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW123").equals("POW123"));
		assertTrue(UnicodeTranslator.toAscii("pow123").equals("pow123"));
	}

	@Test
	public void POW1Digit() {
		assertTrue(UnicodeTranslator.toAscii("POW1123").equals("POW1123"));
		assertTrue(UnicodeTranslator.toAscii("pow1123").equals("pow1123"));
	}

	@Test
	public void TRUEDigit() {
		assertTrue(UnicodeTranslator.toAscii("TRUE123").equals("TRUE123"));
		assertTrue(UnicodeTranslator.toAscii("true123").equals("true123"));
	}

	@Test
	public void UNIONDigit() {
		assertTrue(UnicodeTranslator.toAscii("UNION123").equals("UNION123"));
		assertTrue(UnicodeTranslator.toAscii("union123").equals("union123"));
	}

	@Test
	public void ANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ANY_").equals("ANY_"));
		assertTrue(UnicodeTranslator.toAscii("any_").equals("any_"));
	}

	@Test
	public void FALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("FALSE_").equals("FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("false_").equals("false_"));
	}

	@Test
	public void INTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER_").equals("INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("integer_").equals("integer_"));
	}

	@Test
	public void INTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTER_").equals("INTER_"));
		assertTrue(UnicodeTranslator.toAscii("inter_").equals("inter_"));
	}

	@Test
	public void NATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT_").equals("NAT_"));
		assertTrue(UnicodeTranslator.toAscii("nat_").equals("nat_"));
	}

	@Test
	public void NAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT1_").equals("NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("nat1_").equals("nat1_"));
	}

	@Test
	public void NATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL_").equals("NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("natural_").equals("natural_"));
	}

	@Test
	public void NOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NOT_").equals("NOT_"));
		assertTrue(UnicodeTranslator.toAscii("not_").equals("not_"));
	}

	@Test
	public void ORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("OR_").equals("OR_"));
		assertTrue(UnicodeTranslator.toAscii("or_").equals("or_"));
	}

	@Test
	public void POWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW_").equals("POW_"));
		assertTrue(UnicodeTranslator.toAscii("pow_").equals("pow_"));
	}

	@Test
	public void POW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("POW1_").equals("POW1_"));
		assertTrue(UnicodeTranslator.toAscii("pow1_").equals("pow1_"));
	}

	@Test
	public void TRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("TRUE_").equals("TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("true_").equals("true_"));
	}

	@Test
	public void UNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("UNION_").equals("UNION_"));
		assertTrue(UnicodeTranslator.toAscii("union_").equals("union_"));
	}

	@Test
	public void ANYLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("ANYabc123").equals("ANYabc123"));
		assertTrue(UnicodeTranslator.toAscii("anyabc123").equals("anyabc123"));
	}

	@Test
	public void FALSELetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("FALSEabc123").equals(
				"FALSEabc123"));
		assertTrue(UnicodeTranslator.toAscii("falseabc123").equals(
				"falseabc123"));
	}

	@Test
	public void INTEGERLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTEGERabc123").equals(
				"INTEGERabc123"));
		assertTrue(UnicodeTranslator.toAscii("integerabc123").equals(
				"integerabc123"));
	}

	@Test
	public void INTERLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTERabc123").equals(
				"INTERabc123"));
		assertTrue(UnicodeTranslator.toAscii("interabc123").equals(
				"interabc123"));
	}

	@Test
	public void NATLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATabc123").equals("NATabc123"));
		assertTrue(UnicodeTranslator.toAscii("natabc123").equals("natabc123"));
	}

	@Test
	public void NAT1LetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT1abc123").equals("NAT1abc123"));
		assertTrue(UnicodeTranslator.toAscii("nat1abc123").equals("nat1abc123"));
	}

	@Test
	public void NATURALLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATURALabc123").equals(
				"NATURALabc123"));
		assertTrue(UnicodeTranslator.toAscii("naturalabc123").equals(
				"naturalabc123"));
	}

	@Test
	public void NOTLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NOTabc123").equals("NOTabc123"));
		assertTrue(UnicodeTranslator.toAscii("notabc123").equals("notabc123"));
	}

	@Test
	public void ORLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("ORabc123").equals("ORabc123"));
		assertTrue(UnicodeTranslator.toAscii("orabc123").equals("orabc123"));
	}

	@Test
	public void POWLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("POWabc123").equals("POWabc123"));
		assertTrue(UnicodeTranslator.toAscii("powabc123").equals("powabc123"));
	}

	@Test
	public void POW1LetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW1abc123").equals("POW1abc123"));
		assertTrue(UnicodeTranslator.toAscii("pow1abc123").equals("pow1abc123"));
	}

	@Test
	public void TRUELetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("TRUEabc123").equals("TRUEabc123"));
		assertTrue(UnicodeTranslator.toAscii("trueabc123").equals("trueabc123"));
	}

	@Test
	public void UNIONLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("UNIONabc123").equals(
				"UNIONabc123"));
		assertTrue(UnicodeTranslator.toAscii("unionabc123").equals(
				"unionabc123"));
	}

	@Test
	public void ANYLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ANYabc_").equals("ANYabc_"));
		assertTrue(UnicodeTranslator.toAscii("anyabc_").equals("anyabc_"));
	}

	@Test
	public void FALSELetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("FALSEabc_").equals("FALSEabc_"));
		assertTrue(UnicodeTranslator.toAscii("falseabc_").equals("falseabc_"));
	}

	@Test
	public void INTEGERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTEGERabc_").equals(
				"INTEGERabc_"));
		assertTrue(UnicodeTranslator.toAscii("integerabc_").equals(
				"integerabc_"));
	}

	@Test
	public void INTERLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTERabc_").equals("INTERabc_"));
		assertTrue(UnicodeTranslator.toAscii("interabc_").equals("interabc_"));
	}

	@Test
	public void NATLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATabc_").equals("NATabc_"));
		assertTrue(UnicodeTranslator.toAscii("natabc_").equals("natabc_"));
	}

	@Test
	public void NAT1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT1abc_").equals("NAT1abc_"));
		assertTrue(UnicodeTranslator.toAscii("nat1abc_").equals("nat1abc_"));
	}

	@Test
	public void NATURALLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATURALabc_").equals(
				"NATURALabc_"));
		assertTrue(UnicodeTranslator.toAscii("naturalabc_").equals(
				"naturalabc_"));
	}

	@Test
	public void NOTLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NOTabc_").equals("NOTabc_"));
		assertTrue(UnicodeTranslator.toAscii("notabc_").equals("notabc_"));
	}

	@Test
	public void ORLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ORabc_").equals("ORabc_"));
		assertTrue(UnicodeTranslator.toAscii("orabc_").equals("orabc_"));
	}

	@Test
	public void POWLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POWabc_").equals("POWabc_"));
		assertTrue(UnicodeTranslator.toAscii("powabc_").equals("powabc_"));
	}

	@Test
	public void POW1LetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW1abc_").equals("POW1abc_"));
		assertTrue(UnicodeTranslator.toAscii("pow1abc_").equals("pow1abc_"));
	}

	@Test
	public void TRUELetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("TRUEabc_").equals("TRUEabc_"));
		assertTrue(UnicodeTranslator.toAscii("trueabc_").equals("trueabc_"));
	}

	@Test
	public void UNIONLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("UNIONabc_").equals("UNIONabc_"));
		assertTrue(UnicodeTranslator.toAscii("unionabc_").equals("unionabc_"));
	}

	@Test
	public void ANYDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("ANY123abc").equals("ANY123abc"));
		assertTrue(UnicodeTranslator.toAscii("any123abc").equals("any123abc"));
	}

	@Test
	public void FALSEDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("FALSE123abc").equals(
				"FALSE123abc"));
		assertTrue(UnicodeTranslator.toAscii("false123abc").equals(
				"false123abc"));
	}

	@Test
	public void INTEGERDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER123abc").equals(
				"INTEGER123abc"));
		assertTrue(UnicodeTranslator.toAscii("integer123abc").equals(
				"integer123abc"));
	}

	@Test
	public void INTERDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTER123abc").equals(
				"INTER123abc"));
		assertTrue(UnicodeTranslator.toAscii("inter123abc").equals(
				"inter123abc"));
	}

	@Test
	public void NATDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT123abc").equals("NAT123abc"));
		assertTrue(UnicodeTranslator.toAscii("nat123abc").equals("nat123abc"));
	}

	@Test
	public void NAT1DigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT1123abc").equals("NAT1123abc"));
		assertTrue(UnicodeTranslator.toAscii("nat1123abc").equals("nat1123abc"));
	}

	@Test
	public void NATURALDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL123abc").equals(
				"NATURAL123abc"));
		assertTrue(UnicodeTranslator.toAscii("natural123abc").equals(
				"natural123abc"));
	}

	@Test
	public void NOTDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NOT123abc").equals("NOT123abc"));
		assertTrue(UnicodeTranslator.toAscii("not123abc").equals("not123abc"));
	}

	@Test
	public void ORDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("OR123abc").equals("OR123abc"));
		assertTrue(UnicodeTranslator.toAscii("or123abc").equals("or123abc"));
	}

	@Test
	public void POWDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW123abc").equals("POW123abc"));
		assertTrue(UnicodeTranslator.toAscii("pow123abc").equals("pow123abc"));
	}

	@Test
	public void POW1DigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW1123abc").equals("POW1123abc"));
		assertTrue(UnicodeTranslator.toAscii("pow1123abc").equals("pow1123abc"));
	}

	@Test
	public void TRUEDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("TRUE123abc").equals("TRUE123abc"));
		assertTrue(UnicodeTranslator.toAscii("true123abc").equals("true123abc"));
	}

	@Test
	public void UNIONDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("UNION123abc").equals(
				"UNION123abc"));
		assertTrue(UnicodeTranslator.toAscii("union123abc").equals(
				"union123abc"));
	}

	@Test
	public void ANYDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ANY123_").equals("ANY123_"));
		assertTrue(UnicodeTranslator.toAscii("any123_").equals("any123_"));
	}

	@Test
	public void FALSEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("FALSE123_").equals("FALSE123_"));
		assertTrue(UnicodeTranslator.toAscii("false123_").equals("false123_"));
	}

	@Test
	public void INTEGERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER123_").equals(
				"INTEGER123_"));
		assertTrue(UnicodeTranslator.toAscii("integer123_").equals(
				"integer123_"));
	}

	@Test
	public void INTERDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTER123_").equals("INTER123_"));
		assertTrue(UnicodeTranslator.toAscii("inter123_").equals("inter123_"));
	}

	@Test
	public void NATDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT123_").equals("NAT123_"));
		assertTrue(UnicodeTranslator.toAscii("nat123_").equals("nat123_"));
	}

	@Test
	public void NAT1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT1123_").equals("NAT1123_"));
		assertTrue(UnicodeTranslator.toAscii("nat1123_").equals("nat1123_"));
	}

	@Test
	public void NATURALDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL123_").equals(
				"NATURAL123_"));
		assertTrue(UnicodeTranslator.toAscii("natural123_").equals(
				"natural123_"));
	}

	@Test
	public void NOTDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NOT123_").equals("NOT123_"));
		assertTrue(UnicodeTranslator.toAscii("not123_").equals("not123_"));
	}

	@Test
	public void ORDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("OR123_").equals("OR123_"));
		assertTrue(UnicodeTranslator.toAscii("or123_").equals("or123_"));
	}

	@Test
	public void POWDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW123_").equals("POW123_"));
		assertTrue(UnicodeTranslator.toAscii("pow123_").equals("pow123_"));
	}

	@Test
	public void POW1DigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW1123_").equals("POW1123_"));
		assertTrue(UnicodeTranslator.toAscii("pow1123_").equals("pow1123_"));
	}

	@Test
	public void TRUEDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("TRUE123_").equals("TRUE123_"));
		assertTrue(UnicodeTranslator.toAscii("true123_").equals("true123_"));
	}

	@Test
	public void UNIONDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("UNION123_").equals("UNION123_"));
		assertTrue(UnicodeTranslator.toAscii("union123_").equals("union123_"));
	}

	@Test
	public void ANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("ANY_abc").equals("ANY_abc"));
		assertTrue(UnicodeTranslator.toAscii("any_abc").equals("any_abc"));
	}

	@Test
	public void FALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("FALSE_abc").equals("FALSE_abc"));
		assertTrue(UnicodeTranslator.toAscii("false_abc").equals("false_abc"));
	}

	@Test
	public void INTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER_abc").equals(
				"INTEGER_abc"));
		assertTrue(UnicodeTranslator.toAscii("integer_abc").equals(
				"integer_abc"));
	}

	@Test
	public void INTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTER_abc").equals("INTER_abc"));
		assertTrue(UnicodeTranslator.toAscii("inter_abc").equals("inter_abc"));
	}

	@Test
	public void NATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT_abc").equals("NAT_abc"));
		assertTrue(UnicodeTranslator.toAscii("nat_abc").equals("nat_abc"));
	}

	@Test
	public void NAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT1_abc").equals("NAT1_abc"));
		assertTrue(UnicodeTranslator.toAscii("nat1_abc").equals("nat1_abc"));
	}

	@Test
	public void NATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL_abc").equals(
				"NATURAL_abc"));
		assertTrue(UnicodeTranslator.toAscii("natural_abc").equals(
				"natural_abc"));
	}

	@Test
	public void NOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NOT_abc").equals("NOT_abc"));
		assertTrue(UnicodeTranslator.toAscii("not_abc").equals("not_abc"));
	}

	@Test
	public void ORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("OR_abc").equals("OR_abc"));
		assertTrue(UnicodeTranslator.toAscii("or_abc").equals("or_abc"));
	}

	@Test
	public void POWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW_abc").equals("POW_abc"));
		assertTrue(UnicodeTranslator.toAscii("pow_abc").equals("pow_abc"));
	}

	@Test
	public void POW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW1_abc").equals("POW1_abc"));
		assertTrue(UnicodeTranslator.toAscii("pow1_abc").equals("pow1_abc"));
	}

	@Test
	public void TRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("TRUE_abc").equals("TRUE_abc"));
		assertTrue(UnicodeTranslator.toAscii("true_abc").equals("true_abc"));
	}

	@Test
	public void UNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("UNION_abc").equals("UNION_abc"));
		assertTrue(UnicodeTranslator.toAscii("union_abc").equals("union_abc"));
	}

	@Test
	public void ANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("ANY_123").equals("ANY_123"));
		assertTrue(UnicodeTranslator.toAscii("any_123").equals("any_123"));
	}

	@Test
	public void FALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("FALSE_123").equals("FALSE_123"));
		assertTrue(UnicodeTranslator.toAscii("false_123").equals("false_123"));
	}

	@Test
	public void INTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER_123").equals(
				"INTEGER_123"));
		assertTrue(UnicodeTranslator.toAscii("integer_123").equals(
				"integer_123"));
	}

	@Test
	public void INTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTER_123").equals("INTER_123"));
		assertTrue(UnicodeTranslator.toAscii("inter_123").equals("inter_123"));
	}

	@Test
	public void NATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT_123").equals("NAT_123"));
		assertTrue(UnicodeTranslator.toAscii("nat_123").equals("nat_123"));
	}

	@Test
	public void NAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT1_123").equals("NAT1_123"));
		assertTrue(UnicodeTranslator.toAscii("nat1_123").equals("nat1_123"));
	}

	@Test
	public void NATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL_123").equals(
				"NATURAL_123"));
		assertTrue(UnicodeTranslator.toAscii("natural_123").equals(
				"natural_123"));
	}

	@Test
	public void NOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("not_123").equals("not_123"));
		assertTrue(UnicodeTranslator.toAscii("NOT_123").equals("NOT_123"));
	}

	@Test
	public void ORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("or_123").equals("or_123"));
		assertTrue(UnicodeTranslator.toAscii("OR_123").equals("OR_123"));
	}

	@Test
	public void POWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW_123").equals("POW_123"));
		assertTrue(UnicodeTranslator.toAscii("pow_123").equals("pow_123"));
	}

	@Test
	public void POW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW1_123").equals("POW1_123"));
		assertTrue(UnicodeTranslator.toAscii("pow1_123").equals("pow1_123"));
	}

	@Test
	public void TRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("TRUE_123").equals("TRUE_123"));
		assertTrue(UnicodeTranslator.toAscii("true_123").equals("true_123"));
	}

	@Test
	public void UNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("UNION_123").equals("UNION_123"));
		assertTrue(UnicodeTranslator.toAscii("union_123").equals("union_123"));
	}

	@Test
	public void ANYLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ANYabc123_").equals("ANYabc123_"));
		assertTrue(UnicodeTranslator.toAscii("anyabc123_").equals("anyabc123_"));
	}

	@Test
	public void FALSELetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("FALSEabc123_").equals(
				"FALSEabc123_"));
		assertTrue(UnicodeTranslator.toAscii("falseabc123_").equals(
				"falseabc123_"));
	}

	@Test
	public void INTEGERLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTEGERabc123_").equals(
				"INTEGERabc123_"));
		assertTrue(UnicodeTranslator.toAscii("integerabc123_").equals(
				"integerabc123_"));
	}

	@Test
	public void INTERLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTERabc123_").equals(
				"INTERabc123_"));
		assertTrue(UnicodeTranslator.toAscii("interabc123_").equals(
				"interabc123_"));
	}

	@Test
	public void NATLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATabc123_").equals("NATabc123_"));
		assertTrue(UnicodeTranslator.toAscii("natabc123_").equals("natabc123_"));
	}

	@Test
	public void NAT1LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT1abc123_").equals(
				"NAT1abc123_"));
		assertTrue(UnicodeTranslator.toAscii("nat1abc123_").equals(
				"nat1abc123_"));
	}

	@Test
	public void NATURALLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATURALabc123_").equals(
				"NATURALabc123_"));
		assertTrue(UnicodeTranslator.toAscii("naturalabc123_").equals(
				"naturalabc123_"));
	}

	@Test
	public void NOTLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NOTabc123_").equals("NOTabc123_"));
		assertTrue(UnicodeTranslator.toAscii("notabc123_").equals("notabc123_"));
	}

	@Test
	public void ORLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ORabc123_").equals("ORabc123_"));
		assertTrue(UnicodeTranslator.toAscii("orabc123_").equals("orabc123_"));
	}

	@Test
	public void POWLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POWabc123_").equals("POWabc123_"));
		assertTrue(UnicodeTranslator.toAscii("powabc123_").equals("powabc123_"));
	}

	@Test
	public void POW1LetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW1abc123_").equals(
				"POW1abc123_"));
		assertTrue(UnicodeTranslator.toAscii("pow1abc123_").equals(
				"pow1abc123_"));
	}

	@Test
	public void TRUELetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("TRUEabc123_").equals(
				"TRUEabc123_"));
		assertTrue(UnicodeTranslator.toAscii("trueabc123_").equals(
				"trueabc123_"));
	}

	@Test
	public void UNIONLetterDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("UNIONabc123_").equals(
				"UNIONabc123_"));
		assertTrue(UnicodeTranslator.toAscii("unionabc123_").equals(
				"unionabc123_"));
	}

	@Test
	public void ANYLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("ANYabc_123").equals("ANYabc_123"));
		assertTrue(UnicodeTranslator.toAscii("anyabc_123").equals("anyabc_123"));
	}

	@Test
	public void FALSELetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("FALSEabc_123").equals(
				"FALSEabc_123"));
		assertTrue(UnicodeTranslator.toAscii("falseabc_123").equals(
				"falseabc_123"));
	}

	@Test
	public void INTEGERLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTEGERabc_123").equals(
				"INTEGERabc_123"));
		assertTrue(UnicodeTranslator.toAscii("integerabc_123").equals(
				"integerabc_123"));
	}

	@Test
	public void INTERLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTERabc_123").equals(
				"INTERabc_123"));
		assertTrue(UnicodeTranslator.toAscii("interabc_123").equals(
				"interabc_123"));
	}

	@Test
	public void NATLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATabc_123").equals("NATabc_123"));
		assertTrue(UnicodeTranslator.toAscii("natabc_123").equals("natabc_123"));
	}

	@Test
	public void NAT1LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT1abc_123").equals(
				"NAT1abc_123"));
		assertTrue(UnicodeTranslator.toAscii("nat1abc_123").equals(
				"nat1abc_123"));
	}

	@Test
	public void NATURALLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATURALabc_123").equals(
				"NATURALabc_123"));
		assertTrue(UnicodeTranslator.toAscii("naturalabc_123").equals(
				"naturalabc_123"));
	}

	@Test
	public void NOTLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("NOTabc_123").equals("NOTabc_123"));
		assertTrue(UnicodeTranslator.toAscii("notabc_123").equals("notabc_123"));
	}

	@Test
	public void ORLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("ORabc_123").equals("ORabc_123"));
		assertTrue(UnicodeTranslator.toAscii("orabc_123").equals("orabc_123"));
	}

	@Test
	public void POWLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("POWabc_123").equals("POWabc_123"));
		assertTrue(UnicodeTranslator.toAscii("powabc_123").equals("powabc_123"));
	}

	@Test
	public void POW1LetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW1abc_123").equals(
				"POW1abc_123"));
		assertTrue(UnicodeTranslator.toAscii("pow1abc_123").equals(
				"pow1abc_123"));
	}

	@Test
	public void TRUELetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("TRUEabc_123").equals(
				"TRUEabc_123"));
		assertTrue(UnicodeTranslator.toAscii("trueabc_123").equals(
				"trueabc_123"));
	}

	@Test
	public void UNIONLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("UNIONabc_123").equals(
				"UNIONabc_123"));
		assertTrue(UnicodeTranslator.toAscii("unionabc_123").equals(
				"unionabc_123"));
	}

	@Test
	public void ANYDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("ANY123abc_").equals("ANY123abc_"));
		assertTrue(UnicodeTranslator.toAscii("any123abc_").equals("any123abc_"));
	}

	@Test
	public void FALSEDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("FALSE123abc_").equals(
				"FALSE123abc_"));
		assertTrue(UnicodeTranslator.toAscii("false123abc_").equals(
				"false123abc_"));
	}

	@Test
	public void INTEGERDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER123abc_").equals(
				"INTEGER123abc_"));
		assertTrue(UnicodeTranslator.toAscii("integer123abc_").equals(
				"integer123abc_"));
	}

	@Test
	public void INTERDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("INTER123abc_").equals(
				"INTER123abc_"));
		assertTrue(UnicodeTranslator.toAscii("inter123abc_").equals(
				"inter123abc_"));
	}

	@Test
	public void NATDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT123abc_").equals("NAT123abc_"));
		assertTrue(UnicodeTranslator.toAscii("nat123abc_").equals("nat123abc_"));
	}

	@Test
	public void NAT1DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NAT1123abc_").equals(
				"NAT1123abc_"));
		assertTrue(UnicodeTranslator.toAscii("nat1123abc_").equals(
				"nat1123abc_"));
	}

	@Test
	public void NATURALDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL123abc_").equals(
				"NATURAL123abc_"));
		assertTrue(UnicodeTranslator.toAscii("natural123abc_").equals(
				"natural123abc_"));
	}

	@Test
	public void NOTDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("NOT123abc_").equals("NOT123abc_"));
		assertTrue(UnicodeTranslator.toAscii("not123abc_").equals("not123abc_"));
	}

	@Test
	public void ORDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("OR123abc_").equals("OR123abc_"));
		assertTrue(UnicodeTranslator.toAscii("or123abc_").equals("or123abc_"));
	}

	@Test
	public void POWDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW123abc_").equals("POW123abc_"));
		assertTrue(UnicodeTranslator.toAscii("pow123abc_").equals("pow123abc_"));
	}

	@Test
	public void POW1DigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("POW1123abc_").equals(
				"POW1123abc_"));
		assertTrue(UnicodeTranslator.toAscii("pow1123abc_").equals(
				"pow1123abc_"));
	}

	@Test
	public void TRUEDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("TRUE123abc_").equals(
				"TRUE123abc_"));
		assertTrue(UnicodeTranslator.toAscii("true123abc_").equals(
				"true123abc_"));
	}

	@Test
	public void UNIONDigitLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("UNION123abc_").equals(
				"UNION123abc_"));
		assertTrue(UnicodeTranslator.toAscii("union123abc_").equals(
				"union123abc_"));
	}

	@Test
	public void ANYDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("ANY123_abc").equals("ANY123_abc"));
		assertTrue(UnicodeTranslator.toAscii("any123_abc").equals("any123_abc"));
	}

	@Test
	public void FALSEDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("FALSE123_abc").equals(
				"FALSE123_abc"));
		assertTrue(UnicodeTranslator.toAscii("false123_abc").equals(
				"false123_abc"));
	}

	@Test
	public void INTEGERDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER123_abc").equals(
				"INTEGER123_abc"));
		assertTrue(UnicodeTranslator.toAscii("integer123_abc").equals(
				"integer123_abc"));
	}

	@Test
	public void INTERDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTER123_abc").equals(
				"INTER123_abc"));
		assertTrue(UnicodeTranslator.toAscii("inter123_abc").equals(
				"inter123_abc"));
	}

	@Test
	public void NATDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT123_abc").equals("NAT123_abc"));
		assertTrue(UnicodeTranslator.toAscii("nat123_abc").equals("nat123_abc"));
	}

	@Test
	public void NAT1DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT1123_abc").equals(
				"NAT1123_abc"));
		assertTrue(UnicodeTranslator.toAscii("nat1123_abc").equals(
				"nat1123_abc"));
	}

	@Test
	public void NATURALDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL123_abc").equals(
				"NATURAL123_abc"));
		assertTrue(UnicodeTranslator.toAscii("natural123_abc").equals(
				"natural123_abc"));
	}

	@Test
	public void NOTDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("NOT123_abc").equals("NOT123_abc"));
		assertTrue(UnicodeTranslator.toAscii("not123_abc").equals("not123_abc"));
	}

	@Test
	public void ORDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("OR123_abc").equals("OR123_abc"));
		assertTrue(UnicodeTranslator.toAscii("or123_abc").equals("or123_abc"));
	}

	@Test
	public void POWDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW123_abc").equals("POW123_abc"));
		assertTrue(UnicodeTranslator.toAscii("pow123_abc").equals("pow123_abc"));
	}

	@Test
	public void POW1DigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW1123_abc").equals(
				"POW1123_abc"));
		assertTrue(UnicodeTranslator.toAscii("pow1123_abc").equals(
				"pow1123_abc"));
	}

	@Test
	public void TRUEDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("TRUE123_abc").equals(
				"TRUE123_abc"));
		assertTrue(UnicodeTranslator.toAscii("true123_abc").equals(
				"true123_abc"));
	}

	@Test
	public void UNIONDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("UNION123_abc").equals(
				"UNION123_abc"));
		assertTrue(UnicodeTranslator.toAscii("union123_abc").equals(
				"union123_abc"));
	}

	@Test
	public void ANYUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("ANY_abc123").equals("ANY_abc123"));
		assertTrue(UnicodeTranslator.toAscii("any_abc123").equals("any_abc123"));
	}

	@Test
	public void FALSEUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("FALSE_abc123").equals(
				"FALSE_abc123"));
		assertTrue(UnicodeTranslator.toAscii("false_abc123").equals(
				"false_abc123"));
	}

	@Test
	public void INTEGERUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER_abc123").equals(
				"INTEGER_abc123"));
		assertTrue(UnicodeTranslator.toAscii("integer_abc123").equals(
				"integer_abc123"));
	}

	@Test
	public void INTERUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("INTER_abc123").equals(
				"INTER_abc123"));
		assertTrue(UnicodeTranslator.toAscii("inter_abc123").equals(
				"inter_abc123"));
	}

	@Test
	public void NATUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT_abc123").equals("NAT_abc123"));
		assertTrue(UnicodeTranslator.toAscii("nat_abc123").equals("nat_abc123"));
	}

	@Test
	public void NAT1UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NAT1_abc123").equals(
				"NAT1_abc123"));
		assertTrue(UnicodeTranslator.toAscii("nat1_abc123").equals(
				"nat1_abc123"));
	}

	@Test
	public void NATURALUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL_abc123").equals(
				"NATURAL_abc123"));
		assertTrue(UnicodeTranslator.toAscii("natural_abc123").equals(
				"natural_abc123"));
	}

	@Test
	public void NOTUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("NOT_abc123").equals("NOT_abc123"));
		assertTrue(UnicodeTranslator.toAscii("not_abc123").equals("not_abc123"));
	}

	@Test
	public void ORUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("OR_abc123").equals("OR_abc123"));
		assertTrue(UnicodeTranslator.toAscii("or_abc123").equals("or_abc123"));
	}

	@Test
	public void POWUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW_abc123").equals("POW_abc123"));
		assertTrue(UnicodeTranslator.toAscii("pow_abc123").equals("pow_abc123"));
	}

	@Test
	public void POW1UnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("POW1_abc123").equals(
				"POW1_abc123"));
		assertTrue(UnicodeTranslator.toAscii("pow1_abc123").equals(
				"pow1_abc123"));
	}

	@Test
	public void TRUEUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("TRUE_abc123").equals(
				"TRUE_abc123"));
		assertTrue(UnicodeTranslator.toAscii("true_abc123").equals(
				"true_abc123"));
	}

	@Test
	public void UNIONUnderscoreLetterDigit() {
		assertTrue(UnicodeTranslator.toAscii("UNION_abc123").equals(
				"UNION_abc123"));
		assertTrue(UnicodeTranslator.toAscii("union_abc123").equals(
				"union_abc123"));
	}

	@Test
	public void ANYUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("ANY_123abc").equals("ANY_123abc"));
		assertTrue(UnicodeTranslator.toAscii("any_123abc").equals("any_123abc"));
	}

	@Test
	public void FALSEUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("FALSE_123abc").equals(
				"FALSE_123abc"));
		assertTrue(UnicodeTranslator.toAscii("false_123abc").equals(
				"false_123abc"));
	}

	@Test
	public void INTEGERUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTEGER_123abc").equals(
				"INTEGER_123abc"));
		assertTrue(UnicodeTranslator.toAscii("integer_123abc").equals(
				"integer_123abc"));
	}

	@Test
	public void INTERUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("INTER_123abc").equals(
				"INTER_123abc"));
		assertTrue(UnicodeTranslator.toAscii("inter_123abc").equals(
				"inter_123abc"));
	}

	@Test
	public void NATUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT_123abc").equals("NAT_123abc"));
		assertTrue(UnicodeTranslator.toAscii("nat_123abc").equals("nat_123abc"));
	}

	@Test
	public void NAT1UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NAT1_123abc").equals(
				"NAT1_123abc"));
		assertTrue(UnicodeTranslator.toAscii("nat1_123abc").equals(
				"nat1_123abc"));
	}

	@Test
	public void NATURALUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NATURAL_123abc").equals(
				"NATURAL_123abc"));
		assertTrue(UnicodeTranslator.toAscii("natural_123abc").equals(
				"natural_123abc"));
	}

	@Test
	public void NOTUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("NOT_123abc").equals("NOT_123abc"));
		assertTrue(UnicodeTranslator.toAscii("not_123abc").equals("not_123abc"));
	}

	@Test
	public void ORUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("OR_123abc").equals("OR_123abc"));
		assertTrue(UnicodeTranslator.toAscii("or_123abc").equals("or_123abc"));
	}

	@Test
	public void POWUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW_123abc").equals("POW_123abc"));
		assertTrue(UnicodeTranslator.toAscii("pow_123abc").equals("pow_123abc"));
	}

	@Test
	public void POW1UnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("POW1_123abc").equals(
				"POW1_123abc"));
		assertTrue(UnicodeTranslator.toAscii("pow1_123abc").equals(
				"pow1_123abc"));
	}

	@Test
	public void TRUEUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("TRUE_123abc").equals(
				"TRUE_123abc"));
		assertTrue(UnicodeTranslator.toAscii("true_123abc").equals(
				"true_123abc"));
	}

	@Test
	public void UNIONUnderscoreDigitLetter() {
		assertTrue(UnicodeTranslator.toAscii("UNION_123abc").equals(
				"UNION_123abc"));
		assertTrue(UnicodeTranslator.toAscii("union_123abc").equals(
				"union_123abc"));
	}

	@Test
	public void UnderscoreDigitUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_123_").equals("_123_"));
	}

	@Test
	public void UnderscoreLetterUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_abc_").equals("_abc_"));
	}

	@Test
	public void UnderscoreANYUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_ANY_").equals("_ANY_"));
		assertTrue(UnicodeTranslator.toAscii("_any_").equals("_any_"));
	}

	@Test
	public void UnderscoreFALSEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_FALSE_").equals("_FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("_false_").equals("_false_"));
	}

	@Test
	public void UnderscoreINTEGERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_INTEGER_").equals("_INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("_integer_").equals("_integer_"));
	}

	@Test
	public void UnderscoreINTERUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_INTER_").equals("_INTER_"));
		assertTrue(UnicodeTranslator.toAscii("_inter_").equals("_inter_"));
	}

	@Test
	public void UnderscoreNATUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_NAT_").equals("_NAT_"));
		assertTrue(UnicodeTranslator.toAscii("_nat_").equals("_nat_"));
	}

	@Test
	public void UnderscoreNAT1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("_NAT1_").equals("_NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("_nat1_").equals("_nat1_"));
	}

	@Test
	public void UnderscoreNATURALUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_NATURAL_").equals("_NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("_natural_").equals("_natural_"));
	}

	@Test
	public void UnderscoreNOTUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_NOT_").equals("_NOT_"));
		assertTrue(UnicodeTranslator.toAscii("_not_").equals("_not_"));
	}

	@Test
	public void UnderscoreORUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_OR_").equals("_OR_"));
		assertTrue(UnicodeTranslator.toAscii("_or_").equals("_or_"));
	}

	@Test
	public void UnderscorePOWUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_POW_").equals("_POW_"));
		assertTrue(UnicodeTranslator.toAscii("_pow_").equals("_pow_"));
	}

	@Test
	public void UnderscorePOW1Underscore() {
		assertTrue(UnicodeTranslator.toAscii("_POW1_").equals("_POW1_"));
		assertTrue(UnicodeTranslator.toAscii("_pow1_").equals("_pow1_"));
	}

	@Test
	public void UnderscoreTRUEUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_TRUE_").equals("_TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("_true_").equals("_true_"));
	}

	@Test
	public void UnderscoreUNIONUnderscore() {
		assertTrue(UnicodeTranslator.toAscii("_UNION_").equals("_UNION_"));
		assertTrue(UnicodeTranslator.toAscii("_union_").equals("_union_"));
	}

	@Test
	public void LetterUnderscoreDigitUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_123_abc").equals(
				"abc_123_abc"));
	}

	@Test
	public void LetterUnderscoreLetterUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_abc_abc").equals(
				"abc_abc_abc"));
	}

	@Test
	public void LetterUnderscoreANYUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_ANY_abc").equals(
				"abc_ANY_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_any_abc").equals(
				"abc_any_abc"));
	}

	@Test
	public void LetterUnderscoreFALSEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_FALSE_abc").equals(
				"abc_FALSE_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_false_abc").equals(
				"abc_false_abc"));
	}

	@Test
	public void LetterUnderscoreINTEGERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTEGER_abc").equals(
				"abc_INTEGER_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_integer_abc").equals(
				"abc_integer_abc"));
	}

	@Test
	public void LetterUnderscoreINTERUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_INTER_abc").equals(
				"abc_INTER_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_inter_abc").equals(
				"abc_inter_abc"));
	}

	@Test
	public void LetterUnderscoreNATUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT_abc").equals(
				"abc_NAT_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat_abc").equals(
				"abc_nat_abc"));
	}

	@Test
	public void LetterUnderscoreNAT1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_NAT1_abc").equals(
				"abc_NAT1_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_nat1_abc").equals(
				"abc_nat1_abc"));
	}

	@Test
	public void LetterUnderscoreNATURALUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_NATURAL_abc").equals(
				"abc_NATURAL_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_natural_abc").equals(
				"abc_natural_abc"));
	}

	@Test
	public void LetterUnderscoreNOTUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_NOT_abc").equals(
				"abc_NOT_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_not_abc").equals(
				"abc_not_abc"));
	}

	@Test
	public void LetterUnderscoreORUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_OR_abc").equals("abc_OR_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_or_abc").equals("abc_or_abc"));
	}

	@Test
	public void LetterUnderscorePOWUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_POW_abc").equals(
				"abc_POW_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_pow_abc").equals(
				"abc_pow_abc"));
	}

	@Test
	public void LetterUnderscorePOW1UnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_POW1_abc").equals(
				"abc_POW1_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_pow1_abc").equals(
				"abc_pow1_abc"));
	}

	@Test
	public void LetterUnderscoreTRUEUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_TRUE_abc").equals(
				"abc_TRUE_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_true_abc").equals(
				"abc_true_abc"));
	}

	@Test
	public void LetterUnderscoreUNIONUnderscoreLetter() {
		assertTrue(UnicodeTranslator.toAscii("abc_UNION_abc").equals(
				"abc_UNION_abc"));
		assertTrue(UnicodeTranslator.toAscii("abc_union_abc").equals(
				"abc_union_abc"));
	}

	@Test
	public void DigitUnderscoreDigitUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_123_123").equals(
				"123_123_123"));
	}

	@Test
	public void DigitUnderscoreLetterUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_abc_123").equals(
				"123_abc_123"));
	}

	@Test
	public void DigitUnderscoreANYUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_ANY_123").equals(
				"123_ANY_123"));
		assertTrue(UnicodeTranslator.toAscii("123_any_123").equals(
				"123_any_123"));
	}

	@Test
	public void DigitUnderscoreFALSEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_FALSE_123").equals(
				"123_FALSE_123"));
		assertTrue(UnicodeTranslator.toAscii("123_false_123").equals(
				"123_false_123"));
	}

	@Test
	public void DigitUnderscoreINTEGERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_INTEGER_123").equals(
				"123_INTEGER_123"));
		assertTrue(UnicodeTranslator.toAscii("123_integer_123").equals(
				"123_integer_123"));
	}

	@Test
	public void DigitUnderscoreINTERUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_INTER_123").equals(
				"123_INTER_123"));
		assertTrue(UnicodeTranslator.toAscii("123_inter_123").equals(
				"123_inter_123"));
	}

	@Test
	public void DigitUnderscoreNATUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_NAT_123").equals(
				"123_NAT_123"));
		assertTrue(UnicodeTranslator.toAscii("123_nat_123").equals(
				"123_nat_123"));
	}

	@Test
	public void DigitUnderscoreNAT1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_NAT1_123").equals(
				"123_NAT1_123"));
		assertTrue(UnicodeTranslator.toAscii("123_nat1_123").equals(
				"123_nat1_123"));
	}

	@Test
	public void DigitUnderscoreNATURALUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_NATURAL_123").equals(
				"123_NATURAL_123"));
		assertTrue(UnicodeTranslator.toAscii("123_natural_123").equals(
				"123_natural_123"));
	}

	@Test
	public void DigitUnderscoreNOTUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_NOT_123").equals(
				"123_NOT_123"));
		assertTrue(UnicodeTranslator.toAscii("123_not_123").equals(
				"123_not_123"));
	}

	@Test
	public void DigitUnderscoreORUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_OR_123").equals("123_OR_123"));
		assertTrue(UnicodeTranslator.toAscii("123_or_123").equals("123_or_123"));
	}

	@Test
	public void DigitUnderscorePOWUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_POW_123").equals(
				"123_POW_123"));
		assertTrue(UnicodeTranslator.toAscii("123_pow_123").equals(
				"123_pow_123"));
	}

	@Test
	public void DigitUnderscorePOW1UnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_POW1_123").equals(
				"123_POW1_123"));
		assertTrue(UnicodeTranslator.toAscii("123_pow1_123").equals(
				"123_pow1_123"));
	}

	@Test
	public void DigitUnderscoreTRUEUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_TRUE_123").equals(
				"123_TRUE_123"));
		assertTrue(UnicodeTranslator.toAscii("123_true_123").equals(
				"123_true_123"));
	}

	@Test
	public void DigitUnderscoreUNIONUnderscoreDigit() {
		assertTrue(UnicodeTranslator.toAscii("123_UNION_123").equals(
				"123_UNION_123"));
		assertTrue(UnicodeTranslator.toAscii("123_union_123").equals(
				"123_union_123"));
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Var_123() {
		assertTrue(UnicodeTranslator.toAscii("var_123").equals("var_123"));
		assertTrue(UnicodeTranslator.toAscii("123_var").equals("123_var"));
		assertTrue(UnicodeTranslator.toAscii("var_123_var").equals(
				"var_123_var"));

		assertTrue(UnicodeTranslator.toAscii("var_").equals("var_"));
		assertTrue(UnicodeTranslator.toAscii("_var").equals("_var"));
		assertTrue(UnicodeTranslator.toAscii("_var_").equals("_var_"));

		assertTrue(UnicodeTranslator.toAscii("123_").equals("123_"));
		assertTrue(UnicodeTranslator.toAscii("_123").equals("_123"));
		assertTrue(UnicodeTranslator.toAscii("_123_").equals("_123_"));
	}

	@Test
	public void Var123() {
		assertTrue(UnicodeTranslator.toAscii("var123").equals("var123"));
		assertTrue(UnicodeTranslator.toAscii("123var").equals("123var"));
		assertTrue(UnicodeTranslator.toAscii("var123var").equals("var123var"));
		assertTrue(UnicodeTranslator.toAscii("123var123").equals("123var123"));
	}

	@Test
	public void VarANY() {
		assertTrue(UnicodeTranslator.toAscii("varANY").equals("varANY"));
		assertTrue(UnicodeTranslator.toAscii("varany").equals("varany"));
		assertTrue(UnicodeTranslator.toAscii("varANYvar").equals("varANYvar"));
		assertTrue(UnicodeTranslator.toAscii("varanyvar").equals("varanyvar"));
		assertTrue(UnicodeTranslator.toAscii("ANYvar").equals("ANYvar"));
		assertTrue(UnicodeTranslator.toAscii("anyvar").equals("anyvar"));

		assertTrue(UnicodeTranslator.toAscii("123any").equals("123any"));
		assertTrue(UnicodeTranslator.toAscii("123ANY").equals("123ANY"));
		assertTrue(UnicodeTranslator.toAscii("123ANY123").equals("123ANY123"));
		assertTrue(UnicodeTranslator.toAscii("123any123").equals("123any123"));
		assertTrue(UnicodeTranslator.toAscii("ANY123").equals("ANY123"));
		assertTrue(UnicodeTranslator.toAscii("any123").equals("any123"));

		assertTrue(UnicodeTranslator.toAscii("_any").equals("_any"));
		assertTrue(UnicodeTranslator.toAscii("_ANY").equals("_ANY"));
		assertTrue(UnicodeTranslator.toAscii("_ANY_").equals("_ANY_"));
		assertTrue(UnicodeTranslator.toAscii("_any_").equals("_any_"));
		assertTrue(UnicodeTranslator.toAscii("ANY_").equals("ANY_"));
		assertTrue(UnicodeTranslator.toAscii("any_").equals("any_"));
	}

	@Test
	public void VarFALSE() {
		assertTrue(UnicodeTranslator.toAscii("varFALSE").equals("varFALSE"));
		assertTrue(UnicodeTranslator.toAscii("varfalse").equals("varfalse"));
		assertTrue(UnicodeTranslator.toAscii("varFALSEvar").equals(
				"varFALSEvar"));
		assertTrue(UnicodeTranslator.toAscii("varfalsevar").equals(
				"varfalsevar"));
		assertTrue(UnicodeTranslator.toAscii("FALSEvar").equals("FALSEvar"));
		assertTrue(UnicodeTranslator.toAscii("falsevar").equals("falsevar"));

		assertTrue(UnicodeTranslator.toAscii("123FALSE").equals("123FALSE"));
		assertTrue(UnicodeTranslator.toAscii("123false").equals("123false"));
		assertTrue(UnicodeTranslator.toAscii("123FALSE123").equals(
				"123FALSE123"));
		assertTrue(UnicodeTranslator.toAscii("123false123").equals(
				"123false123"));
		assertTrue(UnicodeTranslator.toAscii("FALSE123").equals("FALSE123"));
		assertTrue(UnicodeTranslator.toAscii("false123").equals("false123"));

		assertTrue(UnicodeTranslator.toAscii("_FALSE").equals("_FALSE"));
		assertTrue(UnicodeTranslator.toAscii("_false").equals("_false"));
		assertTrue(UnicodeTranslator.toAscii("_FALSE_").equals("_FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("_false_").equals("_false_"));
		assertTrue(UnicodeTranslator.toAscii("FALSE_").equals("FALSE_"));
		assertTrue(UnicodeTranslator.toAscii("false_").equals("false_"));
	}

	@Test
	public void VarINTEGER() {
		assertTrue(UnicodeTranslator.toAscii("varINTEGER").equals("varINTEGER"));
		assertTrue(UnicodeTranslator.toAscii("varinteger").equals("varinteger"));
		assertTrue(UnicodeTranslator.toAscii("varINTEGERvar").equals(
				"varINTEGERvar"));
		assertTrue(UnicodeTranslator.toAscii("varintegervar").equals(
				"varintegervar"));
		assertTrue(UnicodeTranslator.toAscii("INTEGERvar").equals("INTEGERvar"));
		assertTrue(UnicodeTranslator.toAscii("integervar").equals("integervar"));

		assertTrue(UnicodeTranslator.toAscii("123INTEGER").equals("123INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("123integer").equals("123integer"));
		assertTrue(UnicodeTranslator.toAscii("INTEGER123").equals("INTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("integer123").equals("integer123"));
		assertTrue(UnicodeTranslator.toAscii("123INTEGER123").equals(
				"123INTEGER123"));
		assertTrue(UnicodeTranslator.toAscii("123integer123").equals(
				"123integer123"));

		assertTrue(UnicodeTranslator.toAscii("_INTEGER").equals("_INTEGER"));
		assertTrue(UnicodeTranslator.toAscii("_integer").equals("_integer"));
		assertTrue(UnicodeTranslator.toAscii("_INTEGER_").equals("_INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("_integer_").equals("_integer_"));
		assertTrue(UnicodeTranslator.toAscii("INTEGER_").equals("INTEGER_"));
		assertTrue(UnicodeTranslator.toAscii("integer_").equals("integer_"));
	}

	@Test
	public void VarINTER() {
		assertTrue(UnicodeTranslator.toAscii("varINTER").equals("varINTER"));
		assertTrue(UnicodeTranslator.toAscii("varinter").equals("varinter"));
		assertTrue(UnicodeTranslator.toAscii("varINTERvar").equals(
				"varINTERvar"));
		assertTrue(UnicodeTranslator.toAscii("varintervar").equals(
				"varintervar"));
		assertTrue(UnicodeTranslator.toAscii("INTERvar").equals("INTERvar"));
		assertTrue(UnicodeTranslator.toAscii("intervar").equals("intervar"));

		assertTrue(UnicodeTranslator.toAscii("123inter").equals("123inter"));
		assertTrue(UnicodeTranslator.toAscii("123INTER").equals("123INTER"));
		assertTrue(UnicodeTranslator.toAscii("123INTER123").equals(
				"123INTER123"));
		assertTrue(UnicodeTranslator.toAscii("123inter123").equals(
				"123inter123"));
		assertTrue(UnicodeTranslator.toAscii("INTER123").equals("INTER123"));
		assertTrue(UnicodeTranslator.toAscii("inter123").equals("inter123"));

		assertTrue(UnicodeTranslator.toAscii("_INTER").equals("_INTER"));
		assertTrue(UnicodeTranslator.toAscii("_inter").equals("_inter"));
		assertTrue(UnicodeTranslator.toAscii("_INTER_").equals("_INTER_"));
		assertTrue(UnicodeTranslator.toAscii("_inter_").equals("_inter_"));
		assertTrue(UnicodeTranslator.toAscii("INTER_").equals("INTER_"));
		assertTrue(UnicodeTranslator.toAscii("inter_").equals("inter_"));
	}

	@Test
	public void VarNAT() {
		assertTrue(UnicodeTranslator.toAscii("varNAT").equals("varNAT"));
		assertTrue(UnicodeTranslator.toAscii("varnat").equals("varnat"));
		assertTrue(UnicodeTranslator.toAscii("varNATvar").equals("varNATvar"));
		assertTrue(UnicodeTranslator.toAscii("varnatvar").equals("varnatvar"));
		assertTrue(UnicodeTranslator.toAscii("NATvar").equals("NATvar"));
		assertTrue(UnicodeTranslator.toAscii("natvar").equals("natvar"));

		assertTrue(UnicodeTranslator.toAscii("123NAT").equals("123NAT"));
		assertTrue(UnicodeTranslator.toAscii("123nat").equals("123nat"));
		assertTrue(UnicodeTranslator.toAscii("123NAT123").equals("123NAT123"));
		assertTrue(UnicodeTranslator.toAscii("123nat123").equals("123nat123"));
		assertTrue(UnicodeTranslator.toAscii("NAT123").equals("NAT123"));
		assertTrue(UnicodeTranslator.toAscii("nat123").equals("nat123"));

		assertTrue(UnicodeTranslator.toAscii("_NAT").equals("_NAT"));
		assertTrue(UnicodeTranslator.toAscii("_nat").equals("_nat"));
		assertTrue(UnicodeTranslator.toAscii("_NAT_").equals("_NAT_"));
		assertTrue(UnicodeTranslator.toAscii("_nat_").equals("_nat_"));
		assertTrue(UnicodeTranslator.toAscii("NAT_").equals("NAT_"));
		assertTrue(UnicodeTranslator.toAscii("nat_").equals("nat_"));
	}

	@Test
	public void VarNAT1() {
		assertTrue(UnicodeTranslator.toAscii("varNAT1").equals("varNAT1"));
		assertTrue(UnicodeTranslator.toAscii("varnat1").equals("varnat1"));
		assertTrue(UnicodeTranslator.toAscii("varNAT1var").equals("varNAT1var"));
		assertTrue(UnicodeTranslator.toAscii("varnat1var").equals("varnat1var"));
		assertTrue(UnicodeTranslator.toAscii("NAT1var").equals("NAT1var"));
		assertTrue(UnicodeTranslator.toAscii("nat1var").equals("nat1var"));

		assertTrue(UnicodeTranslator.toAscii("123NAT1").equals("123NAT1"));
		assertTrue(UnicodeTranslator.toAscii("123nat1").equals("123nat1"));
		assertTrue(UnicodeTranslator.toAscii("123NAT1123").equals("123NAT1123"));
		assertTrue(UnicodeTranslator.toAscii("123nat1123").equals("123nat1123"));
		assertTrue(UnicodeTranslator.toAscii("NAT1123").equals("NAT1123"));
		assertTrue(UnicodeTranslator.toAscii("nat1123").equals("nat1123"));

		assertTrue(UnicodeTranslator.toAscii("_NAT1").equals("_NAT1"));
		assertTrue(UnicodeTranslator.toAscii("_nat1").equals("_nat1"));
		assertTrue(UnicodeTranslator.toAscii("_NAT1_").equals("_NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("_nat1_").equals("_nat1_"));
		assertTrue(UnicodeTranslator.toAscii("NAT1_").equals("NAT1_"));
		assertTrue(UnicodeTranslator.toAscii("nat1_").equals("nat1_"));
	}

	@Test
	public void VarNATURAL() {
		assertTrue(UnicodeTranslator.toAscii("varNATURAL").equals("varNATURAL"));
		assertTrue(UnicodeTranslator.toAscii("varnatural").equals("varnatural"));
		assertTrue(UnicodeTranslator.toAscii("varNATURALvar").equals(
				"varNATURALvar"));
		assertTrue(UnicodeTranslator.toAscii("varnaturalvar").equals(
				"varnaturalvar"));
		assertTrue(UnicodeTranslator.toAscii("NATURALvar").equals("NATURALvar"));
		assertTrue(UnicodeTranslator.toAscii("naturalvar").equals("naturalvar"));

		assertTrue(UnicodeTranslator.toAscii("123NATURAL").equals("123NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("123natural").equals("123natural"));
		assertTrue(UnicodeTranslator.toAscii("123NATURAL123").equals(
				"123NATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("123natural123").equals(
				"123natural123"));
		assertTrue(UnicodeTranslator.toAscii("NATURAL123").equals("NATURAL123"));
		assertTrue(UnicodeTranslator.toAscii("natural123").equals("natural123"));

		assertTrue(UnicodeTranslator.toAscii("_NATURAL").equals("_NATURAL"));
		assertTrue(UnicodeTranslator.toAscii("_natural").equals("_natural"));
		assertTrue(UnicodeTranslator.toAscii("_NATURAL_").equals("_NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("_natural_").equals("_natural_"));
		assertTrue(UnicodeTranslator.toAscii("NATURAL_").equals("NATURAL_"));
		assertTrue(UnicodeTranslator.toAscii("natural_").equals("natural_"));
	}

	@Test
	public void VarNOT() {
		assertTrue(UnicodeTranslator.toAscii("varNOT").equals("varNOT"));
		assertTrue(UnicodeTranslator.toAscii("varnot").equals("varnot"));
		assertTrue(UnicodeTranslator.toAscii("varNOTvar").equals("varNOTvar"));
		assertTrue(UnicodeTranslator.toAscii("varnotvar").equals("varnotvar"));
		assertTrue(UnicodeTranslator.toAscii("NOTvar").equals("NOTvar"));
		assertTrue(UnicodeTranslator.toAscii("notvar").equals("notvar"));

		assertTrue(UnicodeTranslator.toAscii("123NOT").equals("123NOT"));
		assertTrue(UnicodeTranslator.toAscii("123not").equals("123not"));
		assertTrue(UnicodeTranslator.toAscii("123NOT123").equals("123NOT123"));
		assertTrue(UnicodeTranslator.toAscii("123not123").equals("123not123"));
		assertTrue(UnicodeTranslator.toAscii("NOT123").equals("NOT123"));
		assertTrue(UnicodeTranslator.toAscii("not123").equals("not123"));

		assertTrue(UnicodeTranslator.toAscii("_NOT").equals("_NOT"));
		assertTrue(UnicodeTranslator.toAscii("_not").equals("_not"));
		assertTrue(UnicodeTranslator.toAscii("_NOT_").equals("_NOT_"));
		assertTrue(UnicodeTranslator.toAscii("_not_").equals("_not_"));
		assertTrue(UnicodeTranslator.toAscii("NOT_").equals("NOT_"));
		assertTrue(UnicodeTranslator.toAscii("not_").equals("not_"));
	}

	@Test
	public void VarOr() {
		assertTrue(UnicodeTranslator.toAscii("varOR").equals("varOR"));
		assertTrue(UnicodeTranslator.toAscii("varor").equals("varor"));
		assertTrue(UnicodeTranslator.toAscii("varORvar").equals("varORvar"));
		assertTrue(UnicodeTranslator.toAscii("varorvar").equals("varorvar"));
		assertTrue(UnicodeTranslator.toAscii("ORvar").equals("ORvar"));
		assertTrue(UnicodeTranslator.toAscii("orvar").equals("orvar"));

		assertTrue(UnicodeTranslator.toAscii("123OR").equals("123OR"));
		assertTrue(UnicodeTranslator.toAscii("123or").equals("123or"));
		assertTrue(UnicodeTranslator.toAscii("123OR123").equals("123OR123"));
		assertTrue(UnicodeTranslator.toAscii("123or123").equals("123or123"));
		assertTrue(UnicodeTranslator.toAscii("OR123").equals("OR123"));
		assertTrue(UnicodeTranslator.toAscii("or123").equals("or123"));

		assertTrue(UnicodeTranslator.toAscii("_OR").equals("_OR"));
		assertTrue(UnicodeTranslator.toAscii("_or").equals("_or"));
		assertTrue(UnicodeTranslator.toAscii("_OR_").equals("_OR_"));
		assertTrue(UnicodeTranslator.toAscii("_or_").equals("_or_"));
		assertTrue(UnicodeTranslator.toAscii("OR_").equals("OR_"));
		assertTrue(UnicodeTranslator.toAscii("or_").equals("or_"));
	}

	@Test
	public void VarPOW() {
		assertTrue(UnicodeTranslator.toAscii("varPOW").equals("varPOW"));
		assertTrue(UnicodeTranslator.toAscii("varpow").equals("varpow"));
		assertTrue(UnicodeTranslator.toAscii("varPOWvar").equals("varPOWvar"));
		assertTrue(UnicodeTranslator.toAscii("varpowvar").equals("varpowvar"));
		assertTrue(UnicodeTranslator.toAscii("POWvar").equals("POWvar"));
		assertTrue(UnicodeTranslator.toAscii("powvar").equals("powvar"));

		assertTrue(UnicodeTranslator.toAscii("123POW").equals("123POW"));
		assertTrue(UnicodeTranslator.toAscii("123pow").equals("123pow"));
		assertTrue(UnicodeTranslator.toAscii("123POW123").equals("123POW123"));
		assertTrue(UnicodeTranslator.toAscii("123pow123").equals("123pow123"));
		assertTrue(UnicodeTranslator.toAscii("POW123").equals("POW123"));
		assertTrue(UnicodeTranslator.toAscii("pow123").equals("pow123"));

		assertTrue(UnicodeTranslator.toAscii("_POW").equals("_POW"));
		assertTrue(UnicodeTranslator.toAscii("_pow").equals("_pow"));
		assertTrue(UnicodeTranslator.toAscii("_POW_").equals("_POW_"));
		assertTrue(UnicodeTranslator.toAscii("_pow_").equals("_pow_"));
		assertTrue(UnicodeTranslator.toAscii("POW_").equals("POW_"));
		assertTrue(UnicodeTranslator.toAscii("pow_").equals("pow_"));
	}

	@Test
	public void VarPOW1() {
		assertTrue(UnicodeTranslator.toAscii("varPOW1").equals("varPOW1"));
		assertTrue(UnicodeTranslator.toAscii("varpow1").equals("varpow1"));
		assertTrue(UnicodeTranslator.toAscii("varPOW1var").equals("varPOW1var"));
		assertTrue(UnicodeTranslator.toAscii("varpow1var").equals("varpow1var"));
		assertTrue(UnicodeTranslator.toAscii("POW1var").equals("POW1var"));
		assertTrue(UnicodeTranslator.toAscii("pow1var").equals("pow1var"));

		assertTrue(UnicodeTranslator.toAscii("123POW1").equals("123POW1"));
		assertTrue(UnicodeTranslator.toAscii("123pow1").equals("123pow1"));
		assertTrue(UnicodeTranslator.toAscii("123POW1123").equals("123POW1123"));
		assertTrue(UnicodeTranslator.toAscii("123pow1123").equals("123pow1123"));
		assertTrue(UnicodeTranslator.toAscii("POW1123").equals("POW1123"));
		assertTrue(UnicodeTranslator.toAscii("pow1123").equals("pow1123"));

		assertTrue(UnicodeTranslator.toAscii("_POW1").equals("_POW1"));
		assertTrue(UnicodeTranslator.toAscii("_pow1").equals("_pow1"));
		assertTrue(UnicodeTranslator.toAscii("_POW1_").equals("_POW1_"));
		assertTrue(UnicodeTranslator.toAscii("_pow1_").equals("_pow1_"));
		assertTrue(UnicodeTranslator.toAscii("POW1_").equals("POW1_"));
		assertTrue(UnicodeTranslator.toAscii("pow1_").equals("pow1_"));
	}

	@Test
	public void VarTRUE() {
		assertTrue(UnicodeTranslator.toAscii("varTRUE").equals("varTRUE"));
		assertTrue(UnicodeTranslator.toAscii("vartrue").equals("vartrue"));
		assertTrue(UnicodeTranslator.toAscii("varTRUEvar").equals("varTRUEvar"));
		assertTrue(UnicodeTranslator.toAscii("vartruevar").equals("vartruevar"));
		assertTrue(UnicodeTranslator.toAscii("TRUEvar").equals("TRUEvar"));
		assertTrue(UnicodeTranslator.toAscii("truevar").equals("truevar"));

		assertTrue(UnicodeTranslator.toAscii("123TRUE").equals("123TRUE"));
		assertTrue(UnicodeTranslator.toAscii("123true").equals("123true"));
		assertTrue(UnicodeTranslator.toAscii("123TRUE123").equals("123TRUE123"));
		assertTrue(UnicodeTranslator.toAscii("123true123").equals("123true123"));
		assertTrue(UnicodeTranslator.toAscii("TRUE123").equals("TRUE123"));
		assertTrue(UnicodeTranslator.toAscii("true123").equals("true123"));

		assertTrue(UnicodeTranslator.toAscii("_TRUE").equals("_TRUE"));
		assertTrue(UnicodeTranslator.toAscii("_true").equals("_true"));
		assertTrue(UnicodeTranslator.toAscii("_TRUE_").equals("_TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("_true_").equals("_true_"));
		assertTrue(UnicodeTranslator.toAscii("TRUE_").equals("TRUE_"));
		assertTrue(UnicodeTranslator.toAscii("true_").equals("true_"));
	}

	@Test
	public void VarUNION() {
		assertTrue(UnicodeTranslator.toAscii("varUNION").equals("varUNION"));
		assertTrue(UnicodeTranslator.toAscii("varunion").equals("varunion"));
		assertTrue(UnicodeTranslator.toAscii("varUNIONvar").equals(
				"varUNIONvar"));
		assertTrue(UnicodeTranslator.toAscii("varunionvar").equals(
				"varunionvar"));
		assertTrue(UnicodeTranslator.toAscii("UNIONvar").equals("UNIONvar"));
		assertTrue(UnicodeTranslator.toAscii("unionvar").equals("unionvar"));

		assertTrue(UnicodeTranslator.toAscii("123UNION").equals("123UNION"));
		assertTrue(UnicodeTranslator.toAscii("123union").equals("123union"));
		assertTrue(UnicodeTranslator.toAscii("123UNION123").equals(
				"123UNION123"));
		assertTrue(UnicodeTranslator.toAscii("123union123").equals(
				"123union123"));
		assertTrue(UnicodeTranslator.toAscii("UNION123").equals("UNION123"));
		assertTrue(UnicodeTranslator.toAscii("union123").equals("union123"));

		assertTrue(UnicodeTranslator.toAscii("_UNION").equals("_UNION"));
		assertTrue(UnicodeTranslator.toAscii("_union").equals("_union"));
		assertTrue(UnicodeTranslator.toAscii("_UNION_").equals("_UNION_"));
		assertTrue(UnicodeTranslator.toAscii("_union_").equals("_union_"));
		assertTrue(UnicodeTranslator.toAscii("UNION_").equals("UNION_"));
		assertTrue(UnicodeTranslator.toAscii("union_").equals("union_"));
	}

	/*
	 * Problems: UnicodeTranslator.toAscii("p ; q") makes "p , q"
	 * ForwardComposition() UnicodeTranslator.toAscii("G ; H") makes "G , H"
	 * SequentialSubstitution() java.io.IOException: Pushback buffer overflow
	 * LeftOverriding(), PrependElement(), AppendElement()
	 * de.prob.unicode.lexer.LexerException: [1,4] Unknown token: _ Var_123(),
	 * etc.
	 */
}
