package com.nbc.hotel.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Reservation {
	private UUID uuid;
	private Room room;
	private String customerName;
	private String customerPhoneNumber;
	// 예약한 날짜
	private LocalDate reservationDate;
	// 예약 당일
	private LocalDateTime reservationDay;
	
	public Reservation() {
		
	}
	
	public Reservation(UUID uuid, Room room, String customerName, String customerPhoneNumber, LocalDate reservationDate, LocalDateTime reservationDay) {
		this.uuid = uuid;
		this.room = room;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.reservationDate = reservationDate;
		this.reservationDay = reservationDay;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return this.customerPhoneNumber;
	}
	
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public LocalDate getReservationDate() {
		return this.reservationDate;
	}
	
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public LocalDateTime getReservationDay() {
		return this.reservationDay;
	}
	
	public void setReservationDay(LocalDateTime reservationDay) {
		this.reservationDay = reservationDay;
	}

	public String getReservationInfo () {
		return String.format("예약일: %s %n예약번호: %s %n객실 크기: %.1f %n객실 금액: %.1f %n예약자 명: %s %n예약자 휴대폰번호: %s %n",
				reservationDay.format(DateTimeFormatter.ISO_DATE),
				uuid,
				room.getSize(),
				room.getPrice(),
				customerName,
				customerPhoneNumber);
	}


	public UUID getReservationId () {
		return uuid;
	}

	public void finalReservation(String selectDate, List<Reservation> reservations) {
		System.out.println(getCustomerName()+"님 예약 완료되셨습니다.");
		System.out.println(getCustomerName()+"님 예약 번호는"+getUUID()+"입니다.");
		LocalDate localDate = reservationDate(selectDate);
		setReservationDate(localDate);
		
		LocalDateTime localTime = LocalDateTime.now();
		setReservationDay(localTime);
		reservationListSave(reservations);
		receipt();
		// 예약 완료
	}
	
    public LocalDate reservationDate(String selectDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate ld = LocalDate.parse(selectDate, formatter);

        return ld;
    }
    
    public boolean hasMoney(Customer customer, double selectRoomPrice) {
    	boolean isTrue = true;
    	if(customer.getMoney() < selectRoomPrice) {
    		System.out.println(customer.getName()+"님의 금액이 부족합니다.");
    		isTrue = false;
    		// 고객이 가진 금액이 적음
    	}
    	return isTrue;
    	// 고객이 가진 금액이 더 많거나 같음
    }
    
    public void reservationListSave(List<Reservation> reservations) {
    	reservations.add(this);

    	String reservationList = receipt();
    	System.out.println(reservationList);
    }
    
    public String receipt() {
        StringBuilder receipt = new StringBuilder();
        
        receipt.append("=============receipt=============");
        receipt.append("\n이름 : ").append(getCustomerName());
        receipt.append("\n전화번호 : ").append(getCustomerPhoneNumber());
        receipt.append("\n예약 신청일 : ").append(getReservationDate());
        receipt.append("\n예약 당일 : ").append(getReservationDay());
        receipt.append("\n예약 ID : ").append(getUUID());
        receipt.append("\n가격 : ").append(getRoom().getPrice());
        receipt.append("\n방 사이즈 : ").append(getRoom().getSize());
        receipt.append("\n================================");
        
        return receipt.toString();
    }
}
