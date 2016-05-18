package Estuary;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all the objects that require dragging in the game.
 */
public class DragComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int XOnScreen;
	private int YOnScreen;
	private int XCoord;
	private int YCoord;
	private MouseListener pressListener;
	private int oldi , oldj;
	private boolean canDrag;
	
	public int getXOnScreen() {
		return XOnScreen;
	}

	public void setXOnScreen(int xOnScreen) {
		XOnScreen = xOnScreen;
	}

	public int getYOnScreen() {
		return YOnScreen;
	}

	public void setYOnScreen(int yOnScreen) {
		YOnScreen = yOnScreen;
	}
	
	public boolean getDrag()
	{
		return canDrag;
	}
	
	public void setDrag(boolean drag)
	{
		canDrag = drag;
	}

	private eChar character;
	
	public eChar getCharacter()
	{
		return character;
	}
	
	public void setCharacter(eChar chara)
	{
		character = chara;
	}
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	
	private eQuad whatQuad;
	
	/**
	 * Constructor for the DragComponent.
	 * @param charIcon
	 * @param thisQuad
	 * @param character
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 */
	
	public DragComponent(ImageIcon charIcon, eQuad thisQuad, eChar character, int x, int y, int i, int j) {
		oldi = i;
		oldj = j;
		setLayout(new BorderLayout());
		JLabel label = new JLabel(charIcon);
		label.setBounds(0, 0, charIcon.getIconWidth(), charIcon.getIconHeight());	
		setBounds(0,0,charIcon.getIconWidth(), charIcon.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);	
		
		canDrag = true;
		
		setLocation(x,y);
		
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
				if(canDrag) {
					placeInArray(getX(), getY());
					getRootPane().repaint();
					getRootPane().revalidate();
				}
			}
		};
		addMouseListener(pressListener);
		
		/**
		 * Adding the mouse event for dragging.
		 */
		
	    addMouseMotionListener(new MouseMotionListener() {
	    	
	    	@Override
	    	public void mouseDragged(MouseEvent e) {
	    		if (canDrag) {
	    			setLocation(XCoord + (e.getXOnScreen() - XOnScreen), YCoord + (e.getYOnScreen() - YOnScreen));
	    		}
	    	}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	    });
  
	}
	
	/**
	 * Method to place an image after being dragged, and check for collisions.
	 * @param XCoord
	 * @param YCoord
	 * @return true/false
	 */
	
	public boolean placeInArray(int XCoord, int YCoord)
	{

		int x = XCoord/(width/38);
		int y = YCoord/(height/24);
		
		switch(whatQuad)
		{
		case N:
				if ((Game.board[y][x] != eChar.BLANK) && (Game.board[y][x] != eChar.NOTHING)) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j] == eChar.BLANK) {
								Game.board[oldi][oldj] = eChar.BLANK;
								Game.board[y+i][x+j] = this.character;
								oldi = y+i;
								oldj = x+j;
								return Game.collision(x+j,y+i,this); // can be changed to return true
							}
						}
					}
					return false;
				}
				else if((Game.board[y][x] == eChar.BLANK) || (Game.board[y][x] == eChar.NOTHING)) {
					Game.board[oldi][oldj] = eChar.BLANK;
					Game.board[y][x] = this.character;
					oldi = y;
					oldj = x;
					Game.collision(x, y,this); 
				}

				
				return false;
		case E:
			
				if ((Game.board[y][x+38] != eChar.BLANK) && (Game.board[y][x+38] != eChar.NOTHING)){
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j+38] == eChar.BLANK) {
								Game.board[oldi][oldj] = eChar.BLANK;
								Game.board[y+i][x+j+38] = this.character;
								oldi = y+i;
								oldj = x+j+38;
								return Game.collision(x+j+38,y+i,this); // can be changed to return true
							}
						}
					}
					return false;
				}
				else {
					Game.board[oldi][oldj] = eChar.BLANK;
					Game.board[y][x+38] = this.character;
					oldi = y;
					oldj = x + 38;
					Game.collision(x + 38, y,this); 
				}
				return false;
		case W:
				if ((Game.board[y+24][x] != eChar.BLANK) && (Game.board[y+24][x] != eChar.NOTHING)){
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i+24][x+j] == eChar.BLANK) {
								Game.board[oldi][oldj] = eChar.BLANK;
								Game.board[y+i+24][x+j] = this.character;
								oldi = y+i+24;
								oldj = x+j;
								return Game.collision(x+j,y+i+24,this); // can be changed to return true
							}
						}
					}
					return false;
				}
				else {
					Game.board[oldi][oldj] = eChar.BLANK;
					Game.board[y+24][x] = this.character;
					oldi = y + 24;
					oldj = x;
					Game.collision(x, y + 24,this); 
				}
				return false;
		case S:
			
				if ((Game.board[y+24][x+38] != eChar.BLANK) && (Game.board[y+24][x+38] != eChar.NOTHING)){
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i+24][x+j+38] == eChar.BLANK) {
								Game.board[y+i+24][x+j+38] = this.character;
								oldi = y+i+24;
								oldj = x+j+38;
								return Game.collision(x+j+38,y+i+24,this); // can be changed to return true
							}
						}
					}
					return false;
				}
				else {
					Game.board[oldi][oldj] = eChar.BLANK;
					Game.board[y+24][x+38] = this.character;
					oldi = y + 24;
					oldj = x + 38;
					Game.collision(x + 38, y + 24, this); 
				}
				return false;
		default:
			break;
		}
		return false;
	}
	
	/**
	 * Method to check collisions within a certain distance from another object.
	 * @param x
	 * @param y
	 * @return true - if there's a collision
	 */
	

	public boolean isCanDrag() {
		return canDrag;
	}

	public void setCanDrag(boolean canDrag) {
		this.canDrag = canDrag;
	}

	/**
	 * Getters and setters.
	 * @return
	 */
	public int getOldi() {
		return oldi;
	}

	public void setOldi(int oldi) {
		this.oldi = oldi;
	}

	public int getOldj() {
		return oldj;
	}

	public void setOldj(int oldj) {
		this.oldj = oldj;
	}

	public MouseListener getMouseListener() {
		// TODO Auto-generated method stub
		return pressListener;
	}
}
