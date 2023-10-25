package com.nbc.hotel.model;

public enum Role {

    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    Role(String role) {
        this.role = role;
    }

    private String role;
}
