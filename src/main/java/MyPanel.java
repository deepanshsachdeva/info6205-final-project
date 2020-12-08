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
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0 , 1200, 900);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(StartX,StartY, BOX_HEIGHT + 20 , BOX_WIDTH + 20);

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
            //this.setBackground(Color.BLUE);
            repaint();
        }
    }

    public void updateLocations(){
        for(int i=0 ; i < perLocation.length ; i++ ){
            for(int j =0; j < perLocation[i].length ; j++){
                if(perLocation[i][j] != null)
                    updateCoordinates(perLocation[i][j]);
            }
        }

    }
    private void updateCoordinates(Person per){
        int[] dir = per.getDir();
        int X = per.getX();
        int Y = per.getY();

        int dirX = dir[0];
        int dirY = dir[1];

        if(X <= StartX  )
            dirX = 1;
        if(X > StartX + BOX_WIDTH )
            dirX = -1;

        if(Y <= StartY )
            dirY = 1;
        if(Y > StartY + BOX_HEIGHT )
            dirY = -1;



        //if(perLocation[per.getX() + dirX * 5][per.getY() + dirY * 5] != null)
        // co-ordinated which needs to null;
        int Xnull = (per.getX() - 400)/5;
        int Ynull = (per.getY() - 100) / 5;
        System.out.println("Xnull : " + Xnull + " Ynull: " + Ynull + " dirX : " + dir[0] + " dirY: " + dir[1]);
        //perLocation[Xnull][Ynull] = null;
        per.setX(X+ dirX * 5);
        per.setY(Y+ dirY * 5);

        int xVal = (per.getX() - 400)/5 , yVal = (per.getY() - 100) / 5;
        System.out.println("xVal : " + xVal + " yVal: " + yVal+ " dirX : " + dirX+ " dirY: " + dirX);
        per.setDir(new int[]{dirX, dirY});
        //perLocation[xVal][yVal] = per;
    }

    private void createPersonInstances(){
        if(!isInstanceCreated){

            for(int count = 0; count < 10; count++){
                int xVal = rand.nextInt(upperBound) , yVal = rand.nextInt(upperBound);
//              rand .nextBoolean() ? -1 : 1,rand .nextBoolean() ? -1 : 1
                Person per = new Person((xVal * 5 ) + 400, (yVal * 5) + 100, false, new int[]{rand .nextBoolean() ? -1 : 1,rand .nextBoolean() ? -1 : 1});
                perLocation[xVal][yVal] = per;
                drawCircle(per);
            }
            isInstanceCreated = true;
        }
        else{
            for(int i=0 ; i < perLocation.length  ; i++ ){
                for(int j =0; j < perLocation[i].length ; j++){
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




