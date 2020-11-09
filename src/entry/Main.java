package entry;

import domain.Buket;
import services.BuketServiceDSFile;
import services.BuketServiceI;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BuketServiceI buketService = new BuketServiceDSFile();

        Controller controller = new Controller(buketService);
        controller.runProgram();
    }
}
