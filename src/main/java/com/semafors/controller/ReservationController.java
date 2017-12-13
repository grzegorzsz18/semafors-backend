package com.semafors.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.entity.Reservation;
import com.semafors.entity.User;
import com.semafors.service.reservation.ResrervationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired ResrervationService reservationService;
	
	@GetMapping("/byPlace/{placeId}")
	public List<Reservation> getAllByPlace(@PathVariable("placeId") Long placeId){
		return reservationService.getAllByPlace(placeId);
	}
	
	@GetMapping("/byUser/{tokenValue}")
	public List<Reservation> getFutureByUser(@PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException{
		return reservationService.getFutureByUser(tokenValue);
	}
	
	@GetMapping("/expiredByUser/{tokenValue}")
	public List<Reservation> getExpiredByUser(@PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException{
		return reservationService.getExpiredByUser(tokenValue);
	}
	
	@GetMapping("byPlace/{placeId}/{duration}/{tokenValue}")
	public Long getAvaliableByPlace(@PathVariable("placeId") Long placeId, @PathVariable("duration") 
	Long duration, @PathVariable("tokenValue") UUID tokenValue) throws Exception {
		return reservationService.getAvaliableStartTime(placeId, duration, tokenValue);
	}
	
	@PutMapping("/add/{tokenValue}")
	public void addReservation(@RequestBody Reservation reservation, @PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException{
		reservationService.addReservation(reservation, tokenValue);
	}
	
	@PostMapping("/delete/{id}/{tokenValue}")
	public void deleteReservation(@PathVariable("id") long id, @PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException {
		reservationService.removeReservation(id, tokenValue);
	}
}
