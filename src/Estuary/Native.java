package Estuary;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Abstract class for the native species objects.
 */
public abstract class Native extends Species {
	private static final long serialVersionUID = 300;
	
	/**
	 * Constructor for the Native species.
	 * @param type
	 * @param amount
	 * @param XCoord
	 * @param yCoord
	 * @param growthRate
	 */
	public Native(eChar type, int amount, int XCoord, int yCoord, int growthRate) {
		super(type, amount, XCoord, yCoord, growthRate);
	}
}
