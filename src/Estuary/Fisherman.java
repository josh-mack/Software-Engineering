package Estuary;

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
	 * Constructor for DNERR. Sets the position of the object to
	 * [10][3] in the game board. This object acts as a game modifier,
	 * in benefit for the player to accomplish the game's goal.
	 * @param thisQuad - 
	 */
	
	public Fisherman(int x, int y)
	{
		ImageIcon image = Game.getFishImage(Game.fishFlag);
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
	
	public static void boatsEvent()
	{
		Game.fishFlag = false;
	}
	
	public static void boatsResolve(eChar character2, int i, int j, DragComponent drag, SpeciesComponent invasiveSpecies)
	{
		
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Game.deleteComponent(i, j);
				Game.gameFrame.getMainWindow().getLayeredPane().remove(drag);
				Game.mainEnviro.setNumInvasive(Game.mainEnviro.getNumInvasive()- 1);
				Game.board[i][j] = eChar.BLANK; 
				Game.mainEnviro.money += 100;
				Game.mainEnviro.setHealth(Game.mainEnviro.getHealth() + 5);
				Game.fishFlag = true;
				
				((Timer)e.getSource()).stop();
			}
		};
		
		Timer temp = new Timer(10000, timerAction);
		temp.start();
		
	}
	
	/**
	 * Method to serialize the DNERR object.
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
	 * @return DNERR object
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


