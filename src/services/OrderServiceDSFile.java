package services;

import domain.OrderNotFoundException;
import domain.OrderWrongStatusException;
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

    public void writeOrderToFile (Ordre ordre) throws OrderWrongStatusException {
        ////0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE
            File file = new File("resources/orders.csv");
            try {
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ordre.printToCsv2());
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //write to active-file
        if (ordre.getStatus().equals("CREATED")) {
            ordre.setStatus("INPROGRES");
        } else if (ordre.getStatus().equals("INPROGRES")) {
            ordre.setStatus("DONE");
        } else {
            throw new OrderWrongStatusException("Order " + ordre.getId() + " has wrong status " + ordre.getStatus());
        }
    }
    public Ordre getOrderById(int ordreId) throws OrderNotFoundException {
        Ordre retVal = null;
        for (Ordre o: bestillinger ) {
            if (o.getId() == ordreId){
                retVal = o;
            }
        }
        if (retVal == null) {
            throw new OrderNotFoundException("order by id " + ordreId + " not found");
        }
        return retVal;


    }
    public void arkiverOrdre(int id) throws OrderNotFoundException, OrderWrongStatusException {
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
