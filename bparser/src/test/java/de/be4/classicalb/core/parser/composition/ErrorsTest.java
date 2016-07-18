package de.be4.classicalb.core.parser.composition;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import util.Helpers;

public class ErrorsTest {

	@Test
	public void testMachineNotFound() throws IOException, BException {
		String PATH = "src/test/resources/composition/errors/";
		String file = PATH + "MachineNotFound.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"'Machine not found: \\'MachineDoesNotExist\\' in \\'MachineNotFound.mch\\''"));
	}
	
	@Test
	public void testMachineNameDoesNotMachtFileName() throws IOException, BException {
		String PATH = "src/test/resources/composition/errors/";
		String file = PATH + "MachineNameDoesNotMatchFileName.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains(
				"'Machine name does not match the file name: \\'Foo\\' vs \\'MachineNameDoesNotMatchFileName\\''"));
	}

}
