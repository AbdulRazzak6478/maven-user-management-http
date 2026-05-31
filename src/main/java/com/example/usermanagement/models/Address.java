package com.example.usermanagement.models;

public class Address {
    private String street;
    private String city;
    private String state;
    private int pincode;

    public Address(String city, String state, String street, int pincode) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.pincode = pincode;
    }

    int getPincode() {
        return this.pincode;
    }

    void setPincode(int pincode) {
        this.pincode = pincode;
    }

    String getCity() {
        return this.city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getState() {
        return this.state;
    }

    void setState(String state) {
        this.state = state;
    }

    String getStreet() {
        return this.street;
    }

    void setStreet(String street) {
        this.street = street;
    }
}
