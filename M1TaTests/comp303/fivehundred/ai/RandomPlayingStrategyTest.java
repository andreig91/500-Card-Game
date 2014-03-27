package comp303.fivehundred.ai;

import static org.junit.Assert.*;

import org.junit.Test;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.AllCardsTa;

public class RandomPlayingStrategyTest
{	
	@Test
	public void testRandomPlayingStrategyConstructorOnly()
	{
		new RandomPlayingStrategy ();	
	}
	

/*	@Test(expected = NullPointerException.class)
	public void testRandomPlayingStrategyPlayEmptyArgumentHand()
	{
		RandomPlayingStrategy randomPlayingStrategy = new RandomPlayingStrategy ();
		randomPlayingStrategy.play(null, null);
	}
	
*/
	
/*	@Test(expected = NullPointerException.class)
	public void testRandomPlayingStrategyPlayEmptyArgumentTrick()
	{
		RandomPlayingStrategy randomPlayingStrategy = new RandomPlayingStrategy ();
		Hand h = new Hand();
		h.add(new Card(Rank.FOUR,Suit.SPADES));
		h.add(new Card(Rank.FIVE,Suit.CLUBS));
		randomPlayingStrategy.play(null, h);
	}
	
*/	
	@Test
	public void testRandomPlayingStrategyPlay()
	{
		RandomPlayingStrategy randomPlayingStrategy = new RandomPlayingStrategy ();
		
		Hand h = new Hand();
		h.add(new Card(Rank.FIVE,Suit.SPADES));
		//Card c = randomPlayingStrategy.play(null, h);
		//assertTrue((c.getRank()==Rank.FIVE)&&(c.getSuit()==Suit.SPADES));
		
		//h.add(new Card(Rank.FIVE,Suit.SPADES));
		Card c = randomPlayingStrategy.play(new Trick(new Bid(10)), h);
		
		assertTrue((c.getRank()==Rank.FIVE)&&(c.getSuit()==Suit.SPADES));
		
		h.add(new Card(Rank.FIVE,Suit.DIAMONDS));		
		h.add(new Card(Rank.FIVE,Suit.CLUBS));		
		h.add(new Card(Rank.FIVE,Suit.HEARTS));
		h.add(new Card(Rank.FOUR,Suit.DIAMONDS));		
		h.add(new Card(Rank.FOUR,Suit.SPADES));		
		h.add(new Card(Rank.FOUR,Suit.CLUBS));		
		h.add(new Card(Rank.FOUR,Suit.HEARTS));
		h.add(new Card(Rank.SIX,Suit.DIAMONDS));		
		h.add(new Card(Rank.SIX,Suit.SPADES));		
		h.add(new Card(Rank.SIX,Suit.CLUBS));		
		h.add(new Card(Rank.SIX,Suit.HEARTS));
		Card myCard = randomPlayingStrategy.play(new Trick(new Bid(20)), h);

		assertTrue(myCard.getRank()==Rank.FOUR || myCard.getRank()==Rank.FIVE || myCard.getRank()==Rank.SIX); 
		
	}
}
