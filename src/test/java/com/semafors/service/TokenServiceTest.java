package com.semafors.service;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.semafors.Exception.WrongUserOrPasswordException;
import com.semafors.Exception.WrongUserTokenException;
import com.semafors.dao.implementations.UserDAOImpl;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.User;
import com.semafors.service.token.TokenService;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

	@InjectMocks
	static TokenService tokenService = new TokenService();
	@Mock
	UserDAO userDao = new UserDAOImpl();
	User user;

	@Before
	public void before() {
		user = new User();
		user.setPassword("pass");
		user.setLogin("login");
	}

	@Test
	public void goodPasswordShouldntThrowsException() throws Exception {
		Mockito.when(userDao.getUserByLogin("login")).thenReturn(user);
		tokenService.setUserDao(userDao);
		tokenService.loginUser(user);
	}

	@Test(expected = WrongUserOrPasswordException.class)
	public void badPasswordShouldThrowWUOPException() throws WrongUserOrPasswordException {
		Mockito.when(userDao.getUserByLogin("login")).thenReturn(null);
		tokenService.setUserDao(userDao);
		tokenService.loginUser(user);
	}
	
	@Test
	public void checkTokenShouldntThrowException() throws WrongUserTokenException {
		UUID tokenValue = UUID.randomUUID();
		Mockito.when(userDao.getByToken(tokenValue)).thenReturn(user);
		tokenService.checkToken(tokenValue);
	}
	
	@Test(expected = WrongUserTokenException.class)
	public void checkTokenShouldThrowException() throws WrongUserTokenException {
		UUID tokenValue = UUID.randomUUID();
		Mockito.when(userDao.getByToken(tokenValue)).thenReturn(null);
		tokenService.checkToken(tokenValue);
	}
}
