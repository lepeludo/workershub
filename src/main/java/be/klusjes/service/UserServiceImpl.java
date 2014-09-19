package be.klusjes.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.UserDao;
import be.klusjes.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MailSendingServiceImpl mailSendingService;

	private ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);

	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void create(User user) {
		userDao.create(user);

	}

	@Override
	public User read(String username) {
		User user = userDao.read(username);
		return user;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<String> findAllUsernames() {
		return userDao.findAllUsernames();
	}

	@Override
	public User readByEmail(String email) {
		return userDao.readByEmail(email);
	}

	@Override
	public void updatePassword(User user) {
		String newPassword = UserServiceImpl.generateRandomPassword();
		System.out.println(newPassword);
		user.setPassword(shaPasswordEncoder.encodePassword(newPassword,
				user.getUsername()));
		if (user.getAuthority().getAuthority().equals("worker")) {
			mailSendingService.sendMail(null, user.getWorker().getEmail(),
					"new password",
					"you have requested a new password.\r\nYour new password is: "
							+ newPassword);
		} else if (user.getAuthority().getAuthority().equals("customer")) {
			mailSendingService.sendMail(null, user.getCustomer().getEmail(),
					"new password",
					"you have requested a new password.\r\nYour new password is: "
							+ newPassword);
		}
	}

	public static String generateRandomPassword() {
		Random RANDOM = new SecureRandom();
		int PASSWORD_LENGTH = 12;

		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789_-";

		String pw = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			pw += letters.substring(index, index + 1);
		}
		return pw;
	}

}
