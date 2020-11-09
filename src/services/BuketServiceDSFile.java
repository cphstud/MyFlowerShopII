package services;

import domain.Buket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuketServiceDSFile implements BuketServiceI{
    List<Buket> buketter;

    public BuketServiceDSFile() {
        buketter = getAllFlowersFromDataStore();
    }

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

}
