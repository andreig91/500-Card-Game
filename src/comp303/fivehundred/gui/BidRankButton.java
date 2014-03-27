package comp303.fivehundred.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import comp303.fivehundred.util.Card.Rank;

public class BidRankButton extends JButton
{
	int aRank;
	public BidRankButton(int pRank)
	{
		super();
		setText(Rank.values()[pRank + 2].toString());
		aRank = pRank;
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			    ((CenterPanel)getParent()).rankPressed(aRank);
			}
		});
	}
}
