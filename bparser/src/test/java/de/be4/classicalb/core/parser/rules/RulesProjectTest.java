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
		File file = new File("src/test/resources/rules/project/references/test1/Rule1.rmch");
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.addLineNumbers = true;
		parsingBehaviour.prologOutput = true;
		RulesProject.parseProject(file, parsingBehaviour, System.out, System.err);
	}

	@Test
	public void testRulesMachineNameDoesNotMatchFileName() throws Exception {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/RulesMachineNameDoesNotMatchFileName.rmch");
		System.out.println(result);
		assertTrue(result.contains("parse_exception(pos(1,15"));
		assertTrue(result.contains("RULES_MACHINE name must match the file name"));
	}

	@Test
	public void testPackage() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/folder/M1.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testForAll() throws Exception {
		String f = "src/test/resources/rules/ForAllPredicate.rmch";
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.addLineNumbers = true;
		RulesProject.parseProject(new File(f), parsingBehaviour, System.out, System.err);
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
		String expected = "(No such file or directory)";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testUnkownRule() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/UnkownRule.rmch");
		String expected = "Unknown operation: ";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testParseError() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/ParseError.rmch");
		String expected = "[4,1] expecting: ";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testFileNameDoesNotMatchMachineName() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/DifferentFileName.rmch");
		String expected = "'RULES_MACHINE name must match the file name: RulesMachine vs DifferentFileName').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));

	}

	@Test
	public void testRuleDependsOnItSelf() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/RuleDependsOnItSelf.rmch");
		String expected = "Cyclic dependencies between operations: rule1 -> rule1').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testComputationDependsOnItSelf() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/ComputationDependsOnItSelf.rmch");
		String expected = "Cyclic dependencies between operations: compute_x -> compute_x').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testMissingComputationDependency() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/MissingComputationDependency.rmch");
		String expected = "'Missing dependency to computation \\'compute_xx\\' in order to use variable \\'set\\'.').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testConfuseRuleAndComputation() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/ConfuseRuleAndComputation.rmch");
		String expected = "Identifier \\'rule1\\' is not a COMPUTATION.').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testCyclicRules() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/CyclicRules.rmch");
		String expected = "Cyclic dependencies between operations: rule1 -> rule2 -> rule1').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testInvisibleComputation() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/MissingReference/M1.rmch");
		String expected = "Operation \\'compute_xx\\' is not visible in RULES_MACHINE \\'M2\\'.";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testUnknwonComputation() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/MissingReference/M2.rmch");
		String expected = "Unknown operation: \\'compute_xx\\'.')";
		System.out.println(result);
		assertTrue(result.contains(expected));
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
