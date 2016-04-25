package Estuary;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Environment implements Serializable{
	private static final long serialVersionUID = 0;

	private int newHealth;
	private int oldHealth;
	static public int money;
	
	public Environment() {   //Default Game Initialization Constructor
		this.newHealth = 50;
		Environment.money = 200;
	}

	
	public int getHealth() {
		return newHealth;
	}
	
	public int getMoney() {
		return Environment.money;
	}
	
	
	public void setHealth(int health) {
		this.oldHealth = newHealth;
		this.newHealth = health;
	}
	
	public void setMoney(int money) {
		Environment.money = money;
	}
	
	public void makeEvent(){
		
	}
	
	public void resolve() {
		
	}
	
	public void calcGrowth() {
		
	}
	
	public void calcHealth() 
	{
		double y = (newHealth + 19/20)*(oldHealth -20)/10;
		double num = (1+Math.pow(Math.exp(1), y));
		double formula = 1/num;
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