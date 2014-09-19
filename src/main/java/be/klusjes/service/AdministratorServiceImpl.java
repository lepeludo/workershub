package be.klusjes.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.UserDao;
import be.klusjes.entities.Administrator;
import be.klusjes.entities.Authority;
import be.klusjes.entities.User;
import be.klusjes.web.AdministratorForm;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService{
	private UserDao userDao;

	@Autowired
	public AdministratorServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void create(AdministratorForm administratorForm) {
		User user  = this.AdministratorFormClassToUserClass(administratorForm);
		userDao.create(user);
	}
	
	public User AdministratorFormClassToUserClass(AdministratorForm administratorForm) {

		// setting up user login information
		User user = new User();
		user.setEnabled(true);
		user.setUsername(administratorForm.getUsername());
		user.setPassword(administratorForm.getPassword());
		
		// setting up security authority
		Authority authority = new Authority();
		authority.setAuthority(administratorForm.getAuthority());
		
		//setting administratorspecific properties
		Administrator administrator = new Administrator();
		Calendar cal = Calendar.getInstance();
		administrator.setDateAccount(cal.getTime());
		administrator.setEmail(administratorForm.getEmail());
		administrator.setName(administratorForm.getName());
		
		// build persistable user
		user.setAuthority(authority);
		user.setAdministrator(administrator);

		authority.setUser(user);

		administrator.setUser(user);

		return user;
	}


	@Override
	public Administrator read(String username) {
		User user = userDao.read(username);
		return user.getAdministrator();
	}

	@Override
	public void update(Administrator administrator) {
		User user = administrator.getUser();
		userDao.update(user);
		
		
	}
	
	
}
