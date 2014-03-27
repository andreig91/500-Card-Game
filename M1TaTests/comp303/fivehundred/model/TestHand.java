package comp303.fivehundred.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import comp303.fivehundred.util.AllCardsTa;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Card.Suit;

public class TestHand
{
	private Hand h;
	private Hand jokerhand;
	private Hand allClubs;
	private Hand jackHand;

	@Before
	public void setUp() throws Exception
	{
		h = new Hand();
		
		h.add(AllCardsTa.a7D);
		h.add(AllCardsTa.aQD);
		h.add(AllCardsTa.aKD);		
		h.add(AllCardsTa.a9H);
		h.add(AllCardsTa.aTS);
		h.add(AllCardsTa.a7S);
		h.add(AllCardsTa.aJS);
		h.add(AllCardsTa.aKS);
		h.add(AllCardsTa.a6C);
		h.add(AllCardsTa.aTC);
		h.add(AllCardsTa.aLJo);
		
		jokerhand = new Hand();
		jokerhand.add(AllCardsTa.aLJo);
		jokerhand.add(AllCardsTa.aHJo);
		
		allClubs = new Hand();
		allClubs.add(AllCardsTa.a4C);
		allClubs.add(AllCardsTa.a9C);
		allClubs.add(AllCardsTa.aJC);
		allClubs.add(AllCardsTa.aHJo);
		
		jackHand = new Hand();
		jackHand.add(AllCardsTa.aJS);
		jackHand.add(AllCardsTa.aJC);
		
	}

	@Test
	public void testClone()
	{	
		Hand hClone = h.clone();
		Assert.assertTrue(((CardList) h).toString().equals(((CardList) hClone).toString()));
	}

//	@Test
//	public void testCanLead()
//	{
//		// cards beside jokers
//		
//		CardList nonJokerList = new CardList();
//		nonJokerList.add(AllCards.a7D);
//		nonJokerList.add(AllCards.aQD);
//		nonJokerList.add(AllCards.aKD);
//		nonJokerList.add(AllCards.a9H);		
//		nonJokerList.add(AllCards.aTS);
//		nonJokerList.add(AllCards.aJS);		
//		nonJokerList.add(AllCards.a7S);		
//		nonJokerList.add(AllCards.aKS);
//		nonJokerList.add(AllCards.a6C);
//		nonJokerList.add(AllCards.aTC);		
//		nonJokerList = nonJokerList.sort(new Card.ByRankComparator());
//		
//		assertTrue(h.canLead().sort(new Card.ByRankComparator()).toString().equals(nonJokerList.toString()));
//
//		// only jokers left
//		CardList jokerList = new CardList();
//		jokerhand.remove(AllCards.aHJo);
//		jokerList.add(AllCards.aLJo);
//		assertTrue(jokerhand.canLead().sort(new Card.ByRankComparator()).toString().equals(jokerList.toString()));
//		
//		jokerhand.add(AllCards.aHJo);
//		jokerList.add(AllCards.aHJo);
//		jokerList = jokerList.sort(new Card.ByRankComparator());		
//		assertTrue(jokerhand.canLead().sort(new Card.ByRankComparator()).toString().equals(jokerList.toString()));
//	}

	@Test
	public void testGetJokers()
	{

		h.remove(AllCardsTa.aLJo);		
		assertEquals(h.getJokers().size(),0);
		
		CardList jokerList = new CardList();
		jokerhand.remove(AllCardsTa.aHJo);
		jokerList.add(AllCardsTa.aLJo);		
		assertTrue(jokerhand.getJokers().sort(new Card.ByRankComparator()).toString().equals(jokerList.toString()));
		
		jokerhand.add(AllCardsTa.aHJo);
		jokerList.add(AllCardsTa.aHJo);
		jokerList = jokerList.sort(new Card.ByRankComparator());		
		assertTrue(jokerhand.getJokers().sort(new Card.ByRankComparator()).toString().equals(jokerList.toString()));
		
		assertEquals(jackHand.getJokers().size(), 0);
	}

