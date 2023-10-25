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
}
