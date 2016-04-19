package de.be4.classicalb.core.parser.analysis.prolog;

import de.be4.classicalb.core.parser.node.Node;

public class MachineReference {

	private final String name;
	private final String path;
	private final Node node;
	
	public MachineReference(String name, Node node){
		this(name, node, null);
	}
	
	public MachineReference(String name, Node node, String path){
		this.name = name;
		this.node = node;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Node getNode() {
		return node;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	
}