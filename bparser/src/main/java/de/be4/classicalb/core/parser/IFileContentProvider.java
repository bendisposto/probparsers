package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.IOException;

public interface IFileContentProvider {

	/**
	 * <p>
	 * Returns the content of the file described by the parameter
	 * <code>filename</code>. The content may be necessary in the process of
	 * parsing referenced definition files, for example.
	 * </p>
	 * <p>
	 * If the <code>filename</code> is relative and not absolute the
	 * implementing class is responsible to resolve its position.
	 * </p>
	 * 
	 * @param directory the directory to search the file
	 * @param fileName the name of file without file extension
	 * @throws IOException	when the given file cannot be found
	 * @return 	the content of the file
	 */
	public String getFileContent(final File directory, final String fileName) throws IOException;

	public File getFile(final File directory, String fileName) throws IOException;
}
