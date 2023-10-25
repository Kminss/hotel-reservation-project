package com.nbc.hotel;

import com.nbc.hotel.app.HotelReservationApp;

public class Main {
    public static void main(String[] args) {
        boolean ended = false;
        HotelReservationApp app = new HotelReservationApp();
        ended = app.start();
        while (!ended) {
            // 프로그램 메인
        }

    }
}