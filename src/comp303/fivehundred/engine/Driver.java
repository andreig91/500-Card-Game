package comp303.fivehundred.engine;
import comp303.fivehundred.model.Player;
import comp303.fivehundred.ai.BasicRobot;
import comp303.fivehundred.ai.RandomRobot;
import comp303.fivehundred.util.Constants;

/**
 * @author
 */
public class Driver
{	
	private static final int NUMBER_OF_GAMES = 100000;
	private static final boolean LOGGER_IS_ON = false;
	private GameStatistics aStats;
	private GameEngine aGame;
		
	/**
	 * 
	 * @param pGame 
	 */
	public Driver(GameEngine pGame)
	{
		GameLogger aLogger = new GameLogger();
		aStats = new GameStatistics();
		aGame = pGame;
		if(LOGGER_IS_ON)
		{
			aGame.addObserver(aLogger);
		}
		aGame.addObserver(aStats);
		playGame();
	}
	
	/**
	 * Sets the players and creates the game.
	 * @param pArgs any extra parameters
	 */
	public static void main(String[] pArgs)
	{
		GameEngine aGame = new GameEngine(new Player(new BasicRobot(), "BasicA"), new Player(new RandomRobot(),
				"RandomA") , new Player(new BasicRobot(), "BasicB"), new Player(new RandomRobot(), "RandomB"));
		new Driver(aGame);
	}
	/**
	 * 
	 */
	public void playGame()
	{	
			for(int i = 0; i < NUMBER_OF_GAMES; i++)
			{
				aGame.initializeGame();
				while( !aGame.isGameOver() )
				{
					aGame.initializeRound();
					aGame.deal();
					aGame.bidBeforeHuman(); // in this version, no human, so could be read as bid()
					while( aGame.allPasses() )
					{
						aGame.deal();
						aGame.bidBeforeHuman();
					}
					aGame.determineWinningBid();
					aGame.exchange();
					for( int lIndex = 0; lIndex < Constants.HAND_SIZE; lIndex++ )
					{
						aGame.playTrickBeforeHuman();
					}
					aGame.computeScore();
				}
				System.out.println("Finished with game number " + (i+1));
			}
			System.out.println(aStats.printStatistics());
	}
}