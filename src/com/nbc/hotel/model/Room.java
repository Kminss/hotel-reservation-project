package com.nbc.hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Double size;
    private Double price;

    private List<Reservation> reservations;

    public Room(Double size, Double price) {
        this.size = size;
        this.price = price;
        this.reservations = new ArrayList<>();
    }

    public Double getSize() {
        return this.size;
    }

    public Double getPrice() {
        return this.price;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean isReserved(LocalDate localDate) {
        return reservations
                .stream()
                .anyMatch(reservation -> reservation.getReservationDate().isEqual(localDate));
    }
}