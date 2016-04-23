package Estuary;

public enum eQuad {

	NE(1), NW(2), SE(3), SW(4);
	
	private int testVal;

	eQuad(int val){
		this.testVal = val;
	}
	eQuad(){
		testVal = 0;
	}
	
}
