package de.hhu.stups.codegenerator;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public void testAbstractMachine() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/AbstractMachine.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/AbstractMachine.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();

        /*Process process2 = Runtime.getRuntime().exec("java -classpath .:btypes.jar AbstractMachine");
        writeInputToSystem(process2.getErrorStream());
        writeInputToOutput(process2.getErrorStream(), process2.getOutputStream());

        process2.waitFor();*/
    }

    @Test
    public void testAbstractMachine3() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/AbstractMachine3.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/AbstractMachine3.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testAbstractMachine4() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/AbstractMachine4.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/AbstractMachine4.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testAbstractMachine6() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/AbstractMachine6.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/AbstractMachine6.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testCounter() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/Counter.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/Counter.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testBakery0() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/Bakery0.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/Bakery0.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testGCD() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/GCD.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/GCD.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testACounter() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/ACounter.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/ACounter.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

    @Test
    public void testLift() throws Exception {
        Path path = Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/Lift.mch").toURI());
        CodeGenerator.generate(path, GeneratorMode.JAVA);

        Process process1 = Runtime.getRuntime().exec("javac build/resources/test/de/hhu/stups/codegenerator/Lift.java -cp btypes.jar");

        writeInputToSystem(process1.getErrorStream());
        writeInputToOutput(process1.getErrorStream(), process1.getOutputStream());
        process1.waitFor();
    }

}
