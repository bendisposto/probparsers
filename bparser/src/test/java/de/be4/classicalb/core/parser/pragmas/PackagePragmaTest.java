package de.be4.classicalb.core.parser.pragmas;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import util.Helpers;
import de.be4.classicalb.core.parser.exceptions.BException;

public class PackagePragmaTest {

	@Test
	public void testImportPackagePragma() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/foo/";
		String file = PATH + "M1.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(3,'M1',[]),[sees(4,[identifier(5,'M2')])]))."));

	}

	@Test
	public void testImportPackageIdentifier() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/foo/";
		String file = PATH + "M11.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(3,'M11',[]),[sees(4,[identifier(5,'M2')])]))."));

	}

	@Test
	public void testInvalidImport() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/foo/";
		String file = PATH + "InvalidImport.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"'Invalid package pragma: foo.*.M2'"));

	}

	@Test
	public void testDuplicateImport() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/foo/";
		String file = PATH + "DuplicateImport.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"Duplicate import statement: foo.bar"));

	}

	@Test
	public void testInvalidPackage() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/";
		String file = PATH + "InvalidPackage1.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("Package declaration \\'foo2\\' does not match the folder structure"));
	}
	
	@Test
	public void testPackageNotFound() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/importPackagePragma/foo/";
		String file = PATH + "PackageNotFound.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("Imported package does not exist"));
	}
	
	@Test
	public void testDuplicateMachineClause() throws Exception {
		final String result = Helpers.fullParsing("src/test/resources/pragmas/packagePragma/project1/Main.mch");
		System.out.println(result);
		//semantic checks (e.g. duplicate clauses) were previously disabled for APackageParseUnit nodes
		assertTrue(result.contains("parse_exception"));
		
	}


}
