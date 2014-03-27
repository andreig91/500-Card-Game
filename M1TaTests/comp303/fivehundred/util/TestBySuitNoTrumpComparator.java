package comp303.fivehundred.util;

import java.util.Comparator;

import org.junit.Test;

import comp303.fivehundred.util.Card.BySuitNoTrumpComparator;

import static org.junit.Assert.*;
import static comp303.fivehundred.util.AllCardsTa.*;

public class TestBySuitNoTrumpComparator
{

	Comparator<Card> comparator = new BySuitNoTrumpComparator();
	
	@Test
	public void testCompareIdenticalCards()
	{
		assertEquals( comparator.compare(a4D, a4D), 0);
		assertTrue( comparator.compare(aHJo, aLJo) > 0);
		assertTrue( comparator.compare(aLJo, aHJo) < 0);
		
		assertTrue( comparator.compare(aHJo, aAH) > 0);
		assertTrue( comparator.compare(aAH, aHJo) < 0);
		
		assertTrue( comparator.compare(aAD, aAS) > 0);
		assertTrue( comparator.compare(aKD, aQD) > 0);
	}
}
