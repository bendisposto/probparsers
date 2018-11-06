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
        List<Path> cFilePaths = codeGenerator.generate(mchPath, GeneratorMode.C, false, true, null);

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
    public void testLiftBenchmarks() throws Exception {
        testC("liftbenchmarks/LiftExec");
    }


    @Test
    public void testSieveBenchmarks() throws Exception {
        testC("sievebenchmarks/Sieve");
    }

    @Test
    public void testTrafficLightBenchmarks() throws Exception {
        testC("trafficlightbenchmarks/TrafficLightExec");
    }

    @Test
    public void testIncreasingSet() throws Exception {
        testC("setoperationbenchmarks/IncreasingSet");
    }

    @Test
    public void testSetOperation() throws Exception {
        testC("setoperationbenchmarks/SetOperation");
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
    public void testSieveParallel() throws Exception {
        testC("SieveParallel");
    }

    @Test
    public void testSwap() throws Exception {
        testC("Swap");
    }

    @Test
    public void testReset() throws Exception {
        testC("Reset");
    }

    @Test
    public void testManyLocalDeclarations() throws Exception {
        testC("ManyLocalDeclarations");
    }

    @Test
    public void testManyLocalDeclarations2() throws Exception {
        testC("ManyLocalDeclarations2");
    }

    @Test
    public void testPlus() throws Exception {
        testC("arithmetic/Plus");
    }

    @Test
    public void testMinus() throws Exception {
        testC("arithmetic/Minus");
    }

    @Test
    public void testMultiply() throws Exception {
        testC("arithmetic/Multiply");
    }

    @Test
    public void testDivide() throws Exception {
        testC("arithmetic/Divide");
    }


    @Test
    public void testModulo() throws Exception {
        testC("arithmetic/Modulo");
    }

    @Test
    public void testNegative() throws Exception {
        testC("arithmetic/Negative");
    }

    @Ignore
    @Test
    public void testPositive() throws Exception {
        testC("arithmetic/Positive");
    }

    @Test
    public void testSmallNumbers() throws Exception {
        testC("integers/SmallNumbers");
    }

    @Test
    public void testBigNumbers() throws Exception {
        testC("integers/BigNumbers");
    }

    @Test
    public void testAnd() throws Exception {
        testC("logical/And");
    }


    @Test
    public void testOr() throws Exception {
        testC("logical/Or");
    }


    @Test
    public void testImpliesPerformance() throws Exception {
        testC("logical/Implies");
    }


    @Ignore
    @Test
    public void testNot() throws Exception {
        testC("logical/Not");
    }

    @Test
    public void testEquivalent() throws Exception {
        testC("logical/Equivalent");
    }

    @Test
    public void testLess() throws Exception {
        testC("comparison/Less");
    }

    @Test
    public void testLessEqual() throws Exception {
        testC("comparison/LessEqual");
    }

    @Test
    public void testGreater() throws Exception {
        testC("comparison/Greater");
    }


    @Test
    public void testGreaterEqual() throws Exception {
        testC("comparison/GreaterEqual");
    }


    @Test
    public void tessEqual() throws Exception {
        testC("comparison/Equal");
    }


    @Test
    public void testUnequal() throws Exception {
        testC("comparison/Unequal");
    }

    @Test
    public void testCardBig() throws Exception {
        testC("setoperation_big/SetCardBig");
    }

    @Test
    public void testComplementBig() throws Exception {
        testC("setoperation_big/SetComplementBig");
    }

    @Test
    public void testElementOfBig() throws Exception {
        testC("setoperation_big/SetElementOfBig");
    }

    @Test
    public void testIntersectionBig() throws Exception {
        testC("setoperation_big/SetIntersectionBig");
    }

    @Test
    public void testUnionBig() throws Exception {
        testC("setoperation_big/SetUnionBig");
    }

    @Test
    public void testCardSmall() throws Exception {
        testC("setoperation_small/SetCardSmall");
    }

    @Test
    public void testComplementSmall() throws Exception {
        testC("setoperation_small/SetComplementSmall");
    }

    @Test
    public void testElementOfSmall() throws Exception {
        testC("setoperation_small/SetElementOfSmall");
    }

    @Test
    public void testIntersectionSmall() throws Exception {
        testC("setoperation_small/SetIntersectionSmall");
    }

    @Test
    public void testUnionSmall() throws Exception {
        testC("setoperation_small/SetUnionSmall");
    }

    @Test
    public void testRangeBig() throws Exception {
        testC("range_big/RangeBig");
    }


    @Test
    public void testRangeCardBig() throws Exception {
        testC("range_big/RangeCardBig");
    }

    @Test
    public void testRangeComplementBig() throws Exception {
        testC("range_big/RangeComplementBig");
    }

    @Test
    public void testRangeElementOfBig() throws Exception {
        testC("range_big/RangeElementOfBig");
    }

    @Test
    public void testRangeIntersectionBig() throws Exception {
        testC("range_big/RangeIntersectionBig");
    }

    @Test
    public void testRangeUnionBig() throws Exception {
        testC("range_big/RangeUnionBig");
    }

    @Test
    public void testRangeSmall() throws Exception {
        testC("range_small/RangeSmall");
    }


    @Test
    public void testRangeCardSmall() throws Exception {
        testC("range_small/RangeCardSmall");
    }

    @Test
    public void testRangeComplementSmall() throws Exception {
        testC("range_small/RangeComplementSmall");
    }

    @Test
    public void testRangeElementOfSmall() throws Exception {
        testC("range_small/RangeElementOfSmall");
    }

    @Test
    public void testRangeIntersectionSmall() throws Exception {
        testC("range_small/RangeIntersectionSmall");
    }

    @Test
    public void testRangeUnionSmall() throws Exception {
        testC("range_small/RangeUnionSmall");
    }

    @Test
    public void testTrafficLight() throws Exception {
        testC("TrafficLight");
    }

    @Test
    public void testChoice() throws Exception {
        testC("Choice");
    }

    @Test
    public void testAssert() throws Exception {
        testC("Assert");
    }

    @Test
    public void testCruiseController1() throws Exception {
        testC("Cruise_finite1");
    }

    @Test
    public void testCruiseControllerk() throws Exception {
        testC("Cruise_finite_k");
    }

    private void cleanUp(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
