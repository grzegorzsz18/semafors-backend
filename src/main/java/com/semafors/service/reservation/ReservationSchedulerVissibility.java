package com.semafors.service.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.semafors.configuration.Configuration;
import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.entity.Reservation;

@Component
public class ReservationSchedulerVissibility {

	@Autowired ReservationDAO reservationDAO;
	
	@Scheduled(fixedRate = Configuration.SCHEDULED_TIME)
	public void scheduleReservation() {
		List<Reservation> reservations = reservationDAO.getAllVissible();
		ArrayList<Reservation> reservationsToChange = new ArrayList<>();
		for( Reservation reservation : reservations) {
			if(reservation.getStartTime() > System.currentTimeMillis()) {
				break;
			}
			if(reservation.getEndTime() < System.currentTimeMillis()) {
				reservation.setVissibility(false);
				reservationsToChange.add(reservation);
			}
		}
		reservationDAO.updateVissibilities(reservationsToChange);
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}
