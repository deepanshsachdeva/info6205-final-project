import java.awt.*;
import java.util.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void infectedColor1Test(){
        Random r = new Random();
        Person p = new Person(r.nextInt(), r.nextInt(), false, new int[]{r .nextBoolean() ? -1 : 1, r .nextBoolean() ? -1 : 1});
        assertEquals(p.getColor(), Color.BLUE);
//        Assertions.assertEquals(p.getColor(), Color.GREEN);
    }

    @Test
    public void InfectedColor2Test(){
        Random r = new Random();
        Person p = new Person(r.nextInt(), r.nextInt(), true, new int[]{r .nextBoolean() ? -1 : 1, r .nextBoolean() ? -1 : 1});
        assertEquals(p.getColor(), Color.RED);
//        Assertions.assertEquals(p.getColor(), Color.RED);
    }
}
