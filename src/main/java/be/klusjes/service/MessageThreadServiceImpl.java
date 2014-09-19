package be.klusjes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.MessageThreadDao;
import be.klusjes.entities.Customer;
import be.klusjes.entities.Message;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Worker;
import be.klusjes.web.MessageThreadForm;

@Service
@Transactional
public class MessageThreadServiceImpl implements MessageThreadService {

	private MessageThreadDao messageThreadDao;

	@Autowired
	public MessageThreadServiceImpl(MessageThreadDao messageThreadDao) {
		this.messageThreadDao = messageThreadDao;
	}

	@Override
	public void create(MessageThread messageThread) {
		messageThreadDao.create(messageThread);

	}

	@Override
	public List<MessageThread> readByWorkerId(Long workerId) {
		return messageThreadDao.readByWorkerId(workerId);
	}

	@Override
	public List<MessageThread> readByCustomerId(Long customerId) {
		return messageThreadDao.readByCustomerId(customerId);
	}

	@Override
	public MessageThread read(Long id) {
		return messageThreadDao.read(id);
	}

	@Override
	public void update(MessageThread messageThread) {
		messageThreadDao.update(messageThread);

	}

	@Override
	public void deleteMessageThread(Long id) {
		messageThreadDao.deleteMessageThread(id);

	}

	@Override
	public List<MessageThread> readByWorkerAndCustomerId(long workerId,
			long customerId) {
		return messageThreadDao.readByWorkerAndCustomerId(workerId, customerId);
	}

	@Override
	public void createThreadFromForm(MessageThreadForm messageThreadForm,
			Customer customer, Worker worker) {
		MessageThread messageThread = new MessageThread();
		messageThread.setWorker(worker);
		messageThread.setCustomer(customer);
		messageThread.setSubject(messageThreadForm.getSubject());
		Message message = new Message();
		//message.setDate(Calendar.getInstance().getTime());
		message.setMessageThread(messageThread);
		message.setReadBool(false);
		message.setText(messageThreadForm.getText());
		message.setWorkerBool(false);
		message.setMessageThread(messageThread);
		messageThread.addMessage(message);
		messageThreadDao.create(messageThread);

	}

}
