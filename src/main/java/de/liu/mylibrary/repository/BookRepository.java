package de.liu.mylibrary.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import de.liu.mylibrary.model.Book;
import de.liu.mylibrary.model.BorrowHistory;

public interface BookRepository {

	Collection<Book> findByTitle(String title) throws DataAccessException ;
		
	Book findByBookId(int id) throws DataAccessException;
	
	Collection<BorrowHistory> findBorrowHistoryByBookId(int id) throws DataAccessException;
	
	List<Book> findAll();

	void save(Book book) throws DataAccessException;
	
	void delete(Book book) throws DataAccessException;
	
}
