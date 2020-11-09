package entry;

import domain.Buket;
import domain.OrderNotFoundException;
import domain.OrderWrongStatusException;
import domain.Ordre;
import services.BuketServiceI;
import services.OrderServiceI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // ha' adgang til nødvendige datastrukturer
    private Scanner sc = new Scanner(System.in);
    private OrderServiceI orderServiceI;
    private BuketServiceI buketServiceI;

    Controller(BuketServiceI buketService, OrderServiceI orderService) {

        //buketter = getAllFlowersFromDataStore();
        this.buketServiceI = buketService;
        this.orderServiceI = orderService;
    }

    public void runProgram() {
        int choice = 0;
        // kører et loop med main actions
        // vis main action - prompt for action
        printMainAction();
        while(choice!=9) {
            choice = sc.nextInt();
            switch(choice) {
                case 1: visBuketter();break;
                case 2: opretOrdre();break;
                //case 3: godkendOrdre();break;
                case 3: retOrdre();break;
                case 4: arkiverOrdre();break;
                case 5: visBestillinger();break;
                case 6: visStatistik();break;
                default:exitProgram();
            }
        }
    }
    private void exitProgram() {
        System.out.println("Farvel og tak");
    }

    private void visStatistik() {
        orderServiceI.visStatistik();
    }

    public void visBestillinger() {
        for (Ordre o: orderServiceI.getBestillinger() ) {
            System.out.println(o.printToCsv());
        }
    }

    private void arkiverOrdre() {
        System.out.println("Hvilken ordre skal arkiveres?");
        int ordreId = sc.nextInt();
        try {
            orderServiceI.arkiverOrdre(ordreId);
        } catch (OrderWrongStatusException | OrderNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Du tastede nok forkert. Prøv igen");
        }
        printMainAction();
    }

    private void retOrdre() {
        System.out.println("Hvilken kundes ordre?");
        int phone = sc.nextInt();
        List<Ordre> ordres = orderServiceI.getOrdersByPhone(phone);
        for (Ordre ordre:ordres ) {
            // TODO: ret evt buketter
        }
    }

    public void opretOrdre() {
        System.out.println("Hvad er dit telefonnummer?");
        int phone = sc.nextInt();
        List<Buket> buketter = new ArrayList<>();
        int buketChoice=0;
        Buket tmpBuket = null;
        int buketNr = 0;
        while(buketNr != 99) {
            System.out.println("Vælg buket");
            buketNr = sc.nextInt();
            if (buketNr != 99) {
                tmpBuket = buketServiceI.getBuketById(buketNr);
                buketter.add(tmpBuket);
            }
        }
        Ordre ordre = new Ordre(phone,buketter);
        System.out.println("Samlet pris:  " + ordre.calcPrice());
        System.out.println("Bekræft ordre:");
        sc.nextLine();
        String conf = sc.nextLine();
        if (conf.toLowerCase().equals("ja")) {
            try {
                orderServiceI.writeOrderToFile(ordre);
            } catch (OrderWrongStatusException e) {
                System.out.println(e.getMessage());
                System.out.println("Din ordre havde forkert status");
            }
        } else {
            ordre.setStatus("CANCELED");
        }
        orderServiceI.addOrderToBestillinger(ordre);
        // loop ind til buketter valgt
        printMainAction();
    }


    public void visBuketter() {
        for (Buket b:buketServiceI.getAllFlowersFromDataStore() ) {
            System.out.println(b.toString());
        }
        printMainAction();
    }

    public void printMainAction() {
        System.out.println("1) vis buketter");
        System.out.println("2) opret ordre ");
        System.out.println("3) ret ordre");
        System.out.println("4) arkiver ordre");
        System.out.println("5) vis alle bestillinger");
        System.out.println("6) vis statistik");
        System.out.println("9) afslut programmet");
    }
}
