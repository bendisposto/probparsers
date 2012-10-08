package de.prob.cliparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

public class LtlConsoleParserTest {

	@Test(timeout=1000)
	public void testLtlCommandline_parserlib11_ctl() throws Exception {
		File temp = File.createTempFile("parserlib_11_ctl_", ".ltl");
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		out.write("AG {taken={}");
		out.close();
		String absolutePath = temp.getAbsolutePath();
		LtlConsoleParser
				.main(new String[] { "-ctl", "-lang", "B", absolutePath });
	}

	
	@Test(timeout=1000)
	public void testLtlCommandline_parserlib11_ltl() throws Exception {
		File temp = File.createTempFile("parserlib_11_ltl", ".ltl");
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		out.write("G {taken={}");
		out.close();
		String absolutePath = temp.getAbsolutePath();
		LtlConsoleParser
		.main(new String[] { "-ltl", "-lang", "B", absolutePath });
	}

}
