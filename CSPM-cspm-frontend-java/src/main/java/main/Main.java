package frege.main;

import frege.main.ExecCommand;
import frege.runtime.Lambda;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Option.*;

import static frege.main.FregeInterface.evaluateIOFunction;
import static frege.main.FregeInterface.evaluateIOUnitFunction;

public class Main {

    /**
     * main-function for the command line.
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("?", "help", false, "display help message");
        options.addOption("V", "version", false, "print version information");
        options.addOption(Option.builder()
                                .longOpt("numeric-version")
                                .desc("print just the version number")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("rename")
                                .desc("run renaming on the AST")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("xmlOut")
                                .desc("translate a CSP-M file to XML")
                                .hasArg()
                                .argName("FILE")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("prettyOut")
                                .desc("pretty print to a file")
                                .hasArg()
                                .argName("FILE")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("addUnicode")
                                .desc("replace some CSP-M symbols with unicode")
                                .hasArg()
                                .argName("FILE")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("removeUnicode")
                                .desc("replace some unicode symbols with default CSP-M encoding")
                                .hasArg()
                                .argName("FILE")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("prologOut")
                                .desc("translate a CSP-M file to Prolog")
                                .hasArg()
                                .argName("FILE")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("prologStdOut")
                                .desc("like prologOut, but prints to stdout instead of writing to a file")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("expressionToPrologTerm")
                                .desc("translate a single CSP-M expression to Prolog")
                                .hasArg()
                                .argName("STRING")
                                .build());
        options.addOption(Option.builder()
                                .longOpt("declarationToPrologTerm")
                                .desc("translate a single CSP-M declaration to Prolog")
                                .hasArg()
                                .argName("STRING")
                                .build());
        
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmdLine = parser.parse(options, args);
            
            String[] arguments = cmdLine.getArgs();
            
            if(cmdLine.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("cspmf translate", options);
                System.out.println();
                System.out.println("usage: cspmf info");
                System.out.println("  print some information about the program");
            } else if(cmdLine.hasOption("version")) {
                evaluateIOUnitFunction(ExecCommand.version);
            } else if(cmdLine.hasOption("numeric-version")) {
                evaluateIOUnitFunction(ExecCommand.numericVersion);
            } else if(arguments.length == 0 ||  arguments[0].equals("info")) {
                evaluateIOUnitFunction(ExecCommand.verbose);
            } else if(arguments.length == 2) {
                if(arguments[0].equals("translate")) {
                    String src = arguments[1];
                    boolean rename = cmdLine.hasOption("rename");
                    if(cmdLine.hasOption("prettyOut")) {
                        String outFile = cmdLine.getOptionValue("prettyOut");
                        evaluateIOUnitFunction(ExecCommand.prettyOut(src, rename, outFile));
                    }
                    if(cmdLine.hasOption("xmlOut")) {
                        String outFile = cmdLine.getOptionValue("xmlOut");
                        evaluateIOUnitFunction(ExecCommand.xmlOut(src, rename, outFile));
                    }
                    if(cmdLine.hasOption("addUnicode")) {
                        String outFile = cmdLine.getOptionValue("addUnicode");
                        evaluateIOUnitFunction(ExecCommand.addUnicode(src, outFile));
                    }
                    if(cmdLine.hasOption("removeUnicode")) {
                        String outFile = cmdLine.getOptionValue("removeUnicode");
                        evaluateIOUnitFunction(ExecCommand.removeUnicode(src, outFile));
                    }
                    if(cmdLine.hasOption("prologOut")) {
                        String outFile = cmdLine.getOptionValue("prologOut");
                        evaluateIOUnitFunction(ExecCommand.prologOut(src, outFile));
                    }
                    if(cmdLine.hasOption("prologStdOut")) {
                        String prologOutput = (String)evaluateIOFunction(ExecCommand.prologStdOut(src));
                        System.out.println(prologOutput);
                    }
                    if(cmdLine.hasOption("expressionToPrologTerm")) {
                        String expr = cmdLine.getOptionValue("expressionToPrologTerm");
                        evaluateIOUnitFunction(ExecCommand.expressionToPrologTerm(src, expr));
                    }
                    if(cmdLine.hasOption("declarationToPrologTerm")) {
                        String decl = cmdLine.getOptionValue("declarationToPrologTerm");
                        evaluateIOUnitFunction(ExecCommand.declarationToPrologTerm(src, decl));
                    }
                    if(cmdLine.getOptions().length == 0) {
                        System.out.println("No output option is set");
                        System.out.println("Set '--xmlOut', '--prettyOut' or an other output option");
                    }
                } else {
                    System.err.println("Missing mode, wanted any of: info translate");
                }
            } else {
                System.err.println("Missing mode or output file.");
            }
        } catch(ParseException exp) {
            System.err.println(exp.getMessage());
        }
    }
}
