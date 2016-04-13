package de.be4.classicalb.core.parser.pragmas;

import java.io.PrintWriter;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import de.be4.classicalb.core.parser.BLexer;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PositionPrinter;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class PragmaTest {

	@Test
	public void testLexer() throws Exception {
//		String input = "/*@ generated */ MACHINE foo(x) \n"
//				+ "/* look at me. */ \n"
//				+ "DEFINITIONS \n" 
//				+ " /*@ conversion */ foo(m) == m \n"
//				+ "PROPERTIES \n"
//				+ "/*@ label foo */ \n"
//				+ "/*@ label bar */ \n"
//				+ "x = /*@ symbolic */ {y|->z| y < z } \n"
//				+ "/*@ desc prop */ \n"
//				+ "SETS A;B={a,b} /*@ desc trololo !!! */;C END";
		
		
//		String input = "MACHINE foo  PROPERTIES /*@ label foo */ x = /*@ symbolic */ {y|->z| y < z }  END";
		
		String input = "MACHINE foo CONSTANTS c /*@ desc konstante nummero uno */ PROPERTIES c = 5  VARIABLES x /*@ desc Hallo du variable */ INVARIANT x=1 INITIALISATION x:= 1 END";
		
		BLexer lex = new BLexer(new PushbackReader(new StringReader(input), 500));
		Token t;
		while(!((t=lex.next()) instanceof EOF)) {
			System.out.print(t.getClass().getSimpleName()+"("+t.getText()+")");
			System.out.print(" ");
		}
		
		
		BParser p = new BParser();
		
		System.out.println("\n"+input);	
		
		
		Start ast = p.parse(input, false);

		ASTPrinter pr = new ASTPrinter();
		ast.apply(pr);
		
		System.out.println(printAST(ast));

	}
	
	private String printAST(final Node node) {
		final StringWriter swriter = new StringWriter();
		NodeIdAssignment nodeids = new NodeIdAssignment();
		node.apply(nodeids);
		IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(swriter),
				false);
		PositionPrinter pprinter = new ClassicalPositionPrinter(nodeids);
		ASTProlog prolog = new ASTProlog(pout, pprinter);
		node.apply(prolog);
		swriter.flush();
		return swriter.toString();
	}
	
	

}