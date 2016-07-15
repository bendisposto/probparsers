package de.be4.classicalb.core.parser.pragmas;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import util.Helpers;
import de.be4.classicalb.core.parser.exceptions.BException;

public class PackagePragmaTest {

	@Test
	public void testImportPragma() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPragma/foo/";
		String file = PATH + "M1.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(3,'M1',[]),[sees(4,[identifier(5,'M2')])]))."));

	}

	@Test
	public void testImportAll() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPragma/foo/";
		String file = PATH + "M11.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(3,'M11',[]),[sees(4,[identifier(5,'M2')])]))."));

	}

	@Test
	public void testInvalidImport() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPragma/foo/";
		String file = PATH + "InvalidImport.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"'Invalid package pragma :\"foo.*.M2\"'"));

	}

	@Test
	public void testDuplicateImport() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPragma/foo/";
		String file = PATH + "DuplicateImport.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"Duplicate import statement: \"foo.bar.*\""));

	}

	@Test
	public void testPackageAndRulesMachine() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPragma/foo/";
		String file = PATH + "RulesMachine.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(3,'RulesMachine',[]),[]))."));

	}

	@Test
	public void testInvalidPackage() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/";
		String file = PATH + "InvalidPackage1.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("Package declaration does not match the folder structure"));
	}

	@Test
	public void testExpectingString() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/";
		String file = PATH + "ExpectingString.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("The package pragma should be followed by a string"));
	}

}
