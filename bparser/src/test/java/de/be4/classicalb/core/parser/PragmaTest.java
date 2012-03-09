package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;


public class PragmaTest {

	@Test
	public void test() throws LexerException, IOException, BException {
		pragmaparse("MACHINE m1 /*! symbolic x y */ END");
		pragmaparse("MACHINE m0 /*dingo*/ /*! symbolic x y */ \n  VARIABLES x  INVARIANT x:NAT INITIALISATION x:= (2+5) /*! x == 7 */ \nEND");
	}
	
	
	private void pragmaparse(String input) throws BException {
		BParser p = new BParser();
		Start start = p.parse(input, false);
		ASTPrinter pr =  new ASTPrinter(System.out);		
		start.apply(pr);
		List<Pragma> pragmas = p.getPragmas();
		for (Pragma pragma : pragmas) {
			System.out.println(pragma);
		}
		
	}


}