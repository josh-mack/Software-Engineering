package Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import Model.Environment;
import Model.eChar;
import Model.eQuad;
import View.City;
import View.DNERR;
import View.DragComponent;
import View.Fisherman;
import View.SpeciesComponent;
import View.Trash;
import View.View;
 
/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Handles all the player's input and updates its impact on the game itself.
 */
public class Game {
	static DNERR dnerrComp;
	public static Environment mainEnviro = new Environment();

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static boolean isFishFlag() {
		return fishFlag;
	}

	public static void setFishFlag(boolean fishFlag) {
		Game.fishFlag = fishFlag;
	}
	static int height = ((int)screenSize.getHeight())/24;
	static int width = (int)screenSize.getWidth()/38;
	
	static Fisherman fishComp;
	
	static int seconds;
	
	static View gameFrame;
	static int dnrecLevel = 1;
	
	static int spawnRate;
	
	static boolean secondChance = true;
	static boolean fishFlag = true;
	
	static ArrayList<SpeciesComponent> resolvingSpecies = new ArrayList<SpeciesComponent>();


	
	public static eChar[][] board =  new eChar[48][76]; //Setting overlying array to BLANK.
	

	/**
	 * Main method that starts the game's timer and initial starting money,
	 * and calls the makeEvent() method to initiate the game's primary problem mechanic.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
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
				{eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.WATER,eChar.WATER,eChar.WATER,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.BLANK,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING,eChar.NOTHING}};


			for(int i = 0; i < 48; i++){
				for(int j = 0; j < 76; j++)
				{
					Game.board[i][j] = testboard[i][j];
				}
			
		}

			board[3][7] = eChar.DNREC;
			board[35][5] = eChar.FISHERMAN;
			board[1][23] = eChar.CITY;
			
			board[1][34] = eChar.TRASH;
			board[25][72] = eChar.TRASH;
			board[1][72] = eChar.TRASH;
			board[25][34] = eChar.TRASH;
			
		gameFrame = new View();
		fishComp = new Fisherman(5%38*width, 35%24*height);
	
	}
	
	/**
	 * 
	 */
	public static void startTimers(){
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				checkProgress();
				Game.gameFrame.getMainWindow().repaint();
				Game.gameFrame.getMainWindow().revalidate();
				updateMoney(gameFrame, mainEnviro.getMoney());
				updateCharacters(gameFrame, mainEnviro.getNumStew(), mainEnviro.getNumRes(), mainEnviro.getNumVol());
				seconds++;
				if (seconds%(5) == 0) {
					updateNatives(mainEnviro.getNumInvasive(), mainEnviro.getNumNative());
				}
				
		}};
		ActionListener Spawn80 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()>80){
					Game.makeEvent(gameFrame.getQuadrant());

				}
		}};
		
		ActionListener Spawn60 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=80 && mainEnviro.getHealth()>60){
					Game.makeEvent(gameFrame.getQuadrant());

				}
		}};
		
		ActionListener Spawn30 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=60 && mainEnviro.getHealth()>30){
					Game.makeEvent(gameFrame.getQuadrant());

				}
		}};
		
		ActionListener Spawn10 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=30 && mainEnviro.getHealth()>10){
					Game.makeEvent(gameFrame.getQuadrant());
				}
		}};
		
		ActionListener Spawn0 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=10){
					Game.makeEvent(gameFrame.getQuadrant());

				}
		}};
		ActionListener nativeSpawn = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				makeNativeSpecies(gameFrame.getQuadrant(), true);
					

		}};
		
		ActionListener fishEvent = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				fishComp.boatsEvent();
			}
		};
		
		new Timer(1000, timerAction).start();
		
		
		new Timer(2000, Spawn80).start();
		new Timer(3000, Spawn60).start();
		new Timer(4000, Spawn30).start();
		new Timer(6000, Spawn10).start();
		new Timer(7500, Spawn0).start();
		
		new Timer(40000, nativeSpawn).start();
		new Timer(60000, fishEvent).start();
	}

	
	/**
	 * Method to update the player's available spending money onto the game screen.
	 * @param mainFrame
	 * @param money
	 */
	static void updateMoney(View mainFrame, int money) {
		mainFrame.getScoreLabel().setText("ESTUARY POINTS: " + money);
	}
	
	static void updateCharacters(View mainFrame, int numStewards, int numResearchers, int numVolunteers) {
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
	public static void drawOnScreen(JLayeredPane pane, eQuad quad, boolean drag){

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
		JComponent newComponent = null;
		
		for(int i = rowStart; i < rowEnd; i++){
			for(int j = colStart; j < colEnd; j++){
				if(board[i][j] != eChar.BLANK && board[i][j]!=eChar.NOTHING){
					DragComponent charPlace = null;
					ImageIcon drawImage = gameFrame.getImage(board[i][j]);
					switch(board[i][j]) {
					case STEWARD:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case RESEARCHER:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j], j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case VOLUNTEER:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case WETSTEWARD:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case WETRESEARCHER:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j], j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case WETVOLUNTEER:
						if(drag){
							charPlace = new DragComponent(drawImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeCompAtLayer(charPlace, -1);
						}
						break;
					case DNREC:
						if(drag){
							dnerrComp = new DNERR(j%38*width, i%24*height,dnrecLevel);
							gameFrame.placeComp(dnerrComp);
						}
						break;
					case FISHERMAN:
						if(drag){
							fishComp = new Fisherman(j%38*width, i%24*height);
							gameFrame.placeComp(fishComp);
						}
						break;
					case CITY:
						if(drag){
							newComponent = new City(j%38*width, i%24*height);
							gameFrame.placeComp(newComponent);
						}
						break;
					case TRASH:
						if(drag){
							newComponent = new Trash(j%38*width, i%24*height);
							gameFrame.placeCompAtLayer(newComponent, -2);
						}
					case WATER:
						break;
					default:
						newComponent = new SpeciesComponent(quad, board[i][j],j%38*width, i%24*height);
						gameFrame.placeComp(newComponent);
						break;
					}
				}
				else{
//					//Grid Layout Testing
//					JLabel holdTest = new JLabel(new ImageIcon("imgs/test.png"));
//					ImageIcon image = new ImageIcon("imgs/test.png");
//					holdTest.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
//					holdTest.setVisible(true);
//					holdTest.setLocation(j%38*width, i%24*height);
//					pane.add(holdTest, 0);
//					
		
				}
			}
		}
		Game.refresh();
		System.out.println("\n------------------------Pane Drawn------------------------");
	}
	
	public static void removeComponent(JComponent toRemove) {
		gameFrame.removeComp(toRemove);
	}
	
	public static void deleteComponentAt(int i, int j) {
		gameFrame.removeSpecies(i, j);
	}
	
	public static void placeComp(int x, int y) {
		gameFrame.placeComp(new SpeciesComponent(gameFrame.getQuadrant(), board[y][x], x%38*width, y%24*height));
	}
	
	public static void replaceDNERR(int x, int y) {
		gameFrame.removeComp(dnerrComp);
		dnerrComp = new DNERR(x,y, dnrecLevel);
		gameFrame.placeComp(dnerrComp);
		gameFrame.changeOverview(dnrecLevel);
	}
	
	public static void replaceFisherman(int x, int y)
	{
		gameFrame.removeComp(fishComp);
		fishComp = new Fisherman(x,y);
		if (Game.gameFrame.getQuadrant() == eQuad.W) {
			gameFrame.placeComp(fishComp);
		}
		gameFrame.highlightOn(eQuad.W);

	}

	public static void refresh(){
		gameFrame.getMainWindow().getLayeredPane().revalidate();
		gameFrame.getMainWindow().getLayeredPane().repaint();
		gameFrame.getMainWindow().revalidate();
		gameFrame.getMainWindow().repaint();
		

	}
	
	public static int updateNatives(int numInvasives, int numNatives) {
		if (numInvasives > numNatives + 5){
			for (int i = 0; i < gameFrame.getPlacedChars().size(); i++) {
				if (gameFrame.getPlacedChars().get(i) instanceof SpeciesComponent) {
					SpeciesComponent animal = (SpeciesComponent)gameFrame.getPlacedChars().get(i);
					if (animal.isInvasive() == false) {
						gameFrame.removeComp(animal);
						mainEnviro.setNumNative(mainEnviro.getNumNative()-1);
						mainEnviro.setHealth(mainEnviro.getHealth() - 1);
						return 0;
					}
				}
			}
		}
		return 0;
	}
	
	public static void checkProgress()
	{
		if(mainEnviro.getHealth() < 5 && secondChance)
		{
			for(int i =0; i<5;i++)
				instakill();
			secondChance = false;
		}
		if(mainEnviro.getHealth() < 5)
			Game.gameFrame.endScreen();
			
		if(mainEnviro.getHealth() > 95)
			Game.gameFrame.endScreen();
	}
	
	public static int instakill() {
		for (int i = 0; i < gameFrame.getPlacedChars().size(); i++) {
			if (gameFrame.getPlacedChars().get(i) instanceof SpeciesComponent) {
				SpeciesComponent animal = (SpeciesComponent)gameFrame.getPlacedChars().get(i);
				if (animal.isInvasive() == true) {
					gameFrame.removeComp(animal);
					mainEnviro.setNumInvasive(mainEnviro.getNumInvasive()-1);
					return 0;
				}
			}
		}
		return 0;
	}

	/**
	 * Method to create game events. This involves placing
	 * an invasive species on the board depending on which
	 * quadrant the player is zoomed into.
	 * 
	 * North quad - Phragmites
	 * East quad - Bamboo
	 * South quad - MittenCrab
	 * West quad - Pollution
	 * 
	 * @param quad
	 * @return new Event
	 */
	public static int makeEvent(eQuad quad){		
		Random rand = new Random();
		int j = rand.nextInt(76);
		int i = rand.nextInt(48);
		int x = j/38;
		int y = i/24;
		
		boolean wet = false;
		
		if (board[i][j] == eChar.WATER) {
			wet = true;
		}
		else if (board[i][j] == eChar.BLANK) {
			wet = false;
		}
		else {
			return makeEvent(quad);
		}
		
		int random = rand.nextInt(2);
		
		eQuad place;
		if (x==1) {
			if (y==1) {
				place = eQuad.S;
			}
			else {
				place = eQuad.E;
			}
		}
		else {
			if (y==1) {
				place = eQuad.W;
			}
			else {
				place = eQuad.N;
			}
		}
		if(quad != place){
			gameFrame.highlightOn(place);
		}

		int XCoord = height*(j%38);
		int YCoord = width*(i%24);
		eChar type = eChar.PHRAG;
		switch (place) {
		case N:
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
			break;
		case E:
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
			break;
		case S:
			if (random == 1) {
				if (wet) {
					return makeEvent(quad);
				}
				else {
					type = eChar.PHRAG;
				}
			}
			else {
				if (wet) {
					return makeEvent(quad);
				}
				else {
					type = eChar.BAMBOO;
				}
			}
			break;
		case W:
			if (random == 1) {
				if (wet) {
					type = eChar.ZEBRA;
				}
				else {
					type = eChar.MCRAB;
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
			break;
		default:
			break;
		}
		System.out.println("Making " + type + " in " + place);

		
		Game.board[i][j] = type;
		if (place == quad) {
			
			SpeciesComponent invasiveAdded = new SpeciesComponent(place, type, XCoord, YCoord);
			gameFrame.placeComp(invasiveAdded);
		}
		
		mainEnviro.setHealth(mainEnviro.getHealth() - 1);
		mainEnviro.setNumInvasive(mainEnviro.getNumInvasive()+1);
		return 0;
	}
	
	
	/**
	 * Method to spawn the native species on the board.
	 * These do not move.
	 * @param quad
	 * @return
	 */
	public static int makeNativeSpecies(eQuad quad, boolean randomQuad) {
		Random rand = new Random();
		int j = rand.nextInt(76);
		int i = rand.nextInt(48);
		int x = j/38;
		int y = i/24;
		
		eQuad place;
		
		int random = rand.nextInt(2);

		if (randomQuad) {			
			if (x==1) {
				if (y==1) {
					place = eQuad.S;
				}
				else {
					place = eQuad.E;
				}
			}
			else {
				if (y==1) {
					place = eQuad.W;
				}
				else {
					place = eQuad.N;
				}
			}
		}
		
		else {
			place = quad;
			i = (i%24) + y*24;
			j = (j%38) + x*38;
		}

		boolean wet = false;
		if (board[i][j] == eChar.WATER) {
			wet = true;
		}
		else if (board[i][j] == eChar.BLANK) {
			wet = false;
		}
		else {
			return makeNativeSpecies(quad, randomQuad);
		}
		
		int XCoord = height*(j%38);
		int YCoord = width*(i%24);
		eChar type = eChar.HCRAB;
		switch (place) {
		case N:
			if (random == 1) {
				if (wet) {
					type = eChar.WETHCRAB;
				}
				else {
					type = eChar.HCRAB;
				}
			}
			else {
				if (wet) {
					type = eChar.WETHCRAB;
				}
				else {
					type = eChar.BLACKEYEDSUSAN;
				}
			}
			break;
		case E:
			if (random == 1) {
				if (wet) {
					type = eChar.WETBCRAB;
				}
				else {
					type = eChar.BCRAB;
				}
			}
			else {
				if (wet) {
					type = eChar.WETBCRAB;
				}
				else {
					type = eChar.BLAZINGSTAR;
				}
			}
			break;
		case S:
			if (random == 1) {
				if (wet) {
					return makeNativeSpecies(quad, randomQuad);
				}
				else {
					type = eChar.BLACKEYEDSUSAN;
				}
			}
			else {
				if (wet) {
					return makeNativeSpecies(quad, randomQuad);
				}
				else {
					type = eChar.BLAZINGSTAR;
				}
			}
			break;
		case W:
			if (random == 1) {
				if (wet) {
					type = eChar.WETHCRAB;
				}
				else {
					type = eChar.HCRAB;
				}
			}
			else {
				if (wet) {
					type = eChar.WETBCRAB;
				}
				else {
					type = eChar.BCRAB;
				}
			}
			break;
		default:
			break;
		}
		System.out.println("Making " + type + " in " + place);

		Game.board[i][j] = type;
		
		if (place == quad) {
			SpeciesComponent nativeAdded = new SpeciesComponent(place, type, XCoord, YCoord);
			gameFrame.placeComp(nativeAdded);
		}
		
		mainEnviro.setHealth(mainEnviro.getHealth() + 1);
		mainEnviro.setNumNative(mainEnviro.getNumNative()+1);	
		return 0;
	}
	
	
	public static ImageIcon getImage(eChar character) {
		return gameFrame.getImage(character);
	}
	
	

	
	public static boolean collision(int x, int y, DragComponent drag1) {
		boolean alreadyBeingResolved = false;
		int a = y/24;
		int b = x/38;
		eQuad whatQuad = eQuad.N;
		if (a==1) {
			if (b==1) {
				whatQuad = eQuad.S;
			}
			else {
				whatQuad = eQuad.E;
			}
		}
		else {
			if (b==1) {
				whatQuad = eQuad.W;
			}
			else {
				whatQuad = eQuad.N;
			}
		}
		for (int i = 0; i < 6; i++ ) {
			for (int j = -3; j < 4; j++) {
				if (((j!=0) || (i!=0)) && (24*a<=y+i) && (y+i<24+24*a) && (38*b<=x+j) && (x+j<38+38*b)) {
					if (Game.board[y+i][x+j].isInvasive()) {
						if (Game.board[y+i][x+j] != eChar.FISHERMAN) {
							for (int k = 0; k < resolvingSpecies.size(); k++) {
								if ((resolvingSpecies.get(k).getI()==y+i) && (resolvingSpecies.get(k).getJ()==x+j) && (resolvingSpecies.get(k).isInvasive())){
									alreadyBeingResolved = true;
								}
							}
							if (alreadyBeingResolved == false) {
								SpeciesComponent invasiveSpecies = new SpeciesComponent(whatQuad, Game.board[y+i][x+j], (x%38)*width, (y%24)*height);
								resolvingSpecies.add(invasiveSpecies);
								Game.mainEnviro.resolve(Game.board[y+i][x+j], Game.board[y][x], y+i, x+j, drag1, invasiveSpecies);
								drag1.setDrag(false);
								return true;
							}
							alreadyBeingResolved = false;
						}
						else if ((Game.board[y+i][x+j] == eChar.FISHERMAN) && (drag1.getDryVersion() == eChar.STEWARD) && (fishFlag == false)) {
							fishComp.boatsResolve(Game.board[y][x], y+i, x+j, drag1);
							drag1.setDrag(false);
							return true;
						}
							
					}
					else if (Game.board[y+i][x+j] == eChar.TRASH) {
						Game.gameFrame.getMainWindow().getLayeredPane().remove(drag1);
						Game.board[drag1.getOldi()][drag1.getOldj()] = eChar.NOTHING;
						switch(drag1.getDryVersion()){
						case STEWARD:
							mainEnviro.increaseStew(true);
							break;
						case RESEARCHER:
							mainEnviro.increaseRes(true);
							break;
						case VOLUNTEER:
							mainEnviro.increaseVol(true);
							break;
						default:
							break;
						}
					}
					else if ((drag1.getDryVersion() == eChar.STEWARD) && (Game.board[y+i][x+j] == eChar.CITY)) {
						drag1.setDrag(false);
						Game.mainEnviro.resolve(Game.board[y+i][x+j], Game.board[y][x], y+i, x+j, drag1, null);
						return true;
					}
					
				}
			}
		}
		
		return false;
	}
	/**
	 * Method to place an image after being dragged, and check for collisions.
	 * @param XCoord
	 * @param YCoord
	 * @return true/false
	 */
	
	public static boolean placeInArray(int XCoord, int YCoord, DragComponent drag, eQuad quad)
	{

		int xinQuad = XCoord/width;
		int yinQuad = YCoord/height;
		int x = xinQuad;
		int y = yinQuad;
		switch (quad) {
		case N:
			x = xinQuad;
			y = yinQuad;
			break;
		case E:
			x = xinQuad + 38;
			y = yinQuad;
			break;
		case W:
			x = xinQuad;
			y = yinQuad + 24;
			break;
		case S:
			x = xinQuad + 38;
			y = yinQuad + 24;
		default:
			break;
		}

		if ((Game.board[y][x] != eChar.BLANK) && (Game.board[y][x] != eChar.NOTHING) && (Game.board[y][x] != eChar.WATER)) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (Game.board[y+i][x+j] == eChar.BLANK) {
						if (drag.getCharacter().isWet()) {
							Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
						}
						else {
							Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
						}
						drag.setCharacter(drag.getDryVersion());
						Game.board[y+i][x+j] = drag.getCharacter();
						drag.setOldi(y+i);
						drag.setOldj(x+j);
						return Game.collision(x+j,y+i,drag); 
					}
					if (Game.board[y+i][x+j] == eChar.WATER) {
						if (drag.getCharacter().isWet()) {
							Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
						}
						else {
							Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
						}
						drag.setCharacter(drag.getWetVersion());
						Game.board[y+i][x+j] = drag.getCharacter();
						drag.setOldi(y+i);
						drag.setOldj(x+j);
						return Game.collision(x+j,y+i,drag);
					}
				}
			}
			return false;
		}
		else if((Game.board[y][x] == eChar.BLANK) || (Game.board[y][x] == eChar.NOTHING)) {
			if (drag.getCharacter().isWet()) {
				Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
			}
			else {
				Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
			}
			drag.setCharacter(drag.getDryVersion());
			Game.board[y][x] = drag.getCharacter();
			drag.setOldi(y);
			drag.setOldj(x);
			return Game.collision(x,y,drag);
		}
		
		else if (Game.board[y][x] == eChar.WATER) {
			if (drag.getCharacter().isWet()) {
				Game.board[drag.getOldi()][drag.getOldj()] = eChar.WATER;
			}
			else {
				Game.board[drag.getOldi()][drag.getOldj()] = eChar.BLANK;
			}
			drag.setCharacter(drag.getWetVersion());
			Game.board[y][x] = drag.getCharacter();
			drag.setOldi(y);
			drag.setOldj(x);
			return Game.collision(x,y,drag);
		}

		return false;
	}

	public static View getGameFrame() {
		return gameFrame;
	}

	public static void setGameFrame(View gameFrame) {
		Game.gameFrame = gameFrame;
	}

	public static int getDnrecLevel() {
		return dnrecLevel;
	}

	public static void setDnrecLevel(int dnrecLevel) {
		Game.dnrecLevel = dnrecLevel;
	}
	
	public static void removeFromResolvingSpeciesArray(SpeciesComponent invasiveSpecies) {
		resolvingSpecies.remove(invasiveSpecies);
	}
}
