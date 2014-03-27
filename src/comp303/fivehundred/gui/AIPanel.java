package comp303.fivehundred.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import comp303.fivehundred.model.Bid;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.CardImages;
import comp303.fivehundred.util.CardList;

public class AIPanel extends JPanel implements AIObserver
{
	private CardPanel aCardPanel = new CardPanel();
	private JLabel aBid = new JLabel("Bid:");
	
	public AIPanel(String pName, int pAxis)
	{
		super();
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
	
	public void handChange(int pCards)
	{
		aCardPanel.removeAll();
		for(int i = 0; i < pCards; i++)
		{
			JLabel lLabel = new JLabel(CardImages.getBack());
			aCardPanel.add(lLabel);
		}
		aCardPanel.validate();
		aCardPanel.repaint();
	}
}