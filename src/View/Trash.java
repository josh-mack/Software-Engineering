package View;

import java.awt.BorderLayout;
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

import Controller.Game;
import Model.eChar;
import Model.eQuad;
/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles the trash bin, which is actually a recycling bin.
 * Used to return characters to the character pool when desired.
 */
public class Trash extends JComponent implements Serializable{

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
	 * Constructor for the trash. Sets the location.
	 * @param x
	 * @param y
	 */
	public Trash(int x, int y)
	{
		this.x = x;
		this.y = y;
		setLayout(new BorderLayout());
		ImageIcon image = Game.getImage(eChar.TRASH);
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		this.whatQuad = eQuad.E;
		this.character = eChar.TRASH;
		setLocation(x,y);

	
		
	}
	
	
	/**
	 * Method to serialize the Trash object.
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
	 * @return Trash object
	 */
	public static Object deserialize(String fileName) {
		Trash obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Trash)ois.readObject();
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


