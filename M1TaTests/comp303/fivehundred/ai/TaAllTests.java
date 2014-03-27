package comp303.fivehundred.ai;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import comp303.fivehundred.model.*;
import comp303.fivehundred.util.*;
import comp303.fivehundred.ai.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RandomBiddingStrategyTest.class, 
	RandomCardExchangeStrategyTest.class, 
	RandomPlayingStrategyTest.class, 
	TestCardListTa.class, 
	TestBidTa.class,
	TestTrick.class,
	TestByRankComparator.class,
	TestBySuitComparator.class,
	TestBySuitNoTrumpComparator.class,
	TestCardTa.class,
	TestComparatorsTa.class,
	TestDeckTa.class,
	})
public class TaAllTests
{

}
