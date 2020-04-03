package com.epf.RentManager.controler;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.epf.RentManager.service.ClientService;
import com.epf.RentManager.service.ReservationService;
import com.epf.RentManager.service.VehicleService;
import com.epf.RentManager.classes.Client;
import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;

public class CliControler {
	
	private ClientService clientService = ClientService.getInstance();
	private VehicleService vehicleService = VehicleService.getInstance();
	private ReservationService reservationService = ReservationService.getInstance();
	
	/*
	 * main function, to test all the functions of the class CliControler
	 */
	public static void main(String[] args) throws ServiceException {
		CliControler cli = new CliControler();
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		int choix = -1;
		while(!done) {
			System.out.println("LISTE DES OPERATIONS :");
			System.out.println("Clients");
			System.out.println("   1 - Affiche la liste des clients");
			System.out.println("   2 - Rechercher un client");
			System.out.println("   3 - Ajouter un client");
			System.out.println("   4 - Supprimer un client");
			System.out.println("   5 - Editer nom client");
			System.out.println("   6 - Rechercher les véhicules loués par un client");
			System.out.println("Véhicules");
			System.out.println("   7 - Affiche la liste des véhicules");
			System.out.println("   8 - Rechercher un véhicule");
			System.out.println("   9 - Ajouter un véhicule");
			System.out.println("   10 - Supprimer un véhicule");
			System.out.println("   11 - Editer constructeur véhicule");
			System.out.println("   12 - Rechercher les clients associés à un véhicule");
			System.out.println("Réservations");
			System.out.println("   13 - Affiche la liste des réservations");
			System.out.println("   14 - Rechercher une réservation par ID");
			System.out.println("   15 - Rechercher une réservation par client");
			System.out.println("   16 - Rechercher une réservation par véhicule");
			System.out.println("   17 - Ajouter une réservation");
			System.out.println("   18 - Supprimer une réservation");
			System.out.println("   19 - Editer une réservation");
			
			try {
				choix = sc.nextInt();
			} catch (InputMismatchException e ) {
				System.out.println("La valeur saisie doit être un nombre");
			}
			sc.nextLine();
			switch(choix) {
			case 0:
				done = true;
				break;
			case 1:
				printAllClients(cli);
				break;
			case 2:
				findClient(cli, sc);
				break;
			case 3:
				addClient(cli, sc);
				break;
			case 4:
				deleteClient(cli, sc);
				break;
			case 5:
				editClient(cli, sc);
				break;
			case 6:
				findVehiclesByClient(cli, sc);
				break;
			case 7:
				printAllVehicles(cli);
				break;
			case 8:
				findVehicle(cli, sc);
				break;
			case 9:
				addVehicle(cli, sc);
				break;
			case 10:
				deleteVehicle(cli, sc);
				break;
			case 11:
				editVehicle(cli, sc);
				break;
			case 12:
				findClientsByVehicle(cli, sc);
				break;
			case 13:
				printAllReservations(cli);
				break;
			case 14:
				findReservation(cli, sc);
				break;
			case 15:
				findResaByClientId(cli, sc);
				break;
			case 16:
				findResaByVehicleId(cli, sc);
				break;
			case 17:
				addReservation(cli, sc);
				break;
			case 18:
				deleteReservation(cli, sc);
				break;
			case 19:
				editReservation(cli, sc);
				break;
			default:
				System.out.println("PAS LE BON CHOIX\n");
			}
		}
		sc.close(); // close scanner
	}

