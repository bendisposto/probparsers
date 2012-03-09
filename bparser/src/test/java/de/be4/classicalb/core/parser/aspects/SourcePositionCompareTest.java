package de.be4.classicalb.core.parser.aspects;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hhu.stups.sablecc.patch.SourcePosition;

public class SourcePositionCompareTest {

	@Test
	public void test() {
		SourcePosition p1 = new SourcePosition(1, 5);
		SourcePosition p2 = new SourcePosition(2, 10);
		SourcePosition p3 = new SourcePosition(3, 1);
		SourcePosition p4 = new SourcePosition(3, 11);
		
		assertTrue(p1.compareTo(p1) == 0);
		assertTrue(p1.compareTo(p2) < 0);
		assertTrue(p2.compareTo(p1) > 0);
		assertTrue(p1.compareTo(p3) < 0);
		assertTrue(p1.compareTo(p4) < 0);
		assertTrue(p2.compareTo(p3) < 0);
		assertTrue(p2.compareTo(p4) < 0);
		assertTrue(p3.compareTo(p4) < 0);
		
		
	}

}
