package com.nbc.hotel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Hotel {

    private List<Room> rooms;
    private List<Reservation> reservations;
    private Double money;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        money = 0.0;
    }

    public List<Reservation> findAllReservations() {
        return reservations;
    }

    /**
     * 예약이 존재하면 예약을 돌려주고, 없다면 NoSuchElementException 던진다
     * @param uuid
     * @return
     */
    public Reservation findReservationByUUID(UUID uuid) {
        return reservations.stream()
                .filter(reservation -> reservation.getUuid().compareTo(uuid) == 0)
                .findFirst().orElseThrow();
    }

    /**
     *
     * @param uuids
     * @return
     */
    public List<Reservation> findReservationsByUUIDs(List<UUID> uuids) {
        List<Reservation> findReservations = new ArrayList<>();
        reservations.stream().filter(reservation -> uuids.contains(reservation.getUuid()))
        return findReservations;
    }

}
