package com.epf.RentManager.classes;


public class Vehicle implements Comparable<Vehicle> {
	
	// ATTRIBUTES OF THE VEHICLE CLASS
	
	private int id;
	private String manufacturer;
	private String model;
	private int nbSeats;
	
	// CONSTRUCTORS
	
	public Vehicle() {}
	
	public Vehicle(int id, String manufacturer, String model, int nbSeats) {
		this.id =  id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.nbSeats = nbSeats;
	}
	
	public Vehicle(int id) {
		this.id =  id;
	}

	// GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNbSeats() {
		return nbSeats;
	}

	public void setNbSeats(int nbSeats) {
		this.nbSeats = nbSeats;
	}

	/**
	 * redefines the toString function
	 */
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructeur=" + manufacturer + ", modele=" + model+ ", nombre de places=" + nbSeats + "]";
	}

	/**
	 * redefines the compareTo function
	 */
	@Override
	public int compareTo(Vehicle vehicle) {
		return toString().compareTo(vehicle.toString());
	}
}
