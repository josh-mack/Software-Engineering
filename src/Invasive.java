
public abstract class Invasive extends Species {
	private int ecoReward;
	
	public Invasive(int amount, int XCoord, int yCoord, int growthRate, int ecoReward) {
		super(amount, XCoord, yCoord, growthRate);
		this.ecoReward = ecoReward;
	}
	
	public int getEcoReward() {
		return this.ecoReward;
	}
	
	public void setEcoReward(int ecoReward) {
		this.ecoReward = ecoReward;
	}
}
