package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class SetType extends AbstractHasFollowers {

	private BType subtype;

	public SetType(BType subtype) {
		setSubtype(subtype);
	}

	public BType getSubtype() {
		return subtype;
	}

	public void setSubtype(BType type) {
		this.subtype = type;
		if (type instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) type).addFollower(this);
		}
	}

	public SetType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other) || this.contains(other))
			throw new UnificationException();

		if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		}
		if (other instanceof SetType) {
			((SetType) other).setFollowersTo(this, typechecker);
			this.subtype = this.subtype.unify(((SetType) other).subtype, typechecker);
			return this;
		}

		if (other instanceof IntegerOrSetType) {
			return (SetType) other.unify(this, typechecker);
		}

		if (other instanceof IntegerOrSetOfPairType) {
			return (SetType) other.unify(this, typechecker);
		}

		if (other instanceof FunctionType) {
			return (SetType) other.unify(this, typechecker);
		}

		throw new UnificationException();
	}

	@Override
	public String toString() {
		if (this.equals(subtype)) {
			return "POW(recursive call)";
		} else {
			return "POW(" + subtype + ")";
		}

	}

	public boolean isUntyped() {
		return subtype.isUntyped();
	}

	public boolean compare(BType other) {
		if (other instanceof SetType) {
			return this.subtype.compare(((SetType) other).subtype);
		}

		if (other instanceof UntypedType)
			return true;
		if (other instanceof IntegerOrSetType || other instanceof IntegerOrSetOfPairType) {
			return true;
		} else if (other instanceof FunctionType) {
			return other.compare(this);
		}
		return false;
	}

	@Override
	public boolean contains(BType other) {
		if (this.equals(subtype)) {
			return true;
		}
		if (this.subtype.equals(other)) {
			return true;
		}
		if (this.subtype instanceof AbstractHasFollowers) {
			return ((AbstractHasFollowers) subtype).contains(other);
		}
		return false;
	}

	public boolean containsInfiniteType() {
		return this.subtype.containsInfiniteType();
	}

	public PExpression createASTNode(Typechecker typechecker) {
		APowSubsetExpression node = new APowSubsetExpression(subtype.createASTNode(typechecker));
		typechecker.setType(node, this);
		return node;
	}

}
