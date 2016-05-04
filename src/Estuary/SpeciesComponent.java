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

public class SpeciesComponent extends JComponent {
	private MouseListener pressListener;
	
	private eChar character;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	
	private eQuad whatQuad;
	
	public SpeciesComponent(eQuad thisQuad, eChar character, int x, int y) {
		String filename = null;
			switch(character){
			case MCRAB:
				filename = "imgs/squirt.png";
				break;
			case PHRAG:
				filename = "imgs/bulb.png";
				break;
			case BAMBOO:
				filename = "imgs/char.png";
				break;
			default:
				filename = "imgs/pokeball.png";
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

	public MouseListener getMouseListener() {
		// TODO Auto-generated method stub
		return pressListener;
	}
	public eQuad getWhatQuad(){
		return whatQuad;
	}
}
