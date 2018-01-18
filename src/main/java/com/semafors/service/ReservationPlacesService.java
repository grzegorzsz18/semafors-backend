package com.semafors.service;
import java.util.List;
import java.util.UUID;

import com.semafors.Exception.NotPermissionException;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.interfaces.ReservationPlacesDAO;
import com.semafors.entity.ReservationPlace;
import com.semafors.service.token.TokenService;

@Service
public class ReservationPlacesService {
	@Autowired ReservationPlacesDAO r;
	@Autowired TokenService tokenService;
	@Autowired UserDAO userDAO;
	
	public void addReservationPlace(ReservationPlace reservationPlace, UUID tokenValue) throws Exception {
		tokenService.checkToken(tokenValue);
		User user = userDAO.getByToken(tokenValue);
		if(!user.isAdmin()){
			throw new NotPermissionException();
		}
		r.addReservationPlace(reservationPlace);
	}
	
	public List<ReservationPlace> getAll(UUID tokenValue) throws WrongUserTokenException{
		tokenService.checkToken(tokenValue);
		return r.getAllNameAndId();
	}
}
