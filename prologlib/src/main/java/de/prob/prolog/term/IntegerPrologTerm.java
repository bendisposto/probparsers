/**
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, Heinrich
 * Heine Universitaet Duesseldorf This software is licenced under EPL 1.0
 * (http://www.eclipse.org/org/documents/epl-v10.html)
 * */

package de.prob.prolog.term;

import java.math.BigInteger;

import de.prob.prolog.output.IPrologTermOutput;

/**
 * Represents a Prolog integer.
 * 
 * @author plagge
 */
public final class IntegerPrologTerm extends PrologTerm {
	private static final long serialVersionUID = -485207706557171193L;

	private final BigInteger value;

	public IntegerPrologTerm(final BigInteger value) {
		super(value.toString());
		this.value = value;
	}

	public IntegerPrologTerm(final long value) {
		this(BigInteger.valueOf(value));
	}
	
	public IntegerPrologTerm(final byte[] arr) {
		super(new BigInteger(arr).toString());
		this.value = new BigInteger(arr);
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	public BigInteger getValue() {
		return value;
	}

	@Override
	public void toTermOutput(final IPrologTermOutput pto) {
		pto.printNumber(value);
	}

	@Override
	public boolean equals(final Object other) {
		boolean isEqual;
		if (this == other) {
			isEqual = true;
		} else if (other != null && other instanceof IntegerPrologTerm) {
			isEqual = this.value.equals(((IntegerPrologTerm) other).value);
		} else {
			isEqual = false;
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return value.hashCode() * 11 + 4;
	}

}
