package Estuary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tutorial extends Game
{
	Game game;
	eQuad quad = eQuad.N;
	boolean resolved = false, upgraded= false, volunteer=false;
	
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
	}
	
	public void loadQuad()
	{
		drawOnScreen(Game.gameFrame.mainWindow.getLayeredPane(),quad, true);	
		Game.gameFrame.inQuad = true;
		Game.gameFrame.loadMenu();
		Game.gameFrame.backgroundPanel.paintComponent(null, Game.gameFrame.backgroundNorthImage);
		Game.gameFrame.topL.setVisible(true);
		Game.gameFrame.botL.setVisible(true);
		Game.gameFrame.mainWindow.repaint();
		Game.gameFrame.mainWindow.revalidate();
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
		SpeciesComponent invasiveAdded = new SpeciesComponent(quad, type, XCoord, YCoord);
		Game.gameFrame.placeComp(invasiveAdded);

		mainEnviro.setHealth(mainEnviro.getHealth() - 1);
		mainEnviro.setNumInvasive(mainEnviro.getNumInvasive()+1);
	}
	
	public void placeSteward()
	{
		Game.board[5][9] = eChar.STEWARD;
		DragComponent steward =  new DragComponent(Game.gameFrame.stewardImage, quad, eChar.STEWARD, 9%38*width, 5%24*height, 5, 9);
		Game.gameFrame.placeComp2(steward);
	}
	
	public void resolve()
	{
		if(mainEnviro.money == 4075)
		{
			resolved = true;
		}
		placeSteward();
	}
	
	public void enterCity()
	{
		if(mainEnviro.getNumVol() == 1)
			volunteer = true;
		placeSteward();
	}
	
	public void upgradeDNREC()
	{
		if(dnrecLevel== 2)
			upgraded = true;
		Game.gameFrame.startScreen();
	}
	
	public void startTimer()
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() 
            {
                resolve();
                enterCity();
                upgradeDNREC();
                completed();
            }
        }, 1000);
	}
	
	public void completed()
	{
		if(volunteer && upgraded && resolved)
			Game.gameFrame.startScreen();
	}
}

