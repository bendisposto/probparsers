package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.AIntegerSetExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class IntegerType implements BType {

	private static IntegerType instance = new IntegerType();

	public static IntegerType getInstance() {
		return instance;
	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other)) {
			throw new UnificationException();
		}
		if (other instanceof IntegerType) {
			return getInstance();
		}
		if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return getInstance();
		}
		if (other instanceof IntegerOrSetOfPairType) {
			return other.unify(this, typechecker);
		}
		if (other instanceof IntegerOrSetType) {
			return other.unify(this, typechecker);
		}
		throw new UnificationException();
	}

	@Override
	public String toString() {
		return "INTEGER";
	}
	
	
	public boolean isUntyped() {
		return false;
	}

	public boolean compare(BType other) {
		if (other instanceof UntypedType || other instanceof IntegerType)
			return true;
		if (other instanceof IntegerOrSetType
				|| other instanceof IntegerOrSetOfPairType)
			return true;
		return false;
	}

	public boolean containsInfiniteType() {
		return true;
	}

	public PExpression createASTNode(Typechecker typechecker) {
		AIntegerSetExpression node = new AIntegerSetExpression();
		typechecker.setType(node, new SetType(this));
		return node;
	}
}
