package comp303.fivehundred.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import comp303.fivehundred.util.Constants;
import comp303.fivehundred.util.Card.Suit;

public class BidSuitButton extends JButton
{
	int aSuit;
	
	public BidSuitButton(int pSuit)
	{
		super();
		aSuit = pSuit;
		if(pSuit == 4)
		{
			setText("No Trumps");
		}
		else
		{
			setText(Suit.values()[pSuit].toString());
		}
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			    ((CenterPanel)getParent()).suitPressed(aSuit);
			}
		});
	}
}
