package com.nbc.hotel.model;


import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations = new ArrayList<>();
    private Double money;
    Boolean cancelReservations;

    public void cancelReservation(Reservation targetReservation) throws ReservationNotFoundException {
        if (!reservations.remove(targetReservation)) {
            throw new ReservationNotFoundException();
        }
    }

    /**
     * 예약이 존재하면 예약을 돌려주고, 없다면 ReservationNotFoundException 던진다
     *
     * @param reservationId
     * @return
     */
    public Reservation findReservationByUUID(UUID reservationId) {
        return reservations
                .stream()
                .filter(reservation -> reservation.getReservationId().compareTo(reservationId) == 0)
                .findFirst()
                .orElseThrow(ReservationNotFoundException::new);
    }


    public List<Reservation> findAllReservations() {
        return reservations;
    }

    /**
     * @param uuids
     * @return
     */
    public List<Reservation> findReservationsByUUIDs(List<UUID> uuids) {
        return reservations
                .stream()
                .filter(reservation -> uuids.contains(reservation.getUuid()))
                .toList();
    }
}
