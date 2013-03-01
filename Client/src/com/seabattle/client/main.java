package com.seabattle.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import com.seabattle.interfaces.Server_int;

public class main {

	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		  Registry registry = LocateRegistry.getRegistry("192.168.1.3", 12345);
	      Server_int server = (Server_int)registry.lookup("SeaBattle");
	      System.out.print("Server said: " + server.hi()+"\n"); 
	      //PrimeNumbersSearchClient client = new PrimeNumbersSearchClient();
	      //PrimeChecker stub = (PrimeChecker)UnicastRemoteObject.exportObject(client, 0);
	      //server.register(stub);
	}

}
