package com.epf.RentManager.controler.client;

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
import com.epf.RentManager.service.ClientService;
import com.epf.RentManager.service.ReservationService;

@WebServlet("/users/delete")

public class DeleteClientServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/delete.jsp");

		try {
			id = Integer.parseInt(request.getParameter("id"));
			int nbReservations = reservationService.findResaByClientId(id).size();
			int nbVehicles = reservationService.findVehiclesByClient(id).size();
			request.setAttribute("nbRents", nbReservations);
			request.setAttribute("nbVehicles", nbVehicles);
			request.setAttribute("user", clientService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("user", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/delete.jsp");
		try {		
			Client clientToDelete = clientService.findById(id);
			deleteAssociatedReservations();
			clientService.delete(clientToDelete);
			response.sendRedirect(request.getContextPath() + "/users");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "erreur :" + e.getMessage());
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void deleteAssociatedReservations() throws ServiceException {
		List<Reservation> listResaToDelete = reservationService.findResaByClientId(id);
		for(Reservation resaToDelete : listResaToDelete) {
			reservationService.delete(resaToDelete);
		}
	}
}
