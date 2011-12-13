package de.be4.classicalb.core.parser.prios;

class Tuple {
	private final BinaryOperator op1;
	private final BinaryOperator op2;
	private final EAssoc assoc;

	public Tuple(BinaryOperator op1, BinaryOperator op2, EAssoc assoc) {
		this.op1 = op1;
		this.op2 = op2;
		this.assoc = assoc;
	}

	public EAssoc getAssoc() {
		return assoc;
	}

	public BinaryOperator getOp1() {
		return op1;
	}

	public BinaryOperator getOp2() {
		return op2;
	}
}