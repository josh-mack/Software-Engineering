package View;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	/**
	 * Main background panels.
	 * Used for end screen, loadMenu, and start screen.
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	int height;
	int width;
	
	public void paint(Graphics g){
		g.drawImage(image,0, 0, width, height, this);
	}
	
	public BackgroundPanel(BufferedImage img, int width, int height){
		this.image = img;
		this.height = height;
		this.width = width;
	}

	public void paintComponent(Graphics g, BufferedImage img){
		this.image = img;
	}
}
