import java.awt.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class PersonTest {

    @Test
    void infectedColor1Test(){
        Random r = new Random();
        Person p = new Person(r.nextInt(), r.nextInt(), false, new int[]{r .nextBoolean() ? -1 : 1, r .nextBoolean() ? -1 : 1});
        Assertions.assertEquals(p.getColor(), Color.GREEN);
    }

    @Test
    void InfectedColor2Test(){
        Random r = new Random();
        Person p = new Person(r.nextInt(), r.nextInt(), true, new int[]{r .nextBoolean() ? -1 : 1, r .nextBoolean() ? -1 : 1});
        Assertions.assertEquals(p.getColor(), Color.RED);
    }
}
