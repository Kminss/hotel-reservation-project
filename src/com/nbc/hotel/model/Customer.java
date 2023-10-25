package com.nbc.hotel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer extends User{

    private String name;
    private String phoneNumber;
    private Double money;
    private List<UUID> reservationIds;

    public Customer(String name, String phoneNumber, Double money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.reservationIds = new ArrayList<>();
    }
    public Customer(String name, String phoneNumber, Double money, List<UUID> reservationIds) {
        this(name, phoneNumber, money);
        this.reservationIds = reservationIds;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getMoney() {
        return money;
    }

    public List<UUID> getReservationIds() {
        return reservationIds;
    }
}
