
package EstuaryTests;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import org.junit.*;

import Controller.Game;
import Model.eChar;
import Model.eQuad;
import View.DragComponent;

import static org.junit.Assert.*;

import java.awt.Menu;


public class GameTest {
	//f
	@Test
	public void testGame_1()
		throws Exception {
		Game result = new Game();
		assertNotNull(result);
		// add additional test code here
	}


	@Test
	public void testCheckProgress_1()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	
	@Test
	public void testCheckProgress_2()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}


	@Test
	public void testCheckProgress_3()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}


	@Test
	public void testCheckProgress_4()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	@Test
	public void testCheckProgress_5()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	
	@Test
	public void testCheckProgress_6()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}


	@Test
	public void testCheckProgress_7()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	/**
	 * Run the void checkProgress() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/21/16 2:43 PM
	 */
	@Test
	public void testCheckProgress_8()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}


	@Test
	public void testCheckProgress_9()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	@Test
	public void testCheckProgress_10()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	@Test
	public void testCheckProgress_11()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}
	@Test
	public void testCheckProgress_12()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	
	@Test
	public void testCheckProgress_13()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	@Test
	public void testCheckProgress_14()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}


	@Test
	public void testCheckProgress_15()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}

	
	@Test
	public void testCheckProgress_16()
		throws Exception {

		Game.checkProgress();

		// add additional test code here
	}



	
	@Test
	public void testGetJComponentAt_1()
		throws Exception {
		int i = 1;
		int j = 1;

		JComponent result = Game.getJComponentAt(i, j);

		// add additional test code here
		assertNotNull(result);
		assertEquals(true, result.isOpaque());
		assertEquals(0.5f, result.getAlignmentX(), 1.0f);
		assertEquals(0.5f, result.getAlignmentY(), 1.0f);
		assertEquals(true, result.isDoubleBuffered());
		assertEquals(false, result.isValidateRoot());
		assertEquals(false, result.requestFocusInWindow());
		assertEquals(false, result.getAutoscrolls());
		assertEquals(0, result.getDebugGraphicsOptions());
		assertEquals(false, result.getInheritsPopupMenu());
		assertEquals(null, result.getInputVerifier());
		assertEquals(null, result.getNextFocusableComponent());
		assertEquals(null, result.getTransferHandler());
		assertEquals(true, result.getVerifyInputWhenFocusTarget());
		assertEquals(false, result.isManagingFocus());
		assertEquals(true, result.isOptimizedDrawingEnabled());
		assertEquals(false, result.isPaintingForPrint());
		assertEquals(false, result.isPaintingTile());
		assertEquals(1280, result.getWidth());
		assertEquals(null, result.getToolTipText());
		assertEquals(0, result.getX());
		assertEquals(0, result.getY());
		assertEquals(800, result.getHeight());
		assertEquals(null, result.getBorder());
		assertEquals(true, result.isRequestFocusEnabled());
		assertEquals(false, result.requestDefaultFocus());
		assertEquals(null, result.getComponentPopupMenu());
		assertEquals("PanelUI", result.getUIClassID());
		assertEquals(1, result.countComponents());
		assertEquals(1, result.getComponentCount());
		assertEquals(null, result.getFocusTraversalPolicy());
		assertEquals(false, result.isFocusCycleRoot());
		assertEquals(false, result.isFocusTraversalPolicyProvider());
		assertEquals(false, result.isFocusTraversalPolicySet());
		assertEquals("javax.swing.JPanel[null.contentPane,0,0,1280x800,layout=javax.swing.JRootPane$1,alignmentX=0.0,alignmentY=0.0,border=,flags=297,maximumSize=,minimumSize=,preferredSize=]", result.toString());
		assertEquals("null.contentPane", result.getName());
		assertEquals(true, result.isValid());
		assertEquals(null, result.getDropTarget());
		assertEquals(true, result.getFocusTraversalKeysEnabled());
		assertEquals(false, result.getIgnoreRepaint());
		assertEquals(null, result.getInputMethodRequests());
		assertEquals(null, result.getMousePosition());
		assertEquals(true, result.isBackgroundSet());
		assertEquals(false, result.isCursorSet());
		assertEquals(true, result.isDisplayable());
		assertEquals(false, result.isFocusOwner());
		assertEquals(true, result.isFocusTraversable());
		assertEquals(true, result.isFocusable());
		assertEquals(true, result.isFontSet());
		assertEquals(true, result.isForegroundSet());
		assertEquals(false, result.isMaximumSizeSet());
		assertEquals(false, result.isMinimumSizeSet());
		assertEquals(false, result.isPreferredSizeSet());
		assertEquals(true, result.isEnabled());
		assertEquals(false, result.hasFocus());
		assertEquals(true, result.isVisible());
		assertEquals(true, result.isShowing());
		assertEquals(true, result.isLightweight());
	}

	

	


	@Test
	public void testStartTimers_1()
		throws Exception {

		Game.startTimers();

		// add additional test code here
	}

	

	@Test
	public void testUpdateNatives_1()
		throws Exception {
		int numInvasives = 1;
		int numNatives = 1;

		int result = Game.updateNatives(numInvasives, numNatives);

		// add additional test code here
		assertEquals(0, result);
	}

	@Test
	public void testUpdateNatives_2()
		throws Exception {
		int numInvasives = 1;
		int numNatives = 1;

		int result = Game.updateNatives(numInvasives, numNatives);

		// add additional test code here
		assertEquals(0, result);
	}

	@Test
	public void testUpdateNatives_3()
		throws Exception {
		int numInvasives = 1;
		int numNatives = 1;

		int result = Game.updateNatives(numInvasives, numNatives);

		// add additional test code here
		assertEquals(0, result);
	}


	@Test
	public void testUpdateNatives_4()
		throws Exception {
		int numInvasives = 1;
		int numNatives = 1;

		int result = Game.updateNatives(numInvasives, numNatives);

		// add additional test code here
		assertEquals(0, result);
	}


	@Test
	public void testUpdateNatives_5()
		throws Exception {
		int numInvasives = 1;
		int numNatives = 1;

		int result = Game.updateNatives(numInvasives, numNatives);

		// add additional test code here
		assertEquals(0, result);
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GameTest.class);
	}
}