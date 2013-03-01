package com.seabattle.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.seabattle.classes.Field;

public interface Client_int extends Remote {
	String hello() throws RemoteException;
	
	int push_turn(Field curField)		throws RemoteException;
	int push_end(Integer description)	throws RemoteException;
	int wanna_play(String creator)		throws RemoteException;
	int game_created()					throws RemoteException;
	int game_started(String turnOwner)	throws RemoteException;
}
