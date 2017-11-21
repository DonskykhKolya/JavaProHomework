package ua.kiev.prog.model;

import java.util.Arrays;


public class User {

    private String name;
    private String surname;
    private String[] phones;
    private String[] sites;
    private Address address;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append("\n");
        sb.append("Surname: ").append(this.surname).append("\n");
        sb.append("Phones: ").append(Arrays.toString(this.phones)).append("\n");
        sb.append("Sites: ").append(Arrays.toString(this.sites)).append("\n");
        sb.append("Address: ").append(this.address).append("\n");
        return sb.toString();
    }
}
