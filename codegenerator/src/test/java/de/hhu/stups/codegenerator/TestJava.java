package de.hhu.stups.codegenerator;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
 * Created by fabian on 31.05.18.
 */

public class TestJava {

	public static void writeInputToSystem(InputStream inputStream) throws IOException {
		writeInputToOutput(inputStream, System.out);
	}

	private static String streamToString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString();
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

	public void testJava(String machinePath, String machineName, String addition) throws Exception {
		testJava(machinePath);
		/*Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
				.getResource("de/hhu/stups/codegenerator/" + machinePath + ".mch").toURI());
		CodeGenerator codeGenerator = new CodeGenerator();
		List<Path> javaFilePaths = codeGenerator.generate(mchPath, GeneratorMode.JAVA, false,true, addition);
		Runtime runtime = Runtime.getRuntime();
		Process compileProcess = runtime.exec("javac -cp btypes_persistent.jar " +
				String.join(" ", javaFilePaths.stream()
						.map(path -> path.toFile().getAbsoluteFile().toString())
						.collect(Collectors.toSet())));
		compileProcess.waitFor();

		writeInputToSystem(compileProcess.getErrorStream());
		writeInputToOutput(compileProcess.getErrorStream(), compileProcess.getOutputStream());

		Path mainPath = javaFilePaths.get(0);

		List<String> cmds = new ArrayList<>();
		cmds.add("java");
		cmds.add("-cp");
		cmds.add(":btypes_persistent.jar");
		cmds.add(machineName);

		ProcessBuilder executeProcessBuilder = new ProcessBuilder().command(cmds).directory(mainPath.getParent().toFile());
		Process executeProcess = executeProcessBuilder.start();
		executeProcess.waitFor();
		Path outPath = mainPath.toFile().getAbsoluteFile().toPath();

		writeInputToSystem(executeProcess.getErrorStream());
		//writeInputToOutput(executeProcess.getInputStream(), new FileOutputStream(mainPath.toFile().getAbsoluteFile().toString().replaceAll(".java", ".abc")));

		String result = streamToString(executeProcess.getInputStream());
		String expectedOutput = streamToString(new FileInputStream(outPath.toString()));

		//assertEquals(result, expectedOutput);

		Set<File> classFiles = javaFilePaths.stream()
				.map(path -> new File(path.getParent().toFile(), machinePath + ".class"))
				.collect(Collectors.toSet());

		//javaFilePaths.forEach(path -> cleanUp(path.toString()));
		//classFiles.forEach(path -> cleanUp(path.getAbsolutePath().toString()));*/
	}

	@Test
	public void testExample() throws Exception {
		testJava("Example", "Example", "ExampleAddition.st");
	}

	@Test
	public void testOperation() throws Exception {
		testJava("Operation", "Operation", "OperationAddition.st");
	}

	@Test
	public void testLocalDeclaration() throws Exception {
		testJava("LocalDeclaration", "LocalDeclaration", "LocalDeclarationAddition.st");
	}

	@Ignore
	@Test
	public void testRefinement() throws Exception {
		// TODO VAR-Node
		testJava("RefinementMachine");
	}

	@Test
	public void testEnumSets() throws Exception {
		testJava("EnumSets", "EnumSets", "EnumSetsAddition.st");
	}

	@Test
	public void testNameCollision() throws Exception {
		testJava("NameCollision", "NameCollision", "NameCollisionAddition.st");
	}

	@Test
	public void testWhile() throws Exception {
		testJava("While", "While", "WhileAddition.st");
	}

	@Test
	public void testInterval() throws Exception {
		testJava("Interval", "Interval", "IntervalAddition.st");
	}


	@Test
	public void testPair() throws Exception {
		testJava("Pair", "Pair", "PairAddition.st");
	}

	@Test
	public void testIfAndPredicates() throws Exception {
		testJava("IfAndPredicates", "IfAndPredicates", "IfAndPredicates.st");
	}

