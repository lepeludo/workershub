package be.klusjes.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import be.klusjes.constraints.ListOfStringsNotEmptyChecker;
import be.klusjes.constraints.ListOfStringsPhonenumbersChecker;
import be.klusjes.constraints.UsernameExistsChecker;
import be.klusjes.constraints.WorkerEmailExistsChecker;

public class WorkerForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min = 2, max = 45)
	@NotBlank
	private String firstName;
	@Size(min = 2, max = 45)
	@NotBlank
	private String lastName;
	@WorkerEmailExistsChecker
	@Email
	@NotBlank
	private String email;
	@Size(min = 1, max = 45)
	@NotBlank
	@UsernameExistsChecker
	private String username;
	@Size(min = 1, max = 45)
	@NotBlank
	private String password;
	@Size(max = 450, message = "Can not be bigger than 450 characters")
	private String extraInformation;
	private final String authority = "worker";
	private final boolean enabled = true;
	private Date dateAccount;
	@ListOfStringsNotEmptyChecker
	@ListOfStringsPhonenumbersChecker
	private List<String> phoneNumbers = new ArrayList<>();
	@ListOfStringsNotEmptyChecker
	private List<String> jobTypes = new ArrayList<>();
	@ListOfStringsNotEmptyChecker
	private List<String> locations = new ArrayList<>();

	public WorkerForm() {
		phoneNumbers.add("");
		Calendar cal = Calendar.getInstance();
		dateAccount = cal.getTime();
	}

	public void addAPhonenumber() {
		phoneNumbers.add("");
	}

	public void removePhonenumbers() {
		phoneNumbers.clear();
		phoneNumbers.add("");
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

	public String getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(String extraInformation) {
		this.extraInformation = extraInformation;
	}

	public String getAuthority() {
		return authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(List<String> jobTypes) {
		this.jobTypes = jobTypes;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public Date getDateAccount() {
		return dateAccount;
	}

	public void setDateAccount(Date dateAccount) {
		this.dateAccount = dateAccount;
	}
}
