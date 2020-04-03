package com.epf.RentManager.controler.vehicle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.VehicleService;

@WebServlet("/vehicles")

public class VehicleListServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
		
		try {
			request.setAttribute("vehicles", vehicleService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("vehicles", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
}
