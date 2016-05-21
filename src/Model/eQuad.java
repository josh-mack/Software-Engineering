package Model;


/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 */

/**
 * enum class to organize the board's quadrants.
 * Quadrants include North(N), East(E), South(S), West(W),
 * and Main (for when the player is zoomed out.
 */
public enum eQuad {

	N(0), E(1), S(2), W(3), MAIN;
	
	@SuppressWarnings("unused")
	private int testVal;

	eQuad(int val){
		this.testVal = val;
	}
	eQuad(){
		testVal = 0;
	}
	
}
