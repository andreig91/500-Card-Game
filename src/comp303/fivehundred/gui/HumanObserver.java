package comp303.fivehundred.gui;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.util.CardList;

public interface HumanObserver extends GameObserver
{
	public void newHand(CardList pNewHand);
	public void newBid(String pBid);
}
