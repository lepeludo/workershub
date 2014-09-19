package be.klusjes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	long id;
	private String firstName;
	private String lastName;
	private String email;
	@OneToOne
	private User user;
    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<Reviews>reviews = new ArrayList<>();
	
	@OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<MessageThread> messageThreads = new ArrayList<>();
	
	private long numberOfDeleteReviews;
	private long numberOfReportedReviews;
	private long numberOfReviewsPosted;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastLogin;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAccount;
	private long numberOfLogins = 0;


	public Customer() {
	}
	
	
	public void addReview(Reviews review) {
		if (reviews.contains(review)) {
			return;
		}
		reviews.add(review);
		review.addCustomer(this);

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}


	public List<Reviews> getReviews() {
		return reviews;
	}


	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
	
	public long getNumberOfDeleteReviews() {
		return numberOfDeleteReviews;
	}


	public void setNumberOfDeleteReviews(long numberOfDeleteReviews) {
		this.numberOfDeleteReviews = numberOfDeleteReviews;
	}


	public long getNumberOfReportedReviews() {
		return numberOfReportedReviews;
	}


	public void setNumberOfReportedReviews(long numberOfReportedReviews) {
		this.numberOfReportedReviews = numberOfReportedReviews;
	}


	public long getNumberOfReviewsPosted() {
		return numberOfReviewsPosted;
	}


	public void setNumberOfReviewsPosted(long numberOfReviewsPosted) {
		this.numberOfReviewsPosted = numberOfReviewsPosted;
	}
	
	


	public long getNumberOfLogins() {
		return numberOfLogins;
	}


	public void setNumberOfLogins(long numberOfLogins) {
		this.numberOfLogins = numberOfLogins;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
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


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Customer)){
			return false;
		}
		Customer temp = (Customer) obj;
		if(temp.hashCode() == this.hashCode()){
			return true;
		}
		return false;
	}

	

	public List<MessageThread> getMessageThreads() {
		return messageThreads;
	}


	public void setMessageThreads(List<MessageThread> messageThreads) {
		this.messageThreads = messageThreads;
	}


	public void addMessageThread(MessageThread messageThread) {
		if (messageThreads.contains(messageThread)) {
			return;
		}
		messageThreads.add(messageThread);
		messageThread.setCustomer(this);

	}
	
}
