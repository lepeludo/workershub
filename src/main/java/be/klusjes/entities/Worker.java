package be.klusjes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Long id;
	private String firstName;
	private String lastName;
	@SuppressWarnings("unused")
	private transient String name;
	private String email;
	private String extraInformation;
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "user_id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastLogin;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAccount;
	private int numberOfLogins = 0;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<Integer>reviewStars = new HashSet<>();
	

	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "workerlocations", joinColumns = @JoinColumn(name = "workerId"), inverseJoinColumns = @JoinColumn(name = "locatieId"))
	private List<Location> locations = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<String> phoneNumbers;

	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "workerJobTypes", joinColumns = @JoinColumn(name = "workerId"), inverseJoinColumns = @JoinColumn(name = "jobTypeId"))
	private List<JobType> jobTypes = new ArrayList<>();

	@OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<MessageThread> messageThreads = new ArrayList<>();
	
	@OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<Reviews> reviews = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private transient int currentReviewStars;

	public Worker() {
	}

	public List<Location> getLocations() {
		return locations;
	}


	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public List<JobType> getJobTypes() {
		return jobTypes;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setJobType(List<JobType> jobTypes) {
		this.jobTypes = jobTypes;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	public void addJobType(JobType jobType) {
		if (jobTypes.contains(jobType)) {
			return;
		}
		jobTypes.add(jobType);
		jobType.addWorker(this);

	}

	public void addLocation(Location location) {
		if (locations.contains(location)) {
			return;
		}
		locations.add(location);
		location.addWorker(this);

	}
	
	public void addReview(Reviews review) {
		if (reviews.contains(review)) {
			return;
		}
		reviews.add(review);
		review.addWorker(this);

	}

	public void removeJobType(JobType jobType) {
		if (!jobTypes.contains(jobType))
			return;
		jobTypes.remove(jobType);
		jobType.removeWorker(this);
	}

	public void removeLocation(Location location) {
		if (!locations.contains(location))
			return;
		jobTypes.remove(location);
		location.removeWorker(this);
	}
	
	public void setJobTypes(List<JobType> jobTypes) {
		this.jobTypes = jobTypes;
	}

	public Date getDateLastLogin() {
		return dateLastLogin;
	}

	public void setDateLastLogin(Date dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}

	public Date getDateAccount() {
		return dateAccount;
	}

	public void setDateAccount(Date dateAccount) {
		this.dateAccount = dateAccount;
	}

	public int getNumberOfLogins() {
		return numberOfLogins;
	}

	public void setNumberOfLogins(int numberOfLogins) {
		this.numberOfLogins = numberOfLogins;
	}

	public List<MessageThread> getMessageThreads() {
		return messageThreads;
	}

	public void setMessageThreads(List<MessageThread> messageThreads) {
		this.messageThreads = messageThreads;
	}

	@Override
	public int hashCode() {
		int result = (getFirstName().hashCode() + getEmail().hashCode()
				+ getLastName().hashCode() 
				+ getExtraInformation().hashCode() + getPhoneNumbers()
				.hashCode())+ getJobTypes().hashCode()+getLocations().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Worker)){
			return false;
		}
		Worker tempWorker = (Worker) obj;
		if(tempWorker.hashCode()==this.hashCode()){
			return true;
		}
		return false;
	}

	public String getName() {
		return firstName+" "+lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getReviewStars() {
		return reviewStars;
	}

	public void setReviewStars(Set<Integer> reviewStars) {
		this.reviewStars = reviewStars;
	}
	
	public void addReviewStars(Integer reviewStars){
		this.reviewStars.add(reviewStars);
	}
	
	public Integer getCurrentReviewStars(){
		int size = getReviewStars().size();
		int totalSum = 0;
		for (int reviewStar : getReviewStars()){
			totalSum += reviewStar;
		}
		try{
		Double temp = (double) (totalSum/size);
		this.setCurrentReviewStars(temp.intValue());
		return (temp.intValue());
		}catch(java.lang.ArithmeticException ex){
			return -1;
		}
	}

	public void setCurrentReviewStars(int currentReviewStars) {
		this.currentReviewStars = currentReviewStars;
	}

	public void addMessageThread(MessageThread messageThread) {
		if (messageThreads.contains(messageThread)) {
			return;
		}
		messageThreads.add(messageThread);
		messageThread.setWorker(this);
		
	}
	
	
	
}
