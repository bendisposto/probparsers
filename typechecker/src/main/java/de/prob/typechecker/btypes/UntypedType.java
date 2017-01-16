package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;

public class UntypedType extends AbstractHasFollowers {

	public BType unify(BType other, ITypechecker typechecker) {
		this.setFollowersTo(other, typechecker);
		return other;
	}

	public boolean isUntyped() {
		return true;
	}

	public boolean compare(BType other) {
		return true;
	}

	@Override
	public boolean contains(BType other) {
		return false;
	}

	public boolean containsInfiniteType() {
		return false;
	}

	public PExpression createASTNode(Typechecker typechecker) {
		return null;
	}

}
