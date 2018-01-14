package com.semafors.dto;

import com.semafors.entity.Reservation;
import com.semafors.entity.ReservationPlace;
import com.semafors.entity.User;

public class UserReservationAndPlace {
    private String userName;
    private Reservation reservation;
    private ReservationPlace reservationPlace;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ReservationPlace getReservationPlace() {
        return reservationPlace;
    }

    public void setReservationPlace(ReservationPlace reservationPlace) {
        this.reservationPlace = reservationPlace;
    }
}
