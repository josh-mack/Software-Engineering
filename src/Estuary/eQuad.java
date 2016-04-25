package Estuary;

public enum eQuad {

	NW(0), NE(1), SE(2), SW(3), MAIN;
	
	private int testVal;

	eQuad(int val){
		this.testVal = val;
	}
	eQuad(){
		testVal = 0;
	}
	
}
