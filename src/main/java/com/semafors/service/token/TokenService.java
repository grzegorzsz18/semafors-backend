package com.semafors.service.token;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semafors.Exception.WrongUserOrPasswordException;
import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.Token;
import com.semafors.entity.User;

@Service
public class TokenService {
	
	@Autowired UserDAO userDao;

	private UUID generateToken() {
		return UUID.randomUUID();
	}
	
	public User setUserToken(User user) {
		Token token = new Token();
		token.setCreationTime(System.currentTimeMillis());
		token.setValue(generateToken());
		user.setToken(token);
		return user;
	}
	
	public void loginUser(User user) throws WrongUserOrPasswordException {
		User userDB = userDao.getUserByLogin(user.getLogin());
		if(userDB == null) {
			throw new WrongUserOrPasswordException();
		}
		if(userDB.getPassword().equals(user.getPassword())) {
			return;
		}
		else {
			throw new WrongUserOrPasswordException();
		}
	}
	
	public void checkToken(UUID tokenValue) throws WrongUserTokenException {
		User userDB = userDao.getByToken(tokenValue);
		if(userDB == null ) {
			throw new WrongUserTokenException();
		}
		return;
	}
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User logout(User user) {
		return setUserToken(user);
	}
}
