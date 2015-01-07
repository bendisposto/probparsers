package de.be4.classicalb.core.parser;

import java.util.Set;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;

public class PreParserIdentifierTypeVisitor extends DepthFirstAdapter {

	private final Set<String> definitions;
	private boolean kaboom = false;

	public PreParserIdentifierTypeVisitor(Set<String> definitions) {
		this.definitions = definitions;
	}

	@Override
	public void inAIdentifierExpression(AIdentifierExpression node) {
		super.inAIdentifierExpression(node);
		if (definitions.contains(node.getIdentifier().get(0).getText()))
			kaboom();
	}

	private void kaboom() {
		kaboom = true;
	}

	public boolean isKaboom() {
		return kaboom;
	}

}
