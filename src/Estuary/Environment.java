package Estuary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComponent;
/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all the game's logic and calculations.
 */

import javax.swing.Timer;
public class Environment implements Serializable{
	private static final long serialVersionUID = 0L;
	
	private int health;
	public int money;
	//private boolean secondChance = true;
	private int numStew;
	private int numVol;
	private int numRes;
	
	private int numSpawn;
	private int counter = 0;
	
	public int getNumInvasive() {
		return numInvasive;
	}

	public void setNumInvasive(int numInvasive) {
		this.numInvasive = numInvasive;
	}

	public int getNumNative() {
		return numNative;
	}

	public void setNumNative(int numNative) {
		this.numNative = numNative;
	}

	private int resolveTime;
	
	private int numInvasive;
	private int numNative;

	
	Timer temp;
	Timer temp2;
	
	/**
	 * Default game initialization constructor.
	 * Creates an empty board of animals and characters.
	 * Sets the player's health to below the maximum.
	 * Sets the player's starting money to 200.
	 */
	public Environment() 
	{
		this.health = 30;
		this.money = 200;
		this.numStew = 2;
		this.numRes = 1;
		this.numVol = 3;
		this.resolveTime = 3;
	}
	
	/**
	 * Other Getters and setters.
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	public int getMoney() {
		return this.money;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * Method used to remove an event from the front of the queue.
	 * This is called when an event is supposed to be completed.
	 */
	public void resolve2(eChar character, int i, int j, DragComponent drag, SpeciesComponent invasiveSpecies) {
		if (character == eChar.RESEARCHER) {
			resolveTime /= 2; 
		}
		if (character == eChar.VOLUNTEER) {
			resolveTime += 4;
		}
		System.out.println("Resolve 2 Method Active");
		
		if (health <= 10) {
			numSpawn = 3;
		}
		else if (health <= 30) {
			numSpawn = 2;
		}
		else if (health <=60) {
			numSpawn = 1;
		}
		else if (health <=80) {
			numSpawn = counter%2;
			counter += 1;
		}
		else {
			numSpawn = (((counter%4)+1)/4);
			counter += 1;
		}
		
		ActionListener nativeSpawn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Game.gameFrame.getQuadrant()!=eQuad.MAIN){
					for (int k = 0; k < numSpawn; k++) {
						Game.makeNativeSpecies(Game.gameFrame.getQuadrant(), false);
					}
				}
				((Timer)e.getSource()).stop();
			}
			
		};
		
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(i);
				System.out.println(j);
				Game.deleteComponent(i, j);
				Game.gameFrame.getMainWindow().getLayeredPane().remove(drag);
				numInvasive--;
				if (Game.board[i][j].isWet()) {
					Game.board[i][j] = eChar.WATER;
				}
				else {
					Game.board[i][j] = eChar.BLANK;
				}
				money += 75;
				calcHealth();
				switch(character){
				case STEWARD:
					numStew++;
					break;
				case RESEARCHER:
					numRes++;
					break;
				case VOLUNTEER:
					numVol++;
					break;
				default:
					break;
				}
				
				if (Game.board[drag.getOldi()][drag.getOldj()].isWet()) {
					Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
				}
				else {
					Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
				}
				
				Game.resolvingSpecies.remove(invasiveSpecies);
				
				System.out.println("Resolve 2 done");
				
				
				((Timer)e.getSource()).stop();
				}
		};

		
		temp = new Timer(resolveTime*1000, timerAction);
		temp.start();
				
		temp2 = new Timer(2000 + (resolveTime*1000), nativeSpawn);
		temp2.start();

	}

	/**
	 * Overloaded resolve() method to handle the
	 * in-game powerups. Each powerup has its own way of
	 * affecting the game.
	 * 
	 * Slowgrowth - halves the growth rate (spawn rate) of invasive species
	 * Fastharacter - 
	 * Instakill - resolves the last event in the queue immediately.
	 */
	public void resolve(eChar species, eChar character, int i, int j, DragComponent drag, SpeciesComponent invasiveSpecies) {
		System.out.println("Resolve 1 Method Active");
		switch(species) {
		case TRASH:
			Game.gameFrame.getMainWindow().getLayeredPane().remove(drag);
			Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
			switch(character){
			case STEWARD:
				numStew++;
				break;
			case RESEARCHER:
				numRes++;
				break;
			case VOLUNTEER:
				numVol++;
				break;
			default:
				break;
			}
			break;
		case DNREC:
			break;
		case HCRAB:
			break;
		case WETHCRAB:
			break;
		case BLAZINGSTAR:
			break;
		case BLACKEYEDSUSAN:
			break;
		case BCRAB:
			break;
		case WETBCRAB:
			break;
		case FISHERMAN:
			if(character == eChar.STEWARD && Game.fishFlag == false)
				Game.fishComp.boatsResolve(character, i, j, drag, invasiveSpecies);
			break;
		case CITY:
			enteredTheCity(character, drag);
			break;
		case MCRAB:
			resolveTime = 4;
			resolve2(character, i, j, drag, invasiveSpecies);
			break;
		case WETMCRAB:
			resolveTime = 4;
			resolve2(character, i, j, drag, invasiveSpecies);
		case PHRAG:
			resolveTime = 6;
			resolve2(character, i, j, drag, invasiveSpecies);
			break;
		case BAMBOO:
			resolveTime = 8;
			resolve2(character, i, j, drag, invasiveSpecies);
			break;
		case ZEBRA:
			resolveTime = 10;
			resolve2(character, i, j, drag, invasiveSpecies);
			break;
		default:
			resolve2(character, i, j, drag, invasiveSpecies);

		}
		
	System.out.println("Resolve 1 Done");
	}
	
	/**
	 * When a Steward is placed next to the city,
	 * a volunteer is added to the character pool,
	 * the steward on the board is removed and re-added
	 * into the character pool.
	 * @param character
	 * @param drag
	 */
	public void enteredTheCity(eChar character, DragComponent drag) {
		ActionListener cityAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (character == eChar.STEWARD) {
					numVol++;
					Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
					Game.gameFrame.getMainWindow().getLayeredPane().remove(drag);
					numStew++;
				}
				temp.stop();
			}
		};
		temp = new Timer(5000, cityAction);
		temp.start();
	}

	/**
	 * Method to calculate the health change,
	 * based off current health.
	 */
	public void calcHealth() 
	{
		setHealth(this.health + 3);
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

	/**
	 * Getters and setters.
	 * @return
	 */
	public int getNumStew() {
		return numStew;
	}

	public void setNumStew(int numStew) {
		this.numStew = numStew;
	}
	
	public void increaseStew(boolean positive) {
		if (positive) {
			numStew++;
		}
		else {
			numStew--;
		}
	}

	public int getNumVol() {
		return numVol;
	}

	public  void setNumVol(int numVol) {
		this.numVol = numVol;
	}
	
	public void increaseVol(boolean positive) {
		if (positive) {
			numVol++;
		}
		else {
			numVol--;
		}
	}

	public int getNumRes() {
		return numRes;
	}

	public void setNumRes(int numRes) {
		this.numRes = numRes;
	}
	
	public void increaseRes(boolean positive) {
		if (positive) {
			numRes++;
		}
		else {
			numRes--;
		}
	}
}