package com.semafors.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.semafors.dao.interfaces.ReservationDAO;
import com.semafors.entity.Reservation;

@Repository
@Transactional
public class ReservationDAOImpl implements ReservationDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addReservation(Reservation reservation) {
		entityManager.persist(reservation);
	}

	@Override
	public ArrayList<Reservation> getAllFuture() {
		Query query = entityManager.createQuery("SELECT r from Reservation r WHERE r.vissibility = TRUE ORDER BY r.startTime", Reservation.class);
		return (ArrayList<Reservation>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getFutureByUser(Long userId) {
		Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.user.id = :user_id and r.vissibility = True ORDER BY r.startTime",Reservation.class)
				.setParameter("user_id", userId);
		return (List<Reservation>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getAllByPlace(Long placeId) {
		Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.reservationPlace.id = :placeId and r.vissibility = True ORDER BY r.startTime", Reservation.class)
				.setParameter("placeId",placeId);
		return (List<Reservation>)query.getResultList();
	}

	@Override
	public List<Reservation> getAllVissible() {
		Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.vissibility = True ORDER BY r.startTime", Reservation.class);
		return (List<Reservation>)query.getResultList();
	}

	@Override
	public void updateVissibilities(List<Reservation> reservations) {
		for(Reservation r :reservations) {
			entityManager.merge(r);
		}
	}

	@Override
	public void deleteReservation(Long reservationId) {
		Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.id = :reservationId")
				.setParameter("reservationId", reservationId);
		Reservation res = (Reservation)query.getSingleResult();
		entityManager.remove(res);
		
	}

	@Override
	public List<Reservation> getExpiredReservations(Long userId) {
		Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.user.id = :user_id and r.vissibility = False ORDER BY r.startTime",Reservation.class)
				.setParameter("user_id", userId);
		List<Reservation> r = (List<Reservation>)query.getResultList();
		return (List<Reservation>)query.getResultList();
	}
}
