package de.hhu.stups.codegenerator;


import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestClojure {

    public static void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        int size;
        byte[] buffer = new byte[1024];
        while ((size = inputStream.read(buffer)) != -1)
            outputStream.write(buffer, 0, size);
    }

    public void testClojure(String machine) throws Exception {
        Path mchPath = Paths.get(CodeGenerator.class.getClassLoader()
                .getResource("de/hhu/stups/codegenerator/" + machine + ".mch").toURI());
        CodeGenerator codeGenerator = new CodeGenerator();
        List<Path> cljFilePaths = codeGenerator.generate(mchPath, GeneratorMode.CLJ, false, true, null);

        //cljFilePaths.forEach(path -> cleanUp(path.toString()));
    }

    @Test
    public void testExample() throws Exception {
        testClojure("Example");
    }

    @Test
    public void testOperation() throws Exception {
        testClojure("Operation");
    }

    @Test
    public void testLocalDeclaration() throws Exception {
        testClojure("LocalDeclaration");
    }

    @Ignore
    @Test
    public void testRefinement() throws Exception {
        // TODO VAR-Node
        testClojure("RefinementMachine");
    }

    @Test
    public void testEnumSets() throws Exception {
        testClojure("EnumSets");
    }

    @Test
    public void testNameCollision() throws Exception {
        testClojure("NameCollision");
    }

    @Test
    public void testWhile() throws Exception {
        testClojure("While");
    }

    @Test
    public void testInterval() throws Exception {
        testClojure("Interval");
    }


    @Test
    public void testPair() throws Exception {
        testClojure("Pair");
    }

    @Test
    public void testIfAndPredicates() throws Exception {
        testClojure("IfAndPredicates");
    }

    @Test
    public void testImplies() throws Exception {
        testClojure("Implies");
    }

    @Test
    public void testEquivalence() throws Exception {
        testClojure("Equivalence");
    }

    @Test
    public void testBooleanPredicate() throws Exception {
        testClojure("BooleanPredicate");
    }

    @Ignore
    @Test
    public void testRecords() throws Exception {
        testClojure("Records");
    }

    @Ignore
    @Test
    public void testNondeterminism() throws Exception {
        testClojure("Nondeterminism");
    }

    @Test
    public void testMapFunction() throws Exception {
        testClojure("MapFunction");
    }

    @Test
    public void testRelationImage() throws Exception {
        testClojure("RelationImage");
    }

    @Test
    public void testEmptySet() throws Exception {
        testClojure("EmptySet");
    }

    @Test
    public void testSetUnion() throws Exception {
        testClojure("SetUnion");
    }


    @Test
    public void testCounter() throws Exception {
        testClojure("Counter");
    }

    @Test
    public void testBakery0() throws Exception {
        testClojure("Bakery0");
    }

    @Ignore
    @Test
    public void testGCD() throws Exception {
        // TODO
        testClojure("GCD");
    }

    @Test
    public void testACounter() throws Exception {
        testClojure("ACounter");
    }

    @Test
    public void testLift() throws Exception {
        testClojure("Lift");
    }

    @Ignore
    @Test
    public void testTravelAgency() throws Exception {
        testClojure("TravelAgency");
    }

    @Ignore
    @Test
    public void testPhonebook() throws Exception {
        // TODO
        testClojure("phonebook");
    }

    @Ignore
    @Test
    public void testPhonebook6() throws Exception {
        // TODO
        testClojure("phonebook6");
    }

    @Ignore
    @Test
    public void testSum() throws Exception {
        testClojure("Sum");
    }

    @Ignore
    @Test
    public void testRecursion() throws Exception {
        //Correct exception
        testClojure("recursion/Sum1");
    }

    @Test
    public void testProject() throws Exception {
        testClojure("project1/A");
    }

    @Test
    public void testProject2() throws Exception {
        testClojure("project2/MachineA");
    }

    @Test
    public void testSieve() throws Exception {
        testClojure("Sieve");
    }

    @Test
    public void testReset() throws Exception {
        testClojure("Reset");
    }

    @Test
    public void testManyLocalDeclarations() throws Exception {
        testClojure("ManyLocalDeclarations");
    }

    @Test
    public void testCruiseController1() throws Exception {
        testClojure("Cruise_finite1");
    }

    @Test
    public void testCruiseControllerk() throws Exception {
        testClojure("Cruise_finite_k");
    }

    private void cleanUp(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

}
