package com.nbc.hotel.model;


import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();                // 현재 방목록
    private List<Reservation> reservations = new ArrayList<>();    // 현재 예약목록
    private Double money;                                        // 총자산

    public Hotel() {
        rooms.add(new Room(1.0, 90.0));
        rooms.add(new Room(1.0, 110.0));
        rooms.add(new Room(1.0, 130.0));
        rooms.add(new Room(1.0, 190.0));

        rooms.add(new Room(2.0, 140.0));
        rooms.add(new Room(2.0, 160.0));
        rooms.add(new Room(2.0, 200.0));
        rooms.add(new Room(2.0, 220.0));
    }

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
                .filter(reservation -> uuids.contains(reservation.getUUID()))
                .toList();
    }


    public List<Room> getRooms() {
        return this.rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // 예약 처리 프로세스
    public void checkCustomerName(String customerName) throws Exception {
        try {
            if (!customerName.matches("^[a-zA-Z가-힣\\s]*$")) {
                throw new IllegalArgumentException("예약자 이름은 특수 문자를 포함해서는 안 됩니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println("예약 페이지로 다시 돌아갑니다.");
            System.out.println("============================");
            throw new Exception();
        }
    }

    public boolean checkPhoneNumber(String customerPhoneNumber) {
        boolean isValid = true;
        try {
            String regex = "^01[0-9]-[0-9]{4}-[0-9]{4}$";
            // 숫자와 하이픈만을 허용하는 정규표현식
            Matcher matcher = Pattern.compile(regex).matcher(customerPhoneNumber);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("올바른 전화번호 형식이 아닙니다. 다시 시도해주세요!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("예외 발생: " + e.getMessage());
            isValid = false;
        }
        return true;
    }

    public boolean checkMoney(Customer customer, double selectRoomPrice) {
        return hasMoney(customer, selectRoomPrice);
    }

    public boolean hasMoney(Customer customer, double selectRoomPrice) {
        boolean isTrue = true;
        if (customer.getMoney() < selectRoomPrice) {
            System.out.println(customer.getName() + "님의 금액이 부족합니다.");
            isTrue = false;
            // 고객이 가진 금액이 적음
        }
        return isTrue;
        // 고객이 가진 금액이 더 많거나 같음
    }
}
