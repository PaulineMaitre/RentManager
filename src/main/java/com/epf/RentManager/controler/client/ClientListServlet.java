package com.epf.RentManager.controler.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ClientService;

@WebServlet("/users")

public class ClientListServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
		
		try {
			request.setAttribute("users", clientService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("users", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
}
