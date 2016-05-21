package View;

import javax.swing.JPanel;

import Model.eQuad;

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
	boolean highlighted;   //is this currently being highlighted?
	
	/**
	 * Constructor for the QuadPanel.
	 * @param loc
	 */
	QuadPanel(eQuad loc){
		this.loc = loc;
	}
	public void highlight(){
		if(this.isOpaque())
			this.setOpaque(false);
		else
			this.setOpaque(true);
		
		this.getRootPane().repaint();

	}
	public void highlightOn(){
		this.highlighted = true;
	}
	public void highlightOff(){
		this.setOpaque(false);
		this.highlighted = false;
	}
	public boolean checkHighlight(){
		return this.highlighted;
	}
	/**
	 * Getter.
	 * @return loc
	 */
	eQuad getLoc(){
		return loc;
	}
}
