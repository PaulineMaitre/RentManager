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

@WebServlet("/vehicles/edit")

public class EditVehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = VehicleService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
		
		String manufacturerValue = null;
		String modelValue = null;
		String nbSeatsValue = null;
		request.setAttribute("manufacturerValue", manufacturerValue);
		request.setAttribute("modelValue", modelValue);
		request.setAttribute("nbSeatsValue", nbSeatsValue);

		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("vehicle", vehicleService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("vehicle", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		
		String manufacturer = request.getParameter("manufacturer");
		String model = request.getParameter("model");
		String nbSeats = request.getParameter("nbSeats");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
		try {		
			Vehicle vehicleToEdit = vehicleService.findById(id);
			vehicleToEdit = editVehicle(vehicleToEdit, manufacturer, model, nbSeats);
			vehicleService.edit(vehicleToEdit);
			
			response.sendRedirect(request.getContextPath() + "/vehicles/details?id=" + id);
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur lors de la modification : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			request.setAttribute("manufacturerValue", manufacturer);
			request.setAttribute("modelValue", model);
			request.setAttribute("nbSeatsValue", nbSeats);
			try {
				request.setAttribute("vehicle", vehicleService.findById(id));
			} catch (ServiceException e1) {
				request.setAttribute("vehicle", "Une erreur est survenue");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private Vehicle editVehicle(Vehicle vehicleToEdit, String manufacturer, String model, String nbSeats) {
		Vehicle vehicleEdited = new Vehicle();
		vehicleEdited.setId(vehicleToEdit.getId());
		if(manufacturer != null && manufacturer != "") {
			vehicleEdited.setManufacturer(manufacturer);
		} else {
			vehicleEdited.setManufacturer(vehicleToEdit.getManufacturer());
		}
		if(model != null && model != "") {
			vehicleEdited.setModel(model);
		} else {
			vehicleEdited.setModel(vehicleToEdit.getModel());
		}
		if(nbSeats != null && nbSeats != "") {
			vehicleEdited.setNbSeats(Integer.parseInt(nbSeats));
		} else {
			vehicleEdited.setNbSeats(vehicleToEdit.getNbSeats());
		}
		return vehicleEdited;
	}

}
