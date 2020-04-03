package com.epf.RentManager.classes;

import java.sql.Date;

public class Client implements Comparable<Client> {

	// ATTRIBUTES OF THE CLIENT CLASS
	
	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private Date birthday;
	
	// CONSTRUCTORS
	
	public Client() {}
	
	public Client(int id, String lastName, String firstName, String email, Date birthday) {
		this.id =  id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.birthday = birthday;
	}
	
	public Client(int id, String lastName, String firstName, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}
	
	public Client(int id) {
		this.id =  id;
	}
	
	// GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * redefines the toString function
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + lastName + ", prenom=" + firstName + ", email=" + email + ", naissance="
				+ birthday + "]";
	}
	
	/**
	 * redefines the compareTo function
	 */
	@Override
	public int compareTo(Client client) {
		return toString().compareTo(client.toString());
	}
}
