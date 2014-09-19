package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.WorkersDao;
import be.klusjes.entities.Worker;

@Repository
public class WorkersDaoImpl implements WorkersDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void update(Worker worker) {
		try {
			entityManager.merge(worker);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public List<Worker> findWorkersByJobTypeAndLocation(List<Long> jobTypeId,
			List<Long> locationId) {
		TypedQuery<Worker> query = entityManager.createNamedQuery(
				"findWorkerByJobTypeAndLocation", Worker.class);
		query.setParameter("jobTypeId", jobTypeId);
		query.setParameter("locationId", locationId);
		return query.getResultList();
	}

	@Override
	public Worker read(Long id) {
		return entityManager.find(Worker.class, id);
	}

	@Override
	public List<Worker> findCustomersByText(String keyword) {
		TypedQuery<Worker> query = entityManager.createNamedQuery(
				"findWorkersByText", Worker.class);
		query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		return query.getResultList();
	}

	@Override
	public List<String> findAllWorkerEmails() {
		TypedQuery<String> query = entityManager.createNamedQuery(
				"findAllWorkerEmails", String.class);
		return query.getResultList();
	}

}
