package de.be4.classicalb.core.parser.exceptions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CompoundException extends Exception {
	private final List<Exception> exceptions = new ArrayList<>();

	public void addException(Exception exception) {
		this.exceptions.add(exception);
	}

	public List<Exception> getExceptions() {
		return this.exceptions;
	}
}
