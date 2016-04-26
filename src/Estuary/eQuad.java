package Estuary;

public enum eQuad {

	N(0), E(1), S(2), W(3), MAIN;
	
	private int testVal;

	eQuad(int val){
		this.testVal = val;
	}
	eQuad(){
		testVal = 0;
	}
	
}
