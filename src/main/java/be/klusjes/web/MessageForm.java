package be.klusjes.web;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class MessageForm {
	@NotBlank
	@Length(min=5, max=250)
	private String text;
	private boolean workerBool;
	private Date date;

	public MessageForm() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	public boolean isWorkerBool() {
		return workerBool;
	}

	public void setWorkerBool(boolean workerBool) {
		this.workerBool = workerBool;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
