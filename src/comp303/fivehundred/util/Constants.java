package comp303.fivehundred.util;

/**
 * 
 * @author JeanBenoit
 * Contains important Constants to the game. 
 */
public final class Constants
{
	
	public static final int PERCENTAGE_MULTIPLICATOR = 100;
	
	public static final int HAND_SIZE = 10;
	public static final int MAX_HAND_SIZE = 16; //Including Widow
	public static final int NUMBER_OF_PLAYERS = 4;
	public static final int LOWEST_BID = 6;
	public static final int HIGHEST_BID = 10;
	public static final int LARGEST_NUMBER_OF_BIDS = 3;
	public static final int LOWEST_PLAYER_INDEX = 0;
	public static final int HIGHEST_PLAYER_INDEX = 3;
	
	public static final int WINNING_SCORE = 500;

	public static final int EXCHANGE_NUMBER_OF_CARDS = 6;
	public static final int NUM_SUITS_FOR_BIDDING = 5; //including no trump
	public static final int MIN_WINNING_SCORE = 40;
	public static final int SCORE_INCREMENT_BETWEEN_INDEXES = 20;
	public static final int MIN_NO_TRUMP_BIDDING_INDEX = 20;
	public static final int MAX_BIDDING_INDEX = 24;
	public static final int NUMBER_POINTS_PER_TRICK = 10;
	
	//BASIC AI CONSTANTS:
	public static final int BASIC_AI_LONG_SUIT_LOWER_RANGE_LB = 5;
	public static final int BASIC_AI_LONG_SUIT_LOWER_RANGE_UB = 6;
	public static final int BASIC_AI_LONG_SUIT_LOWER_RANGE_SCORE = 20;
	public static final int BASIC_AI_LONG_SUIT_MIDDLE_RANGE_LB = 7;
	public static final int BASIC_AI_LONG_SUIT_MIDDLE_RANGE_UB = 8;
	public static final int BASIC_AI_LONG_SUIT_MIDDLE_RANGE_SCORE = 25;
	public static final int BASIC_AI_LONG_SUIT_UPPER_RANGE_LB = 9;
	public static final int BASIC_AI_LONG_SUIT_UPPER_RANGE_UB = 10;
	public static final int BASIC_AI_LONG_SUIT_UPPER_RANGE_SCORE = 30;
	public static final int BASIC_AI_LONG_SUIT_POINTS_DIVISOR = 1; //10
	public static final int BASIC_AI_PARTNER_BID_SAME_SUIT_SCORE = 2;
	public static final int BASIC_AI_JOKER_POINTS = 15;
	public static final int BASIC_AI_JOKER_POINTS_DIVISOR = 1; //10
	public static final int BASIC_AI_TOTAL_STRONG_CARDS_POINTS_DIVISOR = 1; //10
	public static final int BASIC_AI_ACE_POINTS = 13;
	public static final int BASIC_AI_KING_POINTS = 10;
	public static final int BASIC_AI_QUEEN_POINTS = 9;
	public static final int BASIC_AI_JACK_POINTS = 8;
	public static final int BASIC_AI_TEN_POINTS = 6;
	public static final int BASIC_AI_STRONG_CARDS_POINTS_DIVISOR = 5;
	public static final int BASIC_AI_LONG_NAME_COMPLETION = 5;
	public static final int BASIC_AI_SECONDS_CONVERSION = 1000;
	public static final int BASIC_AI_MIDDLE_FREQ = 5;
	public static final int BASIC_AI_MIDDLE_FREQ_MULTIPLIER = 5;
	public static final int BASIC_AI_UPPER_MIDDLE_FREQ = 6;
	public static final int BASIC_AI_UPPER_MIDDLE_FREQ_MULTIPLIER = 6;
	public static final int BASIC_AI_UPPER_FREQ = 7;
	public static final int BASIC_AI_UPPER_FREQ_MULTIPLIER = 8;
	public static final int BASIC_AI_JOKER_POINTS_DIVISOR_ESTIMATE = 15;
	public static final int BASIC_AI_STRONG_CARDS_POINTS_DIVISOR_ESTIMATE = 15;
	public static final int BASIC_AI_LONG_SUIT_POINTS_DIVISOR_ESTIMATE = 5;
	public static final int BASIC_AI_JOKER_POINTS_DIVISOR_NOTRUMP = 15;
	public static final int BASIC_AI_TOTAL_STRONG_CARDS_POINTS_DIVISOR_NOTRUMP = 8;
	
	private Constants(){}
	
}
