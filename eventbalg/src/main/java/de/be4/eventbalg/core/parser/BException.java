package de.be4.eventbalg.core.parser;

@SuppressWarnings("serial")
public class BException extends Exception {

	private final Exception ex;

	public BException(final Exception e) {
		ex = e;
	}

	@Override
	public Exception getCause() {
		return ex;
	}

	@Override
	public String getMessage() {
		return ex.getMessage();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return ex.getStackTrace();
	}
}
