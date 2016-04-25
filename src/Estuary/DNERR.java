package Estuary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DNERR extends JComponent implements Serializable
{

	private static final long serialVersionUID = 600;
	
	short level = 1;
	private final int xCoord = 800;
	private final int yCoord = 200;
	ImageIcon building;
	
	public DNERR()
	{
		
		setLayout(new BorderLayout());
		building = new ImageIcon("imgs/level1.png");
		JLabel label = new JLabel(building);
		label.setBounds(0, 0, building.getIconWidth(), building.getIconHeight());
		setBounds(0,0,building.getIconWidth(), building.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		
		JButton upgrade = new JButton("Upgrade");
		upgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				upgrade();
			}
		});
	}
		

	void upgrade()
	{
		switch(level)
		{
		case 2:
			Environment.money -= 200;
			building = new ImageIcon("img/level2");
			break;
		case 3:
			Environment.money -= 500;
			building = new ImageIcon("imgs/level3");
			break;
		default:
			building = new ImageIcon("img.level2");
		}
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


