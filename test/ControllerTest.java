import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void writeOrderToFile() {
        List<Buket> buketter = new ArrayList<>();
        Buket buket1 = new Buket(3,"Mix bundt med 7 stilke pastel hortensia",275);
        Buket buket2 = new Buket(5,"Queen blomsterbuket",275);
        Buket buket3 = new Buket(2,"Max blomsterbuket",375);
        Buket buket4 = new Buket(10,"Trendy efterårsbuket",225);
        buketter.add(buket1);
        buketter.add(buket2);
        int phone = 32324512;
        int phone2 = 52324512;
        Ordre ordre = new Ordre(phone,buketter);
        buketter.add(buket3);
        buketter.add(buket4);
        Ordre ordre2 = new Ordre(phone,buketter);
        ordre.setStatus("CREATED");
        ordre2.setStatus("CREATED");
        controller.writeOrderToFile(ordre);
        controller.writeOrderToFile(ordre2);
        ordre.setStatus("INPROGRES");
        controller.writeOrderToFile(ordre);
    }

    @Test
    public void visStatistik() {
        controller.visStatistik();
    }
}