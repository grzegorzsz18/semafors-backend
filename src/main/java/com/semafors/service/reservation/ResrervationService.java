package com.semafors.service.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.Reservation;
import com.semafors.entity.User;
import com.semafors.service.UserService;
import com.semafors.service.token.TokenService;

@Service
@Transactional
public class ResrervationService {
	
	@Autowired ReservationDAO reservationDAO;
	@Autowired ReservationScheduler reservationScheduler;
	@Autowired TokenService tokenService;
	@Autowired UserDAO userDAO;
	
	public synchronized void addReservation(Reservation reservation, UUID tokenValue) throws WrongUserTokenException {
		tokenService.checkToken(tokenValue);
		reservation.setUser(userDAO.getByToken(tokenValue));
		List<Reservation> reservations = reservationDAO.getAllByPlace(reservation.getReservationPlace().getId());
		reservationDAO.addReservation(reservationScheduler.addNewReservation(reservations, reservation));
	}
	
	public Long getAvaliableStartTime(Long reservationPlaceId, Long duration, UUID tokenValue) throws Exception{
		tokenService.checkToken(tokenValue);
		List<Reservation> reservations = reservationDAO.getAllByPlace(reservationPlaceId);
		Reservation reservation= new Reservation();
		reservation.setDuring(duration);
		return reservationScheduler.getAvaliableStartTime(reservations, reservation) - System.currentTimeMillis();
	}
	
	public List<Reservation> getFutureByUser(UUID tokenValue) throws WrongUserTokenException{
		tokenService.checkToken(tokenValue);
		User user = userDAO.getByToken(tokenValue);
		return reservationDAO.getFutureByUser(user.getId());
	}
	
	public List<Reservation> getExpiredByUser(UUID tokenValue) throws WrongUserTokenException{
		tokenService.checkToken(tokenValue);
		User user = userDAO.getByToken(tokenValue);
		return reservationDAO.getExpiredReservations(user.getId());
	}
	
	public List<Reservation> getAllByPlace(Long placeId){
		return reservationDAO.getAllByPlace(placeId);
	}
	
	public void removeReservation(Long id, UUID tokenValue) throws WrongUserTokenException {
		tokenService.checkToken(tokenValue);
		reservationDAO.deleteReservation(id);
	}
}
