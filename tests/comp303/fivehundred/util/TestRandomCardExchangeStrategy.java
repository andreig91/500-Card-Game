/**
 * 
 */
package comp303.fivehundred.util;

import static org.junit.Assert.*;
import comp303.fivehundred.ai.*;
import comp303.fivehundred.model.*;
import comp303.fivehundred.util.Card.Suit;

import org.junit.Test;

/**
 * @author Amjad Al-Rikabi
 *
 */
public class TestRandomCardExchangeStrategy
{
	@Test
	public void TestRandomCardExchangeStrategyTest1()
	{
		//Bid1
		Bid aBid1 = new Bid(7, Suit.SPADES);
		//Bid2
		Bid aBid2 = new Bid(6, Suit.HEARTS);
		//Bid3
		Bid aBid3 = new Bid(7, Suit.DIAMONDS);
		//Bid4
		Bid aBid4 = new Bid(24);
		
		//Hand1
		Hand aHand1 = new Hand();
		aHand1.add(AllCardsTa.a4H);
		aHand1.add(AllCardsTa.a5C);
		aHand1.add(AllCardsTa.a6S);
		aHand1.add(AllCardsTa.a5H);
		aHand1.add(AllCardsTa.a6C);
		aHand1.add(AllCardsTa.a4S);
		aHand1.add(AllCardsTa.a7S);
		aHand1.add(AllCardsTa.a7H);
		aHand1.add(AllCardsTa.a7C);
		aHand1.add(AllCardsTa.aLJo);
		aHand1.add(AllCardsTa.a6D);
		aHand1.add(AllCardsTa.a4D);
		aHand1.add(AllCardsTa.a7D);
		aHand1.add(AllCardsTa.aKD);
		aHand1.add(AllCardsTa.aJH);
		aHand1.add(AllCardsTa.aQD);
		
		//Bid Array
		Bid[] aBidArray1 = {aBid1, aBid2, aBid3, aBid4};
		
		//Loop 100 times for randomness
		for (int i =0; i<1000; i++)
		{
			//Check aHand1 Size
			assertTrue(aHand1.size() == 16);
			//Create a new CardList
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray1, 0, aHand1);
			//Check that we have the correct Discarded List Size
			assertTrue(aCardList1.size() == 6);
			//Check that we have not changed aHand
			assertTrue(aHand1.size() == 16);
		}
	}
	
	@Test
	public void TestRandomCardExchangeStrategyTestAssertions()
	{
		//Bid1
		Bid aBid1 = new Bid(7, Suit.SPADES);
		//Bid2
		Bid aBid2 = new Bid(6, Suit.HEARTS);
		//Bid3
		Bid aBid3 = new Bid(7, Suit.DIAMONDS);
		//Bid4
		Bid aBid4 = new Bid(24);
		//PassBid1
		Bid aPassBid1 = new Bid();
		//PassBid1
		Bid aPassBid2 = new Bid();
		//PassBid1
		Bid aPassBid3 = new Bid();
		//PassBid1
		Bid aPassBid4 = new Bid();
		
		//Hand1
		Hand aHand1 = new Hand();
		aHand1.add(AllCardsTa.a4H);
		aHand1.add(AllCardsTa.a5C);
		aHand1.add(AllCardsTa.a6S);
		aHand1.add(AllCardsTa.a5H);
		aHand1.add(AllCardsTa.a6C);
		aHand1.add(AllCardsTa.a4S);
		aHand1.add(AllCardsTa.a7S);
		aHand1.add(AllCardsTa.a7H);
		aHand1.add(AllCardsTa.a7C);
		aHand1.add(AllCardsTa.aLJo);
		aHand1.add(AllCardsTa.a6D);
		aHand1.add(AllCardsTa.a4D);
		aHand1.add(AllCardsTa.a7D);
		aHand1.add(AllCardsTa.aKD);
		aHand1.add(AllCardsTa.aJH);
		aHand1.add(AllCardsTa.aQD);
		
		//Hand2 - 1 Card Less
		Hand aHand2 = new Hand();
		aHand2.add(AllCardsTa.a4H);
		aHand2.add(AllCardsTa.a5C);
		aHand2.add(AllCardsTa.a6S);
		aHand2.add(AllCardsTa.a5H);
		aHand2.add(AllCardsTa.a6C);
		aHand2.add(AllCardsTa.a4S);
		aHand2.add(AllCardsTa.a7S);
		aHand2.add(AllCardsTa.a7H);
		aHand2.add(AllCardsTa.a7C);
		aHand2.add(AllCardsTa.aLJo);
		aHand2.add(AllCardsTa.a6D);
		aHand2.add(AllCardsTa.a4D);
		aHand2.add(AllCardsTa.a7D);
		aHand2.add(AllCardsTa.aKD);
		aHand2.add(AllCardsTa.aJH);
		
		//Hand3 - One Card More
		Hand aHand3 = new Hand();
		aHand3.add(AllCardsTa.a4H);
		aHand3.add(AllCardsTa.a5C);
		aHand3.add(AllCardsTa.a6S);
		aHand3.add(AllCardsTa.a5H);
		aHand3.add(AllCardsTa.a6C);
		aHand3.add(AllCardsTa.a4S);
		aHand3.add(AllCardsTa.a7S);
		aHand3.add(AllCardsTa.a7H);
		aHand3.add(AllCardsTa.a7C);
		aHand3.add(AllCardsTa.aLJo);
		aHand3.add(AllCardsTa.a6D);
		aHand3.add(AllCardsTa.a4D);
		aHand3.add(AllCardsTa.a7D);
		aHand3.add(AllCardsTa.aKD);
		aHand3.add(AllCardsTa.aJH);
		aHand3.add(AllCardsTa.aQD);
		aHand3.add(AllCardsTa.aHJo);
		
		//Bid Array
		Bid[] aBidArray1 = {aBid1, aBid2, aBid3, aBid4};
		Bid[] aBidArray2 = {aBid1, aBid2, aBid3, aBid4, aBid3};
		Bid[] aPassBidArray1 = {aPassBid1, aPassBid2, aPassBid3, aPassBid4};
		
		//Check Assertion for Number Of Bids 
		try
		  {
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray2, 0, aHand1);
		    fail("Should have thrown AIException but did not!");
		  }
		  catch( final AIException e )
		  {
		    final String msg = "We do not have 4 Bids!";
		    assertEquals(msg, e.getMessage());
		  }
		
		//Check Assertion for Number Of Cards in a Hand
		try
		  {
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray1, 0, aHand2);
		    fail("Should have thrown AIException but did not!");
		  }
		  catch( final AIException e )
		  {
		    final String msg = "We do not have 16 Cards in the Hand!";
		    assertEquals(msg, e.getMessage());
		  }
		
		//Check Assertion for Atleast One non-Passing Bid
		try
		  {
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aPassBidArray1, 0, aHand2);
		    fail("Should have thrown AIException but did not!");
		  }
		  catch( final AIException e )
		  {
		    final String msg = "We do not have at least one non-passing Bid!";
		    assertEquals(msg, e.getMessage());
		  }
		
		//Check Assertion for Valid Player Index: 5
		try
		  {
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray1, 5, aHand1);
		    fail("Should have thrown AIException but did not!");
		  }
		  catch( final AIException e )
		  {
		    final String msg = "We do not have a valid Player index!";
		    assertEquals(msg, e.getMessage());
		  }
		
		//Check Assertion for Valid Player Index: -1
		try
		  {
			CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray1, -1, aHand1);
		    fail("Should have thrown AIException but did not!");
		  }
		  catch( final AIException e )
		  {
		    final String msg = "We do not have a valid Player index!";
		    assertEquals(msg, e.getMessage());
		  }
		
		//PASS ONE CASE FOR SANITY
		//Check aHand1 Size
		assertTrue(aHand1.size() == 16);
		//Create a new CardList
		CardList aCardList1 = new RandomCardExchangeStrategy().selectCardsToDiscard(aBidArray1, 0, aHand1);
		//Check that we have the correct Discarded List Size
		assertTrue(aCardList1.size() == 6);
		//Check that we have not changed aHand
		assertTrue(aHand1.size() == 16);
	}
}
