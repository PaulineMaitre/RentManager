package com.epf.RentManager.controler.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ClientService;
import com.epf.RentManager.service.ReservationService;

@WebServlet("/users/details")

public class ClientDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Reservation> listReservations = reservationService.findResaByClientId(id);
			List<Vehicle> listVehicles = reservationService.findVehiclesByClient(id);
			request.setAttribute("nbRents", listReservations.size());
			request.setAttribute("nbVehicles", listVehicles.size());
			request.setAttribute("rents", listReservations);
			request.setAttribute("vehicles", listVehicles);
			request.setAttribute("user", clientService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "erreur :" + e.getMessage());
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
