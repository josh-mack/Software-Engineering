package Estuary;

public enum eQuad {

	NE("NE"), NW("NW"), SE("SE"), SW("SE");
	
	private String testVal;

	eQuad(String val){
		this.testVal = val;
	}
	eQuad(){
		testVal = "None";
	}
	@Override
	public String toString(){
		return testVal;
	}
}
