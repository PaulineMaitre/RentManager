package com.epf.RentManager.controler.reservation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.classes.Reservation;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ReservationService;

@WebServlet("/rents/delete")

public class DeleteReservationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ReservationService reservationService = ReservationService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/delete.jsp");

		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("rent", reservationService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("rent", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/delete.jsp");
		try {		
			Reservation reservationToDelete = reservationService.findById(id);
			reservationService.delete(reservationToDelete);
			response.sendRedirect(request.getContextPath() + "/rents");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "erreur :" + e.getMessage());
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
