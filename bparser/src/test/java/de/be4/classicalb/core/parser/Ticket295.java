package de.be4.classicalb.core.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.parser.Parser;
import de.be4.classicalb.core.parser.parser.ParserException;
import de.hhu.stups.sablecc.patch.IToken;

public class Ticket295 {

	// #x. /* comment */ (x>1000 & x<2**10)
	@Test
	@Ignore
	public void test() throws Exception {

	//	String input = "#FORMULA #x. /* comment */ (x>1000 & x<2**10)";
		String input = "#FORMULA #x. /*buh */ (  x>1000 & x<2**10)";

		DefinitionTypes defTypes = new DefinitionTypes();
		final BLexer lexer = new BLexer(new PushbackReader(new StringReader(
				input), 99), defTypes, input.length());
		lexer.setDebugOutput(true);

		Parser parser = new Parser(lexer);
		final Start rootNode = parser.parse();
		final List<IToken> tokenList = lexer.getTokenList();

		for (IToken iToken : tokenList) {
			System.out.println(iToken);
		}

		assertNotNull(rootNode);

	}

}
