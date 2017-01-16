package de.prob.typechecker.btypes;

import de.be4.classicalb.core.parser.node.PExpression;
import de.hhu.stups.sablecc.patch.SourcePosition;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.TypeErrorException;
import de.prob.typechecker.exceptions.UnificationException;

public class IntegerOrSetOfPairType extends AbstractHasFollowers {

	private AbstractHasFollowers first;
	private AbstractHasFollowers second;

	public AbstractHasFollowers getFirst() {
		return first;
	}

	public AbstractHasFollowers getSecond() {
		return second;
	}

	public IntegerOrSetOfPairType(SourcePosition sourcePosition,
			SourcePosition sourcePosition2) {

		IntegerOrSetType i1 = new IntegerOrSetType();
		this.first = i1;
		first.addFollower(this);

		IntegerOrSetType i2 = new IntegerOrSetType();
		this.second = i2;
		second.addFollower(this);
	}

	public void update(BType oldType, BType newType, ITypechecker typechecker) {
		if(second.getFollowers().contains(first)){
			throw new RuntimeException();
		}
		if (newType instanceof IntegerType) {
			// if newType is an Integer then both arguments and the result are
			// Integers

			if (this.first == oldType) {
				// do nothing
			} else {
				first.deleteFollower(this); // we do not want to update this
											// node twice
				first.unify(newType, typechecker);
			}
			if (this.second == oldType) {
				// do nothing
			} else {
				second.deleteFollower(this);
				second.unify(newType, typechecker);
			}
			this.setFollowersTo(IntegerType.getInstance(), typechecker);

			return;
		} else if (newType instanceof SetType) {
			SetType newFirst;
			SetType newSecond;
			
			
			if (first == second && first != oldType){
				first.deleteFollower(this);
				newFirst = new SetType(new UntypedType());
				newSecond = newFirst;
				newFirst.addFollower(this);
			}else {
				if (this.first == oldType) {
					first.deleteFollower(this);
					newFirst = (SetType) newType;
				} else {
					first.deleteFollower(this);
					newFirst = new SetType(new UntypedType());
					first.setFollowersTo(newFirst, typechecker);
				}

				if (this.second == oldType) {
					first.deleteFollower(this);
					newSecond = (SetType) newType;
				} else {
					second.deleteFollower(this);
					newSecond = new SetType(new UntypedType());
					this.second.setFollowersTo(newSecond, typechecker);
				}
			}
			
			if (oldType == this) {
				this.setFollowersTo(newType, typechecker);

				PairType pair = new PairType(newFirst, newSecond);
				((SetType) newType).getSubtype().unify(pair, typechecker);
			} else {
				SetType setOfPairSetType = new SetType(new PairType(
						newFirst.getSubtype(), newSecond.getSubtype()));
				setOfPairSetType.unify(this, typechecker);
			}
			return;
		} else if (newType instanceof IntegerOrSetOfPairType) {
			if (this.first == oldType) {
				first.deleteFollower(this);
				first = (AbstractHasFollowers) newType;
				first.addFollower(this);
			}
			if (this.second == oldType) {
				second.deleteFollower(this);
				second = (AbstractHasFollowers) newType;
				second.addFollower(this);
			}
		} else if (newType instanceof IntegerOrSetType) {
			if (this.first == oldType) {
				first.deleteFollower(this);
				first = (AbstractHasFollowers) newType;
				first.addFollower(this);
			}
			if (this.second == oldType) {
				second.deleteFollower(this);
				second = (AbstractHasFollowers) newType;
				second.addFollower(this);
			}
		} else {
			throw new TypeErrorException(
					"Expected 'INTEGER' or 'POW(_A)', found " + newType);
		}

	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other) || this.contains(other)){
			throw new UnificationException();
		}
			
		
		if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		}
		
		if (other instanceof IntegerType) {
			this.setFollowersTo(IntegerType.getInstance(), typechecker);
			this.getFirst().deleteFollower(this);
			this.getSecond().deleteFollower(this);
			first.unify(IntegerType.getInstance(), typechecker);
			second.unify(IntegerType.getInstance(), typechecker);
			return IntegerType.getInstance();
		}
		
		if (other instanceof IntegerOrSetType) {
			((IntegerOrSetType) other).setFollowersTo(this, typechecker);
			return this;
		}
		
		if(other instanceof SetType){
			first.deleteFollower(this);
			second.deleteFollower(this);
			
			SetType newFirst = new SetType(new UntypedType()).unify(first, typechecker);
			SetType newSecond =  new SetType(new UntypedType()).unify(second, typechecker);
			
			SetType found = new SetType(new PairType(newFirst.getSubtype(),
					newSecond.getSubtype()));
			
			this.setFollowersTo(found, typechecker);

			return found.unify(other, typechecker);
		}
		if(other instanceof FunctionType){
			return other.unify(this, typechecker);
		}
		if(other instanceof IntegerOrSetOfPairType){
			IntegerOrSetOfPairType o = (IntegerOrSetOfPairType) other;
			o.first.deleteFollower(o);
			o.second.deleteFollower(o);
			first = (AbstractHasFollowers) this.first.unify(((IntegerOrSetOfPairType) other).first, typechecker);
			second = (AbstractHasFollowers) this.second.unify(((IntegerOrSetOfPairType) other).second, typechecker);
			((IntegerOrSetOfPairType) other).setFollowersTo(this, typechecker);
			return this;
		}
		throw new RuntimeException();
	}

	@Override
	public String toString() {
		return "IntegerOrSetOfPairType" + this.hashCode() + "("
				+ this.getFirst() + "," + this.getSecond() + ")";
	}

	public boolean isUntyped() {
		// TODO proof
		return true;
	}

	public boolean compare(BType other) {
		if (other instanceof UntypedType || other instanceof IntegerType
				|| other instanceof IntegerOrSetType
				|| other instanceof IntegerOrSetOfPairType
				|| other instanceof FunctionType)
			return true;
		else if (other instanceof SetType) {
			BType subType = ((SetType) other).getSubtype();
			if (subType instanceof UntypedType || subType instanceof PairType)
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean contains(BType other) {
		if (this.first.equals(other) || this.second.equals(other)) {
			return true;
		}
		
		if(first.contains(other) || second.contains(other)){
			return true;
		}else
			return false;
	}

	public boolean containsInfiniteType() {
		return false;
	}

	public PExpression createASTNode(Typechecker typechecker) {
		return null;
	}

}
