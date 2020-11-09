package services;

import domain.Ordre;

import java.util.List;

public interface OrderServiceI {
    public void writeOrderToFile(Ordre ordre);
    public void addOrderToBestillinger(Ordre ordre);
    public Ordre getOrderById(int ordreid);
    public List<Ordre> getOrdersByPhone(int phone);
    public void arkiverOrdre(int id);
    public List<Ordre> getBestillinger();
    public void visStatistik();
}
