package comp303.fivehundred.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp303.fivehundred.util.TestBasicCardExchangeStrategy;
import comp303.fivehundred.util.TestBasicPlayingStrategy;
import comp303.fivehundred.util.TestBid;
import comp303.fivehundred.util.TestCardTa;
import comp303.fivehundred.util.TestCardListTa;
import comp303.fivehundred.util.TestComparatorsTa;
import comp303.fivehundred.util.TestDeckTa;
import comp303.fivehundred.util.TestGameEngine;
import comp303.fivehundred.util.TestPlayer;
import comp303.fivehundred.util.TestRandomCardExchangeStrategy;
import comp303.fivehundred.util.TestRandomBiddingStrategy;
import comp303.fivehundred.util.TestRandomPlayingStrategy;
import comp303.fivehundred.util.TestTricks;
import comp303.fivehundred.util.TestHand;
import comp303.fivehundred.util.TestBasicBiddingStrategy;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCardTa.class, 
	TestDeckTa.class,
	TestComparatorsTa.class,
	TestCardListTa.class,
	TestBid.class,
	TestRandomCardExchangeStrategy.class,
	TestRandomBiddingStrategy.class,
	TestRandomPlayingStrategy.class,
	TestTricks.class,
	TestHand.class,
	TestBasicBiddingStrategy.class,
	TestBasicCardExchangeStrategy.class,
	TestGameEngine.class,
	TestBasicPlayingStrategy.class,
	TestPlayer.class,
	})
public class AllTests
{

}
