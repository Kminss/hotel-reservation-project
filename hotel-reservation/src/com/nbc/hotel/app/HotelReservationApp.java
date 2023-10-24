package com.nbc.hotel.app;

import com.nbc.hotel.Util;
import com.nbc.hotel.exception.ReservationNotFoundException;
import com.nbc.hotel.model.Hotel;
import com.nbc.hotel.model.Reservation;
import com.nbc.hotel.model.Room;
import com.nbc.hotel.model.ViewManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelReservationApp {
    ViewManagement viewManagement = new ViewManagement();
    Hotel hotel = new Hotel();

    public boolean start() {
        return false;
    }


    public void cancelReservationProcess() {
        viewManagement.showCancelReservation();
        String reservationId = viewManagement.handleInput();
        if (reservationId.equals("0")) {
            return; //메인메뉴? 특정 메뉴로 돌아가는 메소드
        }

        try {
            Reservation reservation = hotel.findReservationByUUID(Util.converStringToUuid(reservationId));
            hotel.cancelReservation(reservation);
            viewManagement.showCancelReservationSuccess(reservation);
        } catch (ReservationNotFoundException exception) {
            viewManagement.showCancelReservationFailed(exception.getMessage());
        }
    }
}
