package de.be4.classicalb.core.parser.prettyprinter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.util.Utils;
import util.Helpers;

public class PrettyMachinePrinterTest {

	@Test
	public void testPrettyPrint() {
		final String testMachine = "MACHINE Test VARIABLES x,y INVARIANT x : INTEGER & y : INTEGER INITIALISATION x:= 1 || y := 2 OPERATIONS foo = skip; bar = skip END";
		final String result1 = Helpers.getPrettyPrint(testMachine);
		final String result2 = Helpers.getPrettyPrint(result1);
		System.out.println(result1);
		assertEquals(result1, result2);
	}

	@Test
	public void testPrettyPrint2() throws FileNotFoundException, IOException {
		final String filePath = "src/test/resources/prettyprinter/PrettyPrinter.mch";
		final String testMachine = Utils.readFile(new File(filePath));
		final String result1 = Helpers.getPrettyPrint(testMachine);
		System.out.println(result1);
		final String result2 = Helpers.getPrettyPrint(result1);
		System.out.println(result1);
		assertEquals(result1, result2);
	}
}
