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
        File f = super.getFile(filename);
        if(f.exists() && f.isFile()) {
            return f;
        }
        try {
            return resolve(filename);
        } catch (IOException e) {
            return f;
        }
    }

    private File resolve(String fileName) throws FileNotFoundException {
        String probPath = System.getenv("PROBPATH");
        if(probPath == null) {
            probPath = ".";
        }

        for (String s : probPath.split(":")) {
            Path path = FileSystems.getDefault().getPath(s, fileName);
            java.io.File f = new java.io.File(path.toString());
            if(f.exists() && f.isFile()) {
                return f;
            }
        }
        throw new FileNotFoundException();
    }
}
