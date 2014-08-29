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
	 * @param filename
	 * @return
	 */
	public String getFileContent(final String filename) throws IOException;

	public File getFile(String fileName);
}
