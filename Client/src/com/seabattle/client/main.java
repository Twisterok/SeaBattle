package com.seabattle.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.seabattle.classes.Account;
import com.seabattle.classes.CallbackConstants;
import com.seabattle.classes.Field;
import com.seabattle.interfaces.Client_int;
import com.seabattle.interfaces.Server_int;

public class main implements Client_int{

	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	
	
	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		  main client = new main();
		  Client_int stub = (Client_int) UnicastRemoteObject.exportObject(client,0);
		  
		  Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
	      Server_int server = (Server_int)registry.lookup("SeaBattle");
	      
	      if (server.Sign_up("alex", new String(CallbackConstants.toMD5("admin"))) == CallbackConstants.GOOD)
	      {
	    	  System.out.println("signed up");
	    	  if (server.Sign_in("alex", new String(CallbackConstants.toMD5("admin")), stub) == CallbackConstants.GOOD)
	    	  {
		    	  System.out.println("signed in");
		    	  if (server.Sign_out("alex") == CallbackConstants.GOOD)
		    	  {
		    		  System.out.println("signed out");
		    	  }
	    	  }
	      }
	      else
	      {
	    	  if (server.Sign_in("alex", new String(CallbackConstants.toMD5("admin")), stub) == CallbackConstants.GOOD)
	    	  {
		    	  System.out.println("signed in");
		    	  Account myAcc = server.Statistic("alex");
		    	  if (myAcc != null)
		    	  {
		    		  System.out.println("login: " + myAcc.getLogin() + "\tpass: "+ myAcc.getPassword());
		    	  }
		    	  if (server.Sign_out("alex") == CallbackConstants.GOOD)
		    	  {
		    		  System.out.println("signed out");
		    	  }
	    	  }
	      }
	      //
	      //System.out.println("login: " + myAcc.getName() + "\tpass: "+ myAcc.getPassword());
	      
	}


	@Override
	public int push_end(Integer description) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int wanna_play(String creator) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int game_created() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int game_started(String turnOwner) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hello() throws RemoteException {
		System.out.println("HELLO");
		return false;
	}


	@Override
	public int push_turn(Field curField, boolean isMe) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
