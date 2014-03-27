package comp303.fivehundred.gui;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.util.Card;

public interface AIObserver extends GameObserver
{
	public void handChange(int pCards);
	public void newBid(String pBid);
}
