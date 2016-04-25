package Estuary;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
