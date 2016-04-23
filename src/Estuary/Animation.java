package Estuary;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Animation extends JPanel{
	private static final long serialVersionUID = 1L;
    
	
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    BufferedImage[][] allImg;
    int xloc = 175;
    int yloc = 50;
    final int xIncr = 8;
    final int yIncr = 3;
    
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
	
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;

		
		g.drawImage(allImg[1][picNum], xloc, yloc, Color.gray, this);
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
    
    //Constructor: get image, segment and store in array
    public Animation(){
    	allImg = new BufferedImage[4][10];
    	for(int j = 0; j < 4; j++){
    		BufferedImage img = createImage(j);
    		pics = new BufferedImage[10];
    		for(int i = 0; i < frameCount; i++){
    			allImg[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    		
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    	}  
    }
	
	
	
	private BufferedImage createImage(int sel){
    	BufferedImage bufferedImage;
    	String filename = "images/orc/orc_forward_southeast.png";
    	
    	switch(sel){
    	case 0:
    		filename = "images/orc/orc_forward_northeast.png";
    		break;
    	case 1:
    		filename = "images/orc/orc_forward_northwest.png";
    		break;
    	case 2:
    		filename = "images/orc/orc_forward_southeast.png";
    		break;
    	case 3:
    		filename = "images/orc/orc_forward_southwest.png";    		
    		break;
    	default:
    	}
    	
    	
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}



