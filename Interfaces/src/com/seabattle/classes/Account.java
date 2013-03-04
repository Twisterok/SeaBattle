package com.seabattle.classes;

import java.io.Serializable;

public class Account implements Serializable
{
	private String 	login;						//1
	private String 	password;					//2
	private String 	Name,Surname,Country;		//3-5
	private Integer skill,Age;					//6-7
	private byte[]	Avatar;						//8
	private Integer type;						//9
	private Integer wins,loses;					//10-11
	private Integer	gender;						//12
	
	
	public Account(String _login, String _password)
	{
		this.wins 	= 0;
		this.loses 	= 0;
		this.login 	= _login;
		this.Age	= 0;
		this.setAvatar(null);
		this.setType(SQLConstants.NOTYPE);
		this.setCountry(new String());
		this.Surname= new String();
		this.Name	= new String();
		this.skill	= SQLConstants.LOW;
		this.gender = SQLConstants.MALE;
		this.password = _password;
	}
	
	public Account() 
	{
		login = new String();
		password = new String();
		this.Age	= 0;
		this.setAvatar(null);
		this.setType(SQLConstants.NOTYPE);
		this.setCountry(new String());
		this.Surname= new String();
		this.Name	= new String();
		this.skill	= SQLConstants.LOW;
		this.wins 	= 0;
		this.loses 	= 0;
		this.setGender(SQLConstants.MALE);
	}
	
	public void setLogin(String _login)
	{
		this.login = _login;
	}
	
	public void setPassword(String _password)
	{
		this.password = _password;
	}
	
	public String getLogin()
	{
		return this.login;
	}
	
	public String getPassword()
	{
		return this.password;
	}

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public void addWins()
	{
		this.wins += 1;
	}
	
	public Integer getLoses() {
		return loses;
	}

	public void setLoses(Integer loses) {
		this.loses = loses;
	}	
	public void addLoses()
	{
		this.loses += 1;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Account))return false;
	    Account otherMyClass = (Account)other;
	    
	    if (otherMyClass.login.equals(this.login))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public Integer getSkill() {
		return skill;
	}

	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public byte[] getAvatar() {
		return Avatar;
	}

	public void setAvatar(byte[] avatar) {
		Avatar = avatar;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	

}
