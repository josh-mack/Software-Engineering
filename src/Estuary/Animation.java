package Estuary;
//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.EnumMap;
import java.util.Random;

public class Animation extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum DIR {NE, NW, SE, SW};
	static EnumMap<DIR, Integer> directionMap;
	static DIR direction;
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
    
    
	static Random rand = new Random();   //Allows for random starting direction
										 //Also for N,S,E,W wall bounces to randomly
										 //Be able to go diagonally on a bounce

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	direction = move(direction);
		xloc = ((direction == DIR.NE)||(direction == DIR.SE))?(xloc+xIncr):(xloc-xIncr);
		yloc = ((direction == DIR.SE)||(direction == DIR.SW))?(yloc+yIncr):(yloc-yIncr);
		
		g.drawImage(allImg[directionMap.get(direction)][picNum], xloc, yloc, Color.gray, this);
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
    public DIR move(DIR current){
    	DIR nextDIR = current;
    		if((xloc+xIncr)>=(frameWidth-imgWidth))
    			nextDIR = (current == DIR.NE)?DIR.NW:DIR.SW;
    		else if((yloc - yIncr)<=-20)
    			nextDIR = (current == DIR.NW)?DIR.SW:DIR.SE;
    		else if((xloc-xIncr)<=-20)
    			nextDIR = (current == DIR.NW)?DIR.NE:DIR.SE;
    		else if((yloc+yIncr)>= (frameHeight-imgHeight))
    			nextDIR = (current == DIR.SE)?DIR.NE:DIR.NW;
    		else{
    			nextDIR = current;
    		}
    		
    	return nextDIR;
    }
    //Make frame, loop on repaint and wait
//    public static void main(String[] args) {
//    	directionMap = new EnumMap<DIR, Integer>(DIR.class);
//    	int num = rand.nextInt(4);
//    	int j = 0;
//    	for(DIR dirVal:DIR.values()){
//    		directionMap.put(dirVal, j);
//    		j++;
//    		if(directionMap.get(dirVal)==num){
//    			direction = dirVal;
//    		}
//    	}
//    	JFrame frame = new JFrame();
//    	frame.getContentPane().add(new Animation());
//    	frame.setBackground(Color.gray);
//    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	frame.setSize(frameWidth, frameHeight);
//    	frame.setVisible(true);
//    	for(int i = 0; i < 1000; i++){
//    		frame.repaint();
//    		try {
//    			Thread.sleep(100);
//    		} catch (InterruptedException e) {
//    			e.printStackTrace();
//    		}
//    	}
//    }

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
    
    //Read image from file and return
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