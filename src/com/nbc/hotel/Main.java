package com.nbc.hotel;

import com.nbc.hotel.app.HotelReservationApp;
import com.nbc.hotel.model.Customer;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("홍길동", "010-1234-5678", 123.1);
        HotelReservationApp app = new HotelReservationApp(customer);
        app.mainProcess();
    }

}