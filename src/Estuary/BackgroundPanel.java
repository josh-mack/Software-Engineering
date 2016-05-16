package Estuary;


import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	String filename;
	int height;
	int width;
	BufferedImage backgroundImage = null;
	
	public void paint(Graphics g){
		try {
			backgroundImage = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(backgroundImage,0, 0, width, height, this);
	}
	public BackgroundPanel(String filename, int width, int height){
		this.filename = filename;
		this.height = height;
		this.width = width;
	}

	protected void paintComponent(Graphics g, String filename){
		this.filename = filename;
	}
}
