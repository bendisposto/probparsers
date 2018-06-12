package de.prob.parser;

import org.junit.Test;

import de.prob.parser.antlr.Antlr4BParser;
import de.prob.parser.antlr.ScopeException;
import de.prob.parser.ast.visitors.TypeErrorException;

public class ScopeCheckerTest {

	@Test
	public void testKnownOperation() throws TypeErrorException {
		String machineA = "MACHINE A\n";
		machineA += "INCLUDES B\n";
		machineA += "OPERATIONS\n";
		machineA += "foo = SELECT 1=1 THEN bar END \n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "OPERATIONS\n";
		machineB += "bar = PRE 1=1 THEN skip END \n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}

	@Test(expected = ScopeException.class)
	public void testUnknownOperation() {
		String machineA = "MACHINE A\n";
		machineA += "INCLUDES B\n";
		machineA += "OPERATIONS\n";
		machineA += "foo = SELECT 1=1 THEN bar END \n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}

	@Test
	public void testKnownConstant() throws TypeErrorException {
		String machineA = "MACHINE A\n";
		machineA += "INCLUDES B\n";
		machineA += "PROPERTIES k = 2-1\n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "CONSTANTS k PROPERTIES k = 1 \n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}

	@Test(expected = ScopeException.class)
	public void testUnknownConstant() throws TypeErrorException {
		String machineA = "MACHINE A\n";
		machineA += "PROPERTIES k = 2-1\n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "CONSTANTS k PROPERTIES k = 1 \n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}
	
	@Test
	public void testKnownVariable() throws TypeErrorException {
		String machineA = "MACHINE A\n";
		machineA += "INCLUDES B\n";
		machineA += "INVARIANT x = 1\n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "VARIABLES x INVARIANT x = 1 INITIALISATION x := 1 \n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}

	@Test(expected = ScopeException.class)
	public void testUnknownVariable() throws TypeErrorException {
		String machineA = "MACHINE A\n";
		machineA += "INVARIANT x = 1\n";
		machineA += "END";

		String machineB = "MACHINE B\n";
		machineB += "VARIABLES x INVARIANT x = 1 INITIALISATION x := 1 \n";
		machineB += "END";
		checkmachines(machineA, machineB);
	}
	
	private void checkmachines(String machineA, String machineB) {
		Antlr4BParser.createProject(machineA, machineB);
	}

}
