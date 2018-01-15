package com.semafors.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
    private
    Long id;
	@Column(name = "start_time")
    private
    Long startTime;
	@Column(name = "endTime")
    private
    Long endTime;
	@ManyToOne
	@JoinColumn(name = "user_id")
    private
    User user;
    private boolean vissibility;
	@ManyToOne
	@JoinColumn(name = "reservation_place_id")
    private
    ReservationPlace reservationPlace;
	@Column(name = "during")
    private
    Long during;
	
	
	
	
	public Reservation(Long startTime, Long endTime, Long during) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.during = during;
	}

	
	public Reservation(Long during) {
		super();
		this.during = during;
	}

	public Reservation() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getVissibility() {
		return vissibility;
	}
	public void setVissibility(Boolean vissibility) {
		this.vissibility = vissibility;
	}
	public ReservationPlace getReservationPlace() {
		return reservationPlace;
	}
	public void setReservationPlace(ReservationPlace reservationPlace) {
		this.reservationPlace = reservationPlace;
	}
	public Long getDuring() {
		return during;
	}
	public void setDuring(Long during) {
		this.during = during;
	}

	
	
}
