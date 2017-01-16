package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class MultTest{

	
	@Test
	public void testMult() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES 1 * 2 = k \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test
	public void testMult2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES 1 * 2 * 3 = k \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test
	public void testMult3() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3 \n"
				+ "PROPERTIES k * k2 * k3 = 4 \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("INTEGER", t.constants.get("k2").toString());
		assertEquals("INTEGER", t.constants.get("k3").toString());
	}
	
	@Test
	public void testMult4() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3 \n"
				+ "PROPERTIES k * 1 * k2 = k3 \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("INTEGER", t.constants.get("k2").toString());
		assertEquals("INTEGER", t.constants.get("k3").toString());
	}
	
	@Test
	public void testMult5() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3, k4 \n"
				+ "PROPERTIES k * k2 * k3 = k4 * 1 \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("INTEGER", t.constants.get("k2").toString());
		assertEquals("INTEGER", t.constants.get("k3").toString());
		assertEquals("INTEGER", t.constants.get("k4").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testMultException() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES TRUE = k * k2 \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testMultException2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES  k = TRUE * k2 \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testCart1() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3 \n"
				+ "PROPERTIES k = k2 * k3 & k2 = {1} & k3 = {TRUE} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER*BOOL)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
		assertEquals("POW(BOOL)", t.constants.get("k3").toString());
	}
	
	@Test
	public void testCart2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k * k2 = {1} * {TRUE} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(BOOL)", t.constants.get("k2").toString());
	}
	
	@Test
	public void testCart3() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3 \n"
				+ "PROPERTIES k * k2 * k3 = {TRUE} * {1} * {TRUE} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(BOOL)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
		assertEquals("POW(BOOL)", t.constants.get("k3").toString());
	}
	
	@Test
	public void testCart4() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3 \n"
				+ "PROPERTIES k * k2 * k3 = k2 * k3 * {1} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k3").toString());
	}
	
	@Test
	public void testCart5() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2, k3, k4 \n"
				+ "PROPERTIES k * k2 = k3 * k4 &  k4 = {1} & k = {1} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k3").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k4").toString());

	}
	
	@Test
	public void testCart6() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k * k2 = k2 * {1} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
	}
	
	@Test
	public void testCart7() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k2 * k = k * {1} \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
	}
	
}
