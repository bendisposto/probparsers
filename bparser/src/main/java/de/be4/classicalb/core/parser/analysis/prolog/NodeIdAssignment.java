package de.be4.classicalb.core.parser.analysis.prolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;

/**
 * This class implements functionality to assign identifiers to all nodes in a
 * syntax tree. Multiple syntax trees can be used with one instance of this
 * class to guarantee unique identifiers.
 * 
 * @author plagge
 */
public class NodeIdAssignment extends DepthFirstAdapter {
	private Map<Node, Integer> nodeToIdentifierMap = new HashMap<>();
	private ArrayList<Node> nodes = new ArrayList<>(1000);
	private int currentIdentifier = 0;

	private int current_file_number = -1;
	private Map<Node, Integer> nodeToFileNumberMap = new HashMap<Node, Integer>();

	/**
	 * Assign identifiers to all elements of the syntax tree.
	 * 
	 * @param node
	 *            The root node of the machine.
	 */
	public void assignIdentifiers(Node node) {
		assignIdentifiers(-1, node);
	}

	/**
	 * Assign identifiers to all elements of the syntax tree.
	 * 
	 * @param fileNumber
	 *            the file number which will be assigned to <code>node</code>
	 *            and its child nodes
	 * @param node
	 *            The root node of the machine.
	 */
	public void assignIdentifiers(int fileNumber, Node node) {
		if (fileNumber < 1) {
			throw new IllegalArgumentException("File number should be >= 1");
		}
		this.current_file_number = fileNumber;
		node.apply(this);
		this.current_file_number = -1;
	}

	/**
	 * Looks up the ID of the given node.
	 * 
	 * @param node
	 *            The node, of which we want to have the ID.
	 * @return The ID of the node, <code>null</code> if no ID can be found.
	 */
	public Integer lookup(Node node) {
		return nodeToIdentifierMap.get(node);
	}

	public Node lookupById(int id) {
		Node result;
		try {
			result = nodes.get(id);
		} catch (IndexOutOfBoundsException e) {
			throw new AssertionError("Unknown id " + id, e);
		}
		if (result == null) {
			throw new AssertionError("Unknown id " + id);
		}
		return result;
	}

	public int lookupFileNumber(Node node) {
		Integer fileNumber = nodeToFileNumberMap.get(node);
		return fileNumber == null ? -1 : fileNumber;
	}

	@Override
	public void defaultIn(Node node) {
		synchronized (nodeToIdentifierMap) {
			nodeToIdentifierMap.put(node, currentIdentifier);
			nodes.add(node);
			if (current_file_number > 0) {
				nodeToFileNumberMap.put(node, current_file_number);
			}
			currentIdentifier++;
		}
	}
}
