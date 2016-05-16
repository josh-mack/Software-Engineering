package Estuary;

/**
 * @author Josh Mack, Bill Bartlett, Peter Grillo, Dan Liang and Marco Arcilla
 * @version 1.0
 * @since
 * Class to handle the invasive species.
 */
public class Invasive extends Species {
	private static final long serialVersionUID = 200;
	private int ecoReward;
	private boolean resolved; 
	
	/**
	 * Constructor for the Invasive class.
	 * @param type
	 * @param amount
	 * @param XCoord
	 * @param yCoord
	 * @param growthRate
	 * @param ecoReward
	 */
	public Invasive(eChar type, int amount, int XCoord, int yCoord, int growthRate, int ecoReward) {
		super(type, amount, XCoord, yCoord, growthRate);
		this.ecoReward = ecoReward;
	}

	public int getEcoReward() {
		return this.ecoReward;
	}
	
	public void setEcoReward(int ecoReward) {
		this.ecoReward = ecoReward;
	}
	public boolean getResolved(){
		return this.resolved;
	}
	public void setResolved(boolean x){
		this.resolved = x;
	}
}
