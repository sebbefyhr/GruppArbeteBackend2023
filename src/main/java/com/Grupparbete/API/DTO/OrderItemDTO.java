package com.Grupparbete.API.DTO;

public class OrderItemDTO {
    private int dishId;
    private String dishName;
    private int quantity;

    public OrderItemDTO(int dishId, String dishName, int quantity) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
