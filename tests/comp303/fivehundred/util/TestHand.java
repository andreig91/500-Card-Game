package comp303.fivehundred.util;

import static comp303.fivehundred.util.AllCardsTa.*;
import static org.junit.Assert.*;

import org.junit.Test;

import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card.Suit;

/**
 * 
 * @author Dan
 *
 */

public class TestHand
{
	@Test
	public void TestCLone()
	{
		Hand aHand = new Hand();
		Hand aHand2 = aHand.clone();
		assertTrue(aHand != aHand2);
		assertFalse(aHand == aHand2);			
	}
	
	@Test
	public void TestcanLead()
	{
		Hand aHand = new Hand();
		
		aHand.add(aHJo);
		
		assertEquals(aHJo,aHand.canLead(true).getFirst());
		
		aHand.add(aJS);
		
		assertEquals(aJS,aHand.canLead(true).getFirst());
		//assertEquals(aLJo,aHand.aCardList.get(1));
		
		aHand.remove(aJS);
		aHand.remove(aHJo);
		
		aHand.add(aJS);
		
		assertEquals(aJS,aHand.canLead(true).getFirst());
		
		aHand.add(aHJo);
		
		assertEquals(aJS,aHand.canLead(true).getFirst());
		//assertEquals(aLJo,aHand.aCardList.get(1));
		
		aHand.remove(aJS);
		aHand.remove(aHJo);
		
		aHand.add(aLJo);
		
		assertEquals(aLJo,aHand.canLead(true).getFirst());
		
		aHand.add(aHJo);
		
		assertEquals(aLJo,aHand.canLead(true).getFirst());
		assertEquals(aHJo,aHand.getLast());
		
		
		
		
		
		aHand.add(aKS);
		aHand.add(aHJo);
		
		assertEquals(3,aHand.size());
		assertEquals(3,aHand.size());
		assertEquals(aKS,aHand.canLead(true).getFirst() );
		
		assertEquals(aLJo,aHand.canLead(false).getFirst() );
		assertEquals(1, aHand.canLead(true).size());	
	}
	
	@Test
	public void TestgetJokers()
	{
		Hand aHand = new Hand();
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(aLJo,aHand.getJokers().getFirst());
		assertEquals(aHJo,aHand.getJokers().getLast());
		
		aHand.add(aKS);
		
		assertEquals(2,aHand.getJokers().size());
		
		aHand.remove(aLJo);
		aHand.remove(aHJo);
		
		assertEquals(0,aHand.getJokers().size());
	}
	
	@Test
	public void TestgetNonJokers()
	{
		Hand aHand = new Hand();
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(0,aHand.getNonJokers().size());
		
		aHand.add(aKS);
		
		assertEquals(aKS,aHand.getNonJokers().getFirst());
		assertEquals(1,aHand.getNonJokers().size());
		
		aHand.add(aTC);
		CardList result = aHand.getNonJokers();
		assertEquals(2,result.size());
		assertEquals(aKS,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aTC,result.getFirst());
	}
	
	@Test
	public void TestgetTrumpCards()
	{
		Hand aHand = new Hand();
		
		aHand.add(aKC);
		
		assertEquals(0,aHand.getTrumpCards(Suit.SPADES).size());
		
		aHand.add(aKS);
		
		assertEquals(aKS,aHand.getTrumpCards(Suit.SPADES).getFirst());
		assertEquals(1,aHand.getTrumpCards(Suit.SPADES).size());
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(aKS,aHand.getTrumpCards(Suit.SPADES).getFirst());
		assertEquals(aHJo,aHand.getTrumpCards(Suit.SPADES).getLast());
		assertEquals(3,aHand.getTrumpCards(Suit.SPADES).size());
		
		aHand.add(aJS);
		aHand.add(aJC);
		aHand.add(aJH);
		
		CardList result = aHand.getTrumpCards(Suit.SPADES);
		assertEquals(5,result.size());
		assertEquals(aKS,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aLJo,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aHJo,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aJS,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aJC,result.getFirst());
	}
	
