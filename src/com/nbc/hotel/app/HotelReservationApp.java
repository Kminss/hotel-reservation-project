package com.nbc.hotel.app;

import com.nbc.hotel.model.*;
import com.nbc.hotel.util.Util;
import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.List;
import java.util.UUID;

public class HotelReservationApp {
    Hotel hotel = new Hotel();


    public boolean start() {
        return false;
    }


    /**
     * 예약 조회 컨트롤 메서드
     */
    public void findReservationProcess() {
        ViewManagement.showFindReservationMenu();
        Integer menuNumber = InputManager.inputMenuNumber(4);

        switch (FindMenu.toMenu(menuNumber)) {
            case FIND_ALL -> {
                findAllReservationsProcess();
            }
            case FIND_OWN -> {
                findOwnReservationsProcess();
            }
            case FIND_UUID -> {
                findOneReservationProcess();
            }
            case QUIT -> {
                findReservationQuitProcess();
            }
        }
    }

    public void findAllReservationsProcess() {
        List<Reservation> allReservations = hotel.findAllReservations();
        ViewManagement.showFindAllReservations(allReservations);
    }

    public void findOwnReservationsProcess() {
        Customer customer = new Customer("이름", "010-1111-2222", 0.0);
        List<Reservation> reservations = hotel.findReservationsByUUIDs(customer.getReservationIds());
        ViewManagement.showFindMyReservations(reservations);
    }

    public void findOneReservationProcess() {
        ViewManagement.showFindReservation(); // 메뉴 출력
        try {
            UUID uuid = InputManager.inputUUID(); // 입력 받음
            Reservation reservation = hotel.findReservationByUUID(uuid); // 찾음
            ViewManagement.showReservations(List.of(reservation)); // 출력
        } catch (ReservationNotFoundException exception) {
            System.out.println("잘못된 UUID 입력입니다.");
        }
    }

    public void findReservationQuitProcess() {
        ViewManagement.showFindReservationClose();
    }


    public void cancelReservationProcess() {
        ViewManagement.showCancelReservation();
        String reservationId = InputManager.handleInput();
        if (reservationId.equals("0")) {
            return; //메인메뉴? 특정 메뉴로 돌아가는 메소드
        }

        try {
            Reservation reservation = hotel.findReservationByUUID(Util.converStringToUuid(reservationId));
            hotel.cancelReservation(reservation);
            ViewManagement.showCancelReservationSuccess(reservation);
        } catch (ReservationNotFoundException exception) {
            ViewManagement.showCancelReservationFailed(exception.getMessage());
        }
    }
}
