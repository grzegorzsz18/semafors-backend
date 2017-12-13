package com.semafors.dao.interfaces;

import java.util.List;
import java.util.UUID;

import com.semafors.entity.Token;
import com.semafors.entity.User;

public interface UserDAO {
	public void addUser(User user);
	public User getByToken(UUID tokenValue);
	public User getUserByLogin(String login);
	public void updateUser(User user);
	public List<User> getAllUser();
}
