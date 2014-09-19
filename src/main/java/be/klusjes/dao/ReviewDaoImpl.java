package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.ReviewDao;
import be.klusjes.entities.Reviews;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Reviews review) {
		try {
			entityManager.persist(review);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public List<Reviews> readByWorkerId(Long workerId) {
		TypedQuery<Reviews> query = entityManager.createNamedQuery(
				"findReviewsByWorkerId", Reviews.class);
		query.setParameter("workerId", workerId);
		return query.getResultList();
	}

	@Override
	public List<Reviews> readByCustomerId(Long customerId) {
		TypedQuery<Reviews> query = entityManager.createNamedQuery(
				"findReviewsByCustomerId", Reviews.class);
		query.setParameter("customerId", customerId);
		return query.getResultList();

	}

	@Override
	public Reviews read(Long id) {
		return entityManager.find(Reviews.class, id);
	}

	@Override
	public void update(Reviews review) {
		try {
			entityManager.merge(review);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public List<Reviews> findUnreadReportedReviews() {
		TypedQuery<Reviews> query = entityManager.createNamedQuery(
				"findUnreadReportedReviews", Reviews.class);
		return query.getResultList();

	}

	@Override
	public void deleteReview(Long id) {
			Reviews review = entityManager.find(Reviews.class, id);
			if (review != null) {
				entityManager.remove(review);
				entityManager.flush();
			}
		}
}
