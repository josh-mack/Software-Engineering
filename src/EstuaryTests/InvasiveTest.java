package EstuaryTests;

import org.junit.Test;
import Estuary.*;
import Model.eChar;

public class InvasiveTest {

	@Test
	public void InvasiveAnimals() {
		Invasive crab1 = new Invasive(eChar.MCRAB, 5, 10, 10, 1, 150);
		crab1.setAmount(3);
		crab1.setXCoord(15);
		assert(crab1.getAmount() == 3);
		assert(crab1.getXCoord() == 15);
		
//		ZebraMussel mussel =new ZebraMussel(2, 10, 3, 2, 60);
//		mussel.setEcoReward(50);
//		mussel.setYCoord(5);
//		mussel.setGrowthRate(15);
//		
//		assert(mussel.getEcoReward() == 50 && mussel.getGrowthRate() == 15);
//		assert(mussel.getYCoord() == 5);
	}
	
//	@Test
//	public void InvasivePlants()
//	{
//		Bamboo bamboo = new Bamboo(10,10,10,11,10);
//		
//		Phragmites phrag = new Phragmites(4, 2, 2, 10, 50);
//		phrag.setAmount(10);
//		phrag.setGrowthRate(30);
//		phrag.setXCoord(4);
//		phrag.setXCoord(4);
//		phrag.setEcoReward(30);
//		
//		assert(phrag.getAmount() == 10);
//		assert(phrag.getEcoReward() == 30);
//		assert(phrag.getGrowthRate() == 30);
//		
//		assert(bamboo.getXCoord() == 10);
//		assert(bamboo.getYCoord() == 10);
//	}
//	
//	@Test
//	public void InvasiveMisc()
//	{
//		Pollution poll = new Pollution(10, 7, 7, 3, 40);
//		
//		assert(poll.getEcoReward() == 40);
//		poll.setAmount(5);
//		assert(poll.getAmount() == 5);
//		
//	}

}
