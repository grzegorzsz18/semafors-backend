package com.semafors.dao.interfaces;

import java.util.List;

import com.semafors.entity.ReservationPlace;

public interface ReservationPlacesDAO{
	void addReservationPlace(ReservationPlace reservationPlace);
	List<ReservationPlace> getAllNameAndId();
}
