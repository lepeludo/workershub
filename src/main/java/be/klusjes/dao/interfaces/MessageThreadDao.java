package be.klusjes.dao.interfaces;

import java.util.List;
import be.klusjes.entities.MessageThread;;

public interface MessageThreadDao {
	
	
	void create(MessageThread messageThread);
	List <MessageThread>  readByWorkerId (Long workerId);
	List< MessageThread>  readByCustomerId (Long customerId);
	MessageThread read(Long id);
	void update(MessageThread messageThread);
	void deleteMessageThread(Long id);
	public List<MessageThread> readByWorkerAndCustomerId(long workerId, long customerId);
}
