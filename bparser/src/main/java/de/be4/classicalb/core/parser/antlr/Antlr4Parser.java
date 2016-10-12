package de.be4.classicalb.core.parser.antlr;

import files.*;
import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.node.Node;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class Antlr4Parser {

	public static void main(String args[]) throws IOException {

		parse("1+1*2");
		// parse("a.b := 2");
		parse("DEFINITIONS foo == 1 ; 1; a == 1 + 1 ;");
		parse("DEFINITIONS foo == a +   ");
	}

	public static ParseTree parse(String input1) {
		// System.out.println(input1);
		ANTLRInputStream input = new ANTLRInputStream(input1);

		BLexer lexer = new BLexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// BLexer.rulesGrammar = true;
		// create a parser that feeds off the tokens buffer
		BParser parser = new BParser(tokens);
		// parser.addErrorListener(new MyErrorListener());
		// parser.removeErrorListeners();
		parser.addErrorListener(new DiagnosticErrorListener());
		MyErrorListener myErrorListener = new MyErrorListener();
		parser.addErrorListener(myErrorListener);
		ParseTree tree = parser.start(); // begin parsing at start rule
//		if (myErrorListener.exception != null) {
//			throw new RuntimeException(myErrorListener.exception);
//		}

		// System.out.println(tree.toStringTree(parser)); // print LISP-style
		// tree

		// PragmaListener pragmaListener = new PragmaListener(tokens);
		ParseTreeWalker walker = new ParseTreeWalker();
		// walker.walk(pragmaListener, tree);

		// MyTreeListener listener = new MyTreeListener();
		ParseTreeWalker walker2 = new ParseTreeWalker();
		// walker.walk(listener, tree);
		// System.out.println("-------------");

		return tree;
	}

	public static Node createSableCCAst(String input) {
		ParseTree tree = parse(input);
		DefinitionsAnalyser definitionAnalyser = new DefinitionsAnalyser(tree);
		definitionAnalyser.analyse();
		SableCCAstBuilder astBuilder = new SableCCAstBuilder(definitionAnalyser);
		Node ast = tree.accept(astBuilder);
		// printAsProlog(ast);
		return ast;
	}

	public static void printAsProlog(Node ast) {
		final ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.prologOutput = true;
		parsingBehaviour.useIndention = false;
		parsingBehaviour.addLineNumbers = false;
		parsingBehaviour.verbose = false;
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		final IPrologTermOutput pout = new PrologTermOutput(output, parsingBehaviour.useIndention);
		printAsProlog(ast, pout);
		System.out.println(output.toString());
	}

	public static void printAsProlog(final Node start, final IPrologTermOutput pout) {
		final NodeIdAssignment nodeIds = new NodeIdAssignment();
		nodeIds.assignIdentifiers(1, start);
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(nodeIds);
		final ASTProlog prolog = new ASTProlog(pout, pprinter);

		// pout.openTerm("machine");
		// if (lineNumbers) {
		// final SourcePositions src = positions.get(entry.getKey());
		// pprinter.setSourcePositions(src);
		// }
		start.apply(prolog);
		// pout.closeTerm();
		// pout.fullstop();
		pout.flush();
	}

	public static ParseTree parseRules(String input1) {
		System.out.println(input1);
		ANTLRInputStream input = new ANTLRInputStream(input1);

		RulesLexer lexer = new RulesLexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		RulesGrammar parser = new RulesGrammar(tokens);
		// parser.addErrorListener(new MyErrorListener());
		// parser.removeErrorListeners();
		parser.addErrorListener(new DiagnosticErrorListener());
		parser.addErrorListener(new MyErrorListener());

		ParseTree tree = parser.start(); // begin parsing at start rule

		System.out.println(tree.toStringTree(parser)); // print LISP-style
		// tree

		// PragmaListener pragmaListener = new PragmaListener(tokens);
		ParseTreeWalker walker = new ParseTreeWalker();
		// walker.walk(pragmaListener, tree);

		MyTreeListener listener = new MyTreeListener();
		ParseTreeWalker walker2 = new ParseTreeWalker();
		walker.walk(listener, tree);
		// System.out.println("-------------");

		return tree;
	}

}
