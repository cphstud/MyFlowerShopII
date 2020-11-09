package services;

import domain.OrderNotFoundException;
import domain.OrderWrongStatusException;
import domain.Ordre;

import java.util.List;

public interface OrderServiceI {
    public void writeOrderToFile(Ordre ordre) throws OrderWrongStatusException;
    public void addOrderToBestillinger(Ordre ordre);
    public Ordre getOrderById(int ordreid) throws OrderNotFoundException;
    public List<Ordre> getOrdersByPhone(int phone);
    public void arkiverOrdre(int id) throws OrderNotFoundException, OrderWrongStatusException;
    public List<Ordre> getBestillinger();
    public void visStatistik();
}
