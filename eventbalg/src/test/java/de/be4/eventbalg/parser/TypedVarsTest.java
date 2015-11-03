package de.be4.eventbalg.parser;

import org.junit.Test;

import de.be4.eventbalg.core.parser.BException;

public class TypedVarsTest extends AbstractTest {

	@Test
	public void testNAT() throws BException {
		parseInput("machine m0 NAT x := x + 1 end", false);
	}

	@Test
	public void testNAT1() throws BException {
		parseInput("machine m0 NAT1 x := x + 1 end", false);
	}

	@Test
	public void testINT() throws BException {
		parseInput("machine m0 INT x := x + 1 end", false);
	}

	@Test
	public void testBOOL() throws BException {
		parseInput("machine m0 BOOL x := x + 1 end", false);
	}

	@Test
	public void testComplicatedType() throws BException {
		parseInput("machine m0 var x type x : NAT init := x + 1 end", false);
	}

	@Test
	public void testMultiple() throws BException {
		parseInput(
				"machine m0 NAT x := x + 1 ; NAT1 x := x + 1; INT x := x + 1 end",
				false);
	}

	@Test
	public void testMultiple2() throws BException {
		parseInput(
				"machine m0 NAT x := x + 1; NAT1 x := x + 1; INT x := x + 1; BOOL x := x + 1 end",
				false);
	}

	@Test
	public void testMultiple3() throws BException {
		parseInput(
				"machine m0 NAT x := x + 1; NAT1 x := x + 1; INT x := x + 1; BOOL x := x + 1; var x type x : NAT init := x + 1 end",
				false);
	}

	@Test
	public void testMultiple3Mixed() throws BException {
		parseInput(
				"machine m0 NAT x := x + 1; var y type y : NAT1 init y := 1; INT x := x + 1; BOOL x := x + 1; var x type x : NAT init x := x + 1 end",
				false);
	}
}
