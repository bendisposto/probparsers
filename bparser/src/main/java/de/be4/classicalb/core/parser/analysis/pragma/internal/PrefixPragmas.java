package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.Node;

public class PrefixPragmas {

	public static boolean isPrefixOperator(Node n) {
		if (n instanceof ANegationPredicate)
			return true;
		return false;
	}

}
