package com.nbc.hotel.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
        super("해당하는 예약을 찾을 수 없습니다.");
    }

    public ReservationNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
