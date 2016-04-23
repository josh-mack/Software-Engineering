package Estuary;


import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundTest extends JPanel{
	int height;
	int width;
	
	public void paint(Graphics g){
		BufferedImage backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("imgs/fullmap.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(backgroundImage,0, 0, width, height, this);
	}
	public BackgroundTest(int width, int height){
		this.height = height;
		this.width = width;
	}

}
