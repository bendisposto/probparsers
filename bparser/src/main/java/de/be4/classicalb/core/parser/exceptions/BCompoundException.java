package de.be4.classicalb.core.parser.exceptions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BCompoundException extends Exception {
	private final List<BException> exceptions = new ArrayList<>();

	public BCompoundException(List<BException> list) {
		super(list.get(0).getLocalizedMessage(), list.get(0));
		this.exceptions.addAll(list);
	}

	public BCompoundException(BException bException) {
		super(bException.getLocalizedMessage(), bException);
		this.exceptions.add(bException);
	}

	public List<BException> getBExceptions() {
		return this.exceptions;
	}

	public BException getFirstException() {
		return this.exceptions.get(0);
	}

	@Override
	public synchronized Throwable getCause() {
		return this.exceptions.get(0).getCause();
	}
}
