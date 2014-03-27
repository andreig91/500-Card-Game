package comp303.fivehundred.util;

import static comp303.fivehundred.util.AllCardsTa.a4C;
import static comp303.fivehundred.util.AllCardsTa.a5C;
import static comp303.fivehundred.util.AllCardsTa.a5D;
import static comp303.fivehundred.util.AllCardsTa.a6C;
import static comp303.fivehundred.util.AllCardsTa.a6D;
import static comp303.fivehundred.util.AllCardsTa.a6H;
import static comp303.fivehundred.util.AllCardsTa.a6S;
import static comp303.fivehundred.util.AllCardsTa.a7C;
import static comp303.fivehundred.util.AllCardsTa.a7D;
import static comp303.fivehundred.util.AllCardsTa.a7H;
import static comp303.fivehundred.util.AllCardsTa.aAC;
import static comp303.fivehundred.util.AllCardsTa.aAH;
import static comp303.fivehundred.util.AllCardsTa.aAS;
import static comp303.fivehundred.util.AllCardsTa.aHJo;
import static comp303.fivehundred.util.AllCardsTa.aJC;
import static comp303.fivehundred.util.AllCardsTa.aJD;
import static comp303.fivehundred.util.AllCardsTa.aJS;
import static comp303.fivehundred.util.AllCardsTa.aKC;
import static comp303.fivehundred.util.AllCardsTa.aKD;
import static comp303.fivehundred.util.AllCardsTa.aKH;
import static comp303.fivehundred.util.AllCardsTa.aKS;
import static comp303.fivehundred.util.AllCardsTa.aLJo;
import static comp303.fivehundred.util.AllCardsTa.aQC;
import static comp303.fivehundred.util.AllCardsTa.aQH;
import static comp303.fivehundred.util.AllCardsTa.aQS;
import static comp303.fivehundred.util.AllCardsTa.aTD;
import static comp303.fivehundred.util.AllCardsTa.aTS;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import comp303.fivehundred.util.Card.ByRankComparator;
import comp303.fivehundred.util.Card.BySuitComparator;
import comp303.fivehundred.util.Card.BySuitNoTrumpComparator;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.model.Trick.TrumpComparatorTrick;
import comp303.fivehundred.model.Trick.NoTrumpComparatorTrick;

/**
 * 
 * @author Andrei
 *
 */
