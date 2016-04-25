package Estuary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game {
	static int seconds;
	int money;
	static eChar[][] board =  new eChar[76][48];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Environment mainEnviro = new Environment();
		Menu test = new Menu();
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				test.hilight(test.sel, test);
				test.sel = (test.sel+1)%8;
				updateTime(test, seconds++);
				updateMoney(test, mainEnviro.getMoney());
				mainEnviro.setMoney(mainEnviro.getMoney()+20);
		}};
		new Timer(1000, timerAction).start();
		
	
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


}