	@Test
	public void testGetNonJokers()
	{
		CardList nonJokerList = new CardList();
		nonJokerList.add(AllCardsTa.a7D);
		nonJokerList.add(AllCardsTa.aQD);
		nonJokerList.add(AllCardsTa.aKD);
		nonJokerList.add(AllCardsTa.a9H);		
		nonJokerList.add(AllCardsTa.aTS);
		nonJokerList.add(AllCardsTa.aJS);		
		nonJokerList.add(AllCardsTa.a7S);		
		nonJokerList.add(AllCardsTa.aKS);
		nonJokerList.add(AllCardsTa.a6C);
		nonJokerList.add(AllCardsTa.aTC);		
		nonJokerList = nonJokerList.sort(new Card.ByRankComparator());
		
		assertTrue( h.getNonJokers().sort(new Card.ByRankComparator()).toString().equals(nonJokerList.toString()));
		
		assertEquals( jokerhand.getNonJokers().size(), 0);
		
		CardList jackList = new CardList();
		jackList.add(AllCardsTa.aJS);
		jackList.add(AllCardsTa.aJC);
		assertTrue(jackHand.getNonJokers().toString().equals(jackList.toString()));

	}

	@Test
	public void testGetTrumpCards()
	{
		
		CardList clubList = new CardList();
		clubList.add(AllCardsTa.a6C);
		clubList.add(AllCardsTa.aTC);		
		clubList.add(AllCardsTa.aJS);
		clubList.add(AllCardsTa.aLJo);
		clubList = clubList.sort(new Card.ByRankComparator());
		
		CardList diamondsList = new CardList();
		diamondsList.add(AllCardsTa.a7D);
		diamondsList.add(AllCardsTa.aQD);
		diamondsList.add(AllCardsTa.aKD);
		diamondsList.add(AllCardsTa.aLJo);
		diamondsList = diamondsList.sort(new Card.ByRankComparator());
		
		CardList heartsList = new CardList();
		heartsList.add(AllCardsTa.a9H);
		heartsList.add(AllCardsTa.aLJo);
		heartsList = heartsList.sort(new Card.ByRankComparator());		
		
		CardList spadesList = new CardList();
		spadesList.add(AllCardsTa.aTS);
		spadesList.add(AllCardsTa.a7S);
		spadesList.add(AllCardsTa.aJS);
		spadesList.add(AllCardsTa.aKS);
		spadesList.add(AllCardsTa.aLJo);

		spadesList = spadesList.sort(new Card.ByRankComparator());		

		assertTrue(h.getTrumpCards(Suit.CLUBS).sort(new Card.ByRankComparator()).toString().equals(clubList.toString()));
		assertTrue(h.getTrumpCards(Suit.DIAMONDS).sort(new Card.ByRankComparator()).toString().equals(diamondsList.toString()));
		assertTrue(h.getTrumpCards(Suit.HEARTS).sort(new Card.ByRankComparator()).toString().equals(heartsList.toString()));
		assertTrue(h.getTrumpCards(Suit.SPADES).sort(new Card.ByRankComparator()).toString().equals(spadesList.toString()));
	}

