package be.klusjes.web;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import be.klusjes.entities.Customer;

public class UpdateCustomerForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(min=1, max=25)
	private String firstName;
	@Size(min=1, max=25)
	private String lastName;
	@Email 
	@NotBlank
	private String email;
	private final String authority = "customer";
	private final boolean enabled = true;

	
	public UpdateCustomerForm() {
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void addExistingCustomer(Customer customer) {
		this.email = customer.getEmail();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		
	}
	
	
	
}
