package com.nbc.hotel.model;

import java.util.List;
import java.util.UUID;

public class Customer {

    private String name;
    private String phoneNumber;
    private Double money;
    private List<UUID> reservationIds;


    public Customer(String name, String phoneNumber, Double money, List<UUID> reservationIds) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.reservationIds = reservationIds;
    }
}
