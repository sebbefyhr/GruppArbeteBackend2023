package com.Grupparbete.API.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sek_price")
    private double sekPrice;

    @Column(name = "yen_price")
    private int yenPrice;

    public Dishes() {
    }

    public Dishes(String name, String description, double sekPrice, int yenPrice) {
        this.name = name;
        this.description = description;
        this.sekPrice = sekPrice;
        this.yenPrice = yenPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSekPrice() {
        return sekPrice;
    }

    public void setSekPrice(double price) {
        this.sekPrice = price;
    }

    public int getYenPrice() {
        return yenPrice;
    }

    public void setYenPrice(int yenPrice) {
        this.yenPrice = yenPrice;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price SEK=" + sekPrice +
                ", price YEN=" + yenPrice +
                '}';
    }
}
