package com.epf.RentManager.controler.vehicle;

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
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ReservationService;
import com.epf.RentManager.service.VehicleService;

@WebServlet("/vehicles/details")

public class VehicleDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = VehicleService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Reservation> listReservations = reservationService.findResaByVehicleId(id);
			List<Client> listClients = reservationService.findClientsByVehicle(id);
			request.setAttribute("nbRents", listReservations.size());
			request.setAttribute("nbClients", listClients.size());
			request.setAttribute("rents", listReservations);
			request.setAttribute("users", listClients);
			request.setAttribute("vehicle", vehicleService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("vehicle", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
