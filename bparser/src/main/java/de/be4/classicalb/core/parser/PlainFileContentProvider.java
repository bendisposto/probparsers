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
		FileSearchPathProvider provider;
		if (parentFile == null) {
			provider = new FileSearchPathProvider(filename);
		} else {
			final String parentPath = parentFile.isDirectory() ? parentFile.getPath()
					: parentFile.getParent();
			provider = new FileSearchPathProvider(parentPath, filename);
		}
		for(File f : provider) {
			if(f.exists() && f.isFile()) {
				return f;
			}
		}
		// TODO should raise exception when we know the file is not available
		// returning file object to keep interface
		return new File(filename);
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
