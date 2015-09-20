# EloRater
A simple, command line tool to provide Elo ratings given a list of results from a tournament (and, optionally, a list of existing players and ratings).

INPUT
The program takes two text files as input.  The Rankings file (which can be blank) gives a list of existing players in the database with their ratings.  Any players that show up in the results that aren't in the database already will be given a base Elo rating (specified in ELOCalc.java).  The Results file consists of one match per line, with each line being [game winner] [game loser] in the order the games were played in tournament.

OUTPUT
The program prints out every match listed with the Elo rating difference, winner's Elo change, and loser's Elo change.  It then prints out a list of all players from highest to lowest Elo.  Finally, it asks for a file to output the results to.  The output file will be in the accepted format for input in the future.

MODIFYING THE SYSTEM
The ELOCalc.java file has a bunch of constants you can modify to make the system work more to your liking.  DISTRIBUTION_MEAN should probably be left at 0; a mean of 0 translates to "players with equal ratings have a 50/50 chance of winning the match."  Increasing DISTRIBUTION_SD will keep points won/lost closer to (ELO_CONSTANT/2), while decreasing it will reward more points for upsets and fewer for games that go the way they should.  ELO_BASE has no effect on calculation, it simply means all new players entered in the system will start with this rating.  Since Elo is normally distributed, the total number of Elo points will always remain the same.  Keep in mind that updating this will not update past rating files.  ELO_CONSTANT is the maximum number of points won or lost per game.  SCORE_WIN and SCORE_LOSE should also probably not be modified.
