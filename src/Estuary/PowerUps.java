package Estuary;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class PowerUps {
	Queue queue;
	Character character;

	public PowerUps(Queue queue, Character character)
	{
		this.queue = queue;
		this.character = character;
	}
	
	void SlowGrowth()
	{
		Iterator<Invasive> it = queue.iterator();
		while(it.hasNext()) 
		{
				Invasive monster = it.next();
				monster.setGrowthRate(monster.getGrowthRate()/2);
		}
	}

	void FastChar()
	{
	character.setSpeed(character.getSpeed()+1);
	}
	void InstaKill()
	{
		queue.removeback();
		//Add resolve
	}

	public Queue getQueue() 
	{
		return this.queue;
	}

	public void setQueue(Queue queue) 
	{
		this.queue = queue;
	}

	public Character getCharacter() 
	{
		return this.character;
	}

	public void setCharacter(Character character) 
	{
		this.character = character;
	}

}