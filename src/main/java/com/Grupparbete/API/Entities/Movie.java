package com.Grupparbete.API.Entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
    @Column(name = "length")
    private int length;
    @Column(name = "agelimit")
    private int agelimit;
    @Column(name = "price")
    private int price;

    public Movie() {
    }

    public Movie(int id, String title, String genre, int length, int agelimit,int price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.agelimit = agelimit;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(int agelimit) {
        this.agelimit = agelimit;
    }

    public Movie(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
