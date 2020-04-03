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

@WebServlet("/rents/edit")

public class EditReservationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	VehicleService vehicleService = VehicleService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
		
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
		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("testError", false);
			request.setAttribute("rent", reservationService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("rent", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		
		String vehicle = request.getParameter("vehicle");
		String client = request.getParameter("client");
		String beginning = request.getParameter("beginning");
		String end = request.getParameter("end");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
		try {		
			Reservation reservationToEdit = reservationService.findById(id);
			reservationToEdit = editReservation(reservationToEdit, vehicle, client, beginning, end);
			reservationService.edit(reservationToEdit);
			
			response.sendRedirect(request.getContextPath() + "/rents");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur lors de la modification : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			try {
				int vehicleId = Integer.parseInt(vehicle);
				request.setAttribute("selectedVehicle", vehicleService.findById(vehicleId));
			} catch (ServiceException e3) {
				request.setAttribute("selectedVehicle", "Une erreur est survenue");
			}
			try {
				int clientId = Integer.parseInt(client);
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
			try {
				request.setAttribute("rent", reservationService.findById(id));
			} catch (ServiceException e1) {
				request.setAttribute("rent", "Une erreur est survenue");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private Reservation editReservation(Reservation reservationToEdit, String vehicle, String client, String beginning, String end) {
		Reservation reservationEdited = new Reservation();
		reservationEdited.setId(reservationToEdit.getId());
		if(vehicle != null && vehicle != "") {
			int vehicleId = Integer.parseInt(vehicle);
			try {
				reservationEdited.setVehicleId(vehicleService.findById(vehicleId));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			reservationEdited.setVehicleId(reservationToEdit.getVehicleId());
		}
		if(client != null && client != "") {
			int clientId = Integer.parseInt(client);
			try {
				reservationEdited.setClientId(clientService.findById(clientId));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			reservationEdited.setClientId(reservationToEdit.getClientId());
		}
		if(beginning != null && beginning != "") {
			reservationEdited.setBeginning(Date.valueOf(beginning));
		} else {
			reservationEdited.setBeginning(reservationToEdit.getBeginning());
		}
		if(end != null && end != "") {
			reservationEdited.setEnd(Date.valueOf(end));
		} else {
			reservationEdited.setEnd(reservationToEdit.getEnd());
		}
		return reservationEdited;
	}

}
