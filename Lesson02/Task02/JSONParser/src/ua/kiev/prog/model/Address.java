package ua.kiev.prog.model;


public class Address {

    private String country;
    private String city;
    private String street;

    @Override
    public String toString() {
        return "[country: " + this.country + ", city: " + this.city + ", street: " + this.street + " ]";
    }
}
