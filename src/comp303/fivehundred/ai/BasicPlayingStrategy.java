package comp303.fivehundred.ai;

import java.util.Comparator;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.BySuitComparator;
import comp303.fivehundred.util.Card.BySuitNoTrumpComparator;
import comp303.fivehundred.util.Card.Joker;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.CardList;

/**
 * @author Amjad Al-Rikabi
 *
 */
public class BasicPlayingStrategy implements IPlayingStrategy
{
	private boolean aIsPartnerWinning;
	
	/**
	 * Selects a card to be played by the player. This method should have no
	 * side effect, i.e., it should not remove any cards from Hand.
	 * If leading, picks a card at random except jokers if playing in no trump. 
	 * If following, choose the lowest card that can follow suit and win. 
	 * If no card can follow suit and win, picks the lowest card that can follow suit. 
	 * If no card can follow suit, picks the lowest trump card that can win. 
	 * If there are no trump card or the trump cards can’t win (because the trick was already trumped), 
	 * then picks the lowest card. If a joker was led, dump the lowest card 
	 * unless it can be beaten with the high joker according to the rules of the game.
	 * 
	 * @param pTrick Cards played so far in the trick. Note that the 
	 * number of cards in pTrick determines the playing order of the player. For 
	 * example, if pTrick.size() == 0, the player leads. If pTrick.size() == 1, he plays
	 * second, etc.
	 * @param pHand The hand of the player to play.
	 * @return One of the cards in pHand. The card must be a legal play, that is, follow suit
	 * if possible.
	 * 
	 * @pre pTrick != null
	 * @pre pHand != null
	 */
	
	public Card play(Trick pTrick, Hand pHand)
	{
		assert pTrick != null;
		assert pHand != null;
		//GetTrump Suit
		Suit aTrumpSuit = pTrick.getTrumpSuit();

		//CHECK IF WE ARE LEADING:
		if (pTrick.size() == 0)
		{
			return leadingCard(pHand, aTrumpSuit);
		}
		//NOT LEADING: WE ARE FOLLOWING
		else
		{
			return followingSuit(pTrick, pHand);
		}
	}

	/**
	 * If we are following a trick, what card should basic AI play?
	 * @param pTrick
	 * @param pHand
	 * @return Card to play
	 */
	private Card followingSuit(Trick pTrick, Hand pHand)
	{
		//Obtain the leading suit
		Suit aSuitLed = pTrick.getSuitLed();
		//Obtain the trump suit if available
		Suit aTrumpSuit = pTrick.getTrumpSuit();
		//Obtain the highest card so far in the trick
		Card aTrickHighestCard = pTrick.highest();
		//Is PartnerWinning()
		isPartnerWinning(pTrick);
		//Obtain Jokers in the hand if any
		CardList aPresentJokers = pHand.getJokers();
		//Using the correct Comparator for card valuation
		Comparator<Card> aComparator = getSuitableComparator(aTrumpSuit);

		//If joker led the trick, then play the high joker or the lowest card:
		if(pTrick.jokerLed())
		{
			return jokerLedTrick(aPresentJokers, aTrickHighestCard, pHand, aTrumpSuit);
		}

		//Finding which cards can follow suit
		CardList aFollowSuitCards = cardsThatFollowLeadSuit(pHand, aSuitLed, aTrumpSuit);

		//If our hand has a card that can follow suit...
		if(aFollowSuitCards.size() > 0)
		{
			return playFollowingLeadSuit(aTrickHighestCard, aFollowSuitCards, aComparator);
		}
		//Or if our hand has no card that can follow suit:
		else
		{
			return playCannotFollowLeadSuit(pHand, aTrumpSuit, aPresentJokers, aTrickHighestCard,
					aSuitLed, aComparator);
		}
	}
	
	/**
	 * Obtains the suitable Card comparator depending on whether we have a trump suit or not.
	 * @param pTrumpSuit
	 * @return Comparator<Card>
	 */
	private Comparator<Card> getSuitableComparator(Suit pTrumpSuit)
	{
		Comparator<Card> aComparator;
		//Assign the Correct comparator
		if(pTrumpSuit == null)
		{
			aComparator = new BySuitNoTrumpComparator();
		}
		else
		{
			aComparator = new BySuitComparator(pTrumpSuit);
		}
		return aComparator;
	}

