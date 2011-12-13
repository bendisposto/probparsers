package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.node.AExpressionDefinition;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.APredicateDefinition;
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

		final Definitions definitions = parser.getDefinitions();
		final AExpressionDefinition def1 = (AExpressionDefinition) definitions
				.getDefinition("def1");
		assertEquals("def1", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof AIdentifierExpression);

		final AExpressionDefinition def2 = (AExpressionDefinition) definitions
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

		final Definitions definitions = parser.getDefinitions();
		final AExpressionDefinition def1 = (AExpressionDefinition) definitions
				.getDefinition("def1");
		assertEquals("def1", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof AIdentifierExpression);
		String ident = Utils
				.getIdentifierAsString(((AIdentifierExpression) def1.getRhs())
						.getIdentifier());
		assertEquals("xx", ident);

		final AExpressionDefinition def2 = (AExpressionDefinition) definitions
				.getDefinition("def2");
		assertEquals("def2", def2.getName().getText());
		assertEquals(0, def2.getParameters().size());
		assertTrue(def2.getRhs() instanceof AIdentifierExpression);
		ident = Utils.getIdentifierAsString(((AIdentifierExpression) def2
				.getRhs()).getIdentifier());
		// definition in machine file should overwrite the one in def file
		assertEquals("aa", ident);

		final AExpressionDefinition def3 = (AExpressionDefinition) definitions
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
		URI uri = getClass().getClassLoader()
				.getResource("parsable/DefinitionFileTest.mch").toURI();
		File machine = new File(uri);
		parser.parseFile(machine, false);

		final Definitions definitions = parser.getDefinitions();
		final APredicateDefinition def1 = (APredicateDefinition) definitions
				.getDefinition("GRD2");
		assertEquals("GRD2", def1.getName().getText());
		assertEquals(0, def1.getParameters().size());
		assertTrue(def1.getRhs() instanceof PPredicate);

		final APredicateDefinition def2 = (APredicateDefinition) definitions
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

	class CountingDefinitionFileProvider implements IDefinitionFileProvider {
		int getStoredCounter = 0;
		int storeCounter = 0;
		int getContentCounter = 0;
		private final Map<String, Definitions> store = new HashMap<String, Definitions>();

		public Definitions getDefinitions(final String filename) {
			getStoredCounter++;
			return store.get(filename);
		}

		public void storeDefinition(final String filename,
				final Definitions definitions) {
			storeCounter++;
			store.put(filename, definitions);
		}

		public String getFileContent(final String filename) throws IOException {
			getContentCounter++;
			if ("DefFile1".equals(filename)) {
				return "DEFINITIONS \"DefFile2\"; def1 == 1";
			} else if ("DefFile2".equals(filename)) {
				return "DEFINITIONS def2 == 2";
			} else {
				return "";
			}
		}

		public File getFile(String fileName) {
			return null;
		}
	}

	public File getFile(String fileName) {
		return null;
	}
}
