package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.be4.classicalb.core.parser.rules.RulesUtil.*;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;

public class RulesProjectExceptionTest {

	@Test
	public void testDuplicateOperationNameException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END; COMPUTATION foo BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Duplicate operation name: \\'foo\\'"));
	}

	@Test
	public void testDependsOnRuleIsNotARuleException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE bar BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END; COMPUTATION bar BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Operation \\'bar\\' is not a RULE operation.'"));
	}

	@Test
	public void testUnkownRuleInPredicateOperatorException() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == FAILED_RULE(foo) END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("Unknown rule \\'foo\\'"));
	}

	@Test
	public void testUnknownFunction() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo BODY VAR x IN x <--Foo(1) END;RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Unknown FUNCTION name \\'Foo\\''"));
	}

	@Test
	public void testWritingDefineVariable() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY DEFINE v1 TYPE POW(INTEGER) VALUE {1} END; v1 := {2} END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result
				.contains("'Identifier \\'v1\\' is not a local variable (VAR). Hence, it can not be assigned here.'"));
	}

	@Test
	public void testRulesMachineInOrdinaryMachineFileException() throws Exception {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};
		PrintStream pStream = new PrintStream(output);
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.setPrologOutput(true);
		BParser bParser = new BParser("RulesMachineInOrdinaryMachineFile.mch");
		bParser.fullParsing(new File("src/test/resources/rules/project/RulesMachineInOrdinaryMachineFile.mch"),
				parsingBehaviour, pStream, pStream);
		System.out.println(output.toString());
		assertTrue(output.toString().contains("parse_exception"));
	}
}
