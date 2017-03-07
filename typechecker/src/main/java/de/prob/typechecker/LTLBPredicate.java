package de.prob.typechecker;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LTLBPredicate {
	LinkedHashMap<String, de.be4.classicalb.core.parser.node.Node> identifierHashTable;
	
	de.be4.classicalb.core.parser.node.Node predicate;
	
	public LTLBPredicate(LinkedHashMap<String, de.be4.classicalb.core.parser.node.Node> identifierHashTable, de.be4.classicalb.core.parser.node.Node predicate){
		this.identifierHashTable = identifierHashTable;
		this.predicate = predicate;
	}

	public LTLBPredicate(
			ArrayList<de.be4.classicalb.core.parser.node.Node> arrayList,
			de.be4.classicalb.core.parser.node.Start start) {
		// TODO Auto-generated constructor stub
	}
	
	public de.be4.classicalb.core.parser.node.Node getBFormula(){
		return predicate;
	}

	public LinkedHashMap<String, de.be4.classicalb.core.parser.node.Node> getIdentifierList(){
		return identifierHashTable;
	}
	
}
