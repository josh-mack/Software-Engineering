package Estuary;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	Boolean frameUp;
	JFrame dnerr;
	JProgressBar bar;
	int x;
	int y;
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
	
	public DNERR(int x, int y,int level)
	{
		frameUp = false;
		this.x = x;
		this.y = y;
		
		ImageIcon image = Game.getDNERRImage(level);
		setLayout(new BorderLayout());
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
				frameUp = true;
				bar.setValue(Game.mainEnviro.getHealth());
				bar.setString(Game.mainEnviro.getHealth()+"%");
				bar.setStringPainted(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!frameUp){
					dnerr.setVisible(false);
				}
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
	
	/**
	 * Method to load the DNERR clickable menu, including
	 * all its buttons and the health bar.
	 * @param x
	 * @param y
	 */
	void loadDNERR(int x, int y){

		dnerr = new JFrame();
		dnerr.setUndecorated(true);
		dnerr.setSize(200,300);
		JPanel dnerrPanel = new JPanel();
		
		MouseListener stayOn = new MouseListener(){

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
				if(e.getSource()==dnerr){
					dnerr.setVisible(false);
					frameUp = false;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		
		
		
		
		dnerrPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 5;
		JLabel info = new JLabel("<html><center>DNREC<br>1st Upgrade Cost: $1800<br>2nd Upgrade Cost: $4000</center></html>");
		info.setSize(190, 100);
		dnerrPanel.add(info,c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty =.5;
		bar = new JProgressBar(JProgressBar.HORIZONTAL);
		bar.setSize(190,100);
		dnerrPanel.add(bar, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = .5;
		JButton instaKill = new JButton("PACIFY ($200)");
		instaKill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0)
			{
				if(Game.mainEnviro.money < 200)
					return;
				
				try{
				Game.instakill();
				}catch(IndexOutOfBoundsException e){
					return;
				}
				Game.mainEnviro.money -= 200;
				dnerr.setVisible(false);
				
			}
		});

		instaKill.setSize(190, 100);
		dnerrPanel.add(instaKill,c);
		
		c.gridx = 0;
		c.gridy = 3;
		JButton upgrade = new JButton("UPGRADE");
		
		upgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				upgrade();
				//upgrade.setText("Upgrade ($4000)");
				dnerr.setVisible(false);
			}
		});
		upgrade.setSize(190, 100);
		dnerrPanel.add(upgrade,c);
		dnerrPanel.addMouseListener(stayOn);
		dnerr.add(dnerrPanel);
		dnerr.setLocation(x+100, y-100);
		dnerr.setVisible(false);
		dnerr.addMouseListener(stayOn);
		
		
		
	}
	/**
	 * When the player has enough money, the DNERR object
	 * can be "upgraded" accordingly. This changes the image.
	 */
	
	void upgrade()
	{
		switch(Game.dnrecLevel)
		{
		case 1:
			if(Game.mainEnviro.money < 1800)
			{
				System.out.println("Not Enough money, Can't Upgrade");
				break;
			}
			Game.dnrecLevel++;
			Game.mainEnviro.setNumRes(Game.mainEnviro.getNumRes() + 1);
			Game.mainEnviro.money -= 1800;
			Game.replaceDNERR(x, y);
			
			break;
		case 2:
			if(Game.mainEnviro.money < 4000)
			{
				System.out.println("Not Enough money, Can't Upgrade");
				break;
			}
			Game.dnrecLevel++;
			Game.mainEnviro.setNumRes(Game.mainEnviro.getNumRes() + 1);
			Game.mainEnviro.money -= 4000;
			Game.replaceDNERR(x, y);

			break;
		case 3:
			System.out.println("dnrecc already at max");
			break;
		
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


