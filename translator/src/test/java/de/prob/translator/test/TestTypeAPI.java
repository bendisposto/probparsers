package de.prob.translator.test;

import de.prob.translator.Translator;
import de.prob.translator.types.*;
import de.prob.translator.types.Integer;
import de.prob.translator.types.String;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestTypeAPI {

    @Test
    public void testSetIteration() throws Exception {
        Set s = (Set) Translator.translate("{1,2,3, \"a\"}");
        assertEquals(s.size(), 4);

        assertTrue(s.contains(new String("a")));
        BObject[] c = s.toArray(new BObject[]{});
        assertEquals(c.length, 4);
        for (BObject bObject : c) {
            assertTrue(s.contains(bObject));
        }
        ArrayList<BObject> bo = new ArrayList<BObject>();
        for (BObject bObject : s) {
            bo.add(bObject);
        }
        assertEquals(bo.size(), 4);
    }


    @Test
    public void testString() throws Exception {
        Sequence s = (Sequence) Translator.translate("[\"a\", \"word\", \"a\"]");
        String s1 = (String) s.get(1);
        String s2 = (String) s.get(2);
        String s3 = (String) s.get(3);

        assertEquals(s1, s3);
        assertSame(s1, s1);
        assertFalse(s1.equals(s2));
        assertEquals(s2.length(), 4);
    }


    @Test
    public void testTuple() throws Exception {
        Tuple t1 = (Tuple) Translator.translate("(1,2)");
        Tuple t2 = (Tuple) Translator.translate("(1 |-> 2)");
        assertEquals(t1, t2);
        assertEquals(t1.get(0), t2.get(0));
        for (BObject bObject : t1) {
            t2.contains(bObject);
        }
        assertEquals(t1.get(0), t1.getFirst());
        assertEquals(t1.get(1), t1.getSecond());

    }

    @Test
    public void testBInt() throws Exception {
        Integer a = new Integer(5);
        Integer b = new Integer(6);
        Integer c = new Integer(5);

        assertEquals(a, c);

        Integer aa = new Integer("123123123123123123121");
        Integer bb = new Integer("123123123123123123123");
        Integer cc = new Integer("123123123123123123121");

        assertEquals(aa, cc);

        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertTrue(a.compareTo(a) == 0);

        assertTrue(aa.compareTo(bb) < 0);
        assertTrue(bb.compareTo(aa) > 0);
        assertTrue(aa.compareTo(aa) == 0);
    }

}
