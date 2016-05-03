package Estuary;
import Estuary.eChar;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all event objects placed into the queue. The impact of the Event
 * on the game is determined by its eChar value.
 */
public class Event {
	int x;
	int y;
	eChar type;
	private boolean resolved; 
	
	/**
	 * Constructor for the Event object.
	 * @param x - X-location
	 * @param y - y-location
	 * @param type - eChar value
	 */
	public Event(int x, int y, eChar type){
		this.x = x;
		this.y = y;
		this.type = type;
		
	}
	
	/**
	 * Getters and setters.
	 * setResolved() used to set the resolved flag when
	 * an event is completed by the player.
	 */
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
