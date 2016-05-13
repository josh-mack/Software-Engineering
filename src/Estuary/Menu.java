package Estuary;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * In-game window where the player selects a character to drop into the game,
 * so long as the player has characters to spend.
 */
public class Menu{
	public JPanel background;
	public JFrame main;
	public JPanel panel;
	JFrame charSel;
	private JPanel charFrame;
	JButton exit;
	JButton mainMap;
	BackgroundTest backgroundPanel;
	
	private JLabel stewardLabel;
	private JLabel researcherLabel;
	private JLabel volunteerLabel;
	
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	Dimension topBarSize = new Dimension(width/4, 500);
	Dimension quadSize = new Dimension(width/3, (height/3)-(height/20));
	Dimension mainSize = new Dimension(width, height);
	Color hilightedColor = new Color(0,0,0, 100);
	Color alphaLayer = new Color(0, 0, 0, 0);
	
	eQuad currentQuad = eQuad.MAIN;
	
	int sel;
	JPanel hilightQ1;
	JPanel hilightQ2;
	JPanel hilightQ3;
	JPanel hilightQ4;
	boolean charMenuOpen = false;
	private JLabel timeLabel;
	private JLabel scoreLabel;
	boolean inQuad = false;
	
	/**
	 * Constructor for the Menu class.
	 * Constructs the drop-down window pane.
	 */
	public Menu(){
		sel = 0;
		main = new JFrame();

		background = new JPanel();
		background.setLayout(new GridBagLayout());
		
		main.setUndecorated(true);
		main.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		startScreen();
		//loadMenu();
	}	
	
	
	public void startScreen()
	{
		panel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		JButton start = new JButton("Start");
		JLabel title = new JLabel("SimEstuary 2");
		
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(title,c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(start, c);
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0)
			{
				loadMenu();
				panel.setVisible(false);
			}
		});
		main.add(panel);
	}
	
	/**
	 * Creates the overlying quadrant system over the map.
	 * 
	 * When you click on a character in the drop-down menu,
	 * you create that draggable character on the board and
	 * can release the mouse to place the character on the board.
	 * 
	 * Also creates the 'Main Menu' and 'Exit' buttons.
	 */
	public void loadMenu(){
		panel = new JPanel(); //Main Layering Panel
		GridBagConstraints c = new GridBagConstraints();
		
		MouseAdapter addCompOnClick = new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				charSel.setVisible(true);	
			}
			public void mouseExit(MouseEvent me){
				charSel.setVisible(false);	
			}

			public void mousePressed(MouseEvent me){
				createChar((((CharLabel) me.getSource()).getType()), me.getXOnScreen(), me.getYOnScreen());	
				charSel.setVisible(false);	
			}
		};
		
		MouseAdapter switchQuadOnClick = new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				loadQuad(((QuadPanel) me.getSource()).getLoc());				
			}
		};
		
		//Adding Hilighted Pane to Q1
		hilightQ1 = new QuadPanel(eQuad.N);
		hilightQ1.setPreferredSize(quadSize);
		hilightQ1.setBackground(hilightedColor);
		hilightQ1.setOpaque(false);
		
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 1;
		c.gridy = 1;
		
		hilightQ1.addMouseListener(switchQuadOnClick);
		background.add(hilightQ1, c);

		
		//Adding Hilighted Pane to Q2
		hilightQ2 = new QuadPanel(eQuad.E);
		hilightQ2.setPreferredSize(quadSize);
		hilightQ2.setBackground(hilightedColor);
		hilightQ2.setOpaque(false);
		
		c.fill = GridBagConstraints.EAST;
		c.gridx = 2;
		c.gridy = 3;
		
		hilightQ2.addMouseListener(switchQuadOnClick);
		background.add(hilightQ2, c);
		

		//Adding Hilighted Pane to Q3
		hilightQ3 = new QuadPanel(eQuad.W);
		hilightQ3.setPreferredSize(quadSize);
		hilightQ3.setBackground(hilightedColor);
		hilightQ3.setOpaque(false);
		
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		
		hilightQ3.addMouseListener(switchQuadOnClick);
		background.add(hilightQ3, c);
		
		
		//Adding Hilighted Pane to Q4
		hilightQ4 = new QuadPanel(eQuad.S);
		hilightQ4.setPreferredSize(quadSize);
		hilightQ4.setBackground(hilightedColor);
		hilightQ4.setOpaque(false);
		
		c.fill = GridBagConstraints.SOUTH;
		c.gridx = 1;
		c.gridy = 4;
		
		hilightQ4.addMouseListener(switchQuadOnClick);
		background.add(hilightQ4, c);
		

		backgroundPanel = new BackgroundTest("imgs/overview.png", width, height);

		JPanel topBarLeft = new JPanel();
		topBarLeft.setLayout(new GridBagLayout());
		

		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////OLD CODE
		timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		scoreLabel = new JLabel("MONEY: $200", JLabel.CENTER);
		
		
		JLabel charLabel = new JLabel("Char Selection", JLabel.CENTER);
		charFrame = new JPanel();
		charFrame.setBackground(Color.BLACK);
		charLabel.setForeground(Color.white);
		charFrame.setSize(width/5, height/10);
		charFrame.setVisible(false);
		
		charSel = new JFrame();
		charSel.setUndecorated(true);
		charSel.setSize(width/6, height/2);
		JPanel charSelection = new JPanel();
		charSelection.setLayout(new GridLayout(3, 2));
		
		
		stewardLabel = new JLabel("Stewards: 2");
		charSelection.add(stewardLabel);
		BufferedImage stewardIcon = null;
		try {
			stewardIcon = ImageIO.read(new File("imgs/volunteer_blueshirt_front_0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel stewardImage = new CharLabel(new ImageIcon(stewardIcon), eChar.STEWARD);
		stewardImage.addMouseListener(addCompOnClick);
	//	charSelection.add(stewardImage);
		
		//Button to buy a Steward
		JButton buySteward = new JButton("Buy a Steward!");
		buySteward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (Game.mainEnviro.money < 50) {
					System.out.println("Not enough money");
					return;
				}
				Game.mainEnviro.money -= 50;
				Game.mainEnviro.increaseStew(true);
			}
		});		
		
		//Steward Panel
		JPanel stewPanel = new JPanel();		
		stewPanel.add(stewardImage);
		stewPanel.add(buySteward);
		charSelection.add(stewPanel);
		
		charSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				stewPanel.setVisible(true);
			}
		});

		
		researcherLabel = new JLabel("Researchers: 1");
		charSelection.add(researcherLabel);
		BufferedImage researcherIcon = null;
		try {
			researcherIcon = ImageIO.read(new File("imgs/researcher_withClipboard.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel researcherImage = new CharLabel(new ImageIcon(researcherIcon), eChar.RESEARCHER);
		researcherImage.addMouseListener(addCompOnClick);
		charSelection.add(researcherImage);
		
		
		volunteerLabel = new JLabel("Volunteers: 3");
		charSelection.add(volunteerLabel);
		BufferedImage volunteerIcon = null;
		try {
			volunteerIcon = ImageIO.read(new File("imgs/volunteer_redshirt_walk_front_0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel volunteerImage = new CharLabel(new ImageIcon(volunteerIcon), eChar.VOLUNTEER);
		volunteerImage.addMouseListener(addCompOnClick);
		charSelection.add(volunteerImage);
		
		
		charSel.add(charSelection);
		charSel.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		
		
		charFrame.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent me){
				if(inQuad){
					charSel.setLocation(charFrame.getLocationOnScreen());
					charSel.setVisible(true);
					
				}
			}
		});

		charSel.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent me){
				charSel.setVisible(false);
			}
		});

		
		charLabel.setSize(width/5, height/10);
		
		
		
		JPanel timeFrame = new JPanel();
		timeFrame.setBackground(Color.BLACK);
		timeLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.white);
		timeFrame.setSize(width/5, height/10);
		
		
		
		JPanel exitBar = new JPanel();
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mainMap = new JButton("Main Map");
		mainMap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				loadQuad(eQuad.MAIN);				
				Component[] junkLayer = main.getLayeredPane().getComponentsInLayer(0);
				for(Component del: junkLayer){
					main.getLayeredPane().remove(del);
					main.revalidate();
					main.repaint();
				}
			}
		});
		
		mainMap.setVisible(false);
		exitBar.add(mainMap);
		exitBar.add(exit);
		exitBar.setOpaque(false);

		charFrame.add(charLabel);
		timeFrame.add(timeLabel);
		timeFrame.add(scoreLabel);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		topBarLeft.add(charFrame, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 2;
		c.gridy = 0;
		topBarLeft.add(exitBar, c);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 3;
		c.gridy = 0;		
		c.anchor = GridBagConstraints.NORTHEAST;


		topBarLeft.add(timeFrame, c);
		

		topBarLeft.setOpaque(false);
		/////////////////////////////////////////////
		
		

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		background.add(topBarLeft, c);
		
		makeLayeredPane();
	
		main.add(panel);
		main.setSize(mainSize);
		main.setVisible(true);
		
		}

	/**
	 * Method for the highlighting system where the player can
	 * see where the clickable quadrants are. Just a switch.
	 * @param sel
	 * @param main
	 */
	public void hilight(int sel,Menu main){
		switch(sel){
		case 0:
			main.hilightQ1.setOpaque(true);
			main.hilightQ1.getRootPane().repaint();
			break;
		case 1:
			main.hilightQ1.setOpaque(false);
			main.hilightQ1.getRootPane().repaint();
			break;
		case 2:
			main.hilightQ2.setOpaque(true);
			main.hilightQ2.getRootPane().repaint();
			break;
		case 3:
			main.hilightQ2.setOpaque(false);
			main.hilightQ2.getRootPane().repaint();
			break;
		case 4:
			main.hilightQ3.setOpaque(true);
			main.hilightQ3.getRootPane().repaint();
			break;
		case 5:
			main.hilightQ3.setOpaque(false);
			main.hilightQ3.getRootPane().repaint();
			break;
		case 6:
			main.hilightQ4.setOpaque(true);
			main.hilightQ4.getRootPane().repaint();
			break;
		case 7:
			main.hilightQ4.setOpaque(false);
			main.hilightQ4.getRootPane().repaint();
			break;
			
		}
	}
	
	/**
	 * Method to change the image of the map to the
	 * quadrant the player is zoomed into.
	 * @param quad
	 */
	private void loadQuad(eQuad quad) {
		if(inQuad && (quad != eQuad.MAIN)){
			//Don't listen in quadrants
			return;
		}

		Game.drawOnScreen(main.getLayeredPane(),quad, true);	
		switch(quad){
			case MAIN:
				inQuad = false;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, "imgs/overview.png");
				charFrame.setVisible(false);
				mainMap.setVisible(false);
				main.repaint();
				main.revalidate();
			break;
			case N:
				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, "imgs/N1.png");
				charFrame.setVisible(true);
				mainMap.setVisible(true);
				main.repaint();
				main.revalidate();
			break;
			case W:
				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, "imgs/W1.png");
				charFrame.setVisible(true);
				mainMap.setVisible(true);
				main.repaint();
				main.revalidate();
			break;
			case S:
				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, "imgs/S1.png");
				charFrame.setVisible(true);
				mainMap.setVisible(true);
				main.repaint();
				main.revalidate();
			break;
			case E:
				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, "imgs/E1.png");
				charFrame.setVisible(true);
				mainMap.setVisible(true);
				main.repaint();
				main.revalidate();
			break;
		}
		main.repaint();

		
	}
	
	/**
	 * Creates a new draggable character based on which character
	 * was selected on the character selection menu, created a few
	 * methods above. If the character is not zoomed into a quadrant,
	 * a character cannot be created.
	 * @param eChar
	 * @param x
	 * @param y
	 * @return
	 */
	public DragComponent createChar(eChar eChar, int x, int y){
		if(!inQuad){
			return null;
		}
		
		DragComponent charPlace = null;
		switch(eChar){
		case STEWARD:
			if (Game.mainEnviro.getNumStew() > 0) {
				charPlace = new DragComponent("imgs/volunteer_blueshirt_front_0.png",currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseStew(false);
			}
			break;
		case RESEARCHER:
			if (Game.mainEnviro.getNumRes() > 0) {
				charPlace = new DragComponent("imgs/researcher_withClipboard.png",currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseRes(false);
			}
			break;
		case VOLUNTEER:
			if (Game.mainEnviro.getNumVol() > 0) {
				charPlace = new DragComponent("imgs/volunteer_redshirt_walk_front_0.png",currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseVol(false);
			}
			break;
		default:
			break;
		}
		
		main.getLayeredPane().add(charPlace);
		return charPlace;
	}
	
	/**
	 * Creates the layered panel over the main panel.
	 */
	public void makeLayeredPane(){
		LayoutManager overlay = new OverlayLayout(panel);
		panel.setLayout(overlay);

		
		background.setOpaque(false);
		background.setBackground(alphaLayer);
		backgroundPanel.setBackground(alphaLayer);
		

		panel.add(background);
		panel.revalidate();
		
		panel.add(backgroundPanel);
		
		
		main.add(panel);
		main.setSize(mainSize);
		main.setVisible(true);
				
	}
	

	public void nukePane(eQuad quad)
	{
		loadQuad(quad);				
		Component[] junkLayer = main.getLayeredPane().getComponentsInLayer(0);
		for(Component del: junkLayer){
			main.getLayeredPane().remove(del);
			//main.revalidate();
			//main.repaint();
		Game.drawOnScreen(main.getLayeredPane(), quad, true);
		}
	}
	
	/**
	 * Accessor methods for the panels and quadrants.
	 * @return
	 */
	public JLabel getTimeLabel() {
		// TODO Auto-generated method stub
		return this.timeLabel;
	}
	public JFrame getMenu() {
		// TODO Auto-generated method stub
		return main;
	}
	public JPanel getMainScreen() {
		// TODO Auto-generated method stub
		return panel;
	}
	public JLabel getScoreLabel() {
		// TODO Auto-generated method stub
		return this.scoreLabel;
	}
	public eQuad getQuadrant(){
		return currentQuad;
	}
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return panel;
	}
	
	public JLabel getStewardLabel() {
		return this.stewardLabel;
	}
	
	public JLabel getVolunteerLabel() {
		return this.volunteerLabel;
	}
	
	public JLabel getResearcherLabel() {
		return this.researcherLabel;
	}
	
}
	
	