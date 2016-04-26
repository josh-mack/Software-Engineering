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
//		for (int i = -1; i < 2; i++) {
//			for (int j = -1; j < 2; j++) {
//				game.board[x+i][y+j] = eChar.BLANK;
//			}
//		}
		game.initBoard();
//		for (int i = 0; i < 48; i++) {
//			for (int j = 0; j < 76; j++) {
//				System.out.print(game.board[i][j]);
//			}
//			System.out.println();
//		}
		DragComponent character = new DragComponent("imgs/pika.png", eQuad.NW, eChar.RESEARCHER, XCoord, YCoord);
		game.board[y-1][x+1] = eChar.FISHERMAN;
		System.out.println(game.board[y-1][x+1]);
		assertEquals(character.placeInArray(XCoord, YCoord), true);
		System.out.println(game.board[y][x]);
		assertEquals(game.board[y][x], eChar.RESEARCHER);
		System.out.println(game.board[y][x]);
		DragComponent character2 = new DragComponent("imgs/squirt.png", eQuad.NW, eChar.STEWARD, XCoord, YCoord);
		character2.placeInArray(XCoord, YCoord);
		assertEquals(game.board[y][x], eChar.RESEARCHER);
		assertEquals(game.board[y-1][x-1], eChar.STEWARD);	
		//game.board[x+2][y] = eChar.BLANK;
		assertEquals(character.placeInArray(XCoord, YCoord), true);

	}
//	
//	public static void main(String[] args) {
//		Collision_test();
//	}

}
