package EstuaryTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Estuary.*;

public class NativeTest {

	@Test
	public void NativeCrabTest() 
	{
		HorseShoeCrab hCrab = new HorseShoeCrab(2, 4, 4, 3);
		
		BlueCrab bCrab = new BlueCrab(2, 10, 10, 10);
		bCrab.setAmount(6);
		bCrab.setXCoord(5);
		assert(bCrab.getXCoord() == 5 && bCrab.getAmount() == 6);
	}
	
	@Test
	public void NativePlantTest()
	{	
		BlackEyedSusan susan = new BlackEyedSusan(4, 7, 7, 40);
		
		BlazingStar bStar = new BlazingStar(5, 4, 7, 20);
		bStar.setGrowthRate(10);
		bStar.setYCoord(8);
		
		assert(bStar.getGrowthRate() == 10);
		assert(bStar.getYCoord() == 8);
	}

}
