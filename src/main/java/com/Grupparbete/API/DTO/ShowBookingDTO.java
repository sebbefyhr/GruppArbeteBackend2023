package com.Grupparbete.API.DTO;

import com.Grupparbete.API.Entities.Customer;


import java.util.Date;
import java.util.List;

public class ShowBookingDTO {
    private int id;
    private Customer customer;
    private int roomId;
    private String roomName;
    private String roomDescription;
    private int guestCount;
    private List<OrderItemDTO> orderedDishes;
    private double totalPriceSEK;
    private double totalPriceYEN;
    private Date bookingDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public List<OrderItemDTO> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderItemDTO> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }

    public double getTotalPriceYEN() {
        return totalPriceYEN;
    }

    public void setTotalPriceYEN(double totalPriceYEN) {
        this.totalPriceYEN = totalPriceYEN;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
