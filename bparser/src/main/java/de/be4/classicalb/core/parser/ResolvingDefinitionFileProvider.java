package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ResolvingDefinitionFileProvider extends CachingDefinitionFileProvider {
    public ResolvingDefinitionFileProvider(final File parentFile) {
        super(parentFile);
    }

    public File getFile(final String filename) {
        // TODO this is a bit redundant
        File o = super.getFile(filename);
        if(o.exists() && o.isFile()) {
            return o;
        }

        for(File f : new FileSearchPathProvider(filename)) {
            if(f.exists() && f.isFile()) {
                return f;
            }
        }
        // TODO should raise exception when we know the file is not available
        // returning file object to keep interface
        return new File(filename);
    }
}
