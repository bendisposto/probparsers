package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlainFileContentProvider implements IFileContentProvider {

	private final File parentFile;

	/**
	 * Please use {@link #PlainFileContentProvider(File)} as constructor to
	 * define a parent directory for the file references.
	 */
	public PlainFileContentProvider() {
		parentFile = null;
	}

	/**
	 * Creates content provider which uses the <code>parentFile</code>'s
	 * directory as starting point for relative file references.
	 * 
	 * @param parentFile
	 */
	public PlainFileContentProvider(final File parentFile) {
		this.parentFile = parentFile;
	}

	public String getFileContent(final String filename) throws IOException {
		// TODO caching could help for speed up if files are used more than
		// once
		final File file;
		file = getFile(filename);
		return readFileContent(file);
	}

	public File getFile(final String filename) {
		final File file;
		if (parentFile == null) {
			file = new File(filename);
		} else {
			final File parentPath = parentFile.isDirectory() ? parentFile
					: parentFile.getParentFile();
			file = new File(parentPath, filename);
		}
		return file;
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

		return builder.toString().replaceAll("\r\n", "\n");
	}

}
