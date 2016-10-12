
import java.io.IOException;
import java.sql.Timestamp;

import org.junit.Ignore;
import org.junit.Test;


import util.Helpers;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.exceptions.BException;

public class Tester {

	@Test
	public void testFormula() throws Exception {
		final String testMachine = "#FORMULA a";
		final String result = util.Helpers.getTreeAsString(testMachine);
		System.out.println(result);
	}
	
	@Ignore
	@Test
	public void testFormula2() throws Exception {
		final String testMachine = "#FORMULA (A <=> (waiting={})) & A";
		final String result = util.Helpers.getTreeAsString(testMachine);
		System.out.println(result);
	}
	
	@Test
	public void testFile() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/Implementation/Main.mch";
		//String result = Helpers.fullParsing(file);
		//System.out.println(result);
		Helpers.parseFile(file);
		
	}
	

	
	@Test
	public void testFile2() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/PerformanceTests/SATLIB/uf50-02.mch";
		//String result = Helpers.fullParsing(file);
		//System.out.println(result);
		Helpers.parseFile(file);
		
	}

	@Test
	public void testFile3() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/PerformanceTests/SATLIB/sudoku.mch";
		//String result = Helpers.fullParsing(file);
		//System.out.println(result);
		Helpers.parseFile(file);
		
	}
	
	@Test
	public void testPositions() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/Bugs/PositionInfo/Main.mch";
		//Helpers.parseFile(file);
		final ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.prologOutput = true;
		parsingBehaviour.useIndention = false;
		parsingBehaviour.addLineNumbers = true;
		parsingBehaviour.verbose = true;
		parsingBehaviour.machineNameMustMatchFileName = true;
		String result = Helpers.fullParsing(file, parsingBehaviour);
		System.out.println(result);
		
	}
	
	@Test
	public void testTest() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/PragmasUnits/InternalRepresentationTests/MultiplicationConversion.mch";
		//String result = Helpers.fullParsing(file);
		//System.out.println(result);
		Helpers.parseFile(file);
		String result = Helpers.fullParsing(file);
		System.out.println(result);

	}
	
	
	@Test
	public void testString() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/XML2B/StringTest.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachine1() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/EngineeringRules/src/test/Track__Rule_ValidTrack/Test_Success.mch";
		String result = Helpers.fullParsing(file);
		//Helpers.parseFile(file);
		System.out.println(result);
	}
	
	@Test
	public void testMachine10() throws IOException, BException {
		java.util.Date date1 = new java.util.Date();
		System.out.println(new Timestamp(date1.getTime()));
		String file = "/Users/hansen/git/thales_git/EngineeringRules/src/data/temp/STR_FRUTIGEN_03_01.mch";
		String result = Helpers.fullParsing(file);
		//Helpers.parseFile(file);
		//System.out.println(result);
		
		System.out.println("********** FINISHED ************");
		java.util.Date date = new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));
	}
	
	@Test
	public void testGoal() throws Exception {
		final String testMachine = "RULES_MACHINE MyTest END";
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}
	
	@Test
	public void testMachine2() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/ExternalFunctions/TestLibraryMeta.mch";
		String result = Helpers.fullParsing(file);
		//Helpers.parseFile(file);
		System.out.println(result);
	}
	
	@Test
	public void testMachine3() throws IOException, BException {
		String file = "/Users/hansen/git//prob_examples/public_examples/B/PragmasUnits/Tests/SomeUnitErrors.mch";
		String result = Helpers.fullParsing(file);
		//Helpers.parseFile(file);
		System.out.println(result);
	}
	
	@Test
	public void testDefinitions() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/Definitions/DefinitionInQuantifier.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}
	
	@Test
	public void test2() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/thales_documents/Engineering_Rules/LanguageExtensions/ForLoopNewSyntax.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachineAllMAs() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/EngineeringRules/src/runner/Runner_LBL.mch";
		// String result =
		// Helpers.parseFile(file);
		Helpers.fullParsing(file);
		// System.out.println(result);
	}

	@Ignore
	@Test
	public void testDefinitionFile() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/thales_documents/Engineering_Rules/LanguageExtensions/ChooseTest.mch";
		// String result =
		// Helpers.parseFile(file);
		Helpers.parseFile(file);
//		String result = Helpers.fullParsing(file);
//		System.out.println(result);
	}
	
	
	@Ignore
	@Test
	public void testLexerException() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/SyntaxHighlighting/Foo.mch";
		// String result =
		Helpers.parseFile(file);
		// Helpers.fullParsing(file);
	}

	@Test
	public void testPushBackBufferTest() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/Tickets/ParserPushBackOverflow/CorrectedTrackData2.mch";
		//Helpers.parseFile(file);
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testPushBackBuffer() throws IOException, BException {
		String file = "/Users/hansen/git/prob_examples/public_examples/B/Tickets/ParserPushBackOverflow/PushBackBufferError.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);

		// Helpers.parseFile(file);
		// String result = Helpers.fullParsing(file);
		// System.out.println(result);
	}

	@Ignore
	@Test
	public void testDefinitionError() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/Definitions/DefinitionError.mch";

		String result = Helpers.fullParsing(file);
		System.out.println(result);

		Helpers.parseFile(file);
		// String result = Helpers.fullParsing(file);
		// System.out.println(result);
	}

	@Ignore
	@Test
	public void testMachineNotFound3() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/EngineeringRules/src/runner/Runner_LBL.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachineNotFound5() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/EngineeringRules/src/variables/Variables_Exit.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachineNotFound6() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/Definitions/DefinitionErrorPosition.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachineNotFound7() throws IOException, BException {
		String file = "/Users/hansen/Desktop/temp/String/StringPosition.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	@Test
	public void testMachineNotFound8() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/thales_documents/Schulung/Folien/Training2/Models/RulesSyntax/SimpleRulesMachine.mch";
		Helpers.parseFile(file);
		// String result = Helpers.fullParsing(file);
		// System.out.println(result);
	}


	@Test
	public void testMachineNotFound22() throws IOException, BException {
		String file = "/Users/hansen/git/thales_git/thales_documents/Schulung/Folien/Training2/Models/TrackGraph/CorrectedTrackData_Rules.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
	}

	// /prob_examples/public_examples/CSP/other/Ivo/BobAlice.csp -ltlassertions
	// -strict -strict -p
}
