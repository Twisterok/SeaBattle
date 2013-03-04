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
		System.out.println("server side login: " + player.getMe().getLogin() + "\tpass: "+
				player.getMe().getPassword());
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
		//TODO: TURN
		if (!players.containsKey(login))
			return CallbackConstants.UNAUTORISED_USER;
		else if (!players.get(login).isPlaying())
			return CallbackConstants.WRONG_MOVE;
		Game searchGame = new Game(players.get(login));
		int searchIndex = games.indexOf(searchGame);
		if (searchIndex < 0)
			return CallbackConstants.NO_GAME;
		else if (!games.get(searchIndex).isStarted())
			return CallbackConstants.WRONG_MOVE;
		else if ( !(login.equals(games.get(searchIndex).getPlayers()
				.get(games.get(searchIndex).getTurn_owner()))))
				{
					return CallbackConstants.WRONG_MOVE;
				}
		
		int resultTurn = players.get(players.get(login).getEnemy()).getField().turn(x, y);
		
		//IF THE GAME IS OVER
		if	(players.get(players.get(login).getEnemy()).getField().isOver())
		{
			for (Player pl : games.get(searchIndex).getPlayers())
			{
				if	(pl.getMe().getLogin().equals(login))
				{
					players.get(pl.getMe().getLogin()).setPlaying(false);
					players.get(pl.getMe().getLogin()).getMe().addWins();
					userDataBase.addWin(pl.getMe().getLogin());
					pl.getRemObj().push_end(CallbackConstants.WON);
				}
				else
				{
					players.get(pl.getMe().getLogin()).setPlaying(false);
					players.get(pl.getMe().getLogin()).getMe().addLoses();
					userDataBase.addLose(pl.getMe().getLogin());
					pl.getRemObj().push_end(CallbackConstants.LOST);
				}
			}
			games.remove(searchIndex);
		}
		
		
		//IF THE PLAYER MADE THE WRONG MOVE
		if (resultTurn == Field.bad_return)
			return CallbackConstants.WRONG_MOVE;
		
		if (resultTurn == Field.milk)
		{
			//IF THE PLAYER MISSED
			games.get(searchIndex).nextTurn_owner();
			for (Player pl : games.get(searchIndex).getPlayers())
			{
				if	(pl.getMe().getLogin().equals(login))
				{
					pl.getRemObj().push_turn(pl.getField()	, CallbackConstants.I_SHOT
															, !CallbackConstants.MY_TURN);
				}
				else
				{
					pl.getRemObj().push_turn(pl.getField()	, !CallbackConstants.I_SHOT
															, CallbackConstants.MY_TURN);
				}
			}
		}
		else
		{
			//OTHERWISE
			for (Player pl : games.get(searchIndex).getPlayers())
			{
				if	(pl.getMe().getLogin().equals(login))
				{
					pl.getRemObj().push_turn(pl.getField()	, CallbackConstants.I_SHOT
															, CallbackConstants.MY_TURN);
				}
				else
				{
					pl.getRemObj().push_turn(pl.getField()	, !CallbackConstants.I_SHOT
															, !CallbackConstants.MY_TURN);
				}
			}
		}
		return CallbackConstants.GOOD;
	}

	@Override
	public int New_game(String me, String enemy) throws RemoteException {
		if (players.containsKey(enemy) && !players.get(enemy).isPlaying())
		{
			players.get(enemy).getRemObj().wanna_play(me);
			return CallbackConstants.WAITING;
		}
		return CallbackConstants.BAD;
	}

	@Override
	public int Accept(String me, String enemy) throws RemoteException {
		
		if (!players.get(me).isPlaying() && !players.get(enemy).isPlaying())
		{
			Vector<Player> gamers = new Vector<Player>();
			gamers.add(players.get(enemy));
			gamers.add(players.get(me));		
			Game newGame = new Game(gamers);
			players.get(me).setPlaying(true);
			players.get(me).setEnemy(enemy);
			players.get(enemy).setPlaying(true);
			players.get(enemy).setEnemy(me);
			players.get(enemy).getRemObj().game_created();
			players.get(me).getRemObj().game_created();
			games.add(newGame);
			return  CallbackConstants.GOOD;
		}
		return CallbackConstants.BAD;
	}

	
	/*
	 * CLIENT MUST CHECK THE FIELD
	 * @see com.seabattle.interfaces.Server_int#Ready(com.seabattle.classes.Field, java.lang.String)
	 */
	@Override
	public int Ready(Field field, String user) throws RemoteException {
		Game searchGame = new Game(players.get(user));
		int index = games.indexOf(searchGame);
		if (games.get(index).isStarted())
		{
			for (int i=0;i<games.get(index).getPlayers().size();++i)
			{
				games.get(index).getPlayers().get(i).getRemObj().push_end(CallbackConstants.CREATION_ERROR);
				players.get(games.get(index).getPlayers().get(i).getMe().getLogin()).setPlaying(false);
			}
			return CallbackConstants.INNER_ERROR;
		}
		
		players.get(user).setReady(true);
		boolean allReady = true;
		for (int i=0;i<games.get(index).getPlayers().size();++i)
		{
			if (!games.get(index).getPlayers().get(i).isReady())
			{
				allReady = false;
			}
		}
		if (allReady)
		{
			Game gm = games.get(index);
			for (int i=0;i<games.get(index).getPlayers().size();++i)
			{
				gm.getPlayers().get(i).getRemObj().game_started(
						gm.getPlayers().get(gm.getTurn_owner())
						.getMe().getLogin());
			}
			return CallbackConstants.GOOD;
		}
		return CallbackConstants.WAITING;
	}

	@Override
	public int Leave(String login) throws RemoteException {
		
		if (!players.containsKey(login) || !players.get(login).isLogged())
			return CallbackConstants.UNAUTORISED_USER;
		else if (!players.get(login).isPlaying())
			return CallbackConstants.NO_LEAVE;
		else
		{
			Player pl = players.get(login);
			Game searchGame = new Game(pl);
			int searchIndex = games.indexOf(searchGame);
			if (searchIndex <0)
				return CallbackConstants.NO_LEAVE;
			
			if (games.get(searchIndex).getPlayers().size() >1)
			{
				int leaverIndex = games.get(searchIndex).getPlayers().indexOf(pl);
				for (int i = 0;i< games.get(searchIndex).getPlayers().size();++i)
				{
					if (i == leaverIndex)
					{
						String leaverLogin = games.get(searchIndex).getPlayers().get(leaverIndex).getMe().getLogin();
						players.get(leaverLogin).getMe().addLoses();
						players.get(leaverLogin).setPlaying(false);
						players.get(leaverLogin).setEnemy(new String());
						userDataBase.addLose(leaverLogin);
						games.get(searchIndex).getPlayers().get(leaverIndex).getRemObj().push_end(CallbackConstants.LOST);
					}
					else
					{
						String winnerLogin = games.get(searchIndex).getPlayers().get(i).getMe().getLogin();
						players.get(winnerLogin).getMe().addWins();
						players.get(winnerLogin).setPlaying(false);
						players.get(winnerLogin).setEnemy(new String());
						userDataBase.addWin(winnerLogin);
						games.get(searchIndex).getPlayers().get(i).getRemObj().push_end(CallbackConstants.WON);
					}
				}
			}
			else if (games.get(searchIndex).getPlayers().size() == 1)
			{
				int FIRST = 0;
				String winnerLogin = games.get(searchIndex).getPlayers().get(FIRST).getMe().getLogin();
				players.get(winnerLogin).getMe().addWins();
				players.get(winnerLogin).setPlaying(false);
				userDataBase.addWin(winnerLogin);
				games.get(searchIndex).getPlayers().get(FIRST).getRemObj().push_end(CallbackConstants.WON);
			}
			games.remove(searchIndex);
			return CallbackConstants.GOOD;
		}
	}

	@Override
	public Account Statistic(String user) throws RemoteException {
		
		if (players.containsKey(user))
		{
			
			return players.get(user).getMe();
		}
		return null;
		
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
