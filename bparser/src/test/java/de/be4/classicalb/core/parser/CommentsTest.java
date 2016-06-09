package de.be4.classicalb.core.parser;

import static util.Helpers.getTreeAsString;

import org.junit.Test;

public class CommentsTest {

	
	@Test
	public void testCommentInsideEmptySet() throws Exception {
		final String testMachine = "MACHINE Comments CONSTANTS k PROPERTIES k : POW(INTEGER) & k = { /* comment */  } END";
		getTreeAsString(testMachine);
	}
	
	@Test
	public void testCommentInsideEmptySequence() throws Exception {
		final String testMachine = "MACHINE Comments CONSTANTS k PROPERTIES k : seq(INTEGER) & k = [ /* comment */  ] END";
		getTreeAsString(testMachine);
	}
	
	@Test
	public void testCommentInsideEmptySequence2() throws Exception {
		final String testMachine = "MACHINE Comments CONSTANTS k PROPERTIES k : seq(INTEGER) & k = < /* comment */  > END";
		getTreeAsString(testMachine);
	}
}
