package com.nbc.hotel.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {


    private UUID uuid;
    private Room room;
    private String customerName;
    private String customerPhoneNumber;
    private LocalDateTime reservationDate;

    public Reservation(UUID uuid, Room room, String customerName, String customerPhoneNumber, LocalDateTime reservationDate) {
        this.uuid = uuid;
        this.room = room;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.reservationDate = reservationDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }
}
