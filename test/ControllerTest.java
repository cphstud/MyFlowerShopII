import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller controller;
    @Before
    public void setup() {
        controller = new Controller();
    }

    @Test
    public void runProgram() {
        controller.runProgram();
    }

    @Test
    public void printMainAction() {
        controller.printMainAction();
        System.out.println("test");
    }
}