	@Test
	public void testDanglingElse() throws Exception {
		testJava("DanglingElse", "DanglingElse", "DanglingElseAddition.st");
	}

	@Test
	public void testImplies() throws Exception {
		testJava("Implies", "Implies", "ImpliesAddition.st");
	}

	@Test
	public void testEquivalence() throws Exception {
		testJava("Equivalence", "Equivalence", "EquivalenceAddition.st");
	}

	@Test
	public void testBooleanPredicate() throws Exception {
		testJava("BooleanPredicate", "BooleanPredicate", "BooleanPredicateAddition.st");
	}

	@Ignore
	@Test
	public void testRecords() throws Exception {
		testJava("Records");
	}

	@Test
	public void testNondeterminism() throws Exception {
		testJava("Nondeterminism", "Nondeterminism", "NondeterminismAddition.st");
	}

	@Test
	public void testMapFunction() throws Exception {
		testJava("MapFunction", "MapFunction", "MapFunctionAddition.st");
	}

	@Test
	public void testRelationImage() throws Exception {
		testJava("RelationImage", "RelationImage", "RelationImageAddition.st");
	}

	@Test
	public void testEmptySet() throws Exception {
		testJava("EmptySet", "EmptySet", "EmptySetAddition.st");
	}

	@Test
	public void testSetUnion() throws Exception {
		testJava("SetUnion", "SetUnion", "SetUnionAddition.st");
	}


	@Test
	public void testCounter() throws Exception {
		testJava("Counter", "Counter", "CounterAddition.st");
	}

	@Test
	public void testBakery0() throws Exception {
		testJava("Bakery0", "Bakery0", "Bakery0Addition.st");
	}

	@Ignore
	@Test
	public void testGCD() throws Exception {
		// TODO
		testJava("GCD");
	}

	@Test
	public void testACounter() throws Exception {
		testJava("ACounter", "ACounter", "ACounterAddition.st");
	}

	@Test
	public void testLift() throws Exception {
		testJava("Lift", "Lift", "LiftAddition.st");
	}

	@Ignore
	@Test
	public void testTravelAgency() throws Exception {
		testJava("TravelAgency");
	}

	@Test
	public void testTrafficLight() throws Exception {
		testJava("TrafficLight", "TrafficLight", "TrafficLightAddition.st");
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
		testJava("project1/A", "A", "AAdidtion.st");
	}

	@Test
	public void testLiftBenchmarks() throws Exception {
		testJava("liftbenchmarks/LiftExec", "LiftExec", "LiftExecAddition.st");
	}


	@Test
	public void testSieveBenchmarks() throws Exception {
		testJava("sievebenchmarks/Sieve", "Sieve", "SieveAddition.st");
	}

	@Test
	public void testTrafficLightBenchmarks() throws Exception {
		testJava("trafficlightbenchmarks/TrafficLightExec", "TrafficLightExec", "TrafficLightExecAddition.st");
	}

	@Test
	public void testIncreasingSet() throws Exception {
		testJava("setoperationbenchmarks/IncreasingSet", "IncreasingSet", "IncreasingSetAddition.st");
	}

	@Test
	public void testSetOperation() throws Exception {
		testJava("setoperationbenchmarks/SetOperation", "SetOperation", "SetOperationAddition.st");
	}

	@Test
	public void testProject2() throws Exception {
		testJava("project2/MachineA", "MachineA", "MachineAAddition.st");
	}

	@Test
	public void testSieve() throws Exception {
		testJava("Sieve", "Sieve", "SieveAddition.st");
	}

	@Test
	public void testSieveParallel() throws Exception {
		testJava("SieveParallel", "SieveParallel", "SieveParallelAddition.st");
	}

	@Test
	public void testReset() throws Exception {
		testJava("Reset", "Reset", "ResetAddition.st");
	}

