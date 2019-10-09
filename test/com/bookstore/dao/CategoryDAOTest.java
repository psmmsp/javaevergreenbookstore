package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{
	
	private static CategoryDAO categoryDAO;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Functional");
		Category category = categoryDAO.create(newCat);
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Core Java");
		cat.setCategoryId(1);
		
		Category category = categoryDAO.update(cat);
		assertEquals(cat.getName(), category.getName());
		
	}

	@Test
	public void testGet() {
		Integer catId = 2;
		Category cat = categoryDAO.get(catId);
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		Integer catId = 1;
		categoryDAO.delete(catId);
		Category cat = categoryDAO.get(catId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> catLst = categoryDAO.listAll();
		assertTrue(catLst.size() > 0);
	}

	@Test
	public void testCount() {
		Long count = categoryDAO.count();
		assertTrue(count != 0);
	}
	
	@Test
	public void testFindByName() {
		String catName = "Functional";
		Category category = categoryDAO.findByName(catName);
		assertNotNull(category);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String catName = "Random Name";
		Category category = categoryDAO.findByName(catName);
		assertNull(category);
	}

}
