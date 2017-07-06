package de.prob.cliparser;

import frege.language.CSPM.TranslateToProlog;
import frege.main.ExecCommand;
import frege.main.FregeInterface;
import frege.prelude.PreludeBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static de.prob.cliparser.CliBParser.print;

/**
 * Methods for communication with Frege CSP parser.
 * @author Markus Brenneis
 */
class FregeCspParser {
    static void addUnicode(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputCspFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(ExecCommand.addUnicode(inputCspFileName, outputCspFileName));
            print("frege_facts(ok).\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void removeUnicode(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputCspFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(ExecCommand.removeUnicode(inputCspFileName, outputCspFileName));
            print("frege_facts(ok).\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void cspToProlog(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputPlFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(TranslateToProlog.translateToProlog(inputCspFileName, outputPlFileName));
            String prologCode = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateToPrologStr(inputCspFileName));
            String listOfFacts = getListOfFacts(prologCode);
            print("frege_facts(" + listOfFacts + ").\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void translateCspDeclarationToProlog(BufferedReader in) {
        try {
            String cspDeclaration = in.readLine();
            String inputCspFileName = in.readLine();
            String prologDeclaration = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateDeclToPrologTerm(FregeInterface.just(inputCspFileName), cspDeclaration));
            String listOfFacts = getListOfFacts(prologDeclaration);
            print("frege_facts(" + listOfFacts + ").\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getListOfFacts(String prologCode) {
        List<String> facts = new LinkedList<>();
        String[] lines = prologCode.split("\n");
        for(String line: lines) {
            if(!line.startsWith(":")) {
                facts.add(line.substring(0, line.length() - 1));
            }
        }
        return "["+String.join(",", facts)+"]";
    }
}
