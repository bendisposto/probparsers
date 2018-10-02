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
		Set<Path> cppFilePaths = codeGenerator.generate(mchPath, GeneratorMode.CPP, true);
		cppFilePaths.forEach(path -> {
			try {
				Process process = Runtime.getRuntime()
                        .exec("g++ -c " + path.toFile().getAbsoluteFile().toString());
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
	public void testProject2() throws Exception {
		testCpp("project2/MachineA");
	}

	@Test
	public void testSieve() throws Exception {
		testCpp("Sieve");
	}

	@Test
	public void testReset() throws Exception {
		testCpp("Reset");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testCpp("ManyLocalDeclarations");
	}

	private void cleanUp(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
