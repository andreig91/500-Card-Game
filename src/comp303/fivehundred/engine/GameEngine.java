package comp303.fivehundred.engine;

import java.util.Observable;
import comp303.fivehundred.gui.AIObserver;
import comp303.fivehundred.gui.CenterPanel;
import comp303.fivehundred.gui.HumanObserver;
import comp303.fivehundred.model.Bid;
import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Player;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card;
import comp303.fivehundred.util.CardList;
import comp303.fivehundred.util.Constants;
import comp303.fivehundred.util.Deck;

/**
 * The game engine class is strictly a reactive system that can store the entire state of the game, and 
 * provides methods to modify the state according to the rules 500.
 * @author JB, Andrei, Dan
 */
public class GameEngine extends Observable implements Cloneable
{
	private Player[] aPlayers;
	private Hand[] aHands;
	private Bid[] aBids;
	private int[] aTricksWon;
	private int aHumanPlayerIndex;
	private int[] aScores;
	private int[] aNumberOfGamesWon = new int[4];
	private int[] aNumberOfTricksWon = new int[4];
	private int[] aNumberOfBidsWon = new int[4];
	private int[] aNumberBidsAccomplished = new int[4];
	private int[] aTotalScore = new int[4];
	private Bid aWinningBid;
	private Trick aTrick;
	private Deck aDeck;
	private String aLog;
	private int aNumberOfTricksPlayed;
	private int aDealerIndex;
	private int aBidderPositionIndex;
	private int aBidWinnerIndex;
	private int aLastContractWinner;
	private int aTrickLeaderIndex;
	private int aTrickPositionIndex;
	private HumanObserver aHumanPanel;
	private AIObserver[] aAIPanels = new AIObserver[4]; 
	private CenterPanel aCenterPanel;
	
	/**
	 * The constructor for the GameEngine takes as input 4 player objects. It creates a players 
	 * array of size 4 and stores the players given as parameters in the array. It then pass the 
	 * control to the initializeGame() method. 
	 * @param pPlayer1 The first player
	 * @param pPlayer2 The second player
	 * @param pPlayer3 The third player
	 * @param pPlayer4 The fourth player
	 */
	public GameEngine(Player pPlayer1, Player pPlayer2, Player pPlayer3, Player pPlayer4 )
	{
		aPlayers = new Player[4];
		aPlayers[0] = pPlayer1;
		aPlayers[1] = pPlayer2;
		aPlayers[2] = pPlayer3;
		aPlayers[3] = pPlayer4;
		initializeGame();
	}
	
	public void addHumanObserver(HumanObserver pHumanPanel)
	{
		aHumanPanel = pHumanPanel;
	}
	
	public void addAIObserver(AIObserver pAIPanel, int pIndex)
	{
		aAIPanels[pIndex] = pAIPanel;
	}
	
	public void addCenterPanelObserver(CenterPanel pCenterPanel)
	{
		aCenterPanel = pCenterPanel;
	}
	
	private void sleep(long pMilis)
	{
		try
		{
			Thread.sleep(pMilis);
		}
		catch (InterruptedException e)
		{
		}
	}
	
	private void newHand()
	{
		aHumanPanel.newHand(aHands[aHumanPlayerIndex]);
		//sleep(1000);
		aAIPanels[0].handChange(aHands[0].size());
		//sleep(1000);
		aAIPanels[2].handChange(aHands[2].size());
		//sleep(1000);
		aAIPanels[3].handChange(aHands[3].size());
		//sleep(1000);
		aCenterPanel.startBidding();
	}
	
	private void newHumanHand()
	{
		aHumanPanel.newHand(aHands[aHumanPlayerIndex]);
	}
	
	private void newBid(Bid pBid, int pIndex)
	{
		aAIPanels[pIndex].newBid(pBid.toString());
		//sleep(1000);
	}
	
	private void newHumanBid(Bid pBid)
	{
		aHumanPanel.newBid(pBid.toString());
		//sleep(1000);
	}
	
	private void newWinningBid(int pIndex)
	{
		for(int i = 0; i < 4; i++)
		{
			if(i != pIndex)
			{
				if(i == aHumanPlayerIndex)
				{
					aHumanPanel.newBid("");
				}
				else
				{
					aAIPanels[i].newBid("");
				}
				//sleep(600);
			}
		}
	}
	
