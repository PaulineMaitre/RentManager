package com.epf.RentManager.service;

import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;

import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.exceptions.ServiceException;

public class ReservationServiceTest {
	
	ReservationService reservationService = ReservationService.getInstance();
	ClientService clientService = ClientService.getInstance();
	VehicleService vehicleService = VehicleService.getInstance();
	
	@Test
	public void testCreate() throws ServiceException {
		Reservation reservation = new Reservation();
		reservation.setClientId(clientService.findById(1));
		reservation.setVehicleId(vehicleService.findById(1));
		reservation.setBeginning(Date.valueOf("2020-06-10"));
		reservation.setEnd(Date.valueOf("2020-06-15"));
		try {
			reservationService.create(reservation);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class) // fails because the duration of the reservation is greater than 7 days
	public void testCreateFail1() throws ServiceException {
		Reservation reservation = new Reservation();
		reservation.setClientId(clientService.findById(1));
		reservation.setVehicleId(vehicleService.findById(1));
		reservation.setBeginning(Date.valueOf("2019-01-10"));
		reservation.setEnd(Date.valueOf("2019-01-20"));
		reservationService.create(reservation);
	}
	
	@Test(expected = ServiceException.class) // fails because end date is before start date
	public void testCreateFail2() throws ServiceException {
		Reservation reservation = new Reservation();
		reservation.setClientId(clientService.findById(1));
		reservation.setVehicleId(vehicleService.findById(1));
		reservation.setBeginning(Date.valueOf("2019-01-10"));
		reservation.setEnd(Date.valueOf("2019-01-05"));
		reservationService.create(reservation);
	}
	
	@Test
	public void testFindAll() throws ServiceException {
		try {
			reservationService.findAll();
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void testFindById() throws ServiceException {
		try {
			reservationService.findById(1);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testFindByIdFail() throws ServiceException {
		reservationService.findById(-1);
	}
	
	@Test
	public void testDelete() throws ServiceException {
		Reservation reservation = new Reservation();
		try {
			reservationService.delete(reservation);
		} catch (ServiceException e) {
			fail();
		}
	}

}
