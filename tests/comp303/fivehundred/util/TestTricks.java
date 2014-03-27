package comp303.fivehundred.util;

import static org.junit.Assert.*;
import comp303.fivehundred.model.*;
import comp303.fivehundred.util.Card.Suit;

import org.junit.Test;
/**
 * @author Amjad Al-Rikabi
 *
 */
public class TestTricks
{
	
	@Test
	public void TestTricksTwoJokers()
	{
		//No Trump Bids
		Bid aBid1 = new Bid(24);
		Bid aBid2 = new Bid(24);
		//Trump Bids
		Bid aBid3 = new Bid(7, Suit.DIAMONDS);
		Bid aBid4 = new Bid(5, Suit.HEARTS);
		// Trick 1
		Trick aTrick1 = new Trick(aBid1);
		aTrick1.add(AllCardsTa.aHJo);
		aTrick1.add(AllCardsTa.aLJo);
		aTrick1.add(AllCardsTa.a4H);
		aTrick1.add(AllCardsTa.aTS);
		// Trick 2
		Trick aTrick2 = new Trick(aBid2);
		aTrick2.add(AllCardsTa.aLJo);
		aTrick2.add(AllCardsTa.aHJo);
		aTrick2.add(AllCardsTa.a4H);
		aTrick2.add(AllCardsTa.aTS);
		// Trick 3
		Trick aTrick3 = new Trick(aBid3);
		aTrick3.add(AllCardsTa.aAD);
		aTrick3.add(AllCardsTa.aJH);
		aTrick3.add(AllCardsTa.aHJo);
		aTrick3.add(AllCardsTa.aLJo);
		// Trick 4
		Trick aTrick4 = new Trick(aBid4);
		aTrick4.add(AllCardsTa.aLJo);
		aTrick4.add(AllCardsTa.aKH);
		aTrick4.add(AllCardsTa.aJD);
		aTrick4.add(AllCardsTa.aHJo);

		//Checking highest()
		assertTrue(aTrick1.highest().equals(AllCardsTa.aHJo));
		assertTrue(aTrick2.highest().equals(AllCardsTa.aHJo));
		assertTrue(aTrick3.highest().equals(AllCardsTa.aHJo));
		assertTrue(aTrick4.highest().equals(AllCardsTa.aHJo));
		
		//Checking winnerIndex()
		assertTrue(aTrick1.winnerIndex() == 0);
		assertTrue(aTrick2.winnerIndex() == 1);
		assertTrue(aTrick3.winnerIndex() == 2);
		assertTrue(aTrick4.winnerIndex() == 3);
	}
	
	@Test
	public void TestTricksOneJokers()
	{
		//No Trump Bids
		Bid aBid1 = new Bid(23);
		Bid aBid2 = new Bid(24);
		//Trump Bids
		Bid aBid3 = new Bid(7, Suit.CLUBS);
		Bid aBid4 = new Bid(5, Suit.SPADES);
		// Trick 1
		Trick aTrick1 = new Trick(aBid1);
		aTrick1.add(AllCardsTa.aHJo);
		aTrick1.add(AllCardsTa.a5H);
		aTrick1.add(AllCardsTa.a4H);
		aTrick1.add(AllCardsTa.aTS);
		// Trick 2
		Trick aTrick2 = new Trick(aBid2);
		aTrick2.add(AllCardsTa.a6D);
		aTrick2.add(AllCardsTa.aHJo);
		aTrick2.add(AllCardsTa.a4H);
		aTrick2.add(AllCardsTa.aTS);
		// Trick 3
		Trick aTrick3 = new Trick(aBid3);
		aTrick3.add(AllCardsTa.aAD);
		aTrick3.add(AllCardsTa.aJH);
		aTrick3.add(AllCardsTa.a7S);
		aTrick3.add(AllCardsTa.aLJo);
		// Trick 4
		Trick aTrick4 = new Trick(aBid4);
		aTrick4.add(AllCardsTa.aLJo);
		aTrick4.add(AllCardsTa.aKH);
		aTrick4.add(AllCardsTa.aJD);
		aTrick4.add(AllCardsTa.aTC);

		//Checking highest()
		assertTrue(aTrick1.highest().equals(AllCardsTa.aHJo));
		assertTrue(aTrick2.highest().equals(AllCardsTa.aHJo));
		assertTrue(aTrick3.highest().equals(AllCardsTa.aLJo));
		assertTrue(aTrick4.highest().equals(AllCardsTa.aLJo));
		
		//Checking winnerIndex()
		assertTrue(aTrick1.winnerIndex() == 0);
		assertTrue(aTrick2.winnerIndex() == 1);
		assertTrue(aTrick3.winnerIndex() == 3);
		assertTrue(aTrick4.winnerIndex() == 0);
	}
	
