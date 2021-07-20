package by.voluevich.entity;

import java.util.Objects;

public class Address {
    private int id;
    private String street;
    private String home;

    public Address(int id, String street, String home) {
        this.id = id;
        this.street = street;
        this.home = home;
    }

    public Address(String street, String home) {
        this.street = street;
        this.home = home;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address - " + street + ", " + home;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(street, address.street) && Objects.equals(home, address.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, home);
    }
}
