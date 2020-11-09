package services;

import domain.Ordre;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceDSFile implements OrderServiceI{

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