	@Test
	public void testGetNonTrumpCards()
	{
		
		CardList nonClubList = new CardList();
		nonClubList.add(AllCardsTa.a7D);
		nonClubList.add(AllCardsTa.aQD);
		nonClubList.add(AllCardsTa.aKD);
		nonClubList.add(AllCardsTa.a9H);		
		nonClubList.add(AllCardsTa.aTS);
		nonClubList.add(AllCardsTa.a7S);		
		nonClubList.add(AllCardsTa.aKS);		
		nonClubList = nonClubList.sort(new Card.ByRankComparator());
		
		assertTrue( h.getNonTrumpCards(Suit.CLUBS).sort(new Card.ByRankComparator()).toString().equals(nonClubList.toString()));
		
		
		CardList nonDiamondList = new CardList();		
		nonDiamondList.add(AllCardsTa.a6C);
		nonDiamondList.add(AllCardsTa.aTC);		
		nonDiamondList.add(AllCardsTa.a9H);		
		nonDiamondList.add(AllCardsTa.aTS);
		nonDiamondList.add(AllCardsTa.a7S);
		nonDiamondList.add(AllCardsTa.aJS);
		nonDiamondList.add(AllCardsTa.aKS);		
		nonDiamondList = nonDiamondList.sort(new Card.ByRankComparator());
		
		assertTrue( h.getNonTrumpCards(Suit.DIAMONDS).sort(new Card.ByRankComparator()).toString().equals(nonDiamondList.toString()));
		
		CardList nonHeartList = new CardList();		
		nonHeartList.add(AllCardsTa.a6C);
		nonHeartList.add(AllCardsTa.aTC);
		nonHeartList.add(AllCardsTa.a7D);
		nonHeartList.add(AllCardsTa.aQD);
		nonHeartList.add(AllCardsTa.aKD);
		nonHeartList.add(AllCardsTa.aTS);
		nonHeartList.add(AllCardsTa.a7S);
		nonHeartList.add(AllCardsTa.aJS);
		nonHeartList.add(AllCardsTa.aKS);		
		nonHeartList = nonHeartList.sort(new Card.ByRankComparator());
		
		assertTrue( h.getNonTrumpCards(Suit.HEARTS).sort(new Card.ByRankComparator()).toString().equals(nonHeartList.toString()));
		
		CardList nonSpadesList = new CardList();		
		nonSpadesList.add(AllCardsTa.a6C);
		nonSpadesList.add(AllCardsTa.aTC);
		nonSpadesList.add(AllCardsTa.a7D);
		nonSpadesList.add(AllCardsTa.aQD);
		nonSpadesList.add(AllCardsTa.aKD);
		nonSpadesList.add(AllCardsTa.a9H);
		nonSpadesList = nonSpadesList.sort(new Card.ByRankComparator());
		
		assertTrue( h.getNonTrumpCards(Suit.SPADES).sort(new Card.ByRankComparator()).toString().equals(nonSpadesList.toString()));
		
		assertEquals(jackHand.getNonTrumpCards(Suit.SPADES).size(), 0);
		assertEquals(jackHand.getNonTrumpCards(Suit.CLUBS).size(), 0);
	}

	@Test
	public void testSelectLowest()
	{
		// no-trump	
		// JB, 28/10
		// MODIFIED TA PREVIOUS ERROR: THEY WERE CLAIMING THIS CARD SHOULD BE 6C, WHEN IN NO TRUMP, IT'S THE
		// SUIT THAT MATTERS. 
		//System.out.println("lowest = " + h.selectLowest(null));
		//IN no trump, 6C is smaller than 7S
		assertTrue(h.selectLowest(null).equals(AllCardsTa.a6C));
		
		//trump
		// JB, 28/10 SAME HERE. Again, we will for now go by: 7S is lower than 6C. 
		assertTrue(h.selectLowest(Suit.CLUBS).equals(AllCardsTa.a7S));
		assertTrue(h.selectLowest(Suit.DIAMONDS).equals(AllCardsTa.a7S));
		assertTrue(h.selectLowest(Suit.HEARTS).equals(AllCardsTa.a7S));
		assertTrue(h.selectLowest(Suit.SPADES).equals(AllCardsTa.a6C));
		
		assertTrue(jokerhand.selectLowest(Suit.SPADES).equals(AllCardsTa.aLJo));
		
		assertTrue(allClubs.selectLowest(Suit.CLUBS).equals(AllCardsTa.a4C));
	}

