package be.klusjes.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class usernameForm {

	@Email
	@NotBlank
	public String username;
	
	public usernameForm() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
