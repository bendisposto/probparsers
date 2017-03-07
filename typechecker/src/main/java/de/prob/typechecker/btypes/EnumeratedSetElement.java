package de.prob.typechecker.btypes;

import java.util.ArrayList;

import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.prob.typechecker.Typechecker;
import de.prob.typechecker.exceptions.UnificationException;

public class EnumeratedSetElement implements BType {
	private String name;

	public EnumeratedSetElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public BType unify(BType other, ITypechecker typechecker) {
		if (!this.compare(other)) {
			throw new UnificationException();
		}
		if (other instanceof EnumeratedSetElement) {
			if (((EnumeratedSetElement) other).getName().equals(this.name)) {
				return this;
			} else {
				throw new UnificationException();
			}

		} else if (other instanceof UntypedType) {
			((UntypedType) other).setFollowersTo(this, typechecker);
			return this;
		}
		throw new RuntimeException();
	}

	public boolean isUntyped() {
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean compare(BType other) {
		if (other instanceof UntypedType)
			return true;
		if (other instanceof EnumeratedSetElement) {
			if (((EnumeratedSetElement) other).getName().equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsInfiniteType() {
		return false;
	}

	public PExpression createASTNode(Typechecker typechecker) {
		TIdentifierLiteral literal = new TIdentifierLiteral(name);
		ArrayList<TIdentifierLiteral> idList = new ArrayList<TIdentifierLiteral>();
		idList.add(literal);
		AIdentifierExpression id = new AIdentifierExpression(idList);
		typechecker.setType(id, new SetType(this));
		return id;
	}
}
