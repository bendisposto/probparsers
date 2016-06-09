package de.be4.classicalb.core.parser;

public interface IDefinitionFileProvider extends IFileContentProvider {
	/**
	 * Returns already parsed definitions for the parameter (file reference) if
	 * available. If the definition file has not yet been parsed or the
	 * definitions are not available for other reasons, <code>null</code> is
	 * returned.
	 * 
	 * @param filename	name of an input file
	 * @return the already parsed definitions
	 */
	public IDefinitions getDefinitions(final String filename);

	/**
	 * Stores the definitions for this file name in chache.
	 * 
	 * @param filename	name of an input file
	 * @param definitions	the definitions to be stored
	 */
	public void storeDefinition(final String filename,
			final IDefinitions definitions);
}
