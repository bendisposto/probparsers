package de.be4.classicalb.core.parser.exceptions;

@SuppressWarnings("serial")
public class BException extends Exception {

	private final Throwable ex;
	private final String filename;
	private final String message;

	public BException(final String filename, final Throwable e) {
		this(filename, null, e);
	}

	public BException(final String filename, final String message,
			final Throwable ex) {
		this.filename = filename;
		this.message = message;
		this.ex = ex;
	}

	@Override
	public Throwable getCause() {
		return ex;
	}

	@Override
	public String getMessage() {
		final StringBuilder sb = new StringBuilder();
		writeMessage(sb);
		return sb.toString();
	}

	private void writeMessage(final StringBuilder sb) {
		if (message != null) {
			sb.append(message);
		}
		if (ex != null) {
			if (ex instanceof BException) {
				final BException other = (BException) ex;
				other.writeMessage(sb);
				printFileRef(sb, filename, other.filename != null);
			} else {
				sb.append(ex.getMessage());
				printFileRef(sb, filename, false);
			}
		} else {
			printFileRef(sb, filename, false);
		}
	}

	private void printFileRef(final StringBuilder sb, final String filename,
			final boolean loaded) {
//		if (filename != null) {
//			sb.append(" ").append(loaded ? "loaded by" : "in file");
//			sb.append(": ").append(filename);
//		}
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
		return ex.getStackTrace();
	}
}
