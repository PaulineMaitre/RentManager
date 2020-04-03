package com.epf.RentManager.controler.reservation; 

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

@WebServlet("/rents/create")

public class AddReservationServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final long serialVersionUID = 1L;
	ReservationService reservationService = ReservationService.getInstance();
	ClientService clientService = ClientService.getInstance();
	VehicleService vehicleService = VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		String selectedVehicle = null;
		String selectedClient = null;
		String beginningValue = null;
		String endValue = null;
		request.setAttribute("selectedVehicle", selectedVehicle);
		request.setAttribute("selectedClient", selectedClient);
		request.setAttribute("beginningValue", beginningValue);
		request.setAttribute("endValue", endValue);
		try {
			request.setAttribute("vehicles", vehicleService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("vehicles", "Une erreur est survenue");
		}
		try {
			request.setAttribute("users", clientService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("users", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		int clientId = Integer.parseInt(request.getParameter("client"));
		int vehicleId = Integer.parseInt(request.getParameter("vehicle"));
		Date beginning = Date.valueOf(request.getParameter("beginning"));
		Date end = Date.valueOf(request.getParameter("end"));
		
		Reservation newReservation= new Reservation();
		
		RequestDispatcher dispatcher = null;
		try {
			newReservation.setClientId(clientService.findById(clientId));
			newReservation.setVehicleId(vehicleService.findById(vehicleId));
			newReservation.setBeginning(beginning);
			newReservation.setEnd(end);
			reservationService.create(newReservation);
			response.sendRedirect(request.getContextPath() + "/rents");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			try {
				request.setAttribute("selectedVehicle", vehicleService.findById(vehicleId));
			} catch (ServiceException e3) {
				request.setAttribute("selectedVehicle", "Une erreur est survenue");
			}
			try {
				request.setAttribute("selectedClient", clientService.findById(clientId));
			} catch (ServiceException e3) {
				request.setAttribute("selectedClient", "Une erreur est survenue");
			}
			request.setAttribute("beginningValue", beginning);
			request.setAttribute("endValue", end);
			try {
				request.setAttribute("vehicles", vehicleService.findAll());
			} catch (ServiceException e1) {
				request.setAttribute("vehicles", "Une erreur est survenue");
			}
			try {
				request.setAttribute("users", clientService.findAll());
			} catch (ServiceException e2) {
				request.setAttribute("users", "Une erreur est survenue");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
			dispatcher.forward(request, response);
		}
	}

}
