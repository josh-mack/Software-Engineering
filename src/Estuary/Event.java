package Estuary;
import Estuary.eChar;

public class Event {
	int x;
	int y;
	eChar type;
	private boolean resolved; 
	
	public Event(int x, int y, eChar type){
		this.x = x;
		this.y = y;
		this.type = type;
		
	}
	
	public void setResolved(boolean x){
		this.resolved = x;
	}
	public boolean getResolved(){
		return this.resolved;
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
