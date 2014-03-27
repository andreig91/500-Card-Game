	package comp303.fivehundred.model;

import comp303.fivehundred.ai.IRobotPlayer;

/**
 * The player class will hold the name and AI robot type of a player.
 * @author Andrei
 */
public class Player
{
	private IRobotPlayer aPlayerType;
	private String aName;
	
	/**
	 * The player constructor takes as input a AI robot type and a name. It creates a new player
	 * with the pPlayerType and pName
	 * @param pPlayerType the type of AI player. Is null if the player is a human player.
	 * @param pName The name of the player
	 */
	public Player(IRobotPlayer pPlayerType, String pName)
	{
		setPlayerType(pPlayerType);
		setName(pName);
	}
	
	/**
	 * Returns the name of the player.
	 * @return The name of the player
	 */
	public String getName()
	{
		return aName;
	}
	
	/**
	 * Returns true if the player is a human, false otherwise.
	 * @return true if the player is a human, false otherwise.
	 */
	public boolean isHuman()
	{
		return aPlayerType == null;
	}
	
	/**
	 * Sets the name of the player to pName.
	 * @param pName 
	 */
	public void setName(String pName)
	{
		aName = pName;
	}
	
	/**
	 * Returns the robot object representing the player.
	 * @return The robot object representing the player
	 */
	public IRobotPlayer getPlayerType()
	{
		return aPlayerType;
	}

	/**
	 * Sets the robot object of the player to pPlayerType.
	 * @param pPlayerType the robot object the player should use.
	 */
	public void setPlayerType(IRobotPlayer pPlayerType)
	{
		this.aPlayerType = pPlayerType;
	}
}
