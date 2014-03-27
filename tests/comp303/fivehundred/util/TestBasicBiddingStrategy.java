package comp303.fivehundred.util;


import org.junit.Test;
import static org.junit.Assert.*;

import comp303.fivehundred.ai.BasicBiddingStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card.Joker;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author Amjad Al-Rikabi
 * Testing class for AI: BasicBiddingStrategy
 */
public class TestBasicBiddingStrategy
{
	/**
	 * The most important thing to note about this test is that it takes for granted that the index can go up to 24. 
	 */
	@Test
	public void Sanity_TestBasicBiddingStrategyTest()
	{
		BasicBiddingStrategy strat1 = new BasicBiddingStrategy();
		BasicBiddingStrategy strat2 = new BasicBiddingStrategy();
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
	
	@Test
	public void TestBasicBiddingStrategyTest1()
	{
		BasicBiddingStrategy strat1 = new BasicBiddingStrategy();
		BasicBiddingStrategy strat2 = new BasicBiddingStrategy();
		Bid[] bidArray = new Bid[4];
		bidArray[0] = new Bid(); //Pass
		bidArray[1] = new Bid(6, Suit.SPADES);
		bidArray[2] = new Bid(); //Pass
		Hand myHand = new Hand();
		myHand.add(new Card(Joker.LOW));
		myHand.add(new Card(Joker.HIGH));
		myHand.add(new Card(Rank.KING, Suit.SPADES));
		myHand.add(new Card(Rank.QUEEN, Suit.SPADES));
		myHand.add(new Card(Rank.TEN, Suit.SPADES));
		myHand.add(new Card(Rank.ACE, Suit.SPADES));
		myHand.add(new Card(Rank.FIVE, Suit.SPADES));
		myHand.add(new Card(Rank.ACE, Suit.HEARTS));
		myHand.add(new Card(Rank.KING, Suit.HEARTS));
		myHand.add(new Card(Rank.QUEEN, Suit.HEARTS));
		assertTrue(myHand.getJokers().size() == 2);
		assertTrue(strat1.getPointsForJokersInHand(myHand) == 30);
		assertTrue(strat1.getStrongCardPointsInSuit(myHand, Suit.SPADES) == 38);
		assertTrue(strat1.getStrongCardPointsInSuit(myHand, Suit.HEARTS) == 32);
		assertTrue(strat1.getStrongCardPointsInSuit(myHand, Suit.CLUBS) == 0);
		assertTrue(strat1.getStrongCardPointsInSuit(myHand, Suit.DIAMONDS) == 0);
		assertTrue(strat1.getNumberOfCardPoints(myHand, Suit.SPADES) == 5);
		assertTrue(strat1.getNumberOfCardPoints(myHand, Suit.HEARTS) == 3); 
		assertTrue(strat1.getNumberOfCardPoints(myHand, Suit.CLUBS) == 0);
		assertTrue(strat1.getNumberOfCardPoints(myHand, Suit.DIAMONDS) == 0);
		assertTrue(strat1.addExtraPointsForLongSuits(strat1.getNumberOfCardPoints(myHand, Suit.SPADES)) == 25);
		assertTrue(strat1.addExtraPointsForLongSuits(strat1.getNumberOfCardPoints(myHand, Suit.HEARTS)) == 3);
		assertTrue(strat1.addExtraPointsForLongSuits(strat1.getNumberOfCardPoints(myHand, Suit.DIAMONDS)) == 0);
		assertTrue(strat1.addExtraPointsForLongSuits(strat1.getNumberOfCardPoints(myHand, Suit.DIAMONDS)) == 0);
		//assertTrue(strat1.selectBid(bidArray, myHand).equals(new Bid(10, Suit.SPADES)));
	}
	
	@Test
	public void TestBasicBiddingStrategyTest2()
	{
		BasicBiddingStrategy strat1 = new BasicBiddingStrategy();
		Bid[] bidArray = new Bid[4];
		bidArray[0] = new Bid(); //Pass
		bidArray[1] = new Bid(6, Suit.SPADES);
		bidArray[2] = new Bid(); //Pass
		Bid aBidResult = new Bid(8, null);
		Hand myHand = new Hand();
		myHand.add(new Card(Rank.EIGHT, Suit.SPADES));
		myHand.add(new Card(Rank.KING, Suit.SPADES));
		myHand.add(new Card(Rank.KING, Suit.HEARTS));
		myHand.add(new Card(Rank.SIX, Suit.CLUBS));
		myHand.add(new Card(Rank.QUEEN, Suit.SPADES));
		myHand.add(new Card(Rank.QUEEN, Suit.CLUBS));
		myHand.add(new Card(Rank.KING, Suit.CLUBS));
		myHand.add(new Card(Joker.HIGH));
		myHand.add(new Card(Rank.TEN, Suit.SPADES));
		myHand.add(new Card(Rank.SEVEN, Suit.HEARTS));
		//assertTrue(strat1.selectBid(bidArray, myHand).equals(aBidResult));
	}
	
	@Test
	public void TestBasicBiddingStrategyTest3()
	{
		BasicBiddingStrategy strat1 = new BasicBiddingStrategy();
		//Andrei: line 118 was Bid[4] instead of Bid[3] 
		Bid[] bidArray = new Bid[3];
		bidArray[0] = new Bid(); //Pass
		bidArray[1] = new Bid(); //Pass
		bidArray[2] = new Bid(); //Pass
		Bid aBidResult = new Bid(7, Suit.DIAMONDS);
		Hand myHand = new Hand();
		myHand.add(new Card(Rank.SEVEN, Suit.SPADES));
		myHand.add(new Card(Rank.KING, Suit.DIAMONDS));
		myHand.add(new Card(Rank.TEN, Suit.CLUBS));
		myHand.add(new Card(Rank.SEVEN, Suit.HEARTS));
		myHand.add(new Card(Rank.NINE, Suit.DIAMONDS));
		myHand.add(new Card(Rank.JACK, Suit.HEARTS));
		myHand.add(new Card(Rank.EIGHT, Suit.HEARTS));
		myHand.add(new Card(Rank.ACE, Suit.DIAMONDS));
		myHand.add(new Card(Rank.KING, Suit.CLUBS));
		myHand.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
		assertTrue(strat1.selectBid(bidArray, myHand).getTricksBid() == 7);
	}
	
}
