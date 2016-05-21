package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.eChar;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Class that associates an eChar with it's corresponding image file.
 */

public class CharLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private eChar type;
	CharLabel(ImageIcon img, eChar type){
		super(img);
		this.type = type;
	}
	
	
	public eChar getType(){
		return this.type;
	}
}
