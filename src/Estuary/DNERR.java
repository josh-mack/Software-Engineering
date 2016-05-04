package Estuary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author super
 * @version 1.0
 * @since
 * 
 * DNERR - handles the Delaware Nation Estuarine Research Reserve (DNERR) object.
 */

public class DNERR extends JComponent implements Serializable
{

	private static final long serialVersionUID = 600;
	
	short level = 1;
	String building;
	JFrame dnrecSel;
	private JPanel panel;
	private JPanel dnrecPanel;
	
	/**
	 * Constructor for DNERR. Sets the position of the object to
	 * [10][3] in the game board. This object acts as a game modifier,
	 * in benefit for the player to accomplish the game's goal.
	 * @param thisQuad - 
	 */
	
	public DNERR()
	{
		
		building = "imgs/level1.png";	
		addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				//loadDNRECMenu();
				System.exit(0);
			}
			public void mousePressed(MouseEvent me){
				//loadDNRECMenu();
				System.out.println("LOADING DNREC");
			}
		});
		loadDNRECMenu();
	}
		
	
	
	private void loadDNRECMenu()
	{
		panel = new JPanel();
		MouseAdapter addCompOnClick = new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				dnrecSel.setVisible(true);
			}
			public void mouseExit(MouseEvent me)
			{
				dnrecSel.setVisible(false);
			}
			public void mousePressed(MouseEvent me)
			{
				
			}
		};
		JLabel charLabel = new JLabel("DNREC",JLabel.CENTER);
		charLabel.setSize(100,100);
		
		dnrecPanel = new JPanel();
		dnrecPanel.setBackground(Color.cyan);
		dnrecPanel.setSize(100,100);
		dnrecPanel.setVisible(false);
		dnrecPanel.add(charLabel);
		
		dnrecSel = new JFrame();
		dnrecSel.setUndecorated(true);
		dnrecSel.setSize(200, 200);
		
		JPanel dnrecLayout =new JPanel();
		dnrecLayout.setLayout(new GridLayout(1,1));
		
		dnrecLayout.add(new JLabel("Pokeball"));
		BufferedImage volunteerIcon = null;
		try {
			volunteerIcon = ImageIO.read(new File("imgs/volunteerIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel volunteerImage = new CharLabel(new ImageIcon(volunteerIcon), eChar.VOLUNTEER);
		volunteerImage.addMouseListener(addCompOnClick);
		dnrecLayout.add(volunteerImage);
		
		dnrecPanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent me)
			{
				dnrecSel.setLocation(dnrecPanel.getLocationOnScreen());
				dnrecSel.setVisible(true);
			}
		});
		
		dnrecSel.addMouseListener(new MouseAdapter()
		{
			public void mouseExited(MouseEvent me)
			{
				dnrecSel.setVisible(false);
			}
		});
		
		
	}
	/**
	 * When the player has enough money, the DNERR object
	 * can be "upgraded" accordingly. This changes the image.
	 */
	
	void upgrade()
	{
		switch(level)
		{
		case 2:
			Environment.money -= 200;
			building = "img/level2";
			break;
		case 3:
			Environment.money -= 500;
			building ="imgs/level3";
			break;
		default:
			building = "img.level2";
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


