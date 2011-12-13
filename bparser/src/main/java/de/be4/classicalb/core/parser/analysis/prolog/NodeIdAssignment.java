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
	private Map<Node, Integer> identifiers = new HashMap<Node, Integer>();
	private ArrayList<Node> nodes = new ArrayList<Node>(1000);
	private int currentIdentifier = 0;

	private int current_file_number = -1;
	private Map<Node, Integer> fileNumbers = new HashMap<Node, Integer>();

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
		return identifiers.get(node);
	}

	public Node lookupById(int id) {
		Node result;
		try {
			result = nodes.get(id);
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("Unknown id " + id);
		}
		if (result == null) {
			throw new RuntimeException("Unknown id " + id);
		}
		return result;
	}

	public int lookupFileNumber(Node node) {
		Integer fileNumber = fileNumbers.get(node);
		return fileNumber == null ? -1 : fileNumber;
	}

	@Override
	public void defaultIn(Node node) {
		synchronized (identifiers) {
			identifiers.put(node, currentIdentifier);
			nodes.add(node);
			if (current_file_number > 0) {
				fileNumbers.put(node, current_file_number);
			}
			currentIdentifier++;
		}
	}
}