	@Test
	public void TestTricksNoJokers()
	{
		//No Trump Bids
		Bid aBid1 = new Bid(24);
		Bid aBid2 = new Bid(9); 
		//Trump Bids
		Bid aBid3 = new Bid(7, Suit.CLUBS);
		Bid aBid4 = new Bid(5, Suit.SPADES);
		// Trick 1
		Trick aTrick1 = new Trick(aBid1);
		aTrick1.add(AllCardsTa.a4D);
		aTrick1.add(AllCardsTa.a5H);
		aTrick1.add(AllCardsTa.a4H);
		aTrick1.add(AllCardsTa.aTS);
		// Trick 2
		Trick aTrick2 = new Trick(aBid2);
		aTrick2.add(AllCardsTa.a6D);
		aTrick2.add(AllCardsTa.aTD);
		aTrick2.add(AllCardsTa.a4H);
		aTrick2.add(AllCardsTa.aTS);
		// Trick 3
		Trick aTrick3 = new Trick(aBid3);
		aTrick3.add(AllCardsTa.aAD);
		aTrick3.add(AllCardsTa.aJH);
		aTrick3.add(AllCardsTa.a7S);
		aTrick3.add(AllCardsTa.a4C);
		// Trick 4
		Trick aTrick4 = new Trick(aBid4);
		aTrick4.add(AllCardsTa.aTS);
		aTrick4.add(AllCardsTa.aKH);
		aTrick4.add(AllCardsTa.aJD);
		aTrick4.add(AllCardsTa.aTC);

		//Checking highest()
		assertTrue(aTrick1.highest().equals(AllCardsTa.a4D));
		assertTrue(aTrick2.highest().equals(AllCardsTa.aTD)); 
		assertTrue(aTrick3.highest().equals(AllCardsTa.a4C));
		assertTrue(aTrick4.highest().equals(AllCardsTa.aTS));
		
		//Checking winnerIndex()
		assertTrue(aTrick1.winnerIndex() == 0);
		assertTrue(aTrick2.winnerIndex() == 1); 
		assertTrue(aTrick3.winnerIndex() == 3);
		assertTrue(aTrick4.winnerIndex() == 0);
	}
	
