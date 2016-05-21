package View;

import Model.*;
import Controller.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
public class View{
	ArrayList<JComponent> placedChars = new ArrayList<JComponent>();
	ActionListener highlightListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == highlightTimer1){
				highlightQ1.highlight();
			}
			else if(e.getSource() == highlightTimer2){
				highlightQ2.highlight();
			}
			else if(e.getSource() == highlightTimer3){
				highlightQ3.highlight();
			}
			else if(e.getSource() == highlightTimer4){
				highlightQ4.highlight();
			}
			else if(e.getSource() == highlightMainTimer){
				flashMainMap();
			}
	}};
	
	Timer highlightTimer1 = new Timer(500,highlightListener);
	Timer highlightTimer2 = new Timer(500,highlightListener);
	Timer highlightTimer3 = new Timer(500,highlightListener);
	Timer highlightTimer4 = new Timer(500,highlightListener);
	Timer highlightMainTimer = new Timer(500,highlightListener);

	
	//Pre-loaded Image Variables
	BufferedImage titleImage;
	BufferedImage endImage;
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
	Image botLImage;
	Image botRImage;
	Image topLImage;
	Image botLImageHighlight;

	ImageIcon botLImageIconhighlight;
	boolean mainMaphighlighted;
	ImageIcon botRImageIcon;
	JLabel botLImageLabel;
	JLabel botRImageLabel;
	JLabel topLImageLabel;
	JLabel topRImageLabel;
	
	static ImageIcon DNERRLvl1Image;
	static ImageIcon DNERRLvl2Image;
	static ImageIcon DNERRLvl3Image;
	
	ImageIcon cityImage;
	static ImageIcon fishermanImage;
	static ImageIcon fishermanOverFlowImage;
	
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
	static ImageIcon dnerrImage = DNERRLvl1Image;
	
	
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
	Dimension quadSize = new Dimension(width/3, (height/3));
	Dimension mainSize = new Dimension(width, height);
	Color highlightedColor = new Color(216,72,72, 100);
	Color alphaLayer = new Color(0, 0, 0, 0);
	
	eQuad currentQuad = eQuad.MAIN;
	
	QuadPanel highlightQ1;
	QuadPanel highlightQ2;
	QuadPanel highlightQ3;
	QuadPanel highlightQ4;
	boolean charMenuOpen = false;
	//private JLabel timeLabel;
	private JLabel scoreLabel;
	boolean inQuad = false;
	
	
	JPanel topL = new JPanel();   //Top Left Corner Panel
	JPanel topR = new JPanel();   //Top Right Corner Panel
	JPanel botL = new JPanel();   //Bottom Left Corner Panel
	JPanel botR = new JPanel();   //Bottom Right Corner Panel
	
	int howToPage = 0;
	boolean ifNotTutorial = true;
	
	
	JPanel overAll;
	JFrame howToFrame;
	JPanel howTo1;
	JPanel howTo2;
	JPanel howTo3;
	JPanel howTo4;
	ImageIcon howToImage1;
	ImageIcon howToImage2;
	ImageIcon howToImage3;
	ImageIcon howToImage4;
	JPanel howToPanel;
	
	
	/**
	 * Constructor for the Menu class.
	 * Constructs the drop-down window pane.
	 */
	public View(){
		loadImages();
		backgroundImage = backgroundOverview1Image;
		mainWindow = new JFrame();

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 3));
		mainPanel.setSize(mainSize);
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
		overAll = new JPanel();
		OverlayLayout over = new OverlayLayout(overAll);
		overAll.setLayout(over);
		
		//GridBagConstraints c = new GridBagConstraints();

		BackgroundPanel titleBack = new BackgroundPanel(titleImage, width, height);
		titleBack.setSize(width, height);
		JLabel start = new JLabel(startButtonImageIcon);
		JPanel startPanel = new JPanel();
		
		JLabel howTo = new JLabel(howToImageIcon);
		howToPanel = new JPanel();
		
		JLabel tutorial = new JLabel(tutorialImageIcon);
		JPanel tutorialPanel = new JPanel();
		
		startPanel.setLayout(new BorderLayout());
		startPanel.add(start,BorderLayout.EAST);
		startPanel.setOpaque(false);
		
		howToPanel.setLayout(new BorderLayout());
		howToPanel.add(howTo,BorderLayout.WEST);
		howToPanel.setOpaque(false);
		
		tutorialPanel.setLayout(new BorderLayout());
		tutorialPanel.add(tutorial,BorderLayout.SOUTH);
		tutorialPanel.setOpaque(false);
		
		overAll.add(startPanel);
		overAll.add(howToPanel);
		//overAll.add(tutorialPanel);
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
				loadHowtoPlay();
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
			public void mouseClicked(MouseEvent arg0) 
			{
				overAll.setVisible(false);
				Tutorial tut = new Tutorial();
				overAll.setVisible(false);
				ifNotTutorial = false;
				
				
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

		BackgroundPanel endScreen = new BackgroundPanel(endImage, width+50, height);
		
		overAll.setSize(mainSize);
		overAll.setVisible(true);
		mainWindow.add(overAll);
		mainWindow.setSize(mainSize);
		mainWindow.setVisible(true);
		
		
		overAll.setVisible(false);
		loadMenu();
		mainWindow.revalidate();
		mainWindow.remove(overAll);
		Game.startTimers();
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
		botL.setSize(quadSize);
		botR.setSize(quadSize);
		topL.setSize(quadSize);
		topR.setSize(quadSize);
		layeringPanel = new JPanel(); //Main Layering Panel
		layeringPanel.setSize(mainSize);
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
		

		

		backgroundPanel = new BackgroundPanel(backgroundOverview1Image, width, height);

		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////OLD CODE
		//timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		scoreLabel = new JLabel("ESTUARY POINTS: 200", JLabel.CENTER);
		
		
		
		
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
				if (Game.mainEnviro.money < 700) {
					System.out.println("Not enough money.");
					return;
				}
				Game.mainEnviro.money -= 700;
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
		timeFrame.setBackground(Color.WHITE);
		//timeLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.BLACK);
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
		
		
		
		

		timeFrame.add(scoreLabel);
		topR.add(timeFrame);
		
		
		Image scaledImage = topLImage.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
		topLImageLabel = new JLabel(new ImageIcon(scaledImage));
		topL.add(topLImageLabel);
		topL.setOpaque(false);
		topL.setVisible(false);
		mainPanel.add(topL);
		
		
		
		

		highlightQ1 = new QuadPanel(eQuad.N);
		highlightQ1.setSize(quadSize);
		highlightQ1.setBackground(highlightedColor);
		highlightQ1.addMouseListener(switchQuadOnClick);
		mainPanel.add(highlightQ1);

		topR.setOpaque(false);
		mainPanel.add(topR);
		
		//Adding highlighted Pane to Q4
				highlightQ4 = new QuadPanel(eQuad.W);
				highlightQ4.setSize(quadSize);
				highlightQ4.setBackground(highlightedColor);
				highlightQ4.addMouseListener(switchQuadOnClick);
				mainPanel.add(highlightQ4);
		
		
		QuadPanel center = new QuadPanel(eQuad.MAIN);
		center.setSize(quadSize);
		center.setOpaque(false);
		mainPanel.add(center);
		//Adding highlighted Pane to Q2
		highlightQ2 = new QuadPanel(eQuad.E);
		highlightQ2.setSize(quadSize);
		highlightQ2.setBackground(highlightedColor);
		highlightQ2.addMouseListener(switchQuadOnClick);
		mainPanel.add(highlightQ2);
		

		Image scaledImage2 = botLImage.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
		botLImageLabel = new JLabel(new ImageIcon(scaledImage2));
		botL.add(botLImageLabel);
		botL.setOpaque(false);
		botL.setVisible(false);
		mainPanel.add(botL);

		
		
		
		//Adding highlighted Pane to Q3
		highlightQ3 = new QuadPanel(eQuad.S);
		highlightQ3.setSize(quadSize);
		highlightQ3.setBackground(highlightedColor);
		highlightQ3.addMouseListener(switchQuadOnClick);
		mainPanel.add(highlightQ3);
		
		
		Image scaledImage3 = botRImage.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
		botRImageLabel = new JLabel(new ImageIcon(scaledImage3));
		botR.add(botRImageLabel);
		botR.setOpaque(false);
		mainPanel.add(botR);
	

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
	public void highlightOff(eQuad quad){
		switch(quad){
		case N:
			highlightTimer1.stop();
			highlightQ1.highlightOff();
			break;
		case E:
			highlightTimer2.stop();
			highlightQ2.highlightOff();
			break;
		case S:
			highlightTimer3.stop();
			highlightQ3.highlightOff();
			break;
		case W:
			highlightTimer4.stop();
			highlightQ4.highlightOff();
			break;
		default:
			break;
		}
	}
	public void pausehighlight(){
			highlightTimer1.stop();
			highlightTimer2.stop();
			highlightTimer3.stop();
			highlightTimer4.stop();
			highlightQ1.setOpaque(false);
			highlightQ2.setOpaque(false);
			highlightQ3.setOpaque(false);
			highlightQ4.setOpaque(false);

	}
	public void resumeHighlight(){
		if(highlightQ1.checkHighlight())
			highlightTimer1.start();
		else if(highlightQ2.checkHighlight())
			highlightTimer2.start();
		else if(highlightQ3.checkHighlight())
			highlightTimer3.start();
		else if(highlightQ4.checkHighlight())
			highlightTimer4.start();
	}
	
	public void highlightOn(eQuad quad){
		highlightMainTimer.start();
		switch(quad){
		case N:
			highlightQ1.highlightOn();
			break;
		case E:
			highlightQ2.highlightOn();
			break;
		case S:
			highlightQ3.highlightOn();
			break;
		case W:
			highlightQ4.highlightOn();
			break;
		default:
			break;
		}
		if(currentQuad == eQuad.MAIN)
			resumeHighlight();
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
		pausehighlight();

		if(quad!=eQuad.MAIN){
			highlightOff(quad);
			Image scaledImage = botLImage.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
			botLImageLabel.setIcon(new ImageIcon(scaledImage));
		}

		switch(quad){
			case MAIN:
				
				highlightMainTimer.stop();
				resumeHighlight();
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
		
		placeCompAtLayer(charPlace, -1);

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
//	public JLabel getTimeLabel() {
//		// TODO Auto-generated method stub
//		return this.timeLabel;
//	}
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
	public void placeCompAtLayer(JComponent toAdd, int layer){
		mainWindow.getLayeredPane().add(toAdd, layer);
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
	public void removeSpecies(int removeI, int removeJ){
		SpeciesComponent toRemove = null;
		
		System.out.println("Removing Species at " + removeI +" , "+ removeJ);
		for(JComponent comp: placedChars){
			if(comp instanceof SpeciesComponent){
				if((removeI == ((SpeciesComponent)comp).getI())&&(((SpeciesComponent)comp).getJ()==removeJ)){
					toRemove = (SpeciesComponent) comp;
					break;
				}
			}
		}
		if(toRemove != null){
			removeComp(toRemove);
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
		endImage = ImageIO.read(new File("imgs/endScreen.png"));
		
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
		howToImageIcon = new ImageIcon("imgs/HowtoPlay.png");
		tutorialImageIcon = new ImageIcon("imgs/TutorialButton.png");
		
		topLImageIcon= new ImageIcon("imgs/TLCorner.png");
		
		
		topRImageIcon= new ImageIcon("imgs/TRCorner.png");
		botLImageIcon= new ImageIcon("imgs/BLCorner.png");
		botLImage= ImageIO.read(new File("imgs/BLCorner.png"));
		botRImage= ImageIO.read(new File("imgs/BRCorner.png"));
		topLImage = ImageIO.read(new File("imgs/TLCorner.png"));

		botLImageHighlight = ImageIO.read(new File("imgs/BLCornerhighlighted.png"));
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
		howToImage1 = new ImageIcon("imgs/howTo.png");
		howToImage2 = new ImageIcon("imgs/howTo2.png");
		howToImage3 = new ImageIcon("imgs/howTo3.png");
		howToImage4 = new ImageIcon("imgs/howTo4.png");

		trash = new ImageIcon("imgs/recycle_open.png");
		}catch(IOException e){
			//System.out.println("Error: Some images weren't found");
			e.printStackTrace();
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
			case STEWARD:
				return stewardImage;
			case RESEARCHER:
				return researcherImage;
			case VOLUNTEER:
				return volunteerImage;
			case WETSTEWARD:
				return stewardImage;
			case WETRESEARCHER:
				return researcherImage;
			case WETVOLUNTEER:
				return volunteerImage;
			default:
				return null;
			
			}
	}
	public static void changeDNERR(int level){
		dnerrImage = (level == 2)?DNERRLvl2Image:DNERRLvl2Image;
	}
	public void flashMainMap(){
		if(mainMaphighlighted){
			Image scaledImage = botLImage.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
			botLImageLabel.setIcon(new ImageIcon(scaledImage));
		}
		else{
			Image scaledImage = botLImageHighlight.getScaledInstance(width/3,height/3,Image.SCALE_SMOOTH);
			botLImageLabel.setIcon(new ImageIcon(scaledImage));
		}
		mainMaphighlighted = !mainMaphighlighted;
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
			Dimension size = new Dimension(578,646);
			howToFrame = new JFrame();
			howTo1 = new JPanel();
			howTo1.add(new JLabel(howToImage1));
			howTo1.addMouseListener(nextPageListener);
			howTo1.setSize(size);
			howTo1.setOpaque(false);

			
			howTo2 = new JPanel();
			howTo2.add(new JLabel(howToImage2));
			howTo2.addMouseListener(nextPageListener);
			howTo2.setSize(size);
			howTo2.setOpaque(false);

			howTo3 = new JPanel();
			howTo3.add(new JLabel(howToImage3));
			howTo3.addMouseListener(nextPageListener);
			howTo3.setSize(size);
			howTo3.setOpaque(false);

			howTo4 = new JPanel();
			howTo4.add(new JLabel(howToImage4));
			howTo4.addMouseListener(nextPageListener);
			howTo4.setSize(size);
			howTo4.setOpaque(false);

			howToFrame.setUndecorated(true);
			howToFrame.setBackground(alphaLayer);
			howToFrame.setSize(size);
			howToFrame.setVisible(true);
			howToFrame.addMouseListener(nextPageListener);
			howToFrame.setLocation(howToPanel.getLocation());
			changePage();

			
	}
	
	public void changePage(){
		switch(howToPage){
		case 0:
			howToPage = 1;
			howToFrame.add(howTo1);
			break;
		case 1:
			howToPage = 2;
			howToFrame.remove(howTo1);
			howToFrame.add(howTo2);
			break;
		case 2:
			howToPage = 3;
			howToFrame.remove(howTo2);
			howToFrame.add(howTo3);
			break;
		case 3:
			howToPage = 4;
			howToFrame.remove(howTo3);
			howToFrame.add(howTo4);
			break;
		case 4:
			howToPage = 0;
			howToFrame.dispose();
			overAll.setVisible(true);
		}
		howToFrame.revalidate();

	}


	public static ImageIcon getDNERRImage(int level) {
		if(level == 1)
			return DNERRLvl1Image;
		changeDNERR(level);
		return (level == 2)?DNERRLvl2Image:DNERRLvl3Image;
	}
	
	public static ImageIcon getFishImage(boolean fishFlag)
	{
		return (fishFlag)?fishermanImage:fishermanOverFlowImage;
	}
}
	
	