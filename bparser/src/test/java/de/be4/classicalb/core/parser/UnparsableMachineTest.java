package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import util.AbstractParseMachineTest;
import util.PolySuite;
import util.PolySuite.Config;
import util.PolySuite.Configuration;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

@RunWith(PolySuite.class)
public class UnparsableMachineTest extends AbstractParseMachineTest {

	private static final String PATH = "src/test/resources/unparsable";

	private final File machine;

	public UnparsableMachineTest(File machine) {
		this.machine = machine;
	}

	@Test(expected = BException.class)
	public void testParsable() throws Exception {
		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		assertNotNull(start);
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
