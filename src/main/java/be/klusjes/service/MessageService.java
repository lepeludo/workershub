package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Customer;
import be.klusjes.entities.Message;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Worker;
import be.klusjes.web.MessageForm;
import be.klusjes.web.MessageThreadForm;

public interface MessageService {
	void create(Message message);
	Message read(Long id);
	void update(Message message);
	void deleteMessage(Long id);
	void createThreadFromForm(MessageThreadForm messageThreadForm,
			Customer customer, Worker worker);
	void createMessageInExistingThreadFromForm(MessageForm messageForm, MessageThread messageThread);
	public List<Message> findMessagesByMessageThreadIdSortedByTime(long MessageThreadId);
}
