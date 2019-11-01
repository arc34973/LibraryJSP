package de.liu.mylibrary.repository.springdata;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import de.liu.mylibrary.model.BorrowHistory;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.repository.UserRepository;

public interface UserRepositorySpringdata extends UserRepository, Repository<User, Integer> {

	
	@Query("SELECT DISTINCT user FROM User user WHERE user.lastName like :lastName%")
	@Transactional(readOnly = true)
	Collection<User> findByLastName(@Param("lastName") String lastName); 
	
	
	@Query("SELECT DISTINCT user FROM User user WHERE user.id= :id")
	@Transactional(readOnly = true)
	User findByUserId(@Param("id") int id);
	
	@Query("SELECT user FROM User user WHERE user.userName= :userName")
	@Transactional(readOnly = true)
	User findByUserName(String userName);
	
	public void save(User user);
	
	@Query("SELECT user FROM User user")
	Collection<User> findAll();
		
	@Query("SELECT borrowHistory FROM BorrowHistory borrowHistory where user.id = :id")
	@Transactional(readOnly = true)
	Collection<BorrowHistory> findBorrowHistoryByUserId(int id);
	
//	@Modifying
//	@Query("DELETE FROM User user where user.id = :id")
//	void deleteUser(@Param("id") int id);
//	
	public void delete(User user);
				
		
}
