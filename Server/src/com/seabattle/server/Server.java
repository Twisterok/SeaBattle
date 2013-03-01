package com.seabattle.server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import com.seabattle.classes.*;
import com.seabattle.dbadapter.DatabaseAdapter;
import com.seabattle.interfaces.*;


public class Server implements Server_int
{
	Map<String,Player>  players;
	Vector<Account>		accounts;
	Vector<Game>		games;
	DatabaseAdapter		userDataBase;
	
	Server()
	{
		super();
		try {
			userDataBase= new DatabaseAdapter();
			accounts 	= userDataBase.loadDB();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.print("CAN NOT INSTALL THE DB\n\n\n");
			e.printStackTrace();	
		} 
		catch (IOException ioE) 
		{	
			ioE.printStackTrace();
		}
		
		players 	= new HashMap<String,Player>();		
		games 		= new Vector<Game>(); 
		
		
		
	}
	
	boolean accountExists(String login)
	{
		Account checker = new Account(login,"blank");
		if (accounts.contains(checker))
		{
			return true;
		}
		return false;
	}	
		
	int autorisation(String login,String password,Client_int remObj)
	{
		if (!accountExists(login))
		{
			return CallbackConstants.AUTORISATION_ERROR;
		}
		if (players.containsKey(login) && players.get(login).isLogged())
		{
			return CallbackConstants.USER_ONLINE;
		}
		
		Player player = new Player(login,password,remObj);
		player.setLogged(true);
		players.put(login, player);
		return CallbackConstants.GOOD;
	}	
	
	int register(String login,String password)
	{
		if (accountExists(login))
		{
			return CallbackConstants.USER_EXISTS;
		}	
		Account newAccount = new Account(login,password);
		accounts.add(newAccount);
		return userDataBase.addAccount(newAccount);
	}

	int deleteAccount(String login)
	{
		Account toDelete = new Account(login,"blank");
		accounts.remove(accounts.indexOf(toDelete));
		return userDataBase.deleteAccount(toDelete);
	}
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, ClassNotFoundException, SQLException {		
		Server server = new Server();
		Server_int stub = (Server_int) UnicastRemoteObject.exportObject(server, 0);		 
	    Registry registry = LocateRegistry.createRegistry(12345);
	    registry.bind("SeaBattle", stub);
	    //server.testAccount();
	    Account me = new Account("alex","admin");
	    //server.Sign_up(me.getLogin(), me.getPassword());
	    //System.out.print(server.accounts.size()+" "+server.accounts.get(0).getLogin() + "\n");
	    /* MD5
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			String md5 = new String("passwd");
	        byte[] array = md.digest(md5.getBytes());
	        System.out.print(new String(array));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Override
	public int Sign_in(String login, String pswd, Client_int remObj)
			throws RemoteException {
		return autorisation(login, pswd, remObj);
	}

	@Override
	public int Sign_up(String login, String pswd) throws RemoteException {
		return register(login, pswd);
	}

	@Override
	public int Sign_out(String login) throws RemoteException {
		
		if (!players.containsKey(login) || !players.get(login).isLogged())
		{
			return CallbackConstants.UNAUTORISED_USER;
		}
		else if (players.get(login).isPlaying())
		{
			return CallbackConstants.USER_PLAYS;
		}
		players.remove(login);
		return CallbackConstants.GOOD;
	}

	@Override
	public int Turn(Integer x, Integer y, String login) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int New_game(String me, String enemy) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Accept(String me, String enemy) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Ready(Field field, String user) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Leave(String login) throws RemoteException {
		
		if (!players.containsKey(login) || !players.get(login).isLogged())
			return CallbackConstants.UNAUTORISED_USER;
		else if (!players.get(login).isPlaying())
			return CallbackConstants.NO_LEAVE;
		else
		{
			/*	TODO: FINISH LEAVE SOLUTION.
			Player pl = new Player();
			pl.getMe().setLogin(login);
			Game searchGame = new Game(pl, 0);
			
			int searchIndex = games.indexOf(searchGame);
			if (searchIndex <0)
				return CallbackConstants.NO_LEAVE;
			
			if (games.get(searchIndex).getPlayers().size() >1)
			{
				games.remove(games.get(searchIndex).getPlayers().indexOf(pl));
				
			}
			*/
			return CallbackConstants.GOOD;
		}
	}

	@Override
	public Account Statistic(String user) throws RemoteException {
		
		Account result = new Account();
		if (players.containsKey(user))
			return players.get(user).getMe();
		return result;
		
	}

		
	@Override
	public int Change_name(String login, String newName) throws RemoteException 
	{
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeName(login, newName);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setName(newName);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_surname(String login, String newSurame)
			throws RemoteException {

		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeSurame(login, newSurame);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setSurname(newSurame);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_age(String login, Integer newAge) throws RemoteException {
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeAge(login, newAge);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setAge(newAge);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_country(String login, String newCountry)
			throws RemoteException {
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeCountry(login, newCountry);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setCountry(newCountry);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_password(String login, byte[] password)
			throws RemoteException {
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changePassword(login, password);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setPassword(new String(password));
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_avatar(String login, byte[] avatar)
			throws RemoteException {
		
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeAvatar(login, avatar);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setAvatar(avatar);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Set_type(String login, Integer type) throws RemoteException {
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.setType(login, type);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setType(type);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Change_gender(String login, Integer newGender)
			throws RemoteException {
		
		int index = accounts.indexOf(new Account(login,"blank"));
		if (index >0)
		{
			int res = userDataBase.changeGender(login, newGender);
			if (res == CallbackConstants.GOOD)
			{
				accounts.get(index).setGender(newGender);
				return res;
			}
		}
		return CallbackConstants.BAD;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*---------------------------------------
	 * 										 *
	 * 				TESTS					 *
	 * 										 *
	 ----------------------------------------*/
	void testAccount()
	{
		Server srv = new Server();
		System.out.println("Before - accounts size = "+srv.accounts.size());
		Account newAcc = new Account("alex","pswd");
		srv.register(newAcc.getLogin(),newAcc.getPassword());
		System.out.println("After add  - accounts size = "+srv.accounts.size());
		srv.deleteAccount(newAcc.getLogin());
		System.out.println("After delete - accounts size = "+srv.accounts.size());
	}
}
