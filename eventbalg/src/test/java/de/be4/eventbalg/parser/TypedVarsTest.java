package de.be4.eventbalg.parser;

import org.junit.Test;

import de.be4.eventbalg.core.parser.BException;

public class TypedVarsTest extends AbstractTest {

	@Test
	public void testNAT() throws BException {
		parseInput("machine m0 var x type x : NAT init x := 0 end", false);
	}

	@Test
	public void testNAT1() throws BException {
		parseInput("machine m0 var x type x : NAT1 init x := 1 end", false);
	}

	@Test
	public void testINT() throws BException {
		parseInput("machine m0 var x type x : INT init x := 1 end", false);
	}

	@Test
	public void testBOOL() throws BException {
		parseInput("machine m0 var x type x : BOOL init x := x + 1 end", false);
	}

	@Test
	public void testComplicatedType() throws BException {
		parseInput("machine m0 var x type x : NAT init := x + 1 end", false);
	}

	@Test
	public void testMultiple() throws BException {
		parseInput(
				"machine m0 var x type x : NAT init x := 0 ; var y type y : NAT init y := 1; var z type z : NAT init z := 2 end",
				false);
	}

	@Test
	public void fullEuclid() throws BException {
		parseInput(
				"machine euclid sees definitions limits var u type u : 0..k init u:=m ; var v type v : 0..k init v:=n algorithm while: u /= 0 do if: u < v then @u u := v ; @v v := u end ; @uu u := u - v end ; assert: u|->m|->n : IsGCD end end",
				false);
	}

	@Test
	public void testDefinitions() throws BException {
		parseInput(
				"context definitions constants Divides GCD axioms @axm Divides = {i↦j ∣ ∃k·k∈0‥j ∧ j = i∗k} @axm2 GCD = {x↦y↦res ∣ res↦x ∈ Divides ∧ res↦y ∈ Divides ∧ (∀r· r ∈ (0‥x ∪ 0‥y) ⇒ (r↦x ∈ Divides ∧ r↦y ∈ Divides ⇒ r↦res ∈ Divides) ) } end",
				false);
	}
}
