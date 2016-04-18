package util;

import static org.junit.Assert.*;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.node.Start;

public class ParserBugTest {
	String s = "MACHINE ParserDefBug\n DEFINITIONS\n D(y) == f\n "
			+ "CONSTANTS f,g\n	PROPERTIES\n f = pred &\n	"
			+ "             g = %x.(x:1..10 | (D(\"hello\")(x)))\n	"
			+ "ASSERTIONS\n	g(1)=0\n END";

	@Test
	public void test() throws Exception {
		BParser parser = new BParser();
		Start ast = parser.parse(s, true);
		ast.apply(new ASTPrinter());
		assertTrue(true);
	}

}
