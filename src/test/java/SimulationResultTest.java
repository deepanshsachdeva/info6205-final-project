import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class SimulationResultTest {

    @Test
    public void getTotalPopulationTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertEquals(100,sm.getTotalPopulation(population));
        assertNotEquals(0, sm.getTotalPopulation(population));
        assertNotEquals(-1, sm.getTotalPopulation(population));
    }

    @Test
    public void getHealthyCountTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        SimulationResult sm = new SimulationResult(population);
        assertNotEquals(-1, sm.getHealthyCount(population));
    }

    @Test
    public void getTotalInfectedTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertNotEquals(-1, sm.getTotalInfected(population));
    }

    @Test
    public void getQuarantineCountTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertTrue(sm.getQuarantineCount(population) >= 0 && sm.getQuarantineCount(population) <= 100);
    }

    @Test
    public void getPopulationWithMaskTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertTrue(sm.getPopulationWithMask(population) >= 0 && sm.getPopulationWithMask(population) <= 100);
    }

    @Test
    public void getPopulationFoll_SocialDistancingTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertTrue(sm.getPopulationFoll_SocialDistancing(population) >= 0 && sm.getPopulationFoll_SocialDistancing(population) <= 100);
    }

    @Test
    public void getInfectedQuarantineTest(){
        MyPanel m = new MyPanel(100);
        List<Person> population = m.generatePopulation(100);
        assertNotNull(population);
        SimulationResult sm = new SimulationResult(population);
        assertNotNull(sm);
        assertTrue(sm.getInfectedQuarantine(population) >= 0 && sm.getInfectedQuarantine(population) <= 100);
    }
}
