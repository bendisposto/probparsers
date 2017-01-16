package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class RecordTest {

	
	@Test
	public void testRecord() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = rec(a:1 , b : TRUE) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("struct(a:INTEGER,b:BOOL)", t.constants.get("k").toString());
	}
	
	@Test
	public void testRecord2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k,k2 \n"
				+ "PROPERTIES rec(a: k, b:TRUE) = rec(a:1 , b : k2) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
		assertEquals("BOOL", t.constants.get("k2").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRecordException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = rec(a:1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRecordException2() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES rec(a:1) = rec(b:1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRecordException3() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES rec(a:TRUE) = rec(a:1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRecordException4() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES rec(a:1,b:1) = rec(a:1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testRecordSelect() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = rec(a: 1)'a \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test
	public void testRecordSelect2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES 1 = rec(a: k)'a \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("INTEGER", t.constants.get("k").toString());
	}
	
	@Test
	public void testRecordSelect3() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k'a = 1 \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("struct(a:INTEGER)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testRecordSelectException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES TRUE = rec(a:1)'a \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testStruct() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = struct(a: INT) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(struct(a:INTEGER))", t.constants.get("k").toString());
	}
	
	@Test
	public void testStruct2() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k, k2 \n"
				+ "PROPERTIES struct(a: k, b:BOOL) = struct(a: INT, b:k2) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(INTEGER)", t.constants.get("k").toString());
		assertEquals("POW(BOOL)", t.constants.get("k2").toString());
	}
	
}
