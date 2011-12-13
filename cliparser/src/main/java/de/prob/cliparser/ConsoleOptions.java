package de.prob.cliparser;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author plagge
 */
public class ConsoleOptions {

	private String intro = null;
	private final Map<String, Option> options = new HashMap<String, Option>();
	private final List<Option> listOfOptions = new ArrayList<Option>();

	private final Map<String, String[]> concreteOptions = new HashMap<String, String[]>();
	private String[] remaining;

	public void setIntro(final String intro) {
		this.intro = intro;
	}

	public void addOption(final String key, final String desc, final int values) {
		if (options.containsKey(key))
			throw new IllegalArgumentException("key " + key + " added twice");
		final Option option = new Option(key, desc, values);
		options.put(key, option);
		listOfOptions.add(option);
	}

	public void addOption(final String key, final String desc) {
		addOption(key, desc, 0);
	}

	public void parseOptions(final String cmd[]) {
		final ArrayList<String> rest = new ArrayList<String>();
		concreteOptions.clear();
		for (int i = 0; i < cmd.length; i++) {
			final String key = cmd[i];
			final Option option = options.get(key);
			if (option == null) {
				rest.add(key);
			} else {
				if (i + option.expected_values < cmd.length) {
					final String[] params = new String[option.expected_values];
					System.arraycopy(cmd, i + 1, params, 0,
							option.expected_values);
					concreteOptions.put(key, params);
					i += option.expected_values;
				} else
					throw new IllegalArgumentException(
							"Too few arguments for option " + key);
			}
		}
		remaining = rest.toArray(new String[0]);
	}

	public boolean isOptionSet(final String key) {
		return concreteOptions.containsKey(key);
	}

	public String[] getRemainingOptions() {
		return remaining;
	}

	public String[] getOptions(final String key) {
		return concreteOptions.get(key);
	}

	public void printUsage(final PrintWriter out) {
		if (intro != null) {
			out.println(intro);
		}

		int width = 0;
		for (final String key : options.keySet()) {
			if (key.length() > width) {
				width = key.length();
			}
		}

		for (final Option option : listOfOptions) {
			out.print(option.key);
			for (int i = 0; i < width + 1 - option.key.length(); i++) {
				out.print(' ');
			}
			out.println(option.description);
		}
		out.flush();
	}

	public void printUsage(final OutputStream out) {
		printUsage(new PrintWriter(out));
	}

	private static class Option {
		private final String key;
		private final String description;
		private final int expected_values;

		public Option(final String key, final String description,
				final int expected_values) {
			super();
			this.expected_values = expected_values;
			this.key = key;
			this.description = description;
		}
	}
}
