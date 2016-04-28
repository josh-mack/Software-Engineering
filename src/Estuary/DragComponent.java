package Estuary;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class DragComponent extends JComponent {
	private volatile int XOnScreen;
	private volatile int YOnScreen;
	private volatile int XCoord;
	private volatile int YCoord;
	private MouseListener pressListener;
	
	private eChar character;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = ((int)screenSize.getHeight());
	int width = (int)screenSize.getWidth();
	
	private eQuad whatQuad;
	private eChar[][] board;
	
	public DragComponent(String imageName, eQuad thisQuad, eChar character, int x, int y) {
		setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(imageName);
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);		
		
		setLocation(x,y);
		//setOpaque(false);
		
		this.whatQuad = thisQuad;
		this.character = character;
		
		pressListener = new MouseListener() {
	
			@Override
			public void mouseClicked(MouseEvent e) { }
	
			@Override
			public void mousePressed(MouseEvent e) {
		        XOnScreen = e.getXOnScreen();
		        YOnScreen = e.getYOnScreen();
		        XCoord = getX();
		        YCoord = getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) 
			{
				int oldx = XCoord;
				int oldy = YCoord;
				placeInArray(getX(), getY());
				//Game.board[oldy][oldx] = eChar.BLANK;
					
					System.out.println(getX());
					System.out.println(getY());
					repaint();
				revalidate();
				System.out.println(getX());
				System.out.println(getY());
				
				}
				//else {
					//getRootPane().remove(getComponentAt(e.getPoint()));
				//}
		//	}
	
	
		};
		addMouseListener(pressListener);
		
	    addMouseMotionListener(new MouseMotionListener() {
	    	
	    	@Override
	    	public void mouseDragged(MouseEvent e) {
	    		setLocation(XCoord + (e.getXOnScreen() - XOnScreen), YCoord + (e.getYOnScreen() - YOnScreen));
	    	}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	    });
  
	}
	
	public boolean placeInArray(int XCoord, int YCoord)
	{

		int x = XCoord/(width/38);
		int y = YCoord/(height/24);
		//eChar[][] board = envio.getBoard();
		
		switch(whatQuad)
		{
		case N:
				if (Game.board[y][x] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j] == eChar.BLANK) {
								Game.board[y+i][x+j] = this.character;
								return Collision(x+j,y+i); // can be changed to return true
							}
						}
					}
					return false;
				}
			//	if (Collision(x,y)) {
				//	Game.board[y][x] = this.character;
					//return true;
			//	}
				else if(Game.board[y][x] == eChar.BLANK){
				
					Game.board[y][x] = this.character;
					//character.setXLoc(38%getY());
					//character.setYLoc(24%getX());
					//this.revalidate();
			    	//this.setLocation(x, y);
					Collision(x, y); 
					
					
			
				}
				
				return false;
		case E:
			
				if (Game.board[y][x+38] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j+38] == eChar.BLANK) {
								Game.board[y+i][x+j+38] = this.character;
								return Collision(x+j+38,y+i); // can be changed to return true
							}
					}
					return false;
					}
				if (Collision(x+38,y)) {
					Game.board[y][x+38] = this.character;
					return true;
				}
				return false;
			}
		case W:
			try
			{
				if (Game.board[y+24][x] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i+24][x+j] == eChar.BLANK) {
								Game.board[y+i+24][x+j] = this.character;
								return Collision(x+j,y+i+24); // can be changed to return true
							}
						}
					}
					return false;
				}
				if (Collision(x,y+24)) {
					Game.board[y+24][x] = this.character;
					return true;
				}
				return false;
			} catch(IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
			break;
			
		case S:
			
				if (Game.board[y+24][x+38] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i+24][x+j+38] == eChar.BLANK) {
								Game.board[y+i+24][x+j+38] = this.character;
								return Collision(x+j+38,y+i+24); // can be changed to return true
							}
						}
					}
					return false;
				}
				else if (Collision(x+38,y+24)) {
					Game.board[y+24][x+38] = this.character;
					return true;
				}
				return false;
			}
		for(int i = 0; i < 48; i++){
			for(int j = 0; j < 76; j++){
				System.out.println(Game.board[i][j]);
			}
		}
		
		return (Collision(x,y));
		
	}
	
	public boolean Collision(int x, int y) {
		//System.out.println("Collision active");
		//Game.mainEnviro.resolve();
		//return true;
	//}
	//	System.out.println("Collision");
		if((Game.board[y+1][x+1] != eChar.BLANK)){
			System.out.println("case 1");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+2][x+2] != eChar.BLANK)){
			System.out.println("case 1");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+2][x+1] != eChar.BLANK)){
			System.out.println("case 2");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+1][x+2] != eChar.BLANK)){
			System.out.println("case 2");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+1][x+0] != eChar.BLANK)){
			System.out.println("case 2");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+0][x+1] != eChar.BLANK)){
			System.out.println("case 3");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x] != eChar.BLANK)){
			System.out.println("case 4");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y][x-1] != eChar.BLANK)){
			System.out.println("case 5");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+1][x-1] != eChar.BLANK)){
			System.out.println("case 6");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x+1] != eChar.BLANK)){
			System.out.println("case 7");
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x-1] != eChar.BLANK)){
			System.out.println("case 8");
			Game.mainEnviro.resolve();
			return true;
		}
		
		return false;
	}

	public MouseListener getMouseListener() {
		// TODO Auto-generated method stub
		return pressListener;
	}
}

