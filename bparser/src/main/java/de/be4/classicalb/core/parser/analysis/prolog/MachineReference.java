package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;

import de.be4.classicalb.core.parser.exceptions.VisitorException;
import de.be4.classicalb.core.parser.node.Node;

public class MachineReference {

	private final String name;
	private final Node node;
	private String filePath;
	private String directoryPath;

	public MachineReference(String name, Node node) {
		this.name = name;
		this.node = node;
	}

	public MachineReference(String name, Node node, String path) throws VisitorException {
		this.name = name;
		this.node = node;
		this.filePath = path;

		File file = new File(path);
		if (file.isDirectory()) {
			throw new VisitorException(node, "Declared path in file pragma is an directory and not a file: " + path);
		} else {
			String fileName = file.getName();
			String baseName = fileName.substring(0, fileName.lastIndexOf("."));
			if (!baseName.equals(name)) {
				throw new VisitorException(node,
						"Declared name in file pragma does not match with the name of the machine referenced: " + name
								+ " vs. " + baseName);
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public String getPath() {
		return this.filePath;
	}

	public Node getNode() {
		return this.node;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getDirectoryPath() {
		return this.directoryPath;
	}

}