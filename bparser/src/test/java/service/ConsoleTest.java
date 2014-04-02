package service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.PrologTermStringOutput;

public class ConsoleTest {

	@Test
	public void testFormulaOutput() throws BException {
		Start start = BParser.parse("#FORMULA 1+1");
		PrologTermStringOutput strOutput = new PrologTermStringOutput();
		ASTProlog printer = new ASTProlog(strOutput, null);
		start.apply(printer);
		strOutput.fullstop();

		// A Friendly Reminder: strOutput includes a newline!
		assertEquals(strOutput.toString(),
				"add(none,integer(none,1),integer(none,1)).\n");
	}

}
