package be.klusjes.web;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import be.klusjes.constraints.UsernameExistsChecker;

public class AdministratorForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min = 2, max = 45)
	@NotBlank
	private String name;
	@Email
	private String email;
	@Size(min = 1, max = 45)
	@NotBlank
	@UsernameExistsChecker
	private String username;
	@Size(min = 1, max = 45)
	@NotBlank
	private String password;
	private final String authority = "administrator";

	public AdministratorForm() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}
	
	

}
