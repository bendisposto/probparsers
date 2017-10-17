package de.prob.cliparser;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class FregeCspParserTest {

    @Test
    public void singleQuoteInParseErrorIsEscaped() {
        String commands = "diff(NonTock,{|enter,leave|}\n../../prob_examples/public_examples/CSP/mydemos/demo/crossing.csp";
        BufferedReader in = fromString(commands);
        String parseResult = FregeCspParser.translateCspExpressionToProlog(in);
        String expected = "frege_facts('ParseErrorException: ParseError \"\\nunexpected ''(''\\nexpecting end of module\" (Token (TokenId 2320) (AlexPn 14480 384 28) 4 L_Ident \"diff\") (AlexPn 14480 384 28)').\n";
        assertEquals(expected, parseResult);
    }

    private static BufferedReader fromString(String string) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes())));
    }

    @Test
    public void testAstCaching() {
        String commands = "diff(NonTock,{|enter,leave|}\n../../prob_examples/public_examples/CSP/mydemos/demo/crossing.csp";
        BufferedReader in1 = fromString(commands);
        String parseResult1 = FregeCspParser.translateCspExpressionToProlog(in1);
        BufferedReader in2 = fromString(commands);
        String parseResult2 = FregeCspParser.translateCspExpressionToProlog(in2);
        assertEquals(parseResult1, parseResult2);
    }
}