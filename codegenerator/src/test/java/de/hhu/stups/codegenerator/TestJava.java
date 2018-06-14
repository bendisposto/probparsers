package de.hhu.stups.codegenerator;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by fabian on 31.05.18.
 */

public class TestJava {

    public static void writeInputToSystem(InputStream inputStream) throws IOException {
        writeInputToOutput(inputStream, System.err);
    }

    public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        int size;
        byte[] buffer = new byte[1024];
        while ((size = inputStream.read(buffer)) != -1) outputStream.write(buffer, 0, size);
    }

    public void testJava(String machineFileName) throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/" + machineFileName + ".mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/" + machineFileName + ".java " + "-cp btypes.jar");

        writeInputToSystem(process.getErrorStream());
        writeInputToOutput(process.getErrorStream(), process.getOutputStream());
        process.waitFor();
    }

    @Test
    public void testAbstractMachine() throws Exception {
        testJava("AbstractMachine");
    }

    @Test
    public void testAbstractMachine3() throws Exception {
        testJava("AbstractMachine3");
    }

    @Test
    public void testAbstractMachine4() throws Exception {
        testJava("AbstractMachine4");
    }

    @Ignore
    @Test
    public void testAbstractMachine5() throws Exception {
    	//TODO machine does not exist
        testJava("AbstractMachine5");
    }

    @Test
    public void testAbstractMachine10() throws Exception {
        testJava("AbstractMachine10");
    }


    @Test
    public void testAbstractMachine6() throws Exception {
        testJava("AbstractMachine6");
    }

    @Test
    public void testCounter() throws Exception {
        testJava("Counter");
    }

    @Test
    public void testBakery0() throws Exception {
        testJava("Bakery0");
    }

    @Ignore
    @Test
    public void testGCD() throws Exception {
        //TODO parallel substitution is currently not supported
    	testJava("GCD");
    }

    @Test
    public void testACounter() throws Exception {
        testJava("ACounter");
    }

    @Test
    public void testLift() throws Exception {
        testJava("Lift");
    }

    @Test
    public void testProject() throws Exception {
        testJava("project1/A");
    }
    
}
