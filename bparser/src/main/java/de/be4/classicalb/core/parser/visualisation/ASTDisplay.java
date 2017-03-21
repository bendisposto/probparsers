package de.be4.classicalb.core.parser.visualisation;

/*
 * NOTES
 * AST Walker which creates graphical tree
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;

public class ASTDisplay extends DepthFirstAdapter {

	private final Stack<DefaultMutableTreeNode> parents = new Stack<DefaultMutableTreeNode>();

	@Override
	public void outStart(final Start node) {
		final JFrame frame = new JFrame("AST Displayer");
		final JTree tree = new JTree(parents.pop());
		final JScrollPane pane = new JScrollPane(tree);

		expandAll(tree);

		/* window listener so the program will die */
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(300, 400);
		frame.getContentPane().add(pane);
		frame.setVisible(true);
	}

	/*
	 * As we come across non terminals, push them onto the stack
	 */
	@Override
	public void defaultIn(final Node node) {
		final DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(node.getClass().getSimpleName());
		parents.push(thisNode);
	}

	/*
	 * As we leave a non terminal, it's parent is the node before it on the
	 * stack, so we pop it off and add it to that node
	 */
	@Override
	public void defaultOut(final Node node) {
		final DefaultMutableTreeNode thisNode = parents.pop();
		parents.peek().add(thisNode);
	}

	/*
	 * Terminals - our parent is always on the top of the stack, so we add
	 * ourselves to it
	 */
	@Override
	public void defaultCase(final Node node) {
		final DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(((Token) node).getText());
		parents.peek().add(thisNode);
	}

	@Override
	public void caseEOF(final EOF node) {
	}

	/*
	 * we want to see the whole tree. These functions expand it for us, they are
	 * written by Christian Kaufhold and taken from the comp.lang.jave.help
	 * newsgroup
	 */
	public static void expandAll(final JTree tree) {
		final Object root = tree.getModel().getRoot();

		if (root != null) {
			expandAll(tree, new TreePath(root));
		}
	}

	public static void expandAll(final JTree tree, final TreePath path) {
		for (final Iterator<TreePath> i = extremalPaths(tree.getModel(), path, new HashSet<TreePath>()).iterator(); i
				.hasNext();) {
			tree.expandPath(i.next());
		}
	}

	/*
	 * The "extremal paths" of the tree model's subtree starting at path. The
	 * extremal paths are those paths that a) are non-leaves and b) have only
	 * leaf children, if any. It suffices to know these to know all non-leaf
	 * paths in the subtree, and those are the ones that matter for expansion
	 * (since the concept of expan- sion only applies to non-leaves). The
	 * extremal paths of a leaves is an empty collection. This method uses the
	 * usual collection filling idiom, i.e. clear and then fill the collection
	 * that it is given, and for convenience return it. The extremal paths are
	 * stored in the order in which they appear in pre-order in the tree model.
	 */
	public static Collection<TreePath> extremalPaths(final TreeModel data, final TreePath path,
			final Collection<TreePath> result) {
		result.clear();

		if (data.isLeaf(path.getLastPathComponent())) {
			return result; // should really be forbidden (?)
		}

		extremalPathsImpl(data, path, result);

		return result;
	}

	private static void extremalPathsImpl(final TreeModel data, final TreePath path,
			final Collection<TreePath> result) {
		final Object node = path.getLastPathComponent();

		boolean hasNonLeafChildren = false;

		final int count = data.getChildCount(node);

		for (int i = 0; i < count; i++) {
			if (!data.isLeaf(data.getChild(node, i))) {
				hasNonLeafChildren = true;
			}
		}

		if (!hasNonLeafChildren) {
			result.add(path);
		} else {
			for (int i = 0; i < count; i++) {
				final Object child = data.getChild(node, i);

				if (!data.isLeaf(child)) {
					extremalPathsImpl(data, path.pathByAddingChild(child), result);
				}
			}
		}
	}
}
