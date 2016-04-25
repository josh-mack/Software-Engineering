package Estuary;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Species implements Serializable{
	private static final long serialVersionUID = 100;
	
	private int amount;
	private int xCoord;
	private int yCoord;
	private int growthRate;
	private eChar type;
	
	public Species(eChar type, int amount, int xCoord, int yCoord, int growthRate) {
		this.type = type;
		this.amount = amount;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.growthRate = growthRate;
	}

	public int getAmount() {
		return amount;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public int getGrowthRate() {
		return growthRate;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public void setGrowthRate(int growthRate) {
		this.growthRate = growthRate;
	}
	
	/**
	 * Method to serialize Species, which takes care of all the
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
	 * Method to read Species in game state.
	 * @param fileName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @return Environment object
	 */
	public static Object deserialize(String fileName) {
		Species obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Species)ois.readObject();
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
	public eChar getType(){
		return this.type;
	}
}
