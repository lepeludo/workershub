package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.JobTypesDao;
import be.klusjes.entities.JobType;

@Repository

public class JobTypesDaoImpl implements JobTypesDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<JobType> findAll() {
		TypedQuery<JobType> query = entityManager.createNamedQuery(
				"findAllJobTypes", JobType.class);
		return query.getResultList();
	}

	@Override
	public JobType read(long id) {
		return entityManager.find(JobType.class, id);
	}

	@Override
	public void create(JobType jobType) {
		try {
			entityManager.persist(jobType);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}

	}

}
