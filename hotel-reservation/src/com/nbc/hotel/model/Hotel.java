package com.nbc.hotel.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.List;
>>>>>>> 9db973ece80c014ea0287f079bf2516ac581871c
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

<<<<<<< HEAD
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

=======
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
>>>>>>> 9db973ece80c014ea0287f079bf2516ac581871c
}
