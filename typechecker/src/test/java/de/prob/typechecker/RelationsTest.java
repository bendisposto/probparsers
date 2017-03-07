package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class RelationsTest {
	
	@Test 
	public void testCouple() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k\n"
				+ "PROPERTIES k =  (1|->1) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER*INTEGER", t.constants.get("k").toString());
	}
	
	@Test 
	public void testCouple2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES (k|->TRUE) =  (1|->k2) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("BOOL", t.constants.get("k2").toString());
	}
	
	@Test 
	public void testCouple3() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k =  (1,2,3) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("(INTEGER*INTEGER)*INTEGER", t.constants.get("k").toString());
	}
	
	@Test 
	public void testCouple4() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES (1|->1) =  (1,1) \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testCoupleWithDifferentNumberOfComponents() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES (1,2) =  (1,2,3) \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test 
	public void testRelation() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = INT <-> INT \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(POW(INTEGER*INTEGER))", t.constants.get("k").toString());
	}
	
	@Test 
	public void testIdentity() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k\n"
				+ "PROPERTIES k = id({1,2}) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test 
	public void testDomainRestriction() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2\n"
				+ "PROPERTIES k = {1} <| {(k2, TRUE)} \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,BOOL)", t.constants.get("k").toString());
		assertEquals("INTEGER", t.constants.get("k2").toString());
	}
	
	
	
}
