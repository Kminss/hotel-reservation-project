package com.nbc.hotel.model;

import java.util.Scanner;

public class ViewManagement {
    private static final Scanner sc = new Scanner(System.in);

    public String showCancelReservation() {
        System.out.println("객실 예약 취소");
        System.out.println("취소하실 객실의 예약 번호를 입력해주세요.");
        System.out.print("입력 > ");
        return sc.nextLine();
    }

    public void showCancelReservationSuccess(Reservation reservation) {
        System.out.println("객실 예약이 취소 되었습니다.");
        System.out.println("[예약 정보]");
        System.out.printf(reservation.getReservationInfo());
    }

    public void showCancelReservationFailed(String reason) {
        System.out.println("객실 예약 취소가 실패하였습니다.");
        System.out.printf("사유 %s %n", reason);
    }
}
