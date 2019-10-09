package com.bookstore.service;

import java.io.IOException;
import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private EntityManager entityManager;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		userDAO = new UserDAO(entityManager);
	}
	
	public void listUser() throws ServletException, IOException{
		listUser(null);
	}
	
	public void listUser(String message) throws ServletException, IOException{
		List<Users> usrLst = userDAO.listAll();
		
		request.setAttribute("usrLst", usrLst);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "user_list.jsp";
		RequestDispatcher requestdispatcher = request.getRequestDispatcher(listPage);
		requestdispatcher.forward(request, response);
		
	}
	
	public void createUser() throws ServletException, IOException{
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users existingUser = userDAO.findByEmail(email);
		if(existingUser != null) {
			String message = "Could not create user. A user with email "+ email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else {
			Users newUsr = new Users(email, fullname, password);
			userDAO.create(newUsr);
			listUser("New user created");
		}
	}
	
	public void editUser() throws ServletException, IOException{
		 Integer userId = Integer.parseInt(request.getParameter("id"));
		 Users usr = userDAO.get(userId);
		 String destPage = "user_form.jsp";
		 if(usr == null) {
			 destPage = "message.jsp";
			 String errorMsg = "Could not find the user with "+ userId;
			 request.setAttribute("message", errorMsg);
		 }else {
			 request.setAttribute("user", usr);

		 }
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		 requestDispatcher.forward(request, response);
		 
	}
	
	public void updateUser() throws ServletException, IOException {
		Integer userid = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		Users userById = userDAO.get(userid);
		Users userByEmail = userDAO.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update the user. User with email " + email +" already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			userById.setEmail(email);
			userById.setFullName(fullname);
			
			if (password != null & !password.isEmpty()) {
				String encryptedPassword = HashGenerator.generateMD5(password);
				userById.setPassword(encryptedPassword);				
			}
			
			userDAO.update(userById);

			String message = "User has been updated successfully";
			listUser(message);
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		Users usr = userDAO.get(userId);
		String message = "User has been successfully deleted";
		if(usr == null) {
			message = "Could not find the user with ID: "+ userId;
			request.setAttribute("message",message);
			request.getRequestDispatcher("message.jsp");
		}else {
			userDAO.delete(userId);
			listUser(message);
		}
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean loginResult = userDAO.checkLogin(email, password);
		if(loginResult) {
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
		
}
