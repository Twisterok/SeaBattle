package com.seabattle.classes;
import java.util.Vector;

public class Field 
{
	public static final int fSize 		= 	10;
	public static final int bad_return	= 	-1;
	public static final int free 		=	0;
	public static final int submarine	=	1;
	public static final int destroyer	=	2;
	public static final int cruiser		=	3;
	public static final int battleship	=	4;
	public static final int stab_s		=	5;
	public static final int stab_d		=	6;
	public static final int stab_c		=	7;
	public static final int stab_b		=	8;
	public static final int milk		=	9;
	public static final int killed		=	10;
	private Vector<Integer> offset;
	private Vector<Vector<Integer>>	field;
	
	public void setValue(int x,int y,Integer value)
	{
		this.field.get(x).set(y, value);
	}
	
	public void printField()
	{
		for (int i=0;i<fSize;++i)
		{
			for (int j=0;j<fSize;++j)
			{
				System.out.print(field.get(i).get(j) + " ");
			}
			System.out.print("\n");
		}
	}
	public Field()
	{
		field = new Vector<Vector<Integer>>();
		offset = new Vector<Integer>();
		for (int i=0;i<3;++i)
		{
			offset.add(i-1);
		}
		for (int i=0;i<fSize;++i)
		{
			Vector<Integer> tmp_vector = new Vector<Integer>();
			for (int j=0;j<fSize;++j)
			{
				tmp_vector.add(0);
			}
			field.add(tmp_vector);
		}		
	}

	public Vector<Vector<Integer>> getField()
	{
		return field;
	}

	public void Init()
	{
		for (int i=0;i<fSize;++i)
		{
			for (int j=0;j<fSize;++j)
			{
				System.out.print("--\n");
				field.get(i).set(j, 0);
			}
		}
	}
	
	/*----------------------------------------------------------------------------------
	 * FUNCTION: 	turn(int,int)
	 * DESCRIPTION:	tries to shoot to the position and change the field then
	 * 
	 * RETURNS:		5 	(milk)		-	if smb missed
	 * 				6 	(stab)		-	if smb has hit the ship
	 * 				-1	(bad_return)-	if move isn't allowed
	 -----------------------------------------------------------------------------------*/
	public int turn(int x, int y)
	{
		// TO THE FREE SPACE (WHEN SMB MISSED)
		if (field.get(x).get(y).equals(free))
		{
			field.get(x).set(y,milk);
			return milk;
		}	
		
		//	MOVE ISN'T ALLOWED
		if (field.get(x).get(y).equals(milk) || (field.get(x).get(y) >= stab_s && field.get(x).get(y) <=stab_b))
		{
			return bad_return; 
		}
		
		
		// SMALL SHIP 1
		if (field.get(x).get(y).equals(submarine))
		{
			field.get(x).set(y,stab_s);
			for (int i=0;i<offset.size();++i)
			{
				for (int j=0;j<offset.size();++j)
				{
					if (!(i == 0 && j == 0)									&&
						(x+offset.get(i) > 0 &&	x+offset.get(i) < fSize )	&&
						(y+offset.get(j) > 0 && y+offset.get(j) < fSize ))
					{
						field.get(x+offset.get(i)).set(y+offset.get(j), milk);
					}
				}
			}
			return stab_s;
		}
				
		// BIG SHIP  2+
		if (field.get(x).get(y) >= destroyer && field.get(x).get(y) <=battleship)
		{
			//	int cur_value -		current value at field[x][y]
			//  it's to check which type of ships was hit
			int cur_value =  field.get(x).get(y);
			
			switch	(cur_value)
			{
			case destroyer:
				field.get(x).set(y, stab_d);
				cur_value = stab_d;
				break;
			case cruiser:
				field.get(x).set(y, stab_c);
				cur_value = stab_c;
				break;
			case battleship:
				field.get(x).set(y, stab_b);
				cur_value = stab_b;
				break;
			}
			
			
			//	chk_values	is to check was the ship crashed or not
			Vector<PairInt> chk_values = new Vector<PairInt>();
			PairInt cur_pair = new PairInt(x,y);
			chk_values.add(cur_pair);
			boolean hasMove = true;
			while (hasMove)
			{
				hasMove = false;
				for (int i=0;i<offset.size();++i)
				{
					for (int j=0;j<offset.size();++j)
					{
						if (!(i == 1 && j == 1)									&&
							(cur_pair.getX()+offset.get(i) >= 0 &&	cur_pair.getX()+offset.get(i) < fSize )	&&
							(cur_pair.getY()+offset.get(j) >= 0 && 	cur_pair.getY()+offset.get(j) < fSize ))
						{
							PairInt tmp_pair = new PairInt(	cur_pair.getX()+offset.get(i),
															cur_pair.getY()+offset.get(j));
							if (field.get(tmp_pair.getX()).get(tmp_pair.getY()).equals(cur_value) &&
									!chk_values.contains(tmp_pair))
							{
								cur_pair = tmp_pair;
								chk_values.add(cur_pair);
								hasMove = true;
								break;
							}
						}
					}
				}
			}
			
			Integer comparer = cur_value;
			if (comparer.equals(chk_values.size()+4))
			{
				for (int k=0;k<chk_values.size();++k)
				{
					for (int i=0;i<offset.size();++i)
					{
						for (int j=0;j<offset.size();++j)
						{
							if (chk_values.get(k).getX()+offset.get(i) >= 0 && chk_values.get(k).getX()+offset.get(i) < fSize &&
								chk_values.get(k).getY()+offset.get(j) >= 0 && chk_values.get(k).getY()+offset.get(j) < fSize &&
								!field.get(chk_values.get(k).getX()+offset.get(i)).get(chk_values.get(k).getY()+offset.get(j)).equals(comparer))
							{
								field.get(chk_values.get(k).getX()+offset.get(i)).set(chk_values.get(k).getY()+offset.get(j), milk);
							}
						}
					}
				}
				return killed;
			}
			else
			{
				return cur_value;
			}
		}
		
		return bad_return;
	}
	
	
	
	
	/*----------------------------------------------------------------------------------
	 * FUNCTION: 	isOver()
	 * DESCRIPTION:	Checks game is over or not 
	 * 			a)	there is no free field
	 * 			b) 	there is no alive ship
	 * 
	 * RETURNS:		TRUE			-	if the game is over
	 * 				FALSE			-	otherwise
	 -----------------------------------------------------------------------------------*/
	public boolean isOver()
	{
		for (int i=0;i<this.fSize;++i)
		{
			for (int j =0;j<this.fSize;++j)
			{
				int curValue = this.getField().get(i).get(j);
				if (curValue == Field.free 		||
					curValue == Field.submarine ||
					curValue == Field.destroyer ||
					curValue == Field.cruiser 	||
					curValue == Field.battleship)
				{
					return false;
				}
			}
				
		}
		return true;
	}
}
