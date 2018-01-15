package com.semafors.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.semafors.dto.UserReservationAndPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.entity.Reservation;
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

	@CrossOrigin
	@PostMapping("/delete/{id}/{tokenValue}")
	public void deleteReservation(@PathVariable("id") long id, @PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException {
		reservationService.removeReservation(id, tokenValue);
	}

	@CrossOrigin
	@GetMapping("all/{tokenValue}")
	public List<UserReservationAndPlace> getAllReservationForAdmin(@PathVariable("tokenValue") UUID tokenValue) throws Exception{
		return reservationService.getAllFutureForAdmin(tokenValue);
	}
}
