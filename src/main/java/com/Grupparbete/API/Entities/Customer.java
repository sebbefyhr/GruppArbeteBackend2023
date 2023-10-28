package com.Grupparbete.API.Entities;

import com.Grupparbete.API.Entities.Address;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    public Customer() {

    }

    public Customer(int customerId, String username, String name, Address address) {
        this.id = customerId;
        this.username = username;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int customerId) {
        this.id = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
