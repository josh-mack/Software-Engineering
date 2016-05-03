package Estuary;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;


/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all the game's logic and calculations.
 */

import javax.swing.Timer;
public class Environment implements Serializable{
	private static final long serialVersionUID = 0;
	
	private Species[] animals;
	private Character[] characters;
	private int health;
	private int newHealth = 10;
	private int oldHealth = 5;
	static public int money;
	Queue events = new Queue();
	
	/**
	 * Default game initializatzion constructor.
	 * Creates an empty board of animals and characters.
	 * Sets the player's health to below the maximum.
	 * Sets the player's starting money to 200.
	 */
	public Environment() {
		this.animals = null;
		this.characters = null;
		this.health = 50;
		this.money = 200;
	}
	
	/**
	 * Returns an array of animals on the board.
	 * @return animals
	 */
	public Species[] getAnimals() {
		return animals;
	}
	
	/**
	 * Returns an array of characters on the board.
	 * @return
	 */
	public Character[] getCharacters() {
		return characters;
	}
	
	/**
	 * Other Getters and setters.
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	public int getNewHealth(){
		return this.newHealth;
	}
	public int getOldHealth(){
		return this.oldHealth;
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
	
	
	/**
	 * Method to create game events. This involves placing
	 * an invasive species on the board depending on which
	 * quadrant the player is zoomed into.
	 * 
	 * North quad - Phragmites
	 * East quad - Bamboo
	 * South quad - MittenCrab
	 * West quad - Pollution
	 * 
	 * @param quad
	 * @return new Event
	 */
	public Event makeEvent(eQuad quad){
		Invasive invasiveAdded =new Phragmites(3, 10, 10, 5, 10);
		
		Random rand = new Random();
		int rowEnd,colEnd;
		int x =0,y =0;
		switch(quad){
		case N:
			rowEnd = 24;
			colEnd = 38;
			x = (rand.nextInt(colEnd)%38);
			y = (rand.nextInt(rowEnd)%24);
			break;
		case E:
			rowEnd = 24;
			colEnd = 76;
			x = (rand.nextInt(colEnd)%38)+38;
			y = (rand.nextInt(rowEnd)%24);
			
			break;
		case S:
			rowEnd = 48;
			colEnd = 76;
			x = (rand.nextInt(colEnd)%38)+38;
			y = (rand.nextInt(rowEnd)%24)+24;
			break;
		case W:
			rowEnd = 48;
			colEnd = 38;
			x = (rand.nextInt(colEnd)%38);
			y = (rand.nextInt(rowEnd)%24)+24;
			break;
			
		default:
			rowEnd = 0;
			colEnd = 0;
		}
		
		switch(quad){
		case N:
			invasiveAdded= new Phragmites(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
			break;
		case E:
			invasiveAdded= new Bamboo(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
			break;
		case S:
			invasiveAdded= new MittenCrab(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
			break;
			
		default: 
			invasiveAdded= new Pollution(3, x, y, 5, 10);
			events.insertFront(invasiveAdded);
			events.setX(x);
			events.setY(y);
		}
		
		
		return new Event(x, y, invasiveAdded.getType());
	}
	
	
	/**
	 * This method is called when an event is completed, 
	 * removing the event from the event queue and
	 * giving money to the player as a reward.
	 */
	public void resolve() {
		System.out.println("Resolve Method Active");
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Game.board[events.peakFront().getYCoord()][events.peakFront().getXCoord()] = eChar.BLANK; 
				events.removeFront();
				money += 100;
				calcHealth();
				}
		};
		new Timer(1000, timerAction).start();
			
		
	
	}
	
	public void calcGrowth() {
		
	}
	
	/**
	 * Method to calculate the health change,
	 * based off current health.
	 */
	public void calcHealth() {
		/*double y = (this.health - 19/20)*(this.health - 20)/10;
		double num  = ( 1 + Math.pow( Math.exp(1.0), y));
		double formula = 1/(num);
		setHealth((int)formula);*/
		setHealth((int)(this.health * 2));
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