	/**
	 * Returns a card to play when no card in our hand can follow lead suit.
	 * @param pHand is our current hand with our cards
	 * @param pTrumpSuit is the trump suit
	 * @param pPresentJokers is the CardList of any present jokers in our hand
	 * @param pTrickHighestCard is the highest card in the trick so far
	 * @param pSuitLed is the leading suit
	 * @param pComparator is the comparator we should be using to arrange the value of the cards
	 * @return Card to play
	 */
	private Card playCannotFollowLeadSuit(Hand pHand, Suit pTrumpSuit, CardList pPresentJokers, Card pTrickHighestCard,
			Suit pSuitLed, Comparator<Card> pComparator)
	{
		//If no trump and we cannot follow suit, return the lowest card:
		if(pTrumpSuit == null)
		{
			return lowestOrJokerCard(pPresentJokers, pHand, pSuitLed);
		}
		//If there is a trump and we cannot follow suit:
		else
		{
			CardList aTrumpCards = pHand.getTrumpCards(pTrumpSuit);
			//If we have trump card(s):
			if(aTrumpCards.size() > 0)
			{
				CardList aTrumpsCanWin = new CardList();
				for(Card aCard : aTrumpCards)
				{
					if(pComparator.compare(aCard, pTrickHighestCard) > 0)
					{
						aTrumpsCanWin.add(aCard);
					}
				}
				//We have a trump that can win:
				if(aTrumpsCanWin.size() > 0 && !aIsPartnerWinning)
				{
					return aTrumpsCanWin.sort(pComparator).getFirst();
				}
				//No trump can win:
				else
				{
					return lowestOrJokerCard(pPresentJokers, pHand, pSuitLed);
				}
			}
			//We don't have a trump card:
			else
			{
				return lowestOrJokerCard(pPresentJokers, pHand, pSuitLed);
			}
		}
	}

	/**
	 * Plays a card if there is a card in the hand that can follow the lead suit.
	 * @param pTrickHighestCard is the highest value card played in the trick.
	 * @param pFollowSuitCards is a CardList with all the cards that can follow the suit
	 * @param pComparator is the comparator used to rank the cards in order of value.
	 * @return card to be played
	 */
	private Card playFollowingLeadSuit(Card pTrickHighestCard, CardList pFollowSuitCards, Comparator<Card> pComparator)
	{
		CardList aCanWin = new CardList();
		CardList aCannotWin = new CardList();

		for(Card aCard : pFollowSuitCards)
		{
			if(pComparator.compare(aCard, pTrickHighestCard) > 0)
			{
				aCanWin.add(aCard);
			}
			else
			{
				aCannotWin.add(aCard);
			}
		}

		if((aCanWin.size() > 0 && !aIsPartnerWinning) || aCannotWin.size() == 0)
		{
			return aCanWin.sort(pComparator).getFirst();
		}
		else
		{
			return aCannotWin.sort(pComparator).getFirst();
		}
	}

	/**
	 * Returns the cardList of the cards that can follow the lead suit.
	 * @param pHand is our current hand
	 * @param pSuitLed
	 * @param pTrumpSuit
	 * @return CardList of cards that can follow the suit
	 */
	private CardList cardsThatFollowLeadSuit(Hand pHand, Suit pSuitLed, Suit pTrumpSuit)
	{
		CardList aFollowSuitCards = new CardList();

		for(Card aCard : pHand)
		{
			//If trumpSuit led the trick
			if((pTrumpSuit == pSuitLed) && (aCard.getEffectiveSuit(pTrumpSuit) == pSuitLed))
			{
				aFollowSuitCards.add(aCard);
			}
			else if(aCard.getSuit() == pSuitLed) 
			{
				aFollowSuitCards.add(aCard);
			}
		}

		return aFollowSuitCards;
	}

	/**
	 * Returns the Lowest Card if no Joker is present.
	 * @param pPresentJokers Cardlist
	 * @param pHand is the current hand for the lowest card
	 * @param pSuitLed is the suit that is leading
	 * @return lowest card or joker
	 */
	private Card lowestOrJokerCard(CardList pPresentJokers, Hand pHand, Suit pSuitLed)
	{
		//Check if we have a Joker:
		if (pPresentJokers.size() > 0 && !aIsPartnerWinning)
		{
			return pPresentJokers.getFirst();
		}
		//Return Lowest
		return pHand.selectLowest(pSuitLed);
	}

	/**
	 * If a Trick is joker led, this method will return a high joker or lowest card.
	 * @param pPresentJokers in the current hand
	 * @param pTrickHighestCard is the highest card in the trick so far
	 * @param pHand is the cards in the hand
	 * @param pTrumpSuit the suit of the trump
	 * @return Card
	 */
	private Card jokerLedTrick(CardList pPresentJokers, Card pTrickHighestCard, Hand pHand, Suit pTrumpSuit)
	{
		if (pPresentJokers.size() > 0 && pTrickHighestCard.getJokerValue().equals(Joker.LOW) && !aIsPartnerWinning)
		{
			return pPresentJokers.getFirst();
		}
		else 
		{
			return pHand.selectLowest(pTrumpSuit);
		}
	}

	/**
	 * Returns a card from the hand when we are leading a trick.
	 * @param pHand is the current Hand.
	 * @param pTrump is the Trump Suit.
	 * @return
	 */
	private Card leadingCard(Hand pHand, Suit pTrump)
	{
		//Check if playing in no trump:
		if (pTrump == null)
		{
			//Pick a random card not including the joker
			return pHand.canLead(true).random();
		}
		else //if playing in trump
		{
			//Pick a random card including the joker
			return pHand.canLead(false).random();
		}
	}
	
	private void isPartnerWinning(Trick pTrick)
	{
		aIsPartnerWinning = (pTrick.winnerIndex() + 2) == pTrick.size();
	}
}
