package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlainFileContentProvider implements IFileContentProvider {

	/**
	 * Please use {@link #PlainFileContentProvider(File)} as constructor to
	 * define a parent directory for the file references.
	 */
	public PlainFileContentProvider() {
	}

	
	@Override
	public String getFileContent(File directory, String filename)
			throws IOException {
		// TODO caching could help for speed up if files are used more than
		// once
		final File file;
		file = getFile(directory, filename);
		return readFileContent(file);
	}

	public File getFile(final File directory, final String filename) throws IOException {
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

	public static String readFileContent(final File file)
			throws FileNotFoundException, IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(file));

		final StringBuilder builder = new StringBuilder();
		final char[] buffer = new char[1024];
		int read;
		while ((read = inputStreamReader.read(buffer)) >= 0) {
			builder.append(String.valueOf(buffer, 0, read));
		}

		inputStreamReader.close();

		return builder.toString().replaceAll("\r\n", "\n");
	}


}
