package comp303.fivehundred.util;

import static comp303.fivehundred.util.AllCardsTa.*;
import static org.junit.Assert.*;
import org.junit.Test;
import comp303.fivehundred.util.Card.Joker;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author Andrei
 *
 */
public class TestCard
{
	@Test
	public void testToString()
	{
		assertEquals( "ACE of CLUBS", aAC.toString());
		assertEquals( "TEN of CLUBS", aTC.toString());
		assertEquals( "JACK of CLUBS", aJC.toString());
		assertEquals( "QUEEN of HEARTS", aQH.toString());
		assertEquals( "KING of SPADES", aKS.toString());
		assertEquals( "QUEEN of DIAMONDS", aQD.toString());
		assertEquals( "NINE of HEARTS", a9H.toString());
		assertEquals( "LOW Joker", aLJo.toString());
		assertEquals( "HIGH Joker", aHJo.toString());
	}
	@Test
	public void testToShortString()
	{
		assertEquals( "AC", aAC.toShortString());
		assertEquals( "TC", aTC.toShortString());
		assertEquals( "JC", aJC.toShortString());
		assertEquals( "QH", aQH.toShortString());
		assertEquals( "KS", aKS.toShortString());
		assertEquals( "QD", aQD.toShortString());
		assertEquals( "9H", a9H.toShortString());
		assertEquals( "LJ", aLJo.toShortString());
		assertEquals( "HJ", aHJo.toShortString());
	}
	@Test
	public void testIsJoker()
	{
		assertTrue( aHJo.isJoker());
		assertTrue( aLJo.isJoker());
		assertFalse( aKS.isJoker());
	}
	@Test
	public void testHashCode()
	{
		assertTrue( aHJo.hashCode() == aHJo.hashCode());
		assertFalse( aHJo.hashCode() == aLJo.hashCode());
		assertTrue( a9S.hashCode() == a9S.hashCode());
		assertFalse( a8D.hashCode() == a4H.hashCode());
		assertFalse(aAS.hashCode() == a4C.hashCode());
	}
	@Test
	public void testGetSuit()
	{
		assertEquals( Suit.CLUBS, aKC.getSuit());
		assertEquals( Suit.SPADES, a6S.getSuit());
		assertEquals( Suit.HEARTS, a9H.getSuit());
		assertEquals( Suit.DIAMONDS, aJD.getSuit());
	}
	@Test
	public void testGetRank()
	{
		assertEquals( Rank.EIGHT, a8C.getRank());
		assertEquals( Rank.FIVE, a5H.getRank());
		assertEquals( Rank.KING, aKC.getRank());
	}
	@Test
	public void testGetJokerValue()
	{
		assertEquals( Joker.HIGH, aHJo.getJokerValue());
		assertEquals( Joker.LOW, aLJo.getJokerValue());
	}
	@Test
	public void testGetEffectiveSuit()
	{
		assertEquals( Suit.CLUBS, aKC.getEffectiveSuit(null));
		assertEquals( Suit.SPADES, aTS.getEffectiveSuit(null));
		assertEquals( Suit.DIAMONDS, aJD.getEffectiveSuit(null));
		assertEquals( Suit.HEARTS, a7H.getEffectiveSuit(null));
		assertEquals( Suit.CLUBS, aKC.getEffectiveSuit(Suit.CLUBS));
		assertEquals( Suit.SPADES, aTS.getEffectiveSuit(Suit.SPADES));
		assertEquals( Suit.DIAMONDS, aQD.getEffectiveSuit(Suit.DIAMONDS));
		assertEquals( Suit.HEARTS, a7H.getEffectiveSuit(Suit.HEARTS));
		assertEquals( Suit.DIAMONDS, aJH.getEffectiveSuit(Suit.DIAMONDS));
		assertEquals( Suit.HEARTS, aJD.getEffectiveSuit(Suit.HEARTS));
		assertEquals( Suit.SPADES, aJC.getEffectiveSuit(Suit.SPADES));
		assertEquals( Suit.CLUBS, aJS.getEffectiveSuit(Suit.CLUBS));
		assertEquals( Suit.SPADES, aJS.getEffectiveSuit(Suit.HEARTS));
	}
	@Test
	public void testEquals()
	{
		assertTrue(aKC.equals(aKC));
		assertTrue(aJS.equals(aJS));
		assertTrue(aLJo.equals(aLJo));
		assertTrue(aHJo.equals(aHJo));
		assertFalse(a9S.equals(a9D));
		assertFalse(aHJo.equals(aLJo));
		assertFalse(aHJo.equals(Rank.ACE));
	}
	@Test
	public void testCompareTo()
	{
		assertTrue(aKC.compareTo(aQD) > 0);
		assertTrue(aLJo.compareTo(aAD) > 0);
		assertTrue(aHJo.compareTo(aLJo) > 0);
		assertTrue(aLJo.compareTo(a6D) > 0);
		assertTrue(aKC.compareTo(a7D) > 0);
		assertEquals(0,aLJo.compareTo(aLJo));
		assertEquals(0,aHJo.compareTo(aHJo));
		assertEquals(0,aKC.compareTo(aKC));
		assertEquals(0,a9C.compareTo(a9C));
		assertTrue(aLJo.compareTo(aHJo) < 0);
		assertTrue(aAS.compareTo(aLJo) < 0);
		assertTrue(a9S.compareTo(aTD) < 0);
		assertTrue(a5D.compareTo(a6D) < 0);
		assertTrue(a7C.compareTo(aQD) < 0);
	}
}