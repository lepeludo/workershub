package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.Customer;

public interface CustomerDao {

	void update(Customer customer);

	Customer read(Long id);

	List<Customer> findCustomersByText(String keyword);
	
	List<String> findAllCustomerEmails();

}
