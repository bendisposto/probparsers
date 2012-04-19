package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;

import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PSet;
import de.be4.classicalb.core.parser.node.Start;

public class UnitPragmaClassifier extends PrefixClassifier{

	@SuppressWarnings("unchecked")
	public UnitPragmaClassifier(String input) {
		super(input, new Class[] { PSet.class, PExpression.class });
	}
	
	@Override
	public Node seek(UnknownPragma p, Start ast) {
		if (p.getSuccessor() instanceof PSet) {
			return p.getSuccessor();
		}
		return super.seek(p, ast);
	}
	

}
