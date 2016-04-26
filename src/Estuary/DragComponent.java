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
				if(placeInArray(getX(), getY())) {
					character.setXLoc(getX());
					character.setYLoc(getY());
					repaint();
					revalidate();
				}
				else {
					getRootPane().remove(getComponentAt(e.getPoint()));
				}
			}
	
	
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

		int x = (XCoord/(width/76))%38;
		int y = (YCoord/(height/48))%24;
		//eChar[][] board = envio.getBoard();
		
		switch(whatQuad)
		{
		case N:
			System.out.println("placeInArray");
				if (Game.board[y][x] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j] == eChar.BLANK) {
								Game.board[y+i][x+j] = this.character;
								return Collision(x+i,y+j); // can be changed to return true
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
					Collision(x, y); 
				}
				return false;
			
		case E:
			
				if (Game.board[y][x+38] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i][x+j+38] == eChar.BLANK) {
								Game.board[y+i][x+j+38] = this.character;
								return Collision(x+i+38,y+j); // can be changed to return true
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
								return Collision(x+i,y+j+24); // can be changed to return true
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
			
		case S:
			
				if (Game.board[y+24][x+38] != eChar.BLANK) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (Game.board[y+i+24][x+j+38] == eChar.BLANK) {
								Game.board[y+i+24][x+j+38] = this.character;
								return Collision(x+i+38,y+j+24); // can be changed to return true
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
		return (Collision(x,y));
		
	}
	
	public boolean Collision(int x, int y) {
		//System.out.println("Collision active");
		//Game.mainEnviro.resolve();
		//return true;
	//}
		System.out.println("Collision");
		if((Game.board[y+1][x+1] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+1][x+0] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+0][x+1] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y][x-1] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y+1][x-1] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x+1] != eChar.BLANK)){
			Game.mainEnviro.resolve();
			return true;
		}
		if((Game.board[y-1][x-1] != eChar.BLANK)){
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

