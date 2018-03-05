package de.be4.classicalb.core.parser.exceptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Token;

public class BException extends Exception {

	private static final long serialVersionUID = -693107947667081359L;
	private final Throwable cause;
	private final String filename;
	private final List<Location> locations = new ArrayList<>();

	public BException(final String filename, final String message, final Exception cause) {
		super(message);
		this.filename = filename;
		this.cause = cause;
	}

	public BException(String fileName, LexerException e) {
		this(fileName, e.getMessage(), e);
	}

	public BException(String fileName, BParseException e) {
		this(fileName, e.getMessage(), e);
		if (e.getToken() != null) {
			Token token = e.getToken();
			locations.add(new Location(filename, token.getLine(), token.getPos(), token.getLine(), token.getPos()));
		}
	}

	public BException(String fileName, PreParseException e) {
		this(fileName, e.getMessage(), e);
		Pattern p = Pattern.compile("\\[(\\d+)[,](\\d+)\\].*", Pattern.DOTALL);
		Matcher m = p.matcher(cause.getMessage());
		boolean posFound = m.lookingAt();
		if (posFound) {
			int line = Integer.parseInt(m.group(1));
			int pos = Integer.parseInt(m.group(2));
			locations.add(new Location(filename, line, pos, line, pos));
		}
	}

	public BException(final String filename, final CheckException e) {
		this(filename, e.getMessage(), e);
		for (Node node : e.getNodes()) {
			locations.add(new Location(filename, node.getStartPos().getLine(), node.getStartPos().getPos(),
					node.getEndPos().getLine(), node.getEndPos().getPos()));
		}
	}

	public BException(final String filename, final IOException e) {
		this(filename, e.getMessage(), e);
	}

	@Override
	public Throwable getCause() {
		return this.cause;
	}

	public List<Location> getLocations() {
		return this.locations;
	}


	public String getFilename() {
		return filename;
	}

	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return cause.getStackTrace();
	}

	public static final class Location implements Serializable {

		private static final long serialVersionUID = -7391092302311266417L;
		private final String filename;
		private final int startLine;
		private final int startColumn;
		private final int endLine;
		private final int endColumn;

		public Location(final String fileName, final int startLine, final int startColumn, final int endLine,
				final int endColumn) {

			this.filename = fileName;
			this.startLine = startLine;
			this.startColumn = startColumn;
			this.endLine = endLine;
			this.endColumn = endColumn;
		}

		public String getFilename() {
			return this.filename;
		}

		public int getStartLine() {
			return this.startLine;
		}

		public int getStartColumn() {
			return this.startColumn;
		}

		public int getEndLine() {
			return this.endLine;
		}

		public int getEndColumn() {
			return this.endColumn;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder(this.filename);
			sb.append(':');
			sb.append(this.getStartLine());
			sb.append(':');
			sb.append(this.getStartColumn());

			if (this.getStartLine() != this.getEndLine() || this.getStartColumn() != this.getEndColumn()) {
				sb.append(" to ");
				sb.append(this.getEndLine());
				sb.append(':');
				sb.append(this.getEndColumn());
			}

			return sb.toString();
		}
	}
}
