package de.be4.classicalb.core.parser.drophaskellregressions;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class Test1002 {

	@Test
	public void file1() throws BException {
		String source = "cajas : STRING +-> NATURAL & num : STRING & m : NATURAL & m > cajas(num) & num : dom(cajas) & cajas /= { } & { num |-> ( ( cajas(num) ) - m ) } /= { } & dom({ num |-> ( ( cajas(num) ) - m ) }) <<: dom(cajas)";
		Start result = BParser.parse(BParser.FORMULA_PREFIX + "" + source);
		assertNotNull(result);
	}

	@Test
	public void file2() throws BException {

		String source = " clients : STRING +-> STRING & balances : STRING +-> NATURAL & owners : STRING <-> STRING & u : STRING & n : STRING & m : NATURAL & m > balances(n) & m < 100000";
		Start result = BParser.parse(BParser.FORMULA_PREFIX + "" + source);
		assertNotNull(result);
	}

	@Test
	public void file3() throws BException {
		String source = "srv : {\"HS\",\"SC\",\"COM\",\"VOM\",\"SDA\",\"RDA\",\"TD\",\"RA\",\"GMR\",\"LDM\",\"EP\",\"MSP\"} & om : {\"SAFETY\",\"NOM\",\"DIAG\"} & acquiring : {TRUE,FALSE} & prepData : {TRUE,FALSE} & prepDataType : {\"SD\",\"HD\",\"MD\"} & page : NATURAL & ia : NATURAL & fa : NATURAL & lpck : seq(STRING) & lpckDT : {\"CR\",\"CD\",\"OM\",\"ND\",\"ASD\",\"AMD\",\"MSE\",\"ICS\",\"LS\"} & csrs : {\"SD\",\"HD\",\"MD\"} --> NATURAL & totCSR : {\"SD\",\"HD\",\"MD\"} --> NATURAL & csc : NATURAL & mem : 1 .. 1024 --> STRING & modMem : POW(NATURAL) & sdwp : NATURAL & sparam : {\"HGT\",\"ILP\",\"AICST\"} --> NATURAL & time : NATURAL & processingCmd : {TRUE,FALSE} & iain : NATURAL & data : seq(STRING) & c : NATURAL & processingCmd = TRUE & srv = \"LDM\" & om = \"NOM\" & csc > 0 & c = csc - 1 & sparam(\"ILP\") <= iain & iain + card(data) : 0 .. 32 & {x | x-iain:dom(data)} /\\ modMem = { } & {x | x-iain:dom(data)} /= { } & modMem = { }";
		Start result = BParser.parse(BParser.FORMULA_PREFIX + "" + source);
		assertNotNull(result);
	}
}
