package de.be4.classicalb.core.parser.definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import util.Helpers;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.IDefinitionFileProvider;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.IFileContentProvider;
import de.be4.classicalb.core.parser.PlainFileContentProvider;
import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PPredicate;

public class DefinitionFilesTest implements IFileContentProvider {

	private static final Map<String, String> defFileContents = new HashMap<String, String>();

	static {
		defFileContents.put("DefFile", "DEFINITIONS def2 == yy; def3 == zz");
		defFileContents.put("DefFile1", "DEFINITIONS \"DefFile2\"; def3 == bb");
		defFileContents.put("DefFile2", "DEFINITIONS def2 == yy; def3 == zz");
		defFileContents.put("DefFile3", "DEFINITIONS \"DefFile4\"");
		defFileContents.put("DefFile4", "DEFINITIONS \"DefFile3\"");
		defFileContents.put("DefFile5", "DEFINITIONS \"DefFile6\"");
		defFileContents.put("DefFile6", "DEFINITIONS def == 5");
	}

	@Test
	public void testOneDefinitionFile() throws BException {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile\"; def1 == xx\nINVARIANT def2 = def3\nEND";
		final BParser parser = new BParser("testcase");
		parser.parse(testMachine, true, this);

		final IDefinitions definitions = parser.getDefinitions();
		final AExpressionDefinitionDefinition def1 = (AExpressionDefinitionDefinition) definitions
				.getDefinition("def1");
		assertEquals("def1", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof AIdentifierExpression);

		final AExpressionDefinitionDefinition def2 = (AExpressionDefinitionDefinition) definitions
				.getDefinition("def2");
		assertEquals("def2", def2.getName().getText());
		assertEquals(0, def2.getParameters().size());
		assertTrue(def2.getRhs() instanceof AIdentifierExpression);
	}

	// TODO test two files

	/*
	 * test recursive references from def file to def file
	 */
	@Test
	public void testRecursiveReference() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile1\"; def1 == xx; def2 == aa\nEND";
		final BParser parser = new BParser("testcase");
		parser.parse(testMachine, false, this);

		final IDefinitions definitions = parser.getDefinitions();
		final AExpressionDefinitionDefinition def1 = (AExpressionDefinitionDefinition) definitions
				.getDefinition("def1");
		assertEquals("def1", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof AIdentifierExpression);
		String ident = Utils
				.getIdentifierAsString(((AIdentifierExpression) def1.getRhs())
						.getIdentifier());
		assertEquals("xx", ident);

		final AExpressionDefinitionDefinition def2 = (AExpressionDefinitionDefinition) definitions
				.getDefinition("def2");
		assertEquals("def2", def2.getName().getText());
		assertEquals(0, def2.getParameters().size());
		assertTrue(def2.getRhs() instanceof AIdentifierExpression);
		ident = Utils.getIdentifierAsString(((AIdentifierExpression) def2
				.getRhs()).getIdentifier());
		// definition in machine file should overwrite the one in def file
		assertEquals("aa", ident);

		final AExpressionDefinitionDefinition def3 = (AExpressionDefinitionDefinition) definitions
				.getDefinition("def3");
		assertEquals("def3", def3.getName().getText());
		assertEquals(0, def3.getParameters().size());
		assertTrue(def3.getRhs() instanceof AIdentifierExpression);
		ident = Utils.getIdentifierAsString(((AIdentifierExpression) def3
				.getRhs()).getIdentifier());
		// definition in outer def file should overwrite the one in referenced
		// def file
		assertEquals("bb", ident);
	}

	/*
	 * test circles references between def files
	 */
	@Test
	public void testCircleReference() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile3\"\nEND";
		final BParser parser = new BParser("testcase");
		try {
			parser.parse(testMachine, false, this);
			fail("Expected PreParseException missing");
		} catch (final BException e) {
			assertTrue(e.getCause() instanceof PreParseException);
		}
	}

	/*
	 * test circles references between def files
	 */
	@Test
	public void testNonCircleReference() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile5\";\n\"DefFile6\"\nEND";
		final BParser parser = new BParser("testcase");
		parser.parse(testMachine, false, this);
	}

	/*
	 * test with real files
	 */
	@Test
	public void testRealFiles() throws Exception {
		final BParser parser = new BParser("testcase");
		File machine = new File(
				"src/test/resources/parsable/DefinitionFileTest.mch");
		parser.parseFile(machine, false);

		final IDefinitions definitions = parser.getDefinitions();
		final APredicateDefinitionDefinition def1 = (APredicateDefinitionDefinition) definitions
				.getDefinition("GRD2");
		assertEquals("GRD2", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof PPredicate);

		final APredicateDefinitionDefinition def2 = (APredicateDefinitionDefinition) definitions
				.getDefinition("GRD1");
		assertEquals("GRD1", def2.getName().getText());
		assertEquals(0, def2.getParameters().size());
		assertTrue(def2.getRhs() instanceof PPredicate);
	}

	@Test
	public void testNotExistingFile() {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile\"; def1 == xx\nEND";
		try {
			new BParser("testcase").parse(testMachine, false,
					new PlainFileContentProvider());
			fail("Expected exception was not thrown");
		} catch (final BException e) {
			// EXPECTED
		}
	}

	@Test
	public void testDefCaching() throws Exception {
		final String testMachine = "MACHINE Test\nDEFINITIONS \"DefFile1\"; \"DefFile2\"\nEND";
		final BParser parser = new BParser("testcase");
		final CountingDefinitionFileProvider provider = new CountingDefinitionFileProvider();
		parser.parse(testMachine, false, provider);

		assertEquals(4, provider.getStoredCounter);
		assertEquals(2, provider.storeCounter);
		assertEquals(2, provider.getContentCounter);
	}

	public String getFileContent(final String filename) throws IOException {
		return defFileContents.get(filename);
	}

	@Override
	public String getFileContent(File directory, String filename)
			throws IOException {
		return defFileContents.get(filename);
	}

	@Test
	public void testErrorInDefinitions() throws IOException, BException {
		String file = "./src/test/resources/definitions/errors/DefinitionErrorPosition.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result
				.startsWith("parse_exception(pos(3,1,"));
	}

	@Test
	public void testErrorInIncludedDefinitionFile() throws IOException,
			BException {
		String file = "./src/test/resources/definitions/errors/MachineWithErrorInIncludedDefinitionFile.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result
				.startsWith("parse_exception(pos(3,1,"));
	}

	class CountingDefinitionFileProvider implements IDefinitionFileProvider {
		int getStoredCounter = 0;
		int storeCounter = 0;
		int getContentCounter = 0;
		private final Map<String, IDefinitions> store = new HashMap<String, IDefinitions>();

		public IDefinitions getDefinitions(final String filename) {
			getStoredCounter++;
			return store.get(filename);
		}

		public void storeDefinition(final String filename,
				final IDefinitions definitions) {
			storeCounter++;
			store.put(filename, definitions);
		}

		@Override
		public String getFileContent(File directory, String filename)
				throws IOException {
			getContentCounter++;
			if ("DefFile1".equals(filename)) {
				return "DEFINITIONS \"DefFile2\"; def1 == 1";
			} else if ("DefFile2".equals(filename)) {
				return "DEFINITIONS def2 == 2";
			} else {
				return "";
			}
		}

		@Override
		public File getFile(File directory, String fileName) throws IOException {
			return null;
		}
	}

	@Override
	public File getFile(File directory, String fileName) throws IOException {
		return null;
	}

}
