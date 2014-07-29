package de.be4.classicalb.core.parser;

import static org.junit.Assert.*;

import java.io.PushbackReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;
import de.be4.classicalb.core.parser.parser.Parser;
import de.hhu.stups.sablecc.patch.IToken;

public class Ticket295 {

	// #x. /* comment */ (x>1000 & x<2**10)
	@Test
	public void ticker295() throws Exception {

		// String input = "#FORMULA #x. /* comment */ (x>1000 & x<2**10)";
		String input1 = "#FORMULA #x. /*buh */ (  x>1000 & x<2**10)";
		String input2 = "#FORMULA #x.(/*buh */ x>1000 & x<2**10)";

		DefinitionTypes defTypes1 = new DefinitionTypes();
		final BLexer lexer1 = new BLexer(new PushbackReader(new StringReader(
				input1), 99), defTypes1, input1.length());
		DefinitionTypes defTypes2 = new DefinitionTypes();
		final BLexer lexer2 = new BLexer(new PushbackReader(new StringReader(
				input2), 99), defTypes2, input2.length());

		Parser parser1 = new Parser(lexer1);
		Parser parser2 = new Parser(lexer2);
		final Start rootNode1 = parser1.parse();
		final Start rootNode2 = parser2.parse();

		final String result1 = getTreeAsString(rootNode1);
		final String result2 = getTreeAsString(rootNode2);

		assertNotNull(rootNode1);
		assertNotNull(rootNode2);
		assertEquals(result1, result2);

	}

	private String getTreeAsString(final Start startNode) throws BException {

		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		return string;
	}

}
