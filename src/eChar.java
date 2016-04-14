
public enum eChar {
	//x13 characters
	
	BAMBOO("BAMB"), BLACKEYEDSUSAN("BEYS"), BLANK("NONE"), BCrab("BCRB"),
	FISHERMAN("FMAN"), HCrab("HCRB"), MCrab("MCRB"), PHRAG("PHRG"),
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
