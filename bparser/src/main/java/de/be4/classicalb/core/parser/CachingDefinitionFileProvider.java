package de.be4.classicalb.core.parser;

import java.util.HashMap;
import java.util.Map;

public class CachingDefinitionFileProvider extends PlainFileContentProvider
		implements IDefinitionFileProvider {

	private final Map<String, IDefinitions> store = new HashMap<String, IDefinitions>();

	/**
	 * s. {@link PlainFileContentProvider#PlainFileContentProvider()}
	 */
	public CachingDefinitionFileProvider() {
		super();
	}

	public IDefinitions getDefinitions(final String filename) {
		return store.get(filename);
	}

	public void storeDefinition(final String filename,
			final IDefinitions definitions) {
		store.put(filename, definitions);
	}
}
