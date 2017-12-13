package com.semafors.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.Reservation;
import com.semafors.entity.Token;
import com.semafors.entity.User;
import com.semafors.service.token.TokenService;

@Service
public class UserService {

	@Autowired UserDAO userDAO;
	@Autowired ReservationDAO reservationDAO;
	@Autowired TokenService tokenService;
	
	
	public void addUser(User user) {
		userDAO.addUser(tokenService.setUserToken(user));
	}
	
	public Token loginUser(User user) throws Exception {
		tokenService.loginUser(user);
		User updatedUser = tokenService.setUserToken(user);
		userDAO.updateUser(updatedUser);
		return updatedUser.getToken();
	}
	
	//TODO add check token
	public void logout(User user) throws Exception {
		User userLogout = tokenService.logout(user);
		userDAO.updateUser(userLogout);
	}
}
