package com.epf.RentManager.service;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;

public class VehicleServiceTest {
	
	VehicleService vehicleService = VehicleService.getInstance();
	@Test
	public void testCreate() {
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("Renault");
		vehicle.setModel("Twizy");
		vehicle.setNbSeats(2);
		try {
			vehicleService.create(vehicle);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class) // fails because of th number of seats
	public void testCreateFail1() throws ServiceException {
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("nom");
		vehicle.setModel("prenom");
		vehicle.setNbSeats(1);
		vehicleService.create(vehicle);
	}
	
	@Test(expected = ServiceException.class) // fails because of th number of seats
	public void testCreateFail2() throws ServiceException {
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("nom");
		vehicle.setModel("prenom");
		vehicle.setNbSeats(10);
		vehicleService.create(vehicle);
	}
	
	@Test(expected = ServiceException.class) // fails because the manufacturer isn't only letters
	public void testCreateFail3() throws ServiceException {
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("----");
		vehicle.setModel("modèle");
		vehicle.setNbSeats(5);
		vehicleService.create(vehicle);
	}
	
	@Test
	public void testFindAll() throws ServiceException {
		try {
			vehicleService.findAll();
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void testFindById() throws ServiceException {
		try {
			vehicleService.findById(1);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testFindByIdFail() throws ServiceException {
		vehicleService.findById(-1);
	}
	
	@Test
	public void testDelete() throws ServiceException {
		Vehicle vehicle = new Vehicle();
		try {
			vehicleService.delete(vehicle);
		} catch (ServiceException e) {
			fail();
		}
	}

}
