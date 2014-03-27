package comp303.fivehundred.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.util.Card.Rank;
import comp303.fivehundred.util.Card.Suit;
import comp303.fivehundred.util.Constants;

public class CenterPanel extends JPanel
{
	int aSuit = -1;
	int aRank = -1;
	GameEngine aGameEngine;
	JLabel aMessageLabel;
	JButton aPassButton;
	JButton aBidButton;
	
	public CenterPanel(GameEngine pGameEngine, JFrame pFrame)
	{
		super();
		aGameEngine = pGameEngine;
		setBackground( GameFrame.BACKGROUND_COLOR );
		aBidButton = new JButton("Bid");
		aBidButton.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			    if(aSuit != -1 && aRank != -1)
			    {
			    	Bid aBid = new Bid(aRank * Constants.NUM_SUITS_FOR_BIDDING + aSuit);
			    	if(!aGameEngine.humanBid(aBid))
			    	{
			    		aMessageLabel.setText("You need to bid higher than the highest bid!");
			    		//sleep(2000);
			    		//aMessageLabel.setText("");
			    		aSuit = -1;
				    	aRank = -1;
			    		return;
			    	}
			    	aMessageLabel.setText("");
			    	aGameEngine.bidAfterHuman();
			    	aSuit = -1;
			    	aRank = -1;
			    }
			}
		});
		aPassButton = new JButton("Pass");
		aPassButton.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Bid aBid = new Bid();
				aGameEngine.humanBid(aBid);
				aSuit = -1;
		    	aRank = -1;
		    	aGameEngine.bidAfterHuman();
			}
		});

		/*JPanel superPanel = new JPanel();
		superPanel.setLayout(new GridLayout(1,3));
		superPanel.add(new JLabel());
		superPanel.add(this);
		superPanel.add(new JLabel());
		superPanel.setBackground( GameFrame.BACKGROUND_COLOR );*/
		
		/*pFrame.setLayout(new BoxLayout(pFrame.getContentPane(), BoxLayout.Y_AXIS));
		setBackground( GameFrame.BACKGROUND_COLOR );
		JLabel aRow1 = new JLabel();
		JLabel aRow2 = new JLabel();
		JLabel aRow3 = new JLabel();
		JLabel aRow4 = new JLabel();
		aRow1.setLayout(new FlowLayout());
		aRow2.setLayout(new FlowLayout());
		aRow3.setLayout(new FlowLayout());
		aRow4.setLayout(new FlowLayout());
		aRow1.add(new BidRankButton(Rank.SIX.ordinal() - 2));
		aRow1.add(new BidRankButton(Rank.SEVEN.ordinal() - 2));
		aRow1.add(new BidRankButton(Rank.EIGHT.ordinal() - 2));
		aRow1.add(new BidRankButton(Rank.NINE.ordinal() - 2));
		aRow1.add(new BidRankButton(Rank.TEN.ordinal() - 2));
		aRow2.add(new BidSuitButton(Suit.SPADES.ordinal()));
		aRow2.add(new BidSuitButton(Suit.CLUBS.ordinal()));
		aRow2.add(new BidSuitButton(Suit.DIAMONDS.ordinal()));
		aRow2.add(new BidSuitButton(Suit.HEARTS.ordinal()));
		aRow2.add(new BidSuitButton(4));
		aRow3.add(aBidButton);
		aRow3.add(aPassButton);
		aRow4.add(aMessageLabel);
		add(aRow1);
		add(aRow2);
		add(aRow3);
		add(aRow4);
		aRow1.setVisible(true);
		aRow2.setVisible(true);
		aRow3.setVisible(true);
		aRow4.setVisible(true);
		setVisible(true);*/
	}
	private void initialize()
	{
		setLayout(new GridLayout(4,1));
		setBackground( GameFrame.BACKGROUND_COLOR );
		add(new BidRankButton(Rank.SIX.ordinal() - 2));
		add(new BidRankButton(Rank.SEVEN.ordinal() - 2));
		add(new BidRankButton(Rank.EIGHT.ordinal() - 2));
		add(new BidRankButton(Rank.NINE.ordinal() - 2));
		add(new BidRankButton(Rank.TEN.ordinal() - 2));
		add(new BidSuitButton(Suit.SPADES.ordinal()));
		add(new BidSuitButton(Suit.CLUBS.ordinal()));
		add(new BidSuitButton(Suit.DIAMONDS.ordinal()));
		add(new BidSuitButton(Suit.HEARTS.ordinal()));
		add(new BidSuitButton(4));
		
		add(new JLabel());
		add(aPassButton);
		add(new JLabel());
		add(aBidButton);
		add(new JLabel());
		add(new JLabel());
		aMessageLabel = new JLabel();
		add(aMessageLabel);
		add(new JLabel());
	}
	
	protected void rankPressed(int pRank)
	{
		aRank = pRank;
	}
	
	protected void suitPressed(int pSuit)
	{
		aSuit = pSuit;
	}
	
	public void startBidding()
	{
		removeAll();
		aGameEngine.reinitializeBids();
		initialize();
		aGameEngine.bidBeforeHuman();
	}
	
	public void endBidding()
	{
		if(aGameEngine.allPasses())
		{	
			aMessageLabel.setText("Everyone passed");
			aGameEngine.reinitializeBids();
			//sleep(2000);
			//aMessageLabel.setText("");
			removeAll();
			aGameEngine.deal();
		}
		else
		{
			aGameEngine.determineWinningBid();
			aGameEngine.exchange();
		}
	}
	
	public void sleep(long pMilis)
	{
		try
		{
			Thread.sleep(pMilis);
		}
		catch (InterruptedException e)
		{
		}
	}
}
