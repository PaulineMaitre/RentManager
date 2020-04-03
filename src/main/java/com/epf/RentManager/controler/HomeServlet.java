package com.epf.RentManager.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ClientService;
import com.epf.RentManager.service.ReservationService;
import com.epf.RentManager.service.VehicleService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	
	// GET INSTANCES OF THE 3 FOLLOWING CLASSES : CLIENTSERVICE, VEHCILESERVICE, RESERVATIONSERVICE
	
	ClientService clientService = ClientService.getInstance();
	VehicleService vehicleService = VehicleService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		
		try {
			List<Client> listClients = clientService.findAll();
			List<Vehicle> listVehicles = vehicleService.findAll();
			List<Reservation> listReservations = reservationService.findAll();
			
			request.setAttribute("nbClients", listClients.size());
			request.setAttribute("nbVehicles", listVehicles.size());
			request.setAttribute("nbRents", listReservations.size());
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
