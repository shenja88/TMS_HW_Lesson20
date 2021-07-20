package by.voluevich.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Telephone> telephones;
    private List<Address> addresses;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        telephones = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        telephones = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public User(String login, String password, List<Telephone> telephones, List<Address> addresses) {
        this.login = login;
        this.password = password;
        this.telephones = telephones;
        this.addresses = addresses;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "\nUser: login - " + login + ", password - " + password + ", telephones: " + telephones + ", addresses: "
                + addresses + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
