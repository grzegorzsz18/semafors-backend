package com.semafors.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.semafors.dao.interfaces.ReservationPlacesDAO;
import com.semafors.entity.ReservationPlace;

@Repository
@Transactional
public class ReservationPlacesDAOImpl implements ReservationPlacesDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addReservationPlace(ReservationPlace reservationPlace) {
		entityManager.persist(reservationPlace);
		
	}

	@Override
	public List<ReservationPlace> getAllNameAndId() {
		Query query = entityManager.createQuery("SELECT r FROM ReservationPlace r");
		return query.getResultList();
	}

}
