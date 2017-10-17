package de.prob.cliparser;

import frege.language.CSPM.TranslateToProlog;
import frege.main.ExecCommand;
import frege.main.FregeInterface;
import frege.language.CSPM.AST.TModule;
import frege.runtime.WrappedCheckedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static frege.main.FregeInterface.evaluateIOFunction;

/**
 * Methods for communication with Frege CSP parser.
 * @author Markus Brenneis
 */
class FregeCspParser {
    private static ASTCache astCache = new ASTCache();

    static String addUnicode(BufferedReader in) {
        try {
            String inputCspFileName = in.readLine();
            String outputCspFileName = in.readLine();
            FregeInterface.evaluateIOUnitFunction(ExecCommand.addUnicode(inputCspFileName, outputCspFileName));
            return "frege_facts(ok).\n";
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsParseResult(e);
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
            return exceptionAsParseResult(e);
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
            String prologCode = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateToPrologStr$tick(astCache.getAst(inputCspFileName)));
            Files.write(Paths.get(outputPlFileName), prologCode.getBytes());
            String listOfFacts = getListOfFacts(prologCode);
            return asFregeFact(listOfFacts);
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsParseResult(e);
        }
    }

    static String translateCspDeclarationToProlog(BufferedReader in) {
        try {
            String cspDeclaration = in.readLine();
            String inputCspFileName = in.readLine();
            String prologDeclaration = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateDeclToPrologTerm$tick(astCache.getAst(inputCspFileName), cspDeclaration));
            String listOfFacts = getListOfFacts(prologDeclaration);
            return asFregeFact(listOfFacts);
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsParseResult(e);
        } catch (frege.runtime.WrappedCheckedException e) {
            return asFregeFact(e);
        }
    }

    static String translateCspExpressionToProlog(BufferedReader in) {
        try {
            String cspExpression = in.readLine();
            String inputCspFileName = in.readLine();
            String prologExpression = (String) FregeInterface.evaluateIOFunction(TranslateToProlog.translateExprToPrologTerm$tick(astCache.getAst(inputCspFileName), cspExpression));
            String listOfFacts = getListOfFacts(prologExpression);
            return asFregeFact(listOfFacts);
        } catch (IOException e) {
            e.printStackTrace();
            return exceptionAsParseResult(e);
        } catch (frege.runtime.WrappedCheckedException e) {
            return asFregeFact(e);
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

    private static String asFregeFact(String term) {
        return "frege_facts(" + term + ").\n";
    }

    private static String asFregeFact(WrappedCheckedException e) {
        return asFregeFact("'" + e.getCause().getLocalizedMessage().replaceAll("'", "''") + "'");
    }

    private static String exceptionAsParseResult(Exception exception) {
        return "'parseResult'('exception','" + exception.getMessage() + "',0,0,0).";
    }

    private static class ASTCache {
        private String lastInputCspFileName = "";
        private long lastInputCspFileModificationTime = 0;
        private TModule cachedCspAst;

        TModule getAst(String filePath) {
            long lastModified = new File(filePath).lastModified();
            if (cacheCanBeUsed(filePath, lastModified)) {
                System.out.println("using cached ast for " + filePath);
                return cachedCspAst;
            }
            lastInputCspFileModificationTime = lastModified;
            lastInputCspFileName = filePath;

            System.out.println("Reading file ...");

            String spec = "";
            try {
                spec = new String(Files.readAllBytes(Paths.get(filePath)));
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Tokenzing and parsing ...");

            long start = System.currentTimeMillis();
            cachedCspAst = (TModule)evaluateIOFunction(
                    TranslateToProlog.translateToAst(spec)
            );
            long end = System.currentTimeMillis();

            System.out.println("Done. (" + (end-start) + " ms)");
            return cachedCspAst;
        }

        boolean cacheCanBeUsed(String filePath, long lastModified) {
            return lastInputCspFileName.equals(filePath) && lastModified == lastInputCspFileModificationTime;
        }
    }
}
