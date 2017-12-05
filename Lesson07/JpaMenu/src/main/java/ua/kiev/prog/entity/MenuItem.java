package ua.kiev.prog.entity;

import javax.persistence.*;


@Entity
@Table(name = "menu")
public class MenuItem {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer weight;

    @Column(name = "discount", nullable = false)
    private Boolean hasDiscount;


    public MenuItem() {}

    public MenuItem(String name, double price, int weight, boolean hasDiscount) {

        this.name = name;
        this.price = price;
        this.weight = weight;
        this.hasDiscount = hasDiscount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", hasDiscount=" + hasDiscount +
                '}';
    }
}
