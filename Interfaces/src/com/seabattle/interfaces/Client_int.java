package com.seabattle.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.seabattle.classes.Field;

/*[INTERFACE ANNOTATION]
 * -------------------------------------
 *int push_turn(Field curField,boolean myTurn) 	
 *	#pushes the new Field on Client side
 *	#NOTE: myTurn set client know was it his move or enemy's
 *
 *-------------------------------------
 *int push_end(Integer description)
 *	#makes client know that game is over
 *	#descriptions:
 *		-int WON		 		= 600;		//player-handler has won
 *		-int LOST	 			= 601;		//player-handler has lost
 *		-int DECLINED			= 602;		//player declined the invation
 *		-int CREATION_ERROR		= 603;		//game creation error
 *	# this constants you can find in the file: com.seabattle.classes.CallbackConstants.java
 *
 *-------------------------------------
 *int wanna_play(String creator)
 *	#makes client know that player(creator) wanna play with him
 *
 *-------------------------------------
 *int game_created()
 *	#makes players of the game know that it's the time to place ships
 *
 *-------------------------------------
 * int game_started(String turnOwner)
 * 	#makes players of the game know that game is started
 * 	#and it's the time to make a turn
 * 
 * -------------------------------------
 */

public interface Client_int extends Remote {
	
	boolean hello()							throws RemoteException;
	int push_turn(Field curField
					,boolean myTurn
					,boolean myField)		throws RemoteException;
	int push_end(Integer description)		throws RemoteException;
	int wanna_play(String creator)			throws RemoteException;
	int game_created()						throws RemoteException;
	int game_started(String turnOwner)		throws RemoteException;
}
