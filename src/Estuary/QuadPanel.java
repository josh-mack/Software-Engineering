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
	
	/**
	 * Constructor for the QuadPanel.
	 * @param loc
	 */
	QuadPanel(eQuad loc){
		this.loc = loc;
	}
	
	/**
	 * Getter.
	 * @return loc
	 */
	eQuad getLoc(){
		return loc;
	}
}
