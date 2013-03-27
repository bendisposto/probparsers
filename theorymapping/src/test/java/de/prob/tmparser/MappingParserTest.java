package de.prob.tmparser;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class MappingParserTest {

	@Test
	public void testNo() throws TheoryMappingException, IOException {
		final OperatorMapping om1 = new OperatorMapping("SUMPRODUCT", "SUM",
				TMOperatorType.INTERNAL, "SIGMA");
		final OperatorMapping om2 = new OperatorMapping("SUMPRODUCT",
				"PRODUCT", TMOperatorType.INTERNAL, "PI");

		final String spec = "operator \"SUM\" internal {SIGMA}\n"
				+ "operator \"PRODUCT\" internal {PI}";
		Collection<OperatorMapping> result = TheoryMappingParser
				.parseTheoryMapping("SUMPRODUCT", new StringReader(spec));
		Assert.assertEquals(2, result.size());
		Assert.assertTrue("SUM operator present", result.contains(om1));
		Assert.assertTrue("PRODUCT operator present", result.contains(om2));
	}
}