package comp303.fivehundred.gui;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import comp303.fivehundred.ai.BasicRobot;
import comp303.fivehundred.ai.RandomRobot;
import comp303.fivehundred.engine.Driver;
import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.model.Player;

/**
 * The GUI; class you have to execute to test the program.
 * @author JeanBenoit
 *
 */
public class GuiLogger extends JFrame implements Observer
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 700;
	private static GameEngine aGame;
	private JTextArea aText = new JTextArea();
	private JScrollPane aScrollPane;
	private JScrollBar aScrollBar;
	
	
	/**
	 * Default constructor: Constructs the game frame.
	 */
	public GuiLogger()
	{
		aScrollPane = new JScrollPane(aText);
		aScrollBar = new JScrollBar();
		aScrollPane.add(aScrollBar);
		add(aScrollPane);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * 
	 * @param pArgs Nothing used
	 */
	public static void main(String[] pArgs)
	{
		GuiLogger frame = new GuiLogger();

		aGame = new GameEngine(new Player(new BasicRobot(), "BasicA"), new Player(new RandomRobot(), "RandomA")
				, new Player(new BasicRobot(), "BasicB"), new Player(new RandomRobot(), "RandomB"));

		aGame.addObserver(frame);

		//aGame.addObserver(stats);
		//Driver d1 = new Driver(aGame);

		new Driver(aGame);

	}

	/**
	 * 
	 * @param pO 
	 * @param pArg 
	 */
	@Override
	public void update(Observable pO, Object pArg)
	{
		aText.append(pArg.toString());		
	}
}
