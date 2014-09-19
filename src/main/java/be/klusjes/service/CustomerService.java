package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Customer;
import be.klusjes.web.CustomerForm;
import be.klusjes.web.UpdateCustomerForm;

public interface CustomerService {
	void delete(long id);

	void update(Customer customer);

	void create(Customer customer);

	Customer read(String username);

	List<Customer> findAll();

	public void create(CustomerForm customerForm);

	void update(UpdateCustomerForm updateCustomerForm, Customer customer);

	Customer readById(Long idAll);

	List<Customer> findCustomersByText(String keyword);
	
	List<String> findAllCustomerEmails();
}
