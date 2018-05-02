package de.prob.parser;

import org.junit.Test;

import de.prob.parser.antlr.Antlr4BParser;
import de.prob.parser.ast.visitors.TypeErrorException;

public class SimpleMachineTest {

	@Test
	public void testMachine() throws TypeErrorException {
		String machine = "MACHINE test\n";
		machine += "CONSTANTS k\n";
		machine += "PROPERTIES k = INTEGER \n";
		machine += "VARIABLES x,y \n";
		machine += "INVARIANT x : INTEGER & y : BOOL \n";
		machine += "INITIALISATION x := 1 || y := TRUE \n";
		machine += "OPERATIONS IncX = SELECT x < 10 THEN x := x + 1 END \n";
		machine += "END";
		Antlr4BParser.createSemanticAST(machine);
	}

}
