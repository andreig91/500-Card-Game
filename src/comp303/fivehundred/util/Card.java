package comp303.fivehundred.util;

import java.util.Comparator;

/**
 * An immutable description of a playing card.
 * @author Andrei Georgescu
 */
public final class Card implements Comparable<Card>
{
	/**
	 * Represents the rank of the card.
	 */
	public enum Rank 
	{ FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
	
	/**
	 * Represents the suit of the card.
	 */
	public enum Suit 
	{ SPADES, CLUBS, DIAMONDS, HEARTS; 
		
		/**
		 * @return the other suit of the same color. 
		 */
		public Suit getConverse()
		{
			Suit lReturn = this;
			switch(this) 
			{
				case SPADES: lReturn = CLUBS; break;
				case CLUBS:  lReturn = SPADES; break;
				case DIAMONDS: lReturn = HEARTS; break;
				case HEARTS: lReturn = DIAMONDS; break;
				default: lReturn = this;
			}
			return lReturn;
		}
	}
	
	/**
	 * Represents the value of the card, if the card is a joker.
	 */
	public enum Joker
	{ LOW, HIGH }
	
	// If this field is null, it means the card is a joker, and vice-versa.
	private final Rank aRank;

	// If this field is null, it means the card is a joker, and vice-versa.
	private final Suit aSuit;
	
	// If this field is null, it means the card is not a joker, and vice-versa.
	private final Joker aJoker;
	
	/**
	 * Create a new card object that is not a joker. 
	 * @param pRank The rank of the card.
	 * @param pSuit The suit of the card.
	 * @pre pRank != null
	 * @pre pSuit != null
	 */
	public Card( Rank pRank, Suit pSuit )
	{
		assert pRank != null;
		assert pSuit != null;
		aRank = pRank;
		aSuit = pSuit;
		aJoker = null;
	}
	
	/**
	 * Creates a new joker card.
	 * @param pValue Whether this is the low or high joker.
	 * @pre pValue != null
	 */
	public Card( Joker pValue )
	{
		assert pValue != null;
		aRank = null;
		aSuit = null;
		aJoker = pValue;
	}
	
	/**
	 * @return True if this Card is a joker, false otherwise.
	 */
	public boolean isJoker()
	{
		return aJoker != null;
	}
	
	/**
	 * @return Whether this is the High or Low joker.
	 */
	public Joker getJokerValue()
	{
		assert isJoker();
		return aJoker;
	}
	
	/**
	 * Obtain the rank of the card.
	 * @return An object representing the rank of the card. Can be null if the card is a joker.
	 * @pre !isJoker();
	 */
	public Rank getRank()
	{
		assert !isJoker();
		return aRank;
	}
	
	/**
	 * Obtain the suit of the card.
	 * @return An object representing the suit of the card 
	 * @pre !isJoker();
	 */
	public Suit getSuit()
	{
		assert !isJoker();
		return aSuit;
	}
	
	/**
	 * Returns the actual suit of the card if pTrump is the
	 * trump suit. Takes care of the suit swapping of jacks.
	 * @param pTrump The current trump. Null if no trump.
	 * @return The suit of the card, except if the card is a Jack
	 * and its converse suit is trump. Then, returns the trump.
	 */
	public Suit getEffectiveSuit( Suit pTrump )
	{
		if( pTrump == null )
		{
			return aSuit;
		}
		else if( aRank == Rank.JACK && aSuit == pTrump.getConverse())
		{
			return pTrump;
		}
		else
		{
			return aSuit;
		}
	} 
	
	/**
	 * @see java.lang.Object#toString()
	 * @return See above.
	 */
	public String toString()
	{
		if( !isJoker() )
		{
			return aRank + " of " + aSuit;
		}
		else
		{
			return aJoker + " " + "Joker";
		}
	}
	
	/**
	 * @return A short textual representation of the card
	 */
	public String toShortString()
	{
		String lReturn = "";
		if( isJoker() )
		{
			lReturn = aJoker.toString().charAt(0) + "J";
		}
		else
		{
			if( aRank.ordinal() <= Rank.NINE.ordinal() )
			{
				lReturn += new Integer(aRank.ordinal() + 4).toString();
			}
			else
			{
				lReturn += aRank.toString().charAt(0);
			}
			lReturn += aSuit.toString().charAt(0);
		}
		return lReturn;
	}

