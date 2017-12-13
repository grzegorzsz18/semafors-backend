package com.semafors.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.semafors.entity.Reservation;
import com.semafors.service.reservation.ReservationScheduler;

public class ReservationSchedulerTest {
	
	static ReservationScheduler rs;
	List<Reservation> reservations;
	List<Reservation> expected;
	long duration = 1000000l;
	long time;
	
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
