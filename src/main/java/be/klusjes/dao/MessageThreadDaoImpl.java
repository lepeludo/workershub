package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.MessageThreadDao;
import be.klusjes.entities.MessageThread;

@Repository
public class MessageThreadDaoImpl implements MessageThreadDao{

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(MessageThread messageThread) {
		try {
			entityManager.persist(messageThread);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public List<MessageThread> readByWorkerId(Long workerId) {
		TypedQuery<MessageThread> query = entityManager.createNamedQuery(
				"findMessageThreadByWorkerId", MessageThread.class);
		query.setParameter("workerId", workerId);
		return query.getResultList();
	}

	@Override
	public List<MessageThread> readByCustomerId(Long customerId) {
		TypedQuery<MessageThread> query = entityManager.createNamedQuery(
				"findMessageThreadByCustomerId", MessageThread.class);
		query.setParameter("customerId", customerId);
		return query.getResultList();

	}

	@Override
	public MessageThread read(Long id) {
		return entityManager.find(MessageThread.class, id);
	}

	@Override
	public void update(MessageThread review) {
		try {
			entityManager.merge(review);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	

	@Override
	public void deleteMessageThread(Long id) {
			MessageThread review = entityManager.find(MessageThread.class, id);
			if (review != null) {
				entityManager.remove(review);
				entityManager.flush();
			}
		}
	
	public List<MessageThread> readByWorkerAndCustomerId(long workerId, long customerId){
		TypedQuery<MessageThread> query = entityManager.createNamedQuery(
				"readByWorkerAndCustomerId", MessageThread.class);
		query.setParameter("workerId", workerId);
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}
}
