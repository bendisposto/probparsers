package de.prob.parser;

import org.junit.Test;

import de.prob.parser.antlr.Antlr4BParser;

public class MachineTest {

	public String getMainMachine() {
		String machine = "MACHINE test\n";
		machine += "CONSTANTS k\n";
		machine += "PROPERTIES k = INTEGER \n";
		machine += "VARIABLES x,y \n";
		machine += "INVARIANT x : INTEGER & y : BOOL \n";
		machine += "INITIALISATION x := 1 || y := TRUE \n";
		machine += "OPERATIONS IncX = SELECT x < 10 THEN x := x + 1 END \n";
		machine += "END";
		return machine;
	}

	@Test
	public void testSingleMachine() throws Exception {
		check(getMainMachine());
	}

	@Test
	public void testMachines() throws Exception {
		String machine = "MACHINE test2\n";
		machine += "INCLUDES test\n";
		machine += "CONSTANTS k2\n";
		machine += "PROPERTIES k2 : k \n";
		machine += "VARIABLES a \n";
		machine += "INVARIANT a : INTEGER \n";
		machine += "INITIALISATION a := 1 \n";
		machine += "OPERATIONS IncX = SELECT a < 10 THEN IncX END;\n";
		machine += "IncX2 = IF a < 10 THEN IncX END \n";
		machine += "END";

		check(machine, getMainMachine());
	}

	@Test
	public void testMachines2() throws Exception {
		String machine = "MACHINE test2\n";
		machine += "INCLUDES test\n";
		machine += "CONSTANTS k2\n";
		machine += "PROPERTIES k2 : k \n";
		machine += "VARIABLES a \n";
		machine += "INVARIANT a : INTEGER \n";
		machine += "INITIALISATION a := 1 \n";
		machine += "OPERATIONS IncX = SELECT a < 10 THEN IncX END;\n";
		machine += "IncX2 = IF a < 10 THEN IncX ELSE IncX END \n";
		machine += "END";

		check(machine, getMainMachine());
	}

	private void check(String main, String... others) throws Exception {
		Antlr4BParser.createBProjectFromMachineStrings(main, others);
	}

}
