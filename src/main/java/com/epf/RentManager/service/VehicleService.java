package com.epf.RentManager.service;

import java.util.List;

import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.dao.VehicleDao;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.exceptions.ServiceException;

public class VehicleService {
	
	// ATTRIBUTE OF VEHICLESERVICE CLASS
	
	private static VehicleService instance = null;
	
	// CONSTRUCTOR 
	
	private VehicleService() {}
	
	// GET INSTANCE FUNCTION
	
	public static VehicleService getInstance() {
		if(instance == null) {
			instance = new VehicleService();
		}
		return instance;
	}
	
	VehicleDao vehicledao = VehicleDao.getInstance();
	
	/**
	 * gets all the existing Vehicle objects
	 * @return list of all the Vehicle objects found
	 * @throws ServiceException if no vehicle found
	 */
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicledao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a Vehicle object from its ID
	 * @param id of the vehicle we are loking for
	 * @return vehicledao.findById(id) : Vehicle object found
	 * @throws ServiceException if no vehicle found
	 */
	public Vehicle findById(int id) throws ServiceException {
		try {
			return vehicledao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * sends a given Vehicle object to the dao to be deleted
	 * @param vehicle : Vehicle object to delete
	 * @return vehicledao.delete(vehicle) : the estate of the request
	 * @throws ServiceException if the vehicle hasn't been deleted 
	 */
	public long delete(Vehicle vehicle) throws ServiceException {
		try { 
			return vehicledao.delete(vehicle);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * tests if the paramaters of the vehicle are correct and then send them to the dao to create the vehicle
	 * @param vehicle : Vehicle object to create
	 * @return vehicledao.create(vehicle) : the estate of the request
	 * @throws ServiceException if the vehicle hasn't been created
	 */
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			checkWordType(vehicle.getManufacturer());
			checkNoEmpty(vehicle);
			checkNbSeats(vehicle);
			return vehicledao.create(vehicle);	
		}
		catch (DaoException e){ 
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * test if the paramaters of the vehicle are correct and then send them to the dao to edit the vehicle
	 * @param vehicle : Vehicle object to edit
	 * @return vehicledao.edit(vehicle) : the estate of the request
	 * @throws ServiceException if the vehicle hasn't been edited
	 */
	public long edit(Vehicle vehicle) throws ServiceException {
		try { 
			checkWordType(vehicle.getManufacturer());
			checkNoEmpty(vehicle);
			checkNbSeats(vehicle);
			return vehicledao.edit(vehicle);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * tests if the word is composed only by letters and spaces, but not special characters or numbers
	 * @param word : word to test
	 * @throws ServiceException if a number or special character is found
	 */
	private void checkWordType(String word) throws ServiceException {
		for(int letter = 0; letter < word.length(); letter++) {
			if(!Character.isLetter(word.charAt(letter))) {
				throw new ServiceException("La marque doit être composés uniquement de lettres.");
			} 
		}
	}
	
	/**
	 * sends words to check to an other function
	 * @param vehicle : Vehicle object
	 * @throws ServiceException propagates the error if it is thrown during the execution
	 */
	private void checkNoEmpty(Vehicle vehicle) throws ServiceException {
		checkNoEmptyWord(vehicle.getManufacturer());
		checkNoEmptyWord(vehicle.getModel());
	}

	/**
	 * checks that a user input is not only composed by spaces ' '
	 * @param wordInput to test
	 * @throws ServiceException if only spaces are found
	 */
	private void checkNoEmptyWord(String wordInput) throws ServiceException {
		boolean notEmpty = false;
		for(int character = 0; character < wordInput.length(); character++) {
			if(wordInput.charAt(character) != ' ') {
				notEmpty = true;
			}
		}
		if(!notEmpty) {
			throw new ServiceException("Les champs remplis ne doivent pas être vides");
		}
	}
	
	/**
	 * checks if the number of seats respects the conditions (between 2 and 9 seats)
	 * @param vehicle : Vehicle object to test
	 * @throws ServiceException if the condition is not respected
	 */
	private void checkNbSeats(Vehicle vehicle) throws ServiceException {
		if(vehicle.getNbSeats() < 2 || vehicle.getNbSeats() > 9) {
			throw new ServiceException("Le nombre de places doit être compris entre 2 et 9.");
		}
	}
}
