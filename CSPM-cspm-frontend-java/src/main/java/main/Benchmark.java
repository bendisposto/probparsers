package frege.main;

import frege.main.Main;
import frege.main.FregeInterface;
import frege.main.ExecCommand;
import frege.language.CSPM.AST.TModule;
import frege.language.CSPM.TranslateToProlog;
import static frege.main.FregeInterface.evaluateIOFunction;
import frege.runtime.WrappedCheckedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.LinkedList;
import java.util.stream.*;

/**
 * This class contains methods for benchmarking typical calls to cspmf.
 */
public class Benchmark {

    /** a remembered ast */
    private static TModule ast;

    /**
     * Measures the runtime of a call with --prologOut, --xmlOut, --translateDecl, or --translateExpr.
     * The output files are saved next to the input files.
     * @param args[0] prologOut, xmlOut, translateDecl, or translateExpr
     * @param args[1] number of repetitions
     * @param args[2] the file to be translated
     * @param args[3] (optional) declaration for --translateDecl
     */
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("too few arguments");
            printUsageInformation();
            return;
        }
        
        int repetitions;
        try {
            repetitions = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.out.println(args[1] + " is not an integer");
            printUsageInformation();
            return;
        }
        String filename = args[2];
        String[] cmdArgs = new String[3];
        
        args[0] = args[0].replace("translate", "").toLowerCase();
        switch(args[0].charAt(0)) {
            case 'p':
                cmdArgs[0] = "translate";
                cmdArgs[1] = "--prologOut=" + filename + ".pl";
                cmdArgs[2] = filename;
                benchmark(repetitions, Main::main, cmdArgs);
                break;
            case 'x':
                cmdArgs[0] = "translate";
                cmdArgs[1] = "--xmlOut=" + filename + ".xml";
                cmdArgs[2] = filename;
                benchmark(repetitions, Main::main, cmdArgs);
                break;
            case 'd':
                cmdArgs[0] = args.length > 2 ? args[3] : "N";
                rememberAstFromFile(filename);
                benchmark(repetitions, Benchmark::translateDeclRun, cmdArgs);
                break;
            case 'e':
                cmdArgs[0] = args.length > 2 ? args[3] : "1+1";
                rememberAstFromFile(filename);
                benchmark(repetitions, Benchmark::translateExprRun, cmdArgs);
                break;
            default:
                System.out.println("unknown option " + args[0]);
                printUsageInformation();
        }
    }
    
    private static void printUsageInformation() {
        System.out.println("Parameters: prologOut|xmlOut|translateDecl|translateExpr numberOfRepetitions file [declaration='N'|expression='1+1']");
    }
    
    /**
     * Parses the given file to an ast and saves it in the class variable ast.
     * @param filePath Path to the file to be parsed
     */
    private static void rememberAstFromFile(String filePath) {
        System.out.println("Reading file ...");
        
        String spec = "";
        try {
            spec = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Tokenzing and parsing ...");
        
        long start = System.currentTimeMillis();
        ast = (TModule)evaluateIOFunction(
            TranslateToProlog.translateToAst(spec)
        );
        long end = System.currentTimeMillis();
        
        System.out.println("Done. (" + (end-start) + " ms)");
    }
    
    /**
     * Calls translateDeclToPrologTerm' on the ast with the given declaration and prints the resulting prolog term.
     * @param decl a list containing one CSPM declaration
     */
    private static void translateDeclRun(String[] decl) {
        try {
            System.out.println("Translating Declaration ...");
            long start = System.currentTimeMillis();
            String term = (String)evaluateIOFunction(
                TranslateToProlog.translateDeclToPrologTerm$tick(
                    ast,
                    decl[0]
                )
            );
            long end = System.currentTimeMillis();
            
            System.out.println("Done. (" + (end-start) + " ms)");
            
            System.out.println(term);
        } catch(WrappedCheckedException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
    
    /**
     * Calls translateExprToPrologTerm' on the ast with the given declaration and prints the resulting prolog term.
     * @param decl a list containing one CSPM declaration
     */
    private static void translateExprRun(String[] decl) {
        try {
            System.out.println("Translating Expression ...");
            long start = System.currentTimeMillis();
            String term = (String)evaluateIOFunction(
                TranslateToProlog.translateExprToPrologTerm$tick(
                    ast,
                    decl[0]
                )
            );
            long end = System.currentTimeMillis();
            
            System.out.println("Done. (" + (end-start) + " ms)");
            
            System.out.println(term);
        } catch(WrappedCheckedException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
    
    /**
     * Calls a function several times and outputs runtime information, esp. the average of the last 50 % of the calls.
     * @param repetitions the number of repetitions
     * @param f the function to be called
     * @param args list of parameters for f
     */
    private static void benchmark(int repetitions, Consumer<String[]> f, String[] args) {
        LinkedList<Long> runtimes = new LinkedList<>();
        
        long loopStart = System.currentTimeMillis();
        for(int i = 0; i < repetitions; i++) {
            
            long start = System.currentTimeMillis();
            f.accept(args);
            long end  = System.currentTimeMillis();
            
            Long runtime = end - start;
            System.out.println("run " + i + ": " + runtime);
            runtimes.add(runtime);
        }
        long loopEnd = System.currentTimeMillis();
        runtimes.forEach(s -> System.out.print(s + ", "));
        System.out.println();
        
        long runtimesSum = runtimes.stream().mapToLong(Long::longValue).sum();
        double runtimesAvg = (double)runtimesSum / repetitions;
        double runtimesAvgHalf = runtimes.subList(runtimes.size()/2, runtimes.size()).stream().mapToLong(Long::longValue).average().getAsDouble();
        
        System.out.println("total time: " + runtimesSum);
        System.out.println("average time: " + runtimesAvg);
        System.out.println("average time for second half: " + runtimesAvgHalf);
        System.out.println("benchmark wallclock time: " + (loopEnd - loopStart));
    }
    
}
