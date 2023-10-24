package com.nbc.hotel.app;

import com.nbc.hotel.model.InputManager;
import com.nbc.hotel.util.Util;
import com.nbc.hotel.exception.ReservationNotFoundException;
import com.nbc.hotel.model.Hotel;
import com.nbc.hotel.model.Reservation;
import com.nbc.hotel.model.ViewManagement;

public class HotelReservationApp {
    Hotel hotel = new Hotel();

    public boolean start() {
        return false;
    }


    public void findAllReservationProcess() {
        ViewManagement.showFindReservationMenu();



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
