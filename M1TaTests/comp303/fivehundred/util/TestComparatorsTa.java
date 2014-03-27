package comp303.fivehundred.util;

import static comp303.fivehundred.util.AllCards.*;
import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import comp303.fivehundred.util.Card.Suit;

public class TestComparatorsTa
{
	@Test
	public void testByRankComparator()
	{
		Comparator<Card> c = new Card.ByRankComparator();
		assertTrue(c.compare(a4S, a5S) < 0);
		assertTrue(c.compare(a4S, a6S) < 0);
		assertTrue(c.compare(a4S, a7S) < 0);
		assertTrue(c.compare(a4S, aJS) < 0);
		assertTrue(c.compare(a4S, aKS) < 0);
		assertTrue(c.compare(a4S, aAS) < 0);
		assertTrue(c.compare(a4S, aJH) < 0);
		assertTrue(c.compare(a4S, aLJo) < 0);
		assertTrue(c.compare(a4S, aHJo) < 0);
		
		assertTrue(c.compare(a4S, a4S) == 0);
		
		assertTrue(c.compare(a9H, aTS) < 0);
		assertTrue(c.compare(aAS, aAC) < 0);
		assertTrue(c.compare(aAS, aAD) < 0);
		assertTrue(c.compare(aAS, aAH) < 0);
		
		assertTrue(c.compare(aAS, aLJo) < 0);

		assertTrue(c.compare(aLJo, aHJo) < 0);
	}
	
	@Test
	public void testBySuitNoTrumpComparator()
	{
		Comparator<Card> c = new Card.BySuitNoTrumpComparator();
		assertTrue(c.compare(a4S, a5S) < 0);
		assertTrue(c.compare(a4S, a6S) < 0);
		assertTrue(c.compare(a4S, a7S) < 0);
		assertTrue(c.compare(a4S, aJS) < 0);
		assertTrue(c.compare(a4S, aKS) < 0);
		assertTrue(c.compare(a4S, aAS) < 0);
		assertTrue(c.compare(a4S, aJH) < 0);
		assertTrue(c.compare(a4S, aLJo) < 0);
		assertTrue(c.compare(a4S, aHJo) < 0);
		
		assertTrue(c.compare(a4S, a4C) < 0);
		assertTrue(c.compare(a4S, a4D) < 0);
		assertTrue(c.compare(a4S, a4H) < 0);
		
		assertTrue(c.compare(a4C, a4D) < 0);
		assertTrue(c.compare(a4C, a4H) < 0);
		
		assertTrue(c.compare(aAS, a4C) < 0);
		assertTrue(c.compare(aAS, aLJo) < 0);
		assertTrue(c.compare(aAS, aHJo) < 0);
		
		assertTrue(c.compare(aJS, aQS) < 0);
		assertTrue(c.compare(aQS, aJC) < 0);
		
		assertTrue(c.compare(aLJo, aHJo) < 0);
	}
	
