package com.field.test;

import com.seabattle.classes.*;

public class FieldTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Field fld = new Field();
		//fld.Init();
		
		fld.setValue(0, 0, Field.destroyer);
		fld.setValue(1, 0, Field.destroyer );
		
		fld.turn(0, 0);
		fld.turn(1, 0);
		fld.printField();
	}

}
