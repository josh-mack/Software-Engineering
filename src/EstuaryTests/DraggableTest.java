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
		int x = (XCoord/(width/76))%38;
		int y = (YCoord/(height/48))%24;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				game.board[x+i][y+j] = eChar.BLANK;
			}
		}
		
		DragComponent character = new DragComponent("imgs/pika.png", eQuad.NW, eChar.RESEARCHER, XCoord, YCoord);
		assertEquals(character.placeInArray(XCoord, YCoord), false);
		assertEquals(game.board[x][y], eChar.RESEARCHER);
		DragComponent character2 = new DragComponent("imgs/squirt.png", eQuad.NW, eChar.STEWARD, XCoord, YCoord);
		character2.placeInArray(XCoord, YCoord);
		assertEquals(game.board[x][y], eChar.RESEARCHER);
		assertEquals(game.board[x-1][y-1], eChar.STEWARD);	
		game.board[x+2][y] = eChar.BLANK;
		assertEquals(character.placeInArray(XCoord, YCoord), true);

	}
//	
//	public static void main(String[] args) {
//		Collision_test();
//	}

}
