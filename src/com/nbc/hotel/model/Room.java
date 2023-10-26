package com.nbc.hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {
	private Double size;
	private Double price;
	
	private List<Reservation> reservations;
	
	public Room(Double size, Double price) {
		this.size = size;
		this.price = price;
		this.reservations = new ArrayList<>();
	}
	
	public Double getSize() {
		return this.size;
	}
	
	public Double getPrice() {
		return this.price;
	}

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}

	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
	}

	public boolean isReserved(LocalDate localDate) {
		return reservations
				.stream()
				.anyMatch(reservation -> reservation.getReservationDate().isEqual(localDate));
	}
}