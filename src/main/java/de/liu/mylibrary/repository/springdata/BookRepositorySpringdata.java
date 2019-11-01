package de.liu.mylibrary.repository.springdata;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import de.liu.mylibrary.model.Book;
import de.liu.mylibrary.model.BorrowHistory;

import de.liu.mylibrary.repository.BookRepository;

public interface BookRepositorySpringdata extends BookRepository, Repository<Book, Integer> {

	
	@Query("SELECT DISTINCT book FROM Book book WHERE book.title like :title%")
	@Transactional(readOnly = true)
	Collection<Book> findByTitle(@Param("title") String title); 
	
	@Query("SELECT book FROM Book book where book.id = :id")
	@Transactional(readOnly = true)
	Book findByBookId(@Param("id") int id);
	
	@Query("SELECT borrowHistory FROM BorrowHistory borrowHistory where book.id = :id")
	@Transactional(readOnly = true)
	Collection<BorrowHistory> findBorrowHistoryByBookId(int id);
	
	@Query("SELECT book FROM Book book")
	List<Book> findAll();
	
	public void save(Book book);
	
	public void delete(Book book);
		
}
