package com.seabattle.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackConstants {
	public static final int GOOD				=200;		//ОК
	public static final int UST					=201;		//Статистика
	public static final int	GLS					=202;		//Список игр
	public static final int GAME_CREATED		=203;		//Игра создана
	public static final int GAME_STATE			=204;		//Текущее состояние игры
	public static final int WAITING				=205;
	public static final int USER_EXISTS			=400;		//Пользователь уже существует
	public static final int	WRONG_PARAMETR		=401;		//Неверный параметр
	public static final int INNER_ERROR			=402;		//Внутренняя ошибка
	public static final int AUTORISATION_ERROR	=403;		//Ошибка авторизации
	public static final int UNAUTORISED_USER	=404;		//Пользователь не авторизован
	public static final int USER_ONLINE			=405;		//Пользователь уже залогинен
	public static final int NO_GAME				=406;		//Игры не существует
	public static final int NO_SLOT				=407;		//В Игре нет мест
	public static final int NO_LEAVE			=408;		//Нельзя выйти из игры - пользователь не в игре
	public static final int WRONG_MOVE			=409;		//Недопустимый ход
	public static final int USER_PLAYS			=410;		//Пользователь еще в игре
	public static final int BAD					=500;		//Плохо
	
	public static final int WON		 			= 600;		//Присвоилась победа
	public static final int LOST	 			= 601;		//Присвоилось поражение
	public static final int DECLINED			= 602;		//Отклонил.
	public static final int CREATION_ERROR		= 603;		//Косяк при создании
		
	public static final boolean MY_TURN			= true;
	public static final boolean I_SHOT			= true;
	
	public static final boolean MAINCHAT		= true;
	
	public static byte[] toMD5(String pass)
	{
		 MessageDigest md;
		 try 
		 {
			md = MessageDigest.getInstance("MD5");
			String md5 = new String("passwd");
		    byte[] array = md.digest(md5.getBytes());
		    return array;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toMD5_String(String pass)
	{
		 MessageDigest md;
		 try 
		 {
			md = MessageDigest.getInstance("MD5");
			String md5 = new String("passwd");
		    byte[] array = md.digest(md5.getBytes());
		    return new String(array);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}	
