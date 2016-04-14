
public abstract class Species {
	private int amount;
	private int xCoord;
	private int yCoord;
	private int growthRate;
	
	public Species(int amount, int xCoord, int yCoord, int growthRate) {
		this.amount = amount;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.growthRate = growthRate;
	}

	public int getAmount() {
		return amount;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public int getGrowthRate() {
		return growthRate;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public void setGrowthRate(int growthRate) {
		this.growthRate = growthRate;
	}

}