	@Test
	public void testBySuitComparator()
	{
		Comparator<Card> c = new Card.BySuitComparator(Suit.CLUBS);
		assertTrue(c.compare(a4S, a5S) < 0);
		assertTrue(c.compare(a4S, a6S) < 0);
		assertTrue(c.compare(a4S, a7S) < 0);
		assertTrue(c.compare(a4S, aJS) < 0);
		assertTrue(c.compare(a4S, aKS) < 0);
		assertTrue(c.compare(a4S, aAS) < 0);
		assertTrue(c.compare(a4S, aJH) < 0);
		assertTrue(c.compare(a4S, aLJo) < 0);
		assertTrue(c.compare(a4S, aHJo) < 0);
		
		assertTrue(c.compare(a4S, a4C) < 0);
		assertTrue(c.compare(a4S, a4D) < 0);
		assertTrue(c.compare(a4S, a4H) < 0);
		
		assertTrue(c.compare(a4D, a4C) < 0);
		assertTrue(c.compare(a4H, a4C) < 0);
		
		assertTrue(c.compare(aAS, a4C) < 0);
		assertTrue(c.compare(aAC, aLJo) < 0);
		assertTrue(c.compare(aAS, aHJo) < 0);
		
		assertTrue(c.compare(aAC, aJC) < 0);
		assertTrue(c.compare(aAC, aJS) < 0);
		assertTrue(c.compare(aJH, aAH) < 0);
		assertTrue(c.compare(aJD, aAH) < 0);
		
		assertTrue(c.compare(a7S, aJS) < 0);
		
		assertTrue(c.compare(aLJo, aHJo) < 0);
		
		assertTrue(c.compare(aJS, aLJo) < 0 );
		
		c = new Card.BySuitComparator(Suit.SPADES);
		
		assertTrue(c.compare(a7S, aJS) < 0);
		
		c = new Card.BySuitComparator(Suit.DIAMONDS);
		
		assertTrue(c.compare(a7S, aJS) < 0);
		
		c = new Card.BySuitComparator(Suit.HEARTS);
		
		assertTrue(c.compare(a7S, aJS) < 0);
	}
	
//	@Test
//	public void testSuitFollowingComparator()
//	{
//		Comparator<Card> c = new Card.SuitFollowingComparator(null,Suit.CLUBS);
//		
//		// Following suit
//		assertTrue(c.compare(a4C, a5C) < 0);
//		assertTrue(c.compare(a5C, a6C) < 0);
//		assertTrue(c.compare(a5C, aAC) < 0);
//		assertTrue(c.compare(aJC, aAC) < 0);
//		assertTrue(c.compare(aAC, aLJo) < 0);
//		
//		// Not following suit
//		assertTrue(c.compare(aAS, a5C) < 0);
//		assertTrue(c.compare(aAD, a5C) < 0);
//		assertTrue(c.compare(aJD, a5C) < 0);
//		assertTrue(c.compare(aAH, a5C) < 0);
//		
//		// Both in wrong suit
//		assertTrue(c.compare(aAS, aAH) < 0);
//		
//		// trump is led, following suit
//		c = new Card.SuitFollowingComparator(Suit.CLUBS,Suit.CLUBS);
//		assertTrue(c.compare(a4C, a5C) < 0);
//		assertTrue(c.compare(a5C, a6C) < 0);
//		assertTrue(c.compare(a5C, aAC) < 0);
//		assertTrue(c.compare(aAC, aJC) < 0);
//		assertTrue(c.compare(aAC, aLJo) < 0);
//		
//		// trump is led, not following suit.
//		assertTrue(c.compare(aAS, a5C) < 0);
//		assertTrue(c.compare(aAD, a5C) < 0);
//		assertTrue(c.compare(aJD, a5C) < 0);
//		assertTrue(c.compare(aAH, a5C) < 0);
//		
//		// non-trump is led, following suit.
//		c = new Card.SuitFollowingComparator(Suit.HEARTS,Suit.CLUBS);
//		assertTrue(c.compare(a4C, a5C) < 0);
//		assertTrue(c.compare(a5C, a6C) < 0);
//		assertTrue(c.compare(a5C, aAC) < 0);
//		assertTrue(c.compare(aJC, aAC) < 0);
//		assertTrue(c.compare(aAC, aLJo) < 0);
//		
//		// non-trump is led, trumping
//		assertTrue(c.compare(aAC, a4H) < 0);
//		assertTrue(c.compare(a4H, aAH) < 0);
//		assertTrue(c.compare(aAH, aJD) < 0);
//		assertTrue(c.compare(aJD, aJH) < 0);
//		assertTrue(c.compare(aJC, aJD) < 0);
//		assertTrue(c.compare(aJD, aLJo) < 0);
//		assertTrue(c.compare(aLJo, aHJo) < 0);
//	}
}
