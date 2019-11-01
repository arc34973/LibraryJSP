package de.liu.mylibrary.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.model.BorrowHistory;

public interface UserRepository {
		
	Collection<User> findByLastName(String lastName) throws DataAccessException;
	
	User findByUserName(String userName) throws DataAccessException;
	
	void save(User user) throws DataAccessException;
	
	Collection<BorrowHistory> findBorrowHistoryByUserId(int id) throws DataAccessException;
	
	User findByUserId(int id) throws DataAccessException;
	
	Collection<User> findAll();
	
	void delete(User user) throws DataAccessException;
	
	}