	public void reinitializeBids()
	{
		for(AIObserver aObserver : aAIPanels)
		{
			if(aObserver != null)
			{
				aObserver.newBid("");
			}
			aHumanPanel.newBid("");
		}
	}
	
	private void biddingOver()
	{
		aCenterPanel.endBidding();
	}
	
	/**
	 * This method is called only once per game. It reinitializes all the values 
	 * that have modified during the previous game. It makes sure that all games
	 * start with the same initial values and that no value gets carried over from
	 * the previous game. The only this that is not reinitialized is the players array
	 * The same player array will be used for all the games that are run using the 
	 * same GameEngine object. If changing the AI Strategy type or changing a player's 
	 * name or changing the position of the human player is required, a new game engine
	 * object needs to be instantiated.
	 */
	public void initializeGame( ) 
	{
		aScores = new int[2];
		aHumanPlayerIndex = getHumanPlayerIndex();	
		aDealerIndex = -1;
		aBidderPositionIndex = -1;
		aNumberOfTricksPlayed = 0;
		initializeRound();
		setRandomDealer();
		aLog = "Game Initialized. Initial Dealer: " + aPlayers[aDealerIndex].getName() + "\n";
		setChanged();
		notifyObservers();
		aLog = "========================NEW GAME==========================\n";
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Randomly sets the dealerIndex to a value between 0 and 3. This 
	 * index represents the player in the player array that is currently the
	 * dealer.
	 */
	private void setRandomDealer()
	{
		aDealerIndex = 0;
	}
	
	/**
	 * The method is called before the very first deal and after every round of
	 * 10 tricks. It reinitializes all the variables which have a scope limited to
	 * a round of 10 tricks. It makes sure that all the rounds are started with the
	 * same initial values.
	 */
	public void initializeRound()
	{
		aBids = new Bid[0];
		aWinningBid = null;
		aHands = new Hand[4];
		aTricksWon = new int[4];
		aTrick = null;
		aDeck = new Deck();
		aBidWinnerIndex = -1;
		aLastContractWinner = -1;
		aTrickLeaderIndex = -1;
		aTrickPositionIndex = -1;
		aNumberOfTricksPlayed = 0;
	}
	
	/**
	 * This method is called at the start of each round and before every bid phase.
	 * Players are each dealt 10 cards one at a time starting to the left of the
	 * dealer and going clockwise. After the deal, the deck will contain 6 more cards
	 * which represent the widow.
	 */
	public void deal( )
	{
		incrementDealer();
		aLog = "***********************NEW DEAL**************************\n";
		setChanged();
		notifyObservers();
		aLog = "Dealer is: " + aPlayers[aDealerIndex].getName() + "\n";
		setChanged();
		notifyObservers();
		aLog = "Players delt cards\n";
		setChanged();
		notifyObservers();
		aDeck = new Deck();
		aHands = new Hand[4];
		for(int i = 0; i < 4; i++)
		{
			aHands[i] = new Hand();
		}
		aDeck.shuffle();
		for(int i = 0; i < Constants.HAND_SIZE; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				Card aCard = aDeck.draw();
				aHands[(j + 1) % 4].add(aCard);
			}
		}
		for(int j = 0; j < 4; j++)
		{
			aLog = getHandString(j) + "\n";
			setChanged();
			notifyObservers();
		}
		newHand();
	}
	
