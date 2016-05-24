package Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.eChar;
import Model.eQuad;
import View.DragComponent;
import View.Fisherman;
import View.SpeciesComponent;
import View.View;

public class Tutorial extends Game
{
	Game game;
	eQuad quad = eQuad.N;
	boolean resolved = false, upgraded= false, volunteer=false;
	JFrame tutFrame;
	JPanel tutPanel1, tutPanel2, tutPanel3, tutPanel4, tutPanel5, tutPanel6;
	JLabel tutHowTo1, tutHowTo2, tutHowTo3, tutHowTo4, tutHowTo5, tutHowTo6;
	DragComponent steward;
	SpeciesComponent invasiveAdded;
	Timer mainTimer, timer, endTimer;
	int dialogNum = 0;
	
	public Tutorial()
	{
		game = new Game();
		Game.gameFrame.loadImages();
		loadQuad();
		placeInvasive();
		placeSteward();
		mainEnviro.setNumRes(0);
		mainEnviro.setNumStew(0);
		mainEnviro.setNumVol(0);
		mainEnviro.setMoney(4000);
		startTimer();
		howToPlay();
	}
	
	public void loadQuad()
	{
		gameFrame.loadMenu();
		Game.drawOnScreen(gameFrame.mainWindow.getLayeredPane(),quad, true);	
		gameFrame.setInQuad(true);
		gameFrame.setCurrentQuad(quad);
		gameFrame.getBackgroundPanel().paintComponent(null, gameFrame.getBackgroundNorthImage());
		gameFrame.getTopL().setVisible(true);
		gameFrame.mainWindow.repaint();
		gameFrame.mainWindow.revalidate();
	}
	
	public void placeInvasive()
	{
		Random rand = new Random();
		int j = rand.nextInt(36);
		int i = rand.nextInt(24);
		
		boolean wet = false;
		
		if (board[i][j] == eChar.WATER) {
			wet = true;
		}
		else if (board[i][j] == eChar.BLANK) {
			wet = false;
		}
		else {
			placeInvasive();
		}
		
		int random = rand.nextInt(2);
		
		int XCoord = height*(j%38);
		int YCoord = width*(i%24);
		eChar type = eChar.PHRAG;
		if (random == 1) {
			if (wet) {
				type = eChar.WETMCRAB;
			}
			else {
				type = eChar.PHRAG;
			}
		}
		else {
			if (wet) {
				type = eChar.WETMCRAB;
			}
			else {
				type = eChar.MCRAB;
			}
		}
		Game.board[i][j] = type;
		invasiveAdded = new SpeciesComponent(quad, type, XCoord, YCoord);
		Game.gameFrame.placeComp(invasiveAdded);

		mainEnviro.setHealth(mainEnviro.getHealth() - 1);
		mainEnviro.setNumInvasive(mainEnviro.getNumInvasive()+1);
	}
	
	public void placeSteward()
	{
		Game.board[5][9] = eChar.STEWARD;
		steward =  new DragComponent(gameFrame.getStewardImage(), quad, eChar.STEWARD, 9%38*width, 5%24*height, 5, 9, true);
		Game.gameFrame.placeCompAtLayer(steward, -1);
	}
	
	public void resolve()
	{
		if(mainEnviro.money == 4075 && resolved == false)
		{
			removeComponent(invasiveAdded);
			resolved = true;
			placeSteward();
			changeDialog();
		}
	}
	
	public void enterCity()
	{
		if(mainEnviro.getNumVol() == 1 && volunteer == false)
		{
			volunteer = true;
			placeSteward();
			changeDialog();
		}	
	}
	
	public void upgradeDNREC()
	{
		if(dnrecLevel== 2 && upgraded == false)
		{
			upgraded = true;
			placeSteward();
			changeDialog();
		}
	}
	
