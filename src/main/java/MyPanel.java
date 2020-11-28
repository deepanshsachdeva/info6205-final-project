import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class MyPanel extends JPanel implements Observer {

    private Graphics2D g2d;
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        // creating circle
        g2d.setColor(Color.BLUE);
        g2d.fillOval(570, 210, 10, 10);
        g2d.fillOval(590, 210, 10, 10);
        g2d.fillOval(610, 210, 10, 10);
        g2d.fillOval(630, 210, 10, 10);
    }

    public void update(Observable observable, Object o) {

            repaint();
        }
    }


