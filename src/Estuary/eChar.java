package Estuary;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * An enum class to handle all the characters/species on the game board.
 */

public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB", true), BLAZINGSTAR("BLAZ", false), BLACKEYEDSUSAN("BEYS", false), BLANK("NONE", false), BCRAB("BCRB", false),
	DNREC("DNREC", false),FISHERMAN("FMAN", true), HCRAB("HCRB", false), MCRAB("MCRB", true), PHRAG("PHRG",true),POLLUTER("POLL", true),
	RESEARCHER("(RH)", false), STEWARD("(ST)",false), VOLUNTEER("(VL)",false), ZEBRA("ZEBR",true), SLOWGROWTH("SLOW",false), FASTCHARACTER("FAST",false), INSTAKILL("INST",false),
	CITY("CITY",false), NOTHING("noth",false);
	
	private String testVal;
	private int xLoc;
	private int yLoc;
	private boolean invasive;
	/**
	 * Getters and setters.
	 * Setters set the X and Y locations of the eChar's on the game board.
	 * @param x
	 * @param y
	 */
	public boolean checkInvasive(){
		return this.invasive;
	}
	
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
	
	eChar(String val, boolean invasive){
		this.testVal = val;
		this.invasive = invasive;
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
