import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.unicode.UnicodeTranslator;

public class ToUnicodeTest {

	@Test
	public void TIn() {
		assertEquals(UnicodeTranslator.toUnicode(":"), "\u2208");
	}

	@Test
	public void TNotsubseteq() {
		assertEquals(UnicodeTranslator.toUnicode("/<:"), "\u2288");
	}

	@Test
	public void TNotsubset() {
		assertEquals(UnicodeTranslator.toUnicode("/<<:"), "\u2284");
	}

	@Test
	public void TSubseteq() {
		assertEquals(UnicodeTranslator.toUnicode("<:"), "\u2286");
	}

	@Test
	public void TSetminus() {
		assertEquals(UnicodeTranslator.toUnicode("\\"), "\u2216");
	}

	@Test
	public void TDotdot() {
		assertEquals(UnicodeTranslator.toUnicode(".."), "\u2025");
	}

	@Test
	public void TNat() {
		assertEquals(UnicodeTranslator.toUnicode("NAT"), "\u2115");
	}

	@Test
	public void TEmptyset() {
		assertEquals(UnicodeTranslator.toUnicode("{}"), "\u2205");
	}

	@Test
	public void TBcmsuch() {
		assertEquals(UnicodeTranslator.toUnicode(":|"), ":\u2223");
	}

	@Test
	public void TBfalse() {
		assertEquals(UnicodeTranslator.toUnicode("false"), "\u22a5");
	}

	@Test
	public void TForall() {
		assertEquals(UnicodeTranslator.toUnicode("!"), "\u2200");
	}

	@Test
	public void TExists() {
		assertEquals(UnicodeTranslator.toUnicode("#"), "\u2203");
	}

	@Test
	public void TMapsto() {
		assertEquals(UnicodeTranslator.toUnicode("|->"), "\u21a6");
	}

	@Test
	public void TBtrue() {
		assertEquals(UnicodeTranslator.toUnicode("true"), "\u22a4");
	}

	@Test
	public void TSubset() {
		assertEquals(UnicodeTranslator.toUnicode("<<:"), "\u2282");
	}

	@Test
	public void TBunion() {
		assertEquals(UnicodeTranslator.toUnicode("\\/"), "\u222a");
	}

	@Test
	public void TBinter() {
		assertEquals(UnicodeTranslator.toUnicode("/\\"), "\u2229");
	}

	@Test
	public void TDomres() {
		assertEquals(UnicodeTranslator.toUnicode("<|"), "\u25c1");
	}

	@Test
	public void TRanres() {
		assertEquals(UnicodeTranslator.toUnicode("|>"), "\u25b7");
	}

	@Test
	public void TDomsub() {
		assertEquals(UnicodeTranslator.toUnicode("<<|"), "\u2a64");
	}

	@Test
	public void TRansub() {
		assertEquals(UnicodeTranslator.toUnicode("|>>"), "\u2a65");
	}

	@Test
	public void TLambda() {
		assertEquals(UnicodeTranslator.toUnicode("%"), "\u03bb");
	}

	@Test
	public void TOftype() {
		assertEquals(UnicodeTranslator.toUnicode("oftype"), "\u2982");
	}

	@Test
	public void TNotin() {
		assertEquals(UnicodeTranslator.toUnicode("/:"), "\u2209");
	}

	@Test
	public void TCprod() {
		assertEquals(UnicodeTranslator.toUnicode("**"), "\u00d7");
	}

	@Test
	public void TUnion() {
		assertEquals(UnicodeTranslator.toUnicode("UNION"), "\u22c3");
	}

	@Test
	public void TInter() {
		assertEquals(UnicodeTranslator.toUnicode("INTER"), "\u22c2");
	}

	@Test
	public void TFcomp() {
		String actual = UnicodeTranslator.toUnicode(";");
		String expected = "\u003b";
		assertEquals(expected, actual);
	}

	@Test
	public void TBcomp() {
		String actual = UnicodeTranslator.toUnicode("circ");
		;
		String expected = "\u2218";
		assertEquals(expected, actual);
	}

	@Test
	public void TStrel() {
		assertEquals(UnicodeTranslator.toUnicode("<<->>"), "\ue102");
	}

	@Test
	public void TDprod() {
		assertEquals(UnicodeTranslator.toUnicode("><"), "\u2297");
	}

	@Test
	public void TPprod() {
		assertEquals(UnicodeTranslator.toUnicode("||"), "\u2225");
	}

	@Test
	public void TBcmeq() {
		assertEquals(UnicodeTranslator.toUnicode(":="), "\u2254");
	}

	@Test
	public void TBcmin() {
		assertEquals(UnicodeTranslator.toUnicode("::"), ":\u2208");
	}

	@Test
	public void TIntg() {
		assertEquals(UnicodeTranslator.toUnicode("INT"), "\u2124");
	}

	@Test
	public void TLand() {
		assertEquals(UnicodeTranslator.toUnicode("&"), "\u2227");
	}

	@Test
	public void TLimp() {
		assertEquals(UnicodeTranslator.toUnicode("=>"), "\u21d2");
	}

	@Test
	public void TLeqv() {
		assertEquals(UnicodeTranslator.toUnicode("<=>"), "\u21d4");
	}

	@Test
	public void TLnot() {
		assertEquals(UnicodeTranslator.toUnicode("not"), "\u00ac");
	}

	@Test
	public void TQdot() {
		assertEquals(UnicodeTranslator.toUnicode("."), "\u00b7");
	}

	@Test
	public void TConv() {
		assertEquals(UnicodeTranslator.toUnicode("~"), "\u223c");
	}

	@Test
	public void TTrel() {
		assertEquals(UnicodeTranslator.toUnicode("<<->"), "\ue100");
	}

	@Test
	public void TSurjectiveRel() {
		assertEquals(UnicodeTranslator.toUnicode("<->>"), "\ue101");
	}

	@Test
	public void TPfun() {
		assertEquals(UnicodeTranslator.toUnicode("+->"), "\u21f8");
	}

	@Test
	public void TTfun() {
		assertEquals(UnicodeTranslator.toUnicode("-->"), "\u2192");
	}

	@Test
	public void TPinj() {
		assertEquals(UnicodeTranslator.toUnicode(">+>"), "\u2914");
	}

	@Test
	public void TTinj() {
		assertEquals(UnicodeTranslator.toUnicode(">->"), "\u21a3");
	}

	@Test
	public void TPsur() {
		assertEquals(UnicodeTranslator.toUnicode("+>>"), "\u2900");
	}

	@Test
	public void TTsur() {
		assertEquals(UnicodeTranslator.toUnicode("->>"), "\u21a0");
	}

	@Test
	public void TTbij() {
		assertEquals(UnicodeTranslator.toUnicode(">->>"), "\u2916");
	}

	@Test
	public void TExpn() {
		// XXX Test doesn't make sense, works in both directions!
		assertEquals(UnicodeTranslator.toUnicode("\u005e"), "^");
		assertEquals(UnicodeTranslator.toUnicode("^"), "\u005e");
	}

	@Test
	public void TLor() {
		assertEquals(UnicodeTranslator.toUnicode("or"), "\u2228");
	}

	@Test
	public void TPow() {
		assertEquals(UnicodeTranslator.toUnicode("POW"), "\u2119");
	}

	@Test
	public void TMid() {
		assertEquals(UnicodeTranslator.toUnicode("|"), "\u2223");
	}

	@Test
	public void TNeq() {
		assertEquals(UnicodeTranslator.toUnicode("/="), "\u2260");
	}

	@Test
	public void TRel() {
		assertEquals(UnicodeTranslator.toUnicode("<->"), "\u2194");
	}

	@Test
	public void TOvl() {
		assertEquals(UnicodeTranslator.toUnicode("<+"), "\ue103");
	}

	@Test
	public void TLeq() {
		assertEquals(UnicodeTranslator.toUnicode("<="), "\u2264");
	}

	@Test
	public void TGeq() {
		assertEquals(UnicodeTranslator.toUnicode(">="), "\u2265");
	}

	@Test
	public void TDiv() {
		assertEquals(UnicodeTranslator.toUnicode("/"), "\u00f7");
	}

	@Test
	public void TMult() {
		assertEquals(UnicodeTranslator.toUnicode("*"), "\u2217");
	}

	@Test
	public void TMinus() {
		assertEquals(UnicodeTranslator.toUnicode("-"), "\u2212");
	}

	@Test
	public void TComma() {
		assertEquals(UnicodeTranslator.toUnicode(","), ",");
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Conjunction() {
		assertEquals(UnicodeTranslator.toUnicode("P & Q"), "P \u2227 Q");
	}

	@Test
	public void Disjunction() {
		assertEquals(UnicodeTranslator.toUnicode("P or Q"), "P \u2228 Q");
	}

	@Test
	public void Implication() {
		assertEquals(UnicodeTranslator.toUnicode("P => Q"), "P \u21d2 Q");
	}

	@Test
	public void Equivalence() {
		assertEquals(UnicodeTranslator.toUnicode("P <=> Q"), "P \u21d4 Q");
	}

	@Test
	public void Negation() {
		assertEquals(UnicodeTranslator.toUnicode("not P"), "\u00ac P");
	}

	@Test
	public void UniversalQuantification() {
		assertEquals(UnicodeTranslator.toUnicode("!(z).(P => Q)"),
				"\u2200(z)\u00b7(P \u21d2 Q)");
	}

	@Test
	public void UniversalQuantification2() {
		assertEquals(UnicodeTranslator.toUnicode("(!z.P => Q)"),
				"(\u2200z\u00b7P \u21d2 Q)");
	}

	@Test
	public void ExistentialQuantification() {
		assertEquals(UnicodeTranslator.toUnicode("#(z).(P & Q)"),
				"\u2203(z)\u00b7(P \u2227 Q)");
	}

	@Test
	public void ExistentialQuantification2() {
		assertEquals(UnicodeTranslator.toUnicode("(#z.P & Q)"),
				"(\u2203z\u00b7P \u2227 Q)");
	}

	@Test
	public void Substitution() {
		assertEquals(UnicodeTranslator.toUnicode("[G] P"), "[G] P");
	}

	@Test
	public void Equality() {
		assertEquals(UnicodeTranslator.toUnicode("E = F"), "E = F");
	}

	@Test
	public void Inequality() {
		assertEquals(UnicodeTranslator.toUnicode("E /= F"), "E \u2260 F");
	}

	@Test
	public void SingletonSet() {
		assertEquals(UnicodeTranslator.toUnicode("{E}"), "{E}");
	}

	@Test
	public void SetEnumeration() {
		assertEquals(UnicodeTranslator.toUnicode("{E, F}"), "{E, F}");
	}

	@Test
	public void EmptySet() {
		assertEquals(UnicodeTranslator.toUnicode("{}"), "\u2205");
	}

	@Test
	public void SetComprehension() {
		assertEquals(UnicodeTranslator.toUnicode("{z | P}"), "{z \u2223 P}");
	}

	@Test
	public void SetComprehension2() {
		assertEquals(UnicodeTranslator.toUnicode("{z . P | F}"),
				"{z \u00b7 P \u2223 F}");
	}

	@Test
	public void SetComprehension3() {
		assertEquals(UnicodeTranslator.toUnicode("{F | P}"), "{F \u2223 P}");
	}

	@Test
	public void SetComprehension4() {
		assertEquals(UnicodeTranslator.toUnicode("{x | P}"), "{x \u2223 P}");
	}

	@Test
	public void Union() {
		assertEquals(UnicodeTranslator.toUnicode("S \\/ T"), "S \u222a T");
	}

	@Test
	public void Intersection() {
		assertEquals(UnicodeTranslator.toUnicode("S /\\ T"), "S \u2229 T");
	}

	@Test
	public void Difference() {
		assertEquals(UnicodeTranslator.toUnicode("S-T"), "S\u2212T");
	}

	@Test
	public void Difference2() {
		String expected = "S \u2216 T";
		String actual = UnicodeTranslator.toUnicode("S \\ T");
		assertEquals(expected, actual);
	}

	@Test
	public void OrderedPair() {
		assertEquals(UnicodeTranslator.toUnicode("E |-> F"), "E \u21a6 F");
	}

	@Test
	public void CartesianProduct() {
		// XXX really \u2217 '*' and not \u00d7 'x'?
		assertEquals(UnicodeTranslator.toUnicode("S * T"), "S \u2217 T");
	}

	@Test
	public void CartesianProduct2() {
		assertEquals(UnicodeTranslator.toUnicode("S ** T"), "S \u00d7 T");
	}

	@Test
	public void Powerset() {
		assertEquals(UnicodeTranslator.toUnicode("POW(S)"), "\u2119(S)");
	}

	@Test
	public void NonEmptySubsets() {
		assertEquals(UnicodeTranslator.toUnicode("POW1(S)"), "\u21191(S)");
	}

	// XXX FiniteSets not provided? What's the unicode character?
	@Test
	public void FiniteSets() {
		assertEquals(UnicodeTranslator.toUnicode("finite S"), "finite S");
	}

	// XXX FiniteSubsets not provided? What's the unicode character? \u1D53D ?
	@Test
	public void FiniteSubsets() {
		assertEquals(UnicodeTranslator.toUnicode("FIN(S)"), "FIN(S)");
	}

	// XXX FiniteNonEmptySubsets not provided? What's the unicode character?
	// \u1D53D and \u2081 ?
	@Test
	public void FiniteNonEmptySubsets() {
		assertEquals(UnicodeTranslator.toUnicode("FIN1(S)"), "FIN1(S)");
	}

	@Test
	public void Cardinality() {
		assertEquals(UnicodeTranslator.toUnicode("card(S)"), "card(S)");
	}

	@Test
	public void Partition() {
		assertEquals(UnicodeTranslator.toUnicode("partition(S,x,y)"),
				"partition(S,x,y)");
	}

	@Test
	public void GeneralizedUnion() {
		assertEquals(UnicodeTranslator.toUnicode("UNION(U)"), "\u22c3(U)");
	}

	@Test
	public void GeneralizedUnion2() {
		assertEquals(UnicodeTranslator.toUnicode("UNION (z).(P | E)"),
				"\u22c3 (z)\u00b7(P \u2223 E)");
	}

	@Test
	public void GeneralizedUnion3() {
		assertEquals(UnicodeTranslator.toUnicode("union(U)"), "union(U)");
	}

	@Test
	public void QuantifiedUnion() {
		assertEquals(UnicodeTranslator.toUnicode("UNION z.P | S"),
				"\u22c3 z\u00b7P \u2223 S");
	}

	@Test
	public void GeneralizedIntersection() {
		assertEquals(UnicodeTranslator.toUnicode("INTER(U)"), "\u22c2(U)");
	}

	@Test
	public void GeneralizedIntersection2() {
		assertEquals(UnicodeTranslator.toUnicode("INTER (z).(P | E)"),
				"\u22c2 (z)\u00b7(P \u2223 E)");
	}

	@Test
	public void SetMembership() {
		assertEquals(UnicodeTranslator.toUnicode("E : S"), "E \u2208 S");
	}

	@Test
	public void SetNonMembership() {
		assertEquals(UnicodeTranslator.toUnicode("E /: S"), "E \u2209 S");
	}

	@Test
	public void Subset() {
		assertEquals(UnicodeTranslator.toUnicode("S <: T"), "S \u2286 T");
	}

	@Test
	public void NotASubset() {
		assertEquals(UnicodeTranslator.toUnicode("S /<: T"), "S \u2288 T");
	}

	@Test
	public void ProperSubset() {
		assertEquals(UnicodeTranslator.toUnicode("S <<: T"), "S \u2282 T");
	}

	@Test
	public void NotAProperSubset() {
		assertEquals(UnicodeTranslator.toUnicode("S /<<: T"), "S \u2284 T");
	}

	@Test
	public void NaturalNumbers() {
		assertEquals(UnicodeTranslator.toUnicode("NAT"), "\u2115");
	}

	@Test
	public void PositiveNaturalNumbers() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1"), "\u21151");
	}

	@Test
	public void Minimum() {
		assertEquals(UnicodeTranslator.toUnicode("min(S)"), "min(S)");
	}

	@Test
	public void Maximum() {
		assertEquals(UnicodeTranslator.toUnicode("max(S)"), "max(S)");
	}

	@Test
	public void Sum() {
		assertEquals(UnicodeTranslator.toUnicode("m + n"), "m + n");
	}

	@Test
	public void DifferenceAlt() {
		assertEquals(UnicodeTranslator.toUnicode("m - n"), "m \u2212 n");
	}

	@Test
	public void Product() {
		// XXX why \u2217 '*' and not \u00d7 'x'?
		assertEquals(UnicodeTranslator.toUnicode("m * n"), "m \u2217 n");
	}

	@Test
	public void Quotient() {
		assertEquals(UnicodeTranslator.toUnicode("m / n"), "m \u00f7 n");
	}

	@Test
	public void Remainder() {
		assertEquals(UnicodeTranslator.toUnicode("m mod n"), "m mod n");
	}

	@Test
	public void Interval() {
		assertEquals(UnicodeTranslator.toUnicode("m .. n"), "m \u2025 n");
	}

	@Test
	public void SetSummation() {
		// SIGMA not provided (\u2211)
		assertEquals(UnicodeTranslator.toUnicode("SIGMA(z).(P | E)"),
				"SIGMA(z)\u00b7(P \u2223 E)");
	}

	@Test
	public void SetProduct() {
		// PI not provided (\u220F)
		assertEquals(UnicodeTranslator.toUnicode("PI(z).(P | E)"),
				"PI(z)\u00b7(P \u2223 E)");
	}

	@Test
	public void Greater() {
		assertEquals(UnicodeTranslator.toUnicode("m > n"), "m > n");
	}

	@Test
	public void Less() {
		assertEquals(UnicodeTranslator.toUnicode("m < n"), "m < n");
	}

	@Test
	public void GreaterOrEqual() {
		assertEquals(UnicodeTranslator.toUnicode("m >= n"), "m \u2265 n");
	}

	@Test
	public void LessOrEqual() {
		assertEquals(UnicodeTranslator.toUnicode("m <= n"), "m \u2264 n");
	}

	@Test
	public void Relations() {
		assertEquals(UnicodeTranslator.toUnicode("S <-> T"), "S \u2194 T");
	}

	@Test
	public void Domain() {
		assertEquals(UnicodeTranslator.toUnicode("dom(r)"), "dom(r)");
	}

	@Test
	public void Range() {
		assertEquals(UnicodeTranslator.toUnicode("ran(r)"), "ran(r)");
	}

	@Test
	public void ForwardComposition() {
		assertEquals(UnicodeTranslator.toUnicode("p ; q"), "p ; q");
	}

	@Test
	public void BackwardComposition() {
		assertEquals(UnicodeTranslator.toUnicode("p circ q"), "p \u2218 q");
	}

	@Test
	public void Identity() {
		assertEquals(UnicodeTranslator.toUnicode("id(S)"), "id(S)");
	}

	@Test
	public void DomainRestriction() {
		assertEquals(UnicodeTranslator.toUnicode("S <| r"), "S \u25c1 r");
	}

	@Test
	public void DomainSubtraction() {
		assertEquals(UnicodeTranslator.toUnicode("S <<| r"), "S \u2a64 r");
	}

	@Test
	public void RangeRestriction() {
		assertEquals(UnicodeTranslator.toUnicode("r |> T"), "r \u25b7 T");
	}

	@Test
	public void RangeSubtraction() {
		assertEquals(UnicodeTranslator.toUnicode("r |>> T"), "r \u2a65 T");
	}

	@Test
	public void Inverse() {
		assertEquals(UnicodeTranslator.toUnicode("r~"), "r\u223c");
	}

	@Test
	public void relationalImage() {
		assertEquals(UnicodeTranslator.toUnicode("r[S]"), "r[S]");
	}

	@Test
	public void RightOverriding() {
		assertEquals(UnicodeTranslator.toUnicode("r1 <+ r2"), "r1 \ue103 r2");
	}

	@Test
	public void DirectProduct() {
		assertEquals(UnicodeTranslator.toUnicode("p >< q"), "p \u2297 q");
	}

	@Test
	public void ParallelProduct() {
		assertEquals(UnicodeTranslator.toUnicode("p || q"), "p \u2225 q");
	}

	// XXX Iteration not provided? something like r^n
	@Test
	public void Iteration() {
		assertEquals(UnicodeTranslator.toUnicode("iterate(r,n)"),
				"iterate(r,n)");
	}

	@Test
	public void Closure() {
		assertEquals(UnicodeTranslator.toUnicode("closure(r)"), "closure(r)");
	}

	// XXX reflexibleClosure not provided? something like r^*
	@Test
	public void rClosure() {
		assertEquals(UnicodeTranslator.toUnicode("rclosure(r)"), "rclosure(r)");
	}

	// irreflexible Closure not provided? something like r^+
	@Test
	public void iClosure() {
		assertEquals(UnicodeTranslator.toUnicode("iclosure(r)"), "iclosure(r)");
	}

