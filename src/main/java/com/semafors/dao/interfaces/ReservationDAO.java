package com.semafors.dao.interfaces;

import java.util.List;

import com.semafors.entity.Reservation;

public interface ReservationDAO {
	public void addReservation(Reservation reservation);
	public List<Reservation> getFutureByUser(Long userId);
	public List<Reservation> getExpiredReservations(Long userId);
	public List<Reservation> getAllByPlace(Long placeId);
	public List<Reservation> getAllVissible();
	public void updateVissibilities(List<Reservation> reservations);
	public void deleteReservation(Long reservationId);
}
