import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // ha' adgang til nødvendige datastrukturer
    List<Buket> buketter;
    List<Ordre> bestillinger;
    Scanner sc = new Scanner(System.in);

    Controller() {
        buketter = getAllFlowersFromDataStore();
        bestillinger = new ArrayList<>();
    }


    public void runProgram() {
        int choice = 0;
        // kører et loop med main actions
        // vis main action - prompt for action
        printMainAction();
        choice = sc.nextInt();
        switch(choice) {
            case 1: visBuketter();break;
            case 2: opretOrdre();break;
            case 3: godkendOrdre();break;
            case 4: retOrdre();break;
            case 5: arkiverOrdre();break;
            case 6: visBestillinger();break;
            case 7: visStatistik();break;
            default:exitProgram();
        }
    }

    private void exitProgram() {
    }

    private void visStatistik() {
    }

    private void visBestillinger() {
    }

    private void arkiverOrdre() {
    }

    private void retOrdre() {
    }

    private void godkendOrdre() {
    }

    public void opretOrdre() {
        System.out.println("Hvad er dit telefonnummer?");
        int phone = sc.nextInt();
        List<Buket> buketter = new ArrayList<>();
        int buketChoice=0;
        Buket tmpBuket = null;
        while(buketChoice != 99) {
            System.out.println("Vælg buket");
            int buketNr = sc.nextInt();
            tmpBuket = getBuketById(buketNr);
            buketter.add(tmpBuket);
        }
        Ordre ordre = new Ordre(phone,buketter);
        System.out.println("Samlet pris:  " + ordre.calcPrice());
        System.out.println("Bekræft ordre:");
        String conf = sc.nextLine();
        if (conf.toLowerCase().equals("ja")) {
            ordre.setStatus("DOING");
        } else {
            ordre.setStatus("CANCELED");
        }
        bestillinger.add(ordre);
        // loop ind til buketter valgt
    }


    public void visBuketter() {
        for (Buket b:buketter ) {
            System.out.println(b.toString());
        }
    }

    public void printMainAction() {
        System.out.println("1) vis buketter");
        System.out.println("2) opret ordre ");
        System.out.println("3) godkend ordre");
        System.out.println("4) ret ordre");
        System.out.println("5) arkiver ordre");
        System.out.println("6) vis alle bestillinger");
        System.out.println("7) vis statistik");
        System.out.println("9) afslut programmet");
    }

    // Services

    public List<Buket> getAllFlowersFromDataStore() {
        List<Buket> bukets = new ArrayList<>();
        String line = "";
        Buket buket = null;
        try {

            File file = new File("resources/fc");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // fyld i fra filen
            // 5,Arranger selv bundt,225,123
            while((line = br.readLine())!= null) {
               String[] lineArr = line.split(",");
               buket = new Buket(Integer.valueOf(lineArr[0]),lineArr[1],Integer.valueOf(lineArr[2]));
               bukets.add(buket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bukets;
    }

    public Buket getBuketById(int buketNr) {
        Buket retVal = null;
        for (Buket b: buketter ) {
            if (b.getId() == buketNr){
                retVal = b;
            }
        }
        return retVal;
    }
}
