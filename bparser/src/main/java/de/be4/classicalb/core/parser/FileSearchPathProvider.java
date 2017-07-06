package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FileSearchPathProvider implements Iterable<File> {
	private final String fileName;
	private ArrayList<String> searchPath = new ArrayList<>();

	public FileSearchPathProvider(String fileName) {
		this(".", fileName);
	}

	public FileSearchPathProvider(String prefix, String fileName) {
		this.fileName = fileName;

		searchPath.add(prefix);
		searchPath.addAll(getLibraryPath());
	}

	public FileSearchPathProvider(String prefix, String fileName, List<String> paths) {
		this.fileName = fileName;

		searchPath.add(prefix);
		searchPath.addAll(paths);
		searchPath.addAll(getLibraryPath());
	}

	private ArrayList<String> getLibraryPath() {
		ArrayList<String> result = new ArrayList<>();
		// User provided stdlib search path
		final String stdlib = System.getProperty("prob.stdlib");
		// prob.home is set by prob 2.0 to the directory of the prob binary
		String home = System.getProperty("prob.home");

		if (stdlib != null) {
			Collections.addAll(result, stdlib.split(File.pathSeparator));
		}
		if (home != null) {
			home = home + File.separator + "stdlib";
			// Simple attempt to avoid adding home twice to the search path
			if (!result.contains(home)) {
				result.add(home);
			}
		}

		if (result.isEmpty()) {
			result.add("." + File.separator + "stdlib");
		}
		return result;
	}

	@Override
	public SearchPathIterator iterator() {
		return new SearchPathIterator(this);
	}

	public int size() {
		return this.searchPath.size();
	}

	public String getFilename() {
		return fileName;
	}

	public File resolve() throws FileNotFoundException {
		for (File f : this) {
			if (f.exists() && f.isFile()) {
				return f;
			}
		}
		throw new FileNotFoundException(fileName);
	}

	private class SearchPathIterator implements Iterator<File> {

		private final FileSearchPathProvider provider;
		private int idx;

		public SearchPathIterator(FileSearchPathProvider provider) {
			this.idx = 0;
			this.provider = provider;
		}

		@Override
		public boolean hasNext() {
			return this.idx < this.provider.size();
		}

		@Override
		public File next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			String base = get(this.idx);
			this.idx += 1;
			Path path = FileSystems.getDefault().getPath(base, this.provider.getFilename());
			return new java.io.File(path.toString());
		}

		private String get(int idx) {
			return FileSearchPathProvider.this.searchPath.get(idx);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
