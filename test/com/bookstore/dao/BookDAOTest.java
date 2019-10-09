package com.bookstore.dao;

import static org.junit.Assert.*;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest{
private static BookDAO bookDao;	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		
		Category category = new Category();
		category.setCategoryId(6);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.86f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		
		String imagePath  = "/⁨Users⁩/⁨pradeepsm⁩/⁨Desktop⁩/⁨Web_Projects⁩/⁨books⁩/EffectiveJava.jpg";
		String imagePath1 = imagePath.replaceAll("[\u2068\u2069]", "");
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath1));
		System.out.println(imageBytes);
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDao.create(newBook);
		assertTrue(createdBook.getBookId() > 0);
		
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existingBook = new Book();
		existingBook.setBookId(1);
		Category category = new Category();
		category.setCategoryId(6);
		existingBook.setCategory(category);
		
		existingBook.setTitle("Effective Java (1st Edition)");
		existingBook.setAuthor("Joshua Bloch");
		existingBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existingBook.setPrice(35f);
		existingBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existingBook.setPublishDate(publishDate);
		
		String imagePath  = "/⁨Users⁩/⁨pradeepsm⁩/⁨Desktop⁩/⁨Web_Projects⁩/⁨books⁩/EffectiveJava.jpg";
		String imagePath1 = imagePath.replaceAll("[\u2068\u2069]", "");
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath1));
		System.out.println(imageBytes);
		existingBook.setImage(imageBytes);
		
		Book updatedBook = bookDao.update(existingBook);
		assertEquals(updatedBook.getTitle(), "Effective Java (1st Edition)");
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFailed() {
		Integer bookId = 100;
		bookDao.delete(bookId);
	}
	
	@Test
	public void testDeleteBook() {
		Integer bookId = 0;
		bookDao.delete(bookId);
		assertTrue(true);
	}
	
	@Test
	public void testGetBookFailed() {
		Integer bookId = 99;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}
	
	@Test
	public void testGetBook() {
		Integer bookId = 1;
		bookDao.get(bookId);
		assertTrue(true);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBook = bookDao.listAll();
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExisting() {
		String title = "some test";
		Book book = bookDao.findByTitle(title);
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExisting() {
		String title = "Effective Java (1st Edition)";
		Book book = bookDao.findByTitle(title);
		assertNotNull(book);
	}
	
	@Test
	public void testCountAll() {
		long total = bookDao.count();
		assertTrue(total > 0);
		
	}
	
	

}
