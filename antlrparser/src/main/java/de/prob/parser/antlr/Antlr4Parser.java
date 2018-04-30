package de.prob.parser.antlr;

import files.*;
import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.util.PrettyPrinter;
import de.prob.parser.antlr.rules.AbstractRulesSableCCAstBuilder;
import de.prob.parser.antlr.rules.MyRulesLexer;
import de.prob.parser.antlr.rules.RulesDefinitionAnalyser;
import de.prob.parser.antlr.rules.RulesSableCCAstBuilder;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class Antlr4Parser {

	public static void main(String args[]) throws IOException {
		String input = "#EXPRESSION foo + 1 ";
		{
			ParseTree tree = parse(input);
			DefinitionsAnalyser definitionAnalyser = new DefinitionsAnalyser(tree);
			definitionAnalyser.analyse();
			BLanguageSableCCAstBuilder astBuilder = new BLanguageSableCCAstBuilder(definitionAnalyser);
			Node ast = tree.accept(astBuilder);
			PrettyPrinter pp = new PrettyPrinter();
			ast.apply(pp);
			System.out.println("----------------");
			System.out.println(pp.getPrettyPrint());
		}
		System.out.println("\n\n----------------Rules");
		{
			ParseTree tree2 = parseRules(input);
			RulesDefinitionAnalyser definitionAnalyser2 = new RulesDefinitionAnalyser(tree2);
			definitionAnalyser2.analyse();
			AbstractRulesSableCCAstBuilder astBuilder2 = new AbstractRulesSableCCAstBuilder(definitionAnalyser2);
			Node ast2 = tree2.accept(astBuilder2);
			PrettyPrinter pp2 = new PrettyPrinter();
			ast2.apply(pp2);
			System.out.println("----------------");
			System.out.println(pp2.getPrettyPrint());
		}

	}

	public static ParseTree parse(String input1) {
		// System.out.println(input1);
		CodePointCharStream fromString = CharStreams.fromString(input1);

		// BLexer lexer = new BLexer(input);
		MyLexer myLexer = new MyLexer(fromString);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(myLexer);
		// BLexer.rulesGrammar = true;
		// create a parser that feeds off the tokens buffer

		BParser parser = new BParser(tokens);
		// RulesGrammar parser = new RulesGrammar(tokens);

		// parser.addErrorListener(new MyErrorListener());
		// parser.removeErrorListeners();
		parser.addErrorListener(new DiagnosticErrorListener());
		MyErrorListener myErrorListener = new MyErrorListener();
		parser.addErrorListener(myErrorListener);
		ParseTree tree = null;

		tree = parser.start();

		System.out.println("----------- Parsing completed");
		System.out.println(tree.getClass());
		// begin parsing at start rule
		// if (myErrorListener.exception != null) {
		// throw new RuntimeException(myErrorListener.exception);
		// }

		// System.out.println(tree.toStringTree(parser)); // print LISP-style
		// tree

		// PragmaListener pragmaListener = new PragmaListener(tokens);
		// ParseTreeWalker walker = new ParseTreeWalker();
		// walker.walk(pragmaListener, tree);

		// MyTreeListener listener = new MyTreeListener();
		// ParseTreeWalker walker2 = new ParseTreeWalker();
		// walker.walk(listener, tree);
		// System.out.println("-------------");

		return tree;
	}

	public static ParseTree parseRules(String input1) {
		CodePointCharStream input = CharStreams.fromString(input1);
		MyRulesLexer myLexer = new MyRulesLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(myLexer);

		RulesGrammar parser = new RulesGrammar(tokens);
		parser.addErrorListener(new DiagnosticErrorListener());
		MyErrorListener myErrorListener = new MyErrorListener();
		parser.addErrorListener(myErrorListener);
		ParseTree tree = null;

		tree = parser.start();

		System.out.println("----------- Parsing completed");
		System.out.println(tree.getClass());

		return tree;
	}

	public static Node createSableCCAst(String input) {
		try {
			ParseTree tree = parse(input);
			DefinitionsAnalyser definitionAnalyser = new DefinitionsAnalyser(tree);
			definitionAnalyser.analyse();
			BLanguageSableCCAstBuilder astBuilder = new BLanguageSableCCAstBuilder(definitionAnalyser);
			Node ast = tree.accept(astBuilder);
			return ast;
		} catch (RuntimeException e) {
			// System.err.println(e.getMessage());
			e.printStackTrace();
			if (e.getCause() != null) {
				throw (BParseException) e.getCause();
			} else {
				throw new BParseException(null, "");
			}

		}
	}

	public static Node createSableCCAstFromRulesGrammar(String input) {
		System.out.println("-------------Rules");
		try {
			ParseTree tree = parseRules(input);
			RulesDefinitionAnalyser definitionAnalyser = new RulesDefinitionAnalyser(tree);
			definitionAnalyser.analyse();
			RulesSableCCAstBuilder astBuilder = new RulesSableCCAstBuilder(definitionAnalyser);
			Node ast = tree.accept(astBuilder);
			return ast;
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			// e.printStackTrace();
			if (e.getCause() != null) {
				throw (BParseException) e.getCause();
			} else {
				throw new BParseException(null, "");
			}

		}
	}

	public static void printAsProlog(Node ast) {
		final ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.setPrologOutput(true);
		parsingBehaviour.setUseIndention(true);
		parsingBehaviour.setAddLineNumbers(true);
		parsingBehaviour.setVerbose(true);
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
		final IPrologTermOutput pout = new PrologTermOutput(output, parsingBehaviour.isUseIndention());
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

}
