package com.seabattle.classes;

import com.seabattle.interfaces.Client_int;

public class Player {
	public static final boolean ONLINE 		= true;
	public static final boolean OFFLINE 	= false;
	public static final boolean PLAYING 	= true;
	
	private Account me;
	private Client_int remObj;
	private boolean isPlaying;
	private boolean isLogged;
	private Field	field;
	
	public Player()
	{
		me = new Account();
		remObj = null;
		isPlaying 	= false;
		isLogged 	= false;
		field		= new Field();
	}
	public Player(Account _me,Client_int _remObj)
	{
		me 			= _me;
		remObj 		= _remObj;
		isPlaying 	= false;
		isLogged 	= false;
		field		= new Field();
	}
	
	public Player(String _login,String _passwd,Client_int _remObj)
	{
		me = new Account(_login,_passwd);
		remObj = _remObj;
		isPlaying 	= false;
		isLogged 	= false;
		field		= new Field();
	}
	
	public Account getMe() {
		return me;
	}
	
	public Client_int getRemObj() {
		return remObj;
	}

	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public boolean isPlaying() {
		return isPlaying;
	}
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Player))return false;
	    Player otherMyClass = (Player)other;
	    
	    if (otherMyClass.getMe().equals(this.getMe()))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
}
