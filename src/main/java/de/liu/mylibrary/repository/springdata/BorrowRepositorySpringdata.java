package de.liu.mylibrary.repository.springdata;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.liu.mylibrary.model.Book;
import de.liu.mylibrary.model.BorrowHistory;
import de.liu.mylibrary.repository.BorrowRepository;

public interface BorrowRepositorySpringdata extends BorrowRepository, Repository<Book, Integer> {

	
	@Query("SELECT borrowHistory FROM BorrowHistory borrowHistory where book.id = :id")
	@Transactional(readOnly = true)
	Collection<BorrowHistory> findBorrowHistoryByBookId(int id);
	
	@Query("SELECT borrowHistory FROM BorrowHistory borrowHistory where user.id = :id")
	@Transactional(readOnly = true)
	Collection<BorrowHistory> findBorrowHistoryByUserId(int id);
	
	@Query("SELECT borrowHistory FROM BorrowHistory borrowHistory where id = :id")
	@Transactional(readOnly = true)
	BorrowHistory findBorrowHistoryById(int id);
	
	@Transactional
	void save(BorrowHistory borrowHistory);
		
}
