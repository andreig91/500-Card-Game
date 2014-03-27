package comp303.fivehundred.util;


import org.junit.Test;
import static org.junit.Assert.*;

import comp303.fivehundred.ai.RandomBiddingStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card.Joker;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author JeanBenoit
 * Testing class for AI: RandomBiddingStrategy
 */
public class TestRandomBiddingStrategy
{
	/**
	 * The most important thing to note about this test is that it takes for granted that the index can go up to 24. 
	 */
	@Test
	public void TestRandomBiddingStrategyTest1()
	{
		RandomBiddingStrategy strat1 = new RandomBiddingStrategy();
		RandomBiddingStrategy strat2 = new RandomBiddingStrategy(80);
		Bid[] bidArray = new Bid[5];
		bidArray[0] = new Bid(20);
		bidArray[1] = new Bid(12);
		bidArray[2] = new Bid(11);
		bidArray[3] = new Bid(22);
		bidArray[4] = new Bid(3);
		Hand myHand = new Hand();
		myHand.add(new Card(Joker.LOW));
		myHand.add(new Card(Rank.FOUR, Suit.SPADES));
		myHand.add(new Card(Rank.FIVE, Suit.HEARTS));
		Bid bid1 = strat1.selectBid(bidArray, myHand);
		Bid bid2 = strat1.selectBid(bidArray, myHand);
		Bid bid3 = strat1.selectBid(bidArray, myHand);
		Bid bid4 = strat2.selectBid(bidArray, myHand);
		Bid bid5 = strat2.selectBid(bidArray, myHand);
		Bid bid6 = strat2.selectBid(bidArray, myHand);
		assertTrue(bid1.isPass() || (bid1.toIndex() > 22 && bid1.toIndex() <= Constants.MAX_BIDDING_INDEX));
		assertTrue(bid2.isPass() || (bid2.toIndex() > 22 && bid2.toIndex() <= Constants.MAX_BIDDING_INDEX));
		assertTrue(bid3.isPass() || (bid3.toIndex() > 22 && bid3.toIndex() <= Constants.MAX_BIDDING_INDEX));
		assertTrue(bid4.isPass() || (bid4.toIndex() > 22 && bid4.toIndex() <= Constants.MAX_BIDDING_INDEX));
		assertTrue(bid5.isPass() || (bid5.toIndex() > 22 && bid5.toIndex() <= Constants.MAX_BIDDING_INDEX));
		assertTrue(bid6.isPass() || (bid6.toIndex() > 22 && bid6.toIndex() <= Constants.MAX_BIDDING_INDEX));
		
		
	}
	
}
