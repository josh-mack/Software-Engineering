package Estuary;


import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Controls the resolution of events using a queue.
 */
public class Queue{
	int x = 0;
	int y = 0;

	private ArrayList<Invasive> que = new ArrayList<Invasive>();

	/**
	 * Getters and setters.
	 * @param q
	 */
	public void setX(int q){
		this.x = q;
	}
	public int getX(){
		return this.x;
	}
	public void setY(int q){
		this.y = q;
	}
	public int getY(){
		return this.y;
	}

	/**
	 * Since this is a double-ended queue, there is a
	 * method for inserting in the front and another for
	 * insterting in the back.
	 * @param x
	 */
	public void insertFront(Invasive x)
	{
		que.add(0,x);
	}

	public void insertBack(Invasive x)
	{
		que.add(x);
	}

	/**
	 * Since this is a double-ended queue, there is a 
	 * method for removing from the front and another for
	 * removing from the back.
	 */
	public void removeFront()
	{
		if(que.isEmpty())
			return;
		Invasive hold = que.remove(0);
	}

	public void removeback()
	{
		if(que.isEmpty())
			return;
		Invasive hold = que.remove(que.size()-1);
	}
	
	/**
	 * Since this is a double-ended queue, there is a
	 * method for peaking at the front and another for
	 * peaking at the back.
	 * @return
	 */
	public Invasive peakFront()
	{
		Invasive hold = que.get(0);
		return hold;
	}

	public Invasive peakBack(){
		Invasive hold = que.get(que.size()-1);
		return hold;
	}	
	
	/**
	 * Iterator for going through the queue.
	 * @return
	 */
	public Iterator<Invasive> iterator() {

		Iterator<Invasive> it = new Iterator<Invasive>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				if (currentIndex < que.size()) {
					return true;
				}
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Invasive next() {
				currentIndex++;
				return que.get(currentIndex-1);
			}

		};
		return it;
	}
}
