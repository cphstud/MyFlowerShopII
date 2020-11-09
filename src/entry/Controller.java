package entry;

import domain.Buket;
import domain.Ordre;
import services.BuketServiceI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // ha' adgang til nødvendige datastrukturer
    List<Buket> buketter;
    List<Ordre> bestillinger;
    Scanner sc = new Scanner(System.in);

    Controller(BuketServiceI buketService) {

        //buketter = getAllFlowersFromDataStore();
        this.buketter = buketService.getAllFlowersFromDataStore();
        bestillinger = new ArrayList<>();
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


    }

    public void visBestillinger() {
        for (Ordre o: bestillinger ) {
            System.out.println(o.printToCsv());
        }
    }

    private void arkiverOrdre() {
        System.out.println("Hvilken ordre skal arkiveres?");
        int ordreId = sc.nextInt();
        Ordre ordre = getOrderById(ordreId);
        writeOrderToFile(ordre);
        ordre.setStatus("DONE");
        printMainAction();
    }


    private void retOrdre() {
        System.out.println("Hvilken kundes ordre?");
        int phone = sc.nextInt();
        List<Ordre> ordres = getOrdersByPhone(phone);
        for (Ordre ordre:ordres ) {
            // TODO: ret evt buketter
        }
    }


    private void godkendOrdre() {
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
                tmpBuket = getBuketById(buketNr);
                buketter.add(tmpBuket);
            }
        }
        Ordre ordre = new Ordre(phone,buketter);
        System.out.println("Samlet pris:  " + ordre.calcPrice());
        System.out.println("Bekræft ordre:");
        sc.nextLine();
        String conf = sc.nextLine();
        if (conf.toLowerCase().equals("ja")) {
            writeOrderToFile(ordre);
        } else {
            ordre.setStatus("CANCELED");
        }
        bestillinger.add(ordre);
        // loop ind til buketter valgt
        printMainAction();
    }


    public void visBuketter() {
        for (Buket b:buketter ) {
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

    // Services

    public Buket getBuketById(int buketNr) {
        Buket retVal = null;
        for (Buket b: buketter ) {
            if (b.getId() == buketNr){
                retVal = b;
            }
        }
        return retVal;
    }

    public Ordre getOrderById(int ordreId) {
        Ordre retVal = null;
        for (Ordre o: bestillinger ) {
            if (o.getId() == ordreId){
                retVal = o;
            }
        }
        return retVal;

    }

    private List<Ordre> getOrdersByPhone(int phone) {
        List<Ordre> ordres = new ArrayList<>();
        for (Ordre o: bestillinger ) {
            if (o.getPhone() == phone && o.getStatus().equals("CREATED")) {
                ordres.add(o);
            }
        }
        return ordres;
    }
}
