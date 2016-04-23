package Estuary;

public enum eQuad {

	NE(0), NW(1), SE(2), SW(3);
	
	private int testVal;

	eQuad(int val){
		this.testVal = val;
	}
	eQuad(){
		testVal = 0;
	}
	
}
