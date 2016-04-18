package EstuaryTests;

import static org.junit.Assert.*;

import org.junit.Test;
import Estuary.*;

public class InvasiveTest {

	@Test
	public void test() {
		MittenCrab crab1 = new MittenCrab(5, 10, 10, 1, 150);
		crab1.setAmount(3);
		crab1.setXCoord(15);
		assert(crab1.getAmount() == 3 && crab1.getXCoord() == 15);
	}

}
