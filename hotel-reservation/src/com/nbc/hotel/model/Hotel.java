package com.nbc.hotel.model;

import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations = new ArrayList<>();
    private Double money;
    Boolean cancelReservations;

    public void cancelReservation(Reservation targetReservation) throws ReservationNotFoundException {
        if(!reservations.remove(targetReservation)) {
            throw new ReservationNotFoundException();
        }
    }

    public Reservation findReservationByUUID(UUID reservationId) {
        return reservations.stream().filter(reservation -> reservation.getReservationId().compareTo(reservationId) == 0)
                .findFirst()
                .orElseThrow(ReservationNotFoundException::new);
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
