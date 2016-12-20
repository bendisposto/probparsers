package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.rules.project.IModel;
import de.be4.classicalb.core.parser.rules.project.RulesParseUnit;
import de.be4.classicalb.core.parser.rules.project.RulesProject;

public class RulesProjectExceptionTest {

	@Test
	public void testDuplicateOperationNameException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo BODY skip END; COMPUTATION foo BODY skip END END";
		String result = parseRulesPrologAndGetExceptionAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,67,null),'Duplicate operation name: \\'foo\\'.').\n", result);
	}

	@Test
	public void testDependsOnRuleIsNotARuleException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE bar BODY skip END; COMPUTATION bar BODY skip END END";
		String result = parseRulesPrologAndGetExceptionAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,56,null),'Operation \\'bar\\' is not a RULE operation.').\n", result);
	}

	@Test
	public void testUnkownRuleInPredicateOperatorException() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == FAILED_RULE(foo) END";
		String result = parseRulesPrologAndGetExceptionAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("does not match any rule"));
	}

	@Test
	public void testRulesMachineInOrdinaryMachineFileException() throws Exception {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};
		PrintStream pStream = new PrintStream(output);
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.prologOutput = true;
		BParser bParser = new BParser("RulesMachineInOrdinaryMachineFile.mch");
		bParser.fullParsing(new File("src/test/resources/rules/project/RulesMachineInOrdinaryMachineFile.mch"),
				parsingBehaviour, pStream, pStream);
		System.out.println(output.toString());
		assertTrue(output.toString().contains("parse_exception"));
	}

	public static String parseRulesPrologAndGetExceptionAsPrologTerm(final String content) throws Exception {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		RulesProject project = new RulesProject(null);
		project.setParsingBehaviour(pb);
		Field field = RulesProject.class.getDeclaredField("bModels");
		field.setAccessible(true);
		List<IModel> bModels = new ArrayList<>();
		bModels.add(unit);
		field.set(project, bModels);
		{
			Method method = RulesProject.class.getDeclaredMethod("checkProject");
			method.setAccessible(true);
			method.invoke(project);
		}

		if (!project.hasErrors()) {
			Method method = RulesProject.class.getDeclaredMethod("flattenProject");
			method.setAccessible(true);
			method.invoke(project);
		}

		assertTrue(project.hasErrors());

		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};
		PrintStream pStream = new PrintStream(output);
		{
			Method method = RulesProject.class.getDeclaredMethod("printPrologOutput", PrintStream.class,
					PrintStream.class);
			method.setAccessible(true);
			method.invoke(project, pStream, pStream);
		}
		return output.toString();
	}

}
