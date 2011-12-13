package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.IOException;

public class NoContentProvider implements IFileContentProvider {

	public String getFileContent(final String filename) throws IOException {
		throw new IOException("Loading of file content not supported.");
	}

	public File getFile(String fileName) {
		return null;
	}
	
}