	@Test
	public void testSwap() throws Exception {
		testJava("Swap", "Swap", "SwapAddition.st");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testJava("ManyLocalDeclarations", "ManyLocalDeclarations", "ManyLocalDeclarationsAddition.st");
	}

	@Test
	public void testManyLocalDeclarations2() throws Exception {
		testJava("ManyLocalDeclarations2", "ManyLocalDeclarations2", "ManyLocalDeclarations2.st");
	}

	@Test
	public void testPlus() throws Exception {
		testJava("arithmetic/Plus", "Plus", "PlusAddition.st");
	}

	@Test
	public void testMinus() throws Exception {
		testJava("arithmetic/Minus", "Minus", "MinusAddition.st");
	}

	@Test
	public void testMultiply() throws Exception {
		testJava("arithmetic/Multiply", "Multiply", "MultiplyAddition.st");
	}

	@Test
	public void testDivide() throws Exception {
		testJava("arithmetic/Divide", "Divide", "DivideAddition.st");
	}


	@Test
	public void testModulo() throws Exception {
		testJava("arithmetic/Modulo", "Modulo", "ModuloAddition.st");
	}

	@Test
	public void testNegative() throws Exception {
		testJava("arithmetic/Negative", "Negative", "NegativeAddition.st");
	}

	@Ignore
	@Test
	public void testPositive() throws Exception {
		testJava("arithmetic/Positive");
	}

	/*@Test
	public void testSmallNumbers() throws Exception {
		testJava("integers/SmallNumbers", "SmallNumbersAddition.st");
	}

	@Test
	public void testBigNumbers() throws Exception {
		testJava("integers/BigNumbers", "BigNumbersAddition.st");
	}*/

	@Test
	public void testAnd() throws Exception {
		testJava("logical/And", "And", "AndAddition.st");
	}


	@Test
	public void testOr() throws Exception {
		testJava("logical/Or", "Or", "OrAddition.st");
	}


	@Test
	public void testImpliesPerformance() throws Exception {
		testJava("logical/Implies", "Implies", "ImpliesAddition.st");
	}

	@Test
	public void testNot() throws Exception {
		testJava("logical/Not");
	}

	@Test
	public void testEquivalent() throws Exception {
		testJava("logical/Equivalent", "Equivalent", "EquivalentAddition.st");
	}

	@Test
	public void testBooleanExpression() throws Exception {
		testJava("logical/BooleanExpression");
	}

	@Test
	public void testBooleanConstant() throws Exception {
		testJava("logical/BooleanConstant");
	}

	@Test
	public void testLess() throws Exception {
		testJava("comparison/Less", "Less", "LessAddition.st");
	}

	@Test
	public void testLessEqual() throws Exception {
		testJava("comparison/LessEqual", "LessEqual", "LessEqualAddition.st");
	}

	@Test
	public void testGreater() throws Exception {
		testJava("comparison/Greater", "Greater", "GreaterAddition.st");
	}


	@Test
	public void testGreaterEqual() throws Exception {
		testJava("comparison/GreaterEqual", "GreaterEqual", "GreaterEqualAddition.st");
	}


	@Test
	public void tessEqual() throws Exception {
		testJava("comparison/Equal", "Equal", "EqualAddition.st");
	}


	@Test
	public void testUnequal() throws Exception {
		testJava("comparison/Unequal", "Unequal", "UnequalAddition.st");
	}

	@Test
	public void testCardBig() throws Exception {
		testJava("setoperation_big/SetCardBig", "SetCardBig", "SetCardBigAddition.st");
	}

	@Test
	public void testComplementBig() throws Exception {
		testJava("setoperation_big/SetComplementBig", "SetComplementBig", "SetComplementAddition.st");
	}

	@Test
	public void testElementOfBig() throws Exception {
		testJava("setoperation_big/SetElementOfBig", "SetElementOfBig", "SetElementOfAddition.st");
	}

	@Test
	public void testIntersectionBig() throws Exception {
		testJava("setoperation_big/SetIntersectionBig", "SetIntersectionBig", "SetIntersectionBigAddition.st");
	}

