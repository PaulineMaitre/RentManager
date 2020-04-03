package com.epf.RentManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.persistence.ConnectionManager;


public class VehicleDao {
	
	// ATTRIBUTES OF VEHICLEDAO CLASS
	
	private static VehicleDao instance = null;
	private static VehicleDao instanceTest = null;
	private boolean test;
	
	// CONSTRUCTORS 
	
	private VehicleDao() {}
	private VehicleDao(boolean test) {
		this.test = test;
	}
	
	// GET INSTANCE FUNCTIONS
	
	public static VehicleDao getInstance(boolean test) {
		if(test) {
			if(instanceTest == null) {
				instanceTest = new VehicleDao(true);
			}
			return instanceTest;
		}
		else {
			if(instance == null) {
				instance = new VehicleDao(false);
			}
			return instance;
		}
	}
	
	public static VehicleDao getInstance() {
		return getInstance(false);
	}
	
	// STRINGS CORRESPONDING TO REQUESTS TO THE DATABASE
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?;";

	/**
	 * gets the list of all Vehicle objects in the database
	 * @return resultList : list of all Vehicle objects
	 * @throws DaoException if error during the execution of the request
	 */
	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> resultList = new ArrayList<>();
		
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_VEHICLES_QUERY);)
		{	
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Vehicle vehicle = new Vehicle(resultSet.getInt(1), 
												resultSet.getString(2),
												resultSet.getString(3), 
												resultSet.getInt(4));
					resultList.add(vehicle);
				}
			} catch (SQLException e) {
				System.out.println("Erreur lors du Select All : " + e.getMessage()); 
			} 
		return resultList;
	}
	
	/**
	 * gets a Vehicle object by its ID
	 * @param id of the vehicle we are looking for
	 * @return vehFoundById : Vehicle object found
	 * @throws DaoException if no vehicle matches the request
	 */
	public Vehicle findById(int id) throws DaoException {
		Vehicle vehFoundById = null;
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_VEHICLE_QUERY);)
		{	
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			vehFoundById = new Vehicle(id, resultSet.getString("constructeur"),
										resultSet.getString("modele"), 
										resultSet.getInt("nb_places"));
			
		} catch(SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return vehFoundById;
	}
	
	/**
	 * deletes the vehicle object in the database fulfilling the missing parts of the prepared request
	 * @param vehicle object to delete
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the supression
	 */
	public long delete(Vehicle vehicle) throws DaoException {
		int id = vehicle.getId();
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY);)
		{	
			statement.setInt(1, id);
			return statement.executeUpdate();
			
		} catch(SQLException e) {
			throw new DaoException("Erreur lors de la suppression : " + e.getMessage());
		}
	}
	
	/**
	 * creates the vehicle in the database fulfilling the missing parts of the prepared request
	 * @param vehicle object to add to the database
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if error during the creation
	 */
	public long create(Vehicle vehicle) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_QUERY);)
		{		
				statement.setString(1, vehicle.getManufacturer());
				statement.setString(2, vehicle.getModel());
				statement.setInt(3, vehicle.getNbSeats());
				return statement.executeUpdate();
				
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la création : " + e.getMessage());
			} 
	}
	
	/**
	 * edits the vehicle object in the database fulfilling the missing parts of the prepared request
	 * @param vehicle object to edit
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the edition
	 */
	public long edit(Vehicle vehicle) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(EDIT_VEHICLE_QUERY);)
		{		
				statement.setString(1, vehicle.getManufacturer());
				statement.setString(2, vehicle.getModel());
				statement.setInt(3, vehicle.getNbSeats());
				statement.setInt(4, vehicle.getId());
				
				return statement.executeUpdate();
				
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la modification : " + e.getMessage());
			} 
	}
}
