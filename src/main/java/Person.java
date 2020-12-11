import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Person {
    private String id;
    private int X;
    private int Y;
    private boolean isInfected;
    private boolean foll_quarantine; // following quarantine.
    private boolean wearing_mask;
    private boolean foll_socialDistancing; // following social distancing
    private Color color;
    int[] dir;
    Integer index;
    Random rand = new Random();

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

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
        setId(generateID());
        this.X = X;
        this.Y = Y;
        this.isInfected = isInfected;
        this.dir = dir;
        if(isInfected)
            this.color = Color.RED;
        else
            this.color = Color.BLUE;

        setWearing_mask(wear_mask());
        setFoll_quarantine(follow_quarantine());
        setFoll_socialDistancing(follow_social_distancing());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String generateID() {
        int i = 4; String uid = "P-";


        while (i-- > 0) {
            uid += r.nextInt(9);
        }
        return uid;
    }

    public boolean isFoll_quarantine() {
        return foll_quarantine;
    }

    public void setFoll_quarantine(boolean foll_quarantine) {
        this.foll_quarantine = foll_quarantine;
    }

    public boolean isWearing_mask() {
        return wearing_mask;
    }

    public void setWearing_mask(boolean wearing_mask) {
        this.wearing_mask = wearing_mask;
    }

    public boolean isFoll_socialDistancing() {
        return foll_socialDistancing;
    }

    public void setFoll_socialDistancing(boolean foll_socialDistancing) {
        this.foll_socialDistancing = foll_socialDistancing;
    }

    public boolean wear_mask(){
        return rand .nextBoolean();
    }

    public boolean follow_quarantine(){
        return rand.nextBoolean();
    }

    public boolean follow_social_distancing(){
        return rand.nextBoolean();
    }
}
