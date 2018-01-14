package com.semafors.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SuppressWarnings("unchecked")
@Table(name = "reservation_places")
public class ReservationPlace implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "reservation_place_id")
	private Long id;
	@JsonIgnore
	@OneToMany(targetEntity = Reservation.class, mappedBy = "reservationPlace",
            cascade = CascadeType.ALL)
    private
    List<Reservation> reservations;
	@JsonIgnore
	@OneToMany(targetEntity = Reservation.class, mappedBy = "reservationPlace",
            cascade = CascadeType.ALL)
    private
    List<Reservation> expiredReservations;
	@Column(name = "place_name")
    private
    String name;
	
	public ReservationPlace() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public List<Reservation> getExpiredReservations() {
		return expiredReservations;
	}

	public void setExpiredReservations(List<Reservation> expiredReservations) {
		this.expiredReservations = expiredReservations;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
