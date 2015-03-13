package de.prob.translator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.prob.translator.Translator;
import de.prob.translator.types.Atom;
import de.prob.translator.types.BObject;
import de.prob.translator.types.Boolean;
import de.prob.translator.types.String;
import de.prob.translator.types.Sequence;
import de.prob.translator.types.Record;
import de.prob.translator.types.Set;
import de.prob.translator.types.Tuple;
import de.prob.translator.types.Number;


public class TestTranslator {

    @Test
    public void testTranslateAnything() throws Exception {
        BObject o = Translator.translate("5");
        assertNotNull(o);
    }

    @Test
    public void testTranslateNumber() throws Exception {
        Number o = (Number) Translator.translate("5");

        assertTrue(o.compareTo(Number.build("5")) == 0);
    }

    @Test
    public void testTranslateIdentifier() throws Exception {
        Atom idx = (Atom) Translator.translate("x");
        assertEquals(idx.getValue(), "x");
    }

    @Test
    public void testTranslateTrue() throws Exception {
        Boolean b = (Boolean) Translator.translate("TRUE");
        assertTrue(b.booleanValue());
    }

    @Test
    public void testTranslateFalse() throws Exception {
        Boolean b = (Boolean) Translator.translate("FALSE");
        assertFalse(b.booleanValue());
    }

    @Test
    public void testTranslateString() throws Exception {
        String str = (String) Translator.translate("\"a\"");
        assert str.getValue().equals("a");
    }

    @Test
    public void testTranslateEmptySet() throws Exception {
        Set o = (Set) Translator.translate("{}");
        assertTrue(o.size() == 0);
    }

    @Test
    public void testTranslateIntSet() throws Exception {
        Set o = (Set) Translator.translate("{5,6,7}");
        assertTrue(o.size() == 3);
        assertFalse(o.contains(Number.build("8")));
    }

    @Test
    public void testTranslateTuple() throws Exception {
        Tuple o = (Tuple) Translator.translate("(1,2)");
        assertTrue(o.size() == 2);
        assertTrue(o.contains(Number.build("1")));
        assertTrue(o.contains(Number.build("2")));
        assertFalse(o.contains(Number.build("8")));
    }

    @Test
    public void testTranslateRecord() throws Exception {
        Record o = (Record) Translator.translate("rec(key1:1, key2:2)");
        assertTrue(o.containsKey("key1"));
        assertTrue(o.containsKey("key2"));
        assertFalse(o.containsKey("key3"));
    }

    @Test
    public void testTranslateSequence() throws Exception {
        Sequence s = (Sequence) Translator.translate("[1,2,3]");
        assertTrue(s.size() == 3);
        for (int i = 1; i <= s.size(); i++) {
            assertTrue(s.get(i).equals(Number.build("" + (i))));
        }
    }
    
    @Test
    public void testTranslateEmptySequence() throws Exception {
        Sequence s = (Sequence) Translator.translate("[]");
        assertTrue(s.size() == 0);
    }
}

