package com.semafors.dao.interfaces;

import java.util.List;
import java.util.UUID;

import com.semafors.entity.User;

public interface UserDAO {
	void addUser(User user);
	User getByToken(UUID tokenValue);
	User getUserByLogin(String login);
	void updateUser(User user);
	List<User> getAllUser();
}
