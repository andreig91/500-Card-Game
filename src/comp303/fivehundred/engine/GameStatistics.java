package comp303.fivehundred.engine;

import java.util.Observable;
import java.util.Observer;
import java.text.DecimalFormat;

import comp303.fivehundred.model.Player;
import comp303.fivehundred.util.Constants;

/**
 * @author Dan
 */
public class GameStatistics implements Observer
{
	private Player[] aPlayers;
	private int[] aNumberOfGamesWon = new int[4];
	private int[] aNumberOfBidsWon = new int[4];
	private int[] aNumberOfTricksWon = new int[4];
	private int[] aNumberBidsAccomplished = new int[4];
	private int[] aTotalScore = new int[4];

	/**
	 * 
	 */
	public GameStatistics()
	{

	}
	
	/**
	 * method returns the number of games played.
	 * @return an integer representing the games played
	 */
	public int gamesPlayed()
	{
		return aNumberOfGamesWon[0] + aNumberOfGamesWon[1] ;
	}
	
	/**
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return a double representing the the number of tricks wan by the player vs. total
	 */
	public double trickPecentageWon(int pPlayerIndex)
	{
		int totalTricksPLayed = aNumberOfTricksWon[0]+aNumberOfTricksWon[1]+aNumberOfTricksWon[2]+aNumberOfTricksWon[3];
		return (double)aNumberOfTricksWon[pPlayerIndex] / totalTricksPLayed * Constants.PERCENTAGE_MULTIPLICATOR;
	}
	
	/**
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return a double representing the number of contracts wan by the player vs. total
	 */
	public double contractsWonPercentage(int pPlayerIndex)
	{
		int totalBidsDone = aNumberOfBidsWon[0]+aNumberOfBidsWon[1]+aNumberOfBidsWon[2]+aNumberOfBidsWon[3];	
		return (double)aNumberOfBidsWon[pPlayerIndex] / totalBidsDone * Constants.PERCENTAGE_MULTIPLICATOR;
	}
	
	/**
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return a double representing the number of contracts made by the player vs. total
	 */
	public double contractsMadePercentage(int pPlayerIndex)
	{
		int totalBidsAccomplished = aNumberBidsAccomplished[0]+aNumberBidsAccomplished[1]+aNumberBidsAccomplished[2]+aNumberBidsAccomplished[3];
		return (double)aNumberBidsAccomplished[pPlayerIndex] / totalBidsAccomplished * Constants.PERCENTAGE_MULTIPLICATOR;
	}
	
	/**
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return a double representing the games wan by the player vs. total
	 */
	public double gamePecentageWon(int pPlayerIndex)
	{
		return (double)aNumberOfGamesWon[pPlayerIndex] / gamesPlayed() * Constants.PERCENTAGE_MULTIPLICATOR;
	}
	
	/**
	 * this method return the score index of the player.
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return the score index of the player
	 */
	public double scoreIndex(int pPlayerIndex)
	{
		return (double)aTotalScore[pPlayerIndex] / (gamesPlayed() * Constants.WINNING_SCORE);
	}
	
	/**
	 * this method returns spaces to move the text a predetermined distance.
	 * @return the string of empty spaces in order to move the top line as far as "Games PLayed: " is long
	 */
	public String spaceGap()
	{
		String initialSpace = "";
		for(int i = 0; i<"Games Played: ".length() ; i++)
		{
			initialSpace = initialSpace + " ";
		}
			return initialSpace;
	}
	
	/**
	 * This method returns the names of the players plus some extra space to normalize the spacing. 
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return the string of the names of the players plus the required spaces to equalise the lines
	 */
	public String variableSpaces(int pPlayerIndex)
	{
		String initialSpace = "";
		if(aPlayers[pPlayerIndex].getName().length() >= spaceGap().length())
		{
			for(int i = 0; i < 4; i++)
			{
				initialSpace = initialSpace + aPlayers[pPlayerIndex].getName().charAt(i);
			}
			initialSpace = initialSpace + ".";
			for(int i = 0; i < spaceGap().length()-Constants.BASIC_AI_LONG_NAME_COMPLETION; i++)
			{
				initialSpace = initialSpace + " ";
			}
			
			
		}
		else
		{
			for(int i = 0; i < aPlayers[pPlayerIndex].getName().length(); i++)
			{
				initialSpace = initialSpace + aPlayers[pPlayerIndex].getName().charAt(i);
			}
			for(int i = 0; i < spaceGap().length()-aPlayers[pPlayerIndex].getName().length(); i++)
			{
				initialSpace = initialSpace + " ";
			}
		}
		return initialSpace;
	}
	
	/**
	 * The method executes all the helper methods in order to return the string containing each player's statistics.
	 * Decimal format library is used in order to adjust the significant figures of the data.
	 * @param pPlayerIndex the index of the player in the Player array.
	 * @return the string containing a specific player's statistics.
	 */
	public String playerStatsLine(int pPlayerIndex)
	{
		DecimalFormat oneDigit = new DecimalFormat("#,##00.0"); //format to 1 decimal place
		DecimalFormat oneDigitPercent = new DecimalFormat("#,##000.0"); //format to 1 decimal place
		String line = variableSpaces(pPlayerIndex) + oneDigit.format(trickPecentageWon(pPlayerIndex)) +"%  ";
		line = line + oneDigit.format(contractsWonPercentage(pPlayerIndex))+ "%  ";
		line = line + oneDigit.format(contractsMadePercentage(pPlayerIndex)) + "%  ";
		return line + oneDigitPercent.format(gamePecentageWon(pPlayerIndex))+ "%  " + oneDigit.format(scoreIndex(pPlayerIndex))+ "\n";

	}
	
	/**
	 * The method generates the lines of the string that represent the statistics of the players. It calls the playerStatsLine
	 * for each player.
	 * @return the string representing the statistics linked to all the players.
	 */
	public String printStatistics()
	{
		String stats;
		stats = "\n\n" + "Games Played: " + gamesPlayed() + "\n\n";
		stats = stats + spaceGap() + "Trick  Cont   Made   Game    Score\n";
		stats = stats + playerStatsLine(0);
		stats = stats + playerStatsLine(1);
		stats = stats + playerStatsLine(2);
		stats = stats + playerStatsLine(3);
		stats = stats + "\n";
		return stats;
	
		
	}

	/**
	 * @param pObject 
	 * @param pArg 
	 */
	@Override
	public void update(Observable pObject, Object pArg)
	{
		aNumberOfGamesWon = ((GameEngine)pObject).getGamesWonStats();
		aTotalScore = ((GameEngine) pObject).getScoreStats();
		aNumberOfTricksWon = ((GameEngine) pObject).getTricksStats();
		aNumberOfBidsWon = ((GameEngine) pObject).getBidsWonStats();
		aNumberBidsAccomplished = ((GameEngine) pObject).getBidsAccomplishedStats();
		aPlayers = ((GameEngine) pObject).getPlayersStats();
	}
}
