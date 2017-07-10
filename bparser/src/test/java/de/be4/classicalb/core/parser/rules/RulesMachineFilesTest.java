package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.rules.RulesMachineRunConfiguration.RuleGoalAssumption;

public class RulesMachineFilesTest {
	public static final String dir = "src/test/resources/rules/";

	@Test
	public void testProject2() throws Exception {
		File file = new File("src/test/resources/rules/project/references/test1/Rule1.rmch");
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.setAddLineNumbers(true);
		parsingBehaviour.setPrologOutput(true);
		RulesProject.parseProject(file, parsingBehaviour, System.out, System.err);
	}

	@Test
	public void testRulesMachineConfiguration() throws Exception {
		File file = new File("src/test/resources/rules/project/RulesMachineConfigurationTest.rmch");
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.setAddLineNumbers(true);
		parsingBehaviour.setPrologOutput(true);
		RulesProject project = new RulesProject();
		project.parseProject(file);
		project.checkAndTranslateProject();
		RulesMachineRunConfiguration rulesMachineRunConfiguration = project.getRulesMachineRunConfiguration();
		Set<RuleGoalAssumption> rulesGoalAssumptions = rulesMachineRunConfiguration.getRulesGoalAssumptions();
		assertEquals(2, rulesGoalAssumptions.size());
		for (Iterator<RuleGoalAssumption> iterator = rulesGoalAssumptions.iterator(); iterator.hasNext();) {
			RuleGoalAssumption next = iterator.next();
			if ("rule1".equals(next.getRuleName())) {
				assertEquals(new HashSet<Integer>(Arrays.asList(1)), next.getErrorTypesAssumedToSucceed());
				assertEquals(true, next.isCheckedForCounterexamples());
				assertEquals("rule1", next.getRuleOperation().getName());
			} else {
				assertEquals("rule2", next.getRuleName());
				assertEquals(new HashSet<Integer>(Arrays.asList(1, 2)), next.getErrorTypesAssumedToFail());
				assertEquals(false, next.isCheckedForCounterexamples());
			}
		}
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
		parsingBehaviour.setAddLineNumbers(true);
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
	public void testUnknownRule() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/UnknownRule.rmch");
		String expected = "Unknown operation: ";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testUnknownIdentifier() throws Exception {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/UnknownIdentifier.rmch");
		String expected = "Unknown identifier ";
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
		String expected = "RULES_MACHINE name must match the file name";
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
	public void testFilePragma() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/FilePragma.rmch");
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testInvalidFilePragma() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/DirectoryInFilePragma.rmch");
		System.out.println(result);
		assertTrue(result.contains("is a directory"));
	}

	@Test
	public void testFileDoesNotExistInFilePragma() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/FileDoesNotExistInFilePragma.rmch");
		System.out.println(result);
		assertTrue(result.contains("parse_exception"));
		assertTrue(result.contains("does not exist"));
	}

	@Test
	public void testFunctionDependencies() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/FunctionDependencies.rmch");
		System.out.println(result);
		assertTrue(result.contains("exception"));
		assertTrue(result.contains("Missing dependencies due to FUNCTION call: COMP_comp1"));
		assertTrue(result.contains("Missing dependencies due to FUNCTION call: COMP_comp2"));
	}

	@Test
	public void testReferencedMachineNotFound() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/ReferencedMachineNotFound.rmch");
		System.out.println(result);
		assertTrue(result.contains("parse_exception"));
		assertTrue(result.contains("Machine not found"));
	}

	@Test
	public void testPackagePragma() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/PackagePragma.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testReplacement() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/references/Replacement.rmch");
		System.out.println(result);
		assertFalse(result.contains("exception"));
		// the result should not contain name of the replacement operation
		assertFalse(result.contains("COMP_comp2New"));
	}

	@Test
	public void testImportedPackageDoesNotExist() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/packagePragma/ImportedPackageDoesNotExist.rmch");
		System.out.println(result);
		assertTrue(result.contains("exception"));
		assertTrue(result.contains("Imported package does not exist"));

	}

	@Test
	public void testImportedPackageDoesNotExist2() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/packagePragma/ImportedPackageDoesNotExist2.rmch");
		System.out.println(result);
		assertTrue(result.contains("parse_exception(pos(3,19,"));
		assertTrue(result.contains("Imported package does not exist"));

	}

	@Test
	public void testDuplicatePackageImport() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/packagePragma/DuplicatePackageImport.rmch");
		System.out.println(result);
		assertTrue(result.contains("exception"));
		assertTrue(result.contains("Duplicate package import"));

	}

	@Test
	public void testInvalidPackagePragma() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/InvalidPackagePragma.rmch");
		System.out.println(result);
		assertTrue(result.contains("does not match the folder structure"));
	}

	@Test
	public void testInvalidPackagePragma2() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/project/references/InvalidPackagePragma2.rmch");
		System.out.println(result);
		assertTrue(result.contains("Invalid folder name"));
	}

	@Test
	public void testComputationDependsOnItSelf() {
		String result = getRulesMachineAsPrologTerm("src/test/resources/rules/project/ComputationDependsOnItSelf.rmch");
		String expected = "Cyclic dependencies between operations: compute_x -> compute_x').\n";
		System.out.println(result);
		assertTrue(result.contains(expected));
	}

	@Test
	public void testImplicitDependencyToComputation() {
		String result = getRulesMachineAsPrologTerm(
				"src/test/resources/rules/ImplicitDependencyToComputation.rmch");
		assertFalse(result.contains("exception"));
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

	@Test
	public void testReplaces() {
		String result = getRulesMachineAsPrologTerm(dir + "Replaces.rmch");
		System.out.println(result);
		//System.out.println(RulesUtil.getRulesMachineAsBMachine(new File(dir, "Replaces.rmch")));
		assertFalse(result.contains("exception"));
		assertFalse(result.contains("COMP_NewComp1"));
	}

	private String getRulesMachineAsPrologTerm(String fileName) {
		File file = new File(fileName);
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.setAddLineNumbers(true);
		parsingBehaviour.setPrologOutput(true);
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
