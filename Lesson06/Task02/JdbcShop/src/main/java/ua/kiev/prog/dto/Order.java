package ua.kiev.prog.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order {

    private int id;
    private LocalDate date;
    private Customer customer;
    private List<Product> products = new ArrayList<>();

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
