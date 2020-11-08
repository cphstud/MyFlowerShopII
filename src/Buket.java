//6,Multifarvet blomsterbuket,250
public class Buket {
    int id;
    String name;
    int price;

    public Buket(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
