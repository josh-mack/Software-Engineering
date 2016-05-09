package Estuary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all the player's input and updates its impact on the game itself.
 */
public class Game {
	public static Environment mainEnviro = new Environment();

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int height = ((int)screenSize.getHeight())/24;
	static int width = (int)screenSize.getWidth()/38;
	
	static int seconds;
	
	static Menu test;
	static int dnrecLevel = 1;
	
	static int spawnRate;

	
	public static eChar[][] board =  new eChar[48][76]; //Setting overlying array to BLANK.
	
	public static void initBoard(){
		for(int i = 0; i < 48; i++){
			for(int j = 0; j < 76; j++)
			{
				board[i][j] = eChar.BLANK;
			}
		}
		board[3][10] = eChar.DNREC;
		board[15][70] = eChar.FISHERMAN;
		board[35][5] = eChar.CITY;
	}
	
	/**
	 * Main method that starts the game's timer and initial starting money,
	 * and calls the makeEvent() method to initiate the game's primary problem mechanic.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		eChar[][] testboard = {{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING},
				{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING}};
			for(int i = 0; i < 48; i++){
			for(int j = 0; j < 76; j++)
			{
				
				board[i][j] = testboard[i%24][j%38];
		
			}
		}

		//initBoard();
		test = new Menu();
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//test.hilight(test.sel, test);
			//	test.sel = (test.sel+1)%8;
				updateTime(test, seconds++);
				updateMoney(test, mainEnviro.getMoney());
				updateCharacters(test, mainEnviro.getNumStew(), mainEnviro.getNumRes(), mainEnviro.getNumVol());
			//	mainEnviro.setMoney(mainEnviro.getMoney()+20);
		}};
		ActionListener Spawn80 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()>80){
					if(test.getQuadrant()!=eQuad.MAIN){
						Event retVal = mainEnviro.makeEvent(test.getQuadrant());
						Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
						drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					}
				}
		}};
		
		ActionListener Spawn60 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=80 && mainEnviro.getHealth()>60){
					if(test.getQuadrant()!=eQuad.MAIN){
						Event retVal = mainEnviro.makeEvent(test.getQuadrant());
						Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
						drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					}
				}
		}};
		
		ActionListener Spawn30 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=60 && mainEnviro.getHealth()>30){
					if(test.getQuadrant()!=eQuad.MAIN){
						Event retVal = mainEnviro.makeEvent(test.getQuadrant());
						Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
						drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					}
				}
		}};
		
		ActionListener SpawnBetterMarcosButton = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=30 && mainEnviro.getHealth()>10){
					if(test.getQuadrant()!=eQuad.MAIN){
						Event retVal = mainEnviro.makeEvent(test.getQuadrant());
						Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
						drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					}
				}
		}};
		
		ActionListener Spawn10 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=10){
					if(test.getQuadrant()!=eQuad.MAIN){
						Event retVal = mainEnviro.makeEvent(test.getQuadrant());
						Game.board[retVal.getY()][retVal.getX()] = retVal.getType();
						drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					}
				}
		}};
		
		ActionListener nativeSpawn = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(test.getQuadrant()!=eQuad.MAIN){
					Native retVal = mainEnviro.makeNativeSpecies(test.getQuadrant());
					Game.board[retVal.getYCoord()][retVal.getXCoord()] = retVal.getType();
					System.out.println(Game.board[retVal.getYCoord()][retVal.getXCoord()]);
					
					
					drawOnScreen(test.getMenu().getLayeredPane(), test.getQuadrant(), false);	
					for(int i = 0; i < 48; i++){
						for(int j = 0; j < 76; j++){
							if(Game.board[i][j] != eChar.BLANK)
							System.out.println(Game.board[i][j]);;

						}
					}
				}
				mainEnviro.setHealth(mainEnviro.getHealth() + 1);
		}};
		
		new Timer(1000, timerAction).start();
		
		//Create an Invasive species every 10 seconds
		
		
		new Timer(4000, Spawn80).start();
		new Timer(6000, Spawn60).start();
		new Timer(8000, Spawn30).start();
		new Timer(12000, SpawnBetterMarcosButton).start();
		new Timer(15000, Spawn10).start();
		
		new Timer(40000, nativeSpawn).start();
		
		//new Timer(30000, powerUpSpawn).start();
		
		
//		ActionListener timerCheck = new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Game.money = mainEnviro.getMoney();
//		}};		
//		new Timer(3000, timerCheck);
	
	
	}
	
	/**
	 * Method to update the in-game timer every one(1) real-time second.
	 * @param mainFrame
	 * @param seconds
	 */
	static void updateTime(Menu mainFrame, int seconds){
	
		if (seconds%60 < 10) {
			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":0" + seconds%60);
		}
		else {
			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":" + seconds%60);
		
		
		}
		mainEnviro.checkProgress();
		mainFrame.getMenu().repaint();
		mainFrame.getMenu().revalidate();
	}
	
	/**
	 * Method to update the player's available spending money onto the game screen.
	 * @param mainFrame
	 * @param money
	 */
	static void updateMoney(Menu mainFrame, int money) {
		mainFrame.getScoreLabel().setText("MONEY: $" + money);
	}
	
	static void updateCharacters(Menu mainFrame, int numStewards, int numResearchers, int numVolunteers) {
		mainFrame.getStewardLabel().setText("Stewards: " + numStewards);
		mainFrame.getResearcherLabel().setText("Researchers: " + numResearchers);
		mainFrame.getVolunteerLabel().setText("Volunteers: " + numVolunteers);
	}
	/**
	 * Method used to place each species (when needed) onto the game's screen.
	 * This is based on the quadrant the player is currently zoomed into.
	 * Prints the eChar and location of the eChar placed in the console.
	 * @param pane
	 * @param quad
	 */
	static void drawOnScreen(JLayeredPane pane, eQuad quad, boolean drag){

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
					//System.out.println("Species: " + board[i][j] + "\n X-location: " + i + "\n Y-location: " + j);
					DragComponent charPlace = null;
					switch(board[i][j]){
					case STEWARD:
						if(drag){
							charPlace = new DragComponent("imgs/volunteer_blueshirt_front_0.png",quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							pane.add(charPlace, 0);
						}
						break;
					case RESEARCHER:
						if(drag){
							charPlace = new DragComponent("imgs/researcher_withClipboard.png",quad, Game.board[i][j], j%38*width, i%24*height,i,j);
							pane.add(charPlace, 0);
						}
						break;
					case VOLUNTEER:
						if(drag){
							charPlace = new DragComponent("imgs/volunteer_redshirt_walk_front_0.png",quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							pane.add(charPlace, 0);
						}
						break;
					case DNREC:
						if(drag){
							pane.add(new DNERR(j%38*width, i%24*height,dnrecLevel),0);
						}
						break;
					case FISHERMAN:
						pane.add(new Fisherman(j%38*width, i%24*height), 0);
						break;
					case CITY:
						pane.add(new City(j%38*width, i%24*height), 0);
						break;
					default:
						test = new SpeciesComponent(quad, board[i][j],j%38*width, i%24*height);
						pane.add(test, 0);
						break;
					}
				}
				else{
					
					/*JLabel holdTest = new JLabel(new ImageIcon("imgs/test.png"));
					ImageIcon image = new ImageIcon("imgs/test.png");
					holdTest.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
					holdTest.setVisible(true);
					holdTest.setLocation(j%38*width, i%24*height);
					pane.add(holdTest, 0);
					*/
		
				}
			}
		}
		System.out.println("\n------------------------Pane Drawn------------------------");
	}
	
	public static void deleteComponent(int i, int j) {
		while (test.getMenu().getLayeredPane().getComponentAt(j%38*width + 20, i%24*height + 20) instanceof SpeciesComponent){
			test.getMenu().getLayeredPane().remove(test.getMenu().getLayeredPane().getComponentAt(j%38*width + 20, i%24*height + 20));
			test.getMenu().getLayeredPane().repaint();
			test.getMenu().getLayeredPane().revalidate();
			System.out.println("Still works better than Marco's button");
		}
	}

	public static void replaceDNERR(int x, int y) {
		test.getMenu().getLayeredPane().remove(test.getMenu().getLayeredPane().getComponentAt(x,y));
		test.getMenu().getLayeredPane().repaint();
		test.getMenu().getLayeredPane().revalidate();
		test.getMenu().getLayeredPane().add(new DNERR(x,y, dnrecLevel),0);
		
		drawOnScreen(test.getMenu().getLayeredPane(),eQuad.N,false);
	}



}
