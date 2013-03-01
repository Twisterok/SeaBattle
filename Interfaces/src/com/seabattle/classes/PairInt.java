package com.seabattle.classes;

public class PairInt {
	private Integer x,y;
	
	public PairInt() {
		x = 0;
		y = 0;
	}
	
	public PairInt(Integer _x, Integer _y)
	{
		x = _x;
		y = _y;
	}
	public Integer getX()
	{
		return x;
	}
	
	public Integer getY()
	{
		return y;
	}
	
	public void setX(Integer _x)
	{
		x = _x;
	}
	
	public void setY(Integer _y)
	{
		y = _y;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof PairInt))return false;
	    PairInt otherMyClass = (PairInt)other;
	    
	    if (otherMyClass.x.equals(this.x) && otherMyClass.y.equals(this.y))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
}
