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
	private static final long serialVersionUID = 1L;
	private eChar type;
	
	/**
	 * Constructor. Each label has an eChar and a corresponding
	 * image to go with that label.
	 * @param img
	 * @param type
	 */
	CharLabel(ImageIcon img, eChar type){
		super(img);
		this.type = type;
	}
	
	/**
	 * Getter.
	 * @return
	 */
	public eChar getType(){
		return this.type;
	}
}