	/**
	 * Compares two cards according to their rank.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param pCard The card to compare to
	 * @return Returns a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than pCard
	 */
	public int compareTo(Card pCard)
	{
		if( isJoker() && pCard.isJoker() )
		{
			return getJokerValue().ordinal() - pCard.getJokerValue().ordinal();
		}
		else if( isJoker() )
		{
			return 1;
		}
		else if( pCard.isJoker() )
		{
			return -1;
		}
		else
		{
			int fCompared =  getRank().ordinal() - pCard.getRank().ordinal();
			if(fCompared == 0) // If cards are of the same Rank, compare them by their suits
			{
				return getSuit().ordinal() - pCard.getSuit().ordinal();
			}
			else
			{
				return fCompared;
			}
		}
	}

	/**
	 * Two cards are equal if they have the same suit and rank or if they 
	 * are two jokers of the same value.
	 * @param pCard The card to test.
	 * @return true if the two cards are equal
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object pCard ) 
	{
	    if ( !( pCard instanceof Card ) )
	    {
	    	return false;
	    }
		return toShortString().equals( ((Card) pCard).toShortString() );
	}

	/** 
	 * The hashcode for a card is the suit*number of ranks + that of the rank (perfect hash).
	 * @return the hashcode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		if( isJoker() )
		{
			return aJoker.ordinal() + 1;
		}
		return (aSuit.ordinal() + 1) * Rank.values().length + aRank.ordinal();
	}
	
	/**
	 * Compares cards using their rank as primary key, then suit. Jacks
	 * rank between 10 and queens of their suit.
	 */
	public static class ByRankComparator implements Comparator<Card>
	{
		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			if( pCard1.compareTo(pCard2) == 0 && !pCard1.isJoker())
			{
				return pCard1.getSuit().ordinal() - pCard2.getSuit().ordinal();
			}
			else
			{
				return pCard1.compareTo(pCard2);
			}
		}
	}
	
	/**
	 * Compares cards using their suit as primary key, then rank. Jacks
	 * rank between 10 and queens of their suit.
	 */
	public static class BySuitNoTrumpComparator implements Comparator<Card>
	{
		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			if( pCard1.isJoker() || pCard2.isJoker() )
			{
				return pCard1.compareTo(pCard2);
			}
			else
			{
				// JB 29/10 later: Actually, it needs to take not only suit forst, but rank in second place
				// JB 29/10: I put it back as it was, because it seems REALLY that suit presides over rank in no trump.
				// JB 28/10 : previous line was   int aTemp = pCard1.getSuit().ordinal() - pCard2.getSuit().ordinal();
				/*int aTemp = pCard1.getRank().ordinal() - pCard2.getRank().ordinal();
				if( aTemp == 0)
				{
					return pCard1.compareTo(pCard2);
				}
				else
				{
					return aTemp;
				}*/
				int fComp = pCard1.getSuit().ordinal() - pCard2.getSuit().ordinal();
				if( fComp == 0)
				{
					return pCard1.compareTo(pCard2);
				}
				else
				{
					return fComp;
				}
			}
		}
	}
	
	/**
	 * Compares cards using their suit as primary key, then rank. Jacks
	 * rank above aces if they are in the trump suit. The trump suit becomes the
	 * highest suit.
	 */
	public static class BySuitComparator implements Comparator<Card>
	{
		private Suit aTrump;
		
		/**
		 * Create a new BySuitComparator object. 
		 * @param pTrump The current trump.
		 * @pre pTrump != null
		 */
		public BySuitComparator(Suit pTrump)
		{
			assert pTrump != null;
			aTrump = pTrump;
		}
		
		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			if( !pCard1.isJoker() && !pCard2.isJoker() && (pCard1.getEffectiveSuit(aTrump) == aTrump || 
						pCard2.getEffectiveSuit(aTrump) == aTrump) )
			{
				if( pCard1.getEffectiveSuit(aTrump) == aTrump && pCard2.getEffectiveSuit(aTrump) == aTrump )
				{
					if( pCard1.getRank() == Rank.JACK && pCard2.getRank() == Rank.JACK )
					{
						return Math.abs(pCard2.getSuit().ordinal() - aTrump.ordinal()) - 
							   Math.abs(pCard1.getSuit().ordinal() - aTrump.ordinal());
					}
					else if( pCard1.getRank() == Rank.JACK || pCard2.getRank() == Rank.JACK )
					{
						return Math.abs(pCard2.getRank().ordinal() - Rank.JACK.ordinal()) - 
							   Math.abs(pCard1.getRank().ordinal() - Rank.JACK.ordinal());
					}
					else
					{
						return pCard1.compareTo(pCard2);
					}
				}
				else
				{
					return Math.abs(pCard2.getEffectiveSuit(aTrump).ordinal() - aTrump.ordinal()) - 
						   Math.abs(pCard1.getEffectiveSuit(aTrump).ordinal() - aTrump.ordinal());
				}
			}
			else
			{
				return new BySuitNoTrumpComparator().compare(pCard1, pCard2);
			}
		}
	}
}
