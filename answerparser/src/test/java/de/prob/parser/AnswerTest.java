package de.prob.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import de.prob.core.sablecc.node.Start;


public class AnswerTest  {
	private static final String PARSETREE_WAS_NULL = "Parsetree was null";

	// executeOperation(1).
	// no
	@Test
	public void testNo() throws ResultParserException  {
		final String testString = "no";
		final Start rootNode = ProBResultParser.parse(testString);
		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// executeOperationNr(1).
	// yes([])

	@Test
	public void testYes() throws ResultParserException {
		final String testString = "yes([])";
		final Start rootNode = ProBResultParser.parse(testString);
		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	@Test
	public void testException() throws ResultParserException {
		final String testString = "exception(ERR-UNKNOWNerror(syntax_error() cannot start an expression),syntax_error(read_term($stream(23185648),_9067,[variable_names(_9074)]),1,) cannot start an expression,[atom(getOperations)-1,(-1,)-1,.-1],2)))";
		final Start rootNode = ProBResultParser.parse(testString);
		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// yes('.'(=('CurID',root),[]))
	@Test
	public void testGetANumber() throws ResultParserException {
		final String testString = "yes('.'(=('CurId',root),[]))";
		final Start rootNode = ProBResultParser.parse(testString);
		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// yes('.'(=('CurID',-1),[]))
	@Test
	public void testGetNegativeNumber() throws ResultParserException {
		final String testString = "yes('.'(=('CurId',-1),[]))";
		final Start rootNode = ProBResultParser.parse(testString);
		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// computeOperationsForState(root,X).
	// yes('.'(=('X','.'(op(initialise_machine([]),0,[]),[])),[]))

	@Test
	public void testOperation() throws ResultParserException {
		final String testString = "yes('.'(=('X','.'(op(initialise_machine([]),0,[],root),[])),[]))";
		final Start rootNode = ProBResultParser.parse(testString);

		/* Review: Using System.out.println is ok, this is a test only */
		System.out.println(testString); // NOPMD by fabian on 8/13/07 12:48 PM
//		new RootNode(new SableAST(rootNode),
//				new HashMap<SetElementItem, String>());

		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	@Test
	public void testOperation3() throws ResultParserException {
		final String testString = "yes('.'(=('PLOps','.'(op(initialise_machine,1,[],root),[])),[]))";

		final Start rootNode = ProBResultParser.parse(testString);

		/* Review: Using System.out.println is ok, this is a test only */
		System.out.println(testString); // NOPMD by fabian on 8/13/07 12:48 PM
//		new RootNode(new SableAST(rootNode),
//				new HashMap<SetElementItem, String>());

		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// computeOperationsForState(7,X).
	// yes('.'(=('X','.'(op(add(fd(3,'Name'),fd(1,'Code')),11,[]),'.'(op(add(fd(3,'Name'),fd(2,'Code')),12,[]),'.'(op(lookup(fd(1,'Name')),7,'.'(fd(1,'Code'),[])),'.'(op(lookup(fd(2,'Name')),7,'.'(fd(1,'Code'),[])),'.'(op(update(fd(1,'Name'),fd(1,'Code')),7,[]),'.'(op(update(fd(1,'Name'),fd(2,'Code')),13,[]),'.'(op(update(fd(2,'Name'),fd(1,'Code')),7,[]),'.'(op(update(fd(2,'Name'),fd(2,'Code')),8,[]),[]))))))))),[]))

	@Test
	public void testOperation2() throws ResultParserException {
		final String testString = "yes('.'(=('X','.'(op(add(fd(3,'Name'),fd(1,'Code')),11,[],0),'.'(op(add(fd(3,'Name'),fd(2,'Code')),12,[],0),'.'(op(lookup(fd(1,'Name')),7,'.'(fd(1,'Code'),[]),0),'.'(op(lookup(fd(2,'Name')),7,'.'(fd(1,'Code'),[]),0),'.'(op(update(fd(1,'Name'),fd(1,'Code')),7,[],0),'.'(op(update(fd(1,'Name'),fd(2,'Code')),13,[],0),'.'(op(update(fd(2,'Name'),fd(1,'Code')),7,[],0),'.'(op(update(fd(2,'Name'),fd(2,'Code')),8,[],0),[]))))))))),[]))";
		final Start rootNode = ProBResultParser.parse(testString);

		/* Review: Using System.out.println is ok, this is a test only */
		System.out.println(testString); // NOPMD by fabian on 8/13/07 12:48 PM
//		new RootNode(new SableAST(rootNode),
//				new HashMap<SetElementItem, String>());

		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

	// yes('.'(=('PLOps','.'(op(addConf(term(bool(0)),int(1),fd(1,'CONF')),12,[],11),'.'(op(addConf(term(bool(0)),int(1),fd(2,'CONF')),13,[],11),'.'(op(addConf(term(bool(0)),int(1),fd(3,'CONF')),14,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(1,'CONF')),15,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(2,'CONF')),16,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(3,'CONF')),17,[],11),'.'(op(healthy(fd(1,'CONF')),18,[],11),'.'(op(healthy(fd(2,'CONF')),18,[],11),'.'(op(healthy(fd(3,'CONF')),18,[],11),[])))))))))),[]))
	@Test
	public void testOperation4() throws ResultParserException {
		final String testString = "yes('.'(=('PLOps','.'(op(addConf(term(bool(0)),int(1),fd(1,'CONF')),12,[],11),'.'(op(addConf(term(bool(0)),int(1),fd(2,'CONF')),13,[],11),'.'(op(addConf(term(bool(0)),int(1),fd(3,'CONF')),14,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(1,'CONF')),15,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(2,'CONF')),16,[],11),'.'(op(addConf(term(bool(1)),int(1),fd(3,'CONF')),17,[],11),'.'(op(healthy(fd(1,'CONF')),18,[],11),'.'(op(healthy(fd(2,'CONF')),18,[],11),'.'(op(healthy(fd(3,'CONF')),18,[],11),[])))))))))),[]))";
//		ProBResultParser.setUseVerboseOutput(true);
		final Start rootNode = ProBResultParser.parse(testString);
//		ProBResultParser.setUseVerboseOutput(false);

		/* Review: Using System.out.println is ok, this is a test only */
		System.out.println(testString); // NOPMD by fabian on 8/13/07 12:48 PM
//		new RootNode(new SableAST(rootNode),
//				new HashMap<SetElementItem, String>());

		assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}
	
	@Test
	public void testExampleThatFailedBefore() throws ResultParserException {
		String input = "yes('.'(=('APrefs','.'(preference('MAXINT',nat,'MaxInt, used for expressions such as xx::NAT (2147483647 for 4 byte ints)',animation,3),'.'(preference('MININT',neg,'MinInt, used for expressions such as xx::INT (-2147483648 for 4 byte ints)',animation,-1),'.'(preference('DEFAULT_SETSIZE',nat,'Size of unspecified deferred sets in SETS section',animation,2),'.'(preference('MAX_INITIALISATIONS',nat,'Max Number of Initialisations Computed',animation,4),'.'(preference('MAX_OPERATIONS',nat,'Max Number of Enablings per Operation Computed',animation,10),'.'(preference('ANIMATE_SKIP_OPERATIONS',bool,'Animate operations which are skip or PRE C THEN skip',animation,true),'.'(preference('EXPAND_CLOSURES_FOR_STATE',bool,'Convert lazy form back into explicit form for Variables, Constants, Operation Arguments',animation,true),'.'(preference('SYMBOLIC',bool,'Lazy expansion of lambdas and set comprehensions',animation,false),'.'(preference('CLPFD',bool,'Use CLP(FD) solver for B integers (restricts range to -2^28..2^28-1 on 32bit machines)',animation,false),'.'(preference('SMT',bool,'Enable SMT-Mode (aggressive treatment of : and /: inside predicates)',advanced,false),'.'(preference('STATIC_ORDERING',bool,'Use static ordering to enumerate constants which occur in most PROPERTIES first',advanced,false),'.'(preference('SYMMETRY_MODE','.'(off,'.'(flood,'.'(nauty,'.'(hash,[])))),'Symmetry Mode: off,flood,canon,nauty,hash',hidden,off),'.'(preference('TIME_OUT',nat1,'Time out for computing enabled transitions (in ms)',animation,2500),'.'(preference('USE_PO',bool,'Restrict invariant checking to affected clauses. Also remove clauses that are proven (EventB)',advanced,true),'.'(preference('TRY_FIND_ABORT',bool,'Try more aggressively to detect not well-defined expressions (e.g. applying function outside of domain), may slow down animator',animation,false),'.'(preference('NUMBER_OF_ANIMATED_ABSTRACTIONS',nat,'How many levels of refined models are animated by default',animation,20),'.'(preference('ALLOW_INCOMPLETE_SETUP_CONSTANTS',bool,'AllowProB to proceed even if only part of the CONSTANTS have been found.',advanced,false),'.'(preference('PARTITION_PROPERTIES',bool,'Partition predicates (PROPERTIES) into components',advanced,true),'.'(preference('USE_RECORD_CONSTRUCTION',bool,'Records: Check if axioms/properties describe a record pattern',advanced,true),[])))))))))))))))))))),'.'(=('BErrors',[]),[])))";
		final Start rootNode = ProBResultParser.parse(input);
        assertNotNull(PARSETREE_WAS_NULL, rootNode);
	}

}