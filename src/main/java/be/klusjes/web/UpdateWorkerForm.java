package be.klusjes.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import be.klusjes.constraints.ListOfStringsNotEmptyChecker;
import be.klusjes.constraints.ListOfStringsPhonenumbersChecker;
import be.klusjes.entities.JobType;
import be.klusjes.entities.Location;
import be.klusjes.entities.Worker;

public class UpdateWorkerForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min = 2, max = 45)
	@NotBlank
	private String firstName;
	@Size(min = 2, max = 45)
	@NotBlank
	private String lastName;
	@Email
	@NotBlank
	private String email;
	@Size(max = 450, message="Can not be bigger than 450 characters")
	private String extraInformation;
	private final String authority = "worker";
	private final boolean enabled = true;
	@ListOfStringsNotEmptyChecker
	@ListOfStringsPhonenumbersChecker
	private List<String> phoneNumbers = new ArrayList<>();
	@ListOfStringsNotEmptyChecker
	private List<String> jobTypes = new ArrayList<>();
	@ListOfStringsNotEmptyChecker
	private List<String> locations = new ArrayList<>();

	public UpdateWorkerForm() {
		
	}

	public void addExistingWorker(Worker worker) {
		this.setEmail(worker.getEmail());
		this.setExtraInformation(worker.getExtraInformation());
		this.setFirstName(worker.getFirstName());
		this.setLastName(worker.getLastName());
		phoneNumbers.addAll(worker.getPhoneNumbers());
		for (JobType jobType : worker.getJobTypes()) {
			jobTypes.add(String.valueOf(jobType.getId()));
		}
		for (Location location : worker.getLocations()) {
			locations.add(String.valueOf(location.getId()));
		}

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

}
