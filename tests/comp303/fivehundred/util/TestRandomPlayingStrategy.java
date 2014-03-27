package comp303.fivehundred.util;

import static org.junit.Assert.*;
import org.junit.Test;

import comp303.fivehundred.ai.RandomPlayingStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card.Joker;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author JeanBenoit
 * Test class for RandomPlayingStrategy in AI
 */
public class TestRandomPlayingStrategy
{
	@Test
	public void TestRandomPlayingStrategyTest1()
	{
		Hand hand = new Hand();
		hand.add(AllCardsTa.aHJo);
		hand.add(AllCardsTa.a8D);
		Hand hand1 = new Hand();
		hand1.add(new Card(Rank.TEN, Suit.HEARTS));
		hand1.add(new Card(Rank.EIGHT, Suit.SPADES));
		hand1.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		Hand hand2 = new Hand();
		hand2.add(new Card(Rank.EIGHT, Suit.SPADES));
		hand2.add(new Card(Rank.SIX, Suit.HEARTS));
		hand2.add(new Card(Rank.ACE, Suit.DIAMONDS));
		hand2.add(new Card(Rank.EIGHT, Suit.HEARTS));
		hand2.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
		Hand hand3 = new Hand();
		hand3.add(new Card(Joker.HIGH));
		Hand hand4 = new Hand();
		hand4.add(new Card(Joker.HIGH));
		hand4.add(new Card(Rank.EIGHT, Suit.SPADES));
		
		Trick trick = new Trick(new Bid(20));
		Trick trick1 = new Trick(new Bid(8, Suit.DIAMONDS)); // create a trick in trump that leads
		Trick trick2 = new Trick(new Bid(23)); // Create a trick in no trump that leads
		Trick trick3 = new Trick(new Bid(23)); // Create a trick in no trump that does not lead
		Trick trick4 = new Trick(new Bid(7, Suit.HEARTS)); // Create a trick in no trump that does not lead
		//Trick trick5 = new Trick(new Bid(3));
		trick1.add(AllCardsTa.a4H);
		trick1.add(AllCardsTa.a7H);
		trick1.add(AllCardsTa.a9C);
		trick2.add(AllCardsTa.a4H);
		trick2.add(AllCardsTa.a7H);
		trick2.add(AllCardsTa.a9C);
		trick3.add(AllCardsTa.a4H);
		trick3.add(AllCardsTa.a9C);
		trick3.add(AllCardsTa.a7H);
		trick4.add(AllCardsTa.a4H);
		trick4.add(AllCardsTa.a7H);
		trick4.add(AllCardsTa.a9C);
		
		RandomPlayingStrategy strat1 = new RandomPlayingStrategy();
		Card placedCard1 = strat1.play(trick1, hand3);
		assertTrue(AllCardsTa.aHJo.equals(placedCard1));
		Card placedCard3 = strat1.play(trick3, hand1);
		assertTrue(AllCardsTa.aTH.equals(placedCard3));
		assertEquals(Suit.HEARTS, trick4.getSuitLed());
		Card placedCard4 = strat1.play(trick4, hand2);
		assertTrue(AllCardsTa.a8H.equals(placedCard4) || AllCardsTa.a6H.equals(placedCard4));
	}
	
}
