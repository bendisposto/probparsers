package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import test.AbstractParseMachineTest;
import test.PolySuite;
import test.PolySuite.Config;
import test.PolySuite.Configuration;
import de.be4.classicalb.core.parser.node.Start;

@RunWith(PolySuite.class)
public class ParsableMachineTest extends AbstractParseMachineTest {

	private static final String PATH = "src/test/resources/parsable";

	private final File machine;

	public ParsableMachineTest(File machine) {
		this.machine = machine;
	}

	@Test
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
