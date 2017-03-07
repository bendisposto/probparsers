package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.AStringSetExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class StringType implements BType {

	private static StringType instance = new StringType();

	public static StringType getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "STRING";
	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other)) {
			throw new UnificationException();
		}

		if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		}
		if (other instanceof StringType) {
			return this;
		}

		throw new UnificationException();
	}

	public boolean isUntyped() {
		return false;
	}

	public boolean compare(BType other) {
		if (other instanceof UntypedType) {
			return true;
		}
		if (other instanceof StringType) {
			return true;
		}
		return false;
	}

	public boolean containsInfiniteType() {
		return true;
	}

	public PExpression createASTNode(Typechecker typechecker) {
		AStringSetExpression node = new AStringSetExpression();
		typechecker.setType(node, new SetType(this));
		return node;
	}

}
