package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.CompoundException;
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
		List<CheckException> exceptions = new ArrayList<>();

		@Override
		public void inAMissingSemicolonOperation(AMissingSemicolonOperation node) {
			exceptions.add(new CheckException("Semicolon missing between operations", node.getOperation()));
		}

		@Override
		public void inAInvalidOperationsClauseMachineClause(AInvalidOperationsClauseMachineClause node) {
			exceptions.add(new CheckException("Invalid semicolon after last operation", node.getSemicolon()));
		}

		@Override
		public void inAInvalidSubstitution(AInvalidSubstitution node) {
			exceptions.add(
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
	public void runChecks(Start rootNode) throws CompoundException, CheckException {
		MissingSemicolonWalker adapter = new MissingSemicolonWalker();
		rootNode.apply(adapter);
		if (adapter.exceptions.size() == 1) {
			throw adapter.exceptions.get(0);
		} else if (adapter.exceptions.size() > 1) {
			CompoundException compoundException = new CompoundException();
			for (CheckException e : adapter.exceptions) {
				compoundException.addException(e);
			}
			throw compoundException;
		}
	}
}