	@Test
	public void TestgetNonTrumpCards()
	{
		Hand aHand = new Hand();
		
		aHand.add(aKC);
		
		assertEquals(1,aHand.getNonTrumpCards(Suit.SPADES).size());
		
		aHand.add(aKS);
		
		assertEquals(1,aHand.getNonTrumpCards(Suit.SPADES).size());
		assertEquals(aKC,aHand.getNonTrumpCards(Suit.SPADES).getFirst());
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(aKC,aHand.getNonTrumpCards(Suit.SPADES).getFirst());
		
		aHand.add(a4D);
		aHand.add(aJS);
		aHand.add(aJC);
		aHand.add(aJH);
		
		CardList result = aHand.getNonTrumpCards(Suit.SPADES);
		assertEquals(aKC,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(a4D,result.getFirst());
		result.remove(result.getFirst());
		assertEquals(aJH,result.getFirst());
		assertEquals(1,result.size());	
	}
	@Test
	public void TestselectLowest()
	{
		Hand aHand = new Hand();
		aHand.add(aHJo);
		
		//assertEquals(aHJo,aHand.selectLowest(Suit.SPADES));
		
		aHand.add(aJS);
		
		assertEquals(aJS,aHand.selectLowest(Suit.SPADES));
		//assertEquals(aLJo,aHand.aCardList.get(1));
		
		aHand.remove(aJS);
		aHand.remove(aHJo);
		
		aHand.add(aJS);
		
		assertEquals(aJS,aHand.selectLowest(Suit.SPADES));
		
		aHand.add(aHJo);
		
		assertEquals(aJS,aHand.selectLowest(Suit.SPADES));
		//assertEquals(aLJo,aHand.aCardList.get(1));
		
		aHand.remove(aJS);
		aHand.remove(aHJo);
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(aLJo,aHand.selectLowest(Suit.SPADES));
		assertEquals(aLJo,aHand.selectLowest(null));
		
		aHand.add(aHJo);
		aHand.add(aLJo);
		
		assertEquals(aLJo,aHand.selectLowest(Suit.SPADES));
		assertEquals(aLJo,aHand.selectLowest(null));
		
		aHand.add(aKS);
		
		assertEquals(aKS,aHand.selectLowest(Suit.SPADES));
		assertEquals(aKS,aHand.selectLowest(null));
		
		aHand.add(aJS);
		
		assertEquals(aJS,aHand.selectLowest(Suit.SPADES));
		assertEquals(aJS,aHand.selectLowest(null));
		
		aHand.add(aJC);
		
		assertEquals(aJS,aHand.selectLowest(Suit.SPADES));
		assertEquals(aJS,aHand.selectLowest(null));
		
		aHand.add(aJD);
		
		assertEquals(aJD,aHand.selectLowest(Suit.SPADES));
		assertEquals(aJS ,aHand.selectLowest(null));
		
		aHand.add(aQH);
		aHand.add(a4H);
		aHand.add(a4D);
		
		assertEquals(a4D,aHand.selectLowest(Suit.SPADES));
		assertEquals(a4D ,aHand.selectLowest(null));		
	}
	
	@Test
	public void TestplayableCards()
	{
		Hand aHand = new Hand();
		
		aHand.add(a6H);
		
		assertEquals(a6H,aHand.playableCards(Suit.HEARTS, Suit.SPADES).getFirst());
		assertEquals(a6H,aHand.playableCards(Suit.HEARTS, null).getFirst());
		
		aHand.add(a7H);
		
		assertEquals(a6H,aHand.playableCards(Suit.HEARTS, Suit.SPADES).getFirst());
		assertEquals(a7H,aHand.playableCards(Suit.HEARTS, Suit.SPADES).getLast());
		assertEquals(a6H,aHand.playableCards(Suit.HEARTS, null).getFirst());
		
		aHand.add(aKS);
		
		assertEquals(2,aHand.playableCards(Suit.HEARTS, Suit.SPADES).size());
		assertEquals(2,aHand.playableCards(Suit.HEARTS, null).size());
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(2,aHand.playableCards(Suit.HEARTS, Suit.SPADES).size());
		assertEquals(2,aHand.playableCards(Suit.HEARTS, null).size()); 
		// What happens to the jokers when there is no trump?
		
		assertEquals(3,aHand.playableCards(Suit.SPADES, Suit.SPADES).size());
		assertEquals(1,aHand.playableCards(Suit.SPADES, null).size());
		
		
		assertEquals(5,aHand.playableCards(Suit.DIAMONDS, Suit.SPADES).size());
		assertEquals(5,aHand.playableCards(Suit.DIAMONDS, null).size());
		
	}
	
	@Test
	public void TestnumberOfCards()
	{
		Hand aHand = new Hand();
		
		aHand.add(a6H);
		
		assertEquals(1,aHand.numberOfCards(Suit.HEARTS, Suit.SPADES));
		assertEquals(1,aHand.numberOfCards(Suit.HEARTS, null));
		
		aHand.add(a7H);
		
		assertEquals(2,aHand.numberOfCards(Suit.HEARTS, Suit.SPADES));
		assertEquals(2,aHand.numberOfCards(Suit.HEARTS, null));
		
		aHand.add(aKS);
		
		assertEquals(2,aHand.numberOfCards(Suit.HEARTS, Suit.SPADES));
		assertEquals(2,aHand.numberOfCards(Suit.HEARTS, null));
		
		assertEquals(1,aHand.numberOfCards(Suit.SPADES, Suit.SPADES));
		assertEquals(1,aHand.numberOfCards(Suit.SPADES, null));
		
		aHand.add(aLJo);
		aHand.add(aHJo);
		
		assertEquals(1,aHand.numberOfCards(Suit.SPADES, null));
		
		aHand.add(aJS);
		aHand.add(aJC);
		aHand.add(aJH);
		
		assertEquals(2,aHand.numberOfCards(Suit.SPADES, null));
		
	}
}
