package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.FileSearchPathProvider;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Node;

public class Reference {
	private final String name;
	private final Node node;
	private String filePath;
	private String directoryPath;
	private final List<String> pathList = new ArrayList<>();
	private File file;

	public Reference(File parentDirectory, String name, Node node, List<String> pathList) throws CheckException {
		this.name = name;
		this.node = node;
		this.pathList.addAll(pathList);

		if (filePath == null) {
			file = lookupFile(parentDirectory, this, pathList);
		} else {
			File p = new File(filePath);
			if (p.isAbsolute()) {
				file = p;
			} else {
				file = new File(directoryPath, filePath);
			}
		}

	}

	private static final String[] SUFFICES = new String[] { ".rmch", ".mch" };

	private File lookupFile(final File parentMachineDirectory, final Reference machineRef, List<String> paths)
			throws CheckException {
		for (final String suffix : SUFFICES) {
			try {
				final String directoryString = machineRef.getDirectoryPath() != null ? machineRef.getDirectoryPath()
						: parentMachineDirectory.getAbsolutePath();
				final File file = new FileSearchPathProvider(directoryString, machineRef.getName() + suffix, paths)
						.resolve();
				return file;
			} catch (FileNotFoundException e) {
				// could not resolve the combination of prefix, machineName and
				// suffix, trying next one
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Machine not found: '");
		sb.append(machineRef.getName());
		sb.append("'");
		throw new CheckException(sb.toString(), machineRef.getNode());
	}

	public Reference(String name, Node node, String path) throws CheckException {
		this.name = name;
		this.node = node;
		this.filePath = path;

		File file = new File(path);
		if (file.isDirectory()) {
			throw new CheckException("Declared path in file pragma is an directory and not a file: " + path, node);
		} else {
			String fileName = file.getName();
			String baseName = fileName.substring(0, fileName.lastIndexOf("."));
			if (!baseName.equals(name)) {
				throw new CheckException(
						"Declared name in file pragma does not match with the name of the machine referenced: " + name
								+ " vs. " + baseName + path,
						node);
			}
		}
	}

	public File getFile() {
		return this.file;
	}

	public List<String> getPathList() {
		return this.pathList;
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
