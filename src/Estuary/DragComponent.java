package Estuary;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class DragComponent extends JComponent {
	private volatile int XOnScreen;
	private volatile int YOnScreen;
	private volatile int XCoord;
	private volatile int YCoord;
	private MouseListener pressListener;
	
	private eChar character;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	
	private eQuad whatQuad;
	private eChar[][] board;
	
	public DragComponent(String imageName, eQuad thisQuad, eChar character, int x, int y) {
		setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(imageName);
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		
		
		setLocation(x,y);
		//setOpaque(false);
		
		this.whatQuad = thisQuad;
		this.character = character;
		
		pressListener = new MouseListener() {
	
			@Override
			public void mouseClicked(MouseEvent e) { }
	
			@Override
			public void mousePressed(MouseEvent e) {
		        XOnScreen = e.getXOnScreen();
		        YOnScreen = e.getYOnScreen();
		        XCoord = getX();
		        YCoord = getY();
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
			public void mouseReleased(MouseEvent e) 
			{
				placeInArray(getX(), getY());
				character.setXLoc(getX());
				character.setYLoc(getY());
				repaint();
				revalidate();
			}
	
	
		};
		addMouseListener(pressListener);
		
	    addMouseMotionListener(new MouseMotionListener() {
	    	
	    	@Override
	    	public void mouseDragged(MouseEvent e) {
	    		setLocation(XCoord + (e.getXOnScreen() - XOnScreen), YCoord + (e.getYOnScreen() - YOnScreen));
	    	}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	    });
  
	}
	
	public boolean placeInArray(int XCoord, int YCoord)
	{
		int x = (XCoord/(width/76))%38;
		int y = (YCoord/(height/48))%24;
		//eChar[][] board = envio.getBoard();
		
		switch(whatQuad)
		{
		case NW:
			if (Game.board[x][y] != eChar.BLANK) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (Game.board[x+i][y+j] == eChar.BLANK) {
							Game.board[x+i][y+j] = this.character;
							return Collision(x+i,y+j);
						}
					}
				}
				return false;
			}
			Game.board[x][y] = this.character;
			return (Collision(x,y));
		case NE:
			if (Game.board[x+38][y] != eChar.BLANK) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (Game.board[x+i+38][y+j] == eChar.BLANK) {
							Game.board[x+i+38][y+j] = this.character;
							return Collision(x+i+38,y+j);
						}
					}
				}
				return false;
			}
			Game.board[x+38][y] = this.character;
			return (Collision(x+38,y));
		case SW:
			if (Game.board[x][y+24] != eChar.BLANK) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (Game.board[x+i][y+j+24] == eChar.BLANK) {
							Game.board[x+i][y+j+24] = this.character;
							return Collision(x+i,y+j+24);
						}
					}
				}
				return false;
			}
			Game.board[x][y+24] = this.character;
			return (Collision(x,y+24));
		case SE:
			if (Game.board[x+38][y+24] != eChar.BLANK) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (Game.board[x+i+38][y+j+24] == eChar.BLANK) {
							Game.board[x+i+38][y+j+24] = this.character;
							return Collision(x+i+38,y+j+24);
						}
					}
				}
				return false;
			}
			Game.board[x+38][y+24] = this.character;
			return (Collision(x+38,y+24));
		}
		
		return (Collision(x,y));
		
	}
	
	public boolean Collision(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				try {
					if ((Game.board[x+i][y+j] != eChar.BLANK) && ((j!=0) && (i!=0))) {
						return true;
					}
				} catch (Exception e) {
					
				}
				
			}
		}
		return false;
	}

	public MouseListener getMouseListener() {
		// TODO Auto-generated method stub
		return pressListener;
	}
}

