package de.liu.mylibrary.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import de.liu.mylibrary.model.BorrowHistory;

public interface BorrowRepository {
		
	Collection<BorrowHistory> findBorrowHistoryByUserId(int id) throws DataAccessException;
	Collection<BorrowHistory> findBorrowHistoryByBookId(int id) throws DataAccessException;
	BorrowHistory findBorrowHistoryById(int id) throws DataAccessException;
	void save(BorrowHistory borrowHistory) throws DataAccessException;
}
