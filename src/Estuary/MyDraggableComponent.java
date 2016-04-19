package Estuary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class MyDraggableComponent
    extends JComponent {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;

  public MyDraggableComponent(String link) {
	  
	  
	  JLabel label;
	  setLayout(new BorderLayout());
      //ImageIcon image = null;
      ImageIcon image = new ImageIcon(link);
     
      label = new JLabel(image);
      label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
      setBounds(0,0,image.getIconWidth(), image.getIconHeight());
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setVerticalAlignment(JLabel.CENTER);
     
      add(label);
	  
	  
	  
    setOpaque(false);
    
    setVisible(true);

    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) { }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) { }

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

    });
       
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        setLocation(myX + deltaX, myY + deltaY);
      }

      @Override
      public void mouseMoved(MouseEvent e) { }

    });
  }

//
//  public static void main(String[] args) {
//    JFrame f = new JFrame("Swing Hello World");
//
//    // by doing this, we prevent Swing from resizing
//    // our nice component
//    f.setLayout(null);
//
//    MyDraggableComponent mc = new MyDraggableComponent("character-color.png");
//    f.add(mc);
//
//    f.setSize(500, 500);
//
//    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    f.setVisible(true);
//  }

}
