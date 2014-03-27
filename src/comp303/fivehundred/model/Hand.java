package comp303.fivehundred.model;

import java.util.Iterator;

import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Card.BySuitComparator;
import comp303.fivehundred.util.Card.ByRankComparator;

/**
 * Additional services to manage a card list that corresponds to
 * the cards in a player's hand.
 * @author Dan Vatnik
 */
public class Hand extends CardList
{
	/**
	 * @see java.lang.Object#clone()
	 * {@inheritDoc}
	 */
	@Override
	public Hand clone()
	{
		Hand aHand = new Hand();
		aHand = (Hand) super.clone();
		return aHand;
	}
	
	/**
	 * @param pNoTrump If the contract is in no-trump
	 * @return A list of cards that can be used to lead a trick.
	 */
	public CardList canLead(boolean pNoTrump)
	{
		CardList aHand = new CardList();
		if(size() == 1)
		{
			aHand.add(getFirst());
		}
		else if(size() == 2 && getFirst().isJoker() && getLast().isJoker())
		{
			aHand.add(getFirst());
			aHand.add(getLast());
		}
		else if(pNoTrump)
		{
			Iterator<Card> aIterator = iterator();
			while(aIterator.hasNext())
			{
				Card aCard = aIterator.next();
				if(!aCard.isJoker())
				{
					aHand.add(aCard);
				}
			}
		}
		else
		{
			aHand = clone();
		}
		return aHand;
	}
	
	/**
	 * @return The cards that are jokers.
	 */
	public CardList getJokers()
	{
		CardList aHand = new CardList();
		Iterator<Card> aIterator = iterator();
		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			if(aCard.isJoker())
			{
				aHand.add(aCard);
			}
		}
		return aHand;
	}
	
	/**
	 * @return The cards that are not jokers.
	 */
	public CardList getNonJokers()
	{
		CardList aHand = new CardList();
		Iterator<Card> aIterator = iterator();
		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			if(!aCard.isJoker())
			{
				aHand.add(aCard);
			}
		}
		return aHand;
	}
	
	/**
	 * Returns all the trump cards in the hand, including jokers.
	 * Takes jack swaps into account.
	 * @param pTrump The trump to check for. Cannot be null.
	 * @return All the trump cards and jokers.
	 * @pre pTrump != null
	 */
	public CardList getTrumpCards(Suit pTrump)
	{
		assert pTrump != null;
		CardList aHand = new CardList();
		Iterator<Card> aIterator = iterator();
		
		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			
			if( aCard.isJoker() )
			{
				aHand.add(aCard);
			}
			
			else if( aCard.getEffectiveSuit(pTrump) == pTrump )
			{
				aHand.add(aCard);
			}
		}
		return aHand;
	}
	
	/**
	 * Returns all the cards in the hand that are not trumps or jokers.
	 * Takes jack swaps into account.
	 * @param pTrump The trump to check for. Cannot be null.
	 * @return All the cards in the hand that are not trump cards.
	 * @pre pTrump != null
	 */
	public CardList getNonTrumpCards(Suit pTrump)
	{
		assert pTrump != null;
		CardList aHand = new CardList();
		Iterator<Card> aIterator = iterator();
		
		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			
			if( aCard.getEffectiveSuit(pTrump) != pTrump && !aCard.isJoker())
			{
				aHand.add(aCard);
			}
		}
		return aHand;
	}
	
	/**
	 * Selects the least valuable card in the hand, if pTrump is the trump.
	 * @param pTrump The trump suit. Can be null to indicate no-trump.
	 * @return The least valuable card in the hand.
	 */
	public Card selectLowest(Suit pTrump)
	{
		CardList aHand = new CardList();
		
		if (size() == 1)
		{
			return getFirst();
		}
		
		if(size() == 2 && getFirst().isJoker() && getLast().isJoker())
		{
			Card lowJoker = new Card(Card.Joker.LOW);
			return lowJoker;
		}
		aHand = getNonJokers();
		
		if( pTrump==null )
		{
			return aHand.sort(new ByRankComparator()).getFirst();
		}
		aHand = getNonTrumpCards(pTrump);
		
		if(aHand.size() == 0)
		{
			return sort(new ByRankComparator()).getFirst();
		}

		return aHand.sort(new BySuitComparator(pTrump)).getFirst();
	}
	
	/**
	 * @param pLed The suit led.
	 * @param pTrump Can be null for no-trump
	 * @return All cards that can legally be played given a lead and a trump.
	 */
	public CardList playableCards( Suit pLed, Suit pTrump )
	{
		CardList playHand = new CardList();
		
		if (pLed == pTrump)
		{
			playHand = getTrumpCards(pTrump); 
			if(playHand.size() != 0)
			{
				return playHand;
			}
		}
		Iterator<Card> aIterator = iterator();
		
		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			if(aCard.getEffectiveSuit(pTrump) == pLed) // JB 28/10: getEFFECTIVEsuit instead of getSuit.
			{
				playHand.add(aCard);
			} 
		}
		
		if (playHand.size() == 0)
		{
			Iterator<Card> aIterator2 = iterator();
			while(aIterator2.hasNext())
			{
				Card aCard = aIterator2.next();
				playHand.add(aCard);
			}
			return playHand;
		}
		
		else
		{
			return playHand;
		}
	}
	
	/**
	 * Returns the number of cards of a certain suit 
	 * in the hand, taking jack swaps into account.
	 * Excludes jokers.
	 * @param pSuit Cannot be null.
	 * @param pTrump Cannot be null.
	 * @return pSuit Can be null.
	 */
	public int numberOfCards(Suit pSuit, Suit pTrump)
	{
		if(pSuit == pTrump)
		{
			int fNumberOfJokers = getJokers().size(); // JB 28/10 Mistake was: we were returning the size with the jokers
			return getTrumpCards(pTrump).size() - fNumberOfJokers;
		}
		
		else
		{	
			CardList aHand2 = getNonTrumpCards(pTrump);
			Iterator<Card> aIterator = iterator();
			
			while(aIterator.hasNext())
			{
				Card aCard = aIterator.next();
				if(aCard.getSuit() != pSuit)
				{
					aHand2.remove(aCard);
				}
			}
			return aHand2.size();
		}		
	}
}