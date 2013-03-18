package com.seabattle.dbadapter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.seabattle.classes.*;


public class DatabaseAdapter {
	
	private Connection bd;
	private Statement st;
	private ResultSet rs;
	
	public DatabaseAdapter() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		PreparedStatement statement;
		bd = DriverManager.getConnection("jdbc:sqlite:"+"SeaBattleAccounts.db");
		System.out.println("CONNECTETD");
		
		try 
		{
			statement = bd.prepareStatement("CREATE TABLE accounts(" +
											"login		VARCHAR(30)	NOT NULL	PRIMARY KEY," 	+
											"password 	BLOB 		NOT NULL," 					+
											"wins		INTEGER		NOT NULL,"					+
											"loses		INTEGER		NOT NULL," 					+
											"skill		INTEGER		NOT NULL," 					+
											"name		VARCHAR(30)," 							+
											"surname	VARCHAR(30)," +
											"age		INTEGER," +
											"country	VARCHAR(50)," +
											"avatar		BLOB," +
											"type		INTEGER," +
											"gender		INTEGER);");
			statement.executeUpdate();
			System.out.println("CREATED");
		}
		catch	(Exception e)
		{
			System.out.println("DB ALREADY EXISTS");
		}
	}
	
	public Vector<Account> loadDB() throws SQLException, IOException
	{
		Vector<Account> result = new Vector<Account>();
		PreparedStatement statement;
		statement = bd.prepareStatement("SELECT * FROM accounts;");
		statement.execute();
		rs = statement.getResultSet();
		while (rs.next())
		{
			Account curAccount = new Account();
			curAccount.setLogin(rs.getString("login"));
			curAccount.setPassword(new String(rs.getBytes("password")));
			curAccount.setWins(rs.getInt("wins"));
			curAccount.setLoses(rs.getInt("loses"));
			curAccount.setSkill(rs.getInt("skill"));
			
			String tmp = rs.getString("name");
			if (!rs.wasNull())
			{
				curAccount.setName(tmp);
			}
			tmp = rs.getString("surname");
			if (!rs.wasNull())
			{
				curAccount.setSurname(tmp);
			}
			tmp = rs.getString("country");
			if (!rs.wasNull())
			{
				curAccount.setCountry(tmp);
			}
			Integer tmpInt = rs.getInt("age");
			if (!rs.wasNull())
			{
				curAccount.setAge(tmpInt);
			}
			
			byte[] ImgBytesArr = rs.getBytes("avatar");
			if (!rs.wasNull())
			{
				//byte[] img;
				//ByteArrayInputStream bais = new ByteArrayInputStream(ImgBytesArr);
				//img = ImageIO.read(bais);
				curAccount.setAvatar(ImgBytesArr);
				Integer type = rs.getInt("type");
				curAccount.setType(type);
			}
			else
			{
				BufferedImage img;
				img = ImageIO.read(new File("unknown_user.jpg"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(img, "jpg", baos);
				baos.flush();
				curAccount.setAvatar(baos.toByteArray());
				curAccount.setType(SQLConstants.NOTYPE);
			}
			
			tmpInt = rs.getInt("gender");
			if (!rs.wasNull())
				curAccount.setGender(tmpInt);
			else
				curAccount.setGender(SQLConstants.MALE);
			
			result.add(curAccount);
		}
		return result;
	}
	
	public int changeName(String login,String name)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET name = ? " +
					"where login = ?;");
			statement.setString(1, name);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING NAME: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public int changeSurame(String login,String surname)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET surname = ? " +
					"where login = ?;");
			statement.setString(1, surname);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING SURNAME: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}

	public int changeAge(String login,int age)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET age = ? " +
					"where login = ?;");
			statement.setInt(1, age);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING AGE: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}

	public int changeCountry(String login,String country)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET country = ? " +
					"where login = ?;");
			statement.setString(1, country);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING COUNTRY: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public int changePassword(String login,byte[] newPassword)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET password = ? " +
					"where login = ?;");
			statement.setBytes(1, newPassword);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING PASSWORD: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public int changeSkill(String login,Integer newSkill)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET skill = ? " +
					"where login = ?;");
			statement.setInt(1, newSkill);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING SKILL: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}

	public int changeAvatar(String login,byte[] avatar)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET avatar = ? " +
					"where login = ?;");
			
			statement.setBytes(1,avatar);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING AVATAR: Bad SQL QUERY OR IMAGE CONVERSION ERROR\n");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}

	public int setType(String login,Integer type)
	{
		if (type != SQLConstants.PNG && type != SQLConstants.JPG && type != SQLConstants.NOTYPE)
			return CallbackConstants.BAD;
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET type = ? " +
					"where login = ?;");
			
			statement.setInt(1,type);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING TYPE: Bad SQL QUERY OR IMAGE CONVERSION ERROR\n");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public int addWin(String login)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET wins = wins+1 " +
					"where login = ?;");
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ADDING WIN: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public int addLose(String login)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET loses = loses+1 " +
					"where login = ?;");
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ADDING LOSE: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}	
		return CallbackConstants.GOOD;
	}

	public int addAccount(Account newAccount)
	{
		PreparedStatement statement;
		try {
			if (!(newAccount.getAvatar() == null))
			{
				statement = bd.prepareStatement("insert into accounts " +
						"(login, password, wins, loses, skill, name, surname, age, country, avatar, type, gender)" +
						" values(?,?,?,?,?,?,?,?,?,?,?,?);");
				statement.setString(1, newAccount.getLogin());
				statement.setBytes(2, newAccount.getPassword().getBytes());
				statement.setInt(3, newAccount.getWins());
				statement.setInt(4, newAccount.getLoses());
				statement.setInt(5, newAccount.getSkill());
				statement.setString(6, newAccount.getName());
				statement.setString(7, newAccount.getSurname());
				statement.setInt(8, newAccount.getAge());
				statement.setString(9, newAccount.getCountry());
				statement.setBytes(10, newAccount.getAvatar());
				statement.setInt(11, newAccount.getType());
				statement.setInt(12, newAccount.getGender());
				//ByteArrayOutputStream baos = new ByteArrayOutputStream();
				//ImageIO.write( newAccount.getAvatar(), "jpg", baos );
				//baos.flush();
				//byte[] imageInByte = baos.toByteArray();
				//baos.close();
				
				//statement.setBytes(10, imageInByte);
				statement.execute();
			}
			else
			{
				statement = bd.prepareStatement("insert into accounts " +
						"(login, password, wins, loses, skill, name, surname, age, country, avatar, type, gender)" +
						" values(?,?,?,?,?,?,?,?,?,?,?,?);");
				statement.setString(1, newAccount.getLogin());
				statement.setBytes(2, newAccount.getPassword().getBytes());
				statement.setInt(3, newAccount.getWins());
				statement.setInt(4, newAccount.getLoses());
				statement.setInt(5, newAccount.getSkill());
				statement.setString(6, newAccount.getName());
				statement.setString(7, newAccount.getSurname());
				statement.setInt(8, newAccount.getAge());
				statement.setString(9, newAccount.getCountry());
				statement.setNull(10, java.sql.Types.BLOB);
				statement.setNull(11, java.sql.Types.INTEGER);
				statement.setInt(12, newAccount.getGender());
				statement.execute();
			}
		} catch (SQLException e) {
			System.out.println("ADDING ACCOUNT: Bad SQL QUERY");
			e.printStackTrace();
			return CallbackConstants.BAD;
		}	
		return CallbackConstants.GOOD;
	}
	
	public int deleteAccount(Account account)
	{
		PreparedStatement statement;
		try
		{
			statement = bd.prepareStatement("DELETE FROM accounts WHERE login = ?;");
			statement.setString(1, account.getLogin());
			statement.executeUpdate();
			return CallbackConstants.GOOD;
		}
		catch (SQLException e) {
			System.out.println("REMOVING ACCOUNT: Bad SQL QUERY");
			return CallbackConstants.BAD;
		}
		
	}
	
	public int changeGender(String login,Integer newGender)
	{
		if (newGender != SQLConstants.MALE && newGender != SQLConstants.FEMALE)
		{
			newGender = SQLConstants.MALE;
		}
		
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("UPDATE accounts SET gender = ? " +
					"where login = ?;");
			
			statement.setInt(1,newGender);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CHANGING GENDER: Bad SQL QUERY OR IMAGE CONVERSION ERROR\n");
			return CallbackConstants.BAD;
		}
		
		return CallbackConstants.GOOD;
	}
	
	public byte[] getAvatar(String login)
	{
		PreparedStatement statement;
		try {
			statement = bd.prepareStatement("SELECT avatar from accounts " +
					"where login = ?;");
			
			statement.setString(1, login);
			statement.execute();
			rs = statement.getResultSet();
			byte[] res = rs.getBytes("avatar");
			return res;
		} catch (SQLException e) {
			System.out.println("GETTING AVATAR: Bad SQL QUERY OR IMAGE CONVERSION ERROR\n");
			return null;
		}
	}
}
