package be.klusjes.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import be.klusjes.entities.Customer;
import be.klusjes.entities.Reviews;
import be.klusjes.entities.Worker;

public class ReviewForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min = 15, max = 45)
	@NotBlank
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private Long workerId;

	public ReviewForm() {
		//inhoud verplaatst uit convertreviewform to review
		Calendar cal = Calendar.getInstance();
		date = cal.getTime();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public Reviews convertReviewFormToReview(Customer customer, Worker worker) {
		Reviews review = new Reviews();
		review.setText(this.text);
		customer.addReview(review);
		worker.addReview(review);
		review.setDate(date);
		return review;
	}

}
