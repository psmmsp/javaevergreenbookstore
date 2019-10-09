package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.bookstore.entity.Users;
public class UsersTest {

	public static void main(String[] args) {
		Users usr1 = new Users();
		usr1.setEmail("sample@email.com");
		usr1.setFullName("sample test name");
		usr1.setPassword("Hello World");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usr1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("New user was insterted ");
		
	}

}
