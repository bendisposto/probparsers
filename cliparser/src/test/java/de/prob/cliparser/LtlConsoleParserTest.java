package de.prob.cliparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

public class LtlConsoleParserTest {

	@Test(timeout=1000)
	public void testLtlCommandline_parserlib11() throws Exception {
		File temp = File.createTempFile("parserlib_11", ".ltl");
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		out.write("AG {taken={}");
		out.close();
		String absolutePath = temp.getAbsolutePath();
		System.out.println(absolutePath);
		LtlConsoleParser
				.main(new String[] { "-ctl", "-lang", "B", absolutePath });
	}

}
