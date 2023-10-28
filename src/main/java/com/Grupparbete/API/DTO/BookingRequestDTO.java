package com.Grupparbete.API.DTO;

import java.util.Date;
import java.util.List;

public class BookingRequestDTO {
    private int customerId;
    private int roomId;
    private int guests;
    private List<Integer> dishIds;
    private List<Integer> quantities;
    private Date bookingDate;

    public BookingRequestDTO() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public List<Integer> getDishIds() {
        return dishIds;
    }

    public void setDishIds(List<Integer> dishIds) {
        this.dishIds = dishIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
