package EstuaryTests;

import Estuary.*;
import Model.eChar;
import Model.eQuad;
import View.DragComponent;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Test;

import Controller.Game;

public class DraggableTest {

	@Test
	public void Collision_test() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = ((int)screenSize.getHeight());
		int width = (int)screenSize.getWidth();
		int XCoord = (width/76)*6;
		int YCoord = (height/48)*7;
		int x = 6;
		int y = 7;
		

	//	DragComponent character = new DragComponent("imgs/pika.png", eQuad.N, eChar.RESEARCHER, XCoord, YCoord,0,0);
		Game.board[y-1][x+1] = eChar.PHRAG;
		System.out.println(Game.board[y-1][x+1]);
		System.out.println(Game.board[y][x]);
		System.out.println(Game.mainEnviro.getMoney());
		System.out.println(Game.mainEnviro.getHealth());
		assertEquals(Game.board[y][x], eChar.RESEARCHER);
		System.out.println(Game.board[y][x]);
		DragComponent character2 = new DragComponent(null, eQuad.N, eChar.STEWARD, XCoord, YCoord,0,0);
		character2.placeInArray(XCoord, YCoord);
		assertEquals(Game.board[y][x], eChar.RESEARCHER);
		assertEquals(Game.board[y-1][x-1], eChar.STEWARD);	
		//game.board[x+2][y] = eChar.BLANK;

	}
}
