package elo;

public class Player implements Comparable {

	private String name;
	private int elo;
	
	public Player(String n, int e)
	{
		name = n;
		elo = e;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getElo()
	{
		return elo;
	}
	
	public void setElo(int e)
	{
		elo = e;
	}
	
	public boolean equals(Player other)
	{
		return name.equals(other.getName());
	}
	
	public String toString()
	{
		return name + ": " + elo;
	}

	@Override
	public int compareTo(Object other) {
		Player otherPlayer = (Player) other;
		
		if(elo > otherPlayer.getElo())
			return -1;
		else if(elo == otherPlayer.getElo())
			return 0;
		else
			return 1;
	}
}
