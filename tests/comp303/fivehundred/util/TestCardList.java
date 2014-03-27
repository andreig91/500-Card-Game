package comp303.fivehundred.util;

import static comp303.fivehundred.util.AllCardsTa.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author Andrei
 *
 */
public class TestCardList
{	
	@Test
	public void TestCardListInit()
	{
		assertEquals(0, new CardList().size());
	}
	
	@Test
	public void TestAdd()
	{
		CardList aList = new CardList();
		aList.add(a4C);
		assertEquals(1, aList.size());
		aList.add(a4C);
		assertEquals(1, aList.size());
	}
	@Test
	public void TestClone()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		CardList aList2 = aList.clone();
		assertTrue(aList != aList2);
		assertTrue(aList.getClass() == aList2.getClass());
		Iterator<Card> aItr1 = aList.iterator();
		Iterator<Card> aItr2 = aList2.iterator();
		for(int i=0; i<aList.size(); i++)
		{
			assertTrue(aItr1.next().equals(aItr2.next()));
		}
	}
	@Test(expected = GameUtilException.class)
	public void TestGetFirst()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		assertEquals(aKD,aList.getFirst());
		aList = new CardList();
		aList.getFirst();
	}
	@Test(expected = GameUtilException.class)
	public void TestGetLast()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		assertEquals(aHJo,aList.getLast());
		aList = new CardList();
		aList.getLast();
	}
	@Test
	public void TestRemove()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		aList.remove(aAS);
		aList.remove(aHJo);
		assertEquals(aKD,aList.getLast());
	}
	@Test
	public void TestToString()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		assertEquals("KD HJ ",aList.toString());
	}
	@Test
	public void TestRandom()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		Card aCard = aList.random();
		Card aCard2 = aList.random();
		assertTrue(aCard.equals(aKD) || aCard.equals(aHJo) );
		assertTrue(aCard2.equals(aKD) || aCard2.equals(aHJo) );
	}
	@Test
	public void TestSort()
	{
		CardList aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		aList.add(a6S);
		aList.add(aQH);
		CardList aList2 = aList.sort(new Card.ByRankComparator());
		assertEquals(a6S,aList2.getFirst());
		aList2.remove(a6S);
		assertEquals(aQH,aList2.getFirst());
		aList2.remove(aQH);
		assertEquals(aKD,aList2.getFirst());
		aList2.remove(aKD);
		assertEquals(aHJo,aList2.getFirst());
		
		aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		aList.add(a6S);
		aList.add(aJD);
		aList.add(aLJo);
		aList2 = aList.sort(new Card.BySuitNoTrumpComparator());
		assertEquals(a6S,aList2.getFirst());
		aList2.remove(a6S);
		assertEquals(aJD,aList2.getFirst());
		aList2.remove(aJD);
		assertEquals(aKD,aList2.getFirst());
		aList2.remove(aKD);
		assertEquals(aLJo,aList2.getFirst());
		aList2.remove(aLJo);
		assertEquals(aHJo,aList2.getFirst());
		
		aList = new CardList();
		aList.add(aKD);
		aList.add(aHJo);
		aList.add(aJS);
		aList.add(aJC);
		aList.add(a6D);
		aList.add(aAD);
		aList2 = aList.sort(new Card.BySuitComparator(Suit.CLUBS));
		assertEquals(a6D,aList2.getFirst());
		aList2.remove(a6D);
		assertEquals(aKD,aList2.getFirst());
		aList2.remove(aKD);
		assertEquals(aAD,aList2.getFirst());
		aList2.remove(aAD);
		assertEquals(aJS,aList2.getFirst());
		aList2.remove(aJS);
		assertEquals(aJC,aList2.getFirst());
		aList2.remove(aJC);
		assertEquals(aHJo,aList2.getFirst());
		aList2.remove(aHJo);
	}
}
