package de.hhu.stups.codegenerator;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by fabian on 31.05.18.
 */

public class TestJava {

	public static void writeInputToSystem(InputStream inputStream) throws IOException {
		writeInputToOutput(inputStream, System.err);
	}

	public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
		int size;
		byte[] buffer = new byte[1024];
		while ((size = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, size);
	}

	public void testJava(String machine) throws Exception {
		Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
				.getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
		CodeGenerator codeGenerator = new CodeGenerator();
		Set<Path> javaFilePaths = codeGenerator.generate(mchPath, GeneratorMode.JAVA, false,true);
		Process process = Runtime.getRuntime()
				.exec("javac -classpath btypes_persistent.jar " + String.join(" ", javaFilePaths.stream()
						.map(path -> path.toFile().getAbsoluteFile().toString())
						.collect(Collectors.toSet())));

		writeInputToSystem(process.getErrorStream());
		writeInputToOutput(process.getErrorStream(), process.getOutputStream());
		process.waitFor();

		Set<File> classFiles = javaFilePaths.stream()
				.map(path -> new File(path.getParent().toFile(), machine + ".class"))
				.collect(Collectors.toSet());

		//javaFilePaths.forEach(path -> cleanUp(path.toString()));
		//classFiles.forEach(path -> cleanUp(path.getAbsolutePath().toString()));
	}

	@Test
	public void testExample() throws Exception {
		testJava("Example");
	}

	@Test
	public void testOperation() throws Exception {
		testJava("Operation");
	}

	@Test
	public void testLocalDeclaration() throws Exception {
		testJava("LocalDeclaration");
	}

	@Ignore
	@Test
	public void testRefinement() throws Exception {
		// TODO VAR-Node
		testJava("RefinementMachine");
	}

	@Test
	public void testEnumSets() throws Exception {
		testJava("EnumSets");
	}

	@Test
	public void testNameCollision() throws Exception {
		testJava("NameCollision");
	}

	@Test
	public void testWhile() throws Exception {
		testJava("While");
	}

	@Test
	public void testInterval() throws Exception {
		testJava("Interval");
	}


	@Test
	public void testPair() throws Exception {
		testJava("Pair");
	}

	@Test
	public void testIfAndPredicates() throws Exception {
		testJava("IfAndPredicates");
	}

	@Test
	public void testDanglingElse() throws Exception {
		testJava("DanglingElse");
	}

	@Test
	public void testImplies() throws Exception {
		testJava("Implies");
	}

	@Test
	public void testEquivalence() throws Exception {
		testJava("Equivalence");
	}

	@Test
	public void testBooleanPredicate() throws Exception {
		testJava("BooleanPredicate");
	}

	@Ignore
	@Test
	public void testRecords() throws Exception {
		testJava("Records");
	}

	@Test
	public void testNondeterminism() throws Exception {
		testJava("Nondeterminism");
	}

	@Test
	public void testMapFunction() throws Exception {
		testJava("MapFunction");
	}

	@Test
	public void testRelationImage() throws Exception {
		testJava("RelationImage");
	}

	@Test
	public void testEmptySet() throws Exception {
		testJava("EmptySet");
	}

	@Test
	public void testSetUnion() throws Exception {
		testJava("SetUnion");
	}


	@Test
	public void testCounter() throws Exception {
		testJava("Counter");
	}

	@Test
	public void testBakery0() throws Exception {
		testJava("Bakery0");
	}

	@Ignore
	@Test
	public void testGCD() throws Exception {
		// TODO
		testJava("GCD");
	}

	@Test
	public void testACounter() throws Exception {
		testJava("ACounter");
	}

	@Test
	public void testLift() throws Exception {
		testJava("Lift");
	}

	@Ignore
	@Test
	public void testTravelAgency() throws Exception {
		testJava("TravelAgency");
	}

	@Test
	public void testTrafficLight() throws Exception {
		testJava("TrafficLight");
	}

	@Ignore
	@Test
	public void testPhonebook() throws Exception {
		// TODO
		testJava("phonebook");
	}

	@Ignore
	@Test
	public void testPhonebook6() throws Exception {
		// TODO
		testJava("phonebook6");
	}

	@Ignore
	@Test
	public void testSum() throws Exception {
		testJava("Sum");
	}

	@Ignore
	@Test
	public void testRecursion() throws Exception {
		//Correct exception
		testJava("recursion/Sum1");
	}

	@Test
	public void testProject() throws Exception {
		testJava("project1/A");
	}

	@Test
	public void testLiftBenchmarks() throws Exception {
		testJava("liftbenchmarks/LiftExec");
	}


	@Test
	public void testSieveBenchmarks() throws Exception {
		testJava("sievebenchmarks/Sieve");
	}

	@Test
	public void testTrafficLightBenchmarks() throws Exception {
		testJava("trafficlightbenchmarks/TrafficLightExec");
	}

	@Test
	public void testIncreasingSet() throws Exception {
		testJava("setoperationbenchmarks/IncreasingSet");
	}

	@Test
	public void testSetOperation() throws Exception {
		testJava("setoperationbenchmarks/SetOperation");
	}

	@Test
	public void testProject2() throws Exception {
		testJava("project2/MachineA");
	}

	@Test
	public void testSieve() throws Exception {
		testJava("Sieve");
	}

