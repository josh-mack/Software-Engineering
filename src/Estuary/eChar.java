package Estuary;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * An enum class to handle all the characters/species on the game board.
 */

public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB"), BLAZINGSTAR("BLAZ"), BLACKEYEDSUSAN("BEYS"), BLANK("NONE"), BCRAB("BCRB"),
	DNREC("DNREC"),FISHERMAN("FMAN"), HCRAB("HCRB"), MCRAB("MCRB"), PHRAG("PHRG"),POLLUTER("POLL"),
	RESEARCHER("(RH)"), STEWARD("(ST)"), VOLUNTEER("(VL)"), ZEBRA("ZEBR");
	
	private String testVal;
	private int xLoc;
	private int yLoc;

	/**
	 * Getters and setters.
	 * Setters set the X and Y locations of the eChar's on the game board.
	 * @param x
	 * @param y
	 */
	
	public void setXLoc(int x)
	{
		this.xLoc = x;
	}
	
	public void setYLoc(int y)
	{
		this.yLoc = y;
	}
	
	public int getXLoc()
	{
		return this.xLoc;
	}
	
	public int getYLoc()
	{
		return this.yLoc;
	}
	
	/**
	 * Constructor for the eChar, initializing which eChar is being used
	 * to the private String.
	 * @param val
	 */
	
	eChar(String val){
		this.testVal = val;
	}
	
	/**
	 * Default Constructor. Sets the eChar's String value to "None"
	 * such that there is no interactable eChar on the board.
	 */
	
	eChar(){
		testVal = "None";
	}
	@Override
	public String toString(){
		return testVal;
	}
}
