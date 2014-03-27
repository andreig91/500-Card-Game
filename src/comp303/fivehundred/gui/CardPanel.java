package comp303.fivehundred.gui;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.CardImages;
import comp303.fivehundred.util.CardList;

public class CardPanel extends JPanel implements GameObserver
{
	// Read-only: not synchronized with the GameEngine
	private HashMap<JLabel,Card> aCards = new HashMap<JLabel,Card>();
	boolean aExchanging = false;
	private CardList aDropedWidowCards = new CardList();
	private CardList aCurrentHand;
	protected GameEngine aGameEngine;
	
	public CardPanel(GameEngine pGameEngine)
	{
		super(new OverlapLayout(new Point(30, 0)));
		Insets ins = new Insets(10, 0, 0, 0);
		((OverlapLayout)getLayout()).setPopupInsets(ins);
		setBackground( GameFrame.BACKGROUND_COLOR );
		aGameEngine = pGameEngine;
	}
	
	public CardPanel()
	{
		super(new OverlapLayout(new Point(30, 0)));
		Insets ins = new Insets(10, 0, 0, 0);
		((OverlapLayout)getLayout()).setPopupInsets(ins);
		setBackground( GameFrame.BACKGROUND_COLOR );
	}
	
	protected void initialize(CardList pCards)
	{
		aCurrentHand = pCards;
		aCards.clear();
		removeAll();
		for( Card card : pCards )
		{
			JLabel lLabel = new JLabel( CardImages.getCard(card));
			aCards.put(lLabel,card);
			lLabel.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					Component c = e.getComponent();
				    if(aExchanging)
				    {
				    	Card aClickedCard = aCards.get(c);
				    	aDropedWidowCards.add(aClickedCard);
				    	aCurrentHand.remove(aClickedCard);
				    	initialize(aCurrentHand);
				    	if(aDropedWidowCards.size() == 6)
				    	{
				    		aGameEngine.humanExchange(aDropedWidowCards);
				    	}
				    	aDropedWidowCards = new CardList();
				    }
				    else
				    {
					    Boolean constraint = ((OverlapLayout)getLayout()).getConstraints(c);

					    if (constraint == null || constraint == OverlapLayout.POP_DOWN)
					    {
					    	popAllDown();
					    	((OverlapLayout)getLayout()).addLayoutComponent(c, OverlapLayout.POP_UP);
					    }
					    else
					    {
					    	((OverlapLayout)getLayout()).addLayoutComponent(c, OverlapLayout.POP_DOWN);
					    }

					    c.getParent().invalidate();
					    c.getParent().validate();
				    }
				}
			});
			add(lLabel);
		}
		validate();
		repaint();
	}
	
	private void popAllDown()
	{
		Component[] lChildren = getComponents();
		for( Component component : lChildren )
		{
			((OverlapLayout)getLayout()).addLayoutComponent(component, OverlapLayout.POP_DOWN);
		}
	}
	
	/**
	 * @return The card that is up. Null if none.
	 */
	public Card isUp()
	{
		for( Component component : getComponents() )
		{
			Boolean constraint = ((OverlapLayout)getLayout()).getConstraints(component);
			if (constraint != null && constraint == OverlapLayout.POP_UP)
			{
				return aCards.get(component);
			}
		}
		return null;
	}
	
	public void setExchanging(boolean pBool)
	{
		aExchanging = pBool;
	}
}