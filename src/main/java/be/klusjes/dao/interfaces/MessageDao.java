package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.Message;

public interface MessageDao {
	
	
	void create(Message message);
	Message read(Long id);
	void update(Message message);
	void deleteMessage(Long id);
	public List<Message> findMessagesByMessageThreadIdSortedByTime(long MessageThreadId);
}
