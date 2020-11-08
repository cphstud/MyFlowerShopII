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

    public void setStatus(String status) {
        // DOING, DONE
        this.status = status;
    }

    public int calcPrice() {
        int retVal = 0;
        for (Buket b:buketter) {
            retVal += b.getPrice();
        }
        return retVal;
    }
}
