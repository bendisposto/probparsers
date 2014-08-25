package de.be4.eventb.core.parser;

import de.be4.eventb.core.parser.node.Node;

@SuppressWarnings("serial")
public class CheckException extends Exception {
	private final Entry[] entries;

	public CheckException(final Entry[] entries) {
		super();
		this.entries = entries;
	}

	public CheckException(final Entry entry) {
		this(new Entry[] { entry });
	}

	public Entry[] getEntries() {
		return entries;
	}

	@Override
	public String getMessage() {
		final StringBuilder buffer = new StringBuilder('[');

		for (int i = 0; i < entries.length; i++) {
			buffer.append(entries[i].message);

			if (i < entries.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');
		return buffer.toString();
	}

	public static class Entry {
		public final Node node;
		public final String message;

		public Entry(final Node node, final String message) {
			this.node = node;
			this.message = message;
		}
	}
}
