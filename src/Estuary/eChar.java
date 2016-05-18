package Estuary;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * An enum class to handle all the characters/species on the game board.
 */

public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB", true, false), BLAZINGSTAR("BLAZ", false, false), BLACKEYEDSUSAN("BEYS", false, false), BLANK("NONE", false, false), BCRAB("BCRB", false, false),
	DNREC("DNREC", false, false),FISHERMAN("FMAN", true, true), HCRAB("HCRB", false, true), MCRAB("MCRB", true, false), PHRAG("PHRG",true, false),POLLUTER("POLL", true,false),
	RESEARCHER("(RH)", false, false), STEWARD("(ST)",false,false), VOLUNTEER("(VL)",false,false), ZEBRA("ZEBR",true,true),
	CITY("CITY",false,false), NOTHING("noth",false,false), TRASH("TRASH", false, false), WETRESEARCHER("WRH", false, true), WETSTEWARD("WST", false, true), WETVOLUNTEER("WVL", false, true),
	NOTHINGSTEWARD("NST", false, false), NOTHINGRESEARCHER("NRH", false, false), NOTHINGVOLUNTEER("NVL", false, false),
	WETHCRAB("WHC", true, true), WETMCRAB("WMC", true, true), WETBCRAB("WBC", true, true), WATER("WAT", false, true);
	
	public boolean isWet() {
		return wet;
	}

	public void setWet(boolean wet) {
		this.wet = wet;
	}

	public boolean isInvasive() {
		return invasive;
	}

	public void setInvasive(boolean invasive) {
		this.invasive = invasive;
	}
	private String testVal;
	private int xLoc;
	private int yLoc;
	private boolean invasive;
	private boolean wet;
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
	
	eChar(String val, boolean invasive, boolean wet){
		this.testVal = val;
		this.invasive = invasive;
		this.wet = wet;
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
