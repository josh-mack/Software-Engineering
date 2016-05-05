package EstuaryTests;

import Estuary.*;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Test;

public class DraggableTest {

	@Test
	public void Collision_test() {
		Game game = new Game();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = ((int)screenSize.getHeight());
		int width = (int)screenSize.getWidth();
		int XCoord = (width/76)*6;
		int YCoord = (height/48)*7;
		int x = 6;
		int y = 7;
		game.initBoard();
		
		DragComponent character = new DragComponent("imgs/pika.png", eQuad.N, eChar.RESEARCHER, XCoord, YCoord, x, y);
		game.board[y-1][x+1] = eChar.PHRAG;
		System.out.println(game.board[y-1][x+1]);
		assertEquals(character.placeInArray(XCoord, YCoord), true);
		System.out.println(game.board[y][x]);
		System.out.println(game.mainEnviro.getMoney());
		System.out.println(game.mainEnviro.getHealth());
		assertEquals(game.board[y][x], eChar.RESEARCHER);
		System.out.println(game.board[y][x]);
		DragComponent character2 = new DragComponent("imgs/squirt.png", eQuad.N, eChar.STEWARD, XCoord, YCoord, x, y);
		character2.placeInArray(XCoord, YCoord);
		assertEquals(game.board[y][x], eChar.RESEARCHER);
		assertEquals(game.board[y-1][x-1], eChar.STEWARD);	
		//game.board[x+2][y] = eChar.BLANK;
		assertEquals(character.placeInArray(XCoord, YCoord), true);

	}
}
