package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;

public class ExceptionWhenParsingDefFileOnTopLevel {

	private static final String PATH = "src/test/resources/LibraryIO.def";
	private PrintStream out;
	private ByteArrayOutputStream baos;
	private File machine;
	private ParsingBehaviour behaviour;

	@Before
	public void before() throws FileNotFoundException {
		baos = new ByteArrayOutputStream();
		out = new PrintStream(baos);

		behaviour = new ParsingBehaviour();
		behaviour.prologOutput = true;
	}

	@Test
	public void testExceptionCaughtAndPrintedToProlog() throws IOException,
			BException {
		machine = new File(PATH);

		final BParser parser = new BParser(machine.getName());
		int returnValue = parser.fullParsing(machine, behaviour, out, out);

		assertEquals(returnValue, -3);
		assertEquals("exception('null in file: LibraryIO.def').\n",
				baos.toString());
	}
}