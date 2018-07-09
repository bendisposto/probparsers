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
		Set<Path> javaFilePaths = codeGenerator.generate(mchPath, GeneratorMode.JAVA, true);
		Process process = Runtime.getRuntime()
				.exec("javac -classpath btypes.jar " + String.join(" ", javaFilePaths.stream()
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
	public void testBooleanPredicate() throws Exception {
		testJava("BooleanPredicate");
	}

	@Test
	public void testRecords() throws Exception {
		testJava("Records");
	}

	@Ignore
	@Test
	public void testNondeterminism() throws Exception {
		testJava("Nondeterminism");
	}

	@Test
	public void testMapFunction() throws Exception {
		testJava("MapFunction");
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
	public void testProject2() throws Exception {
		testJava("project2/MachineA");
	}

	private void cleanUp(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
