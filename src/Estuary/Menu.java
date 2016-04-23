package Estuary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		JPanel panel = new JPanel(); //Main Layering Panel
		GridBagConstraints c = new GridBagConstraints();
		
		MouseAdapter hilightOnMouse = new MouseAdapter(){
			
		};
		JLayeredPane NWPane = new JLayeredPane();
		NWPane.setPreferredSize(quadSize);
		JPanel blank = new JPanel();
		blank.setBackground(new Color(255,0,0,100));
		blank.setSize(quadSize);
		NWPane.add(blank);
		
		
		
		//Adding Hilighted Pane to Q1
		hilightQ1 = new JPanel();
		hilightQ1.setSize(quadSize);
		hilightQ1.setBackground(alphaLayer);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		NWPane.add(hilightQ1);
		NWPane.setLayer(hilightQ1, 2);
		background.add(NWPane, c);
		
		JLayeredPane NEPane = new JLayeredPane();
		NEPane.setPreferredSize(quadSize);
		JPanel blank2 = new JPanel();
		blank2.setBackground(new Color(0,0,255,100));
		blank2.setSize(quadSize);
		NEPane.add(blank2);

		
		//Adding Hilighted Pane to Q2
		hilightQ2 = new JPanel();
		hilightQ2.setSize(quadSize);
		hilightQ2.setBackground(alphaLayer);
		
		hilightQ2.addMouseListener(hilightOnMouse);
				
		c.fill = GridBagConstraints.REMAINDER;
		c.gridx = 1;
		c.gridy = 1;
		
		NEPane.add(hilightQ2);
		NEPane.setLayer(hilightQ2, 2);
		background.add(NEPane, c);
		
		JLayeredPane SEPane = new JLayeredPane();
		SEPane.setPreferredSize(quadSize);
		JPanel blank3 = new JPanel();
		blank3.setBackground(new Color(0,255,0,100));
		blank3.setSize(quadSize);
		SEPane.add(blank3);

		//Adding Hilighted Pane to Q3
		hilightQ3 = new JPanel();
		hilightQ3.setSize(quadSize);
		hilightQ3.setBackground(alphaLayer);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		
		SEPane.add(hilightQ3);
		SEPane.setLayer(hilightQ3, 2);
		background.add(SEPane, c);

		
		
		JLayeredPane SWPane = new JLayeredPane();
		SWPane.setPreferredSize(quadSize);
		JPanel blank4 = new JPanel();
		blank4.setBackground(new Color(255,255,100,100));
		blank4.setSize(quadSize);
		SWPane.add(blank4);
		
	
		hilightQ4 = new JPanel();
		hilightQ4.setSize(quadSize);
		hilightQ4.setBackground(alphaLayer);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 2;
		SWPane.add(hilightQ4);
		SWPane.setLayer(hilightQ4, 2);
		
		background.add(SWPane, c);
		
		
		
		
		
		JPanel backgroundPanel = new BackgroundTest(width, height);
		
//		BufferedImage backgroundImage = null;
//		try {
//			backgroundImage = ImageIO.read(new File("imgs/fullmap.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		backgroundPanel.setLayout(new GridLayout());
//		JLabel image = new JLabel(new ImageIcon(backgroundImage));
//
//		backgroundPanel.add(image);
	//	JLayeredPane imgPane = new JLayeredPane();
		
		
		
		
		JPanel topBarLeft = new JPanel();
		topBarLeft.setLayout(new GridBagLayout());
		

		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////OLD CODE
		JLabel timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		
		JLabel scoreLabel = new JLabel("SCORE: $0", JLabel.CENTER);
		
		JLabel charLabel = new JLabel("Char Selection", JLabel.CENTER);
		
		JPanel charFrame = new JPanel();
		charFrame.setBackground(Color.BLACK);
		charFrame.setSize(width/5, height/10);
		
		JFrame charSel = new JFrame();
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
		JLabel stewardImage = new JLabel(new ImageIcon(stewardIcon));
		charSelection.add(stewardImage);
		
		
		
		charSelection.add(new JLabel("Researcher"));
		BufferedImage researcherIcon = null;
		try {
			researcherIcon = ImageIO.read(new File("imgs/researcherIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel researcherImage = new JLabel(new ImageIcon(researcherIcon));
		charSelection.add(researcherImage);
		
		
		
		
		charSelection.add(new JLabel("Volunteer"));
		BufferedImage volunteerIcon = null;
		try {
			volunteerIcon = ImageIO.read(new File("imgs/volunteerIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel volunteerImage = new JLabel(new ImageIcon(volunteerIcon));
		charSelection.add(volunteerImage);
		
		
		
		charSel.add(charSelection);
		charSel.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		
		
		charFrame.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent me){
				charSel.setLocation(charFrame.getLocationOnScreen());
				charSel.setVisible(true);				
			}
		});

		charSel.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent me){
				charSel.setVisible(false);
			///	charSel.getContentPane().revalidate();
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
		
		
		DragComponent charPlace = new DragComponent("imgs/red.png");
		charPlace.setLocation(100,100);
		main.add(charPlace);
		
		main.add(panel);
		main.setSize(mainSize);
		main.setVisible(true);
	}

	public static void main(String[] args) {
		Menu test = new Menu();
		//ActionListener timerAction = new ActionListener(){
		//	@Override
	//		public void actionPerformed(ActionEvent e) {
	//			test.hilight(test.sel, test);
//				test.sel = (test.sel+1)%8;
	//	}};
		//new Timer(500, timerAction).start();
	}
	
	public void hilight(int sel,Menu main){
		switch(sel){
		case 0:
			main.hilightQ1.setBackground(main.hilightedColor);
			main.hilightQ1.getRootPane().repaint();
			break;
		case 1:
			main.hilightQ1.setBackground(main.alphaLayer);
			main.hilightQ1.getRootPane().repaint();
			break;
		case 2:
			main.hilightQ2.setBackground(main.hilightedColor);
			main.hilightQ2.getRootPane().repaint();
			break;
		case 3:
			main.hilightQ2.setBackground(main.alphaLayer);
			main.hilightQ2.getRootPane().repaint();
			break;
		case 4:
			main.hilightQ3.setBackground(main.hilightedColor);
			main.hilightQ3.getRootPane().repaint();
			break;
		case 5:
			main.hilightQ3.setBackground(main.alphaLayer);
			main.hilightQ3.getRootPane().repaint();
			break;
		case 6:
			main.hilightQ4.setBackground(main.hilightedColor);
			main.hilightQ4.getRootPane().repaint();
			break;
		case 7:
			main.hilightQ4.setBackground(main.alphaLayer);
			main.hilightQ4.getRootPane().repaint();
			break;
			
		}
	}
}
	
	