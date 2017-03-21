package de.be4.classicalb.core.parser.rules.project;

import java.io.File;

import de.be4.classicalb.core.parser.node.Node;

public class RulesMachineReference {
	private final String name;
	private final Node node;
	private final File file;

	public RulesMachineReference(File file, String name, Node node) {
		this.name = name;
		this.node = node;
		this.file = file;
	}

	public File getFile() {
		return this.file;
	}

	public String getName() {
		return this.name;
	}

	public Node getNode() {
		return this.node;
	}

}