	public void startTimer()
	{
		ActionListener tick = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
            	updateCharacters(gameFrame, mainEnviro.getNumStew(), mainEnviro.getNumRes(), mainEnviro.getNumVol());
            	updateMoney(gameFrame, mainEnviro.getMoney());
                resolve();
                enterCity();
                upgradeDNREC();
                completed();
			}
		};
		
		mainTimer = new Timer(1000, tick);
		mainTimer.start();
	}
	
	
	
	public void completed()
	{
		ActionListener end = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eChar[][] testboard = {{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING},
						{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,/*hold */eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING},
						{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING},
						{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER},
						{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER},
						{eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER},
						{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING},
						{eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING},
						{eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING},
						{eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING},
						{eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
						{eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK},
						{eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK},
						{eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.NOTHING},
						{eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING},
						{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING}};
						for(int i = 0; i < 48; i++){
							for(int j = 0; j < 76; j++)
							{
								Game.board[i][j] = testboard[i][j];
							}
						}
						dnrecLevel = 1;
						board[5][7] = eChar.DNREC;
						board[35][5] = eChar.FISHERMAN;
						board[5][23] = eChar.CITY;
						board[1][34] = eChar.TRASH;
						board[25][72] = eChar.TRASH;
						board[1][72] = eChar.TRASH;
						board[25][34] = eChar.TRASH;
						gameFrame = new View();
						fishComp = new Fisherman(5%38*width, 35%24*height);
						
						endTimer.stop();
			}
		};
		
		endTimer = new Timer(5000, end);
		if(volunteer && upgraded && resolved)
		{
			changeDialog();
			endTimer.start();
			mainTimer.stop();
			System.gc();	
		}
	}
	
	public void howToPlay()
	{
		
		tutFrame = new JFrame();
		
		tutPanel1 = new JPanel();
		ImageIcon tut1 = gameFrame.getTutHowTo1();
		int imgWidth = tut1.getIconWidth();
		int imgHeight = tut1.getIconHeight();
		tutPanel1.setSize(imgWidth, imgHeight);
		tutPanel1.setOpaque(false);	
		tutPanel1.add(new JLabel(tut1));
		
		tutPanel2 = new JPanel();
		ImageIcon tut2 = gameFrame.getTutHowTo2();
		imgWidth = tut2.getIconWidth();
		imgHeight = tut2.getIconHeight();
		tutPanel2.setSize(imgWidth, imgHeight);
		tutPanel2.setOpaque(false);	
		tutPanel2.add(new JLabel(tut2));
		
		tutPanel3 = new JPanel();
		ImageIcon tut3 = gameFrame.getTutHowTo3();
		imgWidth = tut3.getIconWidth();
		imgHeight = tut3.getIconHeight();
		tutPanel3.setSize(imgWidth, imgHeight);
		tutPanel3.setOpaque(false);	
		tutPanel3.add(new JLabel(tut3));
		
		tutPanel4 = new JPanel();
		ImageIcon tut4 = gameFrame.getTutHowTo4();
		imgWidth = tut4.getIconWidth();
		imgHeight = tut4.getIconHeight();
		tutPanel4.setSize(imgWidth, imgHeight);		
		tutPanel4.setOpaque(false);	
		tutPanel4.add(new JLabel(tut4));
		
		tutPanel5 = new JPanel();
		ImageIcon tut5 = gameFrame.getTutHowTo5();
		imgWidth = tut5.getIconWidth();
		imgHeight = tut5.getIconHeight();
		tutPanel5.setSize(imgWidth, imgHeight);
		tutPanel5.setOpaque(false);	
		tutPanel5.add(new JLabel(tut5));
		
		tutPanel6 = new JPanel();
		ImageIcon tut6 = gameFrame.getTutHowTo6();
		imgWidth = tut6.getIconWidth();
		imgHeight = tut6.getIconHeight();
		tutPanel6.setSize(imgWidth, imgHeight);
		tutPanel6.setOpaque(false);	
		tutPanel6.add(new JLabel(tut6));
		
		tutFrame.add(tutPanel1);		
		tutFrame.setAlwaysOnTop(true);
		tutFrame.setUndecorated(true);
		tutFrame.setOpacity(1.0f);
		tutFrame.setBackground(View.alphaLayer);
		tutFrame.setSize(imgWidth, imgHeight);
		tutFrame.setLocation(steward.getX()+imgWidth/2, steward.getY()-imgHeight);
		tutFrame.setVisible(true);
		
	}
	
	public void changeDialog(){
		switch(dialogNum){
		case 0:
			dialogNum = 1;
			tutFrame.remove(tutPanel1);
			tutFrame.add(tutPanel2);
			break;
		case 1:
			dialogNum = 2;
			tutFrame.remove(tutPanel2);
			tutFrame.add(tutPanel3);
			changeDialogTimer();
			break;
		case 2:
			dialogNum = 3;
			tutFrame.remove(tutPanel3);
			tutFrame.add(tutPanel4);
			break;
		case 3:
			dialogNum = 4;
			tutFrame.remove(tutPanel4);
			tutFrame.add(tutPanel5);
			break;
		case 4:
			dialogNum = 5;
			tutFrame.remove(tutPanel5);
			tutFrame.add(tutPanel6);
		case 5:
			dialogNum = 0;
			tutFrame.dispose();
		}
		tutFrame.revalidate();
		
	}
	
	public void changeDialogTimer()
	{
		ActionListener tick = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
            	changeDialog();
            	changeDialog();
            	timer.stop();
			}
		};
		
		timer = new Timer(5000, tick);
		timer.start();
	}
}

