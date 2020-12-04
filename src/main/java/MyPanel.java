import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

@SuppressWarnings("deprecation")
public class MyPanel extends JPanel implements Observer, Constants {

    private Graphics2D g2d;
   // Person per;
    Random rand = new Random();
    Boolean isInstanceCreated = false;
    Person[][] perLocation = new Person[80][80];

    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(StartX,StartY, BOX_HEIGHT, BOX_WIDTH);
        //g2d.fillRect(0, 100 , frameWidth, 300);
        // creating circle
        //g2d.setColor(Color.BLUE);
        //g2d.fillOval(570, 210, DIAMETER, DIAMETER);
        //g2d.fillOval(590, 210, DIAMETER, DIAMETER);
        //g2d.fillOval(610, 210, DIAMETER, DIAMETER);
        //g2d.fillOval(630, 210, DIAMETER, DIAMETER);
        createPersonInstances();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof VirusSimulation) {
            updateLocations();
            repaint();
        }
    }

    public void updateLocations(){
        for(int i=0 ; i < perLocation.length; i++ ){
            for(int j =0; j < perLocation[i].length; j++){
                if(perLocation[i][j] != null)
                    updateCoordinates(perLocation[i][j]);
            }
        }

    }
    private void updateCoordinates(Person per){
        int[] dir = per.getDir();
        int X = per.getX();
        int Y = per.getY();

        // x < x +width , y < y + height
        // x > x , y > y
        int dirX = dir[0];
        int dirY = dir[1];

        if(X <= StartX )
            dirX = 1;
        if(X >= StartX + BOX_WIDTH)
            dirX = -1;

        if(Y <= StartY)
            dirY = 1;
        if(Y > StartY + BOX_HEIGHT)
            dirY = -1;


        per.setDir(new int[]{dirX, dirY});
        per.setX(per.getX() + dirX * 5);
        per.setY(per.getY() + dirY * 5);
    }

    private void createPersonInstances(){
        if(!isInstanceCreated){

            for(int count = 0; count < 100; count++){
                int xVal = rand.nextInt(upperBound) , yVal = rand.nextInt(upperBound);

                Person per = new Person((xVal * 5 )+ 400, (yVal * 5) + 100, false, new int[]{rand .nextBoolean() ? -1 : 1,rand .nextBoolean() ? -1 : 1});
                perLocation[xVal][yVal] = per;
                drawCircle(per);
            }
            isInstanceCreated = true;
        }
        else{
            for(int i=0 ; i < perLocation.length; i++ ){
                for(int j =0; j < perLocation[i].length; j++){
                    if(perLocation[i][j] != null)
                        drawCircle(perLocation[i][j]);
                }
            }
        }

    }

    private void drawCircle(Person person){
        Shape circle = new Ellipse2D.Double(person.getX(),person.getY(), DIAMETER, DIAMETER);
        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }
}




