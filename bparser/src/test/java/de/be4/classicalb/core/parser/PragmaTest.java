package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;


public class PragmaTest {

	@Test
	public void test() throws LexerException, IOException, BException {
//		pragmaprint("some foo /* comment */ more m7 /*! my pragma is bigger than yours !*/ and even more foo!");
//		pragmaprint("some foo /* comment */ more m8 /*! my pragma is bigger than yours */ and even more foo!");
//		pragmaprint("dawg /*! symbolic a b c */");
//		pragmaparse("MACHINE m1 /*! symbolic x y */ END");
		String input = "MACHINE m0 /*dingo*/ /*! symbolic x y */   VARIABLES x  INVARIANT x:NAT INITIALISATION x:= (2+5) /*! x == 7 */   END";
		pragmaprint(input);
		BParser p = new BParser();
		Start start = p.parse(input, false);
		BParser.printASTasProlog(System.out, p, new File(""), start, false, true);
	}
	
	
	private void pragmaparse(String input) throws BException {
		BParser p = new BParser();
		Start start = p.parse(input, false);
		ASTPrinter pr =  new ASTPrinter(System.out);		
		start.apply(pr);
	}

	private void pragmaprint(String input) throws LexerException, IOException {
		final BLexer lexer = new BLexer(new PushbackReader(new StringReader(input ), 99),
				new DefinitionTypes(), input.length() / 2);
		Token t;
		while (!((t = lexer.next()) instanceof EOF)) {
			System.out.print(t.getClass().getSimpleName()+"("+t.getText()+") ");
		}

		System.out.println();
		
		List<Pragma> pragmas = lexer.getPragmas();
		for (Pragma pragma : pragmas) {
			System.out.println(pragma.toString());
		}
	}
	
}