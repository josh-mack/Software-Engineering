package Estuary;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
public class Environment implements Serializable{
	private static final long serialVersionUID = 0;
	
	private Species[] animals;
	private Character[] characters;
	private int health;
	private int newHealth;
	private int oldHealth;
	static public int money;
	Queue events = new Queue();
	
	public Environment() {   //Default Game Initialization Constructor
		this.animals = null;
		this.characters = null;
		this.health = 50;
		this.money = 200;
	}
	
	public Species[] getAnimals() {
		return animals;
	}
	
	public Character[] getCharacters() {
		return characters;
	}
	

	public int getHealth() {
		return health;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setAnimals(Species[] animals) {
		this.animals = animals;
	}
	
	public void setCharacters(Character[] characters) {
		this.characters = characters;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public Event makeEvent(eQuad quad){
		Invasive invasiveAdded;
		Random rand = new Random();
		int x = rand.nextInt(37);
		int y = rand.nextInt(23);
		
		
		switch(quad){
		case MAIN:
			invasiveAdded= new Phragmites(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
			break;
			
		default: 
			invasiveAdded = new MittenCrab(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
		}
		
		
		return new Event(x, y, invasiveAdded.getType());
	}
	
	public void resolve() {
		if(events.peakFront().getResolved() == true){
			calcHealth();
			Game.board[events.peakFront().getYCoord()][events.peakBack().getXCoord()] = eChar.DNREC; 
			events.removeFront();
		}
	
	}
	
	public void calcGrowth() {
		
	}
	
	public void calcHealth() {
		double y = (newHealth - 19/20)*(oldHealth - 20)/10;
		double num  = ( 1 + Math.pow( Math.exp(1.0), y));
		double formula = 1/(num);
		setHealth((int)formula);
	}
	
	public void calcMoney() {
		
	}
	
	/**
	 * Method to serialize Environment, which takes care of all the
	 * game logic, containing the serialized version of the objects.
	 * @param obj
	 * @param fileName
	 * 
	 * @throws IOException
	 */
	public static void serializable(Object obj, String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			fos.close();
		} catch(IOException e) {
			System.out.println("Read error: " + e.getMessage());
		}
	}
	
	/**
	 * Method to read game state.
	 * @param fileName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @return Environment object
	 */
	public static Object deserialize(String fileName) {
		Environment obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Environment)ois.readObject();
			ois.close();
		}
		catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println("Read Error: " + e.getMessage());
		}
		return obj;
	}
	
	
	
}