	/**
	 * function that prints all the Client objects in the database
	 * @param cli : CliControler object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void printAllClients(CliControler cli) throws ServiceException {
		try {
			List<Client> list = cli.clientService.findAll();
			for(Client client : list) {
				System.out.println(client);
			}
		} catch(ServiceException e) {
			throw new ServiceException("Une erreur est survenue" + e.getMessage());
		}
		System.out.println("\n");
	}
	
	/**
	 * function that gets the ID of a Client and prints it if it exists in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findClient(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du client à consulter");
		try {
			Client clientFoundById = cli.clientService.findById(sc.nextInt());
			if(clientFoundById != null) {
				System.out.println(clientFoundById);
			}
		} catch (ServiceException e) {
			System.out.println("Erreur : Ce client n'existe pas. \n");
		}
	}
	
	/**
	 * function that asks the user to enter parameters and then creates a new Client object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void addClient(CliControler cli, Scanner sc) throws ServiceException {
		Client client = new Client();
		sc.reset();
		System.out.println("Entrez le nom");
		client.setLastName(sc.nextLine());
		System.out.println("Entrez le prénom");
		client.setFirstName(sc.nextLine());
		System.out.println("Entrez l'email");
		client.setEmail(sc.nextLine());
		System.out.println("Entrez la date de naissance au format yyyy-[m]m-[d]d");
		client.setBirthday(Date.valueOf(sc.nextLine()));
		try {
			cli.clientService.create(client);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la création du client : " + e.getMessage() + "\n");
		}
	}
	
	/**
	 * function that asks the user to enter one Client object's ID and then delete the associated Client in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void deleteClient(CliControler cli, Scanner sc) throws ServiceException {
		printAllClients(cli);
		sc.reset();
		System.out.println("Entrez l'ID du client à supprimer :");
		Client clientToDelete = cli.clientService.findById(sc.nextInt());
		if(clientToDelete != null) {
			deleteAssociatedReservations(cli, clientToDelete);
			cli.clientService.delete(clientToDelete);
		}
		else {
			System.out.println("Cet ID de client n'existe pas.\n");
		}
	}
	
	/**
	 * function that gets all the reservations associated to a Client and then delete them in the database
	 * @param cli : CliControler object, 
	 * @param clientToDelete : Client to delete 
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void deleteAssociatedReservations(CliControler cli, Client clientToDelete) throws ServiceException {
		List<Reservation> listResaToDelete = cli.reservationService.findResaByClientId(clientToDelete.getId());
		for(Reservation resaToDelete : listResaToDelete) {
			cli.reservationService.delete(resaToDelete);
		}
	}
	
	/**
	 * function that asks the user to choose a Client by its ID, to enter 
	 * new parameters and then edit an existing Client object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void editClient(CliControler cli, Scanner sc) throws ServiceException {
		printAllClients(cli);
		sc.reset();
		System.out.println("Entrez l'ID du client à modifier");
		Client clientToEdit = new Client();
		Client clientEdited = new Client();
		String lastName = "";
		String firstName = null; 
		String email = null;
		String birthday = null;
		clientToEdit = cli.clientService.findById(sc.nextInt());
		sc.nextLine();
		System.out.println("Entrer le nouveau nom (appuyer sur ENTER pour passer) : ");
		lastName = sc.nextLine();
		System.out.println("Entrer le nouveau prénom (appuyer sur ENTER pour passer) : ");
		firstName = sc.nextLine();
		System.out.println("Entrer le nouvel email (appuyer sur ENTER pour passer) : ");
		email = sc.nextLine();
		System.out.println("Entrer la nouvelle date de naissance au format yyyy-[m]m-[d]d (appuyer sur ENTER pour passer) : ");
		birthday = sc.nextLine();
		clientEdited = editClientProperties(clientToEdit, lastName, firstName, email, birthday);
		try {
			cli.clientService.edit(clientEdited);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la modification du client : " + e.getMessage() + "\n");
		}
	}

	/**
	 * function that checks if all paramaters have been filled and if not, fill them by the old parameters of the Client
	 * @param clientToEdit : Client object to edit
	 * @param lastName : lastName entered by the user
	 * @param firstName : firstName entered by the user
	 * @param email : email entered by the user
	 * @param birthday : birthdate entered by the user
	 * @return clientEdited : the client with edited properties
	 */
	private static Client editClientProperties(Client clientToEdit, String lastName, String firstName, String email, String birthday) {
		Client clientEdited = new Client();
		clientEdited.setId(clientToEdit.getId());
		if(lastName != null && lastName != "" && !lastName.isEmpty()) {
			clientEdited.setLastName(lastName);
		} else {
			clientEdited.setLastName(clientToEdit.getLastName());
		}
		if(firstName != null && firstName != "" && !firstName.isEmpty()) {
			clientEdited.setFirstName(firstName);
		} else {
			clientEdited.setFirstName(clientToEdit.getFirstName());
		}
		if(email != null && email != "" && !email.isEmpty()) {
			clientEdited.setEmail(email);
		} else {
			clientEdited.setEmail(clientToEdit.getEmail());
		}
		if(birthday != null && birthday != "" && !birthday.isEmpty()) {
			clientEdited.setBirthday(Date.valueOf(birthday));
		} else {
			clientEdited.setBirthday(clientToEdit.getBirthday());
		}
		return clientEdited;
	}
	
