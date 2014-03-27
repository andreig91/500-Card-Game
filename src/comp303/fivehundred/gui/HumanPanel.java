package comp303.fivehundred.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.CardImages;
import comp303.fivehundred.util.CardList;

public class HumanPanel extends JPanel implements HumanObserver
{
	private CardPanel aCardPanel;
	private JLabel aBid = new JLabel("Bid:");
	
	public HumanPanel(String pName, int pAxis, GameEngine pGameEngine)
	{
		super();
		aCardPanel = new CardPanel(pGameEngine);
		setBorder(new TitledBorder(pName));
		setBackground( GameFrame.BACKGROUND_COLOR );
		setLayout(new BoxLayout(this, pAxis));
		add(aCardPanel);
		add(aBid);
	}
	
	public void newBid(String pBid)
	{
		aBid.setText("Bid: " + pBid);
	}

	public void newHand(CardList pNewHand)
	{
		if(pNewHand.size() == 16)
		{
			aCardPanel.setExchanging(true);
		}
		else
		{
			aCardPanel.setExchanging(false);
		}
		aCardPanel.initialize(pNewHand);
	}
}