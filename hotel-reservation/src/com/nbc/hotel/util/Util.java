package com.nbc.hotel.util;

import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.UUID;

public class Util {
    public static UUID converStringToUuid(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException exception) {
            throw new ReservationNotFoundException();
        }
    }
}
