package services;

import domain.Buket;
import domain.Ordre;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceDSFileTest {
    OrderServiceDSFile os;
    Ordre ordre;

    @Before
    public void setUp() throws Exception {
        os = new OrderServiceDSFile();
        List<Buket> buketter = new ArrayList<>();
        Buket buket1 = new Buket(3,"Mix bundt med 7 stilke pastel hortensia",275);
        Buket buket2 = new Buket(5,"Queen blomsterbuket",275);
        buketter.add(buket1);
        buketter.add(buket2);
        int phone = 32324512;
        ordre = new Ordre(phone,buketter);

    }

    @Test
    public void addOrderToBestillinger() {
        os.addOrderToBestillinger(ordre);
        int expecteed = 1;
        int actual = os.getBestillinger().size();
        assertEquals(expecteed,actual);
    }

    @Test
    public void getBestillinger() {
    }

    @Test
    public void writeOrderToFile() {
    }

    @Test
    public void getOrderById() {
    }

    @Test
    public void arkiverOrdre() {
    }

    @Test
    public void getOrdersByPhone() {
    }

    @Test
    public void visStatistik() {
    }
}