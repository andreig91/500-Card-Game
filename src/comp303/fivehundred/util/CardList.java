package comp303.fivehundred.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


/**
 * A mutable list of cards. Does not support duplicates.
 * The cards are maintained in the order added.
 * @author Andrei Georgescu
 */
public class CardList implements Iterable<Card>, Cloneable
{
	private ArrayList<Card> aCardList;
	
	/**
	 * Creates a new, empty card list.
	 */
	public CardList()
	{
		aCardList = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card if it is not
	 * already in the list. Has no effect if the card
	 * is already in the list.
	 * @param pCard The card to add.
	 * @pre pCard != null
	 */
	public void add(Card pCard)
	{
		assert pCard != null;
		if( !aCardList.contains(pCard))
		{
			aCardList.add(pCard);
		}
	}
	
	/**
	 * @return The number of cards in the list.
	 */
	public int size()
	{
		return aCardList.size();
	}
	
	/**
	 * @return The first card in the list, according to whatever
	 * order is currently being used. 
	 * @throws GameUtilException if the list is empty.
	 */
	public Card getFirst()
	{
		if( size()==0 )
		{
			throw new GameUtilException("Cannot get element of an empty list.");
		}
		else
		{
			return aCardList.get(0);
		}
	}
	
	/**
	 * @return The last card in the list, according to whatever
	 * order is currently being used. 
	 * @throws GameUtilException If the list is empty.
	 */
	public Card getLast()
	{
		if( size()==0 )
		{
			throw new GameUtilException("Cannot get element of an empty list.");
		}
		else
		{
			return aCardList.get(size() - 1);
		}
	}
	
	/**
	 * Removes a card from the list. Has no effect if the card is
	 * not in the list.
	 * @param pCard The card to remove. 
	 * @pre pCard != null;
	 */
	public void remove(Card pCard)
	{
		assert pCard != null;
		aCardList.remove(pCard);
	}
	
	/**
	 * @see java.lang.Object#clone()
	 * {@inheritDoc}
	 */
	public CardList clone()
	{
		try 
		{
		    CardList clone = (CardList) super.clone();
		    clone.aCardList = new ArrayList<Card>();
		    for( Card card : aCardList )
		    {
		    	if( card.isJoker() )
		    	{
		    		clone.aCardList.add(new Card(card.getJokerValue()));
		    	}
		    	else
		    	{
		    		clone.aCardList.add(new Card(card.getRank(), card.getSuit()));
		    	}
		    }
		    return clone;
		}
		catch (CloneNotSupportedException e) 
		{
			return null;
		}
	}
	
	/**
	 * @see java.lang.Iterable#iterator()
	 * {@inheritDoc}
	 */
	public Iterator<Card> iterator()
	{
		return aCardList.iterator();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * {@inheritDoc}
	 */
	public String toString()
	{
		String aString = "";
		for(Card card : aCardList)
		{
			aString += card.toShortString() + " ";
		}
		return aString;
	}
	
	/**
	 * @pre aCards.size() > 0
	 * @return A randomly-chosen card from the set. 
	 */
	public Card random()
	{
		assert aCardList.size() > 0;
		Random generator = new Random();
		return aCardList.get(generator.nextInt(size()));
	}
	
	/**
	 * Returns another CardList, sorted as desired. This
	 * method has no side effects.
	 * @param pComparator Defines the sorting order.
	 * @return the sorted CardList
	 * @pre pComparator != null
	 */
	public CardList sort(Comparator<Card> pComparator)
	{
		assert pComparator != null;
		ArrayList<Card> sortedArrayList = new ArrayList<Card>();
		for(Card aCard : aCardList)
		{
			if( aCard.isJoker() )
	    	{
	    		sortedArrayList.add(new Card(aCard.getJokerValue()));
	    	}
	    	else
	    	{
	    		sortedArrayList.add(new Card(aCard.getRank(), aCard.getSuit()));
	    	}
		}
		Collections.sort(sortedArrayList, pComparator);
		CardList sortedCardList = new CardList();
		sortedCardList.aCardList = sortedArrayList;
		return sortedCardList;
	}
}