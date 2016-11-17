package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.rules.project.RulesProject;

public class RulesProjectTest {

	@Test
	public void testProject2() throws Exception {
		File file = new File("src/test/resources/rules/project/test1/Rule1.rmch");
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.addLineNumbers = true;
		parsingBehaviour.prologOutput = true;
		RulesProject.parseProject(file, parsingBehaviour, System.out, System.err);
	}

	@Test
	public void testPackage() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/folder/M1.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testTransitiveDependency() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/TransitiveDependency.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}
	
	@Test
	public void testDisabled() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/Disabled.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testTransitiveDependencyRule() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/TransitiveDependencyRule.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testMainFileDoesNotExist() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/FileDoesNotExist.rmch");
		String expected = "exception('src/test/resources/rules/project/FileDoesNotExist.rmch (No such file or directory)').\n";
		assertEquals(expected, result);
	}

	@Test
	public void testUnkownRule() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/UnkownRule.rmch");
		String expected = "parse_exception(pos(6,17,'src/test/resources/rules/project/UnkownRule.rmch'),'Unknown operation: \\'otherRule\\'.').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testFileNameDoesNotMatchMachineName() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/DifferentFileName.rmch");
		String expected = "parse_exception(pos(1,15,'src/test/resources/rules/project/DifferentFileName.rmch'),'RULES_MACHINE name must match the file name: RulesMachine vs DifferentFileName').\n";
		System.out.println(result);
		assertEquals(expected, result);

	}

	@Test
	public void testRuleDependsOnItSelf() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/RuleDependsOnItSelf.rmch");
		String expected = "parse_exception(pos(5,17,'src/test/resources/rules/project/RuleDependsOnItSelf.rmch'),'Cyclic dependencies between operations: rule1 -> rule1').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testMissingComputationDependency() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/MissingComputationDependency.rmch");
		String expected = "parse_exception(pos(19,10,'src/test/resources/rules/project/MissingComputationDependency.rmch'),'Missing dependency to computation \\'compute_xx\\' in order to use variable \\'set\\'.').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testConfuseRuleAndComputation() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/ConfuseRuleAndComputation.rmch");
		String expected = "parse_exception(pos(10,24,'src/test/resources/rules/project/ConfuseRuleAndComputation.rmch'),'Identifier \\'rule1\\' is not a COMPUTATION.').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testCyclicRules() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/CyclicRules.rmch");
		String expected = "parse_exception(pos(11,17,'src/test/resources/rules/project/CyclicRules.rmch'),'Cyclic dependencies between operations: rule1 -> rule2 -> rule1').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testInvisibleComputation() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/MissingReference/M1.rmch");
		String expected = "parse_exception(pos(4,24,'src/test/resources/rules/project/references/MissingReference/M2.rmch'),'Operation \\'compute_xx\\' is not visible in RULES_MACHINE \\'M2\\'.').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testUnknwonComputation() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/MissingReference/M2.rmch");
		String expected = "parse_exception(pos(4,24,'src/test/resources/rules/project/references/MissingReference/M2.rmch'),'Unknown operation: \\'compute_xx\\'.').\n";
		System.out.println(result);
		assertEquals(expected, result);
	}

	private String getRulesMachineAsPrologTerm(String fileName) {
		File file = new File(fileName);
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.addLineNumbers = true;
		parsingBehaviour.prologOutput = true;
		OutputStream out = new OutputStream() {
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
		RulesProject.parseProject(file, parsingBehaviour, new PrintStream(out), new PrintStream(out));
		return out.toString();
	}

}
