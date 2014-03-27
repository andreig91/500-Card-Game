package comp303.fivehundred.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp303.fivehundred.ai.RandomBiddingStrategyTest;
import comp303.fivehundred.ai.RandomCardExchangeStrategyTest;
import comp303.fivehundred.ai.RandomPlayingStrategyTest;
import comp303.fivehundred.model.TestBidTa;
import comp303.fivehundred.model.TestHand;
import comp303.fivehundred.model.TestTrick;
import comp303.fivehundred.util.TestByRankComparator;
import comp303.fivehundred.util.TestBySuitComparator;
import comp303.fivehundred.util.TestBySuitNoTrumpComparator;
import comp303.fivehundred.util.TestCard;
import comp303.fivehundred.util.TestCardList;
import comp303.fivehundred.util.TestComparators;
import comp303.fivehundred.util.TestDeck;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCard.class, 
	TestByRankComparator.class,
	TestBySuitComparator.class,
	TestBySuitNoTrumpComparator.class,
	TestBidTa.class,
	TestDeck.class,
	TestComparators.class,
	TestTrick.class,
	TestHand.class,
	RandomBiddingStrategyTest.class,
	RandomCardExchangeStrategyTest.class,
	RandomPlayingStrategyTest.class,
	TestCardList.class
	
	})
public class AllTestsTa
{

}
