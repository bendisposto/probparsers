package de.be4.eventbalg.parser;

import org.junit.Test;

import de.be4.eventbalg.core.parser.BException;
import de.be4.eventbalg.core.parser.analysis.ASTDisplay;
import de.be4.eventbalg.core.parser.analysis.ASTPrinter;
import de.be4.eventbalg.core.parser.node.Start;

public class AlgorithmTest extends AbstractTest {

	@Test
	public void test() throws BException {
		parseInput(
				"machine m0 algorithm while: 1=1 do assert: 1=3 end; assert: 1=1; assert: 1=2 end end",
				false);
	}

	@Test
	public void test2() throws BException {
		parseInput(
				"machine m0 algorithm while: u /=0 do if: u < v then @act u:=v; @act2 v:=u else @act4 r:=x end; @act3 u := u - v end end end",
				false);
	}

	@Test
	public void nestedWhiles() throws BException {
		parseInput(
				"machine m0 algorithm while: a do while: b do @act u:=v end; while: c do @act2 u:=r end end end end",
				false);
	}

	@Test
	public void nestedWhilesWithVariant() throws BException {
		parseInput(
				"machine m0 algorithm while: a variant: x+y do while: b variant: b do @act u:=v end; while: c do @act2 u:=r end end end end",
				true);
	}

	@Test
	public void nestedWhilesWithInvariant() throws BException {
		parseInput(
				"machine m0 algorithm while: a invariant: x+y=0 do while: b invariant: b/=0 do @act u:=v end; while: c do @act2 u:=r end end end end",
				false);
	}

	@Test
	public void nestedWhilesWithVariantAndInvariant() throws BException {
		parseInput(
				"machine m0 algorithm while: a invariant: x+y=0 variant: x+y do while: b invariant: x+y=0 variant: b do @act u:=v end; while: c do @act2 u:=r end end end end",
				false);
	}

	@Test
	public void nestedIfs() throws BException {
		parseInput(
				"machine m0 algorithm if: a then if: b then @act u:=v end else @act4 r := x end; while: c do @act2 u:=r end end end",
				false);
	}

	@Test
	public void fullEuclid() throws BException {
		parseInput(
				"machine euclid\n  sees definitions limits\nvariables u v\ninvariants\n  @inv u : 0..k\n  @inv2 v : 0..k\n\nevents\n  event INITIALISATION\n    then\n      @act u:=m\n      @act2 v:=n\n  end\n\n  algorithm\n    while: u /= 0 \n    do\n      if: u < v then\n        @u u := v ;\n        @v v := u\n      end ;\n      @uu u := u - v\n    end ;\n    assert: u|->m|->n : IsGCD\n  end\n\nend",
				false);
	}
}
