package com.semafors.controller;



import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.implementations.ReservationPlacesDAOImpl;
import com.semafors.entity.ReservationPlace;
import com.semafors.service.ReservationPlacesService;
import com.semafors.service.token.TokenService;




@RestController
@RequestMapping("/reservationPlace/")
public class ReservationPlaceController {

	@Autowired TokenService tokenService;
	@Autowired ReservationPlacesService reservationPlacesService;
	
	@PutMapping("add")
	public void addNewReservationPlace(@RequestBody ReservationPlace reservationPlace) {
		reservationPlacesService.addReservationPlace(reservationPlace);
	}
	
	@GetMapping("all/{tokenValue}")
	public ResponseEntity<List<ReservationPlace>> getAll(@PathVariable("tokenValue") UUID tokenValue) throws WrongUserTokenException{
		return new ResponseEntity<>(reservationPlacesService.getAll(tokenValue),HttpStatus.OK);
	}
}
