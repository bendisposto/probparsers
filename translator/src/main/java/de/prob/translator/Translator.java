package de.prob.translator;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Node;
import de.prob.translator.types.BObject;

public class Translator {
	public static BObject translate(String s) throws BCompoundException {
		Node ast = BParser.parse("#EXPRESSION" + s);
		TranslatingVisitor v = new TranslatingVisitor();
		ast.apply(v);
		return v.getResult();
	}
}
