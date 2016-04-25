package Estuary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Game {
	static int seconds;
	int money;
	public static eChar[][] board =  new eChar[76][48];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		initBoard();
		Environment mainEnviro = new Environment();
		Menu test = new Menu();
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
//				test.hilight(test.sel, test);
//				test.sel = (test.sel+1)%8;
				updateTime(test, seconds++);
				updateMoney(test, mainEnviro.getMoney());
				mainEnviro.setMoney(mainEnviro.getMoney()+20);
		}};
		new Timer(1000, timerAction).start();
		
		Event retVal = mainEnviro.makeEvent(test.getQuadrant());
		board[retVal.getY()][retVal.getX()] = retVal.getType();
		drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant());		
	
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
		for(int i = 0; i < 76; i++){
			for(int j = 0; j < 48; j++){
				if(board[i][j] != eChar.BLANK){
					pane.add(new SpeciesComponent(quad, board[i][j], 200, 200));
				}
			}
		}
	}
	
	static void initBoard(){
		for(int i = 0; i < 76; i++){
			for(int j = 0; j < 48; j++){
				board[i][j] = eChar.BLANK;
			}
		}
	}


}