	/**
	 * This method is called after the deal method has been called and each player
	 * has 10 cards in his or her hand. This method will make all the AI, whose turn to
	 * bid comes before the human's turn, place their bid. The bids are kept in a bid array.
	 */
	public void bidBeforeHuman( )
	{
		aBids = new Bid[0];
		aBidderPositionIndex = (aDealerIndex + 1) % 4;
		int i = 0;
		while( aBidderPositionIndex != aHumanPlayerIndex && i < 4)
		{
			Bid aBid = aPlayers[aBidderPositionIndex].getPlayerType().selectBid(aBids, aHands[aBidderPositionIndex]);
			aBids = increaseArraySize(aBids);
			aBids[aBids.length - 1] = aBid;
			aLog = aPlayers[aBidderPositionIndex].getName() + " Bid " + aBid.toString() + "\n";
			newBid(aBid,aBidderPositionIndex);
			i++;
			incrementBidderPosition();
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * This method is called only if there is a human player in the game and only once
	 * it is his turn to bid. It takes the bid passed to it and places it in the bid array.
	 * It finally calls the bidAfterHuman method.
	 * @param pBid The bid the human player has elected to bid.
	 */
	public boolean humanBid(Bid pBid)
	{
		Bid aMaxBid = Bid.max(aBids);
		if(!aMaxBid.isPass() && !pBid.isPass())
		{
			if(pBid.compareTo(aMaxBid) < 1)
			{
				return false;
			}
		}
		aBids = increaseArraySize(aBids);
		aBids[aBids.length - 1] = pBid;
		aLog = aPlayers[aBidderPositionIndex].getName() + " Bid " + pBid.toString() + "\n";
		setChanged();
		notifyObservers();
		incrementBidderPosition();
		newHumanBid(pBid);
		return true;
	}
	
	/**
	 * This method is called after the deal method has been called and each player
	 * has 10 cards in his or her hand. This method will make all the AI, whose turn to
	 * bid comes after the human's turn, place their bid. The bids are kept in a bid array.
	 */
	public void bidAfterHuman()
	{
		// The difference between dealerIndex and bidPositionIndex is the number of remaining AI players before finishing with 
		// the dealer itself. Here this number could be negative, so we have to add 4 for the difference to represent the number
		// of players. We still have to add 1 for the dealer. 
		int aRemainingAIPlayers = (aDealerIndex - aBidderPositionIndex + 4 + 1) % 4;
		for( int i = 0; i< aRemainingAIPlayers; i++)
		{
			Bid aBid = aPlayers[aBidderPositionIndex].getPlayerType().selectBid(aBids, aHands[aBidderPositionIndex]);
			aBids = increaseArraySize(aBids);
			aBids[aBids.length-1] = aBid;
			aLog = aPlayers[aBidderPositionIndex].getName() + " Bid " + aBid.toString() + "\n";
			newBid(aBid,aBidderPositionIndex);
			incrementBidderPosition();
			setChanged();
			notifyObservers();
		}
		biddingOver();
	}
	
	/**
	 * This is a helper method for the 3 bid methods. It increases by one the size of pArray
	 * while keeping the original elements of pArray intact and in the same order.
	 * @param pArray the array of bids for which the size must be increased
	 * @return a new bid array of size one higher that pArray
	 */
	private Bid[] increaseArraySize(Bid[] pArray)
	{      
		Bid[] newList = new Bid[pArray.length + 1];  
		for (int j = 0; j < pArray.length; j++ )
		{  
			newList[j] = pArray[j];
		}  
		return newList;  
	}
	
	/**
	 * 
	 * @return true if all bids are passing bids, otherwise returns false
	 */
	public boolean allPasses()
	{
		for(int i = 0; i < 4; i++)
		{
			if(!aBids[i].isPass())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * This method is only called once there has been at least one non-passing bid entered
	 * during the bid phase. It will get the highest bid in the bid array and store it in
	 * aWinningBid. Then, it will get the index of the player that place that highest bid and
	 * store it in aBidWinnerIndex. This index will represent the player that will lead to the
	 * first trick.
	 */
	public void determineWinningBid()
	{
		aWinningBid = Bid.max(aBids);
		for(int i = 0; i < 4; i++)
		{
			if(aBids[i] == aWinningBid)
			{
				aBidWinnerIndex = i;
			}
		}
		// Offset the BidWinnerIndex to represent the index in the players' array, instead of the index in the aBids.
		aBidWinnerIndex = (aBidWinnerIndex + aDealerIndex + 1) % 4;
		// The one to start the first trick will be the winning bidder
		aTrickLeaderIndex = aBidWinnerIndex;
		aLog = aPlayers[aBidWinnerIndex].getName() + " Has the winning bid: " + aWinningBid.toString() + "\n";
		setChanged();
		notifyObservers();
		newWinningBid(aBidWinnerIndex);
	}
	
	/**
	 * This method is only called once there has been at least one non-passing bid entered
	 * during the bid phase. It will add the 6 cards of the widow to the hand of the player
	 * that has placed the highest bid. Then, if that player is an AI player, its selectCardsToDiscard
	 * method will be called and will take care of removing 6 cards from the AI's hand.
	 */
	public void exchange( )
	{
		aNumberOfBidsWon[aBidWinnerIndex] ++;
		aLog = "The widow containing: ";
		for(int j = 0; j < Constants.EXCHANGE_NUMBER_OF_CARDS; j++)
		{
			Card aCard = aDeck.draw();
			aLog += aCard.toShortString() + " ";
			aHands[aBidWinnerIndex].add(aCard);
		}
		aLog += "is added to " + aPlayers[aBidWinnerIndex].getName() + "'s hand\n";
		setChanged();
		notifyObservers();
		if(aHumanPlayerIndex != aBidWinnerIndex)
		{
			CardList aCardList = aPlayers[aBidWinnerIndex].getPlayerType().selectCardsToDiscard(aBids, aBidWinnerIndex, aHands[aBidWinnerIndex]);
			aLog = aPlayers[aBidWinnerIndex].getName() + " discards " + aCardList.toString() + "\n";
			setChanged();
			notifyObservers();
			for(Card aCard : aCardList)
			{
				aHands[aBidWinnerIndex].remove(aCard);
			}
			aLog = getHandString(aBidWinnerIndex) + "\n";
			setChanged();
			notifyObservers();
		}
		else
		{
			newHumanHand();
		}
	}
	
	/**
	 * This method is called if a human player is in the game and has won the bidding phase.
	 * This method is called after the human player has been given the 6 cards of the widow.
	 * This method will receive the 6 cards the player wishes to discard and will remove them
	 * from his hand.
	 * @param pCardList The list of cards the player has decides to discard.
	 * @pre pCardList.size() == 6
	 * Will not be used for Milestone 2
	 */
	public void humanExchange(CardList pCardList)
	{
		assert pCardList.size() == Constants.EXCHANGE_NUMBER_OF_CARDS;
		for(Card aCard : pCardList)
		{
			aHands[aBidWinnerIndex].remove(aCard);
		}
		aLog = aPlayers[aBidWinnerIndex].getName() + " discards " + pCardList.toString() + "\n";
		setChanged();
		notifyObservers();
		aLog = getHandString(aBidWinnerIndex) + "\n";
		setChanged();
		notifyObservers();
		newHumanHand();
	}
	
	/**
	 * This method will be call for the first time after exchange() has completed. It will then be called 
	 * 9 more times (10 tricks will then have been played). This method will make all the AI, whose turn to
	 * play a card comes before the human's turn, play a card. When a card is played, it is removed from the
	 * player's hand and added to aTrick. The trick is played starting with the trick leader and continuing 
	 * clockwise.
	 */
	public void playTrickBeforeHuman()
	{
		aNumberOfTricksPlayed++;
		aLog = "--------------------TRICK " + aNumberOfTricksPlayed + "---------------------\n"; 
		setChanged();
		notifyObservers();
		aTrick = new Trick(aWinningBid);
		Card aCard;
		int i = 0;
		aTrickPositionIndex = aTrickLeaderIndex;
		while(aTrickPositionIndex != aHumanPlayerIndex && i < 4)
		{
			aLog = getHandString(aTrickPositionIndex);
			aCard = aPlayers[aTrickPositionIndex].getPlayerType().play(aTrick, aHands[aTrickPositionIndex]);
			aTrick.add(aCard);
			aHands[aTrickPositionIndex].remove(aCard);
			aLog += " plays " + aCard.toString() + "\n";
			setChanged();
			notifyObservers();
			i++;
			incrementPlayerPosition();
		}
		if(aHumanPlayerIndex == -1)
		{
			determineTrickWinner();
		}
	}
	
	/**
	 * This method will be called once it is the human player's turn to play a card. pCard, the card
	 * the human player decided to play will be removed from his hand and added to aTrick.
	 * playTrickAfterHuman() is then called
	 * @param pCard the card the human player elected to play for this trick.
	 */
	@SuppressWarnings("unused")
	private void humanPlayTrick(Card pCard)
	{
		aTrick.add(pCard);
		aHands[aTrickPositionIndex].remove(pCard);
		aLog = getHandString(aTrickPositionIndex);
		aLog += " plays " + pCard.toString() + "\n";
		incrementPlayerPosition();
		playTrickAfterHuman();
	}
	
	/**
	 * This method is called if there is a human player in the game and after he has played a card. The
	 * method will make all the AI players, whose turn to play comes after the human, play a card. The
	 * card played will be removed from the AI's hand and added to aTrick.
	 */
	private void playTrickAfterHuman()
	{
		aNumberOfTricksPlayed++;
		aLog = "--------------------TRICK " + aNumberOfTricksPlayed + "---------------------\n"; 
		setChanged();
		notifyObservers();
		int aRemainingAIPlayers = (aTrickLeaderIndex - aTrickPositionIndex + 4) % 4;
		for( int i = 0; i< aRemainingAIPlayers; i++)
		{
			aLog = getHandString(aTrickPositionIndex);
			Card aCard = aPlayers[aTrickPositionIndex].getPlayerType().play(aTrick, aHands[aTrickPositionIndex]);
			aTrick.add(aCard);
			aHands[aTrickPositionIndex].remove(aCard);
			aLog += " plays " + aCard.toString() + "\n";
			setChanged();
			notifyObservers();
			incrementPlayerPosition();
		}
		determineTrickWinner();
	}
	
	/**
	 * This method is called after all the 4 cards have been played for a given trick. The method
	 * will determine the index of the player in the player array who played the highest card of the trick.
	 * That player will be set as the next trick's leader and will lead that trick. The number of tricks
	 * won by the winning player will also be incremented by 1.
	 */
	private void determineTrickWinner()
	{
		aTrickLeaderIndex = (aTrick.winnerIndex() + aTrickLeaderIndex) % 4;
		aLog = aPlayers[aTrickLeaderIndex].getName() + " Won the trick\n";
		setChanged();
		notifyObservers();
		aTricksWon[aTrickLeaderIndex]++;
	}
	
	/**
	 * This method is called only after all 10 tricks of a given round have been played. The method
	 * determines if the contractor has made the contract or not. Points are added to the aScores array
	 * accordingly. 
	 */
	public void computeScore()
	{
		aLog = "--------------------SCORING--------------------\n";
		setChanged();
		notifyObservers();
		int aContractorTeam = (aBidWinnerIndex + 2) % 2;
		int aDefenderTeam = (aContractorTeam + 3) % 2;
		int aContractPoints = aWinningBid.getScore();
		int[] aTeamTricks = {aTricksWon[0] + aTricksWon[2], aTricksWon[1] + aTricksWon[3]};
		aLog = aPlayers[aBidWinnerIndex].getName() + " Has the contract of " + aWinningBid.toString() + "\n";
		setChanged();
		notifyObservers();
		for(int i = 0; i < 2; i++)
		{
			aLog = aPlayers[i].getName() + " and " + aPlayers[i + 2].getName() + " won " + aTeamTricks[i] + " tricks\n";
			setChanged();
			notifyObservers();
		}
		for(int i = 0; i < 4; i++)
		{
			aNumberOfTricksWon[i] += aTricksWon[i];
		}
		for(int i = 0; i < 2; i++)
		{
			if(aContractorTeam == i)
			{
				if(aTeamTricks[i] >= aWinningBid.getTricksBid())
				{
					aNumberBidsAccomplished[i]++;
					aNumberBidsAccomplished[i+2]++;
					aLog = aPlayers[i].getName() + " and " + aPlayers[i + 2].getName() + " make their contract!\n";
					setChanged();
					notifyObservers();
					aLog = "Contractors round score: " + aContractPoints + "\n";
					setChanged();
					notifyObservers();
					aScores[aContractorTeam] += aContractPoints;
					aScores[aDefenderTeam] += aTeamTricks[aDefenderTeam] * Constants.NUMBER_POINTS_PER_TRICK;
					aLastContractWinner = i;
				}
				else
				{
					aLog = aPlayers[i].getName() + " and " + aPlayers[i + 2].getName() + " didn't make their contract\n";
					setChanged();
					notifyObservers();
					aLog = "Contractors round score: " + "-" + aContractPoints + "\n";
					setChanged();
					notifyObservers();
					aScores[aContractorTeam] -= aContractPoints;
					aScores[aDefenderTeam] += aTeamTricks[aDefenderTeam] * Constants.NUMBER_POINTS_PER_TRICK;
					aLastContractWinner = -1;
				}
				aLog = "Defenders round score: " + aTeamTricks[aDefenderTeam] * Constants.NUMBER_POINTS_PER_TRICK + "\n";
				setChanged();
				notifyObservers();
			}
		}
		for(int i = 0; i < 2; i++)
		{
			aLog = aPlayers[i].getName() + " and " + aPlayers[i + 2].getName() + " overall score: " + aScores[i] + "\n";
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Increments the dealer position. It is called by the driver when the round of tricks is over
	 */
	public void incrementDealer()
	{
		aDealerIndex = (aDealerIndex + 1) % 4;
	}
	
	/**
	 * Returns true if the game is over and false otherwise. The game is over once one of the 2 teams
	 * has reached 500 points while also having won the current contract. 
	 * @return true if the game is over and false otherwise.
	 */
	public boolean isGameOver()
	{
		for(int i = 0; i < 2; i++)
		{
			if(aScores[i] >= Constants.WINNING_SCORE && aLastContractWinner == i )
			{
		
				aNumberOfGamesWon[i]++;
	
				aNumberOfGamesWon[i + 2]++;
				
				aTotalScore[0] = aTotalScore[0] + aScores[0];
				aTotalScore[1] = aTotalScore[1] + aScores[1];
				aTotalScore[2] = aTotalScore[2] + aScores[0];
				aTotalScore[3] = aTotalScore[3] + aScores[1];
				 
				setChanged();
				notifyObservers();				
				setChanged();
				notifyObservers();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void notifyObservers()
	{
		super.notifyObservers(aLog);
	}
	
	/**
	 * This method is called every time a player places a bid. The method will increment the position of
	 * the next player to place a bid using a modulo so that the value of the bid position stays between
	 * 0 and 3.
	 */
	private void incrementBidderPosition()
	{
		aBidderPositionIndex = (aBidderPositionIndex + 1) % 4;		
	}
	
	/**
	 * This method is called every time a player plays a card for a trick. The method will increment the 
	 * position of the next player to play a card using a modulo so that the value of the playing position 
	 * stays between 0 and 3.
	 */
	private void incrementPlayerPosition()
	{
		aTrickPositionIndex = (aTrickPositionIndex + 1) % 4;
	}
	
	/**
	 * This method returns the index of the human player in the player array. 
	 * @return the index of the human player between 0 and 3. 
	 * Returns -1 if there is no human player in the game
	 */
	public int getHumanPlayerIndex()
	{
		for(int i = 0; i < 4; i++)
		{
			if(aPlayers[i].isHuman())
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns a string representation of the cards in the hand of player at position pIndex.
	 * @param pIndex The index of a player in the player array
	 * @return A string representation of the cards in the hand of player at position pIndex
	 */
	private String getHandString(int pIndex)
	{
		String aString;
		aString = aPlayers[pIndex].getName() + " cards: " + aHands[pIndex].toString();
		return aString;
	}
	
	/**
	 * Returns the bid array containing the bids entered so far for the current round.
	 * @return the Bids for this round
	 */
	public Bid[] getBids()
	{
		return aBids;
	}
	
	/**
	 * Returns the array of hands.
	 * @return the array of hands
	 */
	public Hand[] getHands()
	{
		return aHands;
	}
	
	/**
	 * Returns the array of the Total scores accumulated by the players.
	 * @return the array the total scores of the players
	 */
	public int[] getScoreStats() 
	{
	    int[] clone = (int[]) aTotalScore.clone(); 
	    return clone;
	}
	
	/**
	 * Returns the array the Total Tricks wan by the players.
	 * @return the array the total tricks wan by the players
	 */
	public int[] getTricksStats() 
	{
	    int[] clone = (int[]) aNumberOfTricksWon.clone(); 
	    return clone;
	}

	/**
	 * Returns the array the Total Bids wan by the players.
	 * @return the array the total Bids wan by the players
	 */
	public int[] getBidsWonStats() 
	{
	    int[] clone = (int[]) aNumberOfBidsWon.clone(); 
	    return clone;
	}

	/**
	 * Returns the array the Total Bids Accomplished by the players.
	 * @return the array the total Bids Accomplished by the players
	 */
	public int[] getBidsAccomplishedStats() 
	{
	    int[] clone = (int[]) aNumberBidsAccomplished.clone(); 
	    return clone;
	}

	/**
	 * Returns the array the players.
	 * @return the array the players
	 */
	public Player[] getPlayersStats() 
	{
	    Player[] clone = (Player[]) aPlayers.clone(); 
	    return clone;
	}
	
	/**
	 * Returns the Total Games wan by the players.
	 * @return the Total Games wan by the players
	 */
	public int[] getGamesWonStats() 
	{
	    int[] clone = (int[]) aNumberOfGamesWon.clone(); 
	    return clone;
	}
}