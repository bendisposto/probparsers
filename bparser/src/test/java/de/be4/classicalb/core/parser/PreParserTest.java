package de.be4.classicalb.core.parser;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.preparser.lexer.LexerException;
import de.be4.classicalb.core.preparser.parser.ParserException;

public class PreParserTest {

	@Test
	public void testSimple1() throws Exception {
		final String testMachine = "DEFINITIONS blub == skip";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testSimple2() throws Exception {
		final String testMachine = "DEFINITIONS blub == skip;bla==blub";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testParameters1() throws Exception {
		final String testMachine = "DEFINITIONS blub(a,b) == skip";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testParameters2() throws Exception {
		final String testMachine = "DEFINITIONS blub(a,b) == skip;\n\tbla(x,y) == x+y";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testComplete1() throws Exception {
		final String testMachine = "MACHINE TestMachine VARIABLES xx DEFINITIONS blub == skip;bla(x,y)==blub END";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testComplete2() throws Exception {
		final String testMachine = "MACHINE TestMachine VARIABLES xx DEFINITIONS blub == skip;bla==blub INVARIANT xx : NAT END";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testComments1() throws Exception {
		final String testMachine = "/* comment1 */bla /* comment2 */ DEFINITIONS/* comment3 */ blub/* comment4 */ ==/* comment5 */ skip/* comment6 */";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testComments2() throws Exception {
		final String testMachine = "/* comment1 * / */bla /* //comment2 */ **DEFINITIONS/* comment3 */ blub/* comment4 */ ==/* comment5 */ skip/* ;comment6 */";
		// final String result =
		getTreeAsString(testMachine);
	}

	@Test
	public void testComments3() throws Exception {
		final String testMachine = "/* comment1 * / */bla /* //comment2 */ **DEFINITIONS/* comment3 */ blub/* comment4 */ ==/* comment5 */ skip/* comment6 */; bla == x:=5";
		// final String result =
		getTreeAsString(testMachine);
	}

	private String getTreeAsString(final String testMachine)
			throws ParserException, LexerException, IOException {
		// System.out.println("Parsing: \"" + testMachine + "\":");
		// final Parser parser = new Parser(new PreLexer(new PushbackReader(
		// new InputStreamReader(new ByteArrayInputStream(testMachine
		// .getBytes())), 99)));
		// final Start startNode = parser.parse();
		// System.out.println();

		// startNode.apply(new PreParserASTPrinter());
		final String string = "";
		// System.out.println(string);
		// System.out.println();
		return string;
	}

}
