package com.bookstore.dao;

import java.util.List;

public interface GenericDAO<E>{
	
	public E create(E E);
	
	public E update(E E);
	
	public E get(Object id);
	
	public void delete(Object id);
	
	public List<E> listAll();
	
	public long count();
}
