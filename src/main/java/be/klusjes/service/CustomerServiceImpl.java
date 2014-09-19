package be.klusjes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.CustomerDao;
import be.klusjes.dao.interfaces.UserDao;
import be.klusjes.entities.Authority;
import be.klusjes.entities.Customer;
import be.klusjes.entities.User;
import be.klusjes.web.CustomerForm;
import be.klusjes.web.UpdateCustomerForm;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private UserDao userDao;
	private CustomerDao customerDao;
	@Autowired
	MailSendingServiceImpl mailSendingService;

	@Autowired
	public CustomerServiceImpl(UserDao userDao, CustomerDao customerDao) {
		this.userDao = userDao;
		this.customerDao = customerDao;
	}
	
	

	public void create(Customer customer) {

	}

	public void create(CustomerForm customerForm) {
		User user = CustomerFormClassToUserClass(customerForm);
		mailSendingService.sendConfirmationEmail(user);
		userDao.create(user);
	}
	
	@Override
	public Customer readById(Long id) {
		return customerDao.read(id);
	}

	public User CustomerFormClassToUserClass(CustomerForm customerForm) {

		// setting up user login information
		User user = new User();
		user.setEnabled(false);
		user.setRandomUUID(java.util.UUID.randomUUID().toString());
		user.setUsername(customerForm.getUsername());
		user.setPassword(customerForm.getPassword());
		
		// setting up security authority
		Authority authority = new Authority();
		authority.setAuthority(customerForm.getAuthority());
		
		// setting customerspecific properties
		Customer customer = new Customer();
		customer.setEmail(customerForm.getEmail());
		customer.setFirstName(customerForm.getFirstName());
		customer.setLastName(customerForm.getLastName());
		customer.setDateAccount(customerForm.getDateAccount());

		// build persistable user
		user.setAuthority(authority);
		user.setCustomer(customer);

		authority.setUser(user);

		customer.setUser(user);

		return user;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);

	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public Customer read(String username) {
		User user = userDao.read(username);
		return user.getCustomer();
	}

	@Override
	public List<Customer> findAll() {

		return null;
	}

	@Override
	public void update(UpdateCustomerForm updateCustomerForm, Customer customer) {
		// set other Worker properties in User object

		customer.setFirstName(updateCustomerForm.getFirstName());
		customer.setLastName(updateCustomerForm.getLastName());
		customer.setEmail(updateCustomerForm.getEmail());

		// steps to persist worker object via user object

		User user = customer.getUser();
		customer.setUser(user);
		user.setCustomer(customer);
		customerDao.update(customer);

	}

	@Override
	public List<Customer> findCustomersByText(String keyword) {

		return customerDao.findCustomersByText(keyword) ;
	}

	@Override
	public List<String> findAllCustomerEmails() {
		return customerDao.findAllCustomerEmails();
	}



}
