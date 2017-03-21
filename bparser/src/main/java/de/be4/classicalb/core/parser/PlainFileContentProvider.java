package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.util.Utils;

import java.io.File;
import java.io.IOException;

public class PlainFileContentProvider implements IFileContentProvider {

	public PlainFileContentProvider() {
	}

	@Override
	public String getFileContent(File directory, String filename)
			throws IOException {
		// TODO caching could help for speed up if files are used more than
		// once
		final File file;
		file = this.getFile(directory, filename);
		return Utils.readFile(file);
	}

	public File getFile(final File directory, final String filename)
			throws IOException {
		FileSearchPathProvider provider;
		if (directory == null) {
			provider = new FileSearchPathProvider(filename);
		} else {
			String parentPath;
			parentPath = directory.getCanonicalPath();
			provider = new FileSearchPathProvider(parentPath, filename);
		}
		return provider.resolve();
	}
}
