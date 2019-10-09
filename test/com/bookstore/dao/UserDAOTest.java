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

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{

private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users usr1 = new Users();
		usr1.setEmail("userdao99@gmail.com");
		usr1.setFullName("dao test");
		usr1.setPassword("DAOHelloWorld");
		usr1 = userDAO.create(usr1);
		assertTrue(usr1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersNotFieldset() {
		Users usr1 = new Users();
		usr1 = userDAO.create(usr1);
		
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("psm99@psm.com");
		user.setFullName("daopsm");
		user.setPassword("psmpsm");
		user = userDAO.update(user);
		String expected = "psmpsm";
		String actual = user.getPassword();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users usr = userDAO.get(userId);
		if(usr != null) {
			System.out.println(usr.getEmail());
		}
		assertNotNull(usr);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users usr = userDAO.get(userId);
		assertNull(usr);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteUser() {
		Integer userId = 2;
		userDAO.delete(userId);
		Users usr = userDAO.get(userId);
		assertNull(usr);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistingUser() {
		Integer userId = 55;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> usrLst = userDAO.listAll();
		assertTrue(usrLst.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertNotEquals(totalUsers, 0);
	}
	
	@Test
	public void testFindByEmai() {
		String email = "psm123@gmail.com";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}
	
	@Test
	public void testLoginSuccess() {
		String email = "psm@psm.com";
		String password = "pass";
		boolean actual = userDAO.checkLogin(email, password);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
