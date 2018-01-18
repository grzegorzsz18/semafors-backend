package com.semafors.controller;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.entity.ReservationPlace;
import com.semafors.service.ReservationPlacesService;
import com.semafors.service.token.TokenService;




@RestController
@RequestMapping("/reservationPlace/")
public class ReservationPlaceController {

	@Autowired TokenService tokenService;
	@Autowired ReservationPlacesService reservationPlacesService;

	@CrossOrigin
	@PutMapping("add/{tokenValue}")
	public void addNewReservationPlace(@RequestBody ReservationPlace reservationPlace, @PathVariable("tokenValue") UUID tokenValue) {
		reservationPlacesService.addReservationPlace(reservationPlace, tokenValue);
	}
	
	@GetMapping("all/{tokenValue}")
	public ResponseEntity<List<ReservationPlace>> getAll(@PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException{
		return new ResponseEntity<>(reservationPlacesService.getAll(tokenValue),HttpStatus.OK);
	}
}
