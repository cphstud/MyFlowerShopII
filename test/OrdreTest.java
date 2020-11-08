import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
//0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE

public class OrdreTest {
    List<Buket> buketter;
    int phone;
    @Before
    public void setup() {
        buketter = new ArrayList<>();
        Buket buket1 = new Buket(3,"Mix bundt med 7 stilke pastel hortensia",275);
        Buket buket2 = new Buket(5,"Queen blomsterbuket",275);
        buketter.add(buket1);
        buketter.add(buket2);
        phone = 32324512;
        // ingredienser til testen
    }

    @Test
    public void createOrderTest() {
        Ordre ordre = new Ordre(buketter,phone);
    }

}