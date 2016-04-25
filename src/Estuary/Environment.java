package Estuary;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Environment implements Serializable{
	private static final long serialVersionUID = 0;
	
	private Species[] animals;
	private Character[] characters;
	private eChar[][] board;
	private int health;
	private int money;
	Queue events = new Queue();
	
	public Environment() {   //Default Game Initialization Constructor
		eChar[][] temp = 
{{eChar.HCRAB, eChar.BLANK, eChar.BCRAB, eChar.BLANK},
 {eChar.BLANK, eChar.BLANK, eChar.BAMBOO, eChar.BLANK},
 {eChar.BLANK, eChar.BLANK, eChar.RESEARCHER, eChar.BLANK},		
 {eChar.BLANK, eChar.BLANK, eChar.BLANK, eChar.BLANK}};
		this.animals = null;
		this.characters = null;
		this.board = temp;
		this.health = 50;
		this.money = 200;
	}
	
	public Species[] getAnimals() {
		return animals;
	}
	
	public Character[] getCharacters() {
		return characters;
	}
	
	public eChar[][] getBoard() {
		return board;
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
	
	public void setBoard(eChar[][] board) {
		this.board = board;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void makeEvent(Invasive x){
		events.insertFront(x);
	}
	
	public void resolve() {
		if((events.peakFront()).getResolved() == true){
			calcHealth();
			events.removeFront();
	}
	}
	
	public void calcGrowth() {
		
	}
	
	public void calcHealth() {
		
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