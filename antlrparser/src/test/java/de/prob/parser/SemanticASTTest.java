package de.prob.parser;

import org.junit.Test;

import de.prob.parser.antlr.Antlr4BParser;

public class SemanticASTTest {

	@Test
	public void testRecords() throws Exception {
		String machine = "MACHINE test2\n";
		machine += "CONSTANTS k\n";
		machine += "PROPERTIES k = rec(a:1, b:TRUE) \n";
		machine += "END";
		check(machine);
		// TODO add class for records and structs
	}

	private void check(String main, String... others) throws Exception {
		Antlr4BParser.createBProjectFromMachineStrings(main, others);
	}

}
