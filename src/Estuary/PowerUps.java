package Estuary;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/master
public class PowerUps {
	Queue queue;
	Character character;
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
	public PowerUps(Queue queue, Character character)
	{
		this.queue = queue;
		this.character = character;
	}
<<<<<<< HEAD
	
=======

>>>>>>> refs/remotes/origin/master
	void SlowGrowth()
	{
		Iterator<Invasive> it = queue.iterator();
<<<<<<< HEAD
		while(it.hasNext()) 
		{
				Invasive monster = it.next();
				monster.setGrowthRate(monster.getGrowthRate()/2);
=======
		while(it.hasNext()) {
			Invasive monster = it.next();
			monster.setGrowthRate(monster.getGrowthRate()/2);
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD

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
=======
	
	public Queue getQueue() {
		return this.queue;
	}
	
	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
	public Character getCharacter() {
		return this.character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	
}
>>>>>>> refs/remotes/origin/master
