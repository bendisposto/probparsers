import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.antlr.Antlr4Parser;
import de.be4.classicalb.core.parser.antlr.Util;

public class PerformanceTest {


	@Test
	public void bigFile1() throws FileNotFoundException, IOException {
		String s = Util.readFile(new File("/Users/hansen/git/prob_examples/public_examples/B/PerformanceTests/SATLIB/uf50-02.mch"));
		test(s);

	}
	
	@Test
	public void bigFile2() throws FileNotFoundException, IOException {
		String s = Util.readFile(new File("/Users/hansen/git/prob_examples/public_examples/B/PerformanceTests/SATLIB/sudoku.mch"));
		test(s);

	}
	
	
	private static void test(String input) {
		Antlr4Parser.createSableCCAst(input);
	}

	private static void fail(String input) {
		try {
			Antlr4Parser.parse(input);
		} catch (Exception e) {
			return;
		}
		throw new RuntimeException();
	}
}
