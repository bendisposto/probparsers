package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.ADefinitionPredicate;
import de.be4.classicalb.core.parser.node.ADefinitionSubstitution;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.util.Utils;

public class DefinitionUsageCheck extends DepthFirstAdapter implements SemanticCheck {

	private final IDefinitions definitions;
	private final Set<Node> erroneousNodes = new HashSet<>();
	private final List<CheckException> exceptions = new ArrayList<>();

	public DefinitionUsageCheck(final IDefinitions definitions) {
		this.definitions = definitions;
	}

	public void runChecks(final Start rootNode) {
		// only need to check complete machines
		if (!Utils.isCompleteMachine(rootNode)) {
			return;
		}

		erroneousNodes.clear();
		rootNode.apply(this);

		if (!erroneousNodes.isEmpty()) {
			exceptions.add(new CheckException("Number of parameters doesn't match declaration of definition",
					erroneousNodes.toArray(new Node[erroneousNodes.size()])));
		}
	}

	@Override
	public void inADefinitionPredicate(final ADefinitionPredicate node) {
		check(node, node.getParameters().size(), node.getDefLiteral().getText());
	}

	@Override
	public void inADefinitionSubstitution(final ADefinitionSubstitution node) {
		check(node, node.getParameters().size(), node.getDefLiteral().getText());
	}

	@Override
	public void inADefinitionExpression(final ADefinitionExpression node) {
		check(node, node.getParameters().size(), node.getDefLiteral().getText());
	}

	private void check(final Node node, final int paramCount, final String literal) {
		final int expected = definitions.getParameterCount(literal);

		if (paramCount != expected) {
			erroneousNodes.add(node);
		}
	}

	public void setOptions(ParseOptions options) {
		// ignore options
	}

	@Override
	public List<CheckException> getCheckExceptions() {
		return this.exceptions;
	}
}
