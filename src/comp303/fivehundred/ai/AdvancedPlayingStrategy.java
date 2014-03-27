package comp303.fivehundred.ai;

import comp303.fivehundred.model.Hand;
import comp303.fivehundred.model.Trick;
import comp303.fivehundred.util.Card;

/**
 * If leading 
 * If no trump, choose suit that you have the most, and play middle card from that suit.
 * If trump, if master, if in 4 first plays, play middle trump. If 6 last, play highest no trump cards
 * or (if cannot) place from highest trumps. 
 * if not master, play the highest non-trump suit of which you have the less. If only trumps, play middle 
 * no-trump. 
 * If not leading
 *  If trump
 * If friend is winning, dump lowest of non-winnning cards. If you have only winning cards, lowest of winning cards. 
 * If left AI is starting and opponents are winning, if you can beat them, use lowest of winning; if not, use lowest of losing. 
 * if right AI is winning (but youre not last to play), play highest winning no trump or middle of winning trump
 *  If no trump
 * If partner is winning, play lowest losing, or lowest winning if you dont have any. 
 * If fourth or second to play, play lowest winning. 
 * If third to play, play highest winning.  
 * @author JeanBenoit
 *
 */
public class AdvancedPlayingStrategy implements IPlayingStrategy
{

	@Override
	public Card play(Trick pTrick, Hand pHand)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
