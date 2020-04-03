package com.epf.RentManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.persistence.ConnectionManager;


public class ReservationDao {
	
	// ATTRIBUTES OF RESERVATIONDAO CLASS

	private static ReservationDao instance = null;
	private static ReservationDao instanceTest = null;
	private boolean test;
	
	// CONSTRUCTORS 
	
	private ReservationDao() {}
	private ReservationDao(boolean test) {
		this.test = test;
	}
	
	// GET INSTANCE FUNCTIONS

	public static ReservationDao getInstance(boolean test) {
		if(test) {
			if(instanceTest == null) {
				instanceTest = new ReservationDao(true);
			}
			return instanceTest;
		}
		else {
			if(instance == null) {
				instance = new ReservationDao(false);
			}
			return instance;
		}
	}
	
	public static ReservationDao getInstance() {
		return getInstance(false);
	}
	
	// STRINGS CORRESPONDING TO REQUESTS TO THE DATABASE
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_BY_ID_QUERY = "SELECT r.id, r.client_id, c.nom, c.prenom, c.email, r.vehicle_id, v.constructeur, v.modele, v.nb_places, r.debut, r.fin FROM Reservation as r INNER JOIN Client as c ON r.client_id = c.id INNER JOIN Vehicle as v ON r.vehicle_id = v.id WHERE r.id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT r.id, r.client_id, c.nom, c.prenom, c.email, r.vehicle_id, v.constructeur, v.modele, v.nb_places, r.debut, r.fin FROM Reservation as r INNER JOIN Vehicle as v ON r.vehicle_id = v.id INNER JOIN Client as c ON r.client_id = c.id WHERE r.client_id=? ORDER BY r.debut DESC;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT r.id, r.client_id, c.nom, c.prenom, c.email, r.vehicle_id, v.constructeur, v.modele, v.nb_places, r.debut, r.fin FROM Reservation as r INNER JOIN Vehicle as v ON r.vehicle_id = v.id INNER JOIN Client as c ON r.client_id = c.id WHERE vehicle_id=? ORDER BY r.debut DESC;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT r.id, r.client_id, c.nom, c.prenom, c.email, r.vehicle_id, v.constructeur, v.modele, v.nb_places, r.debut, r.fin FROM Reservation as r, Client as c, Vehicle as v WHERE r.client_id = c.id AND r.vehicle_id = v.id ORDER BY r.debut DESC;";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	
	/**
	 * gets the list of all Reservation objects in the database
	 * @return list of all Reservation objects
	 * @throws DaoException if error during the execution of the request
	 */
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> resultList = new ArrayList<>();
		
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_QUERY);)
		{		
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Reservation reservation = new Reservation(resultSet.getInt("id"), 
												new Client(resultSet.getInt("client_id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email")),
												new Vehicle(resultSet.getInt("vehicle_id"), resultSet.getString("constructeur"), resultSet.getString("modele"), resultSet.getInt("nb_places")), 
												resultSet.getDate("debut"), 
												resultSet.getDate("fin"));
					resultList.add(reservation);
				}
			} catch (SQLException e) {
				System.out.println("Erreur lors du Select All : " + e.getMessage()); 
			} 
		return resultList;
	}
	
	/**
	 * gets a Reservation object by its ID
	 * @param id of the reservation we are looking for
	 * @return Reservation object found
	 * @throws DaoException if no reservation matches the request
	 */
	public Reservation findById(int id) throws DaoException {
		Reservation resaFoundById = null;
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_RESERVATION_BY_ID_QUERY);)
		{	
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			resaFoundById = new Reservation(id, 
											new Client(resultSet.getInt("client_id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email")), 
											new Vehicle(resultSet.getInt("vehicle_id"), resultSet.getString("constructeur"), resultSet.getString("modele"), resultSet.getInt("nb_places")),
											resultSet.getDate("debut"), 
											resultSet.getDate("fin"));
		} catch(SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return resaFoundById;
	}
	
	/**
	 * gets a list of Reservation objects by a Client ID
	 * @param clientId : id of the Client whose reservations are being looked for
	 * @return list of Reservation objects found
	 * @throws DaoException if no reservation matches the request
	 */
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> resultList = new ArrayList<>();
		
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);)
		{	
			statement.setInt(1, clientId);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reservation resaFoundByClientId = new Reservation(resultSet.getInt("id"), 
											new Client(clientId, resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email")),
											new Vehicle(resultSet.getInt("vehicle_id"), resultSet.getString("constructeur"), resultSet.getString("modele"), resultSet.getInt("nb_places")),
											resultSet.getDate("debut"), 
											resultSet.getDate("fin"));
				resultList.add(resaFoundByClientId);
			}
		} catch(SQLException e) {
			System.out.println("Erreur lors du Select par ID : " + e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * gets a list of Reservation objects by a Vehicle ID
	 * @param vehicleId : id of the Vehicle whose reservations are being looked for
	 * @return list of Reservation objects found
	 * @throws DaoException if no reservation matches the request
	 */
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> resultList = new ArrayList<>();
		
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);)
		{	
			statement.setInt(1, vehicleId);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reservation resaFoundByVehicleId = new Reservation(resultSet.getInt("id"), 
												new Client(resultSet.getInt("client_id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email")),
												new Vehicle(vehicleId, resultSet.getString("constructeur"), resultSet.getString("modele"), resultSet.getInt("nb_places")),
												resultSet.getDate("debut"), 
												resultSet.getDate("fin"));
				resultList.add(resaFoundByVehicleId);
			}
		} catch(SQLException e) {
			System.out.println("Erreur lors du Select par ID : " + e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * deletes the reservation object in the database fulfilling the missing parts of the prepared request
	 * @param reservation object to delete
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the supression
	 */
	public long delete(Reservation reservation) throws DaoException {
		int id = reservation.getId();
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_RESERVATION_QUERY);)
		{	
			statement.setInt(1, id);
			long result = statement.executeUpdate();
			return result;
			
		} catch(SQLException e) {
			throw new DaoException("Erreur lors de la suppression : " + e.getMessage());
		}
	}
	
	/**
	 * creates the reservation in the database fulfilling the missing parts of the prepared request
	 * @param reservation object to add to the database
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if error during the creation
	 */
	public long create(Reservation reservation) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
					:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(CREATE_RESERVATION_QUERY);)
		{		
				statement.setInt(1, reservation.getClientId().getId());
				statement.setInt(2, reservation.getVehicleId().getId());
				statement.setDate(3, reservation.getBeginning());
				statement.setDate(4, reservation.getEnd());

				return statement.executeUpdate();
				
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la création : " + e.getMessage());
			} 
	}
	
	/**
	 * edits the reservation object in the database fulfilling the missing parts of the prepared request
	 * @param reservation object to edit
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the edition
	 */
	public long edit(Reservation reservation) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(EDIT_RESERVATION_QUERY);)
		{	
			statement.setInt(1, reservation.getClientId().getId());
			statement.setInt(2, reservation.getVehicleId().getId());
			statement.setDate(3, reservation.getBeginning());
			statement.setDate(4, reservation.getEnd());
			statement.setInt(5, reservation.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la modification : " + e.getMessage());
		} 
		
	}
}
