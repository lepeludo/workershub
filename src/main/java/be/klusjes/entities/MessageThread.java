package be.klusjes.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "messageThreads")
public class MessageThread implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "workerid")
	private Worker worker;

	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;

	private String subject;

	@OneToMany(mappedBy = "messageThread")
	private Set<Message> messages = new HashSet<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		// prevent endless loop
		if (this.worker != null) {
			if (worker.equals(this.worker))
				return;
		}
		// set new worker
		this.worker = worker;
		// set myself into new worker
		if (worker != null)
			worker.addMessageThread(this);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		// prevent endless loop
		if (customer.equals(this.customer))
			return;
		// set new worker
		this.customer = customer;
		// set myself into new worker
		if (customer != null)
			customer.addMessageThread(this);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		if (messages.contains(message)) {
			return;
		}
		messages.add(message);
		message.setMessageThread(this);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		MessageThread other = (MessageThread) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		}
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageThread [id=" + id + ", worker=" + worker + ", customer="
				+ customer + ", subject=" + subject + ", messages=" + messages
				+ "]";
	}

}
