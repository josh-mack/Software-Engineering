package Estuary;


import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Queue{
 
	private ArrayList<Invasive> que = new ArrayList<Invasive>();
	public void insertFront(Invasive x)
	{
		que.add(0,x);
    }

	public void insertBack(Invasive x)
	{
		que.add(x);
    }

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

	public Invasive peakFront()
	{
		Invasive hold = que.get(0);
		return hold;
    }
	
	public Invasive peakBack()
	{
		Invasive hold = que.get(que.size()-1);
		return hold;
	}	

	public Iterator<Invasive> iterator() 
	{
		Iterator<Invasive> it = new Iterator<Invasive>() 
		{
			private int currentIndex = 0;
			@Override
			public boolean hasNext() 
			{
				if (currentIndex < que.size()) 
				{
					return true;
				}
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public Invasive next() 
			{
				currentIndex++;
				return que.get(currentIndex-1);

			}
		};
		return it;
	}
}