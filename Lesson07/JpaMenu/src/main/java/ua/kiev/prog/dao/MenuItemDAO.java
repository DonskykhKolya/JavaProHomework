package ua.kiev.prog.dao;

import ua.kiev.prog.entity.MenuItem;

import java.util.List;


public interface MenuItemDAO {

    boolean add(MenuItem newItem);
    List<MenuItem> getByPrice(double min, double max);
    List<MenuItem> getWithDiscount();
    List<MenuItem> getByWeight(int maxWeight);
    void close();
}
