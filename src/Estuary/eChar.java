package Estuary;

public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB"), BLACKEYEDSUSAN("BEYS"), BLANK("NONE"), BCRAB("BCRB"),
	FISHERMAN("FMAN"), HCRAB("HCRB"), MCRAB("MCRB"), PHRAG("PHRG"),
	RESEARCHER("(RH)"), STEWARD("(ST)"), VOLUNTEER("(VL)"), ZEBRA("ZEBR");
	
	private String testVal;
	private int xLoc;
	private int yLoc;

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
	eChar(String val){
		this.testVal = val;
	}
	eChar(){
		testVal = "None";
	}
	@Override
	public String toString(){
		return testVal;
	}
}
