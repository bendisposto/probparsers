package de.be4.classicalb.core.parser.composition;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import util.Helpers;

public class ErrorsTest {



	@Test
	public void testMachineNotFound() throws IOException, BException {
		String PATH = "src/test/resources/composition/errors/";
		String file = PATH + "MachineNotFound.mch";
		Helpers.parseFile2(file);
	}
	
}
