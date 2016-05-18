package Estuary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * In-game window where the player selects a character to drop into the game,
 * so long as the player has characters to spend.
 */
public class Menu{
	ArrayList<JComponent> placedChars = new ArrayList<JComponent>();
	ActionListener hilightListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == hilightTimer1){
				hilightQ1.hilight();
			}
			else if(e.getSource() == hilightTimer2){
				hilightQ2.hilight();
			}
			else if(e.getSource() == hilightTimer3){
				hilightQ3.hilight();
			}
			else if(e.getSource() == hilightTimer4){
				hilightQ4.hilight();
			}
			else if(e.getSource() == hilightMainTimer){
				flashMainMap();
			}
	}};
	
	Timer hilightTimer1 = new Timer(500,hilightListener);
	Timer hilightTimer2 = new Timer(500,hilightListener);
	Timer hilightTimer3 = new Timer(500,hilightListener);
	Timer hilightTimer4 = new Timer(500,hilightListener);
	Timer hilightMainTimer = new Timer(500,hilightListener);

	
	//Pre-loaded Image Variables
	BufferedImage titleImage;
	BufferedImage backgroundOverview1Image;
	BufferedImage backgroundOverview2Image;
	BufferedImage backgroundOverview3Image;
	
	BufferedImage backgroundNorthImage;
	BufferedImage backgroundEastImage;
	BufferedImage backgroundSouthImage;
	BufferedImage backgroundWestImage;

	ImageIcon stewardImage;
	ImageIcon researcherImage;
	ImageIcon volunteerImage;
	
	ImageIcon stewardImageIcon;
	ImageIcon researcherImageIcon;
	ImageIcon volunteerImageIcon;
	
	ImageIcon startButtonImageIcon;
	ImageIcon howToImageIcon;
	ImageIcon tutorialImageIcon;
	ImageIcon topLImageIcon;
	ImageIcon topRImageIcon;
	ImageIcon botLImageIcon;
	ImageIcon botLImageIconHilight;
	boolean mainMapHilighted;
	ImageIcon botRImageIcon;
	JLabel botLImageLabel;
	
	ImageIcon DNERRLvl1Image;
	ImageIcon DNERRLvl2Image;
	ImageIcon DNERRLvl3Image;
	
	ImageIcon cityImage;
	ImageIcon fishermanImage;
	ImageIcon fishermanOverFlowImage;
	
	ImageIcon blueCrabImage;
	ImageIcon horseshoeCrabImage;
	ImageIcon mittenCrabImage;
	ImageIcon zebraMusselImage;
	ImageIcon bambooImage;
	ImageIcon blackEyedSusanImage;
	ImageIcon blazingStarImage;
	ImageIcon phragmitesImage;

	ImageIcon trash;

	

	
	BufferedImage backgroundImage;   //Which Overview to use based on DNERR Level
	ImageIcon dnerrImage = DNERRLvl1Image;
	
	
	public JPanel mainPanel;
	public JFrame mainWindow;
	public JPanel layeringPanel;
	JFrame charSel;
	BackgroundPanel backgroundPanel;
	
	private JLabel stewardLabel;
	private JLabel researcherLabel;
	private JLabel volunteerLabel;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	Dimension topBarSize = new Dimension(width/4, 500);
	Dimension quadSize = new Dimension(width/3, (height/3)-(height/20));
	Dimension mainSize = new Dimension(width, height);
	Color hilightedColor = new Color(216,72,72, 100);
	Color alphaLayer = new Color(0, 0, 0, 0);
	
	eQuad currentQuad = eQuad.MAIN;
	
	QuadPanel hilightQ1;
	QuadPanel hilightQ2;
	QuadPanel hilightQ3;
	QuadPanel hilightQ4;
	boolean charMenuOpen = false;
	private JLabel timeLabel;
	private JLabel scoreLabel;
	boolean inQuad = false;
	
	
	JPanel topL = new JPanel();   //Top Left Corner Panel
	JPanel topR = new JPanel();   //Top Right Corner Panel
	JPanel botL = new JPanel();   //Bottom Left Corner Panel
	JPanel botR = new JPanel();   //Bottom Right Corner Panel
	
	int howToPage = 1;
	boolean ifNotTutorial = true;
	
	
	JFrame howToFrame;
	JPanel howTo1;
	JPanel howTo2;
	JPanel howTo3;
	JPanel howTo4;
	ImageIcon howToImage1;
	ImageIcon howToImage2;
	ImageIcon howToImage3;
	ImageIcon howToImage4;
	
	
	/**
	 * Constructor for the Menu class.
	 * Constructs the drop-down window pane.
	 */
	public Menu(){
		loadImages();
		backgroundImage = backgroundOverview1Image;
		mainWindow = new JFrame();

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainWindow.setUndecorated(true);
		mainWindow.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		if(ifNotTutorial)
			startScreen();
	}	
	
	
	public void startScreen()
	{
		JPanel overAll = new JPanel();
		OverlayLayout over = new OverlayLayout(overAll);
		overAll.setLayout(over);

		BackgroundPanel titleBack = new BackgroundPanel(titleImage, width+50, height);
		titleBack.setSize(width, height);
		JLabel start = new JLabel(startButtonImageIcon);
		JPanel startPanel = new JPanel();
		
		JLabel howTo = new JLabel(howToImageIcon);
		JPanel howToPanel = new JPanel();
		
		JLabel tutorial = new JLabel(tutorialImageIcon);
		JPanel tutorialPanel = new JPanel();
		
		startPanel.setLayout(new BorderLayout());
		startPanel.add(start,BorderLayout.CENTER);
		startPanel.setOpaque(false);
		
		howToPanel.setLayout(new BorderLayout());
		howToPanel.add(start,BorderLayout.CENTER);
		howToPanel.setOpaque(false);
		
		tutorialPanel.setLayout(new BorderLayout());
		tutorialPanel.add(start,BorderLayout.CENTER);
		tutorialPanel.setOpaque(false);
		
		overAll.add(startPanel);
		overAll.add(howToPanel);
		overAll.add(tutorialPanel);
		overAll.add(startPanel);
		overAll.add(titleBack);
		
		start.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				overAll.setVisible(false);
				loadMenu();
				mainWindow.revalidate();
				mainWindow.remove(overAll);
				Game.startTimers();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		
		howTo.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				overAll.setVisible(false);
				loadMenu();
				mainWindow.revalidate();
				mainWindow.remove(overAll);
				Game.startTimers();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		tutorial.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Tutorial tut = new Tutorial();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		overAll.setSize(mainSize);
		overAll.setVisible(true);
		mainWindow.add(overAll);
		mainWindow.setSize(mainSize);
		mainWindow.setVisible(true);


	}
	
	public void endScreen()
	{
		JPanel overAll = new JPanel();
		OverlayLayout over = new OverlayLayout(overAll);
		overAll.setLayout(over);

		BackgroundPanel titleBack = new BackgroundPanel(titleImage, width+50, height);
		titleBack.setSize(width, height);
		JLabel start = new JLabel(startButtonImageIcon);
		JPanel startPanel = new JPanel();
		
		
		startPanel.setLayout(new BorderLayout());
		startPanel.add(start,BorderLayout.CENTER);
		startPanel.setOpaque(false);
		
		overAll.add(startPanel);
		overAll.add(titleBack);
		
		start.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		
		overAll.setSize(mainSize);
		overAll.setVisible(true);
		mainWindow.add(overAll);
		mainWindow.setSize(mainSize);
		mainWindow.setVisible(true);
	}
	
	/**
	 * Creates the overlying quadrant system over the map.
	 * 
	 * When you click on a character in the drop-down menu,
	 * you create that draggable character on the board and
	 * can release the mouse to place the character on the board, 
	 * in addition to buying a new Steward.
	 * 
	 * Also creates the 'Main Menu' and 'Exit' buttons.
	 */
	public void loadMenu(){
		layeringPanel = new JPanel(); //Main Layering Panel
		GridBagConstraints c = new GridBagConstraints();
		
		MouseAdapter addCompOnClick = new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				charSel.setVisible(true);	
			}
			@SuppressWarnings("unused")
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
		mainPanel.add(hilightQ1, c);

		
		//Adding Hilighted Pane to Q2
		hilightQ2 = new QuadPanel(eQuad.E);
		hilightQ2.setPreferredSize(quadSize);
		hilightQ2.setBackground(hilightedColor);
		hilightQ2.setOpaque(false);
		
		c.fill = GridBagConstraints.EAST;
		c.gridx = 2;
		c.gridy = 3;
		
		hilightQ2.addMouseListener(switchQuadOnClick);
		mainPanel.add(hilightQ2, c);
		

		//Adding Hilighted Pane to Q4
		hilightQ4 = new QuadPanel(eQuad.W);
		hilightQ4.setPreferredSize(quadSize);
		hilightQ4.setBackground(hilightedColor);
		hilightQ4.setOpaque(false);
		
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		
		hilightQ4.addMouseListener(switchQuadOnClick);
		mainPanel.add(hilightQ4, c);
		
		
		//Adding Hilighted Pane to Q3
		hilightQ3 = new QuadPanel(eQuad.S);
		hilightQ3.setPreferredSize(quadSize);
		hilightQ3.setBackground(hilightedColor);
		hilightQ3.setOpaque(false);
		
		c.fill = GridBagConstraints.SOUTH;
		c.gridx = 1;
		c.gridy = 4;
		
		hilightQ3.addMouseListener(switchQuadOnClick);
		mainPanel.add(hilightQ3, c);
		

		backgroundPanel = new BackgroundPanel(backgroundOverview1Image, width, height);

		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////OLD CODE
		timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		scoreLabel = new JLabel("MONEY: $200", JLabel.CENTER);
		
		
		
		
		charSel = new JFrame();
		charSel.setUndecorated(true);
		charSel.setSize(width/6, height/4);
		JPanel charSelection = new JPanel();
		charSelection.setLayout(new GridLayout(3, 2));
		
		
		stewardLabel = new JLabel("Stewards: 2");
		//charSelection.add(stewardLabel);
	
		JLabel stewardImage = new CharLabel(stewardImageIcon, eChar.STEWARD);
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
		buySteward.setSize(width/12, height/24);
		
		
		JPanel stewardPanel = new JPanel();
		stewardPanel.add(stewardLabel);
		stewardPanel.add(buySteward);
		charSelection.add(stewardPanel);
		
		
		//Steward Panel
		JPanel stewPanel = new JPanel();		
		stewPanel.add(stewardImage);
		//stewPanel.add(buySteward);
		charSelection.add(stewPanel);
		
		charSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				stewPanel.setVisible(true);
			}
		});

		
		researcherLabel = new JLabel("Researchers: 1");
		charSelection.add(researcherLabel);
		
		JLabel researcherImage = new CharLabel(researcherImageIcon, eChar.RESEARCHER);
		researcherImage.addMouseListener(addCompOnClick);
		charSelection.add(researcherImage);
		
		
		volunteerLabel = new JLabel("Volunteers: 3");
		
		JLabel volunteerImage = new CharLabel(volunteerImageIcon, eChar.VOLUNTEER);
		volunteerImage.addMouseListener(addCompOnClick);
		charSelection.add(volunteerLabel);
		charSelection.add(volunteerImage);
		
		
		charSel.add(charSelection);
		charSel.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		
		
		topL.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent me){
				if(inQuad){
					charSel.setLocation(topL.getLocationOnScreen());
					charSel.setVisible(true);
					
				}
			}
		});

		charSel.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent me){
				charSel.setVisible(false);
			}
		});

		
		
		
		
		JPanel timeFrame = new JPanel();
		timeFrame.setBackground(Color.BLACK);
		timeLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.white);
		timeFrame.setSize(width/5, height/10);
		
		
		
		botR.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		botL.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				clearList();
				loadQuad(eQuad.MAIN);				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		
		
		
		

		timeFrame.add(timeLabel);
		timeFrame.add(scoreLabel);
		topR.add(timeFrame);
		
		
		topL.add(new JLabel(topLImageIcon));
		topL.setOpaque(false);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		topL.setVisible(false);
		mainPanel.add(topL, c);
		
		
		botR.add(new JLabel(botRImageIcon));
		botR.setOpaque(false);
		c.fill = 0;
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 5;
		c.gridheight = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.SOUTHEAST;
		mainPanel.add(botR, c);
		
		

		topR.add(new JLabel(topRImageIcon));
		topR.setOpaque(false);
		c.fill = 0;
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 5;
		c.gridheight = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHEAST;
		mainPanel.add(topR, c);

		botLImageLabel = new JLabel(botLImageIcon);
		botL.add(botLImageLabel);
		botL.setOpaque(false);
		c.fill = 0;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 5;
		c.gridheight = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.SOUTHWEST;
		botL.setVisible(false);
		mainPanel.add(botL, c);

		makeLayeredPane();
	
		mainWindow.add(layeringPanel);
		mainWindow.setSize(mainSize);
		mainWindow.setVisible(true);
		
		}

	public ArrayList<JComponent> getPlacedChars() {
		return placedChars;
	}


	public void setPlacedChars(ArrayList<JComponent> placedChars) {
		this.placedChars = placedChars;
	}


	/**
	 * Method for the highlighting system where the player can
	 * see where the clickable quadrants are. Just a switch.
	 * @param sel
	 * @param main
	 */
	public void hilightOff(eQuad quad){
		switch(quad){
		case N:
			hilightTimer1.stop();
			hilightQ1.hilightOff();
			break;
		case E:
			hilightTimer2.stop();
			hilightQ2.hilightOff();
			break;
		case S:
			hilightTimer3.stop();
			hilightQ3.hilightOff();
			break;
		case W:
			hilightTimer4.stop();
			hilightQ4.hilightOff();
			break;
		default:
			break;
		}
	}
	public void pauseHilight(){
			hilightTimer1.stop();
			hilightTimer2.stop();
			hilightTimer3.stop();
			hilightTimer4.stop();
			hilightQ1.setOpaque(false);
			hilightQ2.setOpaque(false);
			hilightQ3.setOpaque(false);
			hilightQ4.setOpaque(false);

	}
	public void resumeHilight(){
		if(hilightQ1.checkHilight())
			hilightTimer1.start();
		else if(hilightQ2.checkHilight())
			hilightTimer2.start();
		else if(hilightQ3.checkHilight())
			hilightTimer3.start();
		else if(hilightQ4.checkHilight())
			hilightTimer4.start();
	}
	
	public void hilightOn(eQuad quad){
		hilightMainTimer.start();
		switch(quad){
		case N:
			hilightQ1.hilightOn();
			break;
		case E:
			hilightQ2.hilightOn();
			break;
		case S:
			hilightQ3.hilightOn();
			break;
		case W:
			hilightQ4.hilightOn();
			break;
		default:
			break;
		}
		if(currentQuad == eQuad.MAIN)
			resumeHilight();
	}
	
	/**
	 * Method to change the image of the map to the
	 * quadrant the player is zoomed into.
	 * @param quad
	 */
	private void loadQuad(eQuad quad) {
		System.out.println("quadtoLoad is "+quad);
		if(inQuad && (quad != eQuad.MAIN)){
			//Don't listen in quadrants
			return;
		}
		pauseHilight();

		if(quad!=eQuad.MAIN){
			hilightOff(quad);
			botLImageLabel.setIcon(botLImageIcon);
		}

		switch(quad){
			case MAIN:
				
				hilightMainTimer.stop();
				resumeHilight();
				Game.drawOnScreen(mainWindow.getLayeredPane(),quad, false);	
				inQuad = false;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, backgroundImage);
				topL.setVisible(false);
				botL.setVisible(false);
				mainWindow.repaint();
				mainWindow.revalidate();
			break;
			case N:
				
				Game.drawOnScreen(mainWindow.getLayeredPane(),quad, true);	
				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, backgroundNorthImage);
				topL.setVisible(true);
				botL.setVisible(true);
				mainWindow.repaint();
				mainWindow.revalidate();
			break;
			case W:
				Game.drawOnScreen(mainWindow.getLayeredPane(),quad, true);	

				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, backgroundWestImage);
				topL.setVisible(true);
				botL.setVisible(true);
				mainWindow.repaint();
				mainWindow.revalidate();
			break;
			case S:
				Game.drawOnScreen(mainWindow.getLayeredPane(),quad, true);	

				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, backgroundSouthImage);
				topL.setVisible(true);
				botL.setVisible(true);
				mainWindow.repaint();
				mainWindow.revalidate();
			break;
			case E:
				Game.drawOnScreen(mainWindow.getLayeredPane(),quad, true);	

				inQuad = true;
				currentQuad = quad;
				backgroundPanel.paintComponent(null, backgroundEastImage);
				topL.setVisible(true);
				botL.setVisible(true);
				mainWindow.repaint();
				mainWindow.revalidate();
			break;
		}

		
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
				charPlace = new DragComponent(stewardImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseStew(false);
			}
			break;
		case RESEARCHER:
			if (Game.mainEnviro.getNumRes() > 0) {
				charPlace = new DragComponent(researcherImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseRes(false);
			}
			break;
		case VOLUNTEER:
			if (Game.mainEnviro.getNumVol() > 0) {
				charPlace = new DragComponent(volunteerImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseVol(false);
			}
			break;
		case WETSTEWARD:
			if (Game.mainEnviro.getNumStew() > 0) {
				charPlace = new DragComponent(stewardImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseStew(false);
			}
			break;
		case WETRESEARCHER:
			if (Game.mainEnviro.getNumRes() > 0) {
				charPlace = new DragComponent(researcherImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseRes(false);
			}
			break;
		case WETVOLUNTEER:
			if (Game.mainEnviro.getNumVol() > 0) {
				charPlace = new DragComponent(volunteerImage,currentQuad, eChar, x, y,0,0);
				Game.mainEnviro.increaseVol(false);
			}
			break;
		default:
			break;
		}
		
		placeComp2(charPlace);

		return charPlace;
	}
	
	/**
	 * Creates the layered panel over the main panel.
	 */
	public void makeLayeredPane(){
		LayoutManager overlay = new OverlayLayout(layeringPanel);
		layeringPanel.setLayout(overlay);

		
		mainPanel.setOpaque(false);
		mainPanel.setBackground(alphaLayer);
		backgroundPanel.setBackground(alphaLayer);
		

		layeringPanel.add(mainPanel);
		layeringPanel.revalidate();
		
		layeringPanel.add(backgroundPanel);
		
		
		mainWindow.add(layeringPanel);
		mainWindow.setSize(mainSize);
		mainWindow.setVisible(true);
				
	}
	

	
	/**
	 * Accessor methods for the panels and quadrants.
	 * @return
	 */
	public JLabel getTimeLabel() {
		// TODO Auto-generated method stub
		return this.timeLabel;
	}
	public JFrame getMainWindow() {
		// TODO Auto-generated method stub
		return mainWindow;
	}
	public JPanel getLayeringPanel() {
		// TODO Auto-generated method stub
		return layeringPanel;
	}
	public JLabel getScoreLabel() {
		// TODO Auto-generated method stub
		return this.scoreLabel;
	}
	public eQuad getQuadrant(){
		return currentQuad;
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
	
	public void placeComp(JComponent toAdd){
		mainWindow.getLayeredPane().add(toAdd,0);
		placedChars.add(toAdd);
		mainWindow.repaint();
		mainWindow.revalidate();
		return;
	}
	public void placeComp2(JComponent toAdd){
		mainWindow.getLayeredPane().add(toAdd,-1);
		placedChars.add(toAdd);
		mainWindow.repaint();
		mainWindow.revalidate();
		return;
	}
	public void placeComp3(JComponent toAdd){
		mainWindow.getLayeredPane().add(toAdd,-2);
		placedChars.add(toAdd);
		mainWindow.repaint();
		mainWindow.revalidate();
		return;
	}
	public void removeComp(JComponent toRemove){
		if(placedChars.remove(toRemove)){
			mainWindow.getLayeredPane().remove(toRemove);
			mainWindow.repaint();
			mainWindow.revalidate();
		}
	}
	public void clearList(){
		System.out.println("Pre Clear\n"+placedChars);
		for(JComponent del:placedChars){
			mainWindow.getLayeredPane().remove(del);
		}
		System.out.println("Post Clear\n"+placedChars);
		mainWindow.repaint();
		mainWindow.revalidate();
		placedChars.clear();
		return;
	}
	
	public void changeOverview(int dnerrLvl){
		backgroundImage = (dnerrLvl == 2)?backgroundOverview2Image:backgroundOverview3Image;
	}
	public void loadImages(){
		try{
		titleImage = ImageIO.read(new File("imgs/titleScreen.png"));
		
		BufferedImage charIcon = ImageIO.read(new File("imgs/stewardIcon.png"));
		stewardImageIcon = new ImageIcon(charIcon.getScaledInstance(width/14, height/14, Image.SCALE_SMOOTH));
		
		charIcon = ImageIO.read(new File("imgs/researcherIcon.png"));
		researcherImageIcon = new ImageIcon(charIcon.getScaledInstance(width/14, height/14, Image.SCALE_SMOOTH));
		
		charIcon = ImageIO.read(new File("imgs/volunteerIcon.png"));
		volunteerImageIcon = new ImageIcon(charIcon.getScaledInstance(width/14, height/14, Image.SCALE_SMOOTH));

		
		stewardImage= new ImageIcon("imgs/steward.png");
		researcherImage= new ImageIcon("imgs/researcher.png");
		volunteerImage= new ImageIcon("imgs/volunteer.png");
		
		backgroundOverview1Image= ImageIO.read(new File("imgs/overview1.png"));
		backgroundOverview2Image= ImageIO.read(new File("imgs/overview2.png"));
		backgroundOverview3Image= ImageIO.read(new File("imgs/overview3.png"));
		
		backgroundNorthImage= ImageIO.read(new File("imgs/north.png"));
		backgroundEastImage= ImageIO.read(new File("imgs/east.png"));
		backgroundSouthImage= ImageIO.read(new File("imgs/south.png"));
		backgroundWestImage= ImageIO.read(new File("imgs/west.png"));
		
		startButtonImageIcon= new ImageIcon("imgs/start.png");
		topLImageIcon= new ImageIcon("imgs/TLCorner.png");
		topRImageIcon= new ImageIcon("imgs/TRCorner.png");
		botLImageIcon= new ImageIcon("imgs/BLCorner.png");
		botLImageIconHilight = new ImageIcon("imgs/BLCornerHilighted.png");
		botRImageIcon= new ImageIcon("imgs/BRCorner.png");
		
		DNERRLvl1Image= new ImageIcon("imgs/level1.png");
		DNERRLvl2Image= new ImageIcon("imgs/level2.png");
		DNERRLvl3Image= new ImageIcon("imgs/level3.png");
		
		fishermanImage= new ImageIcon("imgs/fisherman.png");
		fishermanOverFlowImage = new ImageIcon("imgs/fishermanOverfishing.png");
		cityImage = new ImageIcon("imgs/city.png");
		
		mittenCrabImage = new ImageIcon("imgs/mittenCrab.png");
		phragmitesImage = new ImageIcon("imgs/phragmites.png");
		bambooImage = new ImageIcon("imgs/bamboo.png");
		zebraMusselImage = new ImageIcon("imgs/zebraMussel.png");
		blazingStarImage = new ImageIcon("imgs/blazingStar.png");
		horseshoeCrabImage = new ImageIcon("imgs/horseShoeCrab.png");
		blackEyedSusanImage = new ImageIcon("imgs/blackEyedSusan.png");
		blueCrabImage = new ImageIcon("imgs/blueCrab.png");
		trash = new ImageIcon("imgs/x.png");
		howToImage1 = new ImageIcon("imgs/howTo.png");
		howToImage2 = new ImageIcon("imgs/howTo2.png");
		howToImage3 = new ImageIcon("imgs/howTo3.png");
		howToImage4 = new ImageIcon("imgs/howTo4.png");

		}catch(IOException e){
			System.out.println("Error: Some images weren't found");
		}
	}
	
	public ImageIcon getImage(eChar select) {
		switch(select){
			case MCRAB:
				return mittenCrabImage;// = "imgs/mittencrab_0.png";
			case PHRAG:
				return phragmitesImage;//  = "imgs/phragmite_spawning3.png";
			case BAMBOO:
				return bambooImage;// = "imgs/bamboo.png";
			case ZEBRA:
				return zebraMusselImage;// = "imgs/zebramussel.png";
			case BLAZINGSTAR:
				return blazingStarImage; //filename = "imgs/blazingstarplant.png";
			case HCRAB:
				return horseshoeCrabImage; //filename = "imgs/horseshoe_crab_left_1.png";
			case BLACKEYEDSUSAN:
				return blackEyedSusanImage;//filename = "imgs/susan.png";
			case BCRAB:
				return blueCrabImage;//filename = "imgs/bluecrab_0.png";
			case CITY:
				return cityImage;
			case FISHERMAN:
				return fishermanImage;
			case TRASH:
				return trash;
			case WETMCRAB:
				return mittenCrabImage;
			case WETHCRAB:
				return horseshoeCrabImage;
			case WETBCRAB:
				return blueCrabImage;
			default:
				return null;
			
			}
	}
	public void changeDNERR(int level){
		dnerrImage = (level == 2)?DNERRLvl2Image:DNERRLvl2Image;
	}
	public void flashMainMap(){
		if(mainMapHilighted){
			botLImageLabel.setIcon(botLImageIcon);
		}
		else{
			botLImageLabel.setIcon(botLImageIconHilight);
		}
		mainMapHilighted = !mainMapHilighted;
		botL.repaint();
	}
	
	
	public void loadHowtoPlay(){				
		MouseListener nextPageListener = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				changePage();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
			howToFrame = new JFrame();
			howTo1 = new JPanel();
			howTo1.add(new JLabel(howToImage1));
			howTo1.addMouseListener(nextPageListener);
			
			howTo2 = new JPanel();
			howTo2.add(new JLabel(howToImage2));
			howTo2.addMouseListener(nextPageListener);
			
			howTo3 = new JPanel();
			howTo3.add(new JLabel(howToImage4));
			howTo3.addMouseListener(nextPageListener);
			
			howTo4 = new JPanel();
			howTo4.add(new JLabel(howToImage4));
			howTo4.addMouseListener(nextPageListener);
			
			howToFrame.addMouseListener(nextPageListener);
			
	}
	
	public void changePage(){
		switch(howToPage){
		case 0:
			howToPage = 1;
			howToFrame.add(howTo1);
		case 1:
			howToPage = 2;
			howToFrame.add(howTo2);
			break;
		case 2:
			howToPage = 3;
			howToFrame.add(howTo3);
			break;
		case 3:
			howToPage = 4;
			howToFrame.add(howTo4);
			break;
		case 4:
			howToPage = 0;
			howToFrame.dispose();
		}
		howToFrame.revalidate();

	}


}
	
	