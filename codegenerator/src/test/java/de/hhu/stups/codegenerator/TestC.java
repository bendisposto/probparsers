package de.hhu.stups.codegenerator;


import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by fabian on 05.08.18.
 */

public class TestC {

    public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        int size;
        byte[] buffer = new byte[1024];
        while ((size = inputStream.read(buffer)) != -1)
            outputStream.write(buffer, 0, size);
    }

    public void testC(String machine) throws Exception {
        Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
                .getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
        CodeGenerator codeGenerator = new CodeGenerator();
        Set<Path> cFilePaths = codeGenerator.generate(mchPath, GeneratorMode.C, false, true);

        //cFilePaths.forEach(path -> cleanUp(path.toString()));
    }

    @Test
    public void testExample() throws Exception {
        testC("Example");
    }

    @Test
    public void testOperation() throws Exception {
        testC("Operation");
    }

    @Test
    public void testLocalDeclaration() throws Exception {
        testC("LocalDeclaration");
    }

    @Ignore
    @Test
    public void testRefinement() throws Exception {
        // TODO VAR-Node
        testC("RefinementMachine");
    }

    @Test
    public void testEnumSets() throws Exception {
        testC("EnumSets");
    }

    @Test
    public void testNameCollision() throws Exception {
        testC("NameCollision");
    }

    @Test
    public void testWhile() throws Exception {
        testC("While");
    }

    @Test
    public void testInterval() throws Exception {
        testC("Interval");
    }


    @Test
    public void testPair() throws Exception {
        testC("Pair");
    }

    @Test
    public void testIfAndPredicates() throws Exception {
        testC("IfAndPredicates");
    }

    @Test
    public void testImplies() throws Exception {
        testC("Implies");
    }

    @Test
    public void testEquivalence() throws Exception {
        testC("Equivalence");
    }

    @Test
    public void testBooleanPredicate() throws Exception {
        testC("BooleanPredicate");
    }

    @Ignore
    @Test
    public void testRecords() throws Exception {
        testC("Records");
    }

    @Ignore
    @Test
    public void testNondeterminism() throws Exception {
        testC("Nondeterminism");
    }

    @Test
    public void testMapFunction() throws Exception {
        testC("MapFunction");
    }

    @Test
    public void testRelationImage() throws Exception {
        testC("RelationImage");
    }

    @Test
    public void testEmptySet() throws Exception {
        testC("EmptySet");
    }

    @Test
    public void testSetUnion() throws Exception {
        testC("SetUnion");
    }


    @Test
    public void testCounter() throws Exception {
        testC("Counter");
    }

    @Test
    public void testBakery0() throws Exception {
        testC("Bakery0");
    }

    @Ignore
    @Test
    public void testGCD() throws Exception {
        // TODO
        testC("GCD");
    }

    @Test
    public void testACounter() throws Exception {
        testC("ACounter");
    }

    @Test
    public void testLift() throws Exception {
        testC("Lift");
    }

    @Ignore
    @Test
    public void testTravelAgency() throws Exception {
        testC("TravelAgency");
    }

    @Ignore
    @Test
    public void testPhonebook() throws Exception {
        // TODO
        testC("phonebook");
    }

    @Ignore
    @Test
    public void testPhonebook6() throws Exception {
        // TODO
        testC("phonebook6");
    }

    @Ignore
    @Test
    public void testSum() throws Exception {
        testC("Sum");
    }

    @Ignore
    @Test
    public void testRecursion() throws Exception {
        //Correct exception
        testC("recursion/Sum1");
    }

    @Test
    public void testProject() throws Exception {
        testC("project1/A");
    }

    @Test
    public void testProject2() throws Exception {
        testC("project2/MachineA");
    }

    @Test
    public void testSieve() throws Exception {
        testC("Sieve");
    }

    @Test
    public void testReset() throws Exception {
        testC("Reset");
    }

    @Test
    public void testManyLocalDeclarations() throws Exception {
        testC("ManyLocalDeclarations");
    }

    private void cleanUp(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

}
