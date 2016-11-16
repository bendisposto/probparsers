package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.rules.project.RulesProject;

@SuppressWarnings("unused")
public class RulesProjectTest {

	@Test
	public void testProject2() throws Exception {
		File file = new File("src/test/resources/rules/references/test1/Rule1.rmch");
		ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.addLineNumbers = true;
		parsingBehaviour.prologOutput = true;
		RulesProject.parseProject(file, parsingBehaviour, System.out, System.err);
	}
}
