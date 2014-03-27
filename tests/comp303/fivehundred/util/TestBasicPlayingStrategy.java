package comp303.fivehundred.util;
import static org.junit.Assert.*;
import org.junit.Test;

import comp303.fivehundred.ai.BasicPlayingStrategy;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;
import static comp303.fivehundred.util.AllCards.*;

public class TestBasicPlayingStrategy
{
	BasicPlayingStrategy bps = new BasicPlayingStrategy();
	@Test
	public void TestIsLeading()
	{
		Trick t = new Trick(new Bid(4));
		Hand h = new Hand();	
		h.add(a6H);
		h.add(aLJo);				
		assertEquals(a6H, bps.play(t, h));
		
		t = new Trick(new Bid(2));
		h = new Hand();	
		h.add(a6H);				
		assertEquals(a6H, bps.play(t, h));
	}
	@Test
	public void TestLowestCardThatCanFollowSuitAndWin()
	{
		Trick t = new Trick(new Bid(4));
		t.add(a5C);
		Hand h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aAC);		
		assertEquals(a6C, bps.play(t,h));
	}
	@Test
	public void TestLowestCardThatCanFollowSuit()
	{
		Trick t = new Trick(new Bid(4));
		t.add(a9C);
		Hand h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(a8C);		
		assertEquals(a4C, bps.play(t,h));
	}
	@Test
	public void TestLowestCardIfPartnerWinning()
	{
		Trick t = new Trick(new Bid(4));
		t.add(a9C);
		t.add(a8C);
		Hand h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aTC);		
		assertEquals(a4C, bps.play(t,h));
		
		t = new Trick(new Bid(4));
		t.add(a8C);
		t.add(a9C);
		h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aTC);		
		assertEquals(aTC, bps.play(t,h));
	}
	@Test
	public void TestLowestTrumpThatCanWin()
	{
		Trick t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(a9C);
		Hand h = new Hand();
		h.add(a8D);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7D);		
		h.add(a4D);		
		h.add(a5D);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aTD);		
		assertEquals(a4D, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(a9C);
		h = new Hand();
		h.add(a8S);
		h.add(a6S);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(a7H);
		h.add(aLJo);		
		h.add(aTS);		
		assertEquals(aLJo, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(a9C);
		h = new Hand();
		h.add(a8S);
		h.add(a6S);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(aJD);
		h.add(aLJo);		
		h.add(aJH);
		assertEquals(aJH, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(aJH);
		h = new Hand();
		h.add(a8S);
		h.add(a6S);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(aJD);
		h.add(aLJo);		
		h.add(a8H);
		assertEquals(aJD, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(aLJo);
		h = new Hand();
		h.add(a8S);
		h.add(a6S);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(aJD);
		h.add(aHJo);		
		h.add(a8H);
		assertEquals(aHJo, bps.play(t,h));
	}
	@Test
	public void TestNoTrumpOrSuit()
	{
		Trick t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(a9C);
		Hand h = new Hand();
		h.add(a8S);
		h.add(a6H);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(a7H);
		h.add(a4H);		
		h.add(aTH);		
		assertEquals(a4S, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8C);
		t.add(a9D);
		h = new Hand();
		h.add(a8S);
		h.add(a6H);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5D);		
		h.add(a7H);
		h.add(a4H);		
		h.add(aTH);		
		assertEquals(a4S, bps.play(t,h));
		
		t = new Trick(new Bid(4));
		t.add(a8C);
		t.add(a9C);
		h = new Hand();
		h.add(a8S);
		h.add(a6H);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5D);		
		h.add(a7H);
		h.add(a4H);		
		h.add(aTH);		
		assertEquals(a4S, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(a8D);
		h = new Hand();
		h.add(a8S);
		h.add(a6H);		
		h.add(a6S);		
		h.add(a6H);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5D);		
		h.add(a7H);
		h.add(a4H);		
		h.add(aTH);		
		assertEquals(a5D, bps.play(t,h));
		
		t = new Trick(new Bid(4));
		t.add(a9H);
		h = new Hand();
		h.add(a8S);
		h.add(a8S);		
		h.add(a6S);		
		h.add(a6C);
		h.add(a7S);		
		h.add(a4S);		
		h.add(a5S);		
		h.add(a7S);
		h.add(aLJo);		
		h.add(aTS);		
		assertEquals(aLJo, bps.play(t,h));
	}
	@Test
	public void TestJokerLedTrick()
	{
		Trick t = new Trick(new Bid(4));
		t.add(aLJo);
		Hand h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aHJo);		
		h.add(a8C);		
		assertEquals(aHJo, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(aLJo);
		h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(aHJo);		
		h.add(a8C);		
		assertEquals(aHJo, bps.play(t,h));
		
		t = new Trick(new Bid(2));
		t.add(aLJo);
		h = new Hand();
		h.add(a6C);
		h.add(a6S);		
		h.add(a6D);		
		h.add(a6H);
		h.add(a7C);		
		h.add(a4C);		
		h.add(a5C);		
		h.add(a7H);
		h.add(a4S);		
		h.add(a8C);		
		assertEquals(a4S, bps.play(t,h));
	}
}
