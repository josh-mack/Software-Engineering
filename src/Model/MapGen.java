package Model;


import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

 
public class MapGen extends Component {
	 public static String[][] board =  new String[1080][1920]; 
	 public static String[][] game = new String[48][76];
  public static void main(String[] foo) {

    new MapGen();
    System.out.print("{");
    for(int z = 0; z < 48; z++ ){
    	for(int t = 0; t < 76; t++){
    		if(t < 76)
    		System.out.print(game[z][t] + ",");
    		else{
    			System.out.print("}");
    			System.out.println("");
    		}
    	}
    }
  
    try
    {
        PrintWriter pr = new PrintWriter("imgs/hold.txt");    
        pr.print("{{");
        for (int i=0; i<48 ; i++){
        	for(int x = 0; x<76; x++){
        		if(x<75)
            pr.print(game[i][x] + ",");
        		else{
        			pr.print("}");
        			pr.println("");
        			
        		}
        	}
        }
        pr.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
  }
  
 
  public int findBlue(int pixel) {
    int blue = (pixel) & 0xff;
    return blue;
  }
  public int findGreen(int pixel) {
	    int green = (pixel >> 8) & 0xff;
	    return green;
	  }
	 
  private void imageIteration(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        int pixel = image.getRGB(j, i);
       if(findBlue(pixel) > findGreen(pixel))
    	   board[i][j] = "blue";
        if(findGreen(pixel) > findBlue(pixel)){
        	board[i][j] = "green";
      }
        else if(findBlue(pixel) == findGreen(pixel)){
        	board[i][j] = "nothing";
        }
      }
    }
    for(int x = 0; x < 48; x++){
    	for(int y = 0; y < 76; y++){
    		int s = 0;
    		int u = y*22;
  
    		game[x][y] = board[s][u];
    		if(y%75 == 0) {
    			s = (s + 1) * 22;
    			
    		}
    		
    	}
    }
   
  }
 
  public MapGen() {
    try {

    BufferedImage img = ImageIO.read(new File("imgs/loadgameboard.png"));
    imageIteration(img);
    } catch (IOException e) {
    }
  }
 
}