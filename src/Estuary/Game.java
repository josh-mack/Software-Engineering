package Estuary;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Environment mainEnviro = new Environment();
		eChar[][] temp = mainEnviro.getBoard();
		int i = 0;
		for(eChar[] set : temp){
			int j = 0;
			for(eChar val : set){
				System.out.print(temp[i][j] + " ");
				j++;
			}
			System.out.println();
			i++;
		}
		Menu mainFrame = new Menu();
		mainFrame.showMenu();

		long startTime = System.currentTimeMillis();
		int seconds = 00;
		int minutes = 0;
		int money = mainEnviro.getMoney();
		
		while (true) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime == 1000) {
				seconds++;
				if (seconds == 60) {
					minutes++;
					seconds = 0;
				}
				startTime = currentTime;
				updateTime(mainFrame, minutes, seconds);
			}
			if (money != mainEnviro.getMoney()) {
				money = mainEnviro.getMoney();
				updateMoney(mainFrame, money);
			}
		}

	}
	static void updateTime(Menu mainFrame, int minutes, int seconds){
		if (seconds < 10) {
			mainFrame.getTimeLabel().setText("TIME: " + minutes + ":0" + seconds);
		}
		else {
			mainFrame.getTimeLabel().setText("TIME: " + minutes + ":" + seconds);
		}
		mainFrame.getMainMenu().revalidate();
	}
	
	static void updateMoney(Menu mainFrame, int money) {
		mainFrame.getScoreLabel().setText("SCORE: $" + money);
	}


}
