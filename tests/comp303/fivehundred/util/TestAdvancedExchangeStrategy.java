package comp303.fivehundred.util;

import org.junit.Test;
import static org.junit.Assert.*;
import static comp303.fivehundred.util.AllCards.*;

import comp303.fivehundred.ai.AdvancedCardExchangeStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card.Suit;

public class TestAdvancedExchangeStrategy
{
	@Test
	public void testUnderSixNoTrumpCards()
	{
		AdvancedCardExchangeStrategy strat1 = new AdvancedCardExchangeStrategy();
		
		Bid[] pbids = new Bid[4];
		pbids[0] = new Bid(6, Suit.SPADES);
		pbids[1] = new Bid(6, Suit.SPADES);
		pbids[2] = new Bid(8, Suit.CLUBS);
		pbids[3] = new Bid(7, Suit.SPADES);
		
		Hand pHand = new Hand();
		pHand.add(a4C);
		pHand.add(a5C);
		pHand.add(a6C);
		pHand.add(a7C);
		pHand.add(a8C);
		pHand.add(a9C);
		pHand.add(aTC);
		pHand.add(aQC);
		pHand.add(aKC);
		pHand.add(aAC);
		pHand.add(aLJo);
		pHand.add(a5S);
		pHand.add(a5H);
		pHand.add(a5D);
		pHand.add(a6D);
		pHand.add(a7H);
		
		CardList discarted;
		discarted = strat1.selectCardsToDiscard(pbids, 2, pHand);
		assertEquals(discarted.getFirst(), a5S);
		assertEquals(discarted.getLast(), a4C);
		assertEquals(discarted.size(), 6);
		
		Hand pHand2 = new Hand();
		pHand2.add(a4C);
		pHand2.add(a5C);
		pHand2.add(a6C);
		pHand2.add(a7C);
		pHand2.add(a8C);
		pHand2.add(a9C);
		pHand2.add(aTC);
		pHand2.add(aQC);
		pHand2.add(aKC);
		pHand2.add(aAC);
		pHand2.add(aLJo);
		pHand2.add(aHJo);
		pHand2.add(a5H);
		pHand2.add(a5D);
		pHand2.add(a6D);
		pHand2.add(a7H);
		
		discarted = strat1.selectCardsToDiscard(pbids, 2, pHand2);
		assertEquals(discarted.getFirst(), a5H);
		assertEquals(discarted.getLast(), a5C);
		assertEquals(discarted.size(), 6);
				
	}
	
	@Test
	public void TestSixCardsNoTrump()
	{
		AdvancedCardExchangeStrategy strat1 = new AdvancedCardExchangeStrategy();
		
		Bid[] pbids = new Bid[4];
		pbids[0] = new Bid(6, Suit.SPADES);
		pbids[1] = new Bid(6, Suit.SPADES);
		pbids[2] = new Bid(8, Suit.CLUBS);
		pbids[3] = new Bid(7, Suit.SPADES);
		
		Hand pHand = new Hand();
		pHand.add(a4C);
		pHand.add(a5C);
		pHand.add(a6C);
		pHand.add(a7C);
		pHand.add(a8C);
		pHand.add(a9C);
		pHand.add(aTC);
		pHand.add(aQC);
		pHand.add(aKC);
		pHand.add(aAC);
		pHand.add(a4S);
		pHand.add(a5S);
		pHand.add(a5H);
		pHand.add(a5D);
		pHand.add(a6D);
		pHand.add(a7H);
		
		CardList discarted;
		discarted = strat1.selectCardsToDiscard(pbids, 2, pHand);
		assertEquals(discarted.getFirst(), a4S);
		assertEquals(discarted.getLast(), a7H);
		assertEquals(discarted.size(), 6);
	}
	
	@Test
	public void TestPLusSixCardsNoTrump()
	{
		AdvancedCardExchangeStrategy strat1 = new AdvancedCardExchangeStrategy();
		CardList discarted;
		Bid[] pbids = new Bid[4];
		pbids[0] = new Bid(6, Suit.SPADES);
		pbids[1] = new Bid(6, Suit.SPADES);
		pbids[2] = new Bid(8, Suit.CLUBS);
		pbids[3] = new Bid(7, Suit.SPADES);
		
		Hand pHand = new Hand();
		pHand.add(a4C);
		pHand.add(a5C);
		pHand.add(a6C);
		pHand.add(a7C);
		pHand.add(a8C);
		pHand.add(a9C);
		pHand.add(aTC);
		pHand.add(aQC);
		pHand.add(aAH);
		pHand.add(a8H);
		pHand.add(a4S);
		pHand.add(a5S);
		pHand.add(a5H);
		pHand.add(a5D);
		pHand.add(a6D);
		pHand.add(a7H);
		

		discarted = strat1.selectCardsToDiscard(pbids, 2, pHand);
		assertEquals(discarted.size(), 6);
		assertEquals(discarted.getFirst(), a4S);
		discarted.remove(a4S);
		assertEquals(discarted.getFirst(), a5S);
		discarted.remove(a5S);
		assertEquals(discarted.getFirst(), a5D);
		discarted.remove(a5D);
		assertEquals(discarted.getFirst(), a6D);
		discarted.remove(a6D);
		assertEquals(discarted.getFirst(), a5H);
		discarted.remove(a5H);
		assertEquals(discarted.getFirst(), a7H);
		discarted.remove(a7H);
		
		Hand pHand2 = new Hand();
		pHand2.add(a4C);
		pHand2.add(a5C);
		pHand2.add(a6C);
		pHand2.add(a7C);
		pHand2.add(a8C);
		pHand2.add(a9C);
		pHand2.add(aTC);
		pHand2.add(aQC);
		pHand2.add(aAH);
		pHand2.add(aKH);
		pHand2.add(a4S);
		pHand2.add(a5S);
		pHand2.add(a5H);
		pHand2.add(a5D);
		pHand2.add(a6D);
		pHand2.add(aQH);
		

		discarted = strat1.selectCardsToDiscard(pbids, 2, pHand2);
		assertEquals(discarted.size(), 6);
		assertEquals(discarted.getFirst(), a4S);
		discarted.remove(a4S);
		assertEquals(discarted.getFirst(), a5S);
		discarted.remove(a5S);
		assertEquals(discarted.getFirst(), a5D);
		discarted.remove(a5D);
		assertEquals(discarted.getFirst(), a6D);
		discarted.remove(a6D);
		assertEquals(discarted.getFirst(), a5H);
		discarted.remove(a5H);
		assertEquals(discarted.getFirst(), aQH);
		discarted.remove(aQH);
		
		
	}
}
