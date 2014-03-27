package comp303.fivehundred.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import comp303.fivehundred.util.AllCardsTa;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.Suit;

public class TestTrick
{

	Trick emptyTrick;
	Trick nonEmptyTrick;
	Trick nonTrumpTrick;
	Trick jokerLHTrick;
	Trick lowJokerFirstTrick;
	Trick highJokerFirstTrick;
	Trick firstjackTrick;
	
	@Before
	public void setUp() throws Exception
	{
		Bid contract = new Bid(8, Suit.CLUBS);		
		emptyTrick = new Trick(contract); 
		
		nonEmptyTrick = new Trick(contract);
		nonEmptyTrick.add(AllCardsTa.aQH);
		nonEmptyTrick.add(AllCardsTa.a7C);
		
		nonTrumpTrick = new Trick(new Bid(9, null));		
		nonTrumpTrick.add(AllCardsTa.a7D);
		nonTrumpTrick.add(AllCardsTa.aKD);
		nonTrumpTrick.add(AllCardsTa.a6S);
		nonTrumpTrick.add(AllCardsTa.aJH);
		
		jokerLHTrick = new Trick(new Bid(9, Suit.DIAMONDS));
		jokerLHTrick.add(AllCardsTa.aLJo);
		jokerLHTrick.add(AllCardsTa.aKD);
		jokerLHTrick.add(AllCardsTa.a7H);
		jokerLHTrick.add(AllCardsTa.aHJo);

		lowJokerFirstTrick = new Trick(new Bid(9, Suit.HEARTS));
		lowJokerFirstTrick.add(AllCardsTa.aLJo);
		lowJokerFirstTrick.add(AllCardsTa.aKD);
		lowJokerFirstTrick.add(AllCardsTa.aQH);
		lowJokerFirstTrick.add(AllCardsTa.a8H);
		
		highJokerFirstTrick = new Trick(new Bid(9, Suit.HEARTS));
		highJokerFirstTrick.add(AllCardsTa.aHJo);
		highJokerFirstTrick.add(AllCardsTa.aKD);
		highJokerFirstTrick.add(AllCardsTa.aQH);
		highJokerFirstTrick.add(AllCardsTa.aLJo);
		
		firstjackTrick = new Trick(new Bid(9, Suit.SPADES));
		firstjackTrick.add(AllCardsTa.aJC);
	}

	@Test
	public void testGetTrumpSuit()
	{
		assertTrue(Suit.CLUBS.equals(emptyTrick.getTrumpSuit()));
		assertTrue(Suit.CLUBS.equals(nonEmptyTrick.getTrumpSuit()));
		assertTrue(nonTrumpTrick.getTrumpSuit() == null);		
	}

	@Test
	public void testGetSuitLed()
	{
		
		assertTrue(nonEmptyTrick.getSuitLed().equals(Suit.HEARTS));
		assertTrue(nonTrumpTrick.getSuitLed().equals(Suit.DIAMONDS));		
		assertTrue(lowJokerFirstTrick.getSuitLed().equals(Suit.HEARTS));	
		assertTrue(highJokerFirstTrick.getSuitLed().equals(Suit.HEARTS));
		assertTrue(firstjackTrick.getSuitLed().equals(Suit.SPADES));
	}

	@Test
	public void testJokerLed()
	{
		
		assertTrue(!nonEmptyTrick.jokerLed());
		assertTrue(!nonTrumpTrick.jokerLed());		
		assertTrue(lowJokerFirstTrick.jokerLed());	
		assertTrue(highJokerFirstTrick.jokerLed());
	}

	@Test
	public void testCardLed()
	{
		assertTrue(nonEmptyTrick.cardLed().equals(AllCardsTa.aQH));
		assertTrue(nonTrumpTrick.cardLed().equals(AllCardsTa.a7D));		
		assertTrue(lowJokerFirstTrick.cardLed().equals(AllCardsTa.aLJo));	
		assertTrue(highJokerFirstTrick.cardLed().equals(AllCardsTa.aHJo));
	}

	@Test
	public void testHighest()
	{
		assertTrue(nonEmptyTrick.highest().equals(AllCardsTa.a7C));
		assertTrue(nonTrumpTrick.highest().equals(AllCardsTa.aKD));		
		assertTrue(jokerLHTrick.highest().equals(AllCardsTa.aHJo));		
		assertTrue(lowJokerFirstTrick.highest().equals(AllCardsTa.aLJo));
		assertTrue(highJokerFirstTrick.highest().equals(AllCardsTa.aHJo));
	}

	@Test
	public void testWinnerIndex()
	{
		
		assertTrue(nonTrumpTrick.winnerIndex() == 1);		
		assertTrue(jokerLHTrick.winnerIndex() == 3);	
		assertTrue(lowJokerFirstTrick.winnerIndex() == 0);
		assertTrue(highJokerFirstTrick.winnerIndex() == 0);	
	}

}



