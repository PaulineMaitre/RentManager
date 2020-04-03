package com.epf.RentManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.persistence.ConnectionManager;


public class ClientDao {
	
	// ATTRIBUTES OF CLIENTDAO CLASS
	
	private static ClientDao instance = null;
	private static ClientDao instanceTest = null;
	private boolean test;
	
	// CONSTRUCTORS 
	
	private ClientDao() {}
	private ClientDao(boolean test) {
		this.test = test;
	}
	
	// GET INSTANCE FUNCTIONS
	
	public static ClientDao getInstance(boolean test) {
		if(test) {
			if(instanceTest == null) {
				instanceTest = new ClientDao(true);
			}
			return instanceTest;
		}
		else {
			if(instance == null) {
				instance = new ClientDao(false);
			}
			return instance;
		}
	}
	
	public static ClientDao getInstance() {
		return getInstance(false);
	}
	
	// STRINGS CORRESPONDING TO REQUESTS TO THE DATABASE
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	
	/**
	 * gets the list of all Clients objects in the database
	 * @return list of all Client objects
	 * @throws DaoException if error during the execution of the request
	 */
	public List<Client> findAll() throws DaoException {
		List<Client> resultList = new ArrayList<>();
		
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_CLIENTS_QUERY);)
		{
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Client client = new Client(resultSet.getInt(1), 
												resultSet.getString(2),
												resultSet.getString(3), 
												resultSet.getString(4), 
												resultSet.getDate(5));
					resultList.add(client);
				}
			} catch (SQLException e) {
				System.out.println("Erreur lors du Select All : " + e.getMessage()); 
			} 
		return resultList;
	}
	
	/**
	 * gets a Client object by its ID
	 * @param id of the client we are looking for
	 * @return Client object found
	 * @throws DaoException if no client matches the request
	 */
	public Client findById(int id) throws DaoException {
		Client clientFoundById = null;
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
										:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_CLIENT_QUERY);)
		{
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			clientFoundById = new Client(id, resultSet.getString("nom"),
										resultSet.getString("prenom"), 
										resultSet.getString("email"), 
										resultSet.getDate("naissance"));
			
		} catch(SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return clientFoundById;
	}
	
	/**
	 * deletes the client object in the database fulfilling the missing parts of the prepared request
	 * @param client object to delete
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the supression
	 */
	public long delete(Client client) throws DaoException {
		int id = client.getId();
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_CLIENT_QUERY);)
		{	
			statement.setInt(1, id);
			long result = statement.executeUpdate();
			return result;
			
		} catch(SQLException e) {
			throw new DaoException("Erreur lors de la suppression : " + e.getMessage());
		}
	}
	
	/**
	 * creates the client in the database fulfilling the missing parts of the prepared request
	 * @param client object to add to the database
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if error during the creation
	 */
	public long create(Client client) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(CREATE_CLIENT_QUERY);)
		{	
			statement.setString(1, client.getLastName());
			statement.setString(2, client.getFirstName());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getBirthday());

			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		} 
	}
	
	/**
	 * edits the client object in the database fulfilling the missing parts of the prepared request
	 * @param client object to edit
	 * @return statement.executeUpdate() the response of the request
	 * @throws DaoException if there is an error during the edition
	 */
	public long edit(Client client) throws DaoException {
		try (Connection conn = test ? ConnectionManager.getConnectionForTest()
									:ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(EDIT_CLIENT_QUERY);)
		{	
			statement.setString(1, client.getLastName());
			statement.setString(2, client.getFirstName());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getBirthday());
			statement.setInt(5, client.getId());

			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la modification : " + e.getMessage());
		} 
	}
}
