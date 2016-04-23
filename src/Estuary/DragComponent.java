package Estuary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class DragComponent extends JComponent {
	private volatile int XOnScreen = 0;
	private volatile int YOnScreen = 0;
	private volatile int XCoord = 0;
	private volatile int YCoord = 0;
	
	public DragComponent(String imageName) {
		setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(imageName);
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		//setOpaque(false);
		
		addMouseListener(new MouseListener() {
	
			@Override
			public void mouseClicked(MouseEvent e) { }
	
			@Override
			public void mousePressed(MouseEvent e) {
		        XOnScreen = e.getXOnScreen();
		        YOnScreen = e.getYOnScreen();
		        XCoord = getX();
		        YCoord = getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	
		});
		
	    addMouseMotionListener(new MouseMotionListener() {
	    	
	    	@Override
	    	public void mouseDragged(MouseEvent e) {
	    		setLocation(XCoord + (e.getXOnScreen() - XOnScreen), YCoord + (e.getYOnScreen() - YOnScreen));
	    	}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
	    });
  
	}
}

