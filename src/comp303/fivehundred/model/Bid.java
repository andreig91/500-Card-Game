package comp303.fivehundred.model;

import static comp303.fivehundred.util.Constants.HIGHEST_BID;
import static comp303.fivehundred.util.Constants.LOWEST_BID;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.Constants;
/**
 * Represents a bid or a contract for a player. Immutable.
 * @author Andrei Georgescu
 */
public class Bid implements Comparable<Bid>
{
	private final int aTricks;
	private final Suit aSuit;
	private final boolean aIsPass;
	private final boolean aNoTrump;
	
	/**
	 * Constructs a new standard bid (not a pass) in a trump.
	 * @param pTricks Number of tricks bid. Must be between 6 and 10 inclusive.
	 * @param pSuit Suit bid. 
	 * @pre pTricks >= 6 && pTricks <= 10
	 */
	public Bid(int pTricks, Suit pSuit)
	{
		assert pTricks >= LOWEST_BID && pTricks <= HIGHEST_BID;
		aTricks = pTricks;
		aSuit = pSuit;
		aIsPass = false;
		if(pSuit == null)
		{
			aNoTrump = true;
		}
		else
		{
			aNoTrump = false;
		}
	}
	
	/**
	 * Constructs a new passing bid.
	 */
	public Bid()
	{
		aIsPass = true;
		aTricks = 0;
		aSuit = null;
		aNoTrump = false;
	}
	
	/**
	 * Creates a bid from an index value between 0 and 24 representing all possible
	 * bids in order of strength.
	 * @param pIndex 0 is the weakest bid (6 spades), 24 is the highest (10 no trump),
	 * and everything in between.
	 * @pre pIndex >= 0 && pIndex <= 24
	 */
	public Bid(int pIndex)
	{
		assert pIndex >= 0 && pIndex <= Constants.MAX_BIDDING_INDEX;
		aTricks = LOWEST_BID + pIndex / Constants.NUM_SUITS_FOR_BIDDING;
		if( (pIndex + 1) % Constants.NUM_SUITS_FOR_BIDDING == 0 )
		{
			aNoTrump = true; 
			aSuit = null;
		}
		else
		{
			aNoTrump = false;
			aSuit = Suit.values()[pIndex % Constants.NUM_SUITS_FOR_BIDDING];
		}
		aIsPass = false;
	}
	
	/**
	 * @return The suit the bid is in, or null if it is in no-trump.
	 * @throws ModelException if the bid is a pass.
	 */
	public Suit getSuit()
	{
		if( aIsPass )
		{
			throw new ModelException("Cannot get the suit of a passing bid.");
		}
		else if( aNoTrump )
		{
			return null;
		}
		else
		{
			return aSuit;
		}
	}
	
	/**
	 * @return The number of tricks bid.
	 * @throws ModelException if the bid is a pass.
	 */
	public int getTricksBid()
	{
		if( aIsPass )
		{
			throw new ModelException("Cannot get the number of tricks of a passing bid.");
		}
		else
		{
			return aTricks;
		}
	}
	
	/**
	 * @return True if this is a passing bid.
	 */
	public boolean isPass()
	{
		return aIsPass;
	}
	
	/**
	 * @return True if the bid is in no trump.
	 */
	public boolean isNoTrump()
	{
		return aNoTrump;
	}

	@Override
	public int compareTo(Bid pBid)
	{
		if( isPass() && pBid.isPass() )
		{
			return 0;
		}
		else if( isPass() )
		{
			return -1;
		}
		else if( toIndex() < pBid.toIndex() )
		{
			return -1;
		}
		else if( toIndex() == pBid.toIndex() )
		{
			return 0;
		}
		return 1;		
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		if( aIsPass )
		{
			return "Passing bid";
		}
		else if( aNoTrump )
		{
			return aTricks + " no trump";
		}
		return aTricks + " of " + aSuit;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pBid)
	{
		if ( !( pBid instanceof Bid ) )
	    {
	    	return false;
	    }
		return toString().equals(pBid.toString());
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		if(isPass())
		{
			return Constants.NUM_SUITS_FOR_BIDDING*LOWEST_BID;
		}
		else
		{
			return toIndex();
		}
	}

	/**
	 * Converts this bid to an index in the 0-24 range.
	 * @return 0 for a bid of 6 spades, 24 for a bid of 10 no-trump,
	 * and everything in between.
	 * @throws ModelException if this is a passing bid.
	 */
	public int toIndex()
	{
		if( aIsPass )
		{
			throw new ModelException("Cannot convert a passing bid to an index in the 0-24 range.");
		}
		else if( aNoTrump )
		{
			return (aTricks - LOWEST_BID + 1) * Constants.NUM_SUITS_FOR_BIDDING - 1;
		}
		else
		{
			return (aTricks - LOWEST_BID) * Constants.NUM_SUITS_FOR_BIDDING + aSuit.ordinal();
		}
	}
	
	/**
	 * Returns the highest bid in pBids. If they are all passing
	 * bids, returns pass.
	 * @param pBids The bids to compare.
	 * @return the highest bid.
	 */
	public static Bid max(Bid[] pBids)
	{
		boolean pass = true;
		int maxIndex = 0;
		for(int i = 0; i < pBids.length; i++)
		{
			if( !pBids[i].aIsPass )
			{
				if(!pass)
				{
					if( pBids[i].toIndex() > pBids[maxIndex].toIndex() )
					{
						maxIndex = i;
					}
				}
				else
				{
					maxIndex = i;
				}
				pass = false;
			}
		}
		if( pass )
		{
			return new Bid();
		}
		else
		{
			return pBids[maxIndex];
		}
	}
	
	/**
	 * @return The score associated with this bid.
	 * @throws ModelException if the bid is a pass.
	 */
	public int getScore()
	{
		if( aIsPass )
		{
			throw new ModelException("Cannot get the score associated with a passing bid.");
		}
		else
		{
			return toIndex() * Constants.SCORE_INCREMENT_BETWEEN_INDEXES + Constants.MIN_WINNING_SCORE;
		}
	}
}