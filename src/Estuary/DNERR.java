package Estuary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
	
	int level;
	String building;
	JFrame dnerr;
	int x;
	int y;
	private MouseListener pressListener;
	
	private eChar character;
	private eQuad whatQuad;
	
	/**
	 * Constructor for DNERR. Sets the position of the object to
	 * [10][3] in the game board. This object acts as a game modifier,
	 * in benefit for the player to accomplish the game's goal.
	 * @param thisQuad - 
	 */
	
	public DNERR(int x, int y,int level)
	{
		this.x = x;
		this.y = y;
		this.level = level;
		switch(level)
		{
		case 1:
			building = "imgs/level1.png";
			break;
		case 2:
			building = "imgs/level2.png";
			break;
		case 3:
			building = "imgs/level3.png";
			break;
		default:
			building = "imgs/level2.png";
		}
		setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(building);
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		this.whatQuad = eQuad.N;
		this.character = eChar.DNREC;
		setLocation(x,y);
		loadDNERR(x, y);

		pressListener = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) { 
				dnerr.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				dnerr.setVisible(false);
			}


			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		addMouseListener(pressListener);
		
	}
	
	void loadDNERR(int x, int y){
		dnerr = new JFrame();
		dnerr.setUndecorated(true);
		dnerr.setSize(200,300);
		JPanel dnerrPanel = new JPanel();
		
		dnerrPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 5;
		JLabel info = new JLabel("Boring info about boring animals");
		info.setSize(190, 100);
		dnerrPanel.add(info,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = .5;
		JButton instaKill = new JButton("INSTAKILL");
		instaKill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0)
			{
				//spawn instakill
				//insert method here
			}
		});

		instaKill.setSize(190, 100);
		dnerrPanel.add(instaKill,c);
		
		c.gridx = 0;
		c.gridy = 2;
		JButton upgrade = new JButton("UPGRADE");
		upgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				upgrade();;
			}
		});
		upgrade.setSize(190, 100);
		dnerrPanel.add(upgrade,c);
		
		
		
		
		
		
		
		
		dnerr.add(dnerrPanel);
		dnerr.setLocation(x+100, y-100);
		dnerr.setVisible(false);
		dnerr.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				dnerr.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//dnerr.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		case 1:
			if(Game.mainEnviro.money < 100)
			{
				System.out.println("Not Enough money, Can't Upgrade");
				break;
			}
			Game.dnrecLevel++;
			Game.mainEnviro.setNumRes(Game.mainEnviro.getNumRes() + 1);
			Game.mainEnviro.money -= 100;
			Game.replaceDNERR(x, y);
			
			break;
		case 2:
			if(Game.mainEnviro.money < 100)
			{
				System.out.println("Not Enough money, Can't Upgrade");
				break;
			}
			Game.dnrecLevel++;
			Game.mainEnviro.setNumRes(Game.mainEnviro.getNumRes() + 1);
			Game.mainEnviro.money -= 100;
			Game.replaceDNERR(x, y);

			break;
		case 3:
			System.out.println("dnrecc already at max");
			break;
		default:
			building = "img.level1";
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


