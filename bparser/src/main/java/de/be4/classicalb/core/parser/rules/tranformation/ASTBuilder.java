package de.be4.classicalb.core.parser.rules.tranformation;

import java.util.List;

import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.PPredicate;

public class ASTBuilder {

	public static PPredicate createConjunction(List<PPredicate> predList) {
		if (predList.size() == 0) {
			return predList.get(0);
		} else {
			PPredicate p = predList.get(0);
			for (int i = 1; i < predList.size(); i++) {
				p = new AConjunctPredicate(p, predList.get(i));
			}
			return p;
		}
	}
}
