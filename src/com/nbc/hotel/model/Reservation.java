package com.nbc.hotel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reservation {
    private UUID uuid;
    private Room room;
    private String customerName;
    private String customerPhoneNumber;
    // 예약한 날짜
    private LocalDate reservationDate;
    // 예약 당일
    private LocalDateTime reservationDay;

    public Reservation(UUID uuid, Room room, String customerName, String customerPhoneNumber, LocalDate reservationDate, LocalDateTime reservationDay) {
        this.uuid = uuid;
        this.room = room;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.reservationDate = reservationDate;
        this.reservationDay = reservationDay;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Room getRoom() {
        return this.room;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCustomerPhoneNumber() {
        return this.customerPhoneNumber;
    }

    public LocalDate getReservationDate() {
        return this.reservationDate;
    }

    public LocalDateTime getReservationDay() {
        return this.reservationDay;
    }

    public String getReservationInfo() {
        return String.format("예약일: %s %n예약번호: %s %n객실 크기: %.1f %n객실 금액: %.1f %n예약자 명: %s %n예약자 휴대폰번호: %s %n",
                reservationDay.format(DateTimeFormatter.ISO_DATE),
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
