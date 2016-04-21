package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;

import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Node;

public class MachineReference {

	private final String name;
	private final Node node;
	private final String pragmaPath;

	public MachineReference(String name, Node node) {
		this.name = name;
		this.node = node;
		this.pragmaPath = null;
	}

	public MachineReference(String name, Node node, String path)
			throws CheckException {
		this.name = name;
		this.node = node;
		this.pragmaPath = path;

		File file = new File(path);
		if (file.isDirectory()) {
			throw new CheckException(
					"Declared path in file pragma is and an directory and not a file: "
							+ path, node);
		} else {
			String fileName = file.getName();
			String baseName = fileName.substring(0, fileName.lastIndexOf("."));
			if (!baseName.equals(name)) {
				throw new CheckException(
						"Declared name in file pragma does not match with the name of the machine referenced: "
								+ baseName, node);
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return pragmaPath;
	}

	public Node getNode() {
		return node;
	}

	@Override
	public String toString() {
		return name;
	}

}