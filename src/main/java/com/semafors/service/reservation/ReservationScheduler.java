package com.semafors.service.reservation;

import java.util.List;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.entity.Reservation;

@Service
public class ReservationScheduler {

	public Reservation addNewReservation(List<Reservation> reservations, Reservation reservation) {
		reservation.setStartTime(getAvaliableStartTime(reservations, reservation));
		reservation.setEndTime(reservation.getStartTime() + reservation.getDuring());
		return reservation;
	}

	public Long getAvaliableStartTime(List<Reservation> reservations, Reservation reservation) {
		if (reservations.size() == 0) {
			return System.currentTimeMillis();
		}
		if (reservations.size() == 1) {
			return getStartTimeOneReservation(reservations, reservation);
		}
		return getStartTimeMoreThanOneReservation(reservations, reservation);
	}

	private Long getStartTimeOneReservation(List<Reservation> reservations, Reservation reservation) {
		if (reservations.get(0).getStartTime() - System.currentTimeMillis() > reservation.getDuring()) {
			return System.currentTimeMillis();
		}
		if (reservations.get(0).getEndTime() > System.currentTimeMillis()) {
			return reservations.get(0).getEndTime();
		}
		return System.currentTimeMillis();
	}

	private Long getStartTimeMoreThanOneReservation(List<Reservation> reservations, Reservation reservation) {
		if (reservations.get(reservations.size() - 1).getEndTime() < System.currentTimeMillis()) {
			return System.currentTimeMillis();
		}
		for (int i = 0; i < reservations.size() - 1; i++) {
			long compResult = compareTwoNeigbour(reservations.get(i), reservations.get(i + 1), reservation);
			if (compResult > 0) {
				return compResult;
			}
		}
		return reservations.get(reservations.size() - 1).getEndTime();

	}

	private Long compareTwoNeigbour(Reservation left, Reservation right, Reservation toInsert) {
		if (left.getEndTime() > System.currentTimeMillis()) {
			if (right.getStartTime() - left.getEndTime() > toInsert.getDuring()) {
				return left.getEndTime();
			} else {
				long ik = left.getEndTime() - right.getStartTime();
				long chyj = toInsert.getDuring();
				return -1l;
			}
		} else {
			if (right.getStartTime() - System.currentTimeMillis() > toInsert.getDuring()) {
				return System.currentTimeMillis();
			} else {
				return -1l;
			}
		}
	}
}
