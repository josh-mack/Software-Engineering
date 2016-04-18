package Estuary;
public abstract class Native extends Species {
	private static final long serialVersionUID = 300;
	
	public Native(int amount, int XCoord, int yCoord, int growthRate) {
		super(amount, XCoord, yCoord, growthRate);
	}
}
