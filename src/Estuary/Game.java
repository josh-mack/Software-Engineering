package Estuary;

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
	static int height = ((int)screenSize.getHeight())/24;
	static int width = (int)screenSize.getWidth()/38;
	
	static Fisherman fishComp;
	
	static int seconds;
	
	static Menu gameFrame;
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


				//System.out.println(testboard[i].length);
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
			
		gameFrame = new Menu();
		fishComp = new Fisherman(5%38*width, 35%24*height);
	
	}
	
	/**
	 * 
	 */
	static void startTimers(){
		ActionListener timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//test.hilight(test.sel, test);
			//	test.sel = (test.sel+1)%8;
				updateTime(gameFrame, seconds++);
				updateMoney(gameFrame, mainEnviro.getMoney());
				updateCharacters(gameFrame, mainEnviro.getNumStew(), mainEnviro.getNumRes(), mainEnviro.getNumVol());
				if (seconds%(5) == 0) {
					updateNatives(mainEnviro.getNumInvasive(), mainEnviro.getNumNative());
				}
				
			//	mainEnviro.setMoney(mainEnviro.getMoney()+20);
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
		
		ActionListener SpawnBetterMarcosButton = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainEnviro.getHealth()<=30 && mainEnviro.getHealth()>10){
					Game.makeEvent(gameFrame.getQuadrant());
				}
		}};
		
		ActionListener Spawn10 = new ActionListener(){
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
		
		//Create an Invasive species every 10 seconds
		
		new Timer(2000, Spawn80).start();
		new Timer(3000, Spawn60).start();
		new Timer(4000, Spawn30).start();
		new Timer(6000, SpawnBetterMarcosButton).start();
		new Timer(7500, Spawn10).start();
		
		new Timer(40000, nativeSpawn).start();
		new Timer(6000, fishEvent).start();
	}
	
	/**
	 * Method to update the in-game timer every one(1) real-time second.
	 * @param mainFrame
	 * @param seconds
	 */
	static void updateTime(Menu mainFrame, int seconds){
	
//		if (seconds%60 < 10) {
//			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":0" + seconds%60);
//		}
//		else {
//			mainFrame.getTimeLabel().setText("TIME: " + seconds/60 + ":" + seconds%60);
//		
//		
//		}
		checkProgress();
		mainFrame.getMainWindow().repaint();
		mainFrame.getMainWindow().revalidate();
	}
	
	/**
	 * Method to update the player's available spending money onto the game screen.
	 * @param mainFrame
	 * @param money
	 */
	static void updateMoney(Menu mainFrame, int money) {
		mainFrame.getScoreLabel().setText("ESTUARY POINTS: " + money);
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
		JComponent newComponent = null;
		
		for(int i = rowStart; i < rowEnd; i++){
			for(int j = colStart; j < colEnd; j++){
				if(board[i][j] != eChar.BLANK && board[i][j]!=eChar.NOTHING){
					DragComponent charPlace = null;
					switch(board[i][j]){
					case STEWARD:
						if(drag){
							charPlace = new DragComponent(gameFrame.stewardImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
						}
						break;
					case RESEARCHER:
						if(drag){
							charPlace = new DragComponent(gameFrame.researcherImage,quad, Game.board[i][j], j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
						}
						break;
					case VOLUNTEER:
						if(drag){
							charPlace = new DragComponent(gameFrame.volunteerImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
						}
						break;
					case WETSTEWARD:
						if(drag){
							charPlace = new DragComponent(gameFrame.stewardImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
						}
						break;
					case WETRESEARCHER:
						if(drag){
							charPlace = new DragComponent(gameFrame.researcherImage,quad, Game.board[i][j], j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
						}
						break;
					case WETVOLUNTEER:
						if(drag){
							charPlace = new DragComponent(gameFrame.volunteerImage,quad, Game.board[i][j],j%38*width, i%24*height,i,j);
							gameFrame.placeComp2(charPlace);
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
							gameFrame.placeComp3(newComponent);
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
	
	public static void deleteComponent(int i, int j) {
		gameFrame.removeComp((JComponent)gameFrame.getMainWindow().getLayeredPane().getComponentAt(j%38*width + 20, i%24*height + 20));
//		gameFrame.getMainWindow().getLayeredPane().remove(gameFrame.getMainWindow().getLayeredPane().getComponentAt(j%38*width + 20, i%24*height + 20));
//		gameFrame.getMainWindow().getLayeredPane().repaint();
//		gameFrame.getMainWindow().getLayeredPane().revalidate();
		System.out.println("Still works better than Marco's button");
		
	}
	public static JComponent getJComponentAt(int i, int j) {
		return ((JComponent)gameFrame.getMainWindow().getLayeredPane().getComponentAt(j%38*width + 20, i%24*height + 20));
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
		gameFrame.hilightOn(eQuad.W);

	}

	public static void refresh(){
		gameFrame.getMainWindow().getLayeredPane().revalidate();
		gameFrame.getMainWindow().getLayeredPane().repaint();
		gameFrame.getMainWindow().revalidate();
		gameFrame.getMainWindow().repaint();
		

	}
	
	public static int updateNatives(int numInvasives, int numNatives) {
//		System.out.print("invasive: ");
//		System.out.println(numInvasives);
//		System.out.print("natives: ");
//		System.out.println(numNatives);
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
		//Invasive invasiveAdded = new Invasive(eChar.PHRAG, 3, 10, 10, 5, 10);
		
		Random rand = new Random();
		int rowEnd,colEnd;
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
			gameFrame.hilightOn(place);
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
		int rowEnd,colEnd;
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
	
	
	public static ImageIcon getFishImage(boolean fishFlag)
	{
		return (fishFlag)?gameFrame.fishermanImage:gameFrame.fishermanOverFlowImage;
	}
	
	public static ImageIcon getDNERRImage(int level) {
		if(level == 1)
			return gameFrame.DNERRLvl1Image;
		gameFrame.changeDNERR(level);
		return (level == 2)?gameFrame.DNERRLvl2Image:gameFrame.DNERRLvl3Image;
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
		for (int i = 0; i < 4; i++ ) {
			for (int j = -2; j < 3; j++) {
				if (((j!=0) || (i!=0)) && (24*a<=y+i) && (y+i<24+24*a) && (38*b<=x+j) && (x+j<38+38*b)) {
					if (/*(Game.board[y+i][x+j] != eChar.BLANK) && (Game.board[y+i][x+j] != eChar.BLACKEYEDSUSAN) && (Game.board[y+i][x+j] != eChar.BLAZINGSTAR)
						&& (Game.board[y+i][x+j] != eChar.HCRAB) && (Game.board[y+i][x+j] != eChar.BCRAB) && (Game.board[y+i][x+j] != eChar.VOLUNTEER)
						&& (Game.board[y+i][x+j] != eChar.RESEARCHER) && (Game.board[y+i][x+j] != eChar.STEWARD) && (Game.board[y+i][x+j] != eChar.DNREC)
						&& (Game.board[y+i][x+j] != eChar.NOTHING) && (Game.board[y+i][x+j] != eChar.CITY) && (Game.board[y+i][x+j] != eChar.TRASH)
						&& (Game.board[y+i][x+j] != eChar.WETRESEARCHER) && (Game.board[y+i][x+j] != eChar.WETVOLUNTEER) && (Game.board[y+i][x+j] != eChar.WETSTEWARD)*/
							Game.board[y+i][x+j].isInvasive()) {
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
									//Game.deleteComponent(y+i, x+j);
									drag1.setDrag(false);
									return true;
								}
								alreadyBeingResolved = false;
							}
							else if ((Game.board[y+i][x+j] == eChar.FISHERMAN) && (drag1.getDryVersion() == eChar.STEWARD) && (fishFlag == false)) {
								fishComp.boatsResolve(Game.board[y][x], y+i, x+j, drag1, null);
								drag1.setDrag(false);
								return true;
							}
							
					}
					else if (Game.board[y+i][x+j] == eChar.TRASH) {
						Game.gameFrame.getMainWindow().getLayeredPane().remove(drag1);
						Game.board[drag1.getOldi()][drag1.getOldj()] = eChar.NOTHING;
						switch(drag1.getCharacter()){
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
}
