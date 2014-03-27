package comp303.fivehundred.ai;


import java.util.ArrayList;
import java.util.Comparator;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.ByRankComparator;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Constants;

/**
 * If your have 10 of the trump, throw the 6 no-trump. 
 * If you have more than 10 trumps, throw all non-trump, then throw the lowest trumps
 * If you have less than 10 trumps, put aside the trumps. Sort the three remaining suits in order of
 * number of cards, then in order of power inside each suit. Do the following until you have thrown 6 cards. 
 * For the smallest suit, keep any ace, ace-king or ace-king-queen, then throw the remaining of that suit.
 * If you cannot throw them all (hitting the limit), throw only the smallest ones and return. 
 * Do the same for the second and third suit. 
 * If you still have not thrown 6 cards, sort the remaining non-trump into power order and throw smallest ones until 6. 
 * Then return.  
 * @author JeanBenoit
 *
 */
public class AdvancedCardExchangeStrategy implements ICardExchangeStrategy
{
	private int aPartnerIndex;
	private int aOpponentOneIndex;
	private int aOpponentTwoIndex;
	
	private int aNumberClubs;
	private int aNumberSpades;
	private int aNumberDiamonds;
	private int aNumberHearts;
	
	
	private Hand aDiscartedCards;

	@Override
	public CardList selectCardsToDiscard(Bid[] pBids, int pIndex, Hand pHand)
	{
		Hand nonTrumps;
		Hand trumps;
		
		CardList temp;
		CardList[] suitCards = new CardList[3];
		
		ArrayList<Suit> nonTrumpsSuits = new ArrayList<Card.Suit>();
		nonTrumpsSuits.add(Suit.HEARTS);
		nonTrumpsSuits.add(Suit.SPADES);
		nonTrumpsSuits.add(Suit.CLUBS);
		nonTrumpsSuits.add(Suit.DIAMONDS);
		nonTrumpsSuits.remove(pBids[pIndex].getSuit());
		
		aPartnerIndex = (pIndex+2)%4;
		aOpponentOneIndex  = (pIndex+1)%4;
		aOpponentTwoIndex  = (pIndex+3)%4;
		
		Suit aPartnerBidSuit = pBids[aPartnerIndex].getSuit();
		Suit aOpponnentOneBidSuit = pBids[aOpponentOneIndex].getSuit();
		Suit aOpponentTwoBidSuit = pBids[aOpponentTwoIndex].getSuit();
		
		aNumberClubs = pHand.numberOfCards(Suit.CLUBS, pBids[pIndex].getSuit());
		aNumberSpades = pHand.numberOfCards(Suit.SPADES, pBids[pIndex].getSuit());
		aNumberDiamonds = pHand.numberOfCards(Suit.DIAMONDS, pBids[pIndex].getSuit());
		aNumberHearts = pHand.numberOfCards(Suit.HEARTS, pBids[pIndex].getSuit());
		
		temp = pHand.getNonTrumpCards(pBids[pIndex].getSuit());
		nonTrumps = convertCardList(temp);
		
		temp = pHand.getTrumpCards(pBids[pIndex].getSuit());
		trumps = convertCardList(temp);
		if(nonTrumps.size() == Constants.EXCHANGE_NUMBER_OF_CARDS) 
			{
			return (CardList)nonTrumps;
			}
		if (nonTrumps.size() < Constants.EXCHANGE_NUMBER_OF_CARDS)
		{
			int cardsRemainingToDiscard = Constants.EXCHANGE_NUMBER_OF_CARDS - nonTrumps.size();
			//Trumps = (Hand)Trumps.sort(new ByRankComparator());
			for(int i = 0; i < cardsRemainingToDiscard; i++)
			{
				nonTrumps.add(trumps.selectLowest(pBids[pIndex].getSuit()));
				trumps.remove(trumps.selectLowest(pBids[pIndex].getSuit()));
			}
			return (CardList)nonTrumps;
		}
		else
		{
			int cardsRemainingToDiscard = Constants.EXCHANGE_NUMBER_OF_CARDS;
			Hand discard = new Hand();
			Hand remain = new Hand();
			for(int i = 0; i < 3; i++)
			{
				suitCards[i] = nonTrumps.playableCards(nonTrumpsSuits.get(i), pBids[pIndex].getSuit());
				if(suitCards[i] != null)
				{
					suitCards[i] = suitCards[i].sort(new ByRankComparator());
				}
			}
			ArrayList<CardList> sortedSuits = sortArray(suitCards);
			//return sortedSuits.get(2);
			for(int i = 0; i < 3; i++)
			{
				if(sortedSuits.get(i).size() > 0 && sortedSuits.get(i).getLast().getRank() == Rank.ACE)
				{
					remain.add(sortedSuits.get(i).getLast());
					sortedSuits.get(i).remove(sortedSuits.get(i).getLast());
					if(sortedSuits.get(i).size() > 0 && sortedSuits.get(i).getLast().getRank() == Rank.KING)
					{
						remain.add(sortedSuits.get(i).getLast());
						sortedSuits.get(i).remove(sortedSuits.get(i).getLast());
						if(sortedSuits.get(i).size() > 0 && sortedSuits.get(i).getLast().getRank() == Rank.QUEEN)
						{
							remain.add(sortedSuits.get(i).getLast());
							sortedSuits.get(i).remove(sortedSuits.get(i).getLast());
						}
					}
				}
				while(sortedSuits.get(i).size() != 0 && cardsRemainingToDiscard > 0)
				{
					discard.add(sortedSuits.get(i).getFirst());
					sortedSuits.get(i).remove(sortedSuits.get(i).getFirst());
					cardsRemainingToDiscard --;
				}	
			}
			if(cardsRemainingToDiscard > 0 )
			{
				temp = remain.sort(new ByRankComparator());
				remain = convertCardList(temp);
				while(remain.size() >0 && cardsRemainingToDiscard > 0)
				{
					discard.add(remain.getFirst());
					remain.remove(remain.getFirst());
					cardsRemainingToDiscard --;
				}
			}
			return discard;
		}
	
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param pCardList
	 * @return Sorted ArrayLis<Cardlist>
	 * Returns a sorted array list of card lists by the size of the card lists 
	 */
	public ArrayList<CardList> sortArray(CardList[] pCardList)
	{
		ArrayList<CardList> sortedArray = new ArrayList<CardList>();
		sortedArray.add(pCardList[0]);
			if(pCardList[1].size() >= sortedArray.get(0).size())
			{
				sortedArray.add(1, pCardList[1]);
			}
			else
			{
				sortedArray.add(0, pCardList[1]);
			}
			if(pCardList[2].size() >= sortedArray.get(0).size() && pCardList[2].size() >= sortedArray.get(1).size() )
			{
				sortedArray.add(2, pCardList[2]);
			}
			if(pCardList[2].size() < sortedArray.get(0).size() && pCardList[2].size() < sortedArray.get(1).size() )
			{
				sortedArray.add(0, pCardList[2]);
			}
			if(pCardList[2].size() >= sortedArray.get(0).size() && pCardList[2].size() < sortedArray.get(1).size() )
			{
				sortedArray.add(1, pCardList[2]);
			}
			
		return sortedArray;
	}
	
	/**
	 * 
	 * @param pCards
	 * @return Hand
	 * Converts a CardLisi into a Hand.
	 */
	public  Hand convertCardList(CardList pCards)
	{
		Hand aHand = new Hand();
		for (Card c : pCards)
		{
		     aHand.add(c);
		}
		return aHand;
	}
}
 
