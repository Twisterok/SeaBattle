package com.seabattle.classes;

import java.util.Vector;

public class Chatroom {
	private StringBuffer history;
	private Vector<Player>	users;

	
	public Chatroom() {
		users = new Vector<Player>();
		history = new StringBuffer();
	}
	
	public Chatroom(Player pl)
	{
		users = new Vector<Player>();
		users.add(pl);
		history = new StringBuffer();
	}
	
	public Chatroom(Vector<Player> players)
	{
		users = players;
		history = new StringBuffer();
	}
	
	public Vector<Player> getUsers() {
		return users;
	}
	public void setUsers(Vector<Player> users) {
		this.users = users;
	}
	
	public int addUser(Player newUser)
	{
		if (users.indexOf(newUser) >= 0)
			return CallbackConstants.BAD;
		users.add(newUser);
		return CallbackConstants.GOOD;
	}
	
	public int removeUser(Player toRemove)
	{
		if (users.indexOf(toRemove)<0)
			return CallbackConstants.BAD;
		
		users.remove(toRemove);
		return CallbackConstants.GOOD;
	}
	
	public void newMessage(String message)
	{
		history.append(message+"\n");
	}
	
	public String getHistory()
	{
		return history.toString();
	}
	
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Chatroom))return false;
	    Chatroom otherMyClass = (Chatroom)other;
	    
	    if (this.getUsers().contains(otherMyClass.getUsers().firstElement()))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
}
