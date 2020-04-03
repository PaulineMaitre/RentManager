package com.epf.RentManager.classes;

import java.sql.Date;

public class Reservation implements Comparable<Reservation> {
	
	// ATTRIBUTES OF THE RESERVATION CLASS
	
	private int id;
	private Client clientId;
	private Vehicle vehicleId;
	private Date beginning;
	private Date end;
	
	// CONSTRUCTORS
	
	public Reservation() {}
	
	public Reservation(int id, Client clientId, Vehicle vehicleId, Date beginning, Date end) {
		this.id =  id;
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.beginning = beginning;
		this.end = end;
	}
	
	// GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	public Vehicle getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Vehicle vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getBeginning() {
		return beginning;
	}

	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * redefines the toString function
	 */
	@Override
	public String toString() {
		return "Réservation [id=" + id + ", client=" + clientId.getLastName() + " " + clientId.getFirstName() + ", vehicule=" + vehicleId.getManufacturer() + " " + vehicleId.getModel() + ", date debut=" 
							+ beginning + ", fin=" + end + "]";
	}
	
	@Override
	public int compareTo(Reservation reservation) {
		return beginning.toString().compareTo(reservation.beginning.toString());
	}

}
