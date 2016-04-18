package Estuary;

import java.io.Serializable;

public abstract class Character implements Serializable{
	private static final long serialVersionUID = 400;
	
	private int xCoord;
	private int yCoord;
	public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public Character(int xCoord, int yCoord)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
}
