package be.klusjes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.klusjes.entities.User;

@Service
public interface UserService {

	User read(String username);
	
	User readByEmail(String email);

	List<User> findAll();

	void create(User user);

	void delete(long id);

	void update(User user);
	
	List<String>findAllUsernames();

	void updatePassword(User user);
}
