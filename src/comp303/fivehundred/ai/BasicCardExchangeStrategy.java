package comp303.fivehundred.ai;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Constants;

/**
 * @author Amjad Al-Rikabi
 *
 */
public class BasicCardExchangeStrategy implements ICardExchangeStrategy
{
	
	/**
	 * Discards the 6 lowest non-trump cards from the hand.
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
		assert pBids.length == Constants.NUMBER_OF_PLAYERS;
		assert pIndex >= Constants.LOWEST_PLAYER_INDEX && pIndex <= Constants.HIGHEST_PLAYER_INDEX;
		assert pHand.size() == Constants.MAX_HAND_SIZE;

		CardList aCardsToDiscard = new CardList();
		Hand aHandCopy = pHand.clone();

		for(int i = 0; i < Constants.EXCHANGE_NUMBER_OF_CARDS; i++)
		{
			//Check if NoTrumpBid or IsPass
			//TODO Cannot be pass
			if (pBids[pIndex].isNoTrump() || pBids[pIndex].isPass())
			{
				aCardsToDiscard.add(aHandCopy.selectLowest(null));
				aHandCopy.remove(aHandCopy.selectLowest(null));
			}
			else //NonPassing Bid
			{
				aCardsToDiscard.add(aHandCopy.selectLowest(pBids[pIndex].getSuit()));
				aHandCopy.remove(aHandCopy.selectLowest(pBids[pIndex].getSuit()));
			}
			
		}
		return aCardsToDiscard;
	}
}
