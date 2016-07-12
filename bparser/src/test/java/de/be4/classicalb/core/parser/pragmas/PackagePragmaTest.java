package de.be4.classicalb.core.parser.pragmas;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import util.Helpers;
import de.be4.classicalb.core.parser.exceptions.BException;

public class PackagePragmaTest {


	@Test
	public void testPackagePragma() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/foo/";
		String file = PATH + "FooM1.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("machine(abstract_machine(9,machine(10),machine_header(12,'BarM1',[]),[]))."));
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(4,'FooM1',[]),[sees(5,[identifier(7,'BarM1')])]))."));
	}

	@Test
	public void testPackagePragma2() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/foo/bar/";
		String file = PATH + "BarM2.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains(
				"machine(abstract_machine(1,machine(2),machine_header(4,'BarM2',[]),[sees(5,[identifier(7,'FooM1')])]))."));
	}

	@Test
	public void testInvalidPackage() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/foo/";
		String file = PATH + "InvalidPackage1.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("Package declaration does not match the folder structure"));
	}

	@Test
	public void testFileDoesNotExist() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/foo/";
		String file = PATH + "ReferencedFileDoesNotExist.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("foo/bar/bazz/M1.mch (No such file or directory)"));
	}

	@Test
	public void testExpectingString() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/packagePragma/foo/";
		String file = PATH + "ExpectingString.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("The package pragma should be followed by a string"));
	}

}
