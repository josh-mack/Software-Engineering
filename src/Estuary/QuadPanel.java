package Estuary;

import javax.swing.JPanel;

public class QuadPanel extends JPanel {
	eQuad loc;
	QuadPanel(eQuad loc){
		this.loc = loc;
	}
	eQuad getLoc(){
		return loc;
	}
}
