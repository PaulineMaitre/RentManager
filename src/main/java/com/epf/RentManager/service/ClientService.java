package com.epf.RentManager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.dao.ClientDao;
import com.epf.RentManager.exceptions.DaoException;
import com.epf.RentManager.exceptions.ServiceException;

public class ClientService {
	
	// ATTRIBUTE OF CLIENTSERVICE CLASS
	
	private static ClientService instance = null;
	
	// CONSTRUCTOR 
	
	private ClientService() {}
	
	// GET INSTANCE FUNCTION
	
	public static ClientService getInstance() {
		if(instance == null) {
			instance = new ClientService();
		}
		return instance;
	}
	
	ClientDao clientdao = ClientDao.getInstance();
	
	/**
	 * gets all the existing Client objects
	 * @return list of all the Client objects found
	 * @throws ServiceException if no client found
	 */
	public List<Client> findAll() throws ServiceException {
		try {
			return clientdao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * gets a Client object from its ID
	 * @param id of the client we are loking for
	 * @return Client object found
	 * @throws ServiceException if no client found
	 */
	public Client findById(int id) throws ServiceException {
		try {
			return clientdao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * sends a given Client object to the dao to be deleted
	 * @param client : Client object to delete
	 * @return clientdao.delete(client) : the estate of the request
	 * @throws ServiceException if the client hasn't been deleted 
	 */
	public long delete(Client client) throws ServiceException {
		try {
			return clientdao.delete(client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * tests if the paramaters of the client are correct and then send them to the dao to create the client
	 * @param client : Client object to create
	 * @return clientdao.create(client) : the estate of the request
	 * @throws ServiceException if the client hasn't been created
	 */
	public long create(Client client) throws ServiceException {
		try {
			checkNoEmpty(client);
			checkNames(client);
			checkUnicityEmail(client);
			checkAge(client);
			return clientdao.create(client);	
		}
		catch (DaoException e){ 
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * test if the paramaters of the client are correct and then send them to the dao to edit the client
	 * @param client : Client object to edit
	 * @return clientdao.edit(client) : the estate of the request
	 * @throws ServiceException if the client hasn't been edited
	 */
	public long edit(Client client) throws ServiceException {
		try {
			checkNoEmpty(client);
			checkNames(client);
			checkUnicityEmail(client);
			checkAge(client);
			return clientdao.edit(client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * sends words to check to an other function
	 * @param client : Client object
	 * @throws ServiceException propagates the error if it is thrown during the execution
	 */
	private void checkNoEmpty(Client client) throws ServiceException {
		checkNoEmptyWord(client.getFirstName());
		checkNoEmptyWord(client.getLastName());
	}

	/**
	 * checks that a user input is not only composed by spaces ' '
	 * @param wordInput : word to test
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
	 * gets and sends to another functions the words we need to test
	 * @param client : Client object
	 * @throws ServiceException propagates the error if it is thrown during the execution
	 */
	private void checkNames(Client client) throws ServiceException {
		String lastName = client.getLastName();
		String firstName = client.getFirstName();
		checkWordType(firstName);
		checkWordType(lastName);
		checkWordSize(firstName, "prénom");
		checkWordSize(lastName, "nom");
	}
	
	/**
	 * tests if the word is composed only by letters and spaces, but not special characters or numbers
	 * @param word : word to test
	 * @throws ServiceException if a number or special character is found
	 */
	private void checkWordType(String word) throws ServiceException {
		for(int letter = 0; letter < word.length(); letter++) {
			if(!Character.isLetter(word.charAt(letter)) && word.charAt(letter) != ' ') {
				throw new ServiceException("Le nom et le prénom doivent être composés uniquement de lettres.");
			} 
		}
	}
	
	/**
	 * tests if the size of the word is more than 3, which is a minimum for Client first and last names
	 * @param word : word to test
	 * @param attributeName : name of the attribute, to write it in the error message
	 * @throws ServiceException if the word is less than 3 characters
	 */
	private void checkWordSize(String word, String attributeName) throws ServiceException {
		if(word.length() < 3) {
			throw new ServiceException("Le " + attributeName + " doit faire au moins 3 caractères.");
		}
	}

	/**
	 * tests if the email the user gave is not already used in the database
	 * @param client : new Client object to test
	 * @throws ServiceException if the new user email adress is already saved in the database
	 */
	private void checkUnicityEmail(Client client) throws ServiceException {
		String email = client.getEmail();
		List<Client> clients;
		try {
			clients = clientdao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		for(Client clientIterator : clients) {
			if(email.equals(clientIterator.getEmail()) && clientIterator.getId() != client.getId()) {
				throw new ServiceException("Cette adresse e-mail est déjà utilisée.");
			}
		}
	}
	
	/**
	 * tests if the client is 18 years or more
	 * @param client object to test
	 * @throws ServiceException if the client is less than 18
	 */
	private void checkAge(Client client) throws ServiceException {
		long age = ChronoUnit.YEARS.between(client.getBirthday().toLocalDate(), LocalDate.now());
		if(age < 18) {
			throw new ServiceException("Le client doit avoir plus de 18 ans.");
		}
	}
}
