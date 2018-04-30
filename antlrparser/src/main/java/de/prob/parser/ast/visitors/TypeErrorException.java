package de.prob.parser.ast.visitors;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.TypedNode;
import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.UnificationException;

public class TypeErrorException extends Exception {
	private static final long serialVersionUID = -5344167922965323221L;
	private final String message;

	private static final String TYPE_ERROR = "Type error";

	public TypeErrorException(String message) {
		this.message = message;
	}

	public TypeErrorException(BType expected, BType found, TypedNode node, UnificationException e) {
		this.message = createErrorMessage(expected, found, node);
		final Logger logger = Logger.getLogger(getClass().getName());
		logger.log(Level.SEVERE, TYPE_ERROR, e);
	}

	private String createErrorMessage(BType expected, BType found, TypedNode node) {
		StringBuilder sb = new StringBuilder();
		sb.append("Expected ").append(expected).append(" but found ").append(found).append(" ");

		SourceCodePosition sourceCodePosition = node.getSourceCodePosition();
		int line = sourceCodePosition.getStartLine();
		int pos = sourceCodePosition.getStartColumn();
		String text = sourceCodePosition.getText();

		sb.append("at '").append(text).append("' starting ");
		sb.append("in line ").append(line);
		sb.append(" column ").append(pos).append(".");
		return sb.toString();
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.message;
	}

}
