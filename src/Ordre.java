import java.time.LocalDateTime;
import java.util.List;

public class Ordre {
    private static int counter = 0;
    int id;
    int phone;
    List<Buket> buketter;
    LocalDateTime ldt;
    String status;

    public Ordre(int phone, List<Buket> buketter) {
        this.phone = phone;
        this.buketter = buketter;
        ldt = LocalDateTime.now();
        this.id = counter;
        counter++;
        this.status = "OPRETTET";
    }

    public int getId() {
        return this.id;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setStatus(String status) {
        // CREATED,INPROGRES,DONE,CANCELED
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
    public String printToCsv2() {
        //0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE
        String retVal = "";
        retVal += id;
        retVal += ";" + phone;
        retVal += ";@";
        for (Buket b : buketter) {
            retVal += "@" + b.getId();
        }
        retVal += ";@";
        retVal += ";" + calcPrice();
        retVal += ";" + status;
        return retVal;
    }

    public String printToCsv() {
        //0;212121;@3,Mix bundt med 7 stilke pastel hortensia,275@5,Arranger selv bundt,22513,Queen blomsterbuket,275@;775;DONE
        String retVal = "";
        retVal += id;
        retVal += ";" + phone;
        retVal += ";@";
        for (Buket b: buketter ) {
            retVal += "@" + b.toString();
        }
        retVal += ";@";
        retVal += ";" + calcPrice();
        retVal += ";" + status;
        return retVal;
    }

    public int calcPrice() {
        int retVal = 0;
        for (Buket b:buketter) {
            retVal += b.getPrice();
        }
        return retVal;
    }
}
