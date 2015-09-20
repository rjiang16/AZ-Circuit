package elo;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ELOCalc {

	// CONSTANTS - feel free to change for different systems
	private static final int DISTRIBUTION_MEAN = 0; // I wouldn't recommend changing this one
	private static final int DISTRIBUTION_SD = 25;
	public static final int ELO_BASE = 1500;
	private static final int ELO_CONSTANT = 30;
	private static final int SCORE_WIN = 1;
	private static final int SCORE_LOSE = 0;
	
	// newElo
	// Purpose: Return a player's updated ELO
	// Input: player's rating, opponent's rating, whether or not the player won
	// Output: player's updated rating
	public static int newElo(int yourRating, int theirRating, boolean winner)
	{
		int score;
		if(winner)
			score = SCORE_WIN;
		else
			score = SCORE_LOSE;
		
		return (int) (yourRating + (ELO_CONSTANT * (score - expectedScore(yourRating, theirRating))));
	}
	
	// expectedScore
	// Purpose: Return the expected score from a game
	// Input: player's rating, opponent's rating
	// Output: expected score (a probability from 0 to 1)
	private static double expectedScore(int yourRating, int theirRating)
	{
		NormalDistribution distribution = new NormalDistribution(DISTRIBUTION_MEAN, DISTRIBUTION_SD);
		return distribution.cumulativeProbability(yourRating - theirRating);
	}
}
