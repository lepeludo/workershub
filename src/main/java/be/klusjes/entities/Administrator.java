package be.klusjes.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "administrators")
public class Administrator {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "user_id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastLogin;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAccount;
	private int numberOfLogins;

	public Administrator() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
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
	
	
	

}
