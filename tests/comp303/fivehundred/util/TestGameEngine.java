package comp303.fivehundred.util;
import comp303.fivehundred.ai.BasicRobot;
import comp303.fivehundred.ai.RandomRobot;
import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.model.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * @author Andrei
 */
public class TestGameEngine
{
	private Player ra = new Player(new RandomRobot(), "ra");
	private Player rb = new Player(new RandomRobot(), "rb");
	private Player rc = new Player(new RandomRobot(), "rc");
	private Player rd = new Player(new RandomRobot(), "rd");
	private Player ba = new Player(new BasicRobot(), "ba");
	private Player bb = new Player(new BasicRobot(), "bb");
	private Player bc = new Player(new BasicRobot(), "bc");
	private Player bd = new Player(new BasicRobot(), "bd");
	private GameEngine g = new GameEngine(ra, rb, rc, rd);
	
	@Before
	public void setUp()
	{
		g = new GameEngine(ra, rb, rc, rd);
	}
	
	@Test
	public void TestGetHumanIndex()
	{
		GameEngine gWithHuman1 = new GameEngine(ra, rb, new Player(null, "human"), rd);
		GameEngine gWithHuman2 = new GameEngine(new Player(null, "human"), ra, rb, rd);
		assertTrue(gWithHuman1.getHumanPlayerIndex() == 2);
		assertTrue(gWithHuman2.getHumanPlayerIndex() == 0);
	}
	
	@Test
	public void TestInitializeGame()
	{
		g.initializeGame(); // no specific thing to test resolves in simply testing that it executes without errors.
	}
	
	@Test
	public void TestDeal()
	{
		g.deal();
		for(int i = 0; i < 4; i++)
		{
			assertEquals(10, g.getHands()[i].size());
			assertFalse(g.getHands()[0] == g.getHands()[1]);
		}
	}
	
	@Test
	public void TestBid()
	{
		g.deal();
		g.bidBeforeHuman();
		assertEquals(4, g.getBids().length);
	}
	
	@Test
	public void TestAllPasses()
	{
		for(int i = 0; i < 100; i++)
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
			Bid[] aBids = g.getBids();
			if(g.allPasses())
			{
				for(Bid aBid : aBids)
				{
					assertEquals(new Bid(), aBid);
				}
			}
			else
			{
				assertTrue(Bid.max(aBids).toIndex() >= 0 && Bid.max(aBids).toIndex() < 25);
			}
		}
	}
	
	@Test
	public void TestDetermineWinningBid()
	{
		do
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
		}
		while(g.allPasses());
		g.determineWinningBid();
		Bid[] bids = g.getBids();
		
		//From here, part of the code is copied from GameEngine, as BidWinnerIndex was to be modified in GameEngine
		// (can<t be gotten and used, because it does not represent the index in bids[] anymore). 
		Bid aWinningBid = Bid.max(bids);
		int aBidWinnerIndex = -1;
		for(int i = 0; i < 4; i++)
		{
			if(bids[i] == aWinningBid)
			{
				aBidWinnerIndex = i;
			}
		}
		assertTrue(bids[3].isPass() || aBidWinnerIndex == 3);
	}
	
	@Test
	public void TestExchange()
	{
		do
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
		}
		while(g.allPasses());
		g.determineWinningBid();
		g.exchange();
		for(int i = 0; i < 4; i++)
		{
			assertEquals(10, g.getHands()[i].size());
		}
	}
	
	@Test
	public void TestPlayCard()
	{
		do
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
		}
		while(g.allPasses());
		g.determineWinningBid();
		g.exchange();
		for(int i = 0; i < 10; i++)
		{
			g.playTrickBeforeHuman();
			for(int j = 0; j < 4; j++)
			{
				assertEquals(9-i, g.getHands()[j].size());
			}
		}
	}
	
	@Test
	public void TestDetermineTrickWinner()
	{
		do
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
		}
		while(g.allPasses());
		g.determineWinningBid();
		g.exchange();
		g.playTrickBeforeHuman();
	}
	
	@Test
	public void TestComputeScore()
	{
			g = new GameEngine(ba, rb, bc, rd);
			g.initializeGame();
			while( !g.isGameOver() )
			{
				g.initializeRound();
				g.deal();
				g.bidBeforeHuman();
				while( g.allPasses() )
				{
					g.deal();
					g.bidBeforeHuman();
				}
				g.determineWinningBid();
				g.exchange();
				for( int lIndex = 0; lIndex < Constants.HAND_SIZE; lIndex++ )
				{
					g.playTrickBeforeHuman();
				}
				g.computeScore();
			}
			
			g = new GameEngine(ra, bb, rc, bd);
			g.initializeGame();
			while( !g.isGameOver() )
			{
				g.initializeRound();
				g.deal();
				g.bidBeforeHuman();
				while( g.allPasses() )
				{
					g.deal();
					g.bidBeforeHuman();
				}
				g.determineWinningBid();
				g.exchange();
				for( int lIndex = 0; lIndex < Constants.HAND_SIZE; lIndex++ )
				{
					g.playTrickBeforeHuman();
				}
				g.computeScore();
			}
	}
	
	@Test
	public void TestIsGameOver()
	{
		
		do
		{
			g = new GameEngine(ra, rb, rc, rd);
			g.deal();
			g.bidBeforeHuman();
		}
		while(g.allPasses());
		g.determineWinningBid();
		g.exchange();
		for(int i = 0; i < Constants.HAND_SIZE; i++)
		{
			g.playTrickBeforeHuman();
		}
		g.computeScore();
		g.isGameOver();
	}
}
