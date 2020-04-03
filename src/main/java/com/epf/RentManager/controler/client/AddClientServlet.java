package com.epf.RentManager.controler.client; 

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.RentManager.classes.Client;
import com.epf.RentManager.exceptions.ServiceException;
import com.epf.RentManager.service.ClientService;

@WebServlet("/users/create")

public class AddClientServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
		//boolean testError = false;
		String lastNameValue = null;
		String firstNameValue = null;
		String emailValue = null;
		String birthdayValue = null;
		request.setAttribute("lastNameValue", lastNameValue);
		request.setAttribute("firstNameValue", firstNameValue);
		request.setAttribute("emailValue", emailValue);
		request.setAttribute("birthdayValue", birthdayValue);
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		Date birthday = Date.valueOf(request.getParameter("birthday"));
		
		Client newClient = new Client();
		newClient.setEmail(email);
		newClient.setLastName(lastName);
		newClient.setFirstName(firstName);
		newClient.setBirthday(birthday);
		RequestDispatcher dispatcher = null;
		try {
			clientService.create(newClient);
			response.sendRedirect(request.getContextPath() + "/users");
		
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			request.setAttribute("lastNameValue", lastName);
			request.setAttribute("firstNameValue", firstName);
			request.setAttribute("emailValue", email);
			request.setAttribute("birthdayValue", birthday);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
			dispatcher.forward(request, response);
		}
	}

}
