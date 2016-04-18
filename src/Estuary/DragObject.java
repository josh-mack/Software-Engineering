package Estuary;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class DragObject extends JPanel {
    public static void main(String[] args){
        runMouseDrag();
    }
    public static void runMouseDrag(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }
 
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new MyDraggableComponent("character-color.png"));
                frame.pack();
                frame.setSize(500,500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    public class DragThisThing extends JLabel{
       
        private JLabel label;
        public DragThisThing(String link){
            setLayout(null);
            ImageIcon image = null;
            image = new ImageIcon(link);
           
            label = new JLabel(image);
            label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
            setBounds(0,0,image.getIconWidth(), image.getIconHeight());
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
           
            add(label);
           
            MouseHandler handler = new MouseHandler();
            label.addMouseListener(handler);
            label.addMouseMotionListener(handler);
           
        }
    }
    public class MouseHandler extends MouseAdapter{
        private boolean active = false;
        private int xChange;
        private int yChange;
       
        @Override
        public void mousePressed(MouseEvent me){
            active = false;
            JLabel label = (JLabel) me.getComponent();
           
            xChange = me.getPoint().x - label.getLocation().x;
            yChange = me.getPoint().y - label.getLocation().y;
           
            label.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
           
        }
        @Override
        public void mouseReleased(MouseEvent me){
            active = false;
            JLabel label = (JLabel) me.getComponent();
            label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
