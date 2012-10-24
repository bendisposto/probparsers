package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import test.AbstractParseMachineTest;
import test.PolySuite;
import test.PolySuite.Config;
import test.PolySuite.Configuration;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;

/**
 * @author bendisposto
 *
 */
/**
 * @author bendisposto
 * 
 */
@RunWith(PolySuite.class)
public class ParseableButProblematicOnWindowsWindowsLF extends
		AbstractParseMachineTest {

	private static final String PATH = "src/test/resources/problematicOnWindows";

	private final File machine;

	public ParseableButProblematicOnWindowsWindowsLF(File machine) {
		this.machine = machine;
	}

	@Test
	public void testParsable() throws Exception {
		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(new PositionTester());
		assertNotNull(start);
	}

	/**
	 * Visitor that checks if all AST nodes contain the position information.
	 * 
	 * @author bendisposto
	 */
	private static class PositionTester extends DepthFirstAdapter {
		@Override
		public void defaultIn(Node node) {
			if (node instanceof Start)
				return; // start does not have position infos
			assertNotNull(node.getClass().getSimpleName() + " start was null",
					node.getStartPos());
			assertNotNull(node.getClass().getSimpleName() + " end was null",
					node.getEndPos());
		}
	}

	@Config
	public static Configuration getConfig() throws IOException {
		final File[] machines = getMachines(PATH);
		final File[] unixMachines = new File[machines.length];

		for (int i = 0; i < machines.length; i++) {
			unixMachines[i] = File.createTempFile(machines[i].getName()
					.replace(".mch", "_win"), ".mch");

			BufferedReader in = new BufferedReader(new FileReader(machines[i]));
			BufferedWriter out = new BufferedWriter(new FileWriter(
					unixMachines[i]));

			String zeile;
			while ((zeile = in.readLine()) != null) {
				out.write(zeile + "\r\n");
			}

			in.close();
			out.close();
		}

		return new Configuration() {

			public int size() {
				return unixMachines.length;
			}

			public File getTestValue(int index) {
				return unixMachines[index];
			}

			public String getTestName(int index) {
				return unixMachines[index].getName();
			}
		};
	}

}
