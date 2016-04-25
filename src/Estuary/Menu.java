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



public class Menu{
	public JLayeredPane mainWindow;
	public JPanel background;
	public JLayeredPane imLayer;
	public JFrame main;
	public JPanel panel;
	JFrame charSel;
	BackgroundTest backgroundPanel;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	Dimension topBarSize = new Dimension(width/4, 500);
	Dimension quadSize = new Dimension(width/2 -20, (height/2)-(height/20)-20);
	Dimension mainSize = new Dimension(width, height);
	Color hilightedColor = new Color(0,0,0, 100);
	Color alphaLayer = new Color(0, 0, 0, 0);
	
	
	int sel;
	JPanel hilightQ1;
	JPanel hilightQ2;
	JPanel hilightQ3;
	JPanel hilightQ4;
	boolean charMenuOpen = false;
	private JLabel timeLabel;
	private JLabel scoreLabel;
	boolean inQuad = false;
	
	public Menu(){
		sel = 0;
		main = new JFrame();

		mainWindow = new JLayeredPane();
		mainWindow.setLayout(new GridLayout());

		imLayer = new JLayeredPane();
		
		background = new JPanel();
		background.setLayout(new GridBagLayout());
		main.setUndecorated(true);
		main.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		loadMenu();
	}	
	private void loadMenu(){
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
		hilightQ1 = new QuadPanel(eQuad.NW);
		hilightQ1.setPreferredSize(quadSize);
		hilightQ1.setBackground(hilightedColor);
		hilightQ1.setOpaque(false);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		hilightQ1.addMouseListener(switchQuadOnClick);
		background.add(hilightQ1, c);
		
		//Adding Hilighted Pane to Q2
		hilightQ2 = new QuadPanel(eQuad.NE);
		hilightQ2.setPreferredSize(quadSize);
		hilightQ2.setBackground(hilightedColor);
		hilightQ2.setOpaque(false);
		hilightQ2.addMouseListener(switchQuadOnClick);
		
		c.fill = GridBagConstraints.REMAINDER;
		c.gridx = 1;
		c.gridy = 1;
		
		
		background.add(hilightQ2, c);
		
		//Adding Hilighted Pane to Q3
		hilightQ3 = new QuadPanel(eQuad.SE);
		hilightQ3.setPreferredSize(quadSize);
		hilightQ3.setBackground(hilightedColor);
		hilightQ3.setOpaque(false);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 2;
		
		hilightQ3.addMouseListener(switchQuadOnClick);
		background.add(hilightQ3, c);

		
	
		hilightQ4 = new QuadPanel(eQuad.SW);
		hilightQ4.setPreferredSize(quadSize);
		hilightQ4.setBackground(hilightedColor);
		hilightQ4.setOpaque(false);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		
		hilightQ4.addMouseListener(switchQuadOnClick);
		background.add(hilightQ4, c);
		
		
		
		
		
		backgroundPanel = new BackgroundTest("imgs/fullmap.png", width, height);

		JPanel topBarLeft = new JPanel();
		topBarLeft.setLayout(new GridBagLayout());
		

		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////OLD CODE
		timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		scoreLabel = new JLabel("MONEY: $200", JLabel.CENTER);
		
		
		JLabel charLabel = new JLabel("Char Selection", JLabel.CENTER);
		JPanel charFrame = new JPanel();
		charFrame.setBackground(Color.BLACK);
		charFrame.setSize(width/5, height/10);
		
		charSel = new JFrame();
		charSel.setUndecorated(true);
		charSel.setSize(width/10, height/4);
		JPanel charSelection = new JPanel();
		charSelection.setLayout(new GridLayout(3, 2));
		
		charSelection.add(new JLabel("Steward"));
		BufferedImage stewardIcon = null;
		try {
			stewardIcon = ImageIO.read(new File("imgs/stewardIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel stewardImage = new CharLabel(new ImageIcon(stewardIcon), eChar.STEWARD);
		stewardImage.addMouseListener(addCompOnClick);
		charSelection.add(stewardImage);
		
		
		
		charSelection.add(new JLabel("Researcher"));
		BufferedImage researcherIcon = null;
		try {
			researcherIcon = ImageIO.read(new File("imgs/researcherIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel researcherImage = new CharLabel(new ImageIcon(researcherIcon), eChar.RESEARCHER);
		researcherImage.addMouseListener(addCompOnClick);
		charSelection.add(researcherImage);
		
		
		
		charSelection.add(new JLabel("Volunteer"));
		BufferedImage volunteerIcon = null;
		try {
			volunteerIcon = ImageIO.read(new File("imgs/volunteerIcon.jpg"));
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
		timeFrame.setBackground(Color.RED);
		timeFrame.setSize(width/5, height/10);
		
		
		
		JPanel exitBar = new JPanel();
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JButton mainMap = new JButton("Main Map");
		mainMap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				loadQuad(eQuad.MAIN);
			}
		});
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
		c.anchor = GridBagConstraints.CENTER;
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
	private void loadQuad(eQuad quad) {
		if(inQuad && (quad != eQuad.MAIN)){
			//Don't listen in quadrants
			return;
		}
		switch(quad){
			case MAIN:
				inQuad = false;
				backgroundPanel.paintComponent(null, "imgs/fullmap.png");
				main.revalidate();
			break;
			case NW:
				inQuad = true;
				backgroundPanel.paintComponent(null, "imgs/NW.png");
				main.revalidate();
			break;
			case SW:
				backgroundPanel.paintComponent(null, "imgs/SW.png");
				main.revalidate();
			break;
			case SE:
				backgroundPanel.paintComponent(null, "imgs/SE.png");
				main.revalidate();
			break;
			case NE:
				backgroundPanel.paintComponent(null, "imgs/NE.png");
				main.revalidate();
			break;
		}
		main.repaint();

		
	}
	public DragComponent createChar(eChar eChar, int x, int y){
		if(!inQuad){
			return null;
		}
		
		DragComponent charPlace = null;
		switch(eChar){
		case BCRAB:
			charPlace = new DragComponent("imgs/squirt.png",eQuad.NW, eChar, x, y);
			break;
		case STEWARD:
			charPlace = new DragComponent("imgs/pika.png",eQuad.NW, eChar, x, y);
			break;
		case RESEARCHER:
			charPlace = new DragComponent("imgs/oak.png",eQuad.NW, eChar, x, y);
			break;
		case VOLUNTEER:
			charPlace = new DragComponent("imgs/red.png",eQuad.NW, eChar, x, y);
			break;
		case BLANK:
			charPlace = new DragComponent("imgs/pokeball.png",eQuad.NW, eChar, x, y);
			break;
		case PHRAG:
			charPlace = new DragComponent("imgs/bulb.png",eQuad.NW, eChar, x, y);
			break;
		case MCRAB:
			charPlace = new DragComponent("imgs/char.png",eQuad.NW, eChar, x, y);
			break;
		default:
			break;
		}
		
		main.getLayeredPane().add(charPlace);
		return charPlace;
	}
	public JLabel getTimeLabel() {
		// TODO Auto-generated method stub
		return this.timeLabel;
	}
	public JFrame getMenu() {
		// TODO Auto-generated method stub
		return main;
	}
	public JLabel getScoreLabel() {
		// TODO Auto-generated method stub
		return this.scoreLabel;
	}
}
	
	