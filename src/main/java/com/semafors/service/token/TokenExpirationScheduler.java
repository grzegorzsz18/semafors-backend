package com.semafors.service.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.semafors.configuration.Configuration;
import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.User;

@Component
public class TokenExpirationScheduler {
	
	@Autowired UserDAO userDAO;
	@Autowired TokenService tokenService;
	
	@Scheduled(fixedRate = Configuration.SCHEDULED_EXPIRATION_TIME)
	public void tokenExpirationScheduler() {
		List<User> users = userDAO.getAllUser();
		for(User u : users) {
			if(isExpired(u)) {
				userDAO.updateUser(tokenService.setUserToken(u));
			}
		}
	}
	
	private Boolean isExpired(User user) {
		return (System.currentTimeMillis() - user.getToken().getCreationTime()) > Configuration.EXPIRATION_TOKEN_TIME;
	}
}
