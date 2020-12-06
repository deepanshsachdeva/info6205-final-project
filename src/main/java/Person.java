import java.awt.*;
import java.util.Random;

public class Person {
    private String id;
    private int X;
    private int Y;
    private boolean isInfected;
    private Color color;
    int[] dir;

    final Random r = new Random();

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[] getDir() {
        return dir;
    }

    public void setDir(int[] dir) {
        this.dir = dir;
    }

    public Random getR() {
        return r;
    }

    public Person(int X, int Y, boolean isInfected, int[] dir){
        this.id = generateID();
        this.X = X;
        this.Y = Y;
        this.isInfected = isInfected;
        this.dir = dir;
        if(isInfected)
            this.color = Color.RED;
        else
            this.color = Color.BLUE;
    }

    public String generateID() {
        int i = 4; String uid = "P-";


        while (i-- > 0) {
            uid += r.nextInt(9);
        }
        return uid;
    }

}
