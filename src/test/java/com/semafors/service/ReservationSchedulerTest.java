package com.semafors.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.semafors.entity.Reservation;
import com.semafors.service.reservation.ReservationScheduler;

public class ReservationSchedulerTest {
	
	private static ReservationScheduler rs;
	private List<Reservation> reservations;
	private List<Reservation> expected;
	private final long duration = 1000000l;
	private long time;
	
	@BeforeClass
	public static void beforeClass() {
		rs = new ReservationScheduler();
	}
	
	@Before
	public void before() {
		reservations = new ArrayList<>();
		expected = new ArrayList<>();
		time = System.currentTimeMillis();
	}
	
	@Test
	@Ignore
	public void insertIntoEmptyList() {		
		assertTrue(System.currentTimeMillis() > rs.getAvaliableStartTime(reservations, new Reservation(100l)));
	}

	@Test
	public void insertInside() {
		reservations.add(new Reservation(time,time + duration, duration));
		reservations.add(new Reservation(time + duration + 1, time + 2*duration + 1, duration));
		reservations.add(new Reservation(time + 5* duration,time + 6*duration, duration));
		assertEquals(time + 2*duration + 2,rs.getAvaliableStartTime(reservations, new Reservation(100l)), 1);
	}
	
	@Test
	public void insertAsFirst() {
		reservations.add(new Reservation(2*time ,2*time +100l,100l));
		assertTrue(rs.getAvaliableStartTime(reservations, new Reservation(100l)) < 2*time);
	}
	
}
