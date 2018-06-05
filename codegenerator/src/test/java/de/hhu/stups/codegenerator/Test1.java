package de.hhu.stups.codegenerator;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by fabian on 31.05.18.
 */

public class Test1 {

    public static void writeInputToSystem(InputStream inputStream) throws IOException {
        writeInputToOutput(inputStream, System.err);
    }

    public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        int size;
        byte[] buffer = new byte[1024];
        while ((size = inputStream.read(buffer)) != -1) outputStream.write(buffer, 0, size);
    }

    @Test
    public void testAbstractMachine() throws URISyntaxException, CodeGenerationException, IOException, InterruptedException {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/AbstractMachine.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        //Runtime.getRuntime().exec("mkdir fabianvu");
        Process process = Runtime.getRuntime().exec("javac /build/resources/test/de/hhu/stups/codegenerator/AbstractMachine.java");
        writeInputToSystem(process.getErrorStream());


        //String outPath = path.toFile().getName().replace(".scala", ".out");
        //writeInputToOutput(process.getInputStream(), new FileOutputStream(outPath));*/
        //process.waitFor();
    }

}
