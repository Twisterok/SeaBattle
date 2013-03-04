package com.seabattle.classes;

//import java.util.Timer;
import java.util.Vector;

public class Game 
{
	public static final int movetime = 45;
	private boolean			isStarted;
	private Vector<Player> 	players;
	private int				turn_owner;
	
	public Game(Player _player)
	{
		setStarted(false);
		players = new Vector<Player>();
		players.add(_player);
		turn_owner = 0;
	}
	
	public Game(Vector<Player> _players)
	{
		setStarted(false);
		players = _players;
		//moveTime = _moveTime;
		turn_owner = 0;
	}
	
	public Vector<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(Vector<Player> players) {
		this.players = players;
	}
/*
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
*/
	public int getTurn_owner() {
		return turn_owner;
	}	
	
	public void nextTurn_owner()
	{
		this.turn_owner +=1;
		this.turn_owner = turn_owner%2;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Game))return false;
	    Game otherMyClass = (Game)other;
	    
	    if (this.getPlayers().contains(otherMyClass.getPlayers().firstElement()))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
}
