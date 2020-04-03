package com.epf.RentManager.service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.dao.ReservationDao;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.exceptions.ServiceException;

public class ReservationService {
	
	// ATTRIBUTE OF RESERVATIONSERVICE CLASS
	private static ReservationService instance = null;
	
	// CONSTRUCTOR 
	
	private ReservationService() {}
	
	// GET INSTANCE FUNCTION
	
	public static ReservationService getInstance() {
		if(instance == null) {
			instance = new ReservationService();
		}
		return instance;
	}
	
	ReservationDao reservationdao = ReservationDao.getInstance();
	
	/**
	 * gets all the existing Reservation objects
	 * @return list of all the Reservation objects found
	 * @throws ServiceException if no reservation found
	 */
	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationdao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a Reservation object from its ID
	 * @param id of the reservation we are loking for
	 * @return Reservation object found
	 * @throws ServiceException if no reservation found
	 */
	public Reservation findById(int id) throws ServiceException {
		try {
			return reservationdao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a list of Vehicles objects by a Client ID
	 * @param clientId : id of the Client whose reservations are being looked for
	 * @return list of Vehicles objects found
	 * @throws ServiceException if no vehicle found
	 */
	public List<Vehicle> findVehiclesByClient(int clientId) throws ServiceException {
		try {
			List<Reservation> resaByClientId = reservationdao.findResaByClientId(clientId);
			List<Vehicle> listVehicles = findVehiclesFromReservations(resaByClientId);
			return listVehicles;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a list of all the vehicles rented by a given Client,
	 * checking that each vehicle is only once in the list
	 * @param resaByClientId list of all the reservations of a given Client
	 * @return list of vehicles rented by a client
	 */
	private List<Vehicle> findVehiclesFromReservations(List<Reservation> resaByClientId) {
		List<Vehicle> allVehicles = new ArrayList<Vehicle>();
		List<Vehicle> listVehicles = new ArrayList<Vehicle>();
		boolean unique = true;
		
		for(Reservation reservation : resaByClientId) {
			allVehicles.add(reservation.getVehicleId());
		}
		for(Vehicle veh : allVehicles) {
			unique = true;
			if(listVehicles.size() == 0)  {
				listVehicles.add(veh);
			}
			else {
				for(Vehicle veh2 : listVehicles) {
					if(veh.getId() == veh2.getId()) {
						unique = false;
					}
				}
				if(unique == true ) {
					listVehicles.add(veh);
				}
			}
		}
		Collections.sort(listVehicles);
		return listVehicles;
	}
	
	/**
	 * gets a list of Client objects by a Client ID
	 * @param vehicleId : id of the vehicle whose reservations are being looked for
	 * @return list of Client objects found
	 * @throws ServiceException if no client found
	 */
	public List<Client> findClientsByVehicle(int vehicleId) throws ServiceException {
		try {
			List<Reservation> resaByVehicleId = reservationdao.findResaByVehicleId(vehicleId);
			List<Client> listClients = findClientsFromReservations(resaByVehicleId);
			return listClients;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a list of all the Clients who have rented a given vehicle,
	 * checking that each client is only once in the list
	 * @param resaByVehicleId list of all the reservations of a given Vehicle
	 * @return list of clients involved with a given vehicle
	 */
	private List<Client> findClientsFromReservations(List<Reservation> resaByVehicleId) {
		List<Client> allClients = new ArrayList<Client>();
		List<Client> listClients = new ArrayList<Client>();
		boolean unique = true;
		for(Reservation reservation : resaByVehicleId) {
			allClients.add(reservation.getClientId());
		}
		for(Client client : allClients) {
			unique = true;
			if(listClients.size() == 0)  {
				listClients.add(client);
			}
			else {
				for(Client clientTest : listClients) {
					if(client.getId() == clientTest.getId()) {
						unique = false;
					}
				}
				if(unique == true ) {
					listClients.add(client);
				}
			}
		}
		Collections.sort(listClients);
		return listClients;
	}
	
	/**
	 * gets a list of Reservation objects by a Client ID
	 * @param clientId : id of the Client whose reservations are being looked for
	 * @return list of Reservation objects found
	 * @throws ServiceException if no reservation found
	 */
	public List<Reservation> findResaByClientId(int clientId) throws ServiceException {
		try {
			return reservationdao.findResaByClientId(clientId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a list of Reservation objects by a Vehicle ID
	 * @param vehicleId : id of the Vehicle whose reservations are being looked for
	 * @return list of Reservation objects found
	 * @throws ServiceException if no reservation found
	 */
	public List<Reservation> findResaByVehicleId(int vehicleId) throws ServiceException {
		try {
			return reservationdao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * sends a given Reservation object to the dao to be deleted
	 * @param reservation : Reservation to delete
	 * @return reservationdao.delete(reservation) : the estate of the request
	 * @throws ServiceException if the reservation hasn't been deleted 
	 */
	public long delete(Reservation reservation) throws ServiceException {
		try {
			return reservationdao.delete(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * tests if the paramaters of the reservation are correct and then send them to the dao to create the reservation
	 * @param reservation : Reservation object to create
	 * @return reservationdao.create(reservation) : the estate of the request
	 * @throws ServiceException if the Reservation hasn't been created
	 */
	public long create(Reservation reservation) throws ServiceException {
		try {
			checkDateConditions(reservation);
			checkDisponibility(reservation);
			checkDurationUnder30Days(reservation);
			return reservationdao.create(reservation);	
		}
		catch (DaoException e){ 
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * test if the paramaters of the reservation are correct and then send them to the dao to edit the reservation
	 * @param reservation : Reservation object to edit
	 * @return reservationdao.edit(reservation) : the estate of the request
	 * @throws ServiceException if the reservation hasn't been edited
	 */
	public long edit(Reservation reservation) throws ServiceException {
		try {
			checkDateConditions(reservation);
			checkDisponibility(reservation);
			checkDurationUnder30Days(reservation);
			return reservationdao.edit(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * tests if the new reservation matches the conditions :
	 * 1. a vehicle can not be rented more than 7 days
	 * 2. the date of end must me after the date of beginning of the reservation
	 * @param reservation we want to add
	 * @throws ServiceException if at least one of the conditions is not respected
	 */
	private void checkDateConditions(Reservation reservation) throws ServiceException {
		long duration = ChronoUnit.DAYS.between(reservation.getBeginning().toLocalDate(), reservation.getEnd().toLocalDate());
		if(duration > 7) {
			throw new ServiceException("La réservation ne peut pas durer plus de 7 jours.");
		}
		else if(duration < 0) {
			throw new ServiceException("La date de début doit être antérieure à la date de fin.");
		}
	}
	
	/**
	 * tests if the vehicle is not already rented during a given period
	 * @param reservation we want to add
	 * @throws ServiceException if the vehicle is already rented during the period
	 */
	private void checkDisponibility(Reservation reservation) throws ServiceException {
		try {
			List<Reservation> listOfReservations = reservationdao.findResaByVehicleId(reservation.getVehicleId().getId());
			Date beginning;
			Date end;
			Date newStart = reservation.getBeginning(); 
			Date newEnd = reservation.getEnd();
			for(Reservation resaFoundByVehicleId : listOfReservations) {
				beginning = resaFoundByVehicleId.getBeginning();
				end = resaFoundByVehicleId.getEnd();
				if(!newEnd.before(beginning) && !newStart.after(end) && resaFoundByVehicleId.getId() != reservation.getId() ) {
					throw new ServiceException("Le véhicule est déjà loué durant cette période.");
				}
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/** 
	 * tests if the total duration of reservation of a given Vehicle without break 
	 * is lower than 30 days
	 * @param reservation we want to add
	 * @throws ServiceException if by adding this reservation the total number of
	 * resservation days without break is greater than 30 days
	 */
	private void checkDurationUnder30Days(Reservation reservation) throws ServiceException {
		boolean inserted = false; // false if the date hasn't been inserted in the list yet
		long daysCounter = 0; // counter of days of reservation without break
		int listIterator = 0;
		Date newStart = reservation.getBeginning(); // start date we want to add
		Date newEnd = reservation.getEnd(); // end date we want to add
		List<List<Date>> allDatesList = new ArrayList<List<Date>>();
		List<Date> newDatesList = new ArrayList<Date>();
		newDatesList.add(newStart);
		newDatesList.add(newEnd);
		try {
			// get the list of all reservations of a given vehicle
			List<Reservation> listOfReservations = reservationdao.findResaByVehicleId(reservation.getVehicleId().getId());
			
			for(Reservation reservationIterator : listOfReservations) { // add all the couples of dates to the list
				allDatesList.add(new ArrayList<Date>(Arrays.asList(reservationIterator.getBeginning(),reservationIterator.getEnd())));
			}
			// add the new couple of dates to the list, at the right place so the list is sorted by decreasing dates of beginning  
			while(!inserted && listIterator < allDatesList.size()) {
				if(newStart.after(allDatesList.get(listIterator).get(0))) {
					allDatesList.add(listIterator, new ArrayList<Date>(Arrays.asList(newStart,newEnd))); 
					inserted = true;
				}
				else {
					listIterator++;
				}
			}
			if(!inserted) { // if the couple of dates hasn't been added yet, we add it at the end of the list
				allDatesList.add(newDatesList); 
			}
			
			int indexAddedDates = allDatesList.indexOf(newDatesList); // get the index of the added couple of dates
			daysCounter = 1 + ChronoUnit.DAYS.between(allDatesList.get(indexAddedDates).get(0).toLocalDate(), allDatesList.get(indexAddedDates).get(1).toLocalDate());
			
			for (int i = indexAddedDates; i < allDatesList.size() - 1; i++) { // iterate the list after the index
				// if the reservation follows the previous one without break, we add the number of days to the days counter
				if (ChronoUnit.DAYS.between(allDatesList.get(i + 1).get(1).toLocalDate(), allDatesList.get(i).get(0).toLocalDate()) == 1) {
					daysCounter += 1 + ChronoUnit.DAYS.between(allDatesList.get(i + 1).get(0).toLocalDate(), allDatesList.get(i + 1).get(1).toLocalDate());
				}
				else {
					break;
				}
			}
			
			for (int i = indexAddedDates; i > 0; i--) { // iterate the list before the index
				// if the reservation comes before the previous one without break, we add the number of days to the days counter
				if (ChronoUnit.DAYS.between(allDatesList.get(i).get(1).toLocalDate(), allDatesList.get(i-1).get(0).toLocalDate()) == 1) {
					daysCounter += 1 + ChronoUnit.DAYS.between(allDatesList.get(i - 1).get(0).toLocalDate(), allDatesList.get(i - 1).get(1).toLocalDate());
				}
				else {
					break;
				}
			}
			if(daysCounter > 30) {
				throw new ServiceException("Le véhicule ne peut pas être loué plus de 30 jours consécutifs.");
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
