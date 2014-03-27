package comp303.fivehundred.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import comp303.fivehundred.ai.BasicRobot;
import comp303.fivehundred.ai.RandomRobot;
import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.engine.GameLogger;
import comp303.fivehundred.model.Player;

/**
 * This Frame ... //TODO
 * @author JeanBenoit
 * 
 */
public class GameFrame extends JFrame
{
	public static Color BACKGROUND_COLOR = new Color(40, 160, 20);
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 700;
	private GameEngine aGameEngine;
	private JPanel aNorthPanel;
	private JPanel aSouthPanel;
	private AIPanel aNorthAIPanel;
	private AIPanel aWestAIPanel; 
	private AIPanel aEastAIPanel;
	private HumanPanel aHumanPanel;
	private CenterPanel aCenterPanel;
	
	public GameFrame(boolean humanPlayer)
	{
		if(humanPlayer)
		{
			aGameEngine = new GameEngine(new Player(new BasicRobot(), "BasicA"), new Player(null, "Human"), 
					      new Player(new BasicRobot(), "BasicB"), new Player(new RandomRobot(), "RandomB"));
			aGameEngine.addObserver(new GameLogger());
			createPanels();
			generateLayout();
		}
		else 
		{
			//TODO what with 4 AIs
		}
	}

	private void generateLayout()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		/*setLayout(new GridLayout(2,2));
		add(aWestAIPanel);
		add(aNorthAIPanel);
		add(aEastAIPanel);
		add(aHumanPanel);*/
		
		setLayout( new BorderLayout() );
		add(aWestAIPanel, BorderLayout.WEST);
		add(aNorthPanel, BorderLayout.NORTH);
		add(aEastAIPanel, BorderLayout.EAST);
		add(aSouthPanel, BorderLayout.SOUTH);
		add(aCenterPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
		setVisible(true);
	}
	
	private void createPanels()
	{
		
		
		aNorthAIPanel = new AIPanel("Dwight", BoxLayout.X_AXIS);
		aWestAIPanel = new AIPanel("Jim", BoxLayout.Y_AXIS);
		aEastAIPanel = new AIPanel("Pam", BoxLayout.Y_AXIS);
		aHumanPanel = new HumanPanel("Mose", BoxLayout.X_AXIS, aGameEngine);
		aCenterPanel = new CenterPanel(aGameEngine, this);
		aGameEngine.addHumanObserver(aHumanPanel);
		aGameEngine.addAIObserver(aWestAIPanel, 0);
		aGameEngine.addAIObserver(aNorthAIPanel, 2);
		aGameEngine.addAIObserver(aEastAIPanel, 3);
		aGameEngine.addCenterPanelObserver(aCenterPanel);
		aNorthPanel = new JPanel();
		aSouthPanel = new JPanel();
		aNorthPanel.setLayout(new BorderLayout());
		aNorthPanel.add(aNorthAIPanel, BorderLayout.CENTER);
		aSouthPanel.setLayout(new BorderLayout());
		aSouthPanel.add(aHumanPanel, BorderLayout.CENTER);
		JMenuBar aMenuBar = new JMenuBar();
		JMenu aStart = new JMenu("Start");
		JMenu aQuit = new JMenu("Quit");
		//JMenuItem aMenuItem = new JMenuItem("Start Game");
		aStart.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			    aGameEngine.deal();
			}
		});
		aQuit.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			    System.exit(0);
			}
		});
		//aMenu.add(aMenuItem);
		aMenuBar.add(aStart);
		aMenuBar.add(aQuit);
		setJMenuBar(aMenuBar);
	}

	public static void main(String[]args)
	{
		boolean humanPlayer = true;
		new GameFrame(humanPlayer);
	}
}