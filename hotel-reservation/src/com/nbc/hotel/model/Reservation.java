package com.nbc.hotel.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getReservationInfo () {
        return String.format("예약일: %s %n예약번호: %s %n객실 크기: %.1f %n객실 금액: %.1f %n예약자 명: %s %n예약자 휴대폰번호: %s %n",
                reservationDate.format(DateTimeFormatter.ISO_DATE),
                uuid,
                room.getSize(),
                room.getPrice(),
                customerName,
                customerPhoneNumber);
    }

    public UUID getReservationId () {
        return uuid;
    }
}
