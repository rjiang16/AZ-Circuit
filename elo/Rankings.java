package elo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Rankings {

	public static void main(String[] args)
	{
		Scanner userInput = new Scanner(System.in);
		ArrayList<Player> playerList = new ArrayList<Player>();

		try
		{
			// Get existing rankings file
			System.out.println("Name of rankings file?");
			File rankingsFile = new File(userInput.next());
			
			// Initialize list of players for ranking file
			Scanner rankingsInput = new Scanner(rankingsFile);
			while(rankingsInput.hasNextLine())
			{
				playerList.add(new Player(rankingsInput.next(), rankingsInput.nextInt()));
				rankingsInput.nextLine();
			}
			rankingsInput.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("ERROR: Could not find rankings file");
			System.exit(1);
		}
		
		// Get results file
		System.out.println("Name of results file?");
		File resultsFile = new File(userInput.next());
		
		// Read through results file
		try
		{
			Scanner resultsInput = new Scanner(resultsFile);
			while(resultsInput.hasNextLine())
			{
				int winner = -1;
				int loser = -1;
				
				String winnerName = resultsInput.next();
				String loserName = resultsInput.next();
				
				// Find winner
				for(int i = 0; i < playerList.size(); i++)
				{
					if(playerList.get(i).getName().equals(winnerName))
					{
						winner = i;
						break;
					}
				}
				// If winner not found, make a new player
				if(winner < 0)
				{
					playerList.add(new Player(winnerName, ELOCalc.ELO_BASE));
					winner = playerList.size() - 1;
				}
				
				// Find loser
				for(int i = 0; i < playerList.size(); i++)
				{
					if(playerList.get(i).getName().equals(loserName))
					{
						loser = i;
						break;
					}
				}
				// If loser not found, make a new player
				if(loser < 0)
				{
					playerList.add(new Player(loserName, ELOCalc.ELO_BASE));
					loser = playerList.size() - 1;
				}
				
				/* NOTE: In order to prevent rounding from removing ELO points, 
				 * the program only calculates lost points from the loser and
				 * then adds that number of points to the winner's score. */
				int winnerElo = playerList.get(winner).getElo();
				int loserElo = playerList.get(loser).getElo();
				playerList.get(loser).setElo(ELOCalc.newElo(loserElo, winnerElo, false));
				int pointsLost = loserElo - playerList.get(loser).getElo();
				playerList.get(winner).setElo(winnerElo + pointsLost);
				
				// DEBUG: Print game results (Elo won and lost)
				System.out.println(playerList.get(winner).getName().toUpperCase() + " vs. " + 
						playerList.get(loser).getName().toUpperCase() + " (" +
						(winnerElo - loserElo) + ")");
				System.out.println("(W) " + playerList.get(winner).getName() + ": " + winnerElo +
						" --> " + playerList.get(winner).getElo() + " (+" + pointsLost +
						")");
				System.out.println("(L) " + playerList.get(loser).getName() + ": " + loserElo + 
						" --> " + playerList.get(loser).getElo() + " (-" + pointsLost +
						")");
				System.out.println();
			}
			resultsInput.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("ERROR: Could not find results file");
			System.exit(1);
		}

		// Sort players by ELO
		Collections.sort(playerList);
		
		// DEBUG: Print list of players sorted by ELO
		for(int i = 0; i < playerList.size(); i++)
			System.out.println(playerList.get(i));
		System.out.println();
		
		// Ask for output file
		System.out.println("Name of output file?");
		File outputFile = new File(userInput.next());
		userInput.close();
		
		// Output results
		try
		{
			PrintWriter fileWriter = new PrintWriter(outputFile);
			for(int i = 0; i < playerList.size(); i++)
				fileWriter.println(playerList.get(i).getName() + " " + playerList.get(i).getElo());
			fileWriter.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("ERROR: Could not output to file '" + outputFile + "'");
			System.exit(1);
		}
	}
}
