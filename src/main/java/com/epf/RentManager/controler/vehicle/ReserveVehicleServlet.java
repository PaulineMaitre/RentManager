package com.epf.RentManager.controler.vehicle; 

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ClientService;
import com.epf.RentManager.service.ReservationService;
import com.epf.RentManager.service.VehicleService;

/**
 * Servlet implementation class HomeServlet
 */

@WebServlet("/vehicles/reserve")

public class ReserveVehicleServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final long serialVersionUID = 1L;
	ReservationService reservationService = ReservationService.getInstance();
	ClientService clientService = ClientService.getInstance();
	VehicleService vehicleService = VehicleService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/reserve.jsp");
		
		String selectedClient = null;
		String beginningValue = null;
		String endValue = null;
		request.setAttribute("selectedClient", selectedClient);
		request.setAttribute("beginningValue", beginningValue);
		request.setAttribute("endValue", endValue);
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("vehicle", vehicleService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("vehicle", "Une erreur est survenue");
		}
		try {
			request.setAttribute("testError", false);
			request.setAttribute("users", clientService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("users", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		int clientId = Integer.parseInt(request.getParameter("client"));
		Date beginning = Date.valueOf(request.getParameter("beginning"));
		Date end = Date.valueOf(request.getParameter("end"));
		
		Reservation newReservation= new Reservation();
		
		RequestDispatcher dispatcher = null;
		try {
			newReservation.setClientId(clientService.findById(clientId));
			newReservation.setVehicleId(vehicleService.findById(id));
			newReservation.setBeginning(beginning);
			newReservation.setEnd(end);
			reservationService.create(newReservation);
			response.sendRedirect(request.getContextPath() + "/vehicles/details?id=" + id);
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur lors de la réservation : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			try {
				request.setAttribute("selectedClient", clientService.findById(clientId));
			} catch (ServiceException e3) {
				request.setAttribute("selectedClient", "Une erreur est survenue");
			}
			request.setAttribute("beginningValue", beginning);
			request.setAttribute("endValue", end);
			try {
				request.setAttribute("vehicle", vehicleService.findById(id));
			} catch (ServiceException e1) {
				request.setAttribute("vehicle", "Une erreur est survenue");
			}
			try {
				request.setAttribute("users", clientService.findAll());
			} catch (ServiceException e2) {
				request.setAttribute("users", "Une erreur est survenue");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/reserve.jsp");
			dispatcher.forward(request, response);
		}
	}

}
