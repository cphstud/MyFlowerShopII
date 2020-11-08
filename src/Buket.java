//6,Multifarvet blomsterbuket,250
public class Buket {
    private int id;
    private String name;
    private int price;

    public Buket(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "nr.:" + id + ", name='" + name + ", price=" + price;
    }
}
