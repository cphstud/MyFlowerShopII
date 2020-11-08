import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller controller;
    @Before
    public void setup() {
        controller = new Controller();
    }

    @Test
    public void printMainAction() {
        controller.printMainAction();
        System.out.println("test");
    }

    @Test
    public void getAllFlowersFromDataStore() {
        List<Buket> bukets = controller.getAllFlowersFromDataStore();
        int expected = 10;
        int actual = bukets.size();
        assertEquals(expected,actual);
    }

    @Test
    public void visBuketter() {
        controller.visBuketter();
        System.out.println("hej");
    }

    @Test
    public void getBuketById() {
        Buket buket = controller.getBuketById(2);
        int expected = 275;
        int actual = buket.getPrice();
        assertEquals(expected,actual);
    }

    @Test
    public void opretOrdre() {
        controller.opretOrdre();
        System.out.println("her");
    }
}