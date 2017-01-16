package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class FunctionTest {

	
	@Test
	public void testLambda() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k =  %x.(x : {1} | 1) \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test
	public void testOverride() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k,k2,p \n"
				+ "PROPERTIES p = 1 & k =  %x.(x : {1} | 1) & k2 = k <+ {1|->2}\n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k2").toString());
	}
	
	
	@Test
	public void testSetOperatorOnFunction() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k =  %x.(x : {1} | 1) \\/ %x.(x : {1} | 1)  \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER*INTEGER)", t.constants.get("k").toString());
	}
	
	@Test
	public void testSetOperatorOnFunction2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = k \\/ k & k(1) = 1 \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("POW(INTEGER*INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testLambdaException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES %x.(x : {1} | TRUE) =  %x.(x : {1} | 1) \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	
	@Test 
	public void testFunctionCall() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k(TRUE) =  1 \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(BOOL,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test 
	public void testFunctionCall2Arguments() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k(TRUE,1) =  1 \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(BOOL*INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test 
	public void testFunctionCallPair() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k(TRUE|->1) =  1 \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(BOOL*INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testFunctionCallException() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = %x.(x : {1} | TRUE) & k(1,1) = 1 \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test 
	public void testFunctionCallSucc() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k,k2 \n"
				+ "PROPERTIES k =  succ(k2) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("INTEGER", t.constants.get("k2").toString());
	}
	
	@Test 
	public void testTotalFunction() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = INT --> INT \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(FUNC(INTEGER,INTEGER))", t.constants.get("k").toString());
	}
	
	@Test 
	public void testTotalFunction2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k = INT --> INT & k2 : k &  k2 = k2 \\/ k2 \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(POW(INTEGER*INTEGER))", t.constants.get("k").toString());
	}
	
	@Test 
	public void testDomain() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k : INT --> INT & k2 = dom(k) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
	}
	
	@Test 
	public void testDomain2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = dom(%x.(x = 1| 1)) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testDomainException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = dom(%x.(x = 1| 1)) \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	
	@Test 
	public void testRange() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k, k2 \n"
				+ "PROPERTIES k : INT --> INT & k2 = ran(k) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(INTEGER)", t.constants.get("k2").toString());
	}
	
	@Test 
	public void testRange2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = ran(%x.(x = 1| 1)) \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRangeException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = ran(%x.(x = 1| 1)) \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	
	@Test 
	public void testPartialFunction() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k = INT +-> INT \n"
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(FUNC(INTEGER,INTEGER))", t.constants.get("k").toString());
	}
	
	
}
