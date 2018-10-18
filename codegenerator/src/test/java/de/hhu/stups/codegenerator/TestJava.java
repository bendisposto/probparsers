package de.hhu.stups.codegenerator;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by fabian on 31.05.18.
 */

public class TestJava {

	public static void writeInputToSystem(InputStream inputStream) throws IOException {
		writeInputToOutput(inputStream, System.out);
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
		List<Path> javaFilePaths = codeGenerator.generate(mchPath, GeneratorMode.JAVA, false,true, null);
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

	public void testJava(String machine, String addition) throws Exception {
		Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
				.getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
		CodeGenerator codeGenerator = new CodeGenerator();
		List<Path> javaFilePaths = codeGenerator.generate(mchPath, GeneratorMode.JAVA, false,true, addition);
		Process compileProcess = Runtime.getRuntime()
				.exec("javac -cp btypes_persistent.jar " + String.join(" ", javaFilePaths.stream()
						.map(path -> path.toFile().getAbsoluteFile().toString())
						.collect(Collectors.toSet())));
		compileProcess.waitFor();

		writeInputToSystem(compileProcess.getErrorStream());
		writeInputToOutput(compileProcess.getErrorStream(), compileProcess.getOutputStream());


		Path mainPath = javaFilePaths.get(0);

		Process executeProcess = Runtime.getRuntime()
				.exec("java -cp :btypes_persistent.jar " + mainPath.toFile().getAbsoluteFile().toString().replace(".java", ""));
		executeProcess.waitFor();

		String outPath = mainPath.toFile().getAbsoluteFile().toString().replace(".java", ".out");

		writeInputToSystem(executeProcess.getInputStream());
		writeInputToOutput(executeProcess.getInputStream(), new FileOutputStream(outPath));


		Set<File> classFiles = javaFilePaths.stream()
				.map(path -> new File(path.getParent().toFile(), machine + ".class"))
				.collect(Collectors.toSet());

		//javaFilePaths.forEach(path -> cleanUp(path.toString()));
		//classFiles.forEach(path -> cleanUp(path.getAbsolutePath().toString()));
	}

	@Test
	public void testExample() throws Exception {
		testJava("Example", "ExampleAddition.st");
	}

	@Test
	public void testOperation() throws Exception {
		testJava("Operation", "OperationAddition.st");
	}

	@Test
	public void testLocalDeclaration() throws Exception {
		testJava("LocalDeclaration", "LocalDeclarationAddition.st");
	}

	@Ignore
	@Test
	public void testRefinement() throws Exception {
		// TODO VAR-Node
		testJava("RefinementMachine");
	}

	@Test
	public void testEnumSets() throws Exception {
		testJava("EnumSets", "EnumSetsAddition.st");
	}

	@Test
	public void testNameCollision() throws Exception {
		testJava("NameCollision", "NameCollisionAddition.st");
	}

	@Test
	public void testWhile() throws Exception {
		testJava("While", "WhileAddition.st");
	}

	@Test
	public void testInterval() throws Exception {
		testJava("Interval", "IntervalAddition.st");
	}


	@Test
	public void testPair() throws Exception {
		testJava("Pair", "PairAddition.st");
	}

	@Test
	public void testIfAndPredicates() throws Exception {
		testJava("IfAndPredicates", "IfAndPredicates.st");
	}

	@Test
	public void testDanglingElse() throws Exception {
		testJava("DanglingElse", "DanglingElseAddition.st");
	}

	@Test
	public void testImplies() throws Exception {
		testJava("Implies", "ImpliesAddition.st");
	}

	@Test
	public void testEquivalence() throws Exception {
		testJava("Equivalence", "EquivalenceAddition.st");
	}

	@Test
	public void testBooleanPredicate() throws Exception {
		testJava("BooleanPredicate", "BooleanPredicateAddition.st");
	}

	@Ignore
	@Test
	public void testRecords() throws Exception {
		testJava("Records");
	}

	@Test
	public void testNondeterminism() throws Exception {
		testJava("Nondeterminism", "NondeterminismAddition.st");
	}

	@Test
	public void testMapFunction() throws Exception {
		testJava("MapFunction", "MapFunctionAddition.st");
	}

	@Test
	public void testRelationImage() throws Exception {
		testJava("RelationImage", "RelationImageAddition.st");
	}

	@Test
	public void testEmptySet() throws Exception {
		testJava("EmptySet", "EmptySetAddition.st");
	}

	@Test
	public void testSetUnion() throws Exception {
		testJava("SetUnion", "SetUnionAddition.st");
	}


	@Test
	public void testCounter() throws Exception {
		testJava("Counter", "CounterAddition.st");
	}

	@Test
	public void testBakery0() throws Exception {
		testJava("Bakery0", "Bakery0Addition.st");
	}

	@Ignore
	@Test
	public void testGCD() throws Exception {
		// TODO
		testJava("GCD");
	}

	@Test
	public void testACounter() throws Exception {
		testJava("ACounter", "ACounterAddition.st");
	}

	@Test
	public void testLift() throws Exception {
		testJava("Lift", "LiftAddition.st");
	}

	@Ignore
	@Test
	public void testTravelAgency() throws Exception {
		testJava("TravelAgency");
	}

	@Test
	public void testTrafficLight() throws Exception {
		testJava("TrafficLight", "TrafficLightAddition.st");
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
		testJava("project1/A", "AAdidtion.st");
	}

	@Test
	public void testLiftBenchmarks() throws Exception {
		testJava("liftbenchmarks/LiftExec", "LiftExecAddition.st");
	}


	@Test
	public void testSieveBenchmarks() throws Exception {
		testJava("sievebenchmarks/Sieve", "SieveAddition.st");
	}

	@Test
	public void testTrafficLightBenchmarks() throws Exception {
		testJava("trafficlightbenchmarks/TrafficLightExec", "TrafficLightExecAddition.st");
	}

	@Test
	public void testIncreasingSet() throws Exception {
		testJava("setoperationbenchmarks/IncreasingSet", "IncreasingSetAddition.st");
	}

	@Test
	public void testSetOperation() throws Exception {
		testJava("setoperationbenchmarks/SetOperation", "SetOperationAddition.st");
	}

	@Test
	public void testProject2() throws Exception {
		testJava("project2/MachineA", "MachineAAddition.st");
	}

	@Test
	public void testSieve() throws Exception {
		testJava("Sieve", "SieveAddition.st");
	}

	@Test
	public void testSieveParallel() throws Exception {
		testJava("SieveParallel", "SieveParallelAddition.st");
	}

	@Test
	public void testReset() throws Exception {
		testJava("Reset", "ResetAddition.st");
	}

	@Test
	public void testSwap() throws Exception {
		testJava("Swap", "SwapAddition.st");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testJava("ManyLocalDeclarations", "ManyLocalDeclarationsAddition.st");
	}

	@Test
	public void testManyLocalDeclarations2() throws Exception {
		testJava("ManyLocalDeclarations2", "ManyLocalDeclarations2.st");
	}

	@Test
	public void testPlus() throws Exception {
		testJava("arithmetic/Plus", "PlusAddition.st");
	}

	@Test
	public void testMinus() throws Exception {
		testJava("arithmetic/Minus", "MinusAddition.st");
	}

	@Test
	public void testMultiply() throws Exception {
		testJava("arithmetic/Multiply", "MultiplyAddition.st");
	}

	@Test
	public void testDivide() throws Exception {
		testJava("arithmetic/Divide", "DivideAddition.st");
	}


	@Test
	public void testModulo() throws Exception {
		testJava("arithmetic/Modulo", "ModuloAddition.st");
	}

	@Test
	public void testNegative() throws Exception {
		testJava("arithmetic/Negative", "NegativeAddition.st");
	}

	@Ignore
	@Test
	public void testPositive() throws Exception {
		testJava("arithmetic/Positive");
	}

	@Test
	public void testSmallNumbers() throws Exception {
		testJava("integers/SmallNumbers", "SmallNumbersAddition.st");
	}

	@Test
	public void testBigNumbers() throws Exception {
		testJava("integers/BigNumbers", "BigNumbersAddition.st");
	}

	@Test
	public void testAnd() throws Exception {
		testJava("logical/And", "AndAddition.st");
	}


	@Test
	public void testOr() throws Exception {
		testJava("logical/Or", "OrAddition.st");
	}


	@Test
	public void testImpliesPerformance() throws Exception {
		testJava("logical/Implies", "ImpliesAddition.st");
	}


	@Ignore
	@Test
	public void testNot() throws Exception {
		testJava("logical/Not");
	}

	@Test
	public void testEquivalent() throws Exception {
		testJava("logical/Equivalent", "EquivalentAddition.st");
	}

	@Test
	public void testLess() throws Exception {
		testJava("comparison/Less", "LessAddition.st");
	}

	@Test
	public void testLessEqual() throws Exception {
		testJava("comparison/LessEqual", "LessEqualAddition.st");
	}

	@Test
	public void testGreater() throws Exception {
		testJava("comparison/Greater", "GreaterAddition.st");
	}


	@Test
	public void testGreaterEqual() throws Exception {
		testJava("comparison/GreaterEqual", "GreaterEqualAddition.st");
	}


	@Test
	public void tessEqual() throws Exception {
		testJava("comparison/Equal", "EqualAddition.st");
	}


	@Test
	public void testUnequal() throws Exception {
		testJava("comparison/Unequal", "UnequalAddition.st");
	}

	@Test
	public void testCardBig() throws Exception {
		testJava("setoperation_big/SetCardBig", "SetCardBigAddition.st");
	}

	@Test
	public void testComplementBig() throws Exception {
		testJava("setoperation_big/SetComplementBig", "SetComplementAddition.st");
	}

	@Test
	public void testElementOfBig() throws Exception {
		testJava("setoperation_big/SetElementOfBig", "SetElementOfAddition.st");
	}

	@Test
	public void testIntersectionBig() throws Exception {
		testJava("setoperation_big/SetIntersectionBig", "SetIntersectionBigAddition.st");
	}

	@Test
	public void testIntersectionBig2() throws Exception {
		testJava("setoperation_big/SetIntersectionBig2", "SetIntersectionBig2Addition.st");
	}

	@Test
	public void testUnionBig() throws Exception {
		testJava("setoperation_big/SetUnionBig", "SetUnionBigAddition.st");
	}

	@Test
	public void testCardSmall() throws Exception {
		testJava("setoperation_small/SetCardSmall", "SetCardSmallAddition.st");
	}

	@Test
	public void testComplementSmall() throws Exception {
		testJava("setoperation_small/SetComplementSmall", "SetComülementSmallAddition.st");
	}

	@Test
	public void testElementOfSmall() throws Exception {
		testJava("setoperation_small/SetElementOfSmall", "SetElementOfSmallAddition.st");
	}

	@Test
	public void testIntersectionSmall() throws Exception {
		testJava("setoperation_small/SetIntersectionSmall", "SetIntersectionSmallAddition.st");
	}

	@Test
	public void testUnionSmall() throws Exception {
		testJava("setoperation_small/SetUnionSmall", "SetUnionSmallAddition.st");
	}

	@Test
	public void testRangeBig() throws Exception {
		testJava("range_big/RangeBig", "RangeBigAddition.st");
	}


	@Test
	public void testRangeCardBig() throws Exception {
		testJava("range_big/RangeCardBig", "RangeCardBigAddition.st");
	}

	@Test
	public void testRangeComplementBig() throws Exception {
		testJava("range_big/RangeComplementBig", "RangeComplementBigAddition.st");
	}

	@Test
	public void testRangeElementOfBig() throws Exception {
		testJava("range_big/RangeElementOfBig", "RangeElementOfBigAddition.st");
	}

	@Test
	public void testRangeIntersectionBig() throws Exception {
		testJava("range_big/RangeIntersectionBig", "RangeIntersectionBigAddition.st");
	}

	@Test
	public void testRangeUnionBig() throws Exception {
		testJava("range_big/RangeUnionBig", "RangeUnionBigAddition.st");
	}

	@Test
	public void testRangeSmall() throws Exception {
		testJava("range_small/RangeSmall", "RangeSmallAddition.st");
	}


	@Test
	public void testRangeCardSmall() throws Exception {
		testJava("range_small/RangeCardSmall", "RangeCardSmallAddition.st");
	}

	@Test
	public void testRangeComplementSmall() throws Exception {
		testJava("range_small/RangeComplementSmall", "RangeComplementSmallAddition.st");
	}

	@Test
	public void testRangeElementOfSmall() throws Exception {
		testJava("range_small/RangeElementOfSmall", "RangeElementOfSmallAddition.st");
	}

	@Test
	public void testRangeIntersectionSmall() throws Exception {
		testJava("range_small/RangeIntersectionSmall", "RangeIntersectionSmallAddition.st");
	}

	@Test
	public void testRangeUnionSmall() throws Exception {
		testJava("range_small/RangeUnionSmall", "RangeUnionSmallAddition.st");
	}

	@Test
	public void testChoice() throws Exception {
		testJava("Choice", "ChoiceAddition.st");
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
