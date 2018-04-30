package de.prob.parser.ast.nodes;

import java.util.Observable;
import java.util.Observer;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.types.BType;

public abstract class TypedNode extends Node implements Observer {

	private BType type;

	public TypedNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

	public BType getType() {
		return type;
	}

	public boolean isUntyped() {
		return type.isUntyped();
	}

	public void setType(BType type) {
		if (type != null && type instanceof Observable) {
			((Observable) type).deleteObserver(this);
		}
		this.type = type;
		if (type instanceof Observable) {
			((Observable) type).addObserver(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		o.deleteObserver(this);
		setType((BType) arg);
	}
}
