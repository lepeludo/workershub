package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.User;

public interface UserDao {

	void delete(long id);

	void update(User user);

	void create(User user);

	User read(String username);
	
	User readByEmail(String email);

	List<User> findAll();
	
	List<String>findAllUsernames();
		
}
