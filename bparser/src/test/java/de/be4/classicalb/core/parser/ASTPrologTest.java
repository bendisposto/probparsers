package de.be4.classicalb.core.parser;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PositionPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.AConstructorFreetypeConstructor;
import de.be4.classicalb.core.parser.node.ADisjunctPredicate;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AEvent;
import de.be4.classicalb.core.parser.node.AEventBModelParseUnit;
import de.be4.classicalb.core.parser.node.AEventsModelClause;
import de.be4.classicalb.core.parser.node.AFalsityPredicate;
import de.be4.classicalb.core.parser.node.AFreetype;
import de.be4.classicalb.core.parser.node.AFreetypesMachineClause;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AIntegerSetExpression;
import de.be4.classicalb.core.parser.node.APartitionPredicate;
import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.ATruthPredicate;
import de.be4.classicalb.core.parser.node.AWitness;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PEvent;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PFreetype;
import de.be4.classicalb.core.parser.node.PFreetypeConstructor;
import de.be4.classicalb.core.parser.node.PModelClause;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.PWitness;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class ASTPrologTest {
	private boolean remove_restrictions;

	@Before
	public void setUp() throws Exception {
		remove_restrictions = false;
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

	private void checkAST(final int counter, final String expected,
			final Node ast) {
		assertEquals(insertNumbers(counter, expected), printAST(ast));
	}

	private void checkProlog(final int counter, final String bspec,
			final String expected) throws BException {
		final BParser parser = new BParser("testcase");
		if (remove_restrictions) {
			parser.getOptions().restrictProverExpressions = false;
		}
		final Start startNode = parser.parse(bspec, false,
				new NoContentProvider());
		checkAST(counter, expected, startNode);
	}

	private void checkPredicate(final String pred, final String expected)
			throws BException {
		checkProlog(2, BParser.PREDICATE_PREFIX + pred, expected);
	}

	private void checkExpression(final String expr, final String expected)
			throws BException {
		checkProlog(2, BParser.EXPRESSION_PREFIX + expr, expected);
	}

	private void checkSubstitution(final String subst, final String expected)
			throws BException {
		checkProlog(2, BParser.SUBSTITUTION_PREFIX + subst, expected);
	}

	private void checkOppatterns(final String pattern, final String expected)
			throws BException {
		checkProlog(1, BParser.OPERATION_PATTERN_PREFIX + pattern, expected);
	}

	private String insertNumbers(int counter, final String string) {
		StringBuilder buf = new StringBuilder();
		char c[] = string.toCharArray();
		for (int i = 0; i < c.length; i++) {
			switch (c[i]) {
			case '$':
				buf.append(counter++);
				break;
			case '%':
				buf.append(counter - 1);
				break;
			default:
				buf.append(c[i]);
				break;
			}
		}
		return buf.toString();
	}

	@Test
	public void testMachine() throws BException {
		String m = "MACHINE name" + "  OPERATIONS op=skip END";
		String expected = "abstract_machine(1,machine(2),machine_header(3,name,[]),[operations(4,[operation(5,identifier(5,op),[],[],skip(6))])])";// todo:
																																					// warum
																																					// taucht
																																					// hier
																																					// die
																																					// 5
																																					// zweimal
																																					// auf?
		checkProlog(1, m, expected);
	}

	@Test
	public void testMachine2() throws BException {
		String m = "MACHINE mname(P)  SETS S; E={e1,e2}"
				+ "  INCLUDES inc(x),rn.inc2  SEES see,s.see2  VARIABLES x"
				+ "  INVARIANT x:NAT  INITIALISATION x:=5"
				+ "  OPERATIONS op=skip; r,s <-- op2(a,b) = skip  END";
		String expected = "abstract_machine($,machine($),machine_header($,mname,[identifier($,'P')]),"
				+ "[sets($,[deferred_set($,'S'),enumerated_set($,'E',[identifier($,e1),identifier($,e2)])]),"
				+ "includes($,[machine_reference($,inc,[identifier($,x)]),machine_reference($,'rn.inc2',[])]),"
				+ "sees($,[identifier($,see),identifier($,'s.see2')]),"
				+ "variables($,[identifier($,x)]),"
				+ "invariant($,member($,identifier($,x),nat_set($))),"
				+ "initialisation($,assign($,[identifier($,x)],[integer($,5)])),"
				+ "operations($,[operation($,identifier(%,op),[],[],skip($)),"
				+ "operation($,identifier(%,op2),[identifier($,r),identifier($,s)],"
				+ "[identifier($,a),identifier($,b)],skip($))])])";

		checkProlog(1, m, expected);
	}

	@Test
	public void testRefinement() throws BException {
		String ref = "REFINEMENT ref REFINES abstract VARIABLES x END";
		String expected = "refinement_machine($,machine_header($,ref,[]),abstract,[variables($,[identifier($,x)])])";
		checkProlog(1, ref, expected);
	}

	@Test
	public void testEmptyString() throws BException {
		checkExpression("\"test\"+\"\"", "add(2,string(3,test),string(4,''))");
	}

	@Test
	public void testPredicates() throws BException {
		checkPredicate("5>r.j", "greater($,integer($,5),identifier($,'r.j'))");
		checkPredicate(
				"!x,y.(x<y)",
				"forall($,[identifier($,x),identifier($,y)],less($,identifier($,x),identifier($,y)))");
	}

	@Test
	public void testExpressions() throws BException {
		checkExpression(
				"SIGMA x,y.(x:NAT & y:INT | x+y)",
				"general_sum($,[identifier($,x),identifier($,y)],"
						+ "conjunct($,member($,identifier($,x),nat_set($)),member($,identifier($,y),int_set($))),"
						+ "add($,identifier($,x),identifier($,y)))");
	}

	@Test
	public void testSubstitutions() throws BException {
		checkSubstitution("x,y :: BOOL",
				"becomes_element_of($,[identifier($,x),identifier($,y)],bool_set($))");
	}

	@Test
	public void testDefinitions() throws BException {
		String m = "MACHINE Defs  DEFINITIONS  INV == x:INT;"
				+ "  lt(a) == x<7;  dbl(a) == 2*x*a;  ax(a) == x:=a"
				+ "  VARIABLES x  INVARIANT INV & lt(7)"
				+ "  INITIALISATION x:=dbl(3)  OPERATIONS  op1 = ax(6)"
				+ "  END";
		String expected = "abstract_machine($,machine($),machine_header($,'Defs',[]),"
				+ "[definitions($,[predicate_definition($,'INV',[],member($,identifier($,x),int_set($))),"
				+ "predicate_definition($,lt,[identifier($,a)],less($,identifier($,x),integer($,7))),"
				+ "expression_definition($,dbl,[identifier($,a)],mult_or_cart($,mult_or_cart($,integer($,2),identifier($,x)),identifier($,a))),"
				+ "substitution_definition($,ax,[identifier($,a)],assign($,[identifier($,x)],[identifier($,a)]))]),"
				+ "variables($,[identifier($,x)]),"
				+ "invariant($,conjunct($,definition($,'INV',[]),definition($,lt,[integer($,7)]))),"
				+ "initialisation($,assign($,[identifier($,x)],[definition($,dbl,[integer($,3)])])),"
				+ "operations($,[operation($,identifier(%,op1),[],[],definition($,ax,[integer($,6)]))])])";
		checkProlog(1, m, expected);
	}

	@Test
	public void testRewrite() throws BException {
		checkPredicate("0 /= -1",
				"not_equal($,integer($,0),unary_minus($,integer($,1)))");
		checkPredicate("NATURAL <: INTEGER",
				"subset($,natural_set($),integer_set($))");
		checkPredicate("NATURAL /<: INTEGER",
				"not_subset($,natural_set($),integer_set($))");
		checkPredicate("NATURAL <<: INTEGER",
				"subset_strict($,natural_set($),integer_set($))");
		checkPredicate("NATURAL /<<: INTEGER",
				"not_subset_strict($,natural_set($),integer_set($))");
		checkPredicate("#x.(x>0)",
				"exists($,[identifier($,x)],greater($,identifier($,x),integer($,0)))");
	}

	@Test
	public void testTrueFalse() throws BException {
		checkPredicate("TRUE : BOOL", "member($,boolean_true($),bool_set($))");
		checkPredicate("FALSE : BOOL", "member($,boolean_false($),bool_set($))");

		final ADisjunctPredicate disjunction = new ADisjunctPredicate();
		disjunction.setLeft(new ATruthPredicate());
		disjunction.setRight(new AFalsityPredicate());

		checkAST(0, "disjunct($,truth($),falsity($))", disjunction);
	}

	@Test
	public void testBFalse() throws BException {
		remove_restrictions = true;
		checkPredicate("bfalse", "falsity($)");
	}

	@Test
	public void testProverSET() throws BException {
		remove_restrictions = true;
		checkExpression(
				"SET(x).(x>0)",
				"comprehension_set($,[identifier($,x)],greater($,identifier($,x),integer($,0)))");
	}

	@Test
	public void testOperationCalls() throws BException {
		checkSubstitution("do(x)",
				"operation_call($,identifier($,do),[],[identifier($,x)])");
		checkSubstitution("r <-- do(x)",
				"operation_call($,identifier(%,do),[identifier($,r)],[identifier($,x)])");
	}

	@Test
	public void testEvent() throws BException {
		final AEventBModelParseUnit model = new AEventBModelParseUnit();
		model.setName(new TIdentifierLiteral("mm"));
		final AEventsModelClause events = new AEventsModelClause();
		model.setModelClauses(Arrays.asList((PModelClause) events));
		AEvent event = new AEvent();
		events.setEvent(Arrays.asList((PEvent) event));
		event.setEventName(new TIdentifierLiteral("testevent"));
		event.setVariables(Arrays.asList(createId("param")));
		event.setGuards(Arrays.asList((PPredicate) new ATruthPredicate()));
		PSubstitution subst1 = new AAssignSubstitution(
				Arrays.asList(createId("x")), Arrays.asList(createId("param")));
		event.setAssignments(Arrays.asList(subst1));
		PWitness witness = new AWitness(new TIdentifierLiteral("ab"),
				new AEqualPredicate(createId("ab"), createId("y")));
		event.setWitness(Arrays.asList(witness));
		event.setRefines(Arrays.asList(new TIdentifierLiteral("abstract1"),
				new TIdentifierLiteral("abstract2")));

		checkAST(
				0,
				"event_b_model($,mm,[events($,["
						+ "event($,testevent,[abstract1,abstract2],[identifier($,param)],[truth($)],[],"
						+ "[assign($,[identifier($,x)],[identifier($,param)])],"
						+ "[witness($,identifier(%,ab),equal($,identifier($,ab),identifier($,y)))])])])",
				model);
	}

	@Test
	public void testPartition() {
		final PExpression set = createId("set");
		final PExpression one = new AIntegerExpression(new TIntegerLiteral("1"));
		final PExpression two = new AIntegerExpression(new TIntegerLiteral("2"));
		final PExpression three = new AIntegerExpression(new TIntegerLiteral(
				"3"));
		final APartitionPredicate pred = new APartitionPredicate(set,
				Arrays.asList(one, two, three));
		final String expected = "partition($,identifier($,set),[integer($,1),integer($,2),integer($,3)])";
		checkAST(0, expected, pred);
	}

	@Test
	public void testOppattern() throws BException {
		final String pattern = "op1(x,_)";
		final String expected = "oppattern($,op1,[def($,identifier($,x)),undef($)])";
		checkOppatterns(pattern, expected);
	}

	@Test
	public void testLargeInteger() throws BException {
		checkExpression("922337203685477580756",
				"integer($,922337203685477580756)");
	}

	@Test
	public void testString() throws BException {
		checkExpression("\" \"", "string($,' ')");
		checkExpression("\"\"", "string($,'')");
		checkExpression("\"a\"", "string($,a)");
		checkExpression("\"A\"", "string($,'A')");
	}

	private PExpression createId(final String name) {
		return new AIdentifierExpression(Arrays.asList(new TIdentifierLiteral(
				name)));
	}

	@Test
	public void testFreeType() throws BException {
		final AConstructorFreetypeConstructor multi = new AConstructorFreetypeConstructor(

		new TIdentifierLiteral("multi"), new APowSubsetExpression(
				new AIntegerSetExpression()));

		final AConstructorFreetypeConstructor single = new AConstructorFreetypeConstructor(
				new TIdentifierLiteral("single"),

				new AIntegerSetExpression());

		final AFreetype freetype = new AFreetype(new TIdentifierLiteral("T"),
				Arrays.<PFreetypeConstructor> asList(multi, single));

		AFreetypesMachineClause clause = new AFreetypesMachineClause(
				Arrays.<PFreetype> asList(freetype));

		final StringWriter swriter = new StringWriter();
		NodeIdAssignment nodeids = new NodeIdAssignment();
		clause.apply(nodeids);
		IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(swriter),
				false);
		PositionPrinter pprinter = new ClassicalPositionPrinter(nodeids);
		ASTProlog prolog = new ASTProlog(pout, pprinter);

		clause.apply(prolog);

		String code = swriter.toString();
		assertFalse(code.isEmpty());
		assertEquals(
				"freetypes(0,[freetype(1,'T',[constructor(2,multi,pow_subset(3,integer_set(4))),constructor(5,single,integer_set(6))])])",
				code);

	}

}
