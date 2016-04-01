package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AMissingSemicolonOperation;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

/**
 * This class checks that there is non missing semicolon between two operations.
 */
public class MissingSemicolonBetweenOperationsCheck implements SemanticCheck {

	private final class MissingSemicolonWalker extends DepthFirstAdapter {
		public boolean semicolonMissing = false;
		public Node node;

		@Override
		public void caseAMissingSemicolonOperation(
				AMissingSemicolonOperation node) {
			semicolonMissing = true;
			this.node = node.getOperation();
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
			throw new CheckException(Utils.getSourcePositionAsString(pos) + " Semicolon missing between operations." , 
					new Node[]{adapter.node});
		}
	}

}
