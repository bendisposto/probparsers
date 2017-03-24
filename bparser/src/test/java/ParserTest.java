import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import de.be4.classicalb.core.parser.antlr.Antlr4Parser;
import de.be4.classicalb.core.parser.antlr.DefinitionsAnalyser;
import de.be4.classicalb.core.parser.antlr.BLanguageSableCCAstBuilder;
import de.be4.classicalb.core.parser.node.Node;
import files.BLexer;

public class ParserTest {
	@Test
	public void testSubstitution() {
		testFormula("skip");
		testFormula("BEGIN skip END");
		testFormula("IF 1=1 THEN skip END");
		testFormula("IF 1=1 THEN skip ELSE skip END");
		testFormula("IF 1=1 THEN skip ELSIF 2=2 THEN skip END");
		testFormula("IF 1=1 THEN skip ELSIF 2=2 THEN skip ELSE skip END");
	}

	@Test
	public void testDefinitions() {
		test("MACHINE test DEFINITIONS foo == 1 END");
		test("MACHINE test DEFINITIONS foo(a) == 1 END");
		test("MACHINE test DEFINITIONS foo(a,b,c) == 1 END");
	}

	@Test
	public void testVariables() {
		//test("MACHINE test VARIABLES a END");
		fail("MACHINE test VARIABLES a; END");
	}

	@Test
	public void testFunctionCall() {
		testFormula("a~");
		testFormula("a~(1)");
		testFormula("a(1)");
		testFormula("a \\/ b(2)");
	}

	@Test
	public void testOperations() {
		test("MACHINE test OPERATIONS foo = skip END");
		test("MACHINE test OPERATIONS foo(a,b) = skip END");
		test("MACHINE test OPERATIONS a,b <-- foo = skip END");
		test("MACHINE test OPERATIONS a,b <-- foo(a,b) = skip END");
		test("MACHINE test OPERATIONS foo = skip ; bar = skip END");
		fail("MACHINE test OPERATIONS foo = skip ; END");
	}

	@Test
	public void testOperationCall() {
		testFormula("BEGIN x <-- foo(a) END");
		testFormula("BEGIN x <-- foo.bar.bazz(1) END");
		testFormula("BEGIN x.y END");
		failFormula("BEGIN x.y <-- foo END ");
	}

	@Test
	public void testAmbiguous() {
		test("MACHINE test DEFINITIONS def == 1 ; def2 == 2 END");
		// test("a;skip");
		// fail("IF 1=1 THEN skip ELSE 3=3 END");
	}

	@Test
	public void testConjunctionDisjunction() {
		testFormula("1=1 & 2=2 & 3=3");
		// test("(a or b) & c");
		// test("a & b or c");
		// test("1=1 or 2=2 & 3=3");
		// test("1=1 & 2=2 or 3=3");
		// test("MACHINE foo PROPERTIES 1=1 END");
	}

	@Test
	public void testDefinition() {
		test("MACHINE foo DEFINITIONS foo == (TRUE) ; bar == 1 END");
	}

	@Test
	public void testMachine() {
		test("MACHINE RULES_MACHNE END");
		//test("MACHINE foo PROPERTIES 1=1 END");
	}

	@Test
	public void testPredicate() {
		testFormula("IF 1=1 THEN 2=2 ELSE 3=3 END");
	}

	@Test
	public void testPredicate2() {
		test("MACHINE test PROPERTIES 1=1 & 2=2 END");
	}
	
	@Test
	public void testFormula() {
		testFormula("1 + 1");
	}

	@Test
	public void testExtraneousSemicolon() {
		fail("MACHINE test VARIABLES x y  END");
	}
	
	@Test
	public void testRulesMachine() {
		test("MACHINE RULES_MACHINE  END");
	}
	
	
	@Test
	public void testRulesGrammar() {
		//ParseTree tree = Antlr4Parser.parseRules("RULES_MACHINE test INITIALISATION FOR x IN {1 } DO skip END END");
		ParseTree tree = Antlr4Parser.parseRules("MACHINE test END");
		System.out.println(tree.getClass());
		DefinitionsAnalyser definitionAnalyser = new DefinitionsAnalyser(tree);
		definitionAnalyser.analyse();
		BLanguageSableCCAstBuilder astBuilder = new BLanguageSableCCAstBuilder(definitionAnalyser);
		Node ast = tree.accept(astBuilder);
		//Antlr4Parser.printAsProlog(ast);
	}
	
	private static void test(String input) {
		Antlr4Parser.parse(input);
	}

	private void testFormula(String string) {
		test("#FORMULA " + string);
	}

	private static void failFormula(String input) {
		fail("#FORMULA " + input);
	}

	private static void fail(String input) {
		try {
			Antlr4Parser.parse(input);
		} catch (Exception e) {
			return;
		}
		throw new RuntimeException();
	}

}
