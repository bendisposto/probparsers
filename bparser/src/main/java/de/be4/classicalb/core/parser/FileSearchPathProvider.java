package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by David Schneider on 27.01.15.
 */
public class FileSearchPathProvider implements Iterable<File> {
    private final String fileName;
    private ArrayList<String> searchPath = new ArrayList<String>();

    public FileSearchPathProvider(String fileName) {
        this(".", fileName);
    }

    public FileSearchPathProvider(String prefix, String fileName) {
        this.fileName = fileName;

        String probPath = System.getenv("PROBPATH");
        searchPath.add(prefix);
        if(probPath != null) {
            for(String p : probPath.split(":")) {
                searchPath.add(p);
            }
        }

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

    private String get(int idx) {
        return this.searchPath.get(idx);
    }

    public File resolve() throws FileNotFoundException {
        for(File f : this) {
            if(f.exists() && f.isFile()) {
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
            String base = this.provider.get(this.idx);
            this.idx += 1;
            Path path = FileSystems.getDefault().getPath(base, this.provider.getFilename());
            return new java.io.File(path.toString());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
