package be.klusjes.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.MessageDao;
import be.klusjes.entities.Customer;
import be.klusjes.entities.Message;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Worker;
import be.klusjes.web.MessageForm;
import be.klusjes.web.MessageThreadForm;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;

	@Autowired
	public MessageServiceImpl(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public void create(Message messageThread) {
		messageDao.create(messageThread);

	}


	@Override
	public Message read(Long id) {
		return messageDao.read(id);
	}

	@Override
	public void update(Message messageThread) {
		messageDao.update(messageThread);

	}

	@Override
	public void deleteMessage(Long id) {
		messageDao.deleteMessage(id);

	}


	@Override
	public void createThreadFromForm(MessageThreadForm messageThreadForm,
			Customer customer, Worker worker) {
		MessageThread messageThread = new MessageThread();
		messageThread.setWorker(worker);
		messageThread.setCustomer(customer);
		messageThread.setSubject(messageThreadForm.getSubject());
		Message message = new Message();
		message.setDate(Calendar.getInstance().getTime());
		message.setMessageThread(messageThread);
		message.setReadBool(false);
		message.setText(messageThreadForm.getText());
		message.setWorkerBool(false);
		messageThread.addMessage(message);
		message.setMessageThread(messageThread);
		messageDao.create(message);

	}

	@Override
	public void createMessageInExistingThreadFromForm(MessageForm messageForm,
			MessageThread messageThread) {
		Message message = new Message();
		message.setDate(Calendar.getInstance().getTime());
		message.setWorkerBool(messageForm.isWorkerBool());
		message.setText(messageForm.getText());
		message.setReadBool(false);
		message.setMessageThread(messageThread);
		messageDao.create(message);
	}

	@Override
	public List<Message> findMessagesByMessageThreadIdSortedByTime(
			long MessageThreadId) {
		return messageDao.findMessagesByMessageThreadIdSortedByTime(MessageThreadId);
	}
	
	

}
