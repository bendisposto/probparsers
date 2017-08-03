package de.prob.cliparser;

import frege.language.CSPM.TranslateToProlog;
import frege.main.ExecCommand;
import frege.main.FregeInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Methods for communication with Frege CSP parser.
 * @author Markus Brenneis
 */
class FregeCspParser {
    static String addUnicode(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputCspFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(ExecCommand.addUnicode(inputCspFileName, outputCspFileName));
            return "frege_facts(ok).\n";
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsPrologTerm(e);
        }
    }

    static String removeUnicode(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputCspFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(ExecCommand.removeUnicode(inputCspFileName, outputCspFileName));
            return "frege_facts(ok).\n";
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsPrologTerm(e);
        }
    }

    /**
     * Parses a CSPM file to Prolog, writes the Prolog code to a file and returns the written facts as list.
     * @param in containing two lines: input csp file name and output prolog file name
     * @return frege_facts/1 predicate with the list of facts returned by the Frege CSPM parser
     */
    static String cspToProlog(BufferedReader in) {
        try {
            final String inputCspFileName = in.readLine();
            final String outputPlFileName = in.readLine();
            String prologCode = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateToPrologStr(inputCspFileName));
            Files.write(Paths.get(outputPlFileName), prologCode.getBytes());
            String listOfFacts = getListOfFacts(prologCode);
            return "frege_facts(" + listOfFacts + ").\n";
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsPrologTerm(e);
        }
    }

    static String translateCspDeclarationToProlog(BufferedReader in) {
        try {
            String cspDeclaration = in.readLine();
            String inputCspFileName = in.readLine();
            String prologDeclaration = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateDeclToPrologTerm(FregeInterface.just(inputCspFileName), cspDeclaration));
            String listOfFacts = getListOfFacts(prologDeclaration);
            return "frege_facts(" + listOfFacts + ").\n";
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsPrologTerm(e);
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

    private static String exceptionAsPrologTerm(Exception exception) {
        return "'parseResult'('exception','" + exception.getMessage() + "',0,0,0).";
    }
}
