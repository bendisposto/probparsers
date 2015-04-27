package de.be4.classicalb.core.parser.drophaskellregressions;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.MockedDefinitions;
import de.be4.classicalb.core.parser.node.Start;

public class PushbackBufferOverflows {
	@Test
	public void withoutPredVars() throws Exception {
		String source = "#PREDICATE procs : STRING +-> {\"waiting\",\"ready\"} & current : STRING & idle : {TRUE,FALSE} & idle = FALSE & procs /= { } & { \"waiting\" } /= { } & { \"waiting\" } /\\ ran(procs) = { } & { \"ready\" } = ran(procs)";
		BParser parser = new BParser();
		Start result = parser.parse(source, false);
		assertNotNull(result);
	}

	@Test
	public void withPredVars() throws Exception {
		String source = "#PREDICATE procs : STRING +-> {\"waiting\",\"ready\"} & current : STRING & idle : {TRUE,FALSE} & idle = FALSE & procs /= { } & { \"waiting\" } /= { } & { \"waiting\" } /\\ ran(procs) = { } & { \"ready\" } = ran(procs)";
		BParser parser = new BParser();
		Start result = parser.eparse(source, new MockedDefinitions());
		assertNotNull(result);
	}
}
