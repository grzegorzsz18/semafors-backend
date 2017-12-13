package com.semafors.dao.implementations;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.semafors.dao.interfaces.UserDAO;
import com.semafors.entity.Token;
import com.semafors.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
		
	}

	@Override
	public User getByToken(UUID tokenValue) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.token.value = :token")
				.setParameter("token", tokenValue);
		return (User)query.getSingleResult();
	}

	@Override
	public User getUserByLogin(String login) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login")
				.setParameter("login", login);
		return (User)query.getSingleResult();
	}

	@Override
	public void updateUser(User user) {
		User userDB = getUserByLogin(user.getLogin());
		userDB.setToken(user.getToken());
		entityManager.merge(userDB);
		return;
	}

	@Override
	public List<User> getAllUser() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}
	
}
