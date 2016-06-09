package util;

import java.io.File;
import java.io.FilenameFilter;

public abstract class AbstractParseMachineTest {
	

	private static final class MachineFilenameFilter implements FilenameFilter {
		private static final String[] MACHINE_SUFFIX = { ".mch", ".imp", ".ref" };

		public boolean accept(final File dir, final String name) {
			for (int i = 0; i < MACHINE_SUFFIX.length; i++) {
				if (name.endsWith(MACHINE_SUFFIX[i])) {
					return true;
				}
			}
			return false;
		}
	}

	protected static File[] getMachines(String path) {
		final File dir = new File(path);
		return dir.listFiles(new MachineFilenameFilter());
	}

	
}