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

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		categoryDAO = new CategoryDAO(entityManager);
	}
	
	public void createCategory() throws ServletException, IOException {
		String catName = request.getParameter("categoryname");
		Category existingCat = categoryDAO.findByName(catName);
		if(existingCat != null) {
			String message = "Could not create category. A category with name "+ catName + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else {
			Category newCat = new Category(catName);
			categoryDAO.create(newCat);
			listCategory("New Category created");
		}
	}
	
	public void listCategory() throws ServletException, IOException{
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException{
		List<Category> catLst = categoryDAO.listAll();
		
		request.setAttribute("catLst", catLst);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestdispatcher = request.getRequestDispatcher(listPage);
		requestdispatcher.forward(request, response);
		
	}

	public void editCategory() throws ServletException, IOException{
		Integer catId = Integer.parseInt(request.getParameter("id"));
		Category cat = categoryDAO.get(catId);
		String destPage = "category_form.jsp";
		if(cat == null) {
			destPage = "message.jsp";
			String errorMsg = "Could not find the category with "+catId;
			request.setAttribute("message", errorMsg);
		}else {
			request.setAttribute("category", cat);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException{
		Integer catId = Integer.parseInt(request.getParameter("categoryId"));
		String catName = request.getParameter("categoryname");
		Category categoryById = categoryDAO.get(catId); 
		Category categoryByName = categoryDAO.findByName(catName);
		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update the category, The category with name "+catName+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else {
			categoryById.setName(catName);
			categoryDAO.update(categoryById);
			String message = "Category updated successfully";
			listCategory(message);
			
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer catId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(catId);
		
		if(category != null) {
			String message = "Deleted Category Successfully with ID: "+ catId;
			request.setAttribute("message", message);
			categoryDAO.delete(catId);
			listCategory(message);
		}else {
			String message = "Category not found in DB";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	
	
	

}
