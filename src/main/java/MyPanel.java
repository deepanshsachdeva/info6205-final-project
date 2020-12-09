import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

@SuppressWarnings("deprecation")
public class MyPanel extends JPanel implements Observer, Constants {
    private int density;
    private int totalPopulation;

    public MyPanel(int density){
        this.density = density;
        this.totalPopulation = getActualPopulation(this.density);
    }
    private Graphics2D g2d;
    Random rand = new Random();
    Boolean isInstanceCreated = false;
    List<Person> personList = new ArrayList<>();
    HashMap<String, Integer> map = new HashMap<>();


    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0 , 1200, 900);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(StartX,StartY, BOX_HEIGHT + 20 , BOX_WIDTH + 20);
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
        for(Person per : personList){
            updateCoordinates(per);
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

        // co-ordinated which needs to null;
        int nextX = X+ dirX * 5, nextY = Y+ dirY * 5;
        boolean isCollide = checkForCollision(nextX, nextY,per);

        if(!isCollide){
            per.setDir(new int[]{dirX, dirY});
            per.setX(X+ dirX * 5);
            per.setY(Y+ dirY * 5);
            updateHashMap( X, Y, per);
        }
        else{
            per = changeDirection(per);
            int[] updatedDir = per.getDir();
            per.setX(X+ (updatedDir[0] * 5));
            per.setY(Y+ (updatedDir[1] * 5));
            updateHashMap( per.getX(), per.getY(), per);
        }
    }

    private void createPersonInstances(){
        if(!isInstanceCreated){

            for(int count = 0; count < totalPopulation; count++){
                int xVal = rand.nextInt(upperBound) , yVal = rand.nextInt(upperBound);

                Person per = new Person((xVal * 5 ) + 400, (yVal * 5) + 100, false, new int[]{rand .nextBoolean() ? -1 : 1,rand .nextBoolean() ? -1 : 1});
                per.setIndex(new Integer(count));

                if(count % 5 == 0)
                    per.setInfected(true);
                else
                    per.setInfected(false);

                boolean result = addToHashMap(xVal, yVal,count );
                personList.add(per);
                drawCircle(per);
            }
            isInstanceCreated = true;
        }
        else{
            for(Person per : personList){
                drawCircle(per);
            }
        }

    }

    private void drawCircle(Person person){
        Shape circle = new Ellipse2D.Double(person.getX(),person.getY(), DIAMETER, DIAMETER);
        if(person.isInfected())
            g2d.setColor(Color.RED);
        else
            g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }

    private boolean addToHashMap(int xVal, int yVal, int perIndex){
        // adding xVal-yVal to map
        String key = getKey(xVal, yVal);
        if(map.containsKey(key)){
            Integer index = map.get(key);

            if(index.intValue() < 0){
                map.put(key, new Integer(perIndex));
                return true;
            }
            else
                return false;
        }
        else{
            map.put(key, new Integer(perIndex));
            return true;
        }
    }

    // check for collision
    private boolean checkForCollision(int X , int Y, Person per){
        String key = getKey(X, Y);
        if(map.containsKey(key)){
            Integer index = map.get(key);
            if(index.intValue() < 0)
                return false; // No collision
            else{
                Person nextPer = personList.get(index.intValue());
                if(per.isInfected() || nextPer.isInfected())
                {
                    per.setInfected(true);
                    nextPer.setInfected(true);
                }
                changeDirection(nextPer);
                return true; // collision detected
            }
        }
        else
            return false;
    }

    private Person changeDirection(Person per){
        int X = per.getX(), Y = getY();
        int newX, newY;

        if(X == 1 && Y == 1){
            newX = -1; newY = 1;
        }else{
            if(X == -1 && X == -1){
                newX = 1; newY = -1;
            }else
            if(X == 1 && Y == -1){
                newX = -1; newY = -1;
            }
            else{
                newX = 1; newY = -1;
            }
        }

        per.setDir(new int[]{newX, newY});
        return per;
    }

    private void updateHashMap(int prevX, int prevY, Person per){
        String preKey = getKey(prevX, prevY);
        map.put(preKey, new Integer(-1));

        String currKey = getKey(per.getX() , per.getY());
        map.put(currKey, new Integer(per.getIndex()));
    }

    private String getKey(int X, int Y){
        return "" + X +"-" +  Y;
    }

    // get population based density
    private int getActualPopulation(int density){
        return density/10;
    }
}




