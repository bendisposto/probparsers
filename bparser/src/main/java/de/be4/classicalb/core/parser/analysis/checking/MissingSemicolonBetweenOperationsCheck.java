package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AMissingSemicolonOperation;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

/**
 * This class checks several dependencies between machine clauses. Examples are:
 * Which clauses are valid for an abstract machine? Is a PROPERTIES clause
 * present, if a CONSTANTS clause is used?
 * 
 * 
 */
public class MissingSemicolonBetweenOperationsCheck implements SemanticCheck {

	private final class MissingSemicolonWalker extends DepthFirstAdapter {
		public boolean semicolonMissing = false;
		public Node node;

		@Override
		public void caseAMissingSemicolonOperation(
				AMissingSemicolonOperation node) {
			semicolonMissing = true;
			this.node = node;
		}
	}

	@Override
	public void setOptions(ParseOptions options) {
		// TODO Auto-generated method stub

	}

	@Override
	public void runChecks(Start rootNode) throws CheckException {

		MissingSemicolonWalker adapter = new MissingSemicolonWalker();

		rootNode.apply(adapter);

		if (adapter.semicolonMissing) {
			SourcePosition pos = adapter.node.getStartPos();
			int line = pos.getLine() + 1;
			int col = pos.getPos();
			throw new CheckException("Semicolon missing between operations. Position close to [" + line +"," + col + "]"  , 
					new Node[]{adapter.node});
		}
	}

}
