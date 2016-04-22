package Estuary;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu{
	private JFrame mainMenu;
	private JLabel timeLabel;
	private JLabel scoreLabel;
	private JLabel charLabel;
	private JPanel backgroundPanel;
	static Point mouseDownCompCoords;

	public Menu(){
		loadMenu();
	}	
	private void loadMenu(){
		mainMenu = new JFrame("Estuary Defenders");
		mainMenu.setLayout(new BorderLayout());		
		mainMenu.setUndecorated(true);
		mainMenu.setLocationRelativeTo( null );
		makeMoveable();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)screenSize.getHeight();
		int width = (int)screenSize.getWidth();
		
//		int height = 1800;
//		int width = 2880;
		
		
		//mainMenu.setBounds(0, 0, width, height);
		mainMenu.setSize(width, height);
		
		timeLabel = new JLabel("TIME: 0:00", JLabel.CENTER);
		
		scoreLabel = new JLabel("SCORE: $0", JLabel.CENTER);
		
		charLabel = new JLabel("Char Selection", JLabel.CENTER);
		
		JPanel charFrame = new JPanel();
		charFrame.setBackground(Color.BLACK);
		charFrame.setSize(width/5, height/10);
		
		JFrame charSel = new JFrame();
		charSel.setUndecorated(true);
		charSel.setSize(width/5, height/4);
		JPanel charSelection = new JPanel();
		charSelection.setLayout(new GridLayout(3, 2));
		
		
		charSelection.add(new JLabel("Steward"));
		BufferedImage stewardIcon = null;
		try {
			stewardIcon = ImageIO.read(new File("stewardIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel stewardImage = new JLabel(new ImageIcon(stewardIcon));
		charSelection.add(stewardImage);
		
		
		
		charSelection.add(new JLabel("Researcher"));
		BufferedImage researcherIcon = null;
		try {
			researcherIcon = ImageIO.read(new File("researcherIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel researcherImage = new JLabel(new ImageIcon(researcherIcon));
		charSelection.add(researcherImage);
		
		
		
		
		charSelection.add(new JLabel("Volunteer"));
		BufferedImage volunteerIcon = null;
		try {
			volunteerIcon = ImageIO.read(new File("volunteerIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel volunteerImage = new JLabel(new ImageIcon(volunteerIcon));
		charSelection.add(volunteerImage);
		
		
		
		charSel.add(charSelection);
		
		
		
		charFrame.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent me){
				charSel.setLocationRelativeTo(charLabel);
				charSel.setVisible(true);				
			}
		});

		charSel.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent me){
				charSel.setVisible(false);
				mainMenu.revalidate();
			}
		});

		
		charLabel.setSize(width/5, height/10);
		mainMenu.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		
		JPanel timeFrame = new JPanel();
		timeFrame.setBackground(Color.RED);
		timeFrame.setSize(width/5, height/10);
		
		
		//Dimension timeDimension = new Dimension(width, height);
		//timeFrame.setPreferredSize(timeDimension);
		
		
		JPanel exitBar = new JPanel();
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBar.add(exit);
		
		backgroundPanel = new JPanel();
		BufferedImage backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel image = new JLabel(new ImageIcon(backgroundImage));
		backgroundPanel.setSize(width, (height*9)/10);
		//backgroundPanel.set;
		backgroundPanel.add(image);
		charFrame.add(charLabel);
		timeFrame.add(timeLabel);
		timeFrame.add(scoreLabel);
		
		mainMenu.add(exitBar, BorderLayout.CENTER);
		mainMenu.add(charFrame, BorderLayout.PAGE_END);
		mainMenu.add(timeFrame, BorderLayout.PAGE_END);

		
		mainMenu.add(new DragComponent("character-color.png"), BorderLayout.PAGE_END);
		
		
		mainMenu.add(backgroundPanel, BorderLayout.PAGE_END);	


		
		//mainMenu.pack();
		
		mainMenu.setLocationRelativeTo(null);
		
	}
	private void makeMoveable() {
        mainMenu.addMouseListener(new MouseListener(){
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });

        mainMenu.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                mainMenu.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }
	void showMenu() {
		mainMenu.setVisible(true);

	}
	public JLabel getTimeLabel() {
		return(timeLabel);
	}
	
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}
	
	public JLabel getScoreLabel() {
		return(scoreLabel);
	}
	
	public JFrame getMainMenu() {
		return(mainMenu);
	}
	
	
	
}