	/**
	 * function that asks the user a client and them prints all the associated vehicles
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findVehiclesByClient(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du client dont vous voulez consulter les véhicules");
		try {
			List<Vehicle> listVehicles = cli.reservationService.findVehiclesByClient(sc.nextInt());
			if(listVehicles.size() > 0) {
				for(Vehicle vehicle : listVehicles) {
					System.out.println(vehicle);
				}
			}
			else {
				System.out.println("Aucun résultat à afficher.");
			}
		} catch (ServiceException e) {
			throw new ServiceException("Une erreur est survenue : " + e.getMessage());
		}
	}
	
	/**
	 * function that prints all the Vehicle objects in the database
	 * @param cli : CliControler object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void printAllVehicles(CliControler cli) throws ServiceException {
		try {
			List<Vehicle> list = cli.vehicleService.findAll();
			
			for(Vehicle veh: list) {
				System.out.println(veh);
			}
		} catch(ServiceException e) {
			throw new ServiceException("Une erreur est survenue : " + e.getMessage());
		}
		System.out.println("\n");
	}
	
	/**
	 * function that gets the ID of a Vehicle and prints it if it exists in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findVehicle(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du véhicule à consulter");
		try {
			Vehicle vehFoundById = cli.vehicleService.findById(sc.nextInt());
			if(vehFoundById != null) {
				System.out.println(vehFoundById);
			}
			else {
				System.out.println("Ce véhicule n'existe pas.\n");
			}
		} catch (ServiceException e) {
			System.out.println("Erreur : Ce véhicule n'existe pas. \n");
		}
	}
	
	/**
	 * function that asks the user to enter parameters and then creates a new Vehicle object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 */
	private static void addVehicle(CliControler cli, Scanner sc) {
		Vehicle veh = new Vehicle();
		sc.reset();
		System.out.println("Entrez le constructeur");
		veh.setManufacturer(sc.nextLine());
		System.out.println("Entrez le modèle");
		veh.setModel(sc.nextLine());
		System.out.println("Entrez le nombre de places");
		veh.setNbSeats(sc.nextInt());
		try {
			cli.vehicleService.create(veh);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la création du véhicule : " + e.getMessage() + "\n");
		}
	}
	
	/**
	 * function that asks the user to enter one Vehicle object's ID and then delete the associated Vehicle in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void deleteVehicle(CliControler cli, Scanner sc) throws ServiceException {
		printAllVehicles(cli);
		sc.reset();
		System.out.println("Entrez l'ID du véhicule à supprimer");
		Vehicle vehToDelete = cli.vehicleService.findById(sc.nextInt());
		if(vehToDelete != null) {
			deleteAssociatedReservations(cli, vehToDelete);
			cli.vehicleService.delete(vehToDelete);
		}
		else {
			System.out.println("Cet ID de véhicule n'existe pas.\n");
		}
	}
	
	/**
	 * function that gets all the reservations associated to a Vehicle and then delete them in the database
	 * @param cli : CliControler object, 
	 * @param vehicleToDelete : Vehicle object to delete 
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void deleteAssociatedReservations(CliControler cli, Vehicle vehicleToDelete) throws ServiceException {
		List<Reservation> listResaToDelete = cli.reservationService.findResaByVehicleId(vehicleToDelete.getId());
		for(Reservation resaToDelete : listResaToDelete) {
			cli.reservationService.delete(resaToDelete);
		}
	}
	
	/**
	 * function that asks the user to choose a Vehicle by its ID, to enter 
	 * new parameters and then edits an existing Vehicle object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void editVehicle(CliControler cli, Scanner sc) throws ServiceException {
		printAllVehicles(cli);
		sc.reset();
		Vehicle vehicleToEdit = new Vehicle();
		Vehicle vehicleEdited = new Vehicle();
		String manufacturer = null;
		String model = null;
		String nbSeats = null;
		System.out.println("Entrez l'ID du véhicule à modifier");
		try {
			vehicleToEdit = cli.vehicleService.findById(sc.nextInt());
			sc.nextLine();
			System.out.println("Entrer le nouveau constructeur : ");
			manufacturer = sc.nextLine();
			System.out.println("Entrer le nouveau modèle : ");
			model = sc.nextLine();
			System.out.println("Entrer le nouveau nombre de places : ");
			nbSeats = sc.nextLine();
			vehicleEdited = editVehicleProperties(vehicleToEdit, manufacturer, model, nbSeats);
			cli.vehicleService.edit(vehicleEdited);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la modification du véhicule : " + e.getMessage() + "\n");
		}
	}
	
	/**
	 * function that checks if all paramaters have been filled and if not, fill them by the old parameters of the Vehicle
	 * @param vehicleToEdit : Vehicle object to edit
	 * @param manufacturer : manufacturer entered by the user
	 * @param model : model entered by the user
	 * @param nbSeats : number of seats entered by the user
	 * @return vehicleEdited the vehicle after its update
	 */
	private static Vehicle editVehicleProperties(Vehicle vehicleToEdit, String manufacturer, String model, String nbSeats) {
		Vehicle vehicleEdited = new Vehicle();
		vehicleEdited.setId(vehicleToEdit.getId());
		if(manufacturer != null && manufacturer != "" && !manufacturer.isEmpty()) {
			vehicleEdited.setManufacturer(manufacturer);
		} else {
			vehicleEdited.setManufacturer(vehicleToEdit.getManufacturer());
		}
		if(model != null && model != "" && !model.isEmpty()) {
			vehicleEdited.setModel(model);
		} else {
			vehicleEdited.setModel(vehicleToEdit.getModel());
		}
		if(nbSeats != null && nbSeats != "" && !nbSeats.isEmpty()) {
			vehicleEdited.setNbSeats(Integer.parseInt(nbSeats));
		} else {
			vehicleEdited.setNbSeats(vehicleToEdit.getNbSeats());
		}
		return vehicleEdited;
	}
	