	@Test
	public void Projection1() {
		assertEquals(UnicodeTranslator.toUnicode("prj1(S,T)"), "prj1(S,T)");
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection1_1() {
		assertEquals(UnicodeTranslator.toUnicode("prj1"), "prj1");
	}

	@Test
	public void Projection2() {
		assertEquals(UnicodeTranslator.toUnicode("prj2(S,T)"), "prj2(S,T)");
	}

	/*
	 * XXX Projection not provided? But how to translate '2'? Take the whole
	 * 'prj2'.
	 */
	@Test
	public void Projection2_1() {
		assertEquals(UnicodeTranslator.toUnicode("prj2"), "prj2");
	}

	@Test
	public void PartialFunctions() {
		assertEquals(UnicodeTranslator.toUnicode("S +-> T"), "S \u21f8 T");
	}

	@Test
	public void TotalFunctions() {
		assertEquals(UnicodeTranslator.toUnicode("S --> T"), "S \u2192 T");
	}

	@Test
	public void PartialInjections() {
		assertEquals(UnicodeTranslator.toUnicode("S >+> T"), "S \u2914 T");
	}

	@Test
	public void TotalInjections() {
		assertEquals(UnicodeTranslator.toUnicode("S >-> T"), "S \u21a3 T");
	}

	@Test
	public void PartialSurjections() {
		assertEquals(UnicodeTranslator.toUnicode("S +>> T"), "S \u2900 T");
	}

	@Test
	public void TotalSurjections() {
		assertEquals(UnicodeTranslator.toUnicode("S ->> T"), "S \u21a0 T");
	}

	@Test
	public void Bijections() {
		assertEquals(UnicodeTranslator.toUnicode("S >->> T"), "S \u2916 T");
	}

	@Test
	public void LambdaAbstraction() {
		assertEquals(UnicodeTranslator.toUnicode("%z.(P|E)"),
				"\u03bbz\u00b7(P\u2223E)");
	}

	@Test
	public void FunctionApplication() {
		assertEquals(UnicodeTranslator.toUnicode("f(E)"), "f(E)");
	}

	@Test
	public void FunctionApplication2() {
		assertEquals(UnicodeTranslator.toUnicode("f(E |-> F)"), "f(E \u21a6 F)");
	}

	@Test
	public void FiniteSequences() {
		assertEquals(UnicodeTranslator.toUnicode("seq S"), "seq S");
		assertEquals(UnicodeTranslator.toUnicode("seq(S)"), "seq(S)");

	}

	@Test
	public void FiniteNonEmptySequences() {
		assertEquals(UnicodeTranslator.toUnicode("seq1(S)"), "seq1(S)");
	}

	@Test
	public void InjectiveSequences() {
		assertEquals(UnicodeTranslator.toUnicode("iseq(S)"), "iseq(S)");
	}

	@Test
	public void Permutations() {
		assertEquals(UnicodeTranslator.toUnicode("perm(S)"), "perm(S)");
	}

	@Test
	public void SequenceConcatenations() {
		// XXX really meant \u005e for sequence concatenation? not \u0311 ?
		assertEquals(UnicodeTranslator.toUnicode("s^t"), "s\u005et");
	}

	@Test
	public void Size() {
		assertEquals(UnicodeTranslator.toUnicode("size(s)"), "size(s)");
	}

	@Test
	public void Reverse() {
		assertEquals(UnicodeTranslator.toUnicode("rev(s)"), "rev(s)");
	}

	@Test
	public void Take() {
		assertEquals(UnicodeTranslator.toUnicode("s /|\\ n"), "s /|\\ n");
	}

	@Test
	public void Drop() {
		assertEquals(UnicodeTranslator.toUnicode("s \\|/ n"), "s \\|/ n");
	}

	@Test
	public void FirstElement() {
		assertEquals(UnicodeTranslator.toUnicode("first(s)"), "first(s)");
	}

	@Test
	public void LastElement() {
		assertEquals(UnicodeTranslator.toUnicode("last(s)"), "last(s)");
	}

	@Test
	public void Tail() {
		assertEquals(UnicodeTranslator.toUnicode("tail(s)"), "tail(s)");
	}

	@Test
	public void Front() {
		assertEquals(UnicodeTranslator.toUnicode("front(s)"), "front(s)");
	}

	@Test
	public void GeneralizedConcatenation() {
		assertEquals(UnicodeTranslator.toUnicode("conc(ss)"), "conc(ss)");
	}

	@Test
	public void Substitution2() {
		assertEquals(UnicodeTranslator.toUnicode("[G]P"), "[G]P");
	}

	@Test
	public void Skip() {
		assertEquals(UnicodeTranslator.toUnicode("skip"), "skip");
	}

	@Test
	public void SimpleSubstitution() {
		assertEquals(UnicodeTranslator.toUnicode("x := E"), "x \u2254 E");
	}

	@Test
	public void BooleanSubstitution() {
		assertEquals(UnicodeTranslator.toUnicode("x := bool(P)"),
				"x \u2254 bool(P)");
	}

	@Test
	public void ChoiceFromSet() {
		assertEquals(UnicodeTranslator.toUnicode("x :: S"), "x :\u2208 S");
	}

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

	@Test
	public void FunctionalOverride() {
		assertEquals(UnicodeTranslator.toUnicode("f(x) := E"), "f(x) \u2254 E");
	}

	@Test
	public void MultipleSubstitution() {
		assertEquals(UnicodeTranslator.toUnicode("x,y := E,F"),
				"x,y \u2254 E,F");
	}

	@Test
	public void ParallelSubstitution() {
		assertEquals(UnicodeTranslator.toUnicode("G || H"), "G \u2225 H");
	}

	@Test
	public void SequentialSubstitution() {
		assertEquals(UnicodeTranslator.toUnicode("G ; H"), "G ; H");
	}

	@Test
	public void Precondition() {
		assertEquals(UnicodeTranslator.toUnicode("P | G"), "P \u2223 G");
	}

	@Test
	public void Context() {
		assertEquals(UnicodeTranslator.toUnicode("CONTEXT"), "CONTEXT");
	}

	@Test
	public void Extends() {
		assertEquals(UnicodeTranslator.toUnicode("EXTENDS"), "EXTENDS");
	}

	@Test
	public void Sets() {
		assertEquals(UnicodeTranslator.toUnicode("SETS"), "SETS");
	}

	@Test
	public void Constants() {
		assertEquals(UnicodeTranslator.toUnicode("CONSTANTS"), "CONSTANTS");
	}

	@Test
	public void Axioms() {
		assertEquals(UnicodeTranslator.toUnicode("AXIOMS"), "AXIOMS");
	}

	@Test
	public void Theorems() {
		assertEquals(UnicodeTranslator.toUnicode("THEOREMS"), "THEOREMS");
	}

	@Test
	public void End() {
		assertEquals(UnicodeTranslator.toUnicode("END"), "END");
	}

	@Test
	public void Machine() {
		assertEquals(UnicodeTranslator.toUnicode("MACHINE"), "MACHINE");
	}

	@Test
	public void Refines() {
		assertEquals(UnicodeTranslator.toUnicode("REFINES"), "REFINES");
	}

	@Test
	public void Sees() {
		assertEquals(UnicodeTranslator.toUnicode("SEES"), "SEES");
	}

	@Test
	public void Variables() {
		assertEquals(UnicodeTranslator.toUnicode("VARIABLES"), "VARIABLES");
	}

	@Test
	public void Invariant() {
		assertEquals(UnicodeTranslator.toUnicode("INVARIANT"), "INVARIANT");
	}

	@Test
	public void Variant() {
		assertEquals(UnicodeTranslator.toUnicode("VARIANT"), "VARIANT");
	}

	@Test
	public void Events() {
		assertEquals(UnicodeTranslator.toUnicode("EVENTS"), "EVENTS");
	}

	@Test
	public void Any() {
		assertEquals(UnicodeTranslator.toUnicode("ANY"), "ANY");
	}

	@Test
	public void Where() {
		assertEquals(UnicodeTranslator.toUnicode("WHERE"), "WHERE");
	}

	@Test
	public void With() {
		assertEquals(UnicodeTranslator.toUnicode("WITH"), "WITH");
	}

	@Test
	public void Then() {
		assertEquals(UnicodeTranslator.toUnicode("THEN"), "THEN");
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Letter() {
		assertEquals(UnicodeTranslator.toUnicode("abc"), "abc");
	}

	@Test
	public void LetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc123"), "abc123");
	}

	@Test
	public void LetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc_"), "abc_");
	}

	@Test
	public void LetterANY() {
		assertEquals(UnicodeTranslator.toUnicode("abcANY"), "abcANY");
		assertEquals(UnicodeTranslator.toUnicode("abcany"), "abcany");
	}

