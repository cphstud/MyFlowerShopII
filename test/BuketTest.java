import org.junit.Test;

import static org.junit.Assert.*;

public class BuketTest {
    //6,Multifarvet blomsterbuket,250,400

    @Test
    public void createBuketTest() {
        Buket buket = new Buket(6,"Multifarvet blomsterbuket",250);
        assertNotNull(buket);

    }



}