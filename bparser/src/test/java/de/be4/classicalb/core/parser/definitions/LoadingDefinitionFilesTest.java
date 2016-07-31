package de.be4.classicalb.core.parser.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Helpers;

public class LoadingDefinitionFilesTest {

	
	@Test
	public void testDefinitionFileLoadedByADefinitionFile() {
		String PATH = "src/test/resources/definitions/definitionFiles/";
		String file = PATH + "MachineIncludingDefinitionFiles.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("MachineIncludingDefinitionFiles.mch"));
		assertTrue(result.contains("Foo.def"));
		assertTrue(result.contains("Bar.def"));
	}
	
	@Test
	public void testDefinitionFileDoesNotExists() {
		String PATH = "src/test/resources/definitions/definitionFiles/";
		String file = PATH + "MachineIncludingNotExistingDefinitionFile.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("Definition file cannot be read"));
	}
	
	@Test
	public void testCyclicDefinitionFile() {
		String PATH = "src/test/resources/definitions/definitionFiles/cycle/";
		String file = PATH + "MachineIncludingCyclicDefinitionFiles.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("Cyclic references in definition files"));
	}
	
	@Test
	public void testLoadingDefinitionsFilesFormAnotherMachine() {
		String PATH = "src/test/resources/definitions/definitionFiles/";
		String file = PATH + "MachineIncludingDefinitionsFromAnotherMachine.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("DefinitionOfMachineWithDefinitions"));
	}
	
	@Test
	public void testLoadingDefinitionsFilesInSubdirectory() {
		String PATH = "src/test/resources/definitions/definitionFiles/";
		String file = PATH + "MachineIncludingDefinitionFileInSubdirectory.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("DefinitionInSubdirectory"));
	}
	
	@Test
	public void testLoadingDefinitionChain() {
		String PATH = "src/test/resources/definitions/definitionFiles/chain/";
		String file = PATH + "MachineIncludingDefinitionFileChain.mch";
		String result = Helpers.fullParsing(file);
		assertTrue(result.contains("A.def"));
		assertTrue(result.contains("B.def"));
		assertTrue(result.contains("C.def"));
	}
	
	@Test
	public void testOverridingDefinition() {
		String PATH = "src/test/resources/definitions/definitionFiles/";
		String file = PATH + "MachineOverridingDefinition.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("Duplicate definition: DefinitionOfBar"));
	}
	
	@Test
	public void testOverridingDefinition2() {
		String PATH = "src/test/resources/definitions/definitionFiles/overridingDefinition/";
		String file = PATH + "MachineIncludesA.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("Duplicate definition: A1"));
	}
	
	@Test
	public void testOverridingDefinition3() {
		String PATH = "src/test/resources/definitions/definitionFiles/overridingDefinition/";
		String file = PATH + "MachineIncludesAAndA2.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}
	
	@Test
	public void testSeesAndIncludes() {
		String PATH = "src/test/resources/definitions/definitionFiles/seesAndIncludes/";
		String file = PATH + "A.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}
	
}
