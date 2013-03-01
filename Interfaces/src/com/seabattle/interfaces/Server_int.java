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
