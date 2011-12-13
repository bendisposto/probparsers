package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Token;


public class LexerTest {

	@Test
	public void test() throws LexerException, IOException {
		pragmaprint("some foo /* comment */ more foo /*! my pragma is bigger than yours !*/ and even more foo!");
		pragmaprint("some foo /* comment */ more foo /*! my pragma is bigger than yours */ and even more foo!");

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
