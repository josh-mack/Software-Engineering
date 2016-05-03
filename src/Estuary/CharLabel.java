package Estuary;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Class that associates an eChar with it's corresponding image file.
 */

public class CharLabel extends JLabel{
	private eChar type;
	CharLabel(ImageIcon img, eChar type){
		super(img);
		this.type = type;
	}
	
	
	public eChar getType(){
		return this.type;
	}
}
