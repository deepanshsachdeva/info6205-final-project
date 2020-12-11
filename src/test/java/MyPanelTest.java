import io.cucumber.messages.internal.com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class MyPanelTest {

    @Test
    public void keyTest(){
        MyPanel panel = new MyPanel(100);
        assertEquals(new String("5-3"), panel.getKey(5,3));
    }

    @Test
    public void populationCountTest(){
        MyPanel panel = new MyPanel(100);

        List<Person> pop1 = panel.generatePopulation(100);
        assertNotNull(pop1);
        assertEquals(100, pop1.size());

        List<Person> pop2 = panel.generatePopulation(500);
        assertNotNull(pop2);
        assertEquals(500, pop2.size());
    }

    @Test
    public void populationTest(){
        MyPanel panel = new MyPanel(100);
        assertEquals(20, panel.getActualPopulation(100));
        assertEquals(50, panel.getActualPopulation(250));
        assertEquals(100, panel.getActualPopulation(500));
    }

    @Test
    public void quarantineCheckTest(){
        MyPanel panel = new MyPanel(100);

        Person p_q = new Person();
        p_q.setInfected(true);
        p_q.setFoll_quarantine(true);
        assertTrue(panel.following_quarantine(p_q));

        Person p_nq = new Person();
        p_nq.setInfected(true);
        p_nq.setFoll_quarantine(false);
        assertFalse(panel.following_quarantine(p_nq));
    }

    @Test
    public void spread1Test(){
        MyPanel panel = new MyPanel(100);
        Person per = new Person();
        Person nextPer = new Person();
        nextPer.setFoll_quarantine(true);
        assertFalse(panel.check_for_spread(per, nextPer));
    }

    @Test
    public void spread2Test(){
        Random r = new Random();

        MyPanel panel = new MyPanel(100);
        Person per = new Person();
        Person nextPer = new Person();

        if(r.nextBoolean())
            per.setWearing_mask(true);
        else
            nextPer.setWearing_mask(true);

        assertFalse(panel.check_for_spread(per, nextPer));
    }

    @Test
    public void spread3Test(){
        Random r = new Random();

        MyPanel panel = new MyPanel(100);
        Person per = new Person();
        Person nextPer = new Person();

        per.setWearing_mask(false);
        nextPer.setWearing_mask(false);

        if(r.nextBoolean())
            per.setInfected(true);
        else
            nextPer.setInfected(true);

        boolean check = panel.check_for_spread(per, nextPer);

        assertTrue(per.isInfected());
        assertTrue(nextPer.isInfected());
        assertTrue(check);
    }

    @Test
    public void collisionTest(){
        Random r = new Random();
        MyPanel panel = new MyPanel(100);
        Person per = new Person();
        assertFalse(panel.checkForCollision(r.nextInt(), r.nextInt(), per));
    }
}
