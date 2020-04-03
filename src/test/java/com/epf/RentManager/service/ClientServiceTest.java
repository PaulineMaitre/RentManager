package com.epf.RentManager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.exceptions.ServiceException;

public class ClientServiceTest {
	
	ClientService clientService = ClientService.getInstance();
	@Test
	public void testCreate() {
		Client client = new Client();
		client.setLastName("nom");
		client.setFirstName("prenom");
		client.setEmail("email2"); // change the email if we test more than one time
		client.setBirthday(Date.valueOf("1998-03-13"));
		try {
			clientService.create(client);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testCreateFail1() throws ServiceException {
		Client client = new Client();
		client.setLastName("nom");
		client.setFirstName("prenom");
		client.setEmail("email1@email");
		client.setBirthday(Date.valueOf("2019-03-13"));
		clientService.create(client);
	}
	
	@Test
	public void testFindAll() throws ServiceException {
		try {
			clientService.findAll();
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testCreateFail2() throws ServiceException {
		Client client = new Client();
		client.setLastName("    ");
		client.setFirstName("prenom");
		client.setEmail("email1@email");
		client.setBirthday(Date.valueOf("2000-03-13"));
		clientService.create(client);
	}
	
	@Test(expected = ServiceException.class)
	public void testCreateFail3() throws ServiceException {
		Client client = new Client();
		client.setLastName("no");
		client.setFirstName("prenom");
		client.setEmail("email1@email");
		client.setBirthday(Date.valueOf("2000-03-13"));
		clientService.create(client);
	}
	
	@Test
	public void testFindById() throws ServiceException {
		try {
			clientService.findById(1);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testFindByIdFail() throws ServiceException {
		clientService.findById(-1);
	}
	
	@Test
	public void testDelete() throws ServiceException {
		Client client = new Client();
		try {
			clientService.delete(client);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void quelquesFonctionsPratiques() {
		assertEquals("Ils ne sont pas égaux", new Long(2), new Long(2));
		assertTrue("Ce n'est pas vrai", true);
	}

}
