package com.epf.RentManager.controler.vehicle; 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epf.RentManager.classes.Vehicle;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.VehicleService;

/**
 * Servlet implementation class HomeServlet
 */

@WebServlet("/vehicles/create")

public class AddVehicleServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
		String manufacturerValue = null;
		String modelValue = null;
		String nbSeatsValue = null;
		request.setAttribute("manufacturerValue", manufacturerValue);
		request.setAttribute("modelValue", modelValue);
		request.setAttribute("nbSeatsValue", nbSeatsValue);
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // hande accents
		String manufacturer = request.getParameter("manufacturer");
		String model = request.getParameter("model");
		int nbSeats = Integer.valueOf(request.getParameter("nbSeats"));
		
		Vehicle newVehicle= new Vehicle();
		newVehicle.setManufacturer(manufacturer);
		newVehicle.setModel(model);
		newVehicle.setNbSeats(nbSeats);
		RequestDispatcher dispatcher = null;
		try {
			vehicleService.create(newVehicle);
			response.sendRedirect(request.getContextPath() + "/vehicles");
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			request.setAttribute("manufacturerValue", manufacturer);
			request.setAttribute("modelValue", model);
			request.setAttribute("nbSeatsValue", nbSeats);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
			dispatcher.forward(request, response);
		}
	}

}
