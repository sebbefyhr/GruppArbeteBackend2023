package com.Grupparbete.API.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street")
    private String street;
    @Column(name = "postalcode")
    private int postalcode;
    @Column(name = "city")
    private String city;

    public Address(String street,int postalCode,String city){
        this.street = street;
        this.postalcode = postalCode;
        this.city = city;
    }
    public Address(){

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

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalCode) {
        this.postalcode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode='" + postalcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

