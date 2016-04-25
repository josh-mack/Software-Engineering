package Estuary;

public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB"), BLACKEYEDSUSAN("BEYS"), BLANK("NONE"), BCRAB("BCRB"),
	FISHERMAN("FMAN"), HCRAB("HCRB"), MCRAB("MCRB"), PHRAG("PHRG"),
	RESEARCHER("(RH)"), STEWARD("(ST)"), VOLUNTEER("(VL)"), ZEBRA("ZEBR");
	
	private String testVal;

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
