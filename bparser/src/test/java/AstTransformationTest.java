import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.antlr.Antlr4Parser;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;
import static org.junit.Assert.*;

public class AstTransformationTest {

	@Test
	public void testAddition() {
		String result = translateFormula("1+2");
		assertEquals("add(2,integer(3,1),integer(4,2))", result);
	}

	@Test
	public void testAnd() {
		String result = translateFormula("1=2 & 3=4");
		assertEquals("conjunct(2,equal(3,integer(4,1),integer(5,2)),equal(6,integer(7,3),integer(8,4)))", result);
	}
	
	
	@Test
	public void testAndOr() {
		final String result = translateFormula("a & b or c");
		assertEquals(
				"disjunct(2,conjunct(3,predicate_identifier(4,a),predicate_identifier(5,b)),predicate_identifier(6,c))",
				result);
	}
	
	@Test
	public void testAndAndAnd() {
		final String result = translateFormula("a & b & c & d");
		assertEquals(
				"conjunct(2,conjunct(3,conjunct(4,predicate_identifier(5,a),predicate_identifier(6,b)),predicate_identifier(7,c)),predicate_identifier(8,d))",
				result);
	}

	@Test
	public void testAndOr2() {
		final String result = translateFormula("a or b & c");
		assertEquals(
				"conjunct(2,disjunct(3,predicate_identifier(4,a),predicate_identifier(5,b)),predicate_identifier(6,c))",
				result);
	}

	@Test
	public void testAndOr3() {
		test("MACHINE test PROPERTIES 1=1 & (2=2 or 3=3) END");
	}

	@Test
	public void testAssign() {
		test("MACHINE test DEFINITIONS def == x := 1 END");
		test("MACHINE test DEFINITIONS def == y(1) := 2 END");
		test("MACHINE test DEFINITIONS def == x,y(1) := 1,2 END");
	}

	@Test
	public void testCouple() {
		test("MACHINE test PROPERTIES 1|->2 = (1,2) END");
	}

	@Test
	public void testAssignRecord() {
		final String result = translate("MACHINE test DEFINITIONS def == z'a := 3 END");
		assertEquals(
				"abstract_machine(1,machine(2),machine_header(3,test,[]),[definitions(4,[substitution_definition(5,def,[],assign(6,[record_field(7,identifier(8,z),identifier(9,a))],[integer(10,3)]))])])",
				result);
	}

	@Test
	public void testAssignRecord2() {
		final String result = translate("MACHINE test DEFINITIONS def == z'a'b := 3 END");
		assertEquals(
				"abstract_machine(1,machine(2),machine_header(3,test,[]),[definitions(4,[substitution_definition(5,def,[],assign(6,[record_field(7,record_field(8,identifier(9,z),identifier(10,a)),identifier(11,b))],[integer(12,3)]))])])",
				result);
	}

	@Test
	public void testCompleteMachine() throws BException {
		final String machine = "MACHINE test" + nl + "VARIABLES x,y" + nl + "INVARIANT 1 : 1" + nl
				+ "INITIALISATION skip" + nl + "END";
		test(machine);
	}

	@Test
	public void testDefinitionTypes() throws BException {
		final String machine = "MACHINE test PROPERTIES\n 1=1 DEFINITIONS predicate == 1 = 1; expr==1; sub== skip END";
		// String result = translateUsingSableCCParser(machine);
		String result2 = translate(machine);
	}

	@Test
	public void testDefinition() throws BException {
		String result = translateUsingSableCCParser(
				"MACHINE test PROPERTIES foo & bar(2)=2 DEFINITIONS foo == 1=1 END");
	}

	@Test
	public void testUnicode() throws BException {
		final String machine = "MACHINE test PROPERTIES \u00ac(1=2) END";
		test(machine);
	}

	@Test
	public void testDefinition2() throws BException {
		final String machine = "MACHINE test PROPERTIES def = 1 DEFINITIONS def == 1 END";
		test(machine);
	}

	@Test
	public void testExpressionDefinition() throws BException {
		final String machine = "MACHINE test PROPERTIES def(1) = 1 DEFINITIONS def(a) ==  a + 1 END";
		test(machine);
	}

	@Test
	public void testSubstitutionDefinition() throws BException {
		final String machine = "MACHINE test DEFINITIONS def(a) == skip INITIALISATION def(x) END";
		test(machine);
	}

	@Test
	public void testPredicateDefinition() throws BException {
		final String machine = "MACHINE test PROPERTIES def(1) DEFINITIONS def(a) ==  a = 1 END";
		test(machine);
	}

	@Test
	public void testMachine() {
		String result = translate("MACHINE test END");
		assertEquals("abstract_machine(1,machine(2),machine_header(3,test,[]),[])", result);
	}

	@Test
	public void testMachineDeclarationClause() {
		String result = translate("MACHINE test VARIABLES x,y,z END");
		assertEquals(
				"abstract_machine(1,machine(2),machine_header(3,test,[]),[variables(4,[identifier(5,x),identifier(6,y),identifier(7,z)])])",
				result);
	}

	@Test
	public void testMachinePredicateClause() {
		String result = translate("MACHINE test PROPERTIES 1=1 END");
		assertEquals(
				"abstract_machine(1,machine(2),machine_header(3,test,[]),[properties(4,equal(5,integer(6,1),integer(7,1)))])",
				result);
	}

	@Test
	public void testReferenceClause() {
		String result = translate("MACHINE test SEES a,b,c END");
		assertEquals(
				"abstract_machine(1,machine(2),machine_header(3,test,[]),[sees(4,[identifier(5,a),identifier(6,b),identifier(7,c)])])",
				result);
	}

	@Test
	public void testReferenceClause2() {
		String result = translate("MACHINE test USES a.b END");
		assertEquals("abstract_machine(1,machine(2),machine_header(3,test,[]),[uses(4,[identifier(5,'a.b')])])",
				result);
	}

	@Test
	public void testIdentifier() {
		String result = translateFormula("a=11");
		assertEquals("equal(2,identifier(3,a),integer(4,11))", result);
	}

	@Test
	public void testImplication() {
		test("MACHINE test PROPERTIES 1=1 => 2=2 END");
	}

	@Test
	public void testString() {
		translate("MACHINE test /**/  /*foo/*  */   /*b@*/  END");
	}
	
	@Test
	public void testPragma() {
		translate("MACHINE test SEES foo /*@ label foo2 */  END");
	}

	private void test(String machine) {
		final String result = translate(machine);
		String expected;
		try {
			expected = translateUsingSableCCParser(machine);
			assertEquals(expected, result);
		} catch (BException e) {
			throw new RuntimeException(e);
		}
	}

	private String translateFormula(String input) {
		return translate("#FORMULA " + input);
	}

	private String translate(String input) {
		Node ast = Antlr4Parser.createSableCCAst(input);

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
		return output.toString();
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

	public static String translateUsingSableCCParser(String input) throws BException {
		ParseOptions options = new ParseOptions();
		options.useAntlr4Parser = false;
		final BParser parser = new BParser("Test", options);
		Start start = parser.parse(input, true);
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
		printAsProlog(start, pout);
		System.out.println(output.toString());
		return output.toString();
	}

	private static final String nl = "\n";
}
