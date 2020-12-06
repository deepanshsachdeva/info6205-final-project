import java.util.*;
import org.junit.jupiter.api.*;

public class UnitTest {

    @Test
    void demoTest(){
        List<Integer> list = new ArrayList<>();
        list.stream().forEach(x -> {
            System.out.println(x);
        });
        Assertions.assertEquals(1,1);
    }
}
