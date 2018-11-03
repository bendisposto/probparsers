package de.hhu.stups.codegenerator;


import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by fabian on 30.08.18.
 */

public class TestCpp {

	public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
		int size;
		byte[] buffer = new byte[1024];
		while ((size = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, size);
	}

	public static void writeInputToSystem(InputStream inputStream) throws IOException {
		writeInputToOutput(inputStream, System.err);
	}


	public void testCpp(String machine) throws Exception {
		Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
				.getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
		CodeGenerator codeGenerator = new CodeGenerator();
		List<Path> cppFilePaths = codeGenerator.generate(mchPath, GeneratorMode.CPP, false, true, null);
		cppFilePaths.forEach(path -> {
			try {
				Process process = Runtime.getRuntime()
                        .exec("g++ -std=c++14 -c " + path.toFile().getAbsoluteFile().toString());
				writeInputToSystem(process.getErrorStream());
				writeInputToOutput(process.getErrorStream(), process.getOutputStream());
				process.waitFor();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});

		Set<File> oFiles = cppFilePaths.stream()
				.map(path -> new File(path.getParent().toFile(), machine + ".o"))
				.collect(Collectors.toSet());

		//javaFilePaths.forEach(path -> cleanUp(path.toString()));
		//classFiles.forEach(path -> cleanUp(path.getAbsolutePath().toString()));
		//cFilePaths.forEach(path -> cleanUp(path.toString()));
	}

	@Test
	public void testExample() throws Exception {
		testCpp("Example");
	}

	@Test
	public void testOperation() throws Exception {
		testCpp("Operation");
	}

	@Test
	public void testLocalDeclaration() throws Exception {
		testCpp("LocalDeclaration");
	}

	@Ignore
	@Test
	public void testRefinement() throws Exception {
		// TODO VAR-Node
		testCpp("RefinementMachine");
	}

	@Test
	public void testEnumSets() throws Exception {
		testCpp("EnumSets");
	}

	@Test
	public void testNameCollision() throws Exception {
		testCpp("NameCollision");
	}

	@Test
	public void testWhile() throws Exception {
		testCpp("While");
	}

	@Test
	public void testInterval() throws Exception {
		testCpp("Interval");
	}


	@Test
	public void testPair() throws Exception {
		testCpp("Pair");
	}

	@Test
	public void testIfAndPredicates() throws Exception {
		testCpp("IfAndPredicates");
	}

	@Test
	public void testImplies() throws Exception {
		testCpp("Implies");
	}

	@Test
	public void testEquivalence() throws Exception {
		testCpp("Equivalence");
	}

	@Test
	public void testBooleanPredicate() throws Exception {
		testCpp("BooleanPredicate");
	}

	@Ignore
	@Test
	public void testRecords() throws Exception {
		testCpp("Records");
	}

	@Ignore
	@Test
	public void testNondeterminism() throws Exception {
		testCpp("Nondeterminism");
	}

	@Test
	public void testMapFunction() throws Exception {
		testCpp("MapFunction");
	}

	@Test
	public void testRelationImage() throws Exception {
		testCpp("RelationImage");
	}

	@Test
	public void testEmptySet() throws Exception {
		testCpp("EmptySet");
	}

	@Test
	public void testSetUnion() throws Exception {
		testCpp("SetUnion");
	}


	@Test
	public void testCounter() throws Exception {
		testCpp("Counter");
	}

	@Test
	public void testBakery0() throws Exception {
		testCpp("Bakery0");
	}

	@Ignore
	@Test
	public void testGCD() throws Exception {
		// TODO
		testCpp("GCD");
	}

	@Test
	public void testACounter() throws Exception {
		testCpp("ACounter");
	}

	@Test
	public void testLift() throws Exception {
		testCpp("Lift");
	}

	@Ignore
	@Test
	public void testTravelAgency() throws Exception {
		testCpp("TravelAgency");
	}

	@Ignore
	@Test
	public void testPhonebook() throws Exception {
		// TODO
		testCpp("phonebook");
	}

	@Ignore
	@Test
	public void testPhonebook6() throws Exception {
		// TODO
		testCpp("phonebook6");
	}

	@Ignore
	@Test
	public void testSum() throws Exception {
		testCpp("Sum");
	}

	@Ignore
	@Test
	public void testRecursion() throws Exception {
		//Correct exception
		testCpp("recursion/Sum1");
	}

	@Test
	public void testProject() throws Exception {
		testCpp("project1/A");
	}

	@Test
	public void testLiftBenchmarks() throws Exception {
		testCpp("liftbenchmarks/LiftExec");
	}


	@Test
	public void testSieveBenchmarks() throws Exception {
		testCpp("sievebenchmarks/Sieve");
	}

	@Test
	public void testTrafficLightBenchmarks() throws Exception {
		testCpp("trafficlightbenchmarks/TrafficLightExec");
	}

	@Test
	public void testIncreasingSet() throws Exception {
		testCpp("setoperationbenchmarks/IncreasingSet");
	}

	@Test
	public void testSetOperation() throws Exception {
		testCpp("setoperationbenchmarks/SetOperation");
	}


	@Test
	public void testProject2() throws Exception {
		testCpp("project2/MachineA");
	}

	@Test
	public void testSieve() throws Exception {
		testCpp("Sieve");
	}

	@Test
	public void testSieveParallel() throws Exception {
		testCpp("SieveParallel");
	}

	@Test
	public void testSwap() throws Exception {
		testCpp("Swap");
	}

	@Test
	public void testReset() throws Exception {
		testCpp("Reset");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testCpp("ManyLocalDeclarations");
	}

	@Test
	public void testManyLocalDeclarations2() throws Exception {
		testCpp("ManyLocalDeclarations2");
	}

	@Test
	public void testPlus() throws Exception {
		testCpp("arithmetic/Plus");
	}

	@Test
	public void testMinus() throws Exception {
		testCpp("arithmetic/Minus");
	}

	@Test
	public void testMultiply() throws Exception {
		testCpp("arithmetic/Multiply");
	}

	@Test
	public void testDivide() throws Exception {
		testCpp("arithmetic/Divide");
	}


	@Test
	public void testModulo() throws Exception {
		testCpp("arithmetic/Modulo");
	}

	@Test
	public void testNegative() throws Exception {
		testCpp("arithmetic/Negative");
	}

	@Ignore
	@Test
	public void testPositive() throws Exception {
		testCpp("arithmetic/Positive");
	}

	@Test
	public void testSmallNumbers() throws Exception {
		testCpp("integers/SmallNumbers");
	}

	@Test
	public void testBigNumbers() throws Exception {
		testCpp("integers/BigNumbers");
	}

	@Test
	public void testAnd() throws Exception {
		testCpp("logical/And");
	}


	@Test
	public void testOr() throws Exception {
		testCpp("logical/Or");
	}


	@Test
	public void testImpliesPerformance() throws Exception {
		testCpp("logical/Implies");
	}


	@Ignore
	@Test
	public void testNot() throws Exception {
		testCpp("logical/Not");
	}

	@Test
	public void testEquivalent() throws Exception {
		testCpp("logical/Equivalent");
	}

	@Test
	public void testLess() throws Exception {
		testCpp("comparison/Less");
	}

	@Test
	public void testLessEqual() throws Exception {
		testCpp("comparison/LessEqual");
	}

	@Test
	public void testGreater() throws Exception {
		testCpp("comparison/Greater");
	}


	@Test
	public void testGreaterEqual() throws Exception {
		testCpp("comparison/GreaterEqual");
	}


	@Test
	public void tessEqual() throws Exception {
		testCpp("comparison/Equal");
	}


	@Test
	public void testUnequal() throws Exception {
		testCpp("comparison/Unequal");
	}

	@Test
	public void testCardBig() throws Exception {
		testCpp("setoperation_big/SetCardBig");
	}

	@Test
	public void testComplementBig() throws Exception {
		testCpp("setoperation_big/SetComplementBig");
	}

	@Test
	public void testElementOfBig() throws Exception {
		testCpp("setoperation_big/SetElementOfBig");
	}

	@Test
	public void testIntersectionBig() throws Exception {
		testCpp("setoperation_big/SetIntersectionBig");
	}

	@Test
	public void testUnionBig() throws Exception {
		testCpp("setoperation_big/SetUnionBig");
	}

	@Test
	public void testCardSmall() throws Exception {
		testCpp("setoperation_small/SetCardSmall");
	}

	@Test
	public void testComplementSmall() throws Exception {
		testCpp("setoperation_small/SetComplementSmall");
	}

	@Test
	public void testElementOfSmall() throws Exception {
		testCpp("setoperation_small/SetElementOfSmall");
	}

	@Test
	public void testIntersectionSmall() throws Exception {
		testCpp("setoperation_small/SetIntersectionSmall");
	}

	@Test
	public void testUnionSmall() throws Exception {
		testCpp("setoperation_small/SetUnionSmall");
	}

	@Test
	public void testRangeBig() throws Exception {
		testCpp("range_big/RangeBig");
	}


	@Test
	public void testRangeCardBig() throws Exception {
		testCpp("range_big/RangeCardBig");
	}

	@Test
	public void testRangeComplementBig() throws Exception {
		testCpp("range_big/RangeComplementBig");
	}

	@Test
	public void testRangeElementOfBig() throws Exception {
		testCpp("range_big/RangeElementOfBig");
	}

	@Test
	public void testRangeIntersectionBig() throws Exception {
		testCpp("range_big/RangeIntersectionBig");
	}

	@Test
	public void testRangeUnionBig() throws Exception {
		testCpp("range_big/RangeUnionBig");
	}

	@Test
	public void testRangeSmall() throws Exception {
		testCpp("range_small/RangeSmall");
	}


	@Test
	public void testRangeCardSmall() throws Exception {
		testCpp("range_small/RangeCardSmall");
	}

	@Test
	public void testRangeComplementSmall() throws Exception {
		testCpp("range_small/RangeComplementSmall");
	}

	@Test
	public void testRangeElementOfSmall() throws Exception {
		testCpp("range_small/RangeElementOfSmall");
	}

	@Test
	public void testRangeIntersectionSmall() throws Exception {
		testCpp("range_small/RangeIntersectionSmall");
	}

	@Test
	public void testRangeUnionSmall() throws Exception {
		testCpp("range_small/RangeUnionSmall");
	}

	@Test
	public void testTrafficLight() throws Exception {
		testCpp("TrafficLight");
	}

	@Test
	public void testChoice() throws Exception {
		testCpp("Choice");
	}

	@Test
	public void testAssert() throws Exception {
		testCpp("Assert");
	}

	@Test
	public void testCruiseController1() throws Exception {
		testCpp("Cruise_finite1");
	}

	@Test
	public void testCruiseControllerk() throws Exception {
		testCpp("Cruise_finite_k");
	}

	private void cleanUp(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