	@Test
	public void testPlayableCards()
	{
//		// no-Trump not specified
		
		CardList clubList = new CardList();
		clubList.add(AllCardsTa.a6C);
		clubList.add(AllCardsTa.aTC);		
//		assertTrue( h.playableCards(Suit.CLUBS, null).toString().equals(clubList.toString()));
		
		CardList diamondsList = new CardList();
		diamondsList.add(AllCardsTa.a7D);
		diamondsList.add(AllCardsTa.aQD);
		diamondsList.add(AllCardsTa.aKD);
//		assertTrue( h.playableCards(Suit.DIAMONDS, null).toString().equals(diamondsList.toString()));
		
		CardList heartsList = new CardList();
		heartsList.add(AllCardsTa.a9H);		
//		assertTrue( h.playableCards(Suit.HEARTS, null).toString().equals(heartsList.toString()));
		
		CardList spadesList = new CardList();
		spadesList.add(AllCardsTa.aTS);
		spadesList.add(AllCardsTa.a7S);
		spadesList.add(AllCardsTa.aJS);
		spadesList.add(AllCardsTa.aKS);
//		assertTrue( h.playableCards(Suit.SPADES, null).toString().equals(spadesList.toString()));
		
		CardList joker = new CardList();			
		joker.add(AllCardsTa.aLJo);		
		joker.add(AllCardsTa.aHJo);
//		assertTrue(jokerhand.playableCards(Suit.DIAMONDS, null).sort(new Card.ByRankComparator()).toString().equals(joker.toString()));
		
		
		// Trump
		clubList.add(AllCardsTa.aJS);
		clubList.add(AllCardsTa.aLJo);
		clubList = clubList.sort(new Card.ByRankComparator());
		assertTrue( h.playableCards(Suit.CLUBS, Suit.CLUBS).sort(new Card.ByRankComparator()).toString().equals(clubList.toString()));
		
		diamondsList.add(AllCardsTa.aLJo);
		diamondsList = diamondsList.sort(new Card.ByRankComparator());
		assertTrue( h.playableCards(Suit.DIAMONDS, Suit.DIAMONDS).sort(new Card.ByRankComparator()).toString().equals(diamondsList.toString()));

		heartsList = heartsList.sort(new Card.ByRankComparator());		
		assertTrue( h.playableCards(Suit.HEARTS, Suit.DIAMONDS).sort(new Card.ByRankComparator()).toString().equals(heartsList.toString()));
		
		// JB I don't know shy they removed the Jack in the first place, maybe they thought that you can't play
		// the Jack of Spades when you have other Spades to play?
		spadesList.remove(AllCardsTa.aJS);
		spadesList = spadesList.sort(new Card.ByRankComparator());
		assertTrue( h.playableCards(Suit.SPADES, Suit.CLUBS).sort(new Card.ByRankComparator()).toString().equals(spadesList.toString()));
		
		// no Trump in hand
		assertTrue(jokerhand.playableCards(Suit.DIAMONDS, Suit.DIAMONDS).sort(new Card.ByRankComparator()).toString().equals(joker.toString()));
		assertTrue(jokerhand.playableCards(Suit.DIAMONDS, Suit.CLUBS).sort(new Card.ByRankComparator()).toString().equals(joker.toString()));
		
		CardList jackList = new CardList();
		jackList.add(AllCardsTa.aJS);
		jackList.add(AllCardsTa.aJC);
		assertTrue(jackHand.playableCards(Suit.CLUBS, Suit.SPADES).sort(new Card.ByRankComparator()).toString().equals(jackList.toString()));
		
	}

	@Test
	public void testNumberOfCards()
	{
		// Trump
		assertTrue(h.numberOfCards(Suit.CLUBS, Suit.CLUBS) == 3); // +1 for jack of spades 
		assertTrue(h.numberOfCards(Suit.DIAMONDS, Suit.DIAMONDS) == 3);
		assertTrue(h.numberOfCards(Suit.HEARTS, Suit.DIAMONDS) == 1);
		assertTrue(h.numberOfCards(Suit.SPADES, Suit.CLUBS) == 3); //-1 for jack of spades 
		
	}


}

