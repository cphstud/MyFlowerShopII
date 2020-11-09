import java.io.*;
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
        while(choice!=9) {
            choice = sc.nextInt();
            switch(choice) {
                case 1: visBuketter();break;
                case 2: opretOrdre();break;
                case 3: retOrdre();break;
                case 4: arkiverOrdre();break;
                case 5: visBestillinger();break;
                case 6: visStatistik();break;
                default:choice=9;exitProgram();
            }
        }
    }

    private void exitProgram() {
        System.out.println("Farvel og tak");
    }

    public void visStatistik() {
        //0;32324512;@3@5@2@10@;1150;DONE
        int[] statArr = new int[buketter.size()];
        String line = "";
        File file = new File("resources/archivedOrders.csv");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(";");
                String buketLine = lineArr[2];
                String[] buketLineArr = buketLine.split("@");
                for(int i=1;i<buketLineArr.length;i++) {
                    statArr[Integer.valueOf(buketLineArr[i])]++;
                }
            }
            br.close();
            fr.close();
            for(int i=0;i<statArr.length;i++) {
                System.out.print("|"+statArr[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ordre.setStatus("DONE");
        writeOrderToFile(ordre);
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
            ordre.setStatus("INPROGRES");
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

    public void writeOrderToFile(Ordre ordre) {
        ////0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE
        if (ordre.getStatus().equals("CREATED")) {
            File file = new File("resources/activeOrders.csv");
            try {
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                ordre.setStatus("INPROGRES");
                bw.write(ordre.printToCsv2());
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //write to active-file
        } else if (ordre.getStatus().equals("INPROGRES")){
            File file = new File("resources/archivedOrders.csv");
            try {
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                ordre.setStatus("DONE");
                bw.write(ordre.printToCsv2());
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ups");
        }
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
