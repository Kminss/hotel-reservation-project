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
    private static final Scanner sc = new Scanner(System.in);
    
    private Hotel hotel;
 
    private int roomNumber = 1;
    
    public ViewManagement() {
    	
    }
    
    public ViewManagement(Hotel hotel) {
        this.hotel = hotel;  // 생성자를 통해 Hotel 객체를 주입받음
    }
    
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    // role : admin, user처리는 팀원과 상의 후 처리 예정
    public void test() {
    	try {
    		System.out.println("호텔을 예약하시겠습니까? ");
        	System.out.println("1. 예약 2. 취소 3. 조회");
        	int select = sc.nextInt();
        	switch(select) {
        	case 1:
        		// 입력단 예외 상황 해결을 위해 makeReservation()으로 옮김
        		System.out.println("날짜를 입력해주세요 : ");
        		String selectDate = sc.next();
            	makeReservation(selectDate);
            	// 날짜 입력 로직 
            	
            	System.out.println("============================");
            	System.out.println("선택하고 싶은 방을 골라주세요 : ");
            	int selectRoomNumber = sc.nextInt();
            	checkRoom(selectRoomNumber, selectDate);
            	// room 확인 및 예약 진행 로직
            	
            	System.out.println("=====1. 예약하기 2. 취소하기=====");
            	int finalCheck = sc.nextInt();
            	reservationOrCancel(finalCheck);
            	// 해당 사항으로 최종확인 여부를 물음
            	
            	System.out.println("예약자 성함을 알려주세요 : ");
            	String customerName = sc.next();
            	hotel.checkCustomerName(customerName, selectRoomNumber);
            	// 예약자 이름 확인 로직
            	
            	System.out.println("예약자 전화번호를 입력해주세요 : (010-****-****만 가능)");
        		String customerPhoneNumber = sc.next();
            	boolean checkBlackList = hotel.checkBlackListPhoneNumber(customerPhoneNumber);

            	if(checkBlackList) {
            		// 예약자가 블랙리스트인 사용자
            		break;
            	}else {
            		// 예약자가 블랙리스트 아닌 사용자
                	System.out.println(customerName+"님의 소지 금액을 입력해주세요 : ");
            		double customerMoney = sc.nextLong();
            		double selectRoomPrice = hotel.getRooms().get(selectRoomNumber-1).getPrice();
            		hotel.checkMoney(customerMoney, selectRoomPrice, selectDate);
            		// 돈 확인
            	}
            	// 블랙리스트 조회 및 돈 확인 
        		break;
        	case 2:
        		// 예약 조회
        		break;
        	case 3:
        		// 예약 취소
        		break;
        	default :
        		System.out.println("잘못된 입력입니다. 다시 시도해주세요");
        		break;
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
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
    
    public void makeReservation(String selectDate) {
    	try {
            // selectDate의 유효성을 검사합니다.
            if (!isValidDate(selectDate)) {
                // 만약 날짜를 파싱해서 제대로된 yyyy-MM-dd가 들어가지 않을때
                throw new IllegalArgumentException("유효하지 않은 날짜 형식입니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println("=========다시 실행합니다=========");
            test();
        }
    	System.out.println("============================");
    	System.out.println("해당 날짜 : "+ selectDate+" 방 예약");
    	System.out.println("============================");
    	roomNumber = 1;
    	 
    	hotel.getRooms().stream().forEach((room)-> {
    	    int roomSize = room.getSize().intValue();
    	    System.out.println(roomNumber + ". " + "size: " + roomSize + " price: $" + room.getPrice());
    	    roomNumber++;
    	});
    }
    
    private boolean isValidDate(String selectDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 날짜 검사 결과를 저장할 변수입니다.
        dateFormat.setLenient(false);
        // yyyy-MM-dd형식에 맞는지
        try {
            Date parsedDate = dateFormat.parse(selectDate);
            // 입력된 날짜를 LocalDate로 변환
            LocalDate selectedDate = LocalDate.parse(selectDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // 현재 날짜와 비교
            LocalDate currentDate = LocalDate.now();
            // 입력된 날짜가 현재 날짜 이전이면 유효하지 않음
            if (selectedDate.isBefore(currentDate)) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public void checkRoom(int selectRoomNumber, String selectDate) {
        try {
            if (selectRoomNumber < 1 || selectRoomNumber > hotel.getRooms().size()) {
                throw new RoomNumberInvalidException("잘못된 방 번호입니다.");
            }

            Integer selectRoomSize = hotel.getRooms().get(selectRoomNumber - 1).getSize().intValue();
            Double selectRoomPrice = hotel.getRooms().get(selectRoomNumber - 1).getPrice();
            System.out.println("=========" + selectDate + "=========");
            System.out.println(selectRoomNumber + ". size : " + selectRoomSize + " price : $" + selectRoomPrice);
        } catch (RoomNumberInvalidException e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println("예약 페이지로 다시 돌아갑니다.");
            System.out.println("============================");
            test();
        }
    }


    
    public void reservationOrCancel(int finalCheck) {
    	switch(finalCheck) {
		case 1:
			return;
		case 2:
			System.out.println("예약을 취소하셨습니다.");
			System.out.println("예약 페이지로 다시 돌아갑니다.");
            System.out.println("============================");
            test();
			break;
		default : 
			System.out.println("잘못된 입력을 하여 예약 페이지로 다시 돌아갑니다.");
            System.out.println("============================");
            test();
			break;
		}
    }

}	
