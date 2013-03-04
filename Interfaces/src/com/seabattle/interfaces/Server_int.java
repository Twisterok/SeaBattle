package com.seabattle.interfaces;

/*
 * 	This file defines all remote functions, that will be provided by the server 
 * 	Server provides Java RMI.
 * 	More information about Java RMI (Java Remote) you can get here:
 *  http://docs.oracle.com/javase/tutorial/rmi/index.html
 *  
 *  for russians:	http://habrahabr.ru/post/74639/
 * 
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.seabattle.classes.*;

/*[INTERFACE ANNOTATION]
 * -------------------------------------
 * int Sign_in	(String login,String pswd,Client_int remObj)
 * 		#Log in the player on the server side
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.AUTORISATION_ERROR
 * 			-com.seabattle.classes.CallbackConstants.USER_ONLINE
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 
 * -------------------------------------
 * int Sign_up	(String login,String pswd)
 * 		#Register the player on the server side and in the DB
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.USER_EXISTS
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------
 * int Sign_out(String login)
 * 		#Log out the player on the server side
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.UNAUTORISED_USER
 * 			-com.seabattle.classes.CallbackConstants.USER_PLAYS
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * -------------------------------------
 * int Turn	(Integer x, Integer y,String login)			
 * 		#Tries to make the move in the game which contains the player(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.UNAUTORISED_USER
 * 			-com.seabattle.classes.CallbackConstants.WRONG_MOVE
 * 			-com.seabattle.classes.CallbackConstants.NO_GAME
 * 			-/...
 *  
 *  -------------------------------------	
 * int New_game(String me,String enemy)	
 * 		#Asks the enemy does he wanna play or not
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.WAITING
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------				
 * int Accept	(String me,String enemy)	
 * 		#Accepts the invite to play a game from the enemy
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD	
 * 
 * -------------------------------------		
 * int Ready	(Field field, String user)		
 * 		#Says to the server that player has placed his ships and he's ready to fight
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.INNER_ERROR
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.WAITING	
 * 
 * -------------------------------------		
 * int Leave	(String login)	
 * 		#Tries to leave the game
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.UNAUTORISED_USER
 * 			-com.seabattle.classes.CallbackConstants.NO_LEAVE
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 
 * -------------------------------------							
 * int 	Change_name(String login,String newName)	
 * 		#Changes the name of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------		
 * int		Change_surname(String login,String newSurame)	
 * 		#Changes the surname of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------		
 * int		Change_age(String login,Integer newAge)		
 * 		#Changes the age of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------			
 * int		Change_country(String login,String newCountry)	
 * 		#Changes country of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------		
 * int		Change_password(String login,byte[] password)		
 * 		#Changes the password of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------	
 * int		Change_avatar(String login,byte[] avatar)	
 * 		#Changes the avatar of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------			
 * int		Set_type(String login,Integer type)	
 * 		#Changes the type of the image (e.g. jpg,png, etc) name of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------					
 * int		Change_gender(String login,Integer newGender)		
 * 		#Changes the gender of the Account(login)
 * 		#Return values:
 * 			-com.seabattle.classes.CallbackConstants.GOOD
 * 			-com.seabattle.classes.CallbackConstants.BAD
 * 
 * -------------------------------------	
 * Account Statistic(String user)		
 * 		#Return the statistic of the Account(user)
 * 		#Return values:
 * 			-Account of the user
 * 			-null
 * -------------------------------------							
 */

public interface Server_int extends Remote 
{
	/*
	 * LOGICAL PART OF THE GAME
	 */
	int Sign_in	(String login,String pswd,Client_int remObj) 	throws RemoteException;
	int Sign_up	(String login,String pswd) 						throws RemoteException;
	int Sign_out(String login)									throws RemoteException;
	int Turn	(Integer x, Integer y,String login)				throws RemoteException;
	int New_game(String me,String enemy)						throws RemoteException;
	int Accept	(String me,String enemy)						throws RemoteException;
	int Ready	(Field field, String user)						throws RemoteException;
	int Leave	(String login)									throws RemoteException;

	
	
	/*
	 * SQL PART OF INTERFACE
	 */
	int 	Change_name(String login,String newName)			throws RemoteException;
	int		Change_surname(String login,String newSurame)		throws RemoteException;
	int		Change_age(String login,Integer newAge)				throws RemoteException;
	int		Change_country(String login,String newCountry)		throws RemoteException;
	int		Change_password(String login,byte[] password)		throws RemoteException;
	int		Change_avatar(String login,byte[] avatar)			throws RemoteException;
	int		Set_type(String login,Integer type)					throws RemoteException;
	int		Change_gender(String login,Integer newGender)		throws RemoteException;
	Account Statistic(String user)								throws RemoteException;
}
