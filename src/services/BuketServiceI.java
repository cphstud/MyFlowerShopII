package services;

import domain.Buket;

import java.util.List;

public interface BuketServiceI {
    public List<Buket> getAllFlowersFromDataStore();
    public Buket getBuketById(int id);
}
