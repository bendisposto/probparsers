package de.be4.classicalb.core.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;

public class DropHaskellRegressionTests {

	@Test
	public void test() throws BCompoundException, IOException {
		String name = "src/test/resources/parsable/InfiniteParityFunction.mch";
		BParser parser = new BParser(name);
		parser.parseFile(new File(name), false);

		String code = "not(finite({x|x>2}))";
		BParser parser2 = new BParser(name);
		
		parser2.getDefinitions().addDefinitions(parser.getDefinitions());
		Start parse = parser2.parse(BParser.FORMULA_PREFIX + " " + code, false,
				parser.getContentProvider());
		assertNotNull(parse);

	}

}
