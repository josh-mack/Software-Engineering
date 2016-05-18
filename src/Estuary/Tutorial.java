package Estuary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class Tutorial extends Game
{
	Game game;
	eQuad quad = eQuad.N;
	boolean resolved = false, upgraded= false, volunteer=false;
	
	public Tutorial()
	{
		game = new Game();
		loadQuad();
		placeInvasive();
		placeSteward();
		mainEnviro.setNumRes(0);
		mainEnviro.setNumStew(0);
		mainEnviro.setNumVol(0);
	}
	
	public void loadQuad()
	{
		drawOnScreen(gameFrame.mainWindow.getLayeredPane(),quad, true);	
		gameFrame.inQuad = true;
		gameFrame.currentQuad = quad;
		gameFrame.backgroundPanel.paintComponent(null, gameFrame.backgroundNorthImage);
		gameFrame.topL.setVisible(true);
		gameFrame.botL.setVisible(true);
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
		SpeciesComponent invasiveAdded = new SpeciesComponent(quad, type, XCoord, YCoord);
		gameFrame.placeComp(invasiveAdded);

		mainEnviro.setHealth(mainEnviro.getHealth() - 1);
		mainEnviro.setNumInvasive(mainEnviro.getNumInvasive()+1);
	}
	
	public void placeSteward()
	{
		Game.board[5][9] = eChar.STEWARD;
		DragComponent steward =  new DragComponent(gameFrame.stewardImage, quad, eChar.STEWARD, 9%38*width, 5%24*height, 5, 9);
		gameFrame.placeComp2(steward);
	}
	
	public void resolve()
	{
		if(mainEnviro.money == 275)
		{
			resolved = true;
		}
	}
	
	public void enterCity()
	{
		if(mainEnviro.getNumVol() == 1)
			volunteer = true;
	}
	
	public void upgradeDNREC()
	{
		if(dnrecLevel== 2)
			upgraded = true;
		gameFrame.startScreen();
	}
	
}

