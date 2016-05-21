package Model;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * An enum class to handle all the characters/species on the game board.
 */

public enum eChar {
	//x24
	
	BAMBOO("BAMB", true, false, false), BLAZINGSTAR("BLAZ", false, false, false), BLACKEYEDSUSAN("BEYS", false, false, false), BLANK("NONE", false, false, false), BCRAB("BCRB", false, false, true),
	DNREC("DNREC", false, false, false),FISHERMAN("FMAN", true, true, true), HCRAB("HCRB", false, true, true), MCRAB("MCRB", true, false, true), PHRAG("PHRG",true, false, false),
	RESEARCHER("(RH)", false, false, false), STEWARD("(ST)",false,false,false), VOLUNTEER("(VL)",false,false, false), ZEBRA("ZEBR",true,true,true),
	CITY("CITY",false,false, false), NOTHING("noth",false,false,false), TRASH("TRASH", false, false,false), WETRESEARCHER("WRH", false, true, false), WETSTEWARD("WST", false, true, false), WETVOLUNTEER("WVL", false, true, false),
	WETHCRAB("WHC", true, true, true), WETMCRAB("WMC", true, true, true), WETBCRAB("WBC", true, true, true), WATER("WAT", false, true, false);
	
	private String testVal;
	private int xLoc;
	private int yLoc;
	private boolean invasive;
	private boolean wet;
	private boolean animal;
	
	public boolean isAnimal() {
		return animal;
	}


	public boolean isWet() {
		return wet;
	}

	public boolean isInvasive() {
		return invasive;
	}


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
	
	eChar(String val, boolean invasive, boolean wet, boolean animal){
		this.testVal = val;
		this.invasive = invasive;
		this.wet = wet;
		this.animal = animal;
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