	/**
	 * function that asks the user a vehicle and them prints all the associated clients
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findClientsByVehicle(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du véhicule dont vous voulez consulter les clients");
		try {
			List<Client> listClients = cli.reservationService.findClientsByVehicle(sc.nextInt());
			if(listClients.size() > 0) {
				for(Client client : listClients) {
					System.out.println(client);
				}
			}
			else {
				System.out.println("Aucun résultat à afficher.");
			}
		} catch (ServiceException e) {
			throw new ServiceException("Une erreur est survenue : " + e.getMessage());
		}
	}
	
	/**
	 * function that prints all the Reservation objects in the database
	 * @param cli : CliControler object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void printAllReservations(CliControler cli) throws ServiceException {
		try {
			List<Reservation> list = cli.reservationService.findAll();
			
			for(Reservation reservation : list) {
				System.out.println(reservation);
			}
		} catch(ServiceException e) {
			throw new ServiceException("Une erreur est survenue : " + e.getMessage());
		}
		System.out.println("\n");
	}
	
	/**
	 * function that gets the ID of a Reservation and prints it if it exists in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findReservation(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID de la réservation à consulter");
		try {
			Reservation resaFoundById = cli.reservationService.findById(sc.nextInt());
			if(resaFoundById != null) {
				System.out.println(resaFoundById);
			}
		} catch (ServiceException e) {
			System.out.println("Erreur : Cette réservation n'existe pas. \n");
		}
	}
	
	/**
	 * function that gets the ID of a Client and prints all the associated reservations if they exist in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findResaByClientId(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du client dont vous voulez consulter les réservations");
		try {
			List<Reservation> listResa = cli.reservationService.findResaByClientId(sc.nextInt());
			if(listResa.size() > 0) {
				for(Reservation reservation : listResa) {
					System.out.println(reservation);
				}
			}
			else {
				System.out.println("Aucun résultat à afficher.");
			}
		} catch (ServiceException e) {
			throw new ServiceException("Une erreur est survenue : " + e.getMessage());
		}
	}
	
	/**
	 * function that gets the ID of a Vehicle and prints all the associated reservations if they exist in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void findResaByVehicleId(CliControler cli, Scanner sc) throws ServiceException {
		sc.reset();
		System.out.println("Entrez l'ID du véhicule dont vous voulez consulter les réservations");
		try {
			List<Reservation> listResa = cli.reservationService.findResaByVehicleId(sc.nextInt());
			if(listResa.size() > 0) {
				for(Reservation reservation : listResa) {
					System.out.println(reservation);
				}
			}
			else {
				System.out.println("Aucun résultat à afficher.");
			}
		} catch (ServiceException e) {
			throw new ServiceException("Une erreur est survenue" + e.getMessage());
		}
	}
	
	/**
	 * function that asks the user to enter parameters and then creates a new Reservation object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 */
	private static void addReservation(CliControler cli, Scanner sc) {
		Reservation reservation = new Reservation();
		sc.reset();
		System.out.println("Entrez le numéro de client");
		reservation.setClientId(new Client(sc.nextInt()));
		System.out.println("Entrez le numéro du véhicule");
		reservation.setVehicleId(new Vehicle(sc.nextInt()));
		sc.nextLine(); //clear buffer
		System.out.println("Entrez la date de début au format yyyy-[m]m-[d]d");
		reservation.setBeginning(Date.valueOf(sc.nextLine()));
		System.out.println("Entrez la date de fin au format yyyy-[m]m-[d]d");
		reservation.setEnd(Date.valueOf(sc.nextLine()));
		try {
			cli.reservationService.create(reservation);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la réservation : " + e.getMessage() + "\n");
		}
	}
	
