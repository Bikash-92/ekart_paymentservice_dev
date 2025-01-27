package com.ekart.dto;

public class ShippingAddress {

    private String name;
    private String city;
    private int zipCode;
    private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", zipCode=" + zipCode +
                ", state='" + state + '\'' +
                '}';
    }
}
