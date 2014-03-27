package comp303.fivehundred.model;

import java.util.Comparator;
import java.util.Iterator;

import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.Card.BySuitComparator;
import comp303.fivehundred.util.CardList;

/**
 * A card list specialized for handling cards discarded
 * as part of the play of a trick.
 * @author Amjad Al-Rikabi
 */
public class Trick extends CardList
{	
	private Bid aCurrentContract;
	/**
	 * Constructs a new empty trick for the specified contract.
	 * @param pContract The contract that this trick is played for.
	 */
	public Trick(Bid pContract)
	{
		aCurrentContract = pContract;
	}
	
	/**
	 * @return Can be null for no-trump.
	 */
	public Suit getTrumpSuit()
	{
		if(aCurrentContract.isNoTrump())
		{
			return null;
		}
		else
		{
			return aCurrentContract.getSuit();
		}
	}


	/**
	 * @return The effective suit led.
	 */
	public Suit getSuitLed()
	{
		if (getFirst().isJoker() || getFirst().getEffectiveSuit(getTrumpSuit())==getTrumpSuit())
		{
			return this.getTrumpSuit();
		}
		else
		{
			return this.getFirst().getSuit();
		}
	}

	/**
	 * @return True if a joker led this trick
	 */
	public boolean jokerLed()
	{
		return this.getFirst().isJoker();
	}

	/**
	 * @return The card that led this trick
	 * @pre size() > 0
	 */
	public Card cardLed()
	{
		assert this.size() > 0;
		Card afirst = this.getFirst();
		return afirst;
	}

	/**
	 * @return Highest card that actually follows suit (or trumps it).
	 * I.e., the card currently winning the trick.
	 * @pre size() > 0
	 */
	public Card highest()
	{
		assert this.size() > 0;
		CardList aSortedList = this.clone();
		//If there is no trump suit:
		if(aCurrentContract.isNoTrump())
		{
			aSortedList = this.sort(new NoTrumpComparatorTrick(this.getSuitLed()) );
		}
		else //If there is a trump suit
		{
			aSortedList = this.sort(new TrumpComparatorTrick(aCurrentContract.getSuit(), this.getSuitLed()) );
		}
		return aSortedList.getLast();
	}

	/**
	 * @return The index of the card that wins the trick.
	 */
	public int winnerIndex()
	{
		int aWinnerIndex = -1;
		int aIndex = 0;
		Card aWinner = this.highest();
		Iterator<Card> aIterator = iterator();

		while(aIterator.hasNext())
		{
			Card aCard = aIterator.next();
			if(aCard.equals(aWinner))
			{
				aWinnerIndex = aIndex;
			}
			aIndex++;
		}

		return aWinnerIndex;
	}

	/**
	 * Compares cards using their rank as primary key. Jacks
	 * rank between 10 and queens of their suit. (Normal)
	 */
	public static class NoTrumpComparatorTrick implements Comparator<Card>
	{
		private Suit aLead;

		/**
		 * Constructor for NoTrumpComparator.
		 * @param pLead is the leading suit.
		 */
		public NoTrumpComparatorTrick(Suit pLead)
		{
			assert pLead != null;
			aLead = pLead;
		}

		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			//Case where both are jokers
			if (pCard1.isJoker() && pCard2.isJoker())
			{
				//Check for highest joker
				return pCard1.getJokerValue().ordinal() - pCard2.getJokerValue().ordinal();
			}
			//Case where only pCard1 is the joker
			else if(pCard1.isJoker())
			{
				return 1;
			}
			//Case where only pCard2 is the joker
			else if(pCard2.isJoker())
			{
				return -1;
			}
			//Case when neither are jokers
			else 
			{
				return new BySuitLeadComparator(aLead).compare(pCard1, pCard2);
			}
		}
	}
	/**
	 * Compares cards using their Trump then trump rank as primary key. Jacks
	 * rank between 10 and queens of their suit. (Normal)
	 */
	public static class TrumpComparatorTrick implements Comparator<Card>
	{
		private Suit aTrump;
		private Suit aLead;

		/**
		 * Constructor for TrumpComparatorTrick.
		 * @param pTrump is the trump suit. 
		 * @param pLead is the leading suit.
		 */
		public TrumpComparatorTrick(Suit pTrump, Suit pLead)
		{
			assert pTrump != null;
			assert pLead != null;
			aTrump = pTrump;
			aLead = pLead;
		}

		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			int aWhichCardIsJoker = 1;

			//Case where both are jokers
			if (pCard1.isJoker() && pCard2.isJoker())
			{
				//Check for highest joker
				return pCard1.getJokerValue().ordinal() - pCard2.getJokerValue().ordinal();
			}
			//Case where either pCard1 or pCard2 is the joker
			else if(pCard1.isJoker() || pCard2.isJoker())
			{
				if(pCard2.isJoker())
				{
					aWhichCardIsJoker = -1;
				}
				return aWhichCardIsJoker;
			}
			//Case when either pCard1 or pCard2 is EffectiveTrump
			else if ((pCard1.getEffectiveSuit(aTrump) == aTrump) || (pCard2.getEffectiveSuit(aTrump) == aTrump))
			{
				return new BySuitComparator(aTrump).compare(pCard1, pCard2);
			}
			//Case when either pCard1 or pCard2 is of Suit led
			else if ((pCard1.getSuit() == aLead) || (pCard2.getSuit() == aLead))
			{
				return new BySuitLeadComparator(aLead).compare(pCard1, pCard2);
			}
			else
			{
				return 0;
			}
		}
	}

	/**
	 * Compares cards using their Suit then Suit rank as primary key. Jacks
	 * rank between 10 and queens of their suit. (Normal)
	 * IMPORTANT: Assume no Jokers in the hand!
	 */
	public static class BySuitLeadComparator implements Comparator<Card>
	{
		private Suit aLead;

		/**
		 * Create a new BySuitComparator object. 
		 * @param pLead The suit of the leading card.
		 * @pre pLead != null
		 */
		public BySuitLeadComparator(Suit pLead)
		{
			assert pLead != null;
			aLead = pLead;
		}

		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			int aResult = 0;
			
			//If pCard2 & pCard 1 has the same suit as the leading suit
			if(pCard2.getSuit().equals(aLead) && pCard1.getSuit().equals(aLead))
			{
				return pCard1.compareTo(pCard2);
			}
			else if (pCard2.getSuit().equals(aLead)) 
			{
				aResult = -1;
			}
			else if (pCard1.getSuit().equals(aLead)) 
			{
				aResult = 1;
			}
			
			return aResult;
		}
	}
}

