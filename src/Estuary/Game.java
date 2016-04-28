package Estuary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Game {
	public static Environment mainEnviro = new Environment();

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int height = ((int)screenSize.getHeight())/24;
	static int width = (int)screenSize.getWidth()/38;
	
	static int seconds;
	static int money;
	
	public static eChar[][] board =  new eChar[48][76];
	public static void initBoard(){
		for(int i = 0; i < 48; i++){
			for(int j = 0; j < 76; j++){
				board[i][j] = eChar.BLANK;
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		initBoard();
		Menu test = new Menu();
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//test.hilight(test.sel, test);
			//	test.sel = (test.sel+1)%8;
				updateTime(test, seconds++);
				updateMoney(test, mainEnviro.getMoney());
			//	mainEnviro.setMoney(mainEnviro.getMoney()+20);
		}};
		ActionListener timerSpawn = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(test.getQuadrant()!=eQuad.MAIN){
					Event retVal = mainEnviro.makeEvent(test.getQuadrant());
					Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
					
					
					drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant());	
					for(int i = 0; i < 48; i++){
						for(int j = 0; j < 76; j++){
							if(Game.board[i][j] != eChar.BLANK)
							System.out.println(Game.board[i][j]);;
						}
					}
				}
		}};
		new Timer(1000, timerAction).start();
		
		//Create an Invasive species every 1 second
		new Timer(10000, timerSpawn).start();
		ActionListener timerCheck = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.money = mainEnviro.getMoney();
		}};		
		new Timer(3000, timerCheck);
	
	
	}
	static void updateTime(Menu mainFrame, int seconds){
	
		if (seconds%60 < 10) {
			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":0" + seconds%60);
		}
		else {
			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":" + seconds%60);
		
		
		}
		mainFrame.getMenu().repaint();
		mainFrame.getMenu().revalidate();
	}
	
	static void updateMoney(Menu mainFrame, int money) {
		mainFrame.getScoreLabel().setText("MONEY: $" + money);
	}
	
	static void drawOnScreen(JLayeredPane pane, eQuad quad){

		int rowStart;
		int colStart;
		int rowEnd;
		int colEnd;
		switch(quad){
		case N:
			rowStart = 0;
			colStart = 0;
			rowEnd = 24;
			colEnd = 38;
			break;
		case E:
			rowStart = 0;
			colStart = 38;
			rowEnd = 24;
			colEnd = 76;
			break;
		case S:
			rowStart = 24;
			colStart = 38;
			rowEnd = 48;
			colEnd = 76;
			break;
		case W:
			rowStart = 24;
			colStart = 0;
			rowEnd = 48;
			colEnd = 38;
			break;
			
		default:
			rowStart = 0;
			colStart = 0;
			rowEnd = 0;
			colEnd = 0;
		}
		SpeciesComponent test = null;
		
		for(int i = rowStart; i < rowEnd; i++){
			for(int j = colStart; j < colEnd; j++){
				if(board[i][j] != eChar.BLANK){
					System.out.println("Species: " + board[i][j] + "\n X-location: " + i + "\n Y-location: " + j);
					DragComponent charPlace = null;
					switch(board[i][j]){
					case STEWARD:
						charPlace = new DragComponent("imgs/pika.png",quad, Game.board[i][j],j%38*width, i%24*height);
						
						pane.add(charPlace, 0);
						break;
					case RESEARCHER:
						charPlace = new DragComponent("imgs/oak.png",quad, Game.board[i][j], j%38*width, i%24*height);
						
						pane.add(charPlace, 0);
						break;
					case VOLUNTEER:
						charPlace = new DragComponent("imgs/red.png",quad, Game.board[i][j],j%38*width, i%24*height);
					
						pane.add(charPlace, 0);
						break;
					
					default:
						test = new SpeciesComponent(quad, board[i][j],j%38*width, i%24*height);
						pane.add(test, 0);
						break;
					}
				}
			}
		}
		System.out.println("\n------------------------Pane Drawn------------------------");
	}
	

	


}
