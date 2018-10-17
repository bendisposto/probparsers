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

/**
 * Created by fabian on 31.05.18.
 */

public class TestPython {

	public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
		int size;
		byte[] buffer = new byte[1024];
		while ((size = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, size);
	}

	public void testPython(String machine) throws Exception {
		Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
				.getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
		CodeGenerator codeGenerator = new CodeGenerator();
		List<Path> pythonFilePaths = codeGenerator.generate(mchPath, GeneratorMode.PY, false,true, null);

		//pythonFilePaths.forEach(path -> cleanUp(path.toString()));
	}

	@Test
	public void testOperation() throws Exception {
		testPython("Operation");
	}

	@Test
	public void testLocalDeclaration() throws Exception {
		testPython("LocalDeclaration");
	}

	@Ignore
	@Test
	public void testRefinement() throws Exception {
		// TODO VAR-Node
		testPython("RefinementMachine");
	}

	@Test
	public void testEnumSets() throws Exception {
		testPython("EnumSets");
	}

	@Test
	public void testNameCollision() throws Exception {
		testPython("NameCollision");
	}

	@Test
	public void testWhile() throws Exception {
		testPython("While");
	}

	@Test
	public void testInterval() throws Exception {
		testPython("Interval");
	}


	@Test
	public void testPair() throws Exception {
		testPython("Pair");
	}

	@Test
	public void testIfAndPredicates() throws Exception {
		testPython("IfAndPredicates");
	}

	@Test
	public void testImplies() throws Exception {
		testPython("Implies");
	}

	@Test
	public void testEquivalence() throws Exception {
		testPython("Equivalence");
	}

	@Test
	public void testBooleanPredicate() throws Exception {
		testPython("BooleanPredicate");
	}

	@Ignore
	@Test
	public void testRecords() throws Exception {
		testPython("Records");
	}

	@Ignore
	@Test
	public void testNondeterminism() throws Exception {
		testPython("Nondeterminism");
	}

	@Test
	public void testMapFunction() throws Exception {
		testPython("MapFunction");
	}

	@Test
	public void testRelationImage() throws Exception {
		testPython("RelationImage");
	}

	@Test
	public void testEmptySet() throws Exception {
		testPython("EmptySet");
	}

	@Test
	public void testSetUnion() throws Exception {
		testPython("SetUnion");
	}


	@Test
	public void testCounter() throws Exception {
		testPython("Counter");
	}

	@Test
	public void testBakery0() throws Exception {
		testPython("Bakery0");
	}

	@Ignore
	@Test
	public void testGCD() throws Exception {
		// TODO
		testPython("GCD");
	}

	@Test
	public void testACounter() throws Exception {
		testPython("ACounter");
	}

	@Test
	public void testLift() throws Exception {
		testPython("Lift");
	}

	@Ignore
	@Test
	public void testTravelAgency() throws Exception {
		testPython("TravelAgency");
	}

	@Ignore
	@Test
	public void testPhonebook() throws Exception {
		// TODO
		testPython("phonebook");
	}

	@Ignore
	@Test
	public void testPhonebook6() throws Exception {
		// TODO
		testPython("phonebook6");
	}

	@Ignore
	@Test
	public void testSum() throws Exception {
		testPython("Sum");
	}

	@Ignore
	@Test
	public void testRecursion() throws Exception {
		//Correct exception
		testPython("recursion/Sum1");
	}

	@Test
	public void testProject() throws Exception {
		testPython("project1/A");
	}

	@Test
	public void testProject2() throws Exception {
		testPython("project2/MachineA");
	}

	@Test
	public void testSieve() throws Exception {
		testPython("Sieve");
	}

	@Test
	public void testReset() throws Exception {
		testPython("Reset");
	}

	@Test
	public void testManyLocalDeclarations() throws Exception {
		testPython("ManyLocalDeclarations");
	}

	private void cleanUp(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