public class TestComparators
{
	@Test
	public void testByRankComparator()
	{
		ByRankComparator rank = new ByRankComparator();
		assertTrue(rank.compare(aKS, aQS) > 0);
		assertTrue(rank.compare(aKC, aKS) > 0);
		assertTrue(rank.compare(aKD, aKC) > 0);
		assertTrue(rank.compare(aKH, aKD) > 0);
		assertTrue(rank.compare(aJS, aTS) > 0);	
		assertTrue(rank.compare(aHJo, aLJo) > 0);
		assertTrue(rank.compare(aLJo, aQS) > 0);
		assertTrue(rank.compare(aKS, aKS) == 0);
		assertTrue(rank.compare(aLJo, aLJo) == 0);
		assertTrue(rank.compare(aHJo, aHJo) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(aKS, aAS) < 0);
		assertTrue(rank.compare(aKS, aKC) < 0);
		assertTrue(rank.compare(aKC, aKD) < 0);
		assertTrue(rank.compare(aKD, aKH) < 0);
		assertTrue(rank.compare(aLJo, aHJo) < 0);
		assertTrue(rank.compare(aKS, aLJo) < 0);
		assertTrue(rank.compare(aKS, aHJo) < 0);
		assertTrue(rank.compare(a6S, aKS) < 0);
		assertTrue(rank.compare(aJS, aQS) < 0);
	}
	@Test
	public void testBySuitNoTrumpComparator()
	{
		BySuitNoTrumpComparator rank = new BySuitNoTrumpComparator();
		assertTrue(rank.compare(a4C, aQS) > 0);
		assertTrue(rank.compare(a5D, aKC) > 0);
		assertTrue(rank.compare(a6D, aAS) > 0);
		assertTrue(rank.compare(a7H, aKS) > 0);
		assertTrue(rank.compare(aHJo, aLJo) > 0);
		assertTrue(rank.compare(aLJo, aAH) > 0);
		assertTrue(rank.compare(a6H, aAC) > 0);
		assertTrue(rank.compare(a7H, aKD) > 0);
		assertTrue(rank.compare(aHJo, aKH) > 0);
		assertTrue(rank.compare(aJD, aTD) > 0);
		assertTrue(rank.compare(aKH, a6H) > 0);
		assertTrue(rank.compare(aKS, aKS) == 0);
		assertTrue(rank.compare(aLJo, aLJo) == 0);
		assertTrue(rank.compare(aHJo, aHJo) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(aJS, aQS) < 0);
		assertTrue(rank.compare(aKS, a6C) < 0);
		assertTrue(rank.compare(aKC, a7D) < 0);
		assertTrue(rank.compare(aJS, a6D) < 0);
		assertTrue(rank.compare(aLJo, aHJo) < 0);
		assertTrue(rank.compare(aKS, aLJo) < 0);
		assertTrue(rank.compare(aQH, aHJo) < 0);
		assertTrue(rank.compare(aAS, a6H) < 0);
		assertTrue(rank.compare(aQC, a6H) < 0);
		assertTrue(rank.compare(aKD, a6H) < 0);
	}
	@Test
	public void testBySuitComparator()
	{
		BySuitComparator rank = new BySuitComparator(Suit.CLUBS);
		assertTrue(rank.compare(a4C, aQS) > 0);
		assertTrue(rank.compare(a5C, aKD) > 0);
		assertTrue(rank.compare(a6C, aAH) > 0);
		assertTrue(rank.compare(a7H, aKS) > 0);
		assertTrue(rank.compare(aHJo, aLJo) > 0);
		assertTrue(rank.compare(aLJo, aAH) > 0);
		assertTrue(rank.compare(a6D, aAS) > 0);
		assertTrue(rank.compare(a7H, aKD) > 0);
		assertTrue(rank.compare(aHJo, aKH) > 0);
		assertTrue(rank.compare(aJC, aAC) > 0);
		assertTrue(rank.compare(aJC, aAH) > 0);
		assertTrue(rank.compare(aJC, aJS) > 0);
		assertTrue(rank.compare(aJS, aAH) > 0);
		assertTrue(rank.compare(aJS, aAS) > 0);
		assertTrue(rank.compare(aKS, aKS) == 0);
		assertTrue(rank.compare(aLJo, aLJo) == 0);
		assertTrue(rank.compare(aHJo, aHJo) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(aJC, aJC) == 0);
		assertTrue(rank.compare(aJS, aJS) == 0);
		assertTrue(rank.compare(aJS, aJC) < 0);
		assertTrue(rank.compare(aKS, a6C) < 0);
		assertTrue(rank.compare(aKD, a7C) < 0);
		assertTrue(rank.compare(a6D, aJS) < 0);
		assertTrue(rank.compare(aLJo, aHJo) < 0);
		assertTrue(rank.compare(aJS, aLJo) < 0);
		assertTrue(rank.compare(aJC, aHJo) < 0);
		assertTrue(rank.compare(aAH, a6C) < 0);
		assertTrue(rank.compare(aAH, aJS) < 0);
		assertTrue(rank.compare(aAH, aJC) < 0);
		assertTrue(rank.compare(aAS, aJS) < 0);
		assertTrue(rank.compare(aAC, aJS) < 0);
		assertTrue(rank.compare(aKC, aAC) < 0);
	}
	/**
	 * @author Amjad
	 */
	@Test
	public void testNoTrumpComparator()
	{
		NoTrumpComparatorTrick rank = new NoTrumpComparatorTrick(Suit.CLUBS);
		assertTrue(rank.compare(a4C, aQS) > 0);
		assertTrue(rank.compare(a5C, aKD) > 0);
		assertTrue(rank.compare(a6C, aAH) > 0);
		assertTrue(rank.compare(a7H, aKS) == 0);
		assertTrue(rank.compare(aHJo, aLJo) > 0);
		assertTrue(rank.compare(aLJo, aAH) > 0);
		assertTrue(rank.compare(a6D, aAS) == 0);
		assertTrue(rank.compare(a7H, aKD) == 0);
		assertTrue(rank.compare(aHJo, aKH) > 0);
		assertTrue(rank.compare(aJC, aAC) < 0);
		assertTrue(rank.compare(aJC, aAH) > 0);
		assertTrue(rank.compare(aJC, aJS) > 0);
		assertTrue(rank.compare(aJS, aAH) == 0);
		assertTrue(rank.compare(aJS, aAS) == 0);
		assertTrue(rank.compare(aKS, aKS) == 0);
		assertTrue(rank.compare(aLJo, aLJo) == 0);
		assertTrue(rank.compare(aHJo, aHJo) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(aJC, aJC) == 0);
		assertTrue(rank.compare(aJS, aJS) == 0);
		assertTrue(rank.compare(aJS, aJC) < 0);
		assertTrue(rank.compare(aKS, a6C) < 0);
		assertTrue(rank.compare(aKD, a7C) < 0);
		assertTrue(rank.compare(a6D, aJS) == 0);
		assertTrue(rank.compare(aLJo, aHJo) < 0);
		assertTrue(rank.compare(aJS, aLJo) < 0);
		assertTrue(rank.compare(aJC, aHJo) < 0);
		assertTrue(rank.compare(aAH, a6C) < 0);
		assertTrue(rank.compare(aAH, aJS) == 0);
		assertTrue(rank.compare(aAH, aJC) < 0);
		assertTrue(rank.compare(aAS, aJS) == 0);
		assertTrue(rank.compare(aAC, aJS) > 0);
		assertTrue(rank.compare(aKC, aAC) < 0);
	}
	/**
	 * @author Amjad
	 */
	@Test
	public void testTrumpComparator()
	{
		TrumpComparatorTrick rank = new TrumpComparatorTrick(Suit.HEARTS, Suit.SPADES);
		assertTrue(rank.compare(aKS, aJD) < 0);
		assertTrue(rank.compare(aKS, aQS) > 0);
		assertTrue(rank.compare(aKS, aKC) > 0);
		assertTrue(rank.compare(aKS, aJD) < 0);
		assertTrue(rank.compare(aKH, aAC) > 0);
		assertTrue(rank.compare(aJS, aTS) > 0);	
		assertTrue(rank.compare(aHJo, aLJo) > 0);
		assertTrue(rank.compare(aLJo, aQS) > 0);
		assertTrue(rank.compare(aKS, aKS) == 0);
		assertTrue(rank.compare(aKH, aKH) == 0);
		assertTrue(rank.compare(aLJo, aLJo) == 0);
		assertTrue(rank.compare(aHJo, aHJo) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(a6H, aLJo) < 0);
		assertTrue(rank.compare(a6D, a6D) == 0);
		assertTrue(rank.compare(a6H, a6H) == 0);
		assertTrue(rank.compare(aKS, aAS) < 0);
		assertTrue(rank.compare(aKS, aAC) > 0);
		assertTrue(rank.compare(aKC, aKD) == 0);
		assertTrue(rank.compare(aKD, aKH) < 0);
		assertTrue(rank.compare(aLJo, aHJo) < 0);
		assertTrue(rank.compare(aKS, aLJo) < 0);
		assertTrue(rank.compare(aKS, aHJo) < 0);
		assertTrue(rank.compare(a6S, aKS) < 0);
		
	}
}
