package de.be4.classicalb.core.parser;

public interface IDefinitionFileProvider extends IFileContentProvider {
	/**
	 * Returns already parsed definitions for the parameter (file reference) if
	 * available. If the definition file has not yet been parsed or the
	 * definitions are not available for other reasons, <code>null</code> is
	 * returned.
	 * 
	 * @param filename
	 * @return
	 */
	public IDefinitions getDefinitions(final String filename);

	/**
	 * Stores the definitions for this file name in chache.
	 * 
	 * @param filename
	 * @param definitions
	 */
	public void storeDefinition(final String filename,
			final IDefinitions definitions);
}
