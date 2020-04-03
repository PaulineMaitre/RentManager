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
import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ReservationService;
import com.epf.RentManager.service.VehicleService;

@WebServlet("/vehicles/delete")

public class DeleteVehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = VehicleService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/delete.jsp");

		try {
			id = Integer.parseInt(request.getParameter("id"));
			List<Reservation> listReservations = reservationService.findResaByVehicleId(id);
			List<Client> listClients = reservationService.findClientsByVehicle(id);
			request.setAttribute("nbRents", listReservations.size());
			request.setAttribute("nbClients", listClients.size());
			request.setAttribute("vehicle", vehicleService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("vehicle", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/delete.jsp");
		try {		
			Vehicle vehicleToDelete = vehicleService.findById(id);
			deleteAssociatedReservations();
			vehicleService.delete(vehicleToDelete);
			response.sendRedirect(request.getContextPath() + "/vehicles");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "erreur :" + e.getMessage());
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void deleteAssociatedReservations() throws ServiceException {
		List<Reservation> listResaToDelete = reservationService.findResaByVehicleId(id);
		for(Reservation resaToDelete : listResaToDelete) {
			reservationService.delete(resaToDelete);
		}
	}
}
