package com.seabattle.classes;

public class SQLConstants {
	public static final int LOW 	= 0;
	public static final int MEDIUM 	= 1;
	public static final int HIGH 	= 2;
	public static final int PRO 	= 3;
	
	public static final int PNG		= 4;
	public static final int JPG		= 5;
	public static final int BMP		= 6;
	
	public static final int NOTYPE	= -1;
	
	public static final int MALE	= 7;
	public static final int FEMALE	= 8;
	
	
	public static String Type_toStr(Integer _type)
	{
		switch (_type)
		{
		case PNG:
			return "png";
		case JPG:
			return "jpg";
		case BMP:
			return "bmp";
		}
		return "";
	}
}
