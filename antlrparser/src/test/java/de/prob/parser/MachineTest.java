package de.prob.parser;

import org.junit.Test;

import de.prob.parser.antlr.Antlr4BParser;
import de.prob.parser.ast.visitors.TypeErrorException;

public class MachineTest {

	public String getTestMachine() {
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
	public void testSingleMachine() throws TypeErrorException {
		Antlr4BParser.createSemanticAST(getTestMachine());
	}

	@Test
	public void testMachines() throws TypeErrorException {
		String machine = "MACHINE test2\n";
		machine += "INCLUDES test\n";
		machine += "CONSTANTS k2\n";
		machine += "PROPERTIES k2 : k \n";
		machine += "VARIABLES a \n";
		machine += "INVARIANT a : INTEGER \n";
		machine += "INITIALISATION a := 1 \n";
		machine += "OPERATIONS IncX = SELECT a < 10 THEN IncX END \n";
		machine += "END";

		Antlr4BParser.createProject(machine, getTestMachine());
	}

}
