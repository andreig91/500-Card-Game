package comp303.fivehundred.util;

import static org.junit.Assert.*;
import comp303.fivehundred.model.*;
import comp303.fivehundred.util.Card.Suit;
import org.junit.Test;

/**
 * 
 * @author Andrei
 *
 */
public class TestBid
{
	@Test
	public void TestBidInit()
	{
		Bid aBid = new Bid();
		assertTrue(aBid.isPass());
		aBid = new Bid(5, Suit.CLUBS);
		assertFalse(aBid.isPass());
		assertEquals(5,aBid.getTricksBid());
		assertEquals(Suit.CLUBS,aBid.getSuit());
		assertFalse(aBid.isNoTrump());
		aBid = new Bid(4);
		assertFalse(aBid.isPass());
		assertEquals(6,aBid.getTricksBid());
		assertEquals(null,aBid.getSuit());
		assertTrue(aBid.isNoTrump());
		aBid = new Bid(8);
		assertFalse(aBid.isPass());
		assertEquals(7,aBid.getTricksBid());
		assertEquals(Suit.HEARTS,aBid.getSuit());
		assertFalse(aBid.isNoTrump());
		aBid = new Bid(12);
		assertFalse(aBid.isPass());
		assertEquals(8,aBid.getTricksBid());
		assertEquals(Suit.DIAMONDS,aBid.getSuit());
		assertFalse(aBid.isNoTrump());
		aBid = new Bid(15);
		assertFalse(aBid.isPass());
		assertEquals(9,aBid.getTricksBid());
		assertEquals(Suit.SPADES,aBid.getSuit());
		assertFalse(aBid.isNoTrump());
		aBid = new Bid(20);
		assertFalse(aBid.isPass());
		assertEquals(10,aBid.getTricksBid());
		assertEquals(Suit.SPADES,aBid.getSuit());
		assertFalse(aBid.isNoTrump());
	}
	@Test(expected = ModelException.class)
	public void TestGetTricksBid()
	{
		Bid aBid = new Bid(4);
		assertEquals(6,aBid.getTricksBid());
		aBid = new Bid();
		aBid.getTricksBid();
	}
	@Test(expected = ModelException.class)
	public void TestGetSuit()
	{
		Bid aBid = new Bid(4);
		assertEquals(null,aBid.getSuit());
		aBid = new Bid();
		aBid.getSuit();
	}
	@Test
	public void TestIsPass()
	{
		Bid aBid = new Bid();
		assertTrue(aBid.isPass());
	}
	@Test
	public void TestCompareTo()
	{
		Bid aBid = new Bid();
		Bid aBid2 = new Bid();
		assertEquals(0,aBid.compareTo(aBid2));
		Bid aBid3 = new Bid(4);
		assertTrue(aBid.compareTo(aBid3) < 0);
		aBid2 = new Bid(5);
		aBid = new Bid(4);
		assertTrue(aBid.compareTo(aBid2) < 0);
		assertTrue(aBid.compareTo(aBid3) == 0);
		assertTrue(aBid2.compareTo(aBid) > 0);		
	}
	@Test
	public void TestToString()
	{
		Bid aBid = new Bid();
		Bid aBid2 = new Bid(20);
		assertEquals("Passing bid", aBid.toString());
		Bid aBid3 = new Bid(4);
		assertEquals("10 of SPADES", aBid2.toString());
		assertEquals("6 no trump", aBid3.toString());
	}
	@Test
	public void TestEquals()
	{
		Bid aBid = new Bid();
		Bid aBid2 = new Bid();
		assertTrue(aBid.equals(aBid2));
	}
	@Test
	public void TestHashCode()
	{
		int[] aHash = new int[26];
		aHash[25] = new Bid().hashCode();
		for(int i=0; i<25; i++)
		{
			Bid aBid = new Bid(i);
			aHash[i] = aBid.hashCode();
		}
		for(int i=0; i<26; i++)
		{
			for(int j=0; j<26; j++)
			{
				if( i != j)
				{
					assertTrue( aHash[i] != aHash[j] );
				}
			}
		}
	}
	@Test(expected = ModelException.class)
	public void TestToIndex()
	{
		Bid aBid;
		for(int i=0; i<25; i++)
		{
			aBid = new Bid(i);
			assertEquals(i, aBid.toIndex());
		}
		aBid = new Bid();
		aBid.toIndex();
	}
	@Test
	public void TestMax()
	{
		Bid[] aBid = new Bid[25];
		for(int i=0; i<25; i++)
		{
			aBid[i] = new Bid(i);
		}
		Bid aMaxBid = Bid.max(aBid);
		assertEquals("10 no trump", aMaxBid.toString());
		aBid = new Bid[25];
		for(int i=0; i<25; i++)
		{
			aBid[i] = new Bid();
		}
		aMaxBid = Bid.max(aBid);
		assertEquals("Passing bid", aMaxBid.toString());
	}
	@Test(expected = ModelException.class)
	public void TestGetScore()
	{
		Bid aBid;
		for(int i=0,j=40; i<25; i++, j+= 20)
		{
			aBid = new Bid(i);
			assertEquals(j,aBid.getScore());
		}
		
		aBid = new Bid();
		aBid.getScore();
	}
}
