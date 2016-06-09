package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import util.AbstractParseMachineTest;
import util.PolySuite;
import util.PolySuite.Config;
import util.PolySuite.Configuration;
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
public class ParseableButProblematicOnWindows extends AbstractParseMachineTest {

	private static final String PATH = "src/test/resources/problematicOnWindows";

	private final File machine;

	public ParseableButProblematicOnWindows(File machine) {
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
	public static Configuration getConfig() {
		final File[] machines = getMachines(PATH);
		return new Configuration() {

			public int size() {
				return machines.length;
			}

			public File getTestValue(int index) {
				return machines[index];
			}

			public String getTestName(int index) {
				return machines[index].getName();
			}
		};
	}

}
