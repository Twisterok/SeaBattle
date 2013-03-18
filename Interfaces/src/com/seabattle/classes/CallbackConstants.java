package com.seabattle.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackConstants {
	public static final int GOOD				=200;		//��
	public static final int UST					=201;		//����������
	public static final int	GLS					=202;		//������ ���
	public static final int GAME_CREATED		=203;		//���� �������
	public static final int GAME_STATE			=204;		//������� ��������� ����
	public static final int WAITING				=205;
	public static final int USER_EXISTS			=400;		//������������ ��� ����������
	public static final int	WRONG_PARAMETR		=401;		//�������� ��������
	public static final int INNER_ERROR			=402;		//���������� ������
	public static final int AUTORISATION_ERROR	=403;		//������ �����������
	public static final int UNAUTORISED_USER	=404;		//������������ �� �����������
	public static final int USER_ONLINE			=405;		//������������ ��� ���������
	public static final int NO_GAME				=406;		//���� �� ����������
	public static final int NO_SLOT				=407;		//� ���� ��� ����
	public static final int NO_LEAVE			=408;		//������ ����� �� ���� - ������������ �� � ����
	public static final int WRONG_MOVE			=409;		//������������ ���
	public static final int USER_PLAYS			=410;		//������������ ��� � ����
	public static final int BAD					=500;		//�����
	
	public static final int WON		 			= 600;		//����������� ������
	public static final int LOST	 			= 601;		//����������� ���������
	public static final int DECLINED			= 602;		//��������.
	public static final int CREATION_ERROR		= 603;		//����� ��� ��������
		
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
