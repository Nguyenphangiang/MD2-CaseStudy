package model;

import java.io.Serializable;

public class Rocket implements Serializable {
    private String name;
    private String countryFactory;
    private int price;
    private int quantity;

    public Rocket() {
    }

    public Rocket(int quantity) {
        this.quantity = quantity;
    }

    public Rocket(String name, String countryFactory, int quantity) {
        this.name = name;
        this.countryFactory = countryFactory;
        this.quantity = quantity;
    }

    public Rocket(String name, String countryFactory, int price, int quantity) {
        this.name = name;
        this.countryFactory = countryFactory;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryFactory() {
        return countryFactory;
    }

    public void setCountryFactory(String countryFactory) {
        this.countryFactory = countryFactory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", countryFactory='" + countryFactory + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
