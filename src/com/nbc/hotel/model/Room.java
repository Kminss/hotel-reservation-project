package com.nbc.hotel.model;

import java.util.List;

public class Room {
    Double size;
    Double price;
    List<Reservation> reservations;

    public Room(Double size, Double price) {
        this.size = size;
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public String getRoomInfo() {
        return String.format("객실 크기: %.1f %n 객실 금액: %.1f %n", size, price);
    }
}
