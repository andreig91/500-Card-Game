package comp303.fivehundred.ai;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.CardList;

/**
 * @author Amjad Al-Rikabi
 * This class is responsible for selecting six cards randomly from a player's 
 * hand.
 */
public class RandomCardExchangeStrategy implements ICardExchangeStrategy
{
	//Declare Game Parameters
	private final int aNumberOfBids = 4;
	private final int aTotalNumberOfPlayers = 4;
	private final int aLowestPlayerIndex = 0;
	private final int aHighestPlayerIndex = aTotalNumberOfPlayers - 1;
	private final int aHandSize = 16;
	private final int aNumberOfCardsToDiscard = 6;

	/**
	 * Assert that we have 4 bids.
	 * @param pBids is an array of the bids announced.
	 * @throws AIException if we do not have 4 bids.
	 */
	private void assertNumberOfBids(Bid[] pBids)
	{
		if( pBids.length != aNumberOfBids )
		{
			System.err.println(pBids.length);
			throw new AIException("We do not have 4 Bids!");
		}
	}
	
	/**
	 * Assert that we have 16 cards in pHand.
	 * @param pHand The hand of cards for this player, with the widow added in.
	 * @throws AIException if we do not have 16 cards in pHand.
	 */
	private void assertNumberOfCards(Hand pHand)
	{
		if( pHand.size() != aHandSize )
		{
			throw new AIException("We do not have 16 Cards in the Hand!");
		}
	}
	
	/**
	 * Assert that we have at least one non-passing Bid.
	 * @param pBids The bids for this round. An array of 4 elements.
	 * @throws AIException if we do not at least one passing bid.
	 */
	private void assertAtLeastOneNonPassingBid(final Bid[] pBids)
	{
		boolean aFoundANonPassingBid = false;

		for(int i = 0; i<aNumberOfBids; i++)
		{
			//Accumulate if we have found a Non-Passing Bid
			aFoundANonPassingBid = aFoundANonPassingBid || !(pBids[i].isPass());
		}
		//Raise Exception if we have not found a non passing bid
		if(!aFoundANonPassingBid)
		{
			throw new AIException("We do not have at least one non-passing Bid!");
		}
	}

	/**
	 * Assert that we have a valid Player Index.
	 * @param pIndex The index of the player in this round. Between 0 and 3 incl.
	 * @throws AIException if we do not have a valid player index.
	 */
	private void assertValidPlayerIndex(int pIndex)
	{
		if( (pIndex<aLowestPlayerIndex) || (pIndex>aHighestPlayerIndex) )
		{
			throw new AIException("We do not have a valid Player index!");
		}
	}


	/**
	 * Select exactly 6 cards from the 16 cards in pHand (basic deal plus widow),
	 * to remove from the hand.
	 * @param pBids The bids for this round. An array of 4 elements.
	 * @param pIndex The index of the player in this round. Between 0 and 3 incl.
	 * @param pHand The hand of cards for this player, with the widow added in.
	 * @return Six cards to remove from the hand.
	 * @pre A least one bid in pBids is non-passing.
	 * @pre pBids.length == 4
	 * @pre pIndex >= 0 && pIndex <= 3
	 * @pre pHand.size() == 16
	 */
	
	public CardList selectCardsToDiscard(Bid[] pBids, int pIndex, Hand pHand)
	{
		CardList aDiscardedCards = new CardList();
		
		//Assert Preconditions:
		assertNumberOfBids(pBids);
		assertAtLeastOneNonPassingBid(pBids);
		assertNumberOfCards(pHand);
		assertValidPlayerIndex(pIndex);
		
		//Clone Hand
		Hand aHand = pHand.clone();
		
		//Select randomly 6 cards:
		for(int i = 0; i < aNumberOfCardsToDiscard; i++) 
		{
			//Add a randomly selected card to the discarded card list
			aDiscardedCards.add(aHand.random());
			aHand.remove(aDiscardedCards.getLast());
		}
		
		//Return aDiscardedCards
		return aDiscardedCards;
	}

}
