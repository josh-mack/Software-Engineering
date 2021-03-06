package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

import Controller.Game;
import Model.eChar;
import Model.eQuad;
/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles the Fisherman object, its event, and resolve.
 */
public class Fisherman extends JComponent implements Serializable{

	private static final long serialVersionUID = 600;
	int x;
	int y;
	@SuppressWarnings("unused")
	private MouseListener pressListener;
	
	@SuppressWarnings("unused")
	private eChar character;
	@SuppressWarnings("unused")
	private eQuad whatQuad;
	
	/**
	 * Getters and setters.
	 */
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * Constructor for Fisherman class. Places Fisherman
	 * on the board.
	 * @param x
	 * @param y
	 */
	public Fisherman(int x, int y)
	{
		ImageIcon image = View.getFishImage(Game.isFishFlag());
		this.x = x;
		this.y = y;
		setLayout(new BorderLayout());
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		this.whatQuad = eQuad.E;
		this.character = eChar.FISHERMAN;
		setLocation(x,y);
		
	}
	
	/**
	 * Method handles the activation of the Fisherman event.
	 */
	public void boatsEvent()
	{
		Game.setFishFlag(false);
		Game.refresh();
		Game.replaceFisherman(x,y);
	}
	
	/**
	 * Resolves method for the Fisherman. Adds money and sets
	 * Fisherman back to its default.
	 * @param character2
	 * @param i
	 * @param j
	 * @param drag
	 */
	public void boatsResolve(eChar character2, int i, int j, DragComponent drag)
	{
		
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Game.deleteComponentAt(i, j);
				Game.removeComponent(drag);
				if (drag.getCharacter() == eChar.STEWARD) {
					Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
				}
				else {
					Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
				}
				Game.mainEnviro.money += 200;
				Game.mainEnviro.setHealth(Game.mainEnviro.getHealth() + 5);
				Game.setFishFlag(true);
				Game.replaceFisherman(x,y);
				Game.refresh();
				Game.mainEnviro.increaseStew(true);
				((Timer)e.getSource()).stop();
			}
		};
		
		Timer temp = new Timer(10000, timerAction);
		temp.start();
		
	}
	
	/**
	 * Method to serialize the Fisherman object.
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
	 * @return Fisherman object
	 */
	public static Object deserialize(String fileName) {
		DNERR obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (DNERR)ois.readObject();
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


