package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.MessageDao;
import be.klusjes.entities.Message;

@Repository
public class MessageDaoImpl implements MessageDao{

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Message message) {
		try {
			entityManager.persist(message);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}


	@Override
	public Message read(Long id) {
		return entityManager.find(Message.class, id);
	}

	@Override
	public void update(Message review) {
		try {
			entityManager.merge(review);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	

	@Override
	public void deleteMessage(Long id) {
			Message review = entityManager.find(Message.class, id);
			if (review != null) {
				entityManager.remove(review);
				entityManager.flush();
			}
		}
	
	@Override
	public List<Message> findMessagesByMessageThreadIdSortedByTime(long MessageThreadId){
		TypedQuery<Message> query = entityManager.createNamedQuery(
				"findMessagesByMessageThreadIdSortedByTime", Message.class);
		query.setParameter("MessageThreadId", MessageThreadId);
		return query.getResultList();
	}

}
