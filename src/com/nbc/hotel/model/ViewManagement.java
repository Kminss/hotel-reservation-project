package com.nbc.hotel.model;


import java.util.List;
import java.util.Scanner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import com.nbc.hotel.exception.RoomNumberInvalidException;

public class ViewManagement {

    public ViewManagement() {

    }

    public static void showReservationOrCancel(int finalCheck) throws Exception {
        switch (finalCheck) {
            case 0:
                return;
            case 1:
                System.out.println("예약을 취소하셨습니다.");
                System.out.println("예약 페이지로 다시 돌아갑니다.");
                System.out.println("============================");
                throw new Exception();
            default:
                System.out.println("잘못된 입력을 하여 예약 페이지로 다시 돌아갑니다.");
                System.out.println("============================");
                throw new Exception();
        }
    }

    public static void showSelectedDateRooms(String selectDate, List<Room> rooms) {
        System.out.println("============================");
        System.out.println("해당 날짜 : " + selectDate + " 방 예약");
        System.out.println("============================");
        int roomNumber = 1;

        for (Room room : rooms) {
            int roomSize = room.getSize().intValue();
            System.out.println(roomNumber + ". " + "size: " + roomSize + " price: $" + room.getPrice());
            roomNumber++;
        }
    }


    public static void showFindReservationMenu() {
        System.out.println();
        System.out.println("객실 예약 조회 화면입니다.");
        System.out.println("1. 모든 예약 조회(관리자 기능)");
        System.out.println("2. 내 예약 목록 조회");
        System.out.println("3. 예약 번호로 조회");
        System.out.println("4. 돌아가기");
        System.out.print("입력: ");
    }

    public static void showFindAllReservations(List<Reservation> reservations) {
        System.out.println("모든 예약을 조회합니다.");
        showReservations(reservations);
    }

    public static void showFindMyReservations(List<Reservation> reservations) {
        System.out.println("내 예약 목록을 조회합니다.");
        showReservations(reservations);
    }

    public static void showFindReservation() {
        System.out.println("예약 번호로 조회합니다.");
        System.out.println("예약 번호를 입력해주세요.");
    }

    public static void showReservations(List<Reservation> reservations) {
        System.out.println("[예약 정보]");
        reservations.forEach(reservation -> {
            System.out.println(reservation.getReservationInfo());
        });
    }

    public static void showFindReservationClose() {
        System.out.println("예약 조회를 종료합니다.");
    }

    public static void showFindReservationFailed() {
        System.out.println("예약 번호로 조회할 수 없습니다.");
    }


    public static void showCancelReservation() {
        System.out.println("객실 예약 취소");
        System.out.println("취소하실 객실의 예약 번호를 입력해주세요.");
        System.out.print("입력 > ");
    }

    public static void showCancelReservationSuccess(Reservation reservation) {
        System.out.println("객실 예약이 취소 되었습니다.");
        System.out.println("[예약 정보]");
        System.out.printf(reservation.getReservationInfo());
    }

    public static void showCancelReservationFailed(String reason) {
        System.out.println("객실 예약 취소가 실패하였습니다.");
        System.out.printf("사유 %s %n", reason);
    }


}	
