package de.liu.mylibrary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.liu.mylibrary.model.Book;
import de.liu.mylibrary.model.BorrowHistory;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.repository.BookRepository;
import de.liu.mylibrary.repository.BorrowRepository;
import de.liu.mylibrary.repository.UserRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

	private UserRepository userRepository;
	private BookRepository bookRepository;
	private BorrowRepository borrowRepository;

	@Autowired
	public LibraryServiceImpl(UserRepository userRepository, BookRepository bookRepository, BorrowRepository borrowRepository) {
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
		this.borrowRepository = borrowRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<User> findByLastName(String lastName) throws DataAccessException {
		return userRepository.findByLastName(lastName);
	}

	@Override
	@Transactional
	public void save(User user) throws DataAccessException {
		userRepository.save(user);

	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Book> findByTitle(String title) throws DataAccessException {
		return bookRepository.findByTitle(title);
	}

	@Override
	public void save(Book book) throws DataAccessException {
		bookRepository.save(book);

	}

	@Override
	public User findByUserId(int id) throws DataAccessException {
		return userRepository.findByUserId(id);
	}

	@Override
	public Book findByBookId(int id) throws DataAccessException {
		return bookRepository.findByBookId(id);
	}

	@Override
	public Collection<BorrowHistory> findBorrowHistoryByUserId(int id) throws DataAccessException {
		return userRepository.findBorrowHistoryByUserId(id);
	}

	@Override
	public Collection<Book> findAllBooks() throws DataAccessException {
		return bookRepository.findAll();
	}
	
	@Override
	public Collection<User> findAllUsers() throws DataAccessException {
		return userRepository.findAll();
	}
	
	
	@Override
	public void delete(User user) throws DataAccessException {
		userRepository.delete(user);

	}

	@Override
	public Collection<BorrowHistory> findBorrowHistoryByBookId(int id) throws DataAccessException {
		return borrowRepository.findBorrowHistoryByBookId(id);
	}

	@Override
	public User findByUserName(String userName) throws DataAccessException {
		return userRepository.findByUserName(userName);
	}

	@Override
	public BorrowHistory findBorrowHistoryById(int id) throws DataAccessException {
		return borrowRepository.findBorrowHistoryById(id);
	}

	@Override
	public void save(BorrowHistory borrowHistory) throws DataAccessException {
		borrowRepository.save(borrowHistory);
		
	}

	@Override
	public void delete(Book book) throws DataAccessException {
		bookRepository.delete(book);
		
	}

		
	

}
