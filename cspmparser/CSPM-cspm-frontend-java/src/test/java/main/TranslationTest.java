package frege.main;

import org.junit.Test;

import frege.runtime.WrappedCheckedException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static frege.language.CSPM.TranslateToProlog.*;
import static frege.main.ExecCommand.xmlString;
import static frege.main.FregeInterface.evaluateIOFunction;
import static frege.main.FregeInterface.just;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TranslationTest {

    @Test
    public void expressionToPrologTermTest() {
        String prologTerm = (String) evaluateIOFunction(translateExpToPrologTerm(just("src/test/resources/very_simple.csp"), "N"));
        assertEquals("'val_of'('N','src_span'(6,28,6,29,163,1)).", prologTerm);
    }

    @Test
    public void declarationToPrologTermTest() {
        String prologTerm = (String) evaluateIOFunction(translateDeclToPrologTerm(just("src/test/resources/very_simple.csp"), "datatype D = F"));
        assertEquals("'dataTypeDef'('D',['constructor'('F')]).", prologTerm);
    }

    @Test
    public void translateTest() {
        try {
            Files.list(Paths.get("src/test/resources/cspm")).forEach(inputFileName -> {
                translateToPrologTest(inputFileName);
                translateToXmlTest(inputFileName);
            });
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private void translateToPrologTest(Path inputFileName) {
        String prologCode = (String) frege.main.FregeInterface.evaluateIOFunction(translateToPrologStr(inputFileName.toString()));
        try {
            String expected = Files.readAllLines(Paths.get("src/test/resources/prolog/" + inputFileName.getFileName().toString() + ".pl")).stream()
                                   .collect(Collectors.joining("\n"));
            assertEquals(expected, prologCode);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private void translateToXmlTest(Path inputFileName) {
        String expected = "";
        try {
            expected = Files.readAllLines(Paths.get("src/test/resources/xml/" + inputFileName.getFileName().toString() + ".xml")).stream()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
        String xml = "";
        try {
            xml = (String) frege.main.FregeInterface.evaluateIOFunction(xmlString(inputFileName.toString(), false));
        } catch (WrappedCheckedException e) {
            System.err.println(e);
        }
        assertEquals(expected, xml);
    }

}
