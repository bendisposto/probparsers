package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.VisitorException;
import de.be4.classicalb.core.parser.node.AInvalidOperationsClauseMachineClause;
import de.be4.classicalb.core.parser.node.AInvalidSubstitution;
import de.be4.classicalb.core.parser.node.AMissingSemicolonOperation;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.Start;

/**
 * This class checks that there is non missing semicolon between two operations.
 */
public class SemicolonCheck implements SemanticCheck {

	private final class MissingSemicolonWalker extends DepthFirstAdapter {

		@Override
		public void caseAMissingSemicolonOperation(AMissingSemicolonOperation node) {
			throw new VisitorException(new CheckException("Semicolon missing between operations", node.getOperation()));
		}

		@Override
		public void caseAInvalidOperationsClauseMachineClause(AInvalidOperationsClauseMachineClause node) {
			throw new VisitorException(
					new CheckException("Invalid semicolon after last operation", node.getSemicolon()));
		}

		@Override
		public void caseAInvalidSubstitution(AInvalidSubstitution node) {
			throw new VisitorException(
					new CheckException("Invalid semicolon after last substitution (before END)", node.getSemicolon()));
		}

		@Override
		public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
			// skip properties clause
		}
	}

	@Override
	public void setOptions(ParseOptions options) {
	}

	@Override
	public void runChecks(Start rootNode) throws CheckException {
		MissingSemicolonWalker adapter = new MissingSemicolonWalker();
		try {
			rootNode.apply(adapter);
		} catch (VisitorException e) {
			throw (CheckException) e.getException();
		}
	}
}
