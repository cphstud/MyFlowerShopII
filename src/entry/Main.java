package entry;

import domain.Buket;
import services.BuketServiceDSFile;
import services.BuketServiceI;
import services.OrderServiceDSFile;
import services.OrderServiceI;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BuketServiceI buketService = new BuketServiceDSFile();
        OrderServiceI orderService = new OrderServiceDSFile();

        Controller controller = new Controller(buketService, orderService);
        controller.runProgram();
    }
}
