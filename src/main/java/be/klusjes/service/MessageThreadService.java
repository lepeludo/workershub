package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Customer;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Worker;
import be.klusjes.web.MessageThreadForm;

public interface MessageThreadService {
	void create(MessageThread messageThread);
	List <MessageThread>  readByWorkerId (Long workerId);
	List< MessageThread>  readByCustomerId (Long customerId);
	MessageThread read(Long id);
	void update(MessageThread messageThread);
	void deleteMessageThread(Long id);
	public List<MessageThread> readByWorkerAndCustomerId(long workerId, long customerId);
	public void createThreadFromForm(MessageThreadForm messageThreadForm, Customer customer, Worker worker);
}
