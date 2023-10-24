package com.nbc.hotel.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reservation {
    private UUID uuid;
    private Room room;
    private String customerName;
    private String customerPhoneNumber;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    private LocalDateTime reservationDate;

    public String getReservationInfo() {
        return String.format("예약일: %s %n예약번호: %s %n객실 크기: %.1f %n객실 금액: %.1f %n예약자 명: %s %n예약자 휴대폰번호: %s %n",
                reservationDate.format(DateTimeFormatter.ISO_DATE),
                uuid,
                room.getSize(),
                room.getPrice(),
                customerName,
                customerPhoneNumber);

    }

    public UUID getReservationId() {
        return uuid;
    }
}
