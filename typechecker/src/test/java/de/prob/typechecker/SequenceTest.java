package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class SequenceTest {

	@Test
	public void testSequence() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [1] \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}

	@Test
	public void testSequence2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES k = [1,2,k2] \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}

	@Test(expected = TypeErrorException.class)
	public void testSequenceException() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [1, TRUE] \n" + "END";
		new TestTypechecker(machine);
	}

	@Test(expected = TypeErrorException.class)
	public void testSequenceException2() throws Exception {
		String machine = "MACHINE test\n" + "PROPERTIES 1 = [1] \n" + "END";
		new TestTypechecker(machine);
	}

	@Test
	public void testSetOperatorOnSequence() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [1] \\/ {} \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(INTEGER*INTEGER)", t.constants.get("k").toString());
	}

	@Test
	public void testConcatenation() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [1] ^ [2] \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}

	@Test
	public void testConcatenationOnSet() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [3] ^ {(1,4)} \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}

	@Test
	public void testSize() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = size([1]) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}

	@Test(expected = TypeErrorException.class)
	public void testSizeException() throws Exception {
		String machine = "MACHINE test\n" + "PROPERTIES TRUE = size([1]) \n"
				+ "END";
		new TestTypechecker(machine);
	}

	@Test(expected = TypeErrorException.class)
	public void testSizeException2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = size(1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testPrepend() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = 1 -> [] \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test
	public void testPrepend2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES [1] = k -> k2 \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k2").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testPrependException() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES 1 = k -> k2 \n" + "END";
		new TestTypechecker(machine);
	}
	
	
	@Test //TODO sequence type
	public void testAppend() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = [] <- 1 \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test
	public void testAppend2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES [1] = k <- k2 \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k2").toString());
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testAppendException() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES 1 = k <- k2 \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testReverse() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = rev([1]) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testReverseException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES 1 = rev([1]) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testFirst() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = first([1]) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testFirstException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES TRUE = first([1]) \n" + "END";
		new TestTypechecker(machine);
	}

	@Test
	public void testLast() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = last([1]) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testLastException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES TRUE = last([1]) \n" + "END";
		new TestTypechecker(machine);
	}
	
}
