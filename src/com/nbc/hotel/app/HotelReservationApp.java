package com.nbc.hotel.app;

import com.nbc.hotel.exception.ReservationNotFoundException;
import com.nbc.hotel.model.*;
import com.nbc.hotel.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.nbc.hotel.model.ViewManagement.showReservationInfo;
import static com.nbc.hotel.model.ViewManagement.showSelectedDateRooms;
import static com.nbc.hotel.util.Util.isValidDate;

public class HotelReservationApp {
    private Hotel hotel;
    private Customer customer;

    public HotelReservationApp(Customer customer) {
        hotel = new Hotel();
        this.customer = customer;
    }

    public void mainProcess() {
        while (true) {
            try {
                System.out.println("호텔 기능을 선택해주세요. ");
                System.out.println("1. 예약 2. 취소 3. 조회");
                int select = InputManager.inputMenuNumber(3);
                switch (select) {
                    case 0:
                        reservationProcess();
                        break;
                    case 1:
                        // 예약 취소
                        cancelReservationProcess();
                        break;
                    case 2:
                        // 예약 조회
                        findReservationProcess();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요");
                        break;
                }
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다. " + e.getMessage());
            }
        }
    }

    private void reservationProcess() throws Exception {
        // 입력단 예외 상황 해결을 위해 makeReservation()으로 옮김
        System.out.println("날짜를 입력해주세요 : ");
        String selectDate = InputManager.handleInput();
        makeReservation(selectDate);

        // 날짜 입력 로직

        System.out.println("============================");
        System.out.println("선택하고 싶은 방을 골라주세요 : ");
        int selectRoomNumber = InputManager.inputMenuNumber(hotel.getRooms().size());
        // room 확인 및 예약 진행 로직

        // 이미 예약된 방인지 검증
        if (isReserved(selectDate, selectRoomNumber))
            throw new Exception("이미 예약된 방입니다.");

        System.out.println("=====1. 예약하기 2. 취소하기=====");
        int finalCheck = InputManager.inputMenuNumber(2);
        ViewManagement.showReservationOrCancel(finalCheck);
        // 해당 사항으로 최종확인 여부를 물음

        System.out.println("예약자 성함을 알려주세요 : ");
        String customerName = InputManager.handleInput();
        hotel.checkCustomerName(customerName, selectRoomNumber);

        // 예약자 이름 확인 로직

        System.out.println("예약자 전화번호를 입력해주세요 : (01*-****-****만 가능)");
        String customerPhoneNumber = InputManager.handleInput();
        boolean isValidPhoneNumber = hotel.checkPhoneNumber(customerPhoneNumber);

        if (!isValidPhoneNumber) {
            throw new Exception("올바르지 않은 전화번호입니다.");
        } else {
            double selectRoomPrice = hotel.getRooms().get(selectRoomNumber).getPrice();
            if (hotel.checkMoney(customer, selectRoomPrice, selectDate)) {
                finalReservation(selectDate, selectRoomNumber,
                        customerName, customerPhoneNumber);
            }

        }
        // 블랙리스트 조회 및 돈 확인
    }

    public void finalReservation(String selectDate, int selectRoomNumber, String customerName,
                                 String customerPhoneNumber) {
        Reservation reservation = new Reservation(UUID.randomUUID(),
                hotel.getRooms().get(selectRoomNumber), customerName,
                customerPhoneNumber, LocalDate.parse(selectDate), LocalDateTime.now());
        hotel.getRooms().get(selectRoomNumber).addReservation(reservation);
        System.out.println(customerName + "님 예약 완료되셨습니다.");
        System.out.println(customerName + "님 예약 번호는" + reservation.getUUID() + "입니다.");

        hotel.getReservations().add(reservation);
        showReservationInfo(reservation);
    }


    private boolean isReserved(String selectDate, int selectRoomNumber) {
        return hotel.getRooms().get(selectRoomNumber).isReserved(LocalDate.parse(selectDate));
    }

    public void makeReservation(String selectDate) throws Exception {
        try {
            // selectDate의 유효성을 검사합니다.
            if (!isValidDate(selectDate)) {
                // 만약 날짜를 파싱해서 제대로된 yyyy-MM-dd가 들어가지 않을때
                throw new IllegalArgumentException("유효하지 않은 날짜 형식입니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println("=========다시 실행합니다=========");
            throw new Exception();
        }
        showSelectedDateRooms(selectDate, hotel.getRooms());
    }

    /**
     * 예약 조회 컨트롤 메서드
     */
    public void findReservationProcess() {
        ViewManagement.showFindReservationMenu();
        Integer menuNumber = InputManager.inputMenuNumber(4);

        switch (FindMenu.toMenu(menuNumber)) {
            case FIND_ALL -> findAllReservationsProcess();
            case FIND_OWN -> findOwnReservationsProcess();
            case FIND_UUID -> findOneReservationProcess();
            case QUIT -> findReservationQuitProcess();
        }
    }

    public void findAllReservationsProcess() {
        List<Reservation> allReservations = hotel.findAllReservations();
        ViewManagement.showFindAllReservations(allReservations);
    }

    public void findOwnReservationsProcess() {
        List<Reservation> reservations = hotel.findReservationsByUUIDs(customer.getReservationIds());
        ViewManagement.showFindMyReservations(reservations);
    }

    public void findOneReservationProcess() {
        ViewManagement.showFindReservation(); // 메뉴 출력
        try {
            UUID uuid = InputManager.inputUUID(); // 입력 받음
            Reservation reservation = hotel.findReservationByUUID(uuid); // 찾음
            ViewManagement.showReservations(List.of(reservation)); // 출력
        } catch (ReservationNotFoundException exception) {
            System.out.println("잘못된 UUID 입력입니다.");
        }
    }

    public void findReservationQuitProcess() {
        ViewManagement.showFindReservationClose();
    }


    public void cancelReservationProcess() {
        ViewManagement.showCancelReservation();
        String reservationId = InputManager.handleInput();

        try {
            Reservation reservation = hotel.findReservationByUUID(Util.converStringToUuid(reservationId));
            hotel.cancelReservation(reservation);
            ViewManagement.showCancelReservationSuccess(reservation);
            mainProcess();
        } catch (ReservationNotFoundException exception) {
            ViewManagement.showCancelReservationFailed(exception.getMessage());
        }
    }
}