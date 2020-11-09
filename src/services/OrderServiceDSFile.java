package services;

import domain.Ordre;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceDSFile implements OrderServiceI{
    private List<Ordre> bestillinger;

    public OrderServiceDSFile() {
        bestillinger = new ArrayList<>();
    }

    public void addOrderToBestillinger(Ordre ordre) {
        bestillinger.add(ordre);
    }

    public List<Ordre> getBestillinger() {
        return bestillinger;
    }

    public void writeOrderToFile (Ordre ordre) {
        ////0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE
        if (ordre.getStatus().equals("CREATED")) {
            File file = new File("resources/orders.csv");
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
            File file = new File("resources/orders.csv");
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
    public Ordre getOrderById(int ordreId) {
        Ordre retVal = null;
        for (Ordre o: bestillinger ) {
            if (o.getId() == ordreId){
                retVal = o;
            }
        }
        return retVal;


    }
    public void arkiverOrdre(int id) {
        Ordre order = null;
        order = getOrderById(id);
        this.writeOrderToFile(order);
        order.setStatus("DONE");
    }

    public List<Ordre> getOrdersByPhone(int phone) {
        List<Ordre> ordres = new ArrayList<>();
        for (Ordre o: bestillinger ) {
            if (o.getPhone() == phone && o.getStatus().equals("CREATED")) {
                ordres.add(o);
            }
        }
        return ordres;
    }

    public void visStatistik() {
        //0;32324512;@3@5@2@10@;1150;DONE
        int[] statArr = new int[30];
        String line = "";
        File file = new File("resources/orders.csv");
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

}
