package Estuary;
import Estuary.eChar;

public class Event {
	int x;
	int y;
	eChar type;
	
	public Event(int x, int y, eChar type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	public eChar getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
}
