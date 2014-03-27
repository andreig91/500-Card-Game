package comp303.fivehundred.util;


import static org.junit.Assert.*;
import org.junit.Test;


import comp303.fivehundred.ai.BasicCardExchangeStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card.Suit;
import static comp303.fivehundred.util.AllCards.*;

public class TestBasicCardExchangeStrategy
{
	/**
	 * @author Dan
	 */
	
	BasicCardExchangeStrategy bces = new BasicCardExchangeStrategy();
	
	@Test
	public void TestSelectCardsToDiscard()
	{
		Bid aBid1 = new Bid();
		Bid aBid2 = new Bid();
		Bid aBid3 = new Bid();
		Bid aBid4 = new Bid();
		
		Bid[] aBidArr = new Bid[4];
		aBidArr[0] = aBid1;
		aBidArr[1] = aBid2;
		aBidArr[2] = aBid3;
		aBidArr[3] = aBid4;
		
		CardList aCards = new CardList();
		aCards.add(a4C);
		aCards.add(a5S);
		aCards.add(a5C);
		aCards.add(a5D);
		aCards.add(a6S);
		aCards.add(a7C);


		
		Hand h = new Hand();
		h.add(aAC);
		h.add(aKC);		
		h.add(aQC);		
		h.add(aJC);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aJS);
		h.add(a6S);
		h.add(a5D);
		h.add(a5S);
		h.add(a8H);
		h.add(a8D);
		h.add(a9H);
		
		aBidArr[0] = new Bid(6,null);
		CardList disc = bces.selectCardsToDiscard(aBidArr,0,h);
		
		for (int i= 0 ; i <6; i++){
			assertEquals(aCards.getFirst() ,disc.getFirst());
			aCards.remove(aCards.getFirst());
			disc.remove(disc.getFirst());		
		}
				
		aCards.add(a5S);
		aCards.add(a6S);
		aCards.add(a5D);
		aCards.add(a8D);
		aCards.add(a7H);
		aCards.add(a8H);
		
		aBidArr[0] = new Bid(6,Suit.CLUBS);
		
		disc = bces.selectCardsToDiscard(aBidArr,0,h);
		
		for (int i= 0 ; i <6; i++){
			assertEquals(aCards.getFirst() ,disc.getFirst());
			aCards.remove(aCards.getFirst());
			disc.remove(disc.getFirst());		
		}
		

		
	}
}
