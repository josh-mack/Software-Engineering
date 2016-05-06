package Estuary;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
	private MouseListener pressListener;
	
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
		String filename = null;
			switch(character){
			case MCRAB:
				filename = "imgs/mittencrab_0.png";
				this.invasive = true;
				break;
			case PHRAG:
				filename = "imgs/phragmite_spawning1.png";
				this.invasive = true;
				break;
			case BAMBOO:
				filename = "imgs/bamboo.png";
				this.invasive = true;
				break;
			case ZEBRA:
				filename = "imgs/zebramussel.png";
				this.invasive = true;
				break;
			case BLAZINGSTAR:
				filename = "imgs/blazingstarplant.png";
				this.invasive = false;
				break;
			case HCRAB:
				filename = "imgs/horseshoe_crab_left_1.png";
				this.invasive = false;
				break;
			default:
				filename = null;
			}
			
		setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(filename);
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
