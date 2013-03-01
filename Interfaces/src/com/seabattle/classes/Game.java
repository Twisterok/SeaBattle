package com.seabattle.classes;

//import java.util.Timer;
import java.util.Vector;

public class Game 
{
	private Vector<Player> 	players;
	private int				turn_owner;
	//private Timer			timer;
	//private int				moveTime;
	
	public Game(Player _player,int _moveTime)
	{
		players = new Vector<Player>();
		players.add(_player);
		turn_owner = 0;
	}
	
	public Game(Vector<Player> _players,int _moveTime)
	{
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
}
