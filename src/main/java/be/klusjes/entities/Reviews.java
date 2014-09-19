package be.klusjes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reviews")
public class Reviews implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "workerid")
	private Worker worker;

	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;
	private boolean reported;
	private boolean readByAdmin;

	public Reviews() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Worker getWorker() {
		return worker;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	private boolean sameAsFormerCustomer(Customer newCustomer) {
		return customer == null ? newCustomer == null : customer
				.equals(newCustomer);
	}

	public void setCustomer(Customer customer) {
		// prevent endless loop
		if (sameAsFormerCustomer(customer))
			return;
		// set new customer
		this.customer = customer;
		// set myself into new customer
		if (customer != null)
			customer.addReview(this);
	}

	private boolean sameAsFormerWorker(Worker newWorker) {
		return worker == null ? newWorker == null : worker.equals(newWorker);
	}

	public void setWorker(Worker worker) {
		// prevent endless loop
		if (sameAsFormerWorker(worker))
			return;
		// set new worker
		this.worker = worker;
		// set myself into new worker
		if (worker != null)
			worker.addReview(this);
	}

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public void addWorker(Worker worker) {
		if (this.worker != null) {
			if (this.worker.equals(worker)) {
				return;
			}
		}
		this.worker = worker;

	}

	public void addCustomer(Customer customer) {
		if (this.customer != null) {
			if (this.customer.equals(customer)) {
				return;
			}
		}
		this.customer = customer;
	}

	public boolean isReadByAdmin() {
		return readByAdmin;
	}

	public void setReadByAdmin(boolean readByAdmin) {
		this.readByAdmin = readByAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (readByAdmin ? 1231 : 1237);
		result = prime * result + (reported ? 1231 : 1237);
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reviews other = (Reviews) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (readByAdmin != other.readByAdmin)
			return false;
		if (reported != other.reported)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}

}