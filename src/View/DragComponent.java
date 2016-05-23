package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.Game;
import Model.eChar;
import Model.eQuad;

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
	
	/**
	 * Getters and setters.
	 * @return
	 */
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
	private eChar dryVersion;
	public eChar getDryVersion() {
		return dryVersion;
	}

	public void setDryVersion(eChar dryVersion) {
		this.dryVersion = dryVersion;
	}

	public eChar getWetVersion() {
		return wetVersion;
	}

	public void setWetVersion(eChar wetVersion) {
		this.wetVersion = wetVersion;
	}

	private eChar wetVersion;
	
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
	
	public DragComponent(ImageIcon charIcon, eQuad thisQuad, eChar character, int x, int y, int i, int j, boolean draggable) {
		oldi = i;
		oldj = j;
		setLayout(new BorderLayout());
		JLabel label = new JLabel(charIcon);
		label.setBounds(0, 0, charIcon.getIconWidth(), charIcon.getIconHeight());	
		setBounds(0,0,charIcon.getIconWidth(), charIcon.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);	
		
		canDrag = draggable;
		
		setLocation(x,y);
		
		this.whatQuad = thisQuad;
		this.character = character;
		
		switch(character) {
		case STEWARD:
			dryVersion = eChar.STEWARD;
			wetVersion = eChar.WETSTEWARD;
			break;
		case WETSTEWARD:
			dryVersion = eChar.STEWARD;
			wetVersion = eChar.WETSTEWARD;
			break;
		case RESEARCHER:
			dryVersion = eChar.RESEARCHER;
			wetVersion = eChar.WETRESEARCHER;
			break;
		case WETRESEARCHER:
			dryVersion = eChar.RESEARCHER;
			wetVersion = eChar.WETRESEARCHER;
			break;
		case VOLUNTEER:
			dryVersion = eChar.VOLUNTEER;
			wetVersion = eChar.WETVOLUNTEER;
			break;
		case WETVOLUNTEER:
			dryVersion = eChar.VOLUNTEER;
			wetVersion = eChar.WETVOLUNTEER;
			break;
		default:
			break;
		}
		

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
					try {
						Game.placeInArray(getX(), getY(),(DragComponent)e.getSource(), thisQuad);
					}
					catch (NullPointerException e1) {
						
					}
					
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
