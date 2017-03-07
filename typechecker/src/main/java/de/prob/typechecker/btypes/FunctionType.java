package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.APartialFunctionExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class FunctionType extends AbstractHasFollowers {
	private BType domain;
	private BType range;

	public FunctionType(BType domain, BType range) {
		setDomain(domain);
		setRange(range);
	}

	public BType getDomain() {
		return domain;
	}

	public void setDomain(BType domain) {
		this.domain = domain;
		if (domain instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) domain).addFollower(this);
		}
	}

	public BType getRange() {
		return range;
	}

	public void setRange(BType range) {
		this.range = range;
		if (range instanceof AbstractHasFollowers) {
			((AbstractHasFollowers) range).addFollower(this);
		}
	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other)) {
			throw new UnificationException();
		}
		if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		} else if (other instanceof FunctionType) {
			((FunctionType) other).setFollowersTo(this, typechecker);
			setDomain(domain.unify(((FunctionType) other).domain, typechecker));
			setRange(range.unify(((FunctionType) other).range, typechecker));
			return this;
		} else if (other instanceof SetType
				|| other instanceof IntegerOrSetType
				|| other instanceof IntegerOrSetOfPairType) {
			if (domain instanceof AbstractHasFollowers) {
				((AbstractHasFollowers) domain).deleteFollower(this);
			}
			if (range instanceof AbstractHasFollowers) {
				((AbstractHasFollowers) range).deleteFollower(this);
			}
			SetType s = new SetType(new PairType(domain, range));
			this.setFollowersTo(s, typechecker);
			return s.unify(other, typechecker);
		}
		throw new RuntimeException();
	}

	public boolean isUntyped() {
		return domain.isUntyped() || range.isUntyped();
	}

	@Override
	public String toString() {
		String res = "FUNC(" + domain + "," + range + ")";
		return res;
	}

	public void update(BType oldType, BType newType) {
		if (domain == oldType)
			setDomain(newType);
		if (range == oldType)
			setRange(newType);
	}

	@Override
	public boolean compare(BType other) {
		if (other instanceof UntypedType)
			return true;
		else if (other instanceof FunctionType) {
			return domain.compare(((FunctionType) other).domain)
					&& range.compare(((FunctionType) other).range);
		} else if (other instanceof IntegerOrSetOfPairType
				|| other instanceof IntegerOrSetType) {
			return true;
		} else if (other instanceof SetType) {
			BType t = ((SetType) other).getSubtype();
			if (t instanceof PairType) {
				return ((PairType) t).getFirst().compare(domain)
						&& ((PairType) t).getSecond().compare(range);
			} else if (t instanceof UntypedType) {
				return true;
			} else
				return false;
		}
		return false;
	}

	@Override
	public boolean contains(BType other) {
		if (this.domain.equals(other) || this.range.equals(other)) {
			return true;
		}
		if (domain instanceof AbstractHasFollowers) {
			if (((AbstractHasFollowers) domain).contains(other))
				return true;
		}
		if (range instanceof AbstractHasFollowers) {
			if (((AbstractHasFollowers) range).contains(other))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsInfiniteType() {
		return this.domain.containsInfiniteType()
				|| this.range.containsInfiniteType();
	}

	@Override
	public PExpression createASTNode(Typechecker typechecker) {
		APartialFunctionExpression node = new APartialFunctionExpression(
				domain.createASTNode(typechecker),
				range.createASTNode(typechecker));
		typechecker.setType(node, new SetType(this));
		
		return node;
	}

}
