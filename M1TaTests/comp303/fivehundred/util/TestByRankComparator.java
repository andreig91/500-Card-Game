package comp303.fivehundred.util;

import java.util.Comparator;

import org.junit.Test;

import comp303.fivehundred.util.Card.ByRankComparator;

import static org.junit.Assert.*;
import static comp303.fivehundred.util.AllCardsTa.*;


public class TestByRankComparator
{
	ByRankComparator comparator = new ByRankComparator();
	
	@Test
	public void testCompareJokers()
	{
		assertTrue(comparator.compare(aHJo, aLJo) > 0);
		assertTrue(comparator.compare(aLJo, aHJo) < 0);
		assertEquals(comparator.compare(aHJo, aHJo), 0);
		assertEquals(comparator.compare(aLJo, aLJo), 0);
	}
	
	@Test
	public void testCompareJokerNonJoker()
	{
		assertTrue(comparator.compare(aHJo, aAH) > 0);
		assertTrue(comparator.compare(aAH, aHJo) < 0);
	}
	
	@Test
	public void testCompareNonJokers()
	{
		assertTrue(comparator.compare(aAH, aQH) > 0);
		assertTrue(comparator.compare(aQS, aKS) < 0);
		assertTrue(comparator.compare(aQH, aQS) > 0);		
	}
}
