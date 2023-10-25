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

    public String getReservationInfo () {
        return String.format("예약일: %s %n예약번호: %s %n %s 예약자 명: %s %n예약자 휴대폰번호: %s %n",
                reservationDate.format(DateTimeFormatter.ISO_DATE),
                uuid,
                room.getRoomInfo(),
                customerName,
                customerPhoneNumber);
    }


    public UUID getReservationId () {
        return uuid;
    }
}
