package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class RulesProjectTest {

	@Test
	public void testRuleDependencies() {
		String testMachine = "RULES_MACHINE Test OPERATIONS\n";
		testMachine += "COMPUTATION c1 DEPENDS_ON_RULE r1 BODY DEFINE x2 TYPE POW(INTEGER) VALUE x1 END END;\n";
		testMachine += "RULE r1 BODY VAR foo IN foo := x1 END;RULE_FAIL COUNTEREXAMPLE \"never\" END END;\n";
		testMachine += "COMPUTATION c2 BODY DEFINE x3 TYPE POW(INTEGER) VALUE x1 \\/ x2  END END;\n";
		testMachine += "COMPUTATION c3 BODY DEFINE x1 TYPE POW(INTEGER) VALUE {} END END\n";
		testMachine += "END";
		RulesProject p = new RulesProject();
		p.parseRulesMachines(testMachine);
		p.checkAndTranslateProject();
		AbstractOperation abstractOperation = p.getOperationsMap().get("c2");
		List<AbstractOperation> deps = abstractOperation.getSortedListOfTransitiveDependencies();
		System.out.println(deps);
		assertEquals("c3", deps.get(0).getName());
		assertEquals("r1", deps.get(1).getName());
		assertEquals("c1", deps.get(2).getName());
	}

}
