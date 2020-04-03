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

@WebServlet("/users/edit")

public class EditClientServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ClientService clientService = ClientService.getInstance();
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");

		String lastNameValue = null;
		String firstNameValue = null;
		String emailValue = null;
		String birthdayValue = null;
		request.setAttribute("lastNameValue", lastNameValue);
		request.setAttribute("firstNameValue", firstNameValue);
		request.setAttribute("emailValue", emailValue);
		request.setAttribute("birthdayValue", birthdayValue);
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("user", clientService.findById(id));
		} catch (ServiceException e) {
			request.setAttribute("user", "Une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // handle accents
		
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
		try {		
			Client clientToEdit = clientService.findById(id);
			clientToEdit = editClient(clientToEdit, lastName, firstName, email, birthday);
			clientService.edit(clientToEdit);
			response.sendRedirect(request.getContextPath() + "/users/details?id=" + id);
		} catch (ServiceException e) {
			request.setAttribute("errorMessage","<strong>Erreur lors de la modification : </strong>" + e.getMessage());
			request.setAttribute("testError", true);
			request.setAttribute("lastNameValue", lastName);
			request.setAttribute("firstNameValue", firstName);
			request.setAttribute("emailValue", email);
			request.setAttribute("birthdayValue", birthday);
			try {
				request.setAttribute("user", clientService.findById(id));
			} catch (ServiceException e1) {
				request.setAttribute("user", "Une erreur est survenue");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private Client editClient(Client clientToEdit, String lastName, String firstName, String email, String birthday) {
		Client clientEdited = new Client();
		clientEdited.setId(clientToEdit.getId());
		if(lastName != null && lastName != "") {
			clientEdited.setLastName(lastName);
		} else {
			clientEdited.setLastName(clientToEdit.getLastName());
		}
		if(firstName != null && firstName != "") {
			clientEdited.setFirstName(firstName);
		} else {
			clientEdited.setFirstName(clientToEdit.getFirstName());
		}
		if(email != null && email != "") {
			clientEdited.setEmail(email);
		} else {
			clientEdited.setEmail(clientToEdit.getEmail());
		}
		if(birthday != null && birthday != "") {
			clientEdited.setBirthday(Date.valueOf(birthday));
		} else {
			clientEdited.setBirthday(clientToEdit.getBirthday());
		}
		return clientEdited;
	}

}