	@Test
	public void TestTricksNoJokersOneEffectiveJack()
	{
		//No Trump Bids
		Bid aBid1 = new Bid(24);
		Bid aBid2 = new Bid(9);
		//Trump Bids
		Bid aBid3 = new Bid(7, Suit.CLUBS);
		Bid aBid4 = new Bid(5, Suit.HEARTS);
		// Trick 1
		Trick aTrick1 = new Trick(aBid1);
		aTrick1.add(AllCardsTa.a4D);
		aTrick1.add(AllCardsTa.a5H);
		aTrick1.add(AllCardsTa.a4H);
		aTrick1.add(AllCardsTa.aTS);
		// Trick 2
		Trick aTrick2 = new Trick(aBid2);
		aTrick2.add(AllCardsTa.a6D);
		aTrick2.add(AllCardsTa.aTD);
		aTrick2.add(AllCardsTa.a4H);
		aTrick2.add(AllCardsTa.aTS);
		// Trick 3
		Trick aTrick3 = new Trick(aBid3);
		aTrick3.add(AllCardsTa.aAD);
		aTrick3.add(AllCardsTa.aKH);
		aTrick3.add(AllCardsTa.aJS);
		aTrick3.add(AllCardsTa.a4C);
		// Trick 4
		Trick aTrick4 = new Trick(aBid4);
		aTrick4.add(AllCardsTa.aTS);
		aTrick4.add(AllCardsTa.aKH);
		aTrick4.add(AllCardsTa.aJD);
		aTrick4.add(AllCardsTa.aTC);

		//Checking highest()
		assertTrue(aTrick1.highest().equals(AllCardsTa.a4D));
		assertTrue(aTrick2.highest().equals(AllCardsTa.aTD)); 
		assertTrue(aTrick3.highest().equals(AllCardsTa.aJS));
		assertTrue(aTrick4.highest().equals(AllCardsTa.aJD));
		
		//Checking winnerIndex()
		assertTrue(aTrick1.winnerIndex() == 0);
		assertTrue(aTrick2.winnerIndex() == 1); 
		assertTrue(aTrick3.winnerIndex() == 2);
		assertTrue(aTrick4.winnerIndex() == 2);
	}
	
	/**
	 * 
	 */
	@Test
	public void TestTricksHelperFunctions()
	{
		//No Trump Bids
		Bid aBid1 = new Bid(24);
		Bid aBid2 = new Bid(9); 
		//Trump Bids
		Bid aBid3 = new Bid(7, Suit.CLUBS);
		Bid aBid4 = new Bid(5, Suit.HEARTS);
		// Trick 1
		Trick aTrick1 = new Trick(aBid1);
		aTrick1.add(AllCardsTa.aHJo);
		aTrick1.add(AllCardsTa.a5H);
		aTrick1.add(AllCardsTa.a4H);
		aTrick1.add(AllCardsTa.aTS);
		// Trick 2
		Trick aTrick2 = new Trick(aBid2);
		aTrick2.add(AllCardsTa.aLJo);
		aTrick2.add(AllCardsTa.aTD);
		aTrick2.add(AllCardsTa.a4H);
		aTrick2.add(AllCardsTa.aTS);
		// Trick 3
		Trick aTrick3 = new Trick(aBid3);
		aTrick3.add(AllCardsTa.aAD);
		aTrick3.add(AllCardsTa.aKH);
		aTrick3.add(AllCardsTa.aJS);
		aTrick3.add(AllCardsTa.a4C);
		// Trick 4
		Trick aTrick4 = new Trick(aBid4);
		aTrick4.add(AllCardsTa.aTS);
		aTrick4.add(AllCardsTa.aKH);
		aTrick4.add(AllCardsTa.aJD);
		aTrick4.add(AllCardsTa.aTC);

		//Checking getTrumpSuit()
		assertTrue(aTrick1.getTrumpSuit() == null);
		assertTrue(aTrick2.getTrumpSuit() == null); 
		assertTrue(aTrick3.getTrumpSuit().equals(Suit.CLUBS));
		assertTrue(aTrick4.getTrumpSuit().equals(Suit.HEARTS));
		
		//Checking jokerLed()
		assertTrue(aTrick1.jokerLed());
		assertTrue(aTrick2.jokerLed());
		assertFalse(aTrick3.jokerLed());
		assertFalse(aTrick4.jokerLed());
		
		//Checking cardLed()
		assertTrue(aTrick1.cardLed().equals(AllCardsTa.aHJo));
		assertTrue(aTrick2.cardLed().equals(AllCardsTa.aLJo));
		assertTrue(aTrick3.cardLed().equals(AllCardsTa.aAD));
		assertTrue(aTrick4.cardLed().equals(AllCardsTa.aTS));
		
	}
}
