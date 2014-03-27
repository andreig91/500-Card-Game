package comp303.fivehundred.ai;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;


public class RandomCardExchangeStrategyTest
{
	@Test
	public void testRandomBiddingStrategyConstructorOK()
	{
		new RandomCardExchangeStrategy ();
	}	

/*	@Test(expected=NullPointerException.class)
	public void testRandomBiddingStrategySelectCardsToDiscardEmptyInput()
	{
		RandomCardExchangeStrategy randomCardExStrategy = new RandomCardExchangeStrategy ();
		randomCardExStrategy.selectCardsToDiscard(null, 1, null);		
	}
*/
	@Test
	public void testRandomBiddingStrategySelectCardsToDiscard()
	{
		Bid bid1 = new Bid(7, Suit.HEARTS);
		Bid bid2 = new Bid(8, Suit.CLUBS);
		Bid bid3 = new Bid(9, Suit.SPADES);
		Bid bid4 = new Bid(10, Suit.DIAMONDS);
		Bid[] bids = {bid1, bid2, bid3, bid4};
		
		RandomCardExchangeStrategy randomCardExStrategy = new RandomCardExchangeStrategy ();
		Hand h = new Hand();
		
		h.add(new Card(Rank.FOUR,Suit.SPADES));
		h.add(new Card(Rank.FIVE,Suit.CLUBS));
		h.add(new Card(Rank.SIX,Suit.DIAMONDS));
		h.add(new Card(Rank.FOUR,Suit.DIAMONDS));
		h.add(new Card(Rank.FIVE,Suit.SPADES));
		h.add(new Card(Rank.SIX,Suit.CLUBS));
		h.add(new Card(Rank.FOUR,Suit.CLUBS));
		h.add(new Card(Rank.FIVE,Suit.DIAMONDS));
		h.add(new Card(Rank.SIX,Suit.SPADES));
		h.add(new Card(Rank.FOUR, Suit.HEARTS));
		h.add(new Card(Rank.FIVE, Suit.CLUBS));
		h.add(new Card(Rank.SIX, Suit.HEARTS));
		h.add(new Card(Rank.EIGHT, Suit.CLUBS));
		h.add(new Card(Rank.EIGHT, Suit.HEARTS));
		h.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
		h.add(new Card (Rank.NINE, Suit.DIAMONDS));
		h.add(new Card(Rank.NINE, Suit.CLUBS));

		CardList cl = randomCardExStrategy.selectCardsToDiscard(bids, 1, h);	
		assertTrue(cl.size()==6);
	}
	

}
