package Estuary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since Abstract class handles all stewards, volunteers, and researches in the
 *        game.
 */

public abstract class Character implements Serializable {
	private static final long serialVersionUID = 400;

	private int xCoord;
	private int yCoord;
	private int speed;

	/**
	 * Getters and setters.
	 * 
	 * @return
	 */

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Character(int xCoord, int yCoord) {
		this.speed = 1;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	/**
	 * Method to serialize Character, which takes care of all the game logic,
	 * containing the serialized version of the objects.
	 * 
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
		} catch (IOException e) {
			System.out.println("Read error: " + e.getMessage());
		}
	}

	/**
	 * Method to read game state.
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @return Environment object
	 */
	public static Object deserialize(String fileName) {
		Character obj = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Character) ois.readObject();
			ois.close();
		} catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		return obj;
	}
}