	@Test
	public void LetterFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("abcFALSE"), "abcFALSE");
		assertEquals(UnicodeTranslator.toUnicode("abcfalse"), "abcfalse");
	}

	@Test
	public void LetterINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTEGER"), "abcINTEGER");
		assertEquals(UnicodeTranslator.toUnicode("abcinteger"), "abcinteger");
	}

	@Test
	public void LetterINTER() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTER"), "abcINTER");
		assertEquals(UnicodeTranslator.toUnicode("abcinter"), "abcinter");
	}

	@Test
	public void LetterNAT() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT"), "abcNAT");
		assertEquals(UnicodeTranslator.toUnicode("abcnat"), "abcnat");
	}

	@Test
	public void LetterNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT1"), "abcNAT1");
		assertEquals(UnicodeTranslator.toUnicode("abcnat1"), "abcnat1");
	}

	@Test
	public void LetterNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("abcNATURAL"), "abcNATURAL");
		assertEquals(UnicodeTranslator.toUnicode("abcnatural"), "abcnatural");
	}

	@Test
	public void LetterNOT() {
		assertEquals(UnicodeTranslator.toUnicode("abcNOT"), "abcNOT");
		assertEquals(UnicodeTranslator.toUnicode("abcnot"), "abcnot");
	}

	@Test
	public void LetterOR() {
		assertEquals(UnicodeTranslator.toUnicode("abcOR"), "abcOR");
		assertEquals(UnicodeTranslator.toUnicode("abcor"), "abcor");
	}

	@Test
	public void LetterPOW() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW"), "abcPOW");
		assertEquals(UnicodeTranslator.toUnicode("abcpow"), "abcpow");
	}

	@Test
	public void LetterPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW1"), "abcPOW1");
		assertEquals(UnicodeTranslator.toUnicode("abcpow1"), "abcpow1");
	}

	@Test
	public void LetterTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("abcTRUE"), "abcTRUE");
		assertEquals(UnicodeTranslator.toUnicode("abctrue"), "abctrue");
	}

	@Test
	public void LetterUNION() {
		assertEquals(UnicodeTranslator.toUnicode("abcUNION"), "abcUNION");
		assertEquals(UnicodeTranslator.toUnicode("abcunion"), "abcunion");
	}

	@Test
	public void LetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_"), "abc123_");
	}

	@Test
	public void LetterDigitANY() {
		assertEquals(UnicodeTranslator.toUnicode("abc123ANY"), "abc123ANY");
		assertEquals(UnicodeTranslator.toUnicode("abc123any"), "abc123any");
	}

	@Test
	public void LetterDigitFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("abc123FALSE"), "abc123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("abc123false"), "abc123false");
	}

	@Test
	public void LetterDigitINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("abc123INTEGER"),
				"abc123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("abc123integer"),
				"abc123integer");
	}

	@Test
	public void LetterDigitINTER() {
		assertEquals(UnicodeTranslator.toUnicode("abc123INTER"), "abc123INTER");
		assertEquals(UnicodeTranslator.toUnicode("abc123inter"), "abc123inter");
	}

	@Test
	public void LetterDigitNAT() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NAT"), "abc123NAT");
		assertEquals(UnicodeTranslator.toUnicode("abc123nat"), "abc123nat");
	}

	@Test
	public void LetterDigitNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NAT1"), "abc123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("abc123nat1"), "abc123nat1");
	}

	@Test
	public void LetterDigitNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NATURAL"),
				"abc123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("abc123natural"),
				"abc123natural");
	}

	@Test
	public void LetterDigitNOT() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NOT"), "abc123NOT");
		assertEquals(UnicodeTranslator.toUnicode("abc123not"), "abc123not");
	}

	@Test
	public void LetterDigitOR() {
		assertEquals(UnicodeTranslator.toUnicode("abc123OR"), "abc123OR");
		assertEquals(UnicodeTranslator.toUnicode("abc123or"), "abc123or");
	}

	@Test
	public void LetterDigitPOW() {
		assertEquals(UnicodeTranslator.toUnicode("abc123POW"), "abc123POW");
		assertEquals(UnicodeTranslator.toUnicode("abc123pow"), "abc123pow");
	}

	@Test
	public void LetterDigitPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("abc123POW1"), "abc123POW1");
		assertEquals(UnicodeTranslator.toUnicode("abc123pow1"), "abc123pow1");
	}

	@Test
	public void LetterDigitTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("abc123TRUE"), "abc123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("abc123true"), "abc123true");
	}

	@Test
	public void LetterDigitUNION() {
		assertEquals(UnicodeTranslator.toUnicode("abc123UNION"), "abc123UNION");
		assertEquals(UnicodeTranslator.toUnicode("abc123union"), "abc123union");
	}

	@Test
	public void LetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123"), "abc_123");
	}

	@Test
	public void LetterUnderscoreANY() {
		assertEquals(UnicodeTranslator.toUnicode("abc_ANY"), "abc_ANY");
		assertEquals(UnicodeTranslator.toUnicode("abc_any"), "abc_any");
	}

	@Test
	public void LetterUnderscoreFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("abc_FALSE"), "abc_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("abc_false"), "abc_false");
	}

	@Test
	public void LetterUnderscoreINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTEGER"), "abc_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("abc_integer"), "abc_integer");
	}

	@Test
	public void LetterUnderscoreINTER() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTER"), "abc_INTER");
		assertEquals(UnicodeTranslator.toUnicode("abc_inter"), "abc_inter");
	}

	@Test
	public void LetterUnderscoreNAT() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT"), "abc_NAT");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat"), "abc_nat");
	}

	@Test
	public void LetterUnderscoreNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT1"), "abc_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat1"), "abc_nat1");
	}

	@Test
	public void LetterUnderscoreNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NATURAL"), "abc_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("abc_natural"), "abc_natural");
	}

	@Test
	public void LetterUnderscoreNOT() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NOT"), "abc_NOT");
		assertEquals(UnicodeTranslator.toUnicode("abc_not"), "abc_not");
	}

	@Test
	public void LetterUnderscoreOR() {
		assertEquals(UnicodeTranslator.toUnicode("abc_OR"), "abc_OR");
		assertEquals(UnicodeTranslator.toUnicode("abc_or"), "abc_or");
	}

	@Test
	public void LetterUnderscorePOW() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW"), "abc_POW");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow"), "abc_pow");
	}

	@Test
	public void LetterUnderscorePOW1() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW1"), "abc_POW1");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow1"), "abc_pow1");
	}

	@Test
	public void LetterUnderscoreTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("abc_TRUE"), "abc_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("abc_true"), "abc_true");
	}

	@Test
	public void LetterUnderscoreUNION() {
		assertEquals(UnicodeTranslator.toUnicode("abc_UNION"), "abc_UNION");
		assertEquals(UnicodeTranslator.toUnicode("abc_union"), "abc_union");
	}

	@Test
	public void LetterANYDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcANY123"), "abcANY123");
		assertEquals(UnicodeTranslator.toUnicode("abcany123"), "abcany123");
	}

	@Test
	public void LetterFALSEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcFALSE123"), "abcFALSE123");
		assertEquals(UnicodeTranslator.toUnicode("abcfalse123"), "abcfalse123");
	}

	@Test
	public void LetterINTEGERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTEGER123"),
				"abcINTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("abcinteger123"),
				"abcinteger123");
	}

	@Test
	public void LetterINTERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTER123"), "abcINTER123");
		assertEquals(UnicodeTranslator.toUnicode("abcinter123"), "abcinter123");
	}

	@Test
	public void LetterNATDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT123"), "abcNAT123");
		assertEquals(UnicodeTranslator.toUnicode("abcnat123"), "abcnat123");
	}

	@Test
	public void LetterNAT1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT1123"), "abcNAT1123");
		assertEquals(UnicodeTranslator.toUnicode("abcnat1123"), "abcnat1123");
	}

	@Test
	public void LetterNATURALDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNATURAL123"),
				"abcNATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("abcnatural123"),
				"abcnatural123");
	}

	@Test
	public void LetterNOTDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNOT123"), "abcNOT123");
		assertEquals(UnicodeTranslator.toUnicode("abcnot123"), "abcnot123");
	}

	@Test
	public void LetterORDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcOR123"), "abcOR123");
		assertEquals(UnicodeTranslator.toUnicode("abcor123"), "abcor123");
	}

	@Test
	public void LetterPOWDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW123"), "abcPOW123");
		assertEquals(UnicodeTranslator.toUnicode("abcpow123"), "abcpow123");
	}

	@Test
	public void LetterPOW1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW1123"), "abcPOW1123");
		assertEquals(UnicodeTranslator.toUnicode("abcpow1123"), "abcpow1123");
	}

	@Test
	public void LetterTRUEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcTRUE123"), "abcTRUE123");
		assertEquals(UnicodeTranslator.toUnicode("abctrue123"), "abctrue123");
	}

	@Test
	public void LetterUNIONDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcUNION123"), "abcUNION123");
		assertEquals(UnicodeTranslator.toUnicode("abcunion123"), "abcunion123");
	}

	@Test
	public void LetterANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcANY_"), "abcANY_");
		assertEquals(UnicodeTranslator.toUnicode("abcany_"), "abcany_");
	}

	@Test
	public void LetterFALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcFALSE_"), "abcFALSE_");
		assertEquals(UnicodeTranslator.toUnicode("abcfalse_"), "abcfalse_");
	}

	@Test
	public void LetterINTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTEGER_"), "abcINTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("abcinteger_"), "abcinteger_");
	}

	@Test
	public void LetterINTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTER_"), "abcINTER_");
		assertEquals(UnicodeTranslator.toUnicode("abcinter_"), "abcinter_");
	}

	@Test
	public void LetterNATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT_"), "abcNAT_");
		assertEquals(UnicodeTranslator.toUnicode("abcnat_"), "abcnat_");
	}

	@Test
	public void LetterNAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT1_"), "abcNAT1_");
		assertEquals(UnicodeTranslator.toUnicode("abcnat1_"), "abcnat1_");
	}

	@Test
	public void LetterNATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNATURAL_"), "abcNATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("abcnatural_"), "abcnatural_");
	}

	@Test
	public void LetterNOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNOT_"), "abcNOT_");
		assertEquals(UnicodeTranslator.toUnicode("abcnot_"), "abcnot_");
	}

	@Test
	public void LetterORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcOR_"), "abcOR_");
		assertEquals(UnicodeTranslator.toUnicode("abcor_"), "abcor_");
	}

	@Test
	public void LetterPOWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW_"), "abcPOW_");
		assertEquals(UnicodeTranslator.toUnicode("abcpow_"), "abcpow_");
	}

	@Test
	public void LetterPOW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW1_"), "abcPOW1_");
		assertEquals(UnicodeTranslator.toUnicode("abcpow1_"), "abcpow1_");
	}

	@Test
	public void LetterTRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcTRUE_"), "abcTRUE_");
		assertEquals(UnicodeTranslator.toUnicode("abctrue_"), "abctrue_");
	}

	@Test
	public void LetterUNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcUNION_"), "abcUNION_");
		assertEquals(UnicodeTranslator.toUnicode("abcunion_"), "abcunion_");
	}

	@Test
	public void LetterDigitUnderscoreANY() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_ANY"), "abc123_ANY");
		assertEquals(UnicodeTranslator.toUnicode("abc123_any"), "abc123_any");
	}

	@Test
	public void LetterDigitUnderscoreFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_FALSE"),
				"abc123_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("abc123_false"),
				"abc123_false");
	}

	@Test
	public void LetterDigitUnderscoreINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_INTEGER"),
				"abc123_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("abc123_integer"),
				"abc123_integer");
	}

	@Test
	public void LetterDigitUnderscoreINTER() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_INTER"),
				"abc123_INTER");
		assertEquals(UnicodeTranslator.toUnicode("abc123_inter"),
				"abc123_inter");
	}

	@Test
	public void LetterDigitUnderscoreNAT() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_NAT"), "abc123_NAT");
		assertEquals(UnicodeTranslator.toUnicode("abc123_nat"), "abc123_nat");
	}

	@Test
	public void LetterDigitUnderscoreNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_NAT1"), "abc123_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("abc123_nat1"), "abc123_nat1");
	}

	@Test
	public void LetterDigitUnderscoreNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_NATURAL"),
				"abc123_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("abc123_natural"),
				"abc123_natural");
	}

	@Test
	public void LetterDigitUnderscoreNOT() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_NOT"), "abc123_NOT");
		assertEquals(UnicodeTranslator.toUnicode("abc123_not"), "abc123_not");
	}

	@Test
	public void LetterDigitUnderscoreOR() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_OR"), "abc123_OR");
		assertEquals(UnicodeTranslator.toUnicode("abc123_or"), "abc123_or");
	}

	@Test
	public void LetterDigitUnderscorePOW() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_POW"), "abc123_POW");
		assertEquals(UnicodeTranslator.toUnicode("abc123_pow"), "abc123_pow");
	}

	@Test
	public void LetterDigitUnderscorePOW1() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_POW1"), "abc123_POW1");
		assertEquals(UnicodeTranslator.toUnicode("abc123_pow1"), "abc123_pow1");
	}

	@Test
	public void LetterDigitUnderscoreTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_TRUE"), "abc123_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("abc123_true"), "abc123_true");
	}

	@Test
	public void LetterDigitUnderscoreUNION() {
		assertEquals(UnicodeTranslator.toUnicode("abc123_UNION"),
				"abc123_UNION");
		assertEquals(UnicodeTranslator.toUnicode("abc123_union"),
				"abc123_union");
	}

	@Test
	public void LetterDigitANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123ANY_"), "abc123ANY_");
		assertEquals(UnicodeTranslator.toUnicode("abc123any_"), "abc123any_");
	}

	@Test
	public void LetterDigitFALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123FALSE_"),
				"abc123FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("abc123false_"),
				"abc123false_");
	}

	@Test
	public void LetterDigitINTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123INTEGER_"),
				"abc123INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("abc123integer_"),
				"abc123integer_");
	}

	@Test
	public void LetterDigitINTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123INTER_"),
				"abc123INTER_");
		assertEquals(UnicodeTranslator.toUnicode("abc123inter_"),
				"abc123inter_");
	}

	@Test
	public void LetterDigitNATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NAT_"), "abc123NAT_");
		assertEquals(UnicodeTranslator.toUnicode("abc123nat_"), "abc123nat_");
	}

	@Test
	public void LetterDigitNAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NAT1_"), "abc123NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("abc123nat1_"), "abc123nat1_");
	}

	@Test
	public void LetterDigitNATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NATURAL_"),
				"abc123NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("abc123natural_"),
				"abc123natural_");
	}

	@Test
	public void LetterDigitNOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123NOT_"), "abc123NOT_");
		assertEquals(UnicodeTranslator.toUnicode("abc123not_"), "abc123not_");
	}

	@Test
	public void LetterDigitORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123OR_"), "abc123OR_");
		assertEquals(UnicodeTranslator.toUnicode("abc123or_"), "abc123or_");
	}

	@Test
	public void LetterDigitPOWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123POW_"), "abc123POW_");
		assertEquals(UnicodeTranslator.toUnicode("abc123pow_"), "abc123pow_");
	}

	@Test
	public void LetterDigitPOW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123POW1_"), "abc123POW1_");
		assertEquals(UnicodeTranslator.toUnicode("abc123pow1_"), "abc123pow1_");
	}

	@Test
	public void LetterDigitTRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123TRUE_"), "abc123TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("abc123true_"), "abc123true_");
	}

	@Test
	public void LetterDigitUNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abc123UNION_"),
				"abc123UNION_");
		assertEquals(UnicodeTranslator.toUnicode("abc123union_"),
				"abc123union_");
	}

	@Test
	public void LetterUnderscoreDigitANY() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123ANY"), "abc_123ANY");
		assertEquals(UnicodeTranslator.toUnicode("abc_123any"), "abc_123any");
	}

	@Test
	public void LetterUnderscoreDigitFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123FALSE"),
				"abc_123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("abc_123false"),
				"abc_123false");
	}

	@Test
	public void LetterUnderscoreDigitINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123INTEGER"),
				"abc_123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("abc_123integer"),
				"abc_123integer");
	}

	@Test
	public void LetterUnderscoreDigitINTER() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123INTER"),
				"abc_123INTER");
		assertEquals(UnicodeTranslator.toUnicode("abc_123inter"),
				"abc_123inter");
	}

	@Test
	public void LetterUnderscoreDigitANT() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123NAT"), "abc_123NAT");
		assertEquals(UnicodeTranslator.toUnicode("abc_123nat"), "abc_123nat");
	}

	@Test
	public void LetterUnderscoreDigitNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123NAT1"), "abc_123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("abc_123nat1"), "abc_123nat1");
	}

	@Test
	public void LetterUnderscoreDigitNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123NATURAL"),
				"abc_123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("abc_123natural"),
				"abc_123natural");
	}

	@Test
	public void LetterUnderscoreDigitNOT() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123NOT"), "abc_123NOT");
		assertEquals(UnicodeTranslator.toUnicode("abc_123not"), "abc_123not");
	}

	@Test
	public void LetterUnderscoreDigitOR() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123OR"), "abc_123OR");
		assertEquals(UnicodeTranslator.toUnicode("abc_123or"), "abc_123or");
	}

	@Test
	public void LetterUnderscoreDigitPOW() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123POW"), "abc_123POW");
		assertEquals(UnicodeTranslator.toUnicode("abc_123pow"), "abc_123pow");
	}

	@Test
	public void LetterUnderscoreDigitPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123POW1"), "abc_123POW1");
		assertEquals(UnicodeTranslator.toUnicode("abc_123pow1"), "abc_123pow1");
	}

	@Test
	public void LetterUnderscoreDigitTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123TRUE"), "abc_123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("abc_123true"), "abc_123true");
	}

	@Test
	public void LetterUnderscoreDigitUNION() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123UNION"),
				"abc_123UNION");
		assertEquals(UnicodeTranslator.toUnicode("abc_123union"),
				"abc_123union");
	}

	@Test
	public void LetterUnderscoreANYDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_ANY123"), "abc_ANY123");
		assertEquals(UnicodeTranslator.toUnicode("abc_any123"), "abc_any123");
	}

	@Test
	public void LetterUnderscoreFALSEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_FALSE123"),
				"abc_FALSE123");
		assertEquals(UnicodeTranslator.toUnicode("abc_false123"),
				"abc_false123");
	}

	@Test
	public void LetterUnderscoreINTEGERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTEGER123"),
				"abc_INTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("abc_integer123"),
				"abc_integer123");
	}

	@Test
	public void LetterUnderscoreINTERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTER123"),
				"abc_INTER123");
		assertEquals(UnicodeTranslator.toUnicode("abc_inter123"),
				"abc_inter123");
	}

	@Test
	public void LetterUnderscoreNATDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT123"), "abc_NAT123");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat123"), "abc_nat123");
	}

	@Test
	public void LetterUnderscoreNAT1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT1123"), "abc_NAT1123");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat1123"), "abc_nat1123");
	}

	@Test
	public void LetterUnderscoreNATURALDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NATURAL123"),
				"abc_NATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("abc_natural123"),
				"abc_natural123");
	}

	@Test
	public void LetterUnderscoreNOTDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NOT123"), "abc_NOT123");
		assertEquals(UnicodeTranslator.toUnicode("abc_not123"), "abc_not123");
	}

	@Test
	public void LetterUnderscoreORDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_OR123"), "abc_OR123");
		assertEquals(UnicodeTranslator.toUnicode("abc_or123"), "abc_or123");
	}

	@Test
	public void LetterUnderscorePOWDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW123"), "abc_POW123");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow123"), "abc_pow123");
	}

	@Test
	public void LetterUnderscorePOW1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW1123"), "abc_POW1123");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow1123"), "abc_pow1123");
	}

	@Test
	public void LetterUnderscoreTRUEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_TRUE123"), "abc_TRUE123");
		assertEquals(UnicodeTranslator.toUnicode("abc_true123"), "abc_true123");
	}

	@Test
	public void LetterUnderscoreUNIONDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abc_UNION123"),
				"abc_UNION123");
		assertEquals(UnicodeTranslator.toUnicode("abc_union123"),
				"abc_union123");
	}

	@Test
	public void LetterANYDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcANY123_"), "abcANY123_");
		assertEquals(UnicodeTranslator.toUnicode("abcany123_"), "abcany123_");
	}

	@Test
	public void LetterFALSEDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcFALSE123_"),
				"abcFALSE123_");
		assertEquals(UnicodeTranslator.toUnicode("abcfalse123_"),
				"abcfalse123_");
	}

	@Test
	public void LetterINTEGERDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTEGER123_"),
				"abcINTEGER123_");
		assertEquals(UnicodeTranslator.toUnicode("abcinteger123_"),
				"abcinteger123_");
	}

	@Test
	public void LetterINTERDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTER123_"),
				"abcINTER123_");
		assertEquals(UnicodeTranslator.toUnicode("abcinter123_"),
				"abcinter123_");
	}

	@Test
	public void LetterNATDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT123_"), "abcNAT123_");
		assertEquals(UnicodeTranslator.toUnicode("abcnat123_"), "abcnat123_");
	}

	@Test
	public void LetterNAT1DigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT1123_"), "abcNAT1123_");
		assertEquals(UnicodeTranslator.toUnicode("abcnat1123_"), "abcnat1123_");
	}

	@Test
	public void LetterNATURALDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNATURAL123_"),
				"abcNATURAL123_");
		assertEquals(UnicodeTranslator.toUnicode("abcnatural123_"),
				"abcnatural123_");
	}

	@Test
	public void LetterNOTDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcNOT123_"), "abcNOT123_");
		assertEquals(UnicodeTranslator.toUnicode("abcnot123_"), "abcnot123_");
	}

	@Test
	public void LetterORDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcOR123_"), "abcOR123_");
		assertEquals(UnicodeTranslator.toUnicode("abcor123_"), "abcor123_");
	}

	@Test
	public void LetterPOWDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW123_"), "abcPOW123_");
		assertEquals(UnicodeTranslator.toUnicode("abcpow123_"), "abcpow123_");
	}

	@Test
	public void LetterPOW1DigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW1123_"), "abcPOW1123_");
		assertEquals(UnicodeTranslator.toUnicode("abcpow1123_"), "abcpow1123_");
	}

	@Test
	public void LetterTRUEDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcTRUE123_"), "abcTRUE123_");
		assertEquals(UnicodeTranslator.toUnicode("abctrue123_"), "abctrue123_");
	}

	@Test
	public void LetterUNIONDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("abcUNION123_"),
				"abcUNION123_");
		assertEquals(UnicodeTranslator.toUnicode("abcunion123_"),
				"abcunion123_");
	}

	@Test
	public void LetterANYUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcANY_123"), "abcANY_123");
		assertEquals(UnicodeTranslator.toUnicode("abcany_123"), "abcany_123");
	}

	@Test
	public void LetterFALSEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcFALSE_123"),
				"abcFALSE_123");
		assertEquals(UnicodeTranslator.toUnicode("abcfalse_123"),
				"abcfalse_123");
	}

	@Test
	public void LetterINTEGERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTEGER_123"),
				"abcINTEGER_123");
		assertEquals(UnicodeTranslator.toUnicode("abcinteger_123"),
				"abcinteger_123");
	}

	@Test
	public void LetterINTERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcINTER_123"),
				"abcINTER_123");
		assertEquals(UnicodeTranslator.toUnicode("abcinter_123"),
				"abcinter_123");
	}

	@Test
	public void LetterNATUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT_123"), "abcNAT_123");
		assertEquals(UnicodeTranslator.toUnicode("abcnat_123"), "abcnat_123");
	}

	@Test
	public void LetterNAT1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNAT1_123"), "abcNAT1_123");
		assertEquals(UnicodeTranslator.toUnicode("abcnat1_123"), "abcnat1_123");
	}

	@Test
	public void LetterNATURALUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNATURAL_123"),
				"abcNATURAL_123");
		assertEquals(UnicodeTranslator.toUnicode("abcnatural_123"),
				"abcnatural_123");
	}

	@Test
	public void LetterNOTUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcNOT_123"), "abcNOT_123");
		assertEquals(UnicodeTranslator.toUnicode("abcnot_123"), "abcnot_123");
	}

	@Test
	public void LetterORUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcOR_123"), "abcOR_123");
		assertEquals(UnicodeTranslator.toUnicode("abcor_123"), "abcor_123");
	}

	@Test
	public void LetterPOWUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW_123"), "abcPOW_123");
		assertEquals(UnicodeTranslator.toUnicode("abcpow_123"), "abcpow_123");
	}

	@Test
	public void LetterPOW1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcPOW1_123"), "abcPOW1_123");
		assertEquals(UnicodeTranslator.toUnicode("abcpow1_123"), "abcpow1_123");
	}

	@Test
	public void LetterTRUEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcTRUE_123"), "abcTRUE_123");
		assertEquals(UnicodeTranslator.toUnicode("abctrue_123"), "abctrue_123");
	}

	@Test
	public void LetterUNIONUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("abcUNION_123"),
				"abcUNION_123");
		assertEquals(UnicodeTranslator.toUnicode("abcunion_123"),
				"abcunion_123");
	}

	@Test
	public void Digit() {
		assertEquals(UnicodeTranslator.toUnicode("123"), "123");
	}

	@Test
	public void DigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123abc"), "123abc");
	}

	@Test
	public void DigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123_"), "123_");
	}

	@Test
	public void DigitANY() {
		assertEquals(UnicodeTranslator.toUnicode("123ANY"), "123ANY");
		assertEquals(UnicodeTranslator.toUnicode("123any"), "123any");
	}

	@Test
	public void DigitFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("123FALSE"), "123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("123false"), "123false");
	}

	@Test
	public void DigitINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("123INTEGER"), "123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123integer"), "123integer");
	}

	@Test
	public void DigitINTER() {
		assertEquals(UnicodeTranslator.toUnicode("123INTER"), "123INTER");
		assertEquals(UnicodeTranslator.toUnicode("123inter"), "123inter");
	}

	@Test
	public void DigitNAT() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT"), "123NAT");
		assertEquals(UnicodeTranslator.toUnicode("123nat"), "123nat");
	}

	@Test
	public void DigitNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT1"), "123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("123nat1"), "123nat1");
	}

	@Test
	public void DigitNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("123NATURAL"), "123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123natural"), "123natural");
	}

	@Test
	public void DigitNOT() {
		assertEquals(UnicodeTranslator.toUnicode("123NOT"), "123NOT");
		assertEquals(UnicodeTranslator.toUnicode("123not"), "123not");
	}

	@Test
	public void DigitOR() {
		assertEquals(UnicodeTranslator.toUnicode("123OR"), "123OR");
		assertEquals(UnicodeTranslator.toUnicode("123or"), "123or");
	}

	@Test
	public void DigitPOW() {
		assertEquals(UnicodeTranslator.toUnicode("123POW"), "123POW");
		assertEquals(UnicodeTranslator.toUnicode("123pow"), "123pow");
	}

	@Test
	public void DigitPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("123POW1"), "123POW1");
		assertEquals(UnicodeTranslator.toUnicode("123pow1"), "123pow1");
	}

	@Test
	public void DigitTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("123TRUE"), "123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("123true"), "123true");
	}

	@Test
	public void DigitUNION() {
		assertEquals(UnicodeTranslator.toUnicode("123UNION"), "123UNION");
		assertEquals(UnicodeTranslator.toUnicode("123union"), "123union");
	}

	@Test
	public void DigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_"), "123abc_");
	}

	@Test
	public void DigitLetterANY() {
		assertEquals(UnicodeTranslator.toUnicode("123abcANY"), "123abcANY");
		assertEquals(UnicodeTranslator.toUnicode("123abcany"), "123abcany");
	}

	@Test
	public void DigitLetterFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("123abcFALSE"), "123abcFALSE");
		assertEquals(UnicodeTranslator.toUnicode("123abcfalse"), "123abcfalse");
	}

	@Test
	public void DigitLetterINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("123abcINTEGER"),
				"123abcINTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123abcinteger"),
				"123abcinteger");
	}

	@Test
	public void DigitLetterINTER() {
		assertEquals(UnicodeTranslator.toUnicode("123abcINTER"), "123abcINTER");
		assertEquals(UnicodeTranslator.toUnicode("123abcinter"), "123abcinter");
	}

	@Test
	public void DigitLetterNAT() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNAT"), "123abcNAT");
		assertEquals(UnicodeTranslator.toUnicode("123abcnat"), "123abcnat");
	}

	@Test
	public void DigitLetterNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNAT1"), "123abcNAT1");
		assertEquals(UnicodeTranslator.toUnicode("123abcnat1"), "123abcnat1");
	}

	@Test
	public void DigitLetterNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNATURAL"),
				"123abcNATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123abcnatural"),
				"123abcnatural");
	}

	@Test
	public void DigitLetterNOT() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNOT"), "123abcNOT");
		assertEquals(UnicodeTranslator.toUnicode("123abcnot"), "123abcnot");
	}

	@Test
	public void DigitLetterOR() {
		assertEquals(UnicodeTranslator.toUnicode("123abcOR"), "123abcOR");
		assertEquals(UnicodeTranslator.toUnicode("123abcor"), "123abcor");
	}

	@Test
	public void DigitLetterPOW() {
		assertEquals(UnicodeTranslator.toUnicode("123abcPOW"), "123abcPOW");
		assertEquals(UnicodeTranslator.toUnicode("123abcpow"), "123abcpow");
	}

	@Test
	public void DigitLetterPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("123abcPOW1"), "123abcPOW1");
		assertEquals(UnicodeTranslator.toUnicode("123abcpow1"), "123abcpow1");
	}

	@Test
	public void DigitLetterTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("123abcTRUE"), "123abcTRUE");
		assertEquals(UnicodeTranslator.toUnicode("123abctrue"), "123abctrue");
	}

	@Test
	public void DigitLetterUNION() {
		assertEquals(UnicodeTranslator.toUnicode("123abcUNION"), "123abcUNION");
		assertEquals(UnicodeTranslator.toUnicode("123abcunion"), "123abcunion");
	}

	@Test
	public void DigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_abc"), "123_abc");
	}

	@Test
	public void DigitUnderscoreANY() {
		assertEquals(UnicodeTranslator.toUnicode("123_ANY"), "123_ANY");
		assertEquals(UnicodeTranslator.toUnicode("123_any"), "123_any");
	}

	@Test
	public void DigitUnderscoreFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("123_FALSE"), "123_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("123_false"), "123_false");
	}

	@Test
	public void DigitUnderscoreINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTEGER"), "123_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123_integer"), "123_integer");
	}

	@Test
	public void DigitUnderscoreINTER() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTER"), "123_INTER");
		assertEquals(UnicodeTranslator.toUnicode("123_inter"), "123_inter");
	}

	@Test
	public void DigitUnderscoreNAT() {
		assertEquals(UnicodeTranslator.toUnicode("123_NAT"), "123_NAT");
		assertEquals(UnicodeTranslator.toUnicode("123_nat"), "123_nat");
	}

	@Test
	public void DigitUnderscoreNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("123_NAT1"), "123_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("123_nat1"), "123_nat1");
	}

	@Test
	public void DigitUnderscoreNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("123_NATURAL"), "123_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123_natural"), "123_natural");
	}

	@Test
	public void DigitUnderscoreNOT() {
		assertEquals(UnicodeTranslator.toUnicode("123_NOT"), "123_NOT");
		assertEquals(UnicodeTranslator.toUnicode("123_not"), "123_not");
	}

	@Test
	public void DigitUnderscoreOR() {
		assertEquals(UnicodeTranslator.toUnicode("123_OR"), "123_OR");
		assertEquals(UnicodeTranslator.toUnicode("123_or"), "123_or");
	}

	@Test
	public void DigitUnderscorePOW() {
		assertEquals(UnicodeTranslator.toUnicode("123_POW"), "123_POW");
		assertEquals(UnicodeTranslator.toUnicode("123_pow"), "123_pow");
	}

	@Test
	public void DigitUnderscorePOW1() {
		assertEquals(UnicodeTranslator.toUnicode("123_POW1"), "123_POW1");
		assertEquals(UnicodeTranslator.toUnicode("123_pow1"), "123_pow1");
	}

	@Test
	public void DigitUnderscoreTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("123_TRUE"), "123_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("123_true"), "123_true");
	}

	@Test
	public void DigitUnderscoreUNION() {
		assertEquals(UnicodeTranslator.toUnicode("123_UNION"), "123_UNION");
		assertEquals(UnicodeTranslator.toUnicode("123_union"), "123_union");
	}

	@Test
	public void DigitANYLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123ANYabc"), "123ANYabc");
		assertEquals(UnicodeTranslator.toUnicode("123anyabc"), "123anyabc");
	}

	@Test
	public void DigitFALSELetter() {
		assertEquals(UnicodeTranslator.toUnicode("123FALSEabc"), "123FALSEabc");
		assertEquals(UnicodeTranslator.toUnicode("123falseabc"), "123falseabc");
	}

	@Test
	public void DigitINTEGERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123INTEGERabc"),
				"123INTEGERabc");
		assertEquals(UnicodeTranslator.toUnicode("123integerabc"),
				"123integerabc");
	}

	@Test
	public void DigitINTERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123INTERabc"), "123INTERabc");
		assertEquals(UnicodeTranslator.toUnicode("123interabc"), "123interabc");
	}

	@Test
	public void DigitNATLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NATabc"), "123NATabc");
		assertEquals(UnicodeTranslator.toUnicode("123natabc"), "123natabc");
	}

	@Test
	public void DigitNAT1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT1abc"), "123NAT1abc");
		assertEquals(UnicodeTranslator.toUnicode("123nat1abc"), "123nat1abc");
	}

	@Test
	public void DigitNATURALLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NATURALabc"),
				"123NATURALabc");
		assertEquals(UnicodeTranslator.toUnicode("123naturalabc"),
				"123naturalabc");
	}

	@Test
	public void DigitNOTLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NOTabc"), "123NOTabc");
		assertEquals(UnicodeTranslator.toUnicode("123notabc"), "123notabc");
	}

	@Test
	public void DigitORLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123ORabc"), "123ORabc");
		assertEquals(UnicodeTranslator.toUnicode("123orabc"), "123orabc");
	}

	@Test
	public void DigitPOWLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123POWabc"), "123POWabc");
		assertEquals(UnicodeTranslator.toUnicode("123powabc"), "123powabc");
	}

	@Test
	public void DigitPOW1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("123POW1abc"), "123POW1abc");
		assertEquals(UnicodeTranslator.toUnicode("123pow1abc"), "123pow1abc");
	}

	@Test
	public void DigitTRUELetter() {
		assertEquals(UnicodeTranslator.toUnicode("123TRUEabc"), "123TRUEabc");
		assertEquals(UnicodeTranslator.toUnicode("123trueabc"), "123trueabc");
	}

	@Test
	public void DigitUNIONLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123UNIONabc"), "123UNIONabc");
		assertEquals(UnicodeTranslator.toUnicode("123unionabc"), "123unionabc");
	}

	@Test
	public void DigitANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123ANY_"), "123ANY_");
		assertEquals(UnicodeTranslator.toUnicode("123any_"), "123any_");
	}

	@Test
	public void DigitFALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123FALSE_"), "123FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("123false_"), "123false_");
	}

	@Test
	public void DigitINTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123INTEGER_"), "123INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("123integer_"), "123integer_");
	}

	@Test
	public void DigitINTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123INTER_"), "123INTER_");
		assertEquals(UnicodeTranslator.toUnicode("123inter_"), "123inter_");
	}

	@Test
	public void DigitNATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT_"), "123NAT_");
		assertEquals(UnicodeTranslator.toUnicode("123nat_"), "123nat_");
	}

	@Test
	public void DigitNAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT1_"), "123NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("123nat1_"), "123nat1_");
	}

	@Test
	public void DigitNATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NATURAL_"), "123NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("123natural_"), "123natural_");
	}

	@Test
	public void DigitNOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NOT_"), "123NOT_");
		assertEquals(UnicodeTranslator.toUnicode("123not_"), "123not_");
	}

	@Test
	public void DigitORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123OR_"), "123OR_");
		assertEquals(UnicodeTranslator.toUnicode("123or_"), "123or_");
	}

	@Test
	public void DigitPOWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123POW_"), "123POW_");
		assertEquals(UnicodeTranslator.toUnicode("123pow_"), "123pow_");
	}

	@Test
	public void DigitPOW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("123POW1_"), "123POW1_");
		assertEquals(UnicodeTranslator.toUnicode("123pow1_"), "123pow1_");
	}

	@Test
	public void DigitTRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123TRUE_"), "123TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("123true_"), "123true_");
	}

	@Test
	public void DigitUNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123UNION_"), "123UNION_");
		assertEquals(UnicodeTranslator.toUnicode("123union_"), "123union_");
	}

	@Test
	public void DigitLetterUnderscoreANY() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_ANY"), "123abc_ANY");
		assertEquals(UnicodeTranslator.toUnicode("123abc_any"), "123abc_any");
	}

	@Test
	public void DigitLetterUnderscoreFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_FALSE"),
				"123abc_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("123abc_false"),
				"123abc_false");
	}

	@Test
	public void DigitLetterUnderscoreINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_INTEGER"),
				"123abc_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123abc_integer"),
				"123abc_integer");
	}

	@Test
	public void DigitLetterUnderscoreINTER() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_INTER"),
				"123abc_INTER");
		assertEquals(UnicodeTranslator.toUnicode("123abc_inter"),
				"123abc_inter");
	}

	@Test
	public void DigitLetterUnderscoreNAT() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_NAT"), "123abc_NAT");
		assertEquals(UnicodeTranslator.toUnicode("123abc_nat"), "123abc_nat");
	}

	@Test
	public void DigitLetterUnderscoreNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_NAT1"), "123abc_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("123abc_nat1"), "123abc_nat1");
	}

	@Test
	public void DigitLetterUnderscoreNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_NATURAL"),
				"123abc_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123abc_natural"),
				"123abc_natural");
	}

	@Test
	public void DigitLetterUnderscoreNOT() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_NOT"), "123abc_NOT");
		assertEquals(UnicodeTranslator.toUnicode("123abc_not"), "123abc_not");
	}

	@Test
	public void DigitLetterUnderscoreOR() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_OR"), "123abc_OR");
		assertEquals(UnicodeTranslator.toUnicode("123abc_or"), "123abc_or");
	}

	@Test
	public void DigitLetterUnderscorePOW() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_POW"), "123abc_POW");
		assertEquals(UnicodeTranslator.toUnicode("123abc_pow"), "123abc_pow");
	}

	@Test
	public void DigitLetterUnderscorePOW1() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_POW1"), "123abc_POW1");
		assertEquals(UnicodeTranslator.toUnicode("123abc_pow1"), "123abc_pow1");
	}

	@Test
	public void DigitLetterUnderscoreTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_TRUE"), "123abc_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("123abc_true"), "123abc_true");
	}

	@Test
	public void DigitLetterUnderscoreUNION() {
		assertEquals(UnicodeTranslator.toUnicode("123abc_UNION"),
				"123abc_UNION");
		assertEquals(UnicodeTranslator.toUnicode("123abc_union"),
				"123abc_union");
	}

	@Test
	public void DigitLetterANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcANY_"), "123abcANY_");
		assertEquals(UnicodeTranslator.toUnicode("123abcany_"), "123abcany_");
	}

	@Test
	public void DigitLetterFALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcFALSE_"),
				"123abcFALSE_");
		assertEquals(UnicodeTranslator.toUnicode("123abcfalse_"),
				"123abcfalse_");
	}

	@Test
	public void DigitLetterINTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcINTEGER_"),
				"123abcINTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("123abcinteger_"),
				"123abcinteger_");
	}

	@Test
	public void DigitLetterINTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcINTER_"),
				"123abcINTER_");
		assertEquals(UnicodeTranslator.toUnicode("123abcinter_"),
				"123abcinter_");
	}

	@Test
	public void DigitLetterNATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNAT_"), "123abcNAT_");
		assertEquals(UnicodeTranslator.toUnicode("123abcnat_"), "123abcnat_");
	}

	@Test
	public void DigitLetterNAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNAT1_"), "123abcNAT1_");
		assertEquals(UnicodeTranslator.toUnicode("123abcnat1_"), "123abcnat1_");
	}

	@Test
	public void DigitLetterNATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNATURAL_"),
				"123abcNATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("123abcnatural_"),
				"123abcnatural_");
	}

	@Test
	public void DigitLetterNOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcNOT_"), "123abcNOT_");
		assertEquals(UnicodeTranslator.toUnicode("123abcnot_"), "123abcnot_");
	}

	@Test
	public void DigitLetterORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcOR_"), "123abcOR_");
		assertEquals(UnicodeTranslator.toUnicode("123abcor_"), "123abcor_");
	}

	@Test
	public void DigitLetterPOWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcPOW_"), "123abcPOW_");
		assertEquals(UnicodeTranslator.toUnicode("123abcpow_"), "123abcpow_");
	}

	@Test
	public void DigitLetterPOW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcPOW1_"), "123abcPOW1_");
		assertEquals(UnicodeTranslator.toUnicode("123abcpow1_"), "123abcpow1_");
	}

	@Test
	public void DigitLetterTRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcTRUE_"), "123abcTRUE_");
		assertEquals(UnicodeTranslator.toUnicode("123abctrue_"), "123abctrue_");
	}

	@Test
	public void DigitLetterUNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123abcUNION_"),
				"123abcUNION_");
		assertEquals(UnicodeTranslator.toUnicode("123abcunion_"),
				"123abcunion_");
	}

	@Test
	public void DigitUnderscoreLetterANY() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcANY"), "123_abcANY");
		assertEquals(UnicodeTranslator.toUnicode("123_abcany"), "123_abcany");
	}

	@Test
	public void DigitUnderscoreLetterFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcFALSE"),
				"123_abcFALSE");
		assertEquals(UnicodeTranslator.toUnicode("123_abcfalse"),
				"123_abcfalse");
	}

	@Test
	public void DigitUnderscoreLetterINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcINTEGER"),
				"123_abcINTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123_abcinteger"),
				"123_abcinteger");
	}

	@Test
	public void DigitUnderscoreLetterINTER() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcINTER"),
				"123_abcINTER");
		assertEquals(UnicodeTranslator.toUnicode("123_abcinter"),
				"123_abcinter");
	}

	@Test
	public void DigitUnderscoreLetterNAT() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcNAT"), "123_abcNAT");
		assertEquals(UnicodeTranslator.toUnicode("123_abcnat"), "123_abcnat");
	}

	@Test
	public void DigitUnderscoreLetterNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcNAT1"), "123_abcNAT1");
		assertEquals(UnicodeTranslator.toUnicode("123_abcnat1"), "123_abcnat1");
	}

	@Test
	public void DigitUnderscoreLetterNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcNATURAL"),
				"123_abcNATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123_abcnatural"),
				"123_abcnatural");
	}

	@Test
	public void DigitUnderscoreLetterNOT() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcNOT"), "123_abcNOT");
		assertEquals(UnicodeTranslator.toUnicode("123_abcnot"), "123_abcnot");
	}

	@Test
	public void DigitUnderscoreLetterOR() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcOR"), "123_abcOR");
		assertEquals(UnicodeTranslator.toUnicode("123_abcor"), "123_abcor");
	}

	@Test
	public void DigitUnderscoreLetterPOW() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcPOW"), "123_abcPOW");
		assertEquals(UnicodeTranslator.toUnicode("123_abcpow"), "123_abcpow");
	}

	@Test
	public void DigitUnderscoreLetterPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcPOW1"), "123_abcPOW1");
		assertEquals(UnicodeTranslator.toUnicode("123_abcpow1"), "123_abcpow1");
	}

	@Test
	public void DigitUnderscoreLetterTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcTRUE"), "123_abcTRUE");
		assertEquals(UnicodeTranslator.toUnicode("123_abctrue"), "123_abctrue");
	}

	@Test
	public void DigitUnderscoreLetterUNION() {
		assertEquals(UnicodeTranslator.toUnicode("123_abcUNION"),
				"123_abcUNION");
		assertEquals(UnicodeTranslator.toUnicode("123_abcunion"),
				"123_abcunion");
	}

	@Test
	public void DigitUnderscoreANYLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_ANYabc"), "123_ANYabc");
		assertEquals(UnicodeTranslator.toUnicode("123_anyabc"), "123_anyabc");
	}

	@Test
	public void DigitUnderscoreFALSELetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_FALSEabc"),
				"123_FALSEabc");
		assertEquals(UnicodeTranslator.toUnicode("123_falseabc"),
				"123_falseabc");
	}

	@Test
	public void DigitUnderscoreINTEGERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTEGERabc"),
				"123_INTEGERabc");
		assertEquals(UnicodeTranslator.toUnicode("123_integerabc"),
				"123_integerabc");
	}

	@Test
	public void DigitUnderscoreINTERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTERabc"),
				"123_INTERabc");
		assertEquals(UnicodeTranslator.toUnicode("123_interabc"),
				"123_interabc");
	}

	@Test
	public void DigitUnderscoreNATLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_NATabc"), "123_NATabc");
		assertEquals(UnicodeTranslator.toUnicode("123_natabc"), "123_natabc");
	}

	@Test
	public void DigitUnderscoreNAT1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("123_NAT1abc"), "123_NAT1abc");
		assertEquals(UnicodeTranslator.toUnicode("123_nat1abc"), "123_nat1abc");
	}

	@Test
	public void DigitUnderscoreNATURALLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_NATURALabc"),
				"123_NATURALabc");
		assertEquals(UnicodeTranslator.toUnicode("123_naturalabc"),
				"123_naturalabc");
	}

	@Test
	public void DigitUnderscoreNOTLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_NOTabc"), "123_NOTabc");
		assertEquals(UnicodeTranslator.toUnicode("123_notabc"), "123_notabc");
	}

	@Test
	public void DigitUnderscoreORLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_ORabc"), "123_ORabc");
		assertEquals(UnicodeTranslator.toUnicode("123_orabc"), "123_orabc");
	}

	@Test
	public void DigitUnderscorePOWLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_POWabc"), "123_POWabc");
		assertEquals(UnicodeTranslator.toUnicode("123_powabc"), "123_powabc");
	}

	@Test
	public void DigitUnderscorePOW1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("123_POW1abc"), "123_POW1abc");
		assertEquals(UnicodeTranslator.toUnicode("123_pow1abc"), "123_pow1abc");
	}

	@Test
	public void DigitUnderscoreTRUELetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_TRUEabc"), "123_TRUEabc");
		assertEquals(UnicodeTranslator.toUnicode("123_trueabc"), "123_trueabc");
	}

	@Test
	public void DigitUnderscoreUNIONLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123_UNIONabc"),
				"123_UNIONabc");
		assertEquals(UnicodeTranslator.toUnicode("123_unionabc"),
				"123_unionabc");
	}

	@Test
	public void DigitANYLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123ANYabc_"), "123ANYabc_");
		assertEquals(UnicodeTranslator.toUnicode("123anyabc_"), "123anyabc_");
	}

	@Test
	public void DigitFALSELetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123FALSEabc_"),
				"123FALSEabc_");
		assertEquals(UnicodeTranslator.toUnicode("123falseabc_"),
				"123falseabc_");
	}

	@Test
	public void DigitINTEGERLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123INTEGERabc_"),
				"123INTEGERabc_");
		assertEquals(UnicodeTranslator.toUnicode("123integerabc_"),
				"123integerabc_");
	}

	@Test
	public void DigitINTERLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123INTERabc_"),
				"123INTERabc_");
		assertEquals(UnicodeTranslator.toUnicode("123interabc_"),
				"123interabc_");
	}

	@Test
	public void DigitNATLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NATabc_"), "123NATabc_");
		assertEquals(UnicodeTranslator.toUnicode("123natabc_"), "123natabc_");
	}

	@Test
	public void DigitNAT1LetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT1abc_"), "123NAT1abc_");
		assertEquals(UnicodeTranslator.toUnicode("123nat1abc_"), "123nat1abc_");
	}

	@Test
	public void DigitNATURALLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NATURALabc_"),
				"123NATURALabc_");
		assertEquals(UnicodeTranslator.toUnicode("123naturalabc_"),
				"123naturalabc_");
	}

	@Test
	public void DigitNOTLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123NOTabc_"), "123NOTabc_");
		assertEquals(UnicodeTranslator.toUnicode("123notabc_"), "123notabc_");
	}

	@Test
	public void DigitORLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123ORabc_"), "123ORabc_");
		assertEquals(UnicodeTranslator.toUnicode("123orabc_"), "123orabc_");
	}

	@Test
	public void DigitPOWLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123POWabc_"), "123POWabc_");
		assertEquals(UnicodeTranslator.toUnicode("123powabc_"), "123powabc_");
	}

	@Test
	public void DigitPOW1LetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123POW1abc_"), "123POW1abc_");
		assertEquals(UnicodeTranslator.toUnicode("123pow1abc_"), "123pow1abc_");
	}

	@Test
	public void DigitTRUELetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123TRUEabc_"), "123TRUEabc_");
		assertEquals(UnicodeTranslator.toUnicode("123trueabc_"), "123trueabc_");
	}

	@Test
	public void DigitUNIONLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("123UNIONabc_"),
				"123UNIONabc_");
		assertEquals(UnicodeTranslator.toUnicode("123unionabc_"),
				"123unionabc_");
	}

	@Test
	public void DigitANYUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123ANY_abc"), "123ANY_abc");
		assertEquals(UnicodeTranslator.toUnicode("123any_abc"), "123any_abc");
	}

	@Test
	public void DigitFALSEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123FALSE_abc"),
				"123FALSE_abc");
		assertEquals(UnicodeTranslator.toUnicode("123false_abc"),
				"123false_abc");
	}

	@Test
	public void DigitINTEGERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123INTEGER_abc"),
				"123INTEGER_abc");
		assertEquals(UnicodeTranslator.toUnicode("123integer_abc"),
				"123integer_abc");
	}

	@Test
	public void DigitINTERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123INTER_abc"),
				"123INTER_abc");
		assertEquals(UnicodeTranslator.toUnicode("123inter_abc"),
				"123inter_abc");
	}

	@Test
	public void DigitNATUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT_abc"), "123NAT_abc");
		assertEquals(UnicodeTranslator.toUnicode("123nat_abc"), "123nat_abc");
	}

	@Test
	public void DigitNAT1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NAT1_abc"), "123NAT1_abc");
		assertEquals(UnicodeTranslator.toUnicode("123nat1_abc"), "123nat1_abc");
	}

	@Test
	public void DigitNATURALUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NATURAL_abc"),
				"123NATURAL_abc");
		assertEquals(UnicodeTranslator.toUnicode("123natural_abc"),
				"123natural_abc");
	}

	@Test
	public void DigitNOTUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123NOT_abc"), "123NOT_abc");
		assertEquals(UnicodeTranslator.toUnicode("123not_abc"), "123not_abc");
	}

	@Test
	public void DigitORUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123OR_abc"), "123OR_abc");
		assertEquals(UnicodeTranslator.toUnicode("123or_abc"), "123or_abc");
	}

	@Test
	public void DigitPOWUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123POW_abc"), "123POW_abc");
		assertEquals(UnicodeTranslator.toUnicode("123pow_abc"), "123pow_abc");
	}

	@Test
	public void DigitPOW1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123POW1_abc"), "123POW1_abc");
		assertEquals(UnicodeTranslator.toUnicode("123pow1_abc"), "123pow1_abc");
	}

	@Test
	public void DigitTRUEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123TRUE_abc"), "123TRUE_abc");
		assertEquals(UnicodeTranslator.toUnicode("123true_abc"), "123true_abc");
	}

	@Test
	public void DigitUNIONUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("123UNION_abc"),
				"123UNION_abc");
		assertEquals(UnicodeTranslator.toUnicode("123union_abc"),
				"123union_abc");
	}

	@Test
	public void Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("_"), "_");
	}

	@Test
	public void UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_abc"), "_abc");
	}

	@Test
	public void UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_123"), "_123");
	}

	@Test
	public void UnderscoreANY() {
		assertEquals(UnicodeTranslator.toUnicode("_ANY"), "_ANY");
		assertEquals(UnicodeTranslator.toUnicode("_any"), "_any");
	}

	@Test
	public void UnderscoreFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSE"), "_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("_false"), "_false");
	}

	@Test
	public void UnderscoreINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("_INTEGER"), "_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_integer"), "_integer");
	}

	@Test
	public void UnderscoreINTER() {
		assertEquals(UnicodeTranslator.toUnicode("_INTER"), "_INTER");
		assertEquals(UnicodeTranslator.toUnicode("_inter"), "_inter");
	}

	@Test
	public void UnderscoreNAT() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT"), "_NAT");
		assertEquals(UnicodeTranslator.toUnicode("_nat"), "_nat");
	}

	@Test
	public void UnderscoreNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1"), "_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("_nat1"), "_nat1");
	}

	@Test
	public void UnderscoreNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURAL"), "_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_natural"), "_natural");
	}

	@Test
	public void UnderscoreNOT() {
		assertEquals(UnicodeTranslator.toUnicode("_NOT"), "_NOT");
		assertEquals(UnicodeTranslator.toUnicode("_not"), "_not");
	}

	@Test
	public void UnderscoreOR() {
		assertEquals(UnicodeTranslator.toUnicode("_OR"), "_OR");
		assertEquals(UnicodeTranslator.toUnicode("_or"), "_or");
	}

	@Test
	public void UnderscorePOW() {
		assertEquals(UnicodeTranslator.toUnicode("_POW"), "_POW");
		assertEquals(UnicodeTranslator.toUnicode("_pow"), "_pow");
	}

	@Test
	public void UnderscorePOW1() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1"), "_POW1");
		assertEquals(UnicodeTranslator.toUnicode("_pow1"), "_pow1");
	}

	@Test
	public void UnderscoreTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUE"), "_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("_true"), "_true");
	}

	@Test
	public void UnderscoreUNION() {
		assertEquals(UnicodeTranslator.toUnicode("_UNION"), "_UNION");
		assertEquals(UnicodeTranslator.toUnicode("_union"), "_union");
	}

	@Test
	public void UnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123"), "_abc123");
	}

	@Test
	public void UnderscoreLetterANY() {
		assertEquals(UnicodeTranslator.toUnicode("_123ANY"), "_123ANY");
		assertEquals(UnicodeTranslator.toUnicode("_123any"), "_123any");
	}

	@Test
	public void UnderscoreLetterFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("_123FALSE"), "_123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("_123false"), "_123false");
	}

	@Test
	public void UnderscoreLetterINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTEGER"), "_123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_123integer"), "_123integer");
	}

	@Test
	public void UnderscoreLetterINTER() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTER"), "_123INTER");
		assertEquals(UnicodeTranslator.toUnicode("_123inter"), "_123inter");
	}

	@Test
	public void UnderscoreLetterNAT() {
		assertEquals(UnicodeTranslator.toUnicode("_123NAT"), "_123NAT");
		assertEquals(UnicodeTranslator.toUnicode("_123nat"), "_123nat");
	}

	@Test
	public void UnderscoreLetterNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("_123NAT1"), "_123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("_123nat1"), "_123nat1");
	}

	@Test
	public void UnderscoreLetterNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("_123NATURAL"), "_123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_123natural"), "_123natural");
	}

	@Test
	public void UnderscoreLetterNOT() {
		assertEquals(UnicodeTranslator.toUnicode("_123NOT"), "_123NOT");
		assertEquals(UnicodeTranslator.toUnicode("_123not"), "_123not");
	}

	@Test
	public void UnderscoreLetterOR() {
		assertEquals(UnicodeTranslator.toUnicode("_123OR"), "_123OR");
		assertEquals(UnicodeTranslator.toUnicode("_123or"), "_123or");
	}

	@Test
	public void UnderscoreLetterPOW() {
		assertEquals(UnicodeTranslator.toUnicode("_123POW"), "_123POW");
		assertEquals(UnicodeTranslator.toUnicode("_123pow"), "_123pow");
	}

	@Test
	public void UnderscoreLetterPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("_123POW1"), "_123POW1");
		assertEquals(UnicodeTranslator.toUnicode("_123pow1"), "_123pow1");
	}

	@Test
	public void UnderscoreLetterTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("_123TRUE"), "_123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("_123true"), "_123true");
	}

	@Test
	public void UnderscoreLetterUNION() {
		assertEquals(UnicodeTranslator.toUnicode("_123UNION"), "_123UNION");
		assertEquals(UnicodeTranslator.toUnicode("_123union"), "_123union");
	}

	@Test
	public void UnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123abc"), "_123abc");
	}

	@Test
	public void UnderscoreDigitANY() {
		assertEquals(UnicodeTranslator.toUnicode("_123ANY"), "_123ANY");
		assertEquals(UnicodeTranslator.toUnicode("_123any"), "_123any");
	}

	@Test
	public void UnderscoreDigitFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("_123FALSE"), "_123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("_123false"), "_123false");
	}

	@Test
	public void UnderscoreDigitINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTEGER"), "_123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_123integer"), "_123integer");
	}

	@Test
	public void UnderscoreDigitINTER() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTER"), "_123INTER");
		assertEquals(UnicodeTranslator.toUnicode("_123inter"), "_123inter");
	}

	@Test
	public void UnderscoreDigitNAT() {
		assertEquals(UnicodeTranslator.toUnicode("_123NAT"), "_123NAT");
		assertEquals(UnicodeTranslator.toUnicode("_123nat"), "_123nat");
	}

	@Test
	public void UnderscoreDigitNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("_123NAT1"), "_123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("_123nat1"), "_123nat1");
	}

	@Test
	public void UnderscoreDigitNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("_123NATURAL"), "_123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_123natural"), "_123natural");
	}

	@Test
	public void UnderscoreDigitNOT() {
		assertEquals(UnicodeTranslator.toUnicode("_123NOT"), "_123NOT");
		assertEquals(UnicodeTranslator.toUnicode("_123not"), "_123not");
	}

	@Test
	public void UnderscoreDigitOR() {
		assertEquals(UnicodeTranslator.toUnicode("_123OR"), "_123OR");
		assertEquals(UnicodeTranslator.toUnicode("_123or"), "_123or");
	}

	@Test
	public void UnderscoreDigitPOW() {
		assertEquals(UnicodeTranslator.toUnicode("_123POW"), "_123POW");
		assertEquals(UnicodeTranslator.toUnicode("_123pow"), "_123pow");
	}

	@Test
	public void UnderscoreDigitPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("_123POW1"), "_123POW1");
		assertEquals(UnicodeTranslator.toUnicode("_123pow1"), "_123pow1");
	}

	@Test
	public void UnderscoreDigitTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("_123TRUE"), "_123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("_123true"), "_123true");
	}

	@Test
	public void UnderscoreDigitUNION() {
		assertEquals(UnicodeTranslator.toUnicode("_123UNION"), "_123UNION");
		assertEquals(UnicodeTranslator.toUnicode("_123union"), "_123union");
	}

	@Test
	public void UnderscoreANYLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_ANYabc"), "_ANYabc");
		assertEquals(UnicodeTranslator.toUnicode("_anyabc"), "_anyabc");
	}

	@Test
	public void UnderscoreFALSELetter() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSEabc"), "_FALSEabc");
		assertEquals(UnicodeTranslator.toUnicode("_falseabc"), "_falseabc");
	}

	@Test
	public void UnderscoreINTEGERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_INTEGERabc"), "_INTEGERabc");
		assertEquals(UnicodeTranslator.toUnicode("_integerabc"), "_integerabc");
	}

	@Test
	public void UnderscoreINTERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_INTERabc"), "_INTERabc");
		assertEquals(UnicodeTranslator.toUnicode("_interabc"), "_interabc");
	}

	@Test
	public void UnderscoreNATLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NATabc"), "_NATabc");
		assertEquals(UnicodeTranslator.toUnicode("_natabc"), "_natabc");
	}

	@Test
	public void UnderscoreNAT1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1abc"), "_NAT1abc");
		assertEquals(UnicodeTranslator.toUnicode("_nat1abc"), "_nat1abc");
	}

	@Test
	public void UnderscoreNATURALLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURALabc"), "_NATURALabc");
		assertEquals(UnicodeTranslator.toUnicode("_naturalabc"), "_naturalabc");
	}

	@Test
	public void UnderscoreNOTLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NOTabc"), "_NOTabc");
		assertEquals(UnicodeTranslator.toUnicode("_notabc"), "_notabc");
	}

	@Test
	public void UnderscoreORLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_ORabc"), "_ORabc");
		assertEquals(UnicodeTranslator.toUnicode("_orabc"), "_orabc");
	}

	@Test
	public void UnderscorePOWLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_POWabc"), "_POWabc");
		assertEquals(UnicodeTranslator.toUnicode("_powabc"), "_powabc");
	}

	@Test
	public void UnderscorePOW1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1abc"), "_POW1abc");
		assertEquals(UnicodeTranslator.toUnicode("_pow1abc"), "_pow1abc");
	}

	@Test
	public void UnderscoreTRUELetter() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUEabc"), "_TRUEabc");
		assertEquals(UnicodeTranslator.toUnicode("_trueabc"), "_trueabc");
	}

	@Test
	public void UnderscoreUNIONLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_UNIONabc"), "_UNIONabc");
		assertEquals(UnicodeTranslator.toUnicode("_unionabc"), "_unionabc");
	}

	@Test
	public void UnderscoreANYDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_ANY123"), "_ANY123");
		assertEquals(UnicodeTranslator.toUnicode("_any123"), "_any123");
	}

	@Test
	public void UnderscoreFALSEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSE123"), "_FALSE123");
		assertEquals(UnicodeTranslator.toUnicode("_false123"), "_false123");
	}

	@Test
	public void UnderscoreINTEGERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_INTEGER123"), "_INTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("_integer123"), "_integer123");
	}

	@Test
	public void UnderscoreINTERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_INTER123"), "_INTER123");
		assertEquals(UnicodeTranslator.toUnicode("_inter123"), "_inter123");
	}

	@Test
	public void UnderscoreNATDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT123"), "_NAT123");
		assertEquals(UnicodeTranslator.toUnicode("_nat123"), "_nat123");
	}

	@Test
	public void UnderscoreNAT1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1123"), "_NAT1123");
		assertEquals(UnicodeTranslator.toUnicode("_nat1123"), "_nat1123");
	}

	@Test
	public void UnderscoreNATURALDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURAL123"), "_NATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("_natural123"), "_natural123");
	}

	@Test
	public void UnderscoreNOTDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NOT123"), "_NOT123");
		assertEquals(UnicodeTranslator.toUnicode("_not123"), "_not123");
	}

	@Test
	public void UnderscoreORDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_OR123"), "_OR123");
		assertEquals(UnicodeTranslator.toUnicode("_or123"), "_or123");
	}

	@Test
	public void UnderscorePOWDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_POW123"), "_POW123");
		assertEquals(UnicodeTranslator.toUnicode("_pow123"), "_pow123");
	}

	@Test
	public void UnderscorePOW1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1123"), "_POW1123");
		assertEquals(UnicodeTranslator.toUnicode("_pow1123"), "_pow1123");
	}

	@Test
	public void UnderscoreTRUEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUE123"), "_TRUE123");
		assertEquals(UnicodeTranslator.toUnicode("_true123"), "_true123");
	}

	@Test
	public void UnderscoreUNIONDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_UNION123"), "_UNION123");
		assertEquals(UnicodeTranslator.toUnicode("_union123"), "_union123");
	}

	@Test
	public void UnderscoreLetterDigitANY() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123ANY"), "_abc123ANY");
		assertEquals(UnicodeTranslator.toUnicode("_abc123any"), "_abc123any");
	}

	@Test
	public void UnderscoreLetterDigitFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123FALSE"),
				"_abc123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("_abc123false"),
				"_abc123false");
	}

	@Test
	public void UnderscoreLetterDigitINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123INTEGER"),
				"_abc123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_abc123integer"),
				"_abc123integer");
	}

	@Test
	public void UnderscoreLetterDigitINTER() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123INTER"),
				"_abc123INTER");
		assertEquals(UnicodeTranslator.toUnicode("_abc123inter"),
				"_abc123inter");
	}

	@Test
	public void UnderscoreLetterDigitNAT() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123NAT"), "_abc123NAT");
		assertEquals(UnicodeTranslator.toUnicode("_abc123nat"), "_abc123nat");
	}

	@Test
	public void UnderscoreLetterDigitNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123NAT1"), "_abc123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("_abc123nat1"), "_abc123nat1");
	}

	@Test
	public void UnderscoreLetterDigitNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123NATURAL"),
				"_abc123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_abc123natural"),
				"_abc123natural");
	}

	@Test
	public void UnderscoreLetterDigitNOT() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123NOT"), "_abc123NOT");
		assertEquals(UnicodeTranslator.toUnicode("_abc123not"), "_abc123not");
	}

	@Test
	public void UnderscoreLetterDigitOR() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123OR"), "_abc123OR");
		assertEquals(UnicodeTranslator.toUnicode("_abc123or"), "_abc123or");
	}

	@Test
	public void UnderscoreLetterDigitPOW() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123POW"), "_abc123POW");
		assertEquals(UnicodeTranslator.toUnicode("_abc123pow"), "_abc123pow");
	}

	@Test
	public void UnderscoreLetterDigitPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123POW1"), "_abc123POW1");
		assertEquals(UnicodeTranslator.toUnicode("_abc123pow1"), "_abc123pow1");
	}

	@Test
	public void UnderscoreLetterDigitTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123TRUE"), "_abc123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("_abc123true"), "_abc123true");
	}

	@Test
	public void UnderscoreLetterDigitUNION() {
		assertEquals(UnicodeTranslator.toUnicode("_abc123UNION"),
				"_abc123UNION");
		assertEquals(UnicodeTranslator.toUnicode("_abc123union"),
				"_abc123union");
	}

	@Test
	public void UnderscoreLetterANYDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcANY123"), "_abcANY123");
		assertEquals(UnicodeTranslator.toUnicode("_abcany123"), "_abcany123");
	}

	@Test
	public void UnderscoreLetterFALSEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcFALSE123"),
				"_abcFALSE123");
		assertEquals(UnicodeTranslator.toUnicode("_abcfalse123"),
				"_abcfalse123");
	}

	@Test
	public void UnderscoreLetterINTEGERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcINTEGER123"),
				"_abcINTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("_abcinteger123"),
				"_abcinteger123");
	}

	@Test
	public void UnderscoreLetterINTERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcINTER123"),
				"_abcINTER123");
		assertEquals(UnicodeTranslator.toUnicode("_abcinter123"),
				"_abcinter123");
	}

	@Test
	public void UnderscoreLetterNATDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcNAT123"), "_abcNAT123");
		assertEquals(UnicodeTranslator.toUnicode("_abcnat123"), "_abcnat123");
	}

	@Test
	public void UnderscoreLetterNAT1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcNAT1123"), "_abcNAT1123");
		assertEquals(UnicodeTranslator.toUnicode("_abcnat1123"), "_abcnat1123");
	}

	@Test
	public void UnderscoreLetterNATURALDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcNATURAL123"),
				"_abcNATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("_abcnatural123"),
				"_abcnatural123");
	}

	@Test
	public void UnderscoreLetterNOTDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcNOT123"), "_abcNOT123");
		assertEquals(UnicodeTranslator.toUnicode("_abcnot123"), "_abcnot123");
	}

	@Test
	public void UnderscoreLetterORDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcOR123"), "_abcOR123");
		assertEquals(UnicodeTranslator.toUnicode("_abcor123"), "_abcor123");
	}

	@Test
	public void UnderscoreLetterPOWDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcPOW123"), "_abcPOW123");
		assertEquals(UnicodeTranslator.toUnicode("_abcpow123"), "_abcpow123");
	}

	@Test
	public void UnderscoreLetterPOW1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcPOW1123"), "_abcPOW1123");
		assertEquals(UnicodeTranslator.toUnicode("_abcpow1123"), "_abcpow1123");
	}

	@Test
	public void UnderscoreLetterTRUEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcTRUE123"), "_abcTRUE123");
		assertEquals(UnicodeTranslator.toUnicode("_abctrue123"), "_abctrue123");
	}

	@Test
	public void UnderscoreLetterUNIONDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_abcUNION123"),
				"_abcUNION123");
		assertEquals(UnicodeTranslator.toUnicode("_abcunion123"),
				"_abcunion123");
	}

	@Test
	public void UnderscoreDigitLetterANY() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcANY"), "_123abcANY");
		assertEquals(UnicodeTranslator.toUnicode("_123abcany"), "_123abcany");
	}

	@Test
	public void UnderscoreDigitLetterFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcFALSE"),
				"_123abcFALSE");
		assertEquals(UnicodeTranslator.toUnicode("_123abcfalse"),
				"_123abcfalse");
	}

	@Test
	public void UnderscoreDigitLetterINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcINTEGER"),
				"_123abcINTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_123abcinteger"),
				"_123abcinteger");
	}

	@Test
	public void UnderscoreDigitLetterINTER() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcINTER"),
				"_123abcINTER");
		assertEquals(UnicodeTranslator.toUnicode("_123abcinter"),
				"_123abcinter");
	}

	@Test
	public void UnderscoreDigitLetterNAT() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcNAT"), "_123abcNAT");
		assertEquals(UnicodeTranslator.toUnicode("_123abcnat"), "_123abcnat");
	}

	@Test
	public void UnderscoreDigitLetterNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcNAT1"), "_123abcNAT1");
		assertEquals(UnicodeTranslator.toUnicode("_123abcnat1"), "_123abcnat1");
	}

	@Test
	public void UnderscoreDigitLetterNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcNATURAL"),
				"_123abcNATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_123abcnatural"),
				"_123abcnatural");
	}

	@Test
	public void UnderscoreDigitLetterNOT() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcNOT"), "_123abcNOT");
		assertEquals(UnicodeTranslator.toUnicode("_123abcnot"), "_123abcnot");
	}

	@Test
	public void UnderscoreDigitLetterOR() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcOR"), "_123abcOR");
		assertEquals(UnicodeTranslator.toUnicode("_123abcor"), "_123abcor");
	}

	@Test
	public void UnderscoreDigitLetterPOW() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcPOW"), "_123abcPOW");
		assertEquals(UnicodeTranslator.toUnicode("_123abcpow"), "_123abcpow");
	}

	@Test
	public void UnderscoreDigitLetterPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcPOW1"), "_123abcPOW1");
		assertEquals(UnicodeTranslator.toUnicode("_123abcpow1"), "_123abcpow1");
	}

	@Test
	public void UnderscoreDigitLetterTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcTRUE"), "_123abcTRUE");
		assertEquals(UnicodeTranslator.toUnicode("_123abctrue"), "_123abctrue");
	}

	@Test
	public void UnderscoreDigitLetterUNION() {
		assertEquals(UnicodeTranslator.toUnicode("_123abcUNION"),
				"_123abcUNION");
		assertEquals(UnicodeTranslator.toUnicode("_123abcunion"),
				"_123abcunion");
	}

	@Test
	public void UnderscoreDigitANYLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123ANYabc"), "_123ANYabc");
		assertEquals(UnicodeTranslator.toUnicode("_123anyabc"), "_123anyabc");
	}

	@Test
	public void UnderscoreDigitFALSELetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123FALSEabc"),
				"_123FALSEabc");
		assertEquals(UnicodeTranslator.toUnicode("_123falseabc"),
				"_123falseabc");
	}

	@Test
	public void UnderscoreDigitINTEGERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTEGERabc"),
				"_123INTEGERabc");
		assertEquals(UnicodeTranslator.toUnicode("_123integerabc"),
				"_123integerabc");
	}

	@Test
	public void UnderscoreDigitINTERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123INTERabc"),
				"_123INTERabc");
		assertEquals(UnicodeTranslator.toUnicode("_123interabc"),
				"_123interabc");
	}

	@Test
	public void UnderscoreDigitNATLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123NATabc"), "_123NATabc");
		assertEquals(UnicodeTranslator.toUnicode("_123natabc"), "_123natabc");
	}

	@Test
	public void UnderscoreDigitNAT1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("_123NAT1abc"), "_123NAT1abc");
		assertEquals(UnicodeTranslator.toUnicode("_123nat1abc"), "_123nat1abc");
	}

	@Test
	public void UnderscoreDigitNATURALLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123NATURALabc"),
				"_123NATURALabc");
		assertEquals(UnicodeTranslator.toUnicode("_123naturalabc"),
				"_123naturalabc");
	}

	@Test
	public void UnderscoreDigitNOTLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123NOTabc"), "_123NOTabc");
		assertEquals(UnicodeTranslator.toUnicode("_123notabc"), "_123notabc");
	}

	@Test
	public void UnderscoreDigitORLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123ORabc"), "_123ORabc");
		assertEquals(UnicodeTranslator.toUnicode("_123orabc"), "_123orabc");
	}

	@Test
	public void UnderscoreDigitPOWLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123POWabc"), "_123POWabc");
		assertEquals(UnicodeTranslator.toUnicode("_123powabc"), "_123powabc");
	}

	@Test
	public void UnderscoreDigitPOW1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("_123POW1abc"), "_123POW1abc");
		assertEquals(UnicodeTranslator.toUnicode("_123pow1abc"), "_123pow1abc");
	}

	@Test
	public void UnderscoreDigitTRUELetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123TRUEabc"), "_123TRUEabc");
		assertEquals(UnicodeTranslator.toUnicode("_123trueabc"), "_123trueabc");
	}

	@Test
	public void UnderscoreDigitUNIONLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_123UNIONabc"),
				"_123UNIONabc");
		assertEquals(UnicodeTranslator.toUnicode("_123unionabc"),
				"_123unionabc");
	}

	@Test
	public void UnderscoreANYLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_ANYabc123"), "_ANYabc123");
		assertEquals(UnicodeTranslator.toUnicode("_anyabc123"), "_anyabc123");
	}

	@Test
	public void UnderscoreFALSELetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSEabc123"),
				"_FALSEabc123");
		assertEquals(UnicodeTranslator.toUnicode("_falseabc123"),
				"_falseabc123");
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
		assertEquals(UnicodeTranslator.toUnicode("_INTERabc123"),
				"_INTERabc123");
		assertEquals(UnicodeTranslator.toUnicode("_interabc123"),
				"_interabc123");
	}

	@Test
	public void UnderscoreNATLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NATabc123"), "_NATabc123");
		assertEquals(UnicodeTranslator.toUnicode("_natabc123"), "_natabc123");
	}

	@Test
	public void UnderscoreNAT1LetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1abc123"), "_NAT1abc123");
		assertEquals(UnicodeTranslator.toUnicode("_nat1abc123"), "_nat1abc123");
	}

	@Test
	public void UnderscoreNATURALLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURALabc123"),
				"_NATURALabc123");
		assertEquals(UnicodeTranslator.toUnicode("_naturalabc123"),
				"_naturalabc123");
	}

	@Test
	public void UnderscoreNOTLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_NOTabc123"), "_NOTabc123");
		assertEquals(UnicodeTranslator.toUnicode("_notabc123"), "_notabc123");
	}

	@Test
	public void UnderscoreORLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_ORabc123"), "_ORabc123");
		assertEquals(UnicodeTranslator.toUnicode("_orabc123"), "_orabc123");
	}

	@Test
	public void UnderscorePOWLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_POWabc123"), "_POWabc123");
		assertEquals(UnicodeTranslator.toUnicode("_powabc123"), "_powabc123");
	}

	@Test
	public void UnderscorePOW1LetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1abc123"), "_POW1abc123");
		assertEquals(UnicodeTranslator.toUnicode("_pow1abc123"), "_pow1abc123");
	}

	@Test
	public void UnderscoreTRUELetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUEabc123"), "_TRUEabc123");
		assertEquals(UnicodeTranslator.toUnicode("_trueabc123"), "_trueabc123");
	}

	@Test
	public void UnderscoreUNIONLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("_UNIONabc123"),
				"_UNIONabc123");
		assertEquals(UnicodeTranslator.toUnicode("_unionabc123"),
				"_unionabc123");
	}

	@Test
	public void UnderscoreANYDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_ANY123abc"), "_ANY123abc");
		assertEquals(UnicodeTranslator.toUnicode("_any123abc"), "_any123abc");
	}

	@Test
	public void UnderscoreFALSEDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSE123abc"),
				"_FALSE123abc");
		assertEquals(UnicodeTranslator.toUnicode("_false123abc"),
				"_false123abc");
	}

	@Test
	public void UnderscoreINTEGERDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_INTEGER123abc"),
				"_INTEGER123abc");
		assertEquals(UnicodeTranslator.toUnicode("_integer123abc"),
				"_integer123abc");
	}

	@Test
	public void UnderscoreINTERDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_INTER123abc"),
				"_INTER123abc");
		assertEquals(UnicodeTranslator.toUnicode("_inter123abc"),
				"_inter123abc");
	}

	@Test
	public void UnderscoreNATDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT123abc"), "_NAT123abc");
		assertEquals(UnicodeTranslator.toUnicode("_nat123abc"), "_nat123abc");
	}

	@Test
	public void UnderscoreNAT1DigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1123abc"), "_NAT1123abc");
		assertEquals(UnicodeTranslator.toUnicode("_nat1123abc"), "_nat1123abc");
	}

	@Test
	public void UnderscoreNATURALDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURAL123abc"),
				"_NATURAL123abc");
		assertEquals(UnicodeTranslator.toUnicode("_natural123abc"),
				"_natural123abc");
	}

	@Test
	public void UnderscoreNOTDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_NOT123abc"), "_NOT123abc");
		assertEquals(UnicodeTranslator.toUnicode("_not123abc"), "_not123abc");
	}

	@Test
	public void UnderscoreORDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_OR123abc"), "_OR123abc");
		assertEquals(UnicodeTranslator.toUnicode("_or123abc"), "_or123abc");
	}

	@Test
	public void UnderscorePOWDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_POW123abc"), "_POW123abc");
		assertEquals(UnicodeTranslator.toUnicode("_pow123abc"), "_pow123abc");
	}

	@Test
	public void UnderscorePOW1DigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1123abc"), "_POW1123abc");
		assertEquals(UnicodeTranslator.toUnicode("_pow1123abc"), "_pow1123abc");
	}

	@Test
	public void UnderscoreTRUEDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUE123abc"), "_TRUE123abc");
		assertEquals(UnicodeTranslator.toUnicode("_true123abc"), "_true123abc");
	}

	@Test
	public void UnderscoreUNIONDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("_UNION123abc"),
				"_UNION123abc");
		assertEquals(UnicodeTranslator.toUnicode("_union123abc"),
				"_union123abc");
	}

	@Test
	public void Keyword() {

		assertEquals(UnicodeTranslator.toUnicode("ANY"), "ANY");
	}

	@Test
	public void ANYLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ANYabc"), "ANYabc");
		assertEquals(UnicodeTranslator.toUnicode("anyabc"), "anyabc");
	}

	@Test
	public void FALSELetter() {
		assertEquals(UnicodeTranslator.toUnicode("FALSEabc"), "FALSEabc");
		assertEquals(UnicodeTranslator.toUnicode("falseabc"), "falseabc");
	}

	@Test
	public void INTEGERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGERabc"), "INTEGERabc");
		assertEquals(UnicodeTranslator.toUnicode("integerabc"), "integerabc");
	}

	@Test
	public void INTERLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTERabc"), "INTERabc");
		assertEquals(UnicodeTranslator.toUnicode("interabc"), "interabc");
	}

	@Test
	public void NATLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATabc"), "NATabc");
		assertEquals(UnicodeTranslator.toUnicode("natabc"), "natabc");
	}

	@Test
	public void NAT1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1abc"), "NAT1abc");
		assertEquals(UnicodeTranslator.toUnicode("nat1abc"), "nat1abc");
	}

	@Test
	public void NATURALLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATURALabc"), "NATURALabc");
		assertEquals(UnicodeTranslator.toUnicode("naturalabc"), "naturalabc");
	}

	@Test
	public void NOTLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NOTabc"), "NOTabc");
		assertEquals(UnicodeTranslator.toUnicode("notabc"), "notabc");
	}

	@Test
	public void ORLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ORabc"), "ORabc");
		assertEquals(UnicodeTranslator.toUnicode("orabc"), "orabc");
	}

	@Test
	public void POWLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POWabc"), "POWabc");
		assertEquals(UnicodeTranslator.toUnicode("powabc"), "powabc");
	}

	@Test
	public void POW1Letter() {
		assertEquals(UnicodeTranslator.toUnicode("POW1abc"), "POW1abc");
		assertEquals(UnicodeTranslator.toUnicode("pow1abc"), "pow1abc");
	}

	@Test
	public void TRUELetter() {
		assertEquals(UnicodeTranslator.toUnicode("TRUEabc"), "TRUEabc");
		assertEquals(UnicodeTranslator.toUnicode("trueabc"), "trueabc");
	}

	@Test
	public void UNIONLetter() {
		assertEquals(UnicodeTranslator.toUnicode("UNIONabc"), "UNIONabc");
		assertEquals(UnicodeTranslator.toUnicode("unionabc"), "unionabc");
	}

	@Test
	public void ANYDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ANY123"), "ANY123");
		assertEquals(UnicodeTranslator.toUnicode("any123"), "any123");
	}

	@Test
	public void FALSEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE123"), "FALSE123");
		assertEquals(UnicodeTranslator.toUnicode("false123"), "false123");
	}

	@Test
	public void INTEGERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123"), "INTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("integer123"), "integer123");
	}

	@Test
	public void INTERDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTER123"), "INTER123");
		assertEquals(UnicodeTranslator.toUnicode("inter123"), "inter123");
	}

	@Test
	public void NATDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT123"), "NAT123");
		assertEquals(UnicodeTranslator.toUnicode("nat123"), "nat123");
	}

	@Test
	public void NAT1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1123"), "NAT1123");
		assertEquals(UnicodeTranslator.toUnicode("nat1123"), "nat1123");
	}

	@Test
	public void NATURALDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123"), "NATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("natural123"), "natural123");
	}

	@Test
	public void NOTDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NOT123"), "NOT123");
		assertEquals(UnicodeTranslator.toUnicode("not123"), "not123");
	}

	@Test
	public void ORDigit() {
		assertEquals(UnicodeTranslator.toUnicode("OR123"), "OR123");
		assertEquals(UnicodeTranslator.toUnicode("or123"), "or123");
	}

	@Test
	public void POWDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW123"), "POW123");
		assertEquals(UnicodeTranslator.toUnicode("pow123"), "pow123");
	}

	@Test
	public void POW1Digit() {
		assertEquals(UnicodeTranslator.toUnicode("POW1123"), "POW1123");
		assertEquals(UnicodeTranslator.toUnicode("pow1123"), "pow1123");
	}

	@Test
	public void TRUEDigit() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE123"), "TRUE123");
		assertEquals(UnicodeTranslator.toUnicode("true123"), "true123");
	}

	@Test
	public void UNIONDigit() {
		assertEquals(UnicodeTranslator.toUnicode("UNION123"), "UNION123");
		assertEquals(UnicodeTranslator.toUnicode("union123"), "union123");
	}

	@Test
	public void ANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ANY_"), "ANY_");
		assertEquals(UnicodeTranslator.toUnicode("any_"), "any_");
	}

	@Test
	public void FALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE_"), "FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("false_"), "false_");
	}

	@Test
	public void INTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_"), "INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("integer_"), "integer_");
	}

	@Test
	public void INTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTER_"), "INTER_");
		assertEquals(UnicodeTranslator.toUnicode("inter_"), "inter_");
	}

	@Test
	public void NATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT_"), "NAT_");
		assertEquals(UnicodeTranslator.toUnicode("nat_"), "nat_");
	}

	@Test
	public void NAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1_"), "NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("nat1_"), "nat1_");
	}

	@Test
	public void NATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_"), "NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("natural_"), "natural_");
	}

	@Test
	public void NOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NOT_"), "NOT_");
		assertEquals(UnicodeTranslator.toUnicode("not_"), "not_");
	}

	@Test
	public void ORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("OR_"), "OR_");
		assertEquals(UnicodeTranslator.toUnicode("or_"), "or_");
	}

	@Test
	public void POWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW_"), "POW_");
		assertEquals(UnicodeTranslator.toUnicode("pow_"), "pow_");
	}

	@Test
	public void POW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW1_"), "POW1_");
		assertEquals(UnicodeTranslator.toUnicode("pow1_"), "pow1_");
	}

	@Test
	public void TRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE_"), "TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("true_"), "true_");
	}

	@Test
	public void UNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("UNION_"), "UNION_");
		assertEquals(UnicodeTranslator.toUnicode("union_"), "union_");
	}

	@Test
	public void ANYLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ANYabc123"), "ANYabc123");
		assertEquals(UnicodeTranslator.toUnicode("anyabc123"), "anyabc123");
	}

	@Test
	public void FALSELetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("FALSEabc123"), "FALSEabc123");
		assertEquals(UnicodeTranslator.toUnicode("falseabc123"), "falseabc123");
	}

	@Test
	public void INTEGERLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGERabc123"),
				"INTEGERabc123");
		assertEquals(UnicodeTranslator.toUnicode("integerabc123"),
				"integerabc123");
	}

	@Test
	public void INTERLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTERabc123"), "INTERabc123");
		assertEquals(UnicodeTranslator.toUnicode("interabc123"), "interabc123");
	}

	@Test
	public void NATLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATabc123"), "NATabc123");
		assertEquals(UnicodeTranslator.toUnicode("natabc123"), "natabc123");
	}

	@Test
	public void NAT1LetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1abc123"), "NAT1abc123");
		assertEquals(UnicodeTranslator.toUnicode("nat1abc123"), "nat1abc123");
	}

	@Test
	public void NATURALLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATURALabc123"),
				"NATURALabc123");
		assertEquals(UnicodeTranslator.toUnicode("naturalabc123"),
				"naturalabc123");
	}

	@Test
	public void NOTLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NOTabc123"), "NOTabc123");
		assertEquals(UnicodeTranslator.toUnicode("notabc123"), "notabc123");
	}

	@Test
	public void ORLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ORabc123"), "ORabc123");
		assertEquals(UnicodeTranslator.toUnicode("orabc123"), "orabc123");
	}

	@Test
	public void POWLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POWabc123"), "POWabc123");
		assertEquals(UnicodeTranslator.toUnicode("powabc123"), "powabc123");
	}

	@Test
	public void POW1LetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW1abc123"), "POW1abc123");
		assertEquals(UnicodeTranslator.toUnicode("pow1abc123"), "pow1abc123");
	}

	@Test
	public void TRUELetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("TRUEabc123"), "TRUEabc123");
		assertEquals(UnicodeTranslator.toUnicode("trueabc123"), "trueabc123");
	}

	@Test
	public void UNIONLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("UNIONabc123"), "UNIONabc123");
		assertEquals(UnicodeTranslator.toUnicode("unionabc123"), "unionabc123");
	}

	@Test
	public void ANYLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ANYabc_"), "ANYabc_");
		assertEquals(UnicodeTranslator.toUnicode("anyabc_"), "anyabc_");
	}

	@Test
	public void FALSELetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("FALSEabc_"), "FALSEabc_");
		assertEquals(UnicodeTranslator.toUnicode("falseabc_"), "falseabc_");
	}

	@Test
	public void INTEGERLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGERabc_"), "INTEGERabc_");
		assertEquals(UnicodeTranslator.toUnicode("integerabc_"), "integerabc_");
	}

	@Test
	public void INTERLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTERabc_"), "INTERabc_");
		assertEquals(UnicodeTranslator.toUnicode("interabc_"), "interabc_");
	}

	@Test
	public void NATLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATabc_"), "NATabc_");
		assertEquals(UnicodeTranslator.toUnicode("natabc_"), "natabc_");
	}

	@Test
	public void NAT1LetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1abc_"), "NAT1abc_");
		assertEquals(UnicodeTranslator.toUnicode("nat1abc_"), "nat1abc_");
	}

	@Test
	public void NATURALLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATURALabc_"), "NATURALabc_");
		assertEquals(UnicodeTranslator.toUnicode("naturalabc_"), "naturalabc_");
	}

	@Test
	public void NOTLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NOTabc_"), "NOTabc_");
		assertEquals(UnicodeTranslator.toUnicode("notabc_"), "notabc_");
	}

	@Test
	public void ORLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ORabc_"), "ORabc_");
		assertEquals(UnicodeTranslator.toUnicode("orabc_"), "orabc_");
	}

	@Test
	public void POWLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POWabc_"), "POWabc_");
		assertEquals(UnicodeTranslator.toUnicode("powabc_"), "powabc_");
	}

	@Test
	public void POW1LetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW1abc_"), "POW1abc_");
		assertEquals(UnicodeTranslator.toUnicode("pow1abc_"), "pow1abc_");
	}

	@Test
	public void TRUELetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("TRUEabc_"), "TRUEabc_");
		assertEquals(UnicodeTranslator.toUnicode("trueabc_"), "trueabc_");
	}

	@Test
	public void UNIONLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("UNIONabc_"), "UNIONabc_");
		assertEquals(UnicodeTranslator.toUnicode("unionabc_"), "unionabc_");
	}

	@Test
	public void ANYDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ANY123abc"), "ANY123abc");
		assertEquals(UnicodeTranslator.toUnicode("any123abc"), "any123abc");
	}

	@Test
	public void FALSEDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE123abc"), "FALSE123abc");
		assertEquals(UnicodeTranslator.toUnicode("false123abc"), "false123abc");
	}

	@Test
	public void INTEGERDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123abc"),
				"INTEGER123abc");
		assertEquals(UnicodeTranslator.toUnicode("integer123abc"),
				"integer123abc");
	}

	@Test
	public void INTERDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTER123abc"), "INTER123abc");
		assertEquals(UnicodeTranslator.toUnicode("inter123abc"), "inter123abc");
	}

	@Test
	public void NATDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT123abc"), "NAT123abc");
		assertEquals(UnicodeTranslator.toUnicode("nat123abc"), "nat123abc");
	}

	@Test
	public void NAT1DigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1123abc"), "NAT1123abc");
		assertEquals(UnicodeTranslator.toUnicode("nat1123abc"), "nat1123abc");
	}

	@Test
	public void NATURALDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123abc"),
				"NATURAL123abc");
		assertEquals(UnicodeTranslator.toUnicode("natural123abc"),
				"natural123abc");
	}

	@Test
	public void NOTDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("not123abc"), "not123abc");
		assertEquals(UnicodeTranslator.toUnicode("NOT123abc"), "NOT123abc");
	}

	@Test
	public void ORDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("or123abc"), "or123abc");
		assertEquals(UnicodeTranslator.toUnicode("OR123abc"), "OR123abc");
	}

	@Test
	public void POWDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW123abc"), "POW123abc");
		assertEquals(UnicodeTranslator.toUnicode("pow123abc"), "pow123abc");
	}

	@Test
	public void POW1DigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW1123abc"), "POW1123abc");
		assertEquals(UnicodeTranslator.toUnicode("pow1123abc"), "pow1123abc");
	}

	@Test
	public void TRUEDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE123abc"), "TRUE123abc");
		assertEquals(UnicodeTranslator.toUnicode("true123abc"), "true123abc");
	}

	@Test
	public void UNIONDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("UNION123abc"), "UNION123abc");
		assertEquals(UnicodeTranslator.toUnicode("union123abc"), "union123abc");
	}

	@Test
	public void ANYDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ANY123_"), "ANY123_");
		assertEquals(UnicodeTranslator.toUnicode("any123_"), "any123_");
	}

	@Test
	public void FALSEDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE123_"), "FALSE123_");
		assertEquals(UnicodeTranslator.toUnicode("false123_"), "false123_");
	}

	@Test
	public void INTEGERDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123_"), "INTEGER123_");
		assertEquals(UnicodeTranslator.toUnicode("integer123_"), "integer123_");
	}

	@Test
	public void INTERDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTER123_"), "INTER123_");
		assertEquals(UnicodeTranslator.toUnicode("inter123_"), "inter123_");
	}

	@Test
	public void NATDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT123_"), "NAT123_");
		assertEquals(UnicodeTranslator.toUnicode("nat123_"), "nat123_");
	}

	@Test
	public void NAT1DigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1123_"), "NAT1123_");
		assertEquals(UnicodeTranslator.toUnicode("nat1123_"), "nat1123_");
	}

	@Test
	public void NATURALDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123_"), "NATURAL123_");
		assertEquals(UnicodeTranslator.toUnicode("natural123_"), "natural123_");
	}

	@Test
	public void NOTDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NOT123_"), "NOT123_");
		assertEquals(UnicodeTranslator.toUnicode("not123_"), "not123_");
	}

	@Test
	public void ORDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("OR123_"), "OR123_");
		assertEquals(UnicodeTranslator.toUnicode("or123_"), "or123_");
	}

	@Test
	public void POWDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW123_"), "POW123_");
		assertEquals(UnicodeTranslator.toUnicode("pow123_"), "pow123_");
	}

	@Test
	public void POW1DigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW1123_"), "POW1123_");
		assertEquals(UnicodeTranslator.toUnicode("pow1123_"), "pow1123_");
	}

	@Test
	public void TRUEDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE123_"), "TRUE123_");
		assertEquals(UnicodeTranslator.toUnicode("true123_"), "true123_");
	}

	@Test
	public void UNIONDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("UNION123_"), "UNION123_");
		assertEquals(UnicodeTranslator.toUnicode("union123_"), "union123_");
	}

	@Test
	public void ANYUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ANY_abc"), "ANY_abc");
		assertEquals(UnicodeTranslator.toUnicode("any_abc"), "any_abc");
	}

	@Test
	public void FALSEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE_abc"), "FALSE_abc");
		assertEquals(UnicodeTranslator.toUnicode("false_abc"), "false_abc");
	}

	@Test
	public void INTEGERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_abc"), "INTEGER_abc");
		assertEquals(UnicodeTranslator.toUnicode("integer_abc"), "integer_abc");
	}

	@Test
	public void INTERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTER_abc"), "INTER_abc");
		assertEquals(UnicodeTranslator.toUnicode("inter_abc"), "inter_abc");
	}

	@Test
	public void NATUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT_abc"), "NAT_abc");
		assertEquals(UnicodeTranslator.toUnicode("nat_abc"), "nat_abc");
	}

	@Test
	public void NAT1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1_abc"), "NAT1_abc");
		assertEquals(UnicodeTranslator.toUnicode("nat1_abc"), "nat1_abc");
	}

	@Test
	public void NATURALUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_abc"), "NATURAL_abc");
		assertEquals(UnicodeTranslator.toUnicode("natural_abc"), "natural_abc");
	}

	@Test
	public void NOTUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NOT_abc"), "NOT_abc");
		assertEquals(UnicodeTranslator.toUnicode("not_abc"), "not_abc");
	}

	@Test
	public void ORUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("OR_abc"), "OR_abc");
		assertEquals(UnicodeTranslator.toUnicode("or_abc"), "or_abc");
	}

	@Test
	public void POWUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW_abc"), "POW_abc");
		assertEquals(UnicodeTranslator.toUnicode("pow_abc"), "pow_abc");
	}

	@Test
	public void POW1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW1_abc"), "POW1_abc");
		assertEquals(UnicodeTranslator.toUnicode("pow1_abc"), "pow1_abc");
	}

	@Test
	public void TRUEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE_abc"), "TRUE_abc");
		assertEquals(UnicodeTranslator.toUnicode("true_abc"), "true_abc");
	}

	@Test
	public void UNIONUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("UNION_abc"), "UNION_abc");
		assertEquals(UnicodeTranslator.toUnicode("union_abc"), "union_abc");
	}

	@Test
	public void ANYUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ANY_123"), "ANY_123");
		assertEquals(UnicodeTranslator.toUnicode("any_123"), "any_123");
	}

	@Test
	public void FALSEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE_123"), "FALSE_123");
		assertEquals(UnicodeTranslator.toUnicode("false_123"), "false_123");
	}

	@Test
	public void INTEGERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_123"), "INTEGER_123");
		assertEquals(UnicodeTranslator.toUnicode("integer_123"), "integer_123");
	}

	@Test
	public void INTERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTER_123"), "INTER_123");
		assertEquals(UnicodeTranslator.toUnicode("inter_123"), "inter_123");
	}

	@Test
	public void NATUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT_123"), "NAT_123");
		assertEquals(UnicodeTranslator.toUnicode("nat_123"), "nat_123");
	}

	@Test
	public void NAT1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1_123"), "NAT1_123");
		assertEquals(UnicodeTranslator.toUnicode("nat1_123"), "nat1_123");
	}

	@Test
	public void NATURALUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_123"), "NATURAL_123");
		assertEquals(UnicodeTranslator.toUnicode("natural_123"), "natural_123");
	}

	@Test
	public void NOTUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NOT_123"), "NOT_123");
		assertEquals(UnicodeTranslator.toUnicode("not_123"), "not_123");
	}

	@Test
	public void ORUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("OR_123"), "OR_123");
		assertEquals(UnicodeTranslator.toUnicode("or_123"), "or_123");
	}

	@Test
	public void POWUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW_123"), "POW_123");
		assertEquals(UnicodeTranslator.toUnicode("pow_123"), "pow_123");
	}

	@Test
	public void POW1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW1_123"), "POW1_123");
		assertEquals(UnicodeTranslator.toUnicode("pow1_123"), "pow1_123");
	}

	@Test
	public void TRUEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE_123"), "TRUE_123");
		assertEquals(UnicodeTranslator.toUnicode("true_123"), "true_123");
	}

	@Test
	public void UNIONUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("UNION_123"), "UNION_123");
		assertEquals(UnicodeTranslator.toUnicode("union_123"), "union_123");
	}

	@Test
	public void ANYLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ANYabc123_"), "ANYabc123_");
		assertEquals(UnicodeTranslator.toUnicode("anyabc123_"), "anyabc123_");
	}

	@Test
	public void FALSELetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("FALSEabc123_"),
				"FALSEabc123_");
		assertEquals(UnicodeTranslator.toUnicode("falseabc123_"),
				"falseabc123_");
	}

	@Test
	public void INTEGERLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGERabc123_"),
				"INTEGERabc123_");
		assertEquals(UnicodeTranslator.toUnicode("integerabc123_"),
				"integerabc123_");
	}

	@Test
	public void INTERLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTERabc123_"),
				"INTERabc123_");
		assertEquals(UnicodeTranslator.toUnicode("interabc123_"),
				"interabc123_");
	}

	@Test
	public void NATLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATabc123_"), "NATabc123_");
		assertEquals(UnicodeTranslator.toUnicode("natabc123_"), "natabc123_");
	}

	@Test
	public void NAT1LetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1abc123_"), "NAT1abc123_");
		assertEquals(UnicodeTranslator.toUnicode("nat1abc123_"), "nat1abc123_");
	}

	@Test
	public void NATURALLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATURALabc123_"),
				"NATURALabc123_");
		assertEquals(UnicodeTranslator.toUnicode("naturalabc123_"),
				"naturalabc123_");
	}

	@Test
	public void NOTLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NOTabc123_"), "NOTabc123_");
		assertEquals(UnicodeTranslator.toUnicode("notabc123_"), "notabc123_");
	}

	@Test
	public void ORLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ORabc123_"), "ORabc123_");
		assertEquals(UnicodeTranslator.toUnicode("orabc123_"), "orabc123_");
	}

	@Test
	public void POWLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POWabc123_"), "POWabc123_");
		assertEquals(UnicodeTranslator.toUnicode("powabc123_"), "powabc123_");
	}

	@Test
	public void POW1LetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW1abc123_"), "POW1abc123_");
		assertEquals(UnicodeTranslator.toUnicode("pow1abc123_"), "pow1abc123_");
	}

	@Test
	public void TRUELetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("TRUEabc123_"), "TRUEabc123_");
		assertEquals(UnicodeTranslator.toUnicode("trueabc123_"), "trueabc123_");
	}

	@Test
	public void UNIONLetterDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("UNIONabc123_"),
				"UNIONabc123_");
		assertEquals(UnicodeTranslator.toUnicode("unionabc123_"),
				"unionabc123_");
	}

	@Test
	public void ANYLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ANYabc_123"), "ANYabc_123");
		assertEquals(UnicodeTranslator.toUnicode("anyabc_123"), "anyabc_123");
	}

	@Test
	public void FALSELetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("FALSEabc_123"),
				"FALSEabc_123");
		assertEquals(UnicodeTranslator.toUnicode("falseabc_123"),
				"falseabc_123");
	}

	@Test
	public void INTEGERLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGERabc_123"),
				"INTEGERabc_123");
		assertEquals(UnicodeTranslator.toUnicode("integerabc_123"),
				"integerabc_123");
	}

	@Test
	public void INTERLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTERabc_123"),
				"INTERabc_123");
		assertEquals(UnicodeTranslator.toUnicode("interabc_123"),
				"interabc_123");
	}

	@Test
	public void NATLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATabc_123"), "NATabc_123");
		assertEquals(UnicodeTranslator.toUnicode("natabc_123"), "natabc_123");
	}

	@Test
	public void NAT1LetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1abc_123"), "NAT1abc_123");
		assertEquals(UnicodeTranslator.toUnicode("nat1abc_123"), "nat1abc_123");
	}

	@Test
	public void NATURALLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATURALabc_123"),
				"NATURALabc_123");
		assertEquals(UnicodeTranslator.toUnicode("naturalabc_123"),
				"naturalabc_123");
	}

	@Test
	public void NOTLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NOTabc_123"), "NOTabc_123");
		assertEquals(UnicodeTranslator.toUnicode("notabc_123"), "notabc_123");
	}

	@Test
	public void ORLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ORabc_123"), "ORabc_123");
		assertEquals(UnicodeTranslator.toUnicode("orabc_123"), "orabc_123");
	}

	@Test
	public void POWLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POWabc_123"), "POWabc_123");
		assertEquals(UnicodeTranslator.toUnicode("powabc_123"), "powabc_123");
	}

	@Test
	public void POW1LetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW1abc_123"), "POW1abc_123");
		assertEquals(UnicodeTranslator.toUnicode("pow1abc_123"), "pow1abc_123");
	}

	@Test
	public void TRUELetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("TRUEabc_123"), "TRUEabc_123");
		assertEquals(UnicodeTranslator.toUnicode("trueabc_123"), "trueabc_123");
	}

	@Test
	public void UNIONLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("UNIONabc_123"),
				"UNIONabc_123");
		assertEquals(UnicodeTranslator.toUnicode("unionabc_123"),
				"unionabc_123");
	}

	@Test
	public void ANYDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("ANY123abc_"), "ANY123abc_");
		assertEquals(UnicodeTranslator.toUnicode("any123abc_"), "any123abc_");
	}

	@Test
	public void FALSEDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE123abc_"),
				"FALSE123abc_");
		assertEquals(UnicodeTranslator.toUnicode("false123abc_"),
				"false123abc_");
	}

	@Test
	public void INTEGERDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123abc_"),
				"INTEGER123abc_");
		assertEquals(UnicodeTranslator.toUnicode("integer123abc_"),
				"integer123abc_");
	}

	@Test
	public void INTERDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("INTER123abc_"),
				"INTER123abc_");
		assertEquals(UnicodeTranslator.toUnicode("inter123abc_"),
				"inter123abc_");
	}

	@Test
	public void NATDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT123abc_"), "NAT123abc_");
		assertEquals(UnicodeTranslator.toUnicode("nat123abc_"), "nat123abc_");
	}

	@Test
	public void NAT1DigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1123abc_"), "NAT1123abc_");
		assertEquals(UnicodeTranslator.toUnicode("nat1123abc_"), "nat1123abc_");
	}

	@Test
	public void NATURALDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123abc_"),
				"NATURAL123abc_");
		assertEquals(UnicodeTranslator.toUnicode("natural123abc_"),
				"natural123abc_");
	}

	@Test
	public void NOTDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("NOT123abc_"), "NOT123abc_");
		assertEquals(UnicodeTranslator.toUnicode("not123abc_"), "not123abc_");
	}

	@Test
	public void ORDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("OR123abc_"), "OR123abc_");
		assertEquals(UnicodeTranslator.toUnicode("or123abc_"), "or123abc_");
	}

	@Test
	public void POWDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW123abc_"), "POW123abc_");
		assertEquals(UnicodeTranslator.toUnicode("pow123abc_"), "pow123abc_");
	}

	@Test
	public void POW1DigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("POW1123abc_"), "POW1123abc_");
		assertEquals(UnicodeTranslator.toUnicode("pow1123abc_"), "pow1123abc_");
	}

	@Test
	public void TRUEDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE123abc_"), "TRUE123abc_");
		assertEquals(UnicodeTranslator.toUnicode("true123abc_"), "true123abc_");
	}

	@Test
	public void UNIONDigitLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("UNION123abc_"),
				"UNION123abc_");
		assertEquals(UnicodeTranslator.toUnicode("union123abc_"),
				"union123abc_");
	}

	@Test
	public void ANYDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ANY123_abc"), "ANY123_abc");
		assertEquals(UnicodeTranslator.toUnicode("any123_abc"), "any123_abc");
	}

	@Test
	public void FALSEDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE123_abc"),
				"FALSE123_abc");
		assertEquals(UnicodeTranslator.toUnicode("false123_abc"),
				"false123_abc");
	}

	@Test
	public void INTEGERDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123_abc"),
				"INTEGER123_abc");
		assertEquals(UnicodeTranslator.toUnicode("integer123_abc"),
				"integer123_abc");
	}

	@Test
	public void INTERDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTER123_abc"),
				"INTER123_abc");
		assertEquals(UnicodeTranslator.toUnicode("inter123_abc"),
				"inter123_abc");
	}

	@Test
	public void NATDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT123_abc"), "NAT123_abc");
		assertEquals(UnicodeTranslator.toUnicode("nat123_abc"), "nat123_abc");
	}

	@Test
	public void NAT1DigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1123_abc"), "NAT1123_abc");
		assertEquals(UnicodeTranslator.toUnicode("nat1123_abc"), "nat1123_abc");
	}

	@Test
	public void NATURALDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123_abc"),
				"NATURAL123_abc");
		assertEquals(UnicodeTranslator.toUnicode("natural123_abc"),
				"natural123_abc");
	}

	@Test
	public void NOTDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NOT123_abc"), "NOT123_abc");
		assertEquals(UnicodeTranslator.toUnicode("not123_abc"), "not123_abc");
	}

	@Test
	public void ORDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("OR123_abc"), "OR123_abc");
		assertEquals(UnicodeTranslator.toUnicode("or123_abc"), "or123_abc");
	}

	@Test
	public void POWDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW123_abc"), "POW123_abc");
		assertEquals(UnicodeTranslator.toUnicode("pow123_abc"), "pow123_abc");
	}

	@Test
	public void POW1DigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW1123_abc"), "POW1123_abc");
		assertEquals(UnicodeTranslator.toUnicode("pow1123_abc"), "pow1123_abc");
	}

	@Test
	public void TRUEDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE123_abc"), "TRUE123_abc");
		assertEquals(UnicodeTranslator.toUnicode("true123_abc"), "true123_abc");
	}

	@Test
	public void UNIONDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("UNION123_abc"),
				"UNION123_abc");
		assertEquals(UnicodeTranslator.toUnicode("union123_abc"),
				"union123_abc");
	}

	@Test
	public void ANYUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("ANY_abc123"), "ANY_abc123");
		assertEquals(UnicodeTranslator.toUnicode("any_abc123"), "any_abc123");
	}

	@Test
	public void FALSEUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE_abc123"),
				"FALSE_abc123");
		assertEquals(UnicodeTranslator.toUnicode("false_abc123"),
				"false_abc123");
	}

	@Test
	public void INTEGERUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_abc123"),
				"INTEGER_abc123");
		assertEquals(UnicodeTranslator.toUnicode("integer_abc123"),
				"integer_abc123");
	}

	@Test
	public void INTERUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("INTER_abc123"),
				"INTER_abc123");
		assertEquals(UnicodeTranslator.toUnicode("inter_abc123"),
				"inter_abc123");
	}

	@Test
	public void NATUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT_abc123"), "NAT_abc123");
		assertEquals(UnicodeTranslator.toUnicode("nat_abc123"), "nat_abc123");
	}

	@Test
	public void NAT1UnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1_abc123"), "NAT1_abc123");
		assertEquals(UnicodeTranslator.toUnicode("nat1_abc123"), "nat1_abc123");
	}

	@Test
	public void NATURALUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_abc123"),
				"NATURAL_abc123");
		assertEquals(UnicodeTranslator.toUnicode("natural_abc123"),
				"natural_abc123");
	}

	@Test
	public void NOTUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("NOT_abc123"), "NOT_abc123");
		assertEquals(UnicodeTranslator.toUnicode("not_abc123"), "not_abc123");
	}

	@Test
	public void ORUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("OR_abc123"), "OR_abc123");
		assertEquals(UnicodeTranslator.toUnicode("or_abc123"), "or_abc123");
	}

	@Test
	public void POWUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW_abc123"), "POW_abc123");
		assertEquals(UnicodeTranslator.toUnicode("pow_abc123"), "pow_abc123");
	}

	@Test
	public void POW1UnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("POW1_abc123"), "POW1_abc123");
		assertEquals(UnicodeTranslator.toUnicode("pow1_abc123"), "pow1_abc123");
	}

	@Test
	public void TRUEUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE_abc123"), "TRUE_abc123");
		assertEquals(UnicodeTranslator.toUnicode("true_abc123"), "true_abc123");
	}

	@Test
	public void UNIONUnderscoreLetterDigit() {
		assertEquals(UnicodeTranslator.toUnicode("UNION_abc123"),
				"UNION_abc123");
		assertEquals(UnicodeTranslator.toUnicode("union_abc123"),
				"union_abc123");
	}

	@Test
	public void ANYUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("ANY_123abc"), "ANY_123abc");
		assertEquals(UnicodeTranslator.toUnicode("any_123abc"), "any_123abc");
	}

	@Test
	public void FALSEUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("FALSE_123abc"),
				"FALSE_123abc");
		assertEquals(UnicodeTranslator.toUnicode("false_123abc"),
				"false_123abc");
	}

	@Test
	public void INTEGERUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_123abc"),
				"INTEGER_123abc");
		assertEquals(UnicodeTranslator.toUnicode("integer_123abc"),
				"integer_123abc");
	}

	@Test
	public void INTERUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("INTER_123abc"),
				"INTER_123abc");
		assertEquals(UnicodeTranslator.toUnicode("inter_123abc"),
				"inter_123abc");
	}

	@Test
	public void NATUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT_123abc"), "NAT_123abc");
		assertEquals(UnicodeTranslator.toUnicode("nat_123abc"), "nat_123abc");
	}

	@Test
	public void NAT1UnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NAT1_123abc"), "NAT1_123abc");
		assertEquals(UnicodeTranslator.toUnicode("nat1_123abc"), "nat1_123abc");
	}

	@Test
	public void NATURALUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_123abc"),
				"NATURAL_123abc");
		assertEquals(UnicodeTranslator.toUnicode("natural_123abc"),
				"natural_123abc");
	}

	@Test
	public void NOTUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("NOT_123abc"), "NOT_123abc");
		assertEquals(UnicodeTranslator.toUnicode("not_123abc"), "not_123abc");
	}

	@Test
	public void ORUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("OR_123abc"), "OR_123abc");
		assertEquals(UnicodeTranslator.toUnicode("or_123abc"), "or_123abc");
	}

	@Test
	public void POWUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW_123abc"), "POW_123abc");
		assertEquals(UnicodeTranslator.toUnicode("pow_123abc"), "pow_123abc");
	}

	@Test
	public void POW1UnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("POW1_123abc"), "POW1_123abc");
		assertEquals(UnicodeTranslator.toUnicode("pow1_123abc"), "pow1_123abc");
	}

	@Test
	public void TRUEUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("TRUE_123abc"), "TRUE_123abc");
		assertEquals(UnicodeTranslator.toUnicode("true_123abc"), "true_123abc");
	}

	@Test
	public void UNIONUnderscoreDigitLetter() {
		assertEquals(UnicodeTranslator.toUnicode("UNION_123abc"),
				"UNION_123abc");
		assertEquals(UnicodeTranslator.toUnicode("union_123abc"),
				"union_123abc");
	}

	@Test
	public void UnderscoreDigitUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_123_"), "_123_");
	}

	@Test
	public void UnderscoreLetterUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_abc_"), "_abc_");
	}

	@Test
	public void UnderscoreANYUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_ANY_"), "_ANY_");
		assertEquals(UnicodeTranslator.toUnicode("_any_"), "_any_");
	}

	@Test
	public void UnderscoreFALSEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_FALSE_"), "_FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("_false_"), "_false_");
	}

	@Test
	public void UnderscoreINTEGERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_INTEGER_"), "_INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("_integer_"), "_integer_");
	}

	@Test
	public void UnderscoreINTERUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_INTER_"), "_INTER_");
		assertEquals(UnicodeTranslator.toUnicode("_inter_"), "_inter_");
	}

	@Test
	public void UnderscoreNATUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT_"), "_NAT_");
		assertEquals(UnicodeTranslator.toUnicode("_nat_"), "_nat_");
	}

	@Test
	public void UnderscoreNAT1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("_NAT1_"), "_NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("_nat1_"), "_nat1_");
	}

	@Test
	public void UnderscoreNATURALUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_NATURAL_"), "_NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("_natural_"), "_natural_");
	}

	@Test
	public void UnderscoreNOTUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_NOT_"), "_NOT_");
		assertEquals(UnicodeTranslator.toUnicode("_not_"), "_not_");
	}

	@Test
	public void UnderscoreORUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_OR_"), "_OR_");
		assertEquals(UnicodeTranslator.toUnicode("_or_"), "_or_");
	}

	@Test
	public void UnderscorePOWUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_POW_"), "_POW_");
		assertEquals(UnicodeTranslator.toUnicode("_pow_"), "_pow_");
	}

	@Test
	public void UnderscorePOW1Underscore() {
		assertEquals(UnicodeTranslator.toUnicode("_POW1_"), "_POW1_");
		assertEquals(UnicodeTranslator.toUnicode("_pow1_"), "_pow1_");
	}

	@Test
	public void UnderscoreTRUEUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_TRUE_"), "_TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("_true_"), "_true_");
	}

	@Test
	public void UnderscoreUNIONUnderscore() {
		assertEquals(UnicodeTranslator.toUnicode("_UNION_"), "_UNION_");
		assertEquals(UnicodeTranslator.toUnicode("_union_"), "_union_");
	}

	@Test
	public void LetterUnderscoreDigitUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_123_abc"), "abc_123_abc");
	}

	@Test
	public void LetterUnderscoreLetterUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_abc_abc"), "abc_abc_abc");
	}

	@Test
	public void LetterUnderscoreANYUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_ANY_abc"), "abc_ANY_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_any_abc"), "abc_any_abc");
	}

	@Test
	public void LetterUnderscoreFALSEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_FALSE_abc"),
				"abc_FALSE_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_false_abc"),
				"abc_false_abc");
	}

	@Test
	public void LetterUnderscoreINTEGERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTEGER_abc"),
				"abc_INTEGER_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_integer_abc"),
				"abc_integer_abc");
	}

	@Test
	public void LetterUnderscoreINTERUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_INTER_abc"),
				"abc_INTER_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_inter_abc"),
				"abc_inter_abc");
	}

	@Test
	public void LetterUnderscoreNATUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT_abc"), "abc_NAT_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat_abc"), "abc_nat_abc");
	}

	@Test
	public void LetterUnderscoreNAT1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NAT1_abc"),
				"abc_NAT1_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_nat1_abc"),
				"abc_nat1_abc");
	}

	@Test
	public void LetterUnderscoreNATURALUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NATURAL_abc"),
				"abc_NATURAL_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_natural_abc"),
				"abc_natural_abc");
	}

	@Test
	public void LetterUnderscoreNOTUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_NOT_abc"), "abc_NOT_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_not_abc"), "abc_not_abc");
	}

	@Test
	public void LetterUnderscoreORUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_OR_abc"), "abc_OR_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_or_abc"), "abc_or_abc");
	}

	@Test
	public void LetterUnderscorePOWUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW_abc"), "abc_POW_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow_abc"), "abc_pow_abc");
	}

	@Test
	public void LetterUnderscorePOW1UnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_POW1_abc"),
				"abc_POW1_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_pow1_abc"),
				"abc_pow1_abc");
	}

	@Test
	public void LetterUnderscoreTRUEUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_TRUE_abc"),
				"abc_TRUE_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_true_abc"),
				"abc_true_abc");
	}

	@Test
	public void LetterUnderscoreUNIONUnderscoreLetter() {
		assertEquals(UnicodeTranslator.toUnicode("abc_UNION_abc"),
				"abc_UNION_abc");
		assertEquals(UnicodeTranslator.toUnicode("abc_union_abc"),
				"abc_union_abc");
	}

	@Test
	public void DigitUnderscoreDigitUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_123_123"), "123_123_123");
	}

	@Test
	public void DigitUnderscoreLetterUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_abc_123"), "123_abc_123");
	}

	@Test
	public void DigitUnderscoreANYUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_ANY_123"), "123_ANY_123");
		assertEquals(UnicodeTranslator.toUnicode("123_any_123"), "123_any_123");
	}

	@Test
	public void DigitUnderscoreFALSEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_FALSE_123"),
				"123_FALSE_123");
		assertEquals(UnicodeTranslator.toUnicode("123_false_123"),
				"123_false_123");
	}

	@Test
	public void DigitUnderscoreINTEGERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTEGER_123"),
				"123_INTEGER_123");
		assertEquals(UnicodeTranslator.toUnicode("123_integer_123"),
				"123_integer_123");
	}

	@Test
	public void DigitUnderscoreINTERUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_INTER_123"),
				"123_INTER_123");
		assertEquals(UnicodeTranslator.toUnicode("123_inter_123"),
				"123_inter_123");
	}

	@Test
	public void DigitUnderscoreNATUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_NAT_123"), "123_NAT_123");
		assertEquals(UnicodeTranslator.toUnicode("123_nat_123"), "123_nat_123");
	}

	@Test
	public void DigitUnderscoreNAT1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_NAT1_123"),
				"123_NAT1_123");
		assertEquals(UnicodeTranslator.toUnicode("123_nat1_123"),
				"123_nat1_123");
	}

	@Test
	public void DigitUnderscoreNATURALUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_NATURAL_123"),
				"123_NATURAL_123");
		assertEquals(UnicodeTranslator.toUnicode("123_natural_123"),
				"123_natural_123");
	}

	@Test
	public void DigitUnderscoreNOTUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_NOT_123"), "123_NOT_123");
		assertEquals(UnicodeTranslator.toUnicode("123_not_123"), "123_not_123");
	}

	@Test
	public void DigitUnderscoreORUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_OR_123"), "123_OR_123");
		assertEquals(UnicodeTranslator.toUnicode("123_or_123"), "123_or_123");
	}

	@Test
	public void DigitUnderscorePOWUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_POW_123"), "123_POW_123");
		assertEquals(UnicodeTranslator.toUnicode("123_pow_123"), "123_pow_123");
	}

	@Test
	public void DigitUnderscorePOW1UnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_POW1_123"),
				"123_POW1_123");
		assertEquals(UnicodeTranslator.toUnicode("123_pow1_123"),
				"123_pow1_123");
	}

	@Test
	public void DigitUnderscoreTRUEUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_TRUE_123"),
				"123_TRUE_123");
		assertEquals(UnicodeTranslator.toUnicode("123_true_123"),
				"123_true_123");
	}

	@Test
	public void DigitUnderscoreUNIONUnderscoreDigit() {
		assertEquals(UnicodeTranslator.toUnicode("123_UNION_123"),
				"123_UNION_123");
		assertEquals(UnicodeTranslator.toUnicode("123_union_123"),
				"123_union_123");
	}

	/*--------------------------------------------------------------*/

	@Test
	public void Var_123() {
		assertEquals(UnicodeTranslator.toUnicode("var_123"), "var_123");
		assertEquals(UnicodeTranslator.toUnicode("123_var"), "123_var");
		assertEquals(UnicodeTranslator.toUnicode("var_123_var"), "var_123_var");

		assertEquals(UnicodeTranslator.toUnicode("var_"), "var_");
		assertEquals(UnicodeTranslator.toUnicode("_var"), "_var");
		assertEquals(UnicodeTranslator.toUnicode("_var_"), "_var_");

		assertEquals(UnicodeTranslator.toUnicode("123_"), "123_");
		assertEquals(UnicodeTranslator.toUnicode("_123"), "_123");
		assertEquals(UnicodeTranslator.toUnicode("_123_"), "_123_");
	}

	@Test
	public void Var123() {
		assertEquals(UnicodeTranslator.toUnicode("var123"), "var123");
		assertEquals(UnicodeTranslator.toUnicode("123var"), "123var");
		assertEquals(UnicodeTranslator.toUnicode("var123var"), "var123var");
		assertEquals(UnicodeTranslator.toUnicode("123var123"), "123var123");
	}

	@Test
	public void VarANY() {
		assertEquals(UnicodeTranslator.toUnicode("varANY"), "varANY");
		assertEquals(UnicodeTranslator.toUnicode("varany"), "varany");
		assertEquals(UnicodeTranslator.toUnicode("varANYvar"), "varANYvar");
		assertEquals(UnicodeTranslator.toUnicode("varanyvar"), "varanyvar");
		assertEquals(UnicodeTranslator.toUnicode("ANYvar"), "ANYvar");
		assertEquals(UnicodeTranslator.toUnicode("anyvar"), "anyvar");

		assertEquals(UnicodeTranslator.toUnicode("123ANY"), "123ANY");
		assertEquals(UnicodeTranslator.toUnicode("123any"), "123any");
		assertEquals(UnicodeTranslator.toUnicode("123ANY123"), "123ANY123");
		assertEquals(UnicodeTranslator.toUnicode("123any123"), "123any123");
		assertEquals(UnicodeTranslator.toUnicode("ANY123"), "ANY123");
		assertEquals(UnicodeTranslator.toUnicode("any123"), "any123");

		assertEquals(UnicodeTranslator.toUnicode("_ANY"), "_ANY");
		assertEquals(UnicodeTranslator.toUnicode("_any"), "_any");
		assertEquals(UnicodeTranslator.toUnicode("_ANY_"), "_ANY_");
		assertEquals(UnicodeTranslator.toUnicode("_any_"), "_any_");
		assertEquals(UnicodeTranslator.toUnicode("ANY_"), "ANY_");
		assertEquals(UnicodeTranslator.toUnicode("any_"), "any_");
	}

	@Test
	public void VarFALSE() {
		assertEquals(UnicodeTranslator.toUnicode("varFALSE"), "varFALSE");
		assertEquals(UnicodeTranslator.toUnicode("varfalse"), "varfalse");
		assertEquals(UnicodeTranslator.toUnicode("varFALSEvar"), "varFALSEvar");
		assertEquals(UnicodeTranslator.toUnicode("varfalsevar"), "varfalsevar");
		assertEquals(UnicodeTranslator.toUnicode("FALSEvar"), "FALSEvar");
		assertEquals(UnicodeTranslator.toUnicode("falsevar"), "falsevar");

		assertEquals(UnicodeTranslator.toUnicode("123FALSE"), "123FALSE");
		assertEquals(UnicodeTranslator.toUnicode("123false"), "123false");
		assertEquals(UnicodeTranslator.toUnicode("123FALSE123"), "123FALSE123");
		assertEquals(UnicodeTranslator.toUnicode("123false123"), "123false123");
		assertEquals(UnicodeTranslator.toUnicode("FALSE123"), "FALSE123");
		assertEquals(UnicodeTranslator.toUnicode("false123"), "false123");

		assertEquals(UnicodeTranslator.toUnicode("_FALSE"), "_FALSE");
		assertEquals(UnicodeTranslator.toUnicode("_false"), "_false");
		assertEquals(UnicodeTranslator.toUnicode("_FALSE_"), "_FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("_false_"), "_false_");
		assertEquals(UnicodeTranslator.toUnicode("FALSE_"), "FALSE_");
		assertEquals(UnicodeTranslator.toUnicode("false_"), "false_");
	}

	@Test
	public void VarINTEGER() {
		assertEquals(UnicodeTranslator.toUnicode("varINTEGER"), "varINTEGER");
		assertEquals(UnicodeTranslator.toUnicode("varinteger"), "varinteger");
		assertEquals(UnicodeTranslator.toUnicode("varINTEGERvar"),
				"varINTEGERvar");
		assertEquals(UnicodeTranslator.toUnicode("varintegervar"),
				"varintegervar");
		assertEquals(UnicodeTranslator.toUnicode("INTEGERvar"), "INTEGERvar");
		assertEquals(UnicodeTranslator.toUnicode("integervar"), "integervar");

		assertEquals(UnicodeTranslator.toUnicode("123INTEGER"), "123INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("123integer"), "123integer");
		assertEquals(UnicodeTranslator.toUnicode("123INTEGER123"),
				"123INTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("123integer123"),
				"123integer123");
		assertEquals(UnicodeTranslator.toUnicode("INTEGER123"), "INTEGER123");
		assertEquals(UnicodeTranslator.toUnicode("integer123"), "integer123");

		assertEquals(UnicodeTranslator.toUnicode("_INTEGER"), "_INTEGER");
		assertEquals(UnicodeTranslator.toUnicode("_integer"), "_integer");
		assertEquals(UnicodeTranslator.toUnicode("_INTEGER_"), "_INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("_integer_"), "_integer_");
		assertEquals(UnicodeTranslator.toUnicode("INTEGER_"), "INTEGER_");
		assertEquals(UnicodeTranslator.toUnicode("integer_"), "integer_");
	}

	@Test
	public void VarINTER() {
		assertEquals(UnicodeTranslator.toUnicode("varINTER"), "varINTER");
		assertEquals(UnicodeTranslator.toUnicode("varinter"), "varinter");
		assertEquals(UnicodeTranslator.toUnicode("varINTERvar"), "varINTERvar");
		assertEquals(UnicodeTranslator.toUnicode("varintervar"), "varintervar");
		assertEquals(UnicodeTranslator.toUnicode("INTERvar"), "INTERvar");
		assertEquals(UnicodeTranslator.toUnicode("intervar"), "intervar");

		assertEquals(UnicodeTranslator.toUnicode("123INTER"), "123INTER");
		assertEquals(UnicodeTranslator.toUnicode("123inter"), "123inter");
		assertEquals(UnicodeTranslator.toUnicode("123INTER123"), "123INTER123");
		assertEquals(UnicodeTranslator.toUnicode("123inter123"), "123inter123");
		assertEquals(UnicodeTranslator.toUnicode("INTER123"), "INTER123");
		assertEquals(UnicodeTranslator.toUnicode("inter123"), "inter123");

		assertEquals(UnicodeTranslator.toUnicode("_INTER"), "_INTER");
		assertEquals(UnicodeTranslator.toUnicode("_inter"), "_inter");
		assertEquals(UnicodeTranslator.toUnicode("_INTER_"), "_INTER_");
		assertEquals(UnicodeTranslator.toUnicode("_inter_"), "_inter_");
		assertEquals(UnicodeTranslator.toUnicode("INTER_"), "INTER_");
		assertEquals(UnicodeTranslator.toUnicode("inter_"), "inter_");
	}

	@Test
	public void VarNAT() {
		assertEquals(UnicodeTranslator.toUnicode("varNAT"), "varNAT");
		assertEquals(UnicodeTranslator.toUnicode("varnat"), "varnat");
		assertEquals(UnicodeTranslator.toUnicode("varNATvar"), "varNATvar");
		assertEquals(UnicodeTranslator.toUnicode("varnatvar"), "varnatvar");
		assertEquals(UnicodeTranslator.toUnicode("NATvar"), "NATvar");
		assertEquals(UnicodeTranslator.toUnicode("natvar"), "natvar");

		assertEquals(UnicodeTranslator.toUnicode("123NAT"), "123NAT");
		assertEquals(UnicodeTranslator.toUnicode("123nat"), "123nat");
		assertEquals(UnicodeTranslator.toUnicode("123NAT123"), "123NAT123");
		assertEquals(UnicodeTranslator.toUnicode("123nat123"), "123nat123");
		assertEquals(UnicodeTranslator.toUnicode("NAT123"), "NAT123");
		assertEquals(UnicodeTranslator.toUnicode("nat123"), "nat123");

		assertEquals(UnicodeTranslator.toUnicode("_NAT"), "_NAT");
		assertEquals(UnicodeTranslator.toUnicode("_nat"), "_nat");
		assertEquals(UnicodeTranslator.toUnicode("_NAT_"), "_NAT_");
		assertEquals(UnicodeTranslator.toUnicode("_nat_"), "_nat_");
		assertEquals(UnicodeTranslator.toUnicode("NAT_"), "NAT_");
		assertEquals(UnicodeTranslator.toUnicode("nat_"), "nat_");
	}

	@Test
	public void VarNAT1() {
		assertEquals(UnicodeTranslator.toUnicode("varNAT1"), "varNAT1");
		assertEquals(UnicodeTranslator.toUnicode("varnat1"), "varnat1");
		assertEquals(UnicodeTranslator.toUnicode("varNAT1var"), "varNAT1var");
		assertEquals(UnicodeTranslator.toUnicode("varnat1var"), "varnat1var");
		assertEquals(UnicodeTranslator.toUnicode("NAT1var"), "NAT1var");
		assertEquals(UnicodeTranslator.toUnicode("nat1var"), "nat1var");

		assertEquals(UnicodeTranslator.toUnicode("123NAT1"), "123NAT1");
		assertEquals(UnicodeTranslator.toUnicode("123nat1"), "123nat1");
		assertEquals(UnicodeTranslator.toUnicode("123NAT1123"), "123NAT1123");
		assertEquals(UnicodeTranslator.toUnicode("123nat1123"), "123nat1123");
		assertEquals(UnicodeTranslator.toUnicode("NAT1123"), "NAT1123");
		assertEquals(UnicodeTranslator.toUnicode("nat1123"), "nat1123");

		assertEquals(UnicodeTranslator.toUnicode("_NAT1"), "_NAT1");
		assertEquals(UnicodeTranslator.toUnicode("_nat1"), "_nat1");
		assertEquals(UnicodeTranslator.toUnicode("_NAT1_"), "_NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("_nat1_"), "_nat1_");
		assertEquals(UnicodeTranslator.toUnicode("NAT1_"), "NAT1_");
		assertEquals(UnicodeTranslator.toUnicode("nat1_"), "nat1_");
	}

	@Test
	public void VarNATURAL() {
		assertEquals(UnicodeTranslator.toUnicode("varNATURAL"), "varNATURAL");
		assertEquals(UnicodeTranslator.toUnicode("varnatural"), "varnatural");
		assertEquals(UnicodeTranslator.toUnicode("varNATURALvar"),
				"varNATURALvar");
		assertEquals(UnicodeTranslator.toUnicode("varnaturalvar"),
				"varnaturalvar");
		assertEquals(UnicodeTranslator.toUnicode("NATURALvar"), "NATURALvar");
		assertEquals(UnicodeTranslator.toUnicode("naturalvar"), "naturalvar");

		assertEquals(UnicodeTranslator.toUnicode("123NATURAL"), "123NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("123natural"), "123natural");
		assertEquals(UnicodeTranslator.toUnicode("123NATURAL123"),
				"123NATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("123natural123"),
				"123natural123");
		assertEquals(UnicodeTranslator.toUnicode("NATURAL123"), "NATURAL123");
		assertEquals(UnicodeTranslator.toUnicode("natural123"), "natural123");

		assertEquals(UnicodeTranslator.toUnicode("_NATURAL"), "_NATURAL");
		assertEquals(UnicodeTranslator.toUnicode("_natural"), "_natural");
		assertEquals(UnicodeTranslator.toUnicode("_NATURAL_"), "_NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("_natural_"), "_natural_");
		assertEquals(UnicodeTranslator.toUnicode("NATURAL_"), "NATURAL_");
		assertEquals(UnicodeTranslator.toUnicode("natural_"), "natural_");
	}

	@Test
	public void VarNOT() {
		assertEquals(UnicodeTranslator.toUnicode("varNOT"), "varNOT");
		assertEquals(UnicodeTranslator.toUnicode("varnot"), "varnot");
		assertEquals(UnicodeTranslator.toUnicode("varNOTvar"), "varNOTvar");
		assertEquals(UnicodeTranslator.toUnicode("varnotvar"), "varnotvar");
		assertEquals(UnicodeTranslator.toUnicode("NOTvar"), "NOTvar");
		assertEquals(UnicodeTranslator.toUnicode("notvar"), "notvar");

		assertEquals(UnicodeTranslator.toUnicode("123NOT"), "123NOT");
		assertEquals(UnicodeTranslator.toUnicode("123not"), "123not");
		assertEquals(UnicodeTranslator.toUnicode("123NOT123"), "123NOT123");
		assertEquals(UnicodeTranslator.toUnicode("123not123"), "123not123");
		assertEquals(UnicodeTranslator.toUnicode("NOT123"), "NOT123");
		assertEquals(UnicodeTranslator.toUnicode("not123"), "not123");

		assertEquals(UnicodeTranslator.toUnicode("_NOT"), "_NOT");
		assertEquals(UnicodeTranslator.toUnicode("_not"), "_not");
		assertEquals(UnicodeTranslator.toUnicode("_NOT_"), "_NOT_");
		assertEquals(UnicodeTranslator.toUnicode("_not_"), "_not_");
		assertEquals(UnicodeTranslator.toUnicode("NOT_"), "NOT_");
		assertEquals(UnicodeTranslator.toUnicode("not_"), "not_");
	}

	@Test
	public void VarOr() {
		assertEquals(UnicodeTranslator.toUnicode("varOR"), "varOR");
		assertEquals(UnicodeTranslator.toUnicode("varor"), "varor");
		assertEquals(UnicodeTranslator.toUnicode("varORvar"), "varORvar");
		assertEquals(UnicodeTranslator.toUnicode("varorvar"), "varorvar");
		assertEquals(UnicodeTranslator.toUnicode("ORvar"), "ORvar");
		assertEquals(UnicodeTranslator.toUnicode("orvar"), "orvar");

		assertEquals(UnicodeTranslator.toUnicode("123OR"), "123OR");
		assertEquals(UnicodeTranslator.toUnicode("123or"), "123or");
		assertEquals(UnicodeTranslator.toUnicode("123OR123"), "123OR123");
		assertEquals(UnicodeTranslator.toUnicode("123or123"), "123or123");
		assertEquals(UnicodeTranslator.toUnicode("OR123"), "OR123");
		assertEquals(UnicodeTranslator.toUnicode("or123"), "or123");

		assertEquals(UnicodeTranslator.toUnicode("_OR"), "_OR");
		assertEquals(UnicodeTranslator.toUnicode("_or"), "_or");
		assertEquals(UnicodeTranslator.toUnicode("_OR_"), "_OR_");
		assertEquals(UnicodeTranslator.toUnicode("_or_"), "_or_");
		assertEquals(UnicodeTranslator.toUnicode("OR_"), "OR_");
		assertEquals(UnicodeTranslator.toUnicode("or_"), "or_");
	}

	@Test
	public void VarPOW() {
		assertEquals(UnicodeTranslator.toUnicode("varPOW"), "varPOW");
		assertEquals(UnicodeTranslator.toUnicode("varpow"), "varpow");
		assertEquals(UnicodeTranslator.toUnicode("varPOWvar"), "varPOWvar");
		assertEquals(UnicodeTranslator.toUnicode("varpowvar"), "varpowvar");
		assertEquals(UnicodeTranslator.toUnicode("POWvar"), "POWvar");
		assertEquals(UnicodeTranslator.toUnicode("powvar"), "powvar");

		assertEquals(UnicodeTranslator.toUnicode("123pow"), "123pow");
		assertEquals(UnicodeTranslator.toUnicode("123POW"), "123POW");
		assertEquals(UnicodeTranslator.toUnicode("123POW123"), "123POW123");
		assertEquals(UnicodeTranslator.toUnicode("123pow123"), "123pow123");
		assertEquals(UnicodeTranslator.toUnicode("POW123"), "POW123");
		assertEquals(UnicodeTranslator.toUnicode("pow123"), "pow123");

		assertEquals(UnicodeTranslator.toUnicode("_POW"), "_POW");
		assertEquals(UnicodeTranslator.toUnicode("_pow"), "_pow");
		assertEquals(UnicodeTranslator.toUnicode("_POW_"), "_POW_");
		assertEquals(UnicodeTranslator.toUnicode("_pow_"), "_pow_");
		assertEquals(UnicodeTranslator.toUnicode("POW_"), "POW_");
		assertEquals(UnicodeTranslator.toUnicode("pow_"), "pow_");
	}

	@Test
	public void VarPOW1() {
		assertEquals(UnicodeTranslator.toUnicode("varPOW1"), "varPOW1");
		assertEquals(UnicodeTranslator.toUnicode("varpow1"), "varpow1");
		assertEquals(UnicodeTranslator.toUnicode("varPOW1var"), "varPOW1var");
		assertEquals(UnicodeTranslator.toUnicode("varpow1var"), "varpow1var");
		assertEquals(UnicodeTranslator.toUnicode("POW1var"), "POW1var");
		assertEquals(UnicodeTranslator.toUnicode("pow1var"), "pow1var");

		assertEquals(UnicodeTranslator.toUnicode("123POW1"), "123POW1");
		assertEquals(UnicodeTranslator.toUnicode("123pow1"), "123pow1");
		assertEquals(UnicodeTranslator.toUnicode("123POW1123"), "123POW1123");
		assertEquals(UnicodeTranslator.toUnicode("123pow1123"), "123pow1123");
		assertEquals(UnicodeTranslator.toUnicode("POW1123"), "POW1123");
		assertEquals(UnicodeTranslator.toUnicode("pow1123"), "pow1123");

		assertEquals(UnicodeTranslator.toUnicode("_POW1"), "_POW1");
		assertEquals(UnicodeTranslator.toUnicode("_pow1"), "_pow1");
		assertEquals(UnicodeTranslator.toUnicode("_POW1_"), "_POW1_");
		assertEquals(UnicodeTranslator.toUnicode("_pow1_"), "_pow1_");
		assertEquals(UnicodeTranslator.toUnicode("POW1_"), "POW1_");
		assertEquals(UnicodeTranslator.toUnicode("pow1_"), "pow1_");
	}

	@Test
	public void VarTRUE() {
		assertEquals(UnicodeTranslator.toUnicode("varTRUE"), "varTRUE");
		assertEquals(UnicodeTranslator.toUnicode("vartrue"), "vartrue");
		assertEquals(UnicodeTranslator.toUnicode("varTRUEvar"), "varTRUEvar");
		assertEquals(UnicodeTranslator.toUnicode("vartruevar"), "vartruevar");
		assertEquals(UnicodeTranslator.toUnicode("TRUEvar"), "TRUEvar");
		assertEquals(UnicodeTranslator.toUnicode("truevar"), "truevar");

		assertEquals(UnicodeTranslator.toUnicode("123TRUE"), "123TRUE");
		assertEquals(UnicodeTranslator.toUnicode("123true"), "123true");
		assertEquals(UnicodeTranslator.toUnicode("123TRUE123"), "123TRUE123");
		assertEquals(UnicodeTranslator.toUnicode("123true123"), "123true123");
		assertEquals(UnicodeTranslator.toUnicode("TRUE123"), "TRUE123");
		assertEquals(UnicodeTranslator.toUnicode("true123"), "true123");

		assertEquals(UnicodeTranslator.toUnicode("_TRUE"), "_TRUE");
		assertEquals(UnicodeTranslator.toUnicode("_true"), "_true");
		assertEquals(UnicodeTranslator.toUnicode("_TRUE_"), "_TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("_true_"), "_true_");
		assertEquals(UnicodeTranslator.toUnicode("TRUE_"), "TRUE_");
		assertEquals(UnicodeTranslator.toUnicode("true_"), "true_");
	}

	@Test
	public void VarUNION() {
		assertEquals(UnicodeTranslator.toUnicode("varUNION"), "varUNION");
		assertEquals(UnicodeTranslator.toUnicode("varunion"), "varunion");
		assertEquals(UnicodeTranslator.toUnicode("varUNIONvar"), "varUNIONvar");
		assertEquals(UnicodeTranslator.toUnicode("varunionvar"), "varunionvar");
		assertEquals(UnicodeTranslator.toUnicode("UNIONvar"), "UNIONvar");
		assertEquals(UnicodeTranslator.toUnicode("unionvar"), "unionvar");

		assertEquals(UnicodeTranslator.toUnicode("123UNION"), "123UNION");
		assertEquals(UnicodeTranslator.toUnicode("123union"), "123union");
		assertEquals(UnicodeTranslator.toUnicode("123UNION123"), "123UNION123");
		assertEquals(UnicodeTranslator.toUnicode("123union123"), "123union123");
		assertEquals(UnicodeTranslator.toUnicode("UNION123"), "UNION123");
		assertEquals(UnicodeTranslator.toUnicode("union123"), "union123");

		assertEquals(UnicodeTranslator.toUnicode("_UNION"), "_UNION");
		assertEquals(UnicodeTranslator.toUnicode("_union"), "_union");
		assertEquals(UnicodeTranslator.toUnicode("_UNION_"), "_UNION_");
		assertEquals(UnicodeTranslator.toUnicode("_union_"), "_union_");
		assertEquals(UnicodeTranslator.toUnicode("UNION_"), "UNION_");
		assertEquals(UnicodeTranslator.toUnicode("union_"), "union_");
	}
}