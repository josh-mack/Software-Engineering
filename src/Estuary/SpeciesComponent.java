package Estuary;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import Estuary.Game;
import Estuary.eChar;
import Estuary.eQuad;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles the creation of a species on the board.
 */
public class SpeciesComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MouseListener pressListener;
	
	@SuppressWarnings("unused")
	private eChar character;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	
	private eQuad whatQuad;
	private boolean invasive;
	
	/**
	 * Constructor for SpeciesComponent.
	 * Attaches the correct image to the corresponding invasive species,
	 * and adds it to a JLabel.
	 * @param thisQuad
	 * @param character
	 * @param x
	 * @param y
	 */
	public SpeciesComponent(eQuad thisQuad, eChar character, int x, int y) {	
		this.invasive = character.checkInvasive();
		setLayout(new BorderLayout());
		ImageIcon image = Game.getImage(character);
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
		
	}

	public boolean isInvasive() {
		return invasive;
	}

	public void setInvasive(boolean invasive) {
		this.invasive = invasive;
	}

	/**
	 * Getters.
	 * @return
	 */
	public MouseListener getMouseListener() {
		// TODO Auto-generated method stub
		return pressListener;
	}
	
	public eQuad getWhatQuad(){
		return whatQuad;
	}
}
