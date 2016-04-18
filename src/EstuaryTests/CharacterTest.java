package EstuaryTests;

import static org.junit.Assert.*;
import org.junit.Test;
import Estuary.*;

public class CharacterTest {

	@Test
	public void Characters()
	{
		Volunteer volun = new Volunteer(5, 5);
		volun.setxCoord(4);
		volun.setyCoord(4);
		
		Researcher search = new Researcher(6, 6);
		search.setxCoord(3);
		search.setyCoord(3);
		
		Steward steward = new Steward(8,8);
		steward.setxCoord(6);
		steward.setyCoord(6);
		
		assert(volun.getxCoord() == 4 && volun.getyCoord() == 4);
		assert(search.getxCoord() == 3 && search.getyCoord() == 3);
		assert(steward.getxCoord() == 6 && steward.getyCoord() == 6);
	} 

}
