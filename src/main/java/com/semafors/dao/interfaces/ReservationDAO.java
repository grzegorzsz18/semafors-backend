package com.semafors.dao.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.semafors.entity.Reservation;

public interface ReservationDAO {
	void addReservation(Reservation reservation);
	ArrayList<Reservation> getAllFuture();
	List<Reservation> getFutureByUser(Long userId);
	List<Reservation> getExpiredReservations(Long userId);
	List<Reservation> getAllByPlace(Long placeId);
	List<Reservation> getAllVissible();
	void updateVissibilities(List<Reservation> reservations);
	void deleteReservation(Long reservationId);
}