	@Test
	public void testReset() throws Exception {
		testJava("Reset");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testJava("ManyLocalDeclarations");
	}

	@Test
	public void testManyLocalDeclarations2() throws Exception {
		testJava("ManyLocalDeclarations2");
	}

	@Test
	public void testPlus() throws Exception {
		testJava("arithmetic/Plus");
	}

	@Test
	public void testMinus() throws Exception {
		testJava("arithmetic/Minus");
	}

	@Test
	public void testMultiply() throws Exception {
		testJava("arithmetic/Multiply");
	}

	@Test
	public void testDivide() throws Exception {
		testJava("arithmetic/Divide");
	}


	@Test
	public void testModulo() throws Exception {
		testJava("arithmetic/Modulo");
	}

	@Test
	public void testNegative() throws Exception {
		testJava("arithmetic/Negative");
	}

	@Ignore
	@Test
	public void testPositive() throws Exception {
		testJava("arithmetic/Positive");
	}

	@Test
	public void testSmallNumbers() throws Exception {
		testJava("integers/SmallNumbers");
	}

	@Test
	public void testBigNumbers() throws Exception {
		testJava("integers/BigNumbers");
	}

	@Test
	public void testAnd() throws Exception {
		testJava("logical/And");
	}


	@Test
	public void testOr() throws Exception {
		testJava("logical/Or");
	}


	@Test
	public void testImpliesPerformance() throws Exception {
		testJava("logical/Implies");
	}


	@Ignore
	@Test
	public void testNot() throws Exception {
		testJava("logical/Not");
	}

	@Test
	public void testEquivalent() throws Exception {
		testJava("logical/Equivalent");
	}

	@Test
	public void testLess() throws Exception {
		testJava("comparison/Less");
	}

	@Test
	public void testLessEqual() throws Exception {
		testJava("comparison/LessEqual");
	}

	@Test
	public void testGreater() throws Exception {
		testJava("comparison/Greater");
	}


	@Test
	public void testGreaterEqual() throws Exception {
		testJava("comparison/GreaterEqual");
	}


	@Test
	public void tessEqual() throws Exception {
		testJava("comparison/Equal");
	}


	@Test
	public void testUnequal() throws Exception {
		testJava("comparison/Unequal");
	}

	@Test
	public void testCardBig() throws Exception {
		testJava("setoperation_big/SetCardBig");
	}

	@Test
	public void testComplementBig() throws Exception {
		testJava("setoperation_big/SetComplementBig");
	}

	@Test
	public void testElementOfBig() throws Exception {
		testJava("setoperation_big/SetElementOfBig");
	}

	@Test
	public void testIntersectionBig() throws Exception {
		testJava("setoperation_big/SetIntersectionBig");
	}

	@Test
	public void testUnionBig() throws Exception {
		testJava("setoperation_big/SetUnionBig");
	}

	@Test
	public void testCardSmall() throws Exception {
		testJava("setoperation_small/SetCardSmall");
	}

	@Test
	public void testComplementSmall() throws Exception {
		testJava("setoperation_small/SetComplementSmall");
	}

	@Test
	public void testElementOfSmall() throws Exception {
		testJava("setoperation_small/SetElementOfSmall");
	}

	@Test
	public void testIntersectionSmall() throws Exception {
		testJava("setoperation_small/SetIntersectionSmall");
	}

	@Test
	public void testUnionSmall() throws Exception {
		testJava("setoperation_small/SetUnionSmall");
	}

	@Test
	public void testRangeBig() throws Exception {
		testJava("range_big/RangeBig");
	}


	@Test
	public void testRangeCardBig() throws Exception {
		testJava("range_big/RangeCardBig");
	}

	@Test
	public void testRangeComplementBig() throws Exception {
		testJava("range_big/RangeComplementBig");
	}

	@Test
	public void testRangeElementOfBig() throws Exception {
		testJava("range_big/RangeElementOfBig");
	}

	@Test
	public void testRangeIntersectionBig() throws Exception {
		testJava("range_big/RangeIntersectionBig");
	}

	@Test
	public void testRangeUnionBig() throws Exception {
		testJava("range_big/RangeUnionBig");
	}

	@Test
	public void testRangeSmall() throws Exception {
		testJava("range_small/RangeSmall");
	}


	@Test
	public void testRangeCardSmall() throws Exception {
		testJava("range_small/RangeCardSmall");
	}

	@Test
	public void testRangeComplementSmall() throws Exception {
		testJava("range_small/RangeComplementSmall");
	}

	@Test
	public void testRangeElementOfSmall() throws Exception {
		testJava("range_small/RangeElementOfSmall");
	}

	@Test
	public void testRangeIntersectionSmall() throws Exception {
		testJava("range_small/RangeIntersectionSmall");
	}

	@Test
	public void testRangeUnionSmall() throws Exception {
		testJava("range_small/RangeUnionSmall");
	}

	@Test
	public void testChoice() throws Exception {
		testJava("Choice");
	}

	@Test
	public void testAssert() throws Exception {
		testJava("Assert");
	}


	@Ignore
	@Test
	public void testCruiseController1() throws Exception {
		testJava("Cruise_finite1");
	}

	@Ignore
	@Test
	public void testCruiseControllerk() throws Exception {
		testJava("Cruise_finite_k");
	}


	private void cleanUp(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
