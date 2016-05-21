package EstuaryTests;

import static org.junit.Assert.*;
import org.junit.Test;
import Estuary.*;
import Model.eChar;

public class eCharTest {

	@Test
	public void eChars() {
		eChar.BAMBOO.setXLoc(10);
		eChar.BAMBOO.setYLoc(10);
		
		assertEquals(eChar.BAMBOO.getXLoc(), 10);
		assertEquals(eChar.BAMBOO.getYLoc(), 10);
	}

}
