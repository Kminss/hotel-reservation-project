package com.nbc.hotel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private String name;
    private String phoneNumber;
    private Double money;
    private List<UUID> reservationIds = new ArrayList<>();
    private final String blacklistPhoneNumber = "010-1133-1122";


    public Customer(String name, String phoneNumber, Double money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public List<UUID> getReservationIds() {
        return reservationIds;
    }

    public void setReservationIds(List<UUID> reservationIds) {
        this.reservationIds = reservationIds;
    }

    public void addReservationId(UUID uuid) {
        this.reservationIds.add(uuid);
    }

    public String getBlacklistPhoneNumber() {
        return this.blacklistPhoneNumber;
    }

}
