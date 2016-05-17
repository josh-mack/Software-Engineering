package Estuary;

import javax.swing.JPanel;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles the location storage of the quadrants.
 */
public class QuadPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	eQuad loc;
	boolean hilighted;   //is this currently being hilighted?
	
	/**
	 * Constructor for the QuadPanel.
	 * @param loc
	 */
	QuadPanel(eQuad loc){
		this.loc = loc;
	}
	public void hilight(){
		if(this.isOpaque())
			this.setOpaque(false);
		else
			this.setOpaque(true);
		
		this.getRootPane().repaint();

	}
	public void hilightOn(){
		this.hilighted = true;
	}
	public void hilightOff(){
		this.setOpaque(false);
		this.hilighted = false;
	}
	public boolean checkHilight(){
		return this.hilighted;
	}
	/**
	 * Getter.
	 * @return loc
	 */
	eQuad getLoc(){
		return loc;
	}
}
