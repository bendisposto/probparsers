package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;

public class DefinitionsTest {

	@Test
	public void testDefinitions1() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_sub1 == skip; def_pred1 == 2 > x; def_expr1 == 41 + 1\nOPERATIONS op = PRE def_pred1 THEN i := def_expr1 || def_sub1 END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([ASubstitutionDefinitionDefinition(def_sub1,[],ASkipSubstitution()),APredicateDefinitionDefinition(def_pred1,[],AGreaterPredicate(AIntegerExpression(2),AIdentifierExpression([x]))),AExpressionDefinitionDefinition(def_expr1,[],AAddExpression(AIntegerExpression(41),AIntegerExpression(1)))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(ADefinitionPredicate(def_pred1,[]),AParallelSubstitution([AAssignSubstitution([AIdentifierExpression([i])],[ADefinitionExpression(def_expr1,[])]),ADefinitionSubstitution(def_sub1,[])])))])]))",
				result);
	}

	@Test
	public void testDefinitions2() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_sub1 == skip; def_pred1 == 2 > x; def_expr1 == 41 + 1; def_expr2(a,b,c) == a+b*c \nOPERATIONS op = PRE def_pred1 THEN i := def_expr1 || def_sub1 || j := def_expr2(1,2,3) END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([ASubstitutionDefinitionDefinition(def_sub1,[],ASkipSubstitution()),APredicateDefinitionDefinition(def_pred1,[],AGreaterPredicate(AIntegerExpression(2),AIdentifierExpression([x]))),AExpressionDefinitionDefinition(def_expr1,[],AAddExpression(AIntegerExpression(41),AIntegerExpression(1))),AExpressionDefinitionDefinition(def_expr2,[AIdentifierExpression([a]),AIdentifierExpression([b]),AIdentifierExpression([c])],AAddExpression(AIdentifierExpression([a]),AMultOrCartExpression(AIdentifierExpression([b]),AIdentifierExpression([c]))))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(ADefinitionPredicate(def_pred1,[]),AParallelSubstitution([AAssignSubstitution([AIdentifierExpression([i])],[ADefinitionExpression(def_expr1,[])]),ADefinitionSubstitution(def_sub1,[]),AAssignSubstitution([AIdentifierExpression([j])],[ADefinitionExpression(def_expr2,[AIntegerExpression(1),AIntegerExpression(2),AIntegerExpression(3)])])])))])]))",
				result);
	}

	@Test
	public void testDefinitions3() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_expr1 == 41 + 1; def_expr2(a,b,c) == a+b*c \nOPERATIONS op = PRE 1 = 1 THEN i := def_expr2(1,def_expr1,3) END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(def_expr1,[],AAddExpression(AIntegerExpression(41),AIntegerExpression(1))),AExpressionDefinitionDefinition(def_expr2,[AIdentifierExpression([a]),AIdentifierExpression([b]),AIdentifierExpression([c])],AAddExpression(AIdentifierExpression([a]),AMultOrCartExpression(AIdentifierExpression([b]),AIdentifierExpression([c]))))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)),AAssignSubstitution([AIdentifierExpression([i])],[ADefinitionExpression(def_expr2,[AIntegerExpression(1),ADefinitionExpression(def_expr1,[]),AIntegerExpression(3)])])))])]))",
				result);
	}

	@Test
	public void testDefinitions4() throws Exception {
		final String testMachine = "MACHINE Test \n DEFINITIONS def_expr1 == 41 + 1; def_expr2(a,b,c) == a+def_expr1*c \nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(def_expr1,[],AAddExpression(AIntegerExpression(41),AIntegerExpression(1))),AExpressionDefinitionDefinition(def_expr2,[AIdentifierExpression([a]),AIdentifierExpression([b]),AIdentifierExpression([c])],AAddExpression(AIdentifierExpression([a]),AMultOrCartExpression(ADefinitionExpression(def_expr1,[]),AIdentifierExpression([c]))))])]))",
				result);
	}

	@Test
	public void testKeywordInRhs() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\nSTACKSTART == (MAXVAR+1); X==5\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(STACKSTART,[],AAddExpression(AIdentifierExpression([MAXVAR]),AIntegerExpression(1))),AExpressionDefinitionDefinition(X,[],AIntegerExpression(5))])]))",
				result);
	}

	@Test
	public void testScoping1() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_expr1 == 42 \n OPERATIONS op = PRE # def_expr1 . (def_expr1 < 43) THEN skip END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(def_expr1,[],AIntegerExpression(42))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(AExistsPredicate([AIdentifierExpression([def_expr1])],ALessPredicate(AIdentifierExpression([def_expr1]),AIntegerExpression(43))),ASkipSubstitution()))])]))",
				result);
	}

	@Test
	public void testScoping2() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_expr1 == 42 \n OPERATIONS op = PRE # def_expr1 . (# def_expr1 . (def_expr1 < 43) & def_expr1 > 41 ) & def_expr1 = 42 THEN skip END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(def_expr1,[],AIntegerExpression(42))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(AConjunctPredicate(AExistsPredicate([AIdentifierExpression([def_expr1])],AConjunctPredicate(AExistsPredicate([AIdentifierExpression([def_expr1])],ALessPredicate(AIdentifierExpression([def_expr1]),AIntegerExpression(43))),AGreaterPredicate(AIdentifierExpression([def_expr1]),AIntegerExpression(41)))),AEqualPredicate(ADefinitionExpression(def_expr1,[]),AIntegerExpression(42))),ASkipSubstitution()))])]))",
				result);
	}

	@Test
	public void testScoping3() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_expr1 == 42 \n OPERATIONS op = PRE ! def_expr1 . (def_expr1 < 43) THEN skip END END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(def_expr1,[],AIntegerExpression(42))]),AOperationsMachineClause([AOperation([],[op],[],APreconditionSubstitution(AForallPredicate([AIdentifierExpression([def_expr1])],ALessPredicate(AIdentifierExpression([def_expr1]),AIntegerExpression(43))),ASkipSubstitution()))])]))",
				result);
	}

	@Test
	public void testUnparsableRhs() {
		final String testMachine = "MACHINE Test\nDEFINITIONS def_expr1 == 42 < \n OPERATIONS op = PRE def_expr1 THEN skip END END";
		try {
			getTreeAsString(testMachine);
			fail("Was expecting BParseException");
		} catch (final BException e) {
			System.out.println(e.getLocalizedMessage());
			// IGNORE is expected
		}
	}

	@Test
	public void testExprInParanthesis() throws Exception {
		final String testMachine = "MACHINE BoolLaws\nDEFINITIONS\npt == (PP=TRUE);\nqt == (QQ=TRUE)\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([BoolLaws],[]),[ADefinitionsMachineClause([APredicateDefinitionDefinition(pt,[],AEqualPredicate(AIdentifierExpression([PP]),ABooleanTrueExpression())),APredicateDefinitionDefinition(qt,[],AEqualPredicate(AIdentifierExpression([QQ]),ABooleanTrueExpression()))])]))",
				result);
	}

	@Test
	public void testDoubleSemicolon() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\npt == (PP=TRUE);;\nqt == (QQ=TRUE)\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			// IGNORE, is expected
		}
	}

	@Test
	public void testDefClause() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS def2 == y;\ndef1 == xx";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AMachineClauseParseUnit(ADefinitionsMachineClause([AExpressionDefinitionDefinition(def2,[],AIdentifierExpression([y])),AExpressionDefinitionDefinition(def1,[],AIdentifierExpression([xx]))])))",
				result);
	}

	@Test
	public void testSemicolonInDef1() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS def1 == (f;g); def2 == skip";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AMachineClauseParseUnit(ADefinitionsMachineClause([AExpressionDefinitionDefinition(def1,[],ACompositionExpression(AIdentifierExpression([f]),AIdentifierExpression([g]))),ASubstitutionDefinitionDefinition(def2,[],ASkipSubstitution())])))",
				result);
	}

	@Test
	public void testSemicolonInDef2() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS law6 ==  (dom((ff ; (gg~))) <: dom(ff))";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AMachineClauseParseUnit(ADefinitionsMachineClause([APredicateDefinitionDefinition(law6,[],ASubsetPredicate(ADomainExpression(ACompositionExpression(AIdentifierExpression([ff]),AReverseExpression(AIdentifierExpression([gg])))),ADomainExpression(AIdentifierExpression([ff]))))])))",
				result);
	}

	@Test
	public void testSemicolonInDef3() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS\n  law1 ==  (dom(ff\\/gg) = dom(ff) \\/ dom(gg));\n  law2 ==  (ran(ff\\/gg) = ran(ff) \\/ ran(gg));\n  law3 ==  (dom(ff/\\gg) <: dom(ff) /\\ dom(gg));\n  law4 ==  (ran(ff/\\gg) <: ran(ff) /\\ ran(gg));\n  law5 ==  ( (ff \\/ gg)~ = ff~ \\/ gg~);\n  law6 ==  (dom((ff ; (gg~))) <: dom(ff));\n  law7 ==  (!(xx,yy).(xx:setX & yy:setY & xx|->yy : ff  =>  yy: ran(gg))\n              =>  (dom((ff ; (gg~))) = dom(ff)));\n  law8 ==  (ff : setX --> setY  <=>  (ff: setX +-> setY & dom(ff) = setX));\n  ff_is_pf == (!(xx,yy,zz).((xx:setX & yy:setY & zz:setY &\n                    xx|->yy:ff & xx|->zz:ff) => (yy=zz)));\n  law9 ==  (ff : setX +-> setY  <=> ff_is_pf);\n  law10 == (ff : setX >->> setY  <=>  (ff : setX >-> setY  &  ff~: setY >-> setX));\n  law11 == (ff : setX >+> setY  <=> (ff: setX +-> setY &\n                                !(xx,yy).(xx:setX & yy:setX & xx/=yy & xx:dom(ff) & yy: dom(ff)  => ff(xx)/=ff(yy)))) ;\n  law12 == (ff : setX +->> setY  <=>  (ff: setX +-> setY &\n                                    !yy.(yy:setY => yy: ran(ff))))";
		getTreeAsString(testMachine);
	}

	@Test
	public void testDefWithNesting1() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS CONSTR3 == (!(f,p).(f:FLIGHTS & f<NRF-1 & p:PERSONNEL &  f|->p:assign & (f+1)|->p:assign => (f+2)|->p /: assign))";
		getTreeAsString(testMachine);
	}

	@Test
	public void testDefWithNesting2() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS FT_TYPE == (from:NODES & to:NODES & from/=to); FTE_TYPE == (FT_TYPE & packet:PACKETS & type:TYPE)";
		getTreeAsString(testMachine);
	}

	@Test
	public void testDefWithNesting3() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS\nFaileSafeIsOn == (sw>0);\nTurnFailSafeOff == BEGIN sw := 0 END;\nTurnFailSafeOn == BEGIN sw := (sw + 1) mod 256\nEND";
		getTreeAsString(testMachine);
	}

	@Test
	public void testDefWithNesting4() throws Exception {
		final String testMachine = "#DEFINITIONS DEFINITIONS\nlaw1 ==  (SS \\/ SS = SS  &  SS = SS \\/ {}  &  SS = SS /\\ SS  &  SS = SS \\ {})";
		getTreeAsString(testMachine);
	}

	@Test
	public void testSemicolonAtEnd1() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\npt == PP=TRUE;\nqt == QQ=TRUE\nEND\n";
		getTreeAsString(testMachine);
	}

	@Test
	public void testSemicolonAtEnd2() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\npt == PP=TRUE;\nqt == QQ=TRUE;\nEND\n";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([APredicateDefinitionDefinition(pt,[],AEqualPredicate(AIdentifierExpression([PP]),ABooleanTrueExpression())),APredicateDefinitionDefinition(qt,[],AEqualPredicate(AIdentifierExpression([QQ]),ABooleanTrueExpression()))])]))",
				result);
	}

	@Test
	public void testExprOrSubst1() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefSubst==g(x)\nOPERATIONS\nop=defSubst\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([ASubstitutionDefinitionDefinition(defSubst,[],AOpSubstitution(AIdentifierExpression([g]),[AIdentifierExpression([x])]))]),AOperationsMachineClause([AOperation([],[op],[],ADefinitionSubstitution(defSubst,[]))])]))",
				result);
	}

	@Test
	public void testExprOrSubst2() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefSubst==g(x)\nOPERATIONS\nop= a:=defSubst\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(defSubst,[],AFunctionExpression(AIdentifierExpression([g]),[AIdentifierExpression([x])]))]),AOperationsMachineClause([AOperation([],[op],[],AAssignSubstitution([AIdentifierExpression([a])],[ADefinitionExpression(defSubst,[])]))])]))",
				result);
	}

	@Test
	public void testExprOrSubst3() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefExpr==g(x)\nOPERATIONS\nop=PRE defExpr=42 THEN defExpr END\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			final BParseException cause = (BParseException) e.getCause();
			assertNull(cause.getToken());
			assertEquals(25, cause.getRange().getBeginIndex());
			assertEquals(
					"Expecting substitution here but found definition with type 'Expression'",
					cause.getLocalizedMessage());
			// IGNORE, is expected
		}
	}

	@Test
	public void testExprOrSubst4() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefExpr==g(x)\nOPERATIONS\nop=BEGIN defExpr; a:=defExpr END\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			final BParseException cause = (BParseException) e.getCause();
			assertNull(cause.getToken());
			assertEquals(24, cause.getRange().getBeginIndex());
			assertEquals(
					"Expecting expression here but found definition with type 'Substitution'",
					cause.getLocalizedMessage());
			// IGNORE, is expected
		}
	}

	@Test
	public void testExprOrSubstWParams1() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefSubst(x)==g(x)\nOPERATIONS\nop=defSubst(x)\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([ASubstitutionDefinitionDefinition(defSubst,[AIdentifierExpression([x])],AOpSubstitution(AIdentifierExpression([g]),[AIdentifierExpression([x])]))]),AOperationsMachineClause([AOperation([],[op],[],ADefinitionSubstitution(defSubst,[AIdentifierExpression([x])]))])]))",
				result);
	}

	@Test
	public void testExprOrSubstWParams2() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefSubst(x)==g(x)\nOPERATIONS\nop= a:=defSubst(x)\nEND";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([AExpressionDefinitionDefinition(defSubst,[AIdentifierExpression([x])],AFunctionExpression(AIdentifierExpression([g]),[AIdentifierExpression([x])]))]),AOperationsMachineClause([AOperation([],[op],[],AAssignSubstitution([AIdentifierExpression([a])],[ADefinitionExpression(defSubst,[AIdentifierExpression([x])])]))])]))",
				result);
	}

	@Test
	public void testExprOrSubstWParams3() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefExpr(x)==g(x)\nOPERATIONS\nop=PRE defExpr(x)=42 THEN defExpr(x) END\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			final BParseException cause = (BParseException) e.getCause();
			assertNull(cause.getToken());
			assertEquals(31, cause.getRange().getBeginIndex());
			assertEquals(
					"Expecting substitution here but found definition with type 'Expression'",
					cause.getLocalizedMessage());
			// IGNORE, is expected
		}
	}

	@Test
	public void testExprOrSubstWParams4() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefExpr(x)==g(x)\nOPERATIONS\nop=BEGIN defExpr(x); a:=defExpr(x) END\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			final BParseException cause = (BParseException) e.getCause();
			assertNull(cause.getToken());
			assertEquals(30, cause.getRange().getBeginIndex());
			assertEquals(
					"Expecting expression here but found definition with type 'Substitution'",
					cause.getLocalizedMessage());
			// IGNORE, is expected
		}
	}

	@Test
	public void testParamsCount1() {
		final String testMachine = "MACHINE Test\nDEFINITIONS\ndefExpr(x)==g(x)\nOPERATIONS\nop=BEGIN defExpr(x); defExpr END\nEND";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			final CheckException cause = (CheckException) e.getCause();
			assertEquals(1, cause.getNodes().length);
			assertNotNull(cause.getNodes()[0]);
			assertEquals(
					"Number of parameters doesn't match declaration of definition",
					cause.getLocalizedMessage());
			// IGNORE, is expected
		}
	}

	public void testAssertInDefinition() throws BException {
		final String testMachine = "MACHINE Test\n" + "DEFINITIONS\n"
				+ "ABORT == ASSERT TRUE=FALSE THEN skip END\n" + "END\n";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[ADefinitionsMachineClause([ASubstitutionDefinitionDefinition(ABORT,[],AAssertionSubstitution(AEqualPredicate(ABooleanTrueExpression(),AFalseExpression()),ASkipSubstitution()))])]))",
				result);
	}

	private String getTreeAsString(final String testMachine) throws BException {
		// System.out.println();
		// System.out.println();
		// System.out.println("Parsing \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true,
				new PlainFileContentProvider());

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}
}