	/**
	 * function that asks the user to enter one reservation object's ID and then delete 
	 * the associated Reservation in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void deleteReservation(CliControler cli, Scanner sc) throws ServiceException {
		printAllReservations(cli);
		sc.reset();
		System.out.println("Entrez l'ID de la réservation à supprimer :");
		Reservation resaToDelete = cli.reservationService.findById(sc.nextInt());
		if(resaToDelete != null) {
			cli.reservationService.delete(resaToDelete);
		}
		else {
			System.out.println("Cet ID de réservations n'existe pas.\n");
		}
	}
	
	/**
	 * function that asks the user to choose a Reservation by its ID, to enter 
	 * new parameters and then edit an existing Reservation object in the database
	 * @param cli : CliControler object, 
	 * @param sc : scanner object
	 * @throws ServiceException if there is an error during the execution
	 */
	private static void editReservation(CliControler cli, Scanner sc) throws ServiceException {
		printAllReservations(cli);
		sc.reset();
		Reservation reservationToEdit = new Reservation();
		Reservation reservationEdited = new Reservation();
		String vehicle = null;
		String client = null;
		String beginning = null;
		String end = null;
		System.out.println("Entrez l'ID de la réservation à modifier");
		try {
			reservationToEdit = cli.reservationService.findById(sc.nextInt());
			sc.nextLine();
			System.out.println("Entrer le nouveau numéro de client : ");
			client = sc.nextLine();
			System.out.println("Entrer le nouveau numéro de véhicule : ");
			vehicle = sc.nextLine();
			System.out.println("Entrer la nouvelle date de début : ");
			beginning = sc.nextLine();
			System.out.println("Entrer la nouvelle date de fin : ");
			end = sc.nextLine();
			reservationEdited = editReservationProperties(cli, reservationToEdit, vehicle, client, beginning, end);
			cli.reservationService.edit(reservationEdited);
		} catch (ServiceException e) {
			System.out.println("Erreur lors de la modification de la réservation : " + e.getMessage() + "\n");
		}
	}
	
	/**
	 * function that checks if all paramaters have been filled and if not, fill them by the 
	 * old parameters of the Reservation
	 * @param cli : CliControler object
	 * @param reservationToEdit : Reservation object to edit
	 * @param vehicle : id of the vehicle entered by the user
	 * @param client : id of the client entered by the user
	 * @param beginning : starting date entered by the user
	 * @param end : end date entered by the user
	 * @return reservationEdited the reservation with edited properties
	 */
	private static Reservation editReservationProperties(CliControler cli, Reservation reservationToEdit, String vehicle, String client, String beginning, String end) {
		Reservation reservationEdited = new Reservation();
		reservationEdited.setId(reservationToEdit.getId());
		if(vehicle != null && vehicle != ""  && !vehicle.isEmpty()) {
			int vehicleId = Integer.parseInt(vehicle);
			try {
				reservationEdited.setVehicleId(cli.vehicleService.findById(vehicleId));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			reservationEdited.setVehicleId(reservationToEdit.getVehicleId());
		}
		if(client != null && client != ""  && !client.isEmpty()) {
			int clientId = Integer.parseInt(client);
			try {
				reservationEdited.setClientId(cli.clientService.findById(clientId));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			reservationEdited.setClientId(reservationToEdit.getClientId());
		}
		if(beginning != null && beginning != ""  && !beginning.isEmpty()) {
			reservationEdited.setBeginning(Date.valueOf(beginning));
		} else {
			reservationEdited.setBeginning(reservationToEdit.getBeginning());
		}
		if(end != null && end != ""  && !end.isEmpty()) {
			reservationEdited.setEnd(Date.valueOf(end));
		} else {
			reservationEdited.setEnd(reservationToEdit.getEnd());
		}
		return reservationEdited;
	}
}
