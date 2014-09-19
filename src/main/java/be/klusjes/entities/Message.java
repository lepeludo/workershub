package be.klusjes.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="messagetable")
public class Message {
	@Id
	@GeneratedValue
	public long id;
	
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "messageThreadId")
	private MessageThread messageThread;

	private boolean workerBool;
	private boolean readBool;

	public Message() {
	}

	public void setMessageThread(MessageThread messageThread) {
		// prevent endless loop
		if (messageThread.equals(this.messageThread))
			return;
		// set new worker
		this.messageThread = messageThread;
		// set myself into new worker
		if (messageThread != null)
			messageThread.addMessage(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public boolean isWorkerBool() {
		return workerBool;
	}

	public void setWorkerBool(boolean workerBool) {
		this.workerBool = workerBool;
	}

	public boolean isReadBool() {
		return readBool;
	}

	public void setReadBool(boolean readBool) {
		this.readBool = readBool;
	}

	public MessageThread getMessageThread() {
		return messageThread;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	

}
