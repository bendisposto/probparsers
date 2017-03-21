package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.ACartesianProductExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class PairType extends AbstractHasFollowers {

	private BType first;
	private BType second;

	public BType getFirst() {
		return this.first;
	}

	public BType getSecond() {
		return this.second;
	}

	public void setFirst(BType newType) {
		this.first = newType;
		if (newType instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) newType).addFollower(this);
		}
	}

	public void setSecond(BType newType) {
		this.second = newType;
		if (newType instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) newType).addFollower(this);
		}
	}

	public void update(BType oldType, BType newType) {

		if (this.first == oldType)
			setFirst(newType);
		if (this.second == oldType)
			setSecond(newType);
	}

	public PairType(BType first, BType second) {
		setFirst(first);
		setSecond(second);
	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other) || this.contains(other))
			throw new UnificationException();

		if (other instanceof PairType) {
			((PairType) other).setFollowersTo(this, typechecker);
			setFirst(first.unify(((PairType) other).first, typechecker));
			setSecond(second.unify(((PairType) other).second, typechecker));
			return this;
		} else if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		}
		throw new UnificationException();
	}

	@Override
	public String toString() {
		String res = "";
		if(this.equals(first)|| this.equals(second)){
			res += "(Recursive Type)";
			return res;
		}else{
			if (first instanceof PairType) {
				res += "(" + first + ")";
			} else
				res += first;
			res += "*";
			if (second instanceof PairType) {
				res += "(" + second + ")";
			} else
				res += second;
			return res;
		}
		
		

	}

	public boolean isUntyped() {
		if (first.isUntyped() || second.isUntyped())
			return true;
		else
			return false;
	}

	public boolean compare(BType other) {
		if (other instanceof UntypedType)
			return true;
		if (other instanceof PairType) {
			return this.first.compare(((PairType) other).first)
					&& this.second.compare(((PairType) other).second);
		}
		return false;
	}

	@Override
	public boolean contains(BType other) {
		if (this.equals(this.first) || this.equals(this.second)) {
			return true;
		}
		if (this.first.equals(other) || this.second.equals(other)) {
			return true;
		}
		if (first instanceof AbstractHasFollowers) {
			if (((AbstractHasFollowers) first).contains(other))
				return true;
		}
		if (second instanceof AbstractHasFollowers) {
			if (((AbstractHasFollowers) second).contains(other))
				return true;
		}
		return false;
	}

	public boolean containsInfiniteType() {
		return this.first.containsInfiniteType()
				|| this.second.containsInfiniteType();
	}

	public PExpression createASTNode(Typechecker typechecker) {
		ACartesianProductExpression node = new ACartesianProductExpression(
				first.createASTNode(typechecker),
				second.createASTNode(typechecker));
		typechecker.setType(node, new SetType(this));
		return node;
	}
}