	@Test
	public void testIntersectionBig2() throws Exception {
		testJava("setoperation_big/SetIntersectionBig2", "SetIntersectionBig2", "SetIntersectionBig2Addition.st");
	}

	@Test
	public void testUnionBig() throws Exception {
		testJava("setoperation_big/SetUnionBig", "SetUnionBig", "SetUnionBigAddition.st");
	}

	@Test
	public void testCardSmall() throws Exception {
		testJava("setoperation_small/SetCardSmall", "SetCardSmall", "SetCardSmallAddition.st");
	}

	@Test
	public void testComplementSmall() throws Exception {
		testJava("setoperation_small/SetComplementSmall", "SetComplementSmall", "SetComülementSmallAddition.st");
	}

	@Test
	public void testElementOfSmall() throws Exception {
		testJava("setoperation_small/SetElementOfSmall", "SetElementOfSmall", "SetElementOfSmallAddition.st");
	}

	@Test
	public void testIntersectionSmall() throws Exception {
		testJava("setoperation_small/SetIntersectionSmall", "SetIntersectionSmall", "SetIntersectionSmallAddition.st");
	}

	@Test
	public void testUnionSmall() throws Exception {
		testJava("setoperation_small/SetUnionSmall", "SetUnionSmall", "SetUnionSmallAddition.st");
	}

	@Test
	public void testRangeBig() throws Exception {
		testJava("range_big/RangeBig", "RangeBig", "RangeBigAddition.st");
	}


	@Test
	public void testRangeCardBig() throws Exception {
		testJava("range_big/RangeCardBig", "RangeCardBig", "RangeCardBigAddition.st");
	}

	@Test
	public void testRangeComplementBig() throws Exception {
		testJava("range_big/RangeComplementBig", "RangeComplementBig", "RangeComplementBigAddition.st");
	}

	@Test
	public void testRangeElementOfBig() throws Exception {
		testJava("range_big/RangeElementOfBig", "RangeElementOfBig", "RangeElementOfBigAddition.st");
	}

	@Test
	public void testRangeIntersectionBig() throws Exception {
		testJava("range_big/RangeIntersectionBig", "RangeIntersectionBig", "RangeIntersectionBigAddition.st");
	}

	@Test
	public void testRangeUnionBig() throws Exception {
		testJava("range_big/RangeUnionBig", "RangeUnionBig", "RangeUnionBigAddition.st");
	}

	@Test
	public void testRangeSmall() throws Exception {
		testJava("range_small/RangeSmall", "RangeSmall", "RangeSmallAddition.st");
	}


	@Test
	public void testRangeCardSmall() throws Exception {
		testJava("range_small/RangeCardSmall", "RangeCardSmall", "RangeCardSmallAddition.st");
	}

	@Test
	public void testRangeComplementSmall() throws Exception {
		testJava("range_small/RangeComplementSmall", "RangeComplementSmall", "RangeComplementSmallAddition.st");
	}

	@Test
	public void testRangeElementOfSmall() throws Exception {
		testJava("range_small/RangeElementOfSmall", "RangeElementOfSmall", "RangeElementOfSmallAddition.st");
	}

	@Test
	public void testRangeIntersectionSmall() throws Exception {
		testJava("range_small/RangeIntersectionSmall", "RangeIntersectionSmall", "RangeIntersectionSmallAddition.st");
	}

	@Test
	public void testRangeUnionSmall() throws Exception {
		testJava("range_small/RangeUnionSmall", "RangeUnionSmall", "RangeUnionSmallAddition.st");
	}

	@Test
	public void testChoice() throws Exception {
		testJava("Choice", "Choice", "ChoiceAddition.st");
	}

	@Test
	public void testAssert() throws Exception {
		testJava("Assert");
	}

	@Test
	public void testCruiseController1() throws Exception {
		testJava("Cruise_finite1");
	